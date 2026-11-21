# Development Status

## Target
- Minecraft Java 26.2
- Fabric only
- Java 25
- Mod ID: `ancientrealms`

## Implemented in the repository
- Gradle/Fabric Loom project, Java 25 and GitHub Actions build workflow.
- Main/client entrypoints, JSON configuration, item/block registration and a dedicated creative tab.
- Progression materials, four sword tiers and seven overworld ore types with world-generation data.
- Three hostile dragon species with registered attributes, melee AI, custom multipart 3D models, animated wings/tails/legs and elemental combat strikes.
- Dragon interaction loop: craft Dragon Treat, bond with a wild dragon, feed treats to restore 12 health, apply a Dragon Saddle and mount it as a passenger.
- Dragon egg item and a separate incubating egg nest block that advances through random ticks and hatches an Ember Dragon; kingdom sigil and Dragon Treat items, recipes, models and progression advancements.
- Eight themed station blocks with assets and native crafting-table interfaces.

## Important limitations (not yet complete)
- Dragon bonding and saddle status are persisted in entity save data and restored on reload.
- Passenger mounting and rider-controlled flight movement are implemented; pitch controls climb/descent and movement inputs steer the dragon.
- Dragon owner and saddle state are persisted through Minecraft entity save/load hooks.
- The incubating egg nest currently hatches an adult Ember Dragon after three incubation stages. Juvenile hatchling forms, selectable species and dragon growth stages remain unimplemented.
- The eight stations still use the vanilla crafting-table interface; they do not have individual block entities, processing queues or gated custom menus.
- Kingdom sigil is a progression/crafting item, not a complete faction, settlement or NPC kingdom simulation.
- Quests, structures, raids and scheduled world events are not implemented.
- Many assets still use vanilla placeholder textures.
- The latest GitHub Actions Gradle build passed compilation and packaging. No Minecraft client/server launch or in-world save/reload test has been performed.

## Verification
- GitHub Actions builds are used to verify compilation and packaging. Check the latest workflow run in the repository Actions tab for the current commit's result.
- A successful Gradle build is not equivalent to in-game gameplay verification.

This is an actively developed mod build, not yet the full feature set originally requested.
