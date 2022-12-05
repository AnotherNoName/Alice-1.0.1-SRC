//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockDirectional
 *  net.minecraft.block.BlockObsidian
 *  net.minecraft.block.BlockPistonBase
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.network.play.server.SPacketBlockChange
 *  net.minecraft.network.play.server.SPacketSoundEffect
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.hidden;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.manager.RotationManager;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.InteractionUtil;
import me.snow.aclient.util.Timer;
import me.snow.aclient.util.ca.sc.BlockUtilSC;
import me.snow.aclient.util.ca.sc.CrystalUtilSC;
import me.snow.aclient.util.ca.sc.RotationUtilSC;
import me.snow.aclient.util.ca.sc.SilentRotaionUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PistonAuraTest
extends Module {
    public /* synthetic */ BlockPos facePos;
    private /* synthetic */ boolean skipPiston;
    private /* synthetic */ Timer torchTimer;
    private /* synthetic */ Setting<Boolean> triggerable;
    private /* synthetic */ Setting<Boolean> raytrace;
    private /* synthetic */ Setting<Integer> actionInterval;
    /* synthetic */ boolean sendPacket;
    private /* synthetic */ Timer brdel;
    private /* synthetic */ Setting<Boolean> smart;
    private /* synthetic */ Setting<rMode> rmode;
    private /* synthetic */ Setting<Boolean> mine;
    private /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ Timer renderTimer;
    private /* synthetic */ Setting<Integer> actionShift;
    private /* synthetic */ BlockPos torchPos;
    private /* synthetic */ BlockPos placedPiston;
    private /* synthetic */ Setting<Boolean> antiSuicide;
    private /* synthetic */ Setting<Boolean> strict;
    private /* synthetic */ int delayTime;
    private /* synthetic */ Stage stage;
    private /* synthetic */ Setting<Boolean> autotrap;
    private /* synthetic */ int tickCounter;
    public /* synthetic */ BlockPos crystalPos;
    public /* synthetic */ EnumFacing pistonOffset;
    private /* synthetic */ Runnable postAction;
    private /* synthetic */ Setting<Integer> breakDelay;
    private /* synthetic */ Setting<Boolean> disableWhenNone;
    public /* synthetic */ EnumFacing faceOffset;
    private /* synthetic */ Setting<Integer> targetRange;
    public /* synthetic */ BlockPos pistonNeighbour;
    private /* synthetic */ Timer actionTimer;
    private static /* synthetic */ PistonAuraTest INSTANCE;
    /* synthetic */ boolean firstPacket;
    private /* synthetic */ Timer placedPistonTimer;
    private /* synthetic */ Timer delayTimer;
    private /* synthetic */ boolean playertrapped;

    @Override
    public void onEnable() {
        if (PistonAuraTest.mc.player == null || PistonAuraTest.mc.world == null) {
            return;
        }
        this.stage = Stage.SEARCHING;
        this.facePos = null;
        this.faceOffset = null;
        this.crystalPos = null;
        this.pistonNeighbour = null;
        this.pistonOffset = null;
        this.delayTime = 0;
        this.tickCounter = 0;
        this.postAction = null;
        this.torchPos = null;
        this.skipPiston = false;
        this.placedPiston = null;
    }

    public void repeattrap(Entity entity) {
        InteractionUtil.Placement placement;
        PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.getobby()));
        if (PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX + 1.0, entity.posY + 2.0, entity.posZ)).getBlock().equals((Object)Blocks.OBSIDIAN) || PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX + 1.0, entity.posY + 2.0, entity.posZ)).getBlock().equals((Object)Blocks.BEDROCK)) {
            placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ), true, true);
            InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
        }
        if (PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX - 1.0, entity.posY + 2.0, entity.posZ)).getBlock().equals((Object)Blocks.OBSIDIAN) || PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX - 1.0, entity.posY + 2.0, entity.posZ)).getBlock().equals((Object)Blocks.BEDROCK)) {
            placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ), true, true);
            InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
        }
        if (PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ + 1.0)).getBlock().equals((Object)Blocks.OBSIDIAN) || PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ + 1.0)).getBlock().equals((Object)Blocks.BEDROCK)) {
            placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ), true, true);
            InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
        }
        if (PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ - 1.0)).getBlock().equals((Object)Blocks.OBSIDIAN) || PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ - 1.0)).getBlock().equals((Object)Blocks.BEDROCK)) {
            placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ), true, true);
            InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
        }
    }

    public void traptarget(Entity entity) {
        if (this.autotrap.getValue().booleanValue() && PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ)).getBlock().equals((Object)Blocks.AIR)) {
            PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.getobby()));
            if (PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX + 1.0, entity.posY + 2.0, entity.posZ)).getBlock().equals((Object)Blocks.OBSIDIAN) || PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX + 1.0, entity.posY + 2.0, entity.posZ)).getBlock().equals((Object)Blocks.BEDROCK)) {
                InteractionUtil.Placement placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ), true, true);
                InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
                return;
            }
            if (PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX - 1.0, entity.posY + 2.0, entity.posZ)).getBlock().equals((Object)Blocks.OBSIDIAN) || PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX - 1.0, entity.posY + 2.0, entity.posZ)).getBlock().equals((Object)Blocks.BEDROCK)) {
                InteractionUtil.Placement placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ), true, true);
                InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
                return;
            }
            if (PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ + 1.0)).getBlock().equals((Object)Blocks.OBSIDIAN) || PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ + 1.0)).getBlock().equals((Object)Blocks.BEDROCK)) {
                InteractionUtil.Placement placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ), true, true);
                InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
                return;
            }
            if (PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ - 1.0)).getBlock().equals((Object)Blocks.OBSIDIAN) || PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ - 1.0)).getBlock().equals((Object)Blocks.BEDROCK)) {
                InteractionUtil.Placement placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ), true, true);
                InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
                return;
            }
            if (PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX + 1.0, entity.posY + 1.0, entity.posZ)).getBlock().equals((Object)Blocks.OBSIDIAN) || PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX + 1.0, entity.posY + 1.0, entity.posZ)).getBlock().equals((Object)Blocks.BEDROCK)) {
                InteractionUtil.Placement placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX + 1.0, entity.posY + 2.0, entity.posZ), true, true);
                InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
                this.repeattrap(entity);
                return;
            }
            if (PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX - 1.0, entity.posY + 1.0, entity.posZ)).getBlock().equals((Object)Blocks.OBSIDIAN) || PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX - 1.0, entity.posY + 1.0, entity.posZ)).getBlock().equals((Object)Blocks.BEDROCK)) {
                InteractionUtil.Placement placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX - 1.0, entity.posY + 2.0, entity.posZ), true, true);
                InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
                this.repeattrap(entity);
                return;
            }
            if (PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 1.0, entity.posZ + 1.0)).getBlock().equals((Object)Blocks.OBSIDIAN) || PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 1.0, entity.posZ + 1.0)).getBlock().equals((Object)Blocks.BEDROCK)) {
                InteractionUtil.Placement placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ + 1.0), true, true);
                InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
                this.repeattrap(entity);
                return;
            }
            if (PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 1.0, entity.posZ - 1.0)).getBlock().equals((Object)Blocks.OBSIDIAN) || PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 1.0, entity.posZ - 1.0)).getBlock().equals((Object)Blocks.BEDROCK)) {
                InteractionUtil.Placement placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ - 1.0), true, true);
                InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
                this.repeattrap(entity);
                return;
            }
            if (this.checkatomizer(new BlockPos(entity.posX + 1.0, entity.posY + 1.0, entity.posZ))) {
                InteractionUtil.Placement placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX + 1.0, entity.posY + 1.0, entity.posZ), true, true);
                InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
                this.traptarget2(entity);
                return;
            }
            if (this.checkatomizer(new BlockPos(entity.posX - 1.0, entity.posY + 1.0, entity.posZ))) {
                InteractionUtil.Placement placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX - 1.0, entity.posY + 1.0, entity.posZ), true, true);
                InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
                this.traptarget2(entity);
                return;
            }
            if (this.checkatomizer(new BlockPos(entity.posX, entity.posY + 1.0, entity.posZ + 1.0))) {
                InteractionUtil.Placement placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX, entity.posY + 1.0, entity.posZ + 1.0), true, true);
                InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
                this.traptarget2(entity);
                return;
            }
            if (this.checkatomizer(new BlockPos(entity.posX, entity.posY + 1.0, entity.posZ - 1.0))) {
                InteractionUtil.Placement placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX, entity.posY + 1.0, entity.posZ - 1.0), true, true);
                InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
                this.traptarget2(entity);
                return;
            }
        }
    }

    private void handleAction(boolean bl) {
        block51: {
            block50: {
                if (this.actionTimer.passedMs(1000L) && this.disableWhenNone.getValue().booleanValue()) {
                    this.toggle();
                }
                if (!this.delayTimer.passedMs(this.delayTime)) {
                    return;
                }
                if (this.strict.getValue().booleanValue() && Math.sqrt(PistonAuraTest.mc.player.motionX * PistonAuraTest.mc.player.motionX + PistonAuraTest.mc.player.motionZ * PistonAuraTest.mc.player.motionZ) > 9.0E-4) {
                    return;
                }
                if (this.mode.getValue() != Mode.DAMAGE) break block50;
                switch (this.stage) {
                    case SEARCHING: {
                        List<EntityPlayer> list = this.getTargets();
                        for (EntityPlayer entityPlayer : list) {
                            if (!this.evaluateTarget(entityPlayer)) continue;
                            if (PistonAuraTest.mc.world.getBlockState(new BlockPos(entityPlayer.posX, entityPlayer.posY + 2.0, entityPlayer.posZ)).getBlock().equals((Object)Blocks.AIR) && this.autotrap.getValue().booleanValue()) {
                                this.traptarget((Entity)entityPlayer);
                                return;
                            }
                            int n = PistonAuraTest.getPistonSlot();
                            if (n == -1) {
                                Command.sendMessage("No pistons found!");
                                this.toggle();
                                return;
                            }
                            if (this.skipPiston) {
                                this.stage = Stage.CRYSTAL;
                                this.skipPiston = false;
                                return;
                            }
                            boolean bl2 = PistonAuraTest.mc.player.inventory.currentItem != n;
                            boolean bl3 = PistonAuraTest.mc.player.isSprinting();
                            boolean bl4 = BlockUtilSC.shouldSneakWhileRightClicking(this.pistonNeighbour);
                            Vec3d vec3d = new Vec3d((Vec3i)this.pistonNeighbour).addVector(0.5, 0.5, 0.5).add(new Vec3d(this.pistonOffset.getDirectionVec()).scale(0.5));
                            if (bl) {
                                float[] arrf = RotationManager.calculateAngle(PistonAuraTest.mc.player.getPositionEyes(mc.getRenderPartialTicks()), vec3d);
                                RotationUtilSC.update(arrf[0], arrf[1]);
                            } else {
                                float[] arrf;
                                if (this.rmode.getValue() == rMode.Oyvey) {
                                    AliceMain.rotationManager.lookAtVec3d(vec3d);
                                }
                                if (this.rmode.getValue() == rMode.Test) {
                                    arrf = RotationManager.calculateAngle(PistonAuraTest.mc.player.getPositionEyes(mc.getRenderPartialTicks()), vec3d);
                                    RotationUtilSC.update(arrf[0], arrf[1]);
                                }
                                if (this.rmode.getValue() == rMode.Silent) {
                                    arrf = RotationManager.calculateAngle(PistonAuraTest.mc.player.getPositionEyes(mc.getRenderPartialTicks()), vec3d);
                                    SilentRotaionUtil.lookAtAngles(arrf[0], arrf[1]);
                                }
                                if (this.rmode.getValue() == rMode.Silent2) {
                                    arrf = RotationManager.calculateAngle(PistonAuraTest.mc.player.getPositionEyes(mc.getRenderPartialTicks()), vec3d);
                                    SilentRotaionUtil.update(arrf[0], arrf[1]);
                                }
                                if (this.rmode.getValue() == rMode.Client) {
                                    arrf = RotationManager.calculateAngle(PistonAuraTest.mc.player.getPositionEyes(mc.getRenderPartialTicks()), vec3d);
                                    SilentRotaionUtil.lookAtAngles(arrf[0], arrf[1]);
                                    PistonAuraTest.mc.player.rotationYaw = arrf[0];
                                    PistonAuraTest.mc.player.rotationPitch = arrf[1];
                                }
                            }
                            this.postAction = () -> {
                                this.renderTimer.reset();
                                if (bl2) {
                                    PistonAuraTest.mc.player.inventory.currentItem = n;
                                    PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
                                }
                                if (bl3) {
                                    PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PistonAuraTest.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
                                }
                                if (bl4) {
                                    PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PistonAuraTest.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                                }
                                PistonAuraTest.mc.playerController.processRightClickBlock(PistonAuraTest.mc.player, PistonAuraTest.mc.world, this.pistonNeighbour, this.pistonOffset, vec3d, EnumHand.MAIN_HAND);
                                PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
                                if (bl4) {
                                    PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PistonAuraTest.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                                }
                                if (bl3) {
                                    PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PistonAuraTest.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                                }
                                this.stage = Stage.CRYSTAL;
                            };
                            return;
                        }
                        break block51;
                    }
                    case CRYSTAL: {
                        if (this.torchPos != null && PistonAuraTest.mc.world.getBlockState(this.torchPos).getBlock() == Blocks.AIR) {
                            this.torchPos = null;
                        }
                        if (this.torchPos != null) {
                            if (this.torchTimer.passedMs(1000L)) {
                                RayTraceResult rayTraceResult = PistonAuraTest.mc.world.rayTraceBlocks(new Vec3d(PistonAuraTest.mc.player.posX, PistonAuraTest.mc.player.posY + (double)PistonAuraTest.mc.player.getEyeHeight(), PistonAuraTest.mc.player.posZ), new Vec3d((double)this.torchPos.getX() + 0.5, (double)this.torchPos.getY() + 0.5, (double)this.torchPos.getZ() + 0.5));
                                EnumFacing enumFacing = rayTraceResult == null || rayTraceResult.sideHit == null ? EnumFacing.UP : rayTraceResult.sideHit;
                                Vec3d vec3d = new Vec3d((Vec3i)this.torchPos).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
                                if (bl) {
                                    float[] arrf = RotationManager.calculateAngle(PistonAuraTest.mc.player.getPositionEyes(mc.getRenderPartialTicks()), vec3d);
                                    RotationUtilSC.update(arrf[0], arrf[1]);
                                } else {
                                    AliceMain.rotationManager.lookAtVec3d(vec3d);
                                }
                                this.postAction = () -> {
                                    PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.torchPos, enumFacing));
                                    PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.torchPos, enumFacing));
                                    this.torchTimer.reset();
                                };
                            }
                            return;
                        }
                        if (!this.isOffhand()) {
                            int n = CrystalUtilSC.getCrystalSlot();
                            if (n == -1) {
                                Command.sendMessage("No crystals found!");
                                this.toggle();
                                return;
                            }
                            if (PistonAuraTest.mc.player.inventory.currentItem != n) {
                                PistonAuraTest.mc.player.inventory.currentItem = n;
                                PistonAuraTest.mc.playerController.updateController();
                            }
                        }
                        if (this.crystalPos == null) {
                            this.stage = Stage.SEARCHING;
                            return;
                        }
                        if (bl) {
                            float[] arrf = SilentRotaionUtil.calculateAngle(PistonAuraTest.mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d((double)this.crystalPos.getX() + 0.5, (double)this.crystalPos.getY() + 0.5, (double)this.crystalPos.getZ() + 0.5));
                            SilentRotaionUtil.update(arrf[0], arrf[1]);
                        } else {
                            SilentRotaionUtil.lookAtVector(new Vec3d((double)this.crystalPos.getX() + 0.5, (double)this.crystalPos.getY() + 0.5, (double)this.crystalPos.getZ() + 0.5));
                        }
                        this.postAction = () -> {
                            this.renderTimer.reset();
                            RayTraceResult rayTraceResult = PistonAuraTest.mc.world.rayTraceBlocks(new Vec3d(PistonAuraTest.mc.player.posX, PistonAuraTest.mc.player.posY + (double)PistonAuraTest.mc.player.getEyeHeight(), PistonAuraTest.mc.player.posZ), new Vec3d((double)this.crystalPos.getX() + 0.5, (double)this.crystalPos.getY() - 0.5, (double)this.crystalPos.getZ() + 0.5));
                            EnumFacing enumFacing = rayTraceResult == null || rayTraceResult.sideHit == null ? EnumFacing.UP : rayTraceResult.sideHit;
                            BlockUtilSC.rightClickBlock(this.crystalPos, PistonAuraTest.mc.player.getPositionVector().addVector(0.0, (double)PistonAuraTest.mc.player.getEyeHeight(), 0.0), this.isOffhand() ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, enumFacing, true);
                            this.stage = Stage.REDSTONE;
                            this.torchTimer.reset();
                        };
                        return;
                    }
                    case REDSTONE: {
                        if (this.facePos == null) {
                            this.stage = Stage.SEARCHING;
                            return;
                        }
                        int n = this.getRedstoneBlockSlot();
                        if (n == -1) {
                            Command.sendMessage("No redstone found!");
                            this.toggle();
                            return;
                        }
                        Optional<BlockUtilSC.ClickLocation> optional = BlockUtilSC.generateClickLocation(this.facePos.offset(this.faceOffset, 3), false, ((ItemBlock)PistonAuraTest.mc.player.inventory.getStackInSlot(this.getRedstoneBlockSlot()).getItem()).getBlock() == Blocks.REDSTONE_TORCH);
                        if (!optional.isPresent() && ((ItemBlock)PistonAuraTest.mc.player.inventory.getStackInSlot(this.getRedstoneBlockSlot()).getItem()).getBlock() == Blocks.REDSTONE_TORCH) {
                            for (EnumFacing enumFacing : EnumFacing.HORIZONTALS) {
                                if (!enumFacing.equals((Object)this.faceOffset) && !enumFacing.equals((Object)this.faceOffset.getOpposite()) && (optional = BlockUtilSC.generateClickLocation(this.facePos.offset(this.faceOffset, 2).offset(enumFacing), false, ((ItemBlock)PistonAuraTest.mc.player.inventory.getStackInSlot(this.getRedstoneBlockSlot()).getItem()).getBlock() == Blocks.REDSTONE_TORCH)).isPresent()) break;
                            }
                        }
                        if (optional.isPresent()) {
                            Object object;
                            EnumFacing enumFacing;
                            boolean bl5 = PistonAuraTest.mc.player.inventory.currentItem != n;
                            int n2 = PistonAuraTest.mc.player.isSprinting();
                            int n3 = BlockUtilSC.shouldSneakWhileRightClicking(optional.get().neighbour) ? 1 : 0;
                            enumFacing = new Vec3d((Vec3i)optional.get().neighbour).addVector(0.5, 0.5, 0.5).add(new Vec3d(optional.get().opposite.getDirectionVec()).scale(0.5));
                            if (bl) {
                                object = RotationManager.calculateAngle(PistonAuraTest.mc.player.getPositionEyes(mc.getRenderPartialTicks()), (Vec3d)enumFacing);
                                RotationUtilSC.update((float)object[0], (float)object[1]);
                            } else {
                                AliceMain.rotationManager.lookAtVec3d((Vec3d)enumFacing);
                            }
                            object = optional;
                            this.postAction = () -> this.lambda$handleAction$6(bl5, n, n2 != 0, n3 != 0, (Optional)object, (Vec3d)enumFacing);
                            return;
                        }
                        this.stage = Stage.BREAKING;
                        return;
                    }
                    case BREAKING: {
                        Entity entity2 = PistonAuraTest.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal).filter(entity -> PistonAuraTest.mc.player.getDistanceToEntity(entity) <= (float)(this.targetRange.getValue() + 4)).min(Comparator.comparing(entity -> Float.valueOf(PistonAuraTest.mc.player.getDistanceToEntity(entity)))).orElse(null);
                        if (entity2 != null) {
                            if (this.antiSuicide.getValue().booleanValue() && PistonAuraTest.mc.player.getHealth() < 7.0f) {
                                return;
                            }
                            this.delayTimer.reset();
                            this.renderTimer.reset();
                            this.delayTime = this.breakDelay.getValue() * 10;
                            if (bl) {
                                float[] arrf = RotationManager.calculateAngle(PistonAuraTest.mc.player.getPositionEyes(mc.getRenderPartialTicks()), entity2.getPositionVector());
                                RotationUtilSC.update(arrf[0], arrf[1]);
                            } else {
                                AliceMain.rotationManager.lookAtVec3d(entity2.getPositionVector());
                            }
                            this.postAction = () -> {
                                PistonAuraTest.mc.playerController.attackEntity((EntityPlayer)PistonAuraTest.mc.player, entity2);
                                PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketAnimation(this.isOffhand() ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND));
                            };
                            break;
                        }
                        if (bl) {
                            float[] arrf = RotationManager.calculateAngle(PistonAuraTest.mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d((double)this.facePos.getX() + 0.5, (double)this.facePos.getY(), (double)this.facePos.getZ() + 0.5));
                            RotationUtilSC.update(arrf[0], arrf[1]);
                            break;
                        }
                        AliceMain.rotationManager.lookAtXYZ((double)this.facePos.getX() + 0.5, this.facePos.getY(), (double)this.facePos.getZ() + 0.5);
                    }
                }
                break block51;
            }
            this.stage = Stage.SEARCHING;
            int n = PistonAuraTest.getPistonSlot();
            if (n == -1) {
                Command.sendMessage("No pistons found!");
                this.toggle();
                return;
            }
            int n4 = this.getRedstoneBlockSlot();
            if (n4 == -1) {
                Command.sendMessage("No redstone found!");
                this.toggle();
                return;
            }
            List<EntityPlayer> list = this.getTargets();
            block8: for (EntityPlayer entityPlayer : list) {
                InteractionUtil.Placement placement;
                Object object;
                if (this.smart.getValue().booleanValue() && !BlockUtilSC.isHole(new BlockPos((Entity)entityPlayer)) && PistonAuraTest.mc.world.getBlockState(new BlockPos((Entity)entityPlayer)).getBlock() == Blocks.AIR) continue;
                BlockPos blockPos = new BlockPos((Entity)entityPlayer).up();
                if (this.antiSuicide.getValue().booleanValue() && blockPos.equals((Object)new BlockPos((Entity)PistonAuraTest.mc.player))) continue;
                for (EnumFacing enumFacing : EnumFacing.HORIZONTALS) {
                    if (!(PistonAuraTest.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock() instanceof BlockPistonBase) && (this.placedPistonTimer.passedMs(CrystalUtilSC.ping() + 150) || !blockPos.offset(enumFacing).equals((Object)this.placedPiston)) || PistonAuraTest.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock() instanceof BlockPistonBase && !(object = (EnumFacing)PistonAuraTest.mc.world.getBlockState(blockPos.offset(enumFacing)).getValue((IProperty)BlockDirectional.FACING)).equals((Object)enumFacing.getOpposite())) continue;
                    if (PistonAuraTest.mc.world.getBlockState(blockPos.offset(enumFacing, 2)).getBlock() == Blocks.REDSTONE_BLOCK || PistonAuraTest.mc.world.getBlockState(blockPos.offset(enumFacing, 2)).getBlock() == Blocks.REDSTONE_TORCH || !InteractionUtil.canPlaceBlock(blockPos.offset(enumFacing, 2), this.raytrace.getValue()) || (placement = InteractionUtil.preparePlacement(blockPos.offset(enumFacing, 2), true, bl, this.raytrace.getValue())) == null) break block8;
                    this.postAction = () -> {
                        boolean bl = PistonAuraTest.mc.player.inventory.currentItem != n4;
                        int n2 = PistonAuraTest.mc.player.inventory.currentItem;
                        if (bl) {
                            PistonAuraTest.mc.player.inventory.currentItem = n4;
                            PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n4));
                        }
                        InteractionUtil.placeBlockSafely(placement, EnumHand.MAIN_HAND, true);
                        this.delayTimer.reset();
                        this.delayTime = CrystalUtilSC.ping() + 150;
                        if (bl) {
                            PistonAuraTest.mc.player.inventory.currentItem = n2;
                            PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n2));
                        }
                        if (this.triggerable.getValue().booleanValue()) {
                            this.toggle();
                        }
                    };
                    return;
                }
                for (EnumFacing enumFacing : EnumFacing.HORIZONTALS) {
                    if (!InteractionUtil.canPlaceBlock(blockPos.offset(enumFacing), this.raytrace.getValue())) continue;
                    if (this.raytrace.getValue().booleanValue()) {
                        if (!InteractionUtil.canPlaceBlock(blockPos.offset(enumFacing, 2), true)) continue;
                    } else if (PistonAuraTest.mc.world.getBlockState(blockPos.offset(enumFacing, 2)).getBlock() != Blocks.AIR) continue;
                    object = SilentRotaionUtil.calculateAngle(PistonAuraTest.mc.player.getPositionEyes(1.0f), new Vec3d((double)blockPos.offset(enumFacing).getX() + 0.5, (double)blockPos.offset(enumFacing).getY() + 1.0, (double)blockPos.offset(enumFacing).getZ() + 0.5));
                    EnumFacing enumFacing2 = EnumFacing.fromAngle((double)((double)object[0]));
                    if (Math.abs((float)object[1]) > 55.0f || enumFacing2 != enumFacing || (placement = InteractionUtil.preparePlacement(blockPos.offset(enumFacing), true, bl, this.raytrace.getValue())) == null) continue;
                    this.postAction = () -> {
                        boolean bl = PistonAuraTest.mc.player.inventory.currentItem != n;
                        int n2 = PistonAuraTest.mc.player.inventory.currentItem;
                        if (bl) {
                            PistonAuraTest.mc.player.inventory.currentItem = n;
                            PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
                        }
                        InteractionUtil.placeBlockSafely(placement, EnumHand.MAIN_HAND, true);
                        this.placedPiston = blockPos.offset(enumFacing);
                        this.placedPistonTimer.reset();
                        if (bl) {
                            PistonAuraTest.mc.player.inventory.currentItem = n2;
                            PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n2));
                        }
                    };
                    return;
                }
            }
        }
    }

    public boolean checkatomizer(BlockPos blockPos) {
        return PistonAuraTest.mc.player.getDistance((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()) > 2.0;
    }

    public void traptarget2(Entity entity) {
        if (this.autotrap.getValue().booleanValue() && PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ)).getBlock().equals((Object)Blocks.AIR)) {
            PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.getobby()));
            if (PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX + 1.0, entity.posY + 2.0, entity.posZ)).getBlock().equals((Object)Blocks.OBSIDIAN) || PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX + 1.0, entity.posY + 2.0, entity.posZ)).getBlock().equals((Object)Blocks.BEDROCK) && this.checkatomizer(new BlockPos(entity.posX + 1.0, entity.posY + 2.0, entity.posZ))) {
                InteractionUtil.Placement placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ), true, true);
                InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
                return;
            }
            if (PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX - 1.0, entity.posY + 2.0, entity.posZ)).getBlock().equals((Object)Blocks.OBSIDIAN) || PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX - 1.0, entity.posY + 2.0, entity.posZ)).getBlock().equals((Object)Blocks.BEDROCK) && this.checkatomizer(new BlockPos(entity.posX - 1.0, entity.posY + 2.0, entity.posZ))) {
                InteractionUtil.Placement placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ), true, true);
                InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
                return;
            }
            if (PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ + 1.0)).getBlock().equals((Object)Blocks.OBSIDIAN) || PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ + 1.0)).getBlock().equals((Object)Blocks.BEDROCK) && this.checkatomizer(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ + 1.0))) {
                InteractionUtil.Placement placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ), true, true);
                InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
                return;
            }
            if (PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ - 1.0)).getBlock().equals((Object)Blocks.OBSIDIAN) || PistonAuraTest.mc.world.getBlockState(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ - 1.0)).getBlock().equals((Object)Blocks.BEDROCK) && this.checkatomizer(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ - 1.0))) {
                InteractionUtil.Placement placement = InteractionUtil.preparePlacement(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ), true, true);
                InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, true);
                return;
            }
        }
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketSoundEffect) {
            SPacketSoundEffect sPacketSoundEffect = (SPacketSoundEffect)receive.getPacket();
            if (this.crystalPos == null) {
                return;
            }
            if (sPacketSoundEffect.getCategory() == SoundCategory.BLOCKS && sPacketSoundEffect.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE && this.crystalPos.up().getDistance((int)sPacketSoundEffect.getX(), (int)sPacketSoundEffect.getY(), (int)sPacketSoundEffect.getZ()) <= 2.0) {
                this.stage = Stage.SEARCHING;
                this.delayTime = 0;
            }
        }
        if (receive.getPacket() instanceof SPacketBlockChange && this.torchPos != null && ((SPacketBlockChange)receive.getPacket()).getBlockPosition().equals((Object)this.torchPos) && ((SPacketBlockChange)receive.getPacket()).getBlockState().getBlock() instanceof BlockAir) {
            this.torchPos = null;
        }
    }

    private boolean isAir(BlockPos blockPos) {
        return PistonAuraTest.mc.world.getBlockState(blockPos).getBlock() instanceof BlockAir;
    }

    private int getobby() {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            Block block;
            ItemStack itemStack = PistonAuraTest.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.field_190927_a || !(itemStack.getItem() instanceof ItemBlock) || !((block = ((ItemBlock)itemStack.getItem()).getBlock()) instanceof BlockObsidian)) continue;
            n = i;
            break;
        }
        return n;
    }

    private int getRedstoneBlockSlot() {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            Block block;
            ItemStack itemStack = PistonAuraTest.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.field_190927_a || !(itemStack.getItem() instanceof ItemBlock) || (block = ((ItemBlock)itemStack.getItem()).getBlock()) != Blocks.REDSTONE_BLOCK && block != Blocks.REDSTONE_TORCH) continue;
            n = i;
            break;
        }
        return n;
    }

    static {
        INSTANCE = new PistonAuraTest();
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayerPre(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 0) {
            if (this.tickCounter < this.actionInterval.getValue()) {
                ++this.tickCounter;
            }
            if (updateWalkingPlayerEvent.isCanceled() || !InteractionUtil.canPlaceNormally()) {
                return;
            }
            if (this.stage == Stage.BREAKING) {
                SilentRotaionUtil.lookAtXYZ((float)this.facePos.getX() + 0.5f, this.facePos.getY(), (float)this.facePos.getZ() + 0.5f);
            }
            if (this.tickCounter < this.actionInterval.getValue()) {
                return;
            }
            this.handleAction(false);
        }
    }

    private boolean evaluateTarget(EntityPlayer entityPlayer) {
        if (this.getRedstoneBlockSlot() == -1) {
            Command.sendMessage("No redstone found!");
            this.toggle();
            return false;
        }
        BlockPos blockPos = new BlockPos((Entity)entityPlayer).up();
        if (this.evaluateTarget(blockPos)) {
            return true;
        }
        blockPos = new BlockPos((Entity)entityPlayer).up().up();
        return this.evaluateTarget(blockPos);
    }

    public boolean isOffhand() {
        return PistonAuraTest.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
    }

    public static boolean canPlaceCrystal(BlockPos blockPos) {
        if (PistonAuraTest.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && PistonAuraTest.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
            return false;
        }
        BlockPos blockPos2 = blockPos.add(0, 1, 0);
        if (PistonAuraTest.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR && PistonAuraTest.mc.world.getBlockState(blockPos2).getBlock() != Blocks.PISTON_HEAD) {
            return false;
        }
        BlockPos blockPos3 = blockPos.add(0, 2, 0);
        if (PistonAuraTest.mc.world.getBlockState(blockPos3).getBlock() != Blocks.AIR) {
            return false;
        }
        return PistonAuraTest.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2, blockPos3.add(1, 1, 1))).isEmpty();
    }

    public boolean evaluateTarget(BlockPos blockPos) {
        if (!this.isAir(blockPos) && !this.mine.getValue().booleanValue()) {
            return false;
        }
        for (EnumFacing enumFacing : EnumFacing.HORIZONTALS) {
            Optional<BlockUtilSC.ClickLocation> optional;
            this.torchPos = null;
            this.skipPiston = false;
            if (!PistonAuraTest.canPlaceCrystal(blockPos.offset(enumFacing).down())) continue;
            if (this.getRedstoneBlockSlot() == -1) {
                return false;
            }
            ItemStack itemStack = PistonAuraTest.mc.player.inventory.getStackInSlot(this.getRedstoneBlockSlot());
            Block block = ((ItemBlock)itemStack.getItem()).getBlock();
            if (block == Blocks.REDSTONE_BLOCK) {
                if (!this.isAir(blockPos.offset(enumFacing, 3))) {
                    if (!this.mine.getValue().booleanValue() || PistonAuraTest.mc.world.getBlockState(blockPos.offset(enumFacing, 3)).getBlock() != Blocks.REDSTONE_TORCH && PistonAuraTest.mc.world.getBlockState(blockPos.offset(enumFacing, 3)).getBlock() != Blocks.REDSTONE_BLOCK) continue;
                    this.torchPos = blockPos.offset(enumFacing, 3);
                }
            } else {
                optional = BlockUtilSC.generateClickLocation(blockPos.offset(enumFacing, 3), false, true);
                if (!optional.isPresent() && this.mine.getValue().booleanValue() && (PistonAuraTest.mc.world.getBlockState(blockPos.offset(enumFacing, 3)).getBlock() == Blocks.REDSTONE_TORCH || PistonAuraTest.mc.world.getBlockState(blockPos.offset(enumFacing, 3)).getBlock() == Blocks.REDSTONE_BLOCK)) {
                    this.torchPos = blockPos.offset(enumFacing, 3);
                }
                if (!optional.isPresent() && this.torchPos == null && ((ItemBlock)PistonAuraTest.mc.player.inventory.getStackInSlot(this.getRedstoneBlockSlot()).getItem()).getBlock() == Blocks.REDSTONE_TORCH) {
                    for (Object object : EnumFacing.HORIZONTALS) {
                        if (object.equals((Object)enumFacing) || object.equals((Object)enumFacing.getOpposite())) continue;
                        optional = BlockUtilSC.generateClickLocation(blockPos.offset(enumFacing, 2).offset(object), false, true);
                        if (optional.isPresent()) break;
                        if (!this.mine.getValue().booleanValue() || PistonAuraTest.mc.world.getBlockState(blockPos.offset(enumFacing, 2).offset(object)).getBlock() != Blocks.REDSTONE_TORCH) continue;
                        this.torchPos = blockPos.offset(enumFacing, 2).offset(object);
                        break;
                    }
                }
                if (!optional.isPresent() && this.torchPos == null) continue;
            }
            optional = BlockUtilSC.generateClickLocation(blockPos.offset(enumFacing, 2));
            this.skipPiston = this.mine.getValue() != false && PistonAuraTest.mc.world.getBlockState(blockPos.offset(enumFacing, 2)).getBlock() instanceof BlockPistonBase;
            boolean bl = this.skipPiston;
            if (!optional.isPresent() && !this.skipPiston) continue;
            if (!this.skipPiston) {
                Object object;
                BlockPos blockPos2 = optional.get().neighbour;
                EnumFacing enumFacing2 = optional.get().opposite;
                object = BlockUtilSC.calculateLookAt(blockPos2.getX(), blockPos2.getY(), blockPos2.getZ(), enumFacing2, (EntityPlayer)PistonAuraTest.mc.player);
                EnumFacing enumFacing3 = EnumFacing.fromAngle((double)object[0]);
                if (Math.abs((double)object[1]) > 55.0 || enumFacing3 != enumFacing || this.raytrace.getValue().booleanValue() && !this.rayTrace(optional.get().neighbour)) continue;
                this.pistonNeighbour = blockPos2;
                this.pistonOffset = enumFacing2;
            }
            this.facePos = blockPos;
            this.faceOffset = enumFacing;
            this.crystalPos = blockPos.offset(enumFacing).down();
            return true;
        }
        return false;
    }

    public static PistonAuraTest getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PistonAuraTest();
        }
        return INSTANCE;
    }

    public static int getPistonSlot() {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            Block block;
            ItemStack itemStack = PistonAuraTest.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.field_190927_a || !(itemStack.getItem() instanceof ItemBlock) || !((block = ((ItemBlock)itemStack.getItem()).getBlock()) instanceof BlockPistonBase)) continue;
            n = i;
            break;
        }
        return n;
    }

    private List<EntityPlayer> getTargets() {
        return PistonAuraTest.mc.world.playerEntities.stream().filter(entityPlayer -> !AliceMain.friendManager.isFriend(entityPlayer.getName())).filter(entityPlayer -> entityPlayer != PistonAuraTest.mc.player).filter(entityPlayer -> PistonAuraTest.mc.player.getDistanceToEntity((Entity)entityPlayer) < (float)this.targetRange.getValue().intValue()).sorted(Comparator.comparing(entityPlayer -> Float.valueOf(PistonAuraTest.mc.player.getDistanceToEntity((Entity)entityPlayer)))).collect(Collectors.toList());
    }

    public PistonAuraTest() {
        super("PistonAura", "Good PistonAura", Module.Category.COMBAT, true, false, false);
        this.mode = this.register(new Setting<Mode>("Mode", Mode.DAMAGE));
        this.smart = this.register(new Setting<Boolean>("Smart", Boolean.TRUE, bl -> this.mode.getValue() == Mode.PUSH));
        this.triggerable = this.register(new Setting<Boolean>("DisablePush", Boolean.TRUE, bl -> this.mode.getValue() == Mode.PUSH));
        this.disableWhenNone = this.register(new Setting<Boolean>("DisableNone", Boolean.FALSE, bl -> this.mode.getValue() == Mode.DAMAGE));
        this.targetRange = this.register(new Setting<Integer>("TargetRange", 3, 1, 6));
        this.breakDelay = this.register(new Setting<Integer>("Delay", 25, 0, 100));
        this.actionShift = this.register(new Setting<Integer>("ActionShift", 3, 1, 8));
        this.actionInterval = this.register(new Setting<Integer>("ActionInterval", 0, 0, 10));
        this.strict = this.register(new Setting<Boolean>("Strict", false));
        this.raytrace = this.register(new Setting<Boolean>("RayTrace", false));
        this.antiSuicide = this.register(new Setting<Boolean>("AntiSuicide", false));
        this.mine = this.register(new Setting<Boolean>("Mine", false));
        this.autotrap = this.register(new Setting<Boolean>("AutoTrap", false));
        this.rmode = this.register(new Setting<rMode>("RotateMode", rMode.Test));
        this.stage = Stage.SEARCHING;
        this.torchTimer = new Timer();
        this.playertrapped = false;
        this.delayTimer = new Timer();
        this.brdel = new Timer();
        this.renderTimer = new Timer();
        this.postAction = null;
        this.tickCounter = 0;
        this.placedPiston = null;
        this.placedPistonTimer = new Timer();
        this.actionTimer = new Timer();
        this.sendPacket = false;
        this.firstPacket = false;
        this.setInstance();
    }

    private boolean rayTrace(BlockPos blockPos) {
        for (double d = 0.1; d < 0.9; d += 0.1) {
            for (double d2 = 0.1; d2 < 0.9; d2 += 0.1) {
                for (double d3 = 0.1; d3 < 0.9; d3 += 0.1) {
                    Vec3d vec3d = new Vec3d(PistonAuraTest.mc.player.posX, PistonAuraTest.mc.player.getEntityBoundingBox().minY + (double)PistonAuraTest.mc.player.getEyeHeight(), PistonAuraTest.mc.player.posZ);
                    Vec3d vec3d2 = new Vec3d((Vec3i)blockPos).addVector(d, d2, d3);
                    double d4 = vec3d.distanceTo(vec3d2);
                    double d5 = vec3d2.xCoord - vec3d.xCoord;
                    double d6 = vec3d2.yCoord - vec3d.yCoord;
                    double d7 = vec3d2.zCoord - vec3d.zCoord;
                    double d8 = MathHelper.sqrt((double)(d5 * d5 + d7 * d7));
                    double[] arrd = new double[]{MathHelper.wrapDegrees((float)((float)Math.toDegrees(Math.atan2(d7, d5)) - 90.0f)), MathHelper.wrapDegrees((float)((float)(-Math.toDegrees(Math.atan2(d6, d8)))))};
                    float f = MathHelper.cos((float)((float)(-arrd[0] * 0.01745329238474369 - 3.1415927410125732)));
                    float f2 = MathHelper.sin((float)((float)(-arrd[0] * 0.01745329238474369 - 3.1415927410125732)));
                    float f3 = -MathHelper.cos((float)((float)(-arrd[1] * 0.01745329238474369)));
                    float f4 = MathHelper.sin((float)((float)(-arrd[1] * 0.01745329238474369)));
                    Vec3d vec3d3 = new Vec3d((double)(f2 * f3), (double)f4, (double)(f * f3));
                    Vec3d vec3d4 = vec3d.addVector(vec3d3.xCoord * d4, vec3d3.yCoord * d4, vec3d3.zCoord * d4);
                    RayTraceResult rayTraceResult = PistonAuraTest.mc.world.rayTraceBlocks(vec3d, vec3d4, false, false, false);
                    if (rayTraceResult == null || rayTraceResult.typeOfHit != RayTraceResult.Type.BLOCK || !rayTraceResult.getBlockPos().equals((Object)blockPos)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private /* synthetic */ void lambda$handleAction$6(boolean bl, int n, boolean bl2, boolean bl3, Optional optional, Vec3d vec3d) {
        this.delayTimer.reset();
        this.renderTimer.reset();
        this.delayTime = this.breakDelay.getValue() * 10;
        if (bl) {
            PistonAuraTest.mc.player.inventory.currentItem = n;
            PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
        }
        if (bl2) {
            PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PistonAuraTest.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
        }
        if (bl3) {
            PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PistonAuraTest.mc.player, CPacketEntityAction.Action.START_SNEAKING));
        }
        PistonAuraTest.mc.playerController.processRightClickBlock(PistonAuraTest.mc.player, PistonAuraTest.mc.world, ((BlockUtilSC.ClickLocation)optional.get()).neighbour, ((BlockUtilSC.ClickLocation)optional.get()).opposite, vec3d, EnumHand.MAIN_HAND);
        PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
        if (bl3) {
            PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PistonAuraTest.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        if (bl2) {
            PistonAuraTest.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)PistonAuraTest.mc.player, CPacketEntityAction.Action.START_SPRINTING));
        }
        this.stage = Stage.BREAKING;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayerPost(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (this.postAction != null) {
            this.actionTimer.reset();
            this.tickCounter = 0;
            this.postAction.run();
            this.postAction = null;
            for (int i = 0; i < this.actionShift.getValue() - 1; ++i) {
                this.handleAction(true);
                if (this.postAction == null) {
                    return;
                }
                this.postAction.run();
                this.postAction = null;
            }
        }
        this.postAction = null;
    }

    private static enum Stage {
        SEARCHING,
        CRYSTAL,
        REDSTONE,
        BREAKING,
        EXPLOSION;

    }

    private static enum bMode {
        Packet,
        Normal,
        Sequential;

    }

    private static enum rMode {
        Test,
        Silent,
        Silent2,
        Client,
        Oyvey;

    }

    private static enum Mode {
        DAMAGE,
        PUSH;

    }
}

