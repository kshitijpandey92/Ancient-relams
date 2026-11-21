package com.ancientrealms.items;
import com.ancientrealms.registry.Registrar;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
public final class ModItems {
 public static final Item MYTHRIL_INGOT=Registrar.item("mythril_ingot",Item::new);
 public static final Item MYTHRIL_SWORD=Registrar.item("mythril_sword",Item::new);
 private ModItems(){}
 public static void initialize(){
  ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries->entries.accept(MYTHRIL_INGOT));
  ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(entries->entries.accept(MYTHRIL_SWORD));
 }
}
