package com.ancientrealms.items;

import com.ancientrealms.registry.Registrar;
import net.minecraft.world.item.Item;

public final class ModItems {
    public static final Item MYTHRIL_INGOT = Registrar.item("mythril_ingot", Item::new);
    public static final Item MYTHRIL_SWORD = Registrar.item("mythril_sword", Item::new);
    public static final Item ADAMANTITE_INGOT = Registrar.item("adamantite_ingot", Item::new);
    public static final Item DRAGONSTEEL_INGOT = Registrar.item("dragonsteel_ingot", Item::new);
    public static final Item SOULSTONE = Registrar.item("soulstone", Item::new);
    public static final Item VOID_CRYSTAL = Registrar.item("void_crystal", Item::new);
    public static final Item CELESTIAL_GOLD_INGOT = Registrar.item("celestial_gold_ingot", Item::new);
    public static final Item BLOOD_CRYSTAL = Registrar.item("blood_crystal", Item::new);
    public static final Item ANCIENT_ESSENCE = Registrar.item("ancient_essence", Item::new);
    public static final Item FROST_CORE = Registrar.item("frost_core", Item::new);
    public static final Item THUNDER_CORE = Registrar.item("thunder_core", Item::new);
    public static final Item INFERNAL_SHARD = Registrar.item("infernal_shard", Item::new);
    public static final Item DRAGON_HEART = Registrar.item("dragon_heart", Item::new);
    public static final Item CRYSTAL_DUST = Registrar.item("crystal_dust", Item::new);
    public static final Item ETERNAL_FRAGMENT = Registrar.item("eternal_fragment", Item::new);
    public static final Item DIVINE_ESSENCE = Registrar.item("divine_essence", Item::new);
    public static final Item ANCIENT_DRAGON_SCALE = Registrar.item("ancient_dragon_scale", Item::new);
    public static final Item WORLD_CORE = Registrar.item("world_core", Item::new);

    private ModItems() {}

    public static void initialize() {
        // Items are registered during class initialization; recipes and the creative tab are data-driven.
    }
}
