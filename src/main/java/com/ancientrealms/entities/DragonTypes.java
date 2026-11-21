package com.ancientrealms.entities;
import com.ancientrealms.registry.Registrar;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
public final class DragonTypes {
 public static final EntityType<DragonEntity> EMBER_DRAGON=register("ember_dragon");
 public static final EntityType<DragonEntity> FROST_DRAGON=register("frost_dragon");
 public static final EntityType<DragonEntity> STORM_DRAGON=register("storm_dragon");
 private DragonTypes(){}
 private static EntityType<DragonEntity> register(String name) {
  ResourceKey<EntityType<?>> key=ResourceKey.create(Registries.ENTITY_TYPE,Registrar.id(name));
  EntityType<DragonEntity> type=Registry.register(BuiltInRegistries.ENTITY_TYPE,key,EntityType.Builder.<DragonEntity>of(DragonEntity::new,MobCategory.MONSTER).sized(4.8F,3.6F).clientTrackingRange(12).build(key));
  FabricDefaultAttributeRegistry.register(type,DragonEntity.createAttributes());
  BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(),MobCategory.MONSTER,type,1,1,1);
  return type;
 }
 public static void initialize() {}
}
