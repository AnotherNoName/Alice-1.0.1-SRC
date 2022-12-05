//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 */
package me.snow.aclient.mixin.mixins;

import me.snow.aclient.AliceMain;
import me.snow.aclient.module.modules.client.CustomFont;
import me.snow.aclient.module.modules.client.HUD;
import me.snow.aclient.module.modules.client.Media;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={FontRenderer.class})
public abstract class MixinFontRenderer {
    @Shadow
    protected abstract int renderString(String var1, float var2, float var3, int var4, boolean var5);

    @Shadow
    protected abstract void renderStringAtPos(String var1, boolean var2);

    @Inject(method={"drawString(Ljava/lang/String;FFIZ)I"}, at={@At(value="HEAD")}, cancellable=true)
    public void renderStringHook(String string, float f, float f2, int n, boolean bl, CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        if (CustomFont.getInstance().isOn() && CustomFont.getInstance().full.getValue().booleanValue() && AliceMain.textManager != null) {
            float f3 = AliceMain.textManager.drawString(string, f, f2, n, bl);
            callbackInfoReturnable.setReturnValue((int)f3);
        }
    }

    @Redirect(method={"drawString(Ljava/lang/String;FFIZ)I"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/FontRenderer;renderString(Ljava/lang/String;FFIZ)I"))
    public int renderStringHook(FontRenderer fontRenderer, String string, float f, float f2, int n, boolean bl) {
        if (AliceMain.moduleManager != null && HUD.getInstance().shadow.getValue().booleanValue() && bl) {
            return this.renderString(string, f - 0.5f, f2 - 0.5f, n, true);
        }
        return this.renderString(string, f, f2, n, bl);
    }

    @Redirect(method={"renderString(Ljava/lang/String;FFIZ)I"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/FontRenderer;renderStringAtPos(Ljava/lang/String;Z)V"))
    public void renderStringAtPosHook(FontRenderer fontRenderer, String string, boolean bl) {
        if (Media.getInstance().isOn()) {
            this.renderStringAtPos(string.replace(Minecraft.getMinecraft().getSession().getUsername(), Media.getInstance().ownName.getValueAsString()), bl);
        } else {
            this.renderStringAtPos(string, bl);
        }
    }
}

