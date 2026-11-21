# Development Status

## Target
- Minecraft Java 26.2
- Fabric only
- Java 25
- Mod ID: `ancientrealms`
- Main package: `com.ancientrealms`

## Implemented in the repository
- [x] Gradle + Fabric Loom project and GitHub Actions build workflow.
- [x] Main and client entrypoints.
- [x] JSON config loader with bounded values for dragon growth, weapon XP, particle limits, and world-event frequency.
- [x] Shared item and block registration helpers using 26.2 resource keys and item/block properties.
- [x] 17 progression materials registered (Mythril, Adamantite, Dragonsteel, Soulstone, Void Crystal, Celestial Gold, Blood Crystal, Ancient Essence, Frost Core, Thunder Core, Infernal Shard, Dragon Heart, Crystal Dust, Eternal Fragment, Divine Essence, Ancient Dragon Scale, World Core).
- [x] Mythril, Adamantite, Dragonsteel, and Celestial Gold sword items registered.
- [x] Dedicated Ancient Realms creative tab.
- [x] Seven ore blocks registered with blockstates, block/item models, loot tables, and pickaxe/iron-tool tags.
- [x] Basic smelting recipes for the seven ores.
- [x] Crafting recipes for Mythril, Adamantite, Dragonsteel, and Celestial Gold swords.
- [x] English names for registered items, blocks, and the creative tab.

## Verification
- GitHub Actions run [44890423469](https://github.com/kshitijpandey92/Ancient-relams/actions/runs/44890423469) passed Gradle build and uploaded its mod JAR at commit `98ed4daf992bbdbab75f5cf087b06973c0476611`.
- Later ore-registration changes were still being verified when this file was updated. Check the Actions page for the latest commit result.
- No Minecraft client launch, dedicated-server launch, world-generation, or save/reload test has been recorded.

## Not complete yet
- [ ] Original custom PNG textures and mod icon. Ore blocks currently reuse vanilla textures; item models reference missing custom textures.
- [ ] Natural ore world generation and biome placement.
- [x] Eight themed workstation blocks with a working vanilla 3x3 crafting-table interface. Custom station-specific menus, processing, and progression gates remain outstanding.
- [ ] Full tier equipment sets, tools, armor, and complete progression/advancement tree.
- [x] Three registered hostile dragon species with custom multipart 3D models, animated wings/tails/legs, separate textures, attributes, and AI. Flight, breath weapons, taming, growth stages, nests, dragon drops, kingdom systems, and world events remain outstanding.
- [ ] In-game client/server testing and save/load verification.

This is an active development build, not yet the complete playable mod described in the master specification. Do not treat unchecked items as implemented.
