//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package me.snow.aclient.module.modules.render;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import net.minecraft.entity.player.EntityPlayer;

public class Animations
extends Module {
    public static /* synthetic */ Setting<Integer> swingDelay;
    public static /* synthetic */ Setting<Boolean> changeSwing;
    public static /* synthetic */ Setting<Float> offhand;
    public static /* synthetic */ Setting<Boolean> changeMainhand;
    public static /* synthetic */ Setting<Boolean> changeOffhand;
    public static /* synthetic */ Setting<Boolean> playersDisableAnimations;
    public static /* synthetic */ Setting<AnimationVersion> swingAnimationVersion;
    public static /* synthetic */ Setting<Float> mainhand;

    public Animations() {
        super("Animations", "Allows you to change animations in your hand", Module.Category.RENDER, true, false, false);
        swingAnimationVersion = this.register(new Setting<AnimationVersion>("Version", AnimationVersion.OneDotEight));
        playersDisableAnimations = this.register(new Setting<Boolean>("Disable Animations", false));
        changeMainhand = this.register(new Setting<Boolean>("Change Mainhand", true));
        mainhand = this.register(new Setting<Float>("Mainhand", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(4.7509747f) ^ 0x7F1807FC)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(1.63819E38f) ^ 0x7EF67CC9)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(30.789412f) ^ 0x7E7650B7))));
        changeOffhand = this.register(new Setting<Boolean>("Change Offhand", true));
        offhand = this.register(new Setting<Float>("Offhand", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(15.8065405f) ^ 0x7EFCE797)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(3.3688825E38f) ^ 0x7F7D7251)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(7.3325067f) ^ 0x7F6AA3E5))));
        changeSwing = this.register(new Setting<Boolean>("Swing Speed", false));
        swingDelay = this.register(new Setting<Integer>("Swing Delay", 6, 1, 20));
    }

    @Override
    public void onUpdate() {
        if (playersDisableAnimations.getValue().booleanValue()) {
            for (EntityPlayer entityPlayer : Animations.mc.world.playerEntities) {
                entityPlayer.limbSwing = Float.intBitsToFloat(Float.floatToIntBits(1.8755627E38f) ^ 0x7F0D1A06);
                entityPlayer.limbSwingAmount = Float.intBitsToFloat(Float.floatToIntBits(6.103741E37f) ^ 0x7E37AD83);
                entityPlayer.prevLimbSwingAmount = Float.intBitsToFloat(Float.floatToIntBits(4.8253957E37f) ^ 0x7E11357F);
            }
        }
        if (changeMainhand.getValue().booleanValue() && Animations.mc.entityRenderer.itemRenderer.equippedProgressMainHand != mainhand.getValue().floatValue()) {
            Animations.mc.entityRenderer.itemRenderer.equippedProgressMainHand = mainhand.getValue().floatValue();
            Animations.mc.entityRenderer.itemRenderer.itemStackMainHand = Animations.mc.player.getHeldItemMainhand();
        }
        if (changeOffhand.getValue().booleanValue() && Animations.mc.entityRenderer.itemRenderer.equippedProgressOffHand != offhand.getValue().floatValue()) {
            Animations.mc.entityRenderer.itemRenderer.equippedProgressOffHand = offhand.getValue().floatValue();
            Animations.mc.entityRenderer.itemRenderer.itemStackOffHand = Animations.mc.player.getHeldItemOffhand();
        }
        if (swingAnimationVersion.getValue() == AnimationVersion.OneDotEight && (double)Animations.mc.entityRenderer.itemRenderer.prevEquippedProgressMainHand >= 0.9) {
            Animations.mc.entityRenderer.itemRenderer.equippedProgressMainHand = 1.0f;
            Animations.mc.entityRenderer.itemRenderer.itemStackMainHand = Animations.mc.player.getHeldItemMainhand();
        }
    }

    public static enum AnimationVersion {
        OneDotEight,
        OneDotTwelve;

    }
}

