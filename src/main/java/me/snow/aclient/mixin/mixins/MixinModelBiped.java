/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.monster.EntityPigZombie
 */
package me.snow.aclient.mixin.mixins;

import me.snow.aclient.module.modules.render.NoRender;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityPigZombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ModelBiped.class})
public class MixinModelBiped {
    @Inject(method={"render"}, at={@At(value="HEAD")}, cancellable=true)
    public void render(Entity entity, float f, float f2, float f3, float f4, float f5, float f6, CallbackInfo callbackInfo) {
        if (entity instanceof EntityPigZombie && NoRender.getInstance().pigmen.getValue().booleanValue()) {
            callbackInfo.cancel();
        }
    }
}

