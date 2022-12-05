//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package me.snow.aclient.module.modules.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.skid.oyvey.HoleUtilSafety;
import me.snow.aclient.util.skid.oyvey.NullUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Safety
extends Module {
    /* synthetic */ SafetyMode safety;

    @Override
    public String getDisplayInfo() {
        return this.safety != null ? this.safety.toString() : "";
    }

    @SubscribeEvent
    public void onUpdate(TickEvent.ClientTickEvent clientTickEvent) {
        if (NullUtils.nullCheck()) {
            return;
        }
        if (Safety.mc.world.getBlockState(Safety.mc.player.getPosition()).getMaterial().isSolid()) {
            this.safety = SafetyMode.Safe;
            return;
        }
        if (HoleUtilSafety.isHole(Safety.mc.player.getPosition())) {
            this.safety = SafetyMode.Safe;
            return;
        }
        this.safety = SafetyMode.Unsafe;
    }

    public Safety() {
        super("Safety", "safeeeeeee", Module.Category.CLIENT, true, false, false);
    }

    static enum SafetyMode {
        Safe(ChatFormatting.GREEN),
        Unsafe(ChatFormatting.RED);

        /* synthetic */ ChatFormatting color;

        private SafetyMode(ChatFormatting chatFormatting) {
            this.color = chatFormatting;
        }

        public String toString() {
            return String.valueOf(new StringBuilder().append(this.color.toString()).append(super.toString()));
        }
    }
}

