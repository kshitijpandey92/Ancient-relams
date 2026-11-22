package com.ancientrealms.blocks;

import com.ancientrealms.registry.Registrar;
import net.minecraft.world.level.block.Block;

public final class ModBlocks {
    public static final Block MYTHRIL_ORE = Registrar.block("mythril_ore");
    public static final Block ADAMANTITE_ORE = Registrar.block("adamantite_ore");
    public static final Block DRAGONSTEEL_ORE = Registrar.block("dragonsteel_ore");
    public static final Block SOULSTONE_ORE = Registrar.block("soulstone_ore");
    public static final Block VOID_CRYSTAL_ORE = Registrar.block("void_crystal_ore");
    public static final Block CELESTIAL_GOLD_ORE = Registrar.block("celestial_gold_ore");
    public static final Block BLOOD_CRYSTAL_ORE = Registrar.block("blood_crystal_ore");

    public static final Block DRAGON_EGG_NEST = Registrar.customBlock("dragon_egg_nest", DragonEggNestBlock::new);

    // Compact, nine-item storage blocks for every core progression material.
    public static final Block MYTHRIL_BLOCK = Registrar.block("mythril_block");
    public static final Block ADAMANTITE_BLOCK = Registrar.block("adamantite_block");
    public static final Block DRAGONSTEEL_BLOCK = Registrar.block("dragonsteel_block");
    public static final Block SOULSTONE_BLOCK = Registrar.block("soulstone_block");
    public static final Block VOID_CRYSTAL_BLOCK = Registrar.block("void_crystal_block");
    public static final Block CELESTIAL_GOLD_BLOCK = Registrar.block("celestial_gold_block");
    public static final Block BLOOD_CRYSTAL_BLOCK = Registrar.block("blood_crystal_block");
    public static final Block ANCIENT_ESSENCE_BLOCK = Registrar.block("ancient_essence_block");
    public static final Block FROST_CORE_BLOCK = Registrar.block("frost_core_block");
    public static final Block THUNDER_CORE_BLOCK = Registrar.block("thunder_core_block");
    public static final Block INFERNAL_SHARD_BLOCK = Registrar.block("infernal_shard_block");
    public static final Block DRAGON_HEART_BLOCK = Registrar.block("dragon_heart_block");
    public static final Block CRYSTAL_DUST_BLOCK = Registrar.block("crystal_dust_block");
    public static final Block ETERNAL_FRAGMENT_BLOCK = Registrar.block("eternal_fragment_block");
    public static final Block DIVINE_ESSENCE_BLOCK = Registrar.block("divine_essence_block");
    public static final Block ANCIENT_DRAGON_SCALE_BLOCK = Registrar.block("ancient_dragon_scale_block");
    public static final Block WORLD_CORE_BLOCK = Registrar.block("world_core_block");

    private ModBlocks() {}

    public static void initialize() {
        // Static fields register all seven ore blocks and their matching block items.
        StationBlocks.initialize();
    }
}
