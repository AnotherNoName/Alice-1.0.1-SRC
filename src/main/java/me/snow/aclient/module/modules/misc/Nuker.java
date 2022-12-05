//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import java.util.ArrayList;
import java.util.List;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.BlockEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Nuker
extends Module {
    private /* synthetic */ boolean isMining;
    public /* synthetic */ Setting<Float> distance;
    public /* synthetic */ Setting<Integer> blockPerTick;
    public /* synthetic */ Setting<Boolean> antiRegear;
    public /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Setting<Boolean> autoSwitch;
    private /* synthetic */ Block selected;
    private /* synthetic */ int oldSlot;
    public /* synthetic */ Setting<Boolean> nuke;
    public /* synthetic */ Setting<Boolean> hopperNuker;
    private final /* synthetic */ Timer timer;
    public /* synthetic */ Setting<Mode> mode;
    public /* synthetic */ Setting<Boolean> rotate;

    private BlockPos getClosestBlockAll() {
        float f = this.distance.getValue().floatValue();
        BlockPos blockPos = null;
        for (float f2 = f; f2 >= -f; f2 -= 1.0f) {
            for (float f3 = f; f3 >= -f; f3 -= 1.0f) {
                for (float f4 = f; f4 >= -f; f4 -= 1.0f) {
                    BlockPos blockPos2 = new BlockPos(Nuker.mc.player.posX + (double)f2, Nuker.mc.player.posY + (double)f3, Nuker.mc.player.posZ + (double)f4);
                    double d = Nuker.mc.player.getDistance((double)blockPos2.getX(), (double)blockPos2.getY(), (double)blockPos2.getZ());
                    if (!(d <= (double)f) || Nuker.mc.world.getBlockState(blockPos2).getBlock() == Blocks.AIR || Nuker.mc.world.getBlockState(blockPos2).getBlock() instanceof BlockLiquid || !this.canBreak(blockPos2) || !((double)blockPos2.getY() >= Nuker.mc.player.posY)) continue;
                    f = (float)d;
                    blockPos = blockPos2;
                }
            }
        }
        return blockPos;
    }

    @SubscribeEvent
    public void onClickBlock(BlockEvent blockEvent) {
        Block block;
        if (blockEvent.getStage() == 3 && (this.mode.getValue() == Mode.SELECTION || this.mode.getValue() == Mode.NUKE) && (block = Nuker.mc.world.getBlockState(blockEvent.pos).getBlock()) != null && block != this.selected) {
            this.selected = block;
            blockEvent.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayerPre(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 0) {
            Object object;
            if (this.nuke.getValue().booleanValue()) {
                object = null;
                switch (this.mode.getValue()) {
                    case SELECTION: 
                    case NUKE: {
                        object = this.getClosestBlockSelection();
                        break;
                    }
                    case ALL: {
                        object = this.getClosestBlockAll();
                    }
                }
                if (object != null) {
                    if (this.mode.getValue() == Mode.SELECTION || this.mode.getValue() == Mode.ALL) {
                        if (this.rotate.getValue().booleanValue()) {
                            float[] arrf = MathUtil.calcAngle(Nuker.mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d((double)((float)object.getX() + 0.5f), (double)((float)object.getY() + 0.5f), (double)((float)object.getZ() + 0.5f)));
                            AliceMain.rotationManager.setPlayerRotations(arrf[0], arrf[1]);
                        }
                        if (this.canBreak((BlockPos)object)) {
                            Nuker.mc.playerController.onPlayerDamageBlock((BlockPos)object, Nuker.mc.player.getHorizontalFacing());
                            Nuker.mc.player.swingArm(EnumHand.MAIN_HAND);
                        }
                    } else {
                        for (int i = 0; i < this.blockPerTick.getValue(); ++i) {
                            object = this.getClosestBlockSelection();
                            if (object == null) continue;
                            if (this.rotate.getValue().booleanValue()) {
                                float[] arrf = MathUtil.calcAngle(Nuker.mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d((double)((float)object.getX() + 0.5f), (double)((float)object.getY() + 0.5f), (double)((float)object.getZ() + 0.5f)));
                                AliceMain.rotationManager.setPlayerRotations(arrf[0], arrf[1]);
                            }
                            if (!this.timer.passedMs(this.delay.getValue().intValue())) continue;
                            Nuker.mc.playerController.onPlayerDamageBlock((BlockPos)object, Nuker.mc.player.getHorizontalFacing());
                            Nuker.mc.player.swingArm(EnumHand.MAIN_HAND);
                            this.timer.reset();
                        }
                    }
                }
            }
            if (this.antiRegear.getValue().booleanValue()) {
                this.breakBlocks(BlockUtil.shulkerList);
            }
            if (this.hopperNuker.getValue().booleanValue()) {
                object = new ArrayList();
                ((ArrayList)object).add(Blocks.HOPPER);
                this.breakBlocks((List<Block>)object);
            }
        }
    }

    @Override
    public void onToggle() {
        this.selected = null;
    }

    public void breakBlocks(List<Block> list) {
        BlockPos blockPos = this.getNearestBlock(list);
        if (blockPos != null) {
            if (!this.isMining) {
                this.oldSlot = Nuker.mc.player.inventory.currentItem;
                this.isMining = true;
            }
            if (this.rotate.getValue().booleanValue()) {
                float[] arrf = MathUtil.calcAngle(Nuker.mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d((double)((float)blockPos.getX() + 0.5f), (double)((float)blockPos.getY() + 0.5f), (double)((float)blockPos.getZ() + 0.5f)));
                AliceMain.rotationManager.setPlayerRotations(arrf[0], arrf[1]);
            }
            if (this.canBreak(blockPos)) {
                if (this.autoSwitch.getValue().booleanValue()) {
                    int n = -1;
                    for (int i = 0; i < 9; ++i) {
                        ItemStack itemStack = Nuker.mc.player.inventory.getStackInSlot(i);
                        if (itemStack == ItemStack.field_190927_a || !(itemStack.getItem() instanceof ItemPickaxe)) continue;
                        n = i;
                        break;
                    }
                    if (n != -1) {
                        Nuker.mc.player.inventory.currentItem = n;
                    }
                }
                Nuker.mc.playerController.onPlayerDamageBlock(blockPos, Nuker.mc.player.getHorizontalFacing());
                Nuker.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
        } else if (this.autoSwitch.getValue().booleanValue() && this.oldSlot != -1) {
            Nuker.mc.player.inventory.currentItem = this.oldSlot;
            this.oldSlot = -1;
            this.isMining = false;
        }
    }

    private boolean canBreak(BlockPos blockPos) {
        IBlockState iBlockState = Nuker.mc.world.getBlockState(blockPos);
        Block block = iBlockState.getBlock();
        return block.getBlockHardness(iBlockState, (World)Nuker.mc.world, blockPos) != -1.0f;
    }

    public Nuker() {
        super("Nuker", "Mines many blocks.", Module.Category.MISC, true, false, false);
        this.timer = new Timer();
        this.autoSwitch = this.register(new Setting<Boolean>("AutoSwitch", false));
        this.rotate = this.register(new Setting<Boolean>("Rotate", false));
        this.distance = this.register(new Setting<Float>("Range", Float.valueOf(6.0f), Float.valueOf(0.1f), Float.valueOf(10.0f)));
        this.blockPerTick = this.register(new Setting<Integer>("Blocks/Attack", 50, 1, 100));
        this.delay = this.register(new Setting<Integer>("Delay/Attack", 50, 1, 1000));
        this.nuke = this.register(new Setting<Boolean>("Nuke", false));
        this.mode = this.register(new Setting<Object>("Mode", (Object)Mode.NUKE, object -> this.nuke.getValue()));
        this.antiRegear = this.register(new Setting<Boolean>("AntiRegear", false));
        this.hopperNuker = this.register(new Setting<Boolean>("HopperAura", false));
        this.oldSlot = -1;
    }

    private BlockPos getClosestBlockSelection() {
        float f = this.distance.getValue().floatValue();
        BlockPos blockPos = null;
        for (float f2 = f; f2 >= -f; f2 -= 1.0f) {
            for (float f3 = f; f3 >= -f; f3 -= 1.0f) {
                for (float f4 = f; f4 >= -f; f4 -= 1.0f) {
                    BlockPos blockPos2 = new BlockPos(Nuker.mc.player.posX + (double)f2, Nuker.mc.player.posY + (double)f3, Nuker.mc.player.posZ + (double)f4);
                    double d = Nuker.mc.player.getDistance((double)blockPos2.getX(), (double)blockPos2.getY(), (double)blockPos2.getZ());
                    if (!(d <= (double)f) || Nuker.mc.world.getBlockState(blockPos2).getBlock() == Blocks.AIR || Nuker.mc.world.getBlockState(blockPos2).getBlock() instanceof BlockLiquid || Nuker.mc.world.getBlockState(blockPos2).getBlock() != this.selected || !this.canBreak(blockPos2) || !((double)blockPos2.getY() >= Nuker.mc.player.posY)) continue;
                    f = (float)d;
                    blockPos = blockPos2;
                }
            }
        }
        return blockPos;
    }

    private BlockPos getNearestBlock(List<Block> list) {
        double d = MathUtil.square(this.distance.getValue().floatValue());
        BlockPos blockPos = null;
        for (double d2 = d; d2 >= -d; d2 -= 1.0) {
            for (double d3 = d; d3 >= -d; d3 -= 1.0) {
                for (double d4 = d; d4 >= -d; d4 -= 1.0) {
                    BlockPos blockPos2 = new BlockPos(Nuker.mc.player.posX + d2, Nuker.mc.player.posY + d3, Nuker.mc.player.posZ + d4);
                    double d5 = Nuker.mc.player.getDistanceSq((double)blockPos2.getX(), (double)blockPos2.getY(), (double)blockPos2.getZ());
                    if (!(d5 <= d) || !list.contains((Object)Nuker.mc.world.getBlockState(blockPos2).getBlock()) || !this.canBreak(blockPos2)) continue;
                    d = d5;
                    blockPos = blockPos2;
                }
            }
        }
        return blockPos;
    }

    public static enum Mode {
        SELECTION,
        ALL,
        NUKE;

    }
}

