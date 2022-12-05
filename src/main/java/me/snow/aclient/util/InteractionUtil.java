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
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
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
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 */
package me.snow.aclient.util;

import java.util.ArrayList;
import java.util.List;
import me.snow.aclient.manager.RotationManager;
import me.snow.aclient.mixin.mixins.accessors.IEntityPlayerSP;
import me.snow.aclient.util.ca.sc.BlockUtilSC;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
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
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class InteractionUtil {
    private static /* synthetic */ Minecraft mc;

    public static ArrayList<EnumFacing> checkAxis(double d, EnumFacing enumFacing, EnumFacing enumFacing2, boolean bl) {
        ArrayList<EnumFacing> arrayList = new ArrayList<EnumFacing>();
        if (d < -0.5) {
            arrayList.add(enumFacing);
        }
        if (d > 0.5) {
            arrayList.add(enumFacing2);
        }
        if (bl) {
            if (!arrayList.contains((Object)enumFacing)) {
                arrayList.add(enumFacing);
            }
            if (!arrayList.contains((Object)enumFacing2)) {
                arrayList.add(enumFacing2);
            }
        }
        return arrayList;
    }

    public static boolean canClick(BlockPos blockPos) {
        return InteractionUtil.mc.world.getBlockState(blockPos).getBlock().canCollideCheck(InteractionUtil.mc.world.getBlockState(blockPos), false);
    }

    public static boolean canPlaceNormally() {
        return true;
    }

    public static void rightClickBlock(BlockPos blockPos, Vec3d vec3d, EnumHand enumHand, EnumFacing enumFacing, boolean bl, boolean bl2) {
        if (bl) {
            float f = (float)(vec3d.xCoord - (double)blockPos.getX());
            float f2 = (float)(vec3d.yCoord - (double)blockPos.getY());
            float f3 = (float)(vec3d.zCoord - (double)blockPos.getZ());
            InteractionUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, f, f2, f3));
        } else {
            InteractionUtil.mc.playerController.processRightClickBlock(InteractionUtil.mc.player, InteractionUtil.mc.world, blockPos, enumFacing, vec3d, enumHand);
        }
        if (bl2) {
            InteractionUtil.mc.player.connection.sendPacket((Packet)new CPacketAnimation(enumHand));
        }
    }

    public static boolean canPlaceNormally(boolean bl) {
        if (!bl) {
            return true;
        }
        return true;
    }

    public static boolean canPlaceBlock(BlockPos blockPos, boolean bl) {
        return InteractionUtil.canPlaceBlock(blockPos, bl, true);
    }

    public static boolean canPlaceBlock(BlockPos blockPos, boolean bl, boolean bl2, boolean bl3) {
        Block block = InteractionUtil.mc.world.getBlockState(blockPos).getBlock();
        if (!(block instanceof BlockAir || block instanceof BlockLiquid || block instanceof BlockTallGrass || block instanceof BlockFire || block instanceof BlockDeadBush || block instanceof BlockSnow)) {
            return false;
        }
        if (bl3) {
            for (EnumFacing enumFacing : InteractionUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos))) {
                if (enumFacing instanceof EntityItem || enumFacing instanceof EntityXPOrb) continue;
                return false;
            }
        }
        for (EnumFacing enumFacing : InteractionUtil.getPlacableFacings(blockPos, bl, bl2)) {
            if (!InteractionUtil.canClick(blockPos.offset(enumFacing))) continue;
            return true;
        }
        return false;
    }

    public static boolean canRayTrace(BlockPos blockPos) {
        return InteractionUtil.mc.world.rayTraceBlocks(new Vec3d(InteractionUtil.mc.player.posX, InteractionUtil.mc.player.posY + (double)InteractionUtil.mc.player.getEyeHeight(), InteractionUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()), false, true, false) == null;
    }

    public static boolean canPlaceBlock(BlockPos blockPos, boolean bl, boolean bl2) {
        return InteractionUtil.canPlaceBlock(blockPos, bl, false, bl2);
    }

    public static Placement preparePlacement(BlockPos blockPos, boolean bl, boolean bl2) {
        return InteractionUtil.preparePlacement(blockPos, bl, bl2, false);
    }

    public static Placement preparePlacement(BlockPos blockPos, boolean bl) {
        return InteractionUtil.preparePlacement(blockPos, bl, false);
    }

    static {
        mc = Minecraft.getMinecraft();
    }

    public static void placeBlock(Placement placement, EnumHand enumHand, boolean bl) {
        InteractionUtil.rightClickBlock(placement.getNeighbour(), placement.getHitVec(), enumHand, placement.getOpposite(), bl, true);
    }

    public static Placement preparePlacement(BlockPos blockPos, boolean bl, boolean bl2, boolean bl3) {
        return InteractionUtil.preparePlacement(blockPos, bl, bl2, bl3, false);
    }

    public static Placement preparePlacement(BlockPos blockPos, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        Object object;
        EnumFacing enumFacing3;
        EnumFacing enumFacing2 = null;
        Vec3d vec3d = null;
        double d = 69420.0;
        for (EnumFacing enumFacing3 : InteractionUtil.getPlacableFacings(blockPos, bl3, bl4)) {
            object = blockPos.offset(enumFacing3);
            Vec3d vec3d2 = new Vec3d((Vec3i)object).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing3.getDirectionVec()).scale(0.5));
            if (!(InteractionUtil.mc.player.getPositionVector().addVector(0.0, (double)InteractionUtil.mc.player.getEyeHeight(), 0.0).distanceTo(vec3d2) < d)) continue;
            enumFacing2 = enumFacing3;
            vec3d = vec3d2;
        }
        if (enumFacing2 == null) {
            return null;
        }
        BlockPos blockPos2 = blockPos.offset(enumFacing2);
        enumFacing3 = enumFacing2.getOpposite();
        object = RotationManager.calculateAngle(InteractionUtil.mc.player.getPositionEyes(mc.getRenderPartialTicks()), vec3d);
        if (bl) {
            if (bl2) {
                InteractionUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation((float)object[0], (float)object[1], InteractionUtil.mc.player.onGround));
                ((IEntityPlayerSP)InteractionUtil.mc.player).setLastReportedYaw((float)object[0]);
                ((IEntityPlayerSP)InteractionUtil.mc.player).setLastReportedPitch((float)object[1]);
            } else {
                InteractionUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation((float)object[0], (float)object[1], InteractionUtil.mc.player.onGround));
                ((IEntityPlayerSP)InteractionUtil.mc.player).setLastReportedYaw((float)object[0]);
                ((IEntityPlayerSP)InteractionUtil.mc.player).setLastReportedPitch((float)object[1]);
            }
        }
        return new Placement(blockPos2, enumFacing3, vec3d, (float)object[0], (float)object[1]);
    }

    public static void placeBlockSafely(Placement placement, EnumHand enumHand, boolean bl) {
        boolean bl2 = InteractionUtil.mc.player.isSprinting();
        boolean bl3 = BlockUtilSC.shouldSneakWhileRightClicking(placement.getNeighbour());
        if (bl2) {
            InteractionUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)InteractionUtil.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
        }
        if (bl3) {
            InteractionUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)InteractionUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
        }
        InteractionUtil.placeBlock(placement, enumHand, bl);
        if (bl3) {
            InteractionUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)InteractionUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        if (bl2) {
            InteractionUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)InteractionUtil.mc.player, CPacketEntityAction.Action.START_SPRINTING));
        }
    }

    public static List<EnumFacing> getPlacableFacings(BlockPos blockPos, boolean bl, boolean bl2) {
        ArrayList<EnumFacing> arrayList = new ArrayList<EnumFacing>();
        for (EnumFacing enumFacing : EnumFacing.values()) {
            IBlockState iBlockState;
            Vec3d vec3d;
            BlockPos blockPos2;
            if (bl2) {
                blockPos2 = new Vec3d((Vec3i)blockPos).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
                vec3d = InteractionUtil.mc.world.rayTraceBlocks(InteractionUtil.mc.player.getPositionEyes(1.0f), (Vec3d)blockPos2);
                if (vec3d != null && vec3d.typeOfHit != RayTraceResult.Type.MISS) {
                    System.out.println("weary");
                    continue;
                }
            }
            blockPos2 = blockPos.offset(enumFacing);
            if (bl) {
                vec3d = InteractionUtil.mc.player.getPositionEyes(1.0f);
                Vec3d vec3d2 = new Vec3d((double)blockPos2.getX() + 0.5, (double)blockPos2.getY() + 0.5, (double)blockPos2.getZ() + 0.5);
                IBlockState iBlockState2 = InteractionUtil.mc.world.getBlockState(blockPos2);
                boolean bl3 = iBlockState2.getBlock() == Blocks.AIR || iBlockState2.isFullBlock();
                ArrayList<EnumFacing> arrayList2 = new ArrayList<EnumFacing>();
                arrayList2.addAll(InteractionUtil.checkAxis(vec3d.xCoord - vec3d2.xCoord, EnumFacing.WEST, EnumFacing.EAST, !bl3));
                arrayList2.addAll(InteractionUtil.checkAxis(vec3d.yCoord - vec3d2.yCoord, EnumFacing.DOWN, EnumFacing.UP, true));
                arrayList2.addAll(InteractionUtil.checkAxis(vec3d.zCoord - vec3d2.zCoord, EnumFacing.NORTH, EnumFacing.SOUTH, !bl3));
                if (!arrayList2.contains((Object)enumFacing.getOpposite())) continue;
            }
            if ((iBlockState = InteractionUtil.mc.world.getBlockState(blockPos2)) == null || !iBlockState.getBlock().canCollideCheck(iBlockState, false) || iBlockState.getMaterial().isReplaceable()) continue;
            arrayList.add(enumFacing);
        }
        return arrayList;
    }

    public static class Placement {
        private final /* synthetic */ Vec3d hitVec;
        private final /* synthetic */ BlockPos neighbour;
        private final /* synthetic */ EnumFacing opposite;
        private final /* synthetic */ float pitch;
        private final /* synthetic */ float yaw;

        public EnumFacing getOpposite() {
            return this.opposite;
        }

        public Vec3d getHitVec() {
            return this.hitVec;
        }

        public float getYaw() {
            return this.yaw;
        }

        public float getPitch() {
            return this.pitch;
        }

        public Placement(BlockPos blockPos, EnumFacing enumFacing, Vec3d vec3d, float f, float f2) {
            this.neighbour = blockPos;
            this.opposite = enumFacing;
            this.hitVec = vec3d;
            this.yaw = f;
            this.pitch = f2;
        }

        public BlockPos getNeighbour() {
            return this.neighbour;
        }
    }
}

