/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.Cancelable
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class BlockEvent
extends EventStage {
    public /* synthetic */ BlockPos pos;
    public /* synthetic */ EnumFacing facing;

    public BlockEvent(int n, BlockPos blockPos, EnumFacing enumFacing) {
        super(n);
        this.pos = blockPos;
        this.facing = enumFacing;
    }
}

