/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.item.EntityEnderCrystal
 */
package me.snow.aclient.module.modules.hidden;

import me.snow.aclient.module.modules.hidden.CrystalAura;
import net.minecraft.entity.item.EntityEnderCrystal;

final class Threads
extends Thread {
    /* synthetic */ EntityEnderCrystal bestCrystal;

    @Override
    public void run() {
        CrystalAura.INSTANCE.staticEnderCrystal = this.bestCrystal = CrystalAura.INSTANCE.getBestCrystal();
    }
}

