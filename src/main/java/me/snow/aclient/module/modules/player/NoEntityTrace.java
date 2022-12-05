//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemPickaxe
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;

public class NoEntityTrace
extends Module {
    public /* synthetic */ Setting<Boolean> obby;
    public /* synthetic */ boolean noTrace;
    public /* synthetic */ Setting<Boolean> gap;
    private static /* synthetic */ NoEntityTrace INSTANCE;
    public /* synthetic */ Setting<Boolean> pick;

    private void setInstance() {
        INSTANCE = this;
    }

    public NoEntityTrace() {
        super("NoEntityTrace", "Mine through entities", Module.Category.PLAYER, false, false, false);
        this.pick = this.register(new Setting<Boolean>("Pick", true));
        this.gap = this.register(new Setting<Boolean>("Gap", false));
        this.obby = this.register(new Setting<Boolean>("Obby", false));
        this.setInstance();
    }

    public static NoEntityTrace getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new NoEntityTrace();
        }
        return INSTANCE;
    }

    static {
        INSTANCE = new NoEntityTrace();
    }

    @Override
    public void onUpdate() {
        Item item = NoEntityTrace.mc.player.getHeldItemMainhand().getItem();
        if (item instanceof ItemPickaxe && this.pick.getValue().booleanValue()) {
            this.noTrace = true;
            return;
        }
        if (item == Items.GOLDEN_APPLE && this.gap.getValue().booleanValue()) {
            this.noTrace = true;
            return;
        }
        if (item instanceof ItemBlock) {
            this.noTrace = ((ItemBlock)item).getBlock() == Blocks.OBSIDIAN && this.obby.getValue() != false;
            return;
        }
        this.noTrace = false;
    }
}

