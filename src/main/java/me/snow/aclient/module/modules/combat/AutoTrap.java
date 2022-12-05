//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockObsidian
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.combat;

import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.InteractionUtil;
import me.snow.aclient.util.Timer;
import me.snow.aclient.util.ca.sc.BlockUtilSC;
import me.snow.aclient.util.ca.sc.CrystalUtilSC;
import me.snow.aclient.util.ca.sc.SilentRotaionUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockObsidian;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoTrap
extends Module {
    private /* synthetic */ Timer lastPlacementTimer;
    /* synthetic */ Timer renderTimer;
    private /* synthetic */ Setting<Boolean> strict;
    private final /* synthetic */ Setting<Boolean> top;
    private /* synthetic */ int tickCounter;
    private /* synthetic */ Setting<Boolean> rotate;
    private /* synthetic */ BlockPos playerPos;
    private /* synthetic */ InteractionUtil.Placement placement;
    private /* synthetic */ BlockPos renderPos;
    private /* synthetic */ Setting<Boolean> toggelable;
    private /* synthetic */ Setting<Integer> actionShift;
    private /* synthetic */ InteractionUtil.Placement lastPlacement;
    private final /* synthetic */ Setting<Float> placeRange;
    private /* synthetic */ int itemSlot;
    private /* synthetic */ Setting<Integer> actionInterval;
    public static /* synthetic */ ConcurrentHashMap<BlockPos, Long> shiftedBlocks;

    private boolean canPlaceBlock(BlockPos blockPos, boolean bl) {
        return InteractionUtil.canPlaceBlock(blockPos, bl) && !shiftedBlocks.containsKey((Object)blockPos);
    }

    static {
        shiftedBlocks = new ConcurrentHashMap();
    }

    private BlockPos getNextPos(BlockPos blockPos) {
        Block block;
        double d;
        BlockPos blockPos2;
        double d2;
        BlockPos blockPos3;
        for (EnumFacing enumFacing : EnumFacing.HORIZONTALS) {
            blockPos3 = null;
            d2 = 0.0;
            if (this.canPlaceBlock(blockPos.offset(enumFacing).down(), true)) {
                double d3;
                blockPos2 = blockPos.offset(enumFacing).down();
                d = AutoTrap.mc.player.getDistance((double)blockPos2.getX() + 0.5, (double)blockPos2.getY() + 0.5, (double)blockPos2.getZ() + 0.5);
                if (d3 >= d2) {
                    blockPos3 = blockPos2;
                    d2 = d;
                }
            }
            if (blockPos3 == null) continue;
            return blockPos3;
        }
        for (EnumFacing enumFacing : EnumFacing.HORIZONTALS) {
            blockPos3 = null;
            d2 = 0.0;
            if (this.canPlaceBlock(blockPos.offset(enumFacing), false)) {
                double d4;
                blockPos2 = blockPos.offset(enumFacing);
                d = AutoTrap.mc.player.getDistance((double)blockPos2.getX() + 0.5, (double)blockPos2.getY() + 0.5, (double)blockPos2.getZ() + 0.5);
                if (d4 >= d2) {
                    blockPos3 = blockPos2;
                    d2 = d;
                }
            }
            if (blockPos3 == null) continue;
            return blockPos3;
        }
        for (EnumFacing enumFacing : EnumFacing.HORIZONTALS) {
            blockPos3 = null;
            d2 = 0.0;
            if (this.canPlaceBlock(blockPos.up().offset(enumFacing), false)) {
                double d5;
                blockPos2 = blockPos.up().offset(enumFacing);
                d = AutoTrap.mc.player.getDistance((double)blockPos2.getX() + 0.5, (double)blockPos2.getY() + 0.5, (double)blockPos2.getZ() + 0.5);
                if (d5 >= d2) {
                    blockPos3 = blockPos2;
                    d2 = d;
                }
            }
            if (blockPos3 == null) continue;
            return blockPos3;
        }
        if (this.top.getValue().booleanValue() && ((block = AutoTrap.mc.world.getBlockState(blockPos.up().up()).getBlock()) instanceof BlockAir || block instanceof BlockLiquid)) {
            if (this.canPlaceBlock(blockPos.up().up(), false)) {
                return blockPos.up().up();
            }
            BlockPos blockPos4 = blockPos.up().up().offset(EnumFacing.getHorizontal((int)(MathHelper.floor((double)((double)(AutoTrap.mc.player.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3)));
            if (this.canPlaceBlock(blockPos4, false)) {
                return blockPos4;
            }
        }
        return null;
    }

    public AutoTrap() {
        super("AutoTrap", "Trap your enemy", Module.Category.COMBAT, true, false, false);
        this.placeRange = this.register(new Setting<Float>("TargetRange", Float.valueOf(4.5f), Float.valueOf(1.0f), Float.valueOf(16.0f)));
        this.actionShift = this.register(new Setting<Integer>("BlockPerTick", 3, 1, 8));
        this.actionInterval = this.register(new Setting<Integer>("Delay", 0, 0, 10));
        this.top = this.register(new Setting<Boolean>("Top", true));
        this.strict = this.register(new Setting<Boolean>("Strict", false));
        this.rotate = this.register(new Setting<Boolean>("Rotate", true));
        this.toggelable = this.register(new Setting<Boolean>("Toggle", false));
        this.renderTimer = new Timer();
        this.tickCounter = 0;
        this.playerPos = null;
        this.lastPlacementTimer = new Timer();
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayerPre(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        int n;
        if (updateWalkingPlayerEvent.getStage() != 0) {
            return;
        }
        if (this.placement != null) {
            this.lastPlacement = this.placement;
            this.lastPlacementTimer.reset();
        }
        this.placement = null;
        this.playerPos = null;
        int n2 = CrystalUtilSC.ping();
        shiftedBlocks.forEach((blockPos, l) -> {
            if (System.currentTimeMillis() - l > (long)(n2 + 100)) {
                shiftedBlocks.remove(blockPos);
            }
        });
        if (updateWalkingPlayerEvent.isCanceled() || !InteractionUtil.canPlaceNormally(this.rotate.getValue())) {
            return;
        }
        if (!(!this.strict.getValue().booleanValue() || AutoTrap.mc.player.onGround && AutoTrap.mc.player.isCollidedVertically)) {
            return;
        }
        if (this.tickCounter < this.actionInterval.getValue()) {
            ++this.tickCounter;
        }
        if ((n = this.getBlockSlot()) == -1) {
            Command.sendMessage("No Obsidian. Disable Module!");
            this.toggle();
            return;
        }
        this.itemSlot = n;
        EntityPlayer entityPlayer = this.getNearestTarget();
        if (entityPlayer == null) {
            return;
        }
        if (this.tickCounter < this.actionInterval.getValue()) {
            if (this.lastPlacement != null && !this.lastPlacementTimer.passedMs(650L)) {
                SilentRotaionUtil.lookAtAngles(this.lastPlacement.getYaw(), this.lastPlacement.getPitch());
            }
            return;
        }
        this.playerPos = new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ);
        BlockPos blockPos2 = this.getNextPos(this.playerPos);
        if (blockPos2 != null) {
            this.placement = InteractionUtil.preparePlacement(blockPos2, this.rotate.getValue());
            if (this.placement != null) {
                shiftedBlocks.put(blockPos2, System.currentTimeMillis());
                this.tickCounter = 0;
                this.renderPos = blockPos2;
                this.renderTimer.reset();
            }
        } else if (this.toggelable.getValue().booleanValue()) {
            this.toggle();
            return;
        }
    }

    private boolean isValidBase(EntityPlayer entityPlayer) {
        BlockPos blockPos = new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ).down();
        Block block = AutoTrap.mc.world.getBlockState(blockPos).getBlock();
        return !(block instanceof BlockAir) && !(block instanceof BlockLiquid);
    }

    private EntityPlayer getNearestTarget() {
        Stream stream = AutoTrap.mc.world.playerEntities.stream();
        return stream.filter(entityPlayer -> entityPlayer != AutoTrap.mc.player && entityPlayer != mc.getRenderViewEntity()).filter(entityPlayer -> !AliceMain.friendManager.isFriend(entityPlayer.getName())).filter(entityPlayer -> AutoTrap.mc.player.getDistanceToEntity((Entity)entityPlayer) < Math.max(this.placeRange.getValue().floatValue() - 1.0f, 1.0f)).filter(this::isValidBase).min(Comparator.comparing(entityPlayer -> Float.valueOf(AutoTrap.mc.player.getDistanceToEntity((Entity)entityPlayer)))).orElse(null);
    }

    private int getBlockSlot() {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            Block block;
            ItemStack itemStack = AutoTrap.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.field_190927_a || !(itemStack.getItem() instanceof ItemBlock) || !((block = ((ItemBlock)itemStack.getItem()).getBlock()) instanceof BlockObsidian)) continue;
            n = i;
            break;
        }
        return n;
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayerPost(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() != 1) {
            return;
        }
        if (this.placement != null && this.playerPos != null && this.itemSlot != -1) {
            boolean bl = AutoTrap.mc.player.inventory.currentItem != this.itemSlot;
            int n = AutoTrap.mc.player.inventory.currentItem;
            if (bl) {
                AutoTrap.mc.player.inventory.currentItem = this.itemSlot;
                AutoTrap.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.itemSlot));
            }
            boolean bl2 = AutoTrap.mc.player.isSprinting();
            boolean bl3 = BlockUtilSC.shouldSneakWhileRightClicking(this.placement.getNeighbour());
            if (bl2) {
                AutoTrap.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoTrap.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
            }
            if (bl3) {
                AutoTrap.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoTrap.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            }
            InteractionUtil.placeBlock(this.placement, EnumHand.MAIN_HAND, true);
            for (int i = 0; i < this.actionShift.getValue() - 1; ++i) {
                InteractionUtil.Placement placement;
                BlockPos blockPos = this.getNextPos(this.playerPos);
                if (blockPos != null) {
                    placement = InteractionUtil.preparePlacement(blockPos, this.rotate.getValue(), true);
                    if (placement == null) break;
                } else {
                    if (!this.toggelable.getValue().booleanValue()) break;
                    this.toggle();
                    if (bl) {
                        AutoTrap.mc.player.inventory.currentItem = n;
                        AutoTrap.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
                    }
                    return;
                }
                this.placement = placement;
                shiftedBlocks.put(blockPos, System.currentTimeMillis());
                InteractionUtil.placeBlock(this.placement, EnumHand.MAIN_HAND, true);
                this.renderPos = blockPos;
                this.renderTimer.reset();
            }
            if (bl3) {
                AutoTrap.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoTrap.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            }
            if (bl2) {
                AutoTrap.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoTrap.mc.player, CPacketEntityAction.Action.START_SPRINTING));
            }
            if (bl) {
                AutoTrap.mc.player.inventory.currentItem = n;
                AutoTrap.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
            }
        }
    }

    @Override
    public void onEnable() {
        if (AutoTrap.mc.player == null || AutoTrap.mc.world == null) {
            this.toggle();
            return;
        }
        this.renderPos = null;
        this.playerPos = null;
        this.placement = null;
        this.lastPlacement = null;
        this.tickCounter = this.actionInterval.getValue();
    }
}

