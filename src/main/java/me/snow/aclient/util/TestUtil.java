//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 */
package me.snow.aclient.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class TestUtil {
    private static final /* synthetic */ Minecraft mc;
    public static /* synthetic */ List<Block> emptyBlocks;
    public static /* synthetic */ List<Block> rightclickableBlocks;

    public static void placeCrystalOnBlock(BlockPos blockPos, EnumHand enumHand) {
        RayTraceResult rayTraceResult = TestUtil.mc.world.rayTraceBlocks(new Vec3d(TestUtil.mc.player.posX, TestUtil.mc.player.posY + (double)TestUtil.mc.player.getEyeHeight(), TestUtil.mc.player.posZ), new Vec3d((double)blockPos.getX() + 0.5, (double)blockPos.getY() - 0.5, (double)blockPos.getZ() + 0.5));
        EnumFacing enumFacing = rayTraceResult == null || rayTraceResult.sideHit == null ? EnumFacing.UP : rayTraceResult.sideHit;
        TestUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, 0.0f, 0.0f, 0.0f));
    }

    public static boolean canPlaceBlock(BlockPos blockPos) {
        if (TestUtil.isBlockEmpty(blockPos)) {
            EnumFacing[] arrenumFacing;
            for (EnumFacing enumFacing : arrenumFacing = EnumFacing.values()) {
                if (emptyBlocks.contains((Object)TestUtil.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock())) continue;
                Vec3d vec3d = new Vec3d((double)blockPos.getX() + 0.5 + (double)enumFacing.getFrontOffsetX() * 0.5, (double)blockPos.getY() + 0.5 + (double)enumFacing.getFrontOffsetY() * 0.5, (double)blockPos.getZ() + 0.5 + (double)enumFacing.getFrontOffsetZ() * 0.5);
                if (!(TestUtil.mc.player.getPositionEyes(mc.getRenderPartialTicks()).distanceTo(vec3d) <= 4.25)) continue;
                return true;
            }
        }
        return false;
    }

    public static void openBlock(BlockPos blockPos) {
        EnumFacing[] arrenumFacing;
        for (EnumFacing enumFacing : arrenumFacing = EnumFacing.values()) {
            Block block = TestUtil.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock();
            if (!emptyBlocks.contains((Object)block)) continue;
            TestUtil.mc.playerController.processRightClickBlock(TestUtil.mc.player, TestUtil.mc.world, blockPos, enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
            return;
        }
    }

    public static boolean isBlockEmpty(BlockPos blockPos) {
        try {
            if (emptyBlocks.contains((Object)TestUtil.mc.world.getBlockState(blockPos).getBlock())) {
                Entity entity;
                AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos);
                Iterator iterator2 = TestUtil.mc.world.loadedEntityList.iterator();
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

    public static boolean rayTracePlaceCheck(BlockPos blockPos, boolean bl, float f) {
        return !bl || TestUtil.mc.world.rayTraceBlocks(new Vec3d(TestUtil.mc.player.posX, TestUtil.mc.player.posY + (double)TestUtil.mc.player.getEyeHeight(), TestUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)((float)blockPos.getY() + f), (double)blockPos.getZ()), false, true, false) == null;
    }

    public static boolean canSeeBlock(BlockPos blockPos) {
        return TestUtil.mc.player != null && TestUtil.mc.world.rayTraceBlocks(new Vec3d(TestUtil.mc.player.posX, TestUtil.mc.player.posY + (double)TestUtil.mc.player.getEyeHeight(), TestUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()), false, true, false) == null;
    }

    static {
        mc = Minecraft.getMinecraft();
        emptyBlocks = Arrays.asList(new Block[]{Blocks.AIR, Blocks.FLOWING_LAVA, Blocks.LAVA, Blocks.FLOWING_WATER, Blocks.WATER, Blocks.VINE, Blocks.SNOW_LAYER, Blocks.TALLGRASS, Blocks.FIRE});
        rightclickableBlocks = Arrays.asList(new Block[]{Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.field_190977_dl, Blocks.field_190978_dm, Blocks.field_190979_dn, Blocks.field_190980_do, Blocks.field_190981_dp, Blocks.field_190982_dq, Blocks.field_190983_dr, Blocks.field_190984_ds, Blocks.field_190985_dt, Blocks.field_190986_du, Blocks.field_190987_dv, Blocks.field_190988_dw, Blocks.field_190989_dx, Blocks.field_190990_dy, Blocks.field_190991_dz, Blocks.field_190975_dA, Blocks.ANVIL, Blocks.WOODEN_BUTTON, Blocks.STONE_BUTTON, Blocks.UNPOWERED_COMPARATOR, Blocks.UNPOWERED_REPEATER, Blocks.POWERED_REPEATER, Blocks.POWERED_COMPARATOR, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.ACACIA_FENCE_GATE, Blocks.BREWING_STAND, Blocks.DISPENSER, Blocks.DROPPER, Blocks.LEVER, Blocks.NOTEBLOCK, Blocks.JUKEBOX, Blocks.BEACON, Blocks.BED, Blocks.FURNACE, Blocks.OAK_DOOR, Blocks.SPRUCE_DOOR, Blocks.BIRCH_DOOR, Blocks.JUNGLE_DOOR, Blocks.ACACIA_DOOR, Blocks.DARK_OAK_DOOR, Blocks.CAKE, Blocks.ENCHANTING_TABLE, Blocks.DRAGON_EGG, Blocks.HOPPER, Blocks.REPEATING_COMMAND_BLOCK, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.CRAFTING_TABLE});
    }

    public static boolean rayTracePlaceCheck(BlockPos blockPos, boolean bl) {
        return TestUtil.rayTracePlaceCheck(blockPos, bl, 1.0f);
    }

    public static boolean placeBlock(BlockPos blockPos) {
        if (TestUtil.isBlockEmpty(blockPos)) {
            EnumFacing[] arrenumFacing;
            for (EnumFacing enumFacing : arrenumFacing = EnumFacing.values()) {
                Block block = TestUtil.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock();
                Vec3d vec3d = new Vec3d((double)blockPos.getX() + 0.5 + (double)enumFacing.getFrontOffsetX() * 0.5, (double)blockPos.getY() + 0.5 + (double)enumFacing.getFrontOffsetY() * 0.5, (double)blockPos.getZ() + 0.5 + (double)enumFacing.getFrontOffsetZ() * 0.5);
                if (emptyBlocks.contains((Object)block) || !(TestUtil.mc.player.getPositionEyes(mc.getRenderPartialTicks()).distanceTo(vec3d) <= 4.25)) continue;
                float[] arrf = new float[]{TestUtil.mc.player.rotationYaw, TestUtil.mc.player.rotationPitch};
                if (rightclickableBlocks.contains((Object)block)) {
                    TestUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)TestUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                }
                TestUtil.mc.playerController.processRightClickBlock(TestUtil.mc.player, TestUtil.mc.world, blockPos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                if (rightclickableBlocks.contains((Object)block)) {
                    TestUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)TestUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                }
                TestUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
                return true;
            }
        }
        return false;
    }
}

