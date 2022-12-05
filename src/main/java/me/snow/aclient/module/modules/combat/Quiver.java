//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.init.PotionTypes
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemAir
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemTippedArrow
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.potion.PotionUtils
 *  net.minecraft.util.math.BlockPos
 */
package me.snow.aclient.module.modules.combat;

import java.util.ArrayList;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.RotationUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTippedArrow;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.BlockPos;

public class Quiver
extends Module {
    private /* synthetic */ int oldSlot;
    private final /* synthetic */ Timer delayTimer;
    private final /* synthetic */ Timer holdTimer;
    private /* synthetic */ int speedSlot;
    private /* synthetic */ ArrayList<Integer> map;
    private /* synthetic */ int strSlot;
    private final /* synthetic */ Setting<Integer> holdLength;
    private final /* synthetic */ Setting<mainEnum> secondary;
    private /* synthetic */ int stage;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Setting<mainEnum> main;

    private void switchTo(Enum<mainEnum> enum_) {
        if (enum_.toString().equalsIgnoreCase("STRENGTH") && this.strSlot != -1) {
            this.switchTo(this.strSlot);
        }
        if (enum_.toString().equalsIgnoreCase("SPEED") && this.speedSlot != -1) {
            this.switchTo(this.speedSlot);
        }
    }

    @Override
    public void onUpdate() {
        int n;
        Object object;
        if (Quiver.nullCheck()) {
            return;
        }
        if (Quiver.mc.currentScreen != null) {
            return;
        }
        if (InventoryUtil.findItemInventorySlot((Item)Items.BOW, true) == -1) {
            Command.sendMessage("Couldn't find bow in inventory! Toggling!");
            this.toggle();
        }
        RotationUtil.faceVector(EntityUtil.getInterpolatedPos((Entity)Quiver.mc.player, Quiver.mc.timer.field_194148_c).addVector(0.0, 3.0, 0.0), false);
        if (this.stage == 0) {
            this.map = this.mapArrows();
            object = this.map.iterator();
            while (object.hasNext()) {
                n = object.next();
                ItemStack itemStack = (ItemStack)Quiver.mc.player.inventoryContainer.getInventory().get(n);
                if ((PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.STRENGTH) || PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.STRONG_STRENGTH) || PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.LONG_STRENGTH)) && this.strSlot == -1) {
                    this.strSlot = n;
                }
                if (!PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.SWIFTNESS) && !PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.LONG_SWIFTNESS) && !PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.STRONG_SWIFTNESS) || this.speedSlot != -1) continue;
                this.speedSlot = n;
            }
            ++this.stage;
        } else if (this.stage == 1) {
            if (!this.delayTimer.passedMs(this.delay.getValue().intValue())) {
                return;
            }
            this.delayTimer.reset();
            ++this.stage;
        } else if (this.stage == 2) {
            this.switchTo(this.main.getValue());
            ++this.stage;
        } else if (this.stage == 3) {
            if (!this.delayTimer.passedMs(this.delay.getValue().intValue())) {
                return;
            }
            this.delayTimer.reset();
            ++this.stage;
        } else if (this.stage == 4) {
            Quiver.mc.gameSettings.keyBindUseItem.pressed = true;
            this.holdTimer.reset();
            ++this.stage;
        } else if (this.stage == 5) {
            if (!this.holdTimer.passedMs(this.holdLength.getValue().intValue())) {
                return;
            }
            this.holdTimer.reset();
            ++this.stage;
        } else if (this.stage == 6) {
            Quiver.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, Quiver.mc.player.getHorizontalFacing()));
            Quiver.mc.player.resetActiveHand();
            Quiver.mc.gameSettings.keyBindUseItem.pressed = false;
            ++this.stage;
        } else if (this.stage == 7) {
            if (!this.delayTimer.passedMs(this.delay.getValue().intValue())) {
                return;
            }
            this.delayTimer.reset();
            ++this.stage;
        } else if (this.stage == 8) {
            this.map = this.mapArrows();
            this.strSlot = -1;
            this.speedSlot = -1;
            object = this.map.iterator();
            while (object.hasNext()) {
                n = object.next();
                ItemStack itemStack = (ItemStack)Quiver.mc.player.inventoryContainer.getInventory().get(n);
                if ((PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.STRENGTH) || PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.STRONG_STRENGTH) || PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.LONG_STRENGTH)) && this.strSlot == -1) {
                    this.strSlot = n;
                }
                if (!PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.SWIFTNESS) && !PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.LONG_SWIFTNESS) && !PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.STRONG_SWIFTNESS) || this.speedSlot != -1) continue;
                this.speedSlot = n;
            }
            ++this.stage;
        }
        if (this.stage == 9) {
            this.switchTo(this.secondary.getValue());
            ++this.stage;
        } else if (this.stage == 10) {
            if (!this.delayTimer.passedMs(this.delay.getValue().intValue())) {
                return;
            }
            ++this.stage;
        } else if (this.stage == 11) {
            Quiver.mc.gameSettings.keyBindUseItem.pressed = true;
            this.holdTimer.reset();
            ++this.stage;
        } else if (this.stage == 12) {
            if (!this.holdTimer.passedMs(this.holdLength.getValue().intValue())) {
                return;
            }
            this.holdTimer.reset();
            ++this.stage;
        } else if (this.stage == 13) {
            Quiver.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, Quiver.mc.player.getHorizontalFacing()));
            Quiver.mc.player.resetActiveHand();
            Quiver.mc.gameSettings.keyBindUseItem.pressed = false;
            ++this.stage;
        } else if (this.stage == 14) {
            object = this.mapEmpty();
            if (!((ArrayList)object).isEmpty()) {
                n = (Integer)((ArrayList)object).get(0);
                Quiver.mc.playerController.windowClick(Quiver.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)Quiver.mc.player);
            }
            ++this.stage;
        } else if (this.stage == 15) {
            this.setEnabled(false);
        }
    }

    @Override
    public void onEnable() {
        if (Quiver.nullCheck()) {
            return;
        }
        InventoryUtil.switchToHotbarSlot(ItemBow.class, false);
        this.clean();
        this.oldSlot = Quiver.mc.player.inventory.currentItem;
        Quiver.mc.gameSettings.keyBindUseItem.pressed = false;
    }

    private ArrayList<Integer> mapEmpty() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 9; i < 45; ++i) {
            if (!(((ItemStack)Quiver.mc.player.inventoryContainer.getInventory().get(i)).getItem() instanceof ItemAir) && Quiver.mc.player.inventoryContainer.getInventory().get(i) != ItemStack.field_190927_a) continue;
            arrayList.add(i);
        }
        return arrayList;
    }

    private ArrayList<Integer> mapArrows() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 9; i < 45; ++i) {
            if (!(((ItemStack)Quiver.mc.player.inventoryContainer.getInventory().get(i)).getItem() instanceof ItemTippedArrow)) continue;
            ItemStack itemStack = (ItemStack)Quiver.mc.player.inventoryContainer.getInventory().get(i);
            if (PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.STRENGTH) || PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.STRONG_STRENGTH) || PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.LONG_STRENGTH)) {
                arrayList.add(i);
            }
            if (!PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.SWIFTNESS) && !PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.LONG_SWIFTNESS) && !PotionUtils.getPotionFromItem((ItemStack)itemStack).equals((Object)PotionTypes.STRONG_SWIFTNESS)) continue;
            arrayList.add(i);
        }
        return arrayList;
    }

    @Override
    public void onDisable() {
        if (Quiver.nullCheck()) {
            return;
        }
        InventoryUtil.switchToHotbarSlot(this.oldSlot, false);
        Quiver.mc.gameSettings.keyBindUseItem.pressed = false;
        this.clean();
    }

    private void clean() {
        this.holdTimer.reset();
        this.delayTimer.reset();
        this.map = null;
        this.speedSlot = -1;
        this.strSlot = -1;
        this.stage = 0;
    }

    public Quiver() {
        super("Quiver", "Automatically shoots yourself with good effects.", Module.Category.COMBAT, true, false, false);
        this.delay = this.register(new Setting<Integer>("Delay", 0, 0, 500));
        this.holdLength = this.register(new Setting<Integer>("Hold Length", 240, 100, 1000));
        this.main = this.register(new Setting<mainEnum>("Main", mainEnum.SPEED));
        this.secondary = this.register(new Setting<mainEnum>("Secondary", mainEnum.STRENGTH));
        this.delayTimer = new Timer();
        this.holdTimer = new Timer();
        this.strSlot = -1;
        this.speedSlot = -1;
        this.oldSlot = 1;
    }

    private void switchTo(int n) {
        if (n == 9) {
            return;
        }
        Quiver.mc.playerController.windowClick(Quiver.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)Quiver.mc.player);
        Quiver.mc.playerController.windowClick(Quiver.mc.player.inventoryContainer.windowId, 9, 0, ClickType.PICKUP, (EntityPlayer)Quiver.mc.player);
        Quiver.mc.playerController.windowClick(Quiver.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)Quiver.mc.player);
        Quiver.mc.playerController.updateController();
    }

    private static enum mainEnum {
        STRENGTH,
        SPEED;

    }
}

