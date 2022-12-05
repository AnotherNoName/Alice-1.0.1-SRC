//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.server.SPacketMoveVehicle
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.movement;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.JesusEvent;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.player.Freecam;
import me.snow.aclient.util.EntityUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketMoveVehicle;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Jesus
extends Module {
    public /* synthetic */ Setting<Mode> mode;
    public /* synthetic */ Setting<EventMode> eventMode;
    public static /* synthetic */ AxisAlignedBB offset;
    public /* synthetic */ Setting<Boolean> fall;
    private static /* synthetic */ Jesus INSTANCE;
    public /* synthetic */ Setting<Boolean> cancelVehicle;
    private /* synthetic */ boolean grounded;

    private void doTrampoline() {
        if (Jesus.mc.player.isSneaking()) {
            return;
        }
        if (EntityUtil.isAboveLiquid((Entity)Jesus.mc.player) && !Jesus.mc.player.isSneaking() && !Jesus.mc.gameSettings.keyBindJump.pressed) {
            Jesus.mc.player.motionY = 0.1;
            return;
        }
        if (Jesus.mc.player.onGround || Jesus.mc.player.isOnLadder()) {
            this.grounded = false;
        }
        if (Jesus.mc.player.motionY > 0.0) {
            if (Jesus.mc.player.motionY < 0.03 && this.grounded) {
                Jesus.mc.player.motionY += 0.06713;
            } else if (Jesus.mc.player.motionY <= 0.05 && this.grounded) {
                Jesus.mc.player.motionY *= 1.20000000999;
                Jesus.mc.player.motionY += 0.06;
            } else if (Jesus.mc.player.motionY <= 0.08 && this.grounded) {
                Jesus.mc.player.motionY *= 1.20000003;
                Jesus.mc.player.motionY += 0.055;
            } else if (Jesus.mc.player.motionY <= 0.112 && this.grounded) {
                Jesus.mc.player.motionY += 0.0535;
            } else if (this.grounded) {
                Jesus.mc.player.motionY *= 1.000000000002;
                Jesus.mc.player.motionY += 0.0517;
            }
        }
        if (this.grounded && Jesus.mc.player.motionY < 0.0 && Jesus.mc.player.motionY > -0.3) {
            Jesus.mc.player.motionY += 0.045835;
        }
        if (!this.fall.getValue().booleanValue()) {
            Jesus.mc.player.fallDistance = 0.0f;
        }
        if (!EntityUtil.checkForLiquid((Entity)Jesus.mc.player, true)) {
            return;
        }
        if (EntityUtil.checkForLiquid((Entity)Jesus.mc.player, true)) {
            Jesus.mc.player.motionY = 0.5;
        }
        this.grounded = true;
    }

    @SubscribeEvent
    public void sendPacket(PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketPlayer && Freecam.getInstance().isOff() && (this.mode.getValue() == Mode.BOUNCE || this.mode.getValue() == Mode.NORMAL) && Jesus.mc.player.getRidingEntity() == null && !Jesus.mc.gameSettings.keyBindJump.isKeyDown()) {
            CPacketPlayer cPacketPlayer = (CPacketPlayer)send.getPacket();
            if (!EntityUtil.isInLiquid() && EntityUtil.isOnLiquid(0.05f) && EntityUtil.checkCollide() && Jesus.mc.player.ticksExisted % 3 == 0) {
                cPacketPlayer.y -= (double)0.05f;
            }
        }
    }

    @SubscribeEvent
    public void onLiquidCollision(JesusEvent jesusEvent) {
        if (Jesus.fullNullCheck() || Freecam.getInstance().isOn()) {
            return;
        }
        if (jesusEvent.getStage() == 0 && (this.mode.getValue() == Mode.BOUNCE || this.mode.getValue() == Mode.VANILLA || this.mode.getValue() == Mode.NORMAL) && Jesus.mc.world != null && Jesus.mc.player != null && EntityUtil.checkCollide() && !(Jesus.mc.player.motionY >= (double)0.1f) && (double)jesusEvent.getPos().getY() < Jesus.mc.player.posY - (double)0.05f) {
            if (Jesus.mc.player.getRidingEntity() != null) {
                jesusEvent.setBoundingBox(new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, (double)0.95f, 1.0));
            } else {
                jesusEvent.setBoundingBox(Block.FULL_BLOCK_AABB);
            }
            jesusEvent.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPacketReceived(PacketEvent.Receive receive) {
        if (this.cancelVehicle.getValue().booleanValue() && receive.getPacket() instanceof SPacketMoveVehicle) {
            receive.setCanceled(true);
        }
    }

    @Override
    public String getDisplayInfo() {
        if (this.mode.getValue() == Mode.NORMAL) {
            return null;
        }
        return this.mode.currentEnumName();
    }

    public static boolean isInLiquid() {
        return Jesus.mc.player.isInLava() || Jesus.mc.player.isInWater();
    }

    @SubscribeEvent
    public void updateWalkingPlayer(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (Jesus.fullNullCheck() || Freecam.getInstance().isOn()) {
            return;
        }
        if (!(updateWalkingPlayerEvent.getStage() != 0 || this.mode.getValue() != Mode.BOUNCE && this.mode.getValue() != Mode.VANILLA && this.mode.getValue() != Mode.NORMAL || Jesus.mc.player.isSneaking() || Jesus.mc.player.noClip || Jesus.mc.gameSettings.keyBindJump.isKeyDown() || !EntityUtil.isInLiquid())) {
            Jesus.mc.player.motionY = 0.1f;
        }
        if (updateWalkingPlayerEvent.getStage() == 0 && this.mode.getValue() == Mode.TRAMPOLINE && (this.eventMode.getValue() == EventMode.ALL || this.eventMode.getValue() == EventMode.PRE)) {
            this.doTrampoline();
        } else if (updateWalkingPlayerEvent.getStage() == 1 && this.mode.getValue() == Mode.TRAMPOLINE && (this.eventMode.getValue() == EventMode.ALL || this.eventMode.getValue() == EventMode.POST)) {
            this.doTrampoline();
        }
    }

    public static Jesus getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Jesus();
        }
        return INSTANCE;
    }

    public Jesus() {
        super("Jesus", "Allows you to walk on water", Module.Category.MOVEMENT, true, false, false);
        this.mode = this.register(new Setting<Mode>("Mode", Mode.NORMAL));
        this.cancelVehicle = this.register(new Setting<Boolean>("NoVehicle", false));
        this.eventMode = this.register(new Setting<Object>("Jump", (Object)EventMode.PRE, object -> this.mode.getValue() == Mode.TRAMPOLINE));
        this.fall = this.register(new Setting<Object>("NoFall", Boolean.valueOf(false), object -> this.mode.getValue() == Mode.TRAMPOLINE));
        this.grounded = false;
        INSTANCE = this;
    }

    static {
        offset = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.9999, 1.0);
        INSTANCE = new Jesus();
    }

    public boolean isStandingOnLiquid() {
        return Jesus.mc.world.handleMaterialAcceleration(Jesus.mc.player.getEntityBoundingBox().expand(0.0, -3.0, 0.0).contract(0.001), Material.WATER, (Entity)Jesus.mc.player) || Jesus.mc.world.handleMaterialAcceleration(Jesus.mc.player.getEntityBoundingBox().expand(0.0, -3.0, 0.0).contract(0.001), Material.LAVA, (Entity)Jesus.mc.player);
    }

    public static enum EventMode {
        PRE,
        POST,
        ALL;

    }

    public static enum Mode {
        TRAMPOLINE,
        BOUNCE,
        VANILLA,
        NORMAL;

    }
}

