/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;
import net.minecraft.util.math.BlockPos;

public class BlockBreakingEvent
extends EventStage {
    public /* synthetic */ BlockPos pos;
    public /* synthetic */ int breakingID;
    public /* synthetic */ int breakStage;

    public BlockBreakingEvent(BlockPos blockPos, int n, int n2) {
        this.pos = blockPos;
        this.breakingID = n;
        this.breakStage = n2;
    }
}

