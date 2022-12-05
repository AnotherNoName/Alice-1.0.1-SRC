//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelPlayer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.util;

import java.awt.Color;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.module.modules.render.PopChams;
import me.snow.aclient.util.NordTessellator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class TotemPopCham {
    /* synthetic */ double alphaLine;
    /* synthetic */ double alphaFill;
    /* synthetic */ EntityOtherPlayerMP player;
    private static final /* synthetic */ Minecraft mc;
    /* synthetic */ Long startTime;
    /* synthetic */ ModelPlayer playerModel;

    public static float prepareScale(EntityLivingBase entityLivingBase, float f) {
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale((float)-1.0f, (float)-1.0f, (float)1.0f);
        double d = entityLivingBase.getRenderBoundingBox().maxX - entityLivingBase.getRenderBoundingBox().minX;
        double d2 = entityLivingBase.getRenderBoundingBox().maxZ - entityLivingBase.getRenderBoundingBox().minZ;
        GlStateManager.scale((double)((double)f + d), (double)(f * entityLivingBase.height), (double)((double)f + d2));
        float f2 = 0.0625f;
        GlStateManager.translate((float)0.0f, (float)-1.501f, (float)0.0f);
        return 0.0625f;
    }

    public static void prepareTranslate(EntityLivingBase entityLivingBase, double d, double d2, double d3) {
        TotemPopCham.renderLivingAt(d - TotemPopCham.mc.getRenderManager().viewerPosX, d2 - TotemPopCham.mc.getRenderManager().viewerPosY, d3 - TotemPopCham.mc.getRenderManager().viewerPosZ);
    }

    public static void renderEntity(EntityLivingBase entityLivingBase, ModelBase modelBase, float f, float f2, float f3, float f4, float f5, float f6) {
        if (mc.getRenderManager() == null) {
            return;
        }
        float f7 = mc.getRenderPartialTicks();
        double d = entityLivingBase.posX - TotemPopCham.mc.getRenderManager().viewerPosX;
        double d2 = entityLivingBase.posY - TotemPopCham.mc.getRenderManager().viewerPosY;
        double d3 = entityLivingBase.posZ - TotemPopCham.mc.getRenderManager().viewerPosZ;
        GlStateManager.pushMatrix();
        if (entityLivingBase.isSneaking()) {
            d2 -= 0.125;
        }
        float f8 = TotemPopCham.interpolateRotation(entityLivingBase.prevRenderYawOffset, entityLivingBase.renderYawOffset, f7);
        float f9 = TotemPopCham.interpolateRotation(entityLivingBase.prevRotationYawHead, entityLivingBase.rotationYawHead, f7);
        float f10 = f9 - f8;
        float f11 = entityLivingBase.prevRotationPitch + (entityLivingBase.rotationPitch - entityLivingBase.prevRotationPitch) * f7;
        TotemPopCham.renderLivingAt(d, d2, d3);
        float f12 = TotemPopCham.handleRotationFloat(entityLivingBase, f7);
        TotemPopCham.prepareRotations(entityLivingBase);
        float f13 = TotemPopCham.prepareScale(entityLivingBase, f6);
        GlStateManager.enableAlpha();
        modelBase.setLivingAnimations(entityLivingBase, f, f2, f7);
        modelBase.setRotationAngles(f, f2, f12, entityLivingBase.rotationYawHead, entityLivingBase.rotationPitch, f13, (Entity)entityLivingBase);
        modelBase.render((Entity)entityLivingBase, f, f2, f12, entityLivingBase.rotationYawHead, entityLivingBase.rotationPitch, f13);
        GlStateManager.popMatrix();
    }

    public static void prepareRotations(EntityLivingBase entityLivingBase) {
        GlStateManager.rotate((float)(180.0f - entityLivingBase.rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
    }

    public static float handleRotationFloat(EntityLivingBase entityLivingBase, float f) {
        return 0.0f;
    }

    public static Color newAlpha(Color color, int n) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
    }

    public static void renderLivingAt(double d, double d2, double d3) {
        GlStateManager.translate((float)((float)d), (float)((float)d2), (float)((float)d3));
    }

    static {
        mc = Minecraft.getMinecraft();
    }

    public TotemPopCham(EntityOtherPlayerMP entityOtherPlayerMP, ModelPlayer modelPlayer, Long l, double d, double d2) {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.player = entityOtherPlayerMP;
        this.playerModel = modelPlayer;
        this.startTime = l;
        this.alphaFill = d;
        this.alphaLine = d;
    }

    double normalize(double d, double d2, double d3) {
        return (d - d2) / (d3 - d2);
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent renderWorldLastEvent) {
        if (this.player == null || TotemPopCham.mc.world == null || TotemPopCham.mc.player == null) {
            return;
        }
        GL11.glLineWidth((float)1.0f);
        Color color = PopChams.INSTANCE.colorsync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(PopChams.INSTANCE.rL.getValue(), PopChams.INSTANCE.gL.getValue(), PopChams.INSTANCE.bL.getValue(), PopChams.INSTANCE.aL.getValue());
        Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), PopChams.INSTANCE.aL.getValue());
        Color color3 = PopChams.INSTANCE.colorsync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(PopChams.INSTANCE.rF.getValue(), PopChams.INSTANCE.gF.getValue(), PopChams.INSTANCE.bF.getValue(), PopChams.INSTANCE.aF.getValue());
        Color color4 = new Color(color3.getRed(), color3.getGreen(), color3.getBlue(), PopChams.INSTANCE.aF.getValue());
        int n = color2.getAlpha();
        int n2 = color4.getAlpha();
        long l = System.currentTimeMillis() - this.startTime - ((Number)PopChams.INSTANCE.fadestart.getValue()).longValue();
        if (System.currentTimeMillis() - this.startTime > ((Number)PopChams.INSTANCE.fadestart.getValue()).longValue()) {
            double d = this.normalize(l, 0.0, ((Number)PopChams.INSTANCE.fadetime.getValue()).doubleValue());
            d = MathHelper.clamp((double)d, (double)0.0, (double)1.0);
            d = -d + 1.0;
            n *= (int)d;
            n2 *= (int)d;
        }
        Color color5 = TotemPopCham.newAlpha(color2, n);
        Color color6 = TotemPopCham.newAlpha(color4, n2);
        if (this.player != null && this.playerModel != null) {
            NordTessellator.prepareGL();
            GL11.glPushAttrib((int)1048575);
            GL11.glEnable((int)2881);
            GL11.glEnable((int)2848);
            if (this.alphaFill > 1.0) {
                this.alphaFill -= PopChams.INSTANCE.fadetime.getValue().doubleValue();
            }
            Color color7 = new Color(color6.getRed(), color6.getGreen(), color6.getBlue(), (int)this.alphaFill);
            if (this.alphaLine > 1.0) {
                this.alphaLine -= PopChams.INSTANCE.fadetime.getValue().doubleValue();
            }
            Color color8 = new Color(color5.getRed(), color5.getGreen(), color5.getBlue(), (int)this.alphaLine);
            TotemPopCham.glColor(color7);
            GL11.glPolygonMode((int)1032, (int)6914);
            TotemPopCham.renderEntity((EntityLivingBase)this.player, (ModelBase)this.playerModel, this.player.limbSwing, this.player.limbSwingAmount, this.player.ticksExisted, this.player.rotationYawHead, this.player.rotationPitch, 1.0f);
            TotemPopCham.glColor(color8);
            GL11.glPolygonMode((int)1032, (int)6913);
            TotemPopCham.renderEntity((EntityLivingBase)this.player, (ModelBase)this.playerModel, this.player.limbSwing, this.player.limbSwingAmount, this.player.ticksExisted, this.player.rotationYawHead, this.player.rotationPitch, 1.0f);
            GL11.glPolygonMode((int)1032, (int)6914);
            GL11.glPopAttrib();
            NordTessellator.releaseGL();
        }
    }

    public static void glColor(Color color) {
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
    }

    public static float interpolateRotation(float f, float f2, float f3) {
        float f4;
        for (f4 = f2 - f; f4 < -180.0f; f4 += 360.0f) {
        }
        while (f4 >= 180.0f) {
            f4 -= 360.0f;
        }
        return f + f3 * f4;
    }
}

