# Ancient Realms Dragons

Three dragon species are registered with distinct textures and custom multipart 3D models. Their bodies, necks, heads, horns, snouts, tails, legs and independently animated wings are rendered client-side.

- **Ember Dragon:** fire immune; elemental strike deals damage and ignites its target.
- **Frost Dragon:** elemental strike deals damage and increases the target's freeze time.
- **Storm Dragon:** elemental strike deals damage and knocks the target back.

All three have hostile AI, 180 health, 14 armor, 18 melee damage, 56-block follow range and rare overworld monster spawns.

## Taming and riding
1. Defeat dragons to obtain their species' core and dragon scales.
2. Craft Dragon Treats using a dragon heart, ancient scales, melon and an enchanted golden apple.
3. Hold a Dragon Treat and interact with a wild dragon to bond with it.
4. Hold a Dragon Saddle and interact with your bonded dragon.
5. Interact again to mount it. Only the bonded player can saddle and mount it.

## Flight controls
While mounted, use normal movement keys to steer forward/back and strafe. Look up to climb and down to descend. Rider-controlled flight movement is implemented and the latest Gradle compile/package step passed.

## Summon commands
- `/summon ancientrealms:ember_dragon`
- `/summon ancientrealms:frost_dragon`
- `/summon ancientrealms:storm_dragon`

## Still in development
Dragon ownership and saddle status are saved with the dragon and restored when the world reloads. The bonded owner can also heal the dragon by feeding it Dragon Treats (12 health per treat). Eggs are currently craftable items, not incubating blocks; hatchling growth, nests, breath projectiles, advanced flight AI and dragon armor are not yet implemented. Elemental attacks are direct combat pulses, not visual projectile breath weapons.
