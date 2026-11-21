# Development Status

## Project target
- Minecraft Java 26.2
- Fabric only (no Forge or NeoForge)
- Java 25
- Mod ID: `ancientrealms`
- Main package: `com.ancientrealms`

## Current state
- [x] Initial Gradle project and Fabric metadata created.
- [x] Main and client entrypoints added.
- [x] Basic config loader and bounded weapon rarity enum added.
- [x] Mythril ingot and sword registration stubs added.
- [x] Initial item models, language entries, and sword recipe JSON added.
- [x] GitHub Actions build workflow added.
- [ ] Phase 1 is not verified: CI has not completed a successful build.
- [ ] Item textures and mod icon are still missing.
- [ ] Phase 2 materials, ores, stations, and progression are not implemented.

## Build issue found and correction
The first GitHub Actions run failed before Java compilation because Fabric Loom 1.19.4 requires Gradle 9.8.0, while the workflow installed Gradle 9.5.1. The workflow is being updated to use Gradle 9.8.0. This fixes the identified Gradle/Loom version mismatch; it does not yet prove that the Java sources compile.

## Verification record
- GitHub Actions run 1: failed during Gradle project configuration due to the Gradle 9.5.1 / Loom 1.19.4 compatibility mismatch.
- No successful build, client launch, dedicated-server launch, or save/load test has been recorded.

## Next work
1. Re-run CI with Gradle 9.8.0 and fix any subsequent dependency/API/compiler errors.
2. Complete and verify Phase 1 before starting Phase 2.
3. Add actual textures and icon; document any temporary placeholders.
4. Only after Phase 1 is buildable, implement Phase 2 materials, ores, stations, and progression.
