//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 */
package me.snow.aclient.module.modules.render;

import java.awt.Color;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.RenderUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class SurroundRender
extends Module {
    public /* synthetic */ EntityPlayer target;
    private final /* synthetic */ Setting<Integer> range;

    private void surroundRender(Vec3d vec3d, double d, double d2, double d3, boolean bl) {
        BlockPos blockPos = new BlockPos(vec3d).add(d, d2, d3);
        if (SurroundRender.mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR) {
            return;
        }
        if (SurroundRender.mc.world.getBlockState(blockPos).getBlock() == Blocks.FIRE) {
            return;
        }
        if (bl) {
            RenderUtil.drawBoxESP(blockPos, new Color(255, 0, 0), false, new Color(255, 0, 0), 1.0f, false, true, 42, true);
            return;
        }
        RenderUtil.drawBoxESP(blockPos, new Color(0, 0, 255), false, new Color(0, 0, 255), 1.0f, false, true, 42, true);
    }

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        if (SurroundRender.fullNullCheck()) {
            return;
        }
        this.target = this.getTarget(this.range.getValue().intValue());
        this.surroundRender();
    }

    private EntityPlayer getTarget(double d) {
        EntityPlayer entityPlayer = null;
        double d2 = d;
        for (EntityPlayer entityPlayer2 : SurroundRender.mc.world.playerEntities) {
            if (EntityUtil.isntValid((Entity)entityPlayer2, d) || !EntityUtil.isInHole((Entity)entityPlayer2)) continue;
            if (entityPlayer == null) {
                entityPlayer = entityPlayer2;
                d2 = SurroundRender.mc.player.getDistanceSqToEntity((Entity)entityPlayer2);
                continue;
            }
            if (!(SurroundRender.mc.player.getDistanceSqToEntity((Entity)entityPlayer2) < d2)) continue;
            entityPlayer = entityPlayer2;
            d2 = SurroundRender.mc.player.getDistanceSqToEntity((Entity)entityPlayer2);
        }
        return entityPlayer;
    }

    public SurroundRender() {
        super("CityESP", "CityESP", Module.Category.RENDER, true, false, false);
        this.range = this.register(new Setting<Integer>("Range", 5, 1, 10));
    }

    private void surroundRender() {
        if (this.target == null) {
            return;
        }
        Vec3d vec3d = this.target.getPositionVector();
        if (SurroundRender.mc.world.getBlockState(new BlockPos(vec3d)).getBlock() == Blocks.OBSIDIAN || SurroundRender.mc.world.getBlockState(new BlockPos(vec3d)).getBlock() == Blocks.ENDER_CHEST) {
            RenderUtil.drawBoxESP(new BlockPos(vec3d), new Color(255, 255, 0), false, new Color(255, 255, 0), 1.0f, false, true, 42, true);
        }
        if (EntityUtil.getSurroundWeakness(vec3d, -1, 1)) {
            this.surroundRender(vec3d, -1.0, 0.0, 0.0, true);
        }
        if (EntityUtil.getSurroundWeakness(vec3d, -1, 2)) {
            this.surroundRender(vec3d, 1.0, 0.0, 0.0, true);
        }
        if (EntityUtil.getSurroundWeakness(vec3d, -1, 3)) {
            this.surroundRender(vec3d, 0.0, 0.0, -1.0, true);
        }
        if (EntityUtil.getSurroundWeakness(vec3d, -1, 4)) {
            this.surroundRender(vec3d, 0.0, 0.0, 1.0, true);
        }
        if (EntityUtil.getSurroundWeakness(vec3d, -1, 5)) {
            this.surroundRender(vec3d, -1.0, 0.0, 0.0, false);
        }
        if (EntityUtil.getSurroundWeakness(vec3d, -1, 6)) {
            this.surroundRender(vec3d, 1.0, 0.0, 0.0, false);
        }
        if (EntityUtil.getSurroundWeakness(vec3d, -1, 7)) {
            this.surroundRender(vec3d, 0.0, 0.0, -1.0, false);
        }
        if (!EntityUtil.getSurroundWeakness(vec3d, -1, 8)) {
            return;
        }
        this.surroundRender(vec3d, 0.0, 0.0, 1.0, false);
    }
}

