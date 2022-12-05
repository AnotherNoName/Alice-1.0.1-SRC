/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.passive.EntityPig
 */
package me.snow.aclient.mixin.mixins;

import me.snow.aclient.module.modules.movement.EntitySpeed;
import net.minecraft.entity.passive.EntityPig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={EntityPig.class})
public class MixinEntityPig {
    @Inject(method={"canBeSteered"}, at={@At(value="HEAD")}, cancellable=true)
    public void canBeSteered(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (EntitySpeed.INSTANCE.isEnabled()) {
            callbackInfoReturnable.setReturnValue(true);
            callbackInfoReturnable.cancel();
        }
    }
}

