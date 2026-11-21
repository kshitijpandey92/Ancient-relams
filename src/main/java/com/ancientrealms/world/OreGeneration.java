package com.ancientrealms.world;

import com.ancientrealms.registry.Registrar;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class OreGeneration {
    private static final String[] ORES = {
        "mythril", "adamantite", "dragonsteel", "soulstone",
        "void_crystal", "celestial_gold", "blood_crystal"
    };

    private OreGeneration() {}

    public static void register() {
        for (String nest : new String[] {"ember_dragon_nest", "frost_dragon_nest", "storm_dragon_nest"}) {
            ResourceKey<PlacedFeature> dragonNest = ResourceKey.create(
                Registries.PLACED_FEATURE, Registrar.id(nest)
            );
            BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                dragonNest
            );
        }

        for (String ore : ORES) {
            ResourceKey<PlacedFeature> key = ResourceKey.create(
                Registries.PLACED_FEATURE, Registrar.id(ore + "_ore")
            );
            BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                key
            );
        }
    }
}
