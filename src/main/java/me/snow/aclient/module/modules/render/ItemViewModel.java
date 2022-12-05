//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.module.modules.render;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;

public class ItemViewModel
extends Module {
    public /* synthetic */ Setting<Integer> fov;
    public /* synthetic */ Setting<Integer> viewAlpha;
    public /* synthetic */ Setting<Float> rotationZ;
    public /* synthetic */ Setting<Boolean> changeswing;
    public /* synthetic */ Setting<Integer> swingspeed;
    public /* synthetic */ Setting<Float> offX;
    public /* synthetic */ Setting<Boolean> normalOffset;
    public /* synthetic */ Setting<Float> sizeZ;
    public /* synthetic */ Setting<Float> offset;
    public /* synthetic */ Setting<Float> sizeX;
    public static /* synthetic */ ItemViewModel INSTANCE;
    public /* synthetic */ Setting<Float> mainX;
    public /* synthetic */ Setting<Float> sizeY;
    public /* synthetic */ Setting<Float> rotationX;
    public /* synthetic */ Setting<Float> positionY;
    public /* synthetic */ Setting<Float> offY;
    public /* synthetic */ Setting<Boolean> oldA;
    public /* synthetic */ Setting<Float> mainY;
    /* synthetic */ float fovOld;
    public /* synthetic */ Setting<Float> positionZ;
    public /* synthetic */ Setting<Float> rotationY;
    public /* synthetic */ Setting<Float> positionX;

    @Override
    public void onDisable() {
        ItemViewModel.mc.gameSettings.fovSetting = this.fovOld;
    }

    public static ItemViewModel getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ItemViewModel();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    public ItemViewModel() {
        super("ItemViewModel", "Allows you to customize how items look in your hand", Module.Category.RENDER, false, false, false);
        this.fov = this.register(new Setting<Integer>("Fov", 130, 90, 180));
        this.normalOffset = this.register(new Setting<Boolean>("OffNormal", false));
        this.viewAlpha = this.register(new Setting<Integer>("Item Alpha", 255, 0, 255));
        this.offset = this.register(new Setting<Object>("Offset", Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(1.0f), object -> this.normalOffset.getValue()));
        this.offX = this.register(new Setting<Object>("OffX", Float.valueOf(0.0f), Float.valueOf(-1.0f), Float.valueOf(1.0f), object -> this.normalOffset.getValue() == false));
        this.offY = this.register(new Setting<Object>("OffY", Float.valueOf(0.0f), Float.valueOf(-1.0f), Float.valueOf(1.0f), object -> this.normalOffset.getValue() == false));
        this.mainX = this.register(new Setting<Float>("MainX", Float.valueOf(0.3f), Float.valueOf(-1.0f), Float.valueOf(1.0f)));
        this.mainY = this.register(new Setting<Float>("MainY", Float.valueOf(0.0f), Float.valueOf(-1.0f), Float.valueOf(1.0f)));
        this.sizeX = this.register(new Setting<Float>("SizeX", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(2.0f)));
        this.sizeY = this.register(new Setting<Float>("SizeY", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(2.0f)));
        this.sizeZ = this.register(new Setting<Float>("SizeZ", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(2.0f)));
        this.rotationX = this.register(new Setting<Float>("RotationX", Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(1.0f)));
        this.rotationY = this.register(new Setting<Float>("RotationY", Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(1.0f)));
        this.rotationZ = this.register(new Setting<Float>("RotationZ", Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(1.0f)));
        this.positionX = this.register(new Setting<Float>("PositionX", Float.valueOf(0.0f), Float.valueOf(-2.0f), Float.valueOf(2.0f)));
        this.positionY = this.register(new Setting<Float>("PositionY", Float.valueOf(0.0f), Float.valueOf(-2.0f), Float.valueOf(2.0f)));
        this.positionZ = this.register(new Setting<Float>("PositionZ", Float.valueOf(0.0f), Float.valueOf(-2.0f), Float.valueOf(2.0f)));
        this.oldA = this.register(new Setting<Boolean>("OldAnimation", false));
        this.changeswing = this.register(new Setting<Boolean>("SlowSwing", false));
        this.swingspeed = this.register(new Setting<Integer>("SwingDelay", Integer.valueOf(15), Integer.valueOf(1), Integer.valueOf(20), n -> this.changeswing.getValue()));
        this.setInstance();
    }

    @Override
    public void onUpdate() {
        if (this.normalOffset.getValue().booleanValue()) {
            ItemViewModel.mc.entityRenderer.itemRenderer.equippedProgressOffHand = this.offset.getValue().floatValue();
        }
        ItemViewModel.mc.gameSettings.fovSetting = this.fov.getValue().intValue();
        if (this.oldA.getValue().booleanValue() && (double)ItemViewModel.mc.entityRenderer.itemRenderer.prevEquippedProgressMainHand >= 0.9) {
            ItemViewModel.mc.entityRenderer.itemRenderer.equippedProgressMainHand = 1.0f;
            ItemViewModel.mc.entityRenderer.itemRenderer.itemStackMainHand = ItemViewModel.mc.player.getHeldItemMainhand();
        }
    }

    static {
        INSTANCE = new ItemViewModel();
    }

    @Override
    public void onEnable() {
        this.fovOld = ItemViewModel.mc.gameSettings.fovSetting;
    }
}

