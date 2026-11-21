package com.ancientrealms.client.dragon;
import com.ancientrealms.entities.DragonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
public final class DragonRenderer extends MobRenderer<DragonEntity,DragonRenderState,DragonModel> {
 private final Identifier texture;
 public DragonRenderer(EntityRendererProvider.Context context,Identifier texture) { super(context,new DragonModel(context.bakeLayer(DragonModel.LAYER)),1.8F); this.texture=texture; }
 @Override public DragonRenderState createRenderState() { return new DragonRenderState(); }
 @Override public Identifier getTextureLocation(DragonRenderState state) { return texture; }
}
