# Development Status

## Target
- Minecraft Java 26.2, Fabric Loader, Java 25.
- Common gameplay code is in `src/main`; renderers are in `src/client`.
- Mod environment is `*`; install the same mod build on the client and dedicated server.

## Implemented
- Fabric project, client/common entrypoints, JSON configuration, item/block registration, creative tab, recipes, loot tables and advancements.
- Seven custom overworld ore types and rare Ember, Frost and Storm egg-nest features.
- Three dragon species with custom 3D models/textures, AI, elemental combat, bonding, treat healing, saddles, rider-controlled flight, saved ownership/growth and egg incubation.
- Eight themed station blocks (currently use vanilla crafting-table menus).
- Scoreboard-based kingdoms with create/info/invite/join/leave commands. Membership is saved by the world scoreboard; pending invitations and founder role tracking are currently in-memory and reset on server restart.
- An automatically generated ruined stone keep structure in the overworld. It contains four corner towers, a gate, battlements, an inner dais, lights, a chest and two vanilla villager residents (weaponsmith and cleric). It is placed through the world-generation structure set in newly generated chunks.

## Still not complete or runtime-verified
- Full kingdom simulation: saved leadership/ranks, diplomacy, claimed territory, NPC-specific guard/merchant/king AI, raids and wars.
- Quest journal/objectives and story progression.
- Custom station block entities, menus, timed processing, upgrades and progression gates.
- Dragon armor, visible breath projectiles, juvenile model stages and richer flight AI.
- Full castle/city/settlement variety, loot balancing, structures across every biome, polished custom textures/audio/particles.
- Dedicated-server startup, client join, multiplayer synchronization, and in-game world-generation/hatching/flight testing have not been performed. A successful Gradle compile is not proof of these runtime behaviors.

This repository is an active implementation and must not be described as the finished full-scope release until the remaining systems and runtime checks are complete.
