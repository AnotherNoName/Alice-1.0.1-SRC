//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.util.ca.sc;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class TessellatorUtil {
    private static final /* synthetic */ Minecraft mc;

    public static void drawBoundingBoxWithSides(BlockPos blockPos, int n, Color color, int n2) {
        TessellatorUtil.drawBoundingBoxWithSides(TessellatorUtil.getBoundingBox(blockPos, 1.0, 1.0, 1.0), n, color, color.getAlpha(), n2);
    }

    private static AxisAlignedBB getBoundingBox(BlockPos blockPos, double d, double d2, double d3) {
        double d4 = blockPos.getX();
        double d5 = blockPos.getY();
        double d6 = blockPos.getZ();
        return new AxisAlignedBB(d4, d5, d6, d4 + d, d5 + d2, d6 + d3);
    }

    public static void drawBox(AxisAlignedBB axisAlignedBB, boolean bl, double d, Color color, int n, int n2) {
        if (bl) {
            TessellatorUtil.drawBox(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX - axisAlignedBB.minX, axisAlignedBB.maxY - axisAlignedBB.minY, axisAlignedBB.maxZ - axisAlignedBB.minZ, color, n, n2);
        } else {
            TessellatorUtil.drawBox(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, axisAlignedBB.maxX - axisAlignedBB.minX, d, axisAlignedBB.maxZ - axisAlignedBB.minZ, color, n, n2);
        }
    }

    public static void drawBoundingBox(BlockPos blockPos, double d, float f, Color color) {
        TessellatorUtil.drawBoundingBox(TessellatorUtil.getBoundingBox(blockPos, 1.0, d, 1.0), (double)f, color, color.getAlpha());
    }

    private static void vertex(double d, double d2, double d3, BufferBuilder bufferBuilder) {
        bufferBuilder.pos(d - TessellatorUtil.mc.getRenderManager().viewerPosX, d2 - TessellatorUtil.mc.getRenderManager().viewerPosY, d3 - TessellatorUtil.mc.getRenderManager().viewerPosZ).endVertex();
    }

    public static void drawBox(AxisAlignedBB axisAlignedBB, Color color) {
        TessellatorUtil.drawBox(axisAlignedBB, true, 1.0, color, color.getAlpha(), 63);
    }

    private static void colorVertex(double d, double d2, double d3, Color color, int n, BufferBuilder bufferBuilder) {
        bufferBuilder.pos(d - TessellatorUtil.mc.getRenderManager().viewerPosX, d2 - TessellatorUtil.mc.getRenderManager().viewerPosY, d3 - TessellatorUtil.mc.getRenderManager().viewerPosZ).color(color.getRed(), color.getGreen(), color.getBlue(), n).endVertex();
    }

    public static void drawBoundingBoxWithSides(BlockPos blockPos, int n, Color color, int n2, int n3) {
        TessellatorUtil.drawBoundingBoxWithSides(TessellatorUtil.getBoundingBox(blockPos, 1.0, 1.0, 1.0), n, color, n2, n3);
    }

    private static void doVerticies(AxisAlignedBB axisAlignedBB, Color color, int n, BufferBuilder bufferBuilder, int n2, boolean bl) {
        if ((n2 & 0x20) != 0) {
            TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, n, bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color, n, bufferBuilder);
            if (bl) {
                TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferBuilder);
            }
        }
        if ((n2 & 0x10) != 0) {
            TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color, n, bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, n, bufferBuilder);
            if (bl) {
                TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferBuilder);
            }
        }
        if ((n2 & 4) != 0) {
            TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, n, bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, n, bufferBuilder);
            if (bl) {
                TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferBuilder);
            }
        }
        if ((n2 & 8) != 0) {
            TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color, n, bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color, n, bufferBuilder);
            if (bl) {
                TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferBuilder);
            }
        }
        if ((n2 & 2) != 0) {
            TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, n, bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, n, bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color, n, bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color, n, bufferBuilder);
            if (bl) {
                TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, n, bufferBuilder);
            }
        }
        if ((n2 & 1) != 0) {
            TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferBuilder);
            TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferBuilder);
            if (bl) {
                TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferBuilder);
            }
        }
    }

    public static void release() {
        GL11.glDisable((int)34383);
        GL11.glDisable((int)2848);
        GlStateManager.enableAlpha();
        GlStateManager.enableCull();
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.glLineWidth((float)1.0f);
        GlStateManager.shadeModel((int)7424);
        GL11.glHint((int)3154, (int)4352);
        GL11.glPopAttrib();
    }

    public static void drawBoundingBox(AxisAlignedBB axisAlignedBB, double d, Color color) {
        TessellatorUtil.drawBoundingBox(axisAlignedBB, d, color, color.getAlpha());
    }

    public static void drawLine(double d, double d2, double d3, double d4, double d5, double d6, Color color, float f) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.glLineWidth((float)f);
        color.getRGB();
        bufferBuilder.begin(1, DefaultVertexFormats.POSITION);
        TessellatorUtil.vertex(d, d2, d3, bufferBuilder);
        TessellatorUtil.vertex(d4, d5, d6, bufferBuilder);
        tessellator.draw();
    }

    public static void drawBox(AxisAlignedBB axisAlignedBB, boolean bl, double d, Color color, int n) {
        TessellatorUtil.drawBox(axisAlignedBB, bl, d, color, color.getAlpha(), n);
    }

    public static void drawBoundingBox(AxisAlignedBB axisAlignedBB, double d, Color color, int n) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.glLineWidth((float)((float)d));
        color.getRGB();
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferBuilder);
        TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferBuilder);
        TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferBuilder);
        TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferBuilder);
        TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferBuilder);
        TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, n, bufferBuilder);
        TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color, n, bufferBuilder);
        TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferBuilder);
        TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ, color, color.getAlpha(), bufferBuilder);
        TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color, n, bufferBuilder);
        TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color, n, bufferBuilder);
        TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ, color, n, bufferBuilder);
        TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, n, bufferBuilder);
        TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ, color, color.getAlpha(), bufferBuilder);
        TessellatorUtil.colorVertex(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, n, bufferBuilder);
        TessellatorUtil.colorVertex(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ, color, n, bufferBuilder);
        tessellator.draw();
    }

    public static void drawBoundingBoxWithSides(AxisAlignedBB axisAlignedBB, int n, Color color, int n2) {
        TessellatorUtil.drawBoundingBoxWithSides(axisAlignedBB, n, color, color.getAlpha(), n2);
    }

    public static void drawBox(AxisAlignedBB axisAlignedBB, double d, Color color, int n) {
        TessellatorUtil.drawBox(axisAlignedBB, false, d, color, color.getAlpha(), n);
    }

    public static void drawBox(BlockPos blockPos, double d, Color color, int n) {
        TessellatorUtil.drawBox(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0, d, 1.0, color, color.getAlpha(), n);
    }

    static {
        mc = Minecraft.getMinecraft();
    }

    public static void drawBox(double d, double d2, double d3, double d4, double d5, double d6, Color color, int n, int n2) {
        GlStateManager.disableAlpha();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        color.getRGB();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        TessellatorUtil.doVerticies(new AxisAlignedBB(d, d2, d3, d + d4, d2 + d5, d3 + d6), color, n, bufferBuilder, n2, false);
        tessellator.draw();
        GlStateManager.enableAlpha();
    }

    public static void drawDirection(Points points, Color color, float f) {
        int n;
        for (n = 0; n < 4; ++n) {
            TessellatorUtil.drawLine(points.getPoint(n)[0], points.yMin, points.getPoint(n)[1], points.getPoint((n + 1) % 4)[0], points.yMin, points.getPoint((n + 1) % 4)[1], color, f);
        }
        for (n = 0; n < 4; ++n) {
            TessellatorUtil.drawLine(points.getPoint(n)[0], points.yMax, points.getPoint(n)[1], points.getPoint((n + 1) % 4)[0], points.yMax, points.getPoint((n + 1) % 4)[1], color, f);
        }
        for (n = 0; n < 4; ++n) {
            TessellatorUtil.drawLine(points.getPoint(n)[0], points.yMin, points.getPoint(n)[1], points.getPoint(n)[0], points.yMax, points.getPoint(n)[1], color, f);
        }
    }

    public static void drawLine(double d, double d2, double d3, double d4, double d5, double d6, Color color) {
        TessellatorUtil.drawLine(d, d2, d3, d4, d5, d6, color, 1.0f);
    }

    public static void drawBoundingBoxWithSides(AxisAlignedBB axisAlignedBB, int n, Color color, int n2, int n3) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.glLineWidth((float)n);
        bufferBuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        TessellatorUtil.doVerticies(axisAlignedBB, color, n2, bufferBuilder, n3, true);
        tessellator.draw();
    }

    public static void prepare() {
        GL11.glPushAttrib((int)1048575);
        GL11.glHint((int)3154, (int)4354);
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.shadeModel((int)7425);
        GlStateManager.depthMask((boolean)false);
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        GL11.glEnable((int)2848);
        GL11.glEnable((int)34383);
    }

    private static class Points {
        public final /* synthetic */ double yMax;
        private /* synthetic */ int count;
        /* synthetic */ double[][] point;
        private final /* synthetic */ double zCenter;
        private final /* synthetic */ double xCenter;
        private final /* synthetic */ float rotation;
        public final /* synthetic */ double yMin;

        public Points(double d, double d2, double d3, double d4, float f) {
            this.point = new double[10][2];
            this.count = 0;
            this.yMin = d;
            this.yMax = d2;
            this.xCenter = d3;
            this.zCenter = d4;
            this.rotation = f;
        }

        public double[] getPoint(int n) {
            return this.point[n];
        }

        public void addPoints(double d, double d2) {
            double d3 = (d -= this.xCenter) * Math.cos(this.rotation) - (d2 -= this.zCenter) * Math.sin(this.rotation);
            double d4 = d * Math.sin(this.rotation) + d2 * Math.cos(this.rotation);
            this.point[this.count++] = new double[]{d3 += this.xCenter, d4 += this.zCenter};
        }
    }
}

