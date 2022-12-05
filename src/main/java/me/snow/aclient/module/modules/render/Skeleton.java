//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.model.ModelPlayer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.module.modules.render;

import java.awt.Color;
import java.util.HashMap;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.util.BlockUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class Skeleton
extends Module {
    private final /* synthetic */ Setting<Integer> alpha;
    private final /* synthetic */ Setting<Boolean> invisibles;
    private final /* synthetic */ Setting<Float> lineWidth;
    private static final /* synthetic */ HashMap<EntityPlayer, float[][]> entities;

    public static void addEntity(EntityPlayer entityPlayer, ModelPlayer modelPlayer) {
        entities.put(entityPlayer, new float[][]{{modelPlayer.bipedHead.rotateAngleX, modelPlayer.bipedHead.rotateAngleY, modelPlayer.bipedHead.rotateAngleZ}, {modelPlayer.bipedRightArm.rotateAngleX, modelPlayer.bipedRightArm.rotateAngleY, modelPlayer.bipedRightArm.rotateAngleZ}, {modelPlayer.bipedLeftArm.rotateAngleX, modelPlayer.bipedLeftArm.rotateAngleY, modelPlayer.bipedLeftArm.rotateAngleZ}, {modelPlayer.bipedRightLeg.rotateAngleX, modelPlayer.bipedRightLeg.rotateAngleY, modelPlayer.bipedRightLeg.rotateAngleZ}, {modelPlayer.bipedLeftLeg.rotateAngleX, modelPlayer.bipedLeftLeg.rotateAngleY, modelPlayer.bipedLeftLeg.rotateAngleZ}});
    }

    private void drawSkeleton(Render3DEvent render3DEvent, EntityPlayer entityPlayer) {
        if (!BlockUtil.isPosInFov(new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ)).booleanValue()) {
            return;
        }
        if (entityPlayer.isInvisible() && !this.invisibles.getValue().booleanValue()) {
            return;
        }
        float[][] arrf = entities.get((Object)entityPlayer);
        if (arrf != null && entityPlayer.isEntityAlive() && !entityPlayer.isDead && entityPlayer != Skeleton.mc.player && !entityPlayer.isPlayerSleeping()) {
            Color color;
            GL11.glPushMatrix();
            GL11.glEnable((int)2848);
            GL11.glLineWidth((float)this.lineWidth.getValue().floatValue());
            if (AliceMain.friendManager.isFriend(entityPlayer.getName())) {
                GlStateManager.color((float)0.0f, (float)191.0f, (float)230.0f, (float)this.alpha.getValue().intValue());
            } else {
                color = Colors.INSTANCE.getCurrentColor();
                GlStateManager.color((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)this.alpha.getValue().intValue());
            }
            color = this.getVec3(render3DEvent, entityPlayer);
            double d = ((Vec3d)color).xCoord - Skeleton.mc.getRenderManager().renderPosX;
            double d2 = ((Vec3d)color).yCoord - Skeleton.mc.getRenderManager().renderPosY;
            double d3 = ((Vec3d)color).zCoord - Skeleton.mc.getRenderManager().renderPosZ;
            GL11.glTranslated((double)d, (double)d2, (double)d3);
            float f = entityPlayer.prevRenderYawOffset + (entityPlayer.renderYawOffset - entityPlayer.prevRenderYawOffset) * render3DEvent.getPartialTicks();
            GL11.glRotatef((float)(-f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslated((double)0.0, (double)0.0, (double)(entityPlayer.isSneaking() ? -0.235 : 0.0));
            float f2 = entityPlayer.isSneaking() ? 0.6f : 0.75f;
            GL11.glPushMatrix();
            GL11.glTranslated((double)-0.125, (double)f2, (double)0.0);
            if (arrf[3][0] != 0.0f) {
                GL11.glRotatef((float)(arrf[3][0] * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
            }
            if (arrf[3][1] != 0.0f) {
                GL11.glRotatef((float)(arrf[3][1] * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
            }
            if (arrf[3][2] != 0.0f) {
                GL11.glRotatef((float)(arrf[3][2] * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glBegin((int)3);
            GL11.glVertex3d((double)0.0, (double)0.0, (double)0.0);
            GL11.glVertex3d((double)0.0, (double)(-f2), (double)0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.125, (double)f2, (double)0.0);
            if (arrf[4][0] != 0.0f) {
                GL11.glRotatef((float)(arrf[4][0] * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
            }
            if (arrf[4][1] != 0.0f) {
                GL11.glRotatef((float)(arrf[4][1] * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
            }
            if (arrf[4][2] != 0.0f) {
                GL11.glRotatef((float)(arrf[4][2] * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glBegin((int)3);
            GL11.glVertex3d((double)0.0, (double)0.0, (double)0.0);
            GL11.glVertex3d((double)0.0, (double)(-f2), (double)0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glTranslated((double)0.0, (double)0.0, (double)(entityPlayer.isSneaking() ? 0.25 : 0.0));
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.0, (double)(entityPlayer.isSneaking() ? -0.05 : 0.0), (double)(entityPlayer.isSneaking() ? -0.01725 : 0.0));
            GL11.glPushMatrix();
            GL11.glTranslated((double)-0.375, (double)((double)f2 + 0.55), (double)0.0);
            if (arrf[1][0] != 0.0f) {
                GL11.glRotatef((float)(arrf[1][0] * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
            }
            if (arrf[1][1] != 0.0f) {
                GL11.glRotatef((float)(arrf[1][1] * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
            }
            if (arrf[1][2] != 0.0f) {
                GL11.glRotatef((float)(-arrf[1][2] * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glBegin((int)3);
            GL11.glVertex3d((double)0.0, (double)0.0, (double)0.0);
            GL11.glVertex3d((double)0.0, (double)-0.5, (double)0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.375, (double)((double)f2 + 0.55), (double)0.0);
            if (arrf[2][0] != 0.0f) {
                GL11.glRotatef((float)(arrf[2][0] * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
            }
            if (arrf[2][1] != 0.0f) {
                GL11.glRotatef((float)(arrf[2][1] * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
            }
            if (arrf[2][2] != 0.0f) {
                GL11.glRotatef((float)(-arrf[2][2] * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glBegin((int)3);
            GL11.glVertex3d((double)0.0, (double)0.0, (double)0.0);
            GL11.glVertex3d((double)0.0, (double)-0.5, (double)0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glRotatef((float)(f - entityPlayer.rotationYawHead), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.0, (double)((double)f2 + 0.55), (double)0.0);
            if (arrf[0][0] != 0.0f) {
                GL11.glRotatef((float)(arrf[0][0] * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
            }
            GL11.glBegin((int)3);
            GL11.glVertex3d((double)0.0, (double)0.0, (double)0.0);
            GL11.glVertex3d((double)0.0, (double)0.3, (double)0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glRotatef((float)(entityPlayer.isSneaking() ? 25.0f : 0.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslated((double)0.0, (double)(entityPlayer.isSneaking() ? -0.16175 : 0.0), (double)(entityPlayer.isSneaking() ? -0.48025 : 0.0));
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.0, (double)f2, (double)0.0);
            GL11.glBegin((int)3);
            GL11.glVertex3d((double)-0.125, (double)0.0, (double)0.0);
            GL11.glVertex3d((double)0.125, (double)0.0, (double)0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.0, (double)f2, (double)0.0);
            GL11.glBegin((int)3);
            GL11.glVertex3d((double)0.0, (double)0.0, (double)0.0);
            GL11.glVertex3d((double)0.0, (double)0.55, (double)0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated((double)0.0, (double)((double)f2 + 0.55), (double)0.0);
            GL11.glBegin((int)3);
            GL11.glVertex3d((double)-0.375, (double)0.0, (double)0.0);
            GL11.glVertex3d((double)0.375, (double)0.0, (double)0.0);
            GL11.glEnd();
            GL11.glPopMatrix();
            GL11.glPopMatrix();
        }
    }

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        if (Skeleton.fullNullCheck()) {
            return;
        }
        this.startEnd(true);
        GL11.glEnable((int)2903);
        GL11.glDisable((int)2848);
        entities.keySet().removeIf(this::doesntContain);
        Skeleton.mc.world.playerEntities.forEach(entityPlayer -> this.drawSkeleton(render3DEvent, (EntityPlayer)entityPlayer));
        Gui.drawRect((int)0, (int)0, (int)0, (int)0, (int)0);
        this.startEnd(false);
    }

    public Skeleton() {
        super("Skeleton", "Draws a skeleton inside the player.", Module.Category.RENDER, false, false, false);
        this.lineWidth = this.register(new Setting<Float>("LineWidth", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(5.0f)));
        this.invisibles = this.register(new Setting<Boolean>("Invisibles", false));
        this.alpha = this.register(new Setting<Integer>("Alpha", 255, 0, 255));
    }

    private void startEnd(boolean bl) {
        if (bl) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GL11.glEnable((int)2848);
            GlStateManager.disableDepth();
            GlStateManager.disableTexture2D();
            GL11.glHint((int)3154, (int)4354);
        } else {
            GlStateManager.disableBlend();
            GlStateManager.enableTexture2D();
            GL11.glDisable((int)2848);
            GlStateManager.enableDepth();
            GlStateManager.popMatrix();
        }
        GlStateManager.depthMask((!bl ? 1 : 0) != 0);
    }

    private boolean doesntContain(EntityPlayer entityPlayer) {
        return !Skeleton.mc.world.playerEntities.contains((Object)entityPlayer);
    }

    static {
        entities = new HashMap();
    }

    private Vec3d getVec3(Render3DEvent render3DEvent, EntityPlayer entityPlayer) {
        float f = render3DEvent.getPartialTicks();
        double d = entityPlayer.lastTickPosX + (entityPlayer.posX - entityPlayer.lastTickPosX) * (double)f;
        double d2 = entityPlayer.lastTickPosY + (entityPlayer.posY - entityPlayer.lastTickPosY) * (double)f;
        double d3 = entityPlayer.lastTickPosZ + (entityPlayer.posZ - entityPlayer.lastTickPosZ) * (double)f;
        return new Vec3d(d, d2, d3);
    }
}

