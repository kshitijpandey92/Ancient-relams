package com.ancientrealms.items;

import com.ancientrealms.registry.Registrar;
import com.ancientrealms.blocks.StationBlocks;
import com.ancientrealms.blocks.ModBlocks;
import com.ancientrealms.items.DragonEggItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

public final class ModItems {
    public static final Item MYTHRIL_INGOT = Registrar.item("mythril_ingot", Item::new);
    public static final Item RAW_MYTHRIL = Registrar.item("raw_mythril", Item::new);
    public static final Item MYTHRIL_NUGGET = Registrar.item("mythril_nugget", Item::new);
    public static final Item RAW_ADAMANTITE = Registrar.item("raw_adamantite", Item::new);
    public static final Item ADAMANTITE_NUGGET = Registrar.item("adamantite_nugget", Item::new);
    public static final Item DRAGONSTEEL_NUGGET = Registrar.item("dragonsteel_nugget", Item::new);
    public static final Item CELESTIAL_GOLD_NUGGET = Registrar.item("celestial_gold_nugget", Item::new);
    public static final Item MYTHRIL_SWORD = Registrar.item("mythril_sword", Item::new);
    public static final Item ADAMANTITE_SWORD = Registrar.item("adamantite_sword", Item::new);
    public static final Item DRAGONSTEEL_SWORD = Registrar.item("dragonsteel_sword", Item::new);
    public static final Item CELESTIAL_GOLD_SWORD = Registrar.item("celestial_gold_sword", Item::new);
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
    public static final Item DRAGON_SADDLE = Registrar.item("dragon_saddle", Item::new);
    public static final Item DRAGON_ARMOR = Registrar.item("dragon_armor", Item::new);
    public static final Item DRAGON_EGG = Registrar.item("dragon_egg", DragonEggItem::new);
    public static final Item FROST_DRAGON_EGG = Registrar.item("frost_dragon_egg", p -> new DragonEggItem(p, 1));
    public static final Item STORM_DRAGON_EGG = Registrar.item("storm_dragon_egg", p -> new DragonEggItem(p, 2));
    public static final Item KINGDOM_SIGIL = Registrar.item("kingdom_sigil", Item::new);
    public static final Item DRAGON_TREAT = Registrar.item("dragon_treat", Item::new);

    private ModItems() {}

    public static void initialize() {
        ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Registrar.id("ancient_realms"));
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, FabricCreativeModeTab.builder()
            .title(Component.translatable("itemGroup.ancientrealms.main"))
            .icon(() -> new ItemStack(MYTHRIL_SWORD))
            .displayItems((parameters, output) -> {
                output.accept(MYTHRIL_INGOT);
                output.accept(RAW_MYTHRIL);
                output.accept(MYTHRIL_NUGGET);
                output.accept(RAW_ADAMANTITE);
                output.accept(ADAMANTITE_NUGGET);
                output.accept(DRAGONSTEEL_NUGGET);
                output.accept(CELESTIAL_GOLD_NUGGET);
                output.accept(MYTHRIL_SWORD);
                output.accept(ADAMANTITE_SWORD);
                output.accept(DRAGONSTEEL_SWORD);
                output.accept(CELESTIAL_GOLD_SWORD);
                output.accept(ADAMANTITE_INGOT);
                output.accept(DRAGONSTEEL_INGOT);
                output.accept(SOULSTONE);
                output.accept(VOID_CRYSTAL);
                output.accept(CELESTIAL_GOLD_INGOT);
                output.accept(BLOOD_CRYSTAL);
                output.accept(ANCIENT_ESSENCE);
                output.accept(FROST_CORE);
                output.accept(THUNDER_CORE);
                output.accept(INFERNAL_SHARD);
                output.accept(DRAGON_HEART);
                output.accept(CRYSTAL_DUST);
                output.accept(ETERNAL_FRAGMENT);
                output.accept(DIVINE_ESSENCE);
                output.accept(ANCIENT_DRAGON_SCALE);
                output.accept(WORLD_CORE);
                output.accept(DRAGON_SADDLE);
                output.accept(DRAGON_ARMOR);
                output.accept(DRAGON_EGG);
                output.accept(FROST_DRAGON_EGG);
                output.accept(STORM_DRAGON_EGG);
                output.accept(ModBlocks.DRAGON_EGG_NEST);
                output.accept(ModBlocks.MYTHRIL_BLOCK);
                output.accept(ModBlocks.ADAMANTITE_BLOCK);
                output.accept(ModBlocks.DRAGONSTEEL_BLOCK);
                output.accept(ModBlocks.SOULSTONE_BLOCK);
                output.accept(ModBlocks.VOID_CRYSTAL_BLOCK);
                output.accept(ModBlocks.CELESTIAL_GOLD_BLOCK);
                output.accept(ModBlocks.BLOOD_CRYSTAL_BLOCK);
                output.accept(ModBlocks.ANCIENT_ESSENCE_BLOCK);
                output.accept(ModBlocks.FROST_CORE_BLOCK);
                output.accept(ModBlocks.THUNDER_CORE_BLOCK);
                output.accept(ModBlocks.INFERNAL_SHARD_BLOCK);
                output.accept(ModBlocks.DRAGON_HEART_BLOCK);
                output.accept(ModBlocks.CRYSTAL_DUST_BLOCK);
                output.accept(ModBlocks.ETERNAL_FRAGMENT_BLOCK);
                output.accept(ModBlocks.DIVINE_ESSENCE_BLOCK);
                output.accept(ModBlocks.ANCIENT_DRAGON_SCALE_BLOCK);
                output.accept(ModBlocks.WORLD_CORE_BLOCK);
                output.accept(KINGDOM_SIGIL);
                output.accept(DRAGON_TREAT);
                output.accept(StationBlocks.DRAGON_FORGE);
                output.accept(StationBlocks.RUNE_ANVIL);
                output.accept(StationBlocks.SOUL_ALTAR);
                output.accept(StationBlocks.ELEMENTAL_CRUCIBLE);
                output.accept(StationBlocks.CELESTIAL_LOOM);
                output.accept(StationBlocks.BLOOD_INFUSER);
                output.accept(StationBlocks.ETERNAL_FOUNDRY);
                output.accept(StationBlocks.WORLD_CORE_MATRIX);
            })
            .build());
    }
}
