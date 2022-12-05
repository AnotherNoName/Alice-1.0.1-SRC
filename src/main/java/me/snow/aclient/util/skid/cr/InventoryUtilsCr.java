//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 */
package me.snow.aclient.util.skid.cr;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class InventoryUtilsCr {
    protected static /* synthetic */ Minecraft mc;

    public static void setSlot(int n) {
        if (n > 8 || n < 0) {
            return;
        }
        InventoryUtilsCr.mc.player.inventory.currentItem = n;
    }

    public static int getSlot() {
        return InventoryUtilsCr.mc.player.inventory.currentItem;
    }

    static {
        mc = Minecraft.getMinecraft();
    }

    public static int getPlaceableItem() {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        for (int i = 0; i < 9; ++i) {
            if (!(((ItemStack)InventoryUtilsCr.mc.player.inventory.mainInventory.get(i)).getItem() instanceof ItemBlock)) continue;
            arrayList.add(InventoryUtilsCr.mc.player.inventory.mainInventory.get(i));
        }
        return -1;
    }

    public static int pickItem(int n) {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        for (int i = 0; i < 9; ++i) {
            if (Item.getIdFromItem((Item)((ItemStack)InventoryUtilsCr.mc.player.inventory.mainInventory.get(i)).getItem()) != n) continue;
            arrayList.add(InventoryUtilsCr.mc.player.inventory.mainInventory.get(i));
        }
        if (arrayList.size() >= 1) {
            return InventoryUtilsCr.mc.player.inventory.mainInventory.indexOf(arrayList.get(0));
        }
        return -1;
    }
}

