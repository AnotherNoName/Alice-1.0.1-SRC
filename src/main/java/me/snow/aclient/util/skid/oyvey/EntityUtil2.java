//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockDeadBush
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockSnow
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.entity.EntityPlayerSP
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
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.MovementInput
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 */
package me.snow.aclient.util.skid.oyvey;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import me.snow.aclient.AliceMain;
import me.snow.aclient.mixin.mixins.accessors.IEntityLivingBase;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.PairUtil;
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
import net.minecraft.client.entity.EntityPlayerSP;
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
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumHand;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class EntityUtil2
implements Util {
    public static final /* synthetic */ Vec3d[] platformOffsetList;
    private static final /* synthetic */ BlockPos[] offsets;
    public static final /* synthetic */ Vec3d[] antiStepOffsetList;
    public static final /* synthetic */ Vec3d[] legOffsetList;
    public static final /* synthetic */ Vec3d[] antiScaffoldOffsetList;
    public static final /* synthetic */ Vec3d[] OffsetList;
    public static final /* synthetic */ Vec3d[] doubleLegOffsetList;
    public static final /* synthetic */ Vec3d[] offsetsNoHead;
    public static final /* synthetic */ Vec3d[] headpiece;
    public static final /* synthetic */ Vec3d[] antiDropOffsetList;

    public static EntityLivingBase getTarget(boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, double d, int n) {
        EntityLivingBase entityLivingBase = null;
        if (n == 0) {
            entityLivingBase = EntityUtil2.mc.world.loadedEntityList.stream().filter(entity -> EntityUtil2.isValid(entity, bl, bl2, bl3, bl4, bl5, d)).min(Comparator.comparing(entity -> EntityUtil2.mc.player.getPositionVector().squareDistanceTo(entity.getPositionVector()))).orElse(null);
        } else if (n == 1) {
            entityLivingBase = EntityUtil2.mc.world.loadedEntityList.stream().filter(entity -> EntityUtil2.isValid(entity, bl, bl2, bl3, bl4, bl5, d)).map(entity -> (EntityLivingBase)entity).min(Comparator.comparing(EntityLivingBase::getHealth)).orElse(null);
        } else if (n == 2) {
            entityLivingBase = EntityUtil2.mc.world.loadedEntityList.stream().filter(entity -> EntityUtil2.isValid(entity, bl, bl2, bl3, bl4, bl5, d)).map(entity -> (EntityLivingBase)entity).max(Comparator.comparing(EntityLivingBase::getHealth)).orElse(null);
        }
        return entityLivingBase;
    }

    public static List<BlockPos> getSphere(BlockPos blockPos, float f, int n, boolean bl, boolean bl2, int n2) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        int n3 = blockPos.getX();
        int n4 = blockPos.getY();
        int n5 = blockPos.getZ();
        int n6 = n3 - (int)f;
        while ((float)n6 <= (float)n3 + f) {
            int n7 = n5 - (int)f;
            while ((float)n7 <= (float)n5 + f) {
                int n8 = bl2 ? n4 - (int)f : n4;
                while (true) {
                    float f2;
                    float f3 = n8;
                    float f4 = f2 = bl2 ? (float)n4 + f : (float)(n4 + n);
                    if (!(f3 < f2)) break;
                    double d = (n3 - n6) * (n3 - n6) + (n5 - n7) * (n5 - n7) + (bl2 ? (n4 - n8) * (n4 - n8) : 0);
                    if (!(!(d < (double)(f * f)) || bl && d < (double)((f - 1.0f) * (f - 1.0f)))) {
                        BlockPos blockPos2 = new BlockPos(n6, n8 + n2, n7);
                        arrayList.add(blockPos2);
                    }
                    ++n8;
                }
                ++n7;
            }
            ++n6;
        }
        return arrayList;
    }

    public static double getDistance(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d - d4;
        double d8 = d2 - d5;
        double d9 = d3 - d6;
        return MathHelper.sqrt((double)(d7 * d7 + d8 * d8 + d9 * d9));
    }

    public static boolean isMobAggressive(Entity entity) {
        if (entity instanceof EntityPigZombie) {
            if (((EntityPigZombie)entity).isArmsRaised() || ((EntityPigZombie)entity).isAngry()) {
                return true;
            }
        } else {
            if (entity instanceof EntityWolf) {
                return ((EntityWolf)entity).isAngry() && !EntityUtil2.mc.player.equals((Object)((EntityWolf)entity).getOwner());
            }
            if (entity instanceof EntityEnderman) {
                return ((EntityEnderman)entity).isScreaming();
            }
        }
        return EntityUtil2.isHostileMob(entity);
    }

    public static Vec3d getInterpolatedAmount(Entity entity, Vec3d vec3d) {
        return EntityUtil2.getInterpolatedAmount(entity, vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
    }

    public static List<Vec3d> getUnsafeBlocks(Entity entity, int n, boolean bl) {
        return EntityUtil2.getUnsafeBlocksFromVec3d(entity.getPositionVector(), n, bl);
    }

    public static void attackEntity(Entity entity, boolean bl, boolean bl2) {
        if (bl) {
            EntityUtil2.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        } else {
            EntityUtil2.mc.playerController.attackEntity((EntityPlayer)EntityUtil2.mc.player, entity);
        }
        if (bl2) {
            EntityUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

    public static boolean isInWater(Entity entity) {
        if (entity == null) {
            return false;
        }
        double d = entity.posY + 0.01;
        for (int i = MathHelper.floor((double)entity.posX); i < MathHelper.ceil((double)entity.posX); ++i) {
            for (int j = MathHelper.floor((double)entity.posZ); j < MathHelper.ceil((double)entity.posZ); ++j) {
                BlockPos blockPos = new BlockPos(i, (int)d, j);
                if (!(EntityUtil2.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean isHostileMob(Entity entity) {
        return entity.isCreatureType(EnumCreatureType.MONSTER, false) && !EntityUtil2.isNeutralMob(entity);
    }

    public static double getDistPlayerToBlock(Entity entity, BlockPos blockPos) {
        return EntityUtil2.getDistance(entity.posX, entity.posY, entity.posZ, blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }

    public static boolean isCrystalAtFeet(EntityEnderCrystal entityEnderCrystal, double d) {
        for (EntityPlayer entityPlayer : EntityUtil2.mc.world.playerEntities) {
            if (EntityUtil2.mc.player.getDistanceSqToEntity((Entity)entityPlayer) > d * d || AliceMain.friendManager.isFriend(entityPlayer)) continue;
            for (Vec3d vec3d : doubleLegOffsetList) {
                if (new BlockPos(entityPlayer.getPositionVector()).add(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord) != entityEnderCrystal.getPosition()) continue;
                return true;
            }
        }
        return false;
    }

    public static Block getBlockOnPos(BlockPos blockPos) {
        return EntityUtil2.mc.world.getBlockState(blockPos).getBlock();
    }

    public static boolean checkCollide() {
        if (EntityUtil2.mc.player.isSneaking()) {
            return false;
        }
        if (EntityUtil2.mc.player.getRidingEntity() != null && EntityUtil2.mc.player.getRidingEntity().fallDistance >= 3.0f) {
            return false;
        }
        return EntityUtil2.mc.player.fallDistance < 3.0f;
    }

    public static Vec3d[] getUnsafeBlockArrayFromVec3d(Vec3d vec3d, int n, boolean bl, boolean bl2) {
        List<Vec3d> list = EntityUtil2.getUnsafeBlocksFromVec3d(vec3d, n, bl, bl2);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])arrvec3d);
    }

    public static List<Vec3d> getUnsafeBlocksFromVec3d(Vec3d vec3d, int n, boolean bl) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        for (Vec3d vec3d2 : EntityUtil2.getOffsets(n, bl)) {
            BlockPos blockPos = new BlockPos(vec3d).add(vec3d2.xCoord, vec3d2.yCoord, vec3d2.zCoord);
            Block block = EntityUtil2.mc.world.getBlockState(blockPos).getBlock();
            if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid) && !(block instanceof BlockTallGrass) && !(block instanceof BlockFire) && !(block instanceof BlockDeadBush) && !(block instanceof BlockSnow)) continue;
            arrayList.add(vec3d2);
        }
        return arrayList;
    }

    public static boolean canEntityFeetBeSeen(Entity entity) {
        return EntityUtil2.mc.world.rayTraceBlocks(new Vec3d(EntityUtil2.mc.player.posX, EntityUtil2.mc.player.posX + (double)EntityUtil2.mc.player.getEyeHeight(), EntityUtil2.mc.player.posZ), new Vec3d(entity.posX, entity.posY, entity.posZ), false, true, false) == null;
    }

    public static List<Vec3d> getOffsetList(int n, boolean bl) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        arrayList.add(new Vec3d(-1.0, (double)n, 0.0));
        arrayList.add(new Vec3d(1.0, (double)n, 0.0));
        arrayList.add(new Vec3d(0.0, (double)n, -1.0));
        arrayList.add(new Vec3d(0.0, (double)n, 1.0));
        if (bl) {
            arrayList.add(new Vec3d(0.0, (double)(n - 1), 0.0));
        }
        return arrayList;
    }

    public static boolean isFriendlyMob(Entity entity) {
        return entity.isCreatureType(EnumCreatureType.CREATURE, false) && !EntityUtil2.isNeutralMob(entity) || entity.isCreatureType(EnumCreatureType.AMBIENT, false) || entity instanceof EntityVillager || entity instanceof EntityIronGolem || EntityUtil2.isNeutralMob(entity) && !EntityUtil2.isMobAggressive(entity);
    }

    public static boolean isTrappedExtended(int n, EntityPlayer entityPlayer, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6, boolean bl7, boolean bl8) {
        return EntityUtil2.getUntrappedBlocksExtended(n, entityPlayer, bl, bl2, bl3, bl4, bl5, bl6, bl7, bl8).size() == 0;
    }

    public static Vec3d getInterpolatedRenderPos(Vec3d vec3d) {
        return new Vec3d(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord).subtract(EntityUtil2.mc.getRenderManager().renderPosX, EntityUtil2.mc.getRenderManager().renderPosY, EntityUtil2.mc.getRenderManager().renderPosZ);
    }

    public static boolean onMovementInput() {
        return EntityUtil2.mc.player.movementInput.field_192832_b != 0.0f || EntityUtil2.mc.player.movementInput.moveStrafe != 0.0f;
    }

    public static boolean isAboveWater(Entity entity, boolean bl) {
        if (entity == null) {
            return false;
        }
        double d = entity.posY - (bl ? 0.03 : (EntityUtil2.isPlayer(entity) ? 0.2 : 0.5));
        for (int i = MathHelper.floor((double)entity.posX); i < MathHelper.ceil((double)entity.posX); ++i) {
            for (int j = MathHelper.floor((double)entity.posZ); j < MathHelper.ceil((double)entity.posZ); ++j) {
                BlockPos blockPos = new BlockPos(i, MathHelper.floor((double)d), j);
                if (!(EntityUtil2.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid)) continue;
                return true;
            }
        }
        return false;
    }

    public static void moveEntityStrafe(double d, Entity entity) {
        if (entity != null) {
            MovementInput movementInput = EntityUtil2.mc.player.movementInput;
            double d2 = movementInput.field_192832_b;
            double d3 = movementInput.moveStrafe;
            float f = EntityUtil2.mc.player.rotationYaw;
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

    public static boolean isAboveBlock(Entity entity, BlockPos blockPos) {
        return entity.posY >= (double)blockPos.getY();
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
        offsets = new BlockPos[]{new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0)};
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

    public static void OffhandAttack(Entity entity, boolean bl, boolean bl2) {
        if (bl) {
            EntityUtil2.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        } else {
            EntityUtil2.mc.playerController.attackEntity((EntityPlayer)EntityUtil2.mc.player, entity);
        }
        if (bl2) {
            EntityUtil2.mc.player.swingArm(EnumHand.OFF_HAND);
        }
    }

    public static double getBaseMovementSpeed() {
        if (EntityUtil2.mc.player.isSneaking()) {
            return 0.064755;
        }
        return EntityUtil2.mc.player.isSprinting() ? 0.2806 : 0.21585;
    }

    public static boolean isVehicle(Entity entity) {
        return entity instanceof EntityBoat || entity instanceof EntityMinecart;
    }

    public static Vec3d getInterpolatedPos(Entity entity, float f) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(EntityUtil2.getInterpolatedAmount(entity, f));
    }

    public static boolean isSafe(Entity entity, int n, boolean bl, boolean bl2) {
        return EntityUtil2.getUnsafeBlocks(entity, n, bl, bl2).size() == 0;
    }

    public static List<Vec3d> getUntrappedBlocks(EntityPlayer entityPlayer, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        if (!bl2 && EntityUtil2.getUnsafeBlocks((Entity)entityPlayer, 2, false).size() == 4) {
            arrayList.addAll(EntityUtil2.getUnsafeBlocks((Entity)entityPlayer, 2, false));
        }
        for (int i = 0; i < EntityUtil2.getTrapOffsets(bl, bl2, bl3, bl4, bl5).length; ++i) {
            Vec3d vec3d = EntityUtil2.getTrapOffsets(bl, bl2, bl3, bl4, bl5)[i];
            BlockPos blockPos = new BlockPos(entityPlayer.getPositionVector()).add(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
            Block block = EntityUtil2.mc.world.getBlockState(blockPos).getBlock();
            if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid) && !(block instanceof BlockTallGrass) && !(block instanceof BlockFire) && !(block instanceof BlockDeadBush) && !(block instanceof BlockSnow)) continue;
            arrayList.add(vec3d);
        }
        return arrayList;
    }

    public static boolean isLiving(Entity entity) {
        return entity instanceof EntityLivingBase;
    }

    public static boolean isNeutralMob(Entity entity) {
        return entity instanceof EntityPigZombie || entity instanceof EntityWolf || entity instanceof EntityEnderman;
    }

    public static Boolean posEqualsBlock(BlockPos blockPos, Block block) {
        return EntityUtil2.getBlockOnPos(blockPos).equals((Object)block);
    }

    public static boolean isInHole(Entity entity) {
        return EntityUtil2.isBlockValid(new BlockPos(entity.posX, entity.posY, entity.posZ));
    }

    public static Vec3d[] getUnsafeBlockArray(Entity entity, int n, boolean bl, boolean bl2) {
        List<Vec3d> list = EntityUtil2.getUnsafeBlocks(entity, n, bl, bl2);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])arrvec3d);
    }

    public static BlockPos getLocalPlayerPosFloored() {
        return new BlockPos(Math.floor(EntityUtil2.mc.player.posX), Math.floor(EntityUtil2.mc.player.posY), Math.floor(EntityUtil2.mc.player.posZ));
    }

    public static BlockPos getEntityPosFloored(Entity entity) {
        return new BlockPos(Math.floor(entity.posX), Math.floor(entity.posY), Math.floor(entity.posZ));
    }

    public static boolean isSafe(Entity entity, int n, boolean bl) {
        return EntityUtil2.getUnsafeBlocks(entity, n, bl).size() == 0;
    }

    public static boolean isTrapped(EntityPlayer entityPlayer, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5) {
        return EntityUtil2.getUntrappedBlocks(entityPlayer, bl, bl2, bl3, bl4, bl5).size() == 0;
    }

    public static double getMovementSpeed() {
        if (!EntityUtil2.mc.player.isPotionActive(MobEffects.SPEED)) {
            return EntityUtil2.getBaseMovementSpeed();
        }
        return EntityUtil2.getBaseMovementSpeed() * 1.0 + 0.06 * (double)(Objects.requireNonNull(EntityUtil2.mc.player.getActivePotionEffect(MobEffects.SPEED)).getAmplifier() + 1);
    }

    private static boolean isValid(Entity entity, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, double d) {
        if (entity.isDead) {
            return false;
        }
        if (entity instanceof EntityLivingBase && entity != EntityUtil2.mc.player && entity.getDistanceSqToEntity((Entity)EntityUtil2.mc.player) <= d * d) {
            if (entity instanceof EntityPlayer && bl) {
                if (!bl3) {
                    return !AliceMain.friendManager.isFriend((EntityPlayer)entity);
                }
                return true;
            }
            if (EntityUtil2.isHostileMob(entity) && bl4) {
                return true;
            }
            if (EntityUtil2.isNeutralMob(entity) && bl2) {
                return true;
            }
            return EntityUtil2.isPassive(entity) && bl5;
        }
        return false;
    }

    public static boolean isMoving(EntityPlayerSP entityPlayerSP) {
        return (double)EntityUtil2.mc.player.field_191988_bg != 0.0 || (double)EntityUtil2.mc.player.moveStrafing != 0.0;
    }

    public static Vec3d interpolateEntity(Entity entity, float f) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f);
    }

    public static boolean isDead(Entity entity) {
        return !EntityUtil2.isAlive(entity);
    }

    public static boolean isPlayerInHole() {
        BlockPos blockPos = EntityUtil2.getLocalPlayerPosFloored();
        IBlockState iBlockState = EntityUtil2.mc.world.getBlockState(blockPos);
        if (iBlockState.getBlock() != Blocks.AIR) {
            return false;
        }
        if (EntityUtil2.mc.world.getBlockState(blockPos.up()).getBlock() != Blocks.AIR) {
            return false;
        }
        if (EntityUtil2.mc.world.getBlockState(blockPos.down()).getBlock() == Blocks.AIR) {
            return false;
        }
        BlockPos[] arrblockPos = new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west()};
        int n = 0;
        for (BlockPos blockPos2 : arrblockPos) {
            IBlockState iBlockState2 = EntityUtil2.mc.world.getBlockState(blockPos2);
            if (iBlockState2.getBlock() == Blocks.AIR || !iBlockState2.isFullBlock()) continue;
            ++n;
        }
        return n >= 4;
    }

    public static Vec3d[] getOffsets(int n, boolean bl) {
        List<Vec3d> list = EntityUtil2.getOffsetList(n, bl);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])arrvec3d);
    }

    public static boolean isAboveWater(Entity entity) {
        return EntityUtil2.isAboveWater(entity, false);
    }

    public static Vec3d[] getHeightOffsets(int n, int n2) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        for (int i = n; i <= n2; ++i) {
            arrayList.add(new Vec3d(0.0, (double)i, 0.0));
        }
        Vec3d[] arrvec3d = new Vec3d[arrayList.size()];
        return arrayList.toArray((T[])arrvec3d);
    }

    public static List<Vec3d> getUnsafeBlocksFromVec3d(Vec3d vec3d, int n, boolean bl, boolean bl2) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        for (Vec3d vec3d2 : EntityUtil2.getOffsets(n, bl, bl2)) {
            BlockPos blockPos = new BlockPos(vec3d).add(vec3d2.xCoord, vec3d2.yCoord, vec3d2.zCoord);
            Block block = EntityUtil2.mc.world.getBlockState(blockPos).getBlock();
            if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid) && !(block instanceof BlockTallGrass) && !(block instanceof BlockFire) && !(block instanceof BlockDeadBush) && !(block instanceof BlockSnow)) continue;
            arrayList.add(vec3d2);
        }
        return arrayList;
    }

    public static boolean isPassive(Entity entity) {
        if (entity instanceof EntityWolf && ((EntityWolf)entity).isAngry()) {
            return false;
        }
        if (entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntitySquid) {
            return true;
        }
        return entity instanceof EntityIronGolem && ((EntityIronGolem)entity).getAITarget() == null;
    }

    public static boolean isObbyHole(BlockPos blockPos) {
        BlockPos[] arrblockPos;
        for (BlockPos blockPos2 : arrblockPos = new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()}) {
            IBlockState iBlockState = EntityUtil2.mc.world.getBlockState(blockPos2);
            if (iBlockState.getBlock() != Blocks.AIR && iBlockState.getBlock() == Blocks.OBSIDIAN) continue;
            return false;
        }
        return true;
    }

    public static Map<String, Integer> getTextRadarPlayers() {
        Map<String, Integer> map2 = new HashMap<String, Integer>();
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        DecimalFormat decimalFormat2 = new DecimalFormat("#.#");
        decimalFormat2.setRoundingMode(RoundingMode.CEILING);
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        for (EntityPlayer entityPlayer : EntityUtil2.mc.world.playerEntities) {
            if (entityPlayer.isInvisible() || entityPlayer.getName().equals(EntityUtil2.mc.player.getName())) continue;
            int n = (int)EntityUtil2.getHealth((Entity)entityPlayer);
            String string = decimalFormat.format(n);
            stringBuilder.append("\u00c2\u00a7");
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
            int n2 = (int)EntityUtil2.mc.player.getDistanceToEntity((Entity)entityPlayer);
            String string2 = decimalFormat2.format(n2);
            stringBuilder2.append("\u00c2\u00a7");
            if (n2 >= 25) {
                stringBuilder2.append("a");
            } else if (n2 > 10) {
                stringBuilder2.append("6");
            } else {
                stringBuilder2.append("c");
            }
            stringBuilder2.append(string2);
            map2.put(String.valueOf(new StringBuilder().append(String.valueOf(stringBuilder)).append(" ").append((Object)(AliceMain.friendManager.isFriend(entityPlayer) ? ChatFormatting.AQUA : ChatFormatting.RED)).append(entityPlayer.getName()).append(" ").append(String.valueOf(stringBuilder2)).append(" \u00c2\u00a7f0")), (int)EntityUtil2.mc.player.getDistanceToEntity((Entity)entityPlayer));
            stringBuilder.setLength(0);
            stringBuilder2.setLength(0);
        }
        if (!map2.isEmpty()) {
            map2 = MathUtil.sortByValue(map2, false);
        }
        return map2;
    }

    public static EntityPlayer getClosestEnemy(double d) {
        EntityPlayer entityPlayer = null;
        for (EntityPlayer entityPlayer2 : EntityUtil2.mc.world.playerEntities) {
            if (EntityUtil2.isntValid((Entity)entityPlayer2, d)) continue;
            if (entityPlayer == null) {
                entityPlayer = entityPlayer2;
                continue;
            }
            if (!(EntityUtil2.mc.player.getDistanceSqToEntity((Entity)entityPlayer2) < EntityUtil2.mc.player.getDistanceSqToEntity((Entity)entityPlayer))) continue;
            entityPlayer = entityPlayer2;
        }
        return entityPlayer;
    }

    public static Vec3d getInterpolatedAmount(Entity entity, double d, double d2, double d3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * d, (entity.posY - entity.lastTickPosY) * d2, (entity.posZ - entity.lastTickPosZ) * d3);
    }

    public static boolean getSurroundWeakness(Vec3d vec3d, int n, int n2) {
        BlockPos blockPos;
        Block block;
        BlockPos blockPos2;
        switch (n) {
            case 1: {
                blockPos2 = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen(blockPos2.getX() - 2, blockPos2.getY(), blockPos2.getZ()) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)(blockPos2.getX() - 2), (double)blockPos2.getY(), (double)blockPos2.getZ())) > 3.0) {
                    return false;
                }
                Block block2 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-2, 1, 0)).getBlock();
                if (block2 != Blocks.AIR && block2 != Blocks.FIRE || (block = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-2, 0, 0)).getBlock()) != Blocks.AIR && block != Blocks.FIRE || (blockPos = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-2, -1, 0)).getBlock()) != Blocks.OBSIDIAN && blockPos != Blocks.BEDROCK || EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 2: {
                blockPos2 = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen(blockPos2.getX() + 2, blockPos2.getY(), blockPos2.getZ()) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)(blockPos2.getX() + 2), (double)blockPos2.getY(), (double)blockPos2.getZ())) > 3.0) {
                    return false;
                }
                Block block3 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(2, 1, 0)).getBlock();
                if (block3 != Blocks.AIR && block3 != Blocks.FIRE || (block = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(2, 0, 0)).getBlock()) != Blocks.AIR && block != Blocks.FIRE || (blockPos = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(2, -1, 0)).getBlock()) != Blocks.OBSIDIAN && blockPos != Blocks.BEDROCK || EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(1, 0, 0)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 3: {
                blockPos2 = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ() - 2) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)blockPos2.getX(), (double)blockPos2.getY(), (double)(blockPos2.getZ() - 2))) > 3.0) {
                    return false;
                }
                Block block4 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, -2)).getBlock();
                if (block4 != Blocks.AIR && block4 != Blocks.FIRE || (block = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -2)).getBlock()) != Blocks.AIR && block != Blocks.FIRE || (blockPos = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, -1, -2)).getBlock()) != Blocks.OBSIDIAN && blockPos != Blocks.BEDROCK || EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -1)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 4: {
                blockPos2 = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ() + 2) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)blockPos2.getX(), (double)blockPos2.getY(), (double)(blockPos2.getZ() + 2))) > 3.0) {
                    return false;
                }
                Block block5 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, 2)).getBlock();
                if (block5 != Blocks.AIR && block5 != Blocks.FIRE || (block = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 2)).getBlock()) != Blocks.AIR && block != Blocks.FIRE || (blockPos = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, -1, 2)).getBlock()) != Blocks.OBSIDIAN && blockPos != Blocks.BEDROCK || EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 1)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 5: {
                blockPos = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen(blockPos.getX() - 1, blockPos.getY(), blockPos.getZ()) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)(blockPos.getX() - 1), (double)blockPos.getY(), (double)blockPos.getZ())) > 3.0) {
                    return false;
                }
                block = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 1, 0)).getBlock();
                if (block != Blocks.AIR && block != Blocks.FIRE || EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 6: {
                blockPos = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen(blockPos.getX() + 1, blockPos.getY(), blockPos.getZ()) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)(blockPos.getX() + 1), (double)blockPos.getY(), (double)blockPos.getZ())) > 3.0) {
                    return false;
                }
                block = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(1, 1, 0)).getBlock();
                if (block != Blocks.AIR && block != Blocks.FIRE || EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(1, 0, 0)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 7: {
                blockPos = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen(blockPos.getX(), blockPos.getY(), blockPos.getZ() - 1) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)blockPos.getX(), (double)blockPos.getY(), (double)(blockPos.getZ() - 1))) > 3.0) {
                    return false;
                }
                block = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, -1)).getBlock();
                if (block != Blocks.AIR && block != Blocks.FIRE || EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -1)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 8: {
                blockPos = new BlockPos(vec3d);
                if (!BlockUtil2.canBlockBeSeen(blockPos.getX(), blockPos.getY(), blockPos.getZ() + 1) && Math.sqrt(EntityUtil2.mc.player.getDistanceSq((double)blockPos.getX(), (double)blockPos.getY(), (double)(blockPos.getZ() + 1))) > 3.0) {
                    return false;
                }
                block = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, 1)).getBlock();
                if (block != Blocks.AIR && block != Blocks.FIRE || EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 1)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
        }
        switch (n2) {
            case 1: {
                blockPos2 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-2, 1, 0)).getBlock();
                if (blockPos2 != Blocks.AIR && blockPos2 != Blocks.FIRE || (block = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-2, 0, 0)).getBlock()) != Blocks.AIR && block != Blocks.FIRE || (blockPos = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-2, -1, 0)).getBlock()) != Blocks.OBSIDIAN && blockPos != Blocks.BEDROCK || EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 2: {
                blockPos2 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(2, 1, 0)).getBlock();
                if (blockPos2 != Blocks.AIR && blockPos2 != Blocks.FIRE || (block = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(2, 0, 0)).getBlock()) != Blocks.AIR && block != Blocks.FIRE || (blockPos = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(2, -1, 0)).getBlock()) != Blocks.OBSIDIAN && blockPos != Blocks.BEDROCK || EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(1, 0, 0)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 3: {
                blockPos2 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, -2)).getBlock();
                if (blockPos2 != Blocks.AIR && blockPos2 != Blocks.FIRE || (block = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -2)).getBlock()) != Blocks.AIR && block != Blocks.FIRE || (blockPos = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, -1, -2)).getBlock()) != Blocks.OBSIDIAN && blockPos != Blocks.BEDROCK || EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -1)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 4: {
                blockPos2 = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, 2)).getBlock();
                if (blockPos2 != Blocks.AIR && blockPos2 != Blocks.FIRE || (block = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 2)).getBlock()) != Blocks.AIR && block != Blocks.FIRE || (blockPos = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, -1, 2)).getBlock()) != Blocks.OBSIDIAN && blockPos != Blocks.BEDROCK || EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 1)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 5: {
                blockPos = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 1, 0)).getBlock();
                if (blockPos != Blocks.AIR && blockPos != Blocks.FIRE || EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 0, 0)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 6: {
                blockPos = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(1, 1, 0)).getBlock();
                if (blockPos != Blocks.AIR && blockPos != Blocks.FIRE || EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(1, 0, 0)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 7: {
                blockPos = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, -1)).getBlock();
                if (blockPos != Blocks.AIR && blockPos != Blocks.FIRE || EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -1)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
            case 8: {
                blockPos = EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, 1)).getBlock();
                if (blockPos != Blocks.AIR && blockPos != Blocks.FIRE || EntityUtil2.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 1)).getBlock() == Blocks.BEDROCK) break;
                return true;
            }
        }
        return false;
    }

    public static boolean isEntityMoving(Entity entity) {
        if (entity == null) {
            return false;
        }
        if (entity instanceof EntityPlayer) {
            return EntityUtil2.mc.gameSettings.keyBindForward.isKeyDown() || EntityUtil2.mc.gameSettings.keyBindBack.isKeyDown() || EntityUtil2.mc.gameSettings.keyBindLeft.isKeyDown() || EntityUtil2.mc.gameSettings.keyBindRight.isKeyDown();
        }
        return entity.motionX != 0.0 || entity.motionY != 0.0 || entity.motionZ != 0.0;
    }

    public static BlockPos getPlayerPos(EntityPlayer entityPlayer) {
        return new BlockPos(Math.floor(entityPlayer.posX), Math.floor(entityPlayer.posY), Math.floor(entityPlayer.posZ));
    }

    public static double[] calcLooking(double d, double d2, double d3, EntityPlayer entityPlayer) {
        double d4 = entityPlayer.posX - d;
        double d5 = entityPlayer.posY - d2;
        double d6 = entityPlayer.posZ - d3;
        double d7 = Math.sqrt(d4 * d4 + d5 * d5 + d6 * d6);
        double d8 = Math.asin(d5 /= d7);
        double d9 = Math.atan2(d6 /= d7, d4 /= d7);
        d8 = d8 * 180.0 / Math.PI;
        d9 = d9 * 180.0 / Math.PI;
        return new double[]{d9 += 90.0, d8};
    }

    public static void attackEntityCrystal(Entity entity) {
        EntityUtil2.mc.playerController.attackEntity((EntityPlayer)EntityUtil2.mc.player, entity);
    }

    public static float getHealth(Entity entity, boolean bl) {
        if (EntityUtil2.isLiving(entity)) {
            EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            return entityLivingBase.getHealth() + (bl ? entityLivingBase.getAbsorptionAmount() : 0.0f);
        }
        return 0.0f;
    }

    public static double getDirection() {
        float f;
        float f2 = 0.0f;
        EntityPlayerSP entityPlayerSP = EntityUtil2.mc.player;
        float f3 = entityPlayerSP.rotationYaw;
        if (entityPlayerSP.field_191988_bg < 0.0f) {
            f3 += 180.0f;
        }
        float f4 = entityPlayerSP.field_191988_bg < 0.0f ? -0.5f : (f = (f2 = entityPlayerSP.field_191988_bg > 0.0f ? 0.5f : 1.0f));
        if (entityPlayerSP.moveStrafing > 0.0f) {
            f3 -= 90.0f * f2;
        } else if (entityPlayerSP.moveStrafing < 0.0f) {
            f3 += 90.0f * f2;
        }
        return f3 * ((float)Math.PI / 180);
    }

    public static boolean isProjectile(Entity entity) {
        return entity instanceof EntityShulkerBullet || entity instanceof EntityFireball;
    }

    public static double getDst(Vec3d vec3d) {
        return EntityUtil2.mc.player.getPositionVector().distanceTo(vec3d);
    }

    public static boolean is32k(ItemStack itemStack) {
        return EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.SHARPNESS, (ItemStack)itemStack) >= 1000;
    }

    public static EntityPlayer getTarget2(float f) {
        EntityPlayer entityPlayer = null;
        int n = EntityUtil.mc.world.playerEntities.size();
        for (int i = 0; i < n; ++i) {
            EntityPlayer entityPlayer2 = (EntityPlayer)EntityUtil.mc.world.playerEntities.get(i);
            if (EntityUtil.isntValid((Entity)entityPlayer2, f)) continue;
            if (entityPlayer == null) {
                entityPlayer = entityPlayer2;
                continue;
            }
            if (!(EntityUtil.mc.player.getDistanceSqToEntity((Entity)entityPlayer2) < EntityUtil.mc.player.getDistanceSqToEntity((Entity)entityPlayer))) continue;
            entityPlayer = entityPlayer2;
        }
        return entityPlayer;
    }

    public static double getRange(Entity entity) {
        return EntityUtil2.mc.player.getPositionVector().addVector(0.0, (double)EntityUtil2.mc.player.eyeHeight, 0.0).distanceTo(entity.getPositionVector().addVector(0.0, (double)entity.height / 2.0, 0.0));
    }

    public static void mutliplyEntitySpeed(Entity entity, double d) {
        if (entity != null) {
            entity.motionX *= d;
            entity.motionZ *= d;
        }
    }

    public static List<EntityPlayer> getNearbyPlayers(double d) {
        if (EntityUtil2.mc.world.getLoadedEntityList().size() == 0) {
            return null;
        }
        List<EntityPlayer> list = EntityUtil2.mc.world.playerEntities.stream().filter(entityPlayer -> EntityUtil2.mc.player != entityPlayer).filter(entityPlayer -> (double)EntityUtil2.mc.player.getDistanceToEntity((Entity)entityPlayer) <= d).filter(entityPlayer -> !(EntityUtil2.getHealth((Entity)entityPlayer) < 0.0f)).collect(Collectors.toList());
        list.removeIf(entityPlayer -> AliceMain.friendManager.isFriend(entityPlayer.getName()));
        return list;
    }

    public static boolean isAlive(Entity entity) {
        return EntityUtil2.isLiving(entity) && !entity.isDead && ((EntityLivingBase)entity).getHealth() > 0.0f;
    }

    public static BlockPos getRoundedBlockPos(Entity entity) {
        return new BlockPos(MathUtil.roundVec(entity.getPositionVector(), 0));
    }

    public static Vec3d[] getUnsafeBlockArray(Entity entity, int n, boolean bl) {
        List<Vec3d> list = EntityUtil2.getUnsafeBlocks(entity, n, bl);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])arrvec3d);
    }

    public static Color getColor(Entity entity, int n, int n2, int n3, int n4, boolean bl) {
        Color color = new Color((float)n / 255.0f, (float)n2 / 255.0f, (float)n3 / 255.0f, (float)n4 / 255.0f);
        if (entity instanceof EntityPlayer && bl && AliceMain.friendManager.isFriend((EntityPlayer)entity)) {
            color = new Color(0.33333334f, 1.0f, 1.0f, (float)n4 / 255.0f);
        }
        return color;
    }

    public static boolean stopSneaking(boolean bl) {
        if (bl && EntityUtil2.mc.player != null) {
            EntityUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)EntityUtil2.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        return false;
    }

    public static int getEntityPing(EntityPlayer entityPlayer) {
        int n = 0;
        try {
            n = MathUtil.clamp(Objects.requireNonNull(mc.getConnection()).getPlayerInfo(entityPlayer.getUniqueID()).getResponseTime(), 1, 99999);
        }
        catch (NullPointerException nullPointerException) {
            // empty catch block
        }
        return n;
    }

    public static List<Vec3d> targets(Vec3d vec3d, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6) {
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
            List<Vec3d> list = EntityUtil2.getUnsafeBlocksFromVec3d(vec3d, 2, false);
            if (list.size() == 4) {
                block5: for (Vec3d vec3d2 : list) {
                    BlockPos blockPos = new BlockPos(vec3d).add(vec3d2.xCoord, vec3d2.yCoord, vec3d2.zCoord);
                    switch (BlockUtil.isPositionPlaceable(blockPos, bl6)) {
                        case 0: {
                            break;
                        }
                        case -1: 
                        case 1: 
                        case 2: {
                            continue block5;
                        }
                        case 3: {
                            arrayList.add(vec3d.add(vec3d2));
                        }
                    }
                    if (bl) {
                        Collections.addAll(arrayList, BlockUtil.convertVec3ds(vec3d, antiScaffoldOffsetList));
                    }
                    return arrayList;
                }
            }
        }
        if (bl) {
            Collections.addAll(arrayList, BlockUtil.convertVec3ds(vec3d, antiScaffoldOffsetList));
        }
        return arrayList;
    }

    public static boolean isBothHole(BlockPos blockPos) {
        BlockPos[] arrblockPos;
        for (BlockPos blockPos2 : arrblockPos = new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()}) {
            IBlockState iBlockState = EntityUtil2.mc.world.getBlockState(blockPos2);
            if (iBlockState.getBlock() != Blocks.AIR && (iBlockState.getBlock() == Blocks.BEDROCK || iBlockState.getBlock() == Blocks.OBSIDIAN)) continue;
            return false;
        }
        return true;
    }

    public void setSpeed(double d) {
        EntityPlayerSP entityPlayerSP = EntityUtil2.mc.player;
        entityPlayerSP.motionX = -(Math.sin(EntityUtil2.getDirection()) * d);
        entityPlayerSP.motionZ = Math.cos(EntityUtil2.getDirection()) * d;
    }

    public static Vec3d getInterpolatedAmount(Entity entity, float f) {
        return EntityUtil2.getInterpolatedAmount(entity, f, f, f);
    }

    public static BlockPos getPlayerPosWithEntity() {
        return new BlockPos(EntityUtil2.mc.player.getRidingEntity() != null ? EntityUtil2.mc.player.getRidingEntity().posX : EntityUtil2.mc.player.posX, EntityUtil2.mc.player.getRidingEntity() != null ? EntityUtil2.mc.player.getRidingEntity().posY : EntityUtil2.mc.player.posY, EntityUtil2.mc.player.getRidingEntity() != null ? EntityUtil2.mc.player.getRidingEntity().posZ : EntityUtil2.mc.player.posZ);
    }

    public static float getHealth(Entity entity) {
        if (EntityUtil2.isLiving(entity)) {
            EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            return entityLivingBase.getHealth() + entityLivingBase.getAbsorptionAmount();
        }
        return 0.0f;
    }

    public static Vec3d[] getOffsets(int n, boolean bl, boolean bl2) {
        List<Vec3d> list = EntityUtil2.getOffsetList(n, bl, bl2);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])arrvec3d);
    }

    public static double getMaxSpeed() {
        double d = 0.2873;
        if (EntityUtil2.mc.player.isPotionActive(Objects.requireNonNull(Potion.getPotionById((int)1)))) {
            d *= 1.0 + 0.2 * (double)(Objects.requireNonNull(EntityUtil2.mc.player.getActivePotionEffect(Objects.requireNonNull(Potion.getPotionById((int)1)))).getAmplifier() + 1);
        }
        return d;
    }

    public static BlockPos getPositionVectors(Entity entity, @Nullable BlockPos blockPos) {
        Vec3d vec3d = entity.getPositionVector();
        if (blockPos == null) {
            return new BlockPos(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
        }
        return new BlockPos(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord).add((Vec3i)blockPos);
    }

    public static Vec3d getInterpolatedRenderPos(Entity entity, float f) {
        return EntityUtil2.getInterpolatedPos(entity, f).subtract(EntityUtil2.mc.getRenderManager().renderPosX, EntityUtil2.mc.getRenderManager().renderPosY, EntityUtil2.mc.getRenderManager().renderPosZ);
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

    public static boolean holdingWeapon(EntityPlayer entityPlayer) {
        return entityPlayer.getHeldItemMainhand().getItem() instanceof ItemSword || entityPlayer.getHeldItemMainhand().getItem() instanceof ItemAxe;
    }

    public static boolean isBedrockHole(BlockPos blockPos) {
        BlockPos[] arrblockPos;
        for (BlockPos blockPos2 : arrblockPos = new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()}) {
            IBlockState iBlockState = EntityUtil2.mc.world.getBlockState(blockPos2);
            if (iBlockState.getBlock() != Blocks.AIR && iBlockState.getBlock() == Blocks.BEDROCK) continue;
            return false;
        }
        return true;
    }

    public static List<Vec3d> getUnsafeBlocks(Entity entity, int n, boolean bl, boolean bl2) {
        return EntityUtil2.getUnsafeBlocksFromVec3d(entity.getPositionVector().addVector(0.0, 0.125, 0.0), n, bl, bl2);
    }

    public static boolean isValid(Entity entity, double d) {
        return !EntityUtil2.isntValid(entity, d);
    }

    public static List<Vec3d> getTrapOffsetsList(boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>(EntityUtil2.getOffsetList(1, false));
        arrayList.add(new Vec3d(0.0, 2.0, 0.0));
        if (bl) {
            arrayList.add(new Vec3d(0.0, 3.0, 0.0));
        }
        if (bl2) {
            arrayList.addAll(EntityUtil2.getOffsetList(2, false));
        }
        if (bl3) {
            arrayList.addAll(EntityUtil2.getOffsetList(0, false));
        }
        if (bl4) {
            arrayList.addAll(EntityUtil2.getOffsetList(-1, false));
            arrayList.add(new Vec3d(0.0, -1.0, 0.0));
        }
        if (bl5) {
            arrayList.add(new Vec3d(0.0, -2.0, 0.0));
        }
        return arrayList;
    }

    public static int toMode(String string) {
        if (string.equalsIgnoreCase("Closest")) {
            return 0;
        }
        if (string.equalsIgnoreCase("Lowest Health")) {
            return 1;
        }
        if (string.equalsIgnoreCase("Highest Health")) {
            return 2;
        }
        throw new IllegalArgumentException(string);
    }

    public static ArrayList<BlockPos> getPos(double d, double d2, double d3, Entity entity) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        if (entity != null) {
            AxisAlignedBB axisAlignedBB = entity.ridingEntity != null ? entity.ridingEntity.getEntityBoundingBox().func_191195_a(0.0, 0.0, 0.0).offset(d, d2, d3) : entity.getEntityBoundingBox().func_191195_a(0.01, 1.0, 0.01).offset(d, d2, d3);
            int n = (int)axisAlignedBB.minY;
            int n2 = (int)Math.floor(axisAlignedBB.minX);
            while ((double)n2 < Math.floor(axisAlignedBB.maxX) + 1.0) {
                int n3 = (int)Math.floor(axisAlignedBB.minZ);
                while ((double)n3 < Math.floor(axisAlignedBB.maxZ) + 1.0) {
                    arrayList.add(new BlockPos(n2, n, n3));
                    ++n3;
                }
                ++n2;
            }
        }
        return arrayList;
    }

    public static Vec3d[] getTrapOffsets(boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5) {
        List<Vec3d> list = EntityUtil2.getTrapOffsetsList(bl, bl2, bl3, bl4, bl5);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])arrvec3d);
    }

    public static boolean isSafe(Entity entity) {
        return EntityUtil2.isSafe(entity, 0, false);
    }

    public static boolean isBlockValid(BlockPos blockPos) {
        return EntityUtil2.isBedrockHole(blockPos) || EntityUtil2.isObbyHole(blockPos) || EntityUtil2.isBothHole(blockPos);
    }

    public static Vec3d[] getUnsafeBlockArrayFromVec3d(Vec3d vec3d, int n, boolean bl) {
        List<Vec3d> list = EntityUtil2.getUnsafeBlocksFromVec3d(vec3d, n, bl);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])arrvec3d);
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

    public static boolean isntValid(Entity entity, double d) {
        return entity == null || EntityUtil2.isDead(entity) || entity.equals((Object)EntityUtil2.mc.player) || entity instanceof EntityPlayer && AliceMain.friendManager.isFriend(entity.getName()) || EntityUtil2.mc.player.getDistanceSqToEntity(entity) > MathUtil.square(d);
    }

    public static boolean rayTraceHitCheck(Entity entity, boolean bl) {
        return !bl || EntityUtil2.mc.player.canEntityBeSeen(entity);
    }

    public static boolean isTrapped(EntityPlayer entityPlayer, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6) {
        return EntityUtil2.getUntrappedBlocks(entityPlayer, bl, bl2, bl3, bl4, bl5).size() == 0;
    }

    public static EntityPlayer getTargetDouble(double d) {
        EntityPlayer entityPlayer = null;
        int n = EntityUtil2.mc.world.playerEntities.size();
        for (int i = 0; i < n; ++i) {
            EntityPlayer entityPlayer2 = (EntityPlayer)EntityUtil2.mc.world.playerEntities.get(i);
            if (EntityUtil2.isntValid((Entity)entityPlayer2, d)) continue;
            if (entityPlayer == null) {
                entityPlayer = entityPlayer2;
                continue;
            }
            if (!(EntityUtil2.mc.player.getDistanceSqToEntity((Entity)entityPlayer2) < EntityUtil2.mc.player.getDistanceSqToEntity((Entity)entityPlayer))) continue;
            entityPlayer = entityPlayer2;
        }
        return entityPlayer;
    }

    public static double[] forward(double d) {
        float f = EntityUtil2.mc.player.movementInput.field_192832_b;
        float f2 = EntityUtil2.mc.player.movementInput.moveStrafe;
        float f3 = EntityUtil2.mc.player.prevRotationYaw + (EntityUtil2.mc.player.rotationYaw - EntityUtil2.mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
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

    public static boolean isDrivenByPlayer(Entity entity) {
        return EntityUtil2.mc.player != null && entity != null && entity.equals((Object)EntityUtil2.mc.player.getRidingEntity());
    }

    public static List<Vec3d> getUntrappedBlocksExtended(int n, EntityPlayer entityPlayer, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, boolean bl6, boolean bl7, boolean bl8) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        if (n == 1) {
            arrayList.addAll(EntityUtil2.targets(entityPlayer.getPositionVector(), bl, bl2, bl3, bl4, bl5, bl6));
        } else {
            int n2 = 1;
            for (Vec3d vec3d : MathUtil.getBlockBlocks((Entity)entityPlayer)) {
                if (n2 > n) break;
                arrayList.addAll(EntityUtil2.targets(vec3d, !bl7, bl2, bl3, bl4, bl5, bl6));
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

    public static boolean isPlayer(Entity entity) {
        return entity instanceof EntityPlayer;
    }

    public static ArrayList<PairUtil<EntityPlayer, ArrayList<BlockPos>>> getCityablePlayers() {
        ArrayList<PairUtil<EntityPlayer, ArrayList<BlockPos>>> arrayList = new ArrayList<PairUtil<EntityPlayer, ArrayList<BlockPos>>>();
        for (EntityPlayer entityPlayer2 : Objects.requireNonNull(EntityUtil2.getNearbyPlayers(6.0)).stream().filter(entityPlayer -> !AliceMain.friendManager.isFriend((EntityPlayer)entityPlayer)).collect(Collectors.toList())) {
            ArrayList<BlockPos> arrayList2 = new ArrayList<BlockPos>();
            for (int i = 0; i < 4; ++i) {
                BlockPos blockPos = EntityUtil2.getPositionVectors((Entity)entityPlayer2, offsets[i]);
                if (EntityUtil2.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) continue;
                boolean bl = false;
                switch (i) {
                    case 0: {
                        bl = BlockUtil.canPlaceCrystal(blockPos.north(2), true, false);
                        break;
                    }
                    case 1: {
                        bl = BlockUtil.canPlaceCrystal(blockPos.east(2), true, false);
                        break;
                    }
                    case 2: {
                        bl = BlockUtil.canPlaceCrystal(blockPos.south(2), true, false);
                        break;
                    }
                    case 3: {
                        bl = BlockUtil.canPlaceCrystal(blockPos.west(2), true, false);
                    }
                }
                if (!bl) continue;
                arrayList2.add(blockPos);
            }
            if (arrayList2.isEmpty()) continue;
            arrayList.add(new PairUtil(entityPlayer2, arrayList2));
        }
        return arrayList;
    }
}

