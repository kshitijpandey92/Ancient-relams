package com.ancientrealms.entities;

import com.ancientrealms.items.ModItems;
import com.ancientrealms.config.ModConfig;
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
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;

public class DragonEntity extends PathfinderMob {
 private static final int MAX_GROWTH_TICKS = 72000;
 private int growthTicks = MAX_GROWTH_TICKS;
 private int elementalCooldown = 0;
 private boolean saddled = false;
 private java.util.UUID bondedPlayer = null;

 public DragonEntity(EntityType<? extends DragonEntity> type, Level level) {
  super(type, level);
  this.xpReward = 45;
  applyGrowthScale();
 }

 public static AttributeSupplier.Builder createAttributes() {
  return PathfinderMob.createMobAttributes()
   .add(Attributes.MAX_HEALTH,180.0)
   .add(Attributes.ARMOR,14.0)
   .add(Attributes.ATTACK_DAMAGE,18.0)
   .add(Attributes.MOVEMENT_SPEED,0.28)
   .add(Attributes.FOLLOW_RANGE,56.0)
   .add(Attributes.KNOCKBACK_RESISTANCE,0.85)
   .add(Attributes.SCALE,1.0);
 }

 @Override protected void registerGoals() {
  goalSelector.addGoal(0,new FloatGoal(this));
  goalSelector.addGoal(1,new MeleeAttackGoal(this,1.18,true));
  goalSelector.addGoal(2,new WaterAvoidingRandomStrollGoal(this,0.9));
  goalSelector.addGoal(3,new LookAtPlayerGoal(this,Player.class,32.0F));
  goalSelector.addGoal(4,new RandomLookAroundGoal(this));
  targetSelector.addGoal(1,new HurtByTargetGoal(this));
  targetSelector.addGoal(2,new NearestAttackableTargetGoal<>(this,Player.class,true) {
   @Override public boolean canUse() {
    return bondedPlayer == null && super.canUse();
   }
  });
 }

 @Override public InteractionResult mobInteract(Player player, InteractionHand hand) {
  var held = player.getItemInHand(hand);
  if (!level().isClientSide() && bondedPlayer == null && held.is(ModItems.DRAGON_TREAT)) {
   bondedPlayer = player.getUUID();
   if (!player.getAbilities().instabuild) held.shrink(1);
   setTarget(null);
   player.sendSystemMessage(net.minecraft.network.chat.Component.literal("The dragon has bonded with you!"));
   return InteractionResult.SUCCESS;
  }
  if (!level().isClientSide() && bondedPlayer != null && bondedPlayer.equals(player.getUUID()) && held.is(ModItems.DRAGON_TREAT) && getHealth() < getMaxHealth()) {
   heal(12.0F);
   if (!player.getAbilities().instabuild) held.shrink(1);
   player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Your dragon enjoyed the treat and recovered health."));
   return InteractionResult.SUCCESS;
  }
  if (!level().isClientSide() && bondedPlayer != null && bondedPlayer.equals(player.getUUID()) && !saddled && held.is(ModItems.DRAGON_SADDLE)) {
   saddled = true;
   if (!player.getAbilities().instabuild) held.shrink(1);
   setTarget(null);
   getNavigation().stop();
   player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Dragon saddled! Interact again to mount."));
   return InteractionResult.SUCCESS;
  }
  if (saddled && bondedPlayer != null && bondedPlayer.equals(player.getUUID()) && getPassengers().isEmpty() && !player.isPassenger()) {
   if (!level().isClientSide()) {
    setTarget(null);
    getNavigation().stop();
    player.startRiding(this);
   }
   return InteractionResult.SUCCESS;
  }
  return super.mobInteract(player, hand);
 }


 @Override public LivingEntity getControllingPassenger() {
  return getPassengers().isEmpty() ? null : (getPassengers().get(0) instanceof LivingEntity rider ? rider : null);
 }

