//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderItem
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package me.snow.aclient.mixin.mixins;

import java.awt.Color;
import me.snow.aclient.AliceMain;
import me.snow.aclient.event.events.RenderItemEvent;
import me.snow.aclient.module.modules.render.ItemViewModel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={RenderItem.class})
public class MixinRenderItem {
    @Shadow
    private void func_191967_a(IBakedModel iBakedModel, int n, ItemStack itemStack) {
    }

    @Redirect(method={"renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/renderer/RenderItem;renderModel(Lnet/minecraft/client/renderer/block/model/IBakedModel;Lnet/minecraft/item/ItemStack;)V"))
    private void yes(RenderItem renderItem, IBakedModel iBakedModel, ItemStack itemStack) {
        if (AliceMain.moduleManager.isModuleEnabled(ItemViewModel.class)) {
            this.func_191967_a(iBakedModel, new Color(1.0f, 1.0f, 1.0f, (float)ItemViewModel.getINSTANCE().viewAlpha.getValue().intValue() / 255.0f).getRGB(), itemStack);
        } else {
            this.func_191967_a(iBakedModel, -1, itemStack);
        }
    }

    @Redirect(method={"renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/renderer/GlStateManager;color(FFFF)V"))
    private void renderItem(float f, float f2, float f3, float f4) {
        if (AliceMain.moduleManager.isModuleEnabled(ItemViewModel.class)) {
            GlStateManager.color((float)f, (float)f2, (float)f3, (float)((float)ItemViewModel.getINSTANCE().viewAlpha.getValue().intValue() / 255.0f));
        } else {
            GlStateManager.color((float)f, (float)f2, (float)f3, (float)f4);
        }
    }

    @Inject(method={"renderItemModel"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/renderer/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V", shift=At.Shift.BEFORE)})
    private void renderItemModel(ItemStack itemStack, IBakedModel iBakedModel, ItemCameraTransforms.TransformType transformType, boolean bl, CallbackInfo callbackInfo) {
        RenderItemEvent renderItemEvent = new RenderItemEvent(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0);
        MinecraftForge.EVENT_BUS.post((Event)renderItemEvent);
    }
}

