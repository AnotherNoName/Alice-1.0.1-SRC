//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.player;

import java.util.Objects;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class YawPitchLock
extends Module {
    public /* synthetic */ Setting<Boolean> lockPitch;
    public /* synthetic */ Setting<Direction> direction;
    public /* synthetic */ Setting<Boolean> lockYaw;
    public /* synthetic */ Setting<Integer> pitch;
    public /* synthetic */ Setting<Integer> yaw;
    public /* synthetic */ Setting<Boolean> byDirection;

    @SubscribeEvent
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (this.lockYaw.getValue().booleanValue()) {
            if (this.byDirection.getValue().booleanValue()) {
                switch (this.direction.getValue()) {
                    case NORTH: {
                        this.setYaw(180);
                        break;
                    }
                    case NE: {
                        this.setYaw(225);
                        break;
                    }
                    case EAST: {
                        this.setYaw(270);
                        break;
                    }
                    case SE: {
                        this.setYaw(315);
                        break;
                    }
                    case SOUTH: {
                        this.setYaw(0);
                        break;
                    }
                    case SW: {
                        this.setYaw(45);
                        break;
                    }
                    case WEST: {
                        this.setYaw(90);
                        break;
                    }
                    case NW: {
                        this.setYaw(135);
                    }
                }
            } else {
                this.setYaw(this.yaw.getValue());
            }
        }
        if (this.lockPitch.getValue().booleanValue()) {
            if (YawPitchLock.mc.player.isRiding()) {
                Objects.requireNonNull(YawPitchLock.mc.player.getRidingEntity()).rotationPitch = this.pitch.getValue().intValue();
            }
            YawPitchLock.mc.player.rotationPitch = this.pitch.getValue().intValue();
        }
    }

    private void setYaw(int n) {
        if (YawPitchLock.mc.player.isRiding()) {
            Objects.requireNonNull(YawPitchLock.mc.player.getRidingEntity()).rotationYaw = n;
        }
        YawPitchLock.mc.player.rotationYaw = n;
    }

    public YawPitchLock() {
        super("YawLock", "Locks your yaw", Module.Category.PLAYER, true, false, false);
        this.lockYaw = this.register(new Setting<Boolean>("LockYaw", false));
        this.byDirection = this.register(new Setting<Boolean>("ByDirection", false));
        this.direction = this.register(new Setting<Object>("Direction", (Object)Direction.NORTH, object -> this.byDirection.getValue()));
        this.yaw = this.register(new Setting<Object>("Yaw", Integer.valueOf(0), Integer.valueOf(-180), Integer.valueOf(180), object -> this.byDirection.getValue() == false));
        this.lockPitch = this.register(new Setting<Boolean>("LockPitch", false));
        this.pitch = this.register(new Setting<Integer>("Pitch", 0, -180, 180));
    }

    public static enum Direction {
        NORTH,
        NE,
        EAST,
        SE,
        SOUTH,
        SW,
        WEST,
        NW;

    }
}

