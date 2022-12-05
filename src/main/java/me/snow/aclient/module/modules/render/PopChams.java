//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelPlayer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.network.play.server.SPacketEntityStatus
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.module.modules.render;

import com.mojang.authlib.GameProfile;
import java.awt.Color;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.util.NordTessellator;
import me.snow.aclient.util.TotemPopCham;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class PopChams
extends Module {
    public /* synthetic */ Setting<Integer> aL;
    /* synthetic */ EntityOtherPlayerMP player;
    public /* synthetic */ Setting<Boolean> onlyOneEsp;
    public /* synthetic */ Setting<Integer> bF;
    /* synthetic */ double alphaLine;
    public static /* synthetic */ PopChams INSTANCE;
    public /* synthetic */ Setting<Integer> rF;
    /* synthetic */ ModelPlayer playerModel;
    /* synthetic */ double alphaFill;
    public /* synthetic */ Setting<Integer> rL;
    /* synthetic */ Long startTime;
    public /* synthetic */ Setting<Integer> gF;
    public /* synthetic */ Setting<Boolean> self;
    public /* synthetic */ Setting<Integer> fadestart;
    public /* synthetic */ Setting<Double> fadetime;
    public /* synthetic */ Setting<Integer> aF;
    public /* synthetic */ Setting<Integer> gL;
    public /* synthetic */ Setting<Boolean> colorsync;
    public /* synthetic */ Setting<Integer> bL;

    public static void renderEntity(EntityLivingBase entityLivingBase, ModelBase modelBase, float f, float f2, float f3, float f4, float f5, float f6) {
        if (mc.getRenderManager() == null) {
            return;
        }
        float f7 = mc.getRenderPartialTicks();
        double d = entityLivingBase.posX - PopChams.mc.getRenderManager().viewerPosX;
        double d2 = entityLivingBase.posY - PopChams.mc.getRenderManager().viewerPosY;
        double d3 = entityLivingBase.posZ - PopChams.mc.getRenderManager().viewerPosZ;
        GlStateManager.pushMatrix();
        if (entityLivingBase.isSneaking()) {
            d2 -= 0.125;
        }
        float f8 = PopChams.interpolateRotation(entityLivingBase.prevRenderYawOffset, entityLivingBase.renderYawOffset, f7);
        float f9 = PopChams.interpolateRotation(entityLivingBase.prevRotationYawHead, entityLivingBase.rotationYawHead, f7);
        float f10 = f9 - f8;
        float f11 = entityLivingBase.prevRotationPitch + (entityLivingBase.rotationPitch - entityLivingBase.prevRotationPitch) * f7;
        PopChams.renderLivingAt(d, d2, d3);
        float f12 = PopChams.handleRotationFloat(entityLivingBase, f7);
        PopChams.prepareRotations(entityLivingBase);
        float f13 = PopChams.prepareScale(entityLivingBase, f6);
        GlStateManager.enableAlpha();
        modelBase.setLivingAnimations(entityLivingBase, f, f2, f7);
        modelBase.setRotationAngles(f, f2, f12, entityLivingBase.rotationYaw, entityLivingBase.rotationPitch, f13, (Entity)entityLivingBase);
        modelBase.render((Entity)entityLivingBase, f, f2, f12, entityLivingBase.rotationYaw, entityLivingBase.rotationPitch, f13);
        GlStateManager.popMatrix();
    }

    public PopChams() {
        super("PopChams", "Renders when some1 pops", Module.Category.RENDER, true, false, false);
        this.self = this.register(new Setting<Boolean>("SelfPop", false));
        this.colorsync = this.register(new Setting<Boolean>("GlobalColor", false));
        this.rL = this.register(new Setting<Integer>("OutLine-Red", 255, 0, 255));
        this.gL = this.register(new Setting<Integer>("OutLine-Green", 0, 0, 255));
        this.bL = this.register(new Setting<Integer>("OutLine-Blue", 0, 0, 255));
        this.rF = this.register(new Setting<Integer>("Fill-Red", 255, 0, 255));
        this.gF = this.register(new Setting<Integer>("Fill-Green", 0, 0, 255));
        this.bF = this.register(new Setting<Integer>("Fill-Blue", 0, 0, 255));
        this.fadestart = this.register(new Setting<Integer>("FadeStart", 2000, 0, 3000));
        this.fadetime = this.register(new Setting<Double>("FadeTime", 2.0, 0.0, 2.0));
        this.aF = this.register(new Setting<Integer>("FillAlpha", 160, 0, 255));
        this.aL = this.register(new Setting<Integer>("OutLineAlpha", 160, 0, 255));
        this.onlyOneEsp = this.register(new Setting<Boolean>("OnlyOneEsp", true));
        INSTANCE = this;
    }

    double normalize(double d, double d2, double d3) {
        return (d - d2) / (d3 - d2);
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent renderWorldLastEvent) {
        if (this.onlyOneEsp.getValue().booleanValue()) {
            if (this.player == null || PopChams.mc.world == null || PopChams.mc.player == null) {
                return;
            }
            GL11.glLineWidth((float)1.0f);
            Color color = this.colorsync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(this.rL.getValue(), this.gL.getValue(), this.bL.getValue(), this.aL.getValue());
            Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), this.aL.getValue());
            Color color3 = this.colorsync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(this.rF.getValue(), this.gF.getValue(), this.bF.getValue(), this.aF.getValue());
            Color color4 = new Color(color3.getRed(), color3.getGreen(), color3.getBlue(), this.aF.getValue());
            int n = color2.getAlpha();
            int n2 = color4.getAlpha();
            long l = System.currentTimeMillis() - this.startTime - ((Number)this.fadestart.getValue()).longValue();
            if (System.currentTimeMillis() - this.startTime > ((Number)this.fadestart.getValue()).longValue()) {
                double d = this.normalize(l, 0.0, ((Number)this.fadetime.getValue()).doubleValue());
                d = MathHelper.clamp((double)d, (double)0.0, (double)1.0);
                d = -d + 1.0;
                n *= (int)d;
                n2 *= (int)d;
            }
            Color color5 = PopChams.newAlpha(color2, n);
            Color color6 = PopChams.newAlpha(color4, n2);
            if (this.player != null && this.playerModel != null) {
                NordTessellator.prepareGL();
                GL11.glPushAttrib((int)1048575);
                GL11.glEnable((int)2881);
                GL11.glEnable((int)2848);
                if (this.alphaFill > 1.0) {
                    this.alphaFill -= this.fadetime.getValue().doubleValue();
                }
                Color color7 = new Color(color6.getRed(), color6.getGreen(), color6.getBlue(), (int)this.alphaFill);
                if (this.alphaLine > 1.0) {
                    this.alphaLine -= this.fadetime.getValue().doubleValue();
                }
                Color color8 = new Color(color5.getRed(), color5.getGreen(), color5.getBlue(), (int)this.alphaLine);
                PopChams.glColor(color7);
                GL11.glPolygonMode((int)1032, (int)6914);
                PopChams.renderEntity((EntityLivingBase)this.player, (ModelBase)this.playerModel, this.player.limbSwing, this.player.limbSwingAmount, this.player.ticksExisted, this.player.rotationYawHead, this.player.rotationPitch, 1.0f);
                PopChams.glColor(color8);
                GL11.glPolygonMode((int)1032, (int)6913);
                PopChams.renderEntity((EntityLivingBase)this.player, (ModelBase)this.playerModel, this.player.limbSwing, this.player.limbSwingAmount, this.player.ticksExisted, this.player.rotationYawHead, this.player.rotationPitch, 1.0f);
                GL11.glPolygonMode((int)1032, (int)6914);
                GL11.glPopAttrib();
                NordTessellator.releaseGL();
            }
        }
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

    public static float handleRotationFloat(EntityLivingBase entityLivingBase, float f) {
        return 0.0f;
    }

    public static Color newAlpha(Color color, int n) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
    }

    public static void prepareRotations(EntityLivingBase entityLivingBase) {
        GlStateManager.rotate((float)(180.0f - entityLivingBase.rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
    }

    public static void glColor(Color color) {
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
    }

    public static void renderLivingAt(double d, double d2, double d3) {
        GlStateManager.translate((float)((float)d), (float)((float)d2), (float)((float)d3));
    }

    @SubscribeEvent
    public void onPacketReceived(PacketEvent.Receive receive) {
        SPacketEntityStatus sPacketEntityStatus;
        if (receive.getPacket() instanceof SPacketEntityStatus && (sPacketEntityStatus = (SPacketEntityStatus)receive.getPacket()).getOpCode() == 35 && sPacketEntityStatus.getEntity((World)PopChams.mc.world) != null && (this.self.getValue().booleanValue() || sPacketEntityStatus.getEntity((World)PopChams.mc.world).getEntityId() != PopChams.mc.player.getEntityId())) {
            GameProfile gameProfile = new GameProfile(PopChams.mc.player.getUniqueID(), "");
            this.player = new EntityOtherPlayerMP((World)PopChams.mc.world, gameProfile);
            this.player.copyLocationAndAnglesFrom(sPacketEntityStatus.getEntity((World)PopChams.mc.world));
            this.playerModel = new ModelPlayer(0.0f, false);
            this.startTime = System.currentTimeMillis();
            this.playerModel.bipedHead.showModel = false;
            this.playerModel.bipedBody.showModel = false;
            this.playerModel.bipedLeftArmwear.showModel = false;
            this.playerModel.bipedLeftLegwear.showModel = false;
            this.playerModel.bipedRightArmwear.showModel = false;
            this.playerModel.bipedRightLegwear.showModel = false;
            this.alphaFill = this.aF.getValue().intValue();
            this.alphaLine = this.aL.getValue().intValue();
            if (!this.onlyOneEsp.getValue().booleanValue()) {
                TotemPopCham totemPopCham = new TotemPopCham(this.player, this.playerModel, this.startTime, this.alphaFill, this.alphaLine);
            }
        }
    }

    public static void prepareTranslate(EntityLivingBase entityLivingBase, double d, double d2, double d3) {
        PopChams.renderLivingAt(d - PopChams.mc.getRenderManager().viewerPosX, d2 - PopChams.mc.getRenderManager().viewerPosY, d3 - PopChams.mc.getRenderManager().viewerPosZ);
    }
}

