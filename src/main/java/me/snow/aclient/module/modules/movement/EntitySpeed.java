//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityBoat
 *  net.minecraft.entity.passive.AbstractHorse
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.passive.EntityPig
 *  net.minecraft.util.MovementInput
 *  net.minecraft.world.chunk.EmptyChunk
 */
package me.snow.aclient.module.modules.movement;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.MovementInput;
import net.minecraft.world.chunk.EmptyChunk;

public class EntitySpeed
extends Module {
    public static /* synthetic */ EntitySpeed INSTANCE;
    private /* synthetic */ Setting<Boolean> antiStuck;
    public /* synthetic */ Setting<Float> speed;
    private /* synthetic */ Setting<Boolean> flight;
    private /* synthetic */ Setting<Boolean> wobble;

    @Override
    public void onUpdate() {
        if (EntitySpeed.mc.world != null && EntitySpeed.mc.player.getRidingEntity() != null) {
            Entity entity = EntitySpeed.mc.player.getRidingEntity();
            if (entity instanceof EntityPig || entity instanceof AbstractHorse) {
                this.steerEntity(entity);
            } else if (entity instanceof EntityBoat) {
                this.steerBoat(this.getBoat());
            }
        }
    }

    public static EntitySpeed getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new EntitySpeed();
        }
        return INSTANCE;
    }

    public boolean isBorderingChunk(Entity entity, double d, double d2) {
        return this.antiStuck.getValue() != false && EntitySpeed.mc.world.getChunkFromChunkCoords((int)(entity.posX + d) >> 4, (int)(entity.posZ + d2) >> 4) instanceof EmptyChunk;
    }

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        EntityBoat entityBoat = this.getBoat();
        if (entityBoat == null) {
            return;
        }
        entityBoat.rotationYaw = EntitySpeed.mc.player.rotationYaw;
        entityBoat.updateInputs(false, false, false, false);
    }

    public void steerBoat(EntityBoat entityBoat) {
        int n = 0;
        if (entityBoat == null) {
            return;
        }
        boolean bl = EntitySpeed.mc.gameSettings.keyBindForward.isKeyDown();
        boolean bl2 = EntitySpeed.mc.gameSettings.keyBindLeft.isKeyDown();
        boolean bl3 = EntitySpeed.mc.gameSettings.keyBindRight.isKeyDown();
        boolean bl4 = EntitySpeed.mc.gameSettings.keyBindBack.isKeyDown();
        if (!bl || !bl4) {
            entityBoat.motionY = 0.0;
        }
        if (EntitySpeed.mc.gameSettings.keyBindJump.isKeyDown()) {
            entityBoat.motionY += (double)(this.speed.getValue().floatValue() / 2.0f);
        }
        if (!(bl || bl2 || bl3 || bl4)) {
            return;
        }
        if (bl2 && bl3) {
            n = bl ? 0 : (bl4 ? 180 : -1);
        } else if (bl && bl4) {
            n = bl2 ? -90 : (bl3 ? 90 : -1);
        } else {
            int n2;
            int n3 = bl2 ? -90 : (n2 = (n = bl3 ? 90 : 0));
            if (bl) {
                n /= 2;
            } else if (bl4) {
                n = 180 - n / 2;
            }
        }
        if (n == -1) {
            return;
        }
        float f = EntitySpeed.mc.player.rotationYaw + (float)n;
        entityBoat.motionX = EntityUtil.getRelativeX(f) * (double)this.speed.getValue().floatValue();
        entityBoat.motionZ = EntityUtil.getRelativeZ(f) * (double)this.speed.getValue().floatValue();
    }

    public void steerEntity(Entity entity) {
        if (!this.flight.getValue().booleanValue()) {
            entity.motionY = -0.4;
        }
        if (this.flight.getValue().booleanValue()) {
            if (EntitySpeed.mc.gameSettings.keyBindJump.isKeyDown()) {
                entity.motionY = this.speed.getValue().floatValue();
            } else if (EntitySpeed.mc.gameSettings.keyBindForward.isKeyDown() || EntitySpeed.mc.gameSettings.keyBindBack.isKeyDown()) {
                entity.motionY = this.wobble.getValue() != false ? Math.sin(EntitySpeed.mc.player.ticksExisted) : 0.0;
            }
        }
        this.moveForward(entity, (double)this.speed.getValue().floatValue() * 3.8);
        if (entity instanceof EntityHorse) {
            entity.rotationYaw = EntitySpeed.mc.player.rotationYaw;
        }
    }

    public static EntitySpeed getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EntitySpeed();
        }
        return INSTANCE;
    }

    static {
        INSTANCE = new EntitySpeed();
    }

    public void moveForward(Entity entity, double d) {
        if (entity != null) {
            MovementInput movementInput = EntitySpeed.mc.player.movementInput;
            double d2 = movementInput.field_192832_b;
            double d3 = movementInput.moveStrafe;
            boolean bl = d2 != 0.0;
            boolean bl2 = d3 != 0.0;
            float f = EntitySpeed.mc.player.rotationYaw;
            if (!bl && !bl2) {
                this.setEntitySpeed(entity, 0.0, 0.0);
            } else {
                double d4;
                double d5;
                double d6;
                if (d2 != 0.0) {
                    if (d3 > 0.0) {
                        f += (float)(d2 > 0.0 ? -45 : 45);
                    } else if (d3 < 0.0) {
                        f += (float)(d2 > 0.0 ? 45 : -45);
                    }
                    d3 = 0.0;
                    double d7 = d2 = d2 > 0.0 ? 1.0 : -1.0;
                }
                if (this.isBorderingChunk(entity, d6 = d2 * d * (d5 = Math.cos(Math.toRadians(f + 90.0f))) + d3 * d * Math.sin(Math.toRadians(f + 90.0f)), d4 = d2 * d * Math.sin(Math.toRadians(f + 90.0f)) - d3 * d * d5)) {
                    d4 = 0.0;
                    d6 = 0.0;
                }
                this.setEntitySpeed(entity, d6, d4);
            }
        }
    }

    public void setInstance() {
        INSTANCE = this;
    }

    public EntitySpeed() {
        super("EntitySpeed", "Abuses client-sided movement to change the speed of rideable entities", Module.Category.MOVEMENT, false, false, false);
        this.speed = this.register(new Setting<Float>("Speed", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(4.0f)));
        this.antiStuck = this.register(new Setting<Boolean>("AntiStuck", true));
        this.flight = this.register(new Setting<Boolean>("Flight", true));
        this.wobble = this.register(new Setting<Boolean>("Wobble", true));
        this.setInstance();
    }

    public void setEntitySpeed(Entity entity, double d, double d2) {
        entity.motionX = d;
        entity.motionZ = d2;
    }

    public EntityBoat getBoat() {
        if (EntitySpeed.mc.player.getRidingEntity() != null && EntitySpeed.mc.player.getRidingEntity() instanceof EntityBoat) {
            return (EntityBoat)EntitySpeed.mc.player.getRidingEntity();
        }
        return null;
    }
}

