//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.gui.GuiNewChat
 *  net.minecraft.client.gui.ScaledResolution
 */
package me.snow.aclient.mixin.mixins;

import me.snow.aclient.AliceMain;
import me.snow.aclient.core.gui.custom.GuiCustomNewChat;
import me.snow.aclient.module.modules.client.HUD;
import me.snow.aclient.module.modules.render.Crosshair;
import me.snow.aclient.module.modules.render.NoRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={GuiIngame.class})
public class MixinGuiIngame
extends Gui {
    @Mutable
    @Shadow
    @Final
    public GuiNewChat persistantChatGUI;

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    public void init(Minecraft minecraft, CallbackInfo callbackInfo) {
        this.persistantChatGUI = new GuiCustomNewChat(minecraft);
    }

    @Inject(method={"renderPortal"}, at={@At(value="HEAD")}, cancellable=true)
    protected void renderPortalHook(float f, ScaledResolution scaledResolution, CallbackInfo callbackInfo) {
        if (NoRender.getInstance().isOn() && NoRender.getInstance().portal.getValue().booleanValue()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"renderPumpkinOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    protected void renderPumpkinOverlayHook(ScaledResolution scaledResolution, CallbackInfo callbackInfo) {
        if (NoRender.getInstance().isOn() && NoRender.getInstance().pumpkin.getValue().booleanValue()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"renderPotionEffects"}, at={@At(value="HEAD")}, cancellable=true)
    protected void renderPotionEffectsHook(ScaledResolution scaledResolution, CallbackInfo callbackInfo) {
        if (AliceMain.moduleManager != null && !HUD.getInstance().potionIcons.getValue().booleanValue()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"renderAttackIndicator"}, at={@At(value="HEAD")}, cancellable=true)
    public void onRenderAttackIndicator(float f, ScaledResolution scaledResolution, CallbackInfo callbackInfo) {
        Crosshair crosshair = new Crosshair();
        if (crosshair.isEnabled()) {
            callbackInfo.cancel();
        }
    }
}

