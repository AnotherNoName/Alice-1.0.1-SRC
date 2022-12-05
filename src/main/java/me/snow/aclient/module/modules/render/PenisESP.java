//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.Cylinder
 *  org.lwjgl.util.glu.Sphere
 */
package me.snow.aclient.module.modules.render;

import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.Sphere;

public class PenisESP
extends Module {
    private /* synthetic */ Setting<Float> penisSize;
    private /* synthetic */ Setting<Boolean> self;
    private /* synthetic */ Setting<Boolean> friends;
    private /* synthetic */ Setting<Float> othersLength;
    private /* synthetic */ Setting<Boolean> others;
    private /* synthetic */ Setting<Float> selfLength;
    private /* synthetic */ Setting<Float> friendLength;

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        for (Object e : PenisESP.mc.world.loadedEntityList) {
            if (!(e instanceof EntityPlayer)) continue;
            EntityPlayer entityPlayer = (EntityPlayer)e;
            double d = entityPlayer.lastTickPosX + (entityPlayer.posX - entityPlayer.lastTickPosX) * (double)PenisESP.mc.timer.field_194147_b;
            mc.getRenderManager();
            double d2 = d - PenisESP.mc.getRenderManager().renderPosX;
            double d3 = entityPlayer.lastTickPosY + (entityPlayer.posY - entityPlayer.lastTickPosY) * (double)PenisESP.mc.timer.field_194147_b;
            mc.getRenderManager();
            double d4 = d3 - PenisESP.mc.getRenderManager().renderPosY;
            double d5 = entityPlayer.lastTickPosZ + (entityPlayer.posZ - entityPlayer.lastTickPosZ) * (double)PenisESP.mc.timer.field_194147_b;
            mc.getRenderManager();
            double d6 = d5 - PenisESP.mc.getRenderManager().renderPosZ;
            GL11.glPushMatrix();
            RenderHelper.disableStandardItemLighting();
            this.esp(entityPlayer, d2, d4, d6);
            RenderHelper.enableStandardItemLighting();
            GL11.glPopMatrix();
        }
    }

    public void esp(EntityPlayer entityPlayer, double d, double d2, double d3) {
        if (!this.shouldRenderPenis(entityPlayer)) {
            return;
        }
        GL11.glDisable((int)2896);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)2848);
        GL11.glDepthMask((boolean)true);
        GL11.glLineWidth((float)1.0f);
        GL11.glTranslated((double)d, (double)d2, (double)d3);
        GL11.glRotatef((float)(-entityPlayer.rotationYaw), (float)0.0f, (float)entityPlayer.height, (float)0.0f);
        GL11.glTranslated((double)(-d), (double)(-d2), (double)(-d3));
        GL11.glTranslated((double)d, (double)(d2 + (double)(entityPlayer.height / 2.0f) - (double)0.225f), (double)d3);
        GL11.glColor4f((float)1.38f, (float)0.55f, (float)2.38f, (float)1.0f);
        GL11.glRotated((double)(entityPlayer.isSneaking() ? 35 : 0), (double)1.0, (double)0.0, (double)0.0);
        GL11.glTranslated((double)0.0, (double)0.0, (double)0.075f);
        Cylinder cylinder = new Cylinder();
        cylinder.setDrawStyle(100013);
        cylinder.draw(0.1f * this.penisSize.getValue().floatValue(), 0.11f, this.getPenisLength(entityPlayer), 25, 20);
        GL11.glTranslated((double)0.0, (double)0.0, (double)-0.12500000298023223);
        GL11.glTranslated((double)-0.09000000074505805, (double)0.0, (double)0.0);
        Sphere sphere = new Sphere();
        sphere.setDrawStyle(100013);
        sphere.draw(0.14f * this.penisSize.getValue().floatValue(), 10, 20);
        GL11.glTranslated((double)0.16000000149011612, (double)0.0, (double)0.0);
        Sphere sphere2 = new Sphere();
        sphere2.setDrawStyle(100013);
        sphere2.draw(0.14f * this.penisSize.getValue().floatValue(), 10, 20);
        GL11.glColor4f((float)1.35f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslated((double)-0.07000000074505806, (double)0.0, (double)((double)this.getPenisLength(entityPlayer) + 0.189999952316284));
        Sphere sphere3 = new Sphere();
        sphere3.setDrawStyle(100013);
        sphere3.draw(0.13f * this.penisSize.getValue().floatValue(), 15, 20);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)2848);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2896);
        GL11.glEnable((int)3553);
    }

    private float getPenisLength(EntityPlayer entityPlayer) {
        if (entityPlayer.entityUniqueID == PenisESP.mc.player.entityUniqueID) {
            return this.selfLength.getValue().floatValue();
        }
        if (AliceMain.friendManager.isFriend(entityPlayer)) {
            return this.friendLength.getValue().floatValue();
        }
        return this.othersLength.getValue().floatValue();
    }

    private boolean shouldRenderPenis(EntityPlayer entityPlayer) {
        if (entityPlayer.entityUniqueID == PenisESP.mc.player.entityUniqueID) {
            return this.self.getValue();
        }
        if (AliceMain.friendManager.isFriend(entityPlayer)) {
            return this.friends.getValue();
        }
        return this.others.getValue();
    }

    public PenisESP() {
        super("Penis", "Renders a penis on ur screen", Module.Category.RENDER, true, false, false);
        this.self = this.register(new Setting<Boolean>("Self", true));
        this.selfLength = this.register(new Setting<Float>("SelfLength", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(5.0f), f -> this.self.getValue()));
        this.friends = this.register(new Setting<Boolean>("Friends", true));
        this.friendLength = this.register(new Setting<Float>("FriendsLength", Float.valueOf(0.8f), Float.valueOf(0.1f), Float.valueOf(5.0f), f -> this.friends.getValue()));
        this.others = this.register(new Setting<Boolean>("Others", true));
        this.othersLength = this.register(new Setting<Float>("OthersLength", Float.valueOf(0.4f), Float.valueOf(0.1f), Float.valueOf(5.0f), f -> this.others.getValue()));
        this.penisSize = this.register(new Setting<Float>("Scale", Float.valueOf(1.5f), Float.valueOf(0.1f), Float.valueOf(5.0f)));
    }
}

