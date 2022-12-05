//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.item.EntityExpBottle
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.monster.EntityIronGolem
 *  net.minecraft.entity.passive.EntityAmbientCreature
 *  net.minecraft.entity.passive.EntitySquid
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.module.modules.render;

import java.util.stream.Collectors;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.LuigiTessellator;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class CsgoESP
extends Module {
    private final /* synthetic */ Setting<Integer> Blue;
    private final /* synthetic */ Setting<Integer> Alpha;
    private final /* synthetic */ Setting<Boolean> crystals;
    private final /* synthetic */ Setting<Integer> PRed;
    private final /* synthetic */ Setting<Boolean> orbs;
    private final /* synthetic */ Setting<Integer> Green;
    private final /* synthetic */ Setting<Integer> PAlpha;
    private final /* synthetic */ Setting<Boolean> items;
    private final /* synthetic */ Setting<Boolean> monsters;
    private final /* synthetic */ Setting<Integer> PGreen;
    private final /* synthetic */ Setting<Boolean> players;
    private final /* synthetic */ Setting<Boolean> xpBottles;
    private final /* synthetic */ Setting<Integer> Red;
    private final /* synthetic */ Setting<Boolean> animals;
    private final /* synthetic */ Setting<Integer> LineWidth;
    private final /* synthetic */ Setting<Integer> PBlue;

    public CsgoESP() {
        super("CsgoESP", "Renders a nice ESP.", Module.Category.RENDER, false, false, false);
        this.players = this.register(new Setting<Boolean>("Players", true));
        this.animals = this.register(new Setting<Boolean>("Animals", true));
        this.monsters = this.register(new Setting<Boolean>("Monsters", false));
        this.items = this.register(new Setting<Boolean>("Items", false));
        this.xpBottles = this.register(new Setting<Boolean>("XpBottles", false));
        this.crystals = this.register(new Setting<Boolean>("Crystals", true));
        this.orbs = this.register(new Setting<Boolean>("XpOrbs", false));
        this.LineWidth = this.register(new Setting<Integer>("LineWidth", 1, 1, 8));
        this.PRed = this.register(new Setting<Integer>("Player Red", 255, 0, 255));
        this.PGreen = this.register(new Setting<Integer>("Player Green", 255, 0, 255));
        this.PBlue = this.register(new Setting<Integer>("Player Blue", 255, 0, 255));
        this.PAlpha = this.register(new Setting<Integer>("Player Alpha", 255, 0, 255));
        this.Red = this.register(new Setting<Integer>("Other Red", 255, 0, 255));
        this.Green = this.register(new Setting<Integer>("Other Green", 255, 0, 255));
        this.Blue = this.register(new Setting<Integer>("Other Blue", 255, 0, 255));
        this.Alpha = this.register(new Setting<Integer>("Other Alpha", 255, 0, 255));
    }

    public static boolean isMonster(Entity entity) {
        return entity.isCreatureType(EnumCreatureType.MONSTER, false);
    }

    public static boolean isanimals(Entity entity) {
        return (!(entity instanceof EntityWolf) || !((EntityWolf)entity).isAngry()) && (entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntitySquid || entity instanceof EntityIronGolem && ((EntityIronGolem)entity).getAITarget() == null);
    }

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        if (CsgoESP.mc.getRenderManager().options == null) {
            return;
        }
        boolean bl = CsgoESP.mc.getRenderManager().options.thirdPersonView == 2;
        float f = CsgoESP.mc.getRenderManager().playerViewY;
        for (Entity entity2 : CsgoESP.mc.world.loadedEntityList.stream().filter(entity -> CsgoESP.mc.player != entity).collect(Collectors.toList())) {
            LuigiTessellator.prepareGL();
            GlStateManager.pushMatrix();
            Vec3d vec3d = EntityUtil.getInterpolatedPos(entity2, render3DEvent.getPartialTicks());
            GlStateManager.translate((double)(vec3d.xCoord - CsgoESP.mc.getRenderManager().renderPosX), (double)(vec3d.yCoord - CsgoESP.mc.getRenderManager().renderPosY), (double)(vec3d.zCoord - CsgoESP.mc.getRenderManager().renderPosZ));
            GlStateManager.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)(-f), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)(bl ? -1 : 1), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
            GL11.glLineWidth((float)((float)this.LineWidth.getValue().intValue() * 6.0f / CsgoESP.mc.player.getDistanceToEntity(entity2)));
            GL11.glEnable((int)2848);
            if (entity2 instanceof EntityPlayer && this.players.getValue().booleanValue()) {
                if (AliceMain.friendManager.isFriend(entity2.getName())) {
                    GL11.glColor4f((float)0.0f, (float)1.0f, (float)1.0f, (float)0.7f);
                } else {
                    GL11.glColor4f((float)((float)this.PRed.getValue().intValue() / 255.0f), (float)(this.PGreen.getValue().floatValue() / 255.0f), (float)(this.PBlue.getValue().floatValue() / 255.0f), (float)(this.PAlpha.getValue().floatValue() / 255.0f));
                }
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(-entity2.width), (double)0.0);
                GL11.glVertex2d((double)(-entity2.width), (double)(entity2.height / 3.0f));
                GL11.glVertex2d((double)(-entity2.width), (double)0.0);
                GL11.glVertex2d((double)(-entity2.width / 3.0f * 2.0f), (double)0.0);
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(-entity2.width), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width / 3.0f * 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width), (double)(entity2.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)entity2.width, (double)entity2.height);
                GL11.glVertex2d((double)(entity2.width / 3.0f * 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)entity2.width, (double)entity2.height);
                GL11.glVertex2d((double)entity2.width, (double)(entity2.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)entity2.width, (double)0.0);
                GL11.glVertex2d((double)(entity2.width / 3.0f * 2.0f), (double)0.0);
                GL11.glVertex2d((double)entity2.width, (double)0.0);
                GL11.glVertex2d((double)entity2.width, (double)(entity2.height / 3.0f));
                GL11.glEnd();
            }
            GL11.glColor4f((float)((float)this.Red.getValue().intValue() / 255.0f), (float)((float)this.Green.getValue().intValue() / 255.0f), (float)((float)this.Blue.getValue().intValue() / 255.0f), (float)((float)this.Alpha.getValue().intValue() / 255.0f));
            if (CsgoESP.isanimals(entity2) && this.animals.getValue().booleanValue()) {
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(-entity2.width), (double)0.0);
                GL11.glVertex2d((double)(-entity2.width), (double)(entity2.height / 3.0f));
                GL11.glVertex2d((double)(-entity2.width), (double)0.0);
                GL11.glVertex2d((double)(-entity2.width / 3.0f * 2.0f), (double)0.0);
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(-entity2.width), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width / 3.0f * 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width), (double)(entity2.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)entity2.width, (double)entity2.height);
                GL11.glVertex2d((double)(entity2.width / 3.0f * 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)entity2.width, (double)entity2.height);
                GL11.glVertex2d((double)entity2.width, (double)(entity2.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)entity2.width, (double)0.0);
                GL11.glVertex2d((double)(entity2.width / 3.0f * 2.0f), (double)0.0);
                GL11.glVertex2d((double)entity2.width, (double)0.0);
                GL11.glVertex2d((double)entity2.width, (double)(entity2.height / 3.0f));
                GL11.glEnd();
            }
            if (CsgoESP.isMonster(entity2) && this.monsters.getValue().booleanValue()) {
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(-entity2.width), (double)0.0);
                GL11.glVertex2d((double)(-entity2.width), (double)(entity2.height / 3.0f));
                GL11.glVertex2d((double)(-entity2.width), (double)0.0);
                GL11.glVertex2d((double)(-entity2.width / 3.0f * 2.0f), (double)0.0);
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(-entity2.width), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width / 3.0f * 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width), (double)(entity2.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)entity2.width, (double)entity2.height);
                GL11.glVertex2d((double)(entity2.width / 3.0f * 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)entity2.width, (double)entity2.height);
                GL11.glVertex2d((double)entity2.width, (double)(entity2.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)entity2.width, (double)0.0);
                GL11.glVertex2d((double)(entity2.width / 3.0f * 2.0f), (double)0.0);
                GL11.glVertex2d((double)entity2.width, (double)0.0);
                GL11.glVertex2d((double)entity2.width, (double)(entity2.height / 3.0f));
                GL11.glEnd();
            }
            if (entity2 instanceof EntityItem && this.items.getValue().booleanValue()) {
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(-entity2.width), (double)0.0);
                GL11.glVertex2d((double)(-entity2.width), (double)(entity2.height / 3.0f));
                GL11.glVertex2d((double)(-entity2.width), (double)0.0);
                GL11.glVertex2d((double)(-entity2.width / 3.0f * 2.0f), (double)0.0);
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(-entity2.width), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width / 3.0f * 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width), (double)(entity2.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)entity2.width, (double)entity2.height);
                GL11.glVertex2d((double)(entity2.width / 3.0f * 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)entity2.width, (double)entity2.height);
                GL11.glVertex2d((double)entity2.width, (double)(entity2.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)entity2.width, (double)0.0);
                GL11.glVertex2d((double)(entity2.width / 3.0f * 2.0f), (double)0.0);
                GL11.glVertex2d((double)entity2.width, (double)0.0);
                GL11.glVertex2d((double)entity2.width, (double)(entity2.height / 3.0f));
                GL11.glEnd();
            }
            if (entity2 instanceof EntityExpBottle && this.xpBottles.getValue().booleanValue()) {
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(-entity2.width), (double)0.0);
                GL11.glVertex2d((double)(-entity2.width), (double)(entity2.height / 3.0f));
                GL11.glVertex2d((double)(-entity2.width), (double)0.0);
                GL11.glVertex2d((double)(-entity2.width / 3.0f * 2.0f), (double)0.0);
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(-entity2.width), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width / 3.0f * 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width), (double)(entity2.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)entity2.width, (double)entity2.height);
                GL11.glVertex2d((double)(entity2.width / 3.0f * 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)entity2.width, (double)entity2.height);
                GL11.glVertex2d((double)entity2.width, (double)(entity2.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)entity2.width, (double)0.0);
                GL11.glVertex2d((double)(entity2.width / 3.0f * 2.0f), (double)0.0);
                GL11.glVertex2d((double)entity2.width, (double)0.0);
                GL11.glVertex2d((double)entity2.width, (double)(entity2.height / 3.0f));
                GL11.glEnd();
            }
            if (entity2 instanceof EntityEnderCrystal && this.crystals.getValue().booleanValue()) {
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(-entity2.width / 2.0f), (double)0.0);
                GL11.glVertex2d((double)(-entity2.width / 2.0f), (double)(entity2.height / 3.0f));
                GL11.glVertex2d((double)(-entity2.width / 2.0f), (double)0.0);
                GL11.glVertex2d((double)(-entity2.width / 3.0f * 2.0f / 2.0f), (double)0.0);
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(-entity2.width / 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width / 3.0f * 2.0f / 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width / 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width / 2.0f), (double)(entity2.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(entity2.width / 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)(entity2.width / 3.0f * 2.0f / 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)(entity2.width / 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)(entity2.width / 2.0f), (double)(entity2.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(entity2.width / 2.0f), (double)0.0);
                GL11.glVertex2d((double)(entity2.width / 3.0f * 2.0f / 2.0f), (double)0.0);
                GL11.glVertex2d((double)(entity2.width / 2.0f), (double)0.0);
                GL11.glVertex2d((double)(entity2.width / 2.0f), (double)(entity2.height / 3.0f));
                GL11.glEnd();
            }
            if (entity2 instanceof EntityXPOrb && this.orbs.getValue().booleanValue()) {
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(-entity2.width / 2.0f), (double)0.0);
                GL11.glVertex2d((double)(-entity2.width / 2.0f), (double)(entity2.height / 3.0f));
                GL11.glVertex2d((double)(-entity2.width / 2.0f), (double)0.0);
                GL11.glVertex2d((double)(-entity2.width / 3.0f * 2.0f / 2.0f), (double)0.0);
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(-entity2.width / 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width / 3.0f * 2.0f / 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width / 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)(-entity2.width / 2.0f), (double)(entity2.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(entity2.width / 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)(entity2.width / 3.0f * 2.0f / 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)(entity2.width / 2.0f), (double)entity2.height);
                GL11.glVertex2d((double)(entity2.width / 2.0f), (double)(entity2.height / 3.0f * 2.0f));
                GL11.glEnd();
                GL11.glBegin((int)2);
                GL11.glVertex2d((double)(entity2.width / 2.0f), (double)0.0);
                GL11.glVertex2d((double)(entity2.width / 3.0f * 2.0f / 2.0f), (double)0.0);
                GL11.glVertex2d((double)(entity2.width / 2.0f), (double)0.0);
                GL11.glVertex2d((double)(entity2.width / 2.0f), (double)(entity2.height / 3.0f));
                GL11.glEnd();
            }
            LuigiTessellator.releaseGL();
            GlStateManager.popMatrix();
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }
}

