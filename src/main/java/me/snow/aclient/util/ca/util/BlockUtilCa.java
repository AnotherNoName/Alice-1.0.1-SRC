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
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.World
 */
package me.snow.aclient.util.ca.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import me.snow.aclient.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class BlockUtilCa
implements Util {
    public static /* synthetic */ List<Block> emptyBlocks;
    public static /* synthetic */ List<Block> rightclickableBlocks;

    public static boolean isScaffoldPos(BlockPos blockPos) {
        return BlockUtilCa.mc.world.isAirBlock(blockPos) || BlockUtilCa.mc.world.getBlockState(blockPos).getBlock() == Blocks.SNOW_LAYER || BlockUtilCa.mc.world.getBlockState(blockPos).getBlock() == Blocks.TALLGRASS || BlockUtilCa.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid;
    }

    public static int isPositionPlaceable(BlockPos blockPos, boolean bl) {
        return BlockUtilCa.isPositionPlaceable(blockPos, bl, true);
    }

    public static boolean isBlockEmpty(BlockPos blockPos) {
        try {
            if (emptyBlocks.contains((Object)BlockUtilCa.mc.world.getBlockState(blockPos).getBlock())) {
                Entity entity;
                AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos);
                Iterator iterator2 = BlockUtilCa.mc.world.loadedEntityList.iterator();
                do {
                    if (iterator2.hasNext()) continue;
                    return true;
                } while (!((entity = (Entity)iterator2.next()) instanceof EntityLivingBase) || !axisAlignedBB.intersectsWith(entity.getEntityBoundingBox()));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return false;
    }

    public static boolean placeBlock(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3) {
        EnumFacing enumFacing = BlockUtilCa.getFirstFacing(blockPos);
        if (enumFacing == null) {
            return bl3;
        }
        BlockPos blockPos2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing2 = enumFacing.getOpposite();
        Vec3d vec3d = new Vec3d((Vec3i)blockPos2).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing2.getDirectionVec()).scale(0.5));
        if (!BlockUtilCa.mc.player.isSneaking()) {
            BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtilCa.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtilCa.mc.player.setSneaking(true);
        }
        if (bl) {
            BlockUtilCa.faceVector(vec3d, true);
        }
        BlockUtilCa.rightClickBlock(blockPos2, vec3d, enumHand, enumFacing2, bl2);
        BlockUtilCa.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtilCa.mc.rightClickDelayTimer = 4;
        return true;
    }

    public static void placeCrystalOnBlock(BlockPos blockPos, EnumHand enumHand, boolean bl) {
        RayTraceResult rayTraceResult = BlockUtilCa.mc.world.rayTraceBlocks(new Vec3d(BlockUtilCa.mc.player.posX, BlockUtilCa.mc.player.posY + (double)BlockUtilCa.mc.player.getEyeHeight(), BlockUtilCa.mc.player.posZ), new Vec3d((double)blockPos.getX() + 0.5, (double)blockPos.getY() - 0.5, (double)blockPos.getZ() + 0.5));
        EnumFacing enumFacing = rayTraceResult == null || rayTraceResult.sideHit == null ? EnumFacing.UP : rayTraceResult.sideHit;
        BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, 0.0f, 0.0f, 0.0f));
        if (bl) {
            BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketAnimation(enumHand));
        }
    }

    public static boolean canBreak(BlockPos blockPos) {
        IBlockState iBlockState = BlockUtilCa.mc.world.getBlockState(blockPos);
        Block block = iBlockState.getBlock();
        return block.getBlockHardness(iBlockState, (World)BlockUtilCa.mc.world, blockPos) != -1.0f;
    }

    public static int isPositionPlaceable(BlockPos blockPos, boolean bl, boolean bl2) {
        Block block = BlockUtilCa.mc.world.getBlockState(blockPos).getBlock();
        if (!(block instanceof BlockAir || block instanceof BlockLiquid || block instanceof BlockTallGrass || block instanceof BlockFire || block instanceof BlockDeadBush || block instanceof BlockSnow)) {
            return 0;
        }
        if (!BlockUtilCa.rayTracePlaceCheck(blockPos, bl, 0.0f)) {
            return -1;
        }
        if (bl2) {
            for (EnumFacing enumFacing : BlockUtilCa.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos))) {
                if (enumFacing instanceof EntityItem || enumFacing instanceof EntityXPOrb) continue;
                return 1;
            }
        }
        for (EnumFacing enumFacing : BlockUtilCa.getPossibleSides(blockPos)) {
            if (!BlockUtilCa.canBeClicked(blockPos.offset(enumFacing))) continue;
            return 3;
        }
        return 2;
    }

    private static IBlockState getState(BlockPos blockPos) {
        return BlockUtilCa.mc.world.getBlockState(blockPos);
    }

    public static boolean canBeClicked(BlockPos blockPos) {
        return BlockUtilCa.getBlock(blockPos).canCollideCheck(BlockUtilCa.getState(blockPos), false);
    }

    private static boolean hasNeighbour(BlockPos blockPos) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            BlockPos blockPos2 = blockPos.offset(enumFacing);
            if (BlockUtilCa.mc.world.getBlockState(blockPos2).getMaterial().isReplaceable()) continue;
            return true;
        }
        return false;
    }

    public static Vec3d[] getHelpingBlocks(Vec3d vec3d) {
        return new Vec3d[]{new Vec3d(vec3d.xCoord, vec3d.yCoord - 1.0, vec3d.zCoord), new Vec3d(vec3d.xCoord != 0.0 ? vec3d.xCoord * 2.0 : vec3d.xCoord, vec3d.yCoord, vec3d.xCoord != 0.0 ? vec3d.zCoord : vec3d.zCoord * 2.0), new Vec3d(vec3d.xCoord == 0.0 ? vec3d.xCoord + 1.0 : vec3d.xCoord, vec3d.yCoord, vec3d.xCoord == 0.0 ? vec3d.zCoord : vec3d.zCoord + 1.0), new Vec3d(vec3d.xCoord == 0.0 ? vec3d.xCoord - 1.0 : vec3d.xCoord, vec3d.yCoord, vec3d.xCoord == 0.0 ? vec3d.zCoord : vec3d.zCoord - 1.0), new Vec3d(vec3d.xCoord, vec3d.yCoord + 1.0, vec3d.zCoord)};
    }

    public static boolean checkForNeighbours(BlockPos blockPos) {
        if (!BlockUtilCa.hasNeighbour(blockPos)) {
            for (EnumFacing enumFacing : EnumFacing.values()) {
                BlockPos blockPos2 = blockPos.offset(enumFacing);
                if (!BlockUtilCa.hasNeighbour(blockPos2)) continue;
                return true;
            }
            return false;
        }
        return true;
    }

    public static boolean placeBlock(BlockPos blockPos, int n, boolean bl, boolean bl2) {
        if (BlockUtilCa.isBlockEmpty(blockPos)) {
            EnumFacing[] arrenumFacing;
            int n2 = -1;
            if (n != BlockUtilCa.mc.player.inventory.currentItem) {
                n2 = BlockUtilCa.mc.player.inventory.currentItem;
                BlockUtilCa.mc.player.inventory.currentItem = n;
            }
            for (EnumFacing enumFacing : arrenumFacing = EnumFacing.values()) {
                Block block = BlockUtilCa.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock();
                Vec3d vec3d = new Vec3d((double)blockPos.getX() + 0.5 + (double)enumFacing.getFrontOffsetX() * 0.5, (double)blockPos.getY() + 0.5 + (double)enumFacing.getFrontOffsetY() * 0.5, (double)blockPos.getZ() + 0.5 + (double)enumFacing.getFrontOffsetZ() * 0.5);
                if (emptyBlocks.contains((Object)block) || !(BlockUtilCa.mc.player.getPositionEyes(mc.getRenderPartialTicks()).distanceTo(vec3d) <= 4.25)) continue;
                float[] arrf = new float[]{BlockUtilCa.mc.player.rotationYaw, BlockUtilCa.mc.player.rotationPitch};
                if (bl) {
                    BlockUtilCa.rotatePacket(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
                }
                if (rightclickableBlocks.contains((Object)block)) {
                    BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtilCa.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                }
                BlockUtilCa.mc.playerController.processRightClickBlock(BlockUtilCa.mc.player, BlockUtilCa.mc.world, blockPos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                if (rightclickableBlocks.contains((Object)block)) {
                    BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtilCa.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                }
                if (bl2) {
                    BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(arrf[0], arrf[1], BlockUtilCa.mc.player.onGround));
                }
                if (n2 != -1) {
                    BlockUtilCa.mc.player.inventory.currentItem = n2;
                }
                return true;
            }
            if (n2 != -1) {
                BlockUtilCa.mc.player.inventory.currentItem = n2;
            }
        }
        return false;
    }

    public static boolean isValidBlock(BlockPos blockPos) {
        Block block = BlockUtilCa.mc.world.getBlockState(blockPos).getBlock();
        return !(block instanceof BlockLiquid) && block.getMaterial(null) != Material.AIR;
    }

    private static Block getBlock(BlockPos blockPos) {
        return BlockUtilCa.getState(blockPos).getBlock();
    }

    public static Vec3d getEyesPos() {
        return new Vec3d(BlockUtilCa.mc.player.posX, BlockUtilCa.mc.player.posY + (double)BlockUtilCa.mc.player.getEyeHeight(), BlockUtilCa.mc.player.posZ);
    }

    public static List<EnumFacing> getPossibleSides(BlockPos blockPos) {
        ArrayList<EnumFacing> arrayList = new ArrayList<EnumFacing>();
        for (EnumFacing enumFacing : EnumFacing.values()) {
            IBlockState iBlockState;
            BlockPos blockPos2 = blockPos.offset(enumFacing);
            if (BlockUtilCa.mc.world.getBlockState(blockPos2) == null) {
                return arrayList;
            }
            if (BlockUtilCa.mc.world.getBlockState(blockPos2).getBlock() == null) {
                return arrayList;
            }
            if (!BlockUtilCa.mc.world.getBlockState(blockPos2).getBlock().canCollideCheck(BlockUtilCa.mc.world.getBlockState(blockPos2), false) || (iBlockState = BlockUtilCa.mc.world.getBlockState(blockPos2)).getMaterial().isReplaceable()) continue;
            arrayList.add(enumFacing);
        }
        return arrayList;
    }

    public static EnumFacing getPlaceableSide(BlockPos blockPos) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            IBlockState iBlockState;
            BlockPos blockPos2 = blockPos.offset(enumFacing);
            if (!BlockUtilCa.mc.world.getBlockState(blockPos2).getBlock().canCollideCheck(BlockUtilCa.mc.world.getBlockState(blockPos2), false) || (iBlockState = BlockUtilCa.mc.world.getBlockState(blockPos2)).getMaterial().isReplaceable()) continue;
            return enumFacing;
        }
        return null;
    }

    public static void rotatePacket(double d, double d2, double d3) {
        double d4 = d - BlockUtilCa.mc.player.posX;
        double d5 = d2 - (BlockUtilCa.mc.player.posY + (double)BlockUtilCa.mc.player.getEyeHeight());
        double d6 = d3 - BlockUtilCa.mc.player.posZ;
        double d7 = Math.sqrt(d4 * d4 + d6 * d6);
        float f = (float)Math.toDegrees(Math.atan2(d6, d4)) - 90.0f;
        float f2 = (float)(-Math.toDegrees(Math.atan2(d5, d7)));
        BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(f, f2, BlockUtilCa.mc.player.onGround));
    }

    public static boolean rayTracePlaceCheck(BlockPos blockPos, boolean bl, float f) {
        return !bl || BlockUtilCa.mc.world.rayTraceBlocks(new Vec3d(BlockUtilCa.mc.player.posX, BlockUtilCa.mc.player.posY + (double)BlockUtilCa.mc.player.getEyeHeight(), BlockUtilCa.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)((float)blockPos.getY() + f), (double)blockPos.getZ()), false, true, false) == null;
    }

    public static void faceVector(Vec3d vec3d, boolean bl) {
        float[] arrf = BlockUtilCa.getLegitRotations(vec3d);
        BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(arrf[0], bl ? (float)MathHelper.normalizeAngle((int)((int)arrf[1]), (int)360) : arrf[1], BlockUtilCa.mc.player.onGround));
    }

    public static void rightClickBlock(BlockPos blockPos, Vec3d vec3d, EnumHand enumHand, EnumFacing enumFacing, boolean bl) {
        if (bl) {
            float f = (float)(vec3d.xCoord - (double)blockPos.getX());
            float f2 = (float)(vec3d.yCoord - (double)blockPos.getY());
            float f3 = (float)(vec3d.zCoord - (double)blockPos.getZ());
            BlockUtilCa.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, f, f2, f3));
        } else {
            BlockUtilCa.mc.playerController.processRightClickBlock(BlockUtilCa.mc.player, BlockUtilCa.mc.world, blockPos, enumFacing, vec3d, enumHand);
        }
    }

    public static EnumFacing getFirstFacing(BlockPos blockPos) {
        Iterator<EnumFacing> iterator2 = BlockUtilCa.getPossibleSides(blockPos).iterator();
        if (iterator2.hasNext()) {
            EnumFacing enumFacing = iterator2.next();
            return enumFacing;
        }
        return null;
    }

    public static boolean canPlaceBlock(BlockPos blockPos) {
        if (BlockUtilCa.isBlockEmpty(blockPos)) {
            EnumFacing[] arrenumFacing;
            for (EnumFacing enumFacing : arrenumFacing = EnumFacing.values()) {
                if (emptyBlocks.contains((Object)BlockUtilCa.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock())) continue;
                Vec3d vec3d = new Vec3d((double)blockPos.getX() + 0.5 + (double)enumFacing.getFrontOffsetX() * 0.5, (double)blockPos.getY() + 0.5 + (double)enumFacing.getFrontOffsetY() * 0.5, (double)blockPos.getZ() + 0.5 + (double)enumFacing.getFrontOffsetZ() * 0.5);
                if (!(BlockUtilCa.mc.player.getPositionEyes(mc.getRenderPartialTicks()).distanceTo(vec3d) <= 4.25)) continue;
                return true;
            }
        }
        return false;
    }

    public static float[] getLegitRotations(Vec3d vec3d) {
        Vec3d vec3d2 = BlockUtilCa.getEyesPos();
        double d = vec3d.xCoord - vec3d2.xCoord;
        double d2 = vec3d.yCoord - vec3d2.yCoord;
        double d3 = vec3d.zCoord - vec3d2.zCoord;
        double d4 = Math.sqrt(d * d + d3 * d3);
        float f = (float)Math.toDegrees(Math.atan2(d3, d)) - 90.0f;
        float f2 = (float)(-Math.toDegrees(Math.atan2(d2, d4)));
        return new float[]{BlockUtilCa.mc.player.rotationYaw + MathHelper.wrapDegrees((float)(f - BlockUtilCa.mc.player.rotationYaw)), BlockUtilCa.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(f2 - BlockUtilCa.mc.player.rotationPitch))};
    }

    public static boolean intersectsWithEntity(BlockPos blockPos) {
        for (Entity entity : BlockUtilCa.mc.world.loadedEntityList) {
            if (entity.equals((Object)BlockUtilCa.mc.player) || entity instanceof EntityItem || !new AxisAlignedBB(blockPos).intersectsWith(entity.getEntityBoundingBox())) continue;
            return true;
        }
        return false;
    }

    public static ValidResult valid(BlockPos blockPos) {
        if (!BlockUtilCa.mc.world.checkNoEntityCollision(new AxisAlignedBB(blockPos))) {
            return ValidResult.NoEntityCollision;
        }
        if (!BlockUtilCa.checkForNeighbours(blockPos)) {
            return ValidResult.NoNeighbors;
        }
        IBlockState iBlockState = BlockUtilCa.mc.world.getBlockState(blockPos);
        if (iBlockState.getBlock() == Blocks.AIR) {
            BlockPos[] arrblockPos;
            for (BlockPos blockPos2 : arrblockPos = new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.up(), blockPos.down()}) {
                IBlockState iBlockState2 = BlockUtilCa.mc.world.getBlockState(blockPos2);
                if (iBlockState2.getBlock() == Blocks.AIR) continue;
                for (EnumFacing enumFacing : EnumFacing.values()) {
                    BlockPos blockPos3 = blockPos.offset(enumFacing);
                    if (!BlockUtilCa.mc.world.getBlockState(blockPos3).getBlock().canCollideCheck(BlockUtilCa.mc.world.getBlockState(blockPos3), false)) continue;
                    return ValidResult.Ok;
                }
            }
            return ValidResult.NoNeighbors;
        }
        return ValidResult.AlreadyBlockThere;
    }

    static {
        emptyBlocks = Arrays.asList(new Block[]{Blocks.AIR, Blocks.FLOWING_LAVA, Blocks.LAVA, Blocks.FLOWING_WATER, Blocks.WATER, Blocks.VINE, Blocks.SNOW_LAYER, Blocks.TALLGRASS, Blocks.FIRE});
        rightclickableBlocks = Arrays.asList(new Block[]{Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.field_190977_dl, Blocks.field_190978_dm, Blocks.field_190979_dn, Blocks.field_190980_do, Blocks.field_190981_dp, Blocks.field_190982_dq, Blocks.field_190983_dr, Blocks.field_190984_ds, Blocks.field_190985_dt, Blocks.field_190986_du, Blocks.field_190987_dv, Blocks.field_190988_dw, Blocks.field_190989_dx, Blocks.field_190990_dy, Blocks.field_190991_dz, Blocks.field_190975_dA, Blocks.ANVIL, Blocks.WOODEN_BUTTON, Blocks.STONE_BUTTON, Blocks.UNPOWERED_COMPARATOR, Blocks.UNPOWERED_REPEATER, Blocks.POWERED_REPEATER, Blocks.POWERED_COMPARATOR, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.ACACIA_FENCE_GATE, Blocks.BREWING_STAND, Blocks.DISPENSER, Blocks.DROPPER, Blocks.LEVER, Blocks.NOTEBLOCK, Blocks.JUKEBOX, Blocks.BEACON, Blocks.BED, Blocks.FURNACE, Blocks.OAK_DOOR, Blocks.SPRUCE_DOOR, Blocks.BIRCH_DOOR, Blocks.JUNGLE_DOOR, Blocks.ACACIA_DOOR, Blocks.DARK_OAK_DOOR, Blocks.CAKE, Blocks.ENCHANTING_TABLE, Blocks.DRAGON_EGG, Blocks.HOPPER, Blocks.REPEATING_COMMAND_BLOCK, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.CRAFTING_TABLE});
    }

    public static List<BlockPos> getCircle(BlockPos blockPos, int n, float f, boolean bl) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        int n2 = blockPos.getX();
        int n3 = blockPos.getZ();
        int n4 = n2 - (int)f;
        while ((float)n4 <= (float)n2 + f) {
            int n5 = n3 - (int)f;
            while ((float)n5 <= (float)n3 + f) {
                double d = (n2 - n4) * (n2 - n4) + (n3 - n5) * (n3 - n5);
                if (d < (double)(f * f) && (!bl || d >= (double)((f - 1.0f) * (f - 1.0f)))) {
                    BlockPos blockPos2 = new BlockPos(n4, n, n5);
                    arrayList.add(blockPos2);
                }
                ++n5;
            }
            ++n4;
        }
        return arrayList;
    }

    public static enum ValidResult {
        NoEntityCollision,
        AlreadyBlockThere,
        NoNeighbors,
        Ok;

    }
}

