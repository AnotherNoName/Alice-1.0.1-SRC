//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 */
package me.snow.aclient.notifications;

import me.snow.aclient.AliceMain;
import me.snow.aclient.module.modules.client.HUD;
import me.snow.aclient.util.RenderUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class Notification {
    private final /* synthetic */ Timer timer;
    private /* synthetic */ String text;
    private /* synthetic */ float width;
    private /* synthetic */ long disableTime;

    public void onDraw(int n) {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        if (this.timer.passedMs(this.disableTime)) {
            AliceMain.notificationManager.getNotifications().remove(this);
        }
        RenderUtil.drawRect((float)(scaledResolution.getScaledWidth() - 4) - this.width, n, scaledResolution.getScaledWidth() - 2, n + AliceMain.moduleManager.getModuleByClass(HUD.class).renderer.getFontHeight() + 3, 0x75000000);
        AliceMain.moduleManager.getModuleByClass(HUD.class).renderer.drawString(this.text, (float)scaledResolution.getScaledWidth() - this.width - 3.0f, n + 2, -1, true);
    }

    public Notification(String string, long l) {
        this.timer = new Timer();
        this.text = string;
        this.disableTime = l;
        this.width = AliceMain.moduleManager.getModuleByClass(HUD.class).renderer.getStringWidth(string);
        this.timer.reset();
    }
}

