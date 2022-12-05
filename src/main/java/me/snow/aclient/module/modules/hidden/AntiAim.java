//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.hidden;

import java.util.Comparator;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.manager.RotationManager;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.InteractionUtil;
import me.snow.aclient.util.ca.sc.RotationUtilSC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AntiAim
extends Module {
    private /* synthetic */ Setting<Integer> yawAdd;
    private /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ float currentPitch;
    private /* synthetic */ float currentYaw;
    private /* synthetic */ Setting<PitchMode> pitchMode;
    private /* synthetic */ Setting<Integer> speed;

    @SubscribeEvent
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        EntityPlayer entityPlayer;
        if (updateWalkingPlayerEvent.isCanceled() || !InteractionUtil.canPlaceNormally()) {
            return;
        }
        this.currentYaw = this.mode.getValue() == Mode.SPIN ? (this.currentYaw += (float)this.speed.getValue().intValue()) : (this.mode.getValue() == Mode.JITTER ? (Math.random() > 0.5 ? (float)((double)this.currentYaw + (double)this.speed.getValue().intValue() * Math.random()) : (float)((double)this.currentYaw - (double)this.speed.getValue().intValue() * Math.random())) : ((entityPlayer = this.getNearestEntity()) != null ? RotationManager.calculateAngle(AntiAim.mc.player.getPositionEyes(1.0f), entityPlayer.getPositionEyes(1.0f))[0] - 180.0f : AntiAim.mc.player.rotationYaw));
        this.currentYaw += (float)this.yawAdd.getValue().intValue();
        this.currentYaw = MathHelper.clampAngle((int)((int)this.currentYaw));
        this.currentPitch = this.pitchMode.getValue() == PitchMode.NONE ? AntiAim.mc.player.rotationPitch : (this.pitchMode.getValue() == PitchMode.JITTER ? (Math.random() > 0.5 ? (float)((double)this.currentPitch + (double)this.speed.getValue().intValue() * Math.random()) : (float)((double)this.currentPitch - (double)this.speed.getValue().intValue() * Math.random())) : (this.pitchMode.getValue() == PitchMode.STARE ? ((entityPlayer = this.getNearestEntity()) != null ? RotationManager.calculateAngle(AntiAim.mc.player.getPositionEyes(1.0f), entityPlayer.getPositionEyes(1.0f))[1] : AntiAim.mc.player.rotationPitch) : 90.0f));
        if (this.currentPitch > 89.0f) {
            this.currentPitch = 89.0f;
        } else if (this.currentPitch < -89.0f) {
            this.currentPitch = -89.0f;
        }
        RotationUtilSC.update(this.currentYaw, this.currentPitch);
    }

    private EntityPlayer getNearestEntity() {
        return AntiAim.mc.world.playerEntities.stream().filter(entityPlayer -> entityPlayer != AntiAim.mc.player).filter(entityPlayer -> !AliceMain.friendManager.isFriend(entityPlayer.getName())).filter(entityPlayer -> entityPlayer.getDistanceToEntity((Entity)AntiAim.mc.player) < 10.0f).min(Comparator.comparing(entityPlayer -> Float.valueOf(AntiAim.mc.player.getDistanceToEntity((Entity)entityPlayer)))).orElse(null);
    }

    public AntiAim() {
        super("AntiAim", "Breaks motion prediction in bad clients", Module.Category.PLAYER, true, false, false);
        this.mode = this.register(new Setting<Mode>("Mode", Mode.SPIN));
        this.pitchMode = this.register(new Setting<PitchMode>("Pitch", PitchMode.JITTER));
        this.speed = this.register(new Setting<Integer>("Speed", 10, 1, 55));
        this.yawAdd = this.register(new Setting<Integer>("YawAdd", 10, -180, 180));
        this.currentYaw = 0.0f;
        this.currentPitch = 0.0f;
    }

    private static enum PitchMode {
        NONE,
        JITTER,
        STARE,
        DOWN;

    }

    private static enum Mode {
        SPIN,
        JITTER,
        STARE;

    }
}

