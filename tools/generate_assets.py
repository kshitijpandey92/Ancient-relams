#!/usr/bin/env python3
"""Generate deterministic, clearly stylized fallback pixel-art textures.

This uses only Python's standard library so CI and local builds need no Pillow.
It creates PNGs for texture paths referenced by this mod's block/item models
when a custom texture is not yet present. Generated art is a fallback, not final
hand-authored artwork.
"""
from pathlib import Path
import json
import math
import struct
import zlib

ROOT = Path(__file__).resolve().parents[1]
ASSETS = ROOT / "src/main/resources/assets/ancientrealms"
MODELS = ASSETS / "models"
PALETTES = {
    "mythril": ((22, 48, 68), (74, 183, 202), (190, 246, 242)),
    "adamantite": ((48, 42, 39), (151, 119, 87), (238, 206, 150)),
    "dragonsteel": ((58, 24, 20), (188, 69, 39), (255, 174, 77)),
    "soulstone": ((30, 20, 54), (116, 69, 167), (218, 170, 255)),
    "void_crystal": ((12, 17, 35), (61, 85, 160), (151, 206, 255)),
    "celestial": ((79, 58, 20), (221, 177, 63), (255, 244, 173)),
    "blood": ((53, 10, 20), (165, 28, 54), (255, 112, 128)),
    "frost": ((25, 63, 89), (98, 190, 224), (226, 252, 255)),
    "thunder": ((41, 35, 85), (116, 105, 230), (255, 245, 139)),
    "infernal": ((74, 24, 13), (218, 72, 24), (255, 203, 91)),
    "dragon": ((45, 37, 32), (158, 105, 60), (232, 190, 126)),
    "eternal": ((35, 49, 53), (95, 167, 144), (218, 255, 220)),
    "divine": ((76, 66, 35), (195, 173, 94), (255, 255, 218)),
    "world": ((25, 67, 57), (77, 167, 120), (190, 255, 184)),
}
def palette(name):
    low = name.lower()
    for key, colors in PALETTES.items():
        if key in low:
            return colors
    return ((42, 48, 58), (115, 132, 148), (220, 230, 238))

def png(path, name):
    dark, mid, light = palette(name)
    pixels = []
    seed = sum((i + 1) * ord(c) for i, c in enumerate(name))
    for y in range(16):
        row = bytearray()
        for x in range(16):
            # Faceted gem/metal pixel-art with deterministic highlights.
            v = (x * 13 + y * 7 + (x ^ y) * 5 + seed) % 29
            if x in (0, 15) or y in (0, 15):
                color = dark
            elif v < 5:
                color = light
            elif v < 17:
                color = mid
            else:
                color = dark
            row.extend((*color, 255))
        pixels.append(b"\x00" + bytes(row))
    raw = b"".join(pixels)
    def chunk(kind, data):
        return struct.pack(">I", len(data)) + kind + data + struct.pack(">I", zlib.crc32(kind + data) & 0xffffffff)
    data = (b"\x89PNG\r\n\x1a\n" +
            chunk(b"IHDR", struct.pack(">2I5B", 16, 16, 8, 6, 0, 0, 0)) +
            chunk(b"IDAT", zlib.compress(raw, 9)) + chunk(b"IEND", b""))
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_bytes(data)


# Storage blocks and their reversible nine-item recipes are generated from this
# single manifest so every material has matching blockstate, models, loot and
# recipe data. The generated files are included in the distributable JAR.
STORAGE_MATERIALS = [
    ("mythril", "mythril_ingot"),
    ("adamantite", "adamantite_ingot"),
    ("dragonsteel", "dragonsteel_ingot"),
    ("soulstone", "soulstone"),
    ("void_crystal", "void_crystal"),
    ("celestial_gold", "celestial_gold_ingot"),
    ("blood_crystal", "blood_crystal"),
    ("ancient_essence", "ancient_essence"),
    ("frost_core", "frost_core"),
    ("thunder_core", "thunder_core"),
    ("infernal_shard", "infernal_shard"),
    ("dragon_heart", "dragon_heart"),
    ("crystal_dust", "crystal_dust"),
    ("eternal_fragment", "eternal_fragment"),
    ("divine_essence", "divine_essence"),
    ("ancient_dragon_scale", "ancient_dragon_scale"),
    ("world_core", "world_core"),
]

def write_json(path, value):
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(value, indent=2) + "\\n", encoding="utf-8")

def generate_storage_blocks():
    resources = ROOT / "src/main/resources"
    for material, ingredient in STORAGE_MATERIALS:
        block = material + "_block"
        write_json(ASSETS / "blockstates" / (block + ".json"), {
            "variants": {"": {"model": "ancientrealms:block/" + block}}
        })
        write_json(MODELS / "block" / (block + ".json"), {
            "parent": "minecraft:block/cube_all",
            "textures": {"all": "ancientrealms:block/" + block}
        })
        write_json(MODELS / "item" / (block + ".json"), {
            "parent": "ancientrealms:block/" + block
        })
        write_json(resources / "data/ancientrealms/loot_table/blocks" / (block + ".json"), {
            "type": "minecraft:block",
            "pools": [{"rolls": 1, "entries": [{"type": "minecraft:item", "name": "ancientrealms:" + block}]}]
        })
        write_json(resources / "data/ancientrealms/recipe" / (block + ".json"), {
            "type": "minecraft:crafting_shaped",
            "category": "building",
            "pattern": ["XXX", "XXX", "XXX"],
            "key": {"X": {"item": "ancientrealms:" + ingredient}},
            "result": {"id": "ancientrealms:" + block, "count": 1}
        })
        write_json(resources / "data/ancientrealms/recipe" / (material + "_from_block.json"), {
            "type": "minecraft:crafting_shapeless",
            "category": "misc",
            "ingredients": [{"item": "ancientrealms:" + block}],
            "result": {"id": "ancientrealms:" + ingredient, "count": 9}
        })


def main():
    generate_storage_blocks()
    made = []
    for model_file in MODELS.rglob("*.json"):
        model = json.loads(model_file.read_text(encoding="utf-8"))
        for value in model.get("textures", {}).values():
            if not isinstance(value, str) or not value.startswith("ancientrealms:"):
                continue
            namespace_path = value.split(":", 1)[1]
            texture = ASSETS / "textures" / (namespace_path + ".png")
            if not texture.exists():
                png(texture, namespace_path)
                made.append(texture.relative_to(ROOT).as_posix())
    icon = ASSETS / "icon.png"
    if not icon.exists():
        png(icon, "ancientrealms_icon")
        made.append(icon.relative_to(ROOT).as_posix())
    print(f"Generated {len(made)} fallback PNG assets.")
    for item in made:
        print(f"  {item}")

if __name__ == "__main__":
    main()
