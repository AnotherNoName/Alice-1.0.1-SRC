//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.player;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.BlockEvent;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.RenderUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InstantMine
extends Module {
    public final /* synthetic */ Setting<Integer> BreakGreen;
    public final /* synthetic */ Setting<Integer> FinishBlue;
    public final /* synthetic */ Setting<Integer> FinishRed;
    private final /* synthetic */ Timer breakSuccess;
    private /* synthetic */ Setting<Boolean> ghostHand;
    private /* synthetic */ boolean empty;
    private static /* synthetic */ InstantMine INSTANCE;
    public final /* synthetic */ Setting<Integer> BreakBlue;
    public final /* synthetic */ Setting<Integer> BreakRed;
    private /* synthetic */ Timer breakTimer;
    private /* synthetic */ boolean cancelStart;
    public final /* synthetic */ Setting<Integer> FinishGreen;
    private final /* synthetic */ List<Block> godBlocks;
    private /* synthetic */ Setting<Boolean> render;
    private /* synthetic */ EnumFacing facing;
    public static /* synthetic */ BlockPos breakPos;
    public /* synthetic */ Setting<Integer> delay;

    @Override
    public String getDisplayInfo() {
        return this.ghostHand.getValue() != false ? "Silent" : "Normal";
    }

    @SubscribeEvent
    public void onBlockEvent(BlockEvent blockEvent) {
        if (InstantMine.fullNullCheck()) {
            return;
        }
        if (BlockUtil.canBreak(blockEvent.pos)) {
            this.empty = false;
            this.cancelStart = false;
            breakPos = blockEvent.pos;
            this.breakSuccess.reset();
            this.facing = blockEvent.facing;
            if (breakPos != null) {
                InstantMine.mc.player.swingArm(EnumHand.MAIN_HAND);
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, breakPos, this.facing));
                this.cancelStart = true;
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, breakPos, this.facing));
                blockEvent.setCanceled(true);
            }
        }
    }

    static {
        INSTANCE = new InstantMine();
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        CPacketPlayerDigging cPacketPlayerDigging;
        if (InstantMine.fullNullCheck()) {
            return;
        }
        if (send.getPacket() instanceof CPacketPlayerDigging && (cPacketPlayerDigging = (CPacketPlayerDigging)send.getPacket()).getAction() == CPacketPlayerDigging.Action.START_DESTROY_BLOCK) {
            send.setCanceled(this.cancelStart);
        }
    }

    @Override
    public void onUpdate() {
        if (InstantMine.fullNullCheck()) {
            return;
        }
        if (!this.breakTimer.passedMs(this.delay.getValue().intValue())) {
            try {
                InstantMine.mc.playerController.blockHitDelay = 0;
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            return;
        }
        this.breakTimer.reset();
        if (this.cancelStart && !this.godBlocks.contains((Object)InstantMine.mc.world.getBlockState(breakPos).getBlock())) {
            if (this.ghostHand.getValue().booleanValue() && InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE) != -1) {
                int n = InstantMine.mc.player.inventory.currentItem;
                if (InstantMine.mc.world.getBlockState(breakPos).getBlock() == Blocks.OBSIDIAN) {
                    if (this.breakSuccess.passedMs(1234L)) {
                        InstantMine.mc.player.inventory.currentItem = InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE);
                        InstantMine.mc.playerController.updateController();
                        InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, breakPos, this.facing));
                        InstantMine.mc.player.inventory.currentItem = n;
                        InstantMine.mc.playerController.updateController();
                    }
                } else {
                    InstantMine.mc.player.inventory.currentItem = InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE);
                    InstantMine.mc.playerController.updateController();
                    InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, breakPos, this.facing));
                    InstantMine.mc.player.inventory.currentItem = n;
                    InstantMine.mc.playerController.updateController();
                }
            } else {
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, breakPos, this.facing));
            }
        }
    }

    public InstantMine() {
        super("InstantMine", "InstantMine", Module.Category.PLAYER, true, false, false);
        this.breakSuccess = new Timer();
        this.delay = this.register(new Setting<Integer>("Delay", 65, 0, 500));
        this.ghostHand = this.register(new Setting<Boolean>("SilentSwitch", true));
        this.render = this.register(new Setting<Boolean>("Render", true));
        this.BreakRed = this.register(new Setting<Integer>("BreakRed", 255, 0, 255));
        this.BreakGreen = this.register(new Setting<Integer>("BreakGreen", 0, 0, 255));
        this.BreakBlue = this.register(new Setting<Integer>("BreakBlue", 0, 0, 255));
        this.FinishRed = this.register(new Setting<Integer>("FinishRed", 0, 0, 255));
        this.FinishGreen = this.register(new Setting<Integer>("FinishGreen", 255, 0, 255));
        this.FinishBlue = this.register(new Setting<Integer>("FinishBlue", 0, 0, 255));
        this.godBlocks = Arrays.asList(new Block[]{Blocks.AIR, Blocks.FLOWING_LAVA, Blocks.LAVA, Blocks.FLOWING_WATER, Blocks.WATER, Blocks.BEDROCK});
        this.cancelStart = false;
        this.empty = false;
        this.setInstance();
        this.breakTimer = new Timer();
    }

    public static InstantMine getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InstantMine();
        }
        return INSTANCE;
    }

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        if (InstantMine.fullNullCheck()) {
            return;
        }
        if (this.render.getValue().booleanValue() && this.cancelStart) {
            if (this.godBlocks.contains((Object)InstantMine.mc.world.getBlockState(breakPos).getBlock())) {
                this.empty = true;
            }
            Color color = new Color(this.empty ? this.FinishRed.getValue().intValue() : this.BreakRed.getValue().intValue(), this.empty ? this.FinishGreen.getValue().intValue() : this.BreakGreen.getValue().intValue(), this.empty ? this.FinishBlue.getValue().intValue() : this.BreakBlue.getValue().intValue(), 255);
            RenderUtil.drawBoxESP(breakPos, color, false, color, 1.0f, true, true, 55, false);
        }
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

