package com.ancientrealms.items;

import com.ancientrealms.registry.Registrar;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

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
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(tab -> {
            tab.accept(MYTHRIL_INGOT);
            tab.accept(ADAMANTITE_INGOT);
            tab.accept(DRAGONSTEEL_INGOT);
            tab.accept(SOULSTONE);
            tab.accept(VOID_CRYSTAL);
            tab.accept(CELESTIAL_GOLD_INGOT);
            tab.accept(BLOOD_CRYSTAL);
            tab.accept(ANCIENT_ESSENCE);
            tab.accept(FROST_CORE);
            tab.accept(THUNDER_CORE);
            tab.accept(INFERNAL_SHARD);
            tab.accept(DRAGON_HEART);
            tab.accept(CRYSTAL_DUST);
            tab.accept(ETERNAL_FRAGMENT);
            tab.accept(DIVINE_ESSENCE);
            tab.accept(ANCIENT_DRAGON_SCALE);
            tab.accept(WORLD_CORE);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(tab -> tab.accept(MYTHRIL_SWORD));
    }
}
