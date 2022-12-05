//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Enchantments
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemElytra
 *  net.minecraft.item.ItemStack
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;

public class ChestSwap
extends Module {
    private /* synthetic */ Setting<Boolean> Curse;
    private /* synthetic */ Setting<Boolean> PreferElytra;

    public ChestSwap() {
        super("ElytraReplace", "Will attempt to instantly swap your chestplate with an elytra or vice versa, depending on what is already equipped", Module.Category.PLAYER, true, false, false);
        this.PreferElytra = this.register(new Setting<Boolean>("PreferElytra", true));
        this.Curse = this.register(new Setting<Boolean>("Curse", false));
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (ChestSwap.mc.player == null) {
            return;
        }
        ItemStack itemStack = ChestSwap.mc.player.inventoryContainer.getSlot(6).getStack();
        if (itemStack.func_190926_b()) {
            int n = this.FindChestItem(this.PreferElytra.getValue());
            if (!this.PreferElytra.getValue().booleanValue() && n == -1) {
                n = this.FindChestItem(true);
            }
            if (n != -1) {
                ChestSwap.mc.playerController.windowClick(ChestSwap.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)ChestSwap.mc.player);
                ChestSwap.mc.playerController.windowClick(ChestSwap.mc.player.inventoryContainer.windowId, 6, 0, ClickType.PICKUP, (EntityPlayer)ChestSwap.mc.player);
                ChestSwap.mc.playerController.windowClick(ChestSwap.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)ChestSwap.mc.player);
            }
            this.toggle();
            return;
        }
        int n = this.FindChestItem(itemStack.getItem() instanceof ItemArmor);
        if (n != -1) {
            ChestSwap.mc.playerController.windowClick(ChestSwap.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)ChestSwap.mc.player);
            ChestSwap.mc.playerController.windowClick(ChestSwap.mc.player.inventoryContainer.windowId, 6, 0, ClickType.PICKUP, (EntityPlayer)ChestSwap.mc.player);
            ChestSwap.mc.playerController.windowClick(ChestSwap.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)ChestSwap.mc.player);
        }
        this.toggle();
    }

    private int FindChestItem(boolean bl) {
        int n = -1;
        float f = 0.0f;
        for (int i = 0; i < ChestSwap.mc.player.inventoryContainer.getInventory().size(); ++i) {
            ItemStack itemStack;
            if (i == 0 || i == 5 || i == 6 || i == 7 || i == 8 || (itemStack = (ItemStack)ChestSwap.mc.player.inventoryContainer.getInventory().get(i)) == null || itemStack.getItem() == Items.field_190931_a) continue;
            if (itemStack.getItem() instanceof ItemArmor) {
                boolean bl2;
                ItemArmor itemArmor = (ItemArmor)itemStack.getItem();
                if (itemArmor.armorType != EntityEquipmentSlot.CHEST) continue;
                float f2 = itemArmor.damageReduceAmount + EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.PROTECTION, (ItemStack)itemStack);
                boolean bl3 = bl2 = this.Curse.getValue() != false ? EnchantmentHelper.func_190938_b((ItemStack)itemStack) : false;
                if (!(f2 > f) || bl2) continue;
                f = f2;
                n = i;
                continue;
            }
            if (!bl || !(itemStack.getItem() instanceof ItemElytra)) continue;
            return i;
        }
        return n;
    }
}

