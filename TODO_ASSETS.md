# Art and Asset Checklist

## Generated fallback assets
The CI workflow runs `python tools/generate_assets.py` before packaging. It creates deterministic 16x16 pixel-art fallback PNGs for every missing texture path referenced by the block/item models and creates a mod icon if absent. This prevents missing-texture purple/black squares in the CI-built JAR. The generated art is deliberately simple fallback art, not finished bespoke art.

## Custom art still recommended
- [ ] Replace generated item textures with individually designed Mythril, Adamantite, Dragonsteel, Soulstone, Void, Celestial, Blood, Frost, Thunder, Infernal, Dragon Heart, Eternal and Divine art.
- [ ] Replace generated ore textures with distinct stone/ore sprites for all seven ore types.
- [ ] Create bespoke station block textures and model details.
- [ ] Create a polished 128x128 or larger mod icon.
- [ ] Add inventory icons and custom UI textures for the eight future station menus.

## Functional implementation gaps (not solved by fallback art)
- [ ] Raw material forms, nuggets and appropriate storage blocks.
- [ ] Five higher-tier ore dimension placements (only Mythril and Adamantite should generate in the Overworld).
- [ ] Eight specified station block entities, menus/screens, custom recipes, timed server processing and progression gates.
- [ ] Full tier equipment sets and progression advancements.
- [ ] Kingdom NPCs, quest/event systems, diplomacy, raids and wars.
- [ ] Dragon armor, visible breath projectiles, and full world-event systems.
- [ ] In-game client/server startup, save/reload, new-world generation and multiplayer verification.

Generated placeholder art must not be represented as final custom art.
