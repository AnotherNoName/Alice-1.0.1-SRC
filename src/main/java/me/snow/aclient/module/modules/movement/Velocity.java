//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.projectile.EntityFishHook
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.play.server.SPacketEntityStatus
 *  net.minecraft.network.play.server.SPacketEntityVelocity
 *  net.minecraft.network.play.server.SPacketExplosion
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.movement;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.PushEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.movement.IceSpeed;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Velocity
extends Module {
    public /* synthetic */ Setting<Float> horizontal;
    private static /* synthetic */ Velocity INSTANCE;
    public /* synthetic */ Setting<Boolean> water;
    public /* synthetic */ Setting<Boolean> blocks;
    public /* synthetic */ Setting<Boolean> explosions;
    public /* synthetic */ Setting<Boolean> ice;
    public /* synthetic */ Setting<Boolean> noPush;
    public /* synthetic */ Setting<Boolean> knockBack;
    public /* synthetic */ Setting<Float> vertical;
    public /* synthetic */ Setting<Boolean> bobbers;

    private void setInstance() {
        INSTANCE = this;
    }

    @SubscribeEvent
    public void onPacketReceived(PacketEvent.Receive receive) {
        if (receive.getStage() == 0 && Velocity.mc.player != null) {
            SPacketExplosion sPacketExplosion;
            Entity entity;
            SPacketEntityStatus sPacketEntityStatus;
            SPacketEntityVelocity sPacketEntityVelocity;
            if (this.knockBack.getValue().booleanValue() && receive.getPacket() instanceof SPacketEntityVelocity && (sPacketEntityVelocity = (SPacketEntityVelocity)receive.getPacket()).getEntityID() == Velocity.mc.player.entityId) {
                if (this.horizontal.getValue().floatValue() == 0.0f && this.vertical.getValue().floatValue() == 0.0f) {
                    receive.setCanceled(true);
                    return;
                }
                sPacketEntityVelocity.motionX = (int)((float)sPacketEntityVelocity.motionX * this.horizontal.getValue().floatValue());
                sPacketEntityVelocity.motionY = (int)((float)sPacketEntityVelocity.motionY * this.vertical.getValue().floatValue());
                sPacketEntityVelocity.motionZ = (int)((float)sPacketEntityVelocity.motionZ * this.horizontal.getValue().floatValue());
            }
            if (receive.getPacket() instanceof SPacketEntityStatus && this.bobbers.getValue().booleanValue() && (sPacketEntityStatus = (SPacketEntityStatus)receive.getPacket()).getOpCode() == 31 && (entity = sPacketEntityStatus.getEntity((World)Velocity.mc.world)) instanceof EntityFishHook) {
                sPacketExplosion = (EntityFishHook)entity;
                if (sPacketExplosion.caughtEntity == Velocity.mc.player) {
                    receive.setCanceled(true);
                }
            }
            if (this.explosions.getValue().booleanValue() && receive.getPacket() instanceof SPacketExplosion) {
                if (this.horizontal.getValue().floatValue() == 0.0f && this.vertical.getValue().floatValue() == 0.0f) {
                    receive.setCanceled(true);
                    return;
                }
                sPacketExplosion = (SPacketExplosion)receive.getPacket();
                sPacketExplosion.motionX *= this.horizontal.getValue().floatValue();
                sPacketExplosion.motionY *= this.vertical.getValue().floatValue();
                sPacketExplosion.motionZ *= this.horizontal.getValue().floatValue();
            }
        }
    }

    @Override
    public String getDisplayInfo() {
        return String.valueOf(new StringBuilder().append("H").append(this.horizontal.getValue()).append("%|V").append(this.vertical.getValue()).append("H"));
    }

    @SubscribeEvent
    public void onPush(PushEvent pushEvent) {
        if (pushEvent.getStage() == 0 && this.noPush.getValue().booleanValue() && pushEvent.entity.equals((Object)Velocity.mc.player)) {
            if (this.horizontal.getValue().floatValue() == 0.0f && this.vertical.getValue().floatValue() == 0.0f) {
                pushEvent.setCanceled(true);
                return;
            }
            pushEvent.x = -pushEvent.x * (double)this.horizontal.getValue().floatValue();
            pushEvent.y = -pushEvent.y * (double)this.vertical.getValue().floatValue();
            pushEvent.z = -pushEvent.z * (double)this.horizontal.getValue().floatValue();
        } else if (pushEvent.getStage() == 1 && this.blocks.getValue().booleanValue()) {
            pushEvent.setCanceled(true);
        } else if (pushEvent.getStage() == 2 && this.water.getValue().booleanValue() && Velocity.mc.player != null && Velocity.mc.player.equals((Object)pushEvent.entity)) {
            pushEvent.setCanceled(true);
        }
    }

    @Override
    public void onDisable() {
        if (IceSpeed.getINSTANCE().isOff()) {
            Blocks.ICE.slipperiness = 0.98f;
            Blocks.PACKED_ICE.slipperiness = 0.98f;
            Blocks.FROSTED_ICE.slipperiness = 0.98f;
        }
    }

    public static Velocity getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Velocity();
        }
        return INSTANCE;
    }

    @Override
    public void onUpdate() {
        if (IceSpeed.getINSTANCE().isOff() && this.ice.getValue().booleanValue()) {
            Blocks.ICE.slipperiness = 0.6f;
            Blocks.PACKED_ICE.slipperiness = 0.6f;
            Blocks.FROSTED_ICE.slipperiness = 0.6f;
        }
    }

    public Velocity() {
        super("Velocity", "Allows you to control your velocity", Module.Category.MOVEMENT, true, false, false);
        this.knockBack = this.register(new Setting<Boolean>("KnockBack", true));
        this.noPush = this.register(new Setting<Boolean>("NoPush", true));
        this.horizontal = this.register(new Setting<Float>("Horizontal", Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(100.0f)));
        this.vertical = this.register(new Setting<Float>("Vertical", Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(100.0f)));
        this.explosions = this.register(new Setting<Boolean>("Explosions", true));
        this.bobbers = this.register(new Setting<Boolean>("Bobbers", true));
        this.water = this.register(new Setting<Boolean>("Water", false));
        this.blocks = this.register(new Setting<Boolean>("Blocks", false));
        this.ice = this.register(new Setting<Boolean>("Ice", false));
        this.setInstance();
    }

    static {
        INSTANCE = new Velocity();
    }
}

