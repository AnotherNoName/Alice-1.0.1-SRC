/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.render;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PerspectiveEvent;
import me.snow.aclient.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Aspect
extends Module {
    public /* synthetic */ Setting<Float> aspect;

    public Aspect() {
        super("Aspect", "Cool.", Module.Category.RENDER, true, false, false);
        this.aspect = this.register(new Setting<Float>("Alpha", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(5.0f)));
    }

    @SubscribeEvent
    public void onPerspectiveEvent(PerspectiveEvent perspectiveEvent) {
        perspectiveEvent.setAspect(this.aspect.getValue().floatValue());
    }
}

