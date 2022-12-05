//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.util.cc;

import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class RenderBuilderCC {
    private /* synthetic */ boolean depth;
    private /* synthetic */ boolean alpha;
    private /* synthetic */ boolean shade;
    private /* synthetic */ double length;
    private /* synthetic */ Color color;
    private /* synthetic */ boolean blend;
    private /* synthetic */ boolean texture;
    private /* synthetic */ boolean setup;
    private /* synthetic */ double height;
    private /* synthetic */ boolean cull;
    private /* synthetic */ Box box;
    private /* synthetic */ double width;
    private /* synthetic */ AxisAlignedBB axisAlignedBB;

    public RenderBuilderCC position(AxisAlignedBB axisAlignedBB) {
        this.axisAlignedBB = axisAlignedBB;
        return this;
    }

    public RenderBuilderCC depth(boolean bl) {
        if (bl) {
            GlStateManager.disableDepth();
            GlStateManager.depthMask((boolean)false);
        }
        this.depth = bl;
        return this;
    }

    public RenderBuilderCC cull(boolean bl) {
        if (this.cull) {
            GlStateManager.disableCull();
        }
        this.cull = bl;
        return this;
    }

    public RenderBuilderCC setup() {
        GlStateManager.pushMatrix();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        this.setup = true;
        return this;
    }

    public Color getColor() {
        return this.color;
    }

    public RenderBuilderCC box(Box box) {
        this.box = box;
        return this;
    }

    public RenderBuilderCC length(double d) {
        this.length = d;
        return this;
    }

    public RenderBuilderCC blend() {
        GlStateManager.enableBlend();
        this.blend = true;
        return this;
    }

    public RenderBuilderCC texture() {
        GlStateManager.disableTexture2D();
        this.texture = true;
        return this;
    }

    public double getHeight() {
        return this.height;
    }

    public RenderBuilderCC line(float f) {
        GlStateManager.glLineWidth((float)f);
        return this;
    }

    public double getWidth() {
        return this.width;
    }

    public RenderBuilderCC width(double d) {
        this.width = d;
        return this;
    }

    public RenderBuilderCC height(double d) {
        this.height = d;
        return this;
    }

    public RenderBuilderCC() {
        this.axisAlignedBB = new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        this.box = Box.FILL;
        this.color = Color.WHITE;
    }

    public Box getBox() {
        return this.box;
    }

    public double getLength() {
        return this.length;
    }

    public AxisAlignedBB getAxisAlignedBB() {
        return this.axisAlignedBB;
    }

    public RenderBuilderCC build() {
        if (this.depth) {
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
        }
        if (this.texture) {
            GlStateManager.enableTexture2D();
        }
        if (this.blend) {
            GlStateManager.disableBlend();
        }
        if (this.cull) {
            GlStateManager.enableCull();
        }
        if (this.alpha) {
            GlStateManager.enableAlpha();
        }
        if (this.shade) {
            GlStateManager.shadeModel((int)7424);
        }
        if (this.setup) {
            GL11.glDisable((int)2848);
            GlStateManager.popMatrix();
        }
        return this;
    }

    public RenderBuilderCC position(BlockPos blockPos) {
        this.position(new AxisAlignedBB((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), (double)(blockPos.getX() + 1), (double)(blockPos.getY() + 1), (double)(blockPos.getZ() + 1)));
        return this;
    }

    public RenderBuilderCC shade(boolean bl) {
        if (bl) {
            GlStateManager.shadeModel((int)7425);
        }
        this.shade = bl;
        return this;
    }

    public RenderBuilderCC position(Vec3d vec3d) {
        this.position(new AxisAlignedBB(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord, vec3d.xCoord + 1.0, vec3d.yCoord + 1.0, vec3d.zCoord + 1.0));
        return this;
    }

    public RenderBuilderCC alpha(boolean bl) {
        if (this.alpha) {
            GlStateManager.disableAlpha();
        }
        this.alpha = bl;
        return this;
    }

    public RenderBuilderCC color(Color color) {
        this.color = color;
        return this;
    }

    public static enum Box {
        FILL,
        OUTLINE,
        BOTH,
        GLOW,
        REVERSE,
        CLAW,
        NONE;

    }
}

