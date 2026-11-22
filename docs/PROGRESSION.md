# Ancient Realms progression

## Intended tier order
Iron → Mythril → Adamantite → Dragonsteel → Celestial Gold → Eternal.

This is the design order, not a claim that every tier gate is implemented yet.

| Tier | Current repository content | Completion |
|---|---|---|
| Iron | Vanilla Minecraft equipment/materials | Vanilla |
| Mythril | Mythril ingot, sword, ore resources, raw Mythril, nugget, smelting and nugget conversion recipes | Partial |
| Adamantite | Ingot, sword, ore resources, raw Adamantite, nugget, smelting and nugget conversion recipes | Partial |
| Dragonsteel | Ingot, sword, ore resources, nugget and conversion recipes | Partial |
| Celestial Gold | Ingot, sword, ore resources, nugget and conversion recipes | Partial |
| Eternal | Eternal Fragment and Divine Essence items/recipes | Partial |

## Ore placement
Only Mythril and Adamantite are registered for Overworld ore placement. Dragonsteel, Soulstone, Void Crystal, Celestial Gold and Blood Crystal have data resources but are not currently placed in the Overworld. Their future dimension and biome placement is not implemented.

## Crafting stations
The eight themed station blocks currently use vanilla crafting-table behavior. Custom block entities, menus, screens, station recipe types, processing queues and progression locks are not implemented.

## Next required progression work
- Add raw forms, nuggets and compact storage blocks consistently for all appropriate metals.
- Implement tier equipment sets and real stat differences.
- Implement the eight custom stations and gate each tier recipe behind the preceding tier.
- Add recipe advancements and test recipe unlocks in a new world.
- Verify every recipe with Minecraft's datapack loader, not JSON parsing alone.
