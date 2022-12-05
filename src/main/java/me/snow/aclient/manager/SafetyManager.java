//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.BlockPos
 */
package me.snow.aclient.manager;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import me.snow.aclient.module.Feature;
import me.snow.aclient.module.modules.client.Global;
import me.snow.aclient.module.modules.combat.autocrystal.AutoCrystal;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.DamageUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class SafetyManager
extends Feature
implements Runnable {
    private final /* synthetic */ AtomicBoolean SAFE;
    private /* synthetic */ ScheduledExecutorService service;
    private final /* synthetic */ Timer syncTimer;

    @Override
    public void run() {
        if (AutoCrystal.getInstance().isOff() || AutoCrystal.getInstance().threadMode.getValue() == AutoCrystal.ThreadMode.NONE) {
            this.doSafetyCheck();
        }
    }

    public boolean isSafe() {
        return this.SAFE.get();
    }

    public void doSafetyCheck() {
        if (!SafetyManager.fullNullCheck()) {
            EntityPlayer entityPlayer;
            boolean bl = true;
            EntityPlayer entityPlayer2 = entityPlayer = Global.getInstance().safety.getValue() != false ? EntityUtil.getClosestEnemy(18.0) : null;
            if (Global.getInstance().safety.getValue().booleanValue() && entityPlayer == null) {
                this.SAFE.set(true);
                return;
            }
            ArrayList arrayList = new ArrayList(SafetyManager.mc.world.loadedEntityList);
            for (Entity entity : arrayList) {
                if (!(entity instanceof EntityEnderCrystal) || !((double)DamageUtil.calculateDamage(entity, (Entity)SafetyManager.mc.player) > 4.0) || entityPlayer != null && !(entityPlayer.getDistanceSqToEntity(entity) < 40.0)) continue;
                bl = false;
                break;
            }
            if (bl) {
                for (BlockPos blockPos : BlockUtil.possiblePlacePositions(4.0f, false, Global.getInstance().oneDot15.getValue())) {
                    if (!((double)DamageUtil.calculateDamage(blockPos, (Entity)SafetyManager.mc.player) > 4.0) || entityPlayer != null && !(entityPlayer.getDistanceSq(blockPos) < 40.0)) continue;
                    bl = false;
                    break;
                }
            }
            this.SAFE.set(bl);
        }
    }

    public String getSafetyString() {
        if (this.SAFE.get()) {
            return "\u00a7aSecure";
        }
        return "\u00a7cUnsafe";
    }

    public ScheduledExecutorService getService() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(this, 0L, Global.getInstance().safetyCheck.getValue().intValue(), TimeUnit.MILLISECONDS);
        return scheduledExecutorService;
    }

    public SafetyManager() {
        this.syncTimer = new Timer();
        this.SAFE = new AtomicBoolean(false);
    }

    public void onUpdate() {
        this.run();
    }
}

