//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.util.cc;

import java.awt.Color;
import me.snow.aclient.util.Util;
import me.snow.aclient.util.cc.RenderBuilderCC;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class RenderUtilCC
implements Util {
    private static /* synthetic */ ScaledResolution resolution;
    public static /* synthetic */ Tessellator tessellator;
    public static /* synthetic */ BufferBuilder bufferBuilder;

    public static void addChainedGlowBoxVertices(double d, double d2, double d3, double d4, double d5, double d6, Color color, Color color2) {
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

    public static void glBillboardDistanceScaled(float f, float f2, float f3, EntityPlayer entityPlayer, float f4) {
        RenderUtilCC.glBillboard(f, f2, f3);
        int n = (int)entityPlayer.getDistance((double)f, (double)f2, (double)f3);
        float f5 = (float)n / 2.0f / (2.0f + (2.0f - f4));
        if (f5 < 1.0f) {
            f5 = 1.0f;
        }
        GlStateManager.scale((float)f5, (float)f5, (float)f5);
    }

    public static void addChainedFilledBoxVertices(double d, double d2, double d3, double d4, double d5, double d6, Color color) {
        bufferBuilder.pos(d, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
    }

    public static void drawLine3D(Vec3d vec3d, Vec3d vec3d2, Color color, double d) {
        GL11.glDepthMask((boolean)false);
        GL11.glDisable((int)2929);
        GL11.glDisable((int)3008);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)0.1f);
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        GL11.glLineWidth((float)((float)d));
        GL11.glBegin((int)1);
        GL11.glVertex3d((double)vec3d.xCoord, (double)vec3d.yCoord, (double)vec3d.zCoord);
        GL11.glVertex3d((double)vec3d2.xCoord, (double)vec3d2.yCoord, (double)vec3d2.zCoord);
        GL11.glEnd();
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glDisable((int)2848);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static void drawSelectionGlowFilledBox(AxisAlignedBB axisAlignedBB, double d, double d2, double d3, Color color, Color color2) {
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        RenderUtilCC.addChainedGlowBoxVertices(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX + d2, axisAlignedBB.maxY + d, axisAlignedBB.maxZ + d3, color, color2);
        tessellator.draw();
    }

    public static void drawBorder(float f, float f2, float f3, float f4, Color color) {
        RenderUtilCC.drawRect(f - 0.5f, f2 - 0.5f, 0.5f, f4 + 1.0f, color);
        RenderUtilCC.drawRect(f + f3, f2 - 0.5f, 0.5f, f4 + 1.0f, color);
        RenderUtilCC.drawRect(f, f2 - 0.5f, f3, 0.5f, color);
        RenderUtilCC.drawRect(f, f2 + f4, f3, 0.5f, color);
    }

    public static void drawCircle(RenderBuilderCC renderBuilderCC, Vec3d vec3d, double d, double d2, Color color) {
        RenderUtilCC.renderCircle(bufferBuilder, vec3d, d, d2, color);
        renderBuilderCC.build();
    }

    public static void drawSelectionBoundingBox(AxisAlignedBB axisAlignedBB, double d, double d2, double d3, Color color) {
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        RenderUtilCC.addChainedBoundingBoxVertices(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX + d2, axisAlignedBB.maxY + d, axisAlignedBB.maxZ + d3, color);
        tessellator.draw();
    }

    public static void drawBox(RenderBuilderCC renderBuilderCC) {
        if (mc.getRenderViewEntity() != null) {
            AxisAlignedBB axisAlignedBB = renderBuilderCC.getAxisAlignedBB().offset(-RenderUtilCC.mc.getRenderManager().viewerPosX, -RenderUtilCC.mc.getRenderManager().viewerPosY, -RenderUtilCC.mc.getRenderManager().viewerPosZ);
            switch (renderBuilderCC.getBox()) {
                case FILL: {
                    RenderUtilCC.drawSelectionBox(axisAlignedBB, renderBuilderCC.getHeight(), renderBuilderCC.getLength(), renderBuilderCC.getWidth(), renderBuilderCC.getColor());
                    break;
                }
                case OUTLINE: {
                    RenderUtilCC.drawSelectionBoundingBox(axisAlignedBB, renderBuilderCC.getHeight(), renderBuilderCC.getLength(), renderBuilderCC.getWidth(), new Color(renderBuilderCC.getColor().getRed(), renderBuilderCC.getColor().getGreen(), renderBuilderCC.getColor().getBlue(), 144));
                    break;
                }
                case BOTH: {
                    RenderUtilCC.drawSelectionBox(axisAlignedBB, renderBuilderCC.getHeight(), renderBuilderCC.getLength(), renderBuilderCC.getWidth(), renderBuilderCC.getColor());
                    RenderUtilCC.drawSelectionBoundingBox(axisAlignedBB, renderBuilderCC.getHeight(), renderBuilderCC.getLength(), renderBuilderCC.getWidth(), new Color(renderBuilderCC.getColor().getRed(), renderBuilderCC.getColor().getGreen(), renderBuilderCC.getColor().getBlue(), 144));
                    break;
                }
                case GLOW: {
                    RenderUtilCC.drawSelectionGlowFilledBox(axisAlignedBB, renderBuilderCC.getHeight(), renderBuilderCC.getLength(), renderBuilderCC.getWidth(), renderBuilderCC.getColor(), new Color(renderBuilderCC.getColor().getRed(), renderBuilderCC.getColor().getGreen(), renderBuilderCC.getColor().getBlue(), 0));
                    break;
                }
                case REVERSE: {
                    RenderUtilCC.drawSelectionGlowFilledBox(axisAlignedBB, renderBuilderCC.getHeight(), renderBuilderCC.getLength(), renderBuilderCC.getWidth(), new Color(renderBuilderCC.getColor().getRed(), renderBuilderCC.getColor().getGreen(), renderBuilderCC.getColor().getBlue(), 0), renderBuilderCC.getColor());
                    break;
                }
                case CLAW: {
                    RenderUtilCC.drawClawBox(axisAlignedBB, renderBuilderCC.getHeight(), renderBuilderCC.getLength(), renderBuilderCC.getWidth(), new Color(renderBuilderCC.getColor().getRed(), renderBuilderCC.getColor().getGreen(), renderBuilderCC.getColor().getBlue(), 255));
                }
            }
            renderBuilderCC.build();
        }
    }

    static {
        tessellator = Tessellator.getInstance();
        bufferBuilder = tessellator.getBuffer();
        resolution = new ScaledResolution(mc);
    }

    public static void addChainedBoundingBoxVertices(double d, double d2, double d3, double d4, double d5, double d6, Color color) {
        bufferBuilder.pos(d, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d4, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d4, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
    }

    public static void addChainedClawBoxVertices(double d, double d2, double d3, double d4, double d5, double d6, Color color) {
        bufferBuilder.pos(d, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d, d2, d6 - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d, d2, d3 + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d4, d2, d6 - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d4, d2, d3 + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d4 - 0.8, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d4 - 0.8, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d + 0.8, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d + 0.8, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d, d2 + 0.2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d, d2 + 0.2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d4, d2 + 0.2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d4, d2 + 0.2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d, d5, d6 - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d, d5, d3 + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d4, d5, d6 - 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d4, d5, d3 + 0.8).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d4 - 0.8, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d4 - 0.8, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d + 0.8, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d + 0.8, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d, d5 - 0.2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d, d5 - 0.2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d4, d5 - 0.2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), 0).endVertex();
        bufferBuilder.pos(d4, d5 - 0.2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
    }

    public static void drawRoundedRect(double d, double d2, double d3, double d4, double d5, Color color) {
        int n;
        GL11.glPushAttrib((int)0);
        GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
        d3 *= 2.0;
        d4 *= 2.0;
        d3 += (d *= 2.0);
        d4 += (d2 *= 2.0);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        GL11.glEnable((int)2848);
        GL11.glBegin((int)9);
        for (n = 0; n <= 90; ++n) {
            GL11.glVertex2d((double)(d + d5 + Math.sin((double)n * Math.PI / 180.0) * d5 * -1.0), (double)(d2 + d5 + Math.cos((double)n * Math.PI / 180.0) * d5 * -1.0));
        }
        for (n = 90; n <= 180; ++n) {
            GL11.glVertex2d((double)(d + d5 + Math.sin((double)n * Math.PI / 180.0) * d5 * -1.0), (double)(d4 - d5 + Math.cos((double)n * Math.PI / 180.0) * d5 * -1.0));
        }
        for (n = 0; n <= 90; ++n) {
            GL11.glVertex2d((double)(d3 - d5 + Math.sin((double)n * Math.PI / 180.0) * d5), (double)(d4 - d5 + Math.cos((double)n * Math.PI / 180.0) * d5));
        }
        for (n = 90; n <= 180; ++n) {
            GL11.glVertex2d((double)(d3 - d5 + Math.sin((double)n * Math.PI / 180.0) * d5), (double)(d2 + d5 + Math.cos((double)n * Math.PI / 180.0) * d5));
        }
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3553);
        GL11.glScaled((double)2.0, (double)2.0, (double)2.0);
        GL11.glPopAttrib();
    }

    public static void drawClawBox(AxisAlignedBB axisAlignedBB, double d, double d2, double d3, Color color) {
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        RenderUtilCC.addChainedClawBoxVertices(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX + d2, axisAlignedBB.maxY + d, axisAlignedBB.maxZ + d3, color);
        tessellator.draw();
    }

    public static void drawRect(float f, float f2, float f3, float f4, int n) {
        Color color = new Color(n, true);
        GL11.glPushMatrix();
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glShadeModel((int)7425);
        GL11.glBegin((int)7);
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        GL11.glVertex2f((float)f, (float)f2);
        GL11.glVertex2f((float)f, (float)(f2 + f4));
        GL11.glVertex2f((float)(f + f3), (float)(f2 + f4));
        GL11.glVertex2f((float)(f + f3), (float)f2);
        GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public static void drawRect(float f, float f2, float f3, float f4, Color color) {
        GL11.glPushMatrix();
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glShadeModel((int)7425);
        GL11.glBegin((int)7);
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        GL11.glVertex2f((float)f, (float)f2);
        GL11.glVertex2f((float)f, (float)(f2 + f4));
        GL11.glVertex2f((float)(f + f3), (float)(f2 + f4));
        GL11.glVertex2f((float)(f + f3), (float)f2);
        GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public static double getDisplayWidth() {
        return resolution.getScaledWidth_double();
    }

    public static double getDisplayHeight() {
        return resolution.getScaledHeight_double();
    }

    public static void drawSelectionBox(AxisAlignedBB axisAlignedBB, double d, double d2, double d3, Color color) {
        bufferBuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        RenderUtilCC.addChainedFilledBoxVertices(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX + d2, axisAlignedBB.maxY + d, axisAlignedBB.maxZ + d3, color);
        tessellator.draw();
    }

    public static void glBillboard(float f, float f2, float f3) {
        float f4 = 0.02666667f;
        GlStateManager.translate((double)((double)f - RenderUtilCC.mc.getRenderManager().viewerPosX), (double)((double)f2 - RenderUtilCC.mc.getRenderManager().viewerPosY), (double)((double)f3 - RenderUtilCC.mc.getRenderManager().viewerPosZ));
        GlStateManager.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)(-RenderUtilCC.mc.player.rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)RenderUtilCC.mc.player.rotationPitch, (float)(RenderUtilCC.mc.gameSettings.thirdPersonView == 2 ? -1.0f : 1.0f), (float)0.0f, (float)0.0f);
        GlStateManager.scale((float)(-f4), (float)(-f4), (float)f4);
    }

    public static void drawPolygon(double d, double d2, float f, int n, Color color) {
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        bufferBuilder.begin(6, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(d, d2, 0.0).endVertex();
        double d3 = Math.PI * 2;
        for (int i = 0; i <= n; ++i) {
            double d4 = d3 * (double)i / (double)n + Math.toRadians(180.0);
            bufferBuilder.pos(d + Math.sin(d4) * (double)f, d2 + Math.cos(d4) * (double)f, 0.0).endVertex();
        }
        tessellator.draw();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
    }

    public static void renderCircle(BufferBuilder bufferBuilder, Vec3d vec3d, double d, double d2, Color color) {
        GlStateManager.disableCull();
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel((int)7425);
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        for (int i = 0; i < 361; ++i) {
            bufferBuilder.pos(vec3d.xCoord + Math.sin(Math.toRadians(i)) * d - RenderUtilCC.mc.getRenderManager().viewerPosX, vec3d.yCoord + d2 - RenderUtilCC.mc.getRenderManager().viewerPosY, vec3d.zCoord + Math.cos(Math.toRadians(i)) * d - RenderUtilCC.mc.getRenderManager().viewerPosZ).color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, 1.0f).endVertex();
        }
        tessellator.draw();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel((int)7424);
    }
}

