//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.manager;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import me.snow.aclient.module.Feature;
import me.snow.aclient.util.RenderUtil;
import me.snow.aclient.util.Util;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

public class WaypointManager
extends Feature {
    public static final /* synthetic */ ResourceLocation WAYPOINT_RESOURCE;
    public /* synthetic */ Map<String, Waypoint> waypoints;

    public WaypointManager() {
        this.waypoints = new HashMap<String, Waypoint>();
    }

    static {
        WAYPOINT_RESOURCE = new ResourceLocation("textures/waypoint.png");
    }

    public static class Waypoint {
        public /* synthetic */ int x;
        public /* synthetic */ int z;
        public /* synthetic */ String owner;
        public /* synthetic */ int red;
        public /* synthetic */ int green;
        public /* synthetic */ int dimension;
        public /* synthetic */ int alpha;
        public /* synthetic */ int blue;
        public /* synthetic */ int y;
        public /* synthetic */ String server;

        public void render() {
            double d = (double)this.x - Util.mc.getRenderManager().renderPosX + 0.5;
            double d2 = (double)this.y - Util.mc.getRenderManager().renderPosY - 0.5;
            double d3 = (double)this.z - Util.mc.getRenderManager().renderPosZ + 0.5;
            float f = (float)(Util.mc.player.getDistance(d + Util.mc.getRenderManager().renderPosX, d2 + Util.mc.getRenderManager().renderPosY, d3 + Util.mc.getRenderManager().renderPosZ) / 4.0);
            if (f < 1.6f) {
                f = 1.6f;
            }
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)d), (float)((float)d2 + 1.4f), (float)((float)d3));
            GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-Util.mc.getRenderManager().playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)Util.mc.getRenderManager().playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glScalef((float)(-(f /= 25.0f)), (float)(-f), (float)f);
            GL11.glDisable((int)2896);
            GL11.glDisable((int)2929);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            int n = Util.mc.fontRendererObj.getStringWidth(this.owner) / 2;
            GL11.glPushMatrix();
            GL11.glPopMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            Util.mc.fontRendererObj.drawStringWithShadow(this.owner, (float)(-n), (float)(-(Util.mc.fontRendererObj.FONT_HEIGHT + 7)), new Color(this.red, this.green, this.blue, this.alpha).getRGB());
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.enableTexture2D();
            GL11.glDisable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)2929);
            GL11.glPopMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }

        public void renderBox() {
            GL11.glPushMatrix();
            GL11.glDisable((int)2896);
            GL11.glDisable((int)2929);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            RenderUtil.drawBlockOutline(new BlockPos(this.x, this.y, this.z), new Color(this.red, this.green, this.blue, this.alpha), 1.0f, true);
            GlStateManager.enableTexture2D();
            GL11.glDisable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)2929);
            GL11.glPopMatrix();
        }

        public Waypoint(String string, String string2, int n, int n2, int n3, int n4, Color color) {
            this.owner = string;
            this.server = string2;
            this.dimension = n;
            this.x = n2;
            this.y = n3;
            this.z = n4;
            this.red = color.getRed();
            this.green = color.getGreen();
            this.blue = color.getBlue();
            this.alpha = color.getAlpha();
        }
    }
}

