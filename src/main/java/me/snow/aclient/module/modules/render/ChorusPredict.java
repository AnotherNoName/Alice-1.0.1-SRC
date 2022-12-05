//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.network.play.server.SPacketSoundEffect
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.render;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.RenderUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChorusPredict
extends Module {
    private final /* synthetic */ Setting<Integer> red;
    private final /* synthetic */ Setting<Integer> alpha;
    private final /* synthetic */ Setting<Integer> green;
    private /* synthetic */ BlockPos pos;
    private final /* synthetic */ Setting<Integer> renderDelay;
    private final /* synthetic */ Timer renderTimer;
    private final /* synthetic */ Setting<Integer> outlineAlpha;
    private final /* synthetic */ Setting<Integer> blue;
    private final /* synthetic */ Setting<Boolean> debug;

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        if (this.pos != null) {
            if (this.renderTimer.passed(this.renderDelay.getValue().intValue())) {
                this.pos = null;
                return;
            }
            RenderUtil.drawBoxESP(this.pos, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.outlineAlpha.getValue()), 1.5f, true, true, this.alpha.getValue());
        }
    }

    public ChorusPredict() {
        super("ChorusView", "Predicts Chorus", Module.Category.RENDER, true, false, false);
        this.renderTimer = new Timer();
        this.debug = this.register(new Setting<Boolean>("Debug", true));
        this.renderDelay = this.register(new Setting<Integer>("RenderDelay", 4000, 0, 4000));
        this.red = this.register(new Setting<Integer>("Red", 255, 0, 255));
        this.green = this.register(new Setting<Integer>("Green", 255, 0, 255));
        this.blue = this.register(new Setting<Integer>("Blue", 255, 0, 255));
        this.alpha = this.register(new Setting<Integer>("Alpha", 150, 0, 255));
        this.outlineAlpha = this.register(new Setting<Integer>("OL-Alpha", 200, 0, 255));
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        SPacketSoundEffect sPacketSoundEffect;
        if (receive.getPacket() instanceof SPacketSoundEffect && ((sPacketSoundEffect = (SPacketSoundEffect)receive.getPacket()).getSound() == SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT || sPacketSoundEffect.getSound() == SoundEvents.ENTITY_ENDERMEN_TELEPORT)) {
            this.renderTimer.reset2();
            this.pos = new BlockPos(sPacketSoundEffect.getX(), sPacketSoundEffect.getY(), sPacketSoundEffect.getZ());
            if (this.debug.getValue().booleanValue()) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("A player chorused to: ").append((Object)ChatFormatting.AQUA).append("X: ").append(this.pos.getX()).append(", Y: ").append(this.pos.getY()).append(", Z: ").append(this.pos.getZ())));
            }
        }
    }
}

