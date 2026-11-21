package com.ancientrealms.client;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public final class AncientRealmsClient implements ClientModInitializer {
 private static final Logger LOGGER=LoggerFactory.getLogger("ancientrealms");
 @Override public void onInitializeClient(){ LOGGER.info("Ancient Realms client initialized."); }
}
