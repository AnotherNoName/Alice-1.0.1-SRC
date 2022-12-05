//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3i
 */
package me.snow.aclient.manager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import me.snow.aclient.module.Feature;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.skid.oyvey.EntityUtil2;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class HoleManager
extends Feature {
    private final /* synthetic */ List<BlockPos> midSafety;
    private /* synthetic */ List<BlockPos> holes;
    private static final /* synthetic */ BlockPos[] surroundOffset;

    public boolean isSafe(BlockPos blockPos) {
        boolean bl = true;
        for (BlockPos blockPos2 : surroundOffset) {
            Block block = HoleManager.mc.world.getBlockState(blockPos.add((Vec3i)blockPos2)).getBlock();
            if (block == Blocks.BEDROCK) continue;
            bl = false;
            break;
        }
        return bl;
    }

    public List<BlockPos> getMidSafety() {
        return this.midSafety;
    }

    public List<BlockPos> getSortedHoles() {
        this.holes.sort(Comparator.comparingDouble(blockPos -> HoleManager.mc.player.getDistanceSq(blockPos)));
        return this.getHoles();
    }

    public HoleManager() {
        this.midSafety = new ArrayList<BlockPos>();
        this.holes = new ArrayList<BlockPos>();
    }

    static {
        surroundOffset = BlockUtil.toBlockPos(EntityUtil2.getOffsets(0, true));
    }

    public void update() {
        if (!HoleManager.fullNullCheck()) {
            this.holes = this.calcHoles();
        }
    }

    public List<BlockPos> getHoles() {
        return this.holes;
    }

    public List<BlockPos> calcHoles() {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        this.midSafety.clear();
        List<BlockPos> list = BlockUtil.getSphere(EntityUtil.getPlayerPos((EntityPlayer)HoleManager.mc.player), 6.0f, 6, false, true, 0);
        for (BlockPos blockPos : list) {
            if (!HoleManager.mc.world.getBlockState(blockPos).getBlock().equals((Object)Blocks.AIR) || !HoleManager.mc.world.getBlockState(blockPos.add(0, 1, 0)).getBlock().equals((Object)Blocks.AIR) || !HoleManager.mc.world.getBlockState(blockPos.add(0, 2, 0)).getBlock().equals((Object)Blocks.AIR)) continue;
            boolean bl = true;
            boolean bl2 = true;
            for (BlockPos blockPos2 : surroundOffset) {
                Block block = HoleManager.mc.world.getBlockState(blockPos.add((Vec3i)blockPos2)).getBlock();
                if (BlockUtil.isBlockUnSolid(block)) {
                    bl2 = false;
                }
                if (block == Blocks.BEDROCK || block == Blocks.OBSIDIAN || block == Blocks.ENDER_CHEST || block == Blocks.ANVIL) continue;
                bl = false;
            }
            if (bl) {
                arrayList.add(blockPos);
            }
            if (!bl2) continue;
            this.midSafety.add(blockPos);
        }
        return arrayList;
    }
}

