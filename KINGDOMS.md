# Kingdom System

Kingdom membership is stored in Minecraft's world-persistent scoreboard teams and player data.

- `/kingdom create <name>` creates a kingdom and marks its founder.
- `/kingdom invite <player>` sends an invitation to an online player. Invitations are stored on the invited player's persistent entity data.
- `/kingdom join <name>` accepts a pending invitation.
- `/kingdom leave` leaves a kingdom. A founder cannot leave while other members remain, preventing an ownerless team.
- `/kingdom info` reports the kingdom, the player's founder/member role, and member count.
- Names must be 3-16 letters, numbers or underscores; kingdom names must be unique.
- Friendly fire is disabled for members of the same team.

This is a playable faction foundation, not a complete kingdom simulation. Leadership transfer, ranks, diplomacy, claimed territory, generated settlements/castles, NPC citizens, quests, raids and wars are still outstanding.
