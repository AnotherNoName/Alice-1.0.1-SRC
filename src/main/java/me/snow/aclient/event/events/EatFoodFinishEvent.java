/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class EatFoodFinishEvent
extends EventStage {
    private /* synthetic */ ItemStack item;
    private /* synthetic */ EntityPlayer player;

    public void setItem(ItemStack itemStack) {
        this.item = itemStack;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public EntityPlayer getPlayer() {
        return this.player;
    }

    public EatFoodFinishEvent(ItemStack itemStack, EntityPlayer entityPlayer) {
        this.item = itemStack;
        this.player = entityPlayer;
    }

    public void setPlayer(EntityPlayer entityPlayer) {
        this.player = entityPlayer;
    }
}

