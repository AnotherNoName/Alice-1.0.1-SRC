//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 */
package me.snow.aclient.util.ca.util;

import java.util.HashMap;
import me.snow.aclient.util.Util;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class HoleUtilCa
implements Util {
    public static HoleInfo isHole(BlockPos blockPos, boolean bl, boolean bl2) {
        HoleInfo holeInfo = new HoleInfo();
        HashMap<BlockOffset, BlockSafety> hashMap = HoleUtilCa.getUnsafeSides(blockPos);
        if (hashMap.containsKey((Object)BlockOffset.DOWN) && hashMap.remove((Object)BlockOffset.DOWN, (Object)BlockSafety.BREAKABLE) && !bl2) {
            holeInfo.setSafety(BlockSafety.BREAKABLE);
            return holeInfo;
        }
        int n = hashMap.size();
        hashMap.entrySet().removeIf(entry -> entry.getValue() == BlockSafety.RESISTANT);
        if (hashMap.size() != n) {
            holeInfo.setSafety(BlockSafety.RESISTANT);
        }
        if ((n = hashMap.size()) == 0) {
            holeInfo.setType(HoleType.SINGLE);
            holeInfo.setCentre(new AxisAlignedBB(blockPos));
            return holeInfo;
        }
        if (n == 1 && !bl) {
            return HoleUtilCa.isDoubleHole(holeInfo, blockPos, (BlockOffset)((Object)hashMap.keySet().stream().findFirst().get()));
        }
        holeInfo.setSafety(BlockSafety.BREAKABLE);
        return holeInfo;
    }

    public static BlockSafety isBlockSafe(Block block) {
        if (block == Blocks.BEDROCK) {
            return BlockSafety.UNBREAKABLE;
        }
        if (block == Blocks.OBSIDIAN || block == Blocks.ENDER_CHEST || block == Blocks.ANVIL) {
            return BlockSafety.RESISTANT;
        }
        return BlockSafety.BREAKABLE;
    }

    private static HoleInfo isDoubleHole(HoleInfo holeInfo, BlockPos blockPos, BlockOffset blockOffset) {
        BlockPos blockPos2 = blockOffset.offset(blockPos);
        HashMap<BlockOffset, BlockSafety> hashMap = HoleUtilCa.getUnsafeSides(blockPos2);
        int n = hashMap.size();
        hashMap.entrySet().removeIf(entry -> entry.getValue() == BlockSafety.RESISTANT);
        if (hashMap.size() != n) {
            holeInfo.setSafety(BlockSafety.RESISTANT);
        }
        if (hashMap.containsKey((Object)BlockOffset.DOWN)) {
            holeInfo.setType(HoleType.CUSTOM);
            hashMap.remove((Object)BlockOffset.DOWN);
        }
        if (hashMap.size() > 1) {
            holeInfo.setType(HoleType.NONE);
            return holeInfo;
        }
        double d = Math.min(blockPos.getX(), blockPos2.getX());
        double d2 = Math.max(blockPos.getX(), blockPos2.getX()) + 1;
        double d3 = Math.min(blockPos.getZ(), blockPos2.getZ());
        double d4 = Math.max(blockPos.getZ(), blockPos2.getZ()) + 1;
        holeInfo.setCentre(new AxisAlignedBB(d, (double)blockPos.getY(), d3, d2, (double)(blockPos.getY() + 1), d4));
        if (holeInfo.getType() != HoleType.CUSTOM) {
            holeInfo.setType(HoleType.DOUBLE);
        }
        return holeInfo;
    }

    public static HashMap<BlockOffset, BlockSafety> getUnsafeSides(BlockPos blockPos) {
        HashMap<BlockOffset, BlockSafety> hashMap = new HashMap<BlockOffset, BlockSafety>();
        BlockSafety blockSafety = HoleUtilCa.isBlockSafe(HoleUtilCa.mc.world.getBlockState(BlockOffset.DOWN.offset(blockPos)).getBlock());
        if (blockSafety != BlockSafety.UNBREAKABLE) {
            hashMap.put(BlockOffset.DOWN, blockSafety);
        }
        if ((blockSafety = HoleUtilCa.isBlockSafe(HoleUtilCa.mc.world.getBlockState(BlockOffset.NORTH.offset(blockPos)).getBlock())) != BlockSafety.UNBREAKABLE) {
            hashMap.put(BlockOffset.NORTH, blockSafety);
        }
        if ((blockSafety = HoleUtilCa.isBlockSafe(HoleUtilCa.mc.world.getBlockState(BlockOffset.SOUTH.offset(blockPos)).getBlock())) != BlockSafety.UNBREAKABLE) {
            hashMap.put(BlockOffset.SOUTH, blockSafety);
        }
        if ((blockSafety = HoleUtilCa.isBlockSafe(HoleUtilCa.mc.world.getBlockState(BlockOffset.EAST.offset(blockPos)).getBlock())) != BlockSafety.UNBREAKABLE) {
            hashMap.put(BlockOffset.EAST, blockSafety);
        }
        if ((blockSafety = HoleUtilCa.isBlockSafe(HoleUtilCa.mc.world.getBlockState(BlockOffset.WEST.offset(blockPos)).getBlock())) != BlockSafety.UNBREAKABLE) {
            hashMap.put(BlockOffset.WEST, blockSafety);
        }
        return hashMap;
    }

    public static enum BlockSafety {
        UNBREAKABLE,
        RESISTANT,
        BREAKABLE;

    }

    public static enum BlockOffset {
        DOWN(0, -1, 0),
        UP(0, 1, 0),
        NORTH(0, 0, -1),
        EAST(1, 0, 0),
        SOUTH(0, 0, 1),
        WEST(-1, 0, 0);

        private final /* synthetic */ int y;
        private final /* synthetic */ int z;
        private final /* synthetic */ int x;

        private BlockOffset(int n2, int n3, int n4) {
            this.x = n2;
            this.y = n3;
            this.z = n4;
        }

        public BlockPos right(BlockPos blockPos, int n) {
            return blockPos.add(-this.z * n, 0, this.x * n);
        }

        public BlockPos offset(BlockPos blockPos) {
            return blockPos.add(this.x, this.y, this.z);
        }

        public BlockPos forward(BlockPos blockPos, int n) {
            return blockPos.add(this.x * n, 0, this.z * n);
        }

        public BlockPos backward(BlockPos blockPos, int n) {
            return blockPos.add(-this.x * n, 0, -this.z * n);
        }

        public BlockPos left(BlockPos blockPos, int n) {
            return blockPos.add(this.z * n, 0, -this.x * n);
        }
    }

    public static class HoleInfo {
        private /* synthetic */ AxisAlignedBB centre;
        private /* synthetic */ HoleType type;
        private /* synthetic */ BlockSafety safety;

        public void setSafety(BlockSafety blockSafety) {
            this.safety = blockSafety;
        }

        public HoleType getType() {
            return this.type;
        }

        public BlockSafety getSafety() {
            return this.safety;
        }

        public void setCentre(AxisAlignedBB axisAlignedBB) {
            this.centre = axisAlignedBB;
        }

        public void setType(HoleType holeType) {
            this.type = holeType;
        }

        public AxisAlignedBB getCentre() {
            return this.centre;
        }

        public HoleInfo(BlockSafety blockSafety, HoleType holeType) {
            this.type = holeType;
            this.safety = blockSafety;
        }

        public HoleInfo() {
            this(BlockSafety.UNBREAKABLE, HoleType.NONE);
        }
    }

    public static enum HoleType {
        SINGLE,
        DOUBLE,
        CUSTOM,
        NONE;

    }
}

