//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.client.event.RenderPlayerEvent$Pre
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.render;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Chams
extends Module {
    public /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Integer> Fgreen;
    public final /* synthetic */ Setting<Float> lineWidth;
    public /* synthetic */ Setting<Integer> red;
    public final /* synthetic */ Setting<Float> alpha;
    private static /* synthetic */ Chams INSTANCE;
    public /* synthetic */ Setting<Boolean> playerModel;
    public /* synthetic */ Setting<Boolean> solidawa;
    public /* synthetic */ Setting<Integer> Fred;
    public /* synthetic */ Setting<Boolean> colorsync;
    public /* synthetic */ Setting<Integer> Fblue;
    public /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Boolean> wireframeawa;

    @SubscribeEvent
    public void onRenderPlayerEvent(RenderPlayerEvent.Pre pre) {
        pre.getEntityPlayer().hurtTime = 0;
    }

    public Chams() {
        super("Chams", "Draws a wireframe esp around other players.", Module.Category.RENDER, false, false, false);
        this.colorsync = this.register(new Setting<Boolean>("ColorSync", Boolean.FALSE));
        this.red = this.register(new Setting<Integer>("Red", 168, 0, 255));
        this.green = this.register(new Setting<Integer>("Green", 0, 0, 255));
        this.blue = this.register(new Setting<Integer>("Blue", 232, 0, 255));
        this.alpha = this.register(new Setting<Float>("PAlpha", Float.valueOf(60.0f), Float.valueOf(0.1f), Float.valueOf(255.0f)));
        this.lineWidth = this.register(new Setting<Float>("PLineWidth", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(3.0f)));
        this.solidawa = this.register(new Setting<Boolean>("Solid", Boolean.FALSE));
        this.wireframeawa = this.register(new Setting<Boolean>("WireFrame", Boolean.FALSE));
        this.playerModel = this.register(new Setting<Boolean>("PlayerModel", Boolean.FALSE, bl -> this.wireframeawa.getValue()));
        this.Fred = this.register(new Setting<Integer>("FriendRed", 0, 0, 255));
        this.Fgreen = this.register(new Setting<Integer>("FriendPGreen", 191, 0, 255));
        this.Fblue = this.register(new Setting<Integer>("FriendPBlue", 255, 0, 255));
        this.setInstance();
    }

    private void setInstance() {
        INSTANCE = this;
    }

    public static Chams getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Chams();
        }
        return INSTANCE;
    }

    static {
        INSTANCE = new Chams();
    }
}

