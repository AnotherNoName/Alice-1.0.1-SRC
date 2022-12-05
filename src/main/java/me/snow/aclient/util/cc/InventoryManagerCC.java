//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.util.cc;

import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.mixin.mixins.accessors.IPlayerControllerMP;
import me.snow.aclient.util.Util;
import net.minecraft.block.Block;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InventoryManagerCC
implements Util {
    private /* synthetic */ int serverSlot;

    public void switchToBlock(Block block, Switch switch_) {
        this.switchToItem(Item.getItemFromBlock((Block)block), switch_);
    }

    public void syncSlot() {
        if (this.serverSlot != InventoryManagerCC.mc.player.inventory.currentItem) {
            InventoryManagerCC.mc.player.inventory.currentItem = this.serverSlot;
        }
    }

    public int searchSlot(Class<? extends Item> class_, InventoryRegion inventoryRegion) {
        int n = -1;
        for (int i = inventoryRegion.getStart(); i <= inventoryRegion.getBound(); ++i) {
            if (!class_.isInstance((Object)InventoryManagerCC.mc.player.inventory.getStackInSlot(i).getItem())) continue;
            n = i;
            break;
        }
        return n;
    }

    public void switchToSlot(int n, Switch switch_) {
        if (InventoryPlayer.isHotbar((int)n) && InventoryManagerCC.mc.player.inventory.currentItem != n) {
            switch (switch_) {
                case NORMAL: {
                    InventoryManagerCC.mc.player.inventory.currentItem = n;
                    InventoryManagerCC.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
                    break;
                }
                case PACKET: {
                    InventoryManagerCC.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
                    ((IPlayerControllerMP)InventoryManagerCC.mc.playerController).setCurrentPlayerItem(n);
                }
            }
        }
    }

    public void switchToItem(Item[] arritem, Switch switch_) {
        int n = -1;
        for (int i = InventoryRegion.HOTBAR.getStart(); i < InventoryRegion.HOTBAR.getBound(); ++i) {
            for (Item item : arritem) {
                if (!InventoryManagerCC.mc.player.inventory.getStackInSlot(i).getItem().equals((Object)item)) continue;
                n = i;
                break;
            }
            if (n != -1) break;
        }
        this.switchToSlot(n, switch_);
    }

    public void switchToBlock(Block[] arrblock, Switch switch_) {
        Item[] arritem = new Item[arrblock.length];
        for (int i = 0; i < arrblock.length; ++i) {
            arritem[i] = Item.getItemFromBlock((Block)arrblock[i]);
        }
        this.switchToItem(arritem, switch_);
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketHeldItemChange) {
            if (!InventoryPlayer.isHotbar((int)((CPacketHeldItemChange)send.getPacket()).getSlotId())) {
                send.setCanceled(true);
                return;
            }
            this.serverSlot = ((CPacketHeldItemChange)send.getPacket()).getSlotId();
        }
    }

    public InventoryManagerCC() {
        this.serverSlot = -1;
    }

    public int searchSlot(Item item, InventoryRegion inventoryRegion) {
        int n = -1;
        for (int i = inventoryRegion.getStart(); i < inventoryRegion.getBound(); ++i) {
            if (!InventoryManagerCC.mc.player.inventory.getStackInSlot(i).getItem().equals((Object)item)) continue;
            n = i;
            break;
        }
        return n;
    }

    public void switchToItem(Item item, Switch switch_) {
        int n = this.searchSlot(item, InventoryRegion.HOTBAR);
        this.switchToSlot(n, switch_);
    }

    public void switchToItem(Class<? extends Item> class_, Switch switch_) {
        int n = this.searchSlot(class_, InventoryRegion.HOTBAR);
        this.switchToSlot(n, switch_);
    }

    public int searchSlot(Block[] arrblock, InventoryRegion inventoryRegion) {
        int n = -1;
        block0: for (int i = inventoryRegion.getStart(); i < inventoryRegion.getBound(); ++i) {
            for (Block block : arrblock) {
                if (n != -1 || !InventoryManagerCC.mc.player.inventory.getStackInSlot(i).getItem().equals((Object)Item.getItemFromBlock((Block)block))) continue;
                n = i;
                continue block0;
            }
        }
        return n;
    }

    public int getServerSlot() {
        return this.serverSlot;
    }

    public static enum InventoryRegion {
        INVENTORY(0, 45),
        HOTBAR(0, 8),
        CRAFTING(80, 83),
        ARMOR(100, 103);

        private final /* synthetic */ int start;
        private final /* synthetic */ int bound;

        public int getBound() {
            return this.bound;
        }

        public int getStart() {
            return this.start;
        }

        private InventoryRegion(int n2, int n3) {
            this.start = n2;
            this.bound = n3;
        }
    }

    public static enum Switch {
        NORMAL,
        PACKET,
        NONE;

    }
}

