//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 */
package me.snow.aclient.module.modules.movement;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import net.minecraft.init.Blocks;

public class IceSpeed
extends Module {
    private static /* synthetic */ IceSpeed INSTANCE;
    private final /* synthetic */ Setting<Float> speed;

    @Override
    public void onUpdate() {
        Blocks.ICE.slipperiness = this.speed.getValue().floatValue();
        Blocks.PACKED_ICE.slipperiness = this.speed.getValue().floatValue();
        Blocks.FROSTED_ICE.slipperiness = this.speed.getValue().floatValue();
    }

    public IceSpeed() {
        super("IceSpeed", "Speeds you up on ice.", Module.Category.MOVEMENT, false, false, false);
        this.speed = this.register(new Setting<Float>("Speed", Float.valueOf(0.4f), Float.valueOf(0.1f), Float.valueOf(1.5f)));
        INSTANCE = this;
    }

    public static IceSpeed getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new IceSpeed();
        }
        return INSTANCE;
    }

    @Override
    public void onDisable() {
        Blocks.ICE.slipperiness = 0.98f;
        Blocks.PACKED_ICE.slipperiness = 0.98f;
        Blocks.FROSTED_ICE.slipperiness = 0.98f;
    }

    static {
        INSTANCE = new IceSpeed();
    }
}

