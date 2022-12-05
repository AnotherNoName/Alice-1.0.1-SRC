//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockDeadBush
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockSnow
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.item.EntityBoat
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.item.EntityMinecart
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.monster.EntityIronGolem
 *  net.minecraft.entity.monster.EntityPigZombie
 *  net.minecraft.entity.passive.EntityAmbientCreature
 *  net.minecraft.entity.passive.EntitySquid
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityFireball
 *  net.minecraft.entity.projectile.EntityShulkerBullet
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Enchantments
 *  net.minecraft.init.MobEffects
 *  net.minecraft.item.ItemAxe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.MovementInput
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.EmptyChunk
 */
package me.snow.aclient.util;

import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import me.snow.aclient.AliceMain;
import me.snow.aclient.mixin.mixins.accessors.IEntityLivingBase;
import me.snow.aclient.module.modules.client.Global;
import me.snow.aclient.module.modules.combat.Killaura;
import me.snow.aclient.module.modules.player.FakeLag;
import me.snow.aclient.module.modules.player.Freecam;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.DamageUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.RotationUtil;
import me.snow.aclient.util.Util;
import me.snow.aclient.util.skid.oyvey.BlockUtil2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumHand;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.chunk.EmptyChunk;

public class EntityUtil
implements Util {
    public static final /* synthetic */ Vec3d[] doubleLegOffsetList;
    public static final /* synthetic */ Vec3d[] antiStepOffsetList;
    public static final /* synthetic */ Vec3d[] offsetsNoHead;
    public static final /* synthetic */ Vec3d[] platformOffsetList;
    public static final /* synthetic */ Vec3d[] headpiece;
    public static final /* synthetic */ Vec3d[] OffsetList;
    public static final /* synthetic */ Vec3d[] legOffsetList;
    public static final /* synthetic */ Vec3d[] antiScaffoldOffsetList;
    public static final /* synthetic */ Vec3d[] antiDropOffsetList;

    public static Boolean posEqualsBlock(BlockPos blockPos, Block block) {
        return EntityUtil.getBlockOnPos(blockPos).equals((Object)block);
    }

    public static boolean isAboveWater(Entity entity, boolean bl) {
        if (entity == null) {
            return false;
        }
        double d = entity.posY - (bl ? 0.03 : (EntityUtil.isPlayer(entity) ? 0.2 : 0.5));
        for (int i = MathHelper.floor((double)entity.posX); i < MathHelper.ceil((double)entity.posX); ++i) {
            for (int j = MathHelper.floor((double)entity.posZ); j < MathHelper.ceil((double)entity.posZ); ++j) {
                BlockPos blockPos = new BlockPos(i, MathHelper.floor((double)d), j);
                if (!(EntityUtil.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid)) continue;
                return true;
            }
        }
        return false;
    }

    public static double[] forward(double d) {
        float f = EntityUtil.mc.player.movementInput.field_192832_b;
        float f2 = EntityUtil.mc.player.movementInput.moveStrafe;
        float f3 = EntityUtil.mc.player.prevRotationYaw + (EntityUtil.mc.player.rotationYaw - EntityUtil.mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
        if (f != 0.0f) {
            if (f2 > 0.0f) {
                f3 += (float)(f > 0.0f ? -45 : 45);
            } else if (f2 < 0.0f) {
                f3 += (float)(f > 0.0f ? 45 : -45);
            }
            f2 = 0.0f;
            if (f > 0.0f) {
                f = 1.0f;
            } else if (f < 0.0f) {
                f = -1.0f;
            }
        }
        double d2 = Math.sin(Math.toRadians(f3 + 90.0f));
        double d3 = Math.cos(Math.toRadians(f3 + 90.0f));
        double d4 = (double)f * d * d3 + (double)f2 * d * d2;
        double d5 = (double)f * d * d2 - (double)f2 * d * d3;
        return new double[]{d4, d5};
    }

    public static void attackEntity(Entity entity, boolean bl, boolean bl2) {
        if (bl) {
            EntityUtil.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        } else {
            EntityUtil.mc.playerController.attackEntity((EntityPlayer)EntityUtil.mc.player, entity);
        }
        if (bl2) {
            EntityUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

    public static boolean isCrystalAtFeet(EntityEnderCrystal entityEnderCrystal, double d) {
        for (EntityPlayer entityPlayer : EntityUtil.mc.world.playerEntities) {
            if (EntityUtil.mc.player.getDistanceSqToEntity((Entity)entityPlayer) > d * d || AliceMain.friendManager.isFriend(entityPlayer)) continue;
            for (Vec3d vec3d : doubleLegOffsetList) {
                if (new BlockPos(entityPlayer.getPositionVector()).add(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord) != entityEnderCrystal.getPosition()) continue;
                return true;
            }
        }
        return false;
    }

    public static Vec3d getInterpolatedAmount(Entity entity, float f) {
        return EntityUtil.getInterpolatedAmount(entity, f, f, f);
    }

    public static double getRelativeX(float f) {
        return MathHelper.sin((float)(-f * ((float)Math.PI / 180)));
    }

    public static float getHealth(Entity entity) {
        if (EntityUtil.isLiving(entity)) {
            EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            return entityLivingBase.getHealth() + entityLivingBase.getAbsorptionAmount();
        }
        return 0.0f;
    }

    public static BlockPos GetPositionVectorBlockPos(Entity entity, @Nullable BlockPos blockPos) {
        Vec3d vec3d = entity.getPositionVector();
        if (blockPos == null) {
            return new BlockPos(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
        }
        return new BlockPos(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord).add((Vec3i)blockPos);
    }

    public static boolean isNeutralMob(Entity entity) {
        return entity instanceof EntityPigZombie || entity instanceof EntityWolf || entity instanceof EntityEnderman;
    }

    public static List<Vec3d> getOffsetList(int n, boolean bl, boolean bl2) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        if (bl2) {
            arrayList.add(new Vec3d(-1.0, (double)n, 0.0));
            arrayList.add(new Vec3d(1.0, (double)n, 0.0));
            arrayList.add(new Vec3d(0.0, (double)n, -1.0));
            arrayList.add(new Vec3d(0.0, (double)n, 1.0));
        } else {
            arrayList.add(new Vec3d(-1.0, (double)n, 0.0));
        }
        if (bl) {
            arrayList.add(new Vec3d(0.0, (double)(n - 1), 0.0));
        }
        return arrayList;
    }

    public static boolean stopSneaking(boolean bl) {
        if (bl && EntityUtil.mc.player != null) {
            EntityUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)EntityUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        return false;
    }

    public static void swingArmNoPacket(EnumHand enumHand, EntityLivingBase entityLivingBase) {
        ItemStack itemStack = entityLivingBase.getHeldItem(enumHand);
        if (!itemStack.func_190926_b() && itemStack.getItem().onEntitySwing(entityLivingBase, itemStack)) {
            return;
        }
        if (!entityLivingBase.isSwingInProgress || entityLivingBase.swingProgressInt >= ((IEntityLivingBase)entityLivingBase).getArmSwingAnimationEnd() / 2 || entityLivingBase.swingProgressInt < 0) {
            entityLivingBase.swingProgressInt = -1;
            entityLivingBase.isSwingInProgress = true;
            entityLivingBase.swingingHand = enumHand;
        }
    }

    public static BlockPos getPlayerPos(EntityPlayer entityPlayer) {
        return new BlockPos(Math.floor(entityPlayer.posX), Math.floor(entityPlayer.posY), Math.floor(entityPlayer.posZ));
    }

    public static boolean isObbyHole(BlockPos blockPos) {
        BlockPos[] arrblockPos;
        for (BlockPos blockPos2 : arrblockPos = new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()}) {
            IBlockState iBlockState = EntityUtil.mc.world.getBlockState(blockPos2);
            if (iBlockState.getBlock() != Blocks.AIR && iBlockState.getBlock() == Blocks.OBSIDIAN) continue;
            return false;
        }
        return true;
    }

    public static float getHealth(Entity entity, boolean bl) {
        if (EntityUtil.isLiving(entity)) {
            EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            return entityLivingBase.getHealth() + (bl ? entityLivingBase.getAbsorptionAmount() : 0.0f);
        }
        return 0.0f;
    }

    public static List<Vec3d> getUnsafeBlocksFromVec3d(Vec3d vec3d, int n, boolean bl, boolean bl2) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        for (Vec3d vec3d2 : EntityUtil.getOffsets(n, bl, bl2)) {
            BlockPos blockPos = new BlockPos(vec3d).add(vec3d2.xCoord, vec3d2.yCoord, vec3d2.zCoord);
            Block block = EntityUtil.mc.world.getBlockState(blockPos).getBlock();
            if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid) && !(block instanceof BlockTallGrass) && !(block instanceof BlockFire) && !(block instanceof BlockDeadBush) && !(block instanceof BlockSnow)) continue;
            arrayList.add(vec3d2);
        }
        return arrayList;
    }

    public static boolean isAboveBlock(Entity entity, BlockPos blockPos) {
        return entity.posY >= (double)blockPos.getY();
    }

    public static boolean checkCollide() {
        return !EntityUtil.mc.player.isSneaking() && (EntityUtil.mc.player.getRidingEntity() == null || EntityUtil.mc.player.getRidingEntity().fallDistance < 3.0f) && EntityUtil.mc.player.fallDistance < 3.0f;
    }

    public static boolean isMobAggressive(Entity entity) {
        if (entity instanceof EntityPigZombie) {
            if (((EntityPigZombie)entity).isArmsRaised() || ((EntityPigZombie)entity).isAngry()) {
                return true;
            }
        } else {
            if (entity instanceof EntityWolf) {
                return ((EntityWolf)entity).isAngry() && !EntityUtil.mc.player.equals((Object)((EntityWolf)entity).getOwner());
            }
            if (entity instanceof EntityEnderman) {
                return ((EntityEnderman)entity).isScreaming();
            }
        }
        return EntityUtil.isHostileMob(entity);
    }

    public static boolean isDrivenByPlayer(Entity entity) {
        return EntityUtil.mc.player != null && entity != null && entity.equals((Object)EntityUtil.mc.player.getRidingEntity());
    }

    public static float calculatePosDamage(BlockPos blockPos, EntityPlayer entityPlayer) {
        return EntityUtil.calculatePosDamage((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1.0, (double)blockPos.getZ() + 0.5, (Entity)entityPlayer);
    }

    public static Vec3d[] getUnsafeBlockArray(Entity entity, int n, boolean bl, boolean bl2) {
        List<Vec3d> list = EntityUtil.getUnsafeBlocks(entity, n, bl, bl2);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])arrvec3d);
    }

    public static void setTimer(float f) {
        Minecraft.getMinecraft().timer.field_194149_e = 50.0f / f;
    }

    public static boolean isLiving(Entity entity) {
        return entity instanceof EntityLivingBase;
    }

    public static boolean isValid(Entity entity, double d) {
        return !EntityUtil.isntValid(entity, d);
    }

    public static void moveEntityStrafe(double d, Entity entity) {
        if (entity != null) {
            MovementInput movementInput = EntityUtil.mc.player.movementInput;
            double d2 = movementInput.field_192832_b;
            double d3 = movementInput.moveStrafe;
            float f = EntityUtil.mc.player.rotationYaw;
            if (d2 == 0.0 && d3 == 0.0) {
                entity.motionX = 0.0;
                entity.motionZ = 0.0;
            } else {
                if (d2 != 0.0) {
                    if (d3 > 0.0) {
                        f += (float)(d2 > 0.0 ? -45 : 45);
                    } else if (d3 < 0.0) {
                        f += (float)(d2 > 0.0 ? 45 : -45);
                    }
                    d3 = 0.0;
                    if (d2 > 0.0) {
                        d2 = 1.0;
                    } else if (d2 < 0.0) {
                        d2 = -1.0;
                    }
                }
                entity.motionX = d2 * d * Math.cos(Math.toRadians(f + 90.0f)) + d3 * d * Math.sin(Math.toRadians(f + 90.0f));
                entity.motionZ = d2 * d * Math.sin(Math.toRadians(f + 90.0f)) - d3 * d * Math.cos(Math.toRadians(f + 90.0f));
            }
        }
    }

    public static boolean checkForLiquid(Entity entity, boolean bl) {
        if (entity == null) {
            return false;
        }
        double d = entity.posY;
        double d2 = bl ? 0.03 : (entity instanceof EntityPlayer ? 0.2 : 0.5);
        double d3 = d - d2;
        for (int i = MathHelper.floor((double)entity.posX); i < MathHelper.ceil((double)entity.posX); ++i) {
            for (int j = MathHelper.floor((double)entity.posZ); j < MathHelper.ceil((double)entity.posZ); ++j) {
                if (!(EntityUtil.mc.world.getBlockState(new BlockPos(i, MathHelper.floor((double)d3), j)).getBlock() instanceof BlockLiquid)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean isSafe(Entity entity, int n, boolean bl, boolean bl2) {
        return EntityUtil.getUnsafeBlocks(entity, n, bl, bl2).size() == 0;
    }

    public static Vec3d[] getOffsets(int n, boolean bl, boolean bl2) {
        List<Vec3d> list = EntityUtil.getOffsetList(n, bl, bl2);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])arrvec3d);
    }

    public static boolean isOnLiquid() {
        double d = EntityUtil.mc.player.posY - 0.03;
        for (int i = MathHelper.floor((double)EntityUtil.mc.player.posX); i < MathHelper.ceil((double)EntityUtil.mc.player.posX); ++i) {
            for (int j = MathHelper.floor((double)EntityUtil.mc.player.posZ); j < MathHelper.ceil((double)EntityUtil.mc.player.posZ); ++j) {
                BlockPos blockPos = new BlockPos(i, MathHelper.floor((double)d), j);
                if (!(EntityUtil.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean isBothHole(BlockPos blockPos) {
        BlockPos[] arrblockPos;
        for (BlockPos blockPos2 : arrblockPos = new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()}) {
            IBlockState iBlockState = EntityUtil.mc.world.getBlockState(blockPos2);
            if (iBlockState.getBlock() != Blocks.AIR && (iBlockState.getBlock() == Blocks.BEDROCK || iBlockState.getBlock() == Blocks.OBSIDIAN)) continue;
            return false;
        }
        return true;
    }

    public static List<Vec3d> targets(Vec3d vec3d, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6, boolean bl7) {
        List<Vec3d> list;
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        if (bl5) {
            Collections.addAll(arrayList, BlockUtil.convertVec3ds(vec3d, antiDropOffsetList));
        }
        if (bl4) {
            Collections.addAll(arrayList, BlockUtil.convertVec3ds(vec3d, platformOffsetList));
        }
        if (bl3) {
            Collections.addAll(arrayList, BlockUtil.convertVec3ds(vec3d, legOffsetList));
        }
        Collections.addAll(arrayList, BlockUtil.convertVec3ds(vec3d, OffsetList));
        if (bl2) {
            Collections.addAll(arrayList, BlockUtil.convertVec3ds(vec3d, antiStepOffsetList));
        } else {
            list = EntityUtil.getUnsafeBlocksFromVec3d(vec3d, 2, false, bl7);
            if (list.size() == 4) {
                block4: for (Vec3d vec3d2 : list) {
                    BlockPos blockPos = new BlockPos(vec3d).add(vec3d2.xCoord, vec3d2.yCoord, vec3d2.zCoord);
                    switch (BlockUtil.isPositionPlaceable(blockPos, bl6)) {
                        case -1: 
                        case 1: 
                        case 2: {
                            continue block4;
                        }
                        case 3: {
                            arrayList.add(vec3d.add(vec3d2));
                        }
                    }
                }
            }
        }
        if (bl) {
            Collections.addAll(arrayList, BlockUtil.convertVec3ds(vec3d, antiScaffoldOffsetList));
        }
        if (!bl7) {
            list = new ArrayList<Vec3d>();
            list.add(new Vec3d(1.0, 1.0, 0.0));
            list.add(new Vec3d(0.0, 1.0, -1.0));
            list.add(new Vec3d(0.0, 1.0, 1.0));
            Vec3d[] arrvec3d = new Vec3d[list.size()];
            arrayList.removeAll(Arrays.asList(BlockUtil.convertVec3ds(vec3d, list.toArray((T[])arrvec3d))));
        }
        return arrayList;
    }

    public static BlockPos getEntityPos(Entity entity) {
        return new BlockPos(entity);
    }

    public static double getBaseMotionSpeed() {
        double d = 0.272;
        if (Util.mc.player.isPotionActive(MobEffects.SPEED)) {
            int n = Objects.requireNonNull(Util.mc.player.getActivePotionEffect(MobEffects.SPEED)).getAmplifier();
            d *= 1.0 + 0.2 * (double)n;
        }
        return d;
    }

    public static BlockPos getPlayerPos() {
        return new BlockPos(EntityUtil.mc.player.posX, EntityUtil.mc.player.posY, EntityUtil.mc.player.posZ);
    }

    public static double getEntitySpeed(Entity entity) {
        if (entity != null) {
            double d = entity.posX - entity.prevPosX;
            double d2 = entity.posZ - entity.prevPosZ;
            double d3 = MathHelper.sqrt((double)(d * d + d2 * d2));
            return d3 * 20.0;
        }
        return 0.0;
    }

    public static List<EntityPlayer> getNearbyPlayers(double d) {
        if (EntityUtil.mc.world.getLoadedEntityList().size() == 0) {
            return null;
        }
        List<EntityPlayer> list = EntityUtil.mc.world.playerEntities.stream().filter(entityPlayer -> EntityUtil.mc.player != entityPlayer).filter(entityPlayer -> (double)EntityUtil.mc.player.getDistanceToEntity((Entity)entityPlayer) <= d).filter(entityPlayer -> !(EntityUtil.getHealth((Entity)entityPlayer) < 0.0f)).collect(Collectors.toList());
        list.removeIf(entityPlayer -> AliceMain.friendManager.isFriend(entityPlayer.getName()));
        return list;
    }

    public static List<EntityPlayer> getPlayers2() {
        return EntityUtil.mc.world.playerEntities.stream().filter(entityPlayer -> entityPlayer.entityId != EntityUtil.mc.player.entityId).collect(Collectors.toList());
    }

    public static boolean isAlive(Entity entity) {
        return EntityUtil.isLiving(entity) && !entity.isDead && ((EntityLivingBase)entity).getHealth() > 0.0f;
    }

    public static boolean isAboveWater(Entity entity) {
        return EntityUtil.isAboveWater(entity, false);
    }

    public static List<Vec3d> getTrapOffsetsList(boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>(EntityUtil.getOffsetList(1, false, bl6));
        arrayList.add(new Vec3d(0.0, 2.0, 0.0));
        if (bl) {
            arrayList.add(new Vec3d(0.0, 3.0, 0.0));
        }
        if (bl2) {
            arrayList.addAll(EntityUtil.getOffsetList(2, false, bl6));
        }
        if (bl3) {
            arrayList.addAll(EntityUtil.getOffsetList(0, false, bl6));
        }
        if (bl4) {
            arrayList.addAll(EntityUtil.getOffsetList(-1, false, bl6));
            arrayList.add(new Vec3d(0.0, -1.0, 0.0));
        }
        if (bl5) {
            arrayList.add(new Vec3d(0.0, -2.0, 0.0));
        }
        return arrayList;
    }

    public static BlockPos getPlayerPos(double d) {
        return new BlockPos(Math.floor(EntityUtil.mc.player.posX), Math.floor(EntityUtil.mc.player.posY + d), Math.floor(EntityUtil.mc.player.posZ));
    }

    public static Vec3d getInterpolatedRenderPos(Entity entity, float f) {
        return EntityUtil.getInterpolatedPos(entity, f).subtract(EntityUtil.mc.getRenderManager().renderPosX, EntityUtil.mc.getRenderManager().renderPosY, EntityUtil.mc.getRenderManager().renderPosZ);
    }

    public static boolean isBedrockHole(BlockPos blockPos) {
        BlockPos[] arrblockPos;
        for (BlockPos blockPos2 : arrblockPos = new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()}) {
            IBlockState iBlockState = EntityUtil.mc.world.getBlockState(blockPos2);
            if (iBlockState.getBlock() != Blocks.AIR && iBlockState.getBlock() == Blocks.BEDROCK) continue;
            return false;
        }
        return true;
    }

    public static boolean holding32k(EntityPlayer entityPlayer) {
        return EntityUtil.is32k(entityPlayer.getHeldItemMainhand());
    }

    public static List<Vec3d> getUntrappedBlocksExtended(int n, EntityPlayer entityPlayer, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6, boolean bl7, boolean bl8) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        if (n == 1) {
            arrayList.addAll(EntityUtil.targets(entityPlayer.getPositionVector(), bl, bl2, bl3, bl4, bl5, bl6, bl8));
        } else {
            int n2 = 1;
            for (Vec3d vec3d : MathUtil.getBlockBlocks((Entity)entityPlayer)) {
                if (n2 > n) break;
                arrayList.addAll(EntityUtil.targets(vec3d, !bl7, bl2, bl3, bl4, bl5, bl6, bl8));
                ++n2;
            }
        }
        ArrayList<Vec3d> arrayList2 = new ArrayList<Vec3d>();
        for (Vec3d vec3d : arrayList) {
            BlockPos blockPos = new BlockPos(vec3d);
            if (BlockUtil.isPositionPlaceable(blockPos, bl6) != -1) continue;
            arrayList2.add(vec3d);
        }
        for (Vec3d vec3d : arrayList2) {
            arrayList.remove((Object)vec3d);
        }
        return arrayList;
    }

    public static boolean isOnLiquid(double d) {
        if (EntityUtil.mc.player.fallDistance >= 3.0f) {
            return false;
        }
        AxisAlignedBB axisAlignedBB = EntityUtil.mc.player.getRidingEntity() != null ? EntityUtil.mc.player.getRidingEntity().getEntityBoundingBox().func_191195_a(0.0, 0.0, 0.0).offset(0.0, -d, 0.0) : EntityUtil.mc.player.getEntityBoundingBox().func_191195_a(0.0, 0.0, 0.0).offset(0.0, -d, 0.0);
        boolean bl = false;
        int n = (int)axisAlignedBB.minY;
        for (int i = MathHelper.floor((double)axisAlignedBB.minX); i < MathHelper.floor((double)(axisAlignedBB.maxX + 1.0)); ++i) {
            for (int j = MathHelper.floor((double)axisAlignedBB.minZ); j < MathHelper.floor((double)(axisAlignedBB.maxZ + 1.0)); ++j) {
                Block block = EntityUtil.mc.world.getBlockState(new BlockPos(i, n, j)).getBlock();
                if (block == Blocks.AIR) continue;
                if (!(block instanceof BlockLiquid)) {
                    return false;
                }
                bl = true;
            }
        }
        return bl;
    }

    public static boolean isPlayerSafe(EntityPlayer entityPlayer) {
        BlockPos blockPos = EntityUtil.getPlayerPos(entityPlayer);
        return !(EntityUtil.mc.world.getBlockState(blockPos.down()).getBlock() != Blocks.OBSIDIAN && EntityUtil.mc.world.getBlockState(blockPos.down()).getBlock() != Blocks.BEDROCK || EntityUtil.mc.world.getBlockState(blockPos.north()).getBlock() != Blocks.OBSIDIAN && EntityUtil.mc.world.getBlockState(blockPos.north()).getBlock() != Blocks.BEDROCK || EntityUtil.mc.world.getBlockState(blockPos.east()).getBlock() != Blocks.OBSIDIAN && EntityUtil.mc.world.getBlockState(blockPos.east()).getBlock() != Blocks.BEDROCK || EntityUtil.mc.world.getBlockState(blockPos.south()).getBlock() != Blocks.OBSIDIAN && EntityUtil.mc.world.getBlockState(blockPos.south()).getBlock() != Blocks.BEDROCK || EntityUtil.mc.world.getBlockState(blockPos.west()).getBlock() != Blocks.OBSIDIAN && EntityUtil.mc.world.getBlockState(blockPos.west()).getBlock() != Blocks.BEDROCK);
    }

    public static boolean isSafe(Entity entity) {
        return EntityUtil.isSafe(entity, 0, false, true);
    }

    public static boolean simpleIs32k(ItemStack itemStack) {
        return EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.SHARPNESS, (ItemStack)itemStack) >= 1000;
    }

    public static float calculatePosDamage(double d, double d2, double d3, Entity entity) {
        float f = 12.0f;
        double d4 = entity.getDistance(d, d2, d3) / (double)f;
        Vec3d vec3d = new Vec3d(d, d2, d3);
        double d5 = entity.world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
        double d6 = (1.0 - d4) * d5;
        float f2 = (int)((d6 * d6 + d6) / 2.0 * 7.0 * (double)f + 1.0);
        double d7 = 1.0;
        if (entity instanceof EntityLivingBase) {
            d7 = DamageUtil.getBlastReduction((EntityLivingBase)entity, EntityUtil.getMultipliedDamage(f2), new Explosion((World)EntityUtil.mc.world, null, d, d2, d3, 6.0f, false, true));
        }
        return (float)d7;
    }

    public static Vec3d getInterpolatedPos(Entity entity, float f) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(EntityUtil.getInterpolatedAmount(entity, f));
    }

    public static BlockPos getPlayerPosWithEntity() {
        return new BlockPos(EntityUtil.mc.player.getRidingEntity() != null ? EntityUtil.mc.player.getRidingEntity().posX : EntityUtil.mc.player.posX, EntityUtil.mc.player.getRidingEntity() != null ? EntityUtil.mc.player.getRidingEntity().posY : EntityUtil.mc.player.posY, EntityUtil.mc.player.getRidingEntity() != null ? EntityUtil.mc.player.getRidingEntity().posZ : EntityUtil.mc.player.posZ);
    }

    public static List<Vec3d> getUnsafeBlocks(Entity entity, int n, boolean bl, boolean bl2) {
        return EntityUtil.getUnsafeBlocksFromVec3d(entity.getPositionVector().addVector(0.0, 0.125, 0.0), n, bl, bl2);
    }

    public static boolean getSurroundWeakness(Vec3d vec3d, int n, int n2) {
        Block block;
        Block block2;
        BlockPos blockPos;
        switch (n) {
            case 1: {
                blockPos = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen(blockPos.getX() - 2, blockPos.getY(), blockPos.getZ()) && Math.sqrt(EntityUtil.mc.player.getDistanceSq((double)(blockPos.getX() - 2), (double)blockPos.getY(), (double)blockPos.getZ())) > 3.0) {
                    return false;
                }
                Block block3 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-2, 1, 0)).getBlock();
                if (block3 != Blocks.AIR && block3 != Blocks.FIRE || (block2 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-2, 0, 0)).getBlock()) != Blocks.AIR && block2 != Blocks.FIRE || (block = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-2, -1, 0)).getBlock()) != Blocks.OBSIDIAN && block != Blocks.BEDROCK || EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 2: {
                blockPos = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen(blockPos.getX() + 2, blockPos.getY(), blockPos.getZ()) && Math.sqrt(EntityUtil.mc.player.getDistanceSq((double)(blockPos.getX() + 2), (double)blockPos.getY(), (double)blockPos.getZ())) > 3.0) {
                    return false;
                }
                Block block4 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(2, 1, 0)).getBlock();
                if (block4 != Blocks.AIR && block4 != Blocks.FIRE || (block2 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(2, 0, 0)).getBlock()) != Blocks.AIR && block2 != Blocks.FIRE || (block = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(2, -1, 0)).getBlock()) != Blocks.OBSIDIAN && block != Blocks.BEDROCK || EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(1, 0, 0)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 3: {
                blockPos = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen(blockPos.getX(), blockPos.getY(), blockPos.getZ() - 2) && Math.sqrt(EntityUtil.mc.player.getDistanceSq((double)blockPos.getX(), (double)blockPos.getY(), (double)(blockPos.getZ() - 2))) > 3.0) {
                    return false;
                }
                Block block5 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, -2)).getBlock();
                if (block5 != Blocks.AIR && block5 != Blocks.FIRE || (block2 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -2)).getBlock()) != Blocks.AIR && block2 != Blocks.FIRE || (block = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, -1, -2)).getBlock()) != Blocks.OBSIDIAN && block != Blocks.BEDROCK || EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -1)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 4: {
                blockPos = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen(blockPos.getX(), blockPos.getY(), blockPos.getZ() + 2) && Math.sqrt(EntityUtil.mc.player.getDistanceSq((double)blockPos.getX(), (double)blockPos.getY(), (double)(blockPos.getZ() + 2))) > 3.0) {
                    return false;
                }
                Block block6 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, 2)).getBlock();
                if (block6 != Blocks.AIR && block6 != Blocks.FIRE || (block2 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 2)).getBlock()) != Blocks.AIR && block2 != Blocks.FIRE || (block = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, -1, 2)).getBlock()) != Blocks.OBSIDIAN && block != Blocks.BEDROCK || EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 1)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 5: {
                blockPos = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen(blockPos.getX() - 1, blockPos.getY(), blockPos.getZ()) && Math.sqrt(EntityUtil.mc.player.getDistanceSq((double)(blockPos.getX() - 1), (double)blockPos.getY(), (double)blockPos.getZ())) > 3.0) {
                    return false;
                }
                Block block7 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 1, 0)).getBlock();
                if (block7 != Blocks.AIR && block7 != Blocks.FIRE || EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 6: {
                blockPos = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen(blockPos.getX() + 1, blockPos.getY(), blockPos.getZ()) && Math.sqrt(EntityUtil.mc.player.getDistanceSq((double)(blockPos.getX() + 1), (double)blockPos.getY(), (double)blockPos.getZ())) > 3.0) {
                    return false;
                }
                Block block8 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(1, 1, 0)).getBlock();
                if (block8 != Blocks.AIR && block8 != Blocks.FIRE || EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(1, 0, 0)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 7: {
                blockPos = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen(blockPos.getX(), blockPos.getY(), blockPos.getZ() - 1) && Math.sqrt(EntityUtil.mc.player.getDistanceSq((double)blockPos.getX(), (double)blockPos.getY(), (double)(blockPos.getZ() - 1))) > 3.0) {
                    return false;
                }
                Block block9 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, -1)).getBlock();
                if (block9 != Blocks.AIR && block9 != Blocks.FIRE || EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -1)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 8: {
                blockPos = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen(blockPos.getX(), blockPos.getY(), blockPos.getZ() + 1) && Math.sqrt(EntityUtil.mc.player.getDistanceSq((double)blockPos.getX(), (double)blockPos.getY(), (double)(blockPos.getZ() + 1))) > 3.0) {
                    return false;
                }
                Block block10 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, 1)).getBlock();
                if (block10 != Blocks.AIR && block10 != Blocks.FIRE || EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 1)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
        }
        switch (n2) {
            case 1: {
                blockPos = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-2, 1, 0)).getBlock();
                if (blockPos != Blocks.AIR && blockPos != Blocks.FIRE) {
                    return false;
                }
                block2 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-2, 0, 0)).getBlock();
                if (block2 != Blocks.AIR && block2 != Blocks.FIRE) {
                    return false;
                }
                block = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-2, -1, 0)).getBlock();
                if (block != Blocks.OBSIDIAN && block != Blocks.BEDROCK) {
                    return false;
                }
                return EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 0, 0)).getBlock() != Blocks.BEDROCK;
            }
            case 2: {
                blockPos = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(2, 1, 0)).getBlock();
                if (blockPos != Blocks.AIR && blockPos != Blocks.FIRE) {
                    return false;
                }
                block2 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(2, 0, 0)).getBlock();
                if (block2 != Blocks.AIR && block2 != Blocks.FIRE) {
                    return false;
                }
                block = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(2, -1, 0)).getBlock();
                if (block != Blocks.OBSIDIAN && block != Blocks.BEDROCK) {
                    return false;
                }
                return EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(1, 0, 0)).getBlock() != Blocks.BEDROCK;
            }
            case 3: {
                blockPos = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, -2)).getBlock();
                if (blockPos != Blocks.AIR && blockPos != Blocks.FIRE) {
                    return false;
                }
                block2 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -2)).getBlock();
                if (block2 != Blocks.AIR && block2 != Blocks.FIRE) {
                    return false;
                }
                block = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, -1, -2)).getBlock();
                if (block != Blocks.OBSIDIAN && block != Blocks.BEDROCK) {
                    return false;
                }
                return EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -1)).getBlock() != Blocks.BEDROCK;
            }
            case 4: {
                blockPos = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, 2)).getBlock();
                if (blockPos != Blocks.AIR && blockPos != Blocks.FIRE) {
                    return false;
                }
                block2 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 2)).getBlock();
                if (block2 != Blocks.AIR && block2 != Blocks.FIRE) {
                    return false;
                }
                block = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, -1, 2)).getBlock();
                if (block != Blocks.OBSIDIAN && block != Blocks.BEDROCK) {
                    return false;
                }
                return EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 1)).getBlock() != Blocks.BEDROCK;
            }
            case 5: {
                blockPos = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 1, 0)).getBlock();
                if (blockPos != Blocks.AIR && blockPos != Blocks.FIRE) {
                    return false;
                }
                return EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 0, 0)).getBlock() != Blocks.BEDROCK;
            }
            case 6: {
                blockPos = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(1, 1, 0)).getBlock();
                if (blockPos != Blocks.AIR && blockPos != Blocks.FIRE) {
                    return false;
                }
                return EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(1, 0, 0)).getBlock() != Blocks.BEDROCK;
            }
            case 7: {
                blockPos = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, -1)).getBlock();
                if (blockPos != Blocks.AIR && blockPos != Blocks.FIRE) {
                    return false;
                }
                return EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -1)).getBlock() != Blocks.BEDROCK;
            }
            case 8: {
                blockPos = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, 1)).getBlock();
                if (blockPos != Blocks.AIR && blockPos != Blocks.FIRE) {
                    return false;
                }
                return EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 1)).getBlock() != Blocks.BEDROCK;
            }
        }
        return false;
    }

    public static boolean isTrappedExtended(int n, EntityPlayer entityPlayer, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6, boolean bl7, boolean bl8) {
        return EntityUtil.getUntrappedBlocksExtended(n, entityPlayer, bl, bl2, bl3, bl4, bl5, bl6, bl7, bl8).size() == 0;
    }

    public static boolean isTrapped(EntityPlayer entityPlayer, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6) {
        return EntityUtil.getUntrappedBlocks(entityPlayer, bl, bl2, bl3, bl4, bl5, bl6).size() == 0;
    }

    public static Vec3d[] getHeightOffsets(int n, int n2) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        for (int i = n; i <= n2; ++i) {
            arrayList.add(new Vec3d(0.0, (double)i, 0.0));
        }
        Vec3d[] arrvec3d = new Vec3d[arrayList.size()];
        return arrayList.toArray((T[])arrvec3d);
    }

    public static boolean isFriendlyMob(Entity entity) {
        return entity.isCreatureType(EnumCreatureType.CREATURE, false) && !EntityUtil.isNeutralMob(entity) || entity.isCreatureType(EnumCreatureType.AMBIENT, false) || entity instanceof EntityVillager || entity instanceof EntityIronGolem || EntityUtil.isNeutralMob(entity) && !EntityUtil.isMobAggressive(entity);
    }

    public static boolean rayTraceHitCheck(Entity entity, boolean bl) {
        return !bl || EntityUtil.mc.player.canEntityBeSeen(entity);
    }

    public static void OffhandAttack(Entity entity, boolean bl, boolean bl2) {
        if (bl) {
            EntityUtil.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        } else {
            EntityUtil.mc.playerController.attackEntity((EntityPlayer)EntityUtil.mc.player, entity);
        }
        if (bl2) {
            EntityUtil.mc.player.swingArm(EnumHand.OFF_HAND);
        }
    }

    public static boolean isPassive(Entity entity) {
        return (!(entity instanceof EntityWolf) || !((EntityWolf)entity).isAngry()) && (entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntitySquid || entity instanceof EntityIronGolem && ((EntityIronGolem)entity).getAITarget() == null);
    }

    private static float getMultipliedDamage(float f) {
        return f * (EntityUtil.mc.world.getDifficulty().getDifficultyId() == 0 ? 0.0f : (EntityUtil.mc.world.getDifficulty().getDifficultyId() == 2 ? 1.0f : (EntityUtil.mc.world.getDifficulty().getDifficultyId() == 1 ? 0.5f : 1.5f)));
    }

    public static boolean isInWater(Entity entity) {
        if (entity == null) {
            return false;
        }
        double d = entity.posY + 0.01;
        for (int i = MathHelper.floor((double)entity.posX); i < MathHelper.ceil((double)entity.posX); ++i) {
            for (int j = MathHelper.floor((double)entity.posZ); j < MathHelper.ceil((double)entity.posZ); ++j) {
                BlockPos blockPos = new BlockPos(i, (int)d, j);
                if (!(EntityUtil.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid)) continue;
                return true;
            }
        }
        return false;
    }

    public static Vec3d interpolateEntity(Entity entity, float f) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f);
    }

    public static boolean isBorderingChunk(Entity entity, Double d, Double d2) {
        return Util.mc.world.getChunkFromChunkCoords((int)(entity.posX + d) / 16, (int)(entity.posZ + d2) / 16) instanceof EmptyChunk;
    }

    public static boolean canEntityFeetBeSeen(Entity entity) {
        return EntityUtil.mc.world.rayTraceBlocks(new Vec3d(EntityUtil.mc.player.posX, EntityUtil.mc.player.posX + (double)EntityUtil.mc.player.getEyeHeight(), EntityUtil.mc.player.posZ), new Vec3d(entity.posX, entity.posY, entity.posZ), false, true, false) == null;
    }

    public static List<EntityPlayer> getEnemyPlayers() {
        return EntityUtil.getPlayers2().stream().filter(entityPlayer -> !AliceMain.friendManager.isFriend((EntityPlayer)entityPlayer)).collect(Collectors.toList());
    }

    public static boolean isEntityMoving(Entity entity) {
        if (entity == null) {
            return false;
        }
        if (entity instanceof EntityPlayer) {
            return EntityUtil.mc.gameSettings.keyBindForward.isKeyDown() || EntityUtil.mc.gameSettings.keyBindBack.isKeyDown() || EntityUtil.mc.gameSettings.keyBindLeft.isKeyDown() || EntityUtil.mc.gameSettings.keyBindRight.isKeyDown();
        }
        return entity.motionX != 0.0 || entity.motionY != 0.0 || entity.motionZ != 0.0;
    }

    public static double getDefaultSpeed() {
        int n;
        double d = 0.2873;
        if (Util.mc.player.isPotionActive(MobEffects.SPEED)) {
            n = Objects.requireNonNull(Util.mc.player.getActivePotionEffect(MobEffects.SPEED)).getAmplifier();
            d *= 1.0 + 0.2 * (double)(n + 1);
        }
        if (Util.mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
            n = Objects.requireNonNull(Util.mc.player.getActivePotionEffect(MobEffects.SPEED)).getAmplifier();
            d /= 1.0 + 0.2 * (double)(n + 1);
        }
        return d;
    }

    public static Vec3d[] getTrapOffsets(boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6) {
        List<Vec3d> list = EntityUtil.getTrapOffsetsList(bl, bl2, bl3, bl4, bl5, bl6);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])arrvec3d);
    }

    public static boolean isMoving(EntityLivingBase entityLivingBase) {
        return (double)entityLivingBase.field_191988_bg != 0.0 || (double)entityLivingBase.moveStrafing != 0.0;
    }

    public static boolean isBlockValid(BlockPos blockPos) {
        return EntityUtil.isBedrockHole(blockPos) || EntityUtil.isObbyHole(blockPos) || EntityUtil.isBothHole(blockPos);
    }

    public static double getMaxSpeed() {
        double d = 0.2873;
        if (EntityUtil.mc.player.isPotionActive(Objects.requireNonNull(Potion.getPotionById((int)1)))) {
            d *= 1.0 + 0.2 * (double)(Objects.requireNonNull(EntityUtil.mc.player.getActivePotionEffect(Objects.requireNonNull(Potion.getPotionById((int)1)))).getAmplifier() + 1);
        }
        return d;
    }

    public static boolean isHostileMob(Entity entity) {
        return entity.isCreatureType(EnumCreatureType.MONSTER, false) && !EntityUtil.isNeutralMob(entity);
    }

    public static EntityPlayer getClosestEnemy(double d) {
        EntityPlayer entityPlayer = null;
        for (EntityPlayer entityPlayer2 : EntityUtil.mc.world.playerEntities) {
            if (EntityUtil.isntValid((Entity)entityPlayer2, d)) continue;
            if (entityPlayer == null) {
                entityPlayer = entityPlayer2;
                continue;
            }
            if (EntityUtil.mc.player.getDistanceSqToEntity((Entity)entityPlayer2) >= EntityUtil.mc.player.getDistanceSqToEntity((Entity)entityPlayer)) continue;
            entityPlayer = entityPlayer2;
        }
        return entityPlayer;
    }

    public static boolean movementKey() {
        return EntityUtil.mc.player.movementInput.forwardKeyDown || EntityUtil.mc.player.movementInput.rightKeyDown || EntityUtil.mc.player.movementInput.leftKeyDown || EntityUtil.mc.player.movementInput.backKeyDown || EntityUtil.mc.player.movementInput.jump || EntityUtil.mc.player.movementInput.sneak;
    }

    public static List<Vec3d> getUntrappedBlocks(EntityPlayer entityPlayer, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        if (!bl2 && EntityUtil.getUnsafeBlocks((Entity)entityPlayer, 2, false, bl6).size() == 4) {
            arrayList.addAll(EntityUtil.getUnsafeBlocks((Entity)entityPlayer, 2, false, bl6));
        }
        for (int i = 0; i < EntityUtil.getTrapOffsets(bl, bl2, bl3, bl4, bl5, bl6).length; ++i) {
            Vec3d vec3d = EntityUtil.getTrapOffsets(bl, bl2, bl3, bl4, bl5, bl6)[i];
            BlockPos blockPos = new BlockPos(entityPlayer.getPositionVector()).add(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
            Block block = EntityUtil.mc.world.getBlockState(blockPos).getBlock();
            if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid) && !(block instanceof BlockTallGrass) && !(block instanceof BlockFire) && !(block instanceof BlockDeadBush) && !(block instanceof BlockSnow)) continue;
            arrayList.add(vec3d);
        }
        return arrayList;
    }

    public static boolean isInLiquid() {
        if (EntityUtil.mc.player.fallDistance >= 3.0f) {
            return false;
        }
        boolean bl = false;
        AxisAlignedBB axisAlignedBB = EntityUtil.mc.player.getRidingEntity() != null ? EntityUtil.mc.player.getRidingEntity().getEntityBoundingBox() : EntityUtil.mc.player.getEntityBoundingBox();
        int n = (int)axisAlignedBB.minY;
        for (int i = MathHelper.floor((double)axisAlignedBB.minX); i < MathHelper.floor((double)axisAlignedBB.maxX) + 1; ++i) {
            for (int j = MathHelper.floor((double)axisAlignedBB.minZ); j < MathHelper.floor((double)axisAlignedBB.maxZ) + 1; ++j) {
                Block block = EntityUtil.mc.world.getBlockState(new BlockPos(i, n, j)).getBlock();
                if (block instanceof BlockAir) continue;
                if (!(block instanceof BlockLiquid)) {
                    return false;
                }
                bl = true;
            }
        }
        return bl;
    }

    public static Vec3d[] getUnsafeBlockArrayFromVec3d(Vec3d vec3d, int n, boolean bl, boolean bl2) {
        List<Vec3d> list = EntityUtil.getUnsafeBlocksFromVec3d(vec3d, n, bl, bl2);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])arrvec3d);
    }

    public static boolean holdingWeapon(EntityPlayer entityPlayer) {
        return entityPlayer.getHeldItemMainhand().getItem() instanceof ItemSword || entityPlayer.getHeldItemMainhand().getItem() instanceof ItemAxe;
    }

    static {
        antiDropOffsetList = new Vec3d[]{new Vec3d(0.0, -2.0, 0.0)};
        platformOffsetList = new Vec3d[]{new Vec3d(0.0, -1.0, 0.0), new Vec3d(0.0, -1.0, -1.0), new Vec3d(0.0, -1.0, 1.0), new Vec3d(-1.0, -1.0, 0.0), new Vec3d(1.0, -1.0, 0.0)};
        legOffsetList = new Vec3d[]{new Vec3d(-1.0, 0.0, 0.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(0.0, 0.0, 1.0)};
        doubleLegOffsetList = new Vec3d[]{new Vec3d(-1.0, 0.0, 0.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-2.0, 0.0, 0.0), new Vec3d(2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -2.0), new Vec3d(0.0, 0.0, 2.0)};
        OffsetList = new Vec3d[]{new Vec3d(1.0, 1.0, 0.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(0.0, 2.0, 0.0)};
        headpiece = new Vec3d[]{new Vec3d(0.0, 2.0, 0.0)};
        offsetsNoHead = new Vec3d[]{new Vec3d(1.0, 1.0, 0.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(0.0, 1.0, -1.0)};
        antiStepOffsetList = new Vec3d[]{new Vec3d(-1.0, 2.0, 0.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(0.0, 2.0, -1.0)};
        antiScaffoldOffsetList = new Vec3d[]{new Vec3d(0.0, 3.0, 0.0)};
    }

    public static boolean is32k(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        if (itemStack.getTagCompound() == null) {
            return false;
        }
        NBTTagList nBTTagList = (NBTTagList)itemStack.getTagCompound().getTag("ench");
        for (int i = 0; i < nBTTagList.tagCount(); ++i) {
            NBTTagCompound nBTTagCompound = nBTTagList.getCompoundTagAt(i);
            if (nBTTagCompound.getInteger("id") != 16) continue;
            int n = nBTTagCompound.getInteger("lvl");
            if (n < 42) break;
            return true;
        }
        return false;
    }

    public static double getRelativeZ(float f) {
        return MathHelper.cos((float)(f * ((float)Math.PI / 180)));
    }

    public static void setMotion(double d) {
        if (EntityUtil.mc.player != null) {
            if (EntityUtil.mc.player.isRiding()) {
                EntityUtil.mc.player.ridingEntity.motionX = d;
                EntityUtil.mc.player.ridingEntity.motionZ = d;
            } else {
                EntityUtil.mc.player.motionX = d;
                EntityUtil.mc.player.motionZ = d;
            }
        }
    }

    public static boolean isntValid(Entity entity, double d) {
        return entity == null || EntityUtil.isDead(entity) || entity.equals((Object)EntityUtil.mc.player) || entity instanceof EntityPlayer && AliceMain.friendManager.isFriend(entity.getName()) || EntityUtil.mc.player.getDistanceSqToEntity(entity) > MathUtil.square(d);
    }

    public static void resetTimer() {
        Minecraft.getMinecraft().timer.field_194149_e = 50.0f;
    }

    public static Vec3d getCenter(double d, double d2, double d3) {
        double d4 = Math.floor(d) + 0.5;
        double d5 = Math.floor(d2);
        double d6 = Math.floor(d3) + 0.5;
        return new Vec3d(d4, d5, d6);
    }

    public static boolean isVehicle(Entity entity) {
        return entity instanceof EntityBoat || entity instanceof EntityMinecart;
    }

    public static boolean isPlayer(Entity entity) {
        return entity instanceof EntityPlayer;
    }

    public static float calculateEntityDamage(EntityEnderCrystal entityEnderCrystal, EntityPlayer entityPlayer) {
        return EntityUtil.calculatePosDamage(entityEnderCrystal.posX, entityEnderCrystal.posY, entityEnderCrystal.posZ, (Entity)entityPlayer);
    }

    public static BlockPos getRoundedBlockPos(Entity entity) {
        return new BlockPos(MathUtil.roundVec(entity.getPositionVector(), 0));
    }

    public static double getDst(Vec3d vec3d) {
        return EntityUtil.mc.player.getPositionVector().distanceTo(vec3d);
    }

    public static Vec3d getInterpolatedAmount(Entity entity, double d, double d2, double d3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * d, (entity.posY - entity.lastTickPosY) * d2, (entity.posZ - entity.lastTickPosZ) * d3);
    }

    public static Block getBlockOnPos(BlockPos blockPos) {
        return EntityUtil.mc.world.getBlockState(blockPos).getBlock();
    }

    public static boolean isMoving() {
        return (double)EntityUtil.mc.player.field_191988_bg != 0.0 || (double)EntityUtil.mc.player.moveStrafing != 0.0;
    }

    public static Vec3d getInterpolatedAmount(Entity entity, Vec3d vec3d) {
        return EntityUtil.getInterpolatedAmount(entity, vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
    }

    public static float[] getLegitRotations(Vec3d vec3d) {
        Vec3d vec3d2 = RotationUtil.getEyesPos();
        double d = vec3d.xCoord - vec3d2.xCoord;
        double d2 = vec3d.yCoord - vec3d2.yCoord;
        double d3 = vec3d.zCoord - vec3d2.zCoord;
        double d4 = Math.sqrt(d * d + d3 * d3);
        float f = (float)Math.toDegrees(Math.atan2(d3, d)) - 90.0f;
        float f2 = (float)(-Math.toDegrees(Math.atan2(d2, d4)));
        return new float[]{Util.mc.player.rotationYaw + MathHelper.wrapDegrees((float)(f - Util.mc.player.rotationYaw)), Util.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(f2 - Util.mc.player.rotationPitch))};
    }

    public static void packetJump(boolean bl) {
        mc.getConnection().sendPacket((Packet)new CPacketPlayer.Position(EntityUtil.mc.player.posX, EntityUtil.mc.player.posY + 0.4199999, EntityUtil.mc.player.posZ, bl));
        mc.getConnection().sendPacket((Packet)new CPacketPlayer.Position(EntityUtil.mc.player.posX, EntityUtil.mc.player.posY + 0.7531999, EntityUtil.mc.player.posZ, bl));
        mc.getConnection().sendPacket((Packet)new CPacketPlayer.Position(EntityUtil.mc.player.posX, EntityUtil.mc.player.posY + 1.0013359, EntityUtil.mc.player.posZ, bl));
        mc.getConnection().sendPacket((Packet)new CPacketPlayer.Position(EntityUtil.mc.player.posX, EntityUtil.mc.player.posY + 1.1661092, EntityUtil.mc.player.posZ, bl));
    }

    public static boolean isDead(Entity entity) {
        return !EntityUtil.isAlive(entity);
    }

    public static Map<String, Integer> getTextRadarPlayers() {
        Map<String, Integer> map2 = new HashMap<String, Integer>();
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        DecimalFormat decimalFormat2 = new DecimalFormat("#.#");
        decimalFormat2.setRoundingMode(RoundingMode.CEILING);
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        for (EntityPlayer entityPlayer : EntityUtil.mc.world.playerEntities) {
            if (entityPlayer.isInvisible() && !Global.getInstance().tRadarInv.getValue().booleanValue() || entityPlayer.getName().equals(EntityUtil.mc.player.getName())) continue;
            int n = (int)EntityUtil.getHealth((Entity)entityPlayer);
            String string = decimalFormat.format(n);
            stringBuilder.append("\u00a7");
            if (n >= 20) {
                stringBuilder.append("a");
            } else if (n >= 10) {
                stringBuilder.append("e");
            } else if (n >= 5) {
                stringBuilder.append("6");
            } else {
                stringBuilder.append("c");
            }
            stringBuilder.append(string);
            int n2 = (int)EntityUtil.mc.player.getDistanceToEntity((Entity)entityPlayer);
            String string2 = decimalFormat2.format(n2);
            stringBuilder2.append("\u00a7");
            if (n2 >= 25) {
                stringBuilder2.append("a");
            } else if (n2 > 10) {
                stringBuilder2.append("6");
            } else {
                stringBuilder2.append("c");
            }
            stringBuilder2.append(string2);
            map2.put(String.valueOf(new StringBuilder().append((Object)stringBuilder).append(" ").append(AliceMain.friendManager.isFriend(entityPlayer) ? "\u00a7b" : "\u00a7r").append(entityPlayer.getName()).append(" ").append((Object)stringBuilder2).append(" \u00a7f").append(AliceMain.totemPopManager.getTotemPopString(entityPlayer)).append(AliceMain.potionManager.getTextRadarPotion(entityPlayer))), (int)EntityUtil.mc.player.getDistanceToEntity((Entity)entityPlayer));
            stringBuilder.setLength(0);
            stringBuilder2.setLength(0);
        }
        if (!map2.isEmpty()) {
            map2 = MathUtil.sortByValue(map2, false);
        }
        return map2;
    }

    public static boolean isFakePlayer(EntityPlayer entityPlayer) {
        Freecam freecam = Freecam.getInstance();
        FakeLag fakeLag = FakeLag.getInstance();
        int n = entityPlayer.getEntityId();
        if (freecam.isOn() && n == 69420) {
            return true;
        }
        return fakeLag.isOn() && n == 6942069;
    }

    public static boolean isProjectile(Entity entity) {
        return entity instanceof EntityShulkerBullet || entity instanceof EntityFireball;
    }

    public static Vec3d getInterpolatedRenderPos(Vec3d vec3d) {
        return new Vec3d(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord).subtract(EntityUtil.mc.getRenderManager().renderPosX, EntityUtil.mc.getRenderManager().renderPosY, EntityUtil.mc.getRenderManager().renderPosZ);
    }

    public static boolean startSneaking() {
        if (EntityUtil.mc.player != null) {
            if (EntityUtil.mc.player.isSneaking()) {
                mc.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)EntityUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                mc.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)EntityUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            } else {
                mc.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)EntityUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            }
        }
        return false;
    }

    public static double[] directionSpeed(double d) {
        float f = EntityUtil.mc.player.movementInput.field_192832_b;
        float f2 = EntityUtil.mc.player.movementInput.moveStrafe;
        float f3 = EntityUtil.mc.player.prevRotationYaw + (EntityUtil.mc.player.rotationYaw - EntityUtil.mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
        if (f != 0.0f) {
            if (f2 > 0.0f) {
                f3 += (float)(f > 0.0f ? -45 : 45);
            } else if (f2 < 0.0f) {
                f3 += (float)(f > 0.0f ? 45 : -45);
            }
            f2 = 0.0f;
            if (f > 0.0f) {
                f = 1.0f;
            } else if (f < 0.0f) {
                f = -1.0f;
            }
        }
        double d2 = Math.sin(Math.toRadians(f3 + 90.0f));
        double d3 = Math.cos(Math.toRadians(f3 + 90.0f));
        double d4 = (double)f * d * d3 + (double)f2 * d * d2;
        double d5 = (double)f * d * d2 - (double)f2 * d * d3;
        return new double[]{d4, d5};
    }

    public static boolean isAboveLiquid(Entity entity) {
        if (entity == null) {
            return false;
        }
        double d = entity.posY + 0.01;
        for (int i = MathHelper.floor((double)entity.posX); i < MathHelper.ceil((double)entity.posX); ++i) {
            for (int j = MathHelper.floor((double)entity.posZ); j < MathHelper.ceil((double)entity.posZ); ++j) {
                if (!(EntityUtil.mc.world.getBlockState(new BlockPos(i, (int)d, j)).getBlock() instanceof BlockLiquid)) continue;
                return true;
            }
        }
        return false;
    }

    public static void mutliplyEntitySpeed(Entity entity, double d) {
        if (entity != null) {
            entity.motionX *= d;
            entity.motionZ *= d;
        }
    }

    public static Color getColor(Entity entity, int n, int n2, int n3, int n4, boolean bl) {
        Color color = new Color((float)n / 255.0f, (float)n2 / 255.0f, (float)n3 / 255.0f, (float)n4 / 255.0f);
        if (entity instanceof EntityPlayer) {
            if (bl && AliceMain.friendManager.isFriend((EntityPlayer)entity)) {
                color = new Color(0.33333334f, 1.0f, 1.0f, (float)n4 / 255.0f);
            }
            Killaura killaura = AliceMain.moduleManager.getModuleByClass(Killaura.class);
            if (Killaura.target != null && Killaura.target.equals((Object)entity)) {
                color = new Color(1.0f, 0.0f, 0.0f, (float)n4 / 255.0f);
            }
        }
        return color;
    }

    public static boolean isInHole(Entity entity) {
        return EntityUtil.isBlockValid(new BlockPos(entity.posX, entity.posY, entity.posZ));
    }
}

