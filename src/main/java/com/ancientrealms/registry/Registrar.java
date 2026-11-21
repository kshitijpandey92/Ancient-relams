package com.ancientrealms.registry;
import com.ancientrealms.AncientRealms;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import java.util.function.Function;
public final class Registrar {
 private Registrar(){}
 public static Identifier id(String path){return Identifier.fromNamespaceAndPath(AncientRealms.MOD_ID,path);}
 public static Item item(String path,Function<Item.Properties,Item> factory){
  Identifier id=id(path); ResourceKey<Item> key=ResourceKey.create(Registries.ITEM,id);
  Item item=factory.apply(new Item.Properties().setId(key));
  return Registry.register(BuiltInRegistries.ITEM,id,item);
 }
}
