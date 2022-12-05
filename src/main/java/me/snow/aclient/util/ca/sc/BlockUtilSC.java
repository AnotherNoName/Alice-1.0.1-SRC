//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockAnvil
 *  net.minecraft.block.BlockBed
 *  net.minecraft.block.BlockButton
 *  net.minecraft.block.BlockCake
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.BlockDoor
 *  net.minecraft.block.BlockFenceGate
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockRedstoneDiode
 *  net.minecraft.block.BlockTrapDoor
 *  net.minecraft.block.BlockWorkbench
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 */
package me.snow.aclient.util.ca.sc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import me.snow.aclient.util.ca.sc.PlayerUtilSC;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockCake;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockRedstoneDiode;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class BlockUtilSC {
    private static final /* synthetic */ Minecraft mc;

    public static void rightClickBlock(BlockPos blockPos, Vec3d vec3d, EnumHand enumHand, EnumFacing enumFacing, boolean bl) {
        if (bl) {
            float f = (float)(vec3d.xCoord - (double)blockPos.getX());
            float f2 = (float)(vec3d.yCoord - (double)blockPos.getY());
            float f3 = (float)(vec3d.zCoord - (double)blockPos.getZ());
            BlockUtilSC.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, f, f2, f3));
            BlockUtilSC.mc.player.connection.sendPacket((Packet)new CPacketAnimation(enumHand));
        } else {
            BlockUtilSC.mc.playerController.processRightClickBlock(BlockUtilSC.mc.player, BlockUtilSC.mc.world, blockPos, enumFacing, vec3d, enumHand);
            BlockUtilSC.mc.player.swingArm(enumHand);
        }
    }

    public static List<BlockPos> getBlocksInSphere(BlockPos blockPos, int n) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        int n2 = blockPos.getX();
        int n3 = blockPos.getY();
        int n4 = blockPos.getZ();
        for (int i = n2 - n; i <= n2 + n; ++i) {
            for (int j = n4 - n; j <= n4 + n; ++j) {
                for (int k = n3 - n; k < n3 + n; ++k) {
                    double d = (n2 - i) * (n2 - i) + (n4 - j) * (n4 - j) + (n3 - k) * (n3 - k);
                    if (!(d < (double)(n * n))) continue;
                    arrayList.add(new BlockPos(i, k, j));
                }
            }
        }
        return arrayList;
    }

    public static boolean shouldSneakWhileRightClicking(BlockPos blockPos) {
        Block block = BlockUtilSC.mc.world.getBlockState(blockPos).getBlock();
        TileEntity tileEntity = null;
        for (TileEntity tileEntity2 : BlockUtilSC.mc.world.loadedTileEntityList) {
            if (!tileEntity2.getPos().equals((Object)blockPos)) continue;
            tileEntity = tileEntity2;
            break;
        }
        return tileEntity != null || block instanceof BlockBed || block instanceof BlockContainer || block instanceof BlockDoor || block instanceof BlockTrapDoor || block instanceof BlockFenceGate || block instanceof BlockButton || block instanceof BlockAnvil || block instanceof BlockWorkbench || block instanceof BlockCake || block instanceof BlockRedstoneDiode;
    }

    public static boolean validObi(BlockPos blockPos) {
        return !(BlockUtilSC.validBedrock(blockPos) || BlockUtilSC.mc.world.getBlockState(blockPos.add(0, -1, 0)).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.add(0, -1, 0)).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.add(1, 0, 0)).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.add(1, 0, 0)).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.add(-1, 0, 0)).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.add(-1, 0, 0)).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.add(0, 0, 1)).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.add(0, 0, 1)).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.add(0, 0, -1)).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.add(0, 0, -1)).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos).getMaterial() != Material.AIR || BlockUtilSC.mc.world.getBlockState(blockPos.add(0, 1, 0)).getMaterial() != Material.AIR || BlockUtilSC.mc.world.getBlockState(blockPos.add(0, 2, 0)).getMaterial() != Material.AIR);
    }

    public static double[] calculateLookAt(BlockPos blockPos, EnumFacing enumFacing, EntityPlayer entityPlayer) {
        return PlayerUtilSC.calculateLookAt((double)blockPos.getX() + 0.5 + (double)enumFacing.getDirectionVec().getX() * 0.5, (double)blockPos.getY() + 0.5 + (double)enumFacing.getDirectionVec().getY() * 0.5, (double)blockPos.getZ() + 0.5 + (double)enumFacing.getDirectionVec().getZ() * 0.5, entityPlayer);
    }

    public static Optional<ClickLocation> generateClickLocation(BlockPos blockPos, boolean bl, boolean bl2, boolean bl3) {
        Entity entity2;
        Block block = BlockUtilSC.mc.world.getBlockState(blockPos).getBlock();
        if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid)) {
            return Optional.empty();
        }
        if (!bl) {
            for (Entity entity2 : BlockUtilSC.mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(blockPos))) {
                if (bl3 && entity2 instanceof EntityEnderCrystal || entity2 instanceof EntityItem || entity2 instanceof EntityXPOrb || entity2 instanceof EntityArrow) continue;
                return Optional.empty();
            }
        }
        EnumFacing enumFacing = null;
        for (EnumFacing enumFacing2 : EnumFacing.values()) {
            IBlockState iBlockState;
            BlockPos blockPos2 = blockPos.offset(enumFacing2);
            if (bl2 && BlockUtilSC.mc.world.getBlockState(blockPos2).getBlock() == Blocks.PISTON || !BlockUtilSC.mc.world.getBlockState(blockPos2).getBlock().canCollideCheck(BlockUtilSC.mc.world.getBlockState(blockPos2), false) || (iBlockState = BlockUtilSC.mc.world.getBlockState(blockPos2)).getMaterial().isReplaceable()) continue;
            enumFacing = enumFacing2;
            break;
        }
        if (enumFacing == null) {
            return Optional.empty();
        }
        entity2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing3 = enumFacing.getOpposite();
        if (!BlockUtilSC.mc.world.getBlockState((BlockPos)entity2).getBlock().canCollideCheck(BlockUtilSC.mc.world.getBlockState((BlockPos)entity2), false)) {
            return Optional.empty();
        }
        return Optional.of(new ClickLocation((BlockPos)entity2, enumFacing3));
    }

    public static BlockPos validTwoBlockObiXZ(BlockPos blockPos) {
        if (!(BlockUtilSC.mc.world.getBlockState(blockPos.down()).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.down()).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.west()).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.west()).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.south()).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.south()).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.north()).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.north()).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos).getMaterial() != Material.AIR || BlockUtilSC.mc.world.getBlockState(blockPos.up()).getMaterial() != Material.AIR || BlockUtilSC.mc.world.getBlockState(blockPos.up(2)).getMaterial() != Material.AIR || BlockUtilSC.mc.world.getBlockState(blockPos.east().down()).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.east().down()).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.east(2)).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.east(2)).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.east().south()).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.east().south()).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.east().north()).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.east().north()).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.east()).getMaterial() != Material.AIR || BlockUtilSC.mc.world.getBlockState(blockPos.east().up()).getMaterial() != Material.AIR || BlockUtilSC.mc.world.getBlockState(blockPos.east().up(2)).getMaterial() != Material.AIR)) {
            return BlockUtilSC.validTwoBlockBedrockXZ(blockPos) == null ? new BlockPos(1, 0, 0) : null;
        }
        if (!(BlockUtilSC.mc.world.getBlockState(blockPos.down()).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.down()).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.west()).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.west()).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.east()).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.east()).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.north()).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.north()).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos).getMaterial() != Material.AIR || BlockUtilSC.mc.world.getBlockState(blockPos.up()).getMaterial() != Material.AIR || BlockUtilSC.mc.world.getBlockState(blockPos.up(2)).getMaterial() != Material.AIR || BlockUtilSC.mc.world.getBlockState(blockPos.south().down()).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.south().down()).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.south(2)).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.south(2)).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.south().east()).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.south().east()).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.south().west()).getBlock() != Blocks.OBSIDIAN && BlockUtilSC.mc.world.getBlockState(blockPos.south().west()).getBlock() != Blocks.BEDROCK || BlockUtilSC.mc.world.getBlockState(blockPos.south()).getMaterial() != Material.AIR || BlockUtilSC.mc.world.getBlockState(blockPos.south().up()).getMaterial() != Material.AIR || BlockUtilSC.mc.world.getBlockState(blockPos.south().up(2)).getMaterial() != Material.AIR)) {
            return BlockUtilSC.validTwoBlockBedrockXZ(blockPos) == null ? new BlockPos(0, 0, 1) : null;
        }
        return null;
    }

    public static double[] calculateLookAt(double d, double d2, double d3, EnumFacing enumFacing, EntityPlayer entityPlayer) {
        return PlayerUtilSC.calculateLookAt(d + 0.5 + (double)enumFacing.getDirectionVec().getX() * 0.5, d2 + 0.5 + (double)enumFacing.getDirectionVec().getY() * 0.5, d3 + 0.5 + (double)enumFacing.getDirectionVec().getZ() * 0.5, entityPlayer);
    }

    public static BlockPos validTwoBlockBedrockXZ(BlockPos blockPos) {
        if (BlockUtilSC.mc.world.getBlockState(blockPos.down()).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.west()).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.south()).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.north()).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos).getMaterial() == Material.AIR && BlockUtilSC.mc.world.getBlockState(blockPos.up()).getMaterial() == Material.AIR && BlockUtilSC.mc.world.getBlockState(blockPos.up(2)).getMaterial() == Material.AIR && BlockUtilSC.mc.world.getBlockState(blockPos.east().down()).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.east(2)).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.east().south()).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.east().north()).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.east()).getMaterial() == Material.AIR && BlockUtilSC.mc.world.getBlockState(blockPos.east().up()).getMaterial() == Material.AIR && BlockUtilSC.mc.world.getBlockState(blockPos.east().up(2)).getMaterial() == Material.AIR) {
            return new BlockPos(1, 0, 0);
        }
        if (BlockUtilSC.mc.world.getBlockState(blockPos.down()).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.west()).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.east()).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.north()).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos).getMaterial() == Material.AIR && BlockUtilSC.mc.world.getBlockState(blockPos.up()).getMaterial() == Material.AIR && BlockUtilSC.mc.world.getBlockState(blockPos.up(2)).getMaterial() == Material.AIR && BlockUtilSC.mc.world.getBlockState(blockPos.south().down()).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.south(2)).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.south().east()).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.south().west()).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.south()).getMaterial() == Material.AIR && BlockUtilSC.mc.world.getBlockState(blockPos.south().up()).getMaterial() == Material.AIR && BlockUtilSC.mc.world.getBlockState(blockPos.south().up(2)).getMaterial() == Material.AIR) {
            return new BlockPos(0, 0, 1);
        }
        return null;
    }

    public static boolean validBedrock(BlockPos blockPos) {
        return BlockUtilSC.mc.world.getBlockState(blockPos.add(0, -1, 0)).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.add(1, 0, 0)).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.add(-1, 0, 0)).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.add(0, 0, 1)).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos.add(0, 0, -1)).getBlock() == Blocks.BEDROCK && BlockUtilSC.mc.world.getBlockState(blockPos).getMaterial() == Material.AIR && BlockUtilSC.mc.world.getBlockState(blockPos.add(0, 1, 0)).getMaterial() == Material.AIR && BlockUtilSC.mc.world.getBlockState(blockPos.add(0, 2, 0)).getMaterial() == Material.AIR;
    }

    static {
        mc = Minecraft.getMinecraft();
    }

    public static Optional<ClickLocation> generateClickLocation(BlockPos blockPos) {
        return BlockUtilSC.generateClickLocation(blockPos, false, false);
    }

    public static Vec3d blockPosToVec(BlockPos blockPos) {
        return new Vec3d((Vec3i)blockPos);
    }

    public static BlockPos vecToBlockPos(Vec3d vec3d) {
        return new BlockPos(vec3d);
    }

    public static Optional<ClickLocation> generateClickLocation(BlockPos blockPos, boolean bl, boolean bl2) {
        return BlockUtilSC.generateClickLocation(blockPos, bl, false, false);
    }

    public static boolean isHole(BlockPos blockPos) {
        return BlockUtilSC.validObi(blockPos) || BlockUtilSC.validBedrock(blockPos);
    }

    public static Optional<ClickLocation> generateClickLocation(BlockPos blockPos, boolean bl) {
        return BlockUtilSC.generateClickLocation(blockPos, bl, false);
    }

    public static boolean hasNeighbours(BlockPos blockPos) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            BlockPos blockPos2 = blockPos.offset(enumFacing);
            if (BlockUtilSC.mc.world.getBlockState(blockPos2).getMaterial().isReplaceable()) continue;
            return true;
        }
        return false;
    }

    public static class ClickLocation {
        public final /* synthetic */ EnumFacing opposite;
        public final /* synthetic */ BlockPos neighbour;

        public ClickLocation(BlockPos blockPos, EnumFacing enumFacing) {
            this.neighbour = blockPos;
            this.opposite = enumFacing;
        }
    }
}

