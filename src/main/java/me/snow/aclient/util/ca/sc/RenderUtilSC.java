//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.shader.Framebuffer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.EXTFramebufferObject
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.Sphere
 */
package me.snow.aclient.util.ca.sc;

import java.awt.Color;
import java.util.Collection;
import java.util.Collections;
import me.snow.aclient.mixin.mixins.accessors.IRenderManager;
import me.snow.aclient.util.ColorUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

public class RenderUtilSC {
    private static final /* synthetic */ Minecraft mc;
    private static final /* synthetic */ Frustum frustrum;

    public static void drawBoundingEdgesBox(AxisAlignedBB axisAlignedBB, int n) {
        RenderUtilSC.drawBoundingEdgesBox(axisAlignedBB, new Color(n));
    }

    public static boolean isInViewFrustrum(AxisAlignedBB axisAlignedBB) {
        Entity entity = Minecraft.getMinecraft().getRenderViewEntity();
        frustrum.setPosition(entity.posX, entity.posY, entity.posZ);
        return frustrum.isBoundingBoxInFrustum(axisAlignedBB);
    }

    public static void drawSphere(double d, double d2, double d3, float f, int n, int n2) {
        Sphere sphere = new Sphere();
        GL11.glPushMatrix();
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GL11.glLineWidth((float)1.2f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        sphere.setDrawStyle(100013);
        double d4 = ((IRenderManager)mc.getRenderManager()).getRenderPosX();
        double d5 = ((IRenderManager)mc.getRenderManager()).getRenderPosY();
        double d6 = ((IRenderManager)mc.getRenderManager()).getRenderPosZ();
        GL11.glTranslated((double)(d - d4), (double)(d2 - d5), (double)(d3 - d6));
        sphere.draw(f, n, n2);
        GL11.glLineWidth((float)2.0f);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public static void drawBoundingBox(AxisAlignedBB axisAlignedBB, Color color) {
        AxisAlignedBB axisAlignedBB2 = axisAlignedBB.offset(-RenderUtilSC.mc.getRenderManager().viewerPosX, -RenderUtilSC.mc.getRenderManager().viewerPosY, -RenderUtilSC.mc.getRenderManager().viewerPosZ);
        RenderUtilSC.drawBoxOutline(axisAlignedBB2.expandXyz((double)0.002f), color.getRed() * 255, color.getGreen() * 255, color.getBlue() * 255, color.getAlpha() * 255);
    }

    public static void drawFilledBoundingBox(AxisAlignedBB axisAlignedBB, Color color) {
        AxisAlignedBB axisAlignedBB2 = axisAlignedBB.offset(-RenderUtilSC.mc.getRenderManager().viewerPosX, -RenderUtilSC.mc.getRenderManager().viewerPosY, -RenderUtilSC.mc.getRenderManager().viewerPosZ);
        RenderUtilSC.glColor(color);
        RenderUtilSC.drawFilledBox(axisAlignedBB2);
    }

    public static void drawTracerPointer(float f, float f2, float f3, float f4, float f5, boolean bl, float f6, int n) {
        boolean bl2 = GL11.glIsEnabled((int)3042);
        float f7 = (float)(n >> 24 & 0xFF) / 255.0f;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glPushMatrix();
        RenderUtilSC.hexColor(n);
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)f, (double)f2);
        GL11.glVertex2d((double)(f - f3 / f4), (double)(f2 + f3));
        GL11.glVertex2d((double)f, (double)(f2 + f3 / f5));
        GL11.glVertex2d((double)(f + f3 / f4), (double)(f2 + f3));
        GL11.glVertex2d((double)f, (double)f2);
        GL11.glEnd();
        if (bl) {
            GL11.glLineWidth((float)f6);
            GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)f7);
            GL11.glBegin((int)2);
            GL11.glVertex2d((double)f, (double)f2);
            GL11.glVertex2d((double)(f - f3 / f4), (double)(f2 + f3));
            GL11.glVertex2d((double)f, (double)(f2 + f3 / f5));
            GL11.glVertex2d((double)(f + f3 / f4), (double)(f2 + f3));
            GL11.glVertex2d((double)f, (double)f2);
            GL11.glEnd();
        }
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        if (!bl2) {
            GL11.glDisable((int)3042);
        }
        GL11.glDisable((int)2848);
    }

    public static void blockEsp(Collection<BlockPos> collection, float f, float f2, float f3, float f4, double d, double d2) {
        RenderUtilSC.beginRender();
        GlStateManager.disableTexture2D();
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
        GlStateManager.depthMask((boolean)false);
        double d3 = ((IRenderManager)mc.getRenderManager()).getRenderPosX();
        double d4 = ((IRenderManager)mc.getRenderManager()).getRenderPosY();
        double d5 = ((IRenderManager)mc.getRenderManager()).getRenderPosZ();
        GL11.glColor4d((double)f, (double)f2, (double)f3, (double)f4);
        for (BlockPos blockPos : collection) {
            GlStateManager.pushMatrix();
            double d6 = (double)blockPos.getX() - d3;
            double d7 = (double)blockPos.getY() - d4;
            double d8 = (double)blockPos.getZ() - d5;
            AxisAlignedBB axisAlignedBB = new AxisAlignedBB(d6, d7, d8, d6 + d2, d7 + 1.0, d8 + d);
            RenderUtilSC.vertexBB(axisAlignedBB, f, f2, f3, f4);
            GlStateManager.popMatrix();
        }
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableAlpha();
        RenderUtilSC.endRender();
    }

    public static boolean isInViewFrustrum(Entity entity) {
        return RenderUtilSC.isInViewFrustrum(entity.getEntityBoundingBox()) || entity.ignoreFrustumCheck;
    }

    public static void glColor(Color color) {
        float f = (float)color.getRed() / 255.0f;
        float f2 = (float)color.getGreen() / 255.0f;
        float f3 = (float)color.getBlue() / 255.0f;
        float f4 = (float)color.getAlpha() / 255.0f;
        GlStateManager.color((float)f, (float)f2, (float)f3, (float)f4);
    }

    private static void glColor(int n) {
        float f = (float)(n >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n & 0xFF) / 255.0f;
        GlStateManager.color((float)f2, (float)f3, (float)f4, (float)f);
    }

    public static void vertexBB(AxisAlignedBB axisAlignedBB, float f, float f2, float f3, float f4) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        tessellator.draw();
    }

    public static void draw2DGradientRect(float f, float f2, float f3, float f4, int n, int n2, int n3, int n4) {
        float f5 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(n & 0xFF) / 255.0f;
        float f9 = (float)(n3 >> 24 & 0xFF) / 255.0f;
        float f10 = (float)(n3 >> 16 & 0xFF) / 255.0f;
        float f11 = (float)(n3 >> 8 & 0xFF) / 255.0f;
        float f12 = (float)(n3 & 0xFF) / 255.0f;
        float f13 = (float)(n2 >> 24 & 0xFF) / 255.0f;
        float f14 = (float)(n2 >> 16 & 0xFF) / 255.0f;
        float f15 = (float)(n2 >> 8 & 0xFF) / 255.0f;
        float f16 = (float)(n2 & 0xFF) / 255.0f;
        float f17 = (float)(n4 >> 24 & 0xFF) / 255.0f;
        float f18 = (float)(n4 >> 16 & 0xFF) / 255.0f;
        float f19 = (float)(n4 >> 8 & 0xFF) / 255.0f;
        float f20 = (float)(n4 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos((double)f3, (double)f2, 0.0).color(f18, f19, f20, f17).endVertex();
        bufferBuilder.pos((double)f, (double)f2, 0.0).color(f14, f15, f16, f13).endVertex();
        bufferBuilder.pos((double)f, (double)f4, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)f3, (double)f4, 0.0).color(f10, f11, f12, f9).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void endRender() {
        GlStateManager.enableCull();
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
    }

    public static void drawLine(Vec3d vec3d, Vec3d vec3d2, Color color) {
        double d = ((IRenderManager)mc.getRenderManager()).getRenderPosX();
        double d2 = ((IRenderManager)mc.getRenderManager()).getRenderPosY();
        double d3 = ((IRenderManager)mc.getRenderManager()).getRenderPosZ();
        Vec3d vec3d3 = vec3d.addVector(-d, -d2, -d3);
        Vec3d vec3d4 = vec3d2.addVector(-d, -d2, -d3);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(vec3d3.xCoord, vec3d3.yCoord, vec3d3.zCoord).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(vec3d4.xCoord, vec3d4.yCoord, vec3d4.zCoord).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tessellator.draw();
    }

    public static void drawSelectionBoundingBox(RayTraceResult rayTraceResult, int n, float f) {
        if (rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
            float f2 = (float)(n >> 16 & 0xFF) / 255.0f;
            float f3 = (float)(n >> 8 & 0xFF) / 255.0f;
            float f4 = (float)(n & 0xFF) / 255.0f;
            float f5 = (float)(n >> 24 & 0xFF) / 255.0f;
            RenderUtilSC.beginRender();
            GlStateManager.glLineWidth((float)2.0f);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask((boolean)false);
            BlockPos blockPos = rayTraceResult.getBlockPos();
            IBlockState iBlockState = RenderUtilSC.mc.world.getBlockState(blockPos);
            if (iBlockState.getMaterial() != Material.AIR && RenderUtilSC.mc.world.getWorldBorder().contains(blockPos)) {
                double d = RenderUtilSC.mc.player.lastTickPosX + (RenderUtilSC.mc.player.posX - RenderUtilSC.mc.player.lastTickPosX) * (double)f;
                double d2 = RenderUtilSC.mc.player.lastTickPosY + (RenderUtilSC.mc.player.posY - RenderUtilSC.mc.player.lastTickPosY) * (double)f;
                double d3 = RenderUtilSC.mc.player.lastTickPosZ + (RenderUtilSC.mc.player.posZ - RenderUtilSC.mc.player.lastTickPosZ) * (double)f;
                RenderUtilSC.drawBoxOutline(iBlockState.getSelectedBoundingBox((World)RenderUtilSC.mc.world, blockPos).expandXyz((double)0.002f).offset(-d, -d2, -d3), f2, f3, f4, f5);
            }
            RenderUtilSC.endRender();
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableTexture2D();
        }
    }

    public static float getNametagSize(EntityLivingBase entityLivingBase) {
        return RenderUtilSC.getNametagSize(entityLivingBase.posX, entityLivingBase.posY, entityLivingBase.posZ);
    }

    public static float getNametagSize(double d, double d2, double d3) {
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        double d4 = (double)scaledResolution.getScaleFactor() / Math.pow(scaledResolution.getScaleFactor(), 2.0);
        return (float)d4 + (float)RenderUtilSC.mc.player.getDistance(d, d2, d3) / 7.0f;
    }

    public static void drawFilledBox(AxisAlignedBB axisAlignedBB) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        tessellator.draw();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        tessellator.draw();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        tessellator.draw();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        tessellator.draw();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        tessellator.draw();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        tessellator.draw();
    }

    public static void drawBoundingEdgesBox(AxisAlignedBB axisAlignedBB, Color color) {
        AxisAlignedBB axisAlignedBB2 = axisAlignedBB.offset(-RenderUtilSC.mc.getRenderManager().viewerPosX, -RenderUtilSC.mc.getRenderManager().viewerPosY, -RenderUtilSC.mc.getRenderManager().viewerPosZ);
        RenderUtilSC.drawBoxEdges(axisAlignedBB2.expandXyz((double)0.002f), color.getRed() * 255, color.getGreen() * 255, color.getBlue() * 255, color.getAlpha() * 255);
    }

    public static void draw1DGradientRect(float f, float f2, float f3, float f4, int n, int n2) {
        float f5 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(n & 0xFF) / 255.0f;
        float f9 = (float)(n2 >> 24 & 0xFF) / 255.0f;
        float f10 = (float)(n2 >> 16 & 0xFF) / 255.0f;
        float f11 = (float)(n2 >> 8 & 0xFF) / 255.0f;
        float f12 = (float)(n2 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos((double)f3, (double)f2, 0.0).color(f10, f11, f12, f9).endVertex();
        bufferBuilder.pos((double)f, (double)f2, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)f, (double)f4, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)f3, (double)f4, 0.0).color(f10, f11, f12, f9).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    static {
        mc = Minecraft.getMinecraft();
        frustrum = new Frustum();
    }

    public static void drawFilledBoundingBox(AxisAlignedBB axisAlignedBB, int n) {
        AxisAlignedBB axisAlignedBB2 = axisAlignedBB.offset(-RenderUtilSC.mc.getRenderManager().viewerPosX, -RenderUtilSC.mc.getRenderManager().viewerPosY, -RenderUtilSC.mc.getRenderManager().viewerPosZ);
        RenderUtilSC.glColor(n);
        RenderUtilSC.drawFilledBox(axisAlignedBB2);
    }

    public static void circleESP(double d, double d2, double d3, double d4, Color color) {
        double d5 = d - ((IRenderManager)mc.getRenderManager()).getRenderPosX();
        double d6 = d2 - ((IRenderManager)mc.getRenderManager()).getRenderPosY();
        double d7 = d3 - ((IRenderManager)mc.getRenderManager()).getRenderPosZ();
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        GL11.glBegin((int)1);
        for (int i = 0; i <= 360; ++i) {
            GL11.glVertex3d((double)(d5 + Math.sin((double)i * Math.PI / 180.0) * d4), (double)d6, (double)(d7 + Math.cos((double)i * Math.PI / 180.0) * d4));
        }
        GL11.glEnd();
    }

    public static void drawBoundingBox(AxisAlignedBB axisAlignedBB, int n) {
        RenderUtilSC.drawBoundingBox(axisAlignedBB, new Color(n));
    }

    public static void blockEsp(BlockPos blockPos, int n, double d, double d2) {
        float f = (float)(n >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(n & 0xFF) / 255.0f;
        float f4 = (float)(n >> 24 & 0xFF) / 255.0f;
        RenderUtilSC.blockEsp(Collections.singleton(blockPos), f, f2, f3, f4, d, d2);
    }

    public static void drawEntityBox(Entity entity, int n, double d) {
        IRenderManager iRenderManager = (IRenderManager)mc.getRenderManager();
        double d2 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * d - iRenderManager.getRenderPosX();
        double d3 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * d - iRenderManager.getRenderPosY();
        double d4 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * d - iRenderManager.getRenderPosZ();
        AxisAlignedBB axisAlignedBB = entity.getEntityBoundingBox();
        AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(axisAlignedBB.minX - entity.posX + d2, axisAlignedBB.minY - entity.posY + d3, axisAlignedBB.minZ - entity.posZ + d4, axisAlignedBB.maxX - entity.posX + d2, axisAlignedBB.maxY - entity.posY + d3, axisAlignedBB.maxZ - entity.posZ + d4);
        GL11.glBlendFunc((int)770, (int)771);
        float[] arrf = ColorUtil.intToRGB(n);
        GL11.glColor4f((float)arrf[0], (float)arrf[1], (float)arrf[2], (float)arrf[3]);
        RenderUtilSC.drawFilledBox(axisAlignedBB2);
    }

    public static void drawBox(AxisAlignedBB axisAlignedBB, float f, float f2, float f3, float f4) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
    }

    public static void drawBoxEdges(AxisAlignedBB axisAlignedBB, float f, float f2, float f3, float f4) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ - 0.8 * (axisAlignedBB.maxZ - axisAlignedBB.minZ)).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ + 0.8 * (axisAlignedBB.maxZ - axisAlignedBB.minZ)).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ - 0.8 * (axisAlignedBB.maxZ - axisAlignedBB.minZ)).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ + 0.8 * (axisAlignedBB.maxZ - axisAlignedBB.minZ)).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX - 0.8 * (axisAlignedBB.maxX - axisAlignedBB.minX), axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX - 0.8 * (axisAlignedBB.maxX - axisAlignedBB.minX), axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX + 0.8 * (axisAlignedBB.maxX - axisAlignedBB.minX), axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX + 0.8 * (axisAlignedBB.maxX - axisAlignedBB.minX), axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY + 0.2 * (axisAlignedBB.maxY - axisAlignedBB.minY), axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY + 0.2 * (axisAlignedBB.maxY - axisAlignedBB.minY), axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY + 0.2 * (axisAlignedBB.maxY - axisAlignedBB.minY), axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY + 0.2 * (axisAlignedBB.maxY - axisAlignedBB.minY), axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ - 0.8 * (axisAlignedBB.maxZ - axisAlignedBB.minZ)).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ + 0.8 * (axisAlignedBB.maxZ - axisAlignedBB.minZ)).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ - 0.8 * (axisAlignedBB.maxZ - axisAlignedBB.minZ)).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ + 0.8 * (axisAlignedBB.maxZ - axisAlignedBB.minZ)).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX - 0.8 * (axisAlignedBB.maxX - axisAlignedBB.minX), axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX - 0.8 * (axisAlignedBB.maxX - axisAlignedBB.minX), axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX + 0.8 * (axisAlignedBB.maxX - axisAlignedBB.minX), axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX + 0.8 * (axisAlignedBB.maxX - axisAlignedBB.minX), axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY - 0.2 * (axisAlignedBB.maxY - axisAlignedBB.minY), axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY - 0.2 * (axisAlignedBB.maxY - axisAlignedBB.minY), axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY - 0.2 * (axisAlignedBB.maxY - axisAlignedBB.minY), axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY - 0.2 * (axisAlignedBB.maxY - axisAlignedBB.minY), axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        tessellator.draw();
    }

    public static void beginRender() {
        GL11.glBlendFunc((int)770, (int)771);
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.enableBlend();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
    }

    public static void hexColor(int n) {
        float f = (float)(n >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(n & 0xFF) / 255.0f;
        float f4 = (float)(n >> 24 & 0xFF) / 255.0f;
        GL11.glColor4f((float)f, (float)f2, (float)f3, (float)f4);
    }

    public static void drawEntityBoundingBox(Entity entity, int n, float f) {
        IRenderManager iRenderManager = (IRenderManager)mc.getRenderManager();
        double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f - iRenderManager.getRenderPosX();
        double d2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f - iRenderManager.getRenderPosY();
        double d3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f - iRenderManager.getRenderPosZ();
        AxisAlignedBB axisAlignedBB = entity.getEntityBoundingBox();
        AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(axisAlignedBB.minX - entity.posX + d, axisAlignedBB.minY - entity.posY + d2, axisAlignedBB.minZ - entity.posZ + d3, axisAlignedBB.maxX - entity.posX + d, axisAlignedBB.maxY - entity.posY + d2, axisAlignedBB.maxZ - entity.posZ + d3);
        if (entity != RenderUtilSC.mc.player) {
            float[] arrf = ColorUtil.intToRGB(n);
            RenderUtilSC.drawBoxOutline(axisAlignedBB2.expandXyz((double)0.002f), arrf[0], arrf[1], arrf[2], arrf[3]);
        }
    }

    public static void drawOutlineRect(int n, int n2, int n3, int n4, float f, int n5) {
        int n6;
        if (n < n3) {
            n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n2 < n4) {
            n6 = n2;
            n2 = n4;
            n4 = n6;
        }
        float f2 = (float)(n5 >> 24 & 0xFF) / 255.0f;
        float f3 = (float)(n5 >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(n5 >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(n5 & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)f3, (float)f4, (float)f5, (float)f2);
        GL11.glEnable((int)2848);
        GlStateManager.glLineWidth((float)f);
        bufferBuilder.begin(1, DefaultVertexFormats.POSITION);
        bufferBuilder.pos((double)n, (double)n2, 0.0).endVertex();
        bufferBuilder.pos((double)n3, (double)n2, 0.0).endVertex();
        bufferBuilder.pos((double)n3, (double)n2, 0.0).endVertex();
        bufferBuilder.pos((double)n3, (double)n4, 0.0).endVertex();
        bufferBuilder.pos((double)n3, (double)n4, 0.0).endVertex();
        bufferBuilder.pos((double)n, (double)n4, 0.0).endVertex();
        bufferBuilder.pos((double)n, (double)n4, 0.0).endVertex();
        bufferBuilder.pos((double)n, (double)n2, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GL11.glDisable((int)2848);
    }

    public static void drawBoxOutline(AxisAlignedBB axisAlignedBB, float f, float f2, float f3, float f4) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f, f2, f3, 0.0f).endVertex();
        tessellator.draw();
    }

    public static class OutlineUtils {
        public static void renderThree() {
            GL11.glStencilFunc((int)514, (int)1, (int)15);
            GL11.glStencilOp((int)7680, (int)7680, (int)7680);
            GL11.glPolygonMode((int)1032, (int)6913);
        }

        public static void renderOne(int n) {
            OutlineUtils.checkSetupFBO();
            GL11.glPushAttrib((int)1048575);
            GL11.glDisable((int)3008);
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glLineWidth((float)n);
            GL11.glEnable((int)2848);
            GL11.glEnable((int)2960);
            GL11.glClear((int)1024);
            GL11.glClearStencil((int)15);
            GL11.glStencilFunc((int)512, (int)1, (int)15);
            GL11.glStencilOp((int)7681, (int)7681, (int)7681);
            GL11.glPolygonMode((int)1032, (int)6913);
        }

        public static void renderFour(int n, float f) {
            OutlineUtils.setColor(new Color(n));
            GL11.glDepthMask((boolean)false);
            GL11.glDisable((int)2929);
            GL11.glEnable((int)10754);
            GL11.glPolygonOffset((float)f, (float)-2000000.0f);
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
        }

        public static void setColor(Color color) {
            GL11.glColor4d((double)((float)color.getRed() / 255.0f), (double)((float)color.getGreen() / 255.0f), (double)((float)color.getBlue() / 255.0f), (double)((float)color.getAlpha() / 255.0f));
        }

        public static void renderTwo() {
            GL11.glStencilFunc((int)512, (int)0, (int)15);
            GL11.glStencilOp((int)7681, (int)7681, (int)7681);
            GL11.glPolygonMode((int)1032, (int)6914);
        }

        public static void setupFBO(Framebuffer framebuffer) {
            EXTFramebufferObject.glDeleteRenderbuffersEXT((int)framebuffer.depthBuffer);
            int n = EXTFramebufferObject.glGenRenderbuffersEXT();
            EXTFramebufferObject.glBindRenderbufferEXT((int)36161, (int)n);
            EXTFramebufferObject.glRenderbufferStorageEXT((int)36161, (int)34041, (int)mc.displayWidth, (int)mc.displayHeight);
            EXTFramebufferObject.glFramebufferRenderbufferEXT((int)36160, (int)36128, (int)36161, (int)n);
            EXTFramebufferObject.glFramebufferRenderbufferEXT((int)36160, (int)36096, (int)36161, (int)n);
        }

        public static void renderFive(float f) {
            GL11.glPolygonOffset((float)(-f), (float)2000000.0f);
            GL11.glDisable((int)10754);
            GL11.glEnable((int)2929);
            GL11.glDepthMask((boolean)true);
            GL11.glDisable((int)2960);
            GL11.glDisable((int)2848);
            GL11.glHint((int)3154, (int)4352);
            GL11.glEnable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
            GL11.glEnable((int)3008);
            GL11.glPopAttrib();
        }

        public static void checkSetupFBO() {
            Framebuffer framebuffer = mc.getFramebuffer();
            if (framebuffer.depthBuffer > -1) {
                OutlineUtils.setupFBO(framebuffer);
                framebuffer.depthBuffer = -1;
            }
        }
    }
}

