//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumHandSide
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.mixin.mixins;

import java.awt.Color;
import me.snow.aclient.event.events.RenderItemEvent;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.module.modules.render.HandModifier;
import me.snow.aclient.module.modules.render.ItemViewModel;
import me.snow.aclient.module.modules.render.NoRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ItemRenderer.class})
public abstract class MixinItemRenderer {
    public Minecraft mc;
    private boolean injection = true;

    @Shadow
    public abstract void renderItemInFirstPerson(AbstractClientPlayer var1, float var2, float var3, EnumHand var4, float var5, ItemStack var6, float var7);

    @Inject(method={"renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V"}, at={@At(value="HEAD")}, cancellable=true)
    public void renderItemInFirstPersonHook(AbstractClientPlayer abstractClientPlayer, float f, float f2, EnumHand enumHand, float f3, ItemStack itemStack, float f4, CallbackInfo callbackInfo) {
        float f5;
        if (this.injection) {
            callbackInfo.cancel();
            ItemViewModel itemViewModel = ItemViewModel.getINSTANCE();
            f5 = 0.0f;
            float f6 = 0.0f;
            this.injection = false;
            if (enumHand == EnumHand.MAIN_HAND) {
                if (itemViewModel.isOn() && abstractClientPlayer.getHeldItemMainhand() != ItemStack.field_190927_a) {
                    f5 = itemViewModel.mainX.getValue().floatValue();
                    f6 = itemViewModel.mainY.getValue().floatValue();
                }
            } else if (!itemViewModel.normalOffset.getValue().booleanValue() && itemViewModel.isOn() && abstractClientPlayer.getHeldItemOffhand() != ItemStack.field_190927_a) {
                f5 = itemViewModel.offX.getValue().floatValue();
                f6 = itemViewModel.offY.getValue().floatValue();
            }
            this.renderItemInFirstPerson(abstractClientPlayer, f, f2, enumHand, f3 + f5, itemStack, f4 + f6);
            this.injection = true;
        }
        if (HandModifier.getINSTANCE().isOn() && this.injection) {
            callbackInfo.cancel();
            float f7 = 0.0f;
            f5 = 0.0f;
            this.injection = false;
            if (enumHand == EnumHand.MAIN_HAND) {
                if (HandModifier.getINSTANCE().isOn()) {
                    f7 = HandModifier.getINSTANCE().mainX.getValue().floatValue();
                    f5 = HandModifier.getINSTANCE().mainY.getValue().floatValue();
                }
            } else if (HandModifier.getINSTANCE().isOn()) {
                f7 = HandModifier.getINSTANCE().offX.getValue().floatValue();
                f5 = HandModifier.getINSTANCE().offY.getValue().floatValue();
            }
            if (HandModifier.getINSTANCE().isOn() && HandModifier.getINSTANCE().chams.getValue().booleanValue() && enumHand == EnumHand.MAIN_HAND && itemStack.func_190926_b()) {
                if (HandModifier.getINSTANCE().mode.getValue().equals((Object)HandModifier.RenderMode.WIREFRAME) && HandModifier.getINSTANCE().chams.getValue().booleanValue()) {
                    this.renderItemInFirstPerson(abstractClientPlayer, f, f2, enumHand, f3 + f7, itemStack, f4 + f5);
                }
                GlStateManager.pushMatrix();
                if (HandModifier.getINSTANCE().mode.getValue().equals((Object)HandModifier.RenderMode.WIREFRAME) && HandModifier.getINSTANCE().chams.getValue().booleanValue()) {
                    GL11.glPushAttrib((int)1048575);
                } else {
                    GlStateManager.pushAttrib();
                }
                if (HandModifier.getINSTANCE().mode.getValue().equals((Object)HandModifier.RenderMode.WIREFRAME) && HandModifier.getINSTANCE().chams.getValue().booleanValue()) {
                    GL11.glPolygonMode((int)1032, (int)6913);
                }
                GL11.glDisable((int)3553);
                GL11.glDisable((int)2896);
                if (!HandModifier.getINSTANCE().mode.getValue().equals((Object)HandModifier.RenderMode.WIREFRAME) || HandModifier.getINSTANCE().chams.getValue().booleanValue()) {
                    // empty if block
                }
                Color color = HandModifier.getINSTANCE().csync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(HandModifier.getINSTANCE().red.getValue(), HandModifier.getINSTANCE().green.getValue(), HandModifier.getINSTANCE().blue.getValue(), HandModifier.getINSTANCE().alpha.getValue());
                GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)HandModifier.getINSTANCE().alpha.getValue().intValue() / 255.0f));
                this.renderItemInFirstPerson(abstractClientPlayer, f, f2, enumHand, f3 + f7, itemStack, f4 + f5);
                GlStateManager.popAttrib();
                GlStateManager.popMatrix();
            }
            if (HandModifier.getINSTANCE().isOn() && (!itemStack.field_190928_g || HandModifier.getINSTANCE().isOff())) {
                this.renderItemInFirstPerson(abstractClientPlayer, f, f2, enumHand, f3 + f7, itemStack, f4 + f5);
            } else if (!itemStack.field_190928_g || HandModifier.getINSTANCE().isOff()) {
                this.renderItemInFirstPerson(abstractClientPlayer, f, f2, enumHand, f3, itemStack, f4);
            }
            this.injection = true;
        }
    }

    @Inject(method={"transformSideFirstPerson"}, at={@At(value="HEAD")}, cancellable=true)
    public void transformSideFirstPerson(EnumHandSide enumHandSide, float f, CallbackInfo callbackInfo) {
        RenderItemEvent renderItemEvent = new RenderItemEvent(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0);
        MinecraftForge.EVENT_BUS.post((Event)renderItemEvent);
    }

    @Redirect(method={"renderArmFirstPerson"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/renderer/GlStateManager;translate(FFF)V", ordinal=0))
    public void translateHook(float f, float f2, float f3) {
        ItemViewModel itemViewModel = ItemViewModel.getINSTANCE();
        boolean bl = Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().player.getHeldItemMainhand() != ItemStack.field_190927_a && itemViewModel.isOn();
        GlStateManager.translate((float)(f + (bl ? itemViewModel.mainX.getValue().floatValue() : 0.0f)), (float)(f2 + (bl ? itemViewModel.mainY.getValue().floatValue() : 0.0f)), (float)f3);
    }

    @Inject(method={"renderFireInFirstPerson"}, at={@At(value="HEAD")}, cancellable=true)
    public void renderFireInFirstPersonHook(CallbackInfo callbackInfo) {
        if (NoRender.getInstance().isOn() && NoRender.getInstance().fire.getValue().booleanValue()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"renderSuffocationOverlay"}, at={@At(value="HEAD")}, cancellable=true)
    public void renderSuffocationOverlay(CallbackInfo callbackInfo) {
        if (NoRender.getInstance().isOn() && NoRender.getInstance().blocks.getValue().booleanValue()) {
            callbackInfo.cancel();
        }
    }
}

