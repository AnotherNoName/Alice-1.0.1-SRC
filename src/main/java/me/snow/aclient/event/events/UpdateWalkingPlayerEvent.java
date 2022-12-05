/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.Cancelable
 */
package me.snow.aclient.event.events;

import me.snow.aclient.event.EventStage;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class UpdateWalkingPlayerEvent
extends EventStage {
    protected /* synthetic */ float yaw;
    protected /* synthetic */ double y;
    protected /* synthetic */ double x;
    protected /* synthetic */ double z;
    protected /* synthetic */ float pitch;
    private static /* synthetic */ UpdateWalkingPlayerEvent instance;
    protected /* synthetic */ boolean onGround;

    public float getPitch() {
        return this.pitch;
    }

    public void setZ(double d) {
        this.z = d;
    }

    public float getYaw() {
        return this.yaw;
    }

    public double getZ() {
        return this.z;
    }

    public UpdateWalkingPlayerEvent(int n) {
        super(n);
    }

    public void setY(double d) {
        this.y = d;
    }

    public void setYaw(float f) {
        this.yaw = f;
    }

    public void setPitch(float f) {
        this.pitch = f;
    }

    public double getX() {
        return this.x;
    }

    public boolean isOnGround() {
        return this.onGround;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double d) {
        this.x = d;
    }

    public void setOnGround(boolean bl) {
        this.onGround = bl;
    }

    public UpdateWalkingPlayerEvent() {
        instance = this;
    }

    public static UpdateWalkingPlayerEvent getInstance() {
        if (instance == null) {
            instance = new UpdateWalkingPlayerEvent();
        }
        return instance;
    }

    public static class Post
    extends UpdateWalkingPlayerEvent {
        private static /* synthetic */ Post INSTANCE;

        public static Post get(double d, double d2, double d3, float f, float f2, boolean bl) {
            INSTANCE.setCanceled(false);
            Post.INSTANCE.x = d;
            Post.INSTANCE.y = d2;
            Post.INSTANCE.z = d3;
            Post.INSTANCE.yaw = f;
            Post.INSTANCE.pitch = f2;
            Post.INSTANCE.onGround = bl;
            return INSTANCE;
        }

        static {
            INSTANCE = new Post();
        }
    }

    public static class Pre
    extends UpdateWalkingPlayerEvent {
        private static /* synthetic */ Pre INSTANCE;

        public static Pre get(double d, double d2, double d3, float f, float f2, boolean bl) {
            INSTANCE.setCanceled(false);
            Pre.INSTANCE.x = d;
            Pre.INSTANCE.y = d2;
            Pre.INSTANCE.z = d3;
            Pre.INSTANCE.yaw = f;
            Pre.INSTANCE.pitch = f2;
            Pre.INSTANCE.onGround = bl;
            return INSTANCE;
        }

        static {
            INSTANCE = new Pre();
        }
    }
}

