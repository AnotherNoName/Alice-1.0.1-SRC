//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.mixin.mixins;

import java.awt.Color;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.module.modules.render.HandChams;
import me.snow.aclient.module.modules.render.Nametags;
import me.snow.aclient.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={RenderPlayer.class})
public class MixinRenderPlayer {
    @Inject(method={"renderEntityName"}, at={@At(value="HEAD")}, cancellable=true)
    public void renderEntityNameHook(AbstractClientPlayer abstractClientPlayer, double d, double d2, double d3, String string, double d4, CallbackInfo callbackInfo) {
        if (Nametags.getInstance().isOn()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"renderRightArm"}, at={@At(value="FIELD", target="Lnet/minecraft/client/model/ModelPlayer;swingProgress:F", opcode=181)}, cancellable=true)
    public void renderRightArmBegin(AbstractClientPlayer abstractClientPlayer, CallbackInfo callbackInfo) {
        if (abstractClientPlayer == Minecraft.getMinecraft().player && HandChams.INSTANCE.isEnabled()) {
            GL11.glPushAttrib((int)1048575);
            GL11.glDisable((int)3008);
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glLineWidth((float)1.5f);
            GL11.glEnable((int)2960);
            GL11.glEnable((int)10754);
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
            if (HandChams.INSTANCE.rainbow.getValue().booleanValue()) {
                Color color = HandChams.INSTANCE.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(RenderUtil.getRainbow(HandChams.INSTANCE.speed.getValue() * 100, 0, (float)HandChams.INSTANCE.saturation.getValue().intValue() / 100.0f, (float)HandChams.INSTANCE.brightness.getValue().intValue() / 100.0f));
                GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)HandChams.INSTANCE.alpha.getValue().intValue() / 255.0f));
            } else {
                Color color = HandChams.INSTANCE.colorSync.getValue() != false ? new Color(Colors.INSTANCE.getCurrentColor().getRed(), Colors.INSTANCE.getCurrentColor().getGreen(), Colors.INSTANCE.getCurrentColor().getBlue(), HandChams.INSTANCE.alpha.getValue()) : new Color(HandChams.INSTANCE.red.getValue(), HandChams.INSTANCE.green.getValue(), HandChams.INSTANCE.blue.getValue(), HandChams.INSTANCE.alpha.getValue());
                GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
            }
        }
    }

    @Inject(method={"renderRightArm"}, at={@At(value="RETURN")}, cancellable=true)
    public void renderRightArmReturn(AbstractClientPlayer abstractClientPlayer, CallbackInfo callbackInfo) {
        if (abstractClientPlayer == Minecraft.getMinecraft().player && HandChams.INSTANCE.isEnabled()) {
            GL11.glEnable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
            GL11.glEnable((int)3008);
            GL11.glPopAttrib();
        }
    }

    @Inject(method={"renderLeftArm"}, at={@At(value="FIELD", target="Lnet/minecraft/client/model/ModelPlayer;swingProgress:F", opcode=181)}, cancellable=true)
    public void renderLeftArmBegin(AbstractClientPlayer abstractClientPlayer, CallbackInfo callbackInfo) {
        if (abstractClientPlayer == Minecraft.getMinecraft().player && HandChams.INSTANCE.isEnabled()) {
            GL11.glPushAttrib((int)1048575);
            GL11.glDisable((int)3008);
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glLineWidth((float)1.5f);
            GL11.glEnable((int)2960);
            GL11.glEnable((int)10754);
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
            if (HandChams.INSTANCE.rainbow.getValue().booleanValue()) {
                Color color = HandChams.INSTANCE.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(RenderUtil.getRainbow(HandChams.INSTANCE.speed.getValue() * 100, 0, (float)HandChams.INSTANCE.saturation.getValue().intValue() / 100.0f, (float)HandChams.INSTANCE.brightness.getValue().intValue() / 100.0f));
                GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)HandChams.INSTANCE.alpha.getValue().intValue() / 255.0f));
            } else {
                Color color = HandChams.INSTANCE.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(RenderUtil.getRainbow(HandChams.INSTANCE.speed.getValue() * 100, 0, (float)HandChams.INSTANCE.saturation.getValue().intValue() / 100.0f, (float)HandChams.INSTANCE.brightness.getValue().intValue() / 100.0f));
                GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)HandChams.INSTANCE.alpha.getValue().intValue() / 255.0f));
            }
        }
    }

    @Inject(method={"renderLeftArm"}, at={@At(value="RETURN")}, cancellable=true)
    public void renderLeftArmReturn(AbstractClientPlayer abstractClientPlayer, CallbackInfo callbackInfo) {
        if (abstractClientPlayer == Minecraft.getMinecraft().player && HandChams.INSTANCE.isEnabled()) {
            GL11.glEnable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
            GL11.glEnable((int)3008);
            GL11.glPopAttrib();
        }
    }
}

