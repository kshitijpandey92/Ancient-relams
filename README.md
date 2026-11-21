# Ancient Realms: Rise of the Dragon Kingdoms

A Fabric mod project targeting Minecraft Java 26.2 and Java 25.

**Mod ID:** `ancientrealms`  
**Package:** `com.ancientrealms`

## Current build

The repository contains a buildable foundation and an expanding content implementation:
- 17 progression materials and four sword tiers.
- Seven registered ore blocks with models, loot tables, mining tags, and smelting recipes.
- A dedicated Ancient Realms creative tab.
- JSON configuration for progression-related tuning.

**Important:** This is not yet the complete playable mod. Natural ore generation, eight functional crafting stations, full equipment/progression, original textures, dragons, kingdoms, and in-game verification remain outstanding. See [DEVELOPMENT_STATUS.md](DEVELOPMENT_STATUS.md) and [TODO_ASSETS.md](TODO_ASSETS.md).

## Build and CI

GitHub Actions builds the project with Java 25 and Gradle 9.8.0 on pushes and pull requests. Open the [Actions page](https://github.com/kshitijpandey92/Ancient-relams/actions) to see the latest build result and download the `ancientrealms-fabric-26.2` artifact when a run succeeds.

If you have a Gradle installation in your development environment, run:

```bash
gradle build
```

## Project specification

See [MASTER_PROMPT.md](MASTER_PROMPT.md) for the intended complete feature scope.
