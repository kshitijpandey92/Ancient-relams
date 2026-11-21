package com.ancientrealms.registry;
import com.ancientrealms.AncientRealms;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import java.util.function.Function;
public final class Registrar {
 private Registrar(){}
 public static Identifier id(String path){return Identifier.fromNamespaceAndPath(AncientRealms.MOD_ID,path);}
 public static Item item(String path,Function<Item.Properties,Item> factory){
  Identifier id=id(path); ResourceKey<Item> key=ResourceKey.create(Registries.ITEM,id);
  Item item=factory.apply(new Item.Properties().setId(key));
  return Registry.register(BuiltInRegistries.ITEM,id,item);
 }
 public static Block block(String path) {
  Identifier id = id(path);
  ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
  Block block = new Block(BlockBehaviour.Properties.of().strength(3.0f, 6.0f).requiresCorrectToolForDrops().setId(blockKey));
  Registry.register(BuiltInRegistries.BLOCK, id, block);
  ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);
  BlockItem blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(itemKey));
  Registry.register(BuiltInRegistries.ITEM, id, blockItem);
  return block;
 }
 public static Block station(String path) {
  Identifier id = id(path);
  ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
  Block block = new CraftingTableBlock(BlockBehaviour.Properties.of().strength(3.5f).setId(blockKey));
  Registry.register(BuiltInRegistries.BLOCK, id, block);
  ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);
  BlockItem item = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(itemKey));
  Registry.register(BuiltInRegistries.ITEM, id, item);
  return block;
 }
}
