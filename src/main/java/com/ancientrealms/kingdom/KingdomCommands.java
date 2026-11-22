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

import java.util.Set;
import java.util.UUID;

public final class KingdomCommands {
    // Scoreboard entries are persisted by Minecraft with the world scoreboard.
    // Reserved fake-player entries store kingdom metadata without an in-memory map.
    private static final String INVITE_PREFIX = "#ari";
    private static final String OWNER_PREFIX = "#aro";
    private static final String WAR_PREFIX = "#arw";

    private KingdomCommands() {}

    private static String marker(String prefix, UUID id) {
        return prefix + id.toString().replace("-", "");
    }

    private static String warMarker(String otherTeam) {
        return WAR_PREFIX + otherTeam;
    }

    private static boolean isMetadata(String entry) {
        return entry.startsWith("#ar");
    }

    private static int realMemberCount(PlayerTeam team) {
        return (int) team.getPlayers().stream().filter(p -> !isMetadata(p)).count();
    }

    private static boolean isOwner(PlayerTeam team, UUID id) {
        return team.getPlayers().contains(marker(OWNER_PREFIX, id));
    }

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
                .then(Commands.literal("transfer")
                    .then(Commands.argument("player", EntityArgument.player())
                        .executes(context -> transfer(context.getSource().getPlayerOrException(),
                            EntityArgument.getPlayer(context, "player"),
                            context.getSource().getServer().getScoreboard()))))
                .then(Commands.literal("declare-war")
                    .then(Commands.argument("kingdom", StringArgumentType.word())
                        .executes(context -> setWar(context.getSource().getPlayerOrException(),
                            context.getSource().getServer().getScoreboard(),
                            StringArgumentType.getString(context, "kingdom"), true))))
                .then(Commands.literal("peace")
                    .then(Commands.argument("kingdom", StringArgumentType.word())
                        .executes(context -> setWar(context.getSource().getPlayerOrException(),
                            context.getSource().getServer().getScoreboard(),
                            StringArgumentType.getString(context, "kingdom"), false))))
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
        scoreboard.addPlayerToTeam(marker(OWNER_PREFIX, player.getUUID()), team);
        player.sendSystemMessage(Component.literal("Kingdom founded: " + name + ". You are its leader.")
            .withStyle(ChatFormatting.GOLD));
        return 1;
    }

    private static int invite(ServerPlayer inviter, ServerPlayer target, Scoreboard scoreboard) {
        PlayerTeam team = scoreboard.getPlayersTeam(inviter.getScoreboardName());
        if (team == null || !isOwner(team, inviter.getUUID())) {
            inviter.sendSystemMessage(Component.literal("Only your kingdom leader can invite players.")
                .withStyle(ChatFormatting.RED));
            return 0;
        }
        if (scoreboard.getPlayersTeam(target.getScoreboardName()) != null) {
            inviter.sendSystemMessage(Component.literal(target.getName().getString() + " already belongs to a kingdom."));
            return 0;
        }
        String invite = marker(INVITE_PREFIX, target.getUUID());
        if (team.getPlayers().contains(invite)) {
            inviter.sendSystemMessage(Component.literal("That player already has a pending invitation."));
            return 0;
        }
        scoreboard.addPlayerToTeam(invite, team);
        inviter.sendSystemMessage(Component.literal("Invitation saved for " + target.getName().getString() + ".")
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
        String invite = marker(INVITE_PREFIX, player.getUUID());
        if (team == null || !team.getPlayers().contains(invite)) {
            player.sendSystemMessage(Component.literal("No saved invitation found for that kingdom.")
                .withStyle(ChatFormatting.RED));
            return 0;
        }
        scoreboard.removePlayerFromTeam(invite, team);
        scoreboard.addPlayerToTeam(player.getScoreboardName(), team);
        player.sendSystemMessage(Component.literal("You joined kingdom " + name + "!")
            .withStyle(ChatFormatting.GOLD));
        return 1;
    }

    private static int transfer(ServerPlayer leader, ServerPlayer target, Scoreboard scoreboard) {
        PlayerTeam team = scoreboard.getPlayersTeam(leader.getScoreboardName());
        if (team == null || !isOwner(team, leader.getUUID())) {
            leader.sendSystemMessage(Component.literal("Only the current leader can transfer leadership."));
            return 0;
        }
        if (scoreboard.getPlayersTeam(target.getScoreboardName()) != team) {
            leader.sendSystemMessage(Component.literal("The new leader must already be a member of your kingdom."));
            return 0;
        }
        scoreboard.removePlayerFromTeam(marker(OWNER_PREFIX, leader.getUUID()), team);
        scoreboard.addPlayerToTeam(marker(OWNER_PREFIX, target.getUUID()), team);
        leader.sendSystemMessage(Component.literal("Leadership transferred to " + target.getName().getString() + "."));
        target.sendSystemMessage(Component.literal("You are now the leader of " + team.getName() + ".")
            .withStyle(ChatFormatting.GOLD));
        return 1;
    }

    private static int setWar(ServerPlayer player, Scoreboard scoreboard, String otherName, boolean declare) {
        PlayerTeam own = scoreboard.getPlayersTeam(player.getScoreboardName());
        PlayerTeam other = scoreboard.getPlayerTeam(otherName);
        if (own == null || !isOwner(own, player.getUUID())) {
            player.sendSystemMessage(Component.literal("Only a kingdom leader can change diplomacy."));
            return 0;
        }
        if (other == null || other == own) {
            player.sendSystemMessage(Component.literal("Choose a different existing kingdom."));
            return 0;
        }
        String ownMarker = warMarker(own.getName());
        String otherMarker = warMarker(other.getName());
        if (declare) {
            scoreboard.addPlayerToTeam(otherMarker, own);
            scoreboard.addPlayerToTeam(ownMarker, other);
            player.sendSystemMessage(Component.literal("Your kingdom has declared war on " + other.getName() + ".")
                .withStyle(ChatFormatting.RED));
        } else {
            scoreboard.removePlayerFromTeam(otherMarker, own);
            scoreboard.removePlayerFromTeam(ownMarker, other);
            player.sendSystemMessage(Component.literal("Peace declared with " + other.getName() + ".")
                .withStyle(ChatFormatting.GREEN));
        }
        return 1;
    }

    private static int leave(ServerPlayer player, Scoreboard scoreboard) {
        PlayerTeam team = scoreboard.getPlayersTeam(player.getScoreboardName());
        if (team == null) {
            player.sendSystemMessage(Component.literal("You are not in a kingdom."));
            return 0;
        }
        if (isOwner(team, player.getUUID()) && realMemberCount(team) > 1) {
            player.sendSystemMessage(Component.literal("Transfer leadership before leaving your kingdom.")
                .withStyle(ChatFormatting.RED));
            return 0;
        }
        scoreboard.removePlayerFromTeam(player.getScoreboardName(), team);
        scoreboard.removePlayerFromTeam(marker(OWNER_PREFIX, player.getUUID()), team);
        scoreboard.removePlayerFromTeam(marker(INVITE_PREFIX, player.getUUID()), team);
        if (realMemberCount(team) == 0) scoreboard.removePlayerTeam(team);
        player.sendSystemMessage(Component.literal("You left kingdom " + team.getName() + "."));
        return 1;
    }

    private static int info(ServerPlayer player, Scoreboard scoreboard) {
        PlayerTeam team = scoreboard.getPlayersTeam(player.getScoreboardName());
        if (team == null) {
            player.sendSystemMessage(Component.literal("You do not belong to a kingdom. Create one with /kingdom create <name>."));
            return 0;
        }
        String role = isOwner(team, player.getUUID()) ? "Leader" : "Member";
        Set<String> entries = team.getPlayers();
        long wars = entries.stream().filter(e -> e.startsWith(WAR_PREFIX)).count();
        player.sendSystemMessage(Component.literal("Kingdom: " + team.getName() + " | Role: " + role
            + " | Members: " + realMemberCount(team) + " | Active wars: " + wars)
            .withStyle(ChatFormatting.GOLD));
        return 1;
    }
}
