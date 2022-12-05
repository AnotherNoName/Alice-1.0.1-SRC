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
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.Display
 *  org.lwjgl.opengl.EXTFramebufferObject
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.GLU
 *  org.lwjgl.util.glu.Sphere
 */
package me.snow.aclient.util.skid.oyvey;

import java.awt.Color;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import me.snow.aclient.AliceMain;
import me.snow.aclient.mixin.mixins.accessors.IRenderManager;
import me.snow.aclient.util.ColorUtil;
import me.snow.aclient.util.EntityUtil;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

public class RenderUtil2
implements Util {
    public static /* synthetic */ ICamera camera;
    private static /* synthetic */ boolean texture;
    public static /* synthetic */ int deltaTime;
    private static final /* synthetic */ BufferBuilder BufferBuilder;
    private static /* synthetic */ boolean clean;
    public static /* synthetic */ Tessellator tessellator;
    public static /* synthetic */ BufferBuilder builder;
    private static final /* synthetic */ FloatBuffer projection;
    private static final /* synthetic */ FloatBuffer modelView;
    private static final /* synthetic */ FloatBuffer screenCoords;
    private static final /* synthetic */ Frustum frustrum;
    private static final /* synthetic */ IntBuffer viewport;
    private static /* synthetic */ boolean depth;
    private static /* synthetic */ boolean bind;
    private static /* synthetic */ boolean override;
    public static /* synthetic */ RenderItem itemRender;

    public static void hexColor(int n) {
        float f = (float)(n >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(n & 0xFF) / 255.0f;
        float f4 = (float)(n >> 24 & 0xFF) / 255.0f;
        GL11.glColor4f((float)f, (float)f2, (float)f3, (float)f4);
    }

    public static void renderBB(int n, AxisAlignedBB axisAlignedBB, Color color, Color color2) {
        GL11.glShadeModel((int)7425);
        axisAlignedBB = RenderUtil2.updateToCamera(axisAlignedBB);
        RenderUtil2.prepare();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        builder = tessellator.getBuffer();
        builder.begin(n, DefaultVertexFormats.POSITION_COLOR);
        RenderUtil2.buildBBBuffer(builder, axisAlignedBB, color, color2);
        tessellator.draw();
        RenderUtil2.release();
        GL11.glShadeModel((int)7424);
    }

    public static void drawTexturedRect(int n, int n2, int n3, int n4, int n5, int n6, int n7) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.pos((double)(n + 0), (double)(n2 + n6), (double)n7).tex((double)((float)(n3 + 0) * 0.00390625f), (double)((float)(n4 + n6) * 0.00390625f)).endVertex();
        bufferBuilder.pos((double)(n + n5), (double)(n2 + n6), (double)n7).tex((double)((float)(n3 + n5) * 0.00390625f), (double)((float)(n4 + n6) * 0.00390625f)).endVertex();
        bufferBuilder.pos((double)(n + n5), (double)(n2 + 0), (double)n7).tex((double)((float)(n3 + n5) * 0.00390625f), (double)((float)(n4 + 0) * 0.00390625f)).endVertex();
        bufferBuilder.pos((double)(n + 0), (double)(n2 + 0), (double)n7).tex((double)((float)(n3 + 0) * 0.00390625f), (double)((float)(n4 + 0) * 0.00390625f)).endVertex();
        tessellator.draw();
    }

    static {
        frustrum = new Frustum();
        screenCoords = BufferUtils.createFloatBuffer((int)3);
        viewport = BufferUtils.createIntBuffer((int)16);
        modelView = BufferUtils.createFloatBuffer((int)16);
        projection = BufferUtils.createFloatBuffer((int)16);
        itemRender = RenderUtil2.mc.getItemRenderer().itemRenderer;
        camera = new Frustum();
        depth = GL11.glIsEnabled((int)2896);
        texture = GL11.glIsEnabled((int)3042);
        clean = GL11.glIsEnabled((int)3553);
        bind = GL11.glIsEnabled((int)2929);
        override = GL11.glIsEnabled((int)2848);
        tessellator = Tessellator.getInstance();
        BufferBuilder = tessellator.getBuffer();
        itemRender = mc.getRenderItem();
        camera = new Frustum();
        depth = GL11.glIsEnabled((int)2896);
        texture = GL11.glIsEnabled((int)3042);
        clean = GL11.glIsEnabled((int)3553);
        bind = GL11.glIsEnabled((int)2929);
        override = GL11.glIsEnabled((int)2848);
        builder = tessellator.getBuffer();
    }

    public static void drawBox(BlockPos blockPos, int n, int n2) {
        int n3 = n >>> 24 & 0xFF;
        int n4 = n >>> 16 & 0xFF;
        int n5 = n >>> 8 & 0xFF;
        int n6 = n & 0xFF;
        RenderUtil2.drawBox(blockPos, n4, n5, n6, n3, n2);
    }

    public static void renderBBFog(AxisAlignedBB axisAlignedBB, Color color, Color color2) {
        GL11.glShadeModel((int)7425);
        axisAlignedBB = RenderUtil2.updateToCamera(axisAlignedBB);
        RenderUtil2.prepare();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        builder = tessellator.getBuffer();
        builder.begin(4, DefaultVertexFormats.POSITION_COLOR);
        RenderUtil2.buildBBBufferFog(builder, axisAlignedBB, color, color2);
        tessellator.draw();
        RenderUtil2.release();
        GL11.glShadeModel((int)7424);
    }

    public static void drawBoxESP(BlockPos blockPos, Color color, float f, boolean bl, boolean bl2, int n) {
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtil2.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtil2.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtil2.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtil2.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtil2.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtil2.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Objects.requireNonNull(RenderUtil2.mc.getRenderViewEntity()).posX, RenderUtil2.mc.getRenderViewEntity().posY, RenderUtil2.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil2.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil2.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask((boolean)false);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            GL11.glLineWidth((float)f);
            double d = RenderUtil2.mc.player.getDistance((double)((float)blockPos.getX() + 0.5f), (double)((float)blockPos.getY() + 0.5f), (double)((float)blockPos.getZ() + 0.5f)) * 0.75;
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
        GL11.glTranslated((double)(d - RenderUtil2.mc.renderManager.renderPosX), (double)(d2 - RenderUtil2.mc.renderManager.renderPosY), (double)(d3 - RenderUtil2.mc.renderManager.renderPosZ));
        sphere.draw(f, n, n2);
        GL11.glLineWidth((float)2.0f);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public static void drawBoxESP(BlockPos blockPos, Color color, boolean bl, Color color2, float f, boolean bl2, boolean bl3, int n, boolean bl4, double d, boolean bl5, boolean bl6, boolean bl7, boolean bl8, int n2) {
        if (bl3) {
            RenderUtil2.drawBox(blockPos, new Color(color.getRed(), color.getGreen(), color.getBlue(), n), d, bl5, bl7, n2);
        }
        if (bl2) {
            RenderUtil2.drawBlockOutline(blockPos, bl ? color2 : color, f, bl4, d, bl6, bl8, n2);
        }
    }

    public static void glBillboard(float f, float f2, float f3) {
        float f4 = 0.02666667f;
        GlStateManager.translate((double)((double)f - RenderUtil2.mc.getRenderManager().renderPosX), (double)((double)f2 - RenderUtil2.mc.getRenderManager().renderPosY), (double)((double)f3 - RenderUtil2.mc.getRenderManager().renderPosZ));
        GlStateManager.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)(-RenderUtil2.mc.player.rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)RenderUtil2.mc.player.rotationPitch, (float)(RenderUtil2.mc.gameSettings.thirdPersonView == 2 ? -1.0f : 1.0f), (float)0.0f, (float)0.0f);
        GlStateManager.scale((float)(-f4), (float)(-f4), (float)f4);
    }

    public static void start(float f) {
        GlStateManager.pushMatrix();
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDisable((int)2884);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        RenderUtil2.width(f);
    }

    public static void drawBlockOutline(BlockPos blockPos, Color color, float f, boolean bl) {
        IBlockState iBlockState = RenderUtil2.mc.world.getBlockState(blockPos);
        if ((bl || iBlockState.getMaterial() != Material.AIR) && RenderUtil2.mc.world.getWorldBorder().contains(blockPos)) {
            assert (RenderUtil2.mc.renderViewEntity != null);
            Vec3d vec3d = EntityUtil.interpolateEntity(RenderUtil2.mc.renderViewEntity, mc.getRenderPartialTicks());
            RenderUtil2.drawBlockOutline(iBlockState.getSelectedBoundingBox((World)RenderUtil2.mc.world, blockPos).expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord), color, f);
        }
    }

    public static void drawGradientBlockOutline(BlockPos blockPos, Color color, Color color2, float f, double d) {
        IBlockState iBlockState = RenderUtil2.mc.world.getBlockState(blockPos);
        Vec3d vec3d = EntityUtil.interpolateEntity((Entity)RenderUtil2.mc.player, mc.getRenderPartialTicks());
        RenderUtil2.drawGradientBlockOutline(iBlockState.getSelectedBoundingBox((World)RenderUtil2.mc.world, blockPos).expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord).addCoord(0.0, d, 0.0), color, color2, f);
    }

    public static void glStart(float f, float f2, float f3, float f4) {
        RenderUtil2.glrendermethod();
        GL11.glColor4f((float)f, (float)f2, (float)f3, (float)f4);
    }

    public static void drawBoundingBoxItem(double d, double d2, double d3, float f, int n, int n2, int n3, int n4) {
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
        double d4 = d - 0.2 - minecraft.getRenderManager().viewerPosX;
        double d5 = d2 - minecraft.getRenderManager().viewerPosY;
        double d6 = d3 - 0.2 - minecraft.getRenderManager().viewerPosZ;
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(d4, d5, d6, d4 + 0.4, d5 + 0.4, d6 + 0.4);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
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

    public static Vec3d getInterpolatedPos(Entity entity, float f) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(RenderUtil2.getInterpolatedAmount(entity, f));
    }

    public static void buildBBBufferFog(BufferBuilder bufferBuilder, AxisAlignedBB axisAlignedBB, Color color, Color color2) {
        double d = (axisAlignedBB.maxX - axisAlignedBB.minX) / 2.0;
        double d2 = (axisAlignedBB.maxY - axisAlignedBB.minY) / 2.0;
        double d3 = (axisAlignedBB.maxZ - axisAlignedBB.minZ) / 2.0;
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX + d, axisAlignedBB.maxY, axisAlignedBB.minZ + d3, color2);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX + d, axisAlignedBB.maxY, axisAlignedBB.minZ + d3, color2);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX + d, axisAlignedBB.maxY, axisAlignedBB.minZ + d3, color2);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX + d, axisAlignedBB.maxY, axisAlignedBB.minZ + d3, color2);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
    }

    public static void renderFour(Color color) {
        RenderUtil2.setColor(color);
        GL11.glDepthMask((boolean)false);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)10754);
        GL11.glPolygonOffset((float)1.0f, (float)-2000000.0f);
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
    }

    public static void prepare(int n) {
        RenderUtil2.prepareGL();
        RenderUtil2.begin(n);
    }

    public static void glEnd() {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
    }

    public static void blockESP(BlockPos blockPos, Color color, double d, double d2) {
        RenderUtil2.blockEsp(blockPos, color, d, d2);
    }

    public static void drawBBClaw(AxisAlignedBB axisAlignedBB, float f, float f2, Color color) {
        GlStateManager.pushMatrix();
        RenderUtil2.start1();
        RenderUtil2.width(f);
        double d = axisAlignedBB.minX;
        double d2 = axisAlignedBB.minY;
        double d3 = axisAlignedBB.minZ;
        double d4 = axisAlignedBB.maxX;
        double d5 = axisAlignedBB.maxY;
        double d6 = axisAlignedBB.maxZ;
        builder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        RenderUtil2.vertex(d, d2, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d, d2, d3 + (double)f2).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d, d2, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d, d2, d6 - (double)f2).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d4, d2, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d4, d2, d3 + (double)f2).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d4, d2, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d4, d2, d6 - (double)f2).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d, d2, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d + (double)f2, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d, d2, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d + (double)f2, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d4, d2, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d4 - (double)f2, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d4, d2, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d4 - (double)f2, d2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d, d2, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d, d2 + (double)f2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d, d2, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d, d2 + (double)f2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d4, d2, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d4, d2 + (double)f2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d4, d2, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d4, d2 + (double)f2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d, d5, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d, d5, d3 + (double)f2).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d, d5, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d, d5, d6 - (double)f2).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d4, d5, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d4, d5, d3 + (double)f2).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d4, d5, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d4, d5, d6 - (double)f2).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d, d5, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d + (double)f2, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d, d5, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d + (double)f2, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d4, d5, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d4 - (double)f2, d5, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d4, d5, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d4 - (double)f2, d5, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d, d5, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d, d5 - (double)f2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d, d5, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d, d5 - (double)f2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d4, d5, d3).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d4, d5 - (double)f2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        RenderUtil2.vertex(d4, d5, d6).color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), 0.0f).endVertex();
        RenderUtil2.vertex(d4, d5 - (double)f2, d6).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tessellator.draw();
        RenderUtil2.end1();
        GlStateManager.popMatrix();
    }

    public static void drawTexts(AxisAlignedBB axisAlignedBB, String string) {
        if (axisAlignedBB == null || string == null) {
            return;
        }
        GlStateManager.pushMatrix();
        RenderUtil2.glBillboardDistanceScaled((float)axisAlignedBB.minX + 0.5f, (float)axisAlignedBB.minY + 0.5f, (float)axisAlignedBB.minZ + 0.5f, (EntityPlayer)RenderUtil2.mc.player, 1.0f);
        GlStateManager.disableDepth();
        GlStateManager.translate((double)(-((double)AliceMain.textManager.getStringWidth(string) / 2.0)), (double)0.0, (double)0.0);
        AliceMain.textManager.drawStringWithShadow(string, 0.0f, 0.0f, -5592406);
        GlStateManager.popMatrix();
    }

    public static double getRenderPosY() {
        return ((IRenderManager)mc.getRenderManager()).getRenderPosY();
    }

    public static void draw_cube_line(float f, float f2, float f3, int n, String string) {
        int n2 = n >>> 24 & 0xFF;
        int n3 = n >>> 16 & 0xFF;
        int n4 = n >>> 8 & 0xFF;
        int n5 = n & 0xFF;
        RenderUtil2.draw_cube_line(tessellator.getBuffer(), f, f2, f3, 1.0f, 0.5645f, 1.0f, n3, n4, n5, n2, string);
    }

    public static void drawOutlinedBlockESP(BlockPos blockPos, Color color, float f) {
        IBlockState iBlockState = RenderUtil2.mc.world.getBlockState(blockPos);
        Vec3d vec3d = EntityUtil.interpolateEntity((Entity)RenderUtil2.mc.player, mc.getRenderPartialTicks());
        RenderUtil2.drawBoundingBox(iBlockState.getSelectedBoundingBox((World)RenderUtil2.mc.world, blockPos).expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord), f, ColorUtil.toRGBA(color));
    }

    public static void releaseGL() {
        GlStateManager.enableCull();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
    }

    public static void buildBBBuffer(BufferBuilder bufferBuilder, AxisAlignedBB axisAlignedBB, Color color, Color color2) {
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, color2);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color2);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color2);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color2);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color2);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, color2);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, color2);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, color2);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, color2);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, color2);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color2);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color2);
        RenderUtil2.addBuilderVertex(bufferBuilder, axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, color2);
    }

    public static void drawCircleOutline(float f, float f2, float f3, int n, int n2, int n3) {
        RenderUtil2.drawArcOutline(f, f2, f3, n, n2, n3);
    }

    public static void drawRectangle(float f, float f2, float f3, float f4) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glBegin((int)2);
        GL11.glVertex2d((double)f3, (double)0.0);
        GL11.glVertex2d((double)0.0, (double)0.0);
        GL11.glVertex2d((double)0.0, (double)f4);
        GL11.glVertex2d((double)f3, (double)f4);
        RenderUtil2.glEnd();
    }

    private static BufferBuilder vertex(double d, double d2, double d3) {
        return builder.pos(d - RenderUtil2.mc.getRenderManager().viewerPosX, d2 - RenderUtil2.mc.getRenderManager().viewerPosY, d3 - RenderUtil2.mc.getRenderManager().viewerPosZ);
    }

    public static void gradientBox(BlockPos blockPos, Color color, float f, boolean bl, boolean bl2, int n, boolean bl3) {
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtil2.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtil2.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtil2.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtil2.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtil2.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtil2.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Objects.requireNonNull(RenderUtil2.mc.getRenderViewEntity()).posX, RenderUtil2.mc.getRenderViewEntity().posY, RenderUtil2.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil2.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil2.mc.getRenderManager().viewerPosZ))) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask((boolean)false);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            GL11.glLineWidth((float)f);
            double d = RenderUtil2.mc.player.getDistance((double)((float)blockPos.getX() + 0.5f), (double)((float)blockPos.getY() + 0.5f), (double)((float)blockPos.getZ() + 0.5f)) * 0.75;
            GL11.glDisable((int)2848);
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    public static void drawFilledBoxESPN(BlockPos blockPos, Color color) {
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtil2.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtil2.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtil2.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtil2.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtil2.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtil2.mc.getRenderManager().viewerPosZ);
        int n = ColorUtil.toRGBA(color);
        RenderUtil2.drawFilledBox(axisAlignedBB, n);
    }

    public static void drawFilledRectangle(float f, float f2, float f3, float f4) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glBegin((int)7);
        GL11.glVertex2d((double)(f + f3), (double)f2);
        GL11.glVertex2d((double)f, (double)f2);
        GL11.glVertex2d((double)f, (double)(f2 + f4));
        GL11.glVertex2d((double)(f + f3), (double)(f2 + f4));
        RenderUtil2.glEnd();
    }

    public static void drawGradientPlane(BlockPos blockPos, EnumFacing enumFacing, Color color, Color color2, boolean bl, boolean bl2) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        IBlockState iBlockState = RenderUtil2.mc.world.getBlockState(blockPos);
        Vec3d vec3d = EntityUtil.interpolateEntity((Entity)RenderUtil2.mc.player, mc.getRenderPartialTicks());
        AxisAlignedBB axisAlignedBB = iBlockState.getSelectedBoundingBox((World)RenderUtil2.mc.world, blockPos).expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord);
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

    public static void drawRectangleXY(float f, float f2, float f3, float f4) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glBegin((int)2);
        GL11.glVertex2d((double)(f + f3), (double)f2);
        GL11.glVertex2d((double)f, (double)f2);
        GL11.glVertex2d((double)f, (double)(f2 + f4));
        GL11.glVertex2d((double)(f + f3), (double)(f2 + f4));
        RenderUtil2.glEnd();
    }

    private static void vertex(int n, int n2, int n3, int n4) {
        builder.pos(0.0, (double)RenderUtil2.mc.player.getEyeHeight(), 0.0).color(n, n2, n3, n4).endVertex();
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

    public static void prepare(String string) {
        int n = 0;
        if (string.equalsIgnoreCase("quads")) {
            n = 7;
        } else if (string.equalsIgnoreCase("lines")) {
            n = 1;
        }
        RenderUtil2.prepare_gl();
        RenderUtil2.begin(n);
    }

    public static void glScissor(float f, float f2, float f3, float f4, ScaledResolution scaledResolution) {
        GL11.glScissor((int)((int)(f * (float)scaledResolution.getScaleFactor())), (int)((int)((float)RenderUtil2.mc.displayHeight - f4 * (float)scaledResolution.getScaleFactor())), (int)((int)((f3 - f) * (float)scaledResolution.getScaleFactor())), (int)((int)((f4 - f2) * (float)scaledResolution.getScaleFactor())));
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

    public static void start2() {
        GlStateManager.pushMatrix();
        RenderUtil2.width(1.0f);
        GL11.glEnable((int)2848);
        GL11.glEnable((int)34383);
        GL11.glHint((int)3154, (int)4354);
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel((int)7425);
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.depthMask((boolean)false);
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
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
        RenderUtil2.glEnd();
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

    public static void checkSetupFBO() {
        Framebuffer framebuffer = RenderUtil2.mc.framebufferMc;
        if (framebuffer != null && framebuffer.depthBuffer > -1) {
            RenderUtil2.setupFBO(framebuffer);
            framebuffer.depthBuffer = -1;
        }
    }

    public static void drawBox(BlockPos blockPos, Color color, double d, boolean bl, boolean bl2, int n) {
        if (bl) {
            Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
            RenderUtil2.drawOpenGradientBox(blockPos, bl2 ? color2 : color, bl2 ? color : color2, d);
            return;
        }
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtil2.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtil2.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtil2.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtil2.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtil2.mc.getRenderManager().viewerPosY + d, (double)(blockPos.getZ() + 1) - RenderUtil2.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Objects.requireNonNull(RenderUtil2.mc.getRenderViewEntity()).posX, RenderUtil2.mc.getRenderViewEntity().posY, RenderUtil2.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil2.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil2.mc.getRenderManager().viewerPosZ))) {
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

    public static void drawBetterBoxESP(AxisAlignedBB axisAlignedBB, Color color, Color color2, float f, boolean bl, boolean bl2, boolean bl3, float f2, float f3, float f4) {
        double d = 0.5 * (double)(1.0f - f3);
        AxisAlignedBB axisAlignedBB2 = RenderUtil2.interpolateAxis(new AxisAlignedBB(axisAlignedBB.minX + d, axisAlignedBB.minY + d + (double)(1.0f - f4), axisAlignedBB.minZ + d, axisAlignedBB.maxX - d, axisAlignedBB.maxY - d, axisAlignedBB.maxZ - d));
        float f5 = (float)color.getRed() / 255.0f;
        float f6 = (float)color.getGreen() / 255.0f;
        float f7 = (float)color.getBlue() / 255.0f;
        float f8 = (float)color.getAlpha() / 255.0f;
        float f9 = (float)color2.getRed() / 255.0f;
        float f10 = (float)color2.getGreen() / 255.0f;
        float f11 = (float)color2.getBlue() / 255.0f;
        float f12 = (float)color2.getAlpha() / 255.0f;
        if (bl3) {
            // empty if block
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

    public static void drawBoundingBoxBottomBlockPos(BlockPos blockPos, float f, int n, int n2, int n3, int n4) {
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
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawBox(double d, double d2, double d3, int n, int n2) {
        int n3 = n >>> 24 & 0xFF;
        int n4 = n >>> 16 & 0xFF;
        int n5 = n >>> 8 & 0xFF;
        int n6 = n & 0xFF;
        RenderUtil2.drawBox(tessellator.getBuffer(), d, d2, d3, 1.0f, 1.0f, 1.0f, n4, n5, n6, n3, n2);
    }

    public static void begin(int n) {
        tessellator.getBuffer().begin(n, DefaultVertexFormats.POSITION_COLOR);
    }

    public static void drawBorderedRect(double d, double d2, double d3, double d4, double d5, int n, int n2) {
        RenderUtil2.enableGL2D();
        RenderUtil2.fakeGuiRect(d + d5, d2 + d5, d3 - d5, d4 - d5, n);
        RenderUtil2.fakeGuiRect(d + d5, d2, d3 - d5, d2 + d5, n2);
        RenderUtil2.fakeGuiRect(d, d2, d + d5, d4, n2);
        RenderUtil2.fakeGuiRect(d3 - d5, d2, d3, d4, n2);
        RenderUtil2.fakeGuiRect(d + d5, d4 - d5, d3 - d5, d4, n2);
        RenderUtil2.disableGL2D();
    }

    public static AxisAlignedBB fixBB(AxisAlignedBB axisAlignedBB) {
        AxisAlignedBB axisAlignedBB2 = axisAlignedBB;
        return new AxisAlignedBB(axisAlignedBB2.minX - RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB2.minY - RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB2.minZ - RenderUtil2.mc.getRenderManager().viewerPosZ, axisAlignedBB2.maxX - RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB2.maxY - RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB2.maxZ - RenderUtil2.mc.getRenderManager().viewerPosZ);
    }

    public static void drawBBOutline(AxisAlignedBB axisAlignedBB, float f, Color color) {
        RenderUtil2.start(f);
        int n = color.getRed();
        int n2 = color.getBlue();
        int n3 = color.getGreen();
        int n4 = color.getAlpha();
        builder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        RenderUtil2.vertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, n, n3, n2, n4);
        RenderUtil2.vertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, n, n3, n2, n4);
        RenderUtil2.vertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, n, n3, n2, n4);
        RenderUtil2.vertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, n, n3, n2, n4);
        RenderUtil2.vertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, n, n3, n2, n4);
        RenderUtil2.vertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, n, n3, n2, n4);
        RenderUtil2.vertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, n, n3, n2, n4);
        RenderUtil2.vertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, n, n3, n2, n4);
        RenderUtil2.vertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, n, n3, n2, n4);
        RenderUtil2.vertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, n, n3, n2, n4);
        RenderUtil2.vertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, n, n3, n2, n4);
        RenderUtil2.vertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, n, n3, n2, n4);
        RenderUtil2.vertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, n, n3, n2, n4);
        RenderUtil2.vertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, n, n3, n2, n4);
        RenderUtil2.vertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, n, n3, n2, n4);
        RenderUtil2.vertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, n, n3, n2, n4);
        tessellator.draw();
        RenderUtil2.end();
    }

    public static void release_gl() {
        GlStateManager.enableCull();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
    }

    public static void drawBoundingBoxBlockPos(BlockPos blockPos, float f, int n, int n2, int n3, int n4) {
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
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
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

    public static void renderTwo() {
        GL11.glStencilFunc((int)512, (int)0, (int)15);
        GL11.glStencilOp((int)7681, (int)7681, (int)7681);
        GL11.glPolygonMode((int)1032, (int)6914);
    }

    public static void drawBoundingBoxFace(AxisAlignedBB axisAlignedBB, float f, int n, int n2, int n3, int n4) {
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
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawBoundingBoxChestBlockPos(BlockPos blockPos, float f, int n, int n2, int n3, int n4) {
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
        double d = (double)blockPos.getX() + 0.06 - minecraft.getRenderManager().viewerPosX;
        double d2 = (double)blockPos.getY() - minecraft.getRenderManager().viewerPosY;
        double d3 = (double)blockPos.getZ() + 0.06 - minecraft.getRenderManager().viewerPosZ;
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(d, d2, d3, d + 0.881, d2 + 0.875, d3 + 0.881);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void render() {
        tessellator.draw();
    }

    public static void draw_cube(float f, float f2, float f3, int n, String string) {
        int n2 = n >>> 24 & 0xFF;
        int n3 = n >>> 16 & 0xFF;
        int n4 = n >>> 8 & 0xFF;
        int n5 = n & 0xFF;
        RenderUtil2.draw_cube(tessellator.getBuffer(), f, f2, f3, 1.0f, 1.0f, 1.0f, n3, n4, n5, n2, string);
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

    public static Vec3d updateToCamera(Vec3d vec3d) {
        return new Vec3d(vec3d.xCoord - RenderUtil2.mc.getRenderManager().viewerPosX, vec3d.yCoord - RenderUtil2.mc.getRenderManager().viewerPosY, vec3d.zCoord - RenderUtil2.mc.getRenderManager().viewerPosZ);
    }

    private static void enableGL2D() {
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
    }

    public static void drawBox(BlockPos blockPos, Color color) {
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtil2.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtil2.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtil2.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtil2.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtil2.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtil2.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Objects.requireNonNull(RenderUtil2.mc.getRenderViewEntity()).posX, RenderUtil2.mc.getRenderViewEntity().posY, RenderUtil2.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil2.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil2.mc.getRenderManager().viewerPosZ))) {
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

    public static void drawRectangle(float f, float f2, float f3, float f4, int n) {
        float f5 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(n & 0xFF) / 255.0f;
        float f8 = (float)(n >> 24 & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos((double)f, (double)f4, 0.0).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos((double)f3, (double)f4, 0.0).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos((double)f3, (double)f2, 0.0).color(f5, f6, f7, f8).endVertex();
        bufferBuilder.pos((double)f, (double)f2, 0.0).color(f5, f6, f7, f8).endVertex();
        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawCircle(float f, float f2, float f3) {
        RenderUtil2.drawCircle(f, f2, f3, 0, 360, 64);
    }

    private static void setupFBO(Framebuffer framebuffer) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT((int)framebuffer.depthBuffer);
        int n = EXTFramebufferObject.glGenRenderbuffersEXT();
        EXTFramebufferObject.glBindRenderbufferEXT((int)36161, (int)n);
        EXTFramebufferObject.glRenderbufferStorageEXT((int)36161, (int)34041, (int)RenderUtil2.mc.displayWidth, (int)RenderUtil2.mc.displayHeight);
        EXTFramebufferObject.glFramebufferRenderbufferEXT((int)36160, (int)36128, (int)36161, (int)n);
        EXTFramebufferObject.glFramebufferRenderbufferEXT((int)36160, (int)36096, (int)36161, (int)n);
    }

    public static void drawRectangleCorrectly(int n, int n2, int n3, int n4, int n5) {
        GL11.glLineWidth((float)1.0f);
        Gui.drawRect((int)n, (int)n2, (int)(n + n3), (int)(n2 + n4), (int)n5);
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

    public static void GlPost() {
        RenderUtil2.GLPost(depth, texture, clean, bind, override);
    }

    public static void drawCircleOutline(float f, float f2, float f3) {
        RenderUtil2.drawCircleOutline(f, f2, f3, 0, 360, 40);
    }

    public static void drawBoxSmall(double d, double d2, double d3, int n, int n2) {
        int n3 = n >>> 24 & 0xFF;
        int n4 = n >>> 16 & 0xFF;
        int n5 = n >>> 8 & 0xFF;
        int n6 = n & 0xFF;
        RenderUtil2.drawBox(tessellator.getBuffer(), d, d2, d3, 0.25f, 0.25f, 0.25f, n4, n5, n6, n3, n2);
    }

    public static void renderOne(float f) {
        RenderUtil2.checkSetupFBO();
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

    public static void drawBlockOutline(BlockPos blockPos, Color color, float f, boolean bl, double d, boolean bl2, boolean bl3, int n) {
        if (bl2) {
            Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
            RenderUtil2.drawGradientBlockOutline(blockPos, bl3 ? color2 : color, bl3 ? color : color2, f, d);
            return;
        }
        IBlockState iBlockState = RenderUtil2.mc.world.getBlockState(blockPos);
        if ((bl || iBlockState.getMaterial() != Material.AIR) && RenderUtil2.mc.world.getWorldBorder().contains(blockPos)) {
            AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtil2.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtil2.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtil2.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtil2.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtil2.mc.getRenderManager().viewerPosY + d, (double)(blockPos.getZ() + 1) - RenderUtil2.mc.getRenderManager().viewerPosZ);
            RenderUtil2.drawBlockOutline(axisAlignedBB.expandXyz((double)0.002f), color, f);
        }
    }

    public static int getTextWidth(String string, double d) {
        return (int)((double)RenderUtil2.mc.fontRendererObj.getStringWidth(string) * d);
    }

    public static void drawBoundingBoxBlockPosHalf(BlockPos blockPos, float f, int n, int n2, int n3, int n4) {
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
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(d, d2, d3, d + 1.0, d2 + 0.5, d3 + 1.0);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawBoundingBoxBottomBlockPosSouth(BlockPos blockPos, float f, int n, int n2, int n3, int n4) {
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
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void glBillboardDistanceScaled(float f, float f2, float f3, EntityPlayer entityPlayer, float f4) {
        RenderUtil2.glBillboard(f, f2, f3);
        int n = (int)entityPlayer.getDistance((double)f, (double)f2, (double)f3);
        float f5 = (float)n / 2.0f / (2.0f + (2.0f - f4));
        if (f5 < 1.0f) {
            f5 = 1.0f;
        }
        GlStateManager.scale((float)f5, (float)f5, (float)f5);
    }

    private static void vertex(double d, double d2, double d3, int n, int n2, int n3, int n4) {
        builder.pos(d - RenderUtil2.mc.getRenderManager().viewerPosX, d2 - RenderUtil2.mc.getRenderManager().viewerPosY, d3 - RenderUtil2.mc.getRenderManager().viewerPosZ).color(n, n2, n3, n4).endVertex();
    }

    public static Vec3d getInterpolatedAmount(Entity entity, double d, double d2, double d3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * d, (entity.posY - entity.lastTickPosY) * d2, (entity.posZ - entity.lastTickPosZ) * d3);
    }

    public static void drawBBSlab(AxisAlignedBB axisAlignedBB, float f, Color color) {
        int n = color.getRed();
        int n2 = color.getGreen();
        int n3 = color.getBlue();
        int n4 = color.getAlpha();
        double d = axisAlignedBB.minX;
        double d2 = axisAlignedBB.minY;
        double d3 = axisAlignedBB.minZ;
        double d4 = axisAlignedBB.maxX;
        double d5 = axisAlignedBB.maxY + (double)f;
        double d6 = axisAlignedBB.maxZ;
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GL11.glDisable((int)2929);
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.disableCull();
        GlStateManager.shadeModel((int)7425);
        builder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        RenderUtil2.vertex(d, d2, d3).color(n, n2, n3, n4).endVertex();
        RenderUtil2.vertex(d4, d2, d3).color(n, n2, n3, n4).endVertex();
        RenderUtil2.vertex(d4, d2, d6).color(n, n2, n3, n4).endVertex();
        RenderUtil2.vertex(d, d2, d6).color(n, n2, n3, n4).endVertex();
        RenderUtil2.vertex(d, d5, d3).color(0, 0, 0, 0).endVertex();
        RenderUtil2.vertex(d, d5, d6).color(0, 0, 0, 0).endVertex();
        RenderUtil2.vertex(d4, d5, d6).color(0, 0, 0, 0).endVertex();
        RenderUtil2.vertex(d4, d5, d3).color(0, 0, 0, 0).endVertex();
        RenderUtil2.vertex(d, d2, d3).color(n, n2, n3, n4).endVertex();
        RenderUtil2.vertex(d, d5, d3).color(0, 0, 0, 0).endVertex();
        RenderUtil2.vertex(d4, d5, d3).color(0, 0, 0, 0).endVertex();
        RenderUtil2.vertex(d4, d2, d3).color(n, n2, n3, n4).endVertex();
        RenderUtil2.vertex(d4, d2, d3).color(n, n2, n3, n4).endVertex();
        RenderUtil2.vertex(d4, d5, d3).color(0, 0, 0, 0).endVertex();
        RenderUtil2.vertex(d4, d5, d6).color(0, 0, 0, 0).endVertex();
        RenderUtil2.vertex(d4, d2, d6).color(n, n2, n3, n4).endVertex();
        RenderUtil2.vertex(d, d2, d6).color(n, n2, n3, n4).endVertex();
        RenderUtil2.vertex(d4, d2, d6).color(n, n2, n3, n4).endVertex();
        RenderUtil2.vertex(d4, d5, d6).color(0, 0, 0, 0).endVertex();
        RenderUtil2.vertex(d, d5, d6).color(0, 0, 0, 0).endVertex();
        RenderUtil2.vertex(d, d2, d3).color(n, n2, n3, n4).endVertex();
        RenderUtil2.vertex(d, d2, d6).color(n, n2, n3, n4).endVertex();
        RenderUtil2.vertex(d, d5, d6).color(0, 0, 0, 0).endVertex();
        RenderUtil2.vertex(d, d5, d3).color(0, 0, 0, 0).endVertex();
        tessellator.draw();
        GL11.glEnable((int)2929);
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
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

    public static void drawBoundingBottomBoxBlockPos(BlockPos blockPos, float f, int n, int n2, int n3, int n4) {
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
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(d, d2, d3, d + 1.0, d2 + 0.0, d3 + 1.0);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        bufferBuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void blockEsp(BlockPos blockPos, Color color, double d, double d2) {
        double d3 = (double)blockPos.getX() - RenderUtil2.mc.renderManager.renderPosX;
        double d4 = (double)blockPos.getY() - RenderUtil2.mc.renderManager.renderPosY;
        double d5 = (double)blockPos.getZ() - RenderUtil2.mc.renderManager.renderPosZ;
        GL11.glPushMatrix();
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3042);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glColor4d((double)((float)color.getRed() / 255.0f), (double)((float)color.getGreen() / 255.0f), (double)((float)color.getBlue() / 255.0f), (double)0.25);
        RenderUtil2.drawColorBox(new AxisAlignedBB(d3, d4, d5, d3 + d2, d4 + 1.0, d5 + d), 0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glColor4d((double)0.0, (double)0.0, (double)0.0, (double)0.5);
        RenderUtil2.drawSelectionBoundingBox(new AxisAlignedBB(d3, d4, d5, d3 + d2, d4 + 1.0, d5 + d));
        GL11.glLineWidth((float)2.0f);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
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

    public static void drawCircle(float f, float f2, float f3, float f4, Color color) {
        BlockPos blockPos = new BlockPos((double)f, (double)f2, (double)f3);
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)blockPos.getX() - RenderUtil2.mc.getRenderManager().viewerPosX, (double)blockPos.getY() - RenderUtil2.mc.getRenderManager().viewerPosY, (double)blockPos.getZ() - RenderUtil2.mc.getRenderManager().viewerPosZ, (double)(blockPos.getX() + 1) - RenderUtil2.mc.getRenderManager().viewerPosX, (double)(blockPos.getY() + 1) - RenderUtil2.mc.getRenderManager().viewerPosY, (double)(blockPos.getZ() + 1) - RenderUtil2.mc.getRenderManager().viewerPosZ);
        camera.setPosition(Objects.requireNonNull(RenderUtil2.mc.getRenderViewEntity()).posX, RenderUtil2.mc.getRenderViewEntity().posY, RenderUtil2.mc.getRenderViewEntity().posZ);
        if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(axisAlignedBB.minX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.minY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ + RenderUtil2.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX + RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY + RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ + RenderUtil2.mc.getRenderManager().viewerPosZ))) {
            RenderUtil2.drawCircleVertices(axisAlignedBB, f4, color);
        }
    }

    public static double getRenderPosX() {
        return ((IRenderManager)mc.getRenderManager()).getRenderPosX();
    }

    public static void drawFace(BufferBuilder bufferBuilder, float f, float f2, float f3, float f4, float f5, float f6, int n, int n2, int n3, int n4, int n5) {
        if ((n5 & 1) != 0) {
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
        }
    }

    public static boolean isInViewFrustrum(Entity entity) {
        return RenderUtil2.isInViewFrustrum(entity.getEntityBoundingBox()) || entity.ignoreFrustumCheck;
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

    public static AxisAlignedBB getBoundingBox(BlockPos blockPos) {
        return RenderUtil2.mc.world.getBlockState(blockPos).getBoundingBox((IBlockAccess)RenderUtil2.mc.world, blockPos).offset(blockPos);
    }

    public static void end() {
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)2884);
        GlStateManager.popMatrix();
    }

    public static void drawText(BlockPos blockPos, String string) {
        GlStateManager.pushMatrix();
        RenderUtil2.glBillboardDistanceScaled((float)blockPos.getX() + 0.5f, (float)blockPos.getY() + 0.5f, (float)blockPos.getZ() + 0.5f, (EntityPlayer)RenderUtil2.mc.player, 1.0f);
        GlStateManager.disableDepth();
        GlStateManager.translate((double)(-((double)AliceMain.textManager.getStringWidth(string) / 2.0)), (double)0.0, (double)0.0);
        AliceMain.textManager.drawStringWithShadow(string, 0.0f, 0.0f, -5592406);
        GlStateManager.popMatrix();
    }

    public static void end1() {
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void addBuilderVertex(BufferBuilder bufferBuilder, double d, double d2, double d3, Color color) {
        bufferBuilder.pos(d, d2, d3).color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f).endVertex();
    }

    public static float getInterpolatedLinWid(float f, float f2, float f3) {
        return f2 * f3 / f;
    }

    public static void drawTracerPointer(float f, float f2, float f3, float f4, float f5, boolean bl, float f6, int n) {
        boolean bl2 = GL11.glIsEnabled((int)3042);
        float f7 = (float)(n >> 24 & 0xFF) / 255.0f;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glPushMatrix();
        RenderUtil2.hexColor(n);
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

    public static void GLPre(float f) {
        depth = GL11.glIsEnabled((int)2896);
        texture = GL11.glIsEnabled((int)3042);
        clean = GL11.glIsEnabled((int)3553);
        bind = GL11.glIsEnabled((int)2929);
        override = GL11.glIsEnabled((int)2848);
        RenderUtil2.GLPre(depth, texture, clean, bind, override, f);
    }

    private static void width(float f) {
        GlStateManager.glLineWidth((float)f);
    }

    public static void drawArcOutline(float f, float f2, float f3, float f4, float f5, int n) {
        GL11.glBegin((int)2);
        int n2 = (int)((float)n / (360.0f / f4)) + 1;
        while ((float)n2 <= (float)n / (360.0f / f5)) {
            double d = Math.PI * 2 * (double)n2 / (double)n;
            GL11.glVertex2d((double)((double)f + Math.cos(d) * (double)f3), (double)((double)f2 + Math.sin(d) * (double)f3));
            ++n2;
        }
        RenderUtil2.glEnd();
    }

    public static boolean isInViewFrustrum(AxisAlignedBB axisAlignedBB) {
        Entity entity = Minecraft.getMinecraft().getRenderViewEntity();
        frustrum.setPosition(entity.posX, entity.posY, entity.posZ);
        return frustrum.isBoundingBoxInFrustum(axisAlignedBB);
    }

    public static void draw_cube(BlockPos blockPos, int n, String string) {
        int n2 = n >>> 24 & 0xFF;
        int n3 = n >>> 16 & 0xFF;
        int n4 = n >>> 8 & 0xFF;
        int n5 = n & 0xFF;
        RenderUtil2.draw_cube(blockPos, n3, n4, n5, n2, string);
    }

    public static void drawCircleVertices(AxisAlignedBB axisAlignedBB, float f, Color color) {
        float f2 = (float)color.getRed() / 255.0f;
        float f3 = (float)color.getGreen() / 255.0f;
        float f4 = (float)color.getBlue() / 255.0f;
        float f5 = (float)color.getAlpha() / 255.0f;
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

    public static void prepare_gl() {
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
        GL11.glLineWidth((float)2.0f);
    }

    public static void drawBox(BlockPos blockPos, int n, int n2, int n3, int n4, int n5) {
        RenderUtil2.drawBox(tessellator.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, n5);
    }

    public static AxisAlignedBB interpolateAxis(AxisAlignedBB axisAlignedBB) {
        return new AxisAlignedBB(axisAlignedBB.minX - RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtil2.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtil2.mc.getRenderManager().viewerPosZ);
    }

    public static void drawBox(BufferBuilder bufferBuilder, double d, double d2, double d3, float f, float f2, float f3, int n, int n2, int n3, int n4, int n5) {
        if ((n5 & 1) != 0) {
            bufferBuilder.pos(d + (double)f, d2, d3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d + (double)f, d2, d3 + (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d, d2, d3 + (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d, d2, d3).color(n, n2, n3, n4).endVertex();
        }
        if ((n5 & 2) != 0) {
            bufferBuilder.pos(d + (double)f, d2 + (double)f2, d3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d, d2 + (double)f2, d3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d, d2 + (double)f2, d3 + (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d + (double)f, d2 + (double)f2, d3 + (double)f3).color(n, n2, n3, n4).endVertex();
        }
        if ((n5 & 4) != 0) {
            bufferBuilder.pos(d + (double)f, d2, d3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d, d2, d3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d, d2 + (double)f2, d3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d + (double)f, d2 + (double)f2, d3).color(n, n2, n3, n4).endVertex();
        }
        if ((n5 & 8) != 0) {
            bufferBuilder.pos(d, d2, d3 + (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d + (double)f, d2, d3 + (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d + (double)f, d2 + (double)f2, d3 + (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d, d2 + (double)f2, d3 + (double)f3).color(n, n2, n3, n4).endVertex();
        }
        if ((n5 & 0x10) != 0) {
            bufferBuilder.pos(d, d2, d3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d, d2, d3 + (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d, d2 + (double)f2, d3 + (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d, d2 + (double)f2, d3).color(n, n2, n3, n4).endVertex();
        }
        if ((n5 & 0x20) != 0) {
            bufferBuilder.pos(d + (double)f, d2, d3 + (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d + (double)f, d2, d3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d + (double)f, d2 + (double)f2, d3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(d + (double)f, d2 + (double)f2, d3 + (double)f3).color(n, n2, n3, n4).endVertex();
        }
    }

    public static void end2() {
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.enableCull();
        GlStateManager.shadeModel((int)7424);
        GlStateManager.enableAlpha();
        GlStateManager.depthMask((boolean)true);
        GL11.glDisable((int)34383);
        GL11.glDisable((int)2848);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f);
        RenderUtil2.width(1.0f);
        GlStateManager.popMatrix();
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

    public static void drawCircle(float f, float f2, float f3, int n, int n2, int n3) {
        RenderUtil2.drawArc(f, f2, f3, n, n2, n3);
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

    public static void draw_cube(BufferBuilder bufferBuilder, float f, float f2, float f3, float f4, float f5, float f6, int n, int n2, int n3, int n4, String string) {
        if (Arrays.asList(string.split("-")).contains("down") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("up") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("north") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("south") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("south") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("south") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
        }
    }

    public static void drawBordered(float f, float f2, float f3, float f4, float f5, int n, int n2) {
        float f6 = 0.0f;
        if (f5 < 1.0f) {
            f6 = 1.0f;
        }
        RenderUtil2.drawRect(f + f5, f2 + f5, f3 - f5, f4 - f5, n);
        RenderUtil2.drawRect(f, f2 + 1.0f - f6, f + f5, f4, n2);
        RenderUtil2.drawRect(f, f2, f3 - 1.0f + f6, f2 + f5, n2);
        RenderUtil2.drawRect(f3 - f5, f2, f3, f4 - 1.0f + f6, n2);
        RenderUtil2.drawRect(f + 1.0f - f6, f4 - f5, f3, f4, n2);
    }

    public static void renderThree() {
        GL11.glStencilFunc((int)514, (int)1, (int)15);
        GL11.glStencilOp((int)7680, (int)7680, (int)7680);
        GL11.glPolygonMode((int)1032, (int)6913);
    }

    public static void drawOpenGradientBox(BlockPos blockPos, Color color, Color color2, double d) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            if (enumFacing == EnumFacing.UP) continue;
            RenderUtil2.drawGradientPlane(blockPos, enumFacing, color, color2, d);
        }
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

    public static void drawBoundingBoxBottomBlockPosNorth(BlockPos blockPos, float f, int n, int n2, int n3, int n4) {
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
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4 / 2).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4 / 2).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4 / 2).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4 / 2).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4 / 2).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static BufferBuilder get_buffer_build() {
        return tessellator.getBuffer();
    }

    public static Vec3d getInterpolatedAmount(Entity entity, double d) {
        return RenderUtil2.getInterpolatedAmount(entity, d, d, d);
    }

    public static void draw_cube_line(BlockPos blockPos, int n, int n2, int n3, int n4, String string) {
        RenderUtil2.draw_cube_line(tessellator.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, string);
    }

    public static void drawOutlinedRoundedRectangle(int n, int n2, int n3, int n4, float f, float f2, float f3, float f4, float f5, float f6) {
        RenderUtil2.drawRoundedRectangle(n, n2, n3, n4, f);
        GL11.glColor4f((float)f2, (float)f3, (float)f4, (float)f5);
        RenderUtil2.drawRoundedRectangle((float)n + f6, (float)n2 + f6, (float)n3 - f6 * 2.0f, (float)n4 - f6 * 2.0f, f);
    }

    public static void setColor(Color color) {
        GL11.glColor4d((double)((double)color.getRed() / 255.0), (double)((double)color.getGreen() / 255.0), (double)((double)color.getBlue() / 255.0), (double)((double)color.getAlpha() / 255.0));
    }

    public static void draw_cube_line(BlockPos blockPos, int n, String string) {
        int n2 = n >>> 24 & 0xFF;
        int n3 = n >>> 16 & 0xFF;
        int n4 = n >>> 8 & 0xFF;
        int n5 = n & 0xFF;
        RenderUtil2.draw_cube_line(blockPos, n3, n4, n5, n2, string);
    }

    public static void drawTextShadow(String string, int n, int n2, int n3, double d) {
        RenderUtil2.mc.fontRendererObj.drawStringWithShadow(string, (float)n, (float)n2, n3);
    }

    public static void drawBoxESP(BlockPos blockPos, Color color, boolean bl, Color color2, float f, boolean bl2, boolean bl3, int n, boolean bl4) {
        if (bl3) {
            RenderUtil2.drawBox(blockPos, new Color(color.getRed(), color.getGreen(), color.getBlue(), n));
        }
        if (bl2) {
            RenderUtil2.drawBlockOutline(blockPos, bl ? color2 : color, f, bl4);
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
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GL11.glShadeModel((int)7425);
    }

    public static void draw_cube(BlockPos blockPos, int n, int n2, int n3, int n4, String string) {
        RenderUtil2.draw_cube(tessellator.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, string);
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

    public static double getRenderPosZ() {
        return ((IRenderManager)mc.getRenderManager()).getRenderPosZ();
    }

    public static int getTextHeight(double d) {
        return (int)((double)RenderUtil2.mc.fontRendererObj.FONT_HEIGHT * d);
    }

    public static void drawCircle(float f, float f2, float f3, int n) {
        float f4 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(n & 0xFF) / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GL11.glColor4f((float)f5, (float)f6, (float)f7, (float)f4);
        GL11.glBegin((int)9);
        for (int i = 0; i <= 360; ++i) {
            GL11.glVertex2d((double)((double)f + Math.sin((double)i * 3.141526 / 180.0) * (double)f3), (double)((double)f2 + Math.cos((double)i * 3.141526 / 180.0) * (double)f3));
        }
        GL11.glEnd();
        GlStateManager.resetColor();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static AxisAlignedBB updateToCamera(AxisAlignedBB axisAlignedBB) {
        return new AxisAlignedBB(axisAlignedBB.minX - RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.minY - RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.minZ - RenderUtil2.mc.getRenderManager().viewerPosZ, axisAlignedBB.maxX - RenderUtil2.mc.getRenderManager().viewerPosX, axisAlignedBB.maxY - RenderUtil2.mc.getRenderManager().viewerPosY, axisAlignedBB.maxZ - RenderUtil2.mc.getRenderManager().viewerPosZ);
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

    public static void start1() {
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
    }

    public static BufferBuilder getBufferBuilder() {
        return tessellator.getBuffer();
    }

    public static void drawGradientPlane(BlockPos blockPos, EnumFacing enumFacing, Color color, Color color2, double d) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        IBlockState iBlockState = RenderUtil2.mc.world.getBlockState(blockPos);
        Vec3d vec3d = EntityUtil.interpolateEntity((Entity)RenderUtil2.mc.player, mc.getRenderPartialTicks());
        AxisAlignedBB axisAlignedBB = iBlockState.getSelectedBoundingBox((World)RenderUtil2.mc.world, blockPos).expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord).addCoord(0.0, d, 0.0);
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

    public static void drawBoundingBoxBottomBlockPosWest(BlockPos blockPos, float f, int n, int n2, int n3, int n4) {
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
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4 / 2).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4 / 2).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawBBFill(AxisAlignedBB axisAlignedBB, Color color) {
        GlStateManager.pushMatrix();
        RenderUtil2.start1();
        RenderUtil2.width(1.0f);
        int n = color.getRed();
        int n2 = color.getBlue();
        int n3 = color.getGreen();
        int n4 = color.getAlpha();
        builder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        double d = axisAlignedBB.minX;
        double d2 = axisAlignedBB.minY;
        double d3 = axisAlignedBB.minZ;
        double d4 = axisAlignedBB.maxX;
        double d5 = axisAlignedBB.maxY;
        double d6 = axisAlignedBB.maxZ;
        RenderUtil2.vertex(d, d2, d3).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d, d2, d3).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d, d2, d3).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d, d2, d6).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d, d5, d3).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d, d5, d6).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d, d5, d6).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d, d2, d6).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d4, d5, d6).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d4, d2, d6).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d4, d2, d6).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d4, d2, d3).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d4, d5, d6).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d4, d5, d3).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d4, d5, d3).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d4, d2, d3).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d, d5, d3).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d, d2, d3).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d, d2, d3).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d4, d2, d3).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d, d2, d6).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d4, d2, d6).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d4, d2, d6).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d, d5, d3).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d, d5, d3).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d, d5, d6).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d4, d5, d3).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d4, d5, d6).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d4, d5, d6).color(n, n3, n2, n4).endVertex();
        RenderUtil2.vertex(d4, d5, d6).color(n, n3, n2, n4).endVertex();
        tessellator.draw();
        RenderUtil2.end1();
        GlStateManager.popMatrix();
    }

    public static void drawBoxBottom(BlockPos blockPos, int n, int n2, int n3, int n4) {
        RenderUtil2.prepare(7);
        RenderUtil2.drawBoxBottom(tessellator.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 1.0f, n, n2, n3, n4);
        RenderUtil2.release();
    }

    public static void drawBoxBottom(BufferBuilder bufferBuilder, float f, float f2, float f3, float f4, float f5, int n, int n2, int n3, int n4) {
        bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f5)).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f5)).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
    }

    public static void drawRoundedRectangle(float f, float f2, float f3, float f4, float f5) {
        GL11.glEnable((int)3042);
        RenderUtil2.drawArc(f + f3 - f5, f2 + f4 - f5, f5, 0.0f, 90.0f, 16);
        RenderUtil2.drawArc(f + f5, f2 + f4 - f5, f5, 90.0f, 180.0f, 16);
        RenderUtil2.drawArc(f + f5, f2 + f5, f5, 180.0f, 270.0f, 16);
        RenderUtil2.drawArc(f + f3 - f5, f2 + f5, f5, 270.0f, 360.0f, 16);
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
        RenderUtil2.glEnd();
        GL11.glDisable((int)3042);
    }

    public static void draw_cube_line(BufferBuilder bufferBuilder, float f, float f2, float f3, float f4, float f5, float f6, int n, int n2, int n3, int n4, String string) {
        if (Arrays.asList(string.split("-")).contains("downwest") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("upwest") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("downeast") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("upeast") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("downnorth") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("upnorth") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("downsouth") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("upsouth") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("nortwest") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("norteast") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("southweast") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("southeast") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
        }
    }

    public static void release() {
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.popMatrix();
        GL11.glEnable((int)3553);
        GL11.glShadeModel((int)7424);
        GL11.glPolygonMode((int)1032, (int)6914);
    }

    public static void glrendermethod() {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glLineWidth((float)2.0f);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)2884);
        GL11.glDisable((int)2929);
        double d = RenderUtil2.mc.getRenderManager().viewerPosX;
        double d2 = RenderUtil2.mc.getRenderManager().viewerPosY;
        double d3 = RenderUtil2.mc.getRenderManager().viewerPosZ;
        GL11.glPushMatrix();
        GL11.glTranslated((double)(-d), (double)(-d2), (double)(-d3));
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

    public static void fakeGuiRect(double d, double d2, double d3, double d4, int n) {
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
        float f = (float)(n >> 24 & 0xFF) / 255.0f;
        float f2 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f3 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f4 = (float)(n & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)f2, (float)f3, (float)f4, (float)f);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferBuilder.pos(d, d4, 0.0).endVertex();
        bufferBuilder.pos(d3, d4, 0.0).endVertex();
        bufferBuilder.pos(d3, d2, 0.0).endVertex();
        bufferBuilder.pos(d, d2, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    private static void disableGL2D() {
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glHint((int)3154, (int)4352);
        GL11.glHint((int)3155, (int)4352);
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

    public static void drawBoundingBoxBottomBlockPosEast(BlockPos blockPos, float f, int n, int n2, int n3, int n4) {
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
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static final class GeometryMasks {
        public static final /* synthetic */ HashMap FACEMAP;

        static {
            FACEMAP = new HashMap();
            FACEMAP.put(EnumFacing.DOWN, 1);
            FACEMAP.put(EnumFacing.WEST, 16);
            FACEMAP.put(EnumFacing.NORTH, 4);
            FACEMAP.put(EnumFacing.SOUTH, 8);
            FACEMAP.put(EnumFacing.EAST, 32);
            FACEMAP.put(EnumFacing.UP, 2);
        }

        public static final class Line {
            public static final /* synthetic */ int DOWN_SOUTH;
            public static final /* synthetic */ int UP_SOUTH;
            public static final /* synthetic */ int ALL;
            public static final /* synthetic */ int UP_EAST;
            public static final /* synthetic */ int NORTH_WEST;
            public static final /* synthetic */ int DOWN_EAST;
            public static final /* synthetic */ int UP_WEST;
            public static final /* synthetic */ int DOWN_NORTH;
            public static final /* synthetic */ int NORTH_EAST;
            public static final /* synthetic */ int UP_NORTH;
            public static final /* synthetic */ int SOUTH_EAST;
            public static final /* synthetic */ int DOWN_WEST;
            public static final /* synthetic */ int SOUTH_WEST;

            static {
                DOWN_EAST = 33;
                SOUTH_WEST = 24;
                DOWN_NORTH = 5;
                NORTH_EAST = 36;
                UP_NORTH = 6;
                NORTH_WEST = 20;
                UP_WEST = 18;
                UP_SOUTH = 10;
                ALL = 63;
                UP_EAST = 34;
                DOWN_SOUTH = 9;
                SOUTH_EAST = 40;
                DOWN_WEST = 17;
            }
        }

        public static final class Quad {
            public static final /* synthetic */ int NORTH;
            public static final /* synthetic */ int ALL;
            public static final /* synthetic */ int SOUTH;
            public static final /* synthetic */ int WEST;
            public static final /* synthetic */ int DOWN;
            public static final /* synthetic */ int EAST;
            public static final /* synthetic */ int UP;

            static {
                EAST = 32;
                UP = 2;
                ALL = 63;
                DOWN = 1;
                SOUTH = 8;
                WEST = 16;
                NORTH = 4;
            }
        }
    }

    public static class RenderTesselator
    extends Tessellator {
        public static /* synthetic */ RenderTesselator INSTANCE;

        public RenderTesselator() {
            super(0x200000);
        }

        public static void drawHalfBox(BlockPos blockPos, int n, int n2, int n3, int n4, int n5) {
            RenderTesselator.drawBox(INSTANCE.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 0.5f, 1.0f, n, n2, n3, n4, n5);
        }

        public static void releaseGL() {
            GlStateManager.enableCull();
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableTexture2D();
            GlStateManager.enableBlend();
            GlStateManager.enableDepth();
        }

        public static void drawBox(BlockPos blockPos, int n, int n2, int n3, int n4, int n5) {
            RenderTesselator.drawBox(INSTANCE.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, n5);
        }

        public static void drawFullBox(AxisAlignedBB axisAlignedBB, BlockPos blockPos, float f, int n, int n2) {
            int n3 = n >>> 24 & 0xFF;
            int n4 = n >>> 16 & 0xFF;
            int n5 = n >>> 8 & 0xFF;
            int n6 = n & 0xFF;
            RenderTesselator.drawFullBox(axisAlignedBB, blockPos, f, n4, n5, n6, n3, n2);
        }

        public static void prepare(int n) {
            RenderTesselator.prepareGL();
            RenderTesselator.begin(n);
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

        static {
            INSTANCE = new RenderTesselator();
        }

        public static void render() {
            INSTANCE.draw();
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

        public static void drawFullBox(AxisAlignedBB axisAlignedBB, BlockPos blockPos, float f, int n, int n2, int n3, int n4, int n5) {
            RenderTesselator.prepare(7);
            RenderTesselator.drawBox(blockPos, n, n2, n3, n4, 63);
            RenderTesselator.release();
            RenderTesselator.drawBoundingBox(axisAlignedBB, f, n, n2, n3, n5);
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

        public static void release() {
            RenderTesselator.render();
            RenderTesselator.releaseGL();
        }

        public static BufferBuilder getBufferBuilder() {
            return INSTANCE.getBuffer();
        }

        public static void begin(int n) {
            INSTANCE.getBuffer().begin(n, DefaultVertexFormats.POSITION_COLOR);
        }
    }
}

