//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
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
package me.snow.aclient.util.skid.oyvey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import me.snow.aclient.AliceMain;
import me.snow.aclient.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
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

public class InventoryUtil2
implements Util {
    public static /* synthetic */ short actionNumber;
    private static /* synthetic */ int currentItem;

    public static void confirmSlot(int n) {
        InventoryUtil2.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
        InventoryUtil2.mc.player.inventory.currentItem = n;
        InventoryUtil2.mc.playerController.updateController();
    }

    public static void switchToSlot(Block block) {
        if (InventoryUtil2.getBlockInHotbar(block) != -1 && InventoryUtil2.mc.player.inventory.currentItem != InventoryUtil2.getBlockInHotbar(block)) {
            InventoryUtil2.mc.player.inventory.currentItem = InventoryUtil2.getBlockInHotbar(block);
        }
    }

    public static int find(Class<? extends Item> class_) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (!InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem().getClass().equals(class_)) continue;
            n = i;
        }
        return n;
    }

    public static int getBlockInHotbar(Block block) {
        for (int i = 0; i < 9; ++i) {
            Item item = InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem();
            if (!(item instanceof ItemBlock) || !((ItemBlock)item).getBlock().equals((Object)block)) continue;
            return i;
        }
        return -1;
    }

    public static void switchToSlotGhost(int n) {
        InventoryUtil2.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
    }

    public static boolean[] switchItemToItem(boolean bl, int n, boolean bl2, Switch switch_, Item item) {
        boolean[] arrbl = new boolean[]{bl2, false};
        switch (switch_) {
            case NORMAL: {
                if (!bl && !bl2) {
                    InventoryUtil2.switchToHotbarSlot(InventoryUtil2.getItemHotbar(item), false);
                    arrbl[0] = true;
                } else if (bl && bl2) {
                    InventoryUtil2.switchToHotbarSlot(n, false);
                    arrbl[0] = false;
                }
                arrbl[1] = true;
                break;
            }
            case SILENT: {
                if (!bl && !bl2) {
                    InventoryUtil2.switchToHotbarSlot(InventoryUtil2.getItemHotbar(item), true);
                    arrbl[0] = true;
                } else if (bl && bl2) {
                    arrbl[0] = false;
                    AliceMain.inventoryManager.recoverSilent(n);
                }
                arrbl[1] = true;
                break;
            }
            case NONE: {
                arrbl[1] = bl || InventoryUtil2.mc.player.inventory.currentItem == InventoryUtil2.getItemHotbar(item);
            }
        }
        return arrbl;
    }

    public static int getItemSlot(Item item) {
        for (int i = 0; i < 36; ++i) {
            Item item2 = Minecraft.getMinecraft().player.inventory.getStackInSlot(i).getItem();
            if (item2 != item) continue;
            if (i < 9) {
                i += 36;
            }
            return i;
        }
        return -1;
    }

    public static int findItemInventorySlot(Item item, boolean bl, boolean bl2) {
        int n = InventoryUtil2.findItemInventorySlot(item, bl);
        if (n == -1 && bl2) {
            for (int i = 1; i < 5; ++i) {
                Item item2;
                Slot slot = (Slot)InventoryUtil2.mc.player.inventoryContainer.inventorySlots.get(i);
                ItemStack itemStack = slot.getStack();
                if (itemStack.getItem() == Items.field_190931_a || (item2 = itemStack.getItem()) != item) continue;
                n = i;
            }
        }
        return n;
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

    public static int amountBlockInHotbar(Block block) {
        return InventoryUtil2.amountInHotbar(new ItemStack(block).getItem());
    }

    public static int findArmorSlot(EntityEquipmentSlot entityEquipmentSlot, boolean bl) {
        int n = -1;
        float f = 0.0f;
        for (int i = 9; i < 45; ++i) {
            boolean bl2;
            ItemArmor itemArmor;
            ItemStack itemStack = Minecraft.getMinecraft().player.inventoryContainer.getSlot(i).getStack();
            if (itemStack.getItem() == Items.field_190931_a || !(itemStack.getItem() instanceof ItemArmor) || (itemArmor = (ItemArmor)itemStack.getItem()).getEquipmentSlot() != entityEquipmentSlot) continue;
            float f2 = itemArmor.damageReduceAmount + EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.PROTECTION, (ItemStack)itemStack);
            boolean bl3 = bl2 = bl && EnchantmentHelper.func_190938_b((ItemStack)itemStack);
            if (!(f2 > f) || bl2) continue;
            f = f2;
            n = i;
        }
        return n;
    }

    public static boolean holdingItem(Class class_) {
        boolean bl = false;
        ItemStack itemStack = InventoryUtil2.mc.player.getHeldItemMainhand();
        bl = InventoryUtil2.isInstanceOf(itemStack, class_);
        if (!bl) {
            ItemStack itemStack2 = InventoryUtil2.mc.player.getHeldItemOffhand();
            bl = InventoryUtil2.isInstanceOf(itemStack, class_);
        }
        return bl;
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

    public static void switchToSlot(int n) {
        if (n != -1 && InventoryUtil2.mc.player.inventory.currentItem != n) {
            InventoryUtil2.mc.player.inventory.currentItem = n;
            InventoryUtil2.mc.playerController.updateController();
        }
    }

    public static boolean isBlock(Item item, Class class_) {
        if (item instanceof ItemBlock) {
            Block block = ((ItemBlock)item).getBlock();
            return class_.isInstance((Object)block);
        }
        return false;
    }

    public static int findStackInventory(Item item, boolean bl) {
        int n;
        int n2 = n = 0;
        int n3 = n = bl ? 0 : 9;
        while (n < 36) {
            Item item2 = InventoryUtil2.mc.player.inventory.getStackInSlot(n).getItem();
            if (Item.getIdFromItem((Item)item) == Item.getIdFromItem((Item)item2)) {
                return n + (n < 9 ? 36 : 0);
            }
            ++n;
        }
        return -1;
    }

    public static int convertHotbarToInv(int n) {
        return 36 + n;
    }

    public static int getHotbarItemSlot(Item item) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (!InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem().equals((Object)item)) continue;
            n = i;
            break;
        }
        return n;
    }

    public static void switchToHotbarSlot(int n, boolean bl) {
        if (InventoryUtil2.mc.player.inventory.currentItem == n || n < 0) {
            return;
        }
        if (bl) {
            InventoryUtil2.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
            InventoryUtil2.mc.playerController.updateController();
        } else {
            InventoryUtil2.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
            InventoryUtil2.mc.player.inventory.currentItem = n;
            InventoryUtil2.mc.playerController.updateController();
        }
    }

    public static int amountInHotbar(Item item, boolean bl) {
        int n = 0;
        for (int i = 44; i > 35; --i) {
            ItemStack itemStack = InventoryUtil2.mc.player.inventoryContainer.getSlot(i).getStack();
            if (itemStack.getItem() != item) continue;
            n += itemStack.func_190916_E();
        }
        if (InventoryUtil2.mc.player.getHeldItemOffhand().getItem() == item && bl) {
            n += InventoryUtil2.mc.player.getHeldItemOffhand().func_190916_E();
        }
        return n;
    }

    public static int findHotbarBlock(Block block) {
        for (int i = 0; i < 9; ++i) {
            Block block2;
            ItemStack itemStack = InventoryUtil2.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.field_190927_a || !(itemStack.getItem() instanceof ItemBlock) || (block2 = ((ItemBlock)itemStack.getItem()).getBlock()) != block) continue;
            return i;
        }
        return -1;
    }

    public static int findInventoryBlock(Class class_, boolean bl) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(-1);
        for (Map.Entry<Integer, ItemStack> entry : InventoryUtil2.getInventoryAndHotbarSlots().entrySet()) {
            if (!InventoryUtil2.isBlock(entry.getValue().getItem(), class_) || entry.getKey() == 45 && !bl) continue;
            atomicInteger.set(entry.getKey());
            return atomicInteger.get();
        }
        return atomicInteger.get();
    }

    public static void pop() {
        InventoryUtil2.mc.player.inventory.currentItem = currentItem;
    }

    public static Map<Integer, ItemStack> getInventoryAndHotbarSlots() {
        return InventoryUtil2.getInventorySlots(9, 44);
    }

    public static int pickItem(int n, boolean bl) {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        for (int i = 0; i < (bl ? InventoryUtil2.mc.player.inventory.mainInventory.size() : 9); ++i) {
            if (Item.getIdFromItem((Item)((ItemStack)InventoryUtil2.mc.player.inventory.mainInventory.get(i)).getItem()) != n) continue;
            arrayList.add(InventoryUtil2.mc.player.inventory.mainInventory.get(i));
        }
        if (arrayList.size() >= 1) {
            return InventoryUtil2.mc.player.inventory.mainInventory.indexOf(arrayList.get(0));
        }
        return -1;
    }

    public static List<Integer> findEmptySlots(boolean bl) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (Map.Entry<Integer, ItemStack> slot : InventoryUtil2.getInventoryAndHotbarSlots().entrySet()) {
            if (!slot.getValue().field_190928_g && slot.getValue().getItem() != Items.field_190931_a) continue;
            arrayList.add(slot.getKey());
        }
        if (bl) {
            for (int i = 1; i < 5; ++i) {
                Slot slot = (Slot)InventoryUtil2.mc.player.inventoryContainer.inventorySlots.get(i);
                ItemStack itemStack = slot.getStack();
                if (!itemStack.func_190926_b() && itemStack.getItem() != Items.field_190931_a) continue;
                arrayList.add(i);
            }
        }
        return arrayList;
    }

    public static int amountBlockInHotbar(Block block, boolean bl) {
        return InventoryUtil2.amountInHotbar(new ItemStack(block).getItem(), bl);
    }

    public static int findItemInHotbar(Item item) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem() != item) continue;
            n = i;
            break;
        }
        return n;
    }

    public static boolean switchTo(Item item) {
        int n = InventoryUtil2.find(item);
        if (n == -1) {
            return false;
        }
        InventoryUtil2.mc.player.inventory.currentItem = n;
        InventoryUtil2.mc.playerController.updateController();
        return true;
    }

    public static boolean isSlotEmpty(int n) {
        Slot slot = (Slot)InventoryUtil2.mc.player.inventoryContainer.inventorySlots.get(n);
        ItemStack itemStack = slot.getStack();
        return itemStack.func_190926_b();
    }

    public static int findBlockSlotInventory(Class class_, boolean bl, boolean bl2) {
        int n = InventoryUtil2.findInventoryBlock(class_, bl);
        if (n == -1 && bl2) {
            for (int i = 1; i < 5; ++i) {
                Block block;
                Slot slot = (Slot)InventoryUtil2.mc.player.inventoryContainer.inventorySlots.get(i);
                ItemStack itemStack = slot.getStack();
                if (itemStack.getItem() == Items.field_190931_a) continue;
                Item item = itemStack.getItem();
                if (class_.isInstance((Object)item)) {
                    n = i;
                    continue;
                }
                if (!(item instanceof ItemBlock) || !class_.isInstance((Object)(block = ((ItemBlock)item).getBlock()))) continue;
                n = i;
            }
        }
        return n;
    }

    public static int findFirst(Item item) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem() != item) continue;
            n = i;
            break;
        }
        return n;
    }

    public static int findArmorSlot(EntityEquipmentSlot entityEquipmentSlot, boolean bl, boolean bl2) {
        int n = InventoryUtil2.findArmorSlot(entityEquipmentSlot, bl);
        if (n == -1 && bl2) {
            float f = 0.0f;
            for (int i = 1; i < 5; ++i) {
                boolean bl3;
                ItemArmor itemArmor;
                Slot slot = (Slot)InventoryUtil2.mc.player.inventoryContainer.inventorySlots.get(i);
                ItemStack itemStack = slot.getStack();
                if (itemStack.getItem() == Items.field_190931_a || !(itemStack.getItem() instanceof ItemArmor) || (itemArmor = (ItemArmor)itemStack.getItem()).getEquipmentSlot() != entityEquipmentSlot) continue;
                float f2 = itemArmor.damageReduceAmount + EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.PROTECTION, (ItemStack)itemStack);
                boolean bl4 = bl3 = bl && EnchantmentHelper.func_190938_b((ItemStack)itemStack);
                if (!(f2 > f) || bl3) continue;
                f = f2;
                n = i;
            }
        }
        return n;
    }

    private static Map<Integer, ItemStack> getInventorySlots(int n, int n2) {
        HashMap<Integer, ItemStack> hashMap = new HashMap<Integer, ItemStack>();
        for (int i = n; i <= n2; ++i) {
            hashMap.put(i, (ItemStack)InventoryUtil2.mc.player.inventoryContainer.getInventory().get(i));
        }
        return hashMap;
    }

    public static int getItemFromHotbar(Item item) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem() != item) continue;
            n = i;
            break;
        }
        return n;
    }

    public static boolean isNull(ItemStack itemStack) {
        return itemStack == null || itemStack.getItem() instanceof ItemAir;
    }

    public static int findItemInventorySlot(Item item, boolean bl) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(-1);
        for (Map.Entry<Integer, ItemStack> entry : InventoryUtil2.getInventoryAndHotbarSlots().entrySet()) {
            if (entry.getValue().getItem() != item || entry.getKey() == 45 && !bl) continue;
            atomicInteger.set(entry.getKey());
            return atomicInteger.get();
        }
        return atomicInteger.get();
    }

    public static int findFirst(Class<? extends Item> class_) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (!InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem().getClass().equals(class_)) continue;
            n = i;
            break;
        }
        return n;
    }

    static {
        actionNumber = 0;
    }

    public static boolean[] switchItem(boolean bl, int n, boolean bl2, Switch switch_, Class class_) {
        boolean[] arrbl = new boolean[]{bl2, false};
        switch (switch_) {
            case NORMAL: {
                if (!bl && !bl2) {
                    InventoryUtil2.switchToHotbarSlot(InventoryUtil2.findHotbarBlock(class_), false);
                    arrbl[0] = true;
                } else if (bl && bl2) {
                    InventoryUtil2.switchToHotbarSlot(n, false);
                    arrbl[0] = false;
                }
                arrbl[1] = true;
                break;
            }
            case SILENT: {
                if (!bl && !bl2) {
                    InventoryUtil2.switchToHotbarSlot(InventoryUtil2.findHotbarBlock(class_), true);
                    arrbl[0] = true;
                } else if (bl && bl2) {
                    arrbl[0] = false;
                    AliceMain.inventoryManager.recoverSilent(n);
                }
                arrbl[1] = true;
                break;
            }
            case NONE: {
                arrbl[1] = bl || InventoryUtil2.mc.player.inventory.currentItem == InventoryUtil2.findHotbarBlock(class_);
            }
        }
        return arrbl;
    }

    public static int find(Item item) {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            if (InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem() != item) continue;
            n = i;
        }
        return n;
    }

    public static void moveItem(int n, int n2) {
        InventoryUtil2.mc.playerController.windowClick(InventoryUtil2.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtil2.mc.player);
        InventoryUtil2.mc.playerController.windowClick(InventoryUtil2.mc.player.inventoryContainer.windowId, n2, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtil2.mc.player);
        InventoryUtil2.mc.playerController.windowClick(InventoryUtil2.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtil2.mc.player);
    }

    public static void clickWindow(int n, ClickType clickType) {
    }

    public static int findStackInventory(Item item) {
        return InventoryUtil2.findStackInventory(item, false);
    }

    public static int pickItem(int n) {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        for (int i = 0; i < 9; ++i) {
            if (Item.getIdFromItem((Item)((ItemStack)InventoryUtil2.mc.player.inventory.mainInventory.get(i)).getItem()) != n) continue;
            arrayList.add(InventoryUtil2.mc.player.inventory.mainInventory.get(i));
        }
        if (arrayList.size() >= 1) {
            return InventoryUtil2.mc.player.inventory.mainInventory.indexOf(arrayList.get(0));
        }
        return -1;
    }

    public static int findInventoryWool(boolean bl) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(-1);
        for (Map.Entry<Integer, ItemStack> entry : InventoryUtil2.getInventoryAndHotbarSlots().entrySet()) {
            if (!(entry.getValue().getItem() instanceof ItemBlock)) continue;
            ItemBlock itemBlock = (ItemBlock)entry.getValue().getItem();
            if (itemBlock.getBlock().blockMaterial != Material.CLOTH || entry.getKey() == 45 && !bl) continue;
            atomicInteger.set(entry.getKey());
            return atomicInteger.get();
        }
        return atomicInteger.get();
    }

    public static int getEmptyXCarry() {
        for (int i = 1; i < 5; ++i) {
            Slot slot = (Slot)InventoryUtil2.mc.player.inventoryContainer.inventorySlots.get(i);
            ItemStack itemStack = slot.getStack();
            if (!itemStack.func_190926_b() && itemStack.getItem() != Items.field_190931_a) continue;
            return i;
        }
        return -1;
    }

    public static void setSlot(int n) {
        if (n > 8 || n < 0) {
            return;
        }
        InventoryUtil2.mc.player.inventory.currentItem = n;
    }

    public static int findHotbarBlock(Class class_) {
        for (int i = 0; i < 9; ++i) {
            Block block;
            ItemStack itemStack = InventoryUtil2.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.field_190927_a) continue;
            if (class_.isInstance((Object)itemStack.getItem())) {
                return i;
            }
            if (!(itemStack.getItem() instanceof ItemBlock) || !class_.isInstance((Object)(block = ((ItemBlock)itemStack.getItem()).getBlock()))) continue;
            return i;
        }
        return -1;
    }

    public static int getItemHotbar(Item item) {
        for (int i = 0; i < 9; ++i) {
            Item item2 = InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem();
            if (Item.getIdFromItem((Item)item2) != Item.getIdFromItem((Item)item)) continue;
            return i;
        }
        return -1;
    }

    public static List<Integer> getItemInventory(Item item) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 9; i < 36; ++i) {
            Item item2 = InventoryUtil2.mc.player.inventory.getStackInSlot(i).getItem();
            if (!(item instanceof ItemBlock) || !((ItemBlock)item).getBlock().equals((Object)item)) continue;
            arrayList.add(i);
        }
        if (arrayList.size() == 0) {
            arrayList.add(-1);
        }
        return arrayList;
    }

    public static int getSlot() {
        return InventoryUtil2.mc.player.inventory.currentItem;
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

    public static void push() {
        currentItem = InventoryUtil2.mc.player.inventory.currentItem;
    }

    public static int amountInHotbar(Item item) {
        return InventoryUtil2.amountInHotbar(item, true);
    }

    public static void switchToHotbarSlot(Class class_, boolean bl) {
        int n = InventoryUtil2.findHotbarBlock(class_);
        if (n > -1) {
            InventoryUtil2.switchToHotbarSlot(n, bl);
        }
    }

    public static class Task {
        private final /* synthetic */ boolean quickClick;
        private final /* synthetic */ boolean update;
        private final /* synthetic */ int slot;

        public boolean isSwitching() {
            return !this.update;
        }

        public Task(int n) {
            this.slot = n;
            this.quickClick = false;
            this.update = false;
        }

        public void run() {
            if (this.update) {
                Util.mc.playerController.updateController();
            }
            if (this.slot != -1) {
                Util.mc.playerController.windowClick(0, this.slot, 0, this.quickClick ? ClickType.QUICK_MOVE : ClickType.PICKUP, (EntityPlayer)Util.mc.player);
            }
        }

        public Task(int n, boolean bl) {
            this.slot = n;
            this.quickClick = bl;
            this.update = false;
        }

        public Task() {
            this.update = true;
            this.slot = -1;
            this.quickClick = false;
        }
    }

    public static enum Switch {
        NORMAL,
        SILENT,
        NONE;

    }
}

