//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.movement;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.MoveEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.ca.sc.PlayerUtilSC;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FastSwim
extends Module {
    public /* synthetic */ Setting<Boolean> lava;
    public /* synthetic */ Setting<Double> wspeed;
    public /* synthetic */ Setting<Boolean> water;
    public /* synthetic */ Setting<Double> lspeed;
    public /* synthetic */ Setting<Boolean> antiKick;

    public FastSwim() {
        super("FastSwim", "Swim fast", Module.Category.MOVEMENT, true, false, false);
        this.wspeed = this.register(new Setting<Double>("WaterSpeed", 1.5, 1.0, 10.0));
        this.lspeed = this.register(new Setting<Double>("LavaSpeed", 1.5, 1.0, 10.0));
        this.water = this.register(new Setting<Boolean>("Water", true));
        this.lava = this.register(new Setting<Boolean>("Lava", true));
        this.antiKick = this.register(new Setting<Boolean>("AntiKick", false));
    }

    @SubscribeEvent
    public void onMove(MoveEvent moveEvent) {
        if (!PlayerUtilSC.isPlayerMoving()) {
            return;
        }
        if (FastSwim.mc.player.isInLava() && !FastSwim.mc.player.onGround && this.lava.getValue().booleanValue()) {
            double[] arrd = FastSwim.mc.player.ticksExisted % 4 == 0 && this.antiKick.getValue() != false ? PlayerUtilSC.directionSpeed(this.lspeed.getValue() / 40.0) : PlayerUtilSC.directionSpeed(this.lspeed.getValue() / 10.0);
            moveEvent.setX(arrd[0]);
            moveEvent.setZ(arrd[1]);
        } else if (FastSwim.mc.player.isInWater() && !FastSwim.mc.player.onGround && this.water.getValue().booleanValue()) {
            double[] arrd = FastSwim.mc.player.ticksExisted % 4 == 0 && this.antiKick.getValue() != false ? PlayerUtilSC.directionSpeed(this.wspeed.getValue() / 40.0) : PlayerUtilSC.directionSpeed(this.wspeed.getValue() / 10.0);
            moveEvent.setX(arrd[0]);
            moveEvent.setZ(arrd[1]);
        }
    }
}

