//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFence
 *  net.minecraft.block.BlockFenceGate
 *  net.minecraft.block.BlockWall
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.crash.CrashReport
 *  net.minecraft.crash.CrashReportCategory
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.MoverType
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.util.EnumFacing$Axis
 *  net.minecraft.util.ReportedException
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package me.snow.aclient.mixin.mixins;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import me.snow.aclient.AliceMain;
import me.snow.aclient.event.events.PushEvent;
import me.snow.aclient.event.events.StepEvent;
import me.snow.aclient.module.modules.movement.VanillaClip;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ReportedException;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={Entity.class})
public abstract class MixinEntity {
    @Shadow
    public double posX;
    @Shadow
    public double posY;
    @Shadow
    public double posZ;
    @Shadow
    public double motionX;
    @Shadow
    public double motionY;
    @Shadow
    public double motionZ;
    @Shadow
    public float rotationYaw;
    @Shadow
    public float rotationPitch;
    @Shadow
    public boolean onGround;
    @Shadow
    public boolean noClip;
    @Shadow
    public float prevDistanceWalkedModified;
    @Shadow
    public World world;
    @Shadow
    @Final
    private double[] field_191505_aI;
    @Shadow
    private long field_191506_aJ;
    @Shadow
    protected boolean isInWeb;
    @Shadow
    public float stepHeight;
    @Shadow
    public boolean isCollidedHorizontally;
    @Shadow
    public boolean isCollidedVertically;
    @Shadow
    public boolean isCollided;
    @Shadow
    public float distanceWalkedModified;
    @Shadow
    public float distanceWalkedOnStepModified;
    @Shadow
    private int field_190534_ay;
    @Shadow
    private int nextStepDistance;
    @Shadow
    private float field_191959_ay;
    @Shadow
    protected Random rand;

    @Shadow
    public abstract boolean isSprinting();

    @Shadow
    public abstract boolean isRiding();

    @Shadow
    public abstract boolean isSneaking();

    @Shadow
    public abstract void setEntityBoundingBox(AxisAlignedBB var1);

    @Shadow
    public abstract AxisAlignedBB getEntityBoundingBox();

    @Shadow
    public abstract void resetPositionToBB();

    @Shadow
    protected abstract void updateFallState(double var1, boolean var3, IBlockState var4, BlockPos var5);

    @Shadow
    protected abstract boolean canTriggerWalking();

    @Shadow
    public abstract boolean isInWater();

    @Shadow
    public abstract boolean isBeingRidden();

    @Shadow
    public abstract Entity getControllingPassenger();

    @Shadow
    public abstract void playSound(SoundEvent var1, float var2, float var3);

    @Shadow
    protected abstract void doBlockCollisions();

    @Shadow
    public abstract boolean isWet();

    @Shadow
    protected abstract void playStepSound(BlockPos var1, Block var2);

    @Shadow
    protected abstract SoundEvent getSwimSound();

    @Shadow
    protected abstract float func_191954_d(float var1);

    @Shadow
    protected abstract boolean func_191957_ae();

    @Shadow
    public abstract void addEntityCrashInfo(CrashReportCategory var1);

    @Shadow
    protected abstract void dealFireDamage(int var1);

    @Shadow
    public abstract void setFire(int var1);

    @Shadow
    protected abstract int func_190531_bD();

    @Shadow
    public abstract boolean isBurning();

    @Shadow
    public abstract int getMaxInPortalTime();

