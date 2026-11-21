package com.ancientrealms.entities;

import com.ancientrealms.items.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class DragonEntity extends PathfinderMob {
 private int elementalCooldown = 0;
 private boolean saddled = false;

 public DragonEntity(EntityType<? extends DragonEntity> type, Level level) {
  super(type, level);
  this.xpReward = 45;
 }

 public static AttributeSupplier.Builder createAttributes() {
  return PathfinderMob.createMobAttributes()
   .add(Attributes.MAX_HEALTH,180.0)
   .add(Attributes.ARMOR,14.0)
   .add(Attributes.ATTACK_DAMAGE,18.0)
   .add(Attributes.MOVEMENT_SPEED,0.28)
   .add(Attributes.FOLLOW_RANGE,56.0)
   .add(Attributes.KNOCKBACK_RESISTANCE,0.85);
 }

 @Override protected void registerGoals() {
  goalSelector.addGoal(0,new FloatGoal(this));
  goalSelector.addGoal(1,new MeleeAttackGoal(this,1.18,true));
  goalSelector.addGoal(2,new WaterAvoidingRandomStrollGoal(this,0.9));
  goalSelector.addGoal(3,new LookAtPlayerGoal(this,Player.class,32.0F));
  goalSelector.addGoal(4,new RandomLookAroundGoal(this));
  targetSelector.addGoal(1,new HurtByTargetGoal(this));
  targetSelector.addGoal(2,new NearestAttackableTargetGoal<>(this,Player.class,true));
 }

 @Override public InteractionResult mobInteract(Player player, InteractionHand hand) {
  var held = player.getItemInHand(hand);
  if (!level().isClientSide() && !saddled && held.is(ModItems.DRAGON_SADDLE)) {
   saddled = true;
   if (!player.getAbilities().instabuild) held.shrink(1);
   setTarget(null);
   getNavigation().stop();
   player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Dragon saddled! Interact again to mount."));
   return InteractionResult.SUCCESS;
  }
  if (saddled && getPassengers().isEmpty() && !player.isPassenger()) {
   if (!level().isClientSide()) {
    setTarget(null);
    getNavigation().stop();
    player.startRiding(this);
   }
   return InteractionResult.SUCCESS;
  }
  return super.mobInteract(player, hand);
 }

 @Override public void tick() {
  super.tick();
  if (elementalCooldown > 0) elementalCooldown--;
  LivingEntity target = getTarget();
  if (!level().isClientSide() && target != null && distanceToSqr(target) < 225.0 && elementalCooldown == 0) {
   if (getType() == DragonTypes.EMBER_DRAGON) {
    target.igniteForSeconds(6.0F);
   } else if (getType() == DragonTypes.FROST_DRAGON) {
    target.setTicksFrozen(Math.min(300, target.getTicksFrozen() + 100));
   } else {
    double dx = target.getX() - getX();
    double dz = target.getZ() - getZ();
    target.push(dx * 0.12, 0.8, dz * 0.12);
   }
   elementalCooldown = 100;
  }
 }

 @Override public boolean fireImmune() {
  return getType()==DragonTypes.EMBER_DRAGON || super.fireImmune();
 }

 @Override public boolean canBeLeashed() { return false; }
}
