//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package me.snow.aclient.util.cc;

import java.util.Arrays;
import me.snow.aclient.AliceMain;
import me.snow.aclient.util.Util;
import me.snow.aclient.util.cc.InventoryManagerCC;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class InventoryUtilCC
implements Util {
    public static boolean isHolding(Block block) {
        return InventoryUtilCC.mc.player.getHeldItemMainhand().getItem().equals((Object)Item.getItemFromBlock((Block)block)) || InventoryUtilCC.mc.player.getHeldItemOffhand().getItem().equals((Object)Item.getItemFromBlock((Block)block));
    }

    public static int getHighestEnchantLevel() {
        short s = 0;
        for (int i = 0; i < InventoryUtilCC.mc.player.getHeldItemMainhand().getEnchantmentTagList().tagCount(); ++i) {
            NBTTagCompound nBTTagCompound = InventoryUtilCC.mc.player.getHeldItemMainhand().getEnchantmentTagList().getCompoundTagAt(i);
            if (nBTTagCompound.getShort("lvl") <= s) continue;
            s = nBTTagCompound.getShort("lvl");
        }
        return s;
    }

    public static boolean isHolding(Block[] arrblock) {
        return Arrays.stream(arrblock).anyMatch(block -> Item.getItemFromBlock((Block)block).equals((Object)InventoryUtilCC.mc.player.getHeldItemMainhand().getItem())) || Arrays.stream(arrblock).anyMatch(block -> Item.getItemFromBlock((Block)block).equals((Object)InventoryUtilCC.mc.player.getHeldItemOffhand().getItem()));
    }

    public static int getItemCount(Item item) {
        if (item instanceof ItemArmor) {
            return InventoryUtilCC.mc.player.inventory.armorInventory.stream().filter(itemStack -> itemStack.getItem().equals((Object)item)).mapToInt(ItemStack::func_190916_E).sum();
        }
        return InventoryUtilCC.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem().equals((Object)item)).mapToInt(ItemStack::func_190916_E).sum() + InventoryUtilCC.mc.player.inventory.offHandInventory.stream().filter(itemStack -> itemStack.getItem().equals((Object)item)).mapToInt(ItemStack::func_190916_E).sum();
    }

    public static boolean isHolding(Item[] arritem) {
        return Arrays.stream(arritem).anyMatch(item -> item.equals((Object)InventoryUtilCC.mc.player.getHeldItemMainhand().getItem())) || Arrays.stream(arritem).anyMatch(item -> item.equals((Object)InventoryUtilCC.mc.player.getHeldItemOffhand().getItem()));
    }

    public static boolean isInHotbar(Item item) {
        return AliceMain.inventoryManagercc.searchSlot(item, InventoryManagerCC.InventoryRegion.HOTBAR) != -1;
    }

    public static boolean isHolding(Item item) {
        return InventoryUtilCC.mc.player.getHeldItemMainhand().getItem().equals((Object)item) || InventoryUtilCC.mc.player.getHeldItemOffhand().getItem().equals((Object)item);
    }

    public static boolean isHolding(Class<? extends Item> class_) {
        return class_.isInstance((Object)InventoryUtilCC.mc.player.getHeldItemMainhand().getItem()) || class_.isInstance((Object)InventoryUtilCC.mc.player.getHeldItemOffhand().getItem());
    }
}