 @Override public void travel(Vec3 travelVector) {
  if (getControllingPassenger() instanceof Player rider) {
   setNoGravity(true);
   setYRot(rider.getYRot());
   setXRot(rider.getXRot() * 0.5F);
   float forward = rider.zza;
   float strafe = rider.xxa;
   double yaw = Math.toRadians(rider.getYRot());
   double pitch = Math.toRadians(rider.getXRot());
   double vx = (-Math.sin(yaw) * forward + Math.cos(yaw) * strafe) * 0.16;
   double vz = ( Math.cos(yaw) * forward + Math.sin(yaw) * strafe) * 0.16;
   double vy = -Math.sin(pitch) * forward * 0.16;
   Vec3 velocity = getDeltaMovement().scale(0.78).add(vx, vy, vz);
   setDeltaMovement(velocity);
   move(MoverType.SELF, velocity);
   return;
  }
  if (isNoGravity()) setNoGravity(false);
  super.travel(travelVector);
 }

 public void setHatchling() {
  growthTicks = 0;
  applyGrowthScale();
 }

 private void applyGrowthScale() {
  var scale = getAttribute(Attributes.SCALE);
  if (scale != null) {
   double progress = Math.min(1.0, growthTicks / (double) MAX_GROWTH_TICKS);
   scale.setBaseValue(0.35 + 0.65 * progress);
  }
 }

 @Override public void tick() {
  super.tick();
  if (!level().isClientSide() && growthTicks < MAX_GROWTH_TICKS) {
   int growthStep = Math.max(1, (int) Math.round(ModConfig.get().dragonGrowthSpeed));
   growthTicks = Math.min(MAX_GROWTH_TICKS, growthTicks + growthStep);
   if (growthTicks % 1200 == 0 || growthTicks == MAX_GROWTH_TICKS) applyGrowthScale();
  }
  if (elementalCooldown > 0) elementalCooldown--;
  LivingEntity target = getTarget();
  if (target != null && bondedPlayer != null && target.getUUID().equals(bondedPlayer)) { setTarget(null); target = null; }
  if (!level().isClientSide() && target != null && distanceToSqr(target) < 225.0 && elementalCooldown == 0) {
   if (getType() == DragonTypes.EMBER_DRAGON) {
    target.hurt(damageSources().mobAttack(this), 8.0F);
    target.igniteForSeconds(6.0F);
   } else if (getType() == DragonTypes.FROST_DRAGON) {
    target.hurt(damageSources().mobAttack(this), 8.0F);
    target.setTicksFrozen(Math.min(300, target.getTicksFrozen() + 100));
   } else {
    double dx = target.getX() - getX();
    double dz = target.getZ() - getZ();
    target.hurt(damageSources().mobAttack(this), 8.0F);
    target.push(dx * 0.12, 0.8, dz * 0.12);
   }
   elementalCooldown = 100;
  }
 }


 @Override
 protected void addAdditionalSaveData(net.minecraft.world.level.storage.ValueOutput output) {
  super.addAdditionalSaveData(output);
  output.putBoolean("AncientRealmsSaddled", saddled);
  output.putInt("AncientRealmsGrowthTicks", growthTicks);
  if (bondedPlayer != null) output.putString("AncientRealmsBondedPlayer", bondedPlayer.toString());
 }

 @Override
 protected void readAdditionalSaveData(net.minecraft.world.level.storage.ValueInput input) {
  super.readAdditionalSaveData(input);
  saddled = input.getBooleanOr("AncientRealmsSaddled", false);
  growthTicks = Math.max(0, Math.min(MAX_GROWTH_TICKS, input.getIntOr("AncientRealmsGrowthTicks", MAX_GROWTH_TICKS)));
  applyGrowthScale();
  String owner = input.getString("AncientRealmsBondedPlayer").orElse("");
  try {
   bondedPlayer = owner.isEmpty() ? null : java.util.UUID.fromString(owner);
  } catch (IllegalArgumentException ignored) {
   bondedPlayer = null;
   saddled = false;
  }
 }

 @Override public boolean fireImmune() {
  return getType()==DragonTypes.EMBER_DRAGON || super.fireImmune();
 }

 @Override public boolean canBeLeashed() { return false; }
}
