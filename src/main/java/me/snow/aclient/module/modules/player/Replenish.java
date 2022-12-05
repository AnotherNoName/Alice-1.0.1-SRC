//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 */
package me.snow.aclient.module.modules.player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.Timer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class Replenish
extends Module {
    private final /* synthetic */ Setting<Integer> percent;
    private /* synthetic */ int refillSlot;
    public static /* synthetic */ Replenish INSTANCE;
    private final /* synthetic */ Setting<Boolean> wait;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Map<Integer, ItemStack> hotbar;

    @Override
    public void onTick() {
        if (this.refillSlot == -1) {
            for (int i = 0; i < 9; ++i) {
                ItemStack itemStack = Replenish.mc.player.inventory.getStackInSlot(i);
                if (this.hotbar.getOrDefault(i, null) == null) {
                    if (itemStack.getItem().equals((Object)Items.field_190931_a)) continue;
                    this.hotbar.put(i, itemStack);
                    continue;
                }
                double d = (double)itemStack.func_190916_E() / (double)itemStack.getMaxStackSize() * 100.0;
                if (!(d <= (double)this.percent.getValue().intValue())) continue;
                if (!this.timer.passedMs(this.delay.getValue().intValue())) {
                    this.refillSlot = i;
                } else {
                    this.fillStack(i, itemStack);
                    this.timer.reset();
                }
                break;
            }
        } else if (this.timer.passedMs(this.delay.getValue().intValue())) {
            this.fillStack(this.refillSlot, this.hotbar.get(this.refillSlot));
            this.timer.reset();
            this.refillSlot = -1;
        }
    }

    @Override
    public void onDisable() {
        this.hotbar.clear();
        this.refillSlot = -1;
    }

    public Replenish() {
        super("Replenish", "Replenishes your hotbar", Module.Category.PLAYER, false, false, false);
        this.percent = this.register(new Setting<Integer>("Percent", 50, 1, 99));
        this.delay = this.register(new Setting<Integer>("Delay", 100, 0, 1000));
        this.wait = this.register(new Setting<Boolean>("Wait", false));
        this.hotbar = new ConcurrentHashMap<Integer, ItemStack>();
        this.timer = new Timer();
        this.refillSlot = -1;
        INSTANCE = this;
    }

    private void fillStack(int n, ItemStack itemStack) {
        if (n != -1 && itemStack != null) {
            int n2;
            int n3 = -1;
            for (n2 = 9; n2 < 36; ++n2) {
                ItemStack itemStack2 = Replenish.mc.player.inventory.getStackInSlot(n2);
                if (itemStack2.func_190926_b() || !itemStack.getDisplayName().equals(itemStack2.getDisplayName())) continue;
                if (itemStack.getItem() instanceof ItemBlock) {
                    if (!(itemStack2.getItem() instanceof ItemBlock)) continue;
                    ItemBlock itemBlock = (ItemBlock)itemStack.getItem();
                    ItemBlock itemBlock2 = (ItemBlock)itemStack2.getItem();
                    if (!itemBlock.getBlock().equals((Object)itemBlock2.getBlock())) {
                        continue;
                    }
                } else if (!itemStack.getItem().equals((Object)itemStack2.getItem())) continue;
                n3 = n2;
            }
            if (n3 != -1) {
                n2 = itemStack.func_190916_E() + Replenish.mc.player.inventory.getStackInSlot(n3).func_190916_E();
                Replenish.mc.playerController.windowClick(0, n3, 0, ClickType.PICKUP, (EntityPlayer)Replenish.mc.player);
                Replenish.mc.playerController.windowClick(0, n < 9 ? n + 36 : n, 0, ClickType.PICKUP, (EntityPlayer)Replenish.mc.player);
                if (n2 >= itemStack.getMaxStackSize()) {
                    Replenish.mc.playerController.windowClick(0, n3, 0, ClickType.PICKUP, (EntityPlayer)Replenish.mc.player);
                }
                this.refillSlot = -1;
            }
        }
    }
}

