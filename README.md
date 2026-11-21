# Ancient Realms: Rise of the Dragon Kingdoms

A Fabric mod targeting Minecraft Java 26.2 and Java 25.

## Client and dedicated-server support

Ancient Realms is designed to run on both the Minecraft client and a dedicated Fabric server. Install the mod and its required Fabric dependencies on the server and on every connecting client using the same compatible mod version.

- **Server/common side:** world generation, blocks, items, dragon entities and AI, kingdom data/commands, incubation, growth, combat and gameplay logic.
- **Client side:** dragon models, textures, renderers, animation and other visual presentation.
- **Multiplayer:** gameplay state is authoritative on the server and shared with connected clients.

The project uses Fabric Loom split client/common source sets and declares the mod environment as `*`. Dedicated-server launch and multiplayer join testing are still required before claiming runtime verification.

## Current content
- 17 progression materials and four sword tiers.
- Seven custom ores with overworld world-generation data, plus rare naturally generated dragon egg nests that can hatch Ember hatchlings.
- Three distinct dragons: Ember, Frost and Storm, each with a custom animated multipart 3D model, elemental combat behavior and its own texture.
- Bonding, treat-based healing, saddles, rider-controlled flight and incubating species-specific dragon eggs.
- Eight themed crafting workstations using Minecraft's native 3x3 crafting interface.
- Persistent scoreboard-based kingdoms with online invitations, joining, leaving and member inspection; invitation/founder session state currently resets on server restart.
- Dedicated creative tab and JSON configuration.

## Dragon summoning
```
/summon ancientrealms:ember_dragon
/summon ancientrealms:frost_dragon
/summon ancientrealms:storm_dragon
```

## Build
GitHub Actions builds the mod with Java 25. See the [Actions page](https://github.com/kshitijpandey92/Ancient-relams/actions) for the latest result and downloadable `ancientrealms-fabric-26.2` artifact.

## Important scope note
This is not yet the fully finished mod. Custom workstation processing/progression, complete kingdom diplomacy and settlements, NPCs, structures, quests, raids, full equipment sets, final assets, and in-game client/dedicated-server verification remain in development. See [DEVELOPMENT_STATUS.md](DEVELOPMENT_STATUS.md), [DRAGONS.md](DRAGONS.md), and [STATIONS.md](STATIONS.md).
