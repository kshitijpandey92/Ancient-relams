package com.ancientrealms;
import com.ancientrealms.config.ModConfig;
import com.ancientrealms.items.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public final class AncientRealms implements ModInitializer {
 public static final String MOD_ID="ancientrealms";
 public static final Logger LOGGER=LoggerFactory.getLogger(MOD_ID);
 @Override public void onInitialize(){ ModConfig.load(); ModItems.initialize(); LOGGER.info("Ancient Realms foundation initialized."); }
}
