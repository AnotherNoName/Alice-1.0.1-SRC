//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.ItemEndCrystal
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.item.ItemTool
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.network.play.client.CPacketUseEntity$Action
 *  net.minecraft.network.play.server.SPacketEntityTeleport
 *  net.minecraft.network.play.server.SPacketExplosion
 *  net.minecraft.network.play.server.SPacketSoundEffect
 *  net.minecraft.network.play.server.SPacketSpawnObject
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 */
package me.snow.aclient.module.modules.hidden;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Bind;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.event.processor.CommitEvent;
import me.snow.aclient.event.processor.EventPriority;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.combat.autocrystal.AutoCrystal;
import me.snow.aclient.module.modules.hidden.Threads;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.ca.util.BlockUtilCa;
import me.snow.aclient.util.ca.util.Colour;
import me.snow.aclient.util.ca.util.CrystalUtilCa;
import me.snow.aclient.util.ca.util.EntityUtilCa;
import me.snow.aclient.util.ca.util.MathsUtilCa;
import me.snow.aclient.util.ca.util.PlayerUtilCa;
import me.snow.aclient.util.ca.util.RenderUtilCa;
import me.snow.aclient.util.ca.util.TimerCa;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.server.SPacketEntityTeleport;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.network.play.server.SPacketSpawnObject;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public final class CrystalAura
extends Module {
    public /* synthetic */ Setting<Rotate> rotate;
    private final /* synthetic */ Setting<Boolean> flat;
    public /* synthetic */ Setting<Integer> renderBoxColourgreen;
    private final /* synthetic */ Setting<Double> breakRange;
    private /* synthetic */ float yaw;
    private final /* synthetic */ Setting<Boolean> ObiYCheck;
    public /* synthetic */ Setting<Integer> renderBoxColouralpha;
    private final /* synthetic */ Setting<Boolean> fade;
    private final /* synthetic */ Setting<Boolean> debug;
    private /* synthetic */ boolean didAnything;
    private final /* synthetic */ Setting<Boolean> antiSuicide;
    private /* synthetic */ long crystalLatency;
    private final /* synthetic */ Setting<Boolean> palceObiFeet;
    private final /* synthetic */ Setting<Double> placeRange;
    private final /* synthetic */ Setting<Boolean> packetSafe;
    private /* synthetic */ boolean placeTimeoutFlag;
    public /* synthetic */ Setting<Integer> renderBoxColourblue;
    private final /* synthetic */ Setting<Double> height;
    public /* synthetic */ Setting<AautoSwitch> autoSwitch;
    public /* synthetic */ Setting<ArotateMode> rotateMode;
    private final /* synthetic */ Setting<Boolean> predictBlock;
    private /* synthetic */ int facePlaceDelayCounter;
    private final /* synthetic */ Setting<Boolean> noBreakCalcs;
    private final /* synthetic */ Setting<Integer> maxCrystals;
    private final /* synthetic */ List<EntityEnderCrystal> attemptedCrystals;
    public /* synthetic */ Setting<Integer> renderBoxColourred;
    private final /* synthetic */ Setting<Boolean> placeSwing;
    private final /* synthetic */ Setting<Integer> width;
    private /* synthetic */ int obiFeetCounter;
    private final /* synthetic */ Setting<Double> breakRangeWall;
    public /* synthetic */ Setting<AfastMode> fastMode;
    private /* synthetic */ EntityEnderCrystal stuckCrystal;
    private final /* synthetic */ Setting<Integer> minBreak;
    public /* synthetic */ EntityPlayer ezTarget;
    private /* synthetic */ int crystalsPlaced;
    private /* synthetic */ int breakDelayCounter;
    private final /* synthetic */ Setting<Integer> facePlaceDelay;
    private final /* synthetic */ Setting<Boolean> ignoreTerrain;
    public final /* synthetic */ Setting<Bind> fpbind;
    public /* synthetic */ Setting<ApredictTeleport> predictTeleport;
    private final /* synthetic */ ArrayList<BlockPos> currentTargets;
    private final /* synthetic */ Setting<Boolean> attackPacket;
    private final /* synthetic */ Setting<Integer> timeoutTicksObiFeet;
    private /* synthetic */ int rotationPacketsSpoofed;
    private final /* synthetic */ Setting<Integer> facePlaceHP;
    private /* synthetic */ float pitch;
    private final /* synthetic */ Setting<Integer> predictedTicks;
    public /* synthetic */ Setting<Integer> renderFillColouralpha;
    public /* synthetic */ Setting<Integer> renderFillColourred;
    private final /* synthetic */ Setting<Integer> breakDelay;
    private final /* synthetic */ Setting<Integer> placeDelay;
    private final /* synthetic */ TimerCa crystalsPlacedTimer;
    private final /* synthetic */ Setting<Boolean> threaded;
    public /* synthetic */ Setting<AcrystalLogic> crystalLogic;
    private /* synthetic */ boolean facePlacing;
    private final /* synthetic */ Setting<Boolean> predictCrystal;
    private final /* synthetic */ Setting<Integer> fuckArmourHP;
    private /* synthetic */ boolean rotating;
    private /* synthetic */ int predict;
    private final /* synthetic */ Setting<Boolean> antiStuck;
    private /* synthetic */ long start;
    public /* synthetic */ Setting<Awhen> when;
    public /* synthetic */ Setting<AarrayListMode> arrayListMode;
    public /* synthetic */ Setting<Integer> renderFillColourgreen;
    private final /* synthetic */ Setting<Boolean> thirteen;
    private final /* synthetic */ Setting<Boolean> renderDamage;
    public /* synthetic */ ArrayList<BlockPos> staticPos;
    private final /* synthetic */ Setting<Boolean> antiWeakness;
    private final /* synthetic */ ArrayList<RenderPos> renderMap;
    public static /* synthetic */ CrystalAura INSTANCE;
    private final /* synthetic */ Setting<Boolean> ignoreSelfDamage;
    private final /* synthetic */ Setting<Double> placeRangeWall;
    public /* synthetic */ Setting<Integer> rotations;
    private final /* synthetic */ Setting<Integer> maxYaw;
    private final /* synthetic */ Setting<Double> targetRange;
    private final /* synthetic */ Setting<Integer> minPlace;
    public /* synthetic */ EntityEnderCrystal staticEnderCrystal;
    private final /* synthetic */ Setting<Boolean> noMP;
    private /* synthetic */ int breakTimeout;
    private final /* synthetic */ Setting<Boolean> silentSwitchHand;
    public /* synthetic */ Setting<Aswing> swing;
    public /* synthetic */ Setting<Amode> mode;
    private /* synthetic */ boolean alreadyAttacking;
    private final /* synthetic */ Setting<Integer> fadeTime;
    private /* synthetic */ int placeTimeout;
    private final /* synthetic */ Setting<Boolean> rotateObiFeet;
    private final /* synthetic */ Setting<Integer> maxSelfPlace;
    private final /* synthetic */ Setting<Integer> maxSelfBreak;
    private final /* synthetic */ Setting<Integer> maxAntiStuckDamage;
    private final /* synthetic */ Setting<Boolean> raytrace;
    private /* synthetic */ int placeDelayCounter;
    private /* synthetic */ boolean hasPacketBroke;
    private final /* synthetic */ Setting<Boolean> entityPredict;
    private final /* synthetic */ Setting<Boolean> sortBlocks;
    public /* synthetic */ Setting<Integer> renderFillColourblue;

    private /* synthetic */ void lambda$onPacketReceive$8(SPacketSoundEffect sPacketSoundEffect, Entity entity) {
        if (entity instanceof EntityPlayer && entity != CrystalAura.mc.player && entity.getDistance(sPacketSoundEffect.getX(), sPacketSoundEffect.getY(), sPacketSoundEffect.getZ()) <= this.targetRange.getValue()) {
            entity.setEntityBoundingBox(entity.getEntityBoundingBox().offset(sPacketSoundEffect.getX(), sPacketSoundEffect.getY(), sPacketSoundEffect.getZ()));
        }
    }

    private void blockObiNextToPlayer(EntityPlayer entityPlayer) {
        if (this.ObiYCheck.getValue().booleanValue() && Math.floor(entityPlayer.posY) == Math.floor(CrystalAura.mc.player.posY)) {
            return;
        }
        this.obiFeetCounter = 0;
        BlockPos blockPos = EntityUtilCa.getFlooredPos((Entity)entityPlayer).down();
        if (EntityUtilCa.isInHole((Entity)entityPlayer) || CrystalAura.mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR) {
            return;
        }
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                BlockPos blockPos2;
                if (i == 0 && j == 0 || !CrystalAura.mc.world.getBlockState(blockPos2 = blockPos.add(i, 0, j)).getMaterial().isReplaceable()) continue;
                BlockUtilCa.placeBlock(blockPos2, PlayerUtilCa.findObiInHotbar(), this.rotateObiFeet.getValue(), this.rotateObiFeet.getValue());
            }
        }
    }

    @CommitEvent(priority=EventPriority.HIGH)
    public void onUpdateWalkingPlayer(@NotNull UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 0 && (this.rotateMode.getValue() == ArotateMode.Break || this.rotateMode.getValue() == ArotateMode.Place || this.rotateMode.getValue() == ArotateMode.Both)) {
            this.doCrystalAura();
        }
    }

    @Override
    public void onEnable() {
        this.placeTimeout = this.placeDelay.getValue();
        this.breakTimeout = this.breakDelay.getValue();
        this.placeTimeoutFlag = false;
        this.ezTarget = null;
        this.facePlacing = false;
        this.attemptedCrystals.clear();
        this.hasPacketBroke = false;
        this.alreadyAttacking = false;
        this.obiFeetCounter = 0;
        this.crystalLatency = 0L;
        this.start = 0L;
        this.staticEnderCrystal = null;
        this.staticPos = null;
        this.crystalsPlaced = 0;
        this.crystalsPlacedTimer.reset();
    }

    @CommitEvent(priority=EventPriority.HIGH)
    public void onPacketReceive(@NotNull PacketEvent.Receive receive) {
        Object object;
        Object object22;
        SPacketSpawnObject sPacketSpawnObject;
        if (receive.getPacket() instanceof SPacketSpawnObject && (sPacketSpawnObject = (SPacketSpawnObject)receive.getPacket()).getType() == 51 && this.predictCrystal.getValue().booleanValue()) {
            for (Object object22 : new ArrayList(CrystalAura.mc.world.playerEntities)) {
                if (this.isCrystalGood(new EntityEnderCrystal((World)CrystalAura.mc.world, sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ()), (EntityPlayer)object22) == 0.0) continue;
                if (this.debug.getValue().booleanValue()) {
                    Command.sendMessage("predict break");
                }
                Object object3 = new CPacketUseEntity();
                ((CPacketUseEntity)object3).entityId = sPacketSpawnObject.getEntityID();
                ((CPacketUseEntity)object3).action = CPacketUseEntity.Action.ATTACK;
                CrystalAura.mc.player.connection.sendPacket((Packet)object3);
                if (this.swing.getValue() == Aswing.Mainhand || this.swing.getValue() == Aswing.Offhand) {
                    // empty if block
                }
                if (!this.packetSafe.getValue().booleanValue()) break;
                this.hasPacketBroke = true;
                this.didAnything = true;
                break;
            }
        }
        if (receive.getPacket() instanceof SPacketEntityTeleport) {
            object = (SPacketEntityTeleport)receive.getPacket();
            object22 = CrystalAura.mc.world.getEntityByID(object.getEntityId());
            if (object22 == CrystalAura.mc.player) {
                return;
            }
            if (object22 instanceof EntityPlayer && this.predictTeleport.getValue() == ApredictTeleport.Packet) {
                object22.setEntityBoundingBox(object22.getEntityBoundingBox().offset(object.getX(), object.getY(), object.getZ()));
            }
        }
        if (receive.getPacket() instanceof SPacketSoundEffect) {
            object = (SPacketSoundEffect)receive.getPacket();
            if (object.getSound() == SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT && this.predictTeleport.getValue() == ApredictTeleport.Sound) {
                CrystalAura.mc.world.loadedEntityList.spliterator().forEachRemaining(arg_0 -> this.lambda$onPacketReceive$8((SPacketSoundEffect)object, arg_0));
            }
            try {
                if (object.getCategory() == SoundCategory.BLOCKS && object.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                    for (Object object3 : new ArrayList(CrystalAura.mc.world.loadedEntityList)) {
                        if (!(object3 instanceof EntityEnderCrystal) || !(object3.getDistance(object.getX(), object.getY(), object.getZ()) <= this.breakRange.getValue())) continue;
                        this.crystalLatency = System.currentTimeMillis() - this.start;
                        if (this.fastMode.getValue() != AfastMode.Sound) continue;
                        object3.setDead();
                    }
                }
            }
            catch (NullPointerException nullPointerException) {
                // empty catch block
            }
        }
        if (receive.getPacket() instanceof SPacketExplosion) {
            object = (SPacketExplosion)receive.getPacket();
            object22 = new BlockPos(Math.floor(object.getX()), Math.floor(object.getY()), Math.floor(object.getZ())).down();
            if (this.predictBlock.getValue().booleanValue()) {
                for (EntityPlayer entityPlayer : new ArrayList(CrystalAura.mc.world.playerEntities)) {
                    if (!(this.isBlockGood((BlockPos)object22, entityPlayer) > 0.0)) continue;
                    BlockUtilCa.placeCrystalOnBlock((BlockPos)object22, EnumHand.MAIN_HAND, true);
                }
            }
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
                this.yaw = arrf[0];
                this.pitch = arrf[1];
                this.rotating = true;
            }
        }
    }

    public boolean setYawPitch(@NotNull BlockPos blockPos) {
        if (this.rotateMode.getValue() == ArotateMode.Off || this.rotateMode.getValue() == ArotateMode.Break) {
            return true;
        }
        float[] arrf = MathsUtilCa.calcAngle(CrystalAura.mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d((double)((float)blockPos.getX() + 0.5f), (double)((float)blockPos.getY() + 0.5f), (double)((float)blockPos.getZ() + 0.5f)));
        float f = arrf[0];
        float f2 = arrf[1];
        float f3 = AliceMain.newrotationManager.getSpoofedYaw();
        if (Math.abs(f3 - f) > (float)this.maxYaw.getValue().intValue() && Math.abs(f3 - 360.0f - f) > (float)this.maxYaw.getValue().intValue() && Math.abs(f3 + 360.0f - f) > (float)this.maxYaw.getValue().intValue()) {
            AliceMain.newrotationManager.setPlayerRotations(Math.abs(f3 - f) < 180.0f ? (f3 > f ? f3 - (float)this.maxYaw.getValue().intValue() : f3 + (float)this.maxYaw.getValue().intValue()) : (f3 > f ? f3 + (float)this.maxYaw.getValue().intValue() : f3 - (float)this.maxYaw.getValue().intValue()), f2);
            return false;
        }
        AliceMain.newrotationManager.setPlayerRotations(f, f2);
        return true;
    }

    @CommitEvent(priority=EventPriority.HIGH)
    public void onPacketSend(@NotNull PacketEvent.Send send) {
        CPacketUseEntity cPacketUseEntity;
        if (send.getStage() == 0 && this.rotate.getValue() != Rotate.OFF && this.rotating && send.getPacket() instanceof CPacketPlayer) {
            CPacketPlayer cPacketPlayer = (CPacketPlayer)send.getPacket();
            cPacketPlayer.yaw = this.yaw;
            cPacketPlayer.pitch = this.pitch;
            ++this.rotationPacketsSpoofed;
            if (this.rotationPacketsSpoofed >= this.rotations.getValue()) {
                this.rotating = false;
                this.rotationPacketsSpoofed = 0;
            }
        }
        if (send.getStage() == 0 && send.getPacket() instanceof CPacketUseEntity && (cPacketUseEntity = (CPacketUseEntity)send.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK && cPacketUseEntity.getEntityFromWorld((World)CrystalAura.mc.world) instanceof EntityEnderCrystal && this.fastMode.getValue() == AfastMode.Ghost) {
            Objects.requireNonNull(cPacketUseEntity.getEntityFromWorld((World)CrystalAura.mc.world)).setDead();
            CrystalAura.mc.world.removeEntityFromWorld(cPacketUseEntity.entityId);
        }
    }

    private void breakCrystalNoCalcs() {
        for (Entity entity : CrystalAura.mc.world.loadedEntityList) {
            EntityEnderCrystal entityEnderCrystal;
            if (!(entity instanceof EntityEnderCrystal) || entity.isDead || (double)CrystalAura.mc.player.getDistanceToEntity(entity) > this.breakRange.getValue() || !CrystalAura.mc.player.canEntityBeSeen(entity) && (this.raytrace.getValue().booleanValue() || (double)CrystalAura.mc.player.getDistanceToEntity(entity) > this.breakRangeWall.getValue())) continue;
            if (this.antiWeakness.getValue().booleanValue() && CrystalAura.mc.player.isPotionActive(MobEffects.WEAKNESS)) {
                boolean bl = true;
                if (CrystalAura.mc.player.isPotionActive(MobEffects.STRENGTH) && Objects.requireNonNull(CrystalAura.mc.player.getActivePotionEffect(MobEffects.STRENGTH)).getAmplifier() == 2) {
                    bl = false;
                }
                if (bl) {
                    if (!this.alreadyAttacking) {
                        this.alreadyAttacking = true;
                    }
                    int n = -1;
                    for (int i = 0; i < 9; ++i) {
                        ItemStack itemStack = CrystalAura.mc.player.inventory.getStackInSlot(i);
                        if (!(itemStack.getItem() instanceof ItemSword) && !(itemStack.getItem() instanceof ItemTool)) continue;
                        n = i;
                        CrystalAura.mc.playerController.updateController();
                        break;
                    }
                    if (n != -1) {
                        CrystalAura.mc.player.inventory.currentItem = n;
                    }
                }
            }
            if (this.setYawPitch(entityEnderCrystal = (EntityEnderCrystal)entity)) {
                EntityUtilCa.attackEntity((Entity)entityEnderCrystal, this.attackPacket.getValue());
                if (this.swing.getValue() == Aswing.Mainhand || this.swing.getValue() == Aswing.Offhand) {
                    // empty if block
                }
                if (this.debug.getValue().booleanValue()) {
                    Command.sendMessage("breaking");
                }
                this.breakDelayCounter = 0;
            } else if (this.debug.getValue().booleanValue()) {
                Command.sendMessage("doing yawstep on break");
            }
            this.rotateTo((Entity)entityEnderCrystal);
        }
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
                this.yaw = arrf[0];
                this.pitch = arrf[1];
                this.rotating = true;
            }
        }
    }

    public float getCPS() {
        return (float)this.crystalsPlaced / ((float)this.crystalsPlacedTimer.getPassedTimeMs() / 1000.0f);
    }

    private double isBlockGood(@NotNull BlockPos blockPos, @NotNull EntityPlayer entityPlayer) {
        if (this.isPlayerValid(entityPlayer)) {
            if (!CrystalUtilCa.canSeePos(blockPos) && this.raytrace.getValue().booleanValue()) {
                return 0.0;
            }
            if (!CrystalUtilCa.canSeePos(blockPos) ? CrystalAura.mc.player.getDistanceSq(blockPos) > MathsUtilCa.square(this.placeRangeWall.getValue().floatValue()) : CrystalAura.mc.player.getDistanceSq(blockPos) > MathsUtilCa.square(this.placeRange.getValue().floatValue())) {
                return 0.0;
            }
            double d = CrystalUtilCa.calculateDamage(blockPos, (Entity)entityPlayer, (boolean)this.ignoreTerrain.getValue());
            this.facePlacing = false;
            double d2 = this.minPlace.getValue().intValue();
            if ((EntityUtilCa.getHealth((Entity)entityPlayer) <= (float)this.facePlaceHP.getValue().intValue() || CrystalUtilCa.getArmourFucker(entityPlayer, this.fuckArmourHP.getValue().intValue()).booleanValue() || this.fpbind.getValue().isDown()) && d < (double)this.minPlace.getValue().intValue()) {
                d2 = EntityUtilCa.isInHole((Entity)entityPlayer) ? 1.0 : 2.0;
                this.facePlacing = true;
            }
            if (d < d2 && (double)EntityUtilCa.getHealth((Entity)entityPlayer) - d > 0.0) {
                return 0.0;
            }
            double d3 = 0.0;
            if (!this.ignoreSelfDamage.getValue().booleanValue()) {
                d3 = CrystalUtilCa.calculateDamage(blockPos, (Entity)CrystalAura.mc.player, (boolean)this.ignoreTerrain.getValue());
            }
            if (d3 > (double)this.maxSelfPlace.getValue().intValue()) {
                return 0.0;
            }
            if ((double)EntityUtilCa.getHealth((Entity)CrystalAura.mc.player) - d3 <= 0.0 && this.antiSuicide.getValue().booleanValue()) {
                return 0.0;
            }
            switch (this.crystalLogic.getValue()) {
                case Safe: {
                    return d - d3;
                }
                case Damage: {
                    return d;
                }
                case Nearby: {
                    double d4 = CrystalAura.mc.player.getDistanceSq(blockPos);
                    return d - d4;
                }
            }
        }
        return 0.0;
    }

    public void doCrystalAura() {
        if (CrystalAura.nullCheck()) {
            this.disable();
            return;
        }
        this.didAnything = false;
        if (!(this.placeDelayCounter <= this.placeTimeout || this.facePlaceDelayCounter < this.facePlaceDelay.getValue() && this.facePlacing)) {
            this.start = System.currentTimeMillis();
            this.placeCrystal();
        }
        if (!(this.breakDelayCounter <= this.breakTimeout || this.hasPacketBroke && this.packetSafe.getValue().booleanValue())) {
            if (this.debug.getValue().booleanValue()) {
                Command.sendMessage("Attempting break");
            }
            if (this.noBreakCalcs.getValue().booleanValue()) {
                this.breakCrystalNoCalcs();
            } else if (this.antiStuck.getValue().booleanValue() && this.stuckCrystal != null) {
                this.breakCrystal(this.stuckCrystal);
                this.stuckCrystal = null;
            } else {
                this.breakCrystal(null);
            }
        }
        if (!this.didAnything) {
            this.hasPacketBroke = false;
        }
        ++this.breakDelayCounter;
        ++this.placeDelayCounter;
        ++this.facePlaceDelayCounter;
        ++this.obiFeetCounter;
    }

    private void breakCrystal(EntityEnderCrystal entityEnderCrystal) {
        EntityEnderCrystal entityEnderCrystal2;
        if (this.threaded.getValue().booleanValue()) {
            Threads threads = new Threads();
            threads.start();
            entityEnderCrystal2 = this.staticEnderCrystal;
        } else {
            entityEnderCrystal2 = this.getBestCrystal();
        }
        if (entityEnderCrystal != null) {
            if (this.debug.getValue().booleanValue()) {
                Command.sendMessage("Overwriting Crystal");
            }
            if (CrystalUtilCa.calculateDamage((Entity)entityEnderCrystal, (Entity)CrystalAura.mc.player, (boolean)this.ignoreTerrain.getValue()) < (float)this.maxAntiStuckDamage.getValue().intValue()) {
                entityEnderCrystal2 = entityEnderCrystal;
            }
        }
        if (entityEnderCrystal2 == null) {
            return;
        }
        if (this.antiWeakness.getValue().booleanValue() && CrystalAura.mc.player.isPotionActive(MobEffects.WEAKNESS)) {
            boolean bl = true;
            if (CrystalAura.mc.player.isPotionActive(MobEffects.STRENGTH) && Objects.requireNonNull(CrystalAura.mc.player.getActivePotionEffect(MobEffects.STRENGTH)).getAmplifier() == 2) {
                bl = false;
            }
            if (bl) {
                if (!this.alreadyAttacking) {
                    this.alreadyAttacking = true;
                }
                int n = -1;
                for (int i = 0; i < 9; ++i) {
                    ItemStack itemStack = CrystalAura.mc.player.inventory.getStackInSlot(i);
                    if (!(itemStack.getItem() instanceof ItemSword) && !(itemStack.getItem() instanceof ItemTool)) continue;
                    n = i;
                    CrystalAura.mc.playerController.updateController();
                    break;
                }
                if (n != -1) {
                    CrystalAura.mc.player.inventory.currentItem = n;
                }
            }
        }
        this.rotateTo((Entity)entityEnderCrystal2);
        this.didAnything = true;
        if (this.setYawPitch(entityEnderCrystal2)) {
            EntityUtilCa.attackEntity((Entity)entityEnderCrystal2, this.attackPacket.getValue());
            if (this.swing.getValue() == Aswing.Mainhand || this.swing.getValue() == Aswing.Offhand) {
                // empty if block
            }
            if (this.debug.getValue().booleanValue()) {
                Command.sendMessage("breaking");
            }
            this.breakDelayCounter = 0;
        } else if (this.debug.getValue().booleanValue()) {
            Command.sendMessage("doing yawstep on break");
        }
    }

    private ArrayList<BlockPos> getBestBlocks() {
        Object object2;
        ArrayList<RenderPos> arrayList = new ArrayList<RenderPos>();
        if (this.getBestCrystal() != null && this.fastMode.getValue() == AfastMode.Off) {
            this.placeTimeoutFlag = true;
            return null;
        }
        if (this.placeTimeoutFlag) {
            this.placeTimeoutFlag = false;
            return null;
        }
        for (Object object2 : new ArrayList(CrystalAura.mc.world.playerEntities)) {
            if (CrystalAura.mc.player.getDistanceSqToEntity((Entity)object2) > MathsUtilCa.square(this.targetRange.getValue().floatValue())) continue;
            if (this.entityPredict.getValue().booleanValue()) {
                float f = ((EntityPlayer)object2).width / 2.0f;
                float f2 = ((EntityPlayer)object2).height;
                object2.setEntityBoundingBox(new AxisAlignedBB(((EntityPlayer)object2).posX - (double)f, ((EntityPlayer)object2).posY, ((EntityPlayer)object2).posZ - (double)f, ((EntityPlayer)object2).posX + (double)f, ((EntityPlayer)object2).posY + (double)f2, ((EntityPlayer)object2).posZ + (double)f));
                Entity entity = CrystalUtilCa.getPredictedPosition((Entity)object2, this.predictedTicks.getValue().intValue());
                object2.setEntityBoundingBox(entity.getEntityBoundingBox());
            }
            for (BlockPos blockPos : CrystalUtilCa.possiblePlacePositions(this.placeRange.getValue().floatValue(), true, this.thirteen.getValue())) {
                double d = this.isBlockGood(blockPos, (EntityPlayer)object2);
                if (d <= 0.0) continue;
                arrayList.add(new RenderPos(blockPos, d));
            }
        }
        if (this.sortBlocks.getValue().booleanValue()) {
            arrayList.sort(new DamageComparator());
        }
        if (this.maxCrystals.getValue() > 1) {
            ArrayList arrayList2 = new ArrayList();
            object2 = new ArrayList();
            for (RenderPos renderPos : arrayList) {
                boolean bl = false;
                Iterator iterator2 = arrayList2.iterator();
                while (iterator2.hasNext()) {
                    BlockPos blockPos = (BlockPos)iterator2.next();
                    if (blockPos.getX() != renderPos.pos.getX() || blockPos.getY() != renderPos.pos.getY() || blockPos.getZ() != renderPos.pos.getZ()) continue;
                    bl = true;
                }
                if (!bl) {
                    arrayList2.addAll(this.getBlockedPositions(renderPos.pos));
                    continue;
                }
                object2.add(renderPos);
            }
            arrayList.removeAll((Collection<?>)object2);
        }
        if (this.ezTarget != null) {
            // empty if block
        }
        int n = this.maxCrystals.getValue();
        if (this.facePlacing && this.noMP.getValue().booleanValue()) {
            n = 1;
        }
        object2 = new ArrayList();
        IntStream.range(0, Math.min(n, arrayList.size())).forEachOrdered(arg_0 -> this.lambda$getBestBlocks$9(arrayList, (ArrayList)object2, arg_0));
        return object2;
    }

    private int getCrystalCount(boolean bl) {
        if (bl) {
            return CrystalAura.mc.player.getHeldItemOffhand().stackSize;
        }
        return CrystalAura.mc.player.getHeldItemMainhand().stackSize;
    }

    private void placeCrystal() {
        ArrayList<BlockPos> arrayList = this.getBestBlocks();
        this.currentTargets.clear();
        this.currentTargets.addAll(arrayList);
        if (arrayList == null) {
            return;
        }
        if (arrayList.size() > 0) {
            boolean bl = false;
            int n = InventoryUtil.findHotbarBlock(ItemEndCrystal.class);
            int n2 = CrystalAura.mc.player.inventory.currentItem;
            EnumHand enumHand = null;
            int n3 = this.getCrystalCount(false);
            this.alreadyAttacking = false;
            if (CrystalAura.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
                if (CrystalAura.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && (this.autoSwitch.getValue() == AautoSwitch.Allways || this.autoSwitch.getValue() == AautoSwitch.NoGap)) {
                    if (this.autoSwitch.getValue() == AautoSwitch.NoGap && CrystalAura.mc.player.getHeldItemMainhand().getItem() == Items.GOLDEN_APPLE) {
                        return;
                    }
                    if (this.findCrystalsHotbar() == -1) {
                        return;
                    }
                    CrystalAura.mc.player.inventory.currentItem = this.findCrystalsHotbar();
                    CrystalAura.mc.playerController.syncCurrentPlayItem();
                }
            } else {
                bl = true;
            }
            if (this.autoSwitch.getValue() == AautoSwitch.Silent && n != -1) {
                if (CrystalAura.mc.player.isHandActive() && this.silentSwitchHand.getValue().booleanValue()) {
                    enumHand = CrystalAura.mc.player.getActiveHand();
                }
                CrystalAura.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
            }
            this.placeDelayCounter = 0;
            this.facePlaceDelayCounter = 0;
            this.didAnything = true;
            for (BlockPos blockPos : arrayList) {
                if (CrystalAura.mc.player.getHeldItemMainhand().getItem() instanceof ItemEndCrystal || CrystalAura.mc.player.getHeldItemOffhand().getItem() instanceof ItemEndCrystal || this.autoSwitch.getValue() == AautoSwitch.Silent) {
                    if (!this.setYawPitch(blockPos)) continue;
                    EntityEnderCrystal entityEnderCrystal = CrystalUtilCa.isCrystalStuck(blockPos.up());
                    if (entityEnderCrystal != null && this.antiStuck.getValue().booleanValue()) {
                        this.stuckCrystal = entityEnderCrystal;
                        if (this.debug.getValue().booleanValue()) {
                            Command.sendMessage("SHITS STUCK");
                        }
                    }
                    this.rotateToPos(blockPos);
                    BlockUtilCa.placeCrystalOnBlock(blockPos, bl ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, this.placeSwing.getValue());
                    if (this.debug.getValue().booleanValue()) {
                        Command.sendMessage("placing");
                    }
                    ++this.crystalsPlaced;
                    continue;
                }
                if (!this.debug.getValue().booleanValue()) continue;
                Command.sendMessage("doing yawstep on place");
            }
            int n4 = this.getCrystalCount(bl);
            if (this.autoSwitch.getValue() == AautoSwitch.Silent && n != -1) {
                CrystalAura.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n2));
                if (this.silentSwitchHand.getValue().booleanValue() && enumHand != null) {
                    CrystalAura.mc.player.setActiveHand(enumHand);
                }
            }
            if (n4 == n3) {
                this.didAnything = false;
            }
        }
    }

    private double isCrystalGood(@NotNull EntityEnderCrystal entityEnderCrystal, @NotNull EntityPlayer entityPlayer) {
        if (this.isPlayerValid(entityPlayer)) {
            if (CrystalAura.mc.player.canEntityBeSeen((Entity)entityEnderCrystal) ? CrystalAura.mc.player.getDistanceSqToEntity((Entity)entityEnderCrystal) > MathsUtilCa.square(this.breakRange.getValue().floatValue()) : CrystalAura.mc.player.getDistanceSqToEntity((Entity)entityEnderCrystal) > MathsUtilCa.square(this.breakRangeWall.getValue().floatValue())) {
                return 0.0;
            }
            if (entityEnderCrystal.isDead) {
                return 0.0;
            }
            if (this.attemptedCrystals.contains((Object)entityEnderCrystal)) {
                return 0.0;
            }
            double d = CrystalUtilCa.calculateDamage((Entity)entityEnderCrystal, (Entity)entityPlayer, (boolean)this.ignoreTerrain.getValue());
            this.facePlacing = false;
            double d2 = this.minBreak.getValue().intValue();
            if ((EntityUtilCa.getHealth((Entity)entityPlayer) <= (float)this.facePlaceHP.getValue().intValue() || CrystalUtilCa.getArmourFucker(entityPlayer, this.fuckArmourHP.getValue().intValue()).booleanValue() || this.fpbind.getValue().isDown()) && d < (double)this.minBreak.getValue().intValue()) {
                d2 = EntityUtilCa.isInHole((Entity)entityPlayer) ? 1.0 : 2.0;
                this.facePlacing = true;
            }
            if (d < d2 && (double)EntityUtilCa.getHealth((Entity)entityPlayer) - d > 0.0) {
                return 0.0;
            }
            double d3 = 0.0;
            if (!this.ignoreSelfDamage.getValue().booleanValue()) {
                d3 = CrystalUtilCa.calculateDamage((Entity)entityEnderCrystal, (Entity)CrystalAura.mc.player, (boolean)this.ignoreTerrain.getValue());
            }
            if (d3 > (double)this.maxSelfBreak.getValue().intValue()) {
                return 0.0;
            }
            if ((double)EntityUtilCa.getHealth((Entity)CrystalAura.mc.player) - d3 <= 0.0 && this.antiSuicide.getValue().booleanValue()) {
                return 0.0;
            }
            switch (this.crystalLogic.getValue()) {
                case Safe: {
                    return d - d3;
                }
                case Damage: {
                    return d;
                }
                case Nearby: {
                    double d4 = CrystalAura.mc.player.getDistanceSqToEntity((Entity)entityEnderCrystal);
                    return d - d4;
                }
            }
        }
        return 0.0;
    }

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        if (this.renderMap.isEmpty()) {
            return;
        }
        boolean bl = false;
        boolean bl2 = false;
        switch (this.mode.getValue()) {
            case Pretty: {
                bl = true;
                bl2 = true;
                break;
            }
            case Solid: {
                bl = false;
                bl2 = true;
                break;
            }
            case Outline: {
                bl = true;
                bl2 = false;
            }
        }
        ArrayList<RenderPos> arrayList = new ArrayList<RenderPos>();
        for (RenderPos renderPos : this.renderMap) {
            int n = this.renderFillColouralpha.getValue();
            int n2 = this.renderBoxColouralpha.getValue();
            if (this.currentTargets.contains((Object)renderPos.pos)) {
                renderPos.fadeTimer = 0.0;
            } else if (!this.fade.getValue().booleanValue()) {
                arrayList.add(renderPos);
            } else {
                renderPos.fadeTimer += 1.0;
                n = (int)((double)n - (double)n * (renderPos.fadeTimer / (double)this.fadeTime.getValue().intValue()));
                n2 = (int)((double)n2 - (double)n2 * (renderPos.fadeTimer / (double)this.fadeTime.getValue().intValue()));
            }
            if (renderPos.fadeTimer > (double)this.fadeTime.getValue().intValue()) {
                arrayList.add(renderPos);
            }
            if (arrayList.contains(renderPos)) continue;
            RenderUtilCa.drawBoxESP(this.flat.getValue() != false ? new BlockPos(renderPos.pos.getX(), renderPos.pos.getY() + 1, renderPos.pos.getZ()) : renderPos.pos, new Colour(this.renderFillColourred.getValue(), this.renderFillColourgreen.getValue(), this.renderFillColourblue.getValue(), Math.max(n, 0)), new Colour(this.renderBoxColourred.getValue(), this.renderBoxColourgreen.getValue(), this.renderBoxColourblue.getValue(), Math.max(n2, 0)), this.width.getValue().intValue(), bl, bl2, true, this.flat.getValue() != false ? this.height.getValue() : 0.0, false, false, false, false, 0);
        }
        this.renderMap.removeAll(arrayList);
    }

    private int findCrystalsHotbar() {
        for (int i = 0; i < 9; ++i) {
            if (CrystalAura.mc.player.inventory.getStackInSlot(i).getItem() != Items.END_CRYSTAL) continue;
            return i;
        }
        return -1;
    }

    private /* synthetic */ void lambda$getBestBlocks$9(ArrayList arrayList, ArrayList arrayList2, int n) {
        RenderPos renderPos = (RenderPos)arrayList.get(n);
        if (this.when.getValue() == Awhen.Both || this.when.getValue() == Awhen.Place) {
            this.clearMap(renderPos.pos);
            if (renderPos.pos != null) {
                this.renderMap.add(renderPos);
            }
        }
        arrayList2.add(renderPos.pos);
    }

    @Override
    public String getDisplayInfo() {
        switch (this.arrayListMode.getValue()) {
            case Latency: {
                return String.valueOf(new StringBuilder().append(this.crystalLatency).append("ms"));
            }
            case CPS: {
                return String.valueOf(new StringBuilder().append("").append(MathsUtilCa.round(this.getCPS(), 2)));
            }
            case Player: {
                return this.ezTarget != null ? this.ezTarget.getName() : null;
            }
        }
        return "";
    }

    private boolean isPlayerValid(@NotNull EntityPlayer entityPlayer) {
        if (entityPlayer.getHealth() + entityPlayer.getAbsorptionAmount() <= 0.0f || entityPlayer == CrystalAura.mc.player) {
            return false;
        }
        if (AliceMain.friendManager.isFriend(entityPlayer.getName())) {
            return false;
        }
        if (entityPlayer.getName().equals(CrystalAura.mc.player.getName())) {
            return false;
        }
        if (entityPlayer.getDistanceSqToEntity((Entity)CrystalAura.mc.player) > 169.0) {
            return false;
        }
        if (this.palceObiFeet.getValue().booleanValue() && this.obiFeetCounter >= this.timeoutTicksObiFeet.getValue() && CrystalAura.mc.player.getDistanceToEntity((Entity)entityPlayer) < 5.0f) {
            try {
                this.blockObiNextToPlayer(entityPlayer);
            }
            catch (ConcurrentModificationException concurrentModificationException) {
                concurrentModificationException.printStackTrace();
            }
        }
        return true;
    }

    public EntityEnderCrystal getBestCrystal() {
        double d = 0.0;
        EntityEnderCrystal entityEnderCrystal = null;
        for (Entity entity : CrystalAura.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityEnderCrystal)) continue;
            EntityEnderCrystal entityEnderCrystal2 = (EntityEnderCrystal)entity;
            for (EntityPlayer entityPlayer : new ArrayList(CrystalAura.mc.world.playerEntities)) {
                double d2;
                if (CrystalAura.mc.player.getDistanceSqToEntity((Entity)entityPlayer) > MathsUtilCa.square(this.targetRange.getValue().floatValue())) continue;
                if (this.entityPredict.getValue().booleanValue() && this.rotateMode.getValue() == ArotateMode.Off) {
                    float f = entityPlayer.width / 2.0f;
                    float f2 = entityPlayer.height;
                    entityPlayer.setEntityBoundingBox(new AxisAlignedBB(entityPlayer.posX - (double)f, entityPlayer.posY, entityPlayer.posZ - (double)f, entityPlayer.posX + (double)f, entityPlayer.posY + (double)f2, entityPlayer.posZ + (double)f));
                    Entity entity2 = CrystalUtilCa.getPredictedPosition((Entity)entityPlayer, this.predictedTicks.getValue().intValue());
                    entityPlayer.setEntityBoundingBox(entity2.getEntityBoundingBox());
                }
                if ((d2 = this.isCrystalGood(entityEnderCrystal2, entityPlayer)) <= 0.0 || !(d2 > d)) continue;
                d = d2;
                this.ezTarget = entityPlayer;
                entityEnderCrystal = entityEnderCrystal2;
            }
        }
        if (this.ezTarget != null) {
            // empty if block
        }
        if (entityEnderCrystal != null && this.when.getValue() == Awhen.Both || this.when.getValue() == Awhen.Break) {
            BlockPos blockPos = entityEnderCrystal.getPosition().down();
            this.clearMap(blockPos);
            this.renderMap.add(new RenderPos(blockPos, d));
        }
        return entityEnderCrystal;
    }

    private ArrayList<BlockPos> getBlockedPositions(BlockPos blockPos) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        arrayList.add(blockPos.add(1, -1, 1));
        arrayList.add(blockPos.add(1, -1, -1));
        arrayList.add(blockPos.add(-1, -1, 1));
        arrayList.add(blockPos.add(-1, -1, -1));
        arrayList.add(blockPos.add(-1, -1, 0));
        arrayList.add(blockPos.add(1, -1, 0));
        arrayList.add(blockPos.add(0, -1, -1));
        arrayList.add(blockPos.add(0, -1, 1));
        arrayList.add(blockPos.add(1, 0, 1));
        arrayList.add(blockPos.add(1, 0, -1));
        arrayList.add(blockPos.add(-1, 0, 1));
        arrayList.add(blockPos.add(-1, 0, -1));
        arrayList.add(blockPos.add(-1, 0, 0));
        arrayList.add(blockPos.add(1, 0, 0));
        arrayList.add(blockPos.add(0, 0, -1));
        arrayList.add(blockPos.add(0, 0, 1));
        arrayList.add(blockPos.add(1, 1, 1));
        arrayList.add(blockPos.add(1, 1, -1));
        arrayList.add(blockPos.add(-1, 1, 1));
        arrayList.add(blockPos.add(-1, 1, -1));
        arrayList.add(blockPos.add(-1, 1, 0));
        arrayList.add(blockPos.add(1, 1, 0));
        arrayList.add(blockPos.add(0, 1, -1));
        arrayList.add(blockPos.add(0, 1, 1));
        return arrayList;
    }

    private void clearMap(BlockPos blockPos) {
        ArrayList<RenderPos> arrayList = new ArrayList<RenderPos>();
        if (blockPos == null || this.renderMap.isEmpty()) {
            return;
        }
        for (RenderPos renderPos : this.renderMap) {
            if (renderPos.pos.getX() != blockPos.getX() || renderPos.pos.getY() != blockPos.getY() || renderPos.pos.getZ() != blockPos.getZ()) continue;
            arrayList.add(renderPos);
        }
        this.renderMap.removeAll(arrayList);
    }

    @Override
    public void onUpdate() {
        if (this.rotateMode.getValue() == ArotateMode.Off) {
            this.doCrystalAura();
        }
    }

    private boolean setYawPitch(@NotNull EntityEnderCrystal entityEnderCrystal) {
        if (this.rotateMode.getValue() == ArotateMode.Off || this.rotateMode.getValue() == ArotateMode.Place) {
            return true;
        }
        float[] arrf = MathsUtilCa.calcAngle(CrystalAura.mc.player.getPositionEyes(mc.getRenderPartialTicks()), entityEnderCrystal.getPositionEyes(mc.getRenderPartialTicks()));
        float f = arrf[0];
        float f2 = arrf[1];
        float f3 = AliceMain.newrotationManager.getSpoofedYaw();
        if (Math.abs(f3 - f) > (float)this.maxYaw.getValue().intValue() && Math.abs(f3 - 360.0f - f) > (float)this.maxYaw.getValue().intValue() && Math.abs(f3 + 360.0f - f) > (float)this.maxYaw.getValue().intValue()) {
            AliceMain.newrotationManager.setPlayerRotations(Math.abs(f3 - f) < 180.0f ? (f3 > f ? f3 - (float)this.maxYaw.getValue().intValue() : f3 + (float)this.maxYaw.getValue().intValue()) : (f3 > f ? f3 + (float)this.maxYaw.getValue().intValue() : f3 - (float)this.maxYaw.getValue().intValue()), f2);
            return false;
        }
        AliceMain.newrotationManager.setPlayerRotations(f, f2);
        return true;
    }

    public CrystalAura() {
        super("CrystalAura", "best ca i think bro.", Module.Category.COMBAT, true, false, false);
        this.breakRange = this.register(new Setting<Double>("Break Range", 5.0, 0.0, 6.0));
        this.placeRange = this.register(new Setting<Double>("Place Range", 5.0, 0.0, 6.0));
        this.breakRangeWall = this.register(new Setting<Double>("Break Range Wall", 3.0, 0.0, 6.0));
        this.placeRangeWall = this.register(new Setting<Double>("Place Range Wall", 3.0, 0.0, 6.0));
        this.targetRange = this.register(new Setting<Double>("Target Range", 15.0, 0.0, 20.0));
        this.placeDelay = this.register(new Setting<Integer>("Place Delay", 0, 0, 10));
        this.breakDelay = this.register(new Setting<Integer>("Break Delay", 0, 0, 10));
        this.sortBlocks = this.register(new Setting<Boolean>("Sort Blocks", true));
        this.ignoreSelfDamage = this.register(new Setting<Boolean>("Ignore Self", false));
        this.minPlace = this.register(new Setting<Integer>("MinPlace", 9, 0, 36));
        this.maxSelfPlace = this.register(new Setting<Integer>("MaxSelfPlace", Integer.valueOf(5), Integer.valueOf(0), Integer.valueOf(36), n -> this.ignoreSelfDamage.getValue() == false));
        this.minBreak = this.register(new Setting<Integer>("MinBreak", 9, 0, 36));
        this.maxSelfBreak = this.register(new Setting<Integer>("MaxSelfBreak", Integer.valueOf(5), Integer.valueOf(0), Integer.valueOf(36), n -> this.ignoreSelfDamage.getValue() == false));
        this.antiSuicide = this.register(new Setting<Boolean>("Anti Suicide", true));
        this.rotate = this.register(new Setting<Rotate>("ARotate", Rotate.OFF));
        this.rotations = this.register(new Setting<Integer>("RotationSpoofs", 1, 1, 20));
        this.rotateMode = this.register(new Setting<ArotateMode>("Rotate", ArotateMode.Off));
        this.maxYaw = this.register(new Setting<Integer>("MaxYaw", 45, 0, 180));
        this.raytrace = this.register(new Setting<Boolean>("Raytrace", false));
        this.fastMode = this.register(new Setting<AfastMode>("Fast", AfastMode.Ignore));
        this.autoSwitch = this.register(new Setting<AautoSwitch>("Switch", AautoSwitch.None));
        this.silentSwitchHand = this.register(new Setting<Boolean>("Hand Activation", Boolean.valueOf(true), bl -> this.autoSwitch.getValue() == AautoSwitch.Silent));
        this.antiWeakness = this.register(new Setting<Boolean>("Anti Weakness", true));
        this.maxCrystals = this.register(new Setting<Integer>("MaxCrystal", 1, 1, 4));
        this.ignoreTerrain = this.register(new Setting<Boolean>("Terrain Trace", true));
        this.crystalLogic = this.register(new Setting<AcrystalLogic>("Placements", AcrystalLogic.Damage));
        this.thirteen = this.register(new Setting<Boolean>("1.13", false));
        this.attackPacket = this.register(new Setting<Boolean>("AttackPacket", true));
        this.packetSafe = this.register(new Setting<Boolean>("Packet Safe", true));
        this.noBreakCalcs = this.register(new Setting<Boolean>("No Break Calcs", false));
        this.arrayListMode = this.register(new Setting<AarrayListMode>("Array List Mode", AarrayListMode.Latency));
        this.debug = this.register(new Setting<Boolean>("Debug", false));
        this.threaded = this.register(new Setting<Boolean>("Threaded", false));
        this.antiStuck = this.register(new Setting<Boolean>("Anti Stuck", false));
        this.maxAntiStuckDamage = this.register(new Setting<Integer>("Stuck Self Damage", Integer.valueOf(8), Integer.valueOf(0), Integer.valueOf(36), n -> this.antiStuck.getValue()));
        this.predictCrystal = this.register(new Setting<Boolean>("Predict Crystal", true));
        this.predictBlock = this.register(new Setting<Boolean>("Predict Block", true));
        this.predictTeleport = this.register(new Setting<ApredictTeleport>("P Teleport", ApredictTeleport.Sound));
        this.entityPredict = this.register(new Setting<Boolean>("Entity Predict", Boolean.valueOf(true), bl -> this.rotateMode.getValue() == ArotateMode.Off));
        this.predictedTicks = this.register(new Setting<Integer>("Predict Ticks", Integer.valueOf(2), Integer.valueOf(0), Integer.valueOf(5), n -> this.entityPredict.getValue() != false && this.rotateMode.getValue() == ArotateMode.Off));
        this.palceObiFeet = this.register(new Setting<Boolean>("Obifeet Enabled", false));
        this.ObiYCheck = this.register(new Setting<Boolean>("Obifeet YCheck", false));
        this.rotateObiFeet = this.register(new Setting<Boolean>("Obifeet Rotate", false));
        this.timeoutTicksObiFeet = this.register(new Setting<Integer>("Timeout", 3, 0, 5));
        this.noMP = this.register(new Setting<Boolean>("NoMultiPlace", false));
        this.facePlaceHP = this.register(new Setting<Integer>("FacePlaceHP", 30, 0, 36));
        this.facePlaceDelay = this.register(new Setting<Integer>("TFacePlaceDelay", 0, 0, 10));
        this.fpbind = this.register(new Setting<Bind>("FacePlace Bind", new Bind(-1)));
        this.fuckArmourHP = this.register(new Setting<Integer>("Armour%", 0, 0, 100));
        this.when = this.register(new Setting<Awhen>("When", Awhen.Place));
        this.mode = this.register(new Setting<Amode>("Mode", Amode.Pretty));
        this.fade = this.register(new Setting<Boolean>("Fade", false));
        this.fadeTime = this.register(new Setting<Integer>("FadeTime", Integer.valueOf(200), Integer.valueOf(0), Integer.valueOf(1000), n -> this.fade.getValue()));
        this.flat = this.register(new Setting<Boolean>("Flat", false));
        this.height = this.register(new Setting<Double>("FlatHeight", Double.valueOf(0.2), Double.valueOf(-2.0), Double.valueOf(2.0), d -> this.flat.getValue()));
        this.width = this.register(new Setting<Integer>("Width", 1, 1, 10));
        this.renderFillColourred = this.register(new Setting<Integer>("FillColour Red", 0, 0, 255));
        this.renderFillColourgreen = this.register(new Setting<Integer>("FillColour Green", 0, 0, 255));
        this.renderFillColourblue = this.register(new Setting<Integer>("FillColour Blue", 0, 0, 255));
        this.renderFillColouralpha = this.register(new Setting<Integer>("FillColour Alpha", 255, 0, 255));
        this.renderBoxColourred = this.register(new Setting<Integer>("BoxColour Red", 255, 0, 255));
        this.renderBoxColourgreen = this.register(new Setting<Integer>("BoxColour Green", 255, 0, 255));
        this.renderBoxColourblue = this.register(new Setting<Integer>("BoxColour Blue", 255, 0, 255));
        this.renderBoxColouralpha = this.register(new Setting<Integer>("BoxColour Alpha", 255, 0, 255));
        this.renderDamage = this.register(new Setting<Boolean>("RenderDamage", true));
        this.swing = this.register(new Setting<Aswing>("Swing", Aswing.Mainhand));
        this.placeSwing = this.register(new Setting<Boolean>("Place Swing", true));
        this.attemptedCrystals = new ArrayList<EntityEnderCrystal>();
        this.renderMap = new ArrayList();
        this.currentTargets = new ArrayList();
        this.crystalsPlacedTimer = new TimerCa();
        this.yaw = 0.0f;
        this.pitch = 0.0f;
        this.rotating = false;
        INSTANCE = this;
    }

    public static enum Rotate {
        OFF,
        PLACE,
        BREAK,
        ALL;

    }

    public static enum AfastMode {
        Off,
        Ignore,
        Ghost,
        Sound;

    }

    public static enum Amode {
        Pretty,
        Solid,
        Outline;

    }

    public static enum AcrystalLogic {
        Damage,
        Nearby,
        Safe;

    }

    public static enum AautoSwitch {
        Allways,
        NoGap,
        None,
        Silent;

    }

    static class DamageComparator
    implements Comparator<RenderPos> {
        @Override
        public int compare(RenderPos renderPos, RenderPos renderPos2) {
            return renderPos2.damage.compareTo(renderPos.damage);
        }

        DamageComparator() {
        }
    }

    public static enum AarrayListMode {
        Latency,
        Player,
        CPS;

    }

    public static enum ArotateMode {
        Off,
        Break,
        Place,
        Both;

    }

    static class RenderPos {
        /* synthetic */ Double damage;
        /* synthetic */ double fadeTimer;
        /* synthetic */ BlockPos pos;

        public RenderPos(BlockPos blockPos, Double d) {
            this.pos = blockPos;
            this.damage = d;
        }
    }

    public static enum Aswing {
        Mainhand,
        Offhand,
        None;

    }

    public static enum Awhen {
        Place,
        Break,
        Both,
        Never;

    }

    public static enum ApredictTeleport {
        Sound,
        Packet,
        None;

    }
}

