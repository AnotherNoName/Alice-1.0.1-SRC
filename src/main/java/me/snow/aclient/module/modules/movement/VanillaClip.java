//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockHopper
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.IBlockAccess
 */
package me.snow.aclient.module.modules.movement;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockHopper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;

public class VanillaClip
extends Module {
    public static /* synthetic */ VanillaClip INSTANCE;
    public /* synthetic */ Setting<Boolean> onlyPhasing;

    public VanillaClip() {
        super("VanillaClip", "VanillaClip", Module.Category.MOVEMENT, true, false, false);
        this.onlyPhasing = this.register(new Setting<Boolean>("OnlyPhasing", true));
        INSTANCE = this;
    }

    public static boolean isPhasing() {
        try {
            AxisAlignedBB axisAlignedBB = VanillaClip.mc.player.getEntityBoundingBox();
            for (int i = MathHelper.floor((double)axisAlignedBB.minX); i < MathHelper.floor((double)axisAlignedBB.maxX) + 1; ++i) {
                for (int j = MathHelper.floor((double)axisAlignedBB.minY); j < MathHelper.floor((double)axisAlignedBB.maxY) + 1; ++j) {
                    for (int k = MathHelper.floor((double)axisAlignedBB.minZ); k < MathHelper.floor((double)axisAlignedBB.maxZ) + 1; ++k) {
                        Block block = VanillaClip.mc.world.getBlockState(new BlockPos(i, j, k)).getBlock();
                        if (block == null || block instanceof BlockAir) continue;
                        AxisAlignedBB axisAlignedBB2 = block.getCollisionBoundingBox(VanillaClip.mc.world.getBlockState(new BlockPos(i, j, k)), (IBlockAccess)VanillaClip.mc.world, new BlockPos(i, j, k)).offset((double)i, (double)j, (double)k);
                        if (block instanceof BlockHopper) {
                            axisAlignedBB2 = new AxisAlignedBB((double)i, (double)j, (double)k, (double)(i + 1), (double)(j + 1), (double)(k + 1));
                        }
                        if (axisAlignedBB2 == null || !axisAlignedBB.intersectsWith(axisAlignedBB2)) continue;
                        return true;
                    }
                }
            }
        }
        catch (Exception exception) {
            return false;
        }
        return false;
    }
}

