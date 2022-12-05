/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.MoverType
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;
import net.minecraft.entity.MoverType;

public class PlayerMoveEvent
extends EventStage {
    private /* synthetic */ MoverType type;
    private static /* synthetic */ PlayerMoveEvent INSTANCE;
    private /* synthetic */ double z;
    private /* synthetic */ double x;
    private /* synthetic */ double y;

    public void setType(MoverType moverType) {
        this.type = moverType;
    }

    public void setY(double d) {
        this.y = d;
    }

    public MoverType getType() {
        return this.type;
    }

    public void setX(double d) {
        this.x = d;
    }

    public double getX() {
        return this.x;
    }

    public static PlayerMoveEvent get(MoverType moverType, double d, double d2, double d3) {
        PlayerMoveEvent.INSTANCE.type = moverType;
        PlayerMoveEvent.INSTANCE.x = d;
        PlayerMoveEvent.INSTANCE.y = d2;
        PlayerMoveEvent.INSTANCE.z = d3;
        return INSTANCE;
    }

    public void setZ(double d) {
        this.z = d;
    }

    public double getZ() {
        return this.z;
    }

    static {
        INSTANCE = new PlayerMoveEvent();
    }

    public double getY() {
        return this.y;
    }
}

