# Crafting station implementation status

The project currently registers eight themed blocks, but they are visual/functional placeholders that inherit vanilla crafting-table behavior. They are not yet the requested dedicated processing stations.

| Required station | Current nearest block | Actual status |
|---|---|---|
| Ancient Forge | Rune Anvil | Not implemented as a dedicated station |
| Dragon Forge | Dragon Forge | Vanilla crafting-table behavior |
| Elemental Altar | Elemental Crucible | Vanilla crafting-table behavior |
| Soul Infuser | Soul Altar | Vanilla crafting-table behavior |
| Celestial Anvil | Celestial Loom | Vanilla crafting-table behavior |
| Dragon Egg Incubator | Dragon Egg Nest | Nest hatches eggs; not an inventory/menu station |
| Legendary Weapon Altar | Blood Infuser | Vanilla crafting-table behavior |
| Ancient Enchantment Table | World Core Matrix | Vanilla crafting-table behavior |

## Required work
- Define station block entities with persistent inventory and progress state.
- Define server-authoritative menus and slot validation.
- Add client screens in the client source set only.
- Add station-specific recipe serializers/types and recipe viewers.
- Validate ingredients and tier progression on the server.
- Persist work progress through save/reload.
- Add sound/particle feedback with server-side limits.
- Test multiplayer synchronization and malicious/invalid input handling.

Do not advertise the current blocks as completed custom crafting stations.
