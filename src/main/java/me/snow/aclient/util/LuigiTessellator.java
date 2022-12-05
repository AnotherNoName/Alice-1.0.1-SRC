//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class LuigiTessellator
extends Tessellator {
    public static /* synthetic */ LuigiTessellator INSTANCE;

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

    public static void drawFullBox(AxisAlignedBB axisAlignedBB, BlockPos blockPos, float f, int n) {
        int n2 = n >>> 24 & 0xFF;
        int n3 = n >>> 16 & 0xFF;
        int n4 = n >>> 8 & 0xFF;
        int n5 = n & 0xFF;
        LuigiTessellator.drawFullBox(axisAlignedBB, blockPos, f, n3, n4, n5, n2);
    }

    public static void drawBoxOutline(BufferBuilder bufferBuilder, float f, float f2, float f3, float f4, float f5, float f6, int n, int n2, int n3, int n4, int n5) {
        if ((n5 & 1) != 0) {
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3 + 0.02).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)f3 + 0.02).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f + 0.02, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f + 0.02, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6) - 0.02).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6) - 0.02).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4) - 0.02, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4) - 0.02, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
        }
    }

    public static void drawFaceOutline(BufferBuilder bufferBuilder, float f, float f2, float f3, float f4, float f5, float f6, int n, int n2, int n3, int n4, int n5) {
        if ((n5 & 1) != 0) {
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3 + 0.02).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)f3 + 0.02).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f + 0.02, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f + 0.02, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6) - 0.02).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6) - 0.02).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4) - 0.02, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4) - 0.02, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
        }
    }

    public static void drawBox(BlockPos blockPos, int n, int n2) {
        int n3 = n >>> 24 & 0xFF;
        int n4 = n >>> 16 & 0xFF;
        int n5 = n >>> 8 & 0xFF;
        int n6 = n & 0xFF;
        LuigiTessellator.drawBox(blockPos, n4, n5, n6, n3, n2);
    }

    public static void drawBox(BlockPos blockPos, int n, int n2, int n3, int n4, int n5) {
        LuigiTessellator.drawBox(INSTANCE.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, n5);
    }

    static {
        INSTANCE = new LuigiTessellator();
    }

    public static void drawFullBox(AxisAlignedBB axisAlignedBB, BlockPos blockPos, float f, int n, int n2, int n3, int n4) {
        LuigiTessellator.prepare(7);
        LuigiTessellator.drawBox(blockPos, n, n2, n3, n4, 63);
        LuigiTessellator.release();
        LuigiTessellator.drawBoundingBox(axisAlignedBB, f, n, n2, n3, 255);
    }

    public static void drawBoundingBox(AxisAlignedBB axisAlignedBB, float f, int n, int n2, int n3, int n4) {
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
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void glBillboard(float f, float f2, float f3) {
        float f4 = 0.02666667f;
        GlStateManager.translate((double)((double)f - Minecraft.getMinecraft().getRenderManager().renderPosX), (double)((double)f2 - Minecraft.getMinecraft().getRenderManager().renderPosY), (double)((double)f3 - Minecraft.getMinecraft().getRenderManager().renderPosZ));
        GlStateManager.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)(-Minecraft.getMinecraft().player.rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)Minecraft.getMinecraft().player.rotationPitch, (float)(Minecraft.getMinecraft().gameSettings.thirdPersonView == 2 ? -1.0f : 1.0f), (float)0.0f, (float)0.0f);
        GlStateManager.scale((float)(-f4), (float)(-f4), (float)f4);
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
        Tessellator.getInstance().draw();
    }

    public static void drawFace(BlockPos blockPos, int n, int n2, int n3, int n4, int n5) {
        LuigiTessellator.drawFace(INSTANCE.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, n5);
    }

    public static void prepare(int n) {
        LuigiTessellator.prepareGL();
        LuigiTessellator.begin(n);
    }

    public static void drawBox(BufferBuilder bufferBuilder, float f, float f2, float f3, float f4, float f5, float f6, int n, int n2, int n3, int n4, int n5) {
        BufferBuilder bufferBuilder2 = INSTANCE.getBuffer();
        if ((n5 & 1) != 0) {
            bufferBuilder2.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
        }
        if ((n5 & 2) != 0) {
            bufferBuilder2.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
        }
        if ((n5 & 4) != 0) {
            bufferBuilder2.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
        }
        if ((n5 & 8) != 0) {
            bufferBuilder2.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
        }
        if ((n5 & 0x10) != 0) {
            bufferBuilder2.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
        }
        if ((n5 & 0x20) != 0) {
            bufferBuilder2.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder2.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
        }
    }

    public static void drawBox2(AxisAlignedBB axisAlignedBB, int n, int n2, int n3, int n4, int n5) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        if ((n5 & 1) != 0) {
            bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        }
        if ((n5 & 2) != 0) {
            bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        }
        if ((n5 & 4) != 0) {
            bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        }
        if ((n5 & 8) != 0) {
            bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        }
        if ((n5 & 0x10) != 0) {
            bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        }
        if ((n5 & 0x20) != 0) {
            bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        }
        tessellator.draw();
    }

    public static void drawFaceOutline(BlockPos blockPos, int n, int n2, int n3, int n4, int n5) {
        LuigiTessellator.drawFaceOutline(INSTANCE.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, n5);
    }

    public static void releaseGL() {
        GlStateManager.enableCull();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
    }

    public static void drawBoxOutline(BlockPos blockPos, int n, int n2, int n3, int n4, int n5) {
        LuigiTessellator.drawBoxOutline(INSTANCE.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, n5);
    }

    public static void drawBox(float f, float f2, float f3, int n, int n2) {
        int n3 = n >>> 24 & 0xFF;
        int n4 = n >>> 16 & 0xFF;
        int n5 = n >>> 8 & 0xFF;
        int n6 = n & 0xFF;
        LuigiTessellator.drawBox(INSTANCE.getBuffer(), f, f2, f3, 1.0f, 1.0f, 1.0f, n4, n5, n6, n3, n2);
    }

    public static void release() {
        LuigiTessellator.render();
        LuigiTessellator.releaseGL();
    }

    public static void drawLines(BlockPos blockPos, int n, int n2) {
        int n3 = n >>> 24 & 0xFF;
        int n4 = n >>> 16 & 0xFF;
        int n5 = n >>> 8 & 0xFF;
        int n6 = n & 0xFF;
        LuigiTessellator.drawLines(blockPos, n4, n5, n6, n3, n2);
    }

    public static void glBillboardDistanceScaled(float f, float f2, float f3, EntityPlayer entityPlayer, float f4) {
        LuigiTessellator.glBillboard(f, f2, f3);
        int n = (int)entityPlayer.getDistance((double)f, (double)f2, (double)f3);
        float f5 = (float)n / 2.0f / (2.0f + (2.0f - f4));
        if (f5 < 1.0f) {
            f5 = 1.0f;
        }
        GlStateManager.scale((float)f5, (float)f5, (float)f5);
    }

    public static void drawBoundingBoxForLogoutSpot(AxisAlignedBB axisAlignedBB, float f, int n, int n2, int n3, int n4) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GlStateManager.glLineWidth((float)f);
        BufferBuilder bufferBuilder = INSTANCE.getBuffer();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        bufferBuilder.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).color(n, n2, n3, n4).endVertex();
        LuigiTessellator.render();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawFace(BlockPos blockPos, int n, int n2) {
        int n3 = n >>> 24 & 0xFF;
        int n4 = n >>> 16 & 0xFF;
        int n5 = n >>> 8 & 0xFF;
        int n6 = n & 0xFF;
        LuigiTessellator.drawFace(blockPos, n4, n5, n6, n3, n2);
    }

    public static void drawFullBox2(AxisAlignedBB axisAlignedBB, BlockPos blockPos, float f, int n, int n2, int n3, int n4) {
        LuigiTessellator.prepare(7);
        LuigiTessellator.drawBox2(axisAlignedBB, n, n2, n3, n4, 63);
        LuigiTessellator.release();
        LuigiTessellator.drawBoundingBox(axisAlignedBB, f, n, n2, n3, 255);
    }

    public static void render() {
        INSTANCE.draw();
    }

    public static void drawBoundingBox(AxisAlignedBB axisAlignedBB, float f, int n) {
        int n2 = n >>> 24 & 0xFF;
        int n3 = n >>> 16 & 0xFF;
        int n4 = n >>> 8 & 0xFF;
        int n5 = n & 0xFF;
        LuigiTessellator.drawBoundingBox(axisAlignedBB, f, n3, n4, n5, n2);
    }

    public static void begin(int n) {
        INSTANCE.getBuffer().begin(n, DefaultVertexFormats.POSITION_COLOR);
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

    public static BufferBuilder getBufferBuilder() {
        return INSTANCE.getBuffer();
    }

    public static void drawLines(BlockPos blockPos, int n, int n2, int n3, int n4, int n5) {
        LuigiTessellator.drawLines(INSTANCE.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, n5);
    }

    public static void drawBoxOutline(BlockPos blockPos, int n, int n2) {
        int n3 = n >>> 24 & 0xFF;
        int n4 = n >>> 16 & 0xFF;
        int n5 = n >>> 8 & 0xFF;
        int n6 = n & 0xFF;
        LuigiTessellator.drawBoxOutline(blockPos, n4, n5, n6, n3, n2);
    }

    public static void drawFullBox2(AxisAlignedBB axisAlignedBB, BlockPos blockPos, float f, int n) {
        int n2 = n >>> 24 & 0xFF;
        int n3 = n >>> 16 & 0xFF;
        int n4 = n >>> 8 & 0xFF;
        int n5 = n & 0xFF;
        LuigiTessellator.drawFullBox2(axisAlignedBB, blockPos, f, n3, n4, n5, n2);
    }

    public static void drawBoxOutline(float f, float f2, float f3, int n, int n2) {
        int n3 = n >>> 24 & 0xFF;
        int n4 = n >>> 16 & 0xFF;
        int n5 = n >>> 8 & 0xFF;
        int n6 = n & 0xFF;
        LuigiTessellator.drawBoxOutline(INSTANCE.getBuffer(), f, f2, f3, 1.0f, 1.0f, 1.0f, n4, n5, n6, n3, n2);
    }

    public static void drawBoundingBoxForLogoutSpot(AxisAlignedBB axisAlignedBB, float f, int n) {
        int n2 = n >>> 24 & 0xFF;
        int n3 = n >>> 16 & 0xFF;
        int n4 = n >>> 8 & 0xFF;
        int n5 = n & 0xFF;
        LuigiTessellator.drawBoundingBoxForLogoutSpot(axisAlignedBB, f, n3, n4, n5, n2);
    }

    public static void drawFace(BufferBuilder bufferBuilder, float f, float f2, float f3, float f4, float f5, float f6, int n, int n2, int n3, int n4, int n5) {
        if ((n5 & 1) != 0) {
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
        }
    }

    public LuigiTessellator() {
        super(0x200000);
    }
}

