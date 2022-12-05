//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  io.netty.util.internal.ConcurrentSet
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.ItemEndCrystal
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.network.play.client.CPacketUseEntity$Action
 *  net.minecraft.network.play.server.SPacketDestroyEntities
 *  net.minecraft.network.play.server.SPacketEntityStatus
 *  net.minecraft.network.play.server.SPacketExplosion
 *  net.minecraft.network.play.server.SPacketSoundEffect
 *  net.minecraft.network.play.server.SPacketSpawnObject
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 */
package me.snow.aclient.module.modules.combat.autocrystal;

import com.mojang.authlib.GameProfile;
import io.netty.util.internal.ConcurrentSet;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.gui.LuigiGui;
import me.snow.aclient.core.setting.Bind;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.ClientEvent;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.combat.autocrystal.ACAutoSwitch;
import me.snow.aclient.module.modules.combat.autocrystal.ACRenderMode;
import me.snow.aclient.module.modules.combat.autocrystal.ACRotate;
import me.snow.aclient.module.modules.combat.autocrystal.ACSettings;
import me.snow.aclient.module.modules.combat.autocrystal.ACSwing;
import me.snow.aclient.module.modules.combat.autocrystal.ACYaw;
import me.snow.aclient.module.modules.misc.NoSoundLag;
import me.snow.aclient.module.modules.player.Speedmine;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.DamageUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.RenderUtil;
import me.snow.aclient.util.Timer;
import me.snow.aclient.util.cc.InventoryUtilCC;
import me.snow.aclient.util.skid.oyvey.ColorUtil2;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.server.SPacketDestroyEntities;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.network.play.server.SPacketSpawnObject;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class AutoCrystal
extends Module {
    public /* synthetic */ Setting<Boolean> oneDot15;
    public /* synthetic */ Setting<Boolean> instant;
    public final /* synthetic */ Timer yawStepTimer;
    private final /* synthetic */ Setting<Integer> oAlpha;
    public final /* synthetic */ Setting<Boolean> antiBlock;
    public /* synthetic */ Setting<Boolean> manualMinDmg;
    private /* synthetic */ Queue<Entity> attackList;
    public static /* synthetic */ Set<BlockPos> brokenPos;
    private /* synthetic */ int minDmgCount;
    public /* synthetic */ Setting<Boolean> antiCommit;
    public /* synthetic */ Setting<Boolean> fullCalc;
    private final /* synthetic */ Setting<Boolean> slabFactor;
    public /* synthetic */ Setting<Integer> predictDelay;
    private final /* synthetic */ Setting<Float> lineWidth;
    public /* synthetic */ Setting<Boolean> manual;
    public /* synthetic */ Setting<Boolean> syncCount;
    private final /* synthetic */ Setting<Boolean> scaleFactor;
    public /* synthetic */ Setting<Boolean> syncySync;
    private final /* synthetic */ Timer switchTimer;
    public /* synthetic */ Setting<Boolean> slowFaceBreak;
    public /* synthetic */ Setting<Boolean> soundConfirm;
    private final /* synthetic */ Timer predictTimer;
    public /* synthetic */ Setting<Boolean> calcEvenIfNoDamage;
    public /* synthetic */ Setting<AntiFriendPop> antiFriendPop;
    public /* synthetic */ Setting<Raytrace> raytrace;
    public /* synthetic */ Setting<Boolean> sound;
    public /* synthetic */ Setting<Boolean> superSafe;
    public /* synthetic */ Setting<Float> placetrace;
    public /* synthetic */ Setting<Float> predictOffset;
    public /* synthetic */ Setting<Bind> switchBind;
    public /* synthetic */ Setting<Boolean> predictCalc;
    private /* synthetic */ BlockPos syncedCrystalPos;
    public /* synthetic */ Setting<Boolean> predictRotate;
    public /* synthetic */ Setting<Integer> predictTicks;
    private final /* synthetic */ Setting<Integer> bBlue;
    public /* synthetic */ Setting<Integer> minArmor;
    private final /* synthetic */ List<RenderPos> positions;
    private /* synthetic */ Thread thread;
    private /* synthetic */ BlockPos lastRenderPos;
    public /* synthetic */ Setting<Integer> breakDelay;
    private final /* synthetic */ AtomicBoolean shouldInterrupt;
    public /* synthetic */ Setting<Float> breaktrace;
    public /* synthetic */ Setting<Integer> confirm;
    public final /* synthetic */ Setting<Boolean> attackOppositeHand;
    private /* synthetic */ float pitch;
    private final /* synthetic */ Timer syncTimer;
    private /* synthetic */ BlockPos lastPos;
    public /* synthetic */ Setting<Switch> switchMode;
    private /* synthetic */ int rotationPacketsSpoofed;
    private /* synthetic */ EntityPlayer currentSyncTarget;
    public /* synthetic */ Setting<Target> targetMode;
    public /* synthetic */ Setting<Boolean> resetBreakTimer;
    public /* synthetic */ Setting<Float> popDamage;
    private static /* synthetic */ AutoCrystal instance;
    private /* synthetic */ boolean foundDoublePop;
    public /* synthetic */ Setting<Boolean> actualSlowBreak;
    private final /* synthetic */ Setting<Boolean> fadeFactor;
    private final /* synthetic */ Setting<Float> moveSpeed;
    private /* synthetic */ boolean offHand;
    private /* synthetic */ double renderDamage;
    public /* synthetic */ Setting<Integer> packets;
    public /* synthetic */ Setting<Float> facePlace;
    public /* synthetic */ Setting<Boolean> outline;
    private final /* synthetic */ Setting<Float> accel;
    private /* synthetic */ boolean switching;
    private /* synthetic */ Map<Entity, Float> crystalMap;
    private final /* synthetic */ Timer syncroTimer;
    public /* synthetic */ Setting<DamageSync> damageSync;
    public /* synthetic */ Setting<Double> popHealth;
    private final /* synthetic */ Timer placeTimer;
    public /* synthetic */ Setting<Boolean> fullSync;
    public /* synthetic */ Setting<Boolean> predictPos;
    public /* synthetic */ Setting<ACAutoSwitch> autoSwitch;
    public /* synthetic */ Entity efficientTarget;
    public static /* synthetic */ Set<BlockPos> lowDmgPos;
    private final /* synthetic */ Setting<Integer> max;
    private final /* synthetic */ Setting<Float> slabHeight;
    public /* synthetic */ Setting<ThreadMode> threadMode;
    private /* synthetic */ boolean mainHand;
    public /* synthetic */ Setting<Boolean> mineSwitch;
    private /* synthetic */ boolean didRotation;
    public /* synthetic */ Setting<PredictTimer> instantTimer;
    private final /* synthetic */ Setting<Float> duration;
    public /* synthetic */ Setting<Boolean> hyperSync;
    public /* synthetic */ Setting<Boolean> rotateFirst;
    public /* synthetic */ Setting<Float> rainbowSaturationAA22;
    public /* synthetic */ Setting<Float> range;
    public /* synthetic */ Setting<Boolean> doublePop;
    private final /* synthetic */ Timer renderTimer;
    private final /* synthetic */ Setting<Integer> bGreen;
    public /* synthetic */ Setting<Integer> YawStepTicks;
    private /* synthetic */ float yaw;
    public /* synthetic */ Setting<Integer> threadDelay;
    private final /* synthetic */ Setting<Integer> oGreen;
    public /* synthetic */ Setting<Boolean> gigaSync;
    public /* synthetic */ Setting<Float> minMinDmg;
    public /* synthetic */ Setting<Integer> popTime;
    public static /* synthetic */ Set<BlockPos> placedPos;
    public /* synthetic */ Setting<Logic> logic;
    private /* synthetic */ boolean addTolowDmg;
    public /* synthetic */ Setting<Boolean> webAttack;
    private /* synthetic */ BlockPos renderPos;
    private final /* synthetic */ Setting<Integer> bAlpha;
    public /* synthetic */ Setting<Boolean> noCount;
    public /* synthetic */ Setting<Float> maxSelfBreak;
    private final /* synthetic */ Setting<Integer> bRed;
    public /* synthetic */ Setting<Boolean> predictFriendDmg;
    private final /* synthetic */ Setting<Integer> oRed;
    public /* synthetic */ Setting<Boolean> colorSync;
    private final /* synthetic */ Queue<CPacketUseEntity> packetUseEntities;
    public /* synthetic */ boolean rotating;
    private /* synthetic */ BlockPos syncedPlayerPos;
    public /* synthetic */ Setting<Boolean> extraSelfCalc;
    public /* synthetic */ Setting<Boolean> lethalSwitch;
    private /* synthetic */ AxisAlignedBB renderBB;
    private /* synthetic */ PlaceInfo placeInfo;
    private final /* synthetic */ Timer manualTimer;
    private final /* synthetic */ Map<EntityPlayer, Timer> totemPops;
    public /* synthetic */ Setting<Boolean> syncedFeetPlace;
    public /* synthetic */ Setting<Float> dropOff;
    public /* synthetic */ Setting<ACYaw> yawstepmode;
    private final /* synthetic */ Setting<Integer> swapdelay;
    public /* synthetic */ Setting<Boolean> suicide;
    public /* synthetic */ Setting<Float> maxSelfPlace;
    private /* synthetic */ ScheduledExecutorService executor;
    public /* synthetic */ Setting<Integer> placeDelay;
    public /* synthetic */ Setting<Float> breakRange;
    public final /* synthetic */ Setting<Boolean> removeAfterAttack;
    public /* synthetic */ Setting<Float> rainbowBrightnessAA22;
    public /* synthetic */ Setting<Integer> wasteAmount;
    public /* synthetic */ Setting<Boolean> sync;
    public /* synthetic */ Setting<Boolean> antiWeakness;
    public /* synthetic */ Setting<Integer> rotations;
    private final /* synthetic */ AtomicBoolean threadOngoing;
    public /* synthetic */ Setting<Integer> facePlaceSpeed;
    private final /* synthetic */ Setting<ACSettings> setting;
    public /* synthetic */ Setting<Float> placeRange;
    public static /* synthetic */ EntityPlayer target;
    public /* synthetic */ Setting<Boolean> holdFacePlace;
    public /* synthetic */ Setting<Boolean> holySync;
    private /* synthetic */ float timePassed;
    public /* synthetic */ Setting<ACRotate> rotate;
    public /* synthetic */ Setting<ACSwing> swingMode;
    private final /* synthetic */ Timer breakTimer;
    private final /* synthetic */ Setting<Integer> oBlue;
    public /* synthetic */ Setting<Boolean> box;
    public /* synthetic */ Setting<Boolean> limitFacePlace;
    public /* synthetic */ Setting<Integer> damageSyncTime;
    private /* synthetic */ BlockPos webPos;
    public /* synthetic */ Setting<Boolean> antiSurround;
    public /* synthetic */ Setting<Integer> YawStepVal;
    public /* synthetic */ Setting<Boolean> exactHand;
    private final /* synthetic */ Setting<Boolean> onlyplaced;
    public /* synthetic */ Setting<Boolean> holdFaceBreak;
    public /* synthetic */ Setting<Boolean> doublePopOnDamage;
    public /* synthetic */ Setting<Boolean> syncThreadBool;
    public /* synthetic */ Setting<Integer> syncThreads;
    public /* synthetic */ Setting<ACRenderMode> renderMode;
    public /* synthetic */ Setting<Float> minDamage;
    public final /* synthetic */ Setting<Boolean> packetswing;
    private /* synthetic */ int lastSlot;
    public /* synthetic */ Setting<Boolean> wasteMinDmgCount;
    private /* synthetic */ int crystalCount;
    private /* synthetic */ boolean posConfirmed;
    public /* synthetic */ Setting<Integer> manualBreak;
    public /* synthetic */ BlockPos placePos;
    private /* synthetic */ double lastDamage;
    private final /* synthetic */ Setting<Integer> eventMode;
    private /* synthetic */ boolean shouldSilent;
    private /* synthetic */ double currentDamage;
    public /* synthetic */ Setting<Float> soundPlayer;
    public /* synthetic */ Setting<Boolean> text;
    public /* synthetic */ Setting<Float> soundRange;
    public /* synthetic */ Setting<Boolean> enormousSync;
    public /* synthetic */ Setting<Boolean> antiNaked;

    private boolean shouldHoldFacePlace() {
        this.addTolowDmg = false;
        if (this.holdFacePlace.getValue().booleanValue() && Mouse.isButtonDown((int)0)) {
            this.addTolowDmg = true;
            return true;
        }
        return false;
    }

    private boolean isEligableForFeetSync(EntityPlayer entityPlayer, BlockPos blockPos) {
        if (this.holySync.getValue().booleanValue()) {
            BlockPos blockPos2 = new BlockPos(entityPlayer.getPositionVector());
            for (EnumFacing enumFacing : EnumFacing.values()) {
                if (enumFacing == EnumFacing.DOWN || enumFacing == EnumFacing.UP || !blockPos.equals((Object)blockPos2.down().offset(enumFacing))) continue;
                return true;
            }
            return false;
        }
        return true;
    }

    private void postProcessPlace() {
        if (this.placeInfo != null) {
            this.placeInfo.runPlace();
            this.placeTimer.reset();
            this.placeInfo = null;
        }
    }

    private void attackEntity(Entity entity) {
        if (entity != null) {
            if (this.eventMode.getValue() == 2 && this.threadMode.getValue() == ThreadMode.NONE && this.rotateFirst.getValue().booleanValue() && this.rotate.getValue() != ACRotate.OFF) {
                this.packetUseEntities.add(new CPacketUseEntity(entity));
            } else {
                EntityUtil.attackEntity(entity, this.sync.getValue(), this.swingMode.getValue() == ACSwing.Break || this.swingMode.getValue() == ACSwing.Both);
                EntityUtil.OffhandAttack(entity, this.attackOppositeHand.getValue(), this.attackOppositeHand.getValue());
                brokenPos.add(new BlockPos(entity.getPositionVector()).down());
            }
        }
    }

    private boolean isDoublePoppable(EntityPlayer entityPlayer, float f) {
        if (this.doublePop.getValue().booleanValue()) {
            float f2;
            float f3 = EntityUtil.getHealth((Entity)entityPlayer);
            if ((double)f2 <= this.popHealth.getValue() && (double)f > (double)f3 + 0.5 && f <= this.popDamage.getValue().floatValue()) {
                Timer timer = this.totemPops.get((Object)entityPlayer);
                return timer == null || timer.passedMs(this.popTime.getValue().intValue());
            }
        }
        return false;
    }

    @Override
    public void onToggle() {
        brokenPos.clear();
        placedPos.clear();
        this.totemPops.clear();
        this.rotating = false;
    }

    @SubscribeEvent
    public void onSettingChange(ClientEvent clientEvent) {
        if (clientEvent.getStage() == 2 && clientEvent.getSetting() != null && clientEvent.getSetting().getFeature() != null && clientEvent.getSetting().getFeature().equals(this) && this.isEnabled() && (clientEvent.getSetting().equals(this.threadDelay) || clientEvent.getSetting().equals(this.threadMode))) {
            if (this.executor != null) {
                this.executor.shutdown();
            }
            if (this.thread != null) {
                this.shouldInterrupt.set(true);
            }
        }
    }

    private void placeCrystal() {
        int n = this.wasteAmount.getValue();
        if (this.placeTimer.passedMs(this.placeDelay.getValue().intValue()) && (this.offHand || this.mainHand || this.switchMode.getValue() == Switch.CALC || this.switchMode.getValue() == Switch.BREAKSLOT && this.switching)) {
            if (!(!this.offHand && !this.mainHand && (this.switchMode.getValue() == Switch.ALWAYS || this.switching) || this.crystalCount < n || this.antiSurround.getValue().booleanValue() && this.lastPos != null && this.lastPos.equals((Object)this.placePos))) {
                return;
            }
            this.calculateDamage(this.getTarget(this.targetMode.getValue() == Target.UNSAFE));
            if (target != null && this.placePos != null) {
                if (!this.offHand && !this.mainHand && this.autoSwitch.getValue() != ACAutoSwitch.None && (this.currentDamage > (double)this.minDamage.getValue().floatValue() || this.lethalSwitch.getValue().booleanValue() && EntityUtil.getHealth((Entity)target) <= this.facePlace.getValue().floatValue()) && !this.switchItem()) {
                    return;
                }
                if (this.currentDamage < (double)this.minDamage.getValue().floatValue() && this.limitFacePlace.getValue().booleanValue()) {
                    n = 1;
                }
                if (this.currentDamage >= (double)this.minMinDmg.getValue().floatValue() && (this.offHand || this.mainHand || this.autoSwitch.getValue() != ACAutoSwitch.None) && (this.crystalCount < n || this.antiSurround.getValue().booleanValue() && this.lastPos != null && this.lastPos.equals((Object)this.placePos)) && (this.currentDamage > (double)this.minDamage.getValue().floatValue() || this.minDmgCount < n) && this.currentDamage >= 1.0 && (DamageUtil.isArmorLow(target, this.minArmor.getValue()) || EntityUtil.getHealth((Entity)target) <= this.facePlace.getValue().floatValue() || this.currentDamage > (double)this.minDamage.getValue().floatValue() || this.shouldHoldFacePlace())) {
                    float f = this.damageSync.getValue() == DamageSync.BREAK ? this.dropOff.getValue().floatValue() - 5.0f : 0.0f;
                    boolean bl = false;
                    if (this.syncedFeetPlace.getValue().booleanValue() && this.placePos.equals((Object)this.lastPos) && this.isEligableForFeetSync(target, this.placePos) && !this.syncTimer.passedMs(this.damageSyncTime.getValue().intValue()) && target.equals((Object)this.currentSyncTarget) && target.getPosition().equals((Object)this.syncedPlayerPos) && this.damageSync.getValue() != DamageSync.NONE) {
                        this.syncedCrystalPos = this.placePos;
                        this.lastDamage = this.currentDamage;
                        if (this.fullSync.getValue().booleanValue()) {
                            this.lastDamage = 100.0;
                        }
                        bl = true;
                    }
                    if (bl || this.currentDamage - (double)f > this.lastDamage || this.syncTimer.passedMs(this.damageSyncTime.getValue().intValue()) || this.damageSync.getValue() == DamageSync.NONE) {
                        if (!bl && this.damageSync.getValue() != DamageSync.BREAK) {
                            this.lastDamage = this.currentDamage;
                        }
                        if (!this.onlyplaced.getValue().booleanValue()) {
                            this.renderPos = this.placePos;
                        }
                        this.renderDamage = this.currentDamage;
                        if (this.switchItem()) {
                            this.currentSyncTarget = target;
                            this.syncedPlayerPos = target.getPosition();
                            if (this.foundDoublePop) {
                                this.totemPops.put(target, new Timer().reset());
                            }
                            this.rotateToPos(this.placePos);
                            if (this.addTolowDmg || this.actualSlowBreak.getValue().booleanValue() && this.currentDamage < (double)this.minDamage.getValue().floatValue()) {
                                lowDmgPos.add(this.placePos);
                            }
                            placedPos.add(this.placePos);
                            if (this.eventMode.getValue() == 2 && this.threadMode.getValue() == ThreadMode.NONE && this.rotateFirst.getValue().booleanValue() && this.rotate.getValue() != ACRotate.OFF) {
                                this.placeInfo = new PlaceInfo(this.placePos, this.offHand, this.swingMode.getValue() == ACSwing.Place || this.swingMode.getValue() == ACSwing.Both, this.exactHand.getValue(), this.shouldSilent);
                            } else {
                                BlockUtil.placeCrystalOnBlockNew(this.placePos, this.offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, this.swingMode.getValue() == ACSwing.Place || this.swingMode.getValue() == ACSwing.Both, this.exactHand.getValue(), this.packetswing.getValue(), this.shouldSilent);
                                AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.placePos, EnumFacing.UP, AutoCrystal.mc.player.getHeldItemOffhand().getItem().equals((Object)Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.5f, 1.0f, 0.5f));
                            }
                            this.lastPos = this.placePos;
                            this.placeTimer.reset();
                            this.posConfirmed = false;
                            if (this.syncTimer.passedMs(this.damageSyncTime.getValue().intValue())) {
                                this.syncedCrystalPos = null;
                                this.syncTimer.reset();
                            }
                        }
                    }
                }
            } else {
                this.renderPos = null;
            }
        }
    }

    static {
        lowDmgPos = new ConcurrentSet();
        placedPos = new HashSet<BlockPos>();
        brokenPos = new HashSet<BlockPos>();
        lowDmgPos = new ConcurrentSet();
        placedPos = new HashSet<BlockPos>();
        brokenPos = new HashSet<BlockPos>();
    }

    private void breakCrystal() {
        if (this.breakTimer.passedMs(this.breakDelay.getValue().intValue()) && (this.switchMode.getValue() == Switch.ALWAYS || this.mainHand || this.offHand)) {
            if (this.packets.getValue() == 1 && this.efficientTarget != null) {
                if (this.syncedFeetPlace.getValue().booleanValue() && this.gigaSync.getValue().booleanValue() && this.syncedCrystalPos != null && this.damageSync.getValue() != DamageSync.NONE) {
                    return;
                }
                this.rotateTo(this.efficientTarget);
                this.attackEntity(this.efficientTarget);
                this.breakTimer.reset();
            } else if (!this.attackList.isEmpty()) {
                if (this.syncedFeetPlace.getValue().booleanValue() && this.gigaSync.getValue().booleanValue() && this.syncedCrystalPos != null && this.damageSync.getValue() != DamageSync.NONE) {
                    return;
                }
                for (int i = 0; i < this.packets.getValue(); ++i) {
                    Entity entity = this.attackList.poll();
                    if (entity == null) continue;
                    this.rotateTo(entity);
                    this.attackEntity(entity);
                }
                this.breakTimer.reset();
            }
        }
    }

    private void manualBreaker() {
        RayTraceResult rayTraceResult;
        if (this.rotate.getValue() != ACRotate.OFF && this.eventMode.getValue() != 2 && this.rotating) {
            if (this.didRotation) {
                AutoCrystal.mc.player.rotationPitch = (float)((double)AutoCrystal.mc.player.rotationPitch + 4.0E-4);
                this.didRotation = false;
            } else {
                AutoCrystal.mc.player.rotationPitch = (float)((double)AutoCrystal.mc.player.rotationPitch - 4.0E-4);
                this.didRotation = true;
            }
        }
        if ((this.offHand || this.mainHand) && this.manual.getValue().booleanValue() && this.manualTimer.passedMs(this.manualBreak.getValue().intValue()) && Mouse.isButtonDown((int)1) && AutoCrystal.mc.player.getHeldItemOffhand().getItem() != Items.GOLDEN_APPLE && AutoCrystal.mc.player.inventory.getCurrentItem().getItem() != Items.GOLDEN_APPLE && AutoCrystal.mc.player.inventory.getCurrentItem().getItem() != Items.BOW && AutoCrystal.mc.player.inventory.getCurrentItem().getItem() != Items.EXPERIENCE_BOTTLE && (rayTraceResult = AutoCrystal.mc.objectMouseOver) != null) {
            switch (rayTraceResult.typeOfHit) {
                case ENTITY: {
                    Entity entity = rayTraceResult.entityHit;
                    if (!(entity instanceof EntityEnderCrystal)) break;
                    EntityUtil.attackEntity(entity, this.sync.getValue(), this.swingMode.getValue() == ACSwing.Break || this.swingMode.getValue() == ACSwing.Both);
                    EntityUtil.OffhandAttack(entity, this.attackOppositeHand.getValue(), this.attackOppositeHand.getValue());
                    this.manualTimer.reset();
                    break;
                }
                case BLOCK: {
                    BlockPos blockPos = AutoCrystal.mc.objectMouseOver.getBlockPos().up();
                    for (Entity entity : AutoCrystal.mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(blockPos))) {
                        if (!(entity instanceof EntityEnderCrystal)) continue;
                        EntityUtil.attackEntity(entity, this.sync.getValue(), this.swingMode.getValue() == ACSwing.Break || this.swingMode.getValue() == ACSwing.Both);
                        EntityUtil.OffhandAttack(entity, this.attackOppositeHand.getValue(), this.attackOppositeHand.getValue());
                        this.manualTimer.reset();
                    }
                    break;
                }
            }
        }
    }

    private ScheduledExecutorService getExecutor() {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(RAutoCrystal.getInstance(this), 0L, this.threadDelay.getValue().intValue(), TimeUnit.MILLISECONDS);
        return scheduledExecutorService;
    }

    @Override
    public void onEnable() {
        if (this.threadMode.getValue() != ThreadMode.NONE) {
            this.processMultiThreading();
        }
    }

    private void processMultiThreading() {
        if (this.isOff()) {
            return;
        }
        if (this.threadMode.getValue() == ThreadMode.WHILE) {
            this.handleWhile();
        } else if (this.threadMode.getValue() != ThreadMode.NONE) {
            this.handlePool(false);
        }
    }

    private boolean isRightThread() {
        return mc.isCallingFromMinecraftThread() || !AliceMain.eventManager.ticksOngoing() && !this.threadOngoing.get();
    }

    @Override
    public String getDisplayInfo() {
        if (this.switching) {
            return "\u00a7aSwitch";
        }
        if (target != null) {
            return target.getName();
        }
        return null;
    }

    private void postProcessBreak() {
        while (!this.packetUseEntities.isEmpty()) {
            CPacketUseEntity cPacketUseEntity = this.packetUseEntities.poll();
            AutoCrystal.mc.player.connection.sendPacket((Packet)cPacketUseEntity);
            if (this.swingMode.getValue() == ACSwing.Break || this.swingMode.getValue() == ACSwing.Both) {
                AutoCrystal.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
            this.breakTimer.reset();
        }
    }

    private boolean shouldSlowBreak(boolean bl) {
        return bl && this.manual.getValue() != false && this.manualMinDmg.getValue() != false && Mouse.isButtonDown((int)1) && (!Mouse.isButtonDown((int)0) || this.holdFacePlace.getValue() == false) || this.holdFacePlace.getValue() != false && this.holdFaceBreak.getValue() != false && Mouse.isButtonDown((int)0) && !this.breakTimer.passedMs(this.facePlaceSpeed.getValue().intValue()) || this.slowFaceBreak.getValue() != false && !this.breakTimer.passedMs(this.facePlaceSpeed.getValue().intValue());
    }

    public void doAutoCrystal() {
        if (this.check()) {
            switch (this.logic.getValue()) {
                case PLACEBREAK: {
                    this.placeCrystal();
                    this.breakCrystal();
                    break;
                }
                case BREAKPLACE: {
                    this.breakCrystal();
                    this.placeCrystal();
                }
            }
            this.manualBreaker();
        }
    }

    private boolean isValid(Entity entity) {
        return entity != null && AutoCrystal.mc.player.getDistanceSqToEntity(entity) <= MathUtil.square(this.breakRange.getValue().floatValue()) && (this.raytrace.getValue() == Raytrace.NONE || this.raytrace.getValue() == Raytrace.PLACE || AutoCrystal.mc.player.canEntityBeSeen(entity) || !AutoCrystal.mc.player.canEntityBeSeen(entity) && AutoCrystal.mc.player.getDistanceSqToEntity(entity) <= MathUtil.square(this.breaktrace.getValue().floatValue()));
    }

    private void handlePool(boolean bl) {
        if (bl || this.executor == null || this.executor.isTerminated() || this.executor.isShutdown() || this.syncroTimer.passedMs(this.syncThreads.getValue().intValue()) && this.syncThreadBool.getValue().booleanValue()) {
            if (this.executor != null) {
                this.executor.shutdown();
            }
            this.executor = this.getExecutor();
            this.syncroTimer.reset();
        }
    }

    public AutoCrystal() {
        super("AutoCrystal", "Best CA made by snow :D", Module.Category.COMBAT, true, false, false);
        this.switchTimer = new Timer();
        this.manualTimer = new Timer();
        this.breakTimer = new Timer();
        this.placeTimer = new Timer();
        this.syncTimer = new Timer();
        this.predictTimer = new Timer();
        this.renderTimer = new Timer();
        this.shouldInterrupt = new AtomicBoolean(false);
        this.syncroTimer = new Timer();
        this.totemPops = new ConcurrentHashMap<EntityPlayer, Timer>();
        this.packetUseEntities = new LinkedList<CPacketUseEntity>();
        this.threadOngoing = new AtomicBoolean(false);
        this.positions = new ArrayList<RenderPos>();
        this.yawStepTimer = new Timer();
        this.setting = this.register(new Setting<ACSettings>("Page", ACSettings.Place));
        this.placeDelay = this.register(new Setting<Object>("PlaceDelay", Integer.valueOf(18), Integer.valueOf(0), Integer.valueOf(500), object -> this.setting.getValue() == ACSettings.Place));
        this.placeRange = this.register(new Setting<Object>("PlaceRange", Float.valueOf(6.0f), Float.valueOf(0.0f), Float.valueOf(10.0f), object -> this.setting.getValue() == ACSettings.Place));
        this.placetrace = this.register(new Setting<Object>("PlaceTrace", Float.valueOf(3.5f), Float.valueOf(0.0f), Float.valueOf(10.0f), object -> this.setting.getValue() == ACSettings.Place && this.raytrace.getValue() != Raytrace.NONE && this.raytrace.getValue() != Raytrace.BREAK));
        this.minDamage = this.register(new Setting<Object>("MinDamage", Float.valueOf(2.0f), Float.valueOf(0.1f), Float.valueOf(20.0f), object -> this.setting.getValue() == ACSettings.Place));
        this.maxSelfPlace = this.register(new Setting<Object>("MaxSelfPlace", Float.valueOf(8.0f), Float.valueOf(0.1f), Float.valueOf(36.0f), object -> this.setting.getValue() == ACSettings.Place));
        this.wasteAmount = this.register(new Setting<Object>("WasteAmount", Integer.valueOf(5), Integer.valueOf(1), Integer.valueOf(5), object -> this.setting.getValue() == ACSettings.Place));
        this.wasteMinDmgCount = this.register(new Setting<Object>("CountMinDmg", Boolean.TRUE, object -> this.setting.getValue() == ACSettings.Place));
        this.facePlace = this.register(new Setting<Object>("Face Place Health", Float.valueOf(36.0f), Float.valueOf(0.1f), Float.valueOf(36.0f), object -> this.setting.getValue() == ACSettings.Place));
        this.antiSurround = this.register(new Setting<Object>("AntiSurround", Boolean.TRUE, object -> this.setting.getValue() == ACSettings.Place));
        this.limitFacePlace = this.register(new Setting<Object>("LimitFacePlace", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Place));
        this.oneDot15 = this.register(new Setting<Object>("1.13 Place", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Place));
        this.doublePop = this.register(new Setting<Object>("AntiTotem", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Place));
        this.popHealth = this.register(new Setting<Object>("PopHealth", Double.valueOf(1.0), Double.valueOf(0.0), Double.valueOf(3.0), object -> this.setting.getValue() == ACSettings.Place && this.doublePop.getValue() != false));
        this.popDamage = this.register(new Setting<Object>("PopDamage", Float.valueOf(4.0f), Float.valueOf(0.0f), Float.valueOf(6.0f), object -> this.setting.getValue() == ACSettings.Place && this.doublePop.getValue() != false));
        this.popTime = this.register(new Setting<Object>("PopTime", Integer.valueOf(500), Integer.valueOf(0), Integer.valueOf(1000), object -> this.setting.getValue() == ACSettings.Place && this.doublePop.getValue() != false));
        this.doublePopOnDamage = this.register(new Setting<Object>("DamagePop", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Place && this.doublePop.getValue() != false && this.targetMode.getValue() == Target.DAMAGE));
        this.switchMode = this.register(new Setting<Object>("Attack", (Object)Switch.BREAKSLOT, object -> this.setting.getValue() == ACSettings.Break));
        this.breakDelay = this.register(new Setting<Object>("BreakDelay", Integer.valueOf(18), Integer.valueOf(0), Integer.valueOf(500), object -> this.setting.getValue() == ACSettings.Break));
        this.breakRange = this.register(new Setting<Object>("BreakRange", Float.valueOf(6.0f), Float.valueOf(0.0f), Float.valueOf(10.0f), object -> this.setting.getValue() == ACSettings.Break));
        this.breaktrace = this.register(new Setting<Object>("BreakTrace", Float.valueOf(3.5f), Float.valueOf(0.0f), Float.valueOf(10.0f), object -> this.setting.getValue() == ACSettings.Break && this.raytrace.getValue() != Raytrace.NONE && this.raytrace.getValue() != Raytrace.PLACE));
        this.packets = this.register(new Setting<Object>("Packets", Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(6), object -> this.setting.getValue() == ACSettings.Break));
        this.maxSelfBreak = this.register(new Setting<Object>("MaxSelfBreakDmg", Float.valueOf(8.0f), Float.valueOf(0.1f), Float.valueOf(36.0f), object -> this.setting.getValue() == ACSettings.Break));
        this.manual = this.register(new Setting<Object>("ManualBreaker", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Break));
        this.manualMinDmg = this.register(new Setting<Object>("ManualMinDmg", Boolean.TRUE, object -> this.setting.getValue() == ACSettings.Break && this.manual.getValue() != false));
        this.manualBreak = this.register(new Setting<Object>("ManualDelay", Integer.valueOf(500), Integer.valueOf(0), Integer.valueOf(500), object -> this.setting.getValue() == ACSettings.Break && this.manual.getValue() != false));
        this.sync = this.register(new Setting<Object>("Sync", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Break && this.manual.getValue() != false));
        this.instant = this.register(new Setting<Object>("Predict", Boolean.TRUE, object -> this.setting.getValue() == ACSettings.Break));
        this.instantTimer = this.register(new Setting<Object>("PredictTimer", (Object)PredictTimer.NONE, object -> this.setting.getValue() == ACSettings.Break && this.instant.getValue() != false));
        this.resetBreakTimer = this.register(new Setting<Object>("ResetBreakTimer", Boolean.TRUE, object -> this.setting.getValue() == ACSettings.Break && this.instant.getValue() != false));
        this.predictDelay = this.register(new Setting<Object>("PredictDelay", Integer.valueOf(12), Integer.valueOf(0), Integer.valueOf(500), object -> this.setting.getValue() == ACSettings.Break && this.instant.getValue() != false && this.instantTimer.getValue() == PredictTimer.PREDICT));
        this.predictCalc = this.register(new Setting<Object>("PredictCalc", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Break && this.instant.getValue() != false));
        this.predictPos = this.register(new Setting<Object>("PredictPos", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Break));
        this.predictTicks = this.register(new Setting<Object>("ExtrapolationTicks", Integer.valueOf(2), Integer.valueOf(1), Integer.valueOf(20), object -> this.setting.getValue() == ACSettings.Break && this.predictPos.getValue() != false));
        this.predictRotate = this.register(new Setting<Object>("PredictRotate", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Break));
        this.predictOffset = this.register(new Setting<Object>("PredictOffset", Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(4.0f), object -> this.setting.getValue() == ACSettings.Break));
        this.superSafe = this.register(new Setting<Object>("SuperSafe", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Break && this.instant.getValue() != false));
        this.antiCommit = this.register(new Setting<Object>("AntiOverCommit", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Break && this.instant.getValue() != false));
        this.antiWeakness = this.register(new Setting<Object>("AntiWeakness", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Break));
        this.renderMode = this.register(new Setting<ACRenderMode>("RenderMode", ACRenderMode.STATIC, aCRenderMode -> this.setting.getValue() == ACSettings.Render));
        this.rainbowBrightnessAA22 = this.register(new Setting<Object>("Brightness ", Float.valueOf(150.0f), Float.valueOf(1.0f), Float.valueOf(255.0f), object -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.NEWRAINBOW));
        this.rainbowSaturationAA22 = this.register(new Setting<Object>("Saturation", Float.valueOf(150.0f), Float.valueOf(1.0f), Float.valueOf(255.0f), object -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.NEWRAINBOW));
        this.fadeFactor = this.register(new Setting<Boolean>("Fade", Boolean.valueOf(true), bl -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.FADE));
        this.scaleFactor = this.register(new Setting<Boolean>("Shrink", Boolean.valueOf(false), bl -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.FADE));
        this.slabFactor = this.register(new Setting<Boolean>("Slab", Boolean.valueOf(false), bl -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.FADE));
        this.onlyplaced = this.register(new Setting<Boolean>("OnlyPlaced", Boolean.valueOf(false), bl -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.FADE));
        this.duration = this.register(new Setting<Float>("Duration", Float.valueOf(1500.0f), Float.valueOf(0.0f), Float.valueOf(5000.0f), f -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.FADE));
        this.max = this.register(new Setting<Integer>("MaxPositions", Integer.valueOf(15), Integer.valueOf(1), Integer.valueOf(30), n -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.FADE));
        this.slabHeight = this.register(new Setting<Float>("SlabDepth", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(1.0f), f -> this.setting.getValue() == ACSettings.Render && (this.renderMode.getValue() == ACRenderMode.STATIC || this.renderMode.getValue() == ACRenderMode.GLIDE)));
        this.moveSpeed = this.register(new Setting<Float>("Speed", Float.valueOf(900.0f), Float.valueOf(0.0f), Float.valueOf(1500.0f), f -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.GLIDE));
        this.accel = this.register(new Setting<Float>("Deceleration", Float.valueOf(0.8f), Float.valueOf(0.0f), Float.valueOf(1.0f), f -> this.setting.getValue() == ACSettings.Render && this.renderMode.getValue() == ACRenderMode.GLIDE));
        this.colorSync = this.register(new Setting<Object>("ColorSync", Boolean.valueOf(true), object -> this.setting.getValue() == ACSettings.Render));
        this.box = this.register(new Setting<Object>("Box", Boolean.valueOf(true), object -> this.setting.getValue() == ACSettings.Render));
        this.bRed = this.register(new Setting<Object>("BoxRed", Integer.valueOf(64), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == ACSettings.Render && this.box.getValue() != false));
        this.bGreen = this.register(new Setting<Object>("BoxGreen", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == ACSettings.Render && this.box.getValue() != false));
        this.bBlue = this.register(new Setting<Object>("BoxBlue", Integer.valueOf(145), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == ACSettings.Render && this.box.getValue() != false));
        this.bAlpha = this.register(new Setting<Object>("BoxAlpha", Integer.valueOf(105), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == ACSettings.Render && this.box.getValue() != false));
        this.outline = this.register(new Setting<Object>("Outline", Boolean.valueOf(true), object -> this.setting.getValue() == ACSettings.Render));
        this.oRed = this.register(new Setting<Object>("OutlineRed", Integer.valueOf(58), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == ACSettings.Render && this.outline.getValue() != false));
        this.oGreen = this.register(new Setting<Object>("OutlineGreen", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == ACSettings.Render && this.outline.getValue() != false));
        this.oBlue = this.register(new Setting<Object>("OutlineBlue", Integer.valueOf(145), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == ACSettings.Render && this.outline.getValue() != false));
        this.oAlpha = this.register(new Setting<Object>("OutlineAlpha", Integer.valueOf(111), Integer.valueOf(0), Integer.valueOf(255), object -> this.setting.getValue() == ACSettings.Render && this.outline.getValue() != false));
        this.lineWidth = this.register(new Setting<Object>("LineWidth", Float.valueOf(1.8f), Float.valueOf(0.1f), Float.valueOf(5.0f), object -> this.setting.getValue() == ACSettings.Render && this.outline.getValue() != false));
        this.text = this.register(new Setting<Object>("RenderDmg", Boolean.valueOf(false), object -> this.setting.getValue() == ACSettings.Render));
        this.holdFacePlace = this.register(new Setting<Object>("HoldFacePlace", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Misc));
        this.holdFaceBreak = this.register(new Setting<Object>("HoldSlowBreak", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Misc && this.holdFacePlace.getValue() != false));
        this.slowFaceBreak = this.register(new Setting<Object>("SlowFaceBreak", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Misc));
        this.actualSlowBreak = this.register(new Setting<Object>("ActuallySlow", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Misc));
        this.facePlaceSpeed = this.register(new Setting<Object>("FaceSpeed", Integer.valueOf(500), Integer.valueOf(0), Integer.valueOf(500), object -> this.setting.getValue() == ACSettings.Misc));
        this.antiNaked = this.register(new Setting<Object>("AntiNaked", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Misc));
        this.range = this.register(new Setting<Object>("TargetRange", Float.valueOf(8.0f), Float.valueOf(0.1f), Float.valueOf(20.0f), object -> this.setting.getValue() == ACSettings.Misc));
        this.targetMode = this.register(new Setting<Object>("Target", (Object)Target.DAMAGE, object -> this.setting.getValue() == ACSettings.Misc));
        this.minArmor = this.register(new Setting<Object>("Armour%", Integer.valueOf(4), Integer.valueOf(0), Integer.valueOf(125), object -> this.setting.getValue() == ACSettings.Misc));
        this.autoSwitch = this.register(new Setting<Object>("SwapMode", (Object)ACAutoSwitch.Spoof, object -> this.setting.getValue() == ACSettings.Misc));
        this.swapdelay = this.register(new Setting<Object>("SwapDelay", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(1000), object -> this.setting.getValue() == ACSettings.Misc));
        this.switchBind = this.register(new Setting<Object>("SwitchBind", new Bind(-1), object -> this.setting.getValue() == ACSettings.Misc && this.autoSwitch.getValue() == ACAutoSwitch.Toggle));
        this.lethalSwitch = this.register(new Setting<Object>("LethalSwitch", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Misc && this.autoSwitch.getValue() != ACAutoSwitch.None && this.autoSwitch.getValue() != ACAutoSwitch.Spoof));
        this.mineSwitch = this.register(new Setting<Object>("MineSwitch", Boolean.TRUE, object -> this.setting.getValue() == ACSettings.Misc && this.autoSwitch.getValue() != ACAutoSwitch.None && this.autoSwitch.getValue() != ACAutoSwitch.Spoof));
        this.rotate = this.register(new Setting<Object>("Rotate", (Object)ACRotate.OFF, object -> this.setting.getValue() == ACSettings.Misc));
        this.rotations = this.register(new Setting<Object>("RotationSpoofs", Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(20), object -> this.setting.getValue() == ACSettings.Misc));
        this.yawstepmode = this.register(new Setting<Object>("YawStep", (Object)ACYaw.Full, object -> this.setting.getValue() == ACSettings.Misc && this.rotate.getValue() != ACRotate.OFF));
        this.YawStepVal = this.register(new Setting<Object>("YawStepThreshold", Integer.valueOf(45), Integer.valueOf(0), Integer.valueOf(180), object -> this.setting.getValue() == ACSettings.Misc && this.rotate.getValue() != ACRotate.OFF && this.yawstepmode.getValue() == ACYaw.Semi));
        this.YawStepTicks = this.register(new Setting<Integer>("YawStepTicks", Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(20), n -> this.setting.getValue() == ACSettings.Misc && this.rotate.getValue() != ACRotate.OFF && this.yawstepmode.getValue() == ACYaw.Semi));
        this.suicide = this.register(new Setting<Object>("IgnoreSelfDamage", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Misc));
        this.webAttack = this.register(new Setting<Object>("WebAttack", Boolean.TRUE, object -> this.setting.getValue() == ACSettings.Misc && this.targetMode.getValue() != Target.DAMAGE));
        this.fullCalc = this.register(new Setting<Object>("ExtraCalc", Boolean.TRUE, object -> this.setting.getValue() == ACSettings.Misc));
        this.logic = this.register(new Setting<Object>("Logic", (Object)Logic.PLACEBREAK, object -> this.setting.getValue() == ACSettings.Development));
        this.raytrace = this.register(new Setting<Object>("Raytrace", (Object)Raytrace.NONE, object -> this.setting.getValue() == ACSettings.Misc));
        this.sound = this.register(new Setting<Object>("Sound", Boolean.TRUE, object -> this.setting.getValue() == ACSettings.Misc));
        this.soundRange = this.register(new Setting<Object>("SoundRange", Float.valueOf(12.0f), Float.valueOf(0.0f), Float.valueOf(12.0f), object -> this.setting.getValue() == ACSettings.Misc));
        this.soundPlayer = this.register(new Setting<Object>("SoundPlayer", Float.valueOf(12.0f), Float.valueOf(0.0f), Float.valueOf(12.0f), object -> this.setting.getValue() == ACSettings.Misc));
        this.soundConfirm = this.register(new Setting<Object>("SoundConfirm", Boolean.TRUE, object -> this.setting.getValue() == ACSettings.Misc));
        this.extraSelfCalc = this.register(new Setting<Object>("MinSelfDmg", Boolean.TRUE, object -> this.setting.getValue() == ACSettings.Misc));
        this.antiFriendPop = this.register(new Setting<Object>("FriendPop", (Object)AntiFriendPop.NONE, object -> this.setting.getValue() == ACSettings.Misc));
        this.noCount = this.register(new Setting<Object>("AntiCount", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Misc && (this.antiFriendPop.getValue() == AntiFriendPop.ALL || this.antiFriendPop.getValue() == AntiFriendPop.BREAK)));
        this.calcEvenIfNoDamage = this.register(new Setting<Object>("BigFriendCalc", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Misc && (this.antiFriendPop.getValue() == AntiFriendPop.ALL || this.antiFriendPop.getValue() == AntiFriendPop.BREAK) && this.targetMode.getValue() != Target.DAMAGE));
        this.predictFriendDmg = this.register(new Setting<Object>("PredictFriend", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Misc && (this.antiFriendPop.getValue() == AntiFriendPop.ALL || this.antiFriendPop.getValue() == AntiFriendPop.BREAK) && this.instant.getValue() != false));
        this.eventMode = this.register(new Setting<Object>("EventUpdates", Integer.valueOf(3), Integer.valueOf(1), Integer.valueOf(3), object -> this.setting.getValue() == ACSettings.Development));
        this.attackOppositeHand = this.register(new Setting<Object>("OppositeHand", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Development));
        this.removeAfterAttack = this.register(new Setting<Object>("FastMode", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Development));
        this.antiBlock = this.register(new Setting<Object>("AntiFeetPlace", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Development));
        this.minMinDmg = this.register(new Setting<Object>("MinMinDmg", Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(3.0f), object -> this.setting.getValue() == ACSettings.Development));
        this.swingMode = this.register(new Setting<Object>("SwingHand", (Object)ACSwing.Both, object -> this.setting.getValue() == ACSettings.Development));
        this.exactHand = this.register(new Setting<Object>("ExactHand", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Development && (this.swingMode.getValue() == ACSwing.Place || this.swingMode.getValue() == ACSwing.Both)));
        this.packetswing = this.register(new Setting<Object>("PacketSwing", Boolean.TRUE, object -> this.setting.getValue() == ACSettings.Development));
        this.damageSync = this.register(new Setting<Object>("DamageSync", (Object)DamageSync.NONE, object -> this.setting.getValue() == ACSettings.Development));
        this.damageSyncTime = this.register(new Setting<Object>("SyncDelay", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(500), object -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE));
        this.dropOff = this.register(new Setting<Object>("DropOff", Float.valueOf(5.0f), Float.valueOf(0.0f), Float.valueOf(10.0f), object -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() == DamageSync.BREAK));
        this.confirm = this.register(new Setting<Object>("Confirm", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(1000), object -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE));
        this.syncedFeetPlace = this.register(new Setting<Object>("FeetSync", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE));
        this.fullSync = this.register(new Setting<Object>("FullSync", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE && this.syncedFeetPlace.getValue() != false));
        this.syncCount = this.register(new Setting<Object>("SyncCount", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE && this.syncedFeetPlace.getValue() != false));
        this.hyperSync = this.register(new Setting<Object>("HyperSync", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE && this.syncedFeetPlace.getValue() != false));
        this.gigaSync = this.register(new Setting<Object>("GigaSync", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE && this.syncedFeetPlace.getValue() != false));
        this.syncySync = this.register(new Setting<Object>("SyncySync", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE && this.syncedFeetPlace.getValue() != false));
        this.enormousSync = this.register(new Setting<Object>("EnormousSync", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE && this.syncedFeetPlace.getValue() != false));
        this.holySync = this.register(new Setting<Object>("UnbelievableSync", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Development && this.damageSync.getValue() != DamageSync.NONE && this.syncedFeetPlace.getValue() != false));
        this.rotateFirst = this.register(new Setting<Object>("FirstRotation", Boolean.FALSE, object -> this.setting.getValue() == ACSettings.Development && this.rotate.getValue() != ACRotate.OFF && this.eventMode.getValue() == 2));
        this.threadMode = this.register(new Setting<Object>("Thread", (Object)ThreadMode.NONE, object -> this.setting.getValue() == ACSettings.Development));
        this.threadDelay = this.register(new Setting<Object>("ThreadDelay", Integer.valueOf(364), Integer.valueOf(1), Integer.valueOf(1000), object -> this.setting.getValue() == ACSettings.Development && this.threadMode.getValue() != ThreadMode.NONE));
        this.syncThreadBool = this.register(new Setting<Object>("ThreadSync", Boolean.TRUE, object -> this.setting.getValue() == ACSettings.Development && this.threadMode.getValue() != ThreadMode.NONE));
        this.syncThreads = this.register(new Setting<Object>("SyncThreads", Integer.valueOf(1000), Integer.valueOf(1), Integer.valueOf(10000), object -> this.setting.getValue() == ACSettings.Development && this.threadMode.getValue() != ThreadMode.NONE && this.syncThreadBool.getValue() != false));
        this.attackList = new ConcurrentLinkedQueue<Entity>();
        this.crystalMap = new HashMap<Entity, Float>();
        this.lastSlot = -1;
        instance = this;
    }

    private void calculateDamage(EntityPlayer entityPlayer) {
        BlockPos blockPos;
        if (entityPlayer == null && this.targetMode.getValue() != Target.DAMAGE && !this.fullCalc.getValue().booleanValue()) {
            return;
        }
        float f = 0.5f;
        EntityPlayer entityPlayer2 = null;
        BlockPos blockPos2 = null;
        float f2 = 0.0f;
        this.foundDoublePop = false;
        BlockPos blockPos3 = null;
        IBlockState iBlockState = null;
        if (this.webAttack.getValue().booleanValue() && entityPlayer != null && AutoCrystal.mc.world.getBlockState(blockPos = new BlockPos(entityPlayer.getPositionVector())).getBlock() == Blocks.WEB) {
            blockPos3 = blockPos;
            iBlockState = AutoCrystal.mc.world.getBlockState(blockPos);
            AutoCrystal.mc.world.setBlockToAir(blockPos);
        }
        block0: for (BlockPos blockPos4 : BlockUtil.possiblePlacePositions(this.placeRange.getValue().floatValue(), this.antiSurround.getValue(), this.oneDot15.getValue())) {
            float f3;
            if (!BlockUtil.rayTracePlaceCheck(blockPos4, (this.raytrace.getValue() == Raytrace.PLACE || this.raytrace.getValue() == Raytrace.FULL) && AutoCrystal.mc.player.getDistanceSq(blockPos4) > MathUtil.square(this.placetrace.getValue().floatValue()), 1.0f)) continue;
            float f4 = -1.0f;
            if (DamageUtil.canTakeDamage(this.suicide.getValue())) {
                f4 = DamageUtil.calculateDamage(blockPos4, (Entity)AutoCrystal.mc.player);
            }
            if (!((double)f4 + 0.5 < (double)EntityUtil.getHealth((Entity)AutoCrystal.mc.player)) || !(f4 <= this.maxSelfPlace.getValue().floatValue())) continue;
            if (entityPlayer != null) {
                f3 = DamageUtil.calculateDamage(blockPos4, (Entity)entityPlayer);
                if (this.calcEvenIfNoDamage.getValue().booleanValue() && (this.antiFriendPop.getValue() == AntiFriendPop.ALL || this.antiFriendPop.getValue() == AntiFriendPop.PLACE)) {
                    boolean bl = false;
                    for (EntityPlayer entityPlayer3 : AutoCrystal.mc.world.playerEntities) {
                        if (entityPlayer3 == null || AutoCrystal.mc.player.equals((Object)entityPlayer3) || entityPlayer3.getDistanceSq(blockPos4) > MathUtil.square(this.range.getValue().floatValue() + this.placeRange.getValue().floatValue()) || !AliceMain.friendManager.isFriend(entityPlayer3) || !((double)DamageUtil.calculateDamage(blockPos4, (Entity)entityPlayer3) > (double)EntityUtil.getHealth((Entity)entityPlayer3) + 0.5)) continue;
                        bl = true;
                        break;
                    }
                    if (bl) continue;
                }
                if (this.isDoublePoppable(entityPlayer, f3) && (blockPos2 == null || entityPlayer.getDistanceSq(blockPos4) < entityPlayer.getDistanceSq(blockPos2))) {
                    entityPlayer2 = entityPlayer;
                    f = f3;
                    blockPos2 = blockPos4;
                    this.foundDoublePop = true;
                    continue;
                }
                if (!(!this.foundDoublePop && (f3 > f || this.extraSelfCalc.getValue() != false && f3 >= f && f4 < f2) && (f3 > f4 || f3 > this.minDamage.getValue().floatValue() && !DamageUtil.canTakeDamage(this.suicide.getValue()) || f3 > EntityUtil.getHealth((Entity)entityPlayer)))) continue;
                f = f3;
                entityPlayer2 = entityPlayer;
                blockPos2 = blockPos4;
                f2 = f4;
                continue;
            }
            f3 = f;
            EntityPlayer entityPlayer4 = entityPlayer2;
            BlockPos blockPos5 = blockPos2;
            float f5 = f2;
            for (EntityPlayer entityPlayer5 : AutoCrystal.mc.world.playerEntities) {
                if (EntityUtil.isValid((Entity)entityPlayer5, this.placeRange.getValue().floatValue() + this.range.getValue().floatValue())) {
                    if (this.antiNaked.getValue().booleanValue() && DamageUtil.isNaked(entityPlayer5)) continue;
                    float f6 = DamageUtil.calculateDamage(blockPos4, (Entity)entityPlayer5);
                    if (this.doublePopOnDamage.getValue().booleanValue() && this.isDoublePoppable(entityPlayer5, f6) && (blockPos2 == null || entityPlayer5.getDistanceSq(blockPos4) < entityPlayer5.getDistanceSq(blockPos2))) {
                        entityPlayer2 = entityPlayer5;
                        f = f6;
                        blockPos2 = blockPos4;
                        f2 = f4;
                        this.foundDoublePop = true;
                        if (this.antiFriendPop.getValue() != AntiFriendPop.BREAK && this.antiFriendPop.getValue() != AntiFriendPop.PLACE) continue;
                        continue block0;
                    }
                    if (!(!this.foundDoublePop && (f6 > f || this.extraSelfCalc.getValue() != false && f6 >= f && f4 < f2) && (f6 > f4 || f6 > this.minDamage.getValue().floatValue() && !DamageUtil.canTakeDamage(this.suicide.getValue()) || f6 > EntityUtil.getHealth((Entity)entityPlayer5)))) continue;
                    f = f6;
                    entityPlayer2 = entityPlayer5;
                    blockPos2 = blockPos4;
                    f2 = f4;
                    continue;
                }
                if (this.antiFriendPop.getValue() != AntiFriendPop.ALL && this.antiFriendPop.getValue() != AntiFriendPop.PLACE || entityPlayer5 == null || !(entityPlayer5.getDistanceSq(blockPos4) <= MathUtil.square(this.range.getValue().floatValue() + this.placeRange.getValue().floatValue())) || !AliceMain.friendManager.isFriend(entityPlayer5) || !((double)DamageUtil.calculateDamage(blockPos4, (Entity)entityPlayer5) > (double)EntityUtil.getHealth((Entity)entityPlayer5) + 0.5)) continue;
                f = f3;
                entityPlayer2 = entityPlayer4;
                blockPos2 = blockPos5;
                f2 = f5;
                continue block0;
            }
        }
        if (blockPos3 != null) {
            AutoCrystal.mc.world.setBlockState(blockPos3, iBlockState);
            this.webPos = blockPos2;
        }
        target = entityPlayer2;
        this.currentDamage = f;
        this.placePos = blockPos2;
    }

    private boolean predictSlowBreak(BlockPos blockPos) {
        if (this.antiCommit.getValue().booleanValue() && lowDmgPos.remove((Object)blockPos)) {
            return this.shouldSlowBreak(false);
        }
        return false;
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        CPacketUseEntity cPacketUseEntity;
        CPacketPlayer cPacketPlayer;
        if (send.getStage() == 0 && this.rotate.getValue() != ACRotate.OFF && this.rotating && this.eventMode.getValue() != 2 && send.getPacket() instanceof CPacketPlayer) {
            cPacketPlayer = (CPacketPlayer)send.getPacket();
            cPacketPlayer.yaw = this.yaw;
            cPacketPlayer.pitch = this.pitch;
            ++this.rotationPacketsSpoofed;
            if (this.rotationPacketsSpoofed >= this.rotations.getValue()) {
                this.rotating = false;
                this.rotationPacketsSpoofed = 0;
            }
        }
        cPacketPlayer = null;
        if (send.getStage() == 0 && send.getPacket() instanceof CPacketUseEntity && (cPacketUseEntity = (CPacketUseEntity)send.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK && cPacketUseEntity.getEntityFromWorld((World)AutoCrystal.mc.world) instanceof EntityEnderCrystal) {
            cPacketPlayer = Objects.requireNonNull(cPacketUseEntity.getEntityFromWorld((World)AutoCrystal.mc.world)).getPosition();
            if (this.removeAfterAttack.getValue().booleanValue()) {
                Objects.requireNonNull(cPacketUseEntity.getEntityFromWorld((World)AutoCrystal.mc.world)).setDead();
                AutoCrystal.mc.world.removeEntityFromWorld(cPacketUseEntity.entityId);
            }
        }
        if (send.getStage() == 0 && send.getPacket() instanceof CPacketUseEntity && (cPacketUseEntity = (CPacketUseEntity)send.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK && cPacketUseEntity.getEntityFromWorld((World)AutoCrystal.mc.world) instanceof EntityEnderCrystal) {
            EntityEnderCrystal entityEnderCrystal = (EntityEnderCrystal)cPacketUseEntity.getEntityFromWorld((World)AutoCrystal.mc.world);
            if (this.antiBlock.getValue().booleanValue() && EntityUtil.isCrystalAtFeet(entityEnderCrystal, this.range.getValue().floatValue()) && cPacketPlayer != null) {
                this.rotateToPos((BlockPos)cPacketPlayer);
                BlockUtil.placeCrystalOnBlock(this.placePos, this.offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, this.swingMode.getValue() == ACSwing.Place || this.swingMode.getValue() == ACSwing.Both, this.exactHand.getValue(), this.shouldSilent);
            }
        }
    }

    private void removePos(BlockPos blockPos) {
        if (this.damageSync.getValue() == DamageSync.PLACE) {
            if (placedPos.remove((Object)blockPos)) {
                this.posConfirmed = true;
            }
        } else if (this.damageSync.getValue() == DamageSync.BREAK && brokenPos.remove((Object)blockPos)) {
            this.posConfirmed = true;
        }
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 1) {
            this.postProcessing();
        }
        if (updateWalkingPlayerEvent.getStage() != 0) {
            return;
        }
        if (this.eventMode.getValue() == 2) {
            this.doAutoCrystal();
        }
    }

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        Color color = new Color(this.bRed.getValue(), this.bGreen.getValue(), this.bBlue.getValue(), this.bAlpha.getValue());
        Color color2 = new Color(this.oRed.getValue(), this.oGreen.getValue(), this.oBlue.getValue(), this.oAlpha.getValue());
        if ((this.offHand || this.mainHand || this.switchMode.getValue() == Switch.CALC) && this.renderPos != null && (this.box.getValue().booleanValue() || this.outline.getValue().booleanValue())) {
            if (this.renderMode.getValue() == ACRenderMode.FADE) {
                this.positions.removeIf(renderPos -> renderPos.getPos().equals((Object)this.renderPos));
                this.positions.add(new RenderPos(this.renderPos, 0.0f));
            }
            if (this.renderMode.getValue() == ACRenderMode.STATIC) {
                RenderUtil.drawSexyBoxPhobosIsRetardedFuckYouESP(new AxisAlignedBB(this.renderPos), color, color2, this.lineWidth.getValue().floatValue(), this.outline.getValue(), this.box.getValue(), this.colorSync.getValue(), 1.0f, 1.0f, this.slabHeight.getValue().floatValue());
            }
            if (this.renderMode.getValue() == ACRenderMode.NEWRAINBOW) {
                AxisAlignedBB axisAlignedBB = AutoCrystal.mc.world.getBlockState(this.renderPos).getSelectedBoundingBox((World)AutoCrystal.mc.world, this.renderPos);
                Vec3d vec3d = EntityUtil.interpolateEntity((Entity)RenderUtil.mc.player, mc.getRenderPartialTicks());
                for (EnumFacing enumFacing : EnumFacing.values()) {
                    RenderUtil.drawGradientPlaneBB(axisAlignedBB.expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord), enumFacing, new Color(ColorUtil2.rainbowTEST22(50).getRed(), ColorUtil2.rainbowTEST22(50).getGreen(), ColorUtil2.rainbowTEST22(50).getBlue(), 127), ColorUtil2.invert(new Color(ColorUtil2.rainbowTEST22(50).getRed(), ColorUtil2.rainbowTEST22(50).getGreen(), ColorUtil2.rainbowTEST22(50).getBlue(), 127)), 2.0);
                }
                RenderUtil.drawGradientBlockOutline(axisAlignedBB.expandXyz((double)0.002f).offset(-vec3d.xCoord, -vec3d.yCoord, -vec3d.zCoord), ColorUtil2.invert(new Color(ColorUtil2.rainbowTEST22(50).getRed(), ColorUtil2.rainbowTEST22(50).getGreen(), ColorUtil2.rainbowTEST22(50).getBlue(), 255)), new Color(ColorUtil2.rainbowTEST22(50).getRed(), ColorUtil2.rainbowTEST22(50).getGreen(), ColorUtil2.rainbowTEST22(50).getBlue(), 255), 2.0f);
            }
            if (this.renderMode.getValue() == ACRenderMode.GLIDE) {
                if (this.lastRenderPos == null || AutoCrystal.mc.player.getDistance(this.renderBB.minX, this.renderBB.minY, this.renderBB.minZ) > (double)this.range.getValue().floatValue()) {
                    this.lastRenderPos = this.renderPos;
                    this.renderBB = new AxisAlignedBB(this.renderPos);
                    this.timePassed = 0.0f;
                }
                if (!this.lastRenderPos.equals((Object)this.renderPos)) {
                    this.lastRenderPos = this.renderPos;
                    this.timePassed = 0.0f;
                }
                double d = (double)this.renderPos.getX() - this.renderBB.minX;
                double d2 = (double)this.renderPos.getY() - this.renderBB.minY;
                double d3 = (double)this.renderPos.getZ() - this.renderBB.minZ;
                float f = this.timePassed / this.moveSpeed.getValue().floatValue() * this.accel.getValue().floatValue();
                if (f > 1.0f) {
                    f = 1.0f;
                }
                this.renderBB = this.renderBB.offset(d * (double)f, d2 * (double)f, d3 * (double)f);
                RenderUtil.drawSexyBoxPhobosIsRetardedFuckYouESP(this.renderBB, color, color2, this.lineWidth.getValue().floatValue(), this.outline.getValue(), this.box.getValue(), this.colorSync.getValue(), 1.0f, 1.0f, this.slabHeight.getValue().floatValue());
                if (this.text.getValue().booleanValue()) {
                    RenderUtil.drawTextWhite(this.renderBB.offset(0.0, (double)(1.0f - this.slabHeight.getValue().floatValue() / 2.0f) - 0.4, 0.0), String.valueOf(new StringBuilder().append(Math.floor(this.renderDamage) == this.renderDamage ? Integer.valueOf((int)this.renderDamage) : String.format("%.1f", this.renderDamage)).append("")));
                }
                this.timePassed = this.renderBB.equals((Object)new AxisAlignedBB(this.renderPos)) ? 0.0f : (this.timePassed += 50.0f);
            }
        }
        if (this.renderMode.getValue() == ACRenderMode.FADE) {
            this.positions.forEach(renderPos -> {
                float f = (this.duration.getValue().floatValue() - renderPos.getRenderTime()) / this.duration.getValue().floatValue();
                RenderUtil.drawSexyBoxPhobosIsRetardedFuckYouESP(new AxisAlignedBB(renderPos.getPos()), color, color2, this.lineWidth.getValue().floatValue(), this.outline.getValue(), this.box.getValue(), this.colorSync.getValue(), this.fadeFactor.getValue() != false ? f : 1.0f, this.scaleFactor.getValue() != false ? f : 1.0f, this.slabFactor.getValue() != false ? f : 1.0f);
                renderPos.setRenderTime(renderPos.getRenderTime() + 50.0f);
            });
            this.positions.removeIf(renderPos -> renderPos.getRenderTime() >= this.duration.getValue().floatValue() || AutoCrystal.mc.world.isAirBlock(renderPos.getPos()) || !AutoCrystal.mc.world.isAirBlock(renderPos.getPos().offset(EnumFacing.UP)));
            if (this.positions.size() > this.max.getValue()) {
                this.positions.remove(0);
            }
        }
        if ((this.offHand || this.mainHand || this.switchMode.getValue() == Switch.CALC) && this.renderPos != null && this.text.getValue().booleanValue() && this.renderMode.getValue() != ACRenderMode.GLIDE) {
            RenderUtil.drawTextWhite(new AxisAlignedBB(this.renderPos).offset(0.0, this.renderMode.getValue() != ACRenderMode.FADE ? (double)(1.0f - this.slabHeight.getValue().floatValue() / 2.0f) - 0.4 : 0.1, 0.0), String.valueOf(new StringBuilder().append(Math.floor(this.renderDamage) == this.renderDamage ? Integer.valueOf((int)this.renderDamage) : String.format("%.1f", this.renderDamage)).append("")));
        }
    }

    private EntityPlayer getTarget(boolean bl) {
        if (this.targetMode.getValue() == Target.DAMAGE) {
            return null;
        }
        EntityPlayer entityPlayer = null;
        for (EntityPlayer entityPlayer2 : AutoCrystal.mc.world.playerEntities) {
            if (EntityUtil.isntValid((Entity)entityPlayer2, this.placeRange.getValue().floatValue() + this.range.getValue().floatValue()) || this.antiNaked.getValue().booleanValue() && DamageUtil.isNaked(entityPlayer2) || bl && EntityUtil.isSafe((Entity)entityPlayer2)) continue;
            if (this.minArmor.getValue() > 0 && DamageUtil.isArmorLow(entityPlayer2, this.minArmor.getValue())) {
                entityPlayer = entityPlayer2;
                break;
            }
            if (entityPlayer == null) {
                entityPlayer = entityPlayer2;
                continue;
            }
            if (!(AutoCrystal.mc.player.getDistanceSqToEntity((Entity)entityPlayer2) < AutoCrystal.mc.player.getDistanceSqToEntity((Entity)entityPlayer))) continue;
            entityPlayer = entityPlayer2;
        }
        if (bl && entityPlayer == null) {
            return this.getTarget(false);
        }
        if (this.predictPos.getValue().booleanValue() && entityPlayer != null) {
            EntityPlayer entityPlayer2;
            entityPlayer.getUniqueID();
            GameProfile gameProfile = new GameProfile(entityPlayer.getUniqueID(), entityPlayer.getName());
            entityPlayer2 = new EntityOtherPlayerMP((World)AutoCrystal.mc.world, gameProfile);
            Vec3d vec3d = MathUtil.extrapolatePlayerPosition(entityPlayer, this.predictTicks.getValue());
            entityPlayer2.copyLocationAndAnglesFrom((Entity)entityPlayer);
            entityPlayer2.posX = vec3d.xCoord;
            entityPlayer2.posY = vec3d.yCoord;
            entityPlayer2.posZ = vec3d.zCoord;
            entityPlayer2.setHealth(EntityUtil.getHealth((Entity)entityPlayer));
            entityPlayer2.inventory.copyInventory(entityPlayer.inventory);
            entityPlayer = entityPlayer2;
        }
        return entityPlayer;
    }

    @Override
    public void onDisable() {
        this.positions.clear();
        this.lastRenderPos = null;
        if (this.thread != null) {
            this.shouldInterrupt.set(true);
        }
        if (this.executor != null) {
            this.executor.shutdown();
        }
    }

    private void rotateToPos(BlockPos blockPos) {
        switch (this.rotate.getValue()) {
            case OFF: {
                this.rotating = false;
            }
            case BREAK: {
                break;
            }
            case PLACE: 
            case ALL: {
                float[] arrf = MathUtil.calcAngle(AutoCrystal.mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d((double)((float)blockPos.getX() + 0.5f), (double)((float)blockPos.getY() - 0.5f), (double)((float)blockPos.getZ() + 0.5f)));
                if (this.eventMode.getValue() == 2 && this.threadMode.getValue() == ThreadMode.NONE) {
                    if (this.yawstepmode.getValue() == ACYaw.Semi) {
                        float f = AliceMain.rotationManager.getYaw();
                        while (f < arrf[1]) {
                            if (AutoCrystal.mc.player.ticksExisted % this.YawStepTicks.getValue() != 0) continue;
                            AliceMain.rotationManager.setPlayerRotations(f += (float)this.YawStepVal.getValue().intValue(), arrf[1]);
                            this.yawStepTimer.reset();
                        }
                        break;
                    }
                    AliceMain.rotationManager.setPlayerRotations(arrf[0], arrf[1]);
                    break;
                }
                this.yaw = arrf[0];
                this.pitch = arrf[1];
                this.rotating = true;
            }
        }
    }

    private void attackCrystalPredict(int n, BlockPos blockPos) {
        if (!(!this.predictRotate.getValue().booleanValue() || this.eventMode.getValue() == 2 && this.threadMode.getValue() == ThreadMode.NONE || this.rotate.getValue() != ACRotate.BREAK && this.rotate.getValue() != ACRotate.ALL)) {
            this.rotateToPos(blockPos);
        }
        CPacketUseEntity cPacketUseEntity = new CPacketUseEntity();
        cPacketUseEntity.entityId = n;
        cPacketUseEntity.action = CPacketUseEntity.Action.ATTACK;
        AutoCrystal.mc.player.connection.sendPacket((Packet)cPacketUseEntity);
        if (this.swingMode.getValue() == ACSwing.Break || this.swingMode.getValue() == ACSwing.Both) {
            AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
        }
        if (this.resetBreakTimer.getValue().booleanValue()) {
            this.breakTimer.reset();
        }
        this.predictTimer.reset();
    }

    private void postProcessing() {
        if (this.threadMode.getValue() != ThreadMode.NONE || this.eventMode.getValue() != 2 || this.rotate.getValue() == ACRotate.OFF || !this.rotateFirst.getValue().booleanValue()) {
            return;
        }
        switch (this.logic.getValue()) {
            case BREAKPLACE: {
                this.postProcessBreak();
                this.postProcessPlace();
                break;
            }
            case PLACEBREAK: {
                this.postProcessPlace();
                this.postProcessBreak();
            }
        }
    }

    private void handleWhile() {
        if (this.thread == null || this.thread.isInterrupted() || !this.thread.isAlive() || this.syncroTimer.passedMs(this.syncThreads.getValue().intValue()) && this.syncThreadBool.getValue().booleanValue()) {
            if (this.thread == null) {
                this.thread = new Thread(RAutoCrystal.getInstance(this));
            } else if (this.syncroTimer.passedMs(this.syncThreads.getValue().intValue()) && !this.shouldInterrupt.get() && this.syncThreadBool.getValue().booleanValue()) {
                this.shouldInterrupt.set(true);
                this.syncroTimer.reset();
                return;
            }
            if (this.thread != null && (this.thread.isInterrupted() || !this.thread.isAlive())) {
                this.thread = new Thread(RAutoCrystal.getInstance(this));
            }
            if (this.thread != null && this.thread.getState() == Thread.State.NEW) {
                try {
                    this.thread.start();
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
                this.syncroTimer.reset();
            }
        }
    }

    @Override
    public void onTick() {
        if (this.threadMode.getValue() == ThreadMode.NONE && this.eventMode.getValue() == 3) {
            this.doAutoCrystal();
        }
    }

    private boolean doSwitch() {
        if (AutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            this.mainHand = false;
        } else {
            InventoryUtil.switchToHotbarSlot(ItemEndCrystal.class, false);
            this.mainHand = true;
        }
        this.switching = false;
        return true;
    }

    public void postTick() {
        if (this.threadMode.getValue() != ThreadMode.NONE) {
            this.processMultiThreading();
        }
    }

    private void mapCrystals() {
        this.efficientTarget = null;
        if (this.packets.getValue() != 1) {
            this.attackList = new ConcurrentLinkedQueue<Entity>();
            this.crystalMap = new HashMap<Entity, Float>();
        }
        this.crystalCount = 0;
        this.minDmgCount = 0;
        Entity object = null;
        float f = 0.5f;
        for (Entity object2 : AutoCrystal.mc.world.loadedEntityList) {
            if (object2.isDead || !(object2 instanceof EntityEnderCrystal) || !this.isValid(object2)) continue;
            if (this.syncedFeetPlace.getValue().booleanValue() && object2.getPosition().down().equals((Object)this.syncedCrystalPos) && this.damageSync.getValue() != DamageSync.NONE) {
                ++this.minDmgCount;
                ++this.crystalCount;
                if (this.syncCount.getValue().booleanValue()) {
                    this.minDmgCount = this.wasteAmount.getValue() + 1;
                    this.crystalCount = this.wasteAmount.getValue() + 1;
                }
                if (!this.hyperSync.getValue().booleanValue()) continue;
                object = null;
                break;
            }
            boolean bl = false;
            boolean bl2 = false;
            float f2 = -1.0f;
            if (DamageUtil.canTakeDamage(this.suicide.getValue())) {
                f2 = DamageUtil.calculateDamage(object2, (Entity)AutoCrystal.mc.player);
            }
            if ((double)f2 + 0.5 < (double)EntityUtil.getHealth((Entity)AutoCrystal.mc.player) && f2 <= this.maxSelfBreak.getValue().floatValue()) {
                Entity entity = object;
                float f3 = f;
                for (EntityPlayer entityPlayer : AutoCrystal.mc.world.playerEntities) {
                    if (!(entityPlayer.getDistanceSqToEntity(object2) <= MathUtil.square(this.range.getValue().floatValue()))) continue;
                    if (EntityUtil.isValid((Entity)entityPlayer, this.range.getValue().floatValue() + this.breakRange.getValue().floatValue())) {
                        float f4;
                        if (this.antiNaked.getValue().booleanValue() && DamageUtil.isNaked(entityPlayer) || !((f4 = DamageUtil.calculateDamage(object2, (Entity)entityPlayer)) > f2 || f4 > this.minDamage.getValue().floatValue() && !DamageUtil.canTakeDamage(this.suicide.getValue())) && !(f4 > EntityUtil.getHealth((Entity)entityPlayer))) continue;
                        if (f4 > f) {
                            f = f4;
                            object = object2;
                        }
                        if (this.packets.getValue() == 1) {
                            if (f4 >= this.minDamage.getValue().floatValue() || !this.wasteMinDmgCount.getValue().booleanValue()) {
                                bl = true;
                            }
                            bl2 = true;
                            continue;
                        }
                        if (this.crystalMap.get((Object)object2) != null && !(this.crystalMap.get((Object)object2).floatValue() < f4)) continue;
                        this.crystalMap.put(object2, Float.valueOf(f4));
                        continue;
                    }
                    if (this.antiFriendPop.getValue() != AntiFriendPop.BREAK && this.antiFriendPop.getValue() != AntiFriendPop.ALL || !AliceMain.friendManager.isFriend(entityPlayer.getName()) || !((double)DamageUtil.calculateDamage(object2, (Entity)entityPlayer) > (double)EntityUtil.getHealth((Entity)entityPlayer) + 0.5)) continue;
                    object = entity;
                    f = f3;
                    this.crystalMap.remove((Object)object2);
                    if (!this.noCount.getValue().booleanValue()) break;
                    bl = false;
                    bl2 = false;
                    break;
                }
            }
            if (!bl2) continue;
            ++this.minDmgCount;
            if (!bl) continue;
            ++this.crystalCount;
        }
        if (this.damageSync.getValue() == DamageSync.BREAK && ((double)f > this.lastDamage || this.syncTimer.passedMs(this.damageSyncTime.getValue().intValue()) || this.damageSync.getValue() == DamageSync.NONE)) {
            this.lastDamage = f;
        }
        if (this.enormousSync.getValue().booleanValue() && this.syncedFeetPlace.getValue().booleanValue() && this.damageSync.getValue() != DamageSync.NONE && this.syncedCrystalPos != null) {
            if (this.syncCount.getValue().booleanValue()) {
                this.minDmgCount = this.wasteAmount.getValue() + 1;
                this.crystalCount = this.wasteAmount.getValue() + 1;
            }
            return;
        }
        if (this.webAttack.getValue().booleanValue() && this.webPos != null) {
            if (AutoCrystal.mc.player.getDistanceSq(this.webPos.up()) > MathUtil.square(this.breakRange.getValue().floatValue())) {
                this.webPos = null;
            } else {
                for (Entity entity : AutoCrystal.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(this.webPos.up()))) {
                    if (!(entity instanceof EntityEnderCrystal)) continue;
                    this.attackList.add(entity);
                    this.efficientTarget = entity;
                    this.webPos = null;
                    this.lastDamage = 0.5;
                    return;
                }
            }
        }
        if (this.shouldSlowBreak(true) && f < this.minDamage.getValue().floatValue() && (target == null || !(EntityUtil.getHealth((Entity)target) <= this.facePlace.getValue().floatValue()) || !this.breakTimer.passedMs(this.facePlaceSpeed.getValue().intValue()) && this.slowFaceBreak.getValue().booleanValue() && Mouse.isButtonDown((int)0) && this.holdFacePlace.getValue().booleanValue() && this.holdFaceBreak.getValue().booleanValue())) {
            this.efficientTarget = null;
            return;
        }
        if (this.packets.getValue() == 1) {
            this.efficientTarget = object;
        } else {
            this.crystalMap = MathUtil.sortByValue(this.crystalMap, true);
            for (Map.Entry entry : this.crystalMap.entrySet()) {
                Entity entity = (Entity)entry.getKey();
                float f5 = ((Float)entry.getValue()).floatValue();
                if (f5 >= this.minDamage.getValue().floatValue() || !this.wasteMinDmgCount.getValue().booleanValue()) {
                    ++this.crystalCount;
                }
                this.attackList.add(entity);
                ++this.minDmgCount;
            }
        }
    }

    public boolean isActiveRotate() {
        return this.isEnabled() && (this.efficientTarget != null || this.placePos != null) && this.isHoldingCrystal() & this.rotate.getValue() != ACRotate.OFF && Speedmine.INSTANCE.rotate2.getValue() != false;
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent keyInputEvent) {
        if (Keyboard.getEventKeyState() && !(AutoCrystal.mc.currentScreen instanceof LuigiGui) && this.switchBind.getValue().getKey() == Keyboard.getEventKey()) {
            this.switching = !this.switching;
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGH, receiveCanceled=true)
    public void onPacketReceive(PacketEvent.Receive receive) {
        SPacketSoundEffect sPacketSoundEffect;
        if (AutoCrystal.fullNullCheck()) {
            return;
        }
        if (this.switchTimer.passedMs(this.swapdelay.getValue().intValue()) && this.instant.getValue().booleanValue() && receive.getPacket() instanceof SPacketSpawnObject && (this.syncedCrystalPos == null || !this.syncedFeetPlace.getValue().booleanValue() || this.damageSync.getValue() == DamageSync.NONE)) {
            BlockPos blockPos;
            SPacketSpawnObject sPacketSpawnObject = (SPacketSpawnObject)receive.getPacket();
            if (sPacketSpawnObject.getType() == 51 && AutoCrystal.mc.player.getDistanceSq(blockPos = new BlockPos(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ())) + (double)this.predictOffset.getValue().floatValue() <= MathUtil.square(this.breakRange.getValue().floatValue()) && (this.instantTimer.getValue() == PredictTimer.NONE || this.instantTimer.getValue() == PredictTimer.BREAK && this.breakTimer.passedMs(this.breakDelay.getValue().intValue()) || this.instantTimer.getValue() == PredictTimer.PREDICT && this.predictTimer.passedMs(this.predictDelay.getValue().intValue()))) {
                if (this.predictSlowBreak(blockPos.down())) {
                    return;
                }
                if (this.predictFriendDmg.getValue().booleanValue() && (this.antiFriendPop.getValue() == AntiFriendPop.BREAK || this.antiFriendPop.getValue() == AntiFriendPop.ALL) && this.isRightThread()) {
                    for (Object object : AutoCrystal.mc.world.playerEntities) {
                        if (object == null || AutoCrystal.mc.player.equals(object) || object.getDistanceSq(blockPos) > MathUtil.square(this.range.getValue().floatValue() + this.placeRange.getValue().floatValue()) || !AliceMain.friendManager.isFriend((EntityPlayer)object) || !((double)DamageUtil.calculateDamage(blockPos, (Entity)object) > (double)EntityUtil.getHealth((Entity)object) + 0.5)) continue;
                        return;
                    }
                }
                if (placedPos.contains((Object)blockPos.down())) {
                    float f;
                    if (this.isRightThread() && this.superSafe.getValue() != false ? DamageUtil.canTakeDamage(this.suicide.getValue()) && ((double)(f = DamageUtil.calculateDamage(blockPos, (Entity)AutoCrystal.mc.player)) - 0.5 > (double)EntityUtil.getHealth((Entity)AutoCrystal.mc.player) || f > this.maxSelfBreak.getValue().floatValue()) : this.superSafe.getValue() != false) {
                        return;
                    }
                    this.attackCrystalPredict(sPacketSpawnObject.getEntityID(), blockPos);
                } else if (this.predictCalc.getValue().booleanValue() && this.isRightThread()) {
                    float f = -1.0f;
                    if (DamageUtil.canTakeDamage(this.suicide.getValue())) {
                        f = DamageUtil.calculateDamage(blockPos, (Entity)AutoCrystal.mc.player);
                    }
                    if ((double)f + 0.5 < (double)EntityUtil.getHealth((Entity)AutoCrystal.mc.player) && f <= this.maxSelfBreak.getValue().floatValue()) {
                        for (EntityPlayer entityPlayer : AutoCrystal.mc.world.playerEntities) {
                            float f2;
                            if (!(entityPlayer.getDistanceSq(blockPos) <= MathUtil.square(this.range.getValue().floatValue())) || !EntityUtil.isValid((Entity)entityPlayer, this.range.getValue().floatValue() + this.breakRange.getValue().floatValue()) || this.antiNaked.getValue().booleanValue() && DamageUtil.isNaked(entityPlayer) || !((f2 = DamageUtil.calculateDamage(blockPos, (Entity)entityPlayer)) > f || f2 > this.minDamage.getValue().floatValue() && !DamageUtil.canTakeDamage(this.suicide.getValue())) && !(f2 > EntityUtil.getHealth((Entity)entityPlayer))) continue;
                            if (this.predictRotate.getValue().booleanValue() && this.eventMode.getValue() != 2 && (this.rotate.getValue() == ACRotate.BREAK || this.rotate.getValue() == ACRotate.ALL)) {
                                this.rotateToPos(blockPos);
                            }
                            this.attackCrystalPredict(sPacketSpawnObject.getEntityID(), blockPos);
                            break;
                        }
                    }
                }
            }
        } else if (!this.soundConfirm.getValue().booleanValue() && receive.getPacket() instanceof SPacketExplosion) {
            SPacketExplosion sPacketExplosion = (SPacketExplosion)receive.getPacket();
            BlockPos blockPos = new BlockPos(sPacketExplosion.getX(), sPacketExplosion.getY(), sPacketExplosion.getZ()).down();
            this.removePos(blockPos);
        } else if (receive.getPacket() instanceof SPacketDestroyEntities) {
            SPacketDestroyEntities sPacketDestroyEntities = (SPacketDestroyEntities)receive.getPacket();
            for (int n : sPacketDestroyEntities.getEntityIDs()) {
                Entity entity = AutoCrystal.mc.world.getEntityByID(n);
                if (!(entity instanceof EntityEnderCrystal)) continue;
                brokenPos.remove((Object)new BlockPos(entity.getPositionVector()).down());
                placedPos.remove((Object)new BlockPos(entity.getPositionVector()).down());
            }
        } else if (receive.getPacket() instanceof SPacketEntityStatus) {
            SPacketEntityStatus sPacketEntityStatus = (SPacketEntityStatus)receive.getPacket();
            if (sPacketEntityStatus.getOpCode() == 35 && sPacketEntityStatus.getEntity((World)AutoCrystal.mc.world) instanceof EntityPlayer) {
                this.totemPops.put((EntityPlayer)sPacketEntityStatus.getEntity((World)AutoCrystal.mc.world), new Timer().reset());
            }
        } else if (receive.getPacket() instanceof SPacketSoundEffect && (sPacketSoundEffect = (SPacketSoundEffect)receive.getPacket()).getCategory() == SoundCategory.BLOCKS && sPacketSoundEffect.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
            BlockPos blockPos = new BlockPos(sPacketSoundEffect.getX(), sPacketSoundEffect.getY(), sPacketSoundEffect.getZ());
            if (this.sound.getValue().booleanValue() || this.threadMode.getValue() == ThreadMode.SOUND) {
                NoSoundLag.removeEntities(sPacketSoundEffect, this.soundRange.getValue().floatValue());
            }
            if (this.soundConfirm.getValue().booleanValue()) {
                this.removePos(blockPos);
            }
            if (this.threadMode.getValue() == ThreadMode.SOUND && this.isRightThread() && AutoCrystal.mc.player != null && AutoCrystal.mc.player.getDistanceSq(blockPos) < MathUtil.square(this.soundPlayer.getValue().floatValue())) {
                this.handlePool(true);
            }
        }
    }

    public static AutoCrystal getInstance() {
        if (instance == null) {
            instance = new AutoCrystal();
        }
        return instance;
    }

    private boolean check() {
        if (AutoCrystal.fullNullCheck()) {
            return false;
        }
        if (this.syncTimer.passedMs(this.damageSyncTime.getValue().intValue())) {
            this.currentSyncTarget = null;
            this.syncedCrystalPos = null;
            this.syncedPlayerPos = null;
        } else if (this.syncySync.getValue().booleanValue() && this.syncedCrystalPos != null) {
            this.posConfirmed = true;
        }
        this.foundDoublePop = false;
        if (this.renderTimer.passedMs(500L)) {
            this.renderPos = null;
            this.renderTimer.reset();
        }
        this.mainHand = AutoCrystal.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL;
        boolean bl = this.mainHand;
        if (this.autoSwitch.getValue() == ACAutoSwitch.Spoof && InventoryUtil.getItemHotbar(Items.END_CRYSTAL) != -1) {
            this.mainHand = true;
            this.shouldSilent = true;
        } else {
            this.shouldSilent = false;
        }
        this.offHand = AutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
        this.currentDamage = 0.0;
        this.placePos = null;
        if (this.lastSlot != AutoCrystal.mc.player.inventory.currentItem) {
            this.lastSlot = AutoCrystal.mc.player.inventory.currentItem;
            this.switchTimer.reset();
        }
        if (!this.offHand && !this.mainHand) {
            this.placeInfo = null;
            this.packetUseEntities.clear();
        }
        if (this.offHand || this.mainHand) {
            this.switching = false;
        }
        if (!((this.offHand || this.mainHand || this.switchMode.getValue() != Switch.BREAKSLOT || this.switching) && DamageUtil.canBreakWeakness((EntityPlayer)AutoCrystal.mc.player) && this.switchTimer.passedMs(this.swapdelay.getValue().intValue()))) {
            this.renderPos = null;
            target = null;
            this.rotating = false;
            return false;
        }
        if (this.mineSwitch.getValue().booleanValue() && Mouse.isButtonDown((int)0) && (this.switching || this.autoSwitch.getValue() == ACAutoSwitch.Swap) && Mouse.isButtonDown((int)1) && AutoCrystal.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
            this.switchItem();
        }
        this.mapCrystals();
        if (!this.posConfirmed && this.damageSync.getValue() != DamageSync.NONE && this.syncTimer.passedMs(this.confirm.getValue().intValue())) {
            this.syncTimer.setMs(this.damageSyncTime.getValue() + 1);
        }
        return true;
    }

    public boolean isActive() {
        return this.isEnabled() && (this.efficientTarget != null || this.placePos != null) && this.isHoldingCrystal();
    }

    private void rotateTo(Entity entity) {
        switch (this.rotate.getValue()) {
            case OFF: {
                this.rotating = false;
            }
            case PLACE: {
                break;
            }
            case BREAK: 
            case ALL: {
                float[] arrf = MathUtil.calcAngle(AutoCrystal.mc.player.getPositionEyes(mc.getRenderPartialTicks()), entity.getPositionVector());
                if (this.eventMode.getValue() == 2 && this.threadMode.getValue() == ThreadMode.NONE) {
                    if (this.yawstepmode.getValue() == ACYaw.Semi) {
                        float f = AliceMain.rotationManager.getYaw();
                        while (f < arrf[1]) {
                            if (AutoCrystal.mc.player.ticksExisted % this.YawStepTicks.getValue() != 0) continue;
                            AliceMain.rotationManager.setPlayerRotations(f += (float)this.YawStepVal.getValue().intValue(), arrf[1]);
                        }
                        break;
                    }
                    AliceMain.rotationManager.setPlayerRotations(arrf[0], arrf[1]);
                    break;
                }
                this.yaw = arrf[0];
                this.pitch = arrf[1];
                this.rotating = true;
            }
        }
    }

    public boolean isHoldingCrystal() {
        return InventoryUtilCC.isHolding(Items.END_CRYSTAL) || this.autoSwitch.getValue().equals((Object)ACAutoSwitch.Spoof) && InventoryUtilCC.isInHotbar(Items.END_CRYSTAL);
    }

    private boolean switchItem() {
        if (this.offHand || this.mainHand) {
            return true;
        }
        switch (this.autoSwitch.getValue()) {
            case None: {
                return false;
            }
            case Toggle: {
                if (!this.switching) {
                    return false;
                }
            }
            case Swap: {
                if (!this.doSwitch()) break;
                return true;
            }
        }
        return false;
    }

    @Override
    public void onUpdate() {
        if (this.threadMode.getValue() == ThreadMode.NONE && this.eventMode.getValue() == 1) {
            this.doAutoCrystal();
        }
    }

    public static enum ThreadMode {
        NONE,
        POOL,
        SOUND,
        WHILE;

    }

    public static enum DamageSync {
        NONE,
        PLACE,
        BREAK;

    }

    public static enum Target {
        CLOSEST,
        UNSAFE,
        DAMAGE;

    }

    public static enum Switch {
        ALWAYS,
        BREAKSLOT,
        CALC;

    }

    public static enum PredictTimer {
        NONE,
        BREAK,
        PREDICT;

    }

    private static class RAutoCrystal
    implements Runnable {
        private /* synthetic */ AutoCrystal autoCrystal;
        private static /* synthetic */ RAutoCrystal instance;

        private RAutoCrystal() {
        }

        @Override
        public void run() {
            if (this.autoCrystal.threadMode.getValue() == ThreadMode.WHILE) {
                while (this.autoCrystal.isOn() && this.autoCrystal.threadMode.getValue() == ThreadMode.WHILE) {
                    while (AliceMain.eventManager.ticksOngoing()) {
                    }
                    if (this.autoCrystal.shouldInterrupt.get()) {
                        this.autoCrystal.shouldInterrupt.set(false);
                        this.autoCrystal.syncroTimer.reset();
                        this.autoCrystal.thread.interrupt();
                        break;
                    }
                    this.autoCrystal.threadOngoing.set(true);
                    AliceMain.safetyManager.doSafetyCheck();
                    this.autoCrystal.doAutoCrystal();
                    this.autoCrystal.threadOngoing.set(false);
                    try {
                        Thread.sleep(this.autoCrystal.threadDelay.getValue().intValue());
                    }
                    catch (InterruptedException interruptedException) {
                        this.autoCrystal.thread.interrupt();
                        interruptedException.printStackTrace();
                    }
                }
            } else if (this.autoCrystal.threadMode.getValue() != ThreadMode.NONE && this.autoCrystal.isOn()) {
                while (AliceMain.eventManager.ticksOngoing()) {
                }
                this.autoCrystal.threadOngoing.set(true);
                AliceMain.safetyManager.doSafetyCheck();
                this.autoCrystal.doAutoCrystal();
                this.autoCrystal.threadOngoing.set(false);
            }
        }

        public static RAutoCrystal getInstance(AutoCrystal autoCrystal) {
            if (instance == null) {
                instance = new RAutoCrystal();
                RAutoCrystal.instance.autoCrystal = autoCrystal;
            }
            return instance;
        }
    }

    private class RenderPos {
        private /* synthetic */ BlockPos renderPos;
        private /* synthetic */ float renderTime;

        public float getRenderTime() {
            return this.renderTime;
        }

        public RenderPos(BlockPos blockPos, float f) {
            this.renderPos = blockPos;
            this.renderTime = f;
        }

        public void setPos(BlockPos blockPos) {
            this.renderPos = blockPos;
        }

        public BlockPos getPos() {
            return this.renderPos;
        }

        public void setRenderTime(float f) {
            this.renderTime = f;
        }
    }

    public static enum Raytrace {
        NONE,
        PLACE,
        BREAK,
        FULL;

    }

    public static class PlaceInfo {
        private final /* synthetic */ boolean placeSwing;
        private final /* synthetic */ BlockPos pos;
        private final /* synthetic */ boolean offhand;
        private final /* synthetic */ boolean silent;
        private final /* synthetic */ boolean exactHand;

        public PlaceInfo(BlockPos blockPos, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
            this.pos = blockPos;
            this.offhand = bl;
            this.placeSwing = bl2;
            this.exactHand = bl3;
            this.silent = bl4;
        }

        public void runPlace() {
            BlockUtil.placeCrystalOnBlock(this.pos, this.offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, this.placeSwing, this.exactHand, this.silent);
        }
    }

    public static enum Logic {
        BREAKPLACE,
        PLACEBREAK;

    }

    public static enum AntiFriendPop {
        NONE,
        PLACE,
        BREAK,
        ALL;

    }
}

