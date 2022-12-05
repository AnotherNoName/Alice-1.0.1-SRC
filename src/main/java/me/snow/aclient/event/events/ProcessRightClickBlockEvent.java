/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.Cancelable
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class ProcessRightClickBlockEvent
extends EventStage {
    public /* synthetic */ EnumHand hand;
    public /* synthetic */ ItemStack stack;
    public /* synthetic */ BlockPos pos;

    public ProcessRightClickBlockEvent(BlockPos blockPos, EnumHand enumHand, ItemStack itemStack) {
        this.pos = blockPos;
        this.hand = enumHand;
        this.stack = itemStack;
    }
}

