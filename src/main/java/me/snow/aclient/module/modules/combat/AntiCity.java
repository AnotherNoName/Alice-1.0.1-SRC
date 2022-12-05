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
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.GameType
 */
package me.snow.aclient.module.modules.combat;

import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.manager.ModuleManager;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.BlockInteractionHelper;
import me.snow.aclient.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockObsidian;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.GameType;

public class AntiCity
extends Module {
    /* synthetic */ int cDelay;
    /* synthetic */ boolean hasDisabled;
    private /* synthetic */ int delayStep;
    private /* synthetic */ int playerHotbarSlot;
    private /* synthetic */ int offsetStep;
    private /* synthetic */ boolean firstRun;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Boolean> triggerable;
    private final /* synthetic */ Setting<Integer> timeoutTicks;
    /* synthetic */ boolean isDisabling;
    private /* synthetic */ int totalTicksRunning;
    private /* synthetic */ int lastHotbarSlot;
    private final /* synthetic */ Setting<Integer> tickDelay;
    private final /* synthetic */ Setting<Boolean> noGlitchBlocks;
    private final /* synthetic */ Setting<Boolean> turnOffCauras;
    private /* synthetic */ boolean isSneaking;
    private final /* synthetic */ Setting<Integer> blocksPerTick;
    /* synthetic */ String caura;
    /* synthetic */ double oldY;

    @Override
    public void onUpdate() {
        if (this.cDelay > 0) {
            --this.cDelay;
        }
        if (this.cDelay == 0 && this.isDisabling) {
            ModuleManager.getModuleByName(this.caura).toggle();
            this.isDisabling = false;
            this.hasDisabled = true;
        }
        if (Util.mc.player == null || AliceMain.moduleManager.isModuleEnabled("Freecam")) {
            return;
        }
        if (ModuleManager.getModuleByName("AutoCrystal") != null) {
            if (ModuleManager.getModuleByName("AutoCrystal").isEnabled() && this.turnOffCauras.getValue().booleanValue() && !this.hasDisabled) {
                this.caura = "AutoCrystal";
                this.cDelay = 19;
                this.isDisabling = true;
                ModuleManager.getModuleByName(this.caura).toggle();
            }
        }
        if (this.triggerable.getValue().booleanValue() && this.totalTicksRunning >= this.timeoutTicks.getValue()) {
            this.totalTicksRunning = 0;
            this.disable();
            return;
        }
        if (AntiCity.mc.player.posY != this.oldY) {
            this.disable();
            return;
        }
        if (!this.firstRun) {
            if (this.delayStep < this.tickDelay.getValue()) {
                ++this.delayStep;
                return;
            }
            this.delayStep = 0;
        }
        if (this.firstRun) {
            this.firstRun = false;
        }
        int n = 0;
        while (n < this.blocksPerTick.getValue()) {
            Vec3d[] arrvec3d = new Vec3d[]{};
            int n2 = 0;
            arrvec3d = Offsets.SURROUND;
            n2 = Offsets.SURROUND.length;
            if (this.offsetStep >= n2) {
                this.offsetStep = 0;
                break;
            }
            BlockPos blockPos = new BlockPos(arrvec3d[this.offsetStep]);
            BlockPos blockPos2 = new BlockPos(Util.mc.player.getPositionVector()).add(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            if (this.placeBlock(blockPos2)) {
                ++n;
            }
            ++this.offsetStep;
        }
        if (n > 0) {
            if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
                Util.mc.player.inventory.currentItem = this.playerHotbarSlot;
                this.lastHotbarSlot = this.playerHotbarSlot;
            }
            if (this.isSneaking) {
                Util.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Util.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                this.isSneaking = false;
            }
        }
        ++this.totalTicksRunning;
    }

    public AntiCity() {
        super("AntiCityBoss", "AntiCity.", Module.Category.COMBAT, true, false, false);
        this.triggerable = this.register(new Setting<Boolean>("Triggerable", true));
        this.turnOffCauras = this.register(new Setting<Boolean>("Toggle Other Cauras", true));
        this.timeoutTicks = this.register(new Setting<Integer>("TimeoutTicks", 40, 1, 100));
        this.blocksPerTick = this.register(new Setting<Integer>("BlocksPerTick", 4, 1, 9));
        this.tickDelay = this.register(new Setting<Integer>("TickDelay", 0, 0, 10));
        this.rotate = this.register(new Setting<Boolean>("Rotate", true));
        this.noGlitchBlocks = this.register(new Setting<Boolean>("NoGlitchBlocks", true));
        this.cDelay = 0;
        this.playerHotbarSlot = -1;
        this.lastHotbarSlot = -1;
        this.offsetStep = 0;
        this.delayStep = 0;
        this.totalTicksRunning = 0;
        this.isSneaking = false;
    }

    private boolean placeBlock(BlockPos blockPos) {
        Entity entity2;
        Block block = Util.mc.world.getBlockState(blockPos).getBlock();
        if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid)) {
            return false;
        }
        for (Entity entity2 : Util.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(blockPos))) {
            if (entity2 instanceof EntityItem || entity2 instanceof EntityXPOrb) continue;
            return false;
        }
        EnumFacing enumFacing = BlockInteractionHelper.getPlaceableSide(blockPos);
        if (enumFacing == null) {
            return false;
        }
        entity2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing2 = enumFacing.getOpposite();
        if (!BlockInteractionHelper.canBeClicked((BlockPos)entity2)) {
            return false;
        }
        Vec3d vec3d = new Vec3d((Vec3i)entity2).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing2.getDirectionVec()).scale(0.5));
        Block block2 = AntiCity.mc.world.getBlockState((BlockPos)entity2).getBlock();
        int n = this.findObiInHotbar();
        if (n == -1) {
            this.disable();
        }
        if (this.lastHotbarSlot != n) {
            Util.mc.player.inventory.currentItem = n;
            this.lastHotbarSlot = n;
        }
        if (!this.isSneaking && BlockInteractionHelper.blackList.contains((Object)block2) || BlockInteractionHelper.shulkerList.contains((Object)block2)) {
            Util.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Util.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            this.isSneaking = true;
        }
        if (this.rotate.getValue().booleanValue()) {
            BlockInteractionHelper.faceVectorPacketInstant(vec3d);
        }
        Util.mc.playerController.processRightClickBlock(Util.mc.player, Util.mc.world, (BlockPos)entity2, enumFacing2, vec3d, EnumHand.MAIN_HAND);
        Util.mc.player.swingArm(EnumHand.MAIN_HAND);
        Util.mc.rightClickDelayTimer = 4;
        if (this.noGlitchBlocks.getValue().booleanValue() && !Util.mc.playerController.getCurrentGameType().equals((Object)GameType.CREATIVE)) {
            Util.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, (BlockPos)entity2, enumFacing2));
        }
        return true;
    }

    private int findObiInHotbar() {
        int n = -1;
        for (int i = 0; i < 9; ++i) {
            Block block;
            ItemStack itemStack = Util.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.field_190927_a || !(itemStack.getItem() instanceof ItemBlock) || !((block = ((ItemBlock)itemStack.getItem()).getBlock()) instanceof BlockObsidian)) continue;
            n = i;
            break;
        }
        return n;
    }

    @Override
    public void onDisable() {
        if (AntiCity.mc.player == null) {
            return;
        }
        if (this.lastHotbarSlot != this.playerHotbarSlot && this.playerHotbarSlot != -1) {
            AntiCity.mc.player.inventory.currentItem = this.playerHotbarSlot;
        }
        if (this.isSneaking) {
            AntiCity.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AntiCity.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.isSneaking = false;
        }
        this.playerHotbarSlot = -1;
        this.lastHotbarSlot = -1;
    }

    @Override
    public void onEnable() {
        if (AntiCity.mc.player == null) {
            this.disable();
            return;
        }
        this.hasDisabled = false;
        this.oldY = AntiCity.mc.player.posY;
        this.firstRun = true;
        this.playerHotbarSlot = AntiCity.mc.player.inventory.currentItem;
        this.lastHotbarSlot = -1;
    }

    private static class Offsets {
        private static final /* synthetic */ Vec3d[] SURROUND;

        static {
            SURROUND = new Vec3d[]{new Vec3d(2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 2.0), new Vec3d(-2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -2.0)};
        }

        private Offsets() {
        }
    }

    private static enum Mode {
        SURROUND,
        FULL;

    }
}

