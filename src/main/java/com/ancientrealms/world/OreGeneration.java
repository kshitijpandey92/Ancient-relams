package com.ancientrealms.world;

import com.ancientrealms.registry.Registrar;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class OreGeneration {
    private OreGeneration() {}

    private static void add(String name, java.util.function.Predicate<BiomeSelectionContext> selector) {
        ResourceKey<PlacedFeature> key = ResourceKey.create(Registries.PLACED_FEATURE, Registrar.id(name + "_ore"));
        BiomeModifications.addFeature(selector, GenerationStep.Decoration.UNDERGROUND_ORES, key);
    }

    public static void register() {
        for (String nest : new String[] {"ember_dragon_nest", "frost_dragon_nest", "storm_dragon_nest"}) {
            ResourceKey<PlacedFeature> key = ResourceKey.create(Registries.PLACED_FEATURE, Registrar.id(nest));
            BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.VEGETAL_DECORATION, key);
        }
        add("mythril", BiomeSelectors.foundInOverworld());
        add("adamantite", BiomeSelectors.foundInOverworld());
        add("dragonsteel", BiomeSelectors.foundInTheNether());
        add("soulstone", BiomeSelectors.foundInTheNether());
        add("blood_crystal", BiomeSelectors.foundInTheNether());
        add("void_crystal", BiomeSelectors.foundInTheEnd());
        add("celestial_gold", BiomeSelectors.foundInTheEnd());
    }
}
