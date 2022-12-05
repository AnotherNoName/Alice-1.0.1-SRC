//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.culling.ICamera
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.util.ca.util;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import me.snow.aclient.util.RenderBuilder;
import me.snow.aclient.util.Util;
import me.snow.aclient.util.ca.util.Colour;
import me.snow.aclient.util.ca.util.EntityUtilCa;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class RenderUtilCa
implements Util {
    static /* synthetic */ Random random;
    public static /* synthetic */ ICamera camera;
    private static final /* synthetic */ Map<Integer, Boolean> glCapMap;

    public static void drawCornerVertices(double d, double d2, double d3, double d4, double d5, double d6, Color color) {
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.pos(d, d2, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d, d2, d6 - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d, d2, d3 + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d4, d2, d6 - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d4, d2, d3 + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d4 - 0.8, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d4 - 0.8, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d + 0.8, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d + 0.8, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d, d2 + 0.2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d, d2 + 0.2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d4, d2 + 0.2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d4, d2 + 0.2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d, d5, d6 - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d, d5, d3 + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d4, d5, d6 - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d4, d5, d3 + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d4 - 0.8, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d4 - 0.8, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d + 0.8, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d + 0.8, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d, d5 - 0.2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d, d5 - 0.2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d4, d5 - 0.2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        bufferBuilder.pos(d4, d5 - 0.2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
    }

    public static void drawBoxESP(BlockPos blockPos, Color color, Color color2, float f, boolean bl, boolean bl2, boolean bl3) {
        if (bl2) {
            RenderUtilCa.drawBox(blockPos, color, bl3);
        }
        if (bl) {
            RenderUtilCa.drawBlockOutline(blockPos, color2, f, bl3);
        }
    }

    public static Color getContrastColor(Color color) {
        double d = (299.0f * (float)color.getRed() + 587.0f * (float)color.getGreen() + 114.0f * (float)color.getBlue()) / 1000.0f;
        return d >= 128.0 ? Color.black : Color.white;
    }

    public static void drawBoxESP(BlockPos blockPos, Color color, Color color2, float f, boolean bl, boolean bl2, boolean bl3, double d, boolean bl4, boolean bl5, boolean bl6, boolean bl7, int n) {
        if (bl2) {
            RenderUtilCa.drawBox(blockPos, new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()), d, bl4, bl6, n);
        }
        if (bl) {
            RenderUtilCa.drawBlockOutline(blockPos, color2, f, bl3, d, bl5, bl7);
        }
    }

    private static void doVerticies(AxisAlignedBB axisAlignedBB, Colour colour, int n, BufferBuilder bufferBuilder, int n2, boolean bl) {
        if ((n2 & 0x20) != 0 || n2 == -1) {
            RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            if (bl) {
                RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            }
        }
        if ((n2 & 0x10) != 0 || n2 == -1) {
            RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            if (bl) {
                RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            }
        }
        if ((n2 & 4) != 0 || n2 == -1) {
            RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            if (bl) {
                RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            }
        }
        if ((n2 & 8) != 0 || n2 == -1) {
            RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            if (bl) {
                RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            }
        }
        if ((n2 & 2) != 0 || n2 == -1) {
            RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            if (bl) {
                RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            }
        }
        if ((n2 & 1) != 0 || n2 == -1) {
            RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
            RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            if (bl) {
                RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, n, bufferBuilder);
            }
        }
    }

    public static AxisAlignedBB getBoundingBox(BlockPos blockPos) {
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosZ);
        return axisAlignedBB;
    }

    public static void drawCircle(float f, float f2, float f3, float f4, Colour colour) {
        BlockPos blockPos = new BlockPos((double)f, (double)f2, (double)f3);
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosZ);
        RenderUtilCa.drawCircleVertices(axisAlignedBB, f4, colour);
    }

    public static void drawBBBox(AxisAlignedBB axisAlignedBB, Color color, int n) {
        AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(axisAlignedBB.minX - RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtilCa.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtilCa.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Objects.requireNonNull(RenderUtilCa.mc.getRenderViewEntity()).posX, RenderUtilCa.mc.getRenderViewEntity().posY, RenderUtilCa.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB2.minX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB2.minY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB2.minZ + RenderUtilCa.mc.getRenderManager().viewerPosZ, axisAlignedBB2.maxX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB2.maxY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB2.maxZ + RenderUtilCa.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask((boolean)false);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            RenderGlobal.renderFilledBox((AxisAlignedBB)axisAlignedBB2, (float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)n / 255.0f));
            GL11.glDisable((int)2848);
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    public static void drawGradientBlockOutline(AxisAlignedBB axisAlignedBB, Color color, Color color2, float f) {
        float f2 = (float)color.getRed() / 255.0f;
        float f3 = (float)color.getGreen() / 255.0f;
        float f4 = (float)color.getBlue() / 255.0f;
        float f5 = (float)color.getAlpha() / 255.0f;
        float f6 = (float)color2.getRed() / 255.0f;
        float f7 = (float)color2.getGreen() / 255.0f;
        float f8 = (float)color2.getBlue() / 255.0f;
        float f9 = (float)color2.getAlpha() / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f6, f7, f8, f9).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f6, f7, f8, f9).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f6, f7, f8, f9).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f6, f7, f8, f9).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f6, f7, f8, f9).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f6, f7, f8, f9).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f6, f7, f8, f9).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f6, f7, f8, f9).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void addChainedGlowBoxVertices(double d, double d2, double d3, double d4, double d5, double d6, Color color, Color color2) {
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.pos(d, d2, d3).color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d4, d2, d3).color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d4, d2, d6).color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d, d2, d6).color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d, d5, d3).color((float)color2.getRed() / 255.0f, (float)color2.getGreen() / 255.0f, (float)color2.getBlue() / 255.0f, (float)color2.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d, d5, d6).color((float)color2.getRed() / 255.0f, (float)color2.getGreen() / 255.0f, (float)color2.getBlue() / 255.0f, (float)color2.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d4, d5, d6).color((float)color2.getRed() / 255.0f, (float)color2.getGreen() / 255.0f, (float)color2.getBlue() / 255.0f, (float)color2.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d4, d5, d3).color((float)color2.getRed() / 255.0f, (float)color2.getGreen() / 255.0f, (float)color2.getBlue() / 255.0f, (float)color2.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d, d2, d3).color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d, d5, d3).color((float)color2.getRed() / 255.0f, (float)color2.getGreen() / 255.0f, (float)color2.getBlue() / 255.0f, (float)color2.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d4, d5, d3).color((float)color2.getRed() / 255.0f, (float)color2.getGreen() / 255.0f, (float)color2.getBlue() / 255.0f, (float)color2.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d4, d2, d3).color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d4, d2, d3).color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d4, d5, d3).color((float)color2.getRed() / 255.0f, (float)color2.getGreen() / 255.0f, (float)color2.getBlue() / 255.0f, (float)color2.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d4, d5, d6).color((float)color2.getRed() / 255.0f, (float)color2.getGreen() / 255.0f, (float)color2.getBlue() / 255.0f, (float)color2.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d4, d2, d6).color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d, d2, d6).color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d4, d2, d6).color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d4, d5, d6).color((float)color2.getRed() / 255.0f, (float)color2.getGreen() / 255.0f, (float)color2.getBlue() / 255.0f, (float)color2.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d, d5, d6).color((float)color2.getRed() / 255.0f, (float)color2.getGreen() / 255.0f, (float)color2.getBlue() / 255.0f, (float)color2.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d, d2, d3).color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d, d2, d6).color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d, d5, d6).color((float)color2.getRed() / 255.0f, (float)color2.getGreen() / 255.0f, (float)color2.getBlue() / 255.0f, (float)color2.getAlpha() / 255.0f).endVertex();
        bufferBuilder.pos(d, d5, d3).color((float)color2.getRed() / 255.0f, (float)color2.getGreen() / 255.0f, (float)color2.getBlue() / 255.0f, (float)color2.getAlpha() / 255.0f).endVertex();
    }

    public static void drawOutlineLine(double d, double d2, double d3, double d4, double d5, int n) {
        double d6;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)((float)d5));
        if (d < d3) {
            d6 = d;
            d = d3;
            d3 = d6;
        }
        if (d2 < d4) {
            d6 = d2;
            d2 = d4;
            d4 = d6;
        }
        float f = (float)(n >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(d, d4, 0.0).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(d3, d4, 0.0).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(d3, d2, 0.0).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(d, d2, 0.0).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(d, d4, 0.0).color(f2, f3, f4, f).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void glBillboardDistanceScaled(float f, float f2, float f3, EntityPlayer entityPlayer, float f4) {
        RenderUtilCa.glBillboard(f, f2, f3);
        int n = (int)entityPlayer.getDistance((double)f, (double)f2, (double)f3);
        float f5 = (float)n / 2.0f / (2.0f + (2.0f - f4));
        if (f5 < 1.0f) {
            f5 = 1.0f;
        }
        GlStateManager.scale((float)f5, (float)f5, (float)f5);
    }

    public static String getRandomFont() {
        String[] arrstring = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(Locale.ENGLISH);
        return arrstring[random.nextInt(arrstring.length)];
    }

    public static void drawGradientBlockOutline(BlockPos blockPos, Color color, Color color2, float f, double d) {
        IBlockState iBlockState = RenderUtilCa.mc.world.getBlockState(blockPos);
        Vec3d vec3d = EntityUtilCa.interpolateEntity((Entity)RenderUtilCa.mc.player, mc.getRenderPartialTicks());
        RenderUtilCa.drawGradientBlockOutline(iBlockState.getSelectedBoundingBox((World)RenderUtilCa.mc.world, blockPos).expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord).addCoord(0.0, d, 0.0), color, color2, f);
    }

    public void resetCaps() {
        glCapMap.forEach((arg_0, arg_1) -> this.setGlState(arg_0, arg_1));
    }

    public static void drawSelectionGlowFilledBox(AxisAlignedBB axisAlignedBB, double d, Color color, Color color2) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        RenderUtilCa.addChainedGlowBoxVertices(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX, axisAlignedBB.maxY + d, axisAlignedBB.maxZ, color, color2);
        tessellator.draw();
    }

    public static void drawGlError(BlockPos blockPos, double d, double d2, double d3, Color color) {
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Objects.requireNonNull(RenderUtilCa.mc.getRenderViewEntity()).posX, RenderUtilCa.mc.getRenderViewEntity().posY, RenderUtilCa.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtilCa.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtilCa.mc.getRenderManager().viewerPosZ))) {
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferBuilder = tessellator.getBuffer();
            bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
            RenderUtilCa.drawCornerVertices(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX + d2, axisAlignedBB.maxY + d, axisAlignedBB.maxZ + d3, color);
            tessellator.draw();
        }
    }

    static {
        random = new Random();
        glCapMap = new HashMap<Integer, Boolean>();
        camera = new Frustum();
    }

    public static void hexColor(int n) {
        float f = (float)(n >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(n & 0xFF) / 255.0f;
        float f4 = (float)(n >> 24 & 0xFF) / 255.0f;
        GL11.glColor4f((float)f, (float)f2, (float)f3, (float)f4);
    }

    public static void drawBlockOutline(BlockPos blockPos, Color color, float f, boolean bl) {
        IBlockState iBlockState = RenderUtilCa.mc.world.getBlockState(blockPos);
        if ((bl || iBlockState.getMaterial() != Material.AIR) && RenderUtilCa.mc.world.getWorldBorder().contains(blockPos)) {
            Vec3d vec3d = EntityUtilCa.interpolateEntity((Entity)RenderUtilCa.mc.player, mc.getRenderPartialTicks());
            RenderUtilCa.drawBlockOutline(iBlockState.getSelectedBoundingBox((World)RenderUtilCa.mc.world, blockPos).expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord), color, f);
        }
    }

    public static void drawBoundingBoxWithSides(AxisAlignedBB axisAlignedBB, int n, Colour colour, int n2, int n3) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.glLineWidth((float)n);
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        RenderUtilCa.doVerticies(axisAlignedBB, colour, n2, bufferBuilder, n3, true);
        tessellator.draw();
    }

    public static void drawBlockOutline(BlockPos blockPos, Color color, float f, boolean bl, double d, boolean bl2, boolean bl3) {
        if (bl2) {
            Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
            RenderUtilCa.drawGradientBlockOutline(blockPos, bl3 ? color2 : color, bl3 ? color : color2, f, d);
            return;
        }
        IBlockState iBlockState = RenderUtilCa.mc.world.getBlockState(blockPos);
        if ((bl || iBlockState.getMaterial() != Material.AIR) && RenderUtilCa.mc.world.getWorldBorder().contains(blockPos)) {
            AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosY + d, (double)(blockPos.getZ() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosZ);
            RenderUtilCa.drawBlockOutline(axisAlignedBB.expandXyz((double)0.002f), color, f);
        }
    }

    public static void drawBoxESP(BlockPos blockPos, Color color, float f, boolean bl, boolean bl2, int n, Double d) {
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosY + d, (double)(blockPos.getZ() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Objects.requireNonNull(RenderUtilCa.mc.getRenderViewEntity()).posX, RenderUtilCa.mc.getRenderViewEntity().posY, RenderUtilCa.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtilCa.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtilCa.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask((boolean)false);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            GL11.glLineWidth((float)f);
            if (bl2) {
                RenderGlobal.renderFilledBox((AxisAlignedBB)axisAlignedBB, (float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)n / 255.0f));
            }
            if (bl) {
                RenderGlobal.drawBoundingBox((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ, (double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ, (float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
            }
            GL11.glDisable((int)2848);
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    public static void drawBoxESP(BlockPos blockPos, Color color, Color color2, double d, boolean bl, boolean bl2, Float f) {
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosY + (double)f.floatValue(), (double)(blockPos.getZ() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Objects.requireNonNull(RenderUtilCa.mc.getRenderViewEntity()).posX, RenderUtilCa.mc.getRenderViewEntity().posY, RenderUtilCa.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtilCa.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtilCa.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask((boolean)false);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            GL11.glLineWidth((float)((float)d));
            if (bl2) {
                RenderGlobal.renderFilledBox((AxisAlignedBB)axisAlignedBB, (float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
            }
            if (bl) {
                RenderGlobal.drawBoundingBox((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ, (double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ, (float)((float)color2.getRed() / 255.0f), (float)((float)color2.getGreen() / 255.0f), (float)((float)color2.getBlue() / 255.0f), (float)((float)color2.getAlpha() / 255.0f));
            }
            GL11.glDisable((int)2848);
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    public static void drawBox(BlockPos blockPos, double d, Colour colour, int n) {
        RenderUtilCa.drawBox(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0, d, 1.0, colour, colour.getAlpha(), n);
    }

    public static void drawColumn(float f, float f2, float f3, float f4, Colour colour, int n, double d) {
        double d2 = d / (double)n;
        float f5 = f4 / (float)n * (float)d;
        BlockPos blockPos = new BlockPos((double)f, (double)f2, (double)f3);
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Objects.requireNonNull(RenderUtilCa.mc.getRenderViewEntity()).posX, RenderUtilCa.mc.getRenderViewEntity().posY, RenderUtilCa.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtilCa.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtilCa.mc.getRenderManager().viewerPosZ))) {
            for (int i = 0; i <= n; ++i) {
                axisAlignedBB = new AxisAlignedBB(axisAlignedBB.minX, axisAlignedBB.minY + d2 * (double)i, axisAlignedBB.minZ, axisAlignedBB.maxX, axisAlignedBB.maxY + d2 * (double)i, axisAlignedBB.maxZ);
                RenderUtilCa.drawCircleVertices(axisAlignedBB, f5 * (float)i, colour);
            }
        }
    }

    public static void drawBBOutline(AxisAlignedBB axisAlignedBB, Colour colour, int n) {
    }

    public static void drawBoundingBox(AxisAlignedBB axisAlignedBB, double d, Colour colour, int n) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.glLineWidth((float)((float)d));
        colour.glColor();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, colour.getAlpha(), bufferBuilder);
        RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, colour.getAlpha(), bufferBuilder);
        RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, colour.getAlpha(), bufferBuilder);
        RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, colour.getAlpha(), bufferBuilder);
        RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, colour.getAlpha(), bufferBuilder);
        RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
        RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
        RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, colour.getAlpha(), bufferBuilder);
        RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, colour, colour.getAlpha(), bufferBuilder);
        RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
        RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
        RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, colour, n, bufferBuilder);
        RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
        RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, colour, colour.getAlpha(), bufferBuilder);
        RenderUtilCa.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
        RenderUtilCa.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, colour, n, bufferBuilder);
        tessellator.draw();
    }

    public static void drawGradientPlane(BlockPos blockPos, EnumFacing enumFacing, Color color, Color color2, double d) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        IBlockState iBlockState = RenderUtilCa.mc.world.getBlockState(blockPos);
        Vec3d vec3d = EntityUtilCa.interpolateEntity((Entity)RenderUtilCa.mc.player, mc.getRenderPartialTicks());
        AxisAlignedBB axisAlignedBB = iBlockState.getSelectedBoundingBox((World)RenderUtilCa.mc.world, blockPos).expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord).addCoord(0.0, d, 0.0);
        float f = (float)color.getRed() / 255.0f;
        float f2 = (float)color.getGreen() / 255.0f;
        float f3 = (float)color.getBlue() / 255.0f;
        float f4 = (float)color.getAlpha() / 255.0f;
        float f5 = (float)color2.getRed() / 255.0f;
        float f6 = (float)color2.getGreen() / 255.0f;
        float f7 = (float)color2.getBlue() / 255.0f;
        float f8 = (float)color2.getAlpha() / 255.0f;
        double d2 = 0.0;
        double d3 = 0.0;
        double d4 = 0.0;
        double d5 = 0.0;
        double d6 = 0.0;
        double d7 = 0.0;
        if (enumFacing == EnumFacing.DOWN) {
            d2 = axisAlignedBB.minX;
            d5 = axisAlignedBB.maxX;
            d3 = axisAlignedBB.minY;
            d6 = axisAlignedBB.minY;
            d4 = axisAlignedBB.minZ;
            d7 = axisAlignedBB.maxZ;
        } else if (enumFacing == EnumFacing.UP) {
            d2 = axisAlignedBB.minX;
            d5 = axisAlignedBB.maxX;
            d3 = axisAlignedBB.maxY;
            d6 = axisAlignedBB.maxY;
            d4 = axisAlignedBB.minZ;
            d7 = axisAlignedBB.maxZ;
        } else if (enumFacing == EnumFacing.EAST) {
            d2 = axisAlignedBB.maxX;
            d5 = axisAlignedBB.maxX;
            d3 = axisAlignedBB.minY;
            d6 = axisAlignedBB.maxY;
            d4 = axisAlignedBB.minZ;
            d7 = axisAlignedBB.maxZ;
        } else if (enumFacing == EnumFacing.WEST) {
            d2 = axisAlignedBB.minX;
            d5 = axisAlignedBB.minX;
            d3 = axisAlignedBB.minY;
            d6 = axisAlignedBB.maxY;
            d4 = axisAlignedBB.minZ;
            d7 = axisAlignedBB.maxZ;
        } else if (enumFacing == EnumFacing.SOUTH) {
            d2 = axisAlignedBB.minX;
            d5 = axisAlignedBB.maxX;
            d3 = axisAlignedBB.minY;
            d6 = axisAlignedBB.maxY;
            d4 = axisAlignedBB.maxZ;
            d7 = axisAlignedBB.maxZ;
        } else if (enumFacing == EnumFacing.NORTH) {
            d2 = axisAlignedBB.minX;
            d5 = axisAlignedBB.maxX;
            d3 = axisAlignedBB.minY;
            d6 = axisAlignedBB.maxY;
            d4 = axisAlignedBB.minZ;
            d7 = axisAlignedBB.minZ;
        }
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.depthMask((boolean)false);
        bufferBuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        if (enumFacing == EnumFacing.EAST || enumFacing == EnumFacing.WEST || enumFacing == EnumFacing.NORTH || enumFacing == EnumFacing.SOUTH) {
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
        } else if (enumFacing == EnumFacing.UP) {
            bufferBuilder.pos(d2, d3, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
        } else if (enumFacing == EnumFacing.DOWN) {
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f, f2, f3, f4).endVertex();
        }
        tessellator.draw();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }

    public static void drawCorner(BlockPos blockPos, double d, double d2, double d3, Color color) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        AxisAlignedBB axisAlignedBB = RenderUtilCa.getBoundingBox(blockPos);
        RenderUtilCa.drawCornerVertices(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX + d2, axisAlignedBB.maxY + d, axisAlignedBB.maxZ + d3, color);
        tessellator.draw();
    }

    public static void drawBoundingBoxWithSides(BlockPos blockPos, int n, Colour colour, int n2) {
        RenderUtilCa.drawBoundingBoxWithSides(RenderUtilCa.getBoundingBox(blockPos, 1.0, 1.0, 1.0), n, colour, colour.getAlpha(), n2);
    }

    private static void colorVertex(double d, double d2, double d3, Colour colour, int n, BufferBuilder bufferBuilder) {
        bufferBuilder.pos(d - RenderUtilCa.mc.getRenderManager().viewerPosX, d2 - RenderUtilCa.mc.getRenderManager().viewerPosY, d3 - RenderUtilCa.mc.getRenderManager().viewerPosZ).color(colour.getRed(), colour.getGreen(), colour.getBlue(), n).endVertex();
    }

    public static void drawGradientPlaneBB(AxisAlignedBB axisAlignedBB, EnumFacing enumFacing, Color color, Color color2, double d) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        float f = (float)color.getRed() / 255.0f;
        float f2 = (float)color.getGreen() / 255.0f;
        float f3 = (float)color.getBlue() / 255.0f;
        float f4 = (float)color.getAlpha() / 255.0f;
        float f5 = (float)color2.getRed() / 255.0f;
        float f6 = (float)color2.getGreen() / 255.0f;
        float f7 = (float)color2.getBlue() / 255.0f;
        float f8 = (float)color2.getAlpha() / 255.0f;
        double d2 = 0.0;
        double d3 = 0.0;
        double d4 = 0.0;
        double d5 = 0.0;
        double d6 = 0.0;
        double d7 = 0.0;
        switch (enumFacing) {
            case DOWN: {
                d2 = axisAlignedBB.minX;
                d5 = axisAlignedBB.maxX;
                d3 = axisAlignedBB.minY;
                d6 = axisAlignedBB.minY;
                d4 = axisAlignedBB.minZ;
                d7 = axisAlignedBB.maxZ;
                break;
            }
            case UP: {
                d2 = axisAlignedBB.minX;
                d5 = axisAlignedBB.maxX;
                d3 = axisAlignedBB.maxY;
                d6 = axisAlignedBB.maxY;
                d4 = axisAlignedBB.minZ;
                d7 = axisAlignedBB.maxZ;
                break;
            }
            case EAST: {
                d2 = axisAlignedBB.maxX;
                d5 = axisAlignedBB.maxX;
                d3 = axisAlignedBB.minY;
                d6 = axisAlignedBB.maxY;
                d4 = axisAlignedBB.minZ;
                d7 = axisAlignedBB.maxZ;
                break;
            }
            case WEST: {
                d2 = axisAlignedBB.minX;
                d5 = axisAlignedBB.minX;
                d3 = axisAlignedBB.minY;
                d6 = axisAlignedBB.maxY;
                d4 = axisAlignedBB.minZ;
                d7 = axisAlignedBB.maxZ;
                break;
            }
            case SOUTH: {
                d2 = axisAlignedBB.minX;
                d5 = axisAlignedBB.maxX;
                d3 = axisAlignedBB.minY;
                d6 = axisAlignedBB.maxY;
                d4 = axisAlignedBB.maxZ;
                d7 = axisAlignedBB.maxZ;
                break;
            }
            case NORTH: {
                d2 = axisAlignedBB.minX;
                d5 = axisAlignedBB.maxX;
                d3 = axisAlignedBB.minY;
                d6 = axisAlignedBB.maxY;
                d4 = axisAlignedBB.minZ;
                d7 = axisAlignedBB.minZ;
            }
        }
        if (enumFacing == EnumFacing.DOWN || enumFacing == EnumFacing.UP || enumFacing == EnumFacing.EAST || enumFacing == EnumFacing.WEST || enumFacing == EnumFacing.SOUTH || enumFacing == EnumFacing.NORTH) {
            // empty if block
        }
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.depthMask((boolean)false);
        bufferBuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        if (enumFacing == EnumFacing.EAST || enumFacing == EnumFacing.WEST || enumFacing == EnumFacing.NORTH || enumFacing == EnumFacing.SOUTH) {
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
        } else if (enumFacing == EnumFacing.UP) {
            bufferBuilder.pos(d2, d3, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f5, f6, f7, f8).endVertex();
        } else if (enumFacing == EnumFacing.DOWN) {
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d3, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d2, d6, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d4).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d5, d6, d7).color(f, f2, f3, f4).endVertex();
        }
        tessellator.draw();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }

    public static void drawBoundingBoxWithSides(BlockPos blockPos, int n, Colour colour, int n2, int n3) {
        RenderUtilCa.drawBoundingBoxWithSides(RenderUtilCa.getBoundingBox(blockPos, 1.0, 1.0, 1.0), n, colour, n2, n3);
    }

    public static void drawBlockOutlineBB(AxisAlignedBB axisAlignedBB, Color color, float f) {
        Vec3d vec3d = EntityUtilCa.interpolateEntity((Entity)RenderUtilCa.mc.player, mc.getRenderPartialTicks());
        RenderUtilCa.drawBlockOutline(axisAlignedBB.expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord), color, f);
    }

    public static void drawBox(BlockPos blockPos, Color color, double d, boolean bl, boolean bl2, int n) {
        if (bl) {
            Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
            RenderUtilCa.drawOpenGradientBox(blockPos, bl2 ? color2 : color, bl2 ? color : color2, d);
            return;
        }
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosY + d, (double)(blockPos.getZ() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Objects.requireNonNull(RenderUtilCa.mc.getRenderViewEntity()).posX, RenderUtilCa.mc.getRenderViewEntity().posY, RenderUtilCa.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtilCa.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtilCa.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask((boolean)false);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            RenderGlobal.renderFilledBox((AxisAlignedBB)axisAlignedBB, (float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
            GL11.glDisable((int)2848);
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    public static void glBillboard(float f, float f2, float f3) {
        float f4 = 0.02666667f;
        GlStateManager.translate((double)((double)f - RenderUtilCa.mc.getRenderManager().renderPosX), (double)((double)f2 - RenderUtilCa.mc.getRenderManager().renderPosY), (double)((double)f3 - RenderUtilCa.mc.getRenderManager().renderPosZ));
        GlStateManager.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)(-RenderUtilCa.mc.player.rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)RenderUtilCa.mc.player.rotationPitch, (float)(RenderUtilCa.mc.gameSettings.thirdPersonView == 2 ? -1.0f : 1.0f), (float)0.0f, (float)0.0f);
        GlStateManager.scale((float)(-f4), (float)(-f4), (float)f4);
    }

    public static void drawBlockOutline(AxisAlignedBB axisAlignedBB, Color color, float f) {
        float f2 = (float)color.getRed() / 255.0f;
        float f3 = (float)color.getGreen() / 255.0f;
        float f4 = (float)color.getBlue() / 255.0f;
        float f5 = (float)color.getAlpha() / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawOpenGradientBox(BlockPos blockPos, Color color, Color color2, double d) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            if (enumFacing == EnumFacing.UP) continue;
            RenderUtilCa.drawGradientPlane(blockPos, enumFacing, color, color2, d);
        }
    }

    public static void drawOpenGradientBoxBB(AxisAlignedBB axisAlignedBB, Color color, Color color2, double d) {
        Vec3d vec3d = EntityUtilCa.interpolateEntity((Entity)RenderUtilCa.mc.player, mc.getRenderPartialTicks());
        for (EnumFacing enumFacing : EnumFacing.values()) {
            RenderUtilCa.drawGradientPlaneBB(axisAlignedBB.expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord), enumFacing, color, color2, d);
        }
    }

    public static void drawCircleVertices(AxisAlignedBB axisAlignedBB, float f, Colour colour) {
        float f2 = (float)colour.getRed() / 255.0f;
        float f3 = (float)colour.getGreen() / 255.0f;
        float f4 = (float)colour.getBlue() / 255.0f;
        float f5 = (float)colour.getAlpha() / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)1.0f);
        for (int i = 0; i < 360; ++i) {
            bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
            bufferBuilder.pos(axisAlignedBB.getCenter().xCoord + Math.sin((double)i * 3.1415926 / 180.0) * (double)f, axisAlignedBB.minY, axisAlignedBB.getCenter().zCoord + Math.cos((double)i * 3.1415926 / 180.0) * (double)f).color(f2, f3, f4, f5).endVertex();
            bufferBuilder.pos(axisAlignedBB.getCenter().xCoord + Math.sin((double)(i + 1) * 3.1415926 / 180.0) * (double)f, axisAlignedBB.minY, axisAlignedBB.getCenter().zCoord + Math.cos((double)(i + 1) * 3.1415926 / 180.0) * (double)f).color(f2, f3, f4, f5).endVertex();
            tessellator.draw();
        }
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawBox(AxisAlignedBB axisAlignedBB, boolean bl, double d, Colour colour, int n, int n2) {
        if (bl) {
            RenderUtilCa.drawBox(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX - axisAlignedBB.minX, axisAlignedBB.maxY - axisAlignedBB.minY, axisAlignedBB.maxZ - axisAlignedBB.minZ, colour, n, n2);
        } else {
            RenderUtilCa.drawBox(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX - axisAlignedBB.minX, d, axisAlignedBB.maxZ - axisAlignedBB.minZ, colour, n, n2);
        }
    }

    public static void drawLine(float f, float f2, float f3, float f4, float f5, int n) {
        float f6 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(n & 0xFF) / 255.0f;
        float f9 = (float)(n >> 24 & 0xFF) / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.shadeModel((int)7425);
        GL11.glLineWidth((float)f5);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos((double)f, (double)f2, 0.0).color(f6, f7, f8, f9).endVertex();
        bufferBuilder.pos((double)f3, (double)f4, 0.0).color(f6, f7, f8, f9).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel((int)7424);
        GL11.glDisable((int)2848);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    public static void drawTriangleOutline(float f, float f2, float f3, float f4, float f5, float f6, int n) {
        boolean bl = GL11.glIsEnabled((int)3042);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glPushMatrix();
        GL11.glLineWidth((float)f6);
        RenderUtilCa.hexColor(n);
        GL11.glBegin((int)2);
        GL11.glVertex2d((double)f, (double)f2);
        GL11.glVertex2d((double)(f - f3 / f4), (double)(f2 - f3));
        GL11.glVertex2d((double)f, (double)(f2 - f3 / f5));
        GL11.glVertex2d((double)(f + f3 / f4), (double)(f2 - f3));
        GL11.glVertex2d((double)f, (double)f2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        if (!bl) {
            GL11.glDisable((int)3042);
        }
        GL11.glDisable((int)2848);
    }

    public static void drawBox(AxisAlignedBB axisAlignedBB, boolean bl, double d, Colour colour, int n) {
        RenderUtilCa.drawBox(axisAlignedBB, bl, d, colour, colour.getAlpha(), n);
    }

    public static void drawGlowBox(BlockPos blockPos, double d, Float f, Color color, Color color2) {
        RenderUtilCa.drawBoxESP(blockPos, color2, f.floatValue(), true, false, color2.getAlpha(), -1.0);
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtilCa.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtilCa.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtilCa.mc.getRenderManager().viewerPosZ);
        RenderBuilder.glSetup();
        RenderBuilder.glPrepare();
        RenderUtilCa.drawSelectionGlowFilledBox(axisAlignedBB, d, new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()), new Color(color.getRed(), color.getGreen(), color.getBlue(), 0));
        RenderBuilder.glRestore();
        RenderBuilder.glRelease();
    }

    public static void drawRect(float f, float f2, float f3, float f4, int n) {
        float f5 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(n & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos((double)f, (double)f4, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)f3, (double)f4, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)f3, (double)f2, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)f, (double)f2, 0.0).color(f6, f7, f8, f5).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static AxisAlignedBB interpolateAxis(AxisAlignedBB axisAlignedBB) {
        return new AxisAlignedBB(axisAlignedBB.minX - RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtilCa.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtilCa.mc.getRenderManager().viewerPosZ);
    }

    public static void drawBoundingBoxWithSides(AxisAlignedBB axisAlignedBB, int n, Colour colour, int n2) {
        RenderUtilCa.drawBoundingBoxWithSides(axisAlignedBB, n, colour, colour.getAlpha(), n2);
    }

    public static void drawBox(BlockPos blockPos, Color color, boolean bl) {
        IBlockState iBlockState = RenderUtilCa.mc.world.getBlockState(blockPos);
        if ((bl || iBlockState.getMaterial() != Material.AIR) && RenderUtilCa.mc.world.getWorldBorder().contains(blockPos)) {
            Vec3d vec3d = EntityUtilCa.interpolateEntity((Entity)RenderUtilCa.mc.player, mc.getRenderPartialTicks());
            AxisAlignedBB axisAlignedBB = iBlockState.getSelectedBoundingBox((World)RenderUtilCa.mc.world, blockPos).expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord);
            camera.setPosition(Objects.requireNonNull(RenderUtilCa.mc.getRenderViewEntity()).posX, RenderUtilCa.mc.getRenderViewEntity().posY, RenderUtilCa.mc.getRenderViewEntity().posZ);
            if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtilCa.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtilCa.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtilCa.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtilCa.mc.getRenderManager().viewerPosZ))) {
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.disableDepth();
                GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
                GlStateManager.disableTexture2D();
                GlStateManager.depthMask((boolean)false);
                GL11.glEnable((int)2848);
                GL11.glHint((int)3154, (int)4354);
                RenderGlobal.renderFilledBox((AxisAlignedBB)axisAlignedBB, (float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
                GL11.glDisable((int)2848);
                GlStateManager.depthMask((boolean)true);
                GlStateManager.enableDepth();
                GlStateManager.enableTexture2D();
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }
        }
    }

    private static AxisAlignedBB getBoundingBox(BlockPos blockPos, double d, double d2, double d3) {
        double d4 = blockPos.getX();
        double d5 = blockPos.getY();
        double d6 = blockPos.getZ();
        return new AxisAlignedBB(d4, d5, d6, d4 + d, d5 + d2, d6 + d3);
    }

    public void setGlState(int n, boolean bl) {
        if (bl) {
            GL11.glEnable((int)n);
        } else {
            GL11.glDisable((int)n);
        }
    }

    public static void drawBox(double d, double d2, double d3, double d4, double d5, double d6, Colour colour, int n, int n2) {
        GlStateManager.disableAlpha();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        colour.glColor();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        RenderUtilCa.doVerticies(new AxisAlignedBB(d, d2, d3, d + d4, d2 + d5, d3 + d6), colour, n, bufferBuilder, n2, false);
        tessellator.draw();
        GlStateManager.enableAlpha();
    }
}

