//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 */
package me.snow.aclient.module.modules.player;

import java.util.Objects;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.combat.CevBreaker;
import me.snow.aclient.module.modules.combat.HeadCrystal;
import me.snow.aclient.module.modules.player.InstantMine;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.InventoryUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Automine
extends Module {
    private final /* synthetic */ Setting<Boolean> disable;
    public /* synthetic */ Setting<Float> TargetRRRRange;
    public /* synthetic */ EntityPlayer target;

    private void surroundMine(Vec3d vec3d, double d, double d2, double d3) {
        BlockPos blockPos = new BlockPos(vec3d).add(d, d2, d3);
        if (InstantMine.getInstance().isOff()) {
            InstantMine.getInstance().enable();
            return;
        }
        if (!InstantMine.getInstance().isOn()) {
            return;
        }
        if (InstantMine.breakPos != null) {
            if (InstantMine.breakPos.equals((Object)blockPos)) {
                return;
            }
            if (InstantMine.breakPos.equals((Object)new BlockPos(this.target.posX, this.target.posY, this.target.posZ)) && Automine.mc.world.getBlockState(new BlockPos(this.target.posX, this.target.posY, this.target.posZ)).getBlock() != Blocks.AIR) {
                return;
            }
        }
        Automine.mc.playerController.onPlayerDamageBlock(blockPos, BlockUtil.getRayTraceFacing(blockPos));
    }

    private void surroundMine() {
        if (this.target == null) {
            return;
        }
        Vec3d vec3d = this.target.getPositionVector();
        if (EntityUtil.getSurroundWeakness(vec3d, 1, -1)) {
            this.surroundMine(vec3d, -1.0, 0.0, 0.0);
            return;
        }
        if (EntityUtil.getSurroundWeakness(vec3d, 2, -1)) {
            this.surroundMine(vec3d, 1.0, 0.0, 0.0);
            return;
        }
        if (EntityUtil.getSurroundWeakness(vec3d, 3, -1)) {
            this.surroundMine(vec3d, 0.0, 0.0, -1.0);
            return;
        }
        if (EntityUtil.getSurroundWeakness(vec3d, 4, -1)) {
            this.surroundMine(vec3d, 0.0, 0.0, 1.0);
            return;
        }
        if (EntityUtil.getSurroundWeakness(vec3d, 5, -1)) {
            this.surroundMine(vec3d, -1.0, 0.0, 0.0);
            return;
        }
        if (EntityUtil.getSurroundWeakness(vec3d, 6, -1)) {
            this.surroundMine(vec3d, 1.0, 0.0, 0.0);
            return;
        }
        if (EntityUtil.getSurroundWeakness(vec3d, 7, -1)) {
            this.surroundMine(vec3d, 0.0, 0.0, -1.0);
            return;
        }
        if (!EntityUtil.getSurroundWeakness(vec3d, 8, -1)) {
            return;
        }
        this.surroundMine(vec3d, 0.0, 0.0, 1.0);
    }

    @Override
    public void onTick() {
        if (Automine.fullNullCheck()) {
            return;
        }
        if (Objects.requireNonNull(AliceMain.moduleManager.getModuleByClass(CevBreaker.class)).isEnabled()) {
            return;
        }
        if (Objects.requireNonNull(AliceMain.moduleManager.getModuleByClass(HeadCrystal.class).isEnabled()).booleanValue()) {
            return;
        }
        if (this.disable.getValue().booleanValue()) {
            this.disable();
        }
        if (InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE) == -1) {
            return;
        }
        this.target = this.getTarget(this.TargetRRRRange.getValue().floatValue());
        this.surroundMine();
    }

    public Automine() {
        super("AutoMine", "AutoMine awa", Module.Category.PLAYER, false, false, false);
        this.disable = this.register(new Setting<Boolean>("AutoDisable", true));
        this.TargetRRRRange = this.register(new Setting<Float>("TargetRange", Float.valueOf(6.0f), Float.valueOf(3.0f), Float.valueOf(8.0f)));
    }

    private EntityPlayer getTarget(double d) {
        EntityPlayer entityPlayer = null;
        double d2 = d;
        for (EntityPlayer entityPlayer2 : Automine.mc.world.playerEntities) {
            if (EntityUtil.isntValid((Entity)entityPlayer2, d) || !EntityUtil.isInHole((Entity)entityPlayer2) && !AliceMain.friendManager.isFriend(entityPlayer2.getName())) continue;
            if (entityPlayer == null) {
                entityPlayer = entityPlayer2;
                d2 = Automine.mc.player.getDistanceSqToEntity((Entity)entityPlayer2);
                continue;
            }
            if (!(Automine.mc.player.getDistanceSqToEntity((Entity)entityPlayer2) < d2)) continue;
            entityPlayer = entityPlayer2;
            d2 = Automine.mc.player.getDistanceSqToEntity((Entity)entityPlayer2);
        }
        return entityPlayer;
    }

    @Override
    public String getDisplayInfo() {
        return "Combat";
    }
}

