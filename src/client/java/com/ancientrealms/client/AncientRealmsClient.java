package com.ancientrealms.client;

import com.ancientrealms.client.dragon.DragonModel;
import com.ancientrealms.client.dragon.DragonRenderer;
import com.ancientrealms.entities.DragonTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AncientRealmsClient implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger("ancientrealms");
    @Override public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(DragonModel.LAYER, DragonModel::createBodyLayer);
        EntityRenderers.register(DragonTypes.EMBER_DRAGON, c -> new DragonRenderer(c, Identifier.fromNamespaceAndPath("ancientrealms","textures/entity/ember_dragon.png")));
        EntityRenderers.register(DragonTypes.FROST_DRAGON, c -> new DragonRenderer(c, Identifier.fromNamespaceAndPath("ancientrealms","textures/entity/frost_dragon.png")));
        EntityRenderers.register(DragonTypes.STORM_DRAGON, c -> new DragonRenderer(c, Identifier.fromNamespaceAndPath("ancientrealms","textures/entity/storm_dragon.png")));
        LOGGER.info("Ancient Realms dragon renderers initialized.");
    }
}
