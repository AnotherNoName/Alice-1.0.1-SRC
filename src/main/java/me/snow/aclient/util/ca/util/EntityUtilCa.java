//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockDeadBush
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockSnow
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.monster.EntityIronGolem
 *  net.minecraft.entity.monster.EntityPigZombie
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.entity.passive.EntityAmbientCreature
 *  net.minecraft.entity.passive.EntitySquid
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 */
package me.snow.aclient.util.ca.util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import me.snow.aclient.AliceMain;
import me.snow.aclient.util.Util;
import me.snow.aclient.util.ca.util.MathsUtilCa;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class EntityUtilCa
implements Util {
    public static Vec3d getInterpolatedAmount(Entity entity, float f) {
        return EntityUtilCa.getInterpolatedAmount(entity, f, f, f);
    }

    public static Vec3d[] getUnsafeBlockArray(Entity entity, int n, boolean bl) {
        List<Vec3d> list = EntityUtilCa.getUnsafeBlocks(entity, n, bl);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])arrvec3d);
    }

    public static void attackEntity(Entity entity, boolean bl) {
        if (bl) {
            EntityUtilCa.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        } else {
            EntityUtilCa.mc.playerController.attackEntity((EntityPlayer)EntityUtilCa.mc.player, entity);
        }
    }

    public static List<BlockPos> getSphere2(BlockPos blockPos, float f, float f2, boolean bl, boolean bl2, int n) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        int n2 = blockPos.getX();
        int n3 = blockPos.getY();
        int n4 = blockPos.getZ();
        int n5 = n2 - (int)f;
        while ((float)n5 <= (float)n2 + f) {
            int n6 = n4 - (int)f;
            while ((float)n6 <= (float)n4 + f) {
                int n7 = bl2 ? n3 - (int)f : n3;
                while (true) {
                    float f3 = n7;
                    float f4 = bl2 ? (float)n3 + f : (float)n3 + f2;
                    if (!(f3 < f4)) break;
                    double d = (n2 - n5) * (n2 - n5) + (n4 - n6) * (n4 - n6) + (bl2 ? (n3 - n7) * (n3 - n7) : 0);
                    if (!(!(d < (double)(f * f)) || bl && d < (double)((f - 1.0f) * (f - 1.0f)))) {
                        BlockPos blockPos2 = new BlockPos(n5, n7 + n, n6);
                        arrayList.add(blockPos2);
                    }
                    ++n7;
                }
                ++n6;
            }
            ++n5;
        }
        return arrayList;
    }

    public static Vec3d getInterpolatedAmount(Entity entity, double d, double d2, double d3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * d, (entity.posY - entity.lastTickPosY) * d2, (entity.posZ - entity.lastTickPosZ) * d3);
    }

    public static Color getColor(Entity entity, int n, int n2, int n3, int n4, boolean bl) {
        Color color = new Color((float)n / 255.0f, (float)n2 / 255.0f, (float)n3 / 255.0f, (float)n4 / 255.0f);
        if (entity instanceof EntityPlayer) {
            if (bl && AliceMain.friendManager.isFriend(entity.getName())) {
                color = new Color(0.33f, 1.0f, 1.0f, (float)n4 / 255.0f);
            }
            if (bl && !AliceMain.friendManager.isFriend(entity.getName())) {
                color = new Color(1.0f, 0.33f, 1.0f, (float)n4 / 255.0f);
            }
        }
        return color;
    }

    public static List<Vec3d> getUnsafeBlocksFromVec3d(Vec3d vec3d, int n, boolean bl) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        for (Vec3d vec3d2 : EntityUtilCa.getOffsets(n, bl)) {
            BlockPos blockPos = new BlockPos(vec3d).add(vec3d2.xCoord, vec3d2.yCoord, vec3d2.zCoord);
            Block block = EntityUtilCa.mc.world.getBlockState(blockPos).getBlock();
            if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid) && !(block instanceof BlockTallGrass) && !(block instanceof BlockFire) && !(block instanceof BlockDeadBush) && !(block instanceof BlockSnow)) continue;
            arrayList.add(vec3d2);
        }
        return arrayList;
    }

    public static double sigmoid(double d) {
        return 1.0 / (1.0 + Math.pow(Math.E, -1.0 * d));
    }

    public static Vec3d interpolateEntity(Entity entity, float f) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f);
    }

    public static boolean isBothHole(BlockPos blockPos) {
        for (BlockPos blockPos2 : new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()}) {
            IBlockState iBlockState = EntityUtilCa.mc.world.getBlockState(blockPos2);
            if (iBlockState.getBlock() != Blocks.AIR && (iBlockState.getBlock() == Blocks.BEDROCK || iBlockState.getBlock() == Blocks.OBSIDIAN)) continue;
            return false;
        }
        return true;
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

    public static double predictPos(double d, double d2) {
        double d3 = Math.abs(d);
        d3 -= d2;
        d3 = EntityUtilCa.sigmoid(d3);
        return d3;
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
                    float f2 = n8;
                    float f3 = bl2 ? (float)n4 + f : (float)(n4 + n);
                    if (!(f2 < f3)) break;
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

    public static boolean isHostileMob(Entity entity) {
        return entity.isCreatureType(EnumCreatureType.MONSTER, false) && !EntityUtilCa.isNeutralMob(entity) || entity instanceof EntitySpider;
    }

    public static void attackEntity(Entity entity, boolean bl, boolean bl2) {
        if (bl) {
            EntityUtilCa.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        } else {
            EntityUtilCa.mc.playerController.attackEntity((EntityPlayer)EntityUtilCa.mc.player, entity);
        }
        if (bl2) {
            EntityUtilCa.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

    public static Block isColliding(double d, double d2, double d3) {
        Block block = null;
        if (EntityUtilCa.mc.player != null) {
            AxisAlignedBB axisAlignedBB = EntityUtilCa.mc.player.getRidingEntity() != null ? EntityUtilCa.mc.player.getRidingEntity().getEntityBoundingBox().func_191195_a(0.0, 0.0, 0.0).offset(d, d2, d3) : EntityUtilCa.mc.player.getEntityBoundingBox().func_191195_a(0.0, 0.0, 0.0).offset(d, d2, d3);
            int n = (int)axisAlignedBB.minY;
            for (int i = MathHelper.floor((double)axisAlignedBB.minX); i < MathHelper.floor((double)axisAlignedBB.maxX) + 1; ++i) {
                for (int j = MathHelper.floor((double)axisAlignedBB.minZ); j < MathHelper.floor((double)axisAlignedBB.maxZ) + 1; ++j) {
                    block = EntityUtilCa.mc.world.getBlockState(new BlockPos(i, n, j)).getBlock();
                }
            }
        }
        return block;
    }

    public static boolean isInHole(Entity entity) {
        return EntityUtilCa.isBlockValid(new BlockPos(entity.posX, entity.posY, entity.posZ));
    }

    public static BlockPos getPlayerPosWithEntity() {
        return new BlockPos(EntityUtilCa.mc.player.getRidingEntity() != null ? EntityUtilCa.mc.player.getRidingEntity().posX : EntityUtilCa.mc.player.posX, EntityUtilCa.mc.player.getRidingEntity() != null ? EntityUtilCa.mc.player.getRidingEntity().posY : EntityUtilCa.mc.player.posY, EntityUtilCa.mc.player.getRidingEntity() != null ? EntityUtilCa.mc.player.getRidingEntity().posZ : EntityUtilCa.mc.player.posZ);
    }

    public static boolean is32k(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        if (itemStack.getTagCompound() == null) {
            return false;
        }
        NBTTagList nBTTagList = (NBTTagList)itemStack.getTagCompound().getTag("ench");
        if (nBTTagList == null) {
            return false;
        }
        for (int i = 0; i < nBTTagList.tagCount(); ++i) {
            NBTTagCompound nBTTagCompound = nBTTagList.getCompoundTagAt(i);
            if (nBTTagCompound.getInteger("id") != 16) continue;
            int n = nBTTagCompound.getInteger("lvl");
            if (n < 42) break;
            return true;
        }
        return false;
    }

    public static boolean isPassiveMob(Entity entity) {
        if (entity instanceof EntityWolf && ((EntityWolf)entity).isAngry()) {
            return false;
        }
        if (entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntitySquid) {
            return true;
        }
        return entity instanceof EntityIronGolem && ((EntityIronGolem)entity).getAITarget() == null;
    }

    public static boolean stopSneaking(boolean bl) {
        if (bl && EntityUtilCa.mc.player != null) {
            EntityUtilCa.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)EntityUtilCa.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        return false;
    }

    public static void setTimer(float f) {
        EntityUtilCa.mc.timer.field_194149_e = 50.0f / f;
    }

    public static float getHealth(Entity entity) {
        if (entity.isEntityAlive()) {
            EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            return entityLivingBase.getHealth() + entityLivingBase.getAbsorptionAmount();
        }
        return 0.0f;
    }

    public static boolean isBlockValid(BlockPos blockPos) {
        return EntityUtilCa.isBedrockHole(blockPos) || EntityUtilCa.isObbyHole(blockPos) || EntityUtilCa.isBothHole(blockPos);
    }

    public static boolean isSafe(Entity entity, int n, boolean bl) {
        return EntityUtilCa.getUnsafeBlocks(entity, n, bl).size() == 0;
    }

    public static boolean isLiving(Entity entity) {
        return entity instanceof EntityLivingBase;
    }

    public static boolean isOnLiquid(double d) {
        if (EntityUtilCa.mc.player.fallDistance >= 3.0f) {
            return false;
        }
        AxisAlignedBB axisAlignedBB = EntityUtilCa.mc.player.getRidingEntity() != null ? EntityUtilCa.mc.player.getRidingEntity().getEntityBoundingBox().func_191195_a(0.0, 0.0, 0.0).offset(0.0, -d, 0.0) : EntityUtilCa.mc.player.getEntityBoundingBox().func_191195_a(0.0, 0.0, 0.0).offset(0.0, -d, 0.0);
        boolean bl = false;
        int n = (int)axisAlignedBB.minY;
        for (int i = MathHelper.floor((double)axisAlignedBB.minX); i < MathHelper.floor((double)(axisAlignedBB.maxX + 1.0)); ++i) {
            for (int j = MathHelper.floor((double)axisAlignedBB.minZ); j < MathHelper.floor((double)(axisAlignedBB.maxZ + 1.0)); ++j) {
                Block block = EntityUtilCa.mc.world.getBlockState(new BlockPos(i, n, j)).getBlock();
                if (block == Blocks.AIR) continue;
                if (!(block instanceof BlockLiquid)) {
                    return false;
                }
                bl = true;
            }
        }
        return bl;
    }

    public static boolean holding32k(EntityPlayer entityPlayer) {
        return EntityUtilCa.is32k(entityPlayer.getHeldItemMainhand());
    }

    public static boolean isInLiquid() {
        if (EntityUtilCa.mc.player != null) {
            if (EntityUtilCa.mc.player.fallDistance >= 3.0f) {
                return false;
            }
            boolean bl = false;
            AxisAlignedBB axisAlignedBB = EntityUtilCa.mc.player.getRidingEntity() != null ? EntityUtilCa.mc.player.getRidingEntity().getEntityBoundingBox() : EntityUtilCa.mc.player.getEntityBoundingBox();
            int n = (int)axisAlignedBB.minY;
            for (int i = MathHelper.floor((double)axisAlignedBB.minX); i < MathHelper.floor((double)axisAlignedBB.maxX) + 1; ++i) {
                for (int j = MathHelper.floor((double)axisAlignedBB.minZ); j < MathHelper.floor((double)axisAlignedBB.maxZ) + 1; ++j) {
                    Block block = EntityUtilCa.mc.world.getBlockState(new BlockPos(i, n, j)).getBlock();
                    if (block instanceof BlockAir) continue;
                    if (!(block instanceof BlockLiquid)) {
                        return false;
                    }
                    bl = true;
                }
            }
            return bl;
        }
        return false;
    }

    public static boolean isntValid(Entity entity, double d) {
        return entity == null || !entity.isEntityAlive() || entity.equals((Object)EntityUtilCa.mc.player) || entity instanceof EntityPlayer && AliceMain.friendManager.isFriend(entity.getName()) || EntityUtilCa.mc.player.getDistanceSqToEntity(entity) > d * d;
    }

    public static boolean isNeutralMob(Entity entity) {
        return entity instanceof EntityPigZombie || entity instanceof EntityWolf || entity instanceof EntityEnderman;
    }

    public static boolean isAboveBlock(Entity entity, BlockPos blockPos) {
        return entity.posY >= (double)blockPos.getY();
    }

    public static List<Vec3d> getUnsafeBlocks(Entity entity, int n, boolean bl) {
        return EntityUtilCa.getUnsafeBlocksFromVec3d(entity.getPositionVector(), n, bl);
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
                if (!(EntityUtilCa.mc.world.getBlockState(new BlockPos(i, MathHelper.floor((double)d3), j)).getBlock() instanceof BlockLiquid)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean isBedrockHole(BlockPos blockPos) {
        for (BlockPos blockPos2 : new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()}) {
            IBlockState iBlockState = EntityUtilCa.mc.world.getBlockState(blockPos2);
            if (iBlockState.getBlock() != Blocks.AIR && iBlockState.getBlock() == Blocks.BEDROCK) continue;
            return false;
        }
        return true;
    }

    public static boolean canEntityFeetBeSeen(Entity entity) {
        return EntityUtilCa.mc.world.rayTraceBlocks(new Vec3d(EntityUtilCa.mc.player.posX, EntityUtilCa.mc.player.posX + (double)EntityUtilCa.mc.player.getEyeHeight(), EntityUtilCa.mc.player.posZ), new Vec3d(entity.posX, entity.posY, entity.posZ), false, true, false) == null;
    }

    public static void resetTimer() {
        EntityUtilCa.mc.timer.field_194149_e = 50.0f;
    }

    public static Vec3d[] getOffsets(int n, boolean bl) {
        List<Vec3d> list = EntityUtilCa.getOffsetList(n, bl);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])arrvec3d);
    }

    public static Vec3d getInterpolatedPos(Entity entity, float f) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(EntityUtilCa.getInterpolatedAmount(entity, f));
    }

    public static BlockPos getFlooredPos(Entity entity) {
        return new BlockPos(Math.floor(entity.posX), Math.floor(entity.posY), Math.floor(entity.posZ));
    }

    public static Vec3d getInterpolatedRenderPos(Entity entity, float f) {
        return EntityUtilCa.getInterpolatedPos(entity, f).subtract(EntityUtilCa.mc.getRenderManager().renderPosX, EntityUtilCa.mc.getRenderManager().renderPosY, EntityUtilCa.mc.getRenderManager().renderPosZ);
    }

    public static BlockPos getRoundedBlockPos(Entity entity) {
        return new BlockPos(MathsUtilCa.roundVec(entity.getPositionVector(), 0));
    }

    public static boolean isAboveLiquid(Entity entity) {
        if (entity == null) {
            return false;
        }
        double d = entity.posY + 0.01;
        for (int i = MathHelper.floor((double)entity.posX); i < MathHelper.ceil((double)entity.posX); ++i) {
            for (int j = MathHelper.floor((double)entity.posZ); j < MathHelper.ceil((double)entity.posZ); ++j) {
                if (!(EntityUtilCa.mc.world.getBlockState(new BlockPos(i, (int)d, j)).getBlock() instanceof BlockLiquid)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean checkCollide() {
        return !EntityUtilCa.mc.player.isSneaking() && (EntityUtilCa.mc.player.getRidingEntity() == null || EntityUtilCa.mc.player.getRidingEntity().fallDistance < 3.0f) && EntityUtilCa.mc.player.fallDistance < 3.0f;
    }

    public static boolean isObbyHole(BlockPos blockPos) {
        for (BlockPos blockPos2 : new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()}) {
            IBlockState iBlockState = EntityUtilCa.mc.world.getBlockState(blockPos2);
            if (iBlockState.getBlock() != Blocks.AIR && iBlockState.getBlock() == Blocks.OBSIDIAN) continue;
            return false;
        }
        return true;
    }

    public static Vec3d[] getUnsafeBlockArrayFromVec3d(Vec3d vec3d, int n, boolean bl) {
        List<Vec3d> list = EntityUtilCa.getUnsafeBlocksFromVec3d(vec3d, n, bl);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])arrvec3d);
    }
}

