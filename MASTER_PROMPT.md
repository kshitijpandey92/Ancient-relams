# Master Project Prompt — Ancient Realms: Rise of the Dragon Kingdoms

Build a complete, maintainable Minecraft Java mod named **Ancient Realms: Rise of the Dragon Kingdoms**.

## Non-negotiable technical constraints
- Target Minecraft Java **26.2**. Never silently downgrade the Minecraft version.
- Use **Fabric only**. Do not use Forge or NeoForge.
- Use Java **25**.
- Mod ID: `ancientrealms`.
- Base package: `com.ancientrealms`.
- Main entrypoint: `com.ancientrealms.AncientRealms`.
- Keep client-only code in the client source set.
- Do not commit Gradle caches, build outputs, run directories, Minecraft jars, or Mojang files.
- Keep the repository buildable after every phase. Do not hide or suppress errors.
- Update `DEVELOPMENT_STATUS.md` with exact work and honest test results after each phase.
- Commit coherent phases with descriptive messages.

## Project vision
Create a fantasy progression mod centered on dragons, ancient kingdoms, rare materials, weapons, crafting stations, world exploration, and a staged equipment progression.

## Phase 1 — Foundation
1. Establish Gradle/Fabric project configuration and a working Gradle wrapper.
2. Add Fabric metadata, main and client entrypoints, a centralized registry helper, and a safe JSON config.
3. Config keys: `dragonGrowthSpeed`, `weaponXpMultiplier`, `maxParticlesPerEffect`, `worldEventFrequency`.
4. Add weapon rarity tiers: Common, Uncommon, Rare, Epic, Legendary, Mythical, Divine, Ancient, Eternal. Keep multipliers capped and validated.
5. Register Mythril Ingot and Mythril Sword, creative-tab entries, item definitions/models, translations, sword recipe, and repair tag.
6. Add required textures and mod icon, or clearly list genuine art tasks in `TODO_ASSETS.md`.
7. Verify Gradle build, client launch, and dedicated-server launch where the environment permits. Never claim tests that were not run.

## Phase 2 — Materials, ores, stations, progression
Add these 17 materials:
- Mythril Ingot
- Adamantite Ingot
- Dragonsteel Ingot
- Soulstone
- Void Crystal
- Celestial Gold Ingot
- Blood Crystal
- Ancient Essence
- Frost Core
- Thunder Core
- Infernal Shard
- Dragon Heart
- Crystal Dust
- Eternal Fragment
- Divine Essence
- Ancient Dragon Scale
- World Core

Add seven ores, their block/item registrations, blockstates, models, loot tables, mining/tool tags, and world-generation data. Add eight crafting stations with appropriate blocks, block entities, menus, screens, server-side recipe handling, and recipe types. Implement station gating and recipe advancements. Document progression:
Iron → Mythril → Adamantite → Dragonsteel → Celestial Gold → Eternal.

Provide every required language entry and data/resource JSON. Generate only clearly identified placeholder textures by script and document art that still needs custom work in `TODO_ASSETS.md`.

## Quality rules
- Use the exact APIs for the pinned Minecraft/Fabric target; verify against official mappings/API artifacts rather than guessing.
- Keep registration deterministic and server-safe.
- Validate config values and recipe inputs.
- Do not put client classes in common code.
- Do not leave dead code, fake implementations, or TODOs disguised as completed features.
- Test after each meaningful batch. Fix the first failing error before adding more features.
- Do not begin Phase 3 until Phase 2 is complete and the project owner explicitly approves it.

## Current checkpoint
Read `DEVELOPMENT_STATUS.md` and the actual repository before editing. The initial scaffold is incomplete and has not yet passed a successful build. First make Phase 1 buildable, then proceed to Phase 2.
