# Ancient Realms: Rise of the Dragon Kingdoms

Fabric mod targeting Minecraft Java 26.2 and Java 25.

## Installation target
The mod is designed for both client and dedicated server. Install the same compatible mod build and required Fabric dependencies on the server and every connecting client. Common gameplay code is separated from client-only renderers.

## Current features
- Mythril and Adamantite Overworld ore generation; the five higher-tier ores are registered as data resources but are not placed in the Overworld.
- Ember, Frost and Storm dragons with 3D models, textures, combat abilities, bonding, healing, saddles, flight and hatchling growth.
- Rare automatically generated ruined keeps and a larger royal castle in newly generated overworld chunks, with towers, gates, battlements, furnishings and loot containers.
- Eight themed crafting stations (currently vanilla crafting-table interfaces).
- Scoreboard kingdoms with create, invite, join, leave, info, leadership transfer, and persistent war/peace declarations. Invitation and leader markers are stored in the world scoreboard.
- Reversible 9-to-1 storage blocks for all 17 core progression materials, with block models, item models, loot tables and recipes generated into the distributable build.\n- Recipes, loot tables, advancements, configuration and a creative tab.

## Dragon commands
```
/summon ancientrealms:ember_dragon
/summon ancientrealms:frost_dragon
/summon ancientrealms:storm_dragon
```

## Build
GitHub Actions builds the mod with Java 25. The workflow generates the royal castle NBT template and validates resource JSON and referenced structure templates before compiling. See the [Actions page](https://github.com/kshitijpandey92/Ancient-relams/actions) for the latest build and artifact.

## Status
This is not yet the finished full-scope release. Persistent kingdom leadership/invitations, NPC AI, quests, diplomacy, raids/wars, custom station processing, additional settlement variety, final assets, and dedicated-server/multiplayer in-game verification remain outstanding. See [DEVELOPMENT_STATUS.md](DEVELOPMENT_STATUS.md), [DRAGONS.md](DRAGONS.md), [KINGDOMS.md](KINGDOMS.md) and [STATIONS.md](STATIONS.md).
