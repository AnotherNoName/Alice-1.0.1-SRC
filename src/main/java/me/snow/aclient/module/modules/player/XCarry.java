//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Slot
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketCloseWindow
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 */
package me.snow.aclient.module.modules.player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.gui.LuigiGui;
import me.snow.aclient.core.setting.Bind;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.ClientEvent;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.ReflectionUtil;
import me.snow.aclient.util.Util;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class XCarry
extends Module {
    private final /* synthetic */ Setting<Integer> tasks;
    private final /* synthetic */ Setting<Boolean> simpleMode;
    private final /* synthetic */ Setting<Integer> obbySlot;
    private final /* synthetic */ AtomicBoolean guiNeedsClose;
    private final /* synthetic */ Setting<Integer> slot3;
    private /* synthetic */ boolean obbySlotDone;
    private final /* synthetic */ Setting<Boolean> store;
    private final /* synthetic */ Setting<Bind> autoStore;
    private /* synthetic */ boolean slot1done;
    private static /* synthetic */ XCarry INSTANCE;
    private /* synthetic */ boolean guiCloseGuard;
    private final /* synthetic */ Setting<Bind> keyBind;
    private final /* synthetic */ Setting<Integer> slot1;
    private final /* synthetic */ Setting<Integer> slot2;
    private final /* synthetic */ Setting<Boolean> withShift;
    private /* synthetic */ boolean autoDuelOn;
    private /* synthetic */ GuiInventory openedGui;
    private final /* synthetic */ Setting<Boolean> shiftClicker;
    private /* synthetic */ boolean slot2done;
    private final /* synthetic */ Queue<InventoryUtil.Task> taskList;
    private /* synthetic */ boolean slot3done;
    private /* synthetic */ List<Integer> doneSlots;

    @SubscribeEvent
    public void onCloseGuiScreen(PacketEvent.Send send) {
        if (this.simpleMode.getValue().booleanValue() && send.getPacket() instanceof CPacketCloseWindow) {
            CPacketCloseWindow cPacketCloseWindow = (CPacketCloseWindow)send.getPacket();
            if (cPacketCloseWindow.windowId == XCarry.mc.player.inventoryContainer.windowId) {
                send.setCanceled(true);
            }
        }
    }

    private void addTasks(int n) {
        if (InventoryUtil.getEmptyXCarry() != -1) {
            int n2 = InventoryUtil.getEmptyXCarry();
            if (!(!this.doneSlots.contains(n2) && InventoryUtil.isSlotEmpty(n2) || !this.doneSlots.contains(++n2) && InventoryUtil.isSlotEmpty(n2) || !this.doneSlots.contains(++n2) && InventoryUtil.isSlotEmpty(n2) || !this.doneSlots.contains(++n2) && InventoryUtil.isSlotEmpty(n2))) {
                return;
            }
            if (n2 > 4) {
                return;
            }
            this.doneSlots.add(n2);
            this.taskList.add(new InventoryUtil.Task(n));
            this.taskList.add(new InventoryUtil.Task(n2));
            this.taskList.add(new InventoryUtil.Task());
        }
    }

    @SubscribeEvent
    public void onSettingChange(ClientEvent clientEvent) {
        if (clientEvent.getStage() == 2 && clientEvent.getSetting() != null && clientEvent.getSetting().getFeature() != null && clientEvent.getSetting().getFeature().equals(this)) {
            Setting setting = clientEvent.getSetting();
            String string = clientEvent.getSetting().getName();
            if (setting.equals(this.simpleMode) && setting.getPlannedValue() != setting.getValue()) {
                this.disable();
            } else if (string.equalsIgnoreCase("Store")) {
                clientEvent.setCanceled(true);
                this.autoDuelOn = !this.autoDuelOn;
                Command.sendMessage("<XCarry> \u00a7aAutostoring...");
            }
        }
    }

    @Override
    public void onLogout() {
        this.onDisable();
    }

    private void close() {
        this.openedGui = null;
        this.guiNeedsClose.set(false);
        this.guiCloseGuard = false;
    }

    public XCarry() {
        super("XCarry", "Uses the crafting inventory for storage", Module.Category.PLAYER, true, false, false);
        this.simpleMode = this.register(new Setting<Boolean>("Simple", false));
        this.autoStore = this.register(new Setting<Bind>("AutoDuel", new Bind(-1)));
        this.obbySlot = this.register(new Setting<Object>("ObbySlot", Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(9), object -> this.autoStore.getValue().getKey() != -1));
        this.slot1 = this.register(new Setting<Object>("Slot1", Integer.valueOf(22), Integer.valueOf(9), Integer.valueOf(44), object -> this.autoStore.getValue().getKey() != -1));
        this.slot2 = this.register(new Setting<Object>("Slot2", Integer.valueOf(23), Integer.valueOf(9), Integer.valueOf(44), object -> this.autoStore.getValue().getKey() != -1));
        this.slot3 = this.register(new Setting<Object>("Slot3", Integer.valueOf(24), Integer.valueOf(9), Integer.valueOf(44), object -> this.autoStore.getValue().getKey() != -1));
        this.tasks = this.register(new Setting<Object>("Actions", Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(12), object -> this.autoStore.getValue().getKey() != -1));
        this.store = this.register(new Setting<Boolean>("Store", false));
        this.shiftClicker = this.register(new Setting<Boolean>("ShiftClick", false));
        this.withShift = this.register(new Setting<Object>("WithShift", Boolean.TRUE, object -> this.shiftClicker.getValue()));
        this.keyBind = this.register(new Setting<Object>("ShiftBind", new Bind(-1), object -> this.shiftClicker.getValue()));
        this.guiNeedsClose = new AtomicBoolean(false);
        this.taskList = new ConcurrentLinkedQueue<InventoryUtil.Task>();
        this.doneSlots = new ArrayList<Integer>();
        this.setInstance();
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onGuiOpen(GuiOpenEvent guiOpenEvent) {
        if (!this.simpleMode.getValue().booleanValue()) {
            if (this.guiCloseGuard) {
                guiOpenEvent.setCanceled(true);
            } else if (guiOpenEvent.getGui() instanceof GuiInventory) {
                this.openedGui = this.createGuiWrapper((GuiInventory)guiOpenEvent.getGui());
                guiOpenEvent.setGui((GuiScreen)this.openedGui);
                this.guiNeedsClose.set(false);
            }
        }
    }

    private GuiInventory createGuiWrapper(GuiInventory guiInventory) {
        try {
            GuiInventoryWrapper guiInventoryWrapper = new GuiInventoryWrapper();
            ReflectionUtil.copyOf(guiInventory, guiInventoryWrapper);
            return guiInventoryWrapper;
        }
        catch (IllegalAccessException | NoSuchFieldException reflectiveOperationException) {
            reflectiveOperationException.printStackTrace();
            return null;
        }
    }

    public static XCarry getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new XCarry();
        }
        return INSTANCE;
    }

    @Override
    public void onUpdate() {
        if (this.shiftClicker.getValue().booleanValue() && XCarry.mc.currentScreen instanceof GuiInventory) {
            Slot slot;
            boolean bl;
            boolean bl2 = bl = this.keyBind.getValue().getKey() != -1 && Keyboard.isKeyDown((int)this.keyBind.getValue().getKey()) && !Keyboard.isKeyDown((int)42);
            if ((Keyboard.isKeyDown((int)42) && this.withShift.getValue().booleanValue() || bl) && Mouse.isButtonDown((int)0) && (slot = ((GuiInventory)XCarry.mc.currentScreen).getSlotUnderMouse()) != null && InventoryUtil.getEmptyXCarry() != -1) {
                int n = slot.slotNumber;
                if (n > 4 && bl) {
                    this.taskList.add(new InventoryUtil.Task(n));
                    this.taskList.add(new InventoryUtil.Task(InventoryUtil.getEmptyXCarry()));
                } else if (n > 4 && this.withShift.getValue().booleanValue()) {
                    boolean bl3 = true;
                    boolean bl4 = true;
                    for (int n2 : InventoryUtil.findEmptySlots(false)) {
                        if (n2 > 4 && n2 < 36) {
                            bl4 = false;
                            continue;
                        }
                        if (n2 <= 35 || n2 >= 45) continue;
                        bl3 = false;
                    }
                    if (n > 35 && n < 45) {
                        if (bl4) {
                            this.taskList.add(new InventoryUtil.Task(n));
                            this.taskList.add(new InventoryUtil.Task(InventoryUtil.getEmptyXCarry()));
                        }
                    } else if (bl3) {
                        this.taskList.add(new InventoryUtil.Task(n));
                        this.taskList.add(new InventoryUtil.Task(InventoryUtil.getEmptyXCarry()));
                    }
                }
            }
        }
        if (this.autoDuelOn) {
            this.doneSlots = new ArrayList<Integer>();
            if (InventoryUtil.getEmptyXCarry() == -1 || this.obbySlotDone && this.slot1done && this.slot2done && this.slot3done) {
                this.autoDuelOn = false;
            }
            if (this.autoDuelOn) {
                if (!this.obbySlotDone && !XCarry.mc.player.inventory.getStackInSlot((int)(this.obbySlot.getValue().intValue() - 1)).field_190928_g) {
                    this.addTasks(36 + this.obbySlot.getValue() - 1);
                }
                this.obbySlotDone = true;
                if (!this.slot1done && !((Slot)XCarry.mc.player.inventoryContainer.inventorySlots.get((int)this.slot1.getValue().intValue())).getStack().field_190928_g) {
                    this.addTasks(this.slot1.getValue());
                }
                this.slot1done = true;
                if (!this.slot2done && !((Slot)XCarry.mc.player.inventoryContainer.inventorySlots.get((int)this.slot2.getValue().intValue())).getStack().field_190928_g) {
                    this.addTasks(this.slot2.getValue());
                }
                this.slot2done = true;
                if (!this.slot3done && !((Slot)XCarry.mc.player.inventoryContainer.inventorySlots.get((int)this.slot3.getValue().intValue())).getStack().field_190928_g) {
                    this.addTasks(this.slot3.getValue());
                }
                this.slot3done = true;
            }
        } else {
            this.obbySlotDone = false;
            this.slot1done = false;
            this.slot2done = false;
            this.slot3done = false;
        }
        if (!this.taskList.isEmpty()) {
            for (int i = 0; i < this.tasks.getValue(); ++i) {
                InventoryUtil.Task task = this.taskList.poll();
                if (task == null) continue;
                task.run();
            }
        }
    }

    @Override
    public void onDisable() {
        if (XCarry.fullNullCheck()) {
            return;
        }
        if (!this.simpleMode.getValue().booleanValue()) {
            this.closeGui();
            this.close();
        } else {
            XCarry.mc.player.connection.sendPacket((Packet)new CPacketCloseWindow(XCarry.mc.player.inventoryContainer.windowId));
        }
    }

    private void setInstance() {
        INSTANCE = this;
    }

    private void closeGui() {
        if (XCarry.fullNullCheck()) {
            return;
        }
        if (this.guiNeedsClose.compareAndSet(true, false) && !XCarry.fullNullCheck()) {
            this.guiCloseGuard = true;
            XCarry.mc.player.closeScreen();
            if (this.openedGui != null) {
                this.openedGui.onGuiClosed();
                this.openedGui = null;
            }
            this.guiCloseGuard = false;
        }
    }

    static {
        INSTANCE = new XCarry();
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent keyInputEvent) {
        if (Keyboard.getEventKeyState() && !(XCarry.mc.currentScreen instanceof LuigiGui) && this.autoStore.getValue().getKey() == Keyboard.getEventKey()) {
            this.autoDuelOn = !this.autoDuelOn;
            Command.sendMessage("<XCarry> \u00a7aAutostoring...");
        }
    }

    private class GuiInventoryWrapper
    extends GuiInventory {
        public void onGuiClosed() {
            if (XCarry.this.guiCloseGuard || !XCarry.this.isEnabled()) {
                super.onGuiClosed();
            }
        }

        GuiInventoryWrapper() {
            super((EntityPlayer)Util.mc.player);
        }

        protected void keyTyped(char c, int n) throws IOException {
            if (XCarry.this.isEnabled() && (n == 1 || this.mc.gameSettings.keyBindInventory.isActiveAndMatches(n))) {
                XCarry.this.guiNeedsClose.set(true);
                this.mc.displayGuiScreen(null);
            } else {
                super.keyTyped(c, n);
            }
        }
    }
}

