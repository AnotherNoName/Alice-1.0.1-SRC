//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockDeadBush
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.BlockSnow
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.block.BlockWeb
 *  net.minecraft.block.BlockWorkbench
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.item.EntityExpBottle
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.util.CombatRules
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package me.snow.aclient.util;

import com.google.common.util.concurrent.AtomicDouble;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.RotationUtil;
import me.snow.aclient.util.Util;
import me.snow.aclient.util.skid.oyvey.BlockUtil2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockUtil
implements Util {
    private static /* synthetic */ BlockPos _currBlock;
    private static /* synthetic */ boolean _started;
    public static final /* synthetic */ List<Block> blackList;
    public static /* synthetic */ List<Block> unSolidBlocks;
    public static /* synthetic */ List<Block> emptyBlocks;
    public static /* synthetic */ List<Block> rightclickableBlocks;
    public static final /* synthetic */ List<Block> shulkerList;

    public static List<BlockPos> getBlockSphere(float f, Class<BlockWorkbench> class_) {
        NonNullList nonNullList = NonNullList.func_191196_a();
        nonNullList.addAll((Collection)BlockUtil.getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil.mc.player), f, (int)f, false, true, 0).stream().filter(blockPos -> class_.isInstance((Object)BlockUtil.mc.world.getBlockState(blockPos).getBlock())).collect(Collectors.toList()));
        return nonNullList;
    }

    public static boolean canBreak(BlockPos blockPos) {
        IBlockState iBlockState = BlockUtil.mc.world.getBlockState(blockPos);
        Block block = iBlockState.getBlock();
        return block.getBlockHardness(iBlockState, (World)BlockUtil.mc.world, blockPos) != -1.0f;
    }

    public static void rightClickBlock(BlockPos blockPos, Vec3d vec3d, EnumHand enumHand, EnumFacing enumFacing, boolean bl) {
        if (bl) {
            float f = (float)(vec3d.xCoord - (double)blockPos.getX());
            float f2 = (float)(vec3d.yCoord - (double)blockPos.getY());
            float f3 = (float)(vec3d.zCoord - (double)blockPos.getZ());
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, f, f2, f3));
        } else {
            BlockUtil.mc.playerController.processRightClickBlock(BlockUtil.mc.player, BlockUtil.mc.world, blockPos, enumFacing, vec3d, enumHand);
        }
        BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil.mc.rightClickDelayTimer = 4;
    }

    private static float getDamageMultiplied(float f) {
        int n = BlockUtil.mc.world.getDifficulty().getDifficultyId();
        return f * (n == 0 ? 0.0f : (n == 2 ? 1.0f : (n == 1 ? 0.5f : 1.5f)));
    }

    public static boolean placeBlockSmartRotate(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3) {
        boolean bl4 = false;
        EnumFacing enumFacing = BlockUtil.getFirstFacing(blockPos);
        if (enumFacing == null) {
            return bl3;
        }
        BlockPos blockPos2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing2 = enumFacing.getOpposite();
        Vec3d vec3d = new Vec3d((Vec3i)blockPos2).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing2.getDirectionVec()).scale(0.5));
        Block block = BlockUtil.mc.world.getBlockState(blockPos2).getBlock();
        if (!BlockUtil.mc.player.isSneaking() && (blackList.contains((Object)block) || shulkerList.contains((Object)block))) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            bl4 = true;
        }
        if (bl) {
            AliceMain.rotationManager.lookAtVec3d(vec3d);
        }
        BlockUtil.rightClickBlock(blockPos2, vec3d, enumHand, enumFacing2, bl2);
        BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil.mc.rightClickDelayTimer = 4;
        return bl4 || bl3;
    }

    public static boolean canPlaceCrystal(BlockPos blockPos, boolean bl, boolean bl2, boolean bl3) {
        BlockPos blockPos2 = blockPos.add(0, 1, 0);
        BlockPos blockPos3 = blockPos.add(0, 2, 0);
        try {
            if (BlockUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && BlockUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
            if (!bl2 && BlockUtil.mc.world.getBlockState(blockPos3).getBlock() != Blocks.AIR || BlockUtil.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR) {
                return false;
            }
            for (Entity entity : BlockUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2))) {
                if (entity.isDead || bl && entity instanceof EntityEnderCrystal) continue;
                return false;
            }
            if (!bl2 && !bl3) {
                for (Entity entity : BlockUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos3))) {
                    if (entity.isDead || bl && entity instanceof EntityEnderCrystal) continue;
                    return false;
                }
            }
        }
        catch (Exception exception) {
            return false;
        }
        return true;
    }

    public static boolean isBlockBelowEntitySolid(Entity entity) {
        if (entity != null) {
            BlockPos blockPos = new BlockPos(entity.posX, entity.posY - 1.0, entity.posZ);
            return BlockUtil.isBlockSolid(blockPos);
        }
        return false;
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

    public static List<BlockPos> getSphereAutoCrystal(double d, boolean bl) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        BlockPos blockPos = new BlockPos(Math.floor(BlockUtil.mc.player.posX), Math.floor(BlockUtil.mc.player.posY), Math.floor(BlockUtil.mc.player.posZ));
        int n = blockPos.getX() - (int)d;
        while ((double)n <= (double)blockPos.getX() + d) {
            int n2 = blockPos.getY() - (int)d;
            while ((double)n2 < (double)blockPos.getY() + d) {
                int n3 = blockPos.getZ() - (int)d;
                while ((double)n3 <= (double)blockPos.getZ() + d) {
                    double d2 = (blockPos.getX() - n) * (blockPos.getX() - n) + (blockPos.getZ() - n3) * (blockPos.getZ() - n3) + (blockPos.getY() - n2) * (blockPos.getY() - n2);
                    BlockPos blockPos2 = new BlockPos(n, n2, n3);
                    if (d2 < d * d && bl && !BlockUtil.mc.world.getBlockState(blockPos2).getBlock().equals((Object)Blocks.AIR)) {
                        arrayList.add(blockPos2);
                    }
                    ++n3;
                }
                ++n2;
            }
            ++n;
        }
        return arrayList;
    }

    public static void rightClickBlockLegit(BlockPos blockPos, float f, boolean bl, EnumHand enumHand, AtomicDouble atomicDouble, AtomicDouble atomicDouble2, AtomicBoolean atomicBoolean, boolean bl2) {
        Vec3d vec3d = RotationUtil.getEyesPos();
        Vec3d vec3d2 = new Vec3d((Vec3i)blockPos).addVector(0.5, 0.5, 0.5);
        double d = vec3d.squareDistanceTo(vec3d2);
        for (EnumFacing enumFacing : EnumFacing.values()) {
            Vec3d vec3d3 = vec3d2.add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
            double d2 = vec3d.squareDistanceTo(vec3d3);
            if (d2 > MathUtil.square(f) || d2 >= d || BlockUtil.mc.world.rayTraceBlocks(vec3d, vec3d3, false, true, false) != null) continue;
            if (bl) {
                float[] arrf = RotationUtil.getLegitRotations(vec3d3);
                atomicDouble.set((double)arrf[0]);
                atomicDouble2.set((double)arrf[1]);
                atomicBoolean.set(true);
            }
            BlockUtil.rightClickBlock(blockPos, vec3d3, enumHand, enumFacing, bl2);
            BlockUtil.mc.player.swingArm(enumHand);
            BlockUtil.mc.rightClickDelayTimer = 4;
            break;
        }
    }

    public static List<EnumFacing> getPossibleSides(BlockPos blockPos) {
        ArrayList<EnumFacing> arrayList = new ArrayList<EnumFacing>();
        if (BlockUtil.mc.world == null || blockPos == null) {
            return arrayList;
        }
        for (EnumFacing enumFacing : EnumFacing.values()) {
            BlockPos blockPos2 = blockPos.offset(enumFacing);
            IBlockState iBlockState = BlockUtil.mc.world.getBlockState(blockPos2);
            if (!iBlockState.getBlock().canCollideCheck(iBlockState, false) || iBlockState.getMaterial().isReplaceable()) continue;
            arrayList.add(enumFacing);
        }
        return arrayList;
    }

    public static boolean isBlockSolid(BlockPos blockPos) {
        return !BlockUtil.isBlockUnSolid(blockPos);
    }

    private static boolean IsDoneBreaking(IBlockState iBlockState) {
        return iBlockState.getBlock() == Blocks.BEDROCK || iBlockState.getBlock() == Blocks.AIR || iBlockState.getBlock() instanceof BlockLiquid;
    }

    public static Vec3d posToVec3d(BlockPos blockPos) {
        return new Vec3d((Vec3i)blockPos);
    }

    public static boolean rayTracePlaceCheck(BlockPos blockPos, boolean bl, float f) {
        return !bl || BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + (double)BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)((float)blockPos.getY() + f), (double)blockPos.getZ()), false, true, false) == null;
    }

    public static boolean isInHole() {
        BlockPos blockPos = new BlockPos(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY, BlockUtil.mc.player.posZ);
        IBlockState iBlockState = BlockUtil.mc.world.getBlockState(blockPos);
        return BlockUtil.isBlockValid(iBlockState, blockPos);
    }

    public static List<BlockPos> possiblePlacePositions(float f) {
        NonNullList nonNullList = NonNullList.func_191196_a();
        nonNullList.addAll((Collection)BlockUtil.getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil.mc.player), f, (int)f, false, true, 0).stream().filter(BlockUtil::canPlaceCrystal).collect(Collectors.toList()));
        return nonNullList;
    }

    private static Block getBlock(BlockPos blockPos) {
        return BlockUtil.getState(blockPos).getBlock();
    }

    public static boolean isScaffoldPos(BlockPos blockPos) {
        return BlockUtil.mc.world.isAirBlock(blockPos) || BlockUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.SNOW_LAYER || BlockUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.TALLGRASS || BlockUtil.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid;
    }

    public static BlockPos GetCurrBlock() {
        return _currBlock;
    }

    public static int isPositionPlaceable(BlockPos blockPos, boolean bl, boolean bl2) {
        Block block = BlockUtil.mc.world.getBlockState(blockPos).getBlock();
        if (!(block instanceof BlockAir || block instanceof BlockLiquid || block instanceof BlockTallGrass || block instanceof BlockFire || block instanceof BlockDeadBush || block instanceof BlockSnow)) {
            return 0;
        }
        if (!BlockUtil.rayTracePlaceCheck(blockPos, bl, 0.0f)) {
            return -1;
        }
        if (bl2) {
            for (EnumFacing enumFacing : BlockUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos))) {
                if (enumFacing instanceof EntityItem || enumFacing instanceof EntityXPOrb || enumFacing instanceof EntityArrow || enumFacing instanceof EntityExpBottle) continue;
                return 1;
            }
        }
        for (EnumFacing enumFacing : BlockUtil.getPossibleSides(blockPos)) {
            if (!BlockUtil.canBeClicked(blockPos.offset(enumFacing))) continue;
            return 3;
        }
        return 2;
    }

    public static boolean canPlaceCrystal(BlockPos blockPos) {
        BlockPos blockPos2 = blockPos.add(0, 1, 0);
        BlockPos blockPos3 = blockPos.add(0, 2, 0);
        try {
            return (BlockUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.BEDROCK || BlockUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN) && BlockUtil.mc.world.getBlockState(blockPos2).getBlock() == Blocks.AIR && BlockUtil.mc.world.getBlockState(blockPos3).getBlock() == Blocks.AIR && BlockUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2)).isEmpty() && BlockUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos3)).isEmpty();
        }
        catch (Exception exception) {
            return false;
        }
    }

    public static boolean canBeClicked(BlockPos blockPos) {
        return BlockUtil.getBlock(blockPos).canCollideCheck(BlockUtil.getState(blockPos), false);
    }

    public static Vec3d[] convertVec3ds(Vec3d vec3d, Vec3d[] arrvec3d) {
        Vec3d[] arrvec3d2 = new Vec3d[arrvec3d.length];
        for (int i = 0; i < arrvec3d.length; ++i) {
            arrvec3d2[i] = vec3d.add(arrvec3d[i]);
        }
        return arrvec3d2;
    }

    public static List<BlockPos> possiblePlacePositions(float f, boolean bl, boolean bl2, boolean bl3) {
        NonNullList nonNullList = NonNullList.func_191196_a();
        nonNullList.addAll((Collection)BlockUtil.getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil.mc.player), f, (int)f, false, true, 0).stream().filter(blockPos -> BlockUtil.canPlaceCrystal(blockPos, bl, bl2, bl3)).collect(Collectors.toList()));
        return nonNullList;
    }

    public static boolean isAir(BlockPos blockPos) {
        return BlockUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR;
    }

    public static boolean placeBlock(BlockPos blockPos) {
        if (BlockUtil.isBlockEmpty(blockPos)) {
            EnumFacing[] arrenumFacing;
            EnumFacing[] arrenumFacing2 = arrenumFacing = EnumFacing.values();
            for (EnumFacing enumFacing : arrenumFacing) {
                Block block = BlockUtil.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock();
                Vec3d vec3d = new Vec3d((double)blockPos.getX() + 0.5 + (double)enumFacing.getFrontOffsetX() * 0.5, (double)blockPos.getY() + 0.5 + (double)enumFacing.getFrontOffsetY() * 0.5, (double)blockPos.getZ() + 0.5 + (double)enumFacing.getFrontOffsetZ() * 0.5);
                if (emptyBlocks.contains((Object)block) || !(BlockUtil.mc.player.getPositionEyes(mc.getRenderPartialTicks()).distanceTo(vec3d) <= 4.25)) continue;
                float[] arrf = new float[]{BlockUtil.mc.player.rotationYaw, BlockUtil.mc.player.rotationPitch};
                if (rightclickableBlocks.contains((Object)block)) {
                    BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                }
                BlockUtil.mc.playerController.processRightClickBlock(BlockUtil.mc.player, BlockUtil.mc.world, blockPos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                if (rightclickableBlocks.contains((Object)block)) {
                    BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                }
                BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
                return true;
            }
        }
        return false;
    }

    public static boolean Update(float f, boolean bl) {
        if (_currBlock == null) {
            return false;
        }
        IBlockState iBlockState = BlockUtil.mc.world.getBlockState(_currBlock);
        if (!BlockUtil.IsDoneBreaking(iBlockState) && !(BlockUtil.mc.player.getDistanceSq(_currBlock) > Math.pow(f, f))) {
            RayTraceResult rayTraceResult;
            BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
            EnumFacing enumFacing = EnumFacing.UP;
            if (bl && (rayTraceResult = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + (double)BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d((double)_currBlock.getX() + 0.5, (double)_currBlock.getY() - 0.5, (double)_currBlock.getZ() + 0.5))) != null && rayTraceResult.sideHit != null) {
                enumFacing = rayTraceResult.sideHit;
            }
            if (!_started) {
                _started = true;
                BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, _currBlock, enumFacing));
                BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, _currBlock, enumFacing));
            } else {
                BlockUtil.mc.playerController.onPlayerDamageBlock(_currBlock, enumFacing);
            }
            return true;
        }
        _currBlock = null;
        return false;
    }

    public static boolean isEnemySafe(EntityPlayer entityPlayer) {
        BlockPos blockPos = new BlockPos(Math.floor(entityPlayer.posX), Math.floor(entityPlayer.posY), Math.floor(entityPlayer.posZ));
        return !(BlockUtil.mc.world.getBlockState(blockPos.down()).getBlock() != Blocks.OBSIDIAN && BlockUtil.mc.world.getBlockState(blockPos.down()).getBlock() != Blocks.BEDROCK || BlockUtil.mc.world.getBlockState(blockPos.north()).getBlock() != Blocks.OBSIDIAN && BlockUtil.mc.world.getBlockState(blockPos.north()).getBlock() != Blocks.BEDROCK || BlockUtil.mc.world.getBlockState(blockPos.east()).getBlock() != Blocks.OBSIDIAN && BlockUtil.mc.world.getBlockState(blockPos.east()).getBlock() != Blocks.BEDROCK || BlockUtil.mc.world.getBlockState(blockPos.south()).getBlock() != Blocks.OBSIDIAN && BlockUtil.mc.world.getBlockState(blockPos.south()).getBlock() != Blocks.BEDROCK || BlockUtil.mc.world.getBlockState(blockPos.west()).getBlock() != Blocks.OBSIDIAN && BlockUtil.mc.world.getBlockState(blockPos.west()).getBlock() != Blocks.BEDROCK);
    }

    private static float getBlockDensity(Vec3d vec3d, AxisAlignedBB axisAlignedBB) {
        double d = 1.0 / ((axisAlignedBB.maxX - axisAlignedBB.minX) * 2.0 + 1.0);
        double d2 = 1.0 / ((axisAlignedBB.maxY - axisAlignedBB.minY) * 2.0 + 1.0);
        double d3 = 1.0 / ((axisAlignedBB.maxZ - axisAlignedBB.minZ) * 2.0 + 1.0);
        double d4 = (1.0 - Math.floor(1.0 / d) * d) / 2.0;
        double d5 = (1.0 - Math.floor(1.0 / d3) * d3) / 2.0;
        if (d >= 0.0 && d2 >= 0.0 && d3 >= 0.0) {
            int n = 0;
            int n2 = 0;
            for (float f = 0.0f; f <= 1.0f; f += (float)d) {
                for (float f2 = 0.0f; f2 <= 1.0f; f2 += (float)d2) {
                    for (float f3 = 0.0f; f3 <= 1.0f; f3 += (float)d3) {
                        double d6 = axisAlignedBB.minX + (axisAlignedBB.maxX - axisAlignedBB.minX) * (double)f;
                        double d7 = axisAlignedBB.minY + (axisAlignedBB.maxY - axisAlignedBB.minY) * (double)f2;
                        double d8 = axisAlignedBB.minZ + (axisAlignedBB.maxZ - axisAlignedBB.minZ) * (double)f3;
                        if (BlockUtil.rayTraceBlocks(new Vec3d(d6 + d4, d7, d8 + d5), vec3d, false, false, false, true) == null) {
                            ++n;
                        }
                        ++n2;
                    }
                }
            }
            return (float)n / (float)n2;
        }
        return 0.0f;
    }

    public static BlockPos[] toBlockPos(Vec3d[] arrvec3d) {
        BlockPos[] arrblockPos = new BlockPos[arrvec3d.length];
        for (int i = 0; i < arrvec3d.length; ++i) {
            arrblockPos[i] = new BlockPos(arrvec3d[i]);
        }
        return arrblockPos;
    }

    public static boolean placeBlock(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3) {
        boolean bl4 = false;
        EnumFacing enumFacing = BlockUtil.getFirstFacing(blockPos);
        if (enumFacing == null) {
            return bl3;
        }
        BlockPos blockPos2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing2 = enumFacing.getOpposite();
        Vec3d vec3d = new Vec3d((Vec3i)blockPos2).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing2.getDirectionVec()).scale(0.5));
        Block block = BlockUtil.mc.world.getBlockState(blockPos2).getBlock();
        if (!BlockUtil.mc.player.isSneaking() && (blackList.contains((Object)block) || shulkerList.contains((Object)block))) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtil.mc.player.setSneaking(true);
            bl4 = true;
        }
        if (bl) {
            RotationUtil.faceVector(vec3d, true);
        }
        BlockUtil.rightClickBlock(blockPos2, vec3d, enumHand, enumFacing2, bl2);
        BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil.mc.rightClickDelayTimer = 4;
        return bl4 || bl3;
    }

    public static boolean isInterceptedByOther(BlockPos blockPos) {
        for (Entity entity : BlockUtil.mc.world.loadedEntityList) {
            if (entity instanceof EntityEnderCrystal || entity instanceof EntityItem || !new AxisAlignedBB(blockPos).intersectsWith(entity.getEntityBoundingBox())) continue;
            return true;
        }
        return false;
    }

    private static float getBlastReduction(EntityLivingBase entityLivingBase, float f, Explosion explosion) {
        float f2 = f;
        if (entityLivingBase instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
            DamageSource damageSource = DamageSource.causeExplosionDamage((Explosion)explosion);
            f2 = CombatRules.getDamageAfterAbsorb((float)f2, (float)entityPlayer.getTotalArmorValue(), (float)((float)entityPlayer.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue()));
            int n = 0;
            try {
                n = EnchantmentHelper.getEnchantmentModifierDamage((Iterable)entityPlayer.getArmorInventoryList(), (DamageSource)damageSource);
            }
            catch (Exception exception) {
                // empty catch block
            }
            float f3 = MathHelper.clamp((float)n, (float)0.0f, (float)20.0f);
            f2 *= 1.0f - f3 / 25.0f;
            if (entityLivingBase.isPotionActive(MobEffects.RESISTANCE)) {
                f2 -= f2 / 4.0f;
            }
            f2 = Math.max(f2, 0.0f);
            return f2;
        }
        f2 = CombatRules.getDamageAfterAbsorb((float)f2, (float)entityLivingBase.getTotalArmorValue(), (float)((float)entityLivingBase.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue()));
        return f2;
    }

    public static List<BlockPos> getSpherefire(double d, AirType airType, EntityPlayer entityPlayer) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        BlockPos blockPos = new BlockPos(entityPlayer.getPositionVector());
        int n = blockPos.getX();
        int n2 = blockPos.getY();
        int n3 = blockPos.getZ();
        int n4 = (int)d;
        int n5 = n - n4;
        while ((double)n5 <= (double)n + d) {
            int n6 = n3 - n4;
            while ((double)n6 <= (double)n3 + d) {
                int n7 = n2 - n4;
                while ((double)n7 < (double)n2 + d) {
                    BlockPos blockPos2;
                    double d2 = (n - n5) * (n - n5) + (n3 - n6) * (n3 - n6) + (n2 - n7) * (n2 - n7);
                    if (!(!(d2 < d * d) || BlockUtil.mc.world.getBlockState(blockPos2 = new BlockPos(n5, n7, n6)).getBlock().equals((Object)Blocks.AIR) && airType.equals((Object)AirType.IgnoreAir) || !BlockUtil.mc.world.getBlockState(blockPos2).getBlock().equals((Object)Blocks.AIR) && airType.equals((Object)AirType.OnlyAir))) {
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

    public static void SetCurrentBlock(BlockPos blockPos) {
        _currBlock = blockPos;
        _started = false;
    }

    public static Boolean isPosInFov(BlockPos blockPos) {
        int n = RotationUtil.getDirection4D();
        if (n == 0 && (double)blockPos.getZ() - BlockUtil.mc.player.getPositionVector().zCoord < 0.0) {
            return false;
        }
        if (n == 1 && (double)blockPos.getX() - BlockUtil.mc.player.getPositionVector().xCoord > 0.0) {
            return false;
        }
        if (n == 2 && (double)blockPos.getZ() - BlockUtil.mc.player.getPositionVector().zCoord > 0.0) {
            return false;
        }
        return n != 3 || !((double)blockPos.getX() - BlockUtil.mc.player.getPositionVector().xCoord < 0.0);
    }

    public static void placeCrystalOnBlockNew(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        RayTraceResult rayTraceResult = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + (double)BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d((double)blockPos.getX() + 0.5, (double)blockPos.getY() - 0.5, (double)blockPos.getZ() + 0.5));
        EnumFacing enumFacing = rayTraceResult == null || rayTraceResult.sideHit == null ? EnumFacing.UP : rayTraceResult.sideHit;
        int n = BlockUtil.mc.player.inventory.currentItem;
        int n2 = InventoryUtil.getItemHotbar(Items.END_CRYSTAL);
        if (enumHand == EnumHand.MAIN_HAND && bl4 && n2 != -1 && n2 != BlockUtil.mc.player.inventory.currentItem) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n2));
        }
        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, 0.0f, 0.0f, 0.0f));
        if (enumHand == EnumHand.MAIN_HAND && bl4 && n2 != -1 && n2 != BlockUtil.mc.player.inventory.currentItem) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
        }
        if (bl) {
            if (bl3) {
                BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketAnimation(bl2 ? enumHand : EnumHand.MAIN_HAND));
            } else {
                BlockUtil.mc.player.swingArm(bl2 ? enumHand : EnumHand.MAIN_HAND);
            }
        }
    }

    public static EnumFacing getFacing(BlockPos blockPos) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            RayTraceResult rayTraceResult = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + (double)BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d((double)blockPos.getX() + 0.5 + (double)enumFacing.getDirectionVec().getX() / 2.0, (double)blockPos.getY() + 0.5 + (double)enumFacing.getDirectionVec().getY() / 2.0, (double)blockPos.getZ() + 0.5 + (double)enumFacing.getDirectionVec().getZ() / 2.0), false, true, false);
            if (rayTraceResult != null && (rayTraceResult.typeOfHit != RayTraceResult.Type.BLOCK || !rayTraceResult.getBlockPos().equals((Object)blockPos))) continue;
            return enumFacing;
        }
        if ((double)blockPos.getY() > BlockUtil.mc.player.posY + (double)BlockUtil.mc.player.getEyeHeight()) {
            return EnumFacing.DOWN;
        }
        return EnumFacing.UP;
    }

    public static void rightClickBed(BlockPos blockPos, float f, boolean bl, EnumHand enumHand, AtomicDouble atomicDouble, AtomicDouble atomicDouble2, AtomicBoolean atomicBoolean, boolean bl2) {
        Vec3d vec3d = new Vec3d((Vec3i)blockPos).addVector(0.5, 0.5, 0.5);
        RayTraceResult rayTraceResult = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + (double)BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), vec3d);
        EnumFacing enumFacing = rayTraceResult == null || rayTraceResult.sideHit == null ? EnumFacing.UP : rayTraceResult.sideHit;
        RotationUtil.getEyesPos();
        if (bl) {
            float[] arrf = RotationUtil.getLegitRotations(vec3d);
            atomicDouble.set((double)arrf[0]);
            atomicDouble2.set((double)arrf[1]);
            atomicBoolean.set(true);
        }
        BlockUtil.rightClickBlock(blockPos, vec3d, enumHand, enumFacing, bl2);
        BlockUtil.mc.player.swingArm(enumHand);
        BlockUtil.mc.rightClickDelayTimer = 4;
    }

    public static void placeCrystalOnBlock(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3) {
        RayTraceResult rayTraceResult = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + (double)BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d((double)blockPos.getX() + 0.5, (double)blockPos.getY() - 0.5, (double)blockPos.getZ() + 0.5));
        EnumFacing enumFacing = rayTraceResult == null || rayTraceResult.sideHit == null ? EnumFacing.UP : rayTraceResult.sideHit;
        int n = BlockUtil.mc.player.inventory.currentItem;
        int n2 = InventoryUtil.getItemHotbar(Items.END_CRYSTAL);
        if (enumHand == EnumHand.MAIN_HAND && bl3 && n2 != -1 && n2 != BlockUtil.mc.player.inventory.currentItem) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n2));
        }
        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, 0.0f, 0.0f, 0.0f));
        if (enumHand == EnumHand.MAIN_HAND && bl3 && n2 != -1 && n2 != BlockUtil.mc.player.inventory.currentItem) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
        }
        if (bl) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketAnimation(bl2 ? enumHand : EnumHand.MAIN_HAND));
        }
    }

    public static List<BlockPos> possiblePlacePositions(float f, boolean bl, boolean bl2) {
        NonNullList nonNullList = NonNullList.func_191196_a();
        nonNullList.addAll((Collection)BlockUtil.getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil.mc.player), f, (int)f, false, true, 0).stream().filter(blockPos -> BlockUtil.canPlaceCrystal(blockPos, bl, bl2)).collect(Collectors.toList()));
        return nonNullList;
    }

    public static boolean rayTracePlaceCheck(BlockPos blockPos) {
        return BlockUtil.rayTracePlaceCheck(blockPos, true);
    }

    private static RayTraceResult rayTraceBlocks(Vec3d vec3d, Vec3d vec3d2, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        if (Double.isNaN(vec3d.xCoord) || Double.isNaN(vec3d.yCoord) || Double.isNaN(vec3d.zCoord)) {
            return null;
        }
        if (!(Double.isNaN(vec3d2.xCoord) || Double.isNaN(vec3d2.yCoord) || Double.isNaN(vec3d2.zCoord))) {
            RayTraceResult rayTraceResult;
            int n = MathHelper.floor((double)vec3d.xCoord);
            int n2 = MathHelper.floor((double)vec3d.yCoord);
            int n3 = MathHelper.floor((double)vec3d.zCoord);
            int n4 = MathHelper.floor((double)vec3d2.xCoord);
            int n5 = MathHelper.floor((double)vec3d2.yCoord);
            int n6 = MathHelper.floor((double)vec3d2.zCoord);
            BlockPos blockPos = new BlockPos(n, n2, n3);
            IBlockState iBlockState = BlockUtil.mc.world.getBlockState(blockPos);
            Block block = iBlockState.getBlock();
            if (!(bl2 && iBlockState.getCollisionBoundingBox((IBlockAccess)BlockUtil.mc.world, blockPos) == Block.NULL_AABB || !block.canCollideCheck(iBlockState, bl) || bl4 && block instanceof BlockWeb || (rayTraceResult = iBlockState.collisionRayTrace((World)BlockUtil.mc.world, blockPos, vec3d, vec3d2)) == null)) {
                return rayTraceResult;
            }
            RayTraceResult rayTraceResult2 = null;
            int n7 = 200;
            while (n7-- >= 0) {
                EnumFacing enumFacing;
                if (Double.isNaN(vec3d.xCoord) || Double.isNaN(vec3d.yCoord) || Double.isNaN(vec3d.zCoord)) {
                    return null;
                }
                if (n == n4 && n2 == n5 && n3 == n6) {
                    return bl3 ? rayTraceResult2 : null;
                }
                boolean bl5 = true;
                boolean bl6 = true;
                boolean bl7 = true;
                double d = 999.0;
                double d2 = 999.0;
                double d3 = 999.0;
                if (n4 > n) {
                    d = (double)n + 1.0;
                } else if (n4 < n) {
                    d = (double)n + 0.0;
                } else {
                    bl5 = false;
                }
                if (n5 > n2) {
                    d2 = (double)n2 + 1.0;
                } else if (n5 < n2) {
                    d2 = (double)n2 + 0.0;
                } else {
                    bl6 = false;
                }
                if (n6 > n3) {
                    d3 = (double)n3 + 1.0;
                } else if (n6 < n3) {
                    d3 = (double)n3 + 0.0;
                } else {
                    bl7 = false;
                }
                double d4 = 999.0;
                double d5 = 999.0;
                double d6 = 999.0;
                double d7 = vec3d2.xCoord - vec3d.xCoord;
                double d8 = vec3d2.yCoord - vec3d.yCoord;
                double d9 = vec3d2.zCoord - vec3d.zCoord;
                if (bl5) {
                    d4 = (d - vec3d.xCoord) / d7;
                }
                if (bl6) {
                    d5 = (d2 - vec3d.yCoord) / d8;
                }
                if (bl7) {
                    d6 = (d3 - vec3d.zCoord) / d9;
                }
                if (d4 == -0.0) {
                    d4 = -1.0E-4;
                }
                if (d5 == -0.0) {
                    d5 = -1.0E-4;
                }
                if (d6 == -0.0) {
                    d6 = -1.0E-4;
                }
                if (d4 < d5 && d4 < d6) {
                    enumFacing = n4 > n ? EnumFacing.WEST : EnumFacing.EAST;
                    vec3d = new Vec3d(d, vec3d.yCoord + d8 * d4, vec3d.zCoord + d9 * d4);
                } else if (d5 < d6) {
                    enumFacing = n5 > n2 ? EnumFacing.DOWN : EnumFacing.UP;
                    vec3d = new Vec3d(vec3d.xCoord + d7 * d5, d2, vec3d.zCoord + d9 * d5);
                } else {
                    enumFacing = n6 > n3 ? EnumFacing.NORTH : EnumFacing.SOUTH;
                    vec3d = new Vec3d(vec3d.xCoord + d7 * d6, vec3d.yCoord + d8 * d6, d3);
                }
                n = MathHelper.floor((double)vec3d.xCoord) - (enumFacing == EnumFacing.EAST ? 1 : 0);
                n2 = MathHelper.floor((double)vec3d.yCoord) - (enumFacing == EnumFacing.UP ? 1 : 0);
                n3 = MathHelper.floor((double)vec3d.zCoord) - (enumFacing == EnumFacing.SOUTH ? 1 : 0);
                blockPos = new BlockPos(n, n2, n3);
                IBlockState iBlockState2 = BlockUtil.mc.world.getBlockState(blockPos);
                Block block2 = iBlockState2.getBlock();
                if (bl2 && iBlockState2.getMaterial() != Material.PORTAL && iBlockState2.getCollisionBoundingBox((IBlockAccess)BlockUtil.mc.world, blockPos) == Block.NULL_AABB) continue;
                if (!(!block2.canCollideCheck(iBlockState2, bl) || bl4 && block2 instanceof BlockWeb)) {
                    RayTraceResult rayTraceResult3 = iBlockState2.collisionRayTrace((World)BlockUtil.mc.world, blockPos, vec3d, vec3d2);
                    if (rayTraceResult3 == null) continue;
                    return rayTraceResult3;
                }
                rayTraceResult2 = new RayTraceResult(RayTraceResult.Type.MISS, vec3d, enumFacing, blockPos);
            }
            return bl3 ? rayTraceResult2 : null;
        }
        return null;
    }

    public static void rotatePacket(double d, double d2, double d3) {
        double d4 = d - BlockUtil2.mc.player.posX;
        double d5 = d2 - (BlockUtil2.mc.player.posY + (double)BlockUtil2.mc.player.getEyeHeight());
        double d6 = d3 - BlockUtil2.mc.player.posZ;
        double d7 = Math.sqrt(d4 * d4 + d6 * d6);
        float f = (float)Math.toDegrees(Math.atan2(d6, d4)) - 90.0f;
        float f2 = (float)(-Math.toDegrees(Math.atan2(d5, d7)));
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(f, f2, BlockUtil2.mc.player.onGround));
    }

    public static void doBreak(BlockPos blockPos, double d) {
        if (!BlockUtil.isInterceptedByOther(blockPos)) {
            return;
        }
        if (BlockUtil.isInterceptedByCrystal(blockPos)) {
            EntityEnderCrystal entityEnderCrystal = null;
            for (Entity entity : BlockUtil.mc.world.loadedEntityList) {
                double d2 = BlockUtil.calculateDamage(entityEnderCrystal.posX, entityEnderCrystal.posY, entityEnderCrystal.posZ, (Entity)BlockUtil.mc.player);
                if (entity == null || d2 < d || (double)BlockUtil.mc.player.getDistanceToEntity(entity) > 2.4 || !(entity instanceof EntityEnderCrystal) || entity.isDead) continue;
                entityEnderCrystal = (EntityEnderCrystal)entity;
            }
            if (entityEnderCrystal != null) {
                mc.getConnection().sendPacket((Packet)new CPacketUseEntity(entityEnderCrystal));
                BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
        }
    }

    public static boolean isBlockUnSolid(BlockPos blockPos) {
        return BlockUtil.isBlockUnSolid(BlockUtil.mc.world.getBlockState(blockPos).getBlock());
    }

    public static EnumFacing getFirstFacing(BlockPos blockPos) {
        Iterator<EnumFacing> iterator2 = BlockUtil.getPossibleSides(blockPos).iterator();
        if (iterator2.hasNext()) {
            return iterator2.next();
        }
        return null;
    }

    public static boolean isBlockAboveEntitySolid(Entity entity) {
        if (entity != null) {
            BlockPos blockPos = new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ);
            return BlockUtil.isBlockSolid(blockPos);
        }
        return false;
    }

    public static boolean isValidBlock(BlockPos blockPos) {
        Block block = BlockUtil.mc.world.getBlockState(blockPos).getBlock();
        return !(block instanceof BlockLiquid) && block.getMaterial(null) != Material.AIR;
    }

    public static boolean isBedrockHole(BlockPos blockPos) {
        for (BlockPos blockPos2 : BlockUtil.getTouchingBlocks(blockPos)) {
            IBlockState iBlockState = BlockUtil.mc.world.getBlockState(blockPos2);
            if (iBlockState.getBlock() != Blocks.AIR && iBlockState.getBlock() == Blocks.BEDROCK) continue;
            return false;
        }
        return true;
    }

    public static boolean isPosValidForCrystal(BlockPos blockPos, boolean bl) {
        if (BlockUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && BlockUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
            return false;
        }
        if (BlockUtil.mc.world.getBlockState(blockPos.up()).getBlock() != Blocks.AIR || !bl && BlockUtil.mc.world.getBlockState(blockPos.up().up()).getBlock() != Blocks.AIR) {
            return false;
        }
        for (Entity entity : BlockUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos.up()))) {
            if (entity.isDead || entity instanceof EntityEnderCrystal) continue;
            return false;
        }
        for (Entity entity : BlockUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos.up().up()))) {
            if (entity.isDead || entity instanceof EntityEnderCrystal) continue;
            return false;
        }
        return true;
    }

    public static boolean canPlaceCrystal(BlockPos blockPos, boolean bl, boolean bl2) {
        BlockPos blockPos2 = blockPos.add(0, 1, 0);
        BlockPos blockPos3 = blockPos.add(0, 2, 0);
        try {
            if (BlockUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && BlockUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
            if (!bl2 && BlockUtil.mc.world.getBlockState(blockPos3).getBlock() != Blocks.AIR || BlockUtil.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR) {
                return false;
            }
            for (Entity entity : BlockUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2))) {
                if (entity.isDead || bl && entity instanceof EntityEnderCrystal) continue;
                return false;
            }
            if (!bl2) {
                for (Entity entity : BlockUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos3))) {
                    if (entity.isDead || bl && entity instanceof EntityEnderCrystal) continue;
                    return false;
                }
            }
        }
        catch (Exception exception) {
            return false;
        }
        return true;
    }

    public static boolean placeBlockLuigi(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3) {
        boolean bl4 = false;
        EnumFacing enumFacing = BlockUtil.getFirstFacing(blockPos);
        if (enumFacing == null) {
            return bl3;
        }
        BlockPos blockPos2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing2 = enumFacing.getOpposite();
        Vec3d vec3d = new Vec3d((Vec3i)blockPos2).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing2.getDirectionVec()).scale(0.5));
        Block block = BlockUtil.mc.world.getBlockState(blockPos2).getBlock();
        if (!BlockUtil.mc.player.isSneaking() && (blackList.contains((Object)block) || shulkerList.contains((Object)block))) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtil.mc.player.setSneaking(true);
            bl4 = true;
        }
        if (bl) {
            BlockUtil.rotatePacket(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
        }
        BlockUtil.rightClickBlock(blockPos2, vec3d, enumHand, enumFacing2, bl2);
        BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil.mc.rightClickDelayTimer = 4;
        return bl4 || bl3;
    }

    public static boolean isBlockUnSolid(Block block) {
        return unSolidBlocks.contains((Object)block);
    }

    public static boolean isBothHole(BlockPos blockPos) {
        for (BlockPos blockPos2 : BlockUtil.getTouchingBlocks(blockPos)) {
            IBlockState iBlockState = BlockUtil.mc.world.getBlockState(blockPos2);
            if (iBlockState.getBlock() != Blocks.AIR && (iBlockState.getBlock() == Blocks.BEDROCK || iBlockState.getBlock() == Blocks.OBSIDIAN)) continue;
            return false;
        }
        return true;
    }

    public static float calculateDamage(double d, double d2, double d3, Entity entity) {
        float f = 12.0f;
        double d4 = entity.getDistance(d, d2, d3) / (double)f;
        Vec3d vec3d = new Vec3d(d, d2, d3);
        double d5 = 0.0;
        try {
            d5 = BlockUtil.getBlockDensity(vec3d, entity.getEntityBoundingBox());
        }
        catch (Exception exception) {
            // empty catch block
        }
        double d6 = (1.0 - d4) * d5;
        float f2 = (int)((d6 * d6 + d6) / 2.0 * 7.0 * (double)f + 1.0);
        double d7 = 1.0;
        if (entity instanceof EntityLivingBase) {
            try {
                d7 = BlockUtil.getBlastReduction((EntityLivingBase)entity, BlockUtil.getDamageMultiplied(f2), new Explosion((World)BlockUtil.mc.world, null, d, d2, d3, 6.0f, false, true));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return (float)d7;
    }

    public static Vec3d[] convertVec3ds(EntityPlayer entityPlayer, Vec3d[] arrvec3d) {
        return BlockUtil.convertVec3ds(entityPlayer.getPositionVector(), arrvec3d);
    }

    public static BlockPos[] getTouchingBlocks(BlockPos blockPos) {
        return new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()};
    }

    public static boolean rayTracePlaceCheck(BlockPos blockPos, boolean bl) {
        return BlockUtil.rayTracePlaceCheck(blockPos, bl, 1.0f);
    }

    public static void debugPos(String string, BlockPos blockPos) {
        Command.sendMessage(String.valueOf(new StringBuilder().append(string).append(blockPos.getX()).append("x, ").append(blockPos.getY()).append("y, ").append(blockPos.getZ()).append("z")));
    }

    public static List<BlockPos> getDisc(BlockPos blockPos, float f) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        int n = blockPos.getX();
        int n2 = blockPos.getY();
        int n3 = blockPos.getZ();
        int n4 = n - (int)f;
        while ((float)n4 <= (float)n + f) {
            int n5 = n3 - (int)f;
            while ((float)n5 <= (float)n3 + f) {
                double d = (n - n4) * (n - n4) + (n3 - n5) * (n3 - n5);
                if (d < (double)(f * f)) {
                    BlockPos blockPos2 = new BlockPos(n4, n2, n5);
                    arrayList.add(blockPos2);
                }
                ++n5;
            }
            ++n4;
        }
        return arrayList;
    }

    public static void placeBlock(BlockPos blockPos, EnumFacing enumFacing, boolean bl) {
        BlockPos blockPos2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing2 = enumFacing.getOpposite();
        if (!Util.mc.player.isSneaking()) {
            Util.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Util.mc.player, CPacketEntityAction.Action.START_SNEAKING));
        }
        Vec3d vec3d = new Vec3d((Vec3i)blockPos2).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing2.getDirectionVec()).scale(0.5));
        if (bl) {
            Util.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, EnumHand.MAIN_HAND, (float)vec3d.xCoord - (float)blockPos.getX(), (float)vec3d.yCoord - (float)blockPos.getY(), (float)vec3d.zCoord - (float)blockPos.getZ()));
        } else {
            Util.mc.playerController.processRightClickBlock(Util.mc.player, Util.mc.world, blockPos2, enumFacing2, vec3d, EnumHand.MAIN_HAND);
        }
        Util.mc.player.swingArm(EnumHand.MAIN_HAND);
    }

    public static BlockPos vec3dToPos(Vec3d vec3d) {
        return new BlockPos(vec3d);
    }

    public static boolean GetState() {
        return _currBlock != null && BlockUtil.IsDoneBreaking(BlockUtil.mc.world.getBlockState(_currBlock));
    }

    static {
        blackList = Arrays.asList(new Block[]{Blocks.ENDER_CHEST, Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER, Blocks.TRAPDOOR, Blocks.ENCHANTING_TABLE});
        shulkerList = Arrays.asList(new Block[]{Blocks.field_190977_dl, Blocks.field_190978_dm, Blocks.field_190979_dn, Blocks.field_190980_do, Blocks.field_190981_dp, Blocks.field_190982_dq, Blocks.field_190983_dr, Blocks.field_190984_ds, Blocks.field_190985_dt, Blocks.field_190986_du, Blocks.field_190987_dv, Blocks.field_190988_dw, Blocks.field_190989_dx, Blocks.field_190990_dy, Blocks.field_190991_dz, Blocks.field_190975_dA});
        emptyBlocks = Arrays.asList(new Block[]{Blocks.AIR, Blocks.FLOWING_LAVA, Blocks.LAVA, Blocks.FLOWING_WATER, Blocks.WATER, Blocks.VINE, Blocks.SNOW_LAYER, Blocks.TALLGRASS, Blocks.FIRE});
        rightclickableBlocks = Arrays.asList(new Block[]{Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.field_190977_dl, Blocks.field_190978_dm, Blocks.field_190979_dn, Blocks.field_190980_do, Blocks.field_190981_dp, Blocks.field_190982_dq, Blocks.field_190983_dr, Blocks.field_190984_ds, Blocks.field_190985_dt, Blocks.field_190986_du, Blocks.field_190987_dv, Blocks.field_190988_dw, Blocks.field_190989_dx, Blocks.field_190990_dy, Blocks.field_190991_dz, Blocks.field_190975_dA, Blocks.ANVIL, Blocks.WOODEN_BUTTON, Blocks.STONE_BUTTON, Blocks.UNPOWERED_COMPARATOR, Blocks.UNPOWERED_REPEATER, Blocks.POWERED_REPEATER, Blocks.POWERED_COMPARATOR, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.ACACIA_FENCE_GATE, Blocks.BREWING_STAND, Blocks.DISPENSER, Blocks.DROPPER, Blocks.LEVER, Blocks.NOTEBLOCK, Blocks.JUKEBOX, Blocks.BEACON, Blocks.BED, Blocks.FURNACE, Blocks.OAK_DOOR, Blocks.SPRUCE_DOOR, Blocks.BIRCH_DOOR, Blocks.JUNGLE_DOOR, Blocks.ACACIA_DOOR, Blocks.DARK_OAK_DOOR, Blocks.CAKE, Blocks.ENCHANTING_TABLE, Blocks.DRAGON_EGG, Blocks.HOPPER, Blocks.REPEATING_COMMAND_BLOCK, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.CRAFTING_TABLE});
        unSolidBlocks = Arrays.asList(new Block[]{Blocks.FLOWING_LAVA, Blocks.FLOWER_POT, Blocks.SNOW, Blocks.CARPET, Blocks.END_ROD, Blocks.SKULL, Blocks.FLOWER_POT, Blocks.TRIPWIRE, Blocks.TRIPWIRE_HOOK, Blocks.WOODEN_BUTTON, Blocks.LEVER, Blocks.STONE_BUTTON, Blocks.LADDER, Blocks.UNPOWERED_COMPARATOR, Blocks.POWERED_COMPARATOR, Blocks.UNPOWERED_REPEATER, Blocks.POWERED_REPEATER, Blocks.UNLIT_REDSTONE_TORCH, Blocks.REDSTONE_TORCH, Blocks.REDSTONE_WIRE, Blocks.AIR, Blocks.PORTAL, Blocks.END_PORTAL, Blocks.WATER, Blocks.FLOWING_WATER, Blocks.LAVA, Blocks.FLOWING_LAVA, Blocks.SAPLING, Blocks.RED_FLOWER, Blocks.YELLOW_FLOWER, Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM, Blocks.WHEAT, Blocks.CARROTS, Blocks.POTATOES, Blocks.BEETROOTS, Blocks.REEDS, Blocks.PUMPKIN_STEM, Blocks.MELON_STEM, Blocks.WATERLILY, Blocks.NETHER_WART, Blocks.COCOA, Blocks.CHORUS_FLOWER, Blocks.CHORUS_PLANT, Blocks.TALLGRASS, Blocks.DEADBUSH, Blocks.VINE, Blocks.FIRE, Blocks.RAIL, Blocks.ACTIVATOR_RAIL, Blocks.DETECTOR_RAIL, Blocks.GOLDEN_RAIL, Blocks.TORCH});
    }

    private static IBlockState getState(BlockPos blockPos) {
        return BlockUtil.mc.world.getBlockState(blockPos);
    }

    public static void placeBlockStopSneaking(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3) {
        boolean bl4 = BlockUtil.placeBlockSmartRotate(blockPos, enumHand, bl, bl2, bl3);
        if (!bl3 && bl4) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
    }

    public static boolean isObbyHole(BlockPos blockPos) {
        for (BlockPos blockPos2 : BlockUtil.getTouchingBlocks(blockPos)) {
            IBlockState iBlockState = BlockUtil.mc.world.getBlockState(blockPos2);
            if (iBlockState.getBlock() != Blocks.AIR && iBlockState.getBlock() == Blocks.OBSIDIAN) continue;
            return false;
        }
        return true;
    }

    public static Vec3d[] getHelpingBlocks(Vec3d vec3d) {
        return new Vec3d[]{new Vec3d(vec3d.xCoord, vec3d.yCoord - 1.0, vec3d.zCoord), new Vec3d(vec3d.xCoord != 0.0 ? vec3d.xCoord * 2.0 : vec3d.xCoord, vec3d.yCoord, vec3d.xCoord != 0.0 ? vec3d.zCoord : vec3d.zCoord * 2.0), new Vec3d(vec3d.xCoord == 0.0 ? vec3d.xCoord + 1.0 : vec3d.xCoord, vec3d.yCoord, vec3d.xCoord == 0.0 ? vec3d.zCoord : vec3d.zCoord + 1.0), new Vec3d(vec3d.xCoord == 0.0 ? vec3d.xCoord - 1.0 : vec3d.xCoord, vec3d.yCoord, vec3d.xCoord == 0.0 ? vec3d.zCoord : vec3d.zCoord - 1.0), new Vec3d(vec3d.xCoord, vec3d.yCoord + 1.0, vec3d.zCoord)};
    }

    public static boolean isElseHole(BlockPos blockPos) {
        for (BlockPos blockPos2 : BlockUtil.getTouchingBlocks(blockPos)) {
            IBlockState iBlockState = BlockUtil.mc.world.getBlockState(blockPos2);
            if (iBlockState.getBlock() != Blocks.AIR && iBlockState.isFullBlock()) continue;
            return false;
        }
        return true;
    }

    public static boolean isBlockEmpty(BlockPos blockPos) {
        try {
            if (emptyBlocks.contains((Object)BlockUtil.mc.world.getBlockState(blockPos).getBlock())) {
                AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos);
                for (Entity entity : BlockUtil.mc.world.loadedEntityList) {
                    if (!(entity instanceof EntityLivingBase) || !axisAlignedBB.intersectsWith(entity.getEntityBoundingBox())) continue;
                    return false;
                }
                return true;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return false;
    }

    public static boolean isBlockValid(IBlockState iBlockState, BlockPos blockPos) {
        if (iBlockState.getBlock() != Blocks.AIR) {
            return false;
        }
        if (BlockUtil.mc.player.getDistanceSq(blockPos) < 1.0) {
            return false;
        }
        if (BlockUtil.mc.world.getBlockState(blockPos.up()).getBlock() != Blocks.AIR) {
            return false;
        }
        if (BlockUtil.mc.world.getBlockState(blockPos.up(2)).getBlock() != Blocks.AIR) {
            return false;
        }
        return BlockUtil.isBedrockHole(blockPos) || BlockUtil.isObbyHole(blockPos) || BlockUtil.isBothHole(blockPos) || BlockUtil.isElseHole(blockPos);
    }

    public static int isPositionPlaceable(BlockPos blockPos, boolean bl) {
        return BlockUtil.isPositionPlaceable(blockPos, bl, true);
    }

    public static EnumFacing getRayTraceFacing(BlockPos blockPos) {
        RayTraceResult rayTraceResult = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + (double)BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d((double)blockPos.getX() + 0.5, (double)blockPos.getX() - 0.5, (double)blockPos.getX() + 0.5));
        if (rayTraceResult == null || rayTraceResult.sideHit == null) {
            return EnumFacing.UP;
        }
        return rayTraceResult.sideHit;
    }

    public static boolean isInterceptedByCrystal(BlockPos blockPos) {
        for (Entity entity : BlockUtil.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityEnderCrystal) || entity == BlockUtil.mc.player || entity instanceof EntityItem || !new AxisAlignedBB(blockPos).intersectsWith(entity.getEntityBoundingBox())) continue;
            return true;
        }
        return false;
    }

    public static boolean placeBlockNotRetarded(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3) {
        EnumFacing enumFacing = BlockUtil.getFirstFacing(blockPos);
        if (enumFacing == null) {
            return false;
        }
        BlockPos blockPos2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing2 = enumFacing.getOpposite();
        Vec3d vec3d = new Vec3d((Vec3i)blockPos2).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing2.getDirectionVec()).scale(0.5));
        Block block = BlockUtil.mc.world.getBlockState(blockPos2).getBlock();
        if (!BlockUtil.mc.player.isSneaking() && (blackList.contains((Object)block) || shulkerList.contains((Object)block))) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtil.mc.player.setSneaking(true);
        }
        if (bl) {
            RotationUtil.faceVector(bl3 ? new Vec3d((Vec3i)blockPos) : vec3d, true);
        }
        BlockUtil.rightClickBlock(blockPos2, vec3d, enumHand, enumFacing2, bl2);
        BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        return true;
    }

    public static double getNearestBlockBelow() {
        for (double d = BlockUtil.mc.player.posY; d > 0.0; d -= 0.001) {
            if (BlockUtil.mc.world.getBlockState(new BlockPos(BlockUtil.mc.player.posX, d, BlockUtil.mc.player.posZ)).getBlock() instanceof BlockSlab || BlockUtil.mc.world.getBlockState(new BlockPos(BlockUtil.mc.player.posX, d, BlockUtil.mc.player.posZ)).getBlock().getDefaultState().getCollisionBoundingBox((IBlockAccess)BlockUtil.mc.world, new BlockPos(0, 0, 0)) == null) continue;
            return d;
        }
        return -1.0;
    }

    public static enum AirType {
        OnlyAir,
        IgnoreAir,
        None;

    }
}

