package com.ancientrealms.client.dragon;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
public final class DragonModel extends EntityModel<DragonRenderState> {
 public static final ModelLayerLocation LAYER=new ModelLayerLocation(Identifier.fromNamespaceAndPath("ancientrealms","dragon"),"main");
 private final ModelPart head,leftWing,rightWing,tail,leftFrontLeg,rightFrontLeg,leftHindLeg,rightHindLeg;
 public DragonModel(ModelPart root) {
  super(root); head=root.getChild("head"); leftWing=root.getChild("left_wing"); rightWing=root.getChild("right_wing"); tail=root.getChild("tail");
  leftFrontLeg=root.getChild("left_front_leg"); rightFrontLeg=root.getChild("right_front_leg"); leftHindLeg=root.getChild("left_hind_leg"); rightHindLeg=root.getChild("right_hind_leg");
 }
 public static LayerDefinition createBodyLayer() {
  MeshDefinition mesh=new MeshDefinition(); PartDefinition root=mesh.getRoot();
  root.addOrReplaceChild("body",CubeListBuilder.create().texOffs(0,0).addBox(-10,-8,-16,20,16,32),PartPose.offset(0,2,0));
  root.addOrReplaceChild("neck",CubeListBuilder.create().texOffs(0,52).addBox(-5,-5,-13,10,10,16),PartPose.offset(0,-3,-15));
  PartDefinition head=root.addOrReplaceChild("head",CubeListBuilder.create().texOffs(56,52).addBox(-6,-6,-9,12,12,14),PartPose.offset(0,-5,-27));
  head.addOrReplaceChild("snout",CubeListBuilder.create().texOffs(56,80).addBox(-4,-3,-8,8,6,10),PartPose.offset(0,1,-7));
  head.addOrReplaceChild("left_horn",CubeListBuilder.create().texOffs(96,0).addBox(-1,-8,-1,3,9,3),PartPose.offset(4,-4,1));
  head.addOrReplaceChild("right_horn",CubeListBuilder.create().texOffs(96,0).addBox(-2,-8,-1,3,9,3),PartPose.offset(-4,-4,1));
  PartDefinition lw=root.addOrReplaceChild("left_wing",CubeListBuilder.create().texOffs(0,100).addBox(0,-1,-5,30,2,24),PartPose.offset(9,-5,-8));
  lw.addOrReplaceChild("left_wing_finger",CubeListBuilder.create().texOffs(0,100).addBox(0,-1,-2,2,2,28),PartPose.offset(27,0,-2));
  PartDefinition rw=root.addOrReplaceChild("right_wing",CubeListBuilder.create().texOffs(0,100).addBox(-30,-1,-5,30,2,24),PartPose.offset(-9,-5,-8));
  rw.addOrReplaceChild("right_wing_finger",CubeListBuilder.create().texOffs(0,100).addBox(-2,-1,-2,2,2,28),PartPose.offset(-27,0,-2));
  root.addOrReplaceChild("tail",CubeListBuilder.create().texOffs(80,0).addBox(-3,-3,0,6,6,30),PartPose.offset(0,1,15));
  root.addOrReplaceChild("left_front_leg",CubeListBuilder.create().texOffs(96,20).addBox(-2,0,-2,5,13,6),PartPose.offset(7,4,-10));
  root.addOrReplaceChild("right_front_leg",CubeListBuilder.create().texOffs(96,20).addBox(-3,0,-2,5,13,6),PartPose.offset(-7,4,-10));
  root.addOrReplaceChild("left_hind_leg",CubeListBuilder.create().texOffs(96,20).addBox(-2,0,-2,5,13,6),PartPose.offset(7,4,10));
  root.addOrReplaceChild("right_hind_leg",CubeListBuilder.create().texOffs(96,20).addBox(-3,0,-2,5,13,6),PartPose.offset(-7,4,10));
  return LayerDefinition.create(mesh,128,128);
 }
 @Override public void setupAnim(DragonRenderState state) {
  super.setupAnim(state); float flap=Mth.sin(state.ageInTicks*0.42F)*0.62F;
  head.xRot=state.xRot*Mth.DEG_TO_RAD; head.yRot=state.yRot*Mth.DEG_TO_RAD; leftWing.zRot=flap; rightWing.zRot=-flap;
  tail.yRot=Mth.sin(state.ageInTicks*0.12F)*0.18F;
  float stride=Mth.cos(state.walkAnimationPos*0.55F)*state.walkAnimationSpeed*0.8F;
  leftFrontLeg.xRot=stride; rightHindLeg.xRot=stride; rightFrontLeg.xRot=-stride; leftHindLeg.xRot=-stride;
 }
}
