/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.Cancelable
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class JesusEvent
extends EventStage {
    private /* synthetic */ BlockPos pos;
    private /* synthetic */ AxisAlignedBB boundingBox;

    public BlockPos getPos() {
        return this.pos;
    }

    public JesusEvent(int n, BlockPos blockPos) {
        super(n);
        this.pos = blockPos;
    }

    public AxisAlignedBB getBoundingBox() {
        return this.boundingBox;
    }

    public void setBoundingBox(AxisAlignedBB axisAlignedBB) {
        this.boundingBox = axisAlignedBB;
    }

    public void setPos(BlockPos blockPos) {
        this.pos = blockPos;
    }
}

