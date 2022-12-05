//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.module.modules.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.util.RenderUtil;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class BreadCrumbs
extends Module {
    public static /* synthetic */ Setting<Float> width;
    public /* synthetic */ Color color;
    public static /* synthetic */ Setting<Integer> blue;
    public static /* synthetic */ Setting<Integer> red;
    public static /* synthetic */ ArrayList<double[]> vecs;
    public static /* synthetic */ Setting<Integer> alpha;
    public static /* synthetic */ Setting<Integer> length;
    public static /* synthetic */ Setting<Boolean> syncColor;
    public static /* synthetic */ Setting<Integer> green;

    public Color getCurrentColor() {
        return new Color(red.getValue(), green.getValue(), blue.getValue(), alpha.getValue());
    }

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        try {
            double d = BreadCrumbs.mc.getRenderManager().renderPosX;
            double d2 = BreadCrumbs.mc.getRenderManager().renderPosY;
            double d3 = BreadCrumbs.mc.getRenderManager().renderPosZ;
            float f = (float)this.color.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.49987957f) ^ 0x7D80F037);
            float f2 = (float)this.color.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.4340212f) ^ 0x7DA13807);
            float f3 = (float)this.color.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.0131841665f) ^ 0x7F270267);
            if (this.isEnabled()) {
                Iterator<double[]> iterator2;
                RenderUtil.prepareGL();
                GL11.glPushMatrix();
                GL11.glEnable((int)2848);
                GL11.glLineWidth((float)width.getValue().floatValue());
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glLineWidth((float)width.getValue().floatValue());
                GL11.glDepthMask((boolean)false);
                GL11.glBegin((int)3);
                Iterator<double[]> iterator3 = iterator2 = vecs.iterator();
                while (iterator2.hasNext()) {
                    double d4 = 0.0;
                    double[] arrd = iterator3.next();
                    double d5 = BreadCrumbs.M(Math.hypot(arrd[0] - BreadCrumbs.mc.player.posX, arrd[1] - BreadCrumbs.mc.player.posY));
                    if (d4 > (double)length.getValue().intValue()) {
                        iterator2 = iterator3;
                        continue;
                    }
                    GL11.glColor4f((float)f, (float)f2, (float)f3, (float)(Float.intBitsToFloat(Float.floatToIntBits(14.099797f) ^ 0x7EE198C5) - (float)(d5 / (double)length.getValue().intValue())));
                    iterator2 = iterator3;
                    GL11.glVertex3d((double)(arrd[0] - d), (double)(arrd[1] - d2), (double)(arrd[2] - d3));
                }
                GL11.glEnd();
                GL11.glDepthMask((boolean)true);
                GL11.glPopMatrix();
                RenderUtil.releaseGL();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Override
    public void onDisable() {
        vecs.removeAll(vecs);
    }

    public static double M(double d) {
        if (d == Double.longBitsToDouble(Double.doubleToLongBits(1.7931000183463725E308) ^ 0x7FEFEB11C3AAD037L)) {
            return d;
        }
        if (d < Double.longBitsToDouble(Double.doubleToLongBits(1.1859585260803721E308) ^ 0x7FE51C5AEE8AD07FL)) {
            return d * Double.longBitsToDouble(Double.doubleToLongBits(-12.527781766526259) ^ 0x7FD90E3969654F8FL);
        }
        return d;
    }

    @Override
    public void onUpdate() {
        this.color = syncColor.getValue() != false ? Colors.INSTANCE.getCurrentColor() : this.getCurrentColor();
        try {
            double d = BreadCrumbs.mc.getRenderManager().renderPosX;
            double d2 = BreadCrumbs.mc.getRenderManager().renderPosY;
            double d3 = BreadCrumbs.mc.getRenderManager().renderPosZ;
            if (this.isEnabled()) {
                for (EntityPlayer entityPlayer : BreadCrumbs.mc.world.playerEntities) {
                    if (!(entityPlayer instanceof EntityPlayer)) continue;
                    EntityPlayer entityPlayer2 = entityPlayer;
                    boolean bl = entityPlayer2 == BreadCrumbs.mc.player;
                    double d4 = d2 + Double.longBitsToDouble(Double.doubleToLongBits(0.48965838138858014) ^ 0x7FDF56901B91AE07L);
                    if (BreadCrumbs.mc.player.isElytraFlying()) {
                        d4 -= Double.longBitsToDouble(Double.doubleToLongBits(29.56900080933637) ^ 0x7FC591AA097B7F4BL);
                    }
                    if (!bl) continue;
                    vecs.add(new double[]{d, d4 - (double)entityPlayer2.height, d3});
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (vecs.size() > length.getValue()) {
            vecs.remove(0);
        }
    }

    public BreadCrumbs() {
        super("BreadCrumbs", "Draws a small line behind you", Module.Category.RENDER, true, false, false);
        length = this.register(new Setting<Integer>("Length", 4, 1, 40));
        width = this.register(new Setting<Float>("Width", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(15.599429f) ^ 0x7EB99743)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(2.076195f) ^ 0x7F04E061)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(1.3190416f) ^ 0x7F08D65B))));
        syncColor = this.register(new Setting<Boolean>("Sync", false));
        red = this.register(new Setting<Integer>("Red", 30, 0, 255));
        green = this.register(new Setting<Integer>("Green", 167, 0, 255));
        blue = this.register(new Setting<Integer>("Blue", 255, 0, 255));
        alpha = this.register(new Setting<Integer>("Alpha", 255, 0, 255));
        vecs = new ArrayList();
    }
}

