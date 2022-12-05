//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockEnderChest
 *  net.minecraft.block.BlockObsidian
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 */
package me.snow.aclient.module.modules.combat;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.ca.util.BlockUtilCa;
import me.snow.aclient.util.ca.util.EntityUtilCa;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class SelfTrap
extends Module {
    private /* synthetic */ BlockPos trapPos;
    public /* synthetic */ Setting<swingA> swing;
    public /* synthetic */ Setting<Boolean> rotate;

    @Override
    public void onUpdate() {
        BlockUtilCa.ValidResult validResult;
        Vec3d vec3d = EntityUtilCa.interpolateEntity((Entity)SelfTrap.mc.player, mc.getRenderPartialTicks());
        this.trapPos = new BlockPos(vec3d.xCoord, vec3d.yCoord + 2.0, vec3d.zCoord);
        if (this.isTrapped()) {
            if (this.isEnabled()) {
                this.disable();
            } else {
                this.enable();
                return;
            }
        }
        if ((validResult = BlockUtilCa.valid(this.trapPos)) == BlockUtilCa.ValidResult.AlreadyBlockThere && !SelfTrap.mc.world.getBlockState(this.trapPos).getMaterial().isReplaceable()) {
            return;
        }
        if (validResult == BlockUtilCa.ValidResult.NoNeighbors) {
            BlockPos[] arrblockPos;
            for (BlockPos blockPos : arrblockPos = new BlockPos[]{this.trapPos.north(), this.trapPos.south(), this.trapPos.east(), this.trapPos.west(), this.trapPos.up(), this.trapPos.down().west()}) {
                BlockUtilCa.ValidResult validResult2 = BlockUtilCa.valid(blockPos);
                if (validResult2 == BlockUtilCa.ValidResult.NoNeighbors || validResult2 == BlockUtilCa.ValidResult.NoEntityCollision || !BlockUtilCa.placeBlock(blockPos, this.findInHotbar(), this.rotate.getValue(), this.rotate.getValue())) continue;
                if (this.swing.getValue() == swingA.Mainhand) {
                    SelfTrap.mc.player.swingArm(EnumHand.MAIN_HAND);
                }
                if (this.swing.getValue() == swingA.Offhand) {
                    SelfTrap.mc.player.swingArm(EnumHand.OFF_HAND);
                }
                return;
            }
            return;
        }
        BlockUtilCa.placeBlock(this.trapPos, this.findInHotbar(), this.rotate.getValue(), this.rotate.getValue());
        if (this.swing.getValue() == swingA.Mainhand) {
            SelfTrap.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        if (this.swing.getValue() == swingA.Offhand) {
            SelfTrap.mc.player.swingArm(EnumHand.OFF_HAND);
        }
    }

    @Override
    public void onEnable() {
        if (this.findInHotbar() == -1) {
            this.disable();
        }
    }

    private int findInHotbar() {
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = SelfTrap.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.field_190927_a || !(itemStack.getItem() instanceof ItemBlock)) continue;
            Block block = ((ItemBlock)itemStack.getItem()).getBlock();
            if (block instanceof BlockEnderChest) {
                return i;
            }
            if (!(block instanceof BlockObsidian)) continue;
            return i;
        }
        return -1;
    }

    public SelfTrap() {
        super("SelfTrap", "Will speed up the game.", Module.Category.COMBAT, false, false, false);
        this.rotate = this.register(new Setting<Boolean>("Rotate", false));
        this.swing = this.register(new Setting<swingA>("SwingHand", swingA.Mainhand));
    }

    public boolean isTrapped() {
        if (this.trapPos == null) {
            return false;
        }
        IBlockState iBlockState = SelfTrap.mc.world.getBlockState(this.trapPos);
        return iBlockState.getBlock() != Blocks.AIR && iBlockState.getBlock() != Blocks.WATER && iBlockState.getBlock() != Blocks.LAVA;
    }

    public static enum swingA {
        Mainhand,
        Offhand,
        None;

    }
}

