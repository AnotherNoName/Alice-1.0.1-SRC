//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.combat.autototem;

import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.MoveEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.combat.autototem.AutoTotemMode;
import me.snow.aclient.util.ca.util.CrystalUtilCa;
import me.snow.aclient.util.ca.util.EntityUtilCa;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoTotem
extends Module {
    public /* synthetic */ Setting<Integer> TotemHp;
    public /* synthetic */ Setting<Boolean> cancelMovement;
    public /* synthetic */ Setting<Boolean> GapSwitch;
    public /* synthetic */ Setting<Integer> fallDistanceHeight;
    private /* synthetic */ Setting<AutoTotemMode> mode;
    public /* synthetic */ Setting<Boolean> fallDistance;
    public /* synthetic */ Setting<Boolean> GapOnSword;
    private /* synthetic */ int timer;
    public /* synthetic */ Setting<Integer> cooldown;
    public /* synthetic */ Setting<Boolean> check32K;
    private /* synthetic */ int Tcount;
    public /* synthetic */ Setting<Boolean> CrystalCheck;
    public /* synthetic */ Setting<Boolean> TotemOnElytra;
    public /* synthetic */ Setting<Integer> HoleHP;
    public /* synthetic */ Setting<Boolean> GapOnPick;
    public /* synthetic */ Setting<Boolean> Always;

    private int getItemSlot(Item item) {
        if (item == AutoTotem.mc.player.getHeldItemOffhand().getItem()) {
            return -1;
        }
        for (int i = 36; i >= 0; --i) {
            Item item2 = AutoTotem.mc.player.inventory.getStackInSlot(i).getItem();
            if (item2 != item) continue;
            if (i < 9) {
                if (item == Items.GOLDEN_APPLE) {
                    return -1;
                }
                i += 36;
            }
            return i;
        }
        return -1;
    }

    public AutoTotem() {
        super("AutoTotem", "Allows you to switch up your Offhand.", Module.Category.COMBAT, true, false, false);
        this.mode = this.register(new Setting<AutoTotemMode>("Mode", AutoTotemMode.Totem));
        this.cancelMovement = this.register(new Setting<Boolean>("CancelMotion", false));
        this.TotemHp = this.register(new Setting<Integer>("TotemHP", 12, 0, 36));
        this.HoleHP = this.register(new Setting<Integer>("HoleHP", 12, 0, 36));
        this.GapSwitch = this.register(new Setting<Boolean>("GapSwap", false));
        this.GapOnSword = this.register(new Setting<Boolean>("SwordGap", Boolean.valueOf(false), bl -> this.GapSwitch.getValue()));
        this.GapOnPick = this.register(new Setting<Boolean>("PickGap", Boolean.valueOf(false), bl -> this.GapSwitch.getValue()));
        this.Always = this.register(new Setting<Boolean>("Always", Boolean.valueOf(false), bl -> this.GapSwitch.getValue()));
        this.CrystalCheck = this.register(new Setting<Boolean>("CrystalCheck", false));
        this.check32K = this.register(new Setting<Boolean>("32KCheck", false));
        this.cooldown = this.register(new Setting<Integer>("Cooldown", 0, 0, 40));
        this.fallDistance = this.register(new Setting<Boolean>("FallDistance", true));
        this.fallDistanceHeight = this.register(new Setting<Integer>("Height", Integer.valueOf(15), Integer.valueOf(0), Integer.valueOf(30), n -> this.fallDistance.getValue()));
        this.TotemOnElytra = this.register(new Setting<Boolean>("TotemOnElytra", false));
        this.timer = 0;
        this.Tcount = 0;
    }

    public void swapItems(int n) {
        if (n == -1 || this.timer <= this.cooldown.getValue() && AutoTotem.mc.player.inventory.getStackInSlot(n).getItem() != Items.field_190929_cY) {
            return;
        }
        this.timer = 0;
        AutoTotem.mc.playerController.windowClick(0, n, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
        AutoTotem.mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
        AutoTotem.mc.playerController.windowClick(0, n, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
        AutoTotem.mc.playerController.updateController();
    }

    public static int getTotemCount() {
        return AutoTotem.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem().equals((Object)Items.field_190929_cY)).mapToInt(ItemStack::func_190916_E).sum() + AutoTotem.mc.player.inventory.offHandInventory.stream().filter(itemStack -> itemStack.getItem().equals((Object)Items.field_190929_cY)).mapToInt(ItemStack::func_190916_E).sum();
    }

    @Override
    public void onUpdate() {
        if (AutoTotem.nullCheck()) {
            return;
        }
        if (AutoTotem.mc.currentScreen instanceof GuiContainer) {
            return;
        }
        float f = EntityUtilCa.getHealth((Entity)AutoTotem.mc.player);
        if (f < (float)this.TotemHp.getValue().intValue()) {
            if (this.cancelMovement.getValue().booleanValue()) {
                StopPlayerMovement.toggle(true);
            }
            this.swapItems(this.getItemSlot(Items.field_190929_cY));
            if (this.cancelMovement.getValue().booleanValue()) {
                StopPlayerMovement.toggle(false);
            }
        }
    }

    private boolean Check32K() {
        if (!this.check32K.getValue().booleanValue() || AutoTotem.mc.world == null || AutoTotem.mc.player == null) {
            return true;
        }
        for (Entity entity : AutoTotem.mc.world.loadedEntityList) {
            if (entity == AutoTotem.mc.player || !AliceMain.friendManager.isFriend(entity.getName()) || !(entity instanceof EntityPlayer) || !(entity.getDistanceToEntity((Entity)AutoTotem.mc.player) < 7.0f) || !EntityUtilCa.holding32k((EntityPlayer)entity)) continue;
            return true;
        }
        return true;
    }

    @Override
    public String getDisplayInfo() {
        return String.valueOf(new StringBuilder().append(this.Tcount).append(""));
    }

    @Override
    public void onTick() {
        if (AutoTotem.nullCheck()) {
            return;
        }
        ++this.timer;
        this.Tcount = AutoTotem.getTotemCount();
        if (AutoTotem.mc.currentScreen == null || AutoTotem.mc.currentScreen instanceof GuiInventory) {
            float f = AutoTotem.mc.player.getHealth() + AutoTotem.mc.player.getAbsorptionAmount();
            if (this.TotemOnElytra.getValue().booleanValue() && AutoTotem.mc.player.isElytraFlying() || this.fallDistance.getValue().booleanValue() && AutoTotem.mc.player.fallDistance >= (float)this.fallDistanceHeight.getValue().intValue() && !AutoTotem.mc.player.isElytraFlying()) {
                this.swapItems(this.getItemSlot(Items.field_190929_cY));
            } else if ((f > (float)this.TotemHp.getValue().intValue() || EntityUtilCa.isInHole((Entity)AutoTotem.mc.player) && f > (float)this.HoleHP.getValue().intValue()) && this.lethalToLocalCheck() && this.Check32K()) {
                if (!(this.mode.getValue() != AutoTotemMode.Crystal || (this.GapOnSword.getValue().booleanValue() && AutoTotem.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword || this.Always.getValue().booleanValue() || this.GapOnPick.getValue().booleanValue() && AutoTotem.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) && AutoTotem.mc.gameSettings.keyBindUseItem.isKeyDown() && this.GapSwitch.getValue().booleanValue())) {
                    this.swapItems(this.getItemSlot(Items.END_CRYSTAL));
                    return;
                }
                if ((this.GapOnSword.getValue().booleanValue() && AutoTotem.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword || this.Always.getValue().booleanValue() || this.GapOnPick.getValue().booleanValue() && AutoTotem.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) && AutoTotem.mc.gameSettings.keyBindUseItem.isKeyDown() && this.GapSwitch.getValue().booleanValue()) {
                    this.swapItems(this.getItemSlot(Items.GOLDEN_APPLE));
                    if (AutoTotem.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
                        AutoTotem.mc.playerController.isHittingBlock = true;
                    }
                    return;
                }
                if (this.mode.getValue() == AutoTotemMode.Totem) {
                    this.swapItems(this.getItemSlot(Items.field_190929_cY));
                    return;
                }
                if (this.mode.getValue() == AutoTotemMode.Gapple) {
                    this.swapItems(this.getItemSlot(Items.GOLDEN_APPLE));
                    return;
                }
            } else {
                this.swapItems(this.getItemSlot(Items.field_190929_cY));
                return;
            }
            if (AutoTotem.mc.player.getHeldItemOffhand().getItem() == Items.field_190931_a) {
                this.swapItems(this.getItemSlot(Items.field_190929_cY));
            }
        }
    }

    private boolean lethalToLocalCheck() {
        if (!this.CrystalCheck.getValue().booleanValue()) {
            return true;
        }
        for (Entity entity : AutoTotem.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityEnderCrystal) || !(AutoTotem.mc.player.getDistanceToEntity(entity) <= 12.0f)) continue;
            BlockPos blockPos = new BlockPos(entity.posX, entity.posY, entity.posZ);
            if (!(CrystalUtilCa.calculateDamage(blockPos, (Entity)AutoTotem.mc.player, false) >= AutoTotem.mc.player.getHealth())) continue;
            return false;
        }
        return true;
    }

    public static class StopPlayerMovement {
        private static final /* synthetic */ StopPlayerMovement stopPlayerMovement;

        static {
            stopPlayerMovement = new StopPlayerMovement();
        }

        public static void toggle(boolean bl) {
            if (bl) {
                AliceMain.EVENT_PROCESSOR.addEventListener(stopPlayerMovement);
            } else {
                AliceMain.EVENT_PROCESSOR.removeEventListener(stopPlayerMovement);
            }
        }

        @SubscribeEvent
        public void onMove(MoveEvent moveEvent) {
            moveEvent.setCanceled(true);
        }
    }
}

