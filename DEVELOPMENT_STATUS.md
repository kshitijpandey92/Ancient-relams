# Development Status — Ancient Realms

## Target and project constraints
- Minecraft Java 26.2; Fabric only; Java 25.
- Mod ID: `ancientrealms`; package: `com.ancientrealms`.
- Common gameplay code: `src/main`; client renderers: `src/client`.
- The mod metadata declares environment `*`; dedicated-server compatibility still requires runtime verification.
- Do not commit Minecraft/Mojang jars or Gradle caches.

## Verified in the latest CI run
- GitHub Actions run [#45000210459](https://github.com/kshitijpandey92/Ancient-relams/actions/runs/45000210459) completed successfully.
- Python resource validation passed, including JSON syntax, structure/template references, and generated compressed NBT checks.
- Gradle build passed on Java 25.
- Artifact `ancientrealms-fabric-26.2` was uploaded (111,785 bytes).
- This verifies resource checks and compilation only. It does not verify client startup, dedicated-server startup, world loading, or multiplayer behavior.

## Implemented, but not fully runtime-verified
- Fabric main/client entrypoints, registrar helper, JSON configuration, rarity enum and creative tab.
- 17 named material items and seven ore block/resource definitions.
- Mythril and Adamantite are registered for Overworld generation. Dragonsteel, Soulstone, Void Crystal, Celestial Gold and Blood Crystal are intentionally not placed in the Overworld pending their future dimensions.
- Ember, Frost and Storm dragons with models/textures, AI, elemental attacks, bonding, treat healing, saddles, rider-controlled flight, growth and egg nests.
- Ruined keep and royal castle structure resources; royal castle NBT is generated during CI.
- Eight themed stations exist as blocks but currently open vanilla crafting interfaces.
- Scoreboard kingdoms support create/info/invite/join/leave; invitations and founder role state are in-memory and do not survive a restart.

## 15-part completion checklist
- [x] 1. Repository source scaffold and Fabric metadata exist (repository privacy/name and local CLI setup do not match the requested setup; see gaps below).
- [ ] 2. Exact pinned toolchain and Gradle wrapper: requested Loader 0.19.1, Fabric API 0.150.1+26.2, Loom 1.15.x and Gradle wrapper 9.3 are not the current repository settings.
- [ ] 3. Phase 1 foundation: entrypoints, registrar, config, rarity and creative tab need source-jar/API verification against the requested exact pins.
- [ ] 4. Phase 1 Mythril assets: item definition JSON and custom Mythril item textures are missing; models alone are not enough.
- [ ] 5. Phase 1 client launch and dedicated-server launch have not been run or inspected.
- [ ] 6. Complete material variants: raw forms, nuggets and suitable storage blocks are not complete.
- [ ] 7. Seven ore loot tables, tags, models, blockstates and texture coverage need a complete audit against the required exact list.
- [ ] 8. Dimension-specific world generation for the five higher-tier ores is not implemented.
- [ ] 9. Eight required station identities and their block behavior do not yet match the requested Ancient Forge, Dragon Forge, Elemental Altar, Soul Infuser, Celestial Anvil, Dragon Egg Incubator, Legendary Weapon Altar and Ancient Enchantment Table.
- [ ] 10. Station block entities, menus, screens, recipe types and server-side processing are not implemented.
- [ ] 11. Tier-gated progression recipes and recipe advancements are incomplete.
- [ ] 12. Full custom item/block textures and mod icon are missing; see `TODO_ASSETS.md`.
- [ ] 13. Persistent kingdom leadership/invitations, NPCs, quests, diplomacy, raids and wars are incomplete.
- [ ] 14. Dragon armor, visible breath projectiles, advanced dragon stages and full world-event systems are incomplete.
- [ ] 15. Full client/server startup, save/reload, new-world generation, multiplayer synchronization and in-game testing remain outstanding.

## Repository/setup mismatches
- Existing repository is `kshitijpandey92/Ancient-relams`, and GitHub currently reports it as **public**; requested name/visibility were `ancientrealms` and private.
- No `gradle/wrapper/gradle-wrapper.jar` or wrapper properties are currently committed.
- Current `gradle.properties` pins Loader 0.20.1, Fabric API 0.161.0+26.2 and Loom 1.19-SNAPSHOT, not the requested values.
- Local `git --version`, `gh --version`, `gh auth status` and local `java -version` cannot be inferred from a GitHub Actions build.

## Known limitations and next work
- A successful Gradle build is not proof that Minecraft accepts every datapack structure schema or that the structures spawn in game.
- Stations are crafting-table-interface placeholders, not functional processing machines.
- Add missing custom assets, exact-version wrapper and dependencies only with a clean compatibility test; do not silently change the requested Minecraft version.
- Run `runClient` and `runServer` in a suitable environment, inspect their logs, then test new-world generation and multiplayer before marking runtime features complete.
