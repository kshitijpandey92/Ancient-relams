# Development Status

## Target
- Minecraft Java 26.2
- Fabric only
- Java 25
- Mod ID: `ancientrealms`

## Implemented
- [x] Gradle/Fabric Loom project, Java 25, GitHub Actions artifact build.
- [x] Main/client entrypoints, JSON configuration and shared item/block registration.
- [x] 17 progression materials and four sword tiers.
- [x] Dedicated Ancient Realms creative tab.
- [x] Seven ore blocks with models, blockstates, item models, loot tables, mining tags, smelting recipes, configured/placed worldgen features, and overworld biome injection.
- [x] Three hostile dragon species (Ember, Frost, Storm) with registered attributes, melee AI, rare overworld spawns, distinct textures, custom multipart 3D models, animated wings/tail/legs, and elemental combat effects.
- [x] Eight themed workstation blocks with blockstates, models, item models, loot tables, translations and working vanilla 3x3 crafting-table interfaces.

## Verification
- GitHub Actions run [44897257809](https://github.com/kshitijpandey92/Ancient-relams/actions/runs/44897257809) passed Gradle build and uploaded a JAR for commit `01f6c4497ad0e244d05f99f4b56bc13573318f86`.
- Subsequent ore-worldgen, elemental combat, natural dragon spawn and workstation resource commits are awaiting their own final CI result.
- No actual Minecraft client launch, dedicated-server launch, world-generation inspection, or save/reload test has been performed.

## Remaining work
- [ ] Full dragon flight AI and player riding/taming, growth stages, eggs/nests, breath projectile effects, and unique loot.
- [ ] Custom station block entities, menus, recipes, timed processing and progression gates (current stations use the native crafting-table screen).
- [ ] Complete tiered tools, armor, ranged weapons and balanced progression/advancements.
- [ ] Dragon kingdoms, NPC factions, structures, quests and world events.
- [ ] Original art for all material/sword/ore/station assets (dragon textures exist; ore and station blocks currently use vanilla texture placeholders).
- [ ] In-game client/server testing and save/load verification.

This is an expanded development build, not yet the complete feature set originally specified.
