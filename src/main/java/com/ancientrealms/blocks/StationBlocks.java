package com.ancientrealms.blocks;

import com.ancientrealms.registry.Registrar;
import net.minecraft.world.level.block.Block;

public final class StationBlocks {
    public static final Block DRAGON_FORGE = Registrar.station("dragon_forge", StationBlock.Kind.DRAGON_FORGE);
    public static final Block RUNE_ANVIL = Registrar.station("rune_anvil", StationBlock.Kind.RUNE_ANVIL);
    public static final Block SOUL_ALTAR = Registrar.station("soul_altar", StationBlock.Kind.SOUL_ALTAR);
    public static final Block ELEMENTAL_CRUCIBLE = Registrar.station("elemental_crucible", StationBlock.Kind.ELEMENTAL_CRUCIBLE);
    public static final Block CELESTIAL_LOOM = Registrar.station("celestial_loom", StationBlock.Kind.CELESTIAL_LOOM);
    public static final Block BLOOD_INFUSER = Registrar.station("blood_infuser", StationBlock.Kind.BLOOD_INFUSER);
    public static final Block ETERNAL_FOUNDRY = Registrar.station("eternal_foundry", StationBlock.Kind.ETERNAL_FOUNDRY);
    public static final Block WORLD_CORE_MATRIX = Registrar.station("world_core_matrix", StationBlock.Kind.WORLD_CORE_MATRIX);

    private StationBlocks() {}
    public static void initialize() {}
}
