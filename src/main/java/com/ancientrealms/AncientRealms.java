package com.ancientrealms;
import com.ancientrealms.config.ModConfig;
import com.ancientrealms.blocks.ModBlocks;
import com.ancientrealms.items.ModItems;
import com.ancientrealms.entities.DragonTypes;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public final class AncientRealms implements ModInitializer {
 public static final String MOD_ID="ancientrealms";
 public static final Logger LOGGER=LoggerFactory.getLogger(MOD_ID);
 @Override public void onInitialize(){ ModConfig.load(); ModItems.initialize(); ModBlocks.initialize(); DragonTypes.initialize(); LOGGER.info("Ancient Realms foundation initialized."); }
}
