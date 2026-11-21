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

    private ModBlocks() {}

    public static void initialize() {
        // Static fields register all seven ore blocks and their matching block items.
        StationBlocks.initialize();
    }
}
