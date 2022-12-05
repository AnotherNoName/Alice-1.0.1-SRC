//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.FoodStats
 *  net.minecraft.world.World
 */
package me.snow.aclient.module.modules.misc;

import me.snow.aclient.module.Module;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

public class AutoEat
extends Module {
    private /* synthetic */ int lastSlot;
    private /* synthetic */ boolean eating;

    @Override
    public void onUpdate() {
        if (this.eating && !AutoEat.mc.player.isHandActive()) {
            if (this.lastSlot != -1) {
                AutoEat.mc.player.inventory.currentItem = this.lastSlot;
                this.lastSlot = -1;
            }
            this.eating = false;
            KeyBinding.setKeyBindState((int)AutoEat.mc.gameSettings.keyBindUseItem.getKeyCode(), (boolean)false);
            return;
        }
        if (this.eating) {
            return;
        }
        FoodStats foodStats = AutoEat.mc.player.getFoodStats();
        if (this.isValid(AutoEat.mc.player.getHeldItemOffhand(), foodStats.getFoodLevel())) {
            AutoEat.mc.player.setActiveHand(EnumHand.OFF_HAND);
            this.eating = true;
            KeyBinding.setKeyBindState((int)AutoEat.mc.gameSettings.keyBindUseItem.getKeyCode(), (boolean)true);
            AutoEat.mc.playerController.processRightClick((EntityPlayer)AutoEat.mc.player, (World)AutoEat.mc.world, EnumHand.MAIN_HAND);
        } else {
            for (int i = 0; i < 9; ++i) {
                if (!this.isValid(AutoEat.mc.player.inventory.getStackInSlot(i), foodStats.getFoodLevel())) continue;
                this.lastSlot = AutoEat.mc.player.inventory.currentItem;
                AutoEat.mc.player.inventory.currentItem = i;
                this.eating = true;
                KeyBinding.setKeyBindState((int)AutoEat.mc.gameSettings.keyBindUseItem.getKeyCode(), (boolean)true);
                AutoEat.mc.playerController.processRightClick((EntityPlayer)AutoEat.mc.player, (World)AutoEat.mc.world, EnumHand.MAIN_HAND);
                return;
            }
        }
    }

    private boolean isValid(ItemStack itemStack, int n) {
        return itemStack.getItem() instanceof ItemFood && 20 - n >= ((ItemFood)itemStack.getItem()).getHealAmount(itemStack);
    }

    public AutoEat() {
        super("AutoEat", "eats automatically for lazy ppl", Module.Category.MISC, true, false, false);
    }
}

