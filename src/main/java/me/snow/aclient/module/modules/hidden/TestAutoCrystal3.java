//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.ItemEndCrystal
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.item.ItemTool
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.network.play.client.CPacketUseEntity$Action
 *  net.minecraft.network.play.server.SPacketEntityStatus
 *  net.minecraft.network.play.server.SPacketPlayerPosLook
 *  net.minecraft.network.play.server.SPacketSoundEffect
 *  net.minecraft.network.play.server.SPacketSpawnObject
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.hidden;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Bind;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.manager.RotationManager;
import me.snow.aclient.mixin.mixins.accessors.ICPacketUseEntity;
import me.snow.aclient.mixin.mixins.accessors.IEntityPlayerSP;
import me.snow.aclient.mixin.mixins.accessors.IRenderManager;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.combat.Killaura;
import me.snow.aclient.module.modules.combat.PistonCrystal;
import me.snow.aclient.module.modules.movement.PacketFlight;
import me.snow.aclient.util.Timer;
import me.snow.aclient.util.ca.sc.BlockUtilSC;
import me.snow.aclient.util.ca.sc.CrystalUtilSC;
import me.snow.aclient.util.ca.sc.SilentRotaionUtil;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.network.play.server.SPacketSpawnObject;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TestAutoCrystal3
extends Module {
    public /* synthetic */ Timer rotationTimer;
    public /* synthetic */ Setting<Float> disableUnderHealth;
    public /* synthetic */ Setting<Float> breakSpeed;
    public final /* synthetic */ Timer noGhostTimer;
    public final /* synthetic */ Timer inhibitTimer;
    public final /* synthetic */ Timer renderTimeoutTimer;
    public final /* synthetic */ List<BlockPos> selfPlacePositions;
    public /* synthetic */ Setting<Boolean> check;
    public /* synthetic */ Setting<Boolean> disableWhenKA;
    public /* synthetic */ Setting<Boolean> noMineSwitch;
    public final /* synthetic */ Setting<Bind> forceFaceplace;
    public /* synthetic */ EntityEnderCrystal inhibitEntity;
    public /* synthetic */ Setting<Float> enemyRange;
    private /* synthetic */ EntityPlayer renderTarget;
    public /* synthetic */ Setting<Float> compromise;
    public /* synthetic */ Setting<Boolean> swing;
    private /* synthetic */ int ticks;
    public /* synthetic */ float[] rotations;
    public /* synthetic */ BlockPos renderBlock;
    public /* synthetic */ Setting<Boolean> terrainIgnore;
    private /* synthetic */ AtomicBoolean lastBroken;
    public /* synthetic */ Setting<Float> security;
    public /* synthetic */ Setting<Float> placeRange;
    public /* synthetic */ Setting<Float> breakWallsRange;
    private /* synthetic */ AtomicBoolean shouldRunThread;
    public /* synthetic */ Setting<Boolean> protocol;
    private /* synthetic */ boolean foundDoublePop;
    public /* synthetic */ Setting<Float> crystalRange;
    public /* synthetic */ BlockPos cachePos;
    private /* synthetic */ Timer renderTargetTimer;
    public /* synthetic */ Vec3d rotationVector;
    private static /* synthetic */ float aboba;
    public /* synthetic */ Setting<Boolean> fire;
    public /* synthetic */ Setting<TimingMode> timingMode;
    private /* synthetic */ RayTraceResult postResult;
    private /* synthetic */ EnumFacing postFacing;
    public /* synthetic */ Setting<DirectionMode> directionMode;
    public /* synthetic */ Setting<Boolean> inhibit;
    public /* synthetic */ Setting<YawStepMode> yawStep;
    public /* synthetic */ BlockPos renderBreakingPos;
    public /* synthetic */ Setting<TargetingMode> targetingMode;
    public /* synthetic */ Setting<Integer> yawTicks;
    public /* synthetic */ Setting<Integer> attackFactor;
    public /* synthetic */ Setting<Boolean> liquids;
    private final /* synthetic */ Setting<Boolean> armorBreaker;
    public /* synthetic */ Setting<Float> mergeOffset;
    private static /* synthetic */ TestAutoCrystal3 INSTANCE;
    private final /* synthetic */ Setting<Float> depletion;
    public final /* synthetic */ Map<EntityPlayer, Timer> totemPops;
    public final /* synthetic */ Timer switchTimer;
    public final /* synthetic */ ConcurrentHashMap<BlockPos, Long> placeLocations;
    public /* synthetic */ Setting<Float> minPlaceDamage;
    public /* synthetic */ Setting<Float> placeSpeed;
    public /* synthetic */ Setting<SyncMode> syncMode;
    public /* synthetic */ Setting<Boolean> disableWhenPA;
    public final /* synthetic */ Timer breakTimer;
    public /* synthetic */ Setting<RotationMode> rotationMode;
    public /* synthetic */ Setting<Boolean> strictDirection;
    public /* synthetic */ Setting<Boolean> predictPops;
    public /* synthetic */ AtomicBoolean tickRunning;
    public /* synthetic */ Setting<Float> yawAngle;
    public /* synthetic */ Setting<Float> maxSelfPlace;
    private /* synthetic */ Thread thread;
    private /* synthetic */ EntityEnderCrystal postBreakPos;
    public /* synthetic */ Setting<Float> suicideHealth;
    public final /* synthetic */ Timer cacheTimer;
    private /* synthetic */ BlockPos postPlacePos;
    public final /* synthetic */ Timer placeTimer;
    public /* synthetic */ Setting<Float> switchDelay;
    public /* synthetic */ Setting<Float> breakRange;
    private final /* synthetic */ Timer scatterTimer;
    private /* synthetic */ Vec3d bilateralVec;
    private /* synthetic */ BlockPos prevPlacePos;
    public final /* synthetic */ ConcurrentHashMap<Integer, Long> breakLocations;
    public /* synthetic */ Setting<Boolean> autoSwap;
    public final /* synthetic */ Timer linearTimer;
    public /* synthetic */ Setting<Integer> predictTicks;
    public final /* synthetic */ Timer renderBreakingTimer;
    public /* synthetic */ Setting<Boolean> noGapSwitch;
    public /* synthetic */ Setting<Boolean> disableOnTP;
    public /* synthetic */ Setting<Boolean> rightClickGap;
    public /* synthetic */ Setting<Boolean> antiWeakness;
    public /* synthetic */ Setting<Float> swapDelay;
    public /* synthetic */ Setting<Integer> delay;
    public /* synthetic */ Setting<Float> placeWallsRange;
    public /* synthetic */ Setting<Float> faceplaceHealth;
    public /* synthetic */ Setting<ConfirmMode> confirm;
    public /* synthetic */ boolean isPlacing;
    public /* synthetic */ float renderDamage;
    public /* synthetic */ Setting<Boolean> limit;

    public static void glBillboard(float f, float f2, float f3) {
        float f4 = 0.02666667f;
        GlStateManager.translate((double)((double)f - ((IRenderManager)mc.getRenderManager()).getRenderPosX()), (double)((double)f2 - ((IRenderManager)mc.getRenderManager()).getRenderPosY()), (double)((double)f3 - ((IRenderManager)mc.getRenderManager()).getRenderPosZ()));
        GlStateManager.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)(-Minecraft.getMinecraft().player.rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)Minecraft.getMinecraft().player.rotationPitch, (float)(Minecraft.getMinecraft().gameSettings.thirdPersonView == 2 ? -1.0f : 1.0f), (float)0.0f, (float)0.0f);
        GlStateManager.scale((float)(-f4), (float)(-f4), (float)f4);
    }

    public BlockPos getPostPlacePos() {
        return this.postPlacePos;
    }

    public EnumFacing handlePlaceRotation(BlockPos blockPos) {
        if (blockPos == null || TestAutoCrystal3.mc.player == null) {
            return null;
        }
        EnumFacing enumFacing = null;
        if (this.directionMode.getValue() != DirectionMode.VANILLA) {
            double[] arrd;
            Vec3d vec3d;
            RayTraceResult rayTraceResult;
            Vec3d vec3d2;
            Vec3d vec3d3;
            float f;
            float f2;
            float f3;
            float f4;
            double[] arrd2;
            double d;
            double d2;
            double d3;
            double d4;
            double d5;
            Vec3d vec3d4;
            double d6;
            double d7;
            double d8;
            Vec3d vec3d5 = null;
            double[] arrd3 = null;
            double d9 = 0.45;
            double d10 = 0.05;
            double d11 = 0.95;
            Vec3d vec3d6 = new Vec3d(TestAutoCrystal3.mc.player.posX, TestAutoCrystal3.mc.player.getEntityBoundingBox().minY + (double)TestAutoCrystal3.mc.player.getEyeHeight(), TestAutoCrystal3.mc.player.posZ);
            for (d8 = d10; d8 <= d11; d8 += d9) {
                for (d7 = d10; d7 <= d11; d7 += d9) {
                    for (d6 = d10; d6 <= d11; d6 += d9) {
                        vec3d4 = new Vec3d((Vec3i)blockPos).addVector(d8, d7, d6);
                        d5 = vec3d6.distanceTo(vec3d4);
                        d4 = vec3d4.xCoord - vec3d6.xCoord;
                        d3 = vec3d4.yCoord - vec3d6.yCoord;
                        d2 = vec3d4.zCoord - vec3d6.zCoord;
                        d = MathHelper.sqrt((double)(d4 * d4 + d2 * d2));
                        arrd2 = new double[]{MathHelper.wrapDegrees((float)((float)Math.toDegrees(Math.atan2(d2, d4)) - 90.0f)), MathHelper.wrapDegrees((float)((float)(-Math.toDegrees(Math.atan2(d3, d)))))};
                        f4 = MathHelper.cos((float)((float)(-arrd2[0] * 0.01745329238474369 - 3.1415927410125732)));
                        f3 = MathHelper.sin((float)((float)(-arrd2[0] * 0.01745329238474369 - 3.1415927410125732)));
                        f2 = -MathHelper.cos((float)((float)(-arrd2[1] * 0.01745329238474369)));
                        f = MathHelper.sin((float)((float)(-arrd2[1] * 0.01745329238474369)));
                        vec3d3 = new Vec3d((double)(f3 * f2), (double)f, (double)(f4 * f2));
                        vec3d2 = vec3d6.addVector(vec3d3.xCoord * d5, vec3d3.yCoord * d5, vec3d3.zCoord * d5);
                        rayTraceResult = TestAutoCrystal3.mc.world.rayTraceBlocks(vec3d6, vec3d2, false, true, false);
                        if (!(this.placeWallsRange.getValue().floatValue() >= this.placeRange.getValue().floatValue() || rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK && rayTraceResult.getBlockPos().equals((Object)blockPos))) continue;
                        vec3d = vec3d4;
                        arrd = arrd2;
                        if (this.strictDirection.getValue().booleanValue()) {
                            if (vec3d5 != null && arrd3 != null && (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK || enumFacing == null)) {
                                if (!(TestAutoCrystal3.mc.player.getPositionVector().addVector(0.0, (double)TestAutoCrystal3.mc.player.getEyeHeight(), 0.0).distanceTo(vec3d) < TestAutoCrystal3.mc.player.getPositionVector().addVector(0.0, (double)TestAutoCrystal3.mc.player.getEyeHeight(), 0.0).distanceTo(vec3d5))) continue;
                                vec3d5 = vec3d;
                                arrd3 = arrd;
                                if (rayTraceResult == null || rayTraceResult.typeOfHit != RayTraceResult.Type.BLOCK) continue;
                                enumFacing = rayTraceResult.sideHit;
                                this.postResult = rayTraceResult;
                                continue;
                            }
                            vec3d5 = vec3d;
                            arrd3 = arrd;
                            if (rayTraceResult == null || rayTraceResult.typeOfHit != RayTraceResult.Type.BLOCK) continue;
                            enumFacing = rayTraceResult.sideHit;
                            this.postResult = rayTraceResult;
                            continue;
                        }
                        if (vec3d5 != null && arrd3 != null && (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK || enumFacing == null)) {
                            if (!(Math.hypot(((arrd[0] - (double)((IEntityPlayerSP)TestAutoCrystal3.mc.player).getLastReportedYaw()) % 360.0 + 540.0) % 360.0 - 180.0, arrd[1] - (double)((IEntityPlayerSP)TestAutoCrystal3.mc.player).getLastReportedPitch()) < Math.hypot(((arrd3[0] - (double)((IEntityPlayerSP)TestAutoCrystal3.mc.player).getLastReportedYaw()) % 360.0 + 540.0) % 360.0 - 180.0, arrd3[1] - (double)((IEntityPlayerSP)TestAutoCrystal3.mc.player).getLastReportedPitch()))) continue;
                            vec3d5 = vec3d;
                            arrd3 = arrd;
                            if (rayTraceResult == null || rayTraceResult.typeOfHit != RayTraceResult.Type.BLOCK) continue;
                            enumFacing = rayTraceResult.sideHit;
                            this.postResult = rayTraceResult;
                            continue;
                        }
                        vec3d5 = vec3d;
                        arrd3 = arrd;
                        if (rayTraceResult == null || rayTraceResult.typeOfHit != RayTraceResult.Type.BLOCK) continue;
                        enumFacing = rayTraceResult.sideHit;
                        this.postResult = rayTraceResult;
                    }
                }
            }
            if (this.placeWallsRange.getValue().floatValue() < this.placeRange.getValue().floatValue() && this.directionMode.getValue() == DirectionMode.STRICT) {
                if (arrd3 != null && enumFacing != null) {
                    this.rotationTimer.reset();
                    this.rotationVector = vec3d5;
                    this.rotations = RotationManager.calculateAngle(TestAutoCrystal3.mc.player.getPositionEyes(1.0f), this.rotationVector);
                    return enumFacing;
                }
                for (d8 = d10; d8 <= d11; d8 += d9) {
                    for (d7 = d10; d7 <= d11; d7 += d9) {
                        for (d6 = d10; d6 <= d11; d6 += d9) {
                            vec3d4 = new Vec3d((Vec3i)blockPos).addVector(d8, d7, d6);
                            d5 = vec3d6.distanceTo(vec3d4);
                            d4 = vec3d4.xCoord - vec3d6.xCoord;
                            d3 = vec3d4.yCoord - vec3d6.yCoord;
                            d2 = vec3d4.zCoord - vec3d6.zCoord;
                            d = MathHelper.sqrt((double)(d4 * d4 + d2 * d2));
                            arrd2 = new double[]{MathHelper.wrapDegrees((float)((float)Math.toDegrees(Math.atan2(d2, d4)) - 90.0f)), MathHelper.wrapDegrees((float)((float)(-Math.toDegrees(Math.atan2(d3, d)))))};
                            f4 = MathHelper.cos((float)((float)(-arrd2[0] * 0.01745329238474369 - 3.1415927410125732)));
                            f3 = MathHelper.sin((float)((float)(-arrd2[0] * 0.01745329238474369 - 3.1415927410125732)));
                            f2 = -MathHelper.cos((float)((float)(-arrd2[1] * 0.01745329238474369)));
                            f = MathHelper.sin((float)((float)(-arrd2[1] * 0.01745329238474369)));
                            vec3d3 = new Vec3d((double)(f3 * f2), (double)f, (double)(f4 * f2));
                            vec3d2 = vec3d6.addVector(vec3d3.xCoord * d5, vec3d3.yCoord * d5, vec3d3.zCoord * d5);
                            rayTraceResult = TestAutoCrystal3.mc.world.rayTraceBlocks(vec3d6, vec3d2, false, true, true);
                            if (rayTraceResult == null || rayTraceResult.typeOfHit != RayTraceResult.Type.BLOCK) continue;
                            vec3d = vec3d4;
                            arrd = arrd2;
                            if (this.strictDirection.getValue().booleanValue()) {
                                if (vec3d5 != null && arrd3 != null && (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK || enumFacing == null)) {
                                    if (!(TestAutoCrystal3.mc.player.getPositionVector().addVector(0.0, (double)TestAutoCrystal3.mc.player.getEyeHeight(), 0.0).distanceTo(vec3d) < TestAutoCrystal3.mc.player.getPositionVector().addVector(0.0, (double)TestAutoCrystal3.mc.player.getEyeHeight(), 0.0).distanceTo(vec3d5))) continue;
                                    vec3d5 = vec3d;
                                    arrd3 = arrd;
                                    if (rayTraceResult == null || rayTraceResult.typeOfHit != RayTraceResult.Type.BLOCK) continue;
                                    enumFacing = rayTraceResult.sideHit;
                                    this.postResult = rayTraceResult;
                                    continue;
                                }
                                vec3d5 = vec3d;
                                arrd3 = arrd;
                                if (rayTraceResult == null || rayTraceResult.typeOfHit != RayTraceResult.Type.BLOCK) continue;
                                enumFacing = rayTraceResult.sideHit;
                                this.postResult = rayTraceResult;
                                continue;
                            }
                            if (vec3d5 != null && arrd3 != null && (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK || enumFacing == null)) {
                                if (!(Math.hypot(((arrd[0] - (double)((IEntityPlayerSP)TestAutoCrystal3.mc.player).getLastReportedYaw()) % 360.0 + 540.0) % 360.0 - 180.0, arrd[1] - (double)((IEntityPlayerSP)TestAutoCrystal3.mc.player).getLastReportedPitch()) < Math.hypot(((arrd3[0] - (double)((IEntityPlayerSP)TestAutoCrystal3.mc.player).getLastReportedYaw()) % 360.0 + 540.0) % 360.0 - 180.0, arrd3[1] - (double)((IEntityPlayerSP)TestAutoCrystal3.mc.player).getLastReportedPitch()))) continue;
                                vec3d5 = vec3d;
                                arrd3 = arrd;
                                if (rayTraceResult == null || rayTraceResult.typeOfHit != RayTraceResult.Type.BLOCK) continue;
                                enumFacing = rayTraceResult.sideHit;
                                this.postResult = rayTraceResult;
                                continue;
                            }
                            vec3d5 = vec3d;
                            arrd3 = arrd;
                            if (rayTraceResult == null || rayTraceResult.typeOfHit != RayTraceResult.Type.BLOCK) continue;
                            enumFacing = rayTraceResult.sideHit;
                            this.postResult = rayTraceResult;
                        }
                    }
                }
            } else {
                if (arrd3 != null) {
                    this.rotationTimer.reset();
                    this.rotationVector = vec3d5;
                    this.rotations = RotationManager.calculateAngle(TestAutoCrystal3.mc.player.getPositionEyes(1.0f), this.rotationVector);
                }
                if (enumFacing != null) {
                    return enumFacing;
                }
            }
        } else {
            Vec3d vec3d;
            EnumFacing enumFacing2 = null;
            Vec3d vec3d7 = null;
            for (EnumFacing enumFacing3 : EnumFacing.values()) {
                vec3d = new Vec3d((double)blockPos.getX() + 0.5 + (double)enumFacing3.getDirectionVec().getX() * 0.5, (double)blockPos.getY() + 0.5 + (double)enumFacing3.getDirectionVec().getY() * 0.5, (double)blockPos.getZ() + 0.5 + (double)enumFacing3.getDirectionVec().getZ() * 0.5);
                RayTraceResult rayTraceResult = TestAutoCrystal3.mc.world.rayTraceBlocks(new Vec3d(TestAutoCrystal3.mc.player.posX, TestAutoCrystal3.mc.player.posY + (double)TestAutoCrystal3.mc.player.getEyeHeight(), TestAutoCrystal3.mc.player.posZ), vec3d, false, true, false);
                if (rayTraceResult == null || !rayTraceResult.typeOfHit.equals((Object)RayTraceResult.Type.BLOCK) || !rayTraceResult.getBlockPos().equals((Object)blockPos)) continue;
                if (this.strictDirection.getValue().booleanValue()) {
                    if (vec3d7 != null && !(TestAutoCrystal3.mc.player.getPositionVector().addVector(0.0, (double)TestAutoCrystal3.mc.player.getEyeHeight(), 0.0).distanceTo(vec3d) < TestAutoCrystal3.mc.player.getPositionVector().addVector(0.0, (double)TestAutoCrystal3.mc.player.getEyeHeight(), 0.0).distanceTo(vec3d7))) continue;
                    vec3d7 = vec3d;
                    enumFacing2 = enumFacing3;
                    this.postResult = rayTraceResult;
                    continue;
                }
                this.rotationTimer.reset();
                this.rotationVector = vec3d;
                this.rotations = RotationManager.calculateAngle(TestAutoCrystal3.mc.player.getPositionEyes(1.0f), this.rotationVector);
                return enumFacing3;
            }
            if (enumFacing2 != null) {
                this.rotationTimer.reset();
                this.rotationVector = vec3d7;
                this.rotations = RotationManager.calculateAngle(TestAutoCrystal3.mc.player.getPositionEyes(1.0f), this.rotationVector);
                return enumFacing2;
            }
            if (this.strictDirection.getValue().booleanValue()) {
                for (EnumFacing enumFacing3 : EnumFacing.values()) {
                    vec3d = new Vec3d((double)blockPos.getX() + 0.5 + (double)enumFacing3.getDirectionVec().getX() * 0.5, (double)blockPos.getY() + 0.5 + (double)enumFacing3.getDirectionVec().getY() * 0.5, (double)blockPos.getZ() + 0.5 + (double)enumFacing3.getDirectionVec().getZ() * 0.5);
                    if (vec3d7 != null && !(TestAutoCrystal3.mc.player.getPositionVector().addVector(0.0, (double)TestAutoCrystal3.mc.player.getEyeHeight(), 0.0).distanceTo(vec3d) < TestAutoCrystal3.mc.player.getPositionVector().addVector(0.0, (double)TestAutoCrystal3.mc.player.getEyeHeight(), 0.0).distanceTo(vec3d7))) continue;
                    vec3d7 = vec3d;
                    enumFacing2 = enumFacing3;
                }
                if (enumFacing2 != null) {
                    this.rotationTimer.reset();
                    this.rotationVector = vec3d7;
                    this.rotations = RotationManager.calculateAngle(TestAutoCrystal3.mc.player.getPositionEyes(1.0f), this.rotationVector);
                    return enumFacing2;
                }
            }
        }
        if ((double)blockPos.getY() > TestAutoCrystal3.mc.player.posY + (double)TestAutoCrystal3.mc.player.getEyeHeight()) {
            this.rotationTimer.reset();
            this.rotationVector = new Vec3d((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1.0, (double)blockPos.getZ() + 0.5);
            this.rotations = RotationManager.calculateAngle(TestAutoCrystal3.mc.player.getPositionEyes(1.0f), this.rotationVector);
            return EnumFacing.DOWN;
        }
        this.rotationTimer.reset();
        this.rotationVector = new Vec3d((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1.0, (double)blockPos.getZ() + 0.5);
        this.rotations = RotationManager.calculateAngle(TestAutoCrystal3.mc.player.getPositionEyes(1.0f), this.rotationVector);
        return EnumFacing.UP;
    }

    public static List<BlockPos> getSphere(BlockPos blockPos, float f, int n, boolean bl, boolean bl2, int n2) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        int n3 = blockPos.getX();
        int n4 = blockPos.getY();
        int n5 = blockPos.getZ();
        int n6 = n3 - (int)f;
        while ((float)n6 <= (float)n3 + f) {
            int n7 = n5 - (int)f;
            while ((float)n7 <= (float)n5 + f) {
                int n8 = bl2 ? n4 - (int)f : n4;
                while (true) {
                    float f2;
                    float f3 = n8;
                    float f4 = f2 = bl2 ? (float)n4 + f : (float)(n4 + n);
                    if (!(f3 < f2)) break;
                    double d = (n3 - n6) * (n3 - n6) + (n5 - n7) * (n5 - n7) + (bl2 ? (n4 - n8) * (n4 - n8) : 0);
                    if (!(!(d < (double)(f * f)) || bl && d < (double)((f - 1.0f) * (f - 1.0f)))) {
                        BlockPos blockPos2 = new BlockPos(n6, n8 + n2, n7);
                        arrayList.add(blockPos2);
                    }
                    ++n8;
                }
                ++n7;
            }
            ++n6;
        }
        return arrayList;
    }

    public TestAutoCrystal3() {
        super("AutoCrystalSSSSSDA", "", Module.Category.COMBAT, true, false, false);
        this.timingMode = this.register(new Setting<TimingMode>("Timing", TimingMode.ADAPTIVE));
        this.rotationMode = this.register(new Setting<RotationMode>("Rotate", RotationMode.TRACK));
        this.inhibit = this.register(new Setting<Boolean>("Inhibit", false));
        this.limit = this.register(new Setting<Boolean>("Limit", true));
        this.yawStep = this.register(new Setting<YawStepMode>("YawStep", YawStepMode.OFF));
        this.yawAngle = this.register(new Setting<Float>("YawAngle", Float.valueOf(0.3f), Float.valueOf(0.1f), Float.valueOf(1.0f)));
        this.yawTicks = this.register(new Setting<Integer>("YawTicks", 1, 1, 5));
        this.strictDirection = this.register(new Setting<Boolean>("StrictDirection", true));
        this.check = this.register(new Setting<Boolean>("Check", true));
        this.directionMode = this.register(new Setting<DirectionMode>("Interact", DirectionMode.NORMAL));
        this.protocol = this.register(new Setting<Boolean>("Protocol", false));
        this.liquids = this.register(new Setting<Boolean>("PlaceInLiquids", false));
        this.fire = this.register(new Setting<Boolean>("PlaceInFire", false));
        this.confirm = this.register(new Setting<ConfirmMode>("Confirm", ConfirmMode.OFF));
        this.delay = this.register(new Setting<Integer>("Delay", 0, 0, 20));
        this.attackFactor = this.register(new Setting<Integer>("AttackFactor", 3, 1, 20));
        this.breakSpeed = this.register(new Setting<Float>("BreakSpeed", Float.valueOf(20.0f), Float.valueOf(1.0f), Float.valueOf(20.0f)));
        this.placeSpeed = this.register(new Setting<Float>("PlaceSpeed", Float.valueOf(20.0f), Float.valueOf(2.0f), Float.valueOf(20.0f)));
        this.syncMode = this.register(new Setting<SyncMode>("Sync", SyncMode.STRICT));
        this.mergeOffset = this.register(new Setting<Float>("Offset", Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(8.0f)));
        this.enemyRange = this.register(new Setting<Float>("EnemyRange", Float.valueOf(8.0f), Float.valueOf(4.0f), Float.valueOf(15.0f)));
        this.crystalRange = this.register(new Setting<Float>("CrystalRange", Float.valueOf(6.0f), Float.valueOf(2.0f), Float.valueOf(12.0f)));
        this.breakRange = this.register(new Setting<Float>("BreakRange", Float.valueOf(4.3f), Float.valueOf(1.0f), Float.valueOf(6.0f)));
        this.breakWallsRange = this.register(new Setting<Float>("BreakWalls", Float.valueOf(1.5f), Float.valueOf(1.0f), Float.valueOf(6.0f)));
        this.placeRange = this.register(new Setting<Float>("PlaceRange", Float.valueOf(4.0f), Float.valueOf(1.0f), Float.valueOf(6.0f)));
        this.placeWallsRange = this.register(new Setting<Float>("PlaceWalls", Float.valueOf(3.0f), Float.valueOf(1.0f), Float.valueOf(6.0f)));
        this.autoSwap = this.register(new Setting<Boolean>("AutoSwap", true));
        this.swapDelay = this.register(new Setting<Float>("SwapDelay", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(20.0f)));
        this.switchDelay = this.register(new Setting<Float>("GhostDelay", Float.valueOf(5.0f), Float.valueOf(0.0f), Float.valueOf(10.0f)));
        this.antiWeakness = this.register(new Setting<Boolean>("AntiWeakness", false));
        this.targetingMode = this.register(new Setting<TargetingMode>("Target", TargetingMode.ALL));
        this.security = this.register(new Setting<Float>("Security", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(5.0f)));
        this.compromise = this.register(new Setting<Float>("Compromise", Float.valueOf(1.0f), Float.valueOf(0.05f), Float.valueOf(2.0f)));
        this.minPlaceDamage = this.register(new Setting<Float>("MinDamage", Float.valueOf(6.0f), Float.valueOf(0.0f), Float.valueOf(20.0f)));
        this.maxSelfPlace = this.register(new Setting<Float>("MaxSelfDmg", Float.valueOf(12.0f), Float.valueOf(0.0f), Float.valueOf(20.0f)));
        this.suicideHealth = this.register(new Setting<Float>("SuicideHealth", Float.valueOf(2.0f), Float.valueOf(0.0f), Float.valueOf(10.0f)));
        this.faceplaceHealth = this.register(new Setting<Float>("FaceplaceHealth", Float.valueOf(4.0f), Float.valueOf(0.0f), Float.valueOf(20.0f)));
        this.forceFaceplace = this.register(new Setting<Bind>("Faceplace", new Bind(-1)));
        this.armorBreaker = this.register(new Setting<Boolean>("ArmorBreaker", true));
        this.depletion = this.register(new Setting<Float>("Depletion", Float.valueOf(0.9f), Float.valueOf(0.1f), Float.valueOf(1.0f), f -> this.armorBreaker.getValue()));
        this.predictTicks = this.register(new Setting<Integer>("PredictTicks", Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(10), n -> this.armorBreaker.getValue()));
        this.predictPops = this.register(new Setting<Boolean>("PredictPops", Boolean.valueOf(false), bl -> this.armorBreaker.getValue()));
        this.terrainIgnore = this.register(new Setting<Boolean>("PredictDestruction", Boolean.valueOf(false), bl -> this.armorBreaker.getValue()));
        this.noMineSwitch = this.register(new Setting<Boolean>("Mining", false));
        this.noGapSwitch = this.register(new Setting<Boolean>("Gapping", false));
        this.rightClickGap = this.register(new Setting<Boolean>("RightClickGap", Boolean.valueOf(false), bl -> this.noGapSwitch.getValue()));
        this.disableWhenKA = this.register(new Setting<Boolean>("KillAura", true));
        this.disableWhenPA = this.register(new Setting<Boolean>("PistonAura", true));
        this.disableUnderHealth = this.register(new Setting<Float>("Health", Float.valueOf(2.0f), Float.valueOf(0.0f), Float.valueOf(10.0f)));
        this.disableOnTP = this.register(new Setting<Boolean>("DisableOnTP", false));
        this.swing = this.register(new Setting<Boolean>("Swing", true));
        this.rotationVector = null;
        this.rotations = new float[]{0.0f, 0.0f};
        this.rotationTimer = new Timer();
        this.prevPlacePos = null;
        this.placeTimer = new Timer();
        this.breakTimer = new Timer();
        this.noGhostTimer = new Timer();
        this.switchTimer = new Timer();
        this.renderDamage = 0.0f;
        this.renderTimeoutTimer = new Timer();
        this.renderBreakingTimer = new Timer();
        this.isPlacing = false;
        this.placeLocations = new ConcurrentHashMap();
        this.breakLocations = new ConcurrentHashMap();
        this.totemPops = new ConcurrentHashMap<EntityPlayer, Timer>();
        this.selfPlacePositions = new CopyOnWriteArrayList<BlockPos>();
        this.tickRunning = new AtomicBoolean(false);
        this.linearTimer = new Timer();
        this.cacheTimer = new Timer();
        this.cachePos = null;
        this.inhibitTimer = new Timer();
        this.inhibitEntity = null;
        this.scatterTimer = new Timer();
        this.bilateralVec = null;
        this.shouldRunThread = new AtomicBoolean(false);
        this.lastBroken = new AtomicBoolean(false);
        this.renderTargetTimer = new Timer();
        this.foundDoublePop = false;
        this.setInstance();
    }

    private boolean shouldArmorBreak(EntityPlayer entityPlayer) {
        if (!this.armorBreaker.getValue().booleanValue()) {
            return false;
        }
        for (int i = 3; i >= 0; --i) {
            double d;
            ItemStack itemStack = (ItemStack)entityPlayer.inventory.armorInventory.get(i);
            if (itemStack == null || !((d = itemStack.getItem().getDurabilityForDisplay(itemStack)) > (double)this.depletion.getValue().floatValue())) continue;
            return true;
        }
        return false;
    }

    private void handleSequential() {
        if (TestAutoCrystal3.mc.player.getHealth() + TestAutoCrystal3.mc.player.getAbsorptionAmount() < this.disableUnderHealth.getValue().floatValue() || this.disableWhenKA.getValue() != false && AliceMain.moduleManager.getModuleByClass(Killaura.class).isEnabled() || this.disableWhenPA.getValue() != false && AliceMain.moduleManager.getModuleByClass(PistonCrystal.class).isEnabled() || this.noGapSwitch.getValue() != false && TestAutoCrystal3.mc.player.getActiveItemStack().getItem() instanceof ItemFood || this.noMineSwitch.getValue().booleanValue() && TestAutoCrystal3.mc.playerController.getIsHittingBlock() && TestAutoCrystal3.mc.player.getHeldItemMainhand().getItem() instanceof ItemTool) {
            this.rotationVector = null;
            return;
        }
        if (this.noGapSwitch.getValue().booleanValue() && this.rightClickGap.getValue().booleanValue() && TestAutoCrystal3.mc.gameSettings.keyBindUseItem.isKeyDown() && TestAutoCrystal3.mc.player.inventory.getCurrentItem().getItem() instanceof ItemEndCrystal) {
            int n = -1;
            for (int i = 0; i < 9; ++i) {
                if (TestAutoCrystal3.mc.player.inventory.getStackInSlot(i).getItem() != Items.GOLDEN_APPLE) continue;
                n = i;
                break;
            }
            if (n != -1 && n != TestAutoCrystal3.mc.player.inventory.currentItem && this.switchTimer.passedMs((long)(this.swapDelay.getValue().floatValue() * 50.0f))) {
                TestAutoCrystal3.mc.player.inventory.currentItem = n;
                TestAutoCrystal3.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
                this.switchTimer.reset();
                this.noGhostTimer.reset();
                return;
            }
        }
        if (!(this.isOffhand() || TestAutoCrystal3.mc.player.inventory.getCurrentItem().getItem() instanceof ItemEndCrystal || this.autoSwap.getValue().booleanValue())) {
            return;
        }
        List<EntityPlayer> list = this.getTargetsInRange();
        EntityEnderCrystal entityEnderCrystal = this.findCrystalTarget(list);
        int n = (int)Math.max(100.0f, (float)(CrystalUtilSC.ping() + 50) / 1.0f) + 150;
        if (entityEnderCrystal != null && this.breakTimer.passedMs((long)(1000.0f - this.breakSpeed.getValue().floatValue() * 50.0f)) && (entityEnderCrystal.ticksExisted >= this.delay.getValue() || this.timingMode.getValue() == TimingMode.ADAPTIVE)) {
            this.postBreakPos = entityEnderCrystal;
            this.handleBreakRotation(this.postBreakPos.posX, this.postBreakPos.posY, this.postBreakPos.posZ);
        }
        if (entityEnderCrystal == null && (this.confirm.getValue() != ConfirmMode.FULL || this.inhibitEntity == null || (double)this.inhibitEntity.ticksExisted >= Math.floor(this.delay.getValue().intValue())) && (this.syncMode.getValue() != SyncMode.STRICT || this.breakTimer.passedMs((long)(950.0f - this.breakSpeed.getValue().floatValue() * 50.0f - (float)CrystalUtilSC.ping()))) && this.placeTimer.passedMs((long)(1000.0f - this.placeSpeed.getValue().floatValue() * 50.0f)) && (this.timingMode.getValue() == TimingMode.SEQUENTIAL || this.linearTimer.passedMs((long)((float)this.delay.getValue().intValue() * 5.0f)))) {
            BlockPos blockPos;
            if (this.confirm.getValue() != ConfirmMode.OFF && this.cachePos != null && !this.cacheTimer.passedMs(n + 100) && this.canPlaceCrystal(this.cachePos)) {
                this.postPlacePos = this.cachePos;
                this.postFacing = this.handlePlaceRotation(this.postPlacePos);
                this.lastBroken.set(false);
                return;
            }
            List<BlockPos> list2 = this.findCrystalBlocks();
            if (!list2.isEmpty() && (blockPos = this.findPlacePosition(list2, list)) != null) {
                this.postPlacePos = blockPos;
                this.postFacing = this.handlePlaceRotation(this.postPlacePos);
            }
        }
        this.lastBroken.set(false);
    }

    public static TestAutoCrystal3 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TestAutoCrystal3();
        }
        return INSTANCE;
    }

    public boolean setCrystalSlot() {
        if (this.isOffhand()) {
            return true;
        }
        int n = CrystalUtilSC.getCrystalSlot();
        if (n == -1) {
            return false;
        }
        if (TestAutoCrystal3.mc.player.inventory.currentItem != n) {
            TestAutoCrystal3.mc.player.inventory.currentItem = n;
            TestAutoCrystal3.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
            this.switchTimer.reset();
            this.noGhostTimer.reset();
        }
        return true;
    }

    static {
        INSTANCE = new TestAutoCrystal3();
    }

    public boolean canPlaceCrystal(BlockPos blockPos) {
        if (TestAutoCrystal3.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && TestAutoCrystal3.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
            return false;
        }
        BlockPos blockPos2 = blockPos.add(0, 1, 0);
        if (!(TestAutoCrystal3.mc.world.getBlockState(blockPos2).getBlock() == Blocks.AIR || TestAutoCrystal3.mc.world.getBlockState(blockPos2).getBlock() == Blocks.FIRE && this.fire.getValue().booleanValue() || TestAutoCrystal3.mc.world.getBlockState(blockPos2).getBlock() instanceof BlockLiquid && this.liquids.getValue().booleanValue())) {
            return false;
        }
        BlockPos blockPos3 = blockPos.add(0, 2, 0);
        if (!(this.protocol.getValue().booleanValue() || TestAutoCrystal3.mc.world.getBlockState(blockPos3).getBlock() == Blocks.AIR || TestAutoCrystal3.mc.world.getBlockState(blockPos2).getBlock() instanceof BlockLiquid && this.liquids.getValue().booleanValue())) {
            return false;
        }
        if (this.check.getValue().booleanValue() && !CrystalUtilSC.rayTraceBreak((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1.0, (double)blockPos.getZ() + 0.5)) {
            Vec3d vec3d = new Vec3d((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1.0, (double)blockPos.getZ() + 0.5);
            if (TestAutoCrystal3.mc.player.getPositionEyes(1.0f).distanceTo(vec3d) > (double)this.breakWallsRange.getValue().floatValue()) {
                return false;
            }
        }
        if (this.placeWallsRange.getValue().floatValue() < this.placeRange.getValue().floatValue()) {
            if (!CrystalUtilSC.rayTracePlace(blockPos)) {
                if (this.strictDirection.getValue().booleanValue()) {
                    boolean bl;
                    block26: {
                        Vec3d vec3d = TestAutoCrystal3.mc.player.getPositionVector().addVector(0.0, (double)TestAutoCrystal3.mc.player.getEyeHeight(), 0.0);
                        bl = false;
                        if (this.directionMode.getValue() == DirectionMode.VANILLA) {
                            for (EnumFacing enumFacing : EnumFacing.values()) {
                                Vec3d vec3d2 = new Vec3d((double)blockPos.getX() + 0.5 + (double)enumFacing.getDirectionVec().getX() * 0.5, (double)blockPos.getY() + 0.5 + (double)enumFacing.getDirectionVec().getY() * 0.5, (double)blockPos.getZ() + 0.5 + (double)enumFacing.getDirectionVec().getZ() * 0.5);
                                if (!(vec3d.distanceTo(vec3d2) <= (double)this.placeWallsRange.getValue().floatValue())) continue;
                                bl = true;
                                break;
                            }
                        } else {
                            double d = 0.45;
                            double d2 = 0.05;
                            double d3 = 0.95;
                            for (double d4 = d2; d4 <= d3; d4 += d) {
                                for (double d5 = d2; d5 <= d3; d5 += d) {
                                    for (double d6 = d2; d6 <= d3; d6 += d) {
                                        Vec3d vec3d3 = new Vec3d((Vec3i)blockPos).addVector(d4, d5, d6);
                                        double d7 = vec3d.distanceTo(vec3d3);
                                        if (!(d7 <= (double)this.placeWallsRange.getValue().floatValue())) continue;
                                        bl = true;
                                        break block26;
                                    }
                                }
                            }
                        }
                    }
                    if (!bl) {
                        return false;
                    }
                } else if ((double)blockPos.getY() > TestAutoCrystal3.mc.player.posY + (double)TestAutoCrystal3.mc.player.getEyeHeight() ? TestAutoCrystal3.mc.player.getDistance((double)blockPos.getX() + 0.5, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5) > (double)this.placeWallsRange.getValue().floatValue() : TestAutoCrystal3.mc.player.getDistance((double)blockPos.getX() + 0.5, (double)(blockPos.getY() + 1), (double)blockPos.getZ() + 0.5) > (double)this.placeWallsRange.getValue().floatValue()) {
                    return false;
                }
            }
        } else if (this.strictDirection.getValue().booleanValue()) {
            boolean bl;
            block27: {
                Vec3d vec3d = TestAutoCrystal3.mc.player.getPositionVector().addVector(0.0, (double)TestAutoCrystal3.mc.player.getEyeHeight(), 0.0);
                bl = false;
                if (this.directionMode.getValue() == DirectionMode.VANILLA) {
                    for (EnumFacing enumFacing : EnumFacing.values()) {
                        Vec3d vec3d4 = new Vec3d((double)blockPos.getX() + 0.5 + (double)enumFacing.getDirectionVec().getX() * 0.5, (double)blockPos.getY() + 0.5 + (double)enumFacing.getDirectionVec().getY() * 0.5, (double)blockPos.getZ() + 0.5 + (double)enumFacing.getDirectionVec().getZ() * 0.5);
                        if (!(vec3d.distanceTo(vec3d4) <= (double)this.placeRange.getValue().floatValue())) continue;
                        bl = true;
                        break;
                    }
                } else {
                    double d = 0.45;
                    double d8 = 0.05;
                    double d9 = 0.95;
                    for (double d10 = d8; d10 <= d9; d10 += d) {
                        for (double d11 = d8; d11 <= d9; d11 += d) {
                            for (double d12 = d8; d12 <= d9; d12 += d) {
                                Vec3d vec3d5 = new Vec3d((Vec3i)blockPos).addVector(d10, d11, d12);
                                double d13 = vec3d.distanceTo(vec3d5);
                                if (!(d13 <= (double)this.placeRange.getValue().floatValue())) continue;
                                bl = true;
                                break block27;
                            }
                        }
                    }
                }
            }
            if (!bl) {
                return false;
            }
        }
        return TestAutoCrystal3.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2, blockPos3.add(1, 1, 1))).stream().filter(entity -> !this.breakLocations.containsKey(entity.getEntityId()) && (!(entity instanceof EntityEnderCrystal) || entity.ticksExisted > 20)).count() == 0L;
    }

    private void runInstantThread() {
        if (this.mergeOffset.getValue().floatValue() == 0.0f) {
            this.doInstant();
        } else {
            this.shouldRunThread.set(true);
            if (this.thread == null || this.thread.isInterrupted() || !this.thread.isAlive()) {
                if (this.thread == null) {
                    this.thread = new Thread(InstantThread.getInstance(this));
                }
                if (this.thread != null && (this.thread.isInterrupted() || !this.thread.isAlive())) {
                    this.thread = new Thread(InstantThread.getInstance(this));
                }
                if (this.thread != null && this.thread.getState() == Thread.State.NEW) {
                    try {
                        this.thread.start();
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                }
            }
        }
    }

    private double getDistance(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = d - d4;
        double d8 = d2 - d5;
        double d9 = d3 - d6;
        return Math.sqrt(d7 * d7 + d8 * d8 + d9 * d9);
    }

    public static void glBillboardDistanceScaled(float f, float f2, float f3, EntityPlayer entityPlayer, float f4) {
        TestAutoCrystal3.glBillboard(f, f2, f3);
        int n = (int)entityPlayer.getDistance((double)f, (double)f2, (double)f3);
        float f5 = (float)n / 2.0f / (2.0f + (2.0f - f4));
        if (f5 < 1.0f) {
            f5 = 1.0f;
        }
        GlStateManager.scale((float)f5, (float)f5, (float)f5);
    }

    public boolean placeCrystal(BlockPos blockPos, EnumFacing enumFacing) {
        if (blockPos != null) {
            if (this.autoSwap.getValue().booleanValue()) {
                if (this.switchTimer.passedMs((long)(this.swapDelay.getValue().floatValue() * 50.0f))) {
                    if (!this.setCrystalSlot()) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            if (!this.isOffhand() && TestAutoCrystal3.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL) {
                return false;
            }
            if (TestAutoCrystal3.mc.world.getBlockState(blockPos.up()).getBlock() == Blocks.FIRE) {
                TestAutoCrystal3.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos.up(), EnumFacing.DOWN));
                TestAutoCrystal3.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos.up(), EnumFacing.DOWN));
                return true;
            }
            this.isPlacing = true;
            if (this.postResult == null) {
                BlockUtilSC.rightClickBlock(blockPos, TestAutoCrystal3.mc.player.getPositionVector().addVector(0.0, (double)TestAutoCrystal3.mc.player.getEyeHeight(), 0.0), this.isOffhand() ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, enumFacing, true);
            } else {
                TestAutoCrystal3.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, this.isOffhand() ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, (float)this.postResult.hitVec.xCoord, (float)this.postResult.hitVec.yCoord, (float)this.postResult.hitVec.zCoord));
                TestAutoCrystal3.mc.player.connection.sendPacket((Packet)new CPacketAnimation(this.isOffhand() ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND));
            }
            if (this.foundDoublePop && this.renderTarget != null) {
                this.totemPops.put(this.renderTarget, new Timer());
            }
            this.isPlacing = false;
            this.placeLocations.put(blockPos, System.currentTimeMillis());
            if (this.security.getValue().floatValue() >= 0.5f) {
                this.selfPlacePositions.add(blockPos);
            }
            this.renderTimeoutTimer.reset();
            this.prevPlacePos = blockPos;
            return true;
        }
        return false;
    }

    public void setSwordSlot() {
        int n = CrystalUtilSC.getSwordSlot();
        if (TestAutoCrystal3.mc.player.inventory.currentItem != n && n != -1) {
            TestAutoCrystal3.mc.player.inventory.currentItem = n;
            TestAutoCrystal3.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
            this.switchTimer.reset();
            this.noGhostTimer.reset();
        }
    }

    public boolean isMoving(EntityPlayer entityPlayer) {
        return (double)entityPlayer.field_191988_bg != 0.0 || (double)entityPlayer.moveStrafing != 0.0;
    }

    private boolean breakCrystal(EntityEnderCrystal entityEnderCrystal) {
        if (!this.noGhostTimer.passedMs((long)(this.switchDelay.getValue().floatValue() * 100.0f))) {
            return false;
        }
        if (entityEnderCrystal != null) {
            if (this.antiWeakness.getValue().booleanValue() && TestAutoCrystal3.mc.player.isPotionActive(MobEffects.WEAKNESS) && !(TestAutoCrystal3.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword)) {
                this.setSwordSlot();
                return false;
            }
            TestAutoCrystal3.mc.playerController.attackEntity((EntityPlayer)TestAutoCrystal3.mc.player, (Entity)entityEnderCrystal);
            TestAutoCrystal3.mc.player.connection.sendPacket((Packet)new CPacketAnimation(this.isOffhand() ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND));
            this.swingArmAfterBreaking(this.isOffhand() ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
            if (this.syncMode.getValue() == SyncMode.MERGE) {
                this.placeTimer.reset();
            }
            if (this.syncMode.getValue() == SyncMode.STRICT) {
                this.lastBroken.set(true);
            }
            this.inhibitTimer.reset();
            this.inhibitEntity = entityEnderCrystal;
            this.renderBreakingPos = new BlockPos((Entity)entityEnderCrystal).down();
            this.renderBreakingTimer.reset();
            return true;
        }
        return false;
    }

    private List<BlockPos> findCrystalBlocks() {
        NonNullList nonNullList = NonNullList.func_191196_a();
        nonNullList.addAll((Collection)TestAutoCrystal3.getSphere(new BlockPos((Entity)TestAutoCrystal3.mc.player), this.strictDirection.getValue() != false ? this.placeRange.getValue().floatValue() + 2.0f : this.placeRange.getValue().floatValue(), this.placeRange.getValue().intValue(), false, true, 0).stream().filter(this::canPlaceCrystal).collect(Collectors.toList()));
        return nonNullList;
    }

    private int getSwingAnimTime(EntityLivingBase entityLivingBase) {
        if (entityLivingBase.isPotionActive(MobEffects.HASTE)) {
            return 6 - (1 + entityLivingBase.getActivePotionEffect(MobEffects.HASTE).getAmplifier());
        }
        return entityLivingBase.isPotionActive(MobEffects.MINING_FATIGUE) ? 6 + (1 + entityLivingBase.getActivePotionEffect(MobEffects.MINING_FATIGUE).getAmplifier()) * 2 : 6;
    }

    public EntityEnderCrystal getPostBreakPos() {
        return this.postBreakPos;
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketSpawnObject) {
            SPacketSpawnObject sPacketSpawnObject = (SPacketSpawnObject)receive.getPacket();
            if (sPacketSpawnObject.getType() == 51) {
                this.placeLocations.forEach((blockPos, l) -> {
                    if (this.getDistance((double)blockPos.getX() + 0.5, blockPos.getY(), (double)blockPos.getZ() + 0.5, sPacketSpawnObject.getX(), sPacketSpawnObject.getY() - 1.0, sPacketSpawnObject.getZ()) < 1.0) {
                        try {
                            this.placeLocations.remove(blockPos);
                            this.cachePos = null;
                            if (!this.limit.getValue().booleanValue() && this.inhibit.getValue().booleanValue()) {
                                this.scatterTimer.reset();
                            }
                        }
                        catch (ConcurrentModificationException concurrentModificationException) {
                            // empty catch block
                        }
                        if (this.timingMode.getValue() != TimingMode.ADAPTIVE) {
                            return;
                        }
                        if (!this.noGhostTimer.passedMs((long)(this.switchDelay.getValue().floatValue() * 100.0f))) {
                            return;
                        }
                        if (this.tickRunning.get()) {
                            return;
                        }
                        if (TestAutoCrystal3.mc.player.isPotionActive(MobEffects.WEAKNESS)) {
                            return;
                        }
                        if (this.breakLocations.containsKey(sPacketSpawnObject.getEntityID())) {
                            return;
                        }
                        if (TestAutoCrystal3.mc.player.getHealth() + TestAutoCrystal3.mc.player.getAbsorptionAmount() < this.disableUnderHealth.getValue().floatValue() || this.disableWhenKA.getValue() != false && AliceMain.moduleManager.getModuleByClass(Killaura.class).isEnabled() || this.disableWhenPA.getValue() != false && AliceMain.moduleManager.getModuleByClass(PistonCrystal.class).isEnabled() || this.noGapSwitch.getValue() != false && TestAutoCrystal3.mc.player.getActiveItemStack().getItem() instanceof ItemFood || this.noMineSwitch.getValue().booleanValue() && TestAutoCrystal3.mc.playerController.getIsHittingBlock() && TestAutoCrystal3.mc.player.getHeldItemMainhand().getItem() instanceof ItemTool) {
                            this.rotationVector = null;
                            return;
                        }
                        Vec3d vec3d = new Vec3d(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ());
                        if (TestAutoCrystal3.mc.player.getPositionEyes(1.0f).distanceTo(vec3d) > (double)this.breakRange.getValue().floatValue()) {
                            return;
                        }
                        if (!this.breakTimer.passedMs((long)(1000.0f - this.breakSpeed.getValue().floatValue() * 50.0f))) {
                            return;
                        }
                        if (CrystalUtilSC.calculateDamage(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ(), (Entity)TestAutoCrystal3.mc.player) + this.suicideHealth.getValue().floatValue() >= TestAutoCrystal3.mc.player.getHealth() + TestAutoCrystal3.mc.player.getAbsorptionAmount()) {
                            return;
                        }
                        this.breakLocations.put(sPacketSpawnObject.getEntityID(), System.currentTimeMillis());
                        this.bilateralVec = new Vec3d(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ());
                        CPacketUseEntity cPacketUseEntity = new CPacketUseEntity();
                        ((ICPacketUseEntity)cPacketUseEntity).setEntityId(sPacketSpawnObject.getEntityID());
                        ((ICPacketUseEntity)cPacketUseEntity).setAction(CPacketUseEntity.Action.ATTACK);
                        TestAutoCrystal3.mc.player.connection.sendPacket((Packet)new CPacketAnimation(this.isOffhand() ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND));
                        TestAutoCrystal3.mc.player.connection.sendPacket((Packet)cPacketUseEntity);
                        this.swingArmAfterBreaking(this.isOffhand() ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
                        this.renderBreakingPos = new BlockPos(sPacketSpawnObject.getX(), sPacketSpawnObject.getY() - 1.0, sPacketSpawnObject.getZ());
                        this.renderBreakingTimer.reset();
                        this.breakTimer.reset();
                        this.linearTimer.reset();
                        if (this.syncMode.getValue() == SyncMode.MERGE) {
                            this.placeTimer.reset();
                        }
                        if (this.syncMode.getValue() == SyncMode.STRICT) {
                            this.lastBroken.set(true);
                        }
                        if (this.syncMode.getValue() == SyncMode.MERGE) {
                            this.runInstantThread();
                        }
                    }
                });
            }
        } else if (receive.getPacket() instanceof SPacketSoundEffect) {
            SPacketSoundEffect sPacketSoundEffect = (SPacketSoundEffect)receive.getPacket();
            if (sPacketSoundEffect.getCategory() == SoundCategory.BLOCKS && sPacketSoundEffect.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                if (this.inhibitEntity != null && this.inhibitEntity.getDistance(sPacketSoundEffect.getX(), sPacketSoundEffect.getY(), sPacketSoundEffect.getZ()) < 6.0) {
                    this.inhibitEntity = null;
                }
                if (this.security.getValue().floatValue() >= 0.5f) {
                    try {
                        this.selfPlacePositions.remove((Object)new BlockPos(sPacketSoundEffect.getX(), sPacketSoundEffect.getY() - 1.0, sPacketSoundEffect.getZ()));
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {}
                }
            }
        } else if (receive.getPacket() instanceof SPacketEntityStatus) {
            SPacketEntityStatus sPacketEntityStatus = (SPacketEntityStatus)receive.getPacket();
            if (sPacketEntityStatus.getOpCode() == 35 && sPacketEntityStatus.getEntity((World)TestAutoCrystal3.mc.world) instanceof EntityPlayer) {
                this.totemPops.put((EntityPlayer)sPacketEntityStatus.getEntity((World)TestAutoCrystal3.mc.world), new Timer());
            }
        } else if (receive.getPacket() instanceof SPacketPlayerPosLook && this.disableOnTP.getValue().booleanValue() && !AliceMain.moduleManager.getModuleByClass(PacketFlight.class).isEnabled()) {
            this.toggle();
        }
    }

    @Override
    public void onDisable() {
    }

    private boolean isValidCrystalTarget(EntityEnderCrystal entityEnderCrystal) {
        if (TestAutoCrystal3.mc.player.getPositionEyes(1.0f).distanceTo(entityEnderCrystal.getPositionVector()) > (double)this.breakRange.getValue().floatValue()) {
            return false;
        }
        if (this.breakLocations.containsKey(entityEnderCrystal.getEntityId()) && this.limit.getValue().booleanValue()) {
            return false;
        }
        if (this.breakLocations.containsKey(entityEnderCrystal.getEntityId()) && entityEnderCrystal.ticksExisted > this.delay.getValue() + this.attackFactor.getValue()) {
            return false;
        }
        return !(CrystalUtilSC.calculateDamage(entityEnderCrystal, (Entity)TestAutoCrystal3.mc.player) + this.suicideHealth.getValue().floatValue() >= TestAutoCrystal3.mc.player.getHealth() + TestAutoCrystal3.mc.player.getAbsorptionAmount());
    }

    private EntityEnderCrystal findCrystalTarget(List<EntityPlayer> list) {
        this.breakLocations.forEach((n, l) -> {
            if (System.currentTimeMillis() - l > 1000L) {
                this.breakLocations.remove(n);
            }
        });
        if (this.syncMode.getValue() == SyncMode.STRICT && !this.limit.getValue().booleanValue() && this.lastBroken.get()) {
            return null;
        }
        EntityEnderCrystal entityEnderCrystal = null;
        int n2 = (int)Math.max(100.0f, (float)(CrystalUtilSC.ping() + 50) / 1.0f) + 150;
        if (this.inhibit.getValue().booleanValue() && !this.limit.getValue().booleanValue() && !this.inhibitTimer.passedMs(n2) && this.inhibitEntity != null && TestAutoCrystal3.mc.world.getEntityByID(this.inhibitEntity.getEntityId()) != null && this.isValidCrystalTarget(this.inhibitEntity)) {
            entityEnderCrystal = this.inhibitEntity;
            return entityEnderCrystal;
        }
        List<Entity> list2 = this.getCrystalInRange();
        if (list2.isEmpty()) {
            return null;
        }
        if (this.security.getValue().floatValue() >= 1.0f) {
            double d = 0.5;
            for (Entity entity2 : list2) {
                if (!(entity2.getPositionVector().distanceTo(TestAutoCrystal3.mc.player.getPositionEyes(1.0f)) < (double)this.breakWallsRange.getValue().floatValue()) && !CrystalUtilSC.rayTraceBreak(entity2.posX, entity2.posY, entity2.posZ)) continue;
                EntityEnderCrystal entityEnderCrystal2 = (EntityEnderCrystal)entity2;
                double d2 = 0.0;
                for (EntityPlayer entityPlayer : list) {
                    double d3 = CrystalUtilSC.calculateDamage(entityEnderCrystal2, (Entity)entityPlayer);
                    d2 += d3;
                }
                double d4 = CrystalUtilSC.calculateDamage(entityEnderCrystal2, (Entity)TestAutoCrystal3.mc.player);
                if (d4 > d2 * (double)(this.security.getValue().floatValue() - 0.8f) && !this.selfPlacePositions.contains((Object)new BlockPos(entity2.posX, entity2.posY - 1.0, entity2.posZ)) || !(d2 > d)) continue;
                d = d2;
                entityEnderCrystal = entityEnderCrystal2;
            }
        } else {
            entityEnderCrystal = this.security.getValue().floatValue() >= 0.5f ? (EntityEnderCrystal)list2.stream().filter(entity -> this.selfPlacePositions.contains((Object)new BlockPos(entity.posX, entity.posY - 1.0, entity.posZ))).filter(entity -> entity.getPositionVector().distanceTo(TestAutoCrystal3.mc.player.getPositionEyes(1.0f)) < (double)this.breakWallsRange.getValue().floatValue() || CrystalUtilSC.rayTraceBreak(entity.posX, entity.posY, entity.posZ)).min(Comparator.comparing(entity -> Float.valueOf(TestAutoCrystal3.mc.player.getDistanceToEntity(entity)))).orElse(null) : (EntityEnderCrystal)list2.stream().filter(entity -> entity.getPositionVector().distanceTo(TestAutoCrystal3.mc.player.getPositionEyes(1.0f)) < (double)this.breakWallsRange.getValue().floatValue() || CrystalUtilSC.rayTraceBreak(entity.posX, entity.posY, entity.posZ)).min(Comparator.comparing(entity -> Float.valueOf(TestAutoCrystal3.mc.player.getDistanceToEntity(entity)))).orElse(null);
        }
        return entityEnderCrystal;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayerPre(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 0) {
            this.placeLocations.forEach((blockPos, l) -> {
                if (System.currentTimeMillis() - l > 1500L) {
                    this.placeLocations.remove(blockPos);
                }
            });
            --this.ticks;
            if (this.bilateralVec != null) {
                for (Entity entity : TestAutoCrystal3.mc.world.loadedEntityList) {
                    if (!(entity instanceof EntityEnderCrystal) || !(entity.getDistance(this.bilateralVec.xCoord, this.bilateralVec.yCoord, this.bilateralVec.zCoord) <= 6.0)) continue;
                    this.breakLocations.put(entity.getEntityId(), System.currentTimeMillis());
                }
                this.bilateralVec = null;
            }
            if (updateWalkingPlayerEvent.isCanceled()) {
                return;
            }
            this.postBreakPos = null;
            this.postPlacePos = null;
            this.postFacing = null;
            this.postResult = null;
            this.foundDoublePop = false;
            this.handleSequential();
            if (this.rotationMode.getValue() != RotationMode.OFF && !this.rotationTimer.passedMs(650L) && this.rotationVector != null) {
                if (this.rotationMode.getValue() == RotationMode.TRACK) {
                    this.rotations = RotationManager.calculateAngle(TestAutoCrystal3.mc.player.getPositionEyes(1.0f), this.rotationVector);
                }
                if (this.yawAngle.getValue().floatValue() < 1.0f && this.yawStep.getValue() != YawStepMode.OFF && (this.postBreakPos != null || this.yawStep.getValue() == YawStepMode.FULL)) {
                    if (this.ticks > 0) {
                        this.rotations[0] = ((IEntityPlayerSP)TestAutoCrystal3.mc.player).getLastReportedYaw();
                        this.postBreakPos = null;
                        this.postPlacePos = null;
                    } else {
                        float f = MathHelper.wrapDegrees((float)(this.rotations[0] - ((IEntityPlayerSP)TestAutoCrystal3.mc.player).getLastReportedYaw()));
                        if (Math.abs(f) > 180.0f * this.yawAngle.getValue().floatValue()) {
                            this.rotations[0] = ((IEntityPlayerSP)TestAutoCrystal3.mc.player).getLastReportedYaw() + f * (180.0f * this.yawAngle.getValue().floatValue() / Math.abs(f));
                            this.postBreakPos = null;
                            this.postPlacePos = null;
                            this.ticks = this.yawTicks.getValue();
                        }
                    }
                }
                SilentRotaionUtil.lookAtAngles(this.rotations[0], this.rotations[1]);
            }
        }
    }

    private List<Entity> getCrystalInRange() {
        return TestAutoCrystal3.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal).filter(entity -> this.isValidCrystalTarget((EntityEnderCrystal)entity)).collect(Collectors.toList());
    }

    @Override
    public void onEnable() {
        this.postBreakPos = null;
        this.postPlacePos = null;
        this.postFacing = null;
        this.postResult = null;
        this.prevPlacePos = null;
        this.cachePos = null;
        this.bilateralVec = null;
        this.lastBroken.set(false);
        this.rotationVector = null;
        this.rotationTimer.reset();
        this.isPlacing = false;
        this.foundDoublePop = false;
        this.totemPops.clear();
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayerPost(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        aboba = this.mergeOffset.getValue().floatValue() / 10.0f;
        if (updateWalkingPlayerEvent.getStage() == 1) {
            if (this.postBreakPos != null) {
                if (this.breakCrystal(this.postBreakPos)) {
                    this.breakTimer.reset();
                    this.breakLocations.put(this.postBreakPos.getEntityId(), System.currentTimeMillis());
                    for (Entity entity : TestAutoCrystal3.mc.world.loadedEntityList) {
                        if (!(entity instanceof EntityEnderCrystal) || !(entity.getDistance(this.postBreakPos.posX, this.postBreakPos.posY, this.postBreakPos.posZ) <= 6.0)) continue;
                        this.breakLocations.put(entity.getEntityId(), System.currentTimeMillis());
                    }
                    this.postBreakPos = null;
                    if (this.syncMode.getValue() == SyncMode.MERGE) {
                        this.runInstantThread();
                    }
                }
            } else if (this.postPlacePos != null) {
                if (!this.placeCrystal(this.postPlacePos, this.postFacing)) {
                    this.shouldRunThread.set(false);
                    this.postPlacePos = null;
                    return;
                }
                this.placeTimer.reset();
                this.postPlacePos = null;
            }
        }
    }

    private void doInstant() {
        BlockPos blockPos;
        List<BlockPos> list;
        if (this.confirm.getValue() != ConfirmMode.OFF && (this.confirm.getValue() != ConfirmMode.FULL || this.inhibitEntity == null || (double)this.inhibitEntity.ticksExisted >= Math.floor(this.delay.getValue().intValue()))) {
            int n = (int)Math.max(100.0f, (float)(CrystalUtilSC.ping() + 50) / 1.0f) + 150;
            if (this.cachePos != null && !this.cacheTimer.passedMs(n + 100) && this.canPlaceCrystal(this.cachePos)) {
                this.postPlacePos = this.cachePos;
                this.postFacing = this.handlePlaceRotation(this.postPlacePos);
                if (this.postPlacePos != null) {
                    if (!this.placeCrystal(this.postPlacePos, this.postFacing)) {
                        this.postPlacePos = null;
                        return;
                    }
                    this.placeTimer.reset();
                    this.postPlacePos = null;
                }
                return;
            }
        }
        if (!(list = this.findCrystalBlocks()).isEmpty() && (blockPos = this.findPlacePosition(list, this.getTargetsInRange())) != null) {
            this.postPlacePos = blockPos;
            this.postFacing = this.handlePlaceRotation(this.postPlacePos);
            if (this.postPlacePos != null) {
                if (!this.placeCrystal(this.postPlacePos, this.postFacing)) {
                    this.postPlacePos = null;
                    return;
                }
                this.placeTimer.reset();
                this.postPlacePos = null;
            }
        }
    }

    private boolean isDoublePoppable(EntityPlayer entityPlayer, float f) {
        if (this.predictPops.getValue().booleanValue() && entityPlayer.getHealth() + entityPlayer.getAbsorptionAmount() <= 2.0f && (double)f > (double)entityPlayer.getHealth() + (double)entityPlayer.getAbsorptionAmount() + 0.5 && f <= 4.0f) {
            Timer timer = this.totemPops.get((Object)entityPlayer);
            return timer == null || timer.passedMs(500L);
        }
        return false;
    }

    public boolean isOffhand() {
        return TestAutoCrystal3.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
    }

    private BlockPos findPlacePosition(List<BlockPos> list, List<EntityPlayer> list2) {
        if (list2.isEmpty()) {
            return null;
        }
        float f = 0.5f;
        EntityPlayer entityPlayer = null;
        BlockPos blockPos = null;
        this.foundDoublePop = false;
        EntityPlayer entityPlayer2 = null;
        for (BlockPos blockPos2 : list) {
            float f2 = CrystalUtilSC.calculateDamage(blockPos2, (Entity)TestAutoCrystal3.mc.player);
            if (!((double)f2 + (double)this.suicideHealth.getValue().floatValue() < (double)(TestAutoCrystal3.mc.player.getHealth() + TestAutoCrystal3.mc.player.getAbsorptionAmount())) || !(f2 <= this.maxSelfPlace.getValue().floatValue())) continue;
            if (this.targetingMode.getValue() != TargetingMode.ALL) {
                entityPlayer2 = list2.get(0);
                if (entityPlayer2.getDistance((double)blockPos2.getX() + 0.5, (double)blockPos2.getY() + 0.5, (double)blockPos2.getZ() + 0.5) > (double)this.crystalRange.getValue().floatValue()) continue;
                float f3 = CrystalUtilSC.calculateDamage(blockPos2, (Entity)entityPlayer2);
                if (this.isDoublePoppable(entityPlayer2, f3) && (blockPos == null || entityPlayer2.getDistanceSq(blockPos2) < entityPlayer2.getDistanceSq(blockPos))) {
                    entityPlayer = entityPlayer2;
                    f = f3;
                    blockPos = blockPos2;
                    this.foundDoublePop = true;
                    continue;
                }
                if (this.foundDoublePop || !(f3 > f) || !(f3 * this.compromise.getValue().floatValue() > f2) && !(f3 > entityPlayer2.getHealth() + entityPlayer2.getAbsorptionAmount()) || f3 < this.minPlaceDamage.getValue().floatValue() && entityPlayer2.getHealth() + entityPlayer2.getAbsorptionAmount() > this.faceplaceHealth.getValue().floatValue() && !this.forceFaceplace.getValue().isDown() && !this.shouldArmorBreak(entityPlayer2)) continue;
                f = f3;
                entityPlayer = entityPlayer2;
                blockPos = blockPos2;
                continue;
            }
            for (EntityPlayer entityPlayer3 : list2) {
                if (entityPlayer3.equals((Object)entityPlayer2) || entityPlayer3.getDistance((double)blockPos2.getX() + 0.5, (double)blockPos2.getY() + 0.5, (double)blockPos2.getZ() + 0.5) > (double)this.crystalRange.getValue().floatValue()) continue;
                float f4 = CrystalUtilSC.calculateDamage(blockPos2, (Entity)entityPlayer3);
                if (this.isDoublePoppable(entityPlayer3, f4) && (blockPos == null || entityPlayer3.getDistanceSq(blockPos2) < entityPlayer3.getDistanceSq(blockPos))) {
                    entityPlayer = entityPlayer3;
                    f = f4;
                    blockPos = blockPos2;
                    this.foundDoublePop = true;
                    continue;
                }
                if (this.foundDoublePop || !(f4 > f) || !(f4 * this.compromise.getValue().floatValue() > f2) && !(f4 > entityPlayer3.getHealth() + entityPlayer3.getAbsorptionAmount()) || f4 < this.minPlaceDamage.getValue().floatValue() && entityPlayer3.getHealth() + entityPlayer3.getAbsorptionAmount() > this.faceplaceHealth.getValue().floatValue() && !this.forceFaceplace.getValue().isDown() && !this.shouldArmorBreak(entityPlayer3)) continue;
                f = f4;
                entityPlayer = entityPlayer3;
                blockPos = blockPos2;
            }
        }
        if (entityPlayer != null && blockPos != null) {
            this.renderTarget = entityPlayer;
            this.renderTargetTimer.reset();
        }
        if (blockPos != null) {
            this.renderBlock = blockPos;
            this.renderDamage = f;
        }
        this.cachePos = blockPos;
        this.cacheTimer.reset();
        return blockPos;
    }

    private void swingArmAfterBreaking(EnumHand enumHand) {
        if (!this.swing.getValue().booleanValue()) {
            return;
        }
        ItemStack itemStack = TestAutoCrystal3.mc.player.getHeldItem(enumHand);
        if (!itemStack.func_190926_b() && itemStack.getItem().onEntitySwing((EntityLivingBase)TestAutoCrystal3.mc.player, itemStack)) {
            return;
        }
        if (!TestAutoCrystal3.mc.player.isSwingInProgress || TestAutoCrystal3.mc.player.swingProgressInt >= this.getSwingAnimTime((EntityLivingBase)TestAutoCrystal3.mc.player) / 2 || TestAutoCrystal3.mc.player.swingProgressInt < 0) {
            TestAutoCrystal3.mc.player.swingProgressInt = -1;
            TestAutoCrystal3.mc.player.isSwingInProgress = true;
            TestAutoCrystal3.mc.player.swingingHand = enumHand;
        }
    }

    private List<EntityPlayer> getTargetsInRange() {
        List<Object> list = TestAutoCrystal3.mc.world.playerEntities.stream().filter(entityPlayer -> entityPlayer != TestAutoCrystal3.mc.player && entityPlayer != mc.getRenderViewEntity()).filter(entityPlayer -> !entityPlayer.isDead).filter(entityPlayer -> !AliceMain.friendManager.isFriend(entityPlayer.getName())).filter(entityPlayer -> entityPlayer.getHealth() > 0.0f).filter(entityPlayer -> TestAutoCrystal3.mc.player.getDistanceToEntity((Entity)entityPlayer) < this.enemyRange.getValue().floatValue()).sorted(Comparator.comparing(entityPlayer -> Float.valueOf(TestAutoCrystal3.mc.player.getDistanceToEntity((Entity)entityPlayer)))).collect(Collectors.toList());
        if (this.targetingMode.getValue() == TargetingMode.SMART) {
            List list2 = list.stream().filter(entityPlayer -> !BlockUtilSC.isHole(new BlockPos((Entity)entityPlayer)) && (TestAutoCrystal3.mc.world.getBlockState(new BlockPos((Entity)entityPlayer)).getBlock() == Blocks.AIR || TestAutoCrystal3.mc.world.getBlockState(new BlockPos((Entity)entityPlayer)).getBlock() == Blocks.WEB || TestAutoCrystal3.mc.world.getBlockState(new BlockPos((Entity)entityPlayer)).getBlock() instanceof BlockLiquid)).sorted(Comparator.comparing(entityPlayer -> Float.valueOf(TestAutoCrystal3.mc.player.getDistanceToEntity((Entity)entityPlayer)))).collect(Collectors.toList());
            if (list2.size() > 0) {
                list = list2;
            }
            if ((list2 = list.stream().filter(entityPlayer -> entityPlayer.getHealth() + entityPlayer.getAbsorptionAmount() < 10.0f).sorted(Comparator.comparing(entityPlayer -> Float.valueOf(TestAutoCrystal3.mc.player.getDistanceToEntity((Entity)entityPlayer)))).collect(Collectors.toList())).size() > 0) {
                list = list2;
            }
        }
        return list;
    }

    public void handleBreakRotation(double d, double d2, double d3) {
        if (this.rotationMode.getValue() != RotationMode.OFF) {
            if (this.rotationMode.getValue() == RotationMode.INTERACT && this.rotationVector != null && !this.rotationTimer.passedMs(650L)) {
                if (this.rotationVector.yCoord < d2 - 0.1) {
                    this.rotationVector = new Vec3d(this.rotationVector.xCoord, d2, this.rotationVector.zCoord);
                }
                this.rotations = RotationManager.calculateAngle(TestAutoCrystal3.mc.player.getPositionEyes(1.0f), this.rotationVector);
                this.rotationTimer.reset();
                return;
            }
            AxisAlignedBB axisAlignedBB = new AxisAlignedBB(d - 1.0, d2, d3 - 1.0, d + 1.0, d2 + 2.0, d3 + 1.0);
            Vec3d vec3d = new Vec3d(TestAutoCrystal3.mc.player.posX, TestAutoCrystal3.mc.player.getEntityBoundingBox().minY + (double)TestAutoCrystal3.mc.player.getEyeHeight(), TestAutoCrystal3.mc.player.posZ);
            double d4 = 0.1;
            double d5 = 0.15;
            double d6 = 0.85;
            if (axisAlignedBB.intersectsWith(TestAutoCrystal3.mc.player.getEntityBoundingBox())) {
                d5 = 0.4;
                d6 = 0.6;
                d4 = 0.05;
            }
            Vec3d vec3d2 = null;
            double[] arrd = null;
            boolean bl = false;
            for (double d7 = d5; d7 <= d6; d7 += d4) {
                for (double d8 = d5; d8 <= d6; d8 += d4) {
                    for (double d9 = d5; d9 <= d6; d9 += d4) {
                        Vec3d vec3d3 = new Vec3d(axisAlignedBB.minX + (axisAlignedBB.maxX - axisAlignedBB.minX) * d7, axisAlignedBB.minY + (axisAlignedBB.maxY - axisAlignedBB.minY) * d8, axisAlignedBB.minZ + (axisAlignedBB.maxZ - axisAlignedBB.minZ) * d9);
                        double d10 = vec3d3.xCoord - vec3d.xCoord;
                        double d11 = vec3d3.yCoord - vec3d.yCoord;
                        double d12 = vec3d3.zCoord - vec3d.zCoord;
                        double[] arrd2 = new double[]{MathHelper.wrapDegrees((float)((float)Math.toDegrees(Math.atan2(d12, d10)) - 90.0f)), MathHelper.wrapDegrees((float)((float)(-Math.toDegrees(Math.atan2(d11, Math.sqrt(d10 * d10 + d12 * d12))))))};
                        boolean bl2 = true;
                        if (this.directionMode.getValue() != DirectionMode.VANILLA && !CrystalUtilSC.isVisible(vec3d3)) {
                            bl2 = false;
                        }
                        if (this.strictDirection.getValue().booleanValue()) {
                            if (vec3d2 != null && arrd != null) {
                                if (!bl2 && bl || !(TestAutoCrystal3.mc.player.getPositionVector().addVector(0.0, (double)TestAutoCrystal3.mc.player.getEyeHeight(), 0.0).distanceTo(vec3d3) < TestAutoCrystal3.mc.player.getPositionVector().addVector(0.0, (double)TestAutoCrystal3.mc.player.getEyeHeight(), 0.0).distanceTo(vec3d2))) continue;
                                vec3d2 = vec3d3;
                                arrd = arrd2;
                                continue;
                            }
                            vec3d2 = vec3d3;
                            arrd = arrd2;
                            bl = bl2;
                            continue;
                        }
                        if (vec3d2 != null && arrd != null) {
                            if (!bl2 && bl || !(Math.hypot(((arrd2[0] - (double)((IEntityPlayerSP)TestAutoCrystal3.mc.player).getLastReportedYaw()) % 360.0 + 540.0) % 360.0 - 180.0, arrd2[1] - (double)((IEntityPlayerSP)TestAutoCrystal3.mc.player).getLastReportedPitch()) < Math.hypot(((arrd[0] - (double)((IEntityPlayerSP)TestAutoCrystal3.mc.player).getLastReportedYaw()) % 360.0 + 540.0) % 360.0 - 180.0, arrd[1] - (double)((IEntityPlayerSP)TestAutoCrystal3.mc.player).getLastReportedPitch()))) continue;
                            vec3d2 = vec3d3;
                            arrd = arrd2;
                            continue;
                        }
                        vec3d2 = vec3d3;
                        arrd = arrd2;
                        bl = bl2;
                    }
                }
            }
            if (vec3d2 != null && arrd != null) {
                this.rotationTimer.reset();
                this.rotationVector = vec3d2;
                this.rotations = RotationManager.calculateAngle(TestAutoCrystal3.mc.player.getPositionEyes(1.0f), this.rotationVector);
            }
        }
    }

    private static enum TargetingMode {
        ALL,
        SMART,
        NEAREST;

    }

    public static enum SyncMode {
        STRICT,
        MERGE;

    }

    private static enum RotationMode {
        OFF,
        TRACK,
        INTERACT;

    }

    private static enum TimingMode {
        SEQUENTIAL,
        ADAPTIVE;

    }

    private static enum ConfirmMode {
        OFF,
        SEMI,
        FULL;

    }

    public static enum RenderTextMode {
        NONE,
        FLAT,
        SHADED;

    }

    public static enum DirectionMode {
        VANILLA,
        NORMAL,
        STRICT;

    }

    private static enum YawStepMode {
        OFF,
        BREAK,
        FULL;

    }

    private static class InstantThread
    implements Runnable {
        private static /* synthetic */ InstantThread INSTANCE;
        private /* synthetic */ TestAutoCrystal3 autoCrystal;

        @Override
        public void run() {
            if (this.autoCrystal.shouldRunThread.get()) {
                try {
                    Thread.sleep((long)(aboba * 40.0f));
                }
                catch (InterruptedException interruptedException) {
                    this.autoCrystal.thread.interrupt();
                }
                if (!this.autoCrystal.shouldRunThread.get()) {
                    return;
                }
                this.autoCrystal.shouldRunThread.set(false);
                if (this.autoCrystal.tickRunning.get()) {
                    return;
                }
                this.autoCrystal.doInstant();
            }
        }

        private InstantThread() {
        }

        private static InstantThread getInstance(TestAutoCrystal3 testAutoCrystal3) {
            if (INSTANCE == null) {
                INSTANCE = new InstantThread();
                InstantThread.INSTANCE.autoCrystal = testAutoCrystal3;
            }
            return INSTANCE;
        }
    }
}

