#!/usr/bin/env python3
"""Generate the compressed Minecraft structure NBT for the Ancient Realms royal castle."""
from pathlib import Path
import gzip
import struct

OUT = Path("src/main/resources/data/ancientrealms/structure/royal_castle/castle.nbt")
PALETTE = [
    "minecraft:stone_bricks", "minecraft:cracked_stone_bricks",
    "minecraft:polished_andesite", "minecraft:oak_planks",
    "minecraft:dark_oak_planks", "minecraft:stone_brick_wall",
    "minecraft:iron_bars", "minecraft:lantern", "minecraft:chain",
    "minecraft:bookshelf", "minecraft:chest", "minecraft:crafting_table",
    "minecraft:anvil", "minecraft:barrel", "minecraft:gold_block",
    "minecraft:purple_carpet", "minecraft:glowstone",
]
STATE = {name: i for i, name in enumerate(PALETTE)}
blocks = {}

def put(x, y, z, name):
    if 0 <= x < 31 and 0 <= y < 20 and 0 <= z < 31:
        blocks[(x, y, z)] = STATE[name]

def box(x1, y1, z1, x2, y2, z2, name, hollow=False):
    for x in range(x1, x2 + 1):
        for y in range(y1, y2 + 1):
            for z in range(z1, z2 + 1):
                if not hollow or x in (x1, x2) or y in (y1, y2) or z in (z1, z2):
                    put(x, y, z, name)

# Raised foundation and broad paved courtyard.
box(1, 0, 1, 29, 1, 29, "minecraft:stone_bricks")
for x in range(2, 29):
    for z in range(2, 29):
        if (x * 3 + z) % 13 == 0:
            put(x, 2, z, "minecraft:cracked_stone_bricks")

# Four-sided curtain wall with crenellations.
box(2, 2, 2, 28, 8, 4, "minecraft:stone_bricks")
box(2, 2, 26, 28, 8, 28, "minecraft:stone_bricks")
box(2, 2, 5, 4, 8, 25, "minecraft:stone_bricks")
box(26, 2, 5, 28, 8, 25, "minecraft:stone_bricks")
for x in range(2, 29, 2):
    for z in (2, 28):
        put(x, 9, z, "minecraft:stone_brick_wall")
for z in range(4, 27, 2):
    for x in (2, 28):
        put(x, 9, z, "minecraft:stone_brick_wall")

# Four tall corner towers, with multiple floors and battlement crowns.
for cx, cz in ((5, 5), (25, 5), (5, 25), (25, 25)):
    box(cx - 2, 2, cz - 2, cx + 2, 15, cz + 2, "minecraft:stone_bricks", True)
    for floor in (3, 8, 13):
        box(cx - 1, floor, cz - 1, cx + 1, floor, cz + 1, "minecraft:oak_planks")
    for dx in (-2, 0, 2):
        for dz in range(-2, 3):
            put(cx + dx, 16, cz + dz, "minecraft:stone_brick_wall")
    for dz in (-2, 0, 2):
        for dx in range(-2, 3):
            put(cx + dx, 16, cz + dz, "minecraft:stone_brick_wall")
    put(cx, 5, cz, "minecraft:lantern")
    put(cx, 10, cz, "minecraft:lantern")

# Gatehouse with an open central passage and iron-barred side windows.
box(12, 2, 2, 18, 11, 7, "minecraft:stone_bricks", True)
for x in range(14, 17):
    for y in range(2, 7):
        for z in range(2, 5):
            blocks.pop((x, y, z), None)
for x in (12, 18):
    for y in range(3, 10):
        put(x, y, 3, "minecraft:iron_bars")

# Central multi-storey royal keep and great hall.
box(10, 2, 10, 20, 17, 20, "minecraft:stone_bricks", True)
box(12, 3, 12, 18, 3, 18, "minecraft:oak_planks")
box(12, 9, 12, 18, 9, 18, "minecraft:oak_planks")
box(12, 15, 12, 18, 15, 18, "minecraft:dark_oak_planks")
for x in (10, 20):
    for z in range(10, 21, 2):
        for y in range(3, 17):
            put(x, y, z, "minecraft:polished_andesite")
for z in (10, 20):
    for x in range(10, 21, 2):
        for y in range(3, 17):
            put(x, y, z, "minecraft:polished_andesite")
for x in range(10, 21, 2):
    put(x, 18, 10, "minecraft:stone_brick_wall")
    put(x, 18, 20, "minecraft:stone_brick_wall")
for z in range(12, 20, 2):
    put(10, 18, z, "minecraft:stone_brick_wall")
    put(20, 18, z, "minecraft:stone_brick_wall")

# Raised throne dais, throne back, royal storage and crafting area.
box(14, 4, 14, 16, 4, 16, "minecraft:gold_block")
put(15, 5, 15, "minecraft:purple_carpet")
box(14, 6, 16, 16, 8, 16, "minecraft:dark_oak_planks")
furnishings = [
    (12, 4, 12, "minecraft:lantern"), (18, 4, 12, "minecraft:lantern"),
    (12, 4, 18, "minecraft:lantern"), (18, 4, 18, "minecraft:lantern"),
    (12, 4, 14, "minecraft:bookshelf"), (12, 4, 15, "minecraft:bookshelf"),
    (18, 4, 14, "minecraft:chest"), (18, 4, 15, "minecraft:barrel"),
    (12, 4, 17, "minecraft:crafting_table"), (13, 4, 17, "minecraft:anvil"),
    (18, 4, 17, "minecraft:chest"), (15, 11, 15, "minecraft:glowstone"),
]
for x, y, z, name in furnishings:
    put(x, y, z, name)

# Courtyard lamp posts.
for x, z in ((8, 9), (22, 9), (8, 22), (22, 22)):
    put(x, 2, z, "minecraft:stone_brick_wall")
    put(x, 3, z, "minecraft:chain")
    put(x, 4, z, "minecraft:lantern")

def named_tag(tag_type, name, payload):
    encoded = name.encode("utf-8")
    return struct.pack(">BH", tag_type, len(encoded)) + encoded + payload

def string_payload(value):
    encoded = value.encode("utf-8")
    return struct.pack(">H", len(encoded)) + encoded

def int_tag(name, value):
    return named_tag(3, name, struct.pack(">i", value))

def list_tag(name, element_type, payloads):
    return named_tag(9, name, struct.pack(">bi", element_type, len(payloads)) + b"".join(payloads))

def compound_payload(tags):
    return b"".join(tags) + b"\x00"

palette_tags = [
    compound_payload([named_tag(8, "Name", string_payload(block_name))])
    for block_name in PALETTE
]
block_tags = []
for (x, y, z), state in blocks.items():
    block_tags.append(compound_payload([
        int_tag("state", state),
        list_tag("pos", 3, [struct.pack(">i", x), struct.pack(">i", y), struct.pack(">i", z)]),
    ]))

root = compound_payload([
    int_tag("DataVersion", 0),
    list_tag("size", 3, [struct.pack(">i", 31), struct.pack(">i", 20), struct.pack(">i", 31)]),
    list_tag("palette", 10, palette_tags),
    list_tag("blocks", 10, block_tags),
    list_tag("entities", 10, []),
])
nbt = named_tag(10, "", root)
OUT.parent.mkdir(parents=True, exist_ok=True)
OUT.write_bytes(gzip.compress(nbt, compresslevel=9))
print(f"Generated {OUT}: {len(blocks)} blocks, {len(PALETTE)} palette states.")
