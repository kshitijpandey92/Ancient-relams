package com.ancientrealms.kingdom;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;

public final class KingdomCommands {
    private KingdomCommands() {}

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
            dispatcher.register(Commands.literal("kingdom")
                .then(Commands.literal("create")
                    .then(Commands.argument("name", StringArgumentType.word())
                        .executes(context -> create(context.getSource().getPlayerOrException(),
                            StringArgumentType.getString(context, "name")))))
                .then(Commands.literal("info")
                    .executes(context -> info(context.getSource().getPlayerOrException())))
            )
        );
    }

    private static int create(ServerPlayer player, String name) {
        if (!name.matches("[A-Za-z0-9_]{3,16}")) {
            player.sendSystemMessage(Component.literal("Kingdom names must be 3-16 letters, numbers or underscores."));
            return 0;
        }
        Scoreboard scoreboard = player.serverLevel().getServer().getScoreboard();
        if (scoreboard.getPlayersTeam(player.getScoreboardName()) != null) {
            player.sendSystemMessage(Component.literal("You already belong to a kingdom.").withStyle(ChatFormatting.RED));
            return 0;
        }
        if (scoreboard.getPlayerTeam(name) != null) {
            player.sendSystemMessage(Component.literal("That kingdom name is already taken.").withStyle(ChatFormatting.RED));
            return 0;
        }
        PlayerTeam team = scoreboard.addPlayerTeam(name);
        team.setDisplayName(Component.literal(name));
        team.setAllowFriendlyFire(false);
        scoreboard.addPlayerToTeam(player.getScoreboardName(), team);
        player.sendSystemMessage(Component.literal("Kingdom founded: " + name + ". Your kingdom is saved with this world.").withStyle(ChatFormatting.GOLD));
        return 1;
    }

    private static int info(ServerPlayer player) {
        PlayerTeam team = player.getServer().getScoreboard().getPlayersTeam(player.getScoreboardName());
        if (team == null) {
            player.sendSystemMessage(Component.literal("You do not belong to a kingdom. Create one with /kingdom create <name>."));
            return 0;
        }
        player.sendSystemMessage(Component.literal("Kingdom: " + team.getName() + " | Members: " + team.getPlayers().size())
            .withStyle(ChatFormatting.GOLD));
        return 1;
    }
}
