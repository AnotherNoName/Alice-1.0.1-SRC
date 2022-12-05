/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.MoverType
 *  net.minecraftforge.fml.common.eventhandler.Cancelable
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;
import net.minecraft.entity.MoverType;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class MoveEvent
extends EventStage {
    public /* synthetic */ double x;
    private /* synthetic */ MoverType type;
    public /* synthetic */ double z;
    public /* synthetic */ double y;

    public void setType(MoverType moverType) {
        this.type = moverType;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double d) {
        this.x = d;
    }

    public void setZ(double d) {
        this.z = d;
    }

    public MoveEvent(int n, MoverType moverType, double d, double d2, double d3) {
        super(n);
        this.type = moverType;
        this.x = d;
        this.y = d2;
        this.z = d3;
    }

    public void setY(double d) {
        this.y = d;
    }

    public double getX() {
        return this.x;
    }

    public MoverType getType() {
        return this.type;
    }

    public double getZ() {
        return this.z;
    }
}

