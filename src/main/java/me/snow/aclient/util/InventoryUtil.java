//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockShulkerBox
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiCrafting
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Enchantments
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemAir
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 */
package me.snow.aclient.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import me.snow.aclient.AliceMain;
import me.snow.aclient.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockShulkerBox;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;

public class InventoryUtil
implements Util {
    public static /* synthetic */ int currentItem;

    public static int findInventoryWool(boolean bl) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(-1);
        for (Map.Entry<Integer, ItemStack> entry : InventoryUtil.getInventoryAndHotbarSlots().entrySet()) {
            if (!(entry.getValue().getItem() instanceof ItemBlock)) continue;
            ItemBlock itemBlock = (ItemBlock)entry.getValue().getItem();
            if (itemBlock.getBlock().blockMaterial != Material.CLOTH || entry.getKey() == 45 && !bl) continue;
            atomicInteger.set(entry.getKey());
            return atomicInteger.get();
        }
        return atomicInteger.get();
    }

    public static int getBlockHotbar(Block block) {
        for (int i = 0; i < 9; ++i) {
            Block block2;
            ItemStack itemStack = InventoryUtil.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.field_190927_a || !(itemStack.getItem() instanceof ItemBlock) || (block2 = ((ItemBlock)itemStack.getItem()).getBlock()) != block) continue;
            return i;
        }
        return -1;
    }

    public static boolean areStacksCompatible(ItemStack itemStack, ItemStack itemStack2) {
        if (!itemStack.getItem().equals((Object)itemStack2.getItem())) {
            return false;
        }
        if (itemStack.getItem() instanceof ItemBlock && itemStack2.getItem() instanceof ItemBlock) {
            Block block = ((ItemBlock)itemStack.getItem()).getBlock();
            Block block2 = ((ItemBlock)itemStack2.getItem()).getBlock();
            if (!block.blockMaterial.equals((Object)block2.blockMaterial)) {
                return false;
            }
        }
        if (!itemStack.getDisplayName().equals(itemStack2.getDisplayName())) {
            return false;
        }
        return itemStack.getItemDamage() == itemStack2.getItemDamage();
    }

    public static boolean heldItem(Item item, Hand hand) {
        switch (hand) {
            case Main: {
                if (InventoryUtil.mc.player.getHeldItemMainhand().getItem() != item) break;
                return true;
            }
            case Off: {
                if (InventoryUtil.mc.player.getHeldItemOffhand().getItem() != item) break;
                return true;
            }
            case Both: {
                if (InventoryUtil.mc.player.getHeldItemOffhand().getItem() != item && InventoryUtil.mc.player.getHeldItemMainhand().getItem() != item) break;
                return true;
            }
        }
        return false;
    }

    public static boolean isNull(ItemStack itemStack) {
        return itemStack == null || itemStack.getItem() instanceof ItemAir;
    }

    public static void pop() {
        InventoryUtil.mc.player.inventory.currentItem = currentItem;
    }

    public static boolean isBlock(Item item, Class class_) {
        if (item instanceof ItemBlock) {
            Block block = ((ItemBlock)item).getBlock();
            return class_.isInstance((Object)block);
        }
        return false;
    }

    public static int findArmorSlot(EntityEquipmentSlot entityEquipmentSlot, boolean bl) {
        int n = -1;
        float f = 0.0f;
        for (int i = 9; i < 45; ++i) {
            boolean bl2;
            ItemStack itemStack = Minecraft.getMinecraft().player.inventoryContainer.getSlot(i).getStack();
            if (itemStack.getItem() == Items.field_190931_a || !(itemStack.getItem() instanceof ItemArmor)) continue;
            ItemArmor itemArmor = (ItemArmor)itemStack.getItem();
            if (itemArmor.armorType != entityEquipmentSlot) continue;
            float f2 = itemArmor.damageReduceAmount + EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.PROTECTION, (ItemStack)itemStack);
            boolean bl3 = bl2 = bl && EnchantmentHelper.func_190938_b((ItemStack)itemStack);
            if (!(f2 > f) || bl2) continue;
            f = f2;
            n = i;
        }
        return n;
    }

    public static void setSlot(int n) {
        if (n > 8 || n < 0) {
            return;
        }
        InventoryUtil.mc.player.inventory.currentItem = n;
    }

    public static void confirmSlot(int n) {
        InventoryUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
        InventoryUtil.mc.player.inventory.currentItem = n;
        InventoryUtil.mc.playerController.updateController();
    }

    public static int findHotbarBlock(Class class_) {
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = InventoryUtil.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.field_190927_a) continue;
            if (class_.isInstance((Object)itemStack.getItem())) {
                return i;
            }
            if (!(itemStack.getItem() instanceof ItemBlock) || !class_.isInstance((Object)((ItemBlock)itemStack.getItem()).getBlock())) continue;
            return i;
        }
        return -1;
    }

    public static void push() {
        currentItem = InventoryUtil.mc.player.inventory.currentItem;
    }

    public static int pickItem(int n, boolean bl) {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        for (int i = 0; i < (bl ? InventoryUtil.mc.player.inventory.mainInventory.size() : 9); ++i) {
            if (Item.getIdFromItem((Item)((ItemStack)InventoryUtil.mc.player.inventory.mainInventory.get(i)).getItem()) != n) continue;
            arrayList.add(InventoryUtil.mc.player.inventory.mainInventory.get(i));
        }
        if (arrayList.size() >= 1) {
            return InventoryUtil.mc.player.inventory.mainInventory.indexOf(arrayList.get(0));
        }
        return -1;
    }

    private static Map<Integer, ItemStack> getInventorySlots(int n, int n2) {
        HashMap<Integer, ItemStack> hashMap = new HashMap<Integer, ItemStack>();
        for (int i = n; i <= n2; ++i) {
            hashMap.put(i, (ItemStack)InventoryUtil.mc.player.inventoryContainer.getInventory().get(i));
        }
        return hashMap;
    }

    public static int findStackInventory(Item item) {
        return InventoryUtil.findStackInventory(item, false);
    }

    public static Map<Integer, ItemStack> getInventoryAndHotbarSlots() {
        if (InventoryUtil.mc.currentScreen instanceof GuiCrafting) {
            return InventoryUtil.fuckYou3arthqu4kev2(10, 45);
        }
        return InventoryUtil.getInventorySlots(9, 44);
    }

    public static int findItemInventorySlot(Item item, boolean bl, boolean bl2) {
        int n = InventoryUtil.findItemInventorySlot(item, bl);
        if (n == -1 && bl2) {
            for (int i = 1; i < 5; ++i) {
                Slot slot = (Slot)InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(i);
                ItemStack itemStack = slot.getStack();
                if (itemStack.getItem() == Items.field_190931_a || itemStack.getItem() != item) continue;
                n = i;
            }
        }
        return n;
    }

    public static int convertHotbarToInv(int n) {
        return 36 + n;
    }

    public static int getEmptyXCarry() {
        for (int i = 1; i < 5; ++i) {
            Slot slot = (Slot)InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(i);
            ItemStack itemStack = slot.getStack();
            if (!itemStack.func_190926_b() && itemStack.getItem() != Items.field_190931_a) continue;
            return i;
        }
        return -1;
    }

    public static int findArmorSlot(EntityEquipmentSlot entityEquipmentSlot, boolean bl, boolean bl2) {
        int n = InventoryUtil.findArmorSlot(entityEquipmentSlot, bl);
        if (n == -1 && bl2) {
            float f = 0.0f;
            for (int i = 1; i < 5; ++i) {
                boolean bl3;
                Slot slot = (Slot)InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(i);
                ItemStack itemStack = slot.getStack();
                if (itemStack.getItem() == Items.field_190931_a || !(itemStack.getItem() instanceof ItemArmor)) continue;
                ItemArmor itemArmor = (ItemArmor)itemStack.getItem();
                if (itemArmor.armorType != entityEquipmentSlot) continue;
                float f2 = itemArmor.damageReduceAmount + EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.PROTECTION, (ItemStack)itemStack);
                boolean bl4 = bl3 = bl && EnchantmentHelper.func_190938_b((ItemStack)itemStack);
                if (!(f2 > f) || bl3) continue;
                f = f2;
                n = i;
            }
        }
        return n;
    }

    public static int findInventoryBlock(Class class_, boolean bl) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(-1);
        for (Map.Entry<Integer, ItemStack> entry : InventoryUtil.getInventoryAndHotbarSlots().entrySet()) {
            if (!InventoryUtil.isBlock(entry.getValue().getItem(), class_) || entry.getKey() == 45 && !bl) continue;
            atomicInteger.set(entry.getKey());
            return atomicInteger.get();
        }
        return atomicInteger.get();
    }

    public static int getItemHotbar(Item item) {
        for (int i = 0; i < 9; ++i) {
            Item item2 = InventoryUtil.mc.player.inventory.getStackInSlot(i).getItem();
            if (Item.getIdFromItem((Item)item2) != Item.getIdFromItem((Item)item)) continue;
            return i;
        }
        return -1;
    }

    public static int findBlockSlotInventory(Class<BlockShulkerBox> class_, boolean bl, boolean bl2) {
        int n = InventoryUtil.findInventoryBlock(class_, bl);
        if (n == -1 && bl2) {
            for (int i = 1; i < 5; ++i) {
                Slot slot = (Slot)InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(i);
                ItemStack itemStack = slot.getStack();
                if (itemStack.getItem() == Items.field_190931_a) continue;
                Item item = itemStack.getItem();
                if (class_.isInstance((Object)item)) {
                    n = i;
                    continue;
                }
                if (!(item instanceof ItemBlock) || !class_.isInstance((Object)((ItemBlock)item).getBlock())) continue;
                n = i;
            }
        }
        return n;
    }

    public static boolean holdingItem(Class class_) {
        ItemStack itemStack = InventoryUtil.mc.player.getHeldItemMainhand();
        boolean bl = InventoryUtil.isInstanceOf(itemStack, class_);
        if (!bl) {
            InventoryUtil.mc.player.getHeldItemOffhand();
            bl = InventoryUtil.isInstanceOf(itemStack, class_);
        }
        return bl;
    }

    public static void switchToHotbarSlot(int n, boolean bl) {
        if (InventoryUtil.mc.player.inventory.currentItem == n || n < 0) {
            return;
        }
        if (bl) {
            InventoryUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
            InventoryUtil.mc.playerController.updateController();
        } else {
            InventoryUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
            InventoryUtil.mc.player.inventory.currentItem = n;
            InventoryUtil.mc.playerController.updateController();
        }
    }

    public static EntityEquipmentSlot getEquipmentFromSlot(int n) {
        if (n == 5) {
            return EntityEquipmentSlot.HEAD;
        }
        if (n == 6) {
            return EntityEquipmentSlot.CHEST;
        }
        if (n == 7) {
            return EntityEquipmentSlot.LEGS;
        }
        return EntityEquipmentSlot.FEET;
    }

    public static boolean[] switchItemToItem(boolean bl, int n, boolean bl2, Switch switch_, Item item) {
        boolean[] arrbl = new boolean[]{bl2, false};
        switch (switch_) {
            case NORMAL: {
                if (!bl && !bl2) {
                    InventoryUtil.switchToHotbarSlot(InventoryUtil.getItemHotbar(item), false);
                    arrbl[0] = true;
                } else if (bl && bl2) {
                    InventoryUtil.switchToHotbarSlot(n, false);
                    arrbl[0] = false;
                }
                arrbl[1] = true;
                break;
            }
            case SILENT: {
                if (!bl && !bl2) {
                    InventoryUtil.switchToHotbarSlot(InventoryUtil.getItemHotbar(item), true);
                    arrbl[0] = true;
                } else if (bl && bl2) {
                    arrbl[0] = false;
                    AliceMain.inventoryManager.recoverSilent(n);
                }
                arrbl[1] = true;
                break;
            }
            case NONE: {
                arrbl[1] = bl || InventoryUtil.mc.player.inventory.currentItem == InventoryUtil.getItemHotbar(item);
            }
        }
        return arrbl;
    }

    public static int getBlockFromHotbar(Block block) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (InventoryUtil.mc.player.inventory.getStackInSlot(i).getItem() != Item.getItemFromBlock((Block)block)) continue;
            n = i;
        }
        return n;
    }

    public static boolean isInstanceOf(ItemStack itemStack, Class class_) {
        if (itemStack == null) {
            return false;
        }
        Item item = itemStack.getItem();
        if (class_.isInstance((Object)item)) {
            return true;
        }
        if (item instanceof ItemBlock) {
            Block block = Block.getBlockFromItem((Item)item);
            return class_.isInstance((Object)block);
        }
        return false;
    }

    public static int findEmptySlot() {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(-1);
        for (Map.Entry<Integer, ItemStack> entry : InventoryUtil.getInventoryAndHotbarSlots().entrySet()) {
            if (!entry.getValue().func_190926_b()) continue;
            atomicInteger.set(entry.getKey());
            return atomicInteger.get();
        }
        return atomicInteger.get();
    }

    public static int findItemInventorySlot(Item item, boolean bl) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(-1);
        for (Map.Entry<Integer, ItemStack> entry : InventoryUtil.getInventoryAndHotbarSlots().entrySet()) {
            if (entry.getValue().getItem() != item || entry.getKey() == 45 && !bl) continue;
            atomicInteger.set(entry.getKey());
            return atomicInteger.get();
        }
        return atomicInteger.get();
    }

    private static Map<Integer, ItemStack> fuckYou3arthqu4kev2(int n, int n2) {
        HashMap<Integer, ItemStack> hashMap = new HashMap<Integer, ItemStack>();
        for (int i = n; i <= n2; ++i) {
            hashMap.put(i, (ItemStack)InventoryUtil.mc.player.openContainer.getInventory().get(i));
        }
        return hashMap;
    }

    public static boolean isSlotEmpty(int n) {
        Slot slot = (Slot)InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(n);
        ItemStack itemStack = slot.getStack();
        return itemStack.func_190926_b();
    }

    public static boolean[] switchItem(boolean bl, int n, boolean bl2, Switch switch_, Class class_) {
        boolean[] arrbl = new boolean[]{bl2, false};
        switch (switch_) {
            case NORMAL: {
                if (!bl && !bl2) {
                    InventoryUtil.switchToHotbarSlot(InventoryUtil.findHotbarBlock(class_), false);
                    arrbl[0] = true;
                } else if (bl && bl2) {
                    InventoryUtil.switchToHotbarSlot(n, false);
                    arrbl[0] = false;
                }
                arrbl[1] = true;
                break;
            }
            case SILENT: {
                if (!bl && !bl2) {
                    InventoryUtil.switchToHotbarSlot(InventoryUtil.findHotbarBlock(class_), true);
                    arrbl[0] = true;
                } else if (bl && bl2) {
                    arrbl[0] = false;
                    AliceMain.inventoryManager.recoverSilent(n);
                }
                arrbl[1] = true;
                break;
            }
            case NONE: {
                arrbl[1] = bl || InventoryUtil.mc.player.inventory.currentItem == InventoryUtil.findHotbarBlock(class_);
            }
        }
        return arrbl;
    }

    public static int findHotbarBlock(Block block) {
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = InventoryUtil.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.field_190927_a || !(itemStack.getItem() instanceof ItemBlock) || ((ItemBlock)itemStack.getItem()).getBlock() != block) continue;
            return i;
        }
        return -1;
    }

    public static int findStackInventory(Item item, boolean bl) {
        int n;
        int n2 = n = bl ? 0 : 9;
        while (n < 36) {
            Item item2 = InventoryUtil.mc.player.inventory.getStackInSlot(n).getItem();
            if (Item.getIdFromItem((Item)item) == Item.getIdFromItem((Item)item2)) {
                return n + (n < 9 ? 36 : 0);
            }
            ++n;
        }
        return -1;
    }

    public static List<Integer> findEmptySlots(boolean bl) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (Map.Entry<Integer, ItemStack> slot : InventoryUtil.getInventoryAndHotbarSlots().entrySet()) {
            if (!slot.getValue().field_190928_g && slot.getValue().getItem() != Items.field_190931_a) continue;
            arrayList.add(slot.getKey());
        }
        if (bl) {
            for (int i = 1; i < 5; ++i) {
                Slot slot = (Slot)InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(i);
                ItemStack itemStack = slot.getStack();
                if (!itemStack.func_190926_b() && itemStack.getItem() != Items.field_190931_a) continue;
                arrayList.add(i);
            }
        }
        return arrayList;
    }

    public static void switchToHotbarSlot(Class class_, boolean bl) {
        int n = InventoryUtil.findHotbarBlock(class_);
        if (n > -1) {
            InventoryUtil.switchToHotbarSlot(n, bl);
        }
    }

    public static int findAnyBlock() {
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = InventoryUtil.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.field_190927_a || !(itemStack.getItem() instanceof ItemBlock)) continue;
            return i;
        }
        return -1;
    }

    public static void SilentSwitchToSlot(int n) {
        if (InventoryUtil.mc.player.inventory.currentItem == n || n == -1) {
            return;
        }
        InventoryUtil.mc.player.inventory.currentItem = n;
        InventoryUtil.mc.playerController.updateController();
    }

    public static class Task {
        private final /* synthetic */ boolean update;
        private final /* synthetic */ boolean quickClick;
        private final /* synthetic */ int slot;

        public Task(int n, boolean bl) {
            this.slot = n;
            this.quickClick = bl;
            this.update = false;
        }

        public boolean isSwitching() {
            return !this.update;
        }

        public void run() {
            if (this.update) {
                Util.mc.playerController.updateController();
            }
            if (this.slot != -1) {
                Util.mc.playerController.windowClick(Util.mc.player.inventoryContainer.windowId, this.slot, 0, this.quickClick ? ClickType.QUICK_MOVE : ClickType.PICKUP, (EntityPlayer)Util.mc.player);
            }
        }

        public Task() {
            this.update = true;
            this.slot = -1;
            this.quickClick = false;
        }

        public Task(int n) {
            this.slot = n;
            this.quickClick = false;
            this.update = false;
        }
    }

    public static enum Hand {
        Main,
        Off,
        Both;

    }

    public static enum Switch {
        NORMAL,
        SILENT,
        NONE;

    }
}

