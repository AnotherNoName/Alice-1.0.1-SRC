//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 */
package me.snow.aclient.util.cc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.snow.aclient.util.Util;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class BlockUtilCC
implements Util {
    public static final /* synthetic */ List<Block> unbreakableBlocks;
    public static final /* synthetic */ List<Block> resistantBlocks;

    public static boolean isReplaceable(BlockPos blockPos) {
        return BlockUtilCC.mc.world.getBlockState(blockPos).getMaterial().isReplaceable();
    }

    public static Resistance getResistance(BlockPos blockPos) {
        Block block = BlockUtilCC.mc.world.getBlockState(blockPos).getBlock();
        if (block != null) {
            if (resistantBlocks.contains((Object)block)) {
                return Resistance.RESISTANT;
            }
            if (unbreakableBlocks.contains((Object)block)) {
                return Resistance.UNBREAKABLE;
            }
            if (block.getDefaultState().getMaterial().isReplaceable()) {
                return Resistance.REPLACEABLE;
            }
            return Resistance.BREAKABLE;
        }
        return Resistance.NONE;
    }

    public static List<BlockPos> getBlocksInArea(EntityPlayer entityPlayer, AxisAlignedBB axisAlignedBB) {
        if (entityPlayer != null) {
            ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
            for (double d = StrictMath.floor(axisAlignedBB.minX); d <= StrictMath.ceil(axisAlignedBB.maxX); d += 1.0) {
                for (double d2 = StrictMath.floor(axisAlignedBB.minY); d2 <= StrictMath.ceil(axisAlignedBB.maxY); d2 += 1.0) {
                    for (double d3 = StrictMath.floor(axisAlignedBB.minZ); d3 <= StrictMath.ceil(axisAlignedBB.maxZ); d3 += 1.0) {
                        BlockPos blockPos = entityPlayer.getPosition().add(d, d2, d3);
                        if (BlockUtilCC.getDistanceToCenter(entityPlayer, blockPos) >= axisAlignedBB.maxX) continue;
                        arrayList.add(blockPos);
                    }
                }
            }
            return arrayList;
        }
        return new ArrayList<BlockPos>();
    }

    static {
        resistantBlocks = Arrays.asList(new Block[]{Blocks.OBSIDIAN, Blocks.ANVIL, Blocks.ENCHANTING_TABLE, Blocks.ENDER_CHEST, Blocks.BEACON});
        unbreakableBlocks = Arrays.asList(new Block[]{Blocks.BEDROCK, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.END_PORTAL_FRAME, Blocks.BARRIER, Blocks.PORTAL});
    }

    public static double getDistanceToCenter(EntityPlayer entityPlayer, BlockPos blockPos) {
        double d = (double)blockPos.getX() + 0.5 - entityPlayer.posX;
        double d2 = (double)blockPos.getY() + 0.5 - entityPlayer.posY;
        double d3 = (double)blockPos.getZ() + 0.5 - entityPlayer.posZ;
        return StrictMath.sqrt(d * d + d2 * d2 + d3 * d3);
    }

    public static boolean isBreakable(BlockPos blockPos) {
        return !BlockUtilCC.getResistance(blockPos).equals((Object)Resistance.UNBREAKABLE);
    }

    public static enum Resistance {
        REPLACEABLE,
        BREAKABLE,
        RESISTANT,
        UNBREAKABLE,
        NONE;

    }
}

