//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderItem
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.item.ItemStack
 */
package me.snow.aclient.mixin.mixins;

import me.snow.aclient.module.Feature;
import me.snow.aclient.module.modules.render.ItemViewModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={RenderItem.class})
public abstract class MixinItemRender {
    @Inject(method={"renderItemModel"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/renderer/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V", shift=At.Shift.BEFORE)})
    private void test(ItemStack itemStack, IBakedModel iBakedModel, ItemCameraTransforms.TransformType transformType, boolean bl, CallbackInfo callbackInfo) {
        if (((Boolean)ItemViewModel.getINSTANCE().enabled.getValue()).booleanValue() && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !Feature.fullNullCheck()) {
            GlStateManager.scale((float)ItemViewModel.getINSTANCE().sizeX.getValue().floatValue(), (float)ItemViewModel.getINSTANCE().sizeY.getValue().floatValue(), (float)ItemViewModel.getINSTANCE().sizeZ.getValue().floatValue());
            GlStateManager.rotate((float)(ItemViewModel.getINSTANCE().rotationX.getValue().floatValue() * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.rotate((float)(ItemViewModel.getINSTANCE().rotationY.getValue().floatValue() * 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)(ItemViewModel.getINSTANCE().rotationZ.getValue().floatValue() * 360.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GlStateManager.translate((float)ItemViewModel.getINSTANCE().positionX.getValue().floatValue(), (float)ItemViewModel.getINSTANCE().positionY.getValue().floatValue(), (float)ItemViewModel.getINSTANCE().positionZ.getValue().floatValue());
        }
    }
}

