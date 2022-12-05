//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiDisconnected
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.multiplayer.GuiConnecting
 *  net.minecraft.client.multiplayer.ServerData
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.event.world.WorldEvent$Unload
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Feature;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.misc.AutoLog;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoReconnect
extends Module {
    private static /* synthetic */ ServerData serverData;
    private static /* synthetic */ AutoReconnect INSTANCE;
    private final /* synthetic */ Setting<Integer> delay;

    @SubscribeEvent
    public void sendPacket(GuiOpenEvent guiOpenEvent) {
        if (guiOpenEvent.getGui() instanceof GuiDisconnected) {
            this.updateLastConnectedServer();
            if (AutoLog.getInstance().isOff()) {
                GuiDisconnected guiDisconnected = (GuiDisconnected)guiOpenEvent.getGui();
                guiOpenEvent.setGui((GuiScreen)new GuiDisconnectedHook(guiDisconnected));
            }
        }
    }

    public AutoReconnect() {
        super("AutoReconnect", "Reconnects you if you disconnect.", Module.Category.MISC, true, false, false);
        this.delay = this.register(new Setting<Integer>("Delay", 5));
        this.setInstance();
    }

    static {
        INSTANCE = new AutoReconnect();
    }

    public void updateLastConnectedServer() {
        ServerData serverData = mc.getCurrentServerData();
        if (serverData != null) {
            AutoReconnect.serverData = serverData;
        }
    }

    public static AutoReconnect getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AutoReconnect();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload unload) {
        this.updateLastConnectedServer();
    }

    private class GuiDisconnectedHook
    extends GuiDisconnected {
        private final /* synthetic */ Timer timer;

        public void drawScreen(int n, int n2, float f) {
            if (Feature.fullNullCheck()) {
                return;
            }
            super.drawScreen(n, n2, f);
            String string = String.valueOf(new StringBuilder().append("Reconnecting in ").append(MathUtil.round((double)((long)((Integer)AutoReconnect.this.delay.getValue() * 1000) - this.timer.getPassedTimeMs()) / 1000.0, 1)));
            AutoReconnect.this.renderer.drawString(string, this.width / 2 - AutoReconnect.this.renderer.getStringWidth(string) / 2, this.height - 16, 0xFFFFFF, true);
        }

        public void updateScreen() {
            if (this.timer.passedS(((Integer)AutoReconnect.this.delay.getValue()).intValue())) {
                this.mc.displayGuiScreen((GuiScreen)new GuiConnecting(this.parentScreen, this.mc, serverData == null ? this.mc.currentServerData : serverData));
            }
        }

        public GuiDisconnectedHook(GuiDisconnected guiDisconnected) {
            super(guiDisconnected.parentScreen, guiDisconnected.reason, guiDisconnected.message);
            this.timer = new Timer();
            this.timer.reset();
        }
    }
}