    @Overwrite
    public void moveEntity(MoverType moverType, double d, double d2, double d3) {
        Entity entity = (Entity)this;
        if (this.noClip) {
            this.setEntityBoundingBox(this.getEntityBoundingBox().offset(d, d2, d3));
            this.resetPositionToBB();
        } else {
            BlockPos blockPos;
            IBlockState iBlockState;
            Block block;
            List list;
            AxisAlignedBB axisAlignedBB;
            int n;
            int n2;
            if (moverType == MoverType.PISTON) {
                double d4;
                int n3;
                long l = this.world.getTotalWorldTime();
                if (l != this.field_191506_aJ) {
                    Arrays.fill(this.field_191505_aI, 0.0);
                    this.field_191506_aJ = l;
                }
                if (d != 0.0) {
                    n3 = EnumFacing.Axis.X.ordinal();
                    d4 = MathHelper.clamp((double)(d + this.field_191505_aI[n3]), (double)-0.51, (double)0.51);
                    d = d4 - this.field_191505_aI[n3];
                    this.field_191505_aI[n3] = d4;
                    if (Math.abs(d) <= (double)1.0E-5f) {
                        return;
                    }
                } else if (d2 != 0.0) {
                    n3 = EnumFacing.Axis.Y.ordinal();
                    d4 = MathHelper.clamp((double)(d2 + this.field_191505_aI[n3]), (double)-0.51, (double)0.51);
                    d2 = d4 - this.field_191505_aI[n3];
                    this.field_191505_aI[n3] = d4;
                    if (Math.abs(d2) <= (double)1.0E-5f) {
                        return;
                    }
                } else {
                    if (d3 == 0.0) {
                        return;
                    }
                    n3 = EnumFacing.Axis.Z.ordinal();
                    d4 = MathHelper.clamp((double)(d3 + this.field_191505_aI[n3]), (double)-0.51, (double)0.51);
                    d3 = d4 - this.field_191505_aI[n3];
                    this.field_191505_aI[n3] = d4;
                    if (Math.abs(d3) <= (double)1.0E-5f) {
                        return;
                    }
                }
            }
            this.world.theProfiler.startSection("move");
            double d5 = this.posX;
            double d6 = this.posY;
            double d7 = this.posZ;
            if (this.isInWeb) {
                this.isInWeb = false;
                d *= 0.25;
                d2 *= (double)0.05f;
                d3 *= 0.25;
                this.motionX = 0.0;
                this.motionY = 0.0;
                this.motionZ = 0.0;
            }
            double d8 = d;
            double d9 = d2;
            double d10 = d3;
            if ((moverType == MoverType.SELF || moverType == MoverType.PLAYER) && this.onGround && this.isSneaking() && entity instanceof EntityPlayer) {
                double d11 = 0.05;
                while (d != 0.0 && this.world.getCollisionBoxes(entity, this.getEntityBoundingBox().offset(d, (double)(-this.stepHeight), 0.0)).isEmpty()) {
                    d8 = d = d < 0.05 && d >= -0.05 ? 0.0 : (d > 0.0 ? (d = d - 0.05) : (d = d + 0.05));
                }
                while (d3 != 0.0 && this.world.getCollisionBoxes(entity, this.getEntityBoundingBox().offset(0.0, (double)(-this.stepHeight), d3)).isEmpty()) {
                    d10 = d3 = d3 < 0.05 && d3 >= -0.05 ? 0.0 : (d3 > 0.0 ? (d3 = d3 - 0.05) : (d3 = d3 + 0.05));
                }
                while (d != 0.0 && d3 != 0.0 && this.world.getCollisionBoxes(entity, this.getEntityBoundingBox().offset(d, (double)(-this.stepHeight), d3)).isEmpty()) {
                    d8 = d = d < 0.05 && d >= -0.05 ? 0.0 : (d > 0.0 ? (d = d - 0.05) : (d = d + 0.05));
                    d10 = d3 = d3 < 0.05 && d3 >= -0.05 ? 0.0 : (d3 > 0.0 ? (d3 = d3 - 0.05) : (d3 = d3 + 0.05));
                }
            }
            List list2 = this.world.getCollisionBoxes(entity, this.getEntityBoundingBox().addCoord(d, d2, d3));
            AxisAlignedBB axisAlignedBB2 = this.getEntityBoundingBox();
            if (d2 != 0.0) {
                n2 = list2.size();
                for (n = 0; n < n2; ++n) {
                    d2 = ((AxisAlignedBB)list2.get(n)).calculateYOffset(this.getEntityBoundingBox(), d2);
                }
                this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, d2, 0.0));
            }
            if (d != 0.0) {
                n2 = list2.size();
                for (n = 0; n < n2; ++n) {
                    d = ((AxisAlignedBB)list2.get(n)).calculateXOffset(this.getEntityBoundingBox(), d);
                }
                if (d != 0.0) {
                    this.setEntityBoundingBox(this.getEntityBoundingBox().offset(d, 0.0, 0.0));
                }
            }
            if (d3 != 0.0) {
                n2 = list2.size();
                for (n = 0; n < n2; ++n) {
                    d3 = ((AxisAlignedBB)list2.get(n)).calculateZOffset(this.getEntityBoundingBox(), d3);
                }
                if (d3 != 0.0) {
                    this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, 0.0, d3));
                }
            }
            int n4 = this.onGround || d9 != d2 && d9 < 0.0 ? 1 : 0;
            n2 = n4;
            if (this.stepHeight > 0.0f && n4 != 0 && (d8 != d || d10 != d3)) {
                StepEvent stepEvent = new StepEvent(0, entity);
                MinecraftForge.EVENT_BUS.post((Event)stepEvent);
                double d12 = d;
                double d13 = d2;
                double d14 = d3;
                axisAlignedBB = this.getEntityBoundingBox();
                this.setEntityBoundingBox(axisAlignedBB2);
                d2 = stepEvent.getHeight();
                list = this.world.getCollisionBoxes(entity, this.getEntityBoundingBox().addCoord(d8, d2, d10));
                AxisAlignedBB axisAlignedBB3 = this.getEntityBoundingBox();
                AxisAlignedBB axisAlignedBB4 = axisAlignedBB3.addCoord(d8, 0.0, d10);
                double d15 = d2;
                int n5 = list.size();
                for (int i = 0; i < n5; ++i) {
                    d15 = ((AxisAlignedBB)list.get(i)).calculateYOffset(axisAlignedBB4, d15);
                }
                axisAlignedBB3 = axisAlignedBB3.offset(0.0, d15, 0.0);
                double d16 = d8;
                int n6 = list.size();
                for (int i = 0; i < n6; ++i) {
                    d16 = ((AxisAlignedBB)list.get(i)).calculateXOffset(axisAlignedBB3, d16);
                }
                axisAlignedBB3 = axisAlignedBB3.offset(d16, 0.0, 0.0);
                double d17 = d10;
                int n7 = list.size();
                for (int i = 0; i < n7; ++i) {
                    d17 = ((AxisAlignedBB)list.get(i)).calculateZOffset(axisAlignedBB3, d17);
                }
                axisAlignedBB3 = axisAlignedBB3.offset(0.0, 0.0, d17);
                AxisAlignedBB axisAlignedBB5 = this.getEntityBoundingBox();
                double d18 = d2;
                int n8 = list.size();
                for (int i = 0; i < n8; ++i) {
                    d18 = ((AxisAlignedBB)list.get(i)).calculateYOffset(axisAlignedBB5, d18);
                }
                axisAlignedBB5 = axisAlignedBB5.offset(0.0, d18, 0.0);
                double d19 = d8;
                int n9 = list.size();
                for (int i = 0; i < n9; ++i) {
                    d19 = ((AxisAlignedBB)list.get(i)).calculateXOffset(axisAlignedBB5, d19);
                }
                axisAlignedBB5 = axisAlignedBB5.offset(d19, 0.0, 0.0);
                double d20 = d10;
                int n10 = list.size();
                for (int i = 0; i < n10; ++i) {
                    d20 = ((AxisAlignedBB)list.get(i)).calculateZOffset(axisAlignedBB5, d20);
                }
                axisAlignedBB5 = axisAlignedBB5.offset(0.0, 0.0, d20);
                double d21 = d16 * d16 + d17 * d17;
                double d22 = d19 * d19 + d20 * d20;
                if (d21 > d22) {
                    d = d16;
                    d3 = d17;
                    d2 = -d15;
                    this.setEntityBoundingBox(axisAlignedBB3);
                } else {
                    d = d19;
                    d3 = d20;
                    d2 = -d18;
                    this.setEntityBoundingBox(axisAlignedBB5);
                }
                int n11 = list.size();
                for (int i = 0; i < n11; ++i) {
                    d2 = ((AxisAlignedBB)list.get(i)).calculateYOffset(this.getEntityBoundingBox(), d2);
                }
                this.setEntityBoundingBox(this.getEntityBoundingBox().offset(0.0, d2, 0.0));
                if (d12 * d12 + d14 * d14 >= d * d + d3 * d3) {
                    d = d12;
                    d2 = d13;
                    d3 = d14;
                    this.setEntityBoundingBox(axisAlignedBB);
                } else {
                    StepEvent stepEvent2 = new StepEvent(1, entity);
                    MinecraftForge.EVENT_BUS.post((Event)stepEvent2);
                }
            }
            this.world.theProfiler.endSection();
            this.world.theProfiler.startSection("rest");
            this.resetPositionToBB();
            this.isCollidedHorizontally = d8 != d || d10 != d3;
            this.isCollidedVertically = d9 != d2;
            this.onGround = this.isCollidedVertically && d9 < 0.0;
            this.isCollided = this.isCollidedHorizontally || this.isCollidedVertically;
            int n12 = MathHelper.floor((double)this.posX);
            int n13 = MathHelper.floor((double)(this.posY - (double)0.2f));
            int n14 = MathHelper.floor((double)this.posZ);
            BlockPos blockPos2 = new BlockPos(n12, n13, n14);
            IBlockState iBlockState2 = this.world.getBlockState(blockPos2);
            if (iBlockState2.getMaterial() == Material.AIR && ((block = (iBlockState = this.world.getBlockState(blockPos = blockPos2.down())).getBlock()) instanceof BlockFence || block instanceof BlockWall || block instanceof BlockFenceGate)) {
                iBlockState2 = iBlockState;
                blockPos2 = blockPos;
            }
            this.updateFallState(d2, this.onGround, iBlockState2, blockPos2);
            if (d8 != d) {
                this.motionX = 0.0;
            }
            if (d10 != d3) {
                this.motionZ = 0.0;
            }
            Block block2 = iBlockState2.getBlock();
            if (d9 != d2) {
                block2.onLanded(this.world, entity);
            }
            if (!(!this.canTriggerWalking() || this.onGround && this.isSneaking() && entity instanceof EntityPlayer || this.isRiding())) {
                double d23 = this.posX - d5;
                double d24 = this.posY - d6;
                double d25 = this.posZ - d7;
                if (block2 != Blocks.LADDER) {
                    d24 = 0.0;
                }
                if (block2 != null && this.onGround) {
                    block2.onEntityWalk(this.world, blockPos2, entity);
                }
                this.distanceWalkedModified = (float)((double)this.distanceWalkedModified + (double)MathHelper.sqrt((double)(d23 * d23 + d25 * d25)) * 0.6);
                this.distanceWalkedOnStepModified = (float)((double)this.distanceWalkedOnStepModified + (double)MathHelper.sqrt((double)(d23 * d23 + d24 * d24 + d25 * d25)) * 0.6);
                if (this.distanceWalkedOnStepModified > (float)this.nextStepDistance && iBlockState2.getMaterial() != Material.AIR) {
                    this.nextStepDistance = (int)this.distanceWalkedOnStepModified + 1;
                    if (this.isInWater()) {
                        Entity entity2 = this.isBeingRidden() && this.getControllingPassenger() != null ? this.getControllingPassenger() : entity;
                        float f = entity2 == entity ? 0.35f : 0.4f;
                        float f2 = MathHelper.sqrt((double)(entity2.motionX * entity2.motionX * (double)0.2f + entity2.motionY * entity2.motionY + entity2.motionZ * entity2.motionZ * (double)0.2f)) * f;
                        if (f2 > 1.0f) {
                            f2 = 1.0f;
                        }
                        this.playSound(this.getSwimSound(), f2, 1.0f + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4f);
                    } else {
                        this.playStepSound(blockPos2, block2);
                    }
                } else if (this.distanceWalkedOnStepModified > this.field_191959_ay && this.func_191957_ae() && iBlockState2.getMaterial() == Material.AIR) {
                    this.field_191959_ay = this.func_191954_d(this.distanceWalkedOnStepModified);
                }
            }
            try {
                this.doBlockCollisions();
            }
            catch (Throwable throwable) {
                axisAlignedBB = CrashReport.makeCrashReport((Throwable)throwable, (String)"Checking entity block collision");
                list = axisAlignedBB.makeCategory("Entity being checked for collision");
                this.addEntityCrashInfo((CrashReportCategory)list);
                throw new ReportedException((CrashReport)axisAlignedBB);
            }
            boolean bl = this.isWet();
            if (this.world.isFlammableWithin(this.getEntityBoundingBox().contract(0.001))) {
                this.dealFireDamage(1);
                if (!bl) {
                    ++this.field_190534_ay;
                    if (this.field_190534_ay == 0) {
                        this.setFire(8);
                    }
                }
            } else if (this.field_190534_ay <= 0) {
                this.field_190534_ay = -this.func_190531_bD();
            }
            if (bl && this.isBurning()) {
                this.playSound(SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, 0.7f, 1.6f + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4f);
                this.field_190534_ay = -this.func_190531_bD();
            }
            this.world.theProfiler.endSection();
        }
    }

    @Redirect(method={"applyEntityCollision"}, at=@At(value="INVOKE", target="Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    public void addVelocityHook(Entity entity, double d, double d2, double d3) {
        PushEvent pushEvent = new PushEvent(entity, d, d2, d3, true);
        MinecraftForge.EVENT_BUS.post((Event)pushEvent);
        if (!pushEvent.isCanceled()) {
            entity.motionX += pushEvent.x;
            entity.motionY += pushEvent.y;
            entity.motionZ += pushEvent.z;
            entity.isAirBorne = pushEvent.airbone;
        }
    }

    @Redirect(method={"move"}, at=@At(value="INVOKE", target="Lnet/minecraft/util/math/AxisAlignedBB;calculateXOffset(Lnet/minecraft/util/math/AxisAlignedBB;D)D"))
    public double calculateXOffset(AxisAlignedBB axisAlignedBB, AxisAlignedBB axisAlignedBB2, double d) {
        if (this.isVanillaClipToggled()) {
            return d;
        }
        return axisAlignedBB.calculateXOffset(axisAlignedBB2, d);
    }

    @Redirect(method={"move"}, at=@At(value="INVOKE", target="Lnet/minecraft/util/math/AxisAlignedBB;calculateZOffset(Lnet/minecraft/util/math/AxisAlignedBB;D)D"))
    public double calculateZOffset(AxisAlignedBB axisAlignedBB, AxisAlignedBB axisAlignedBB2, double d) {
        if (this.isVanillaClipToggled()) {
            return d;
        }
        return axisAlignedBB.calculateZOffset(axisAlignedBB2, d);
    }

    private boolean isVanillaClipToggled() {
        return AliceMain.moduleManager.getModuleByClass(VanillaClip.class).isEnabled() && (VanillaClip.INSTANCE.onlyPhasing.getValue() == false || VanillaClip.isPhasing());
    }
}

