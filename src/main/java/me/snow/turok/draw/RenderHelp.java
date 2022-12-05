//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.math.BlockPos
 *  org.lwjgl.opengl.GL11
 */
package me.snow.turok.draw;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import me.snow.aclient.util.Util;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class RenderHelp
extends Tessellator {
    public static /* synthetic */ RenderHelp INSTANCE;

    public static void draw_gradiant_line(BufferBuilder bufferBuilder, double d, double d2, double d3, double d4, double d5, double d6, Color color, Color color2) {
        bufferBuilder.pos(d, d2, d3).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos(d4, d5, d6).color(color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()).endVertex();
    }

    private static void colorVertex(double d, double d2, double d3, Color color, int n, BufferBuilder bufferBuilder) {
        bufferBuilder.pos(d - Util.mc.getRenderManager().viewerPosX, d2 - Util.mc.getRenderManager().viewerPosY, d3 - Util.mc.getRenderManager().viewerPosZ).color(color.getRed(), color.getGreen(), color.getBlue(), n).endVertex();
    }

    public static void prepare(String string) {
        int n = 0;
        if (string.equalsIgnoreCase("quads")) {
            n = 7;
        } else if (string.equalsIgnoreCase("lines")) {
            n = 1;
        }
        RenderHelp.prepare_gl();
        RenderHelp.begin(n);
    }

    static {
        INSTANCE = new RenderHelp();
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

    public static void begin(int n) {
        INSTANCE.getBuffer().begin(n, DefaultVertexFormats.POSITION_COLOR);
    }

    public static void release_gl() {
        GlStateManager.enableCull();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
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
    }

    public static void draw_cube_line(float f, float f2, float f3, int n, String string) {
        int n2 = n >>> 24 & 0xFF;
        int n3 = n >>> 16 & 0xFF;
        int n4 = n >>> 8 & 0xFF;
        int n5 = n & 0xFF;
        RenderHelp.draw_cube_line(INSTANCE.getBuffer(), f, f2, f3, 1.0f, 1.0f, 1.0f, n3, n4, n5, n2, 1.0f, string);
    }

    public static void draw_triangle_line(BufferBuilder bufferBuilder, float f, float f2, float f3, float f4, float f5, float f6, int n, int n2, int n3, int n4, float f7, String string) {
        GL11.glLineWidth((float)f7);
        if (Arrays.asList(string.split("-")).contains("downwest") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f + 0.5, (double)f2 + 0.5, (double)f3 + 0.5).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("upwest") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f + 0.5, (double)f2 + 0.5, (double)f3 + 0.5).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("downeast") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f + 0.5, (double)f2 + 0.5, (double)f3 + 0.5).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("upeast") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f + 0.5, (double)f2 + 0.5, (double)f3 + 0.5).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("downnorth") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f + 0.5, (double)f2 + 0.5, (double)f3 + 0.5).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("upnorth") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f + 0.5, (double)f2 + 0.5, (double)f3 + 0.5).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("downsouth") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f + 0.5, (double)f2 + 0.5, (double)f3 + 0.5).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("upsouth") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f + 0.5, (double)f2 + 0.5, (double)f3 + 0.5).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("nortwest") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f + 0.5, (double)f2 + 0.5, (double)f3 + 0.5).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("norteast") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f + 0.5, (double)f2 + 0.5, (double)f3 + 0.5).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("southweast") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f + 0.5, (double)f2 + 0.5, (double)f3 + 0.5).color(n, n2, n3, n4).endVertex();
        }
        if (Arrays.asList(string.split("-")).contains("southeast") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f + 0.5, (double)f2 + 0.5, (double)f3 + 0.5).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f + 0.5, (double)f2 + 0.5, (double)f3 + 0.5).color(n, n2, n3, n4).endVertex();
        }
    }

    public static void prepare_gl_2d() {
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

    public static void render() {
        INSTANCE.draw();
    }

    public static void release() {
        RenderHelp.render();
        RenderHelp.release_gl();
    }

    public static void draw_gradiant_outline(BufferBuilder bufferBuilder, double d, double d2, double d3, double d4, Color color, Color color2, String string) {
        List<String> list = Arrays.asList(string.split("-"));
        boolean bl = string.equalsIgnoreCase("all");
        if (list.contains("northwest") || bl) {
            RenderHelp.draw_gradiant_line(bufferBuilder, d, d2, d3, d, d2 + d4, d3, color, color2);
        }
        if (list.contains("northeast") || bl) {
            RenderHelp.draw_gradiant_line(bufferBuilder, d + 1.0, d2, d3, d + 1.0, d2 + d4, d3, color, color2);
        }
        if (list.contains("southwest") || bl) {
            RenderHelp.draw_gradiant_line(bufferBuilder, d, d2, d3 + 1.0, d, d2 + d4, d3 + 1.0, color, color2);
        }
        if (list.contains("southeast") || bl) {
            RenderHelp.draw_gradiant_line(bufferBuilder, d + 1.0, d2, d3 + 1.0, d + 1.0, d2 + d4, d3 + 1.0, color, color2);
        }
    }

    public static BufferBuilder get_buffer_build() {
        return INSTANCE.getBuffer();
    }

    public static void draw_gradiant_cube(BufferBuilder bufferBuilder, float f, float f2, float f3, float f4, float f5, float f6, Color color, Color color2, String string) {
        int n = color.getRed();
        int n2 = color.getGreen();
        int n3 = color.getBlue();
        int n4 = color.getAlpha();
        int n5 = color2.getRed();
        int n6 = color2.getGreen();
        int n7 = color2.getBlue();
        int n8 = color2.getAlpha();
        List<String> list = Arrays.asList(string.split("-"));
        if (list.contains("north") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)f3).color(n5, n6, n7, n8).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n5, n6, n7, n8).endVertex();
        }
        if (list.contains("south") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n5, n6, n7, n8).endVertex();
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n5, n6, n7, n8).endVertex();
        }
        if (list.contains("west") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)f, (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)(f3 + f6)).color(n5, n6, n7, n8).endVertex();
            bufferBuilder.pos((double)f, (double)(f2 + f5), (double)f3).color(n5, n6, n7, n8).endVertex();
        }
        if (list.contains("east") || string.equalsIgnoreCase("all")) {
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)(f3 + f6)).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)f2, (double)f3).color(n, n2, n3, n4).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)f3).color(n5, n6, n7, n8).endVertex();
            bufferBuilder.pos((double)(f + f4), (double)(f2 + f5), (double)(f3 + f6)).color(n5, n6, n7, n8).endVertex();
        }
    }

    public static void draw_cube(BlockPos blockPos, int n, int n2, int n3, int n4, String string) {
        RenderHelp.draw_cube(INSTANCE.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, string);
    }

    public RenderHelp() {
        super(0x200000);
    }

    public static void draw_cube_line(BlockPos blockPos, int n, int n2, int n3, int n4, String string) {
        RenderHelp.draw_cube_line(INSTANCE.getBuffer(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0f, 1.0f, 1.0f, n, n2, n3, n4, 1.0f, string);
    }

    public static void draw_cube_line(BlockPos blockPos, int n, String string) {
        int n2 = n >>> 24 & 0xFF;
        int n3 = n >>> 16 & 0xFF;
        int n4 = n >>> 8 & 0xFF;
        int n5 = n & 0xFF;
        RenderHelp.draw_cube_line(blockPos, n3, n4, n5, n2, string);
    }

    public static void draw_cube(BlockPos blockPos, int n, String string) {
        int n2 = n >>> 24 & 0xFF;
        int n3 = n >>> 16 & 0xFF;
        int n4 = n >>> 8 & 0xFF;
        int n5 = n & 0xFF;
        RenderHelp.draw_cube(blockPos, n3, n4, n5, n2, string);
    }

    public static void draw_cube_line(BufferBuilder bufferBuilder, float f, float f2, float f3, float f4, float f5, float f6, int n, int n2, int n3, int n4, float f7, String string) {
        GL11.glLineWidth((float)f7);
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

    public static void release_gl_2d() {
        GL11.glDisable((int)34383);
        GL11.glDisable((int)2848);
        GlStateManager.enableAlpha();
        GlStateManager.enableCull();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.glLineWidth((float)1.0f);
        GlStateManager.shadeModel((int)7424);
        GL11.glHint((int)3154, (int)4352);
    }

    public static void draw_cube(float f, float f2, float f3, int n, String string) {
        int n2 = n >>> 24 & 0xFF;
        int n3 = n >>> 16 & 0xFF;
        int n4 = n >>> 8 & 0xFF;
        int n5 = n & 0xFF;
        RenderHelp.draw_cube(INSTANCE.getBuffer(), f, f2, f3, 1.0f, 1.0f, 1.0f, n3, n4, n5, n2, string);
    }

    public static void drawTriangleOutline(float f, float f2, float f3, float f4, float f5, float f6, int n) {
        boolean bl = GL11.glIsEnabled((int)3042);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2848);
        GL11.glPushMatrix();
        GL11.glLineWidth((float)f6);
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
}

