/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package me.snow.aclient.mixin.mixins;

import me.snow.aclient.AliceMain;
import me.snow.aclient.event.events.ElytraEvent;
import me.snow.aclient.event.events.HandleLiquidJumpEvent;
import me.snow.aclient.module.modules.render.Animations;
import me.snow.aclient.module.modules.render.HandModifier;
import me.snow.aclient.module.modules.render.ItemViewModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={EntityLivingBase.class})
public abstract class MixinEntityLivingBase
extends Entity {
    public MixinEntityLivingBase(World world) {
        super(world);
    }

    @Inject(method={"travel"}, at={@At(value="HEAD")}, cancellable=true)
    public void onTravel(float f, float f2, float f3, CallbackInfo callbackInfo) {
        ElytraEvent elytraEvent = new ElytraEvent(this);
        MinecraftForge.EVENT_BUS.post((Event)elytraEvent);
        if (elytraEvent.isCanceled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"getArmSwingAnimationEnd"}, at={@At(value="HEAD")}, cancellable=true)
    private void getArmSwingAnimationEnd(CallbackInfoReturnable<Integer> callbackInfoReturnable) {
        if (AliceMain.moduleManager.isModuleEnabled(HandModifier.class) && HandModifier.getINSTANCE().slowSwing.getValue().booleanValue()) {
            callbackInfoReturnable.setReturnValue(15);
        }
        if (AliceMain.moduleManager.getModuleByClass(ItemViewModel.class).isEnabled() && ItemViewModel.getINSTANCE().changeswing.getValue().booleanValue()) {
            callbackInfoReturnable.setReturnValue((int)ItemViewModel.getINSTANCE().swingspeed.getValue());
        }
        if (AliceMain.moduleManager.getModuleByClass(Animations.class).isEnabled() && Animations.changeSwing.getValue().booleanValue()) {
            callbackInfoReturnable.setReturnValue((int)Animations.swingDelay.getValue());
        }
    }

    @Inject(method={"handleJumpWater"}, at={@At(value="HEAD")}, cancellable=true)
    private void handleJumpWater(CallbackInfo callbackInfo) {
        HandleLiquidJumpEvent handleLiquidJumpEvent = new HandleLiquidJumpEvent();
        if (handleLiquidJumpEvent.isCanceled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"handleJumpLava"}, at={@At(value="HEAD")}, cancellable=true)
    private void handleJumpLava(CallbackInfo callbackInfo) {
        HandleLiquidJumpEvent handleLiquidJumpEvent = new HandleLiquidJumpEvent();
        if (handleLiquidJumpEvent.isCanceled()) {
            callbackInfo.cancel();
        }
    }
}

