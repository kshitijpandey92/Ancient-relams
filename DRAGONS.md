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

## Egg incubation
Use a Dragon Egg item on a block to place an Incubating Dragon Egg Nest. Its incubation stage advances through random world ticks; once fully incubated, it hatches the selected species and removes the nest. The standard Dragon Egg hatches Ember; combine it with Frost Core or Thunder Core to craft Frost Dragon Egg or Storm Dragon Egg. Hatchlings currently emerge as adult-sized entities; juvenile growth is not implemented.

## Still in development
Dragon ownership and saddle status are saved with the dragon and restored when the world reloads. The bonded owner can also heal the dragon by feeding it Dragon Treats (12 health per treat). The crafted Dragon Egg remains an item; the separate Incubating Dragon Egg Nest block hatches an Ember Dragon after its incubation stages. Juvenile hatchling models, growth stages, species selection, advanced flight AI and dragon armor are not yet implemented. Elemental attacks are direct combat pulses, not visual projectile breath weapons.
