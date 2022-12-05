//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.module.Module;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoGlitchBlocks
extends Module {
    private static /* synthetic */ NoGlitchBlocks INSTANCE;

    private void removeGlitchBlocks(BlockPos blockPos) {
        for (int i = -4; i <= 4; ++i) {
            for (int j = -4; j <= 4; ++j) {
                for (int k = -4; k <= 4; ++k) {
                    BlockPos blockPos2 = new BlockPos(blockPos.getX() + i, blockPos.getY() + j, blockPos.getZ() + k);
                    if (!NoGlitchBlocks.mc.world.getBlockState(blockPos2).getBlock().equals((Object)Blocks.AIR)) continue;
                    NoGlitchBlocks.mc.playerController.processRightClickBlock(NoGlitchBlocks.mc.player, NoGlitchBlocks.mc.world, blockPos2, EnumFacing.DOWN, new Vec3d(0.5, 0.5, 0.5), EnumHand.MAIN_HAND);
                }
            }
        }
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @SubscribeEvent
    public void onBreak(BlockEvent.BreakEvent breakEvent) {
        if (NoGlitchBlocks.fullNullCheck()) {
            return;
        }
        if (!(NoGlitchBlocks.mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock)) {
            BlockPos blockPos = NoGlitchBlocks.mc.player.getPosition();
            this.removeGlitchBlocks(blockPos);
        }
    }

    public static NoGlitchBlocks getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new NoGlitchBlocks();
        }
        return INSTANCE;
    }

    static {
        INSTANCE = new NoGlitchBlocks();
    }

    public NoGlitchBlocks() {
        super("NoGlitchBlocks", "Prevents ghost blocks", Module.Category.PLAYER, true, false, false);
        this.setInstance();
    }
}

