//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.MobEffects
 *  net.minecraft.network.play.server.SPacketEntityEffect
 *  net.minecraft.potion.PotionEffect
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.render;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import net.minecraft.init.MobEffects;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Fullbright
extends Module {
    public /* synthetic */ Setting<Mode> mode;
    public /* synthetic */ Setting<Boolean> effects;
    private /* synthetic */ float previousSetting;

    @Override
    public void onUpdate() {
        if (this.mode.getValue() == Mode.GAMMA) {
            Fullbright.mc.gameSettings.gammaSetting = 1000.0f;
        }
        if (this.mode.getValue() == Mode.POTION) {
            Fullbright.mc.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 5210));
        }
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (receive.getStage() == 0 && receive.getPacket() instanceof SPacketEntityEffect && this.effects.getValue().booleanValue()) {
            SPacketEntityEffect sPacketEntityEffect = (SPacketEntityEffect)receive.getPacket();
            if (Fullbright.mc.player != null && sPacketEntityEffect.getEntityId() == Fullbright.mc.player.getEntityId() && (sPacketEntityEffect.getEffectId() == 9 || sPacketEntityEffect.getEffectId() == 15)) {
                receive.setCanceled(true);
            }
        }
    }

    @Override
    public void onEnable() {
        this.previousSetting = Fullbright.mc.gameSettings.gammaSetting;
    }

    public Fullbright() {
        super("Fullbright", "Makes your game brighter.", Module.Category.RENDER, true, false, false);
        this.mode = this.register(new Setting<Mode>("Mode", Mode.GAMMA));
        this.effects = this.register(new Setting<Boolean>("Effects", false));
        this.previousSetting = 1.0f;
    }

    @Override
    public String getDisplayInfo() {
        switch (this.mode.getValue()) {
            case GAMMA: {
                return "Gamma";
            }
            case POTION: {
                return "Potion";
            }
        }
        return null;
    }

    @Override
    public void onDisable() {
        if (this.mode.getValue() == Mode.POTION) {
            Fullbright.mc.player.removePotionEffect(MobEffects.NIGHT_VISION);
        }
        Fullbright.mc.gameSettings.gammaSetting = this.previousSetting;
    }

    public static enum Mode {
        GAMMA,
        POTION;

    }
}

