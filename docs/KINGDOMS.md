# Kingdoms: commands and persistence

## Implemented command surface
- `/kingdom create <name>` — create a scoreboard team and become its leader.
- `/kingdom invite <player>` — leader issues a world-persistent invitation.
- `/kingdom join <name>` — accept the invitation.
- `/kingdom transfer <player>` — transfer leadership to an existing member.
- `/kingdom leave` — leave, subject to the leader-transfer rule.
- `/kingdom info` — show kingdom name, role, member count and declared-war count.
- `/kingdom declare-war <kingdom>` and `/kingdom peace <kingdom>` — record reciprocal diplomacy markers in the persistent scoreboard.

Invitations, leader identity and war declarations use reserved fake scoreboard entries, which are saved with the world scoreboard rather than held only in static memory.

## Current limitations
- Diplomacy is a persistent declaration/status system; it does not yet implement territory control, siege logic, AI armies, combat modifiers or automated raids.
- There is no alliance/treaty negotiation UI.
- Leader transfer and save/reload behavior compile, but still require dedicated-server runtime testing.
- Scoreboard metadata entries are internal bookkeeping and should not be manually removed.

## Verification
The source compiles in GitHub Actions. No in-game restart or multiplayer test has yet verified persistence.
