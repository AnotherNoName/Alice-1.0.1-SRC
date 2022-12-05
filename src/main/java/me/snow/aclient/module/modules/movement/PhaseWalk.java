//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.network.play.client.CPacketPlayer$PositionRotation
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 */
package me.snow.aclient.module.modules.movement;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.PlayerUtil;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class PhaseWalk
extends Module {
    public final /* synthetic */ Setting<Boolean> phaseCheck;
    public final /* synthetic */ Setting<NoClipMode> noClipMode;
    public final /* synthetic */ Setting<Integer> stopMotionDelay;
    public final /* synthetic */ Setting<Boolean> stopMotion;
    public final /* synthetic */ Setting<Integer> antiVoidHeight;
    public final /* synthetic */ Setting<Double> phaseSpeed;
    /* synthetic */ int delay;
    public final /* synthetic */ Setting<Boolean> sprintPacket;
    public final /* synthetic */ Setting<Boolean> fallPacket;
    public final /* synthetic */ Setting<Boolean> antiVoid;
    public final /* synthetic */ Setting<Boolean> downOnShift;
    public final /* synthetic */ Setting<Boolean> clip;
    public final /* synthetic */ Setting<Boolean> instantWalk;
    public final /* synthetic */ Setting<Double> instantWalkSpeed;

    private double[] getMotion(double d) {
        float f = PhaseWalk.mc.player.movementInput.field_192832_b;
        float f2 = PhaseWalk.mc.player.movementInput.moveStrafe;
        float f3 = PhaseWalk.mc.player.prevRotationYaw + (PhaseWalk.mc.player.rotationYaw - PhaseWalk.mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
        if (f != 0.0f) {
            if (f2 > 0.0f) {
                f3 += (float)(f > 0.0f ? -45 : 45);
            } else if (f2 < 0.0f) {
                f3 += (float)(f > 0.0f ? 45 : -45);
            }
            f2 = 0.0f;
            if (f > 0.0f) {
                f = 1.0f;
            } else if (f < 0.0f) {
                f = -1.0f;
            }
        }
        double d2 = (double)f * d * -Math.sin(Math.toRadians(f3)) + (double)f2 * d * Math.cos(Math.toRadians(f3));
        double d3 = (double)f * d * Math.cos(Math.toRadians(f3)) - (double)f2 * d * -Math.sin(Math.toRadians(f3));
        return new double[]{d2, d3};
    }

    private double[] getDistance() {
        float f = PhaseWalk.mc.player.movementInput.field_192832_b;
        float f2 = PhaseWalk.mc.player.movementInput.moveStrafe;
        float f3 = PhaseWalk.mc.player.prevRotationYaw + (PhaseWalk.mc.player.rotationYaw - PhaseWalk.mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
        double d = (double)f2 * (double)f * -Math.sin(Math.toRadians(f3)) + (double)f2 * (double)f * Math.cos(Math.toRadians(f3));
        double d2 = (double)f2 * (double)f * Math.cos(Math.toRadians(f3)) - (double)f2 * (double)f * -Math.sin(Math.toRadians(f3));
        return new double[]{d, d2};
    }

    @Override
    public void onUpdate() {
        RayTraceResult rayTraceResult;
        ++this.delay;
        double d = this.phaseSpeed.getValue() / 1000.0;
        double d2 = this.instantWalkSpeed.getValue() / 10.0;
        if (this.antiVoid.getValue().booleanValue() && PhaseWalk.mc.player.posY <= (double)this.antiVoidHeight.getValue().intValue() && ((rayTraceResult = PhaseWalk.mc.world.rayTraceBlocks(PhaseWalk.mc.player.getPositionVector(), new Vec3d(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ), false, false, false)) == null || rayTraceResult.typeOfHit != RayTraceResult.Type.BLOCK)) {
            PhaseWalk.mc.player.setVelocity(0.0, 0.0, 0.0);
        }
        if (this.phaseCheck.getValue().booleanValue()) {
            if ((PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) && (!this.eChestCheck() && !PhaseWalk.mc.world.getBlockState(PlayerUtil.getPlayerPos()).getBlock().equals((Object)Blocks.AIR) || !PhaseWalk.mc.world.getBlockState(PlayerUtil.getPlayerPos().up()).getBlock().equals((Object)Blocks.AIR))) {
                double[] arrd;
                double[] arrd2;
                if (PhaseWalk.mc.player.isCollidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isPressed() && PhaseWalk.mc.player.isSneaking()) {
                    arrd2 = this.getMotion(d);
                    if (this.downOnShift.getValue().booleanValue() && PhaseWalk.mc.player.isCollidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + arrd2[0], PhaseWalk.mc.player.posY - 0.0424, PhaseWalk.mc.player.posZ + arrd2[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                    } else {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + arrd2[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + arrd2[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                    }
                    if (this.noClipMode.getValue() == NoClipMode.Fall) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX, -1300.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.rotationYaw * -5.0f, PhaseWalk.mc.player.rotationPitch * -5.0f, true));
                    }
                    if (this.noClipMode.getValue() == NoClipMode.NoClip) {
                        PhaseWalk.mc.player.setVelocity(0.0, 0.0, 0.0);
                        if (PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown()) {
                            arrd = MathUtil.directionSpeed(0.06f);
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX + arrd[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + arrd[1], PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                        if (PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY - (double)0.06f, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                        if (PhaseWalk.mc.gameSettings.keyBindJump.isKeyDown()) {
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY + (double)0.06f, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                    }
                    if (this.noClipMode.getValue() == NoClipMode.Bypass) {
                        PhaseWalk.mc.player.noClip = true;
                    }
                    if (this.fallPacket.getValue().booleanValue()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.STOP_RIDING_JUMP));
                    }
                    if (this.sprintPacket.getValue().booleanValue()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                    }
                    if (this.downOnShift.getValue().booleanValue() && PhaseWalk.mc.player.isCollidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + arrd2[0], PhaseWalk.mc.player.posY - 0.0424, PhaseWalk.mc.player.posZ + arrd2[1]);
                    } else {
                        PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + arrd2[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + arrd2[1]);
                    }
                    PhaseWalk.mc.player.motionZ = 0.0;
                    PhaseWalk.mc.player.motionY = 0.0;
                    PhaseWalk.mc.player.motionX = 0.0;
                    PhaseWalk.mc.player.noClip = true;
                }
                if (!PhaseWalk.mc.player.isCollidedHorizontally || !this.clip.getValue().booleanValue() || PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown()) {
                    // empty if block
                }
                if (PhaseWalk.mc.player.isCollidedHorizontally && this.stopMotion.getValue() != false ? this.delay >= this.stopMotionDelay.getValue() : PhaseWalk.mc.player.isCollidedHorizontally) {
                    arrd2 = this.getMotion(d);
                    if (this.downOnShift.getValue().booleanValue() && PhaseWalk.mc.player.isCollidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + arrd2[0], PhaseWalk.mc.player.posY - 0.1, PhaseWalk.mc.player.posZ + arrd2[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                    } else {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + arrd2[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + arrd2[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                    }
                    if (this.noClipMode.getValue() == NoClipMode.Fall) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX, -1300.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.rotationYaw * -5.0f, PhaseWalk.mc.player.rotationPitch * -5.0f, true));
                    }
                    if (this.noClipMode.getValue() == NoClipMode.NoClip) {
                        PhaseWalk.mc.player.setVelocity(0.0, 0.0, 0.0);
                        if (PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown()) {
                            arrd = MathUtil.directionSpeed(0.06f);
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX + arrd[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + arrd[1], PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                        if (PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY - (double)0.06f, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                        if (PhaseWalk.mc.gameSettings.keyBindJump.isKeyDown()) {
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY + (double)0.06f, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                            PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        }
                    }
                    if (this.noClipMode.getValue() == NoClipMode.Bypass) {
                        PhaseWalk.mc.player.noClip = true;
                    }
                    if (this.fallPacket.getValue().booleanValue()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.STOP_RIDING_JUMP));
                    }
                    if (this.sprintPacket.getValue().booleanValue()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                    }
                    if (this.downOnShift.getValue().booleanValue() && PhaseWalk.mc.player.isCollidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + arrd2[0], PhaseWalk.mc.player.posY - 0.1, PhaseWalk.mc.player.posZ + arrd2[1]);
                    } else {
                        PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + arrd2[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + arrd2[1]);
                    }
                    PhaseWalk.mc.player.motionZ = 0.0;
                    PhaseWalk.mc.player.motionY = 0.0;
                    PhaseWalk.mc.player.motionX = 0.0;
                    PhaseWalk.mc.player.noClip = true;
                    this.delay = 0;
                } else if (this.instantWalk.getValue().booleanValue()) {
                    double[] arrd3 = MathUtil.directionSpeed(d2);
                    PhaseWalk.mc.player.motionX = arrd3[0];
                    PhaseWalk.mc.player.motionZ = arrd3[1];
                }
            }
        } else if (PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
            double[] arrd;
            double[] arrd4;
            if (PhaseWalk.mc.player.isCollidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isPressed() && PhaseWalk.mc.player.isSneaking()) {
                arrd4 = this.getMotion(d);
                if (this.downOnShift.getValue().booleanValue() && PhaseWalk.mc.player.isCollidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + arrd4[0], PhaseWalk.mc.player.posY - 0.0424, PhaseWalk.mc.player.posZ + arrd4[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                } else {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + arrd4[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + arrd4[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                }
                if (this.noClipMode.getValue() == NoClipMode.Fall) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX, -1300.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.rotationYaw * -5.0f, PhaseWalk.mc.player.rotationPitch * -5.0f, true));
                }
                if (this.noClipMode.getValue() == NoClipMode.NoClip) {
                    PhaseWalk.mc.player.setVelocity(0.0, 0.0, 0.0);
                    if (PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown()) {
                        arrd = MathUtil.directionSpeed(0.06f);
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX + arrd[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + arrd[1], PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                    if (PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY - (double)0.06f, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                    if (PhaseWalk.mc.gameSettings.keyBindJump.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY + (double)0.06f, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                }
                if (this.noClipMode.getValue() == NoClipMode.Bypass) {
                    PhaseWalk.mc.player.noClip = true;
                }
                if (this.fallPacket.getValue().booleanValue()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.STOP_RIDING_JUMP));
                }
                if (this.sprintPacket.getValue().booleanValue()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                }
                if (this.downOnShift.getValue().booleanValue() && PhaseWalk.mc.player.isCollidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + arrd4[0], PhaseWalk.mc.player.posY - 0.0424, PhaseWalk.mc.player.posZ + arrd4[1]);
                } else {
                    PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + arrd4[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + arrd4[1]);
                }
                PhaseWalk.mc.player.motionZ = 0.0;
                PhaseWalk.mc.player.motionY = 0.0;
                PhaseWalk.mc.player.motionX = 0.0;
                PhaseWalk.mc.player.noClip = true;
            }
            if (PhaseWalk.mc.player.isCollidedHorizontally && this.stopMotion.getValue() != false ? this.delay >= this.stopMotionDelay.getValue() : PhaseWalk.mc.player.isCollidedHorizontally) {
                arrd4 = this.getMotion(d);
                if (this.downOnShift.getValue().booleanValue() && PhaseWalk.mc.player.isCollidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + arrd4[0], PhaseWalk.mc.player.posY - 0.1, PhaseWalk.mc.player.posZ + arrd4[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                } else {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX + arrd4[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + arrd4[1], PhaseWalk.mc.player.rotationYaw, PhaseWalk.mc.player.rotationPitch, false));
                }
                if (this.noClipMode.getValue() == NoClipMode.Fall) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(PhaseWalk.mc.player.posX, -1300.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.rotationYaw * -5.0f, PhaseWalk.mc.player.rotationPitch * -5.0f, true));
                }
                if (this.noClipMode.getValue() == NoClipMode.NoClip) {
                    PhaseWalk.mc.player.setVelocity(0.0, 0.0, 0.0);
                    if (PhaseWalk.mc.gameSettings.keyBindForward.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindBack.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindLeft.isKeyDown() || PhaseWalk.mc.gameSettings.keyBindRight.isKeyDown()) {
                        arrd = MathUtil.directionSpeed(0.06f);
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX + arrd[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + arrd[1], PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                    if (PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY - (double)0.06f, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                    if (PhaseWalk.mc.gameSettings.keyBindJump.isKeyDown()) {
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, PhaseWalk.mc.player.posY + (double)0.06f, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                        PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(PhaseWalk.mc.player.posX, 0.0, PhaseWalk.mc.player.posZ, PhaseWalk.mc.player.onGround));
                    }
                }
                if (this.noClipMode.getValue() == NoClipMode.Bypass) {
                    PhaseWalk.mc.player.noClip = true;
                }
                if (this.fallPacket.getValue().booleanValue()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.STOP_RIDING_JUMP));
                }
                if (this.sprintPacket.getValue().booleanValue()) {
                    PhaseWalk.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PhaseWalk.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                }
                if (this.downOnShift.getValue().booleanValue() && PhaseWalk.mc.player.isCollidedVertically && PhaseWalk.mc.gameSettings.keyBindSneak.isKeyDown()) {
                    PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + arrd4[0], PhaseWalk.mc.player.posY - 0.1, PhaseWalk.mc.player.posZ + arrd4[1]);
                } else {
                    PhaseWalk.mc.player.setPosition(PhaseWalk.mc.player.posX + arrd4[0], PhaseWalk.mc.player.posY, PhaseWalk.mc.player.posZ + arrd4[1]);
                }
                PhaseWalk.mc.player.motionZ = 0.0;
                PhaseWalk.mc.player.motionY = 0.0;
                PhaseWalk.mc.player.motionX = 0.0;
                PhaseWalk.mc.player.noClip = true;
                this.delay = 0;
            } else if (this.instantWalk.getValue().booleanValue()) {
                double[] arrd5 = MathUtil.directionSpeed(d2);
                PhaseWalk.mc.player.motionX = arrd5[0];
                PhaseWalk.mc.player.motionZ = arrd5[1];
            }
        }
    }

    public PhaseWalk() {
        super("PhaseWalk", "Allows you to walk through blocks", Module.Category.MOVEMENT, true, false, false);
        this.phaseCheck = this.register(new Setting<Boolean>("Only In Block", true));
        this.noClipMode = this.register(new Setting<NoClipMode>("NoClipMode", NoClipMode.NoClip));
        this.fallPacket = this.register(new Setting<Boolean>("Fall Packet", true));
        this.sprintPacket = this.register(new Setting<Boolean>("Sprint Packet", true));
        this.instantWalk = this.register(new Setting<Boolean>("Instant Walk", true));
        this.antiVoid = this.register(new Setting<Boolean>("Anti Void", false));
        this.clip = this.register(new Setting<Boolean>("Clip", true));
        this.antiVoidHeight = this.register(new Setting<Integer>("Anti Void Height", 5, 1, 100));
        this.instantWalkSpeed = this.register(new Setting<Object>("Instant Speed", Double.valueOf(1.8), Double.valueOf(0.1), Double.valueOf(2.0), object -> this.instantWalk.getValue()));
        this.phaseSpeed = this.register(new Setting<Double>("Phase Walk Speed", 42.4, 0.1, 70.0));
        this.downOnShift = this.register(new Setting<Boolean>("Phase Down When Crouch", true));
        this.stopMotion = this.register(new Setting<Boolean>("Attempt Clips", true));
        this.stopMotionDelay = this.register(new Setting<Object>("Attempt Clips Delay", Integer.valueOf(5), Integer.valueOf(0), Integer.valueOf(20), object -> this.stopMotion.getValue()));
        this.delay = 0;
    }

    @Override
    public void onDisable() {
        PhaseWalk.mc.player.noClip = false;
    }

    private boolean eChestCheck() {
        String string = String.valueOf(PhaseWalk.mc.player.posY);
        String string2 = string.split("\\.")[1];
        return string2.equals("875");
    }

    public static enum NoClipMode {
        NoClip,
        Fall,
        Bypass,
        None;

    }
}

