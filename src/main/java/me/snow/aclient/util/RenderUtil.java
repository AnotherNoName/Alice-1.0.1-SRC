//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.RenderItem
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.culling.ICamera
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.shader.Framebuffer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec2f
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.Display
 *  org.lwjgl.opengl.EXTFramebufferObject
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.Disk
 *  org.lwjgl.util.glu.GLU
 *  org.lwjgl.util.glu.Sphere
 */
package me.snow.aclient.util;

import java.awt.Color;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;
import me.snow.aclient.AliceMain;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.util.ColorUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.GLUProjection;
import me.snow.aclient.util.Util;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Disk;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

public class RenderUtil
implements Util {
    private static /* synthetic */ boolean bind;
    private static final /* synthetic */ FloatBuffer modelView;
    public static /* synthetic */ int deltaTime;
    public static /* synthetic */ Tessellator tessellator;
    private static final /* synthetic */ FloatBuffer projection;
    private static final /* synthetic */ BufferBuilder bufferbuilder;
    private static /* synthetic */ boolean depth;
    private static /* synthetic */ boolean texture;
    public static /* synthetic */ BufferBuilder builder;
    private static /* synthetic */ boolean override;
    private static final /* synthetic */ IntBuffer viewport;
    private static /* synthetic */ boolean clean;
    private static final /* synthetic */ FloatBuffer screenCoords;
    public static /* synthetic */ ICamera camera;
    private static final /* synthetic */ Frustum frustrum;
    public static /* synthetic */ RenderItem itemRender;

    public static void drawRectangle(float f, float f2, float f3, float f4) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glBegin((int)2);
        GL11.glVertex2d((double)f3, (double)0.0);
        GL11.glVertex2d((double)0.0, (double)0.0);
        GL11.glVertex2d((double)0.0, (double)f4);
        GL11.glVertex2d((double)f3, (double)f4);
        RenderUtil.glEnd();
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

    public static void drawBetterGradientBox(BlockPos blockPos, Color color, Color color2) {
        float f = (float)color.getRed() / 255.0f;
        float f2 = (float)color.getGreen() / 255.0f;
        float f3 = (float)color.getBlue() / 255.0f;
        float f4 = (float)color.getAlpha() / 255.0f;
        float f5 = (float)color2.getRed() / 255.0f;
        float f6 = (float)color2.getGreen() / 255.0f;
        float f7 = (float)color2.getBlue() / 255.0f;
        float f8 = (float)color2.getAlpha() / 255.0f;
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtil.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtil.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtil.mc.getRenderManager().viewerPosZ);
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
        bufferBuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
    }

    public static void glBillboard(float f, float f2, float f3) {
        float f4 = 0.02666667f;
        GlStateManager.translate((double)((double)f - RenderUtil.mc.getRenderManager().renderPosX), (double)((double)f2 - RenderUtil.mc.getRenderManager().renderPosY), (double)((double)f3 - RenderUtil.mc.getRenderManager().renderPosZ));
        GlStateManager.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)(-RenderUtil.mc.player.rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)RenderUtil.mc.player.rotationPitch, (float)(RenderUtil.mc.gameSettings.thirdPersonView == 2 ? -1.0f : 1.0f), (float)0.0f, (float)0.0f);
        GlStateManager.scale((float)(-f4), (float)(-f4), (float)f4);
    }

    public static void rotationHelper(float f, float f2, float f3) {
        GlStateManager.rotate((float)f2, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)f3, (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.rotate((float)f, (float)1.0f, (float)0.0f, (float)0.0f);
    }

    public static void glrendermethod() {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2884);
        GL11.glDisable((int)2929);
        double d = RenderUtil.mc.getRenderManager().viewerPosX;
        double d2 = RenderUtil.mc.getRenderManager().viewerPosY;
        double d3 = RenderUtil.mc.getRenderManager().viewerPosZ;
        GL11.glPushMatrix();
        GL11.glTranslated((double)(-d), (double)(-d2), (double)(-d3));
    }

    public static void drawCircle(float f, float f2, float f3) {
        RenderUtil.drawCircle(f, f2, f3, 0, 360, 64);
    }

    public static void renderThree() {
        GL11.glStencilFunc((int)514, (int)1, (int)15);
        GL11.glStencilOp((int)7680, (int)7680, (int)7680);
        GL11.glPolygonMode((int)1032, (int)6913);
    }

    public static void drawBoundingBox(AxisAlignedBB axisAlignedBB, float f, int n) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)f);
        float f2 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f3 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(n & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f3, f4, f5, f2).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f3, f4, f5, f2).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f3, f4, f5, f2).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f3, f4, f5, f2).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f3, f4, f5, f2).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f3, f4, f5, f2).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f3, f4, f5, f2).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f3, f4, f5, f2).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f3, f4, f5, f2).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f3, f4, f5, f2).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f3, f4, f5, f2).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f3, f4, f5, f2).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f3, f4, f5, f2).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f3, f4, f5, f2).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f3, f4, f5, f2).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f3, f4, f5, f2).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawCompleteImage(float f, float f2, float f3, float f4) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)f, (float)f2, (float)0.0f);
        GL11.glBegin((int)7);
        GL11.glTexCoord2f((float)0.0f, (float)0.0f);
        GL11.glVertex3f((float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glTexCoord2f((float)0.0f, (float)1.0f);
        GL11.glVertex3f((float)0.0f, (float)f4, (float)0.0f);
        GL11.glTexCoord2f((float)1.0f, (float)1.0f);
        GL11.glVertex3f((float)f3, (float)f4, (float)0.0f);
        GL11.glTexCoord2f((float)1.0f, (float)0.0f);
        GL11.glVertex3f((float)f3, (float)0.0f, (float)0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
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

    public static void drawGradientSideways(double d, double d2, double d3, double d4, int n, int n2) {
        float f = (float)(n >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n & 0xFF) / 255.0f;
        float f5 = (float)(n2 >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(n2 >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(n2 >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(n2 & 0xFF) / 255.0f;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glShadeModel((int)7425);
        GL11.glPushMatrix();
        GL11.glBegin((int)7);
        GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)f);
        GL11.glVertex2d((double)d, (double)d2);
        GL11.glVertex2d((double)d, (double)d4);
        GL11.glColor4f((float)f6, (float)f7, (float)f8, (float)f5);
        GL11.glVertex2d((double)d3, (double)d4);
        GL11.glVertex2d((double)d3, (double)d2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glShadeModel((int)7424);
    }

    public static int getRainbow(int n, int n2, float f, float f2) {
        float f3 = (System.currentTimeMillis() + (long)n2) % (long)n;
        return Color.getHSBColor(f3 / (float)n, f, f2).getRGB();
    }

    public static void glBillboardDistanceScaled(float f, float f2, float f3, EntityPlayer entityPlayer, float f4) {
        RenderUtil.glBillboard(f, f2, f3);
        int n = (int)entityPlayer.getDistance((double)f, (double)f2, (double)f3);
        float f5 = (float)n / 2.0f / (2.0f + (2.0f - f4));
        if (f5 < 1.0f) {
            f5 = 1.0f;
        }
        GlStateManager.scale((float)f5, (float)f5, (float)f5);
    }

    public static void renderTwo() {
        GL11.glStencilFunc((int)512, (int)0, (int)15);
        GL11.glStencilOp((int)7681, (int)7681, (int)7681);
        GL11.glPolygonMode((int)1032, (int)6914);
    }

    public static void drawBlockOutline(BlockPos blockPos, Color color, float f, boolean bl) {
        IBlockState iBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        if ((bl || iBlockState.getMaterial() != Material.AIR) && RenderUtil.mc.world.getWorldBorder().contains(blockPos)) {
            Vec3d vec3d = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.player, mc.getRenderPartialTicks());
            RenderUtil.drawBlockOutline(iBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord), color, f);
        }
    }

    public static void drawOutlinedBlockESP(BlockPos blockPos, Color color, float f) {
        IBlockState iBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        Vec3d vec3d = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.player, mc.getRenderPartialTicks());
        RenderUtil.drawBoundingBox(iBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord), f, ColorUtil.toRGBA(color));
    }

    public static void drawCheckmark(float f, float f2, Color color) {
        GL11.glPushMatrix();
        GL11.glEnable((int)2848);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glColor4f((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), (float)color.getAlpha());
        GL11.glLineWidth((float)2.0f);
        GL11.glBegin((int)1);
        GL11.glVertex2d((double)(f + 1.0f), (double)(f2 + 1.0f));
        GL11.glVertex2d((double)(f + 3.0f), (double)(f2 + 4.0f));
        GL11.glEnd();
        GL11.glBegin((int)1);
        GL11.glVertex2d((double)(f + 3.0f), (double)(f2 + 4.0f));
        GL11.glVertex2d((double)(f + 6.0f), (double)(f2 - 2.0f));
        GL11.glEnd();
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)2848);
        GL11.glPopMatrix();
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

    public static void drawSexyBoxPhobosIsRetardedFuckYouESP(AxisAlignedBB axisAlignedBB, Color color, Color color2, float f, boolean bl, boolean bl2, boolean bl3, float f2, float f3, float f4) {
        double d = 0.5 * (double)(1.0f - f3);
        AxisAlignedBB axisAlignedBB2 = RenderUtil.interpolateAxis(new AxisAlignedBB(axisAlignedBB.minX + d, axisAlignedBB.minY + d + (double)(1.0f - f4), axisAlignedBB.minZ + d, axisAlignedBB.maxX - d, axisAlignedBB.maxY - d, axisAlignedBB.maxZ - d));
        float f5 = (float)color.getRed() / 255.0f;
        float f6 = (float)color.getGreen() / 255.0f;
        float f7 = (float)color.getBlue() / 255.0f;
        float f8 = (float)color.getAlpha() / 255.0f;
        float f9 = (float)color2.getRed() / 255.0f;
        float f10 = (float)color2.getGreen() / 255.0f;
        float f11 = (float)color2.getBlue() / 255.0f;
        float f12 = (float)color2.getAlpha() / 255.0f;
        if (bl3) {
            f5 = (float)Colors.INSTANCE.getCurrentColor().getRed() / 255.0f;
            f6 = (float)Colors.INSTANCE.getCurrentColor().getGreen() / 255.0f;
            f7 = (float)Colors.INSTANCE.getCurrentColor().getBlue() / 255.0f;
            f9 = (float)Colors.INSTANCE.getCurrentColor().getRed() / 255.0f;
            f10 = (float)Colors.INSTANCE.getCurrentColor().getGreen() / 255.0f;
            f11 = (float)Colors.INSTANCE.getCurrentColor().getBlue() / 255.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        f8 *= f2;
        f12 *= f2;
        if (bl2) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask((boolean)false);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            RenderGlobal.renderFilledBox((AxisAlignedBB)axisAlignedBB2, (float)f5, (float)f6, (float)f7, (float)f8);
            GL11.glDisable((int)2848);
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
        if (bl) {
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
            bufferBuilder.pos(axisAlignedBB2.minX, axisAlignedBB2.minY, axisAlignedBB2.minZ).color(f9, f10, f11, f12).endVertex();
            bufferBuilder.pos(axisAlignedBB2.minX, axisAlignedBB2.minY, axisAlignedBB2.maxZ).color(f9, f10, f11, f12).endVertex();
            bufferBuilder.pos(axisAlignedBB2.maxX, axisAlignedBB2.minY, axisAlignedBB2.maxZ).color(f9, f10, f11, f12).endVertex();
            bufferBuilder.pos(axisAlignedBB2.maxX, axisAlignedBB2.minY, axisAlignedBB2.minZ).color(f9, f10, f11, f12).endVertex();
            bufferBuilder.pos(axisAlignedBB2.minX, axisAlignedBB2.minY, axisAlignedBB2.minZ).color(f9, f10, f11, f12).endVertex();
            bufferBuilder.pos(axisAlignedBB2.minX, axisAlignedBB2.maxY, axisAlignedBB2.minZ).color(f9, f10, f11, f12).endVertex();
            bufferBuilder.pos(axisAlignedBB2.minX, axisAlignedBB2.maxY, axisAlignedBB2.maxZ).color(f9, f10, f11, f12).endVertex();
            bufferBuilder.pos(axisAlignedBB2.minX, axisAlignedBB2.minY, axisAlignedBB2.maxZ).color(f9, f10, f11, f12).endVertex();
            bufferBuilder.pos(axisAlignedBB2.maxX, axisAlignedBB2.minY, axisAlignedBB2.maxZ).color(f9, f10, f11, f12).endVertex();
            bufferBuilder.pos(axisAlignedBB2.maxX, axisAlignedBB2.maxY, axisAlignedBB2.maxZ).color(f9, f10, f11, f12).endVertex();
            bufferBuilder.pos(axisAlignedBB2.minX, axisAlignedBB2.maxY, axisAlignedBB2.maxZ).color(f9, f10, f11, f12).endVertex();
            bufferBuilder.pos(axisAlignedBB2.maxX, axisAlignedBB2.maxY, axisAlignedBB2.maxZ).color(f9, f10, f11, f12).endVertex();
            bufferBuilder.pos(axisAlignedBB2.maxX, axisAlignedBB2.maxY, axisAlignedBB2.minZ).color(f9, f10, f11, f12).endVertex();
            bufferBuilder.pos(axisAlignedBB2.maxX, axisAlignedBB2.minY, axisAlignedBB2.minZ).color(f9, f10, f11, f12).endVertex();
            bufferBuilder.pos(axisAlignedBB2.maxX, axisAlignedBB2.maxY, axisAlignedBB2.minZ).color(f9, f10, f11, f12).endVertex();
            bufferBuilder.pos(axisAlignedBB2.minX, axisAlignedBB2.maxY, axisAlignedBB2.minZ).color(f9, f10, f11, f12).endVertex();
            tessellator.draw();
            GL11.glDisable((int)2848);
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    public static Color getRainbowAlpha(int n, int n2, float f, float f2, int n3) {
        float f3 = (System.currentTimeMillis() + (long)n2) % (long)n;
        Color color = new Color(Color.getHSBColor(f3 / (float)n, f, f2).getRGB());
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), n3);
    }

    public static void drawGradientFilledBox(AxisAlignedBB axisAlignedBB, Color color, Color color2) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        float f = (float)color2.getAlpha() / 255.0f;
        float f2 = (float)color2.getRed() / 255.0f;
        float f3 = (float)color2.getGreen() / 255.0f;
        float f4 = (float)color2.getBlue() / 255.0f;
        float f5 = (float)color.getAlpha() / 255.0f;
        float f6 = (float)color.getRed() / 255.0f;
        float f7 = (float)color.getGreen() / 255.0f;
        float f8 = (float)color.getBlue() / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        tessellator.draw();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawProperGradientBlockOutline(AxisAlignedBB axisAlignedBB, Color color, Color color2, Color color3, float f) {
        float f2 = (float)color3.getRed() / 255.0f;
        float f3 = (float)color3.getGreen() / 255.0f;
        float f4 = (float)color3.getBlue() / 255.0f;
        float f5 = (float)color3.getAlpha() / 255.0f;
        float f6 = (float)color2.getRed() / 255.0f;
        float f7 = (float)color2.getGreen() / 255.0f;
        float f8 = (float)color2.getBlue() / 255.0f;
        float f9 = (float)color2.getAlpha() / 255.0f;
        float f10 = (float)color.getRed() / 255.0f;
        float f11 = (float)color.getGreen() / 255.0f;
        float f12 = (float)color.getBlue() / 255.0f;
        float f13 = (float)color.getAlpha() / 255.0f;
        double d = (axisAlignedBB.maxY - axisAlignedBB.minY) / 2.0;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)f);
        GL11.glBegin((int)1);
        GL11.glColor4d((double)f2, (double)f3, (double)f4, (double)f5);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glColor4d((double)f6, (double)f7, (double)f8, (double)f9);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)(axisAlignedBB.minY + d), (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)(axisAlignedBB.minY + d), (double)axisAlignedBB.minZ);
        GL11.glColor4f((float)f10, (float)f11, (float)f12, (float)f13);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glColor4d((double)f2, (double)f3, (double)f4, (double)f5);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glColor4d((double)f6, (double)f7, (double)f8, (double)f9);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)(axisAlignedBB.minY + d), (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)(axisAlignedBB.minY + d), (double)axisAlignedBB.maxZ);
        GL11.glColor4d((double)f10, (double)f11, (double)f12, (double)f13);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glColor4d((double)f2, (double)f3, (double)f4, (double)f5);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glColor4d((double)f6, (double)f7, (double)f8, (double)f9);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)(axisAlignedBB.minY + d), (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)(axisAlignedBB.minY + d), (double)axisAlignedBB.maxZ);
        GL11.glColor4d((double)f10, (double)f11, (double)f12, (double)f13);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glColor4d((double)f2, (double)f3, (double)f4, (double)f5);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glColor4d((double)f6, (double)f7, (double)f8, (double)f9);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)(axisAlignedBB.minY + d), (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)(axisAlignedBB.minY + d), (double)axisAlignedBB.minZ);
        GL11.glColor4d((double)f10, (double)f11, (double)f12, (double)f13);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glColor4d((double)f10, (double)f11, (double)f12, (double)f13);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glEnd();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    private static void setupFBO(Framebuffer framebuffer) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT((int)framebuffer.depthBuffer);
        int n = EXTFramebufferObject.glGenRenderbuffersEXT();
        EXTFramebufferObject.glBindRenderbufferEXT((int)36161, (int)n);
        EXTFramebufferObject.glRenderbufferStorageEXT((int)36161, (int)34041, (int)RenderUtil.mc.displayWidth, (int)RenderUtil.mc.displayHeight);
        EXTFramebufferObject.glFramebufferRenderbufferEXT((int)36160, (int)36128, (int)36161, (int)n);
        EXTFramebufferObject.glFramebufferRenderbufferEXT((int)36160, (int)36096, (int)36161, (int)n);
    }

    public static void drawBoxESP(BlockPos blockPos, Color color, float f, boolean bl, boolean bl2, int n) {
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtil.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtil.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtil.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask((boolean)false);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            GL11.glLineWidth((float)f);
            double d = RenderUtil.mc.player.getDistance((double)((float)blockPos.getX() + 0.5f), (double)((float)blockPos.getY() + 0.5f), (double)((float)blockPos.getZ() + 0.5f)) * 0.75;
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

    public static void GLPre(float f) {
        depth = GL11.glIsEnabled((int)2896);
        texture = GL11.glIsEnabled((int)3042);
        clean = GL11.glIsEnabled((int)3553);
        bind = GL11.glIsEnabled((int)2929);
        override = GL11.glIsEnabled((int)2848);
        RenderUtil.GLPre(depth, texture, clean, bind, override, f);
    }

    public static void checkSetupFBO() {
        Framebuffer framebuffer = RenderUtil.mc.framebufferMc;
        if (framebuffer != null && framebuffer.depthBuffer > -1) {
            RenderUtil.setupFBO(framebuffer);
            framebuffer.depthBuffer = -1;
        }
    }

    public static void drawText(AxisAlignedBB axisAlignedBB, String string) {
        if (axisAlignedBB == null || string == null) {
            return;
        }
        GlStateManager.pushMatrix();
        RenderUtil.glBillboardDistanceScaled((float)axisAlignedBB.minX + 0.5f, (float)axisAlignedBB.minY + 0.5f, (float)axisAlignedBB.minZ + 0.5f, (EntityPlayer)RenderUtil.mc.player, 1.0f);
        GlStateManager.disableDepth();
        GlStateManager.translate((double)(-((double)AliceMain.textManager.getStringWidth(string) / 2.0)), (double)0.0, (double)0.0);
        AliceMain.textManager.drawStringWithShadow(string, 0.0f, 0.0f, -5592406);
        GlStateManager.popMatrix();
    }

    public static void drawGradientRect(int n, int n2, int n3, int n4, int n5, int n6) {
        float f = (float)(n5 >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(n5 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n5 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n5 & 0xFF) / 255.0f;
        float f5 = (float)(n6 >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(n6 >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(n6 >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(n6 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos((double)n + (double)n3, (double)n2, 0.0).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos((double)n, (double)n2, 0.0).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos((double)n, (double)n2 + (double)n4, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)n + (double)n3, (double)n2 + (double)n4, 0.0).color(f6, f7, f8, f5).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawBox(BlockPos blockPos, Color color, double d, boolean bl, boolean bl2, int n) {
        if (bl) {
            Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
            RenderUtil.drawOpenGradientBox(blockPos, bl2 ? color2 : color, bl2 ? color : color2, d);
            return;
        }
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtil.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtil.mc.getRenderManager().viewerPosY + d, (double)(blockPos.getZ() + 1) - RenderUtil.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil.mc.getRenderManager().viewerPosZ))) {
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

    public static void drawCircle(float f, float f2, float f3, int n, int n2, int n3) {
        RenderUtil.drawArc(f, f2, f3, n, n2, n3);
    }

    public static void drawCircleOutline(float f, float f2, float f3, int n, int n2, int n3) {
        RenderUtil.drawArcOutline(f, f2, f3, n, n2, n3);
    }

    public static void drawOutlinedRoundedRectangle(int n, int n2, int n3, int n4, float f, float f2, float f3, float f4, float f5, float f6) {
        RenderUtil.drawRoundedRectangle(n, n2, n3, n4, f);
        GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)f5);
        RenderUtil.drawRoundedRectangle((float)n + f6, (float)n2 + f6, (float)n3 - f6 * 2.0f, (float)n4 - f6 * 2.0f, f);
    }

    public static void releaseGL() {
        GlStateManager.enableCull();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
        GlStateManager.color((float)Float.intBitsToFloat(Float.floatToIntBits(12.552789f) ^ 0x7EC8D839), (float)Float.intBitsToFloat(Float.floatToIntBits(7.122752f) ^ 0x7F63ED96), (float)Float.intBitsToFloat(Float.floatToIntBits(5.4278784f) ^ 0x7F2DB12E));
        GL11.glColor4f((float)Float.intBitsToFloat(Float.floatToIntBits(10.5715685f) ^ 0x7EA92525), (float)Float.intBitsToFloat(Float.floatToIntBits(4.9474883f) ^ 0x7F1E51D3), (float)Float.intBitsToFloat(Float.floatToIntBits(4.9044757f) ^ 0x7F1CF177), (float)Float.intBitsToFloat(Float.floatToIntBits(9.482457f) ^ 0x7E97B825));
    }

    public static void renderFive() {
        GL11.glPolygonOffset((float)1.0f, (float)2000000.0f);
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

    public static void draw3DRect(float f, float f2, float f3, float f4, Color color, Color color2, float f5) {
        float f6 = (float)color.getAlpha() / 255.0f;
        float f7 = (float)color.getRed() / 255.0f;
        float f8 = (float)color.getGreen() / 255.0f;
        float f9 = (float)color.getBlue() / 255.0f;
        float f10 = (float)color2.getAlpha() / 255.0f;
        float f11 = (float)color2.getRed() / 255.0f;
        float f12 = (float)color2.getGreen() / 255.0f;
        float f13 = (float)color2.getBlue() / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth((float)f5);
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos((double)f, (double)f4, 0.0).color(f7, f8, f9, f6).endVertex();
        bufferBuilder.pos((double)f3, (double)f4, 0.0).color(f7, f8, f9, f6).endVertex();
        bufferBuilder.pos((double)f3, (double)f2, 0.0).color(f7, f8, f9, f6).endVertex();
        bufferBuilder.pos((double)f, (double)f2, 0.0).color(f7, f8, f9, f6).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    private static void GLPre(boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, float f) {
        if (bl) {
            GL11.glDisable((int)2896);
        }
        if (!bl2) {
            GL11.glEnable((int)3042);
        }
        GL11.glLineWidth((float)f);
        if (bl3) {
            GL11.glDisable((int)3553);
        }
        if (bl4) {
            GL11.glDisable((int)2929);
        }
        if (!bl5) {
            GL11.glEnable((int)2848);
        }
        GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GL11.glHint((int)3154, (int)4354);
        GlStateManager.depthMask((boolean)false);
    }

    private static void GLPost(boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5) {
        GlStateManager.depthMask((boolean)true);
        if (!bl5) {
            GL11.glDisable((int)2848);
        }
        if (bl4) {
            GL11.glEnable((int)2929);
        }
        if (bl3) {
            GL11.glEnable((int)3553);
        }
        if (!bl2) {
            GL11.glDisable((int)3042);
        }
        if (bl) {
            GL11.glEnable((int)2896);
        }
    }

    public static void drawGradientFilledBox(BlockPos blockPos, Color color, Color color2) {
        IBlockState iBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        Vec3d vec3d = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.player, mc.getRenderPartialTicks());
        RenderUtil.drawGradientFilledBox(iBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord), color, color2);
    }

    public static void GlPost() {
        RenderUtil.GLPost(depth, texture, clean, bind, override);
    }

    public static void drawOutlineRect3(double d, double d2, double d3, double d4, Color color, float f) {
        double d5;
        if (d < d3) {
            d5 = d;
            d = d3;
            d3 = d5;
        }
        if (d2 < d4) {
            d5 = d2;
            d2 = d4;
            d4 = d5;
        }
        float f2 = (float)(color.getRGB() >> 24 & 0xFF) / 255.0f;
        float f3 = (float)(color.getRGB() >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(color.getRGB() >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(color.getRGB() & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GL11.glPolygonMode((int)1032, (int)6913);
        GL11.glLineWidth((float)f);
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)f3, (float)f4, (float)f5, (float)f2);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(d, d4, 0.0).endVertex();
        bufferBuilder.pos(d3, d4, 0.0).endVertex();
        bufferBuilder.pos(d3, d2, 0.0).endVertex();
        bufferBuilder.pos(d, d2, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GL11.glPolygonMode((int)1032, (int)6914);
    }

    public static void drawCrossESP(BlockPos blockPos, Color color, float f, boolean bl) {
        RenderUtil.drawBlockCrossedESP(blockPos, new Color(color.getRed(), color.getGreen(), color.getBlue()), f, bl);
    }

    public static void drawCircleOutline(float f, float f2, float f3) {
        RenderUtil.drawCircleOutline(f, f2, f3, 0, 360, 40);
    }

    public static void drawOutlineRect(float f, float f2, float f3, float f4, int n) {
        float f5 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(n & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth((float)1.0f);
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        bufferBuilder.begin(2, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos((double)f, (double)f4, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)f3, (double)f4, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)f3, (double)f2, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)f, (double)f2, 0.0).color(f6, f7, f8, f5).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawBBBox(AxisAlignedBB axisAlignedBB, Color color, int n) {
        AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(axisAlignedBB.minX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtil.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB2.minX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB2.minY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB2.minZ + RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB2.maxX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB2.maxY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB2.maxZ + RenderUtil.mc.getRenderManager().viewerPosZ))) {
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

    public static void drawArc(float f, float f2, float f3, float f4, float f5, int n) {
        GL11.glBegin((int)4);
        int n2 = (int)((float)n / (360.0f / f4)) + 1;
        while ((float)n2 <= (float)n / (360.0f / f5)) {
            double d = Math.PI * 2 * (double)(n2 - 1) / (double)n;
            double d2 = Math.PI * 2 * (double)n2 / (double)n;
            GL11.glVertex2d((double)f, (double)f2);
            GL11.glVertex2d((double)((double)f + Math.cos(d2) * (double)f3), (double)((double)f2 + Math.sin(d2) * (double)f3));
            GL11.glVertex2d((double)((double)f + Math.cos(d) * (double)f3), (double)((double)f2 + Math.sin(d) * (double)f3));
            ++n2;
        }
        RenderUtil.glEnd();
    }

    public static void glScissor(float f, float f2, float f3, float f4, ScaledResolution scaledResolution) {
        GL11.glScissor((int)((int)(f * (float)scaledResolution.getScaleFactor())), (int)((int)((float)RenderUtil.mc.displayHeight - f4 * (float)scaledResolution.getScaleFactor())), (int)((int)((f3 - f) * (float)scaledResolution.getScaleFactor())), (int)((int)((f4 - f2) * (float)scaledResolution.getScaleFactor())));
    }

    public static AxisAlignedBB interpolateAxis(AxisAlignedBB axisAlignedBB) {
        return new AxisAlignedBB(axisAlignedBB.minX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtil.mc.getRenderManager().viewerPosZ);
    }

    public static void drawBlockOutline(BlockPos blockPos, Color color, float f, boolean bl, double d, boolean bl2, boolean bl3, int n) {
        if (bl2) {
            Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
            RenderUtil.drawGradientBlockOutline(blockPos, bl3 ? color2 : color, bl3 ? color : color2, f, d);
            return;
        }
        IBlockState iBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        if ((bl || iBlockState.getMaterial() != Material.AIR) && RenderUtil.mc.world.getWorldBorder().contains(blockPos)) {
            AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtil.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtil.mc.getRenderManager().viewerPosY + d, (double)(blockPos.getZ() + 1) - RenderUtil.mc.getRenderManager().viewerPosZ);
            RenderUtil.drawBlockOutline(axisAlignedBB.expandXyz((double)0.002f), color, f);
        }
    }

    public static void drawColorBox(AxisAlignedBB axisAlignedBB, float f, float f2, float f3, float f4) {
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

    public static void drawFilledBox(AxisAlignedBB axisAlignedBB, int n) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        float f = (float)(n >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f).endVertex();
        tessellator.draw();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawGradientRect(float f, float f2, float f3, float f4, int n, int n2) {
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
        bufferBuilder.pos((double)f + (double)f3, (double)f2, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)f, (double)f2, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)f, (double)f2 + (double)f4, 0.0).color(f10, f11, f12, f9).endVertex();
        bufferBuilder.pos((double)f + (double)f3, (double)f2 + (double)f4, 0.0).color(f10, f11, f12, f9).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawRectBase(int n, double d, double d2, double d3, int n2) {
        double d4;
        if ((double)n < d2) {
            d4 = n;
            n = (int)d2;
            d2 = (int)d4;
        }
        if (d < d3) {
            d4 = d;
            d = d3;
            d3 = d4;
        }
        GlStateManager.enableBlend();
        GL11.glDisable((int)3042);
        GL11.glDisable((int)3008);
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)((float)(n2 >> 16 & 0xFF) / 255.0f), (float)((float)(n2 >> 8 & 0xFF) / 255.0f), (float)((float)(n2 & 0xFF) / 255.0f), (float)((float)(n2 >> 24 & 0xFF) / 255.0f));
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos((double)n, d3, 0.0).endVertex();
        bufferbuilder.pos(d2, d3, 0.0).endVertex();
        bufferbuilder.pos(d2, d, 0.0).endVertex();
        bufferbuilder.pos((double)n, d, 0.0).endVertex();
        tessellator.draw();
        GL11.glEnable((int)3042);
        GL11.glEnable((int)3008);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawBorder(float f, float f2, float f3, float f4, Color color) {
        RenderUtil.drawRectCol(f - 1.0f, f2 - 1.0f, 1.0f, f4 + 2.0f, color);
        RenderUtil.drawRectCol(f + f3, f2 - 1.0f, 1.0f, f4 + 2.0f, color);
        RenderUtil.drawRectCol(f, f2 - 1.0f, f3, 1.0f, color);
        RenderUtil.drawRectCol(f, f2 + f4, f3, 1.0f, color);
    }

    public static void drawOpenGradientBox(BlockPos blockPos, Color color, Color color2, double d) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            if (enumFacing == EnumFacing.UP) continue;
            RenderUtil.drawGradientPlane(blockPos, enumFacing, color, color2, d);
        }
    }

    public static void drawGradientBlockOutline(BlockPos blockPos, Color color, Color color2, float f, double d) {
        IBlockState iBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        Vec3d vec3d = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.player, mc.getRenderPartialTicks());
        RenderUtil.drawGradientBlockOutline(iBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord).addCoord(0.0, d, 0.0), color, color2, f);
    }

    public static void drawEvenBetterGradientBox(BlockPos blockPos, Color color, Color color2, Color color3) {
        float f = (float)color.getRed() / 255.0f;
        float f2 = (float)color.getGreen() / 255.0f;
        float f3 = (float)color.getBlue() / 255.0f;
        float f4 = (float)color.getAlpha() / 255.0f;
        float f5 = (float)color3.getRed() / 255.0f;
        float f6 = (float)color3.getGreen() / 255.0f;
        float f7 = (float)color3.getBlue() / 255.0f;
        float f8 = (float)color3.getAlpha() / 255.0f;
        float f9 = (float)color2.getRed() / 255.0f;
        float f10 = (float)color2.getGreen() / 255.0f;
        float f11 = (float)color2.getBlue() / 255.0f;
        float f12 = (float)color2.getAlpha() / 255.0f;
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtil.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtil.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtil.mc.getRenderManager().viewerPosZ);
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
        bufferBuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawFilledBoxESPN(BlockPos blockPos, Color color) {
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtil.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtil.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtil.mc.getRenderManager().viewerPosZ);
        int n = ColorUtil.toRGBA(color);
        RenderUtil.drawFilledBox(axisAlignedBB, n);
    }

    public static void drawBlockCrossedESP(BlockPos blockPos, Color color, float f, boolean bl) {
        IBlockState iBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        if ((bl || iBlockState.getMaterial() != Material.AIR) && RenderUtil.mc.world.getWorldBorder().contains(blockPos)) {
            assert (RenderUtil.mc.renderViewEntity != null);
            Vec3d vec3d = EntityUtil.interpolateEntity(RenderUtil.mc.renderViewEntity, mc.getRenderPartialTicks());
            RenderUtil.drawBlockCrossed(iBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord), color, f);
        }
    }

    public static void drawGradientPlane(BlockPos blockPos, EnumFacing enumFacing, Color color, Color color2, boolean bl, boolean bl2) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        IBlockState iBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        Vec3d vec3d = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.player, mc.getRenderPartialTicks());
        AxisAlignedBB axisAlignedBB = iBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord);
        float f = (float)color.getRed() / 255.0f;
        float f2 = (float)color.getGreen() / 255.0f;
        float f3 = (float)color.getBlue() / 255.0f;
        float f4 = (float)color.getAlpha() / 255.0f;
        float f5 = (float)color2.getRed() / 255.0f;
        float f6 = (float)color2.getGreen() / 255.0f;
        float f7 = (float)color2.getBlue() / 255.0f;
        float f8 = (float)color2.getAlpha() / 255.0f;
        double d = 0.0;
        double d2 = 0.0;
        double d3 = 0.0;
        double d4 = 0.0;
        double d5 = 0.0;
        double d6 = 0.0;
        if (enumFacing == EnumFacing.DOWN) {
            d = axisAlignedBB.minX;
            d4 = axisAlignedBB.maxX;
            d2 = axisAlignedBB.minY + (bl2 ? 0.5 : 0.0);
            d5 = axisAlignedBB.minY + (bl2 ? 0.5 : 0.0);
            d3 = axisAlignedBB.minZ;
            d6 = axisAlignedBB.maxZ;
        } else if (enumFacing == EnumFacing.UP) {
            d = axisAlignedBB.minX;
            d4 = axisAlignedBB.maxX;
            d2 = axisAlignedBB.maxY / (double)(bl ? 2 : 1);
            d5 = axisAlignedBB.maxY / (double)(bl ? 2 : 1);
            d3 = axisAlignedBB.minZ;
            d6 = axisAlignedBB.maxZ;
        } else if (enumFacing == EnumFacing.EAST) {
            d = axisAlignedBB.maxX;
            d4 = axisAlignedBB.maxX;
            d2 = axisAlignedBB.minY + (bl2 ? 0.5 : 0.0);
            d5 = axisAlignedBB.maxY / (double)(bl ? 2 : 1);
            d3 = axisAlignedBB.minZ;
            d6 = axisAlignedBB.maxZ;
        } else if (enumFacing == EnumFacing.WEST) {
            d = axisAlignedBB.minX;
            d4 = axisAlignedBB.minX;
            d2 = axisAlignedBB.minY + (bl2 ? 0.5 : 0.0);
            d5 = axisAlignedBB.maxY / (double)(bl ? 2 : 1);
            d3 = axisAlignedBB.minZ;
            d6 = axisAlignedBB.maxZ;
        } else if (enumFacing == EnumFacing.SOUTH) {
            d = axisAlignedBB.minX;
            d4 = axisAlignedBB.maxX;
            d2 = axisAlignedBB.minY + (bl2 ? 0.5 : 0.0);
            d5 = axisAlignedBB.maxY / (double)(bl ? 2 : 1);
            d3 = axisAlignedBB.maxZ;
            d6 = axisAlignedBB.maxZ;
        } else if (enumFacing == EnumFacing.NORTH) {
            d = axisAlignedBB.minX;
            d4 = axisAlignedBB.maxX;
            d2 = axisAlignedBB.minY + (bl2 ? 0.5 : 0.0);
            d5 = axisAlignedBB.maxY / (double)(bl ? 2 : 1);
            d3 = axisAlignedBB.minZ;
            d6 = axisAlignedBB.minZ;
        }
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.depthMask((boolean)false);
        bufferBuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        if (enumFacing == EnumFacing.EAST || enumFacing == EnumFacing.WEST || enumFacing == EnumFacing.NORTH || enumFacing == EnumFacing.SOUTH) {
            bufferBuilder.pos(d, d2, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d2, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d2, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d2, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d5, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d5, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d5, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d2, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d5, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d2, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d2, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d2, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d5, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d5, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d5, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d2, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d5, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d2, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d2, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d2, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d2, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d2, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d2, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d5, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d5, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d5, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d5, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d5, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d5, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d5, d6).color(f5, f6, f7, f8).endVertex();
        } else if (enumFacing == EnumFacing.UP) {
            bufferBuilder.pos(d, d2, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d2, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d2, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d2, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d5, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d5, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d5, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d2, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d5, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d2, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d2, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d2, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d5, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d5, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d5, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d2, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d5, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d2, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d2, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d2, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d2, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d2, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d2, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d5, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d5, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d, d5, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d5, d3).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d5, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d5, d6).color(f5, f6, f7, f8).endVertex();
            bufferBuilder.pos(d4, d5, d6).color(f5, f6, f7, f8).endVertex();
        } else if (enumFacing == EnumFacing.DOWN) {
            bufferBuilder.pos(d, d2, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d2, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d2, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d2, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d5, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d5, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d5, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d2, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d5, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d2, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d2, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d2, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d5, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d5, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d5, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d2, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d5, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d2, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d2, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d2, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d2, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d2, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d2, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d5, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d5, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d, d5, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d5, d3).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d5, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d5, d6).color(f, f2, f3, f4).endVertex();
            bufferBuilder.pos(d4, d5, d6).color(f, f2, f3, f4).endVertex();
        }
        tessellator.draw();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }

    public static void drawBoundingBoxBottomBlockPosXInMiddle(BlockPos blockPos, float f, int n, int n2, int n3, int n4) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)f);
        Minecraft minecraft = Minecraft.getMinecraft();
        double d = (double)blockPos.getX() - minecraft.getRenderManager().viewerPosX;
        double d2 = (double)blockPos.getY() - minecraft.getRenderManager().viewerPosY;
        double d3 = (double)blockPos.getZ() - minecraft.getRenderManager().viewerPosZ;
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(d, d2, d3, d + 1.0, d2 + 1.0, d3 + 1.0);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawRectangleXY(float f, float f2, float f3, float f4) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glBegin((int)2);
        GL11.glVertex2d((double)(f + f3), (double)f2);
        GL11.glVertex2d((double)f, (double)f2);
        GL11.glVertex2d((double)f, (double)(f2 + f4));
        GL11.glVertex2d((double)(f + f3), (double)(f2 + f4));
        RenderUtil.glEnd();
    }

    public static void drawOutlineRect1(double d, double d2, double d3, double d4, Color color, float f) {
        double d5;
        if (d < d3) {
            d5 = d;
            d = d3;
            d3 = d5;
        }
        if (d2 < d4) {
            d5 = d2;
            d2 = d4;
            d4 = d5;
        }
        float f2 = (float)(color.getRGB() >> 24 & 0xFF) / 255.0f;
        float f3 = (float)(color.getRGB() >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(color.getRGB() >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(color.getRGB() & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GL11.glPolygonMode((int)1032, (int)6913);
        GL11.glLineWidth((float)f);
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)f3, (float)f4, (float)f5, (float)f2);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(d, d4, 0.0).endVertex();
        bufferBuilder.pos(d3, d4, 0.0).endVertex();
        bufferBuilder.pos(d3, d2, 0.0).endVertex();
        bufferBuilder.pos(d, d2, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GL11.glPolygonMode((int)1032, (int)6914);
    }

    public static void drawOutlineRect2(double d, double d2, double d3, double d4, Color color, float f) {
        double d5;
        if (d < d3) {
            d5 = d;
            d = d3;
            d3 = d5;
        }
        if (d2 < d4) {
            d5 = d2;
            d2 = d4;
            d4 = d5;
        }
        float f2 = (float)(color.getRGB() >> 24 & 0xFF) / 255.0f;
        float f3 = (float)(color.getRGB() >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(color.getRGB() >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(color.getRGB() & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GL11.glPolygonMode((int)1032, (int)6913);
        GL11.glLineWidth((float)f);
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)f3, (float)f4, (float)f5, (float)f2);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(d, d4, 0.0).endVertex();
        bufferBuilder.pos(d3, d4, 0.0).endVertex();
        bufferBuilder.pos(d3, d2, 0.0).endVertex();
        bufferBuilder.pos(d, d2, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GL11.glPolygonMode((int)1032, (int)6914);
    }

    public static void hexColor(int n) {
        float f = (float)(n >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(n & 0xFF) / 255.0f;
        float f4 = (float)(n >> 24 & 0xFF) / 255.0f;
        GL11.glColor4f((float)f, (float)f2, (float)f3, (float)f4);
    }

    public static void release() {
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.popMatrix();
        GL11.glEnable((int)3553);
        GL11.glPolygonMode((int)1032, (int)6914);
    }

    public static void setColor(Color color) {
        GL11.glColor4d((double)((double)color.getRed() / 255.0), (double)((double)color.getGreen() / 255.0), (double)((double)color.getBlue() / 255.0), (double)((double)color.getAlpha() / 255.0));
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
        GL11.glTranslated((double)(d - RenderUtil.mc.renderManager.renderPosX), (double)(d2 - RenderUtil.mc.renderManager.renderPosY), (double)(d3 - RenderUtil.mc.renderManager.renderPosZ));
        sphere.draw(f, n, n2);
        GL11.glLineWidth((float)2.0f);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public static void drawRoundedRectangle(float f, float f2, float f3, float f4, float f5) {
        GL11.glEnable((int)3042);
        RenderUtil.drawArc(f + f3 - f5, f2 + f4 - f5, f5, 0.0f, 90.0f, 16);
        RenderUtil.drawArc(f + f5, f2 + f4 - f5, f5, 90.0f, 180.0f, 16);
        RenderUtil.drawArc(f + f5, f2 + f5, f5, 180.0f, 270.0f, 16);
        RenderUtil.drawArc(f + f3 - f5, f2 + f5, f5, 270.0f, 360.0f, 16);
        GL11.glBegin((int)4);
        GL11.glVertex2d((double)(f + f3 - f5), (double)f2);
        GL11.glVertex2d((double)(f + f5), (double)f2);
        GL11.glVertex2d((double)(f + f3 - f5), (double)(f2 + f5));
        GL11.glVertex2d((double)(f + f3 - f5), (double)(f2 + f5));
        GL11.glVertex2d((double)(f + f5), (double)f2);
        GL11.glVertex2d((double)(f + f5), (double)(f2 + f5));
        GL11.glVertex2d((double)(f + f3), (double)(f2 + f5));
        GL11.glVertex2d((double)f, (double)(f2 + f5));
        GL11.glVertex2d((double)f, (double)(f2 + f4 - f5));
        GL11.glVertex2d((double)(f + f3), (double)(f2 + f5));
        GL11.glVertex2d((double)f, (double)(f2 + f4 - f5));
        GL11.glVertex2d((double)(f + f3), (double)(f2 + f4 - f5));
        GL11.glVertex2d((double)(f + f3 - f5), (double)(f2 + f4 - f5));
        GL11.glVertex2d((double)(f + f5), (double)(f2 + f4 - f5));
        GL11.glVertex2d((double)(f + f3 - f5), (double)(f2 + f4));
        GL11.glVertex2d((double)(f + f3 - f5), (double)(f2 + f4));
        GL11.glVertex2d((double)(f + f5), (double)(f2 + f4 - f5));
        GL11.glVertex2d((double)(f + f5), (double)(f2 + f4));
        RenderUtil.glEnd();
    }

    public static void drawArcOutline(float f, float f2, float f3, float f4, float f5, int n) {
        GL11.glBegin((int)2);
        int n2 = (int)((float)n / (360.0f / f4)) + 1;
        while ((float)n2 <= (float)n / (360.0f / f5)) {
            double d = Math.PI * 2 * (double)n2 / (double)n;
            GL11.glVertex2d((double)((double)f + Math.cos(d) * (double)f3), (double)((double)f2 + Math.sin(d) * (double)f3));
            ++n2;
        }
        RenderUtil.glEnd();
    }

    public static void drawBox(BlockPos blockPos, Color color) {
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtil.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtil.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtil.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil.mc.getRenderManager().viewerPosZ))) {
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

    public static void drawBetterGradientBox(BlockPos blockPos, Color color, Color color2, Color color3) {
        float f = (float)color.getRed() / 255.0f;
        float f2 = (float)color.getGreen() / 255.0f;
        float f3 = (float)color.getBlue() / 255.0f;
        float f4 = (float)color.getAlpha() / 255.0f;
        float f5 = (float)color3.getRed() / 255.0f;
        float f6 = (float)color3.getGreen() / 255.0f;
        float f7 = (float)color3.getBlue() / 255.0f;
        float f8 = (float)color3.getAlpha() / 255.0f;
        float f9 = (float)color2.getRed() / 255.0f;
        float f10 = (float)color2.getGreen() / 255.0f;
        float f11 = (float)color2.getBlue() / 255.0f;
        float f12 = (float)color2.getAlpha() / 255.0f;
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtil.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtil.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtil.mc.getRenderManager().viewerPosZ);
        double d = (axisAlignedBB.maxY - axisAlignedBB.minY) / 2.0;
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
        bufferBuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY + d, axisAlignedBB.minZ).color(f9, f10, f11, f12).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY + d, axisAlignedBB.maxZ).color(f9, f10, f11, f12).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY + d, axisAlignedBB.maxZ).color(f9, f10, f11, f12).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY + d, axisAlignedBB.maxZ).color(f9, f10, f11, f12).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY + d, axisAlignedBB.minZ).color(f9, f10, f11, f12).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY + d, axisAlignedBB.minZ).color(f9, f10, f11, f12).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY + d, axisAlignedBB.minZ).color(f9, f10, f11, f12).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY + d, axisAlignedBB.maxZ).color(f9, f10, f11, f12).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY + d, axisAlignedBB.maxZ).color(f9, f10, f11, f12).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f, f2, f3, f4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY + d, axisAlignedBB.maxZ).color(f9, f10, f11, f12).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static Vec3d to2D(double d, double d2, double d3) {
        GL11.glGetFloat((int)2982, (FloatBuffer)modelView);
        GL11.glGetFloat((int)2983, (FloatBuffer)projection);
        GL11.glGetInteger((int)2978, (IntBuffer)viewport);
        boolean bl = GLU.gluProject((float)((float)d), (float)((float)d2), (float)((float)d3), (FloatBuffer)modelView, (FloatBuffer)projection, (IntBuffer)viewport, (FloatBuffer)screenCoords);
        if (bl) {
            return new Vec3d((double)screenCoords.get(0), (double)((float)Display.getHeight() - screenCoords.get(1)), (double)screenCoords.get(2));
        }
        return null;
    }

    public static void drawProjectedText(GLUProjection.Projection projection, float f, float f2, String string, Color color, boolean bl) {
        if (projection.getType() == GLUProjection.Projection.Type.INSIDE) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((double)projection.getX(), (double)projection.getY(), (double)0.0);
            AliceMain.textManager.drawString(string, -((float)AliceMain.textManager.getStringWidth(string) / 2.0f) + f, f2, color.getRGB(), bl);
            GlStateManager.translate((double)(-projection.getX()), (double)(-projection.getY()), (double)0.0);
            GlStateManager.popMatrix();
        }
    }

    public static void drawBar(GLUProjection.Projection projection, float f, float f2, float f3, Color color, Color color2) {
        if (projection.getType() == GLUProjection.Projection.Type.INSIDE) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((double)projection.getX(), (double)projection.getY(), (double)0.0);
            RenderUtil.drawOutlineRect(-(f3 / 2.0f), -(f2 / 2.0f), f3, f2, color2.getRGB());
            RenderUtil.drawRect(-(f3 / 2.0f), -(f2 / 2.0f), f, f2, color.getRGB());
            GlStateManager.translate((double)(-projection.getX()), (double)(-projection.getY()), (double)0.0);
            GlStateManager.popMatrix();
        }
    }

    public static void drawColoredBoundingBox(AxisAlignedBB axisAlignedBB, float f, float f2, float f3, float f4, float f5) {
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
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f2, f3, f4, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(f2, f3, f4, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(f2, f3, f4, 0.0f).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f2, f3, f4, 0.0f).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawSidewaysGradientRect(int n, int n2, int n3, int n4, int n5, int n6) {
        float f = (float)(n5 >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(n5 >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n5 >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n5 & 0xFF) / 255.0f;
        float f5 = (float)(n6 >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(n6 >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(n6 >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(n6 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel((int)7425);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos((double)n3, (double)n2, 0.0).color(f2, f3, f4, f).endVertex();
        bufferBuilder.pos((double)n, (double)n2, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)n, (double)n4, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)n3, (double)n4, 0.0).color(f2, f3, f4, f).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    static {
        frustrum = new Frustum();
        screenCoords = BufferUtils.createFloatBuffer((int)3);
        viewport = BufferUtils.createIntBuffer((int)16);
        modelView = BufferUtils.createFloatBuffer((int)16);
        projection = BufferUtils.createFloatBuffer((int)16);
        itemRender = mc.getRenderItem();
        camera = new Frustum();
        depth = GL11.glIsEnabled((int)2896);
        texture = GL11.glIsEnabled((int)3042);
        clean = GL11.glIsEnabled((int)3553);
        bind = GL11.glIsEnabled((int)2929);
        override = GL11.glIsEnabled((int)2848);
        tessellator = Tessellator.getInstance();
        builder = tessellator.getBuffer();
        bufferbuilder = Tessellator.getInstance().getBuffer();
    }

    public static void drawWaypointImage(BlockPos blockPos, GLUProjection.Projection projection, Color color, String string, boolean bl, Color color2) {
        GlStateManager.pushMatrix();
        GlStateManager.translate((double)projection.getX(), (double)projection.getY(), (double)0.0);
        String string2 = String.valueOf(new StringBuilder().append(string).append(": ").append(Math.round(RenderUtil.mc.player.getDistance((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()))).append("M"));
        float f = AliceMain.textManager.getStringWidth(string2);
        AliceMain.textManager.drawString(string2, -(f / 2.0f), -((float)AliceMain.textManager.getFontHeight() / 2.0f) + (float)AliceMain.textManager.getFontHeight() / 2.0f, color.getRGB(), false);
        GlStateManager.translate((double)(-projection.getX()), (double)(-projection.getY()), (double)0.0);
        GlStateManager.popMatrix();
    }

    public static void drawTracerPointer(float f, float f2, float f3, float f4, float f5, boolean bl, float f6, int n) {
        boolean bl2 = GL11.glIsEnabled((int)3042);
        float f7 = (float)(n >> 24 & 0xFF) / 255.0f;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glPushMatrix();
        RenderUtil.hexColor(n);
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

    public static void drawGradientPlane(BlockPos blockPos, EnumFacing enumFacing, Color color, Color color2, double d) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        IBlockState iBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        Vec3d vec3d = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.player, mc.getRenderPartialTicks());
        AxisAlignedBB axisAlignedBB = iBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord).addCoord(0.0, d, 0.0);
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

    public static void addBuilderVertex(BufferBuilder bufferBuilder, double d, double d2, double d3, Color color) {
        bufferBuilder.pos(d, d2, d3).color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f).endVertex();
    }

    public static void drawFilledRectangle(float f, float f2, float f3, float f4) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)(f + f3), (double)f2);
        GL11.glVertex2d((double)f, (double)f2);
        GL11.glVertex2d((double)f, (double)(f2 + f4));
        GL11.glVertex2d((double)(f + f3), (double)(f2 + f4));
        RenderUtil.glEnd();
    }

    public static void blockESP(BlockPos blockPos, Color color, double d, double d2) {
        RenderUtil.blockEsp(blockPos, color, d, d2);
    }

    public static void blockEsp(BlockPos blockPos, Color color, double d, double d2) {
        double d3 = (double)blockPos.getX() - RenderUtil.mc.renderManager.renderPosX;
        double d4 = (double)blockPos.getY() - RenderUtil.mc.renderManager.renderPosY;
        double d5 = (double)blockPos.getZ() - RenderUtil.mc.renderManager.renderPosZ;
        GL11.glPushMatrix();
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glColor4d((double)((float)color.getRed() / 255.0f), (double)((float)color.getGreen() / 255.0f), (double)((float)color.getBlue() / 255.0f), (double)0.25);
        RenderUtil.drawColorBox(new AxisAlignedBB(d3, d4, d5, d3 + d2, d4 + 1.0, d5 + d), 0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glColor4d((double)0.0, (double)0.0, (double)0.0, (double)0.5);
        RenderUtil.drawSelectionBoundingBox(new AxisAlignedBB(d3, d4, d5, d3 + d2, d4 + 1.0, d5 + d));
        GL11.glLineWidth((float)2.0f);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static AxisAlignedBB getBoundingBox(BlockPos blockPos) {
        return RenderUtil.mc.world.getBlockState(blockPos).getBoundingBox((IBlockAccess)RenderUtil.mc.world, blockPos).offset(blockPos);
    }

    public static void drawClock(float f, float f2, float f3, int n, int n2, float f4, boolean bl, Color color) {
        Disk disk = new Disk();
        int n3 = 180 + -(Calendar.getInstance().get(10) * 30 + Calendar.getInstance().get(12) / 2);
        int n4 = 180 + -(Calendar.getInstance().get(12) * 6 + Calendar.getInstance().get(13) / 10);
        int n5 = 180 + -(Calendar.getInstance().get(13) * 6);
        int n6 = Calendar.getInstance().get(12);
        int n7 = Calendar.getInstance().get(10);
        if (bl) {
            GL11.glPushMatrix();
            GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glEnable((int)3042);
            GL11.glLineWidth((float)f4);
            GL11.glDisable((int)3553);
            disk.setOrientation(100020);
            disk.setDrawStyle(100012);
            GL11.glTranslated((double)f, (double)f2, (double)0.0);
            disk.draw(0.0f, f3, n, n2);
            GL11.glEnable((int)3553);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
            GL11.glEnable((int)3042);
            GL11.glLineWidth((float)f4);
            GL11.glDisable((int)3553);
            GL11.glBegin((int)3);
            ArrayList<Vec2f> arrayList = new ArrayList<Vec2f>();
            float f5 = (float)(System.currentTimeMillis() % 7200L) / 7200.0f;
            for (int i = 0; i <= 360; ++i) {
                Vec2f vec2f = new Vec2f(f + (float)Math.sin((double)i * Math.PI / 180.0) * f3, f2 + (float)Math.cos((double)i * Math.PI / 180.0) * f3);
                arrayList.add(vec2f);
            }
            Color color2 = new Color(Color.HSBtoRGB(f5, 1.0f, 1.0f));
            for (int i = 0; i < arrayList.size() - 1; ++i) {
                GL11.glColor4f((float)((float)color2.getRed() / 255.0f), (float)((float)color2.getGreen() / 255.0f), (float)((float)color2.getBlue() / 255.0f), (float)((float)color2.getAlpha() / 255.0f));
                GL11.glVertex3d((double)((Vec2f)arrayList.get((int)i)).x, (double)((Vec2f)arrayList.get((int)i)).y, (double)0.0);
                GL11.glVertex3d((double)((Vec2f)arrayList.get((int)(i + 1))).x, (double)((Vec2f)arrayList.get((int)(i + 1))).y, (double)0.0);
                color2 = new Color(Color.HSBtoRGB(f5 += 0.0027777778f, 1.0f, 1.0f));
            }
            GL11.glEnd();
            GL11.glEnable((int)3553);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
        RenderUtil.drawLine(f, f2, f + (float)Math.sin((double)n3 * Math.PI / 180.0) * (f3 / 2.0f), f2 + (float)Math.cos((double)n3 * Math.PI / 180.0) * (f3 / 2.0f), 1.0f, Color.WHITE.getRGB());
        RenderUtil.drawLine(f, f2, f + (float)Math.sin((double)n4 * Math.PI / 180.0) * (f3 - f3 / 10.0f), f2 + (float)Math.cos((double)n4 * Math.PI / 180.0) * (f3 - f3 / 10.0f), 1.0f, Color.WHITE.getRGB());
        RenderUtil.drawLine(f, f2, f + (float)Math.sin((double)n5 * Math.PI / 180.0) * (f3 - f3 / 10.0f), f2 + (float)Math.cos((double)n5 * Math.PI / 180.0) * (f3 - f3 / 10.0f), 1.0f, Color.RED.getRGB());
    }

    public static void drawBorderedRect(int n, double d, int n2, double d2, int n3, int n4, int n5, boolean bl) {
        if (bl) {
            n4 = ColorUtil.shadeColour(n4, -20);
            n5 = ColorUtil.shadeColour(n5, -20);
        }
        RenderUtil.drawRectBase(n + n3, d + (double)n3, n2 - n3, d2 - (double)n3, n4);
        RenderUtil.drawRectBase(n, d + (double)n3, n + n3, d2 - (double)n3, n5);
        RenderUtil.drawRectBase(n2 - n3, d + (double)n3, n2, d2 - (double)n3, n5);
        RenderUtil.drawRectBase(n, d, n2, d + (double)n3, n5);
        RenderUtil.drawRectBase(n, d2 - (double)n3, n2, d2, n5);
    }

    public static void drawRectCol(float f, float f2, float f3, float f4, Color color) {
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

    public static void drawBlockCrossed(AxisAlignedBB axisAlignedBB, Color color, float f) {
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
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(f2, f3, f4, f5).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(f2, f3, f4, f5).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawFilledCircle(double d, double d2, double d3, Color color, double d4) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
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

    public static void glStart(float f, float f2, float f3, float f4) {
        RenderUtil.glrendermethod();
        GL11.glColor4f((float)f, (float)f2, (float)f3, (float)f4);
    }

    public static boolean isInViewFrustrum(AxisAlignedBB axisAlignedBB) {
        Entity entity = Minecraft.getMinecraft().getRenderViewEntity();
        frustrum.setPosition(Objects.requireNonNull(entity).posX, entity.posY, entity.posZ);
        return frustrum.isBoundingBoxInFrustum(axisAlignedBB);
    }

    public static void drawOutlinedBox(AxisAlignedBB axisAlignedBB) {
        GL11.glBegin((int)1);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.minY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.maxX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.maxZ);
        GL11.glVertex3d((double)axisAlignedBB.minX, (double)axisAlignedBB.maxY, (double)axisAlignedBB.minZ);
        GL11.glEnd();
    }

    public static AxisAlignedBB fixBB(AxisAlignedBB axisAlignedBB) {
        AxisAlignedBB axisAlignedBB2 = axisAlignedBB;
        return new AxisAlignedBB(axisAlignedBB2.minX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB2.minY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB2.minZ - RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB2.maxX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB2.maxY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB2.maxZ - RenderUtil.mc.getRenderManager().viewerPosZ);
    }

    public static void prepareGL() {
        GL11.glBlendFunc((int)770, (int)771);
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth((float)Float.intBitsToFloat(Float.floatToIntBits(5.0675106f) ^ 0x7F22290C));
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        GlStateManager.color((float)Float.intBitsToFloat(Float.floatToIntBits(11.925059f) ^ 0x7EBECD0B), (float)Float.intBitsToFloat(Float.floatToIntBits(18.2283f) ^ 0x7E11D38F), (float)Float.intBitsToFloat(Float.floatToIntBits(9.73656f) ^ 0x7E9BC8F3));
    }

    public static void drawTricolorGradientBox(BlockPos blockPos, Color color, Color color2, Color color3) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            if (enumFacing == EnumFacing.UP) continue;
            RenderUtil.drawGradientPlane(blockPos, enumFacing, color, color2, true, false);
        }
        for (EnumFacing enumFacing : EnumFacing.values()) {
            if (enumFacing == EnumFacing.DOWN) continue;
            RenderUtil.drawGradientPlane(blockPos, enumFacing, color2, color3, true, true);
        }
    }

    public static void drawTextWhite(AxisAlignedBB axisAlignedBB, String string) {
        if (axisAlignedBB == null || string == null) {
            return;
        }
        GlStateManager.pushMatrix();
        RenderUtil.glBillboardDistanceScaled((float)axisAlignedBB.minX + 0.5f, (float)axisAlignedBB.minY + 0.5f, (float)axisAlignedBB.minZ + 0.5f, (EntityPlayer)RenderUtil.mc.player, 1.0f);
        GlStateManager.disableDepth();
        GlStateManager.translate((double)(-((double)AliceMain.textManager.getStringWidth(string) / 2.0)), (double)0.0, (double)0.0);
        AliceMain.textManager.drawStringWithShadow(string, 0.0f, 0.0f, ColorUtil.toRGBA(255, 255, 255, 255));
        GlStateManager.popMatrix();
    }

    public static void drawGradientBoxTest(BlockPos blockPos, Color color, Color color2) {
    }

    public static Vec3d updateToCamera(Vec3d vec3d) {
        return new Vec3d(vec3d.xCoord - RenderUtil.mc.getRenderManager().viewerPosX, vec3d.yCoord - RenderUtil.mc.getRenderManager().viewerPosY, vec3d.zCoord - RenderUtil.mc.getRenderManager().viewerPosZ);
    }

    public static float[][] getBipedRotations(ModelBiped modelBiped) {
        float[][] arrarrf = new float[5][];
        float[] arrf = new float[]{modelBiped.bipedHead.rotateAngleX, modelBiped.bipedHead.rotateAngleY, modelBiped.bipedHead.rotateAngleZ};
        arrarrf[0] = arrf;
        float[] arrf2 = new float[]{modelBiped.bipedRightArm.rotateAngleX, modelBiped.bipedRightArm.rotateAngleY, modelBiped.bipedRightArm.rotateAngleZ};
        arrarrf[1] = arrf2;
        float[] arrf3 = new float[]{modelBiped.bipedLeftArm.rotateAngleX, modelBiped.bipedLeftArm.rotateAngleY, modelBiped.bipedLeftArm.rotateAngleZ};
        arrarrf[2] = arrf3;
        float[] arrf4 = new float[]{modelBiped.bipedRightLeg.rotateAngleX, modelBiped.bipedRightLeg.rotateAngleY, modelBiped.bipedRightLeg.rotateAngleZ};
        arrarrf[3] = arrf4;
        float[] arrf5 = new float[]{modelBiped.bipedLeftLeg.rotateAngleX, modelBiped.bipedLeftLeg.rotateAngleY, modelBiped.bipedLeftLeg.rotateAngleZ};
        arrarrf[4] = arrf5;
        return arrarrf;
    }

    public static void drawBoxESP(BlockPos blockPos, Color color, boolean bl, Color color2, float f, boolean bl2, boolean bl3, int n, boolean bl4) {
        if (bl3) {
            RenderUtil.drawBox(blockPos, new Color(color.getRed(), color.getGreen(), color.getBlue(), n));
        }
        if (bl2) {
            RenderUtil.drawBlockOutline(blockPos, bl ? color2 : color, f, bl4);
        }
    }

    public static void drawProperGradientBlockOutline(BlockPos blockPos, Color color, Color color2, Color color3, float f) {
        IBlockState iBlockState = RenderUtil.mc.world.getBlockState(blockPos);
        Vec3d vec3d = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.player, mc.getRenderPartialTicks());
        RenderUtil.drawProperGradientBlockOutline(iBlockState.getSelectedBoundingBox((World)RenderUtil.mc.world, blockPos).expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord), color, color2, color3, f);
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

    public static void renderBorder(int n, int n2, int n3, int n4, int n5, Color color) {
        Gui.drawRect((int)n, (int)n2, (int)n3, (int)(n2 + n5), (int)color.getRGB());
        Gui.drawRect((int)n, (int)n2, (int)(n + n5), (int)n4, (int)color.getRGB());
        Gui.drawRect((int)n3, (int)n2, (int)(n3 - n5), (int)n4, (int)color.getRGB());
        Gui.drawRect((int)n, (int)n4, (int)n3, (int)(n4 - n5), (int)color.getRGB());
    }

    public static void drawClosedGradientBox(BlockPos blockPos, Color color, Color color2, double d) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            RenderUtil.drawGradientPlane(blockPos, enumFacing, color, color2, d);
        }
    }

    public static void prepare() {
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.depthMask((boolean)false);
        GlStateManager.disableAlpha();
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2848);
        GL11.glBlendFunc((int)770, (int)771);
    }

    public static void drawSelectionBoundingBox(AxisAlignedBB axisAlignedBB) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        tessellator.draw();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        tessellator.draw();
        bufferBuilder.begin(1, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        tessellator.draw();
    }

    public static void drawBoundingBoxBottomBlockPosXInMiddle2(BlockPos blockPos, float f, int n, int n2, int n3, int n4) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)f);
        Minecraft minecraft = Minecraft.getMinecraft();
        double d = (double)blockPos.getX() - minecraft.getRenderManager().viewerPosX;
        double d2 = (double)blockPos.getY() - minecraft.getRenderManager().viewerPosY;
        double d3 = (double)blockPos.getZ() - minecraft.getRenderManager().viewerPosZ;
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(d, d2, d3, d + 1.0, d2 + 1.0, d3 + 1.0);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawHLineG(int n, int n2, int n3, int n4, int n5) {
        RenderUtil.drawSidewaysGradientRect(n, n2, n + n3, n2 + 1, n4, n5);
    }

    public static boolean isInViewFrustrum(Entity entity) {
        return RenderUtil.isInViewFrustrum(entity.getEntityBoundingBox()) || entity.ignoreFrustumCheck;
    }

    public static void renderOne(float f) {
        RenderUtil.checkSetupFBO();
        GL11.glPushAttrib((int)1048575);
        GL11.glDisable((int)3008);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glLineWidth((float)f);
        GL11.glEnable((int)2848);
        GL11.glEnable((int)2960);
        GL11.glClear((int)1024);
        GL11.glClearStencil((int)15);
        GL11.glStencilFunc((int)512, (int)1, (int)15);
        GL11.glStencilOp((int)7681, (int)7681, (int)7681);
        GL11.glPolygonMode((int)1032, (int)6913);
    }

    public static void drawTexturedRect(int n, int n2, int n3, int n4, int n5, int n6, int n7) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.pos((double)n, (double)(n2 + n6), (double)n7).tex((double)((float)n3 * 0.00390625f), (double)((float)(n4 + n6) * 0.00390625f)).endVertex();
        bufferBuilder.pos((double)(n + n5), (double)(n2 + n6), (double)n7).tex((double)((float)(n3 + n5) * 0.00390625f), (double)((float)(n4 + n6) * 0.00390625f)).endVertex();
        bufferBuilder.pos((double)(n + n5), (double)n2, (double)n7).tex((double)((float)(n3 + n5) * 0.00390625f), (double)((float)n4 * 0.00390625f)).endVertex();
        bufferBuilder.pos((double)n, (double)n2, (double)n7).tex((double)((float)n3 * 0.00390625f), (double)((float)n4 * 0.00390625f)).endVertex();
        tessellator.draw();
    }

    public static void boxESP(BlockPos blockPos, Color color, float f, boolean bl, boolean bl2, int n, boolean bl3) {
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtil.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtil.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtil.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtil.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtil.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtil.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Objects.requireNonNull(RenderUtil.mc.getRenderViewEntity()).posX, RenderUtil.mc.getRenderViewEntity().posY, RenderUtil.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil.mc.getRenderManager().viewerPosZ))) {
            double d = 0.0;
            double d2 = 0.0;
            double d3 = 0.0;
            double d4 = 0.0;
            double d5 = 0.0;
            double d6 = 0.0;
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask((boolean)false);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            GL11.glLineWidth((float)f);
            double d7 = RenderUtil.mc.playerController.curBlockDamageMP;
            if (bl3) {
                d6 = axisAlignedBB.minX + 0.5 - d7 / 2.0;
                d5 = axisAlignedBB.minY + 0.5 - d7 / 2.0;
                d4 = axisAlignedBB.minZ + 0.5 - d7 / 2.0;
                d3 = axisAlignedBB.maxX - 0.5 + d7 / 2.0;
                d2 = axisAlignedBB.maxY - 0.5 + d7 / 2.0;
                d = axisAlignedBB.maxZ - 0.5 + d7 / 2.0;
            }
            AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(d6, d5, d4, d3, d2, d);
            if (bl2) {
                RenderUtil.drawFilledBox(axisAlignedBB2, new Color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)n / 255.0f).getRGB());
            }
            GL11.glDisable((int)2848);
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    public static AxisAlignedBB updateToCamera(AxisAlignedBB axisAlignedBB) {
        return new AxisAlignedBB(axisAlignedBB.minX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtil.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtil.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtil.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtil.mc.getRenderManager().viewerPosZ);
    }

    public static void updateModelViewProjectionMatrix() {
        GL11.glGetFloat((int)2982, (FloatBuffer)modelView);
        GL11.glGetFloat((int)2983, (FloatBuffer)projection);
        GL11.glGetInteger((int)2978, (IntBuffer)viewport);
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        GLUProjection.getInstance().updateMatrices(viewport, modelView, projection, (float)scaledResolution.getScaledWidth() / (float)Minecraft.getMinecraft().displayWidth, (float)scaledResolution.getScaledHeight() / (float)Minecraft.getMinecraft().displayHeight);
    }

    public static void drawRectangleCorrectly(int n, int n2, int n3, int n4, int n5) {
        GL11.glLineWidth((float)1.0f);
        Gui.drawRect((int)n, (int)n2, (int)(n + n3), (int)(n2 + n4), (int)n5);
    }

    public static void drawBoxESP(BlockPos blockPos, Color color, boolean bl, Color color2, float f, boolean bl2, boolean bl3, int n, boolean bl4, double d, boolean bl5, boolean bl6, boolean bl7, boolean bl8, int n2) {
        if (bl3) {
            RenderUtil.drawBox(blockPos, new Color(color.getRed(), color.getGreen(), color.getBlue(), n), d, bl5, bl7, n2);
        }
        if (bl2) {
            RenderUtil.drawBlockOutline(blockPos, bl ? color2 : color, f, bl4, d, bl6, bl8, n2);
        }
    }

    public static void drawGradientRainbowOutLine(double d, double d2, double d3, double d4, float f) {
        double d5;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)f);
        if (d < d3) {
            d5 = d;
            d = d3;
            d3 = d5;
        }
        if (d2 < d4) {
            d5 = d2;
            d2 = d4;
            d4 = d5;
        }
    }

    public static void drawChungusESP(GLUProjection.Projection projection, float f, float f2, ResourceLocation resourceLocation) {
        if (projection.getType() == GLUProjection.Projection.Type.INSIDE) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((double)projection.getX(), (double)projection.getY(), (double)0.0);
            mc.getTextureManager().bindTexture(resourceLocation);
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            mc.getTextureManager().bindTexture(resourceLocation);
            RenderUtil.drawCompleteImage(0.0f, 0.0f, f, f2);
            mc.getTextureManager().deleteTexture(resourceLocation);
            GlStateManager.enableBlend();
            GlStateManager.disableTexture2D();
            GlStateManager.translate((double)(-projection.getX()), (double)(-projection.getY()), (double)0.0);
            GlStateManager.popMatrix();
        }
    }

    public static void renderFour(Color color) {
        RenderUtil.setColor(color);
        GL11.glDepthMask((boolean)false);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)10754);
        GL11.glPolygonOffset((float)1.0f, (float)-2000000.0f);
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
    }

    public static void glEnd() {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
    }

    public static class RenderTesselator
    extends Tessellator {
        public static /* synthetic */ RenderTesselator INSTANCE;

        static {
            INSTANCE = new RenderTesselator();
        }

        public RenderTesselator() {
            super(0x200000);
        }

        public static void drawFullBox(AxisAlignedBB axisAlignedBB, BlockPos blockPos, float f, int n, int n2) {
            int n3 = n >>> 24 & 0xFF;
            int n4 = n >>> 16 & 0xFF;
            int n5 = n >>> 8 & 0xFF;
            int n6 = n & 0xFF;
            RenderTesselator.drawFullBox(axisAlignedBB, blockPos, f, n4, n5, n6, n3, n2);
        }

        public static void drawBox(BlockPos blockPos, int n, int n2, int n3, int n4, int n5) {
            RenderTesselator.drawBox(INSTANCE.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, n5);
        }

        public static void drawBox(BufferBuilder bufferBuilder, float f, float f2, float f3, float f4, float f5, float f6, int n, int n2, int n3, int n4, int n5) {
            if ((n5 & 1) != 0) {
                bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            }
            if ((n5 & 2) != 0) {
                bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            }
            if ((n5 & 4) != 0) {
                bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            }
            if ((n5 & 8) != 0) {
                bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            }
            if ((n5 & 0x10) != 0) {
                bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            }
            if ((n5 & 0x20) != 0) {
                bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            }
        }

        public static void drawFullBox(AxisAlignedBB axisAlignedBB, BlockPos blockPos, float f, int n, int n2, int n3, int n4, int n5) {
            RenderTesselator.prepare(7);
            RenderTesselator.drawBox(blockPos, n, n2, n3, n4, 63);
            RenderTesselator.release();
            RenderTesselator.drawBoundingBox(axisAlignedBB, f, n, n2, n3, n5);
        }

        public static void drawHalfBox(BlockPos blockPos, int n, int n2, int n3, int n4, int n5) {
            RenderTesselator.drawBox(INSTANCE.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 0.5f, 1.0f, n, n2, n3, n4, n5);
        }

        public static void begin(int n) {
            INSTANCE.getBuffer().begin(n, DefaultVertexFormats.POSITION_COLOR);
        }

        public static void drawBox(BlockPos blockPos, int n, int n2) {
            int n3 = n >>> 24 & 0xFF;
            int n4 = n >>> 16 & 0xFF;
            int n5 = n >>> 8 & 0xFF;
            int n6 = n & 0xFF;
            RenderTesselator.drawBox(blockPos, n4, n5, n6, n3, n2);
        }

        public static void drawLines(BufferBuilder bufferBuilder, float f, float f2, float f3, float f4, float f5, float f6, int n, int n2, int n3, int n4, int n5) {
            if ((n5 & 0x11) != 0) {
                bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            }
            if ((n5 & 0x12) != 0) {
                bufferBuilder.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            }
            if ((n5 & 0x21) != 0) {
                bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            }
            if ((n5 & 0x22) != 0) {
                bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            }
            if ((n5 & 5) != 0) {
                bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            }
            if ((n5 & 6) != 0) {
                bufferBuilder.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            }
            if ((n5 & 9) != 0) {
                bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            }
            if ((n5 & 0xA) != 0) {
                bufferBuilder.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            }
            if ((n5 & 0x14) != 0) {
                bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            }
            if ((n5 & 0x24) != 0) {
                bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            }
            if ((n5 & 0x18) != 0) {
                bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            }
            if ((n5 & 0x28) != 0) {
                bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
                bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            }
        }

        public static void releaseGL() {
            GlStateManager.enableCull();
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableTexture2D();
            GlStateManager.enableBlend();
            GlStateManager.enableDepth();
        }

        public static void prepare(int n) {
            RenderTesselator.prepareGL();
            RenderTesselator.begin(n);
        }

        public static void drawBoundingBox(AxisAlignedBB axisAlignedBB, float f, float f2, float f3, float f4, float f5) {
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

        public static void render() {
            INSTANCE.draw();
        }

        public static void prepareGL() {
            GL11.glBlendFunc((int)770, (int)771);
            GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
            GlStateManager.glLineWidth((float)1.5f);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask((boolean)false);
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            GlStateManager.enableAlpha();
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f);
        }

        public static void drawHalfBox(BlockPos blockPos, int n, int n2) {
            int n3 = n >>> 24 & 0xFF;
            int n4 = n >>> 16 & 0xFF;
            int n5 = n >>> 8 & 0xFF;
            int n6 = n & 0xFF;
            RenderTesselator.drawHalfBox(blockPos, n4, n5, n6, n3, n2);
        }

        public static void drawBox(float f, float f2, float f3, int n, int n2) {
            int n3 = n >>> 24 & 0xFF;
            int n4 = n >>> 16 & 0xFF;
            int n5 = n >>> 8 & 0xFF;
            int n6 = n & 0xFF;
            RenderTesselator.drawBox(INSTANCE.getBuffer(), f, f2, f3, 1.0f, 1.0f, 1.0f, n4, n5, n6, n3, n2);
        }

        public static void release() {
            RenderTesselator.render();
            RenderTesselator.releaseGL();
        }

        public static BufferBuilder getBufferBuilder() {
            return INSTANCE.getBuffer();
        }
    }

    public static final class GeometryMasks {
        public static final /* synthetic */ HashMap<EnumFacing, Integer> FACEMAP;

        static {
            FACEMAP = new HashMap();
            FACEMAP.put(EnumFacing.DOWN, 1);
            FACEMAP.put(EnumFacing.WEST, 16);
            FACEMAP.put(EnumFacing.NORTH, 4);
            FACEMAP.put(EnumFacing.SOUTH, 8);
            FACEMAP.put(EnumFacing.EAST, 32);
            FACEMAP.put(EnumFacing.UP, 2);
        }

        public static final class Quad {
            public static final /* synthetic */ int NORTH;
            public static final /* synthetic */ int EAST;
            public static final /* synthetic */ int ALL;
            public static final /* synthetic */ int DOWN;
            public static final /* synthetic */ int SOUTH;
            public static final /* synthetic */ int UP;
            public static final /* synthetic */ int WEST;

            static {
                UP = 2;
                WEST = 16;
                ALL = 63;
                DOWN = 1;
                SOUTH = 8;
                NORTH = 4;
                EAST = 32;
            }
        }

        public static final class Line {
            public static final /* synthetic */ int SOUTH_EAST;
            public static final /* synthetic */ int ALL;
            public static final /* synthetic */ int NORTH_WEST;
            public static final /* synthetic */ int UP_NORTH;
            public static final /* synthetic */ int UP_WEST;
            public static final /* synthetic */ int DOWN_WEST;
            public static final /* synthetic */ int NORTH_EAST;
            public static final /* synthetic */ int DOWN_NORTH;
            public static final /* synthetic */ int UP_SOUTH;
            public static final /* synthetic */ int UP_EAST;
            public static final /* synthetic */ int DOWN_SOUTH;
            public static final /* synthetic */ int DOWN_EAST;
            public static final /* synthetic */ int SOUTH_WEST;

            static {
                UP_NORTH = 6;
                NORTH_WEST = 20;
                SOUTH_EAST = 40;
                NORTH_EAST = 36;
                UP_WEST = 18;
                DOWN_EAST = 33;
                DOWN_NORTH = 5;
                ALL = 63;
                UP_EAST = 34;
                DOWN_WEST = 17;
                SOUTH_WEST = 24;
                DOWN_SOUTH = 9;
                UP_SOUTH = 10;
            }
        }
    }
}

