# Development Status

## Target and runtime support
- Minecraft Java 26.2
- Fabric only
- Java 25
- Mod ID: `ancientrealms`
- Client and dedicated-server target: `environment: "*"` with Fabric Loom split common/client source sets.
- Common/server gameplay code is in `src/main`; renderers and other client-only presentation code are in `src/client`.
- The mod must be installed on both a dedicated server and each connecting client. Dedicated-server startup and multiplayer connection tests remain outstanding.

## Implemented in the repository
- Gradle/Fabric Loom project, Java 25 and GitHub Actions build workflow.
- Main/client entrypoints, JSON configuration, item/block registration and a dedicated creative tab.
- Progression materials, four sword tiers and seven overworld ore types with world-generation data.
- Three hostile dragon species with registered attributes, melee AI, custom multipart 3D models, animated wings/tails/legs and elemental combat strikes.
- Dragon interaction loop: craft Dragon Treat, bond with a wild dragon, feed treats to restore 12 health, apply a Dragon Saddle and mount it as a passenger.
- Placeable dragon egg items and an incubating nest block with Ember, Frost and Storm species selection; elemental egg crafting recipes; kingdom sigil and Dragon Treat items, recipes, models and progression advancements.
- Eight themed station blocks with assets and native crafting-table interfaces.
- Basic persistent kingdom teams through `/kingdom create <name>` and `/kingdom info`.

## Important limitations (not yet complete)
- Dragon bonding and saddle status are persisted in entity save data and restored on reload.
- Passenger mounting and rider-controlled flight movement are implemented; pitch controls climb/descent and movement inputs steer the dragon.
- The incubating egg nest hatches the selected species after three incubation stages. Hatchlings emerge at 35% scale and grow to adult size over 72,000 game ticks; growth progress is persisted and uses the `dragonGrowthSpeed` config setting.
- The eight stations still use the vanilla crafting-table interface; they do not have individual block entities, processing queues or gated custom menus.
- Kingdom diplomacy, invitations, ranks, settlements, NPCs, territory and raids are not implemented.
- Quests, structures, raids and scheduled world events are not implemented.
- Many assets still use vanilla placeholder textures.
- Compilation/package checks do not prove the mod starts on a dedicated server or works in a multiplayer world. Those runtime checks remain outstanding.

## Verification
- Check the repository Actions tab for the current commit's Gradle build result.
- A successful Gradle build is not equivalent to dedicated-server startup, client connection, or in-game gameplay verification.

This is an actively developed mod build, not yet the full feature set originally requested.
