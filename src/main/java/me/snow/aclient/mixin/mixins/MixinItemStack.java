//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package me.snow.aclient.mixin.mixins;

import me.snow.aclient.module.modules.player.TrueDurability;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ItemStack.class})
public abstract class MixinItemStack {
    @Shadow
    private int itemDamage;

    @Inject(method={"<init>(Lnet/minecraft/item/Item;IILnet/minecraft/nbt/NBTTagCompound;)V"}, at={@At(value="RETURN")})
    @Dynamic
    private void initHook(Item item, int n, int n2, NBTTagCompound nBTTagCompound, CallbackInfo callbackInfo) {
        this.itemDamage = this.checkDurability((ItemStack)ItemStack.class.cast(this), this.itemDamage, n2);
    }

    @Inject(method={"<init>(Lnet/minecraft/nbt/NBTTagCompound;)V"}, at={@At(value="RETURN")})
    private void initHook2(NBTTagCompound nBTTagCompound, CallbackInfo callbackInfo) {
        this.itemDamage = this.checkDurability((ItemStack)ItemStack.class.cast(this), this.itemDamage, nBTTagCompound.getShort("Damage"));
    }

    private int checkDurability(ItemStack itemStack, int n, int n2) {
        int n3 = n;
        if (TrueDurability.getInstance().isOn() && n2 < 0) {
            n3 = n2;
        }
        return n3;
    }
}

