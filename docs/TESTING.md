# Testing record

## Automated CI
The GitHub Actions workflow:
1. Generates fallback pixel-art assets.
2. Generates the royal castle NBT template.
3. Validates resource JSON and referenced structure templates.
4. Builds the Fabric mod with Java 25.
5. Uploads the mod JAR if all previous steps pass.

## What CI does not test
- Minecraft client startup or rendering.
- Dedicated-server startup.
- World creation, chunk generation, structure placement or datapack warnings.
- Save/reload behavior for dragons and kingdoms.
- Multiplayer synchronization or permissions.
- Station menu/recipe processing.

## Manual verification still required
Run a clean client and dedicated server with Minecraft Java 26.2 and the same Fabric dependencies. Inspect logs for registry, datapack, texture and mixin errors. Create a fresh world, explore generated chunks, hatch and bond each dragon type, create/join/leave a kingdom, save/reload, and repeat on a dedicated server with a second client.
