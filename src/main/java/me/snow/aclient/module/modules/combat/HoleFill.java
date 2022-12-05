//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.block.BlockWeb
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.server.SPacketBlockChange
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.combat;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.StreamSupport;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.mixin.mixins.accessors.IEntityPlayerSP;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.InteractionUtil;
import me.snow.aclient.util.ca.sc.BlockUtilSC;
import me.snow.aclient.util.ca.sc.CrystalUtilSC;
import net.minecraft.block.BlockWeb;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HoleFill
extends Module {
    public final /* synthetic */ Setting<Boolean> swing;
    public final /* synthetic */ Setting<Integer> actionShift;
    public final /* synthetic */ Setting<Boolean> rotate;
    public final /* synthetic */ Setting<SmartMode> smartMode;
    private /* synthetic */ int itemSlot;
    public final /* synthetic */ Setting<Double> targetRange;
    public final /* synthetic */ Setting<Boolean> strictDirection;
    public final /* synthetic */ Setting<Boolean> onlyWebs;
    /* synthetic */ ArrayList<BlockPos> blocks;
    private /* synthetic */ int tickCounter;
    public final /* synthetic */ Setting<Double> rangeXZ;
    public final /* synthetic */ Setting<Boolean> rayTrace;
    public final /* synthetic */ Setting<Boolean> doubleHoles;
    public final /* synthetic */ Setting<Boolean> jumpDisable;
    public final /* synthetic */ Setting<Integer> actionInterval;
    private /* synthetic */ Map<BlockPos, Long> renderBlocks;
    private /* synthetic */ Map<BlockPos, Long> placedBlocks;
    private /* synthetic */ InteractionUtil.Placement placement;
    public final /* synthetic */ Setting<Boolean> Toggle;

    private EntityPlayer getNearestTarget() {
        return HoleFill.mc.world.playerEntities.stream().filter(entityPlayer -> entityPlayer != HoleFill.mc.player).filter(entityPlayer -> !AliceMain.friendManager.isFriend(entityPlayer.getName())).filter(entityPlayer -> (double)HoleFill.mc.player.getDistanceToEntity((Entity)entityPlayer) < this.targetRange.getValue()).min(Comparator.comparing(entityPlayer -> Float.valueOf(HoleFill.mc.player.getDistanceToEntity((Entity)entityPlayer)))).orElse(null);
    }

    private boolean isHole(BlockPos blockPos) {
        if (this.placedBlocks.containsKey((Object)blockPos)) {
            return false;
        }
        for (Entity entity : HoleFill.mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(blockPos))) {
            if (entity instanceof EntityItem || entity instanceof EntityXPOrb || entity instanceof EntityArrow) continue;
            return false;
        }
        if (this.doubleHoles.getValue().booleanValue()) {
            BlockPos blockPos2 = BlockUtilSC.validTwoBlockBedrockXZ(blockPos);
            if (blockPos2 == null) {
                blockPos2 = BlockUtilSC.validTwoBlockObiXZ(blockPos);
            }
            if (blockPos2 != null) {
                return true;
            }
        }
        return BlockUtilSC.isHole(blockPos);
    }

    private boolean isValidItem(Item item) {
        if (item instanceof ItemBlock) {
            if (this.onlyWebs.getValue().booleanValue()) {
                return ((ItemBlock)item).getBlock() instanceof BlockWeb;
            }
            return true;
        }
        return false;
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayerPre(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        this.placement = null;
        if (this.jumpDisable.getValue().booleanValue() && HoleFill.mc.player.prevPosY < HoleFill.mc.player.posY) {
            this.toggle();
        }
        if (updateWalkingPlayerEvent.isOnGround() || !InteractionUtil.canPlaceNormally(this.rotate.getValue())) {
            return;
        }
        if (this.tickCounter < this.actionInterval.getValue()) {
            ++this.tickCounter;
        }
        if (this.tickCounter < this.actionInterval.getValue()) {
            return;
        }
        int n = this.getBlockSlot();
        this.itemSlot = -1;
        if (n == -1) {
            return;
        }
        this.blocks = Lists.newArrayList((Iterable)BlockPos.getAllInBox((BlockPos)HoleFill.mc.player.getPosition().add(-this.rangeXZ.getValue().doubleValue(), -this.rangeXZ.getValue().doubleValue(), -this.rangeXZ.getValue().doubleValue()), (BlockPos)HoleFill.mc.player.getPosition().add(this.rangeXZ.getValue().doubleValue(), this.rangeXZ.getValue().doubleValue(), this.rangeXZ.getValue().doubleValue())));
        int n2 = CrystalUtilSC.ping();
        this.placedBlocks.forEach((blockPos, l) -> {
            if (System.currentTimeMillis() - l > (long)(n2 + 100)) {
                this.placedBlocks.remove(blockPos);
            }
        });
        if (this.smartMode.getValue() == SmartMode.TARGET && this.getNearestTarget() == null) {
            return;
        }
        BlockPos blockPos2 = StreamSupport.stream(this.blocks.spliterator(), false).filter(this::isHole).filter(blockPos -> HoleFill.mc.player.getDistance((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5) <= this.rangeXZ.getValue()).filter(blockPos -> InteractionUtil.canPlaceBlock(blockPos, this.strictDirection.getValue(), this.rayTrace.getValue(), true)).min(Comparator.comparing(blockPos -> Float.valueOf(MathHelper.sqrt((double)HoleFill.mc.player.getDistanceSq(blockPos))))).orElse(null);
        if (blockPos2 != null) {
            this.placement = InteractionUtil.preparePlacement(blockPos2, this.rotate.getValue(), false, this.strictDirection.getValue(), this.rayTrace.getValue());
            if (this.placement != null) {
                this.tickCounter = 0;
                this.itemSlot = n;
                this.renderBlocks.put(blockPos2, System.currentTimeMillis());
                this.placedBlocks.put(blockPos2, System.currentTimeMillis());
            }
        } else if (this.Toggle.getValue().booleanValue()) {
            Command.sendMessage("We are finished hole filling. toggling");
            this.toggle();
        }
    }

    public HoleFill() {
        super("HoleFill", "Automatically Fills safe holes near the opponent for Crystal PvP", Module.Category.COMBAT, true, false, false);
        this.rotate = this.register(new Setting<Boolean>("Rotate", true));
        this.swing = this.register(new Setting<Boolean>("Swing", true));
        this.rangeXZ = this.register(new Setting<Double>("Range", 5.0, 0.1, 6.0));
        this.strictDirection = this.register(new Setting<Boolean>("StrictDirection", false));
        this.actionShift = this.register(new Setting<Integer>("BlockPerTick", 1, 1, 8));
        this.actionInterval = this.register(new Setting<Integer>("Delay", 0, 0, 5));
        this.rayTrace = this.register(new Setting<Boolean>("RayTrace", false));
        this.doubleHoles = this.register(new Setting<Boolean>("Double", false));
        this.jumpDisable = this.register(new Setting<Boolean>("JumpDisable", false));
        this.onlyWebs = this.register(new Setting<Boolean>("OnlyWebs", false));
        this.smartMode = this.register(new Setting<SmartMode>("Smart", SmartMode.ALWAYS));
        this.targetRange = this.register(new Setting<Double>("EnemyRange", 10.0, 1.0, 15.0));
        this.Toggle = this.register(new Setting<Boolean>("TogglesOff", false));
        this.renderBlocks = new ConcurrentHashMap<BlockPos, Long>();
        this.placement = null;
        this.placedBlocks = new ConcurrentHashMap<BlockPos, Long>();
        this.tickCounter = 0;
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketBlockChange && this.renderBlocks.containsKey((Object)((SPacketBlockChange)receive.getPacket()).getBlockPosition()) && ((SPacketBlockChange)receive.getPacket()).getBlockState().getBlock() != Blocks.AIR) {
            this.renderBlocks.remove((Object)((SPacketBlockChange)receive.getPacket()).getBlockPosition());
        }
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayerPost(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (this.placement != null && this.itemSlot != -1) {
            boolean bl = HoleFill.mc.player.inventory.currentItem != this.itemSlot;
            int n = HoleFill.mc.player.inventory.currentItem;
            if (bl) {
                HoleFill.mc.player.inventory.currentItem = this.itemSlot;
                HoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.itemSlot));
            }
            boolean bl2 = HoleFill.mc.player.isSprinting();
            boolean bl3 = BlockUtilSC.shouldSneakWhileRightClicking(this.placement.getNeighbour());
            if (bl2) {
                HoleFill.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)HoleFill.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
            }
            if (bl3) {
                HoleFill.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)HoleFill.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            }
            Vec3d vec3d = new Vec3d((Vec3i)this.placement.getNeighbour()).addVector(0.5, 0.5, 0.5).add(new Vec3d(this.placement.getOpposite().getDirectionVec()).scale(0.5));
            InteractionUtil.rightClickBlock(this.placement.getNeighbour(), vec3d, EnumHand.MAIN_HAND, this.placement.getOpposite(), true, this.swing.getValue());
            double d = HoleFill.mc.player.posX - ((IEntityPlayerSP)HoleFill.mc.player).getLastReportedPosX();
            double d2 = HoleFill.mc.player.posY - ((IEntityPlayerSP)HoleFill.mc.player).getLastReportedPosY();
            double d3 = HoleFill.mc.player.posZ - ((IEntityPlayerSP)HoleFill.mc.player).getLastReportedPosZ();
            boolean bl4 = d * d + d2 * d2 + d3 * d3 > 9.0E-4;
            for (int i = 0; i < this.actionShift.getValue() - 1 && !bl4; ++i) {
                InteractionUtil.Placement placement;
                EntityPlayer entityPlayer = this.getNearestTarget();
                BlockPos blockPos2 = StreamSupport.stream(this.blocks.spliterator(), false).filter(this::isHole).min(Comparator.comparing(blockPos -> Float.valueOf(this.smartMode.getValue() != SmartMode.NONE && entityPlayer != null ? MathHelper.sqrt((double)HoleFill.mc.player.getDistanceSqToEntity((Entity)entityPlayer)) : MathHelper.sqrt((double)HoleFill.mc.player.getDistanceSq(blockPos))))).orElse(null);
                if (blockPos2 == null || !InteractionUtil.canPlaceBlock(blockPos2, this.strictDirection.getValue()) || (placement = InteractionUtil.preparePlacement(blockPos2, this.rotate.getValue(), true, this.strictDirection.getValue())) == null) break;
                Vec3d vec3d2 = new Vec3d((Vec3i)placement.getNeighbour()).addVector(0.5, 0.5, 0.5).add(new Vec3d(placement.getOpposite().getDirectionVec()).scale(0.5));
                InteractionUtil.rightClickBlock(placement.getNeighbour(), vec3d2, EnumHand.MAIN_HAND, placement.getOpposite(), true, this.swing.getValue());
                this.placedBlocks.put(blockPos2, System.currentTimeMillis());
                this.renderBlocks.put(blockPos2, System.currentTimeMillis());
            }
            if (bl3) {
                HoleFill.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)HoleFill.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            }
            if (bl2) {
                HoleFill.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)HoleFill.mc.player, CPacketEntityAction.Action.START_SPRINTING));
            }
            if (bl) {
                HoleFill.mc.player.inventory.currentItem = n;
                HoleFill.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
            }
        }
    }

    @Override
    public void onEnable() {
        if (HoleFill.mc.player == null || HoleFill.mc.world == null) {
            this.toggle();
            return;
        }
        this.blocks = Lists.newArrayList((Iterable)BlockPos.getAllInBox((BlockPos)HoleFill.mc.player.getPosition().add(-this.rangeXZ.getValue().doubleValue(), -this.rangeXZ.getValue().doubleValue(), -this.rangeXZ.getValue().doubleValue()), (BlockPos)HoleFill.mc.player.getPosition().add(this.rangeXZ.getValue().doubleValue(), this.rangeXZ.getValue().doubleValue(), this.rangeXZ.getValue().doubleValue())));
        this.tickCounter = this.actionInterval.getValue();
    }

    private int getBlockSlot() {
        ItemStack itemStack = HoleFill.mc.player.getHeldItemMainhand();
        if (!itemStack.func_190926_b() && this.isValidItem(itemStack.getItem())) {
            return HoleFill.mc.player.inventory.currentItem;
        }
        for (int i = 0; i < 9; ++i) {
            itemStack = HoleFill.mc.player.inventory.getStackInSlot(i);
            if (itemStack.func_190926_b() || !this.isValidItem(itemStack.getItem())) continue;
            return i;
        }
        return -1;
    }

    private static enum SmartMode {
        NONE,
        ALWAYS,
        TARGET;

    }
}

