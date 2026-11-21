# Kingdom System

Kingdom membership is stored in Minecraft's world-persistent scoreboard teams.

- `/kingdom create <name>` creates a kingdom and marks its founder for the current server session.
- `/kingdom invite <player>` invites an online player.
- `/kingdom join <name>` accepts that player's pending invitation.
- `/kingdom leave` leaves a kingdom. A founder cannot leave while other members remain.
- `/kingdom info` reports the kingdom, founder/member role when known in the current session, and member count.
- Names must be 3-16 letters, numbers or underscores; kingdom names must be unique.
- Friendly fire is disabled for members of the same team.

Membership and teams are saved with the world. Invitations and founder-session tracking currently live only until the server restarts. Leadership transfer, ranks, diplomacy, claimed territory, generated settlements/castles, NPC citizens, quests, raids and wars are still outstanding.
