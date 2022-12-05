//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemExpBottle
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  org.lwjgl.input.Keyboard
 */
package me.snow.aclient.module.modules.combat;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.gui.LuigiGui;
import me.snow.aclient.core.setting.Bind;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.player.XCarry;
import me.snow.aclient.util.DamageUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class AutoArmor
extends Module {
    private final /* synthetic */ Setting<Integer> chestThreshold;
    private final /* synthetic */ Setting<Integer> legThreshold;
    private final /* synthetic */ Setting<Boolean> mendingTakeOff;
    private final /* synthetic */ Setting<Integer> actions;
    private final /* synthetic */ Setting<Integer> bootsThreshold;
    private final /* synthetic */ Setting<Bind> elytraBind;
    private final /* synthetic */ Setting<Integer> helmetThreshold;
    private final /* synthetic */ Setting<Boolean> curse;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ List<Integer> doneSlots;
    private final /* synthetic */ Queue<InventoryUtil.Task> taskList;
    private final /* synthetic */ Setting<Integer> closestEnemy;
    private /* synthetic */ boolean elytraOn;
    private final /* synthetic */ Setting<Boolean> updateController;
    private final /* synthetic */ Timer elytraTimer;
    private final /* synthetic */ Setting<Boolean> shiftClick;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Boolean> tps;

    private boolean isSafe() {
        EntityPlayer entityPlayer = EntityUtil.getClosestEnemy(this.closestEnemy.getValue().intValue());
        if (entityPlayer == null) {
            return true;
        }
        return AutoArmor.mc.player.getDistanceSqToEntity((Entity)entityPlayer) >= MathUtil.square(this.closestEnemy.getValue().intValue());
    }

    @Override
    public void onDisable() {
        this.taskList.clear();
        this.doneSlots.clear();
        this.elytraOn = false;
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent keyInputEvent) {
        if (Keyboard.getEventKeyState() && !(AutoArmor.mc.currentScreen instanceof LuigiGui) && this.elytraBind.getValue().getKey() == Keyboard.getEventKey()) {
            this.elytraOn = !this.elytraOn;
        }
    }

    @Override
    public void onTick() {
        int n;
        if (AutoArmor.fullNullCheck() || AutoArmor.mc.currentScreen instanceof GuiContainer && !(AutoArmor.mc.currentScreen instanceof GuiInventory)) {
            return;
        }
        if (this.taskList.isEmpty()) {
            int n2;
            int n3;
            ItemStack itemStack;
            int n4;
            if (this.mendingTakeOff.getValue().booleanValue() && InventoryUtil.holdingItem(ItemExpBottle.class) && AutoArmor.mc.gameSettings.keyBindUseItem.isKeyDown() && (this.isSafe() || EntityUtil.isSafe((Entity)AutoArmor.mc.player, 1, false, true))) {
                ItemStack itemStack2 = AutoArmor.mc.player.inventoryContainer.getSlot(5).getStack();
                if (!itemStack2.field_190928_g && DamageUtil.getRoundedDamage(itemStack2) >= this.helmetThreshold.getValue()) {
                    this.takeOffSlot(5);
                }
                ItemStack itemStack3 = AutoArmor.mc.player.inventoryContainer.getSlot(6).getStack();
                if (!itemStack3.field_190928_g && DamageUtil.getRoundedDamage(itemStack3) >= this.chestThreshold.getValue()) {
                    this.takeOffSlot(6);
                }
                ItemStack itemStack4 = AutoArmor.mc.player.inventoryContainer.getSlot(7).getStack();
                if (!itemStack4.field_190928_g && DamageUtil.getRoundedDamage(itemStack4) >= this.legThreshold.getValue()) {
                    this.takeOffSlot(7);
                }
                ItemStack itemStack5 = AutoArmor.mc.player.inventoryContainer.getSlot(8).getStack();
                if (!itemStack5.field_190928_g && DamageUtil.getRoundedDamage(itemStack5) >= this.bootsThreshold.getValue()) {
                    this.takeOffSlot(8);
                }
                return;
            }
            ItemStack itemStack6 = AutoArmor.mc.player.inventoryContainer.getSlot(5).getStack();
            if (itemStack6.getItem() == Items.field_190931_a && (n4 = InventoryUtil.findArmorSlot(EntityEquipmentSlot.HEAD, this.curse.getValue(), XCarry.getInstance().isOn())) != -1) {
                this.getSlotOn(5, n4);
            }
            if ((itemStack = AutoArmor.mc.player.inventoryContainer.getSlot(6).getStack()).getItem() == Items.field_190931_a) {
                if (this.taskList.isEmpty()) {
                    if (this.elytraOn && this.elytraTimer.passedMs(500L)) {
                        int n5 = InventoryUtil.findItemInventorySlot(Items.ELYTRA, false, XCarry.getInstance().isOn());
                        if (n5 != -1) {
                            if (n5 < 5 && n5 > 1 || !this.shiftClick.getValue().booleanValue()) {
                                this.taskList.add(new InventoryUtil.Task(n5));
                                this.taskList.add(new InventoryUtil.Task(6));
                            } else {
                                this.taskList.add(new InventoryUtil.Task(n5, true));
                            }
                            if (this.updateController.getValue().booleanValue()) {
                                this.taskList.add(new InventoryUtil.Task());
                            }
                            this.elytraTimer.reset();
                        }
                    } else if (!this.elytraOn && (n3 = InventoryUtil.findArmorSlot(EntityEquipmentSlot.CHEST, this.curse.getValue(), XCarry.getInstance().isOn())) != -1) {
                        this.getSlotOn(6, n3);
                    }
                }
            } else if (this.elytraOn && itemStack.getItem() != Items.ELYTRA && this.elytraTimer.passedMs(500L)) {
                if (this.taskList.isEmpty()) {
                    n3 = InventoryUtil.findItemInventorySlot(Items.ELYTRA, false, XCarry.getInstance().isOn());
                    if (n3 != -1) {
                        this.taskList.add(new InventoryUtil.Task(n3));
                        this.taskList.add(new InventoryUtil.Task(6));
                        this.taskList.add(new InventoryUtil.Task(n3));
                        if (this.updateController.getValue().booleanValue()) {
                            this.taskList.add(new InventoryUtil.Task());
                        }
                    }
                    this.elytraTimer.reset();
                }
            } else if (!this.elytraOn && itemStack.getItem() == Items.ELYTRA && this.elytraTimer.passedMs(500L) && this.taskList.isEmpty()) {
                n3 = InventoryUtil.findItemInventorySlot((Item)Items.DIAMOND_CHESTPLATE, false, XCarry.getInstance().isOn());
                if (n3 == -1 && (n3 = InventoryUtil.findItemInventorySlot((Item)Items.IRON_CHESTPLATE, false, XCarry.getInstance().isOn())) == -1 && (n3 = InventoryUtil.findItemInventorySlot((Item)Items.GOLDEN_CHESTPLATE, false, XCarry.getInstance().isOn())) == -1 && (n3 = InventoryUtil.findItemInventorySlot((Item)Items.CHAINMAIL_CHESTPLATE, false, XCarry.getInstance().isOn())) == -1) {
                    n3 = InventoryUtil.findItemInventorySlot((Item)Items.LEATHER_CHESTPLATE, false, XCarry.getInstance().isOn());
                }
                if (n3 != -1) {
                    this.taskList.add(new InventoryUtil.Task(n3));
                    this.taskList.add(new InventoryUtil.Task(6));
                    this.taskList.add(new InventoryUtil.Task(n3));
                    if (this.updateController.getValue().booleanValue()) {
                        this.taskList.add(new InventoryUtil.Task());
                    }
                }
                this.elytraTimer.reset();
            }
            if (AutoArmor.mc.player.inventoryContainer.getSlot(7).getStack().getItem() == Items.field_190931_a && (n2 = InventoryUtil.findArmorSlot(EntityEquipmentSlot.LEGS, this.curse.getValue(), XCarry.getInstance().isOn())) != -1) {
                this.getSlotOn(7, n2);
            }
            if (AutoArmor.mc.player.inventoryContainer.getSlot(8).getStack().getItem() == Items.field_190931_a && (n = InventoryUtil.findArmorSlot(EntityEquipmentSlot.FEET, this.curse.getValue(), XCarry.getInstance().isOn())) != -1) {
                this.getSlotOn(8, n);
            }
        }
        if (this.timer.passedMs((int)((float)this.delay.getValue().intValue() * (this.tps.getValue() != false ? AliceMain.serverManager.getTpsFactor() : 1.0f)))) {
            if (!this.taskList.isEmpty()) {
                for (n = 0; n < this.actions.getValue(); ++n) {
                    InventoryUtil.Task task = this.taskList.poll();
                    if (task == null) continue;
                    task.run();
                }
            }
            this.timer.reset();
        }
    }

    @Override
    public String getDisplayInfo() {
        if (this.elytraOn) {
            return "Elytra";
        }
        return null;
    }

    @Override
    public void onLogin() {
        this.timer.reset();
        this.elytraTimer.reset();
    }

    public AutoArmor() {
        super("AutoArmor", "Puts Armor on for you.", Module.Category.COMBAT, true, false, false);
        this.delay = this.register(new Setting<Integer>("Delay", 50, 0, 500));
        this.mendingTakeOff = this.register(new Setting<Boolean>("AutoMend", true));
        this.closestEnemy = this.register(new Setting<Object>("Enemy", Integer.valueOf(8), Integer.valueOf(1), Integer.valueOf(20), object -> this.mendingTakeOff.getValue()));
        this.helmetThreshold = this.register(new Setting<Object>("Helmet%", Integer.valueOf(100), Integer.valueOf(1), Integer.valueOf(100), object -> this.mendingTakeOff.getValue()));
        this.chestThreshold = this.register(new Setting<Object>("Chest%", Integer.valueOf(100), Integer.valueOf(1), Integer.valueOf(100), object -> this.mendingTakeOff.getValue()));
        this.legThreshold = this.register(new Setting<Object>("Legs%", Integer.valueOf(100), Integer.valueOf(1), Integer.valueOf(100), object -> this.mendingTakeOff.getValue()));
        this.bootsThreshold = this.register(new Setting<Object>("Boots%", Integer.valueOf(100), Integer.valueOf(1), Integer.valueOf(100), object -> this.mendingTakeOff.getValue()));
        this.curse = this.register(new Setting<Boolean>("CurseOfBinding", false));
        this.actions = this.register(new Setting<Integer>("Actions", 3, 1, 12));
        this.elytraBind = this.register(new Setting<Bind>("Elytra", new Bind(-1)));
        this.tps = this.register(new Setting<Boolean>("TpsSync", true));
        this.updateController = this.register(new Setting<Boolean>("Update", true));
        this.shiftClick = this.register(new Setting<Boolean>("ShiftClick", false));
        this.timer = new Timer();
        this.elytraTimer = new Timer();
        this.taskList = new ConcurrentLinkedQueue<InventoryUtil.Task>();
        this.doneSlots = new ArrayList<Integer>();
    }

    private void getSlotOn(int n, int n2) {
        if (this.taskList.isEmpty()) {
            this.doneSlots.remove((Object)n2);
            if (n2 < 5 && n2 > 0 || !this.shiftClick.getValue().booleanValue()) {
                this.taskList.add(new InventoryUtil.Task(n2));
                this.taskList.add(new InventoryUtil.Task(n));
            } else {
                this.taskList.add(new InventoryUtil.Task(n2, true));
            }
            if (this.updateController.getValue().booleanValue()) {
                this.taskList.add(new InventoryUtil.Task());
            }
        }
    }

    @Override
    public void onLogout() {
        this.taskList.clear();
        this.doneSlots.clear();
    }

    private void takeOffSlot(int n) {
        if (this.taskList.isEmpty()) {
            int n2 = -1;
            for (int n3 : InventoryUtil.findEmptySlots(XCarry.getInstance().isOn())) {
                if (this.doneSlots.contains(n2)) continue;
                n2 = n3;
                this.doneSlots.add(n3);
            }
            if (n2 != -1) {
                if (n2 < 5 && n2 > 0 || !this.shiftClick.getValue().booleanValue()) {
                    this.taskList.add(new InventoryUtil.Task(n));
                    this.taskList.add(new InventoryUtil.Task(n2));
                } else {
                    this.taskList.add(new InventoryUtil.Task(n, true));
                }
                if (this.updateController.getValue().booleanValue()) {
                    this.taskList.add(new InventoryUtil.Task());
                }
            }
        }
    }
}

