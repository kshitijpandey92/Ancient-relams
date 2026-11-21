package com.ancientrealms.kingdom;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;

public final class KingdomCommands {
    private static final String INVITE_PREFIX = "ancientrealms_invite_";

    private KingdomCommands() {}

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
            dispatcher.register(Commands.literal("kingdom")
                .then(Commands.literal("create")
                    .then(Commands.argument("name", StringArgumentType.word())
                        .executes(context -> create(context.getSource().getPlayerOrException(),
                            context.getSource().getServer().getScoreboard(),
                            StringArgumentType.getString(context, "name")))))
                .then(Commands.literal("info")
                    .executes(context -> info(context.getSource().getPlayerOrException(),
                        context.getSource().getServer().getScoreboard())))
                .then(Commands.literal("invite")
                    .then(Commands.argument("player", EntityArgument.player())
                        .executes(context -> invite(context.getSource().getPlayerOrException(),
                            EntityArgument.getPlayer(context, "player"),
                            context.getSource().getServer().getScoreboard()))))
                .then(Commands.literal("join")
                    .then(Commands.argument("name", StringArgumentType.word())
                        .executes(context -> join(context.getSource().getPlayerOrException(),
                            context.getSource().getServer().getScoreboard(),
                            StringArgumentType.getString(context, "name")))))
                .then(Commands.literal("leave")
                    .executes(context -> leave(context.getSource().getPlayerOrException(),
                        context.getSource().getServer().getScoreboard())))
            )
        );
    }

    private static int create(ServerPlayer player, Scoreboard scoreboard, String name) {
        if (!name.matches("[A-Za-z0-9_]{3,16}")) {
            player.sendSystemMessage(Component.literal("Kingdom names must be 3-16 letters, numbers or underscores."));
            return 0;
        }
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
        player.addTag(founderTag(name));
        player.sendSystemMessage(Component.literal("Kingdom founded: " + name + ". You are its founder.")
            .withStyle(ChatFormatting.GOLD));
        return 1;
    }

    private static int invite(ServerPlayer inviter, ServerPlayer target, Scoreboard scoreboard) {
        PlayerTeam team = scoreboard.getPlayersTeam(inviter.getScoreboardName());
        if (team == null) {
            inviter.sendSystemMessage(Component.literal("Create or join a kingdom before inviting players."));
            return 0;
        }
        if (scoreboard.getPlayersTeam(target.getScoreboardName()) != null) {
            inviter.sendSystemMessage(Component.literal(target.getName().getString() + " already belongs to a kingdom."));
            return 0;
        }
        String tag = inviteTag(team.getName());
        if (!target.addTag(tag)) {
            inviter.sendSystemMessage(Component.literal("That player already has a pending invitation."));
            return 0;
        }
        inviter.sendSystemMessage(Component.literal("Invitation sent to " + target.getName().getString() + ".")
            .withStyle(ChatFormatting.GREEN));
        target.sendSystemMessage(Component.literal("You were invited to " + team.getName()
            + ". Join with /kingdom join " + team.getName() + ".").withStyle(ChatFormatting.GOLD));
        return 1;
    }

    private static int join(ServerPlayer player, Scoreboard scoreboard, String name) {
        if (scoreboard.getPlayersTeam(player.getScoreboardName()) != null) {
            player.sendSystemMessage(Component.literal("Leave your current kingdom before joining another."));
            return 0;
        }
        PlayerTeam team = scoreboard.getPlayerTeam(name);
        if (team == null || !player.getTags().contains(inviteTag(name))) {
            player.sendSystemMessage(Component.literal("No invitation found for that kingdom.")
                .withStyle(ChatFormatting.RED));
            return 0;
        }
        player.removeTag(inviteTag(name));
        scoreboard.addPlayerToTeam(player.getScoreboardName(), team);
        player.sendSystemMessage(Component.literal("You joined kingdom " + name + "!")
            .withStyle(ChatFormatting.GOLD));
        return 1;
    }

    private static int leave(ServerPlayer player, Scoreboard scoreboard) {
        PlayerTeam team = scoreboard.getPlayersTeam(player.getScoreboardName());
        if (team == null) {
            player.sendSystemMessage(Component.literal("You are not in a kingdom."));
            return 0;
        }
        String name = team.getName();
        if (player.getTags().contains(founderTag(name)) && team.getPlayers().size() > 1) {
            player.sendSystemMessage(Component.literal("As founder, transfer leadership before leaving. (Leadership transfer is not available yet.)")
                .withStyle(ChatFormatting.RED));
            return 0;
        }
        scoreboard.removePlayerFromTeam(player.getScoreboardName(), team);
        player.removeTag(founderTag(name));
        player.removeTag(inviteTag(name));
        if (team.getPlayers().isEmpty()) scoreboard.removePlayerTeam(team);
        player.sendSystemMessage(Component.literal("You left kingdom " + name + "."));
        return 1;
    }

    private static int info(ServerPlayer player, Scoreboard scoreboard) {
        PlayerTeam team = scoreboard.getPlayersTeam(player.getScoreboardName());
        if (team == null) {
            player.sendSystemMessage(Component.literal("You do not belong to a kingdom. Create one with /kingdom create <name>."));
            return 0;
        }
        String role = player.getTags().contains(founderTag(team.getName())) ? "Founder" : "Member";
        player.sendSystemMessage(Component.literal("Kingdom: " + team.getName() + " | Role: " + role
            + " | Members: " + team.getPlayers().size()).withStyle(ChatFormatting.GOLD));
        return 1;
    }

    private static String inviteTag(String kingdom) {
        return INVITE_PREFIX + kingdom;
    }

    private static String founderTag(String kingdom) {
        return "ancientrealms_founder_" + kingdom;
    }
}
