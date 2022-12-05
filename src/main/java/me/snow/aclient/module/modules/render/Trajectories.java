//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemEgg
 *  net.minecraft.item.ItemEnderPearl
 *  net.minecraft.item.ItemExpBottle
 *  net.minecraft.item.ItemFishingRod
 *  net.minecraft.item.ItemSnowball
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.Cylinder
 */
package me.snow.aclient.module.modules.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.Colors;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemSnowball;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;

public class Trajectories
extends Module {
    public /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Integer> alpha;
    public /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Boolean> colorSync;

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        if (Trajectories.mc.world != null && Trajectories.mc.player != null) {
            Color color = this.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue());
            mc.getRenderManager();
            double d = Trajectories.mc.player.lastTickPosX + (Trajectories.mc.player.posX - Trajectories.mc.player.lastTickPosX) * (double)render3DEvent.getPartialTicks();
            double d2 = Trajectories.mc.player.lastTickPosY + (Trajectories.mc.player.posY - Trajectories.mc.player.lastTickPosY) * (double)render3DEvent.getPartialTicks();
            double d3 = Trajectories.mc.player.lastTickPosZ + (Trajectories.mc.player.posZ - Trajectories.mc.player.lastTickPosZ) * (double)render3DEvent.getPartialTicks();
            Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND);
            if (Trajectories.mc.gameSettings.thirdPersonView == 0 && (Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemBow || Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemFishingRod || Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemEnderPearl || Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemEgg || Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSnowball || Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemExpBottle)) {
                Vec3d vec3d;
                float f;
                GL11.glPushMatrix();
                Item item = Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem();
                double d4 = d - (double)(MathHelper.cos((float)(Trajectories.mc.player.rotationYaw / 180.0f * (float)Math.PI)) * 0.16f);
                double d5 = d2 + (double)Trajectories.mc.player.getEyeHeight() - 0.1000000014901161;
                double d6 = d3 - (double)(MathHelper.sin((float)(Trajectories.mc.player.rotationYaw / 180.0f * (float)Math.PI)) * 0.16f);
                double d7 = (double)(-MathHelper.sin((float)(Trajectories.mc.player.rotationYaw / 180.0f * (float)Math.PI)) * MathHelper.cos((float)(Trajectories.mc.player.rotationPitch / 180.0f * (float)Math.PI))) * (item instanceof ItemBow ? 1.0 : 0.4);
                double d8 = (double)(-MathHelper.sin((float)(Trajectories.mc.player.rotationPitch / 180.0f * (float)Math.PI))) * (item instanceof ItemBow ? 1.0 : 0.4);
                double d9 = (double)(MathHelper.cos((float)(Trajectories.mc.player.rotationYaw / 180.0f * (float)Math.PI)) * MathHelper.cos((float)(Trajectories.mc.player.rotationPitch / 180.0f * (float)Math.PI))) * (item instanceof ItemBow ? 1.0 : 0.4);
                int n = 72000 - Trajectories.mc.player.getItemInUseCount();
                float f2 = (float)n / 20.0f;
                f2 = (f2 * f2 + f2 * 2.0f) / 3.0f;
                if (f2 > 1.0f) {
                    f2 = 1.0f;
                }
                float f3 = MathHelper.sqrt((double)(d7 * d7 + d8 * d8 + d9 * d9));
                d7 /= (double)f3;
                d8 /= (double)f3;
                d9 /= (double)f3;
                float f4 = item instanceof ItemBow ? f2 * 2.0f : (item instanceof ItemFishingRod ? 1.25f : (f = Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.EXPERIENCE_BOTTLE ? 0.9f : 1.0f));
                d7 *= (double)(f * (item instanceof ItemFishingRod ? 0.75f : (Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.EXPERIENCE_BOTTLE ? 0.75f : 1.5f)));
                d8 *= (double)(f * (item instanceof ItemFishingRod ? 0.75f : (Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.EXPERIENCE_BOTTLE ? 0.75f : 1.5f)));
                d9 *= (double)(f * (item instanceof ItemFishingRod ? 0.75f : (Trajectories.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.EXPERIENCE_BOTTLE ? 0.75f : 1.5f)));
                this.enableGL3D(2.0f);
                GlStateManager.color((float)((float)Integer.valueOf(color.getRed()).intValue() / 255.0f), (float)((float)Integer.valueOf(color.getGreen()).intValue() / 255.0f), (float)((float)Integer.valueOf(color.getBlue()).intValue() / 255.0f), (float)((float)this.alpha.getValue().intValue() / 255.0f));
                GL11.glEnable((int)2848);
                float f5 = (float)(item instanceof ItemBow ? 0.3 : 0.25);
                boolean bl = false;
                Entity entity = null;
                RayTraceResult rayTraceResult = null;
                while (!bl && d5 > 0.0) {
                    Vec3d vec3d2 = new Vec3d(d4, d5, d6);
                    vec3d = new Vec3d(d4 + d7, d5 + d8, d6 + d9);
                    RayTraceResult rayTraceResult2 = Trajectories.mc.world.rayTraceBlocks(vec3d2, vec3d, false, true, false);
                    if (rayTraceResult2 != null && rayTraceResult2.typeOfHit != RayTraceResult.Type.MISS) {
                        rayTraceResult = rayTraceResult2;
                        bl = true;
                    }
                    AxisAlignedBB axisAlignedBB = new AxisAlignedBB(d4 - (double)f5, d5 - (double)f5, d6 - (double)f5, d4 + (double)f5, d5 + (double)f5, d6 + (double)f5);
                    List<Entity> list = this.getEntitiesWithinAABB(axisAlignedBB.offset(d7, d8, d9).addCoord(1.0, 1.0, 1.0));
                    for (Entity entity2 : list) {
                        AxisAlignedBB axisAlignedBB2;
                        RayTraceResult rayTraceResult3;
                        if (!entity2.canBeCollidedWith() || entity2 == Trajectories.mc.player || (rayTraceResult3 = (axisAlignedBB2 = entity2.getEntityBoundingBox().addCoord((double)0.3f, (double)0.3f, (double)0.3f)).calculateIntercept(vec3d2, vec3d)) == null) continue;
                        bl = true;
                        entity = entity2;
                        rayTraceResult = rayTraceResult3;
                    }
                    if (entity != null) {
                        GlStateManager.color((float)((float)Integer.valueOf(color.getRed()).intValue() / 255.0f), (float)((float)Integer.valueOf(color.getGreen()).intValue() / 255.0f), (float)((float)Integer.valueOf(color.getBlue()).intValue() / 255.0f), (float)((float)this.alpha.getValue().intValue() / 255.0f));
                    }
                    d8 *= (double)0.99f;
                    this.drawLine3D((d4 += (d7 *= (double)0.99f)) - d, (d5 += (d8 -= item instanceof ItemBow ? 0.05 : 0.03)) - d2, (d6 += (d9 *= (double)0.99f)) - d3);
                }
                if (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
                    GlStateManager.translate((double)(d4 - d), (double)(d5 - d2), (double)(d6 - d3));
                    int n2 = rayTraceResult.sideHit.getIndex();
                    if (n2 == 2) {
                        GlStateManager.rotate((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    } else if (n2 == 3) {
                        GlStateManager.rotate((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    } else if (n2 == 4) {
                        GlStateManager.rotate((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    } else if (n2 == 5) {
                        GlStateManager.rotate((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    }
                    vec3d = new Cylinder();
                    GlStateManager.rotate((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    vec3d.setDrawStyle(100011);
                    if (entity != null) {
                        GlStateManager.color((float)((float)Integer.valueOf(color.getRed()).intValue() / 255.0f), (float)((float)Integer.valueOf(color.getGreen()).intValue() / 255.0f), (float)((float)Integer.valueOf(color.getBlue()).intValue() / 255.0f), (float)((float)this.alpha.getValue().intValue() / 255.0f));
                        GL11.glLineWidth((float)2.5f);
                        vec3d.draw(0.5f, 0.5f, 0.0f, Integer.valueOf(9).intValue(), 1);
                        GL11.glLineWidth((float)0.1f);
                        GlStateManager.color((float)((float)Integer.valueOf(color.getRed()).intValue() / 255.0f), (float)((float)Integer.valueOf(color.getGreen()).intValue() / 255.0f), (float)((float)Integer.valueOf(color.getBlue()).intValue() / 255.0f), (float)((float)this.alpha.getValue().intValue() / 255.0f));
                    }
                    vec3d.draw(0.5f, 0.2f, 0.0f, Integer.valueOf(9).intValue(), 1);
                }
                this.disableGL3D();
                GL11.glPopMatrix();
            }
        }
    }

    public void drawLine3D(double d, double d2, double d3) {
        GL11.glVertex3d((double)d, (double)d2, (double)d3);
    }

    public void enableGL3D(float f) {
        GL11.glDisable((int)3008);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)2884);
        Trajectories.mc.entityRenderer.disableLightmap();
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
        GL11.glLineWidth((float)f);
    }

    public void disableGL3D() {
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glDepthMask((boolean)true);
        GL11.glCullFace((int)1029);
        GL11.glDisable((int)2848);
        GL11.glHint((int)3154, (int)4352);
        GL11.glHint((int)3155, (int)4352);
    }

    public Trajectories() {
        super("Trajectories", "Draws trajectories.", Module.Category.RENDER, false, false, false);
        this.colorSync = this.register(new Setting<Boolean>("CSync", false));
        this.red = this.register(new Setting<Integer>("Red", 0, 0, 255));
        this.green = this.register(new Setting<Integer>("Green", 255, 0, 255));
        this.blue = this.register(new Setting<Integer>("Blue", 0, 0, 255));
        this.alpha = this.register(new Setting<Integer>("Alpha", 170, 0, 255));
    }

    private List<Entity> getEntitiesWithinAABB(AxisAlignedBB axisAlignedBB) {
        ArrayList<Entity> arrayList = new ArrayList<Entity>();
        int n = MathHelper.floor((double)((axisAlignedBB.minX - 2.0) / 16.0));
        int n2 = MathHelper.floor((double)((axisAlignedBB.maxX + 2.0) / 16.0));
        int n3 = MathHelper.floor((double)((axisAlignedBB.minZ - 2.0) / 16.0));
        int n4 = MathHelper.floor((double)((axisAlignedBB.maxZ + 2.0) / 16.0));
        for (int i = n; i <= n2; ++i) {
            for (int j = n3; j <= n4; ++j) {
                if (Trajectories.mc.world.getChunkProvider().getLoadedChunk(i, j) == null) continue;
                Trajectories.mc.world.getChunkFromChunkCoords(i, j).getEntitiesWithinAABBForEntity((Entity)Trajectories.mc.player, axisAlignedBB, arrayList, null);
            }
        }
        return arrayList;
    }
}

