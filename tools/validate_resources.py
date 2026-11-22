#!/usr/bin/env python3
"""Validate Ancient Realms data-pack JSON and generated structure templates."""
from __future__ import annotations

import gzip
import json
from pathlib import Path

ROOT = Path("src/main/resources")
DATA = ROOT / "data" / "ancientrealms"
errors: list[str] = []
json_files = sorted(ROOT.rglob("*.json"))
parsed: dict[Path, object] = {}

for path in json_files:
    try:
        parsed[path] = json.loads(path.read_text(encoding="utf-8"))
    except (OSError, json.JSONDecodeError) as exc:
        errors.append(f"{path}: invalid JSON: {exc}")

metadata_path = ROOT / "fabric.mod.json"
metadata = parsed.get(metadata_path)
if not isinstance(metadata, dict):
    errors.append("fabric.mod.json: metadata must be a JSON object")
else:
    if metadata.get("id") != "ancientrealms":
        errors.append("fabric.mod.json: mod id must be ancientrealms")
    if metadata.get("environment") != "*":
        errors.append("fabric.mod.json: expected common client/server environment '*'")

# Every structure definition must point to a template pool that exists.
for path in sorted((DATA / "worldgen" / "structure").glob("*.json")):
    structure = parsed.get(path)
    if not isinstance(structure, dict):
        continue
    pool = structure.get("start_pool")
    if not isinstance(pool, str) or ":" not in pool:
        errors.append(f"{path}: missing namespaced start_pool")
        continue
    namespace, resource = pool.split(":", 1)
    pool_path = ROOT / "data" / namespace / "worldgen" / "template_pool" / (resource + ".json")
    if pool_path not in parsed:
        errors.append(f"{path}: start_pool {pool!r} does not exist at {pool_path}")

# Ensure each single-pool structure element resolves to a generated NBT template.
for path in sorted((DATA / "worldgen" / "template_pool").rglob("*.json")):
    pool = parsed.get(path)
    if not isinstance(pool, dict):
        continue
    for index, entry in enumerate(pool.get("elements", [])):
        element = entry.get("element", {}) if isinstance(entry, dict) else {}
        location = element.get("location") if isinstance(element, dict) else None
        if element.get("element_type") != "minecraft:single_pool_element":
            continue
        if not isinstance(location, str) or ":" not in location:
            errors.append(f"{path}: element {index} has invalid location")
            continue
        namespace, resource = location.split(":", 1)
        nbt_path = ROOT / "data" / namespace / "structure" / (resource + ".nbt")
        if not nbt_path.is_file():
            errors.append(f"{path}: missing structure template {nbt_path}")
            continue
        try:
            raw = gzip.decompress(nbt_path.read_bytes())
            if len(raw) < 4 or raw[:3] != bytes((10, 0, 0)):
                errors.append(f"{nbt_path}: not a valid gzip-compressed root NBT compound")
        except (OSError, EOFError) as exc:
            errors.append(f"{nbt_path}: cannot decompress structure NBT: {exc}")


# Generated material storage blocks must have all of their gameplay resources.
storage_materials = [
    "mythril", "adamantite", "dragonsteel", "soulstone", "void_crystal",
    "celestial_gold", "blood_crystal", "ancient_essence", "frost_core",
    "thunder_core", "infernal_shard", "dragon_heart", "crystal_dust",
    "eternal_fragment", "divine_essence", "ancient_dragon_scale", "world_core",
]
pickaxe_tag = parsed.get(DATA.parent / "minecraft" / "tags" / "block" / "mineable" / "pickaxe.json")
iron_tag = parsed.get(DATA.parent / "minecraft" / "tags" / "block" / "needs_iron_tool.json")
for material in storage_materials:
    block = material + "_block"
    required = [
        ROOT / "assets/ancientrealms/blockstates" / (block + ".json"),
        ROOT / "assets/ancientrealms/models/block" / (block + ".json"),
        ROOT / "assets/ancientrealms/models/item" / (block + ".json"),
        DATA / "loot_table/blocks" / (block + ".json"),
        DATA / "recipe" / (block + ".json"),
        DATA / "recipe" / (material + "_from_block.json"),
    ]
    for resource in required:
        if not resource.is_file():
            errors.append(f"storage block {block}: missing {resource}")
    if isinstance(pickaxe_tag, dict) and "ancientrealms:" + block not in pickaxe_tag.get("values", []):
        errors.append(f"storage block {block}: missing minecraft:mineable/pickaxe tag")
    if isinstance(iron_tag, dict) and "ancientrealms:" + block not in iron_tag.get("values", []):
        errors.append(f"storage block {block}: missing minecraft:needs_iron_tool tag")

if errors:
    print("Ancient Realms resource validation FAILED:")
    for error in errors:
        print(f" - {error}")
    raise SystemExit(1)

print(f"Validated {len(json_files)} JSON resources and all referenced structure templates.")
