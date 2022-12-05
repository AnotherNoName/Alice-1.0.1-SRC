//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFalling
 *  net.minecraft.block.BlockObsidian
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 */
package me.snow.aclient.module.modules.combat;

import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.InventoryUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockObsidian;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class SelfAnvil
extends Module {
    private final /* synthetic */ Setting<Integer> blocksPerTick;
    private /* synthetic */ int blockSlot;
    private /* synthetic */ BlockPos playerPos;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Boolean> onlyHole;
    private final /* synthetic */ Setting<Boolean> helpingBlocks;
    private /* synthetic */ int obbySlot;
    private final /* synthetic */ Setting<Boolean> chat;
    private /* synthetic */ int lastBlock;
    private /* synthetic */ BlockPos placePos;
    private final /* synthetic */ Setting<Boolean> packet;
    private /* synthetic */ int blocksThisTick;

    private boolean doFirstChecks() {
        int n = BlockUtil.isPositionPlaceable(this.placePos, false, true);
        if (SelfAnvil.fullNullCheck() || !SelfAnvil.mc.world.isAirBlock(this.playerPos)) {
            return false;
        }
        if (!BlockUtil.isBothHole(this.playerPos) && this.onlyHole.getValue().booleanValue()) {
            return false;
        }
        if (this.blockSlot == -1) {
            if (this.chat.getValue().booleanValue()) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("<").append(this.getDisplayName()).append("> \u00a7cNo Anvils in hotbar.")));
            }
            return false;
        }
        if (n == 2) {
            if (!this.helpingBlocks.getValue().booleanValue()) {
                if (this.chat.getValue().booleanValue()) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append("<").append(this.getDisplayName()).append("> \u00a7cNowhere to place.")));
                }
                return false;
            }
            if (this.obbySlot == -1) {
                if (this.chat.getValue().booleanValue()) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append("<").append(this.getDisplayName()).append("> \u00a7cNo Obsidian in hotbar.")));
                }
                return false;
            }
        } else if (n != 3) {
            if (this.chat.getValue().booleanValue()) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("<").append(this.getDisplayName()).append("> \u00a7cNot enough room.")));
            }
            return false;
        }
        return true;
    }

    private void doSelfAnvil() {
        if (this.helpingBlocks.getValue().booleanValue() && BlockUtil.isPositionPlaceable(this.placePos, false, true) == 2) {
            InventoryUtil.switchToHotbarSlot(this.obbySlot, false);
            this.doHelpBlocks();
        }
        if (this.blocksThisTick < this.blocksPerTick.getValue() && BlockUtil.isPositionPlaceable(this.placePos, false, true) == 3) {
            InventoryUtil.switchToHotbarSlot(this.blockSlot, false);
            BlockUtil.placeBlock(this.placePos, EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), false);
            InventoryUtil.switchToHotbarSlot(this.lastBlock, false);
            SelfAnvil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)SelfAnvil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.disable();
        }
    }

    public SelfAnvil() {
        super("SelfAnvil", "funne falling block", Module.Category.COMBAT, true, false, false);
        this.rotate = this.register(new Setting<Boolean>("Rotate", true));
        this.onlyHole = this.register(new Setting<Boolean>("HoleOnly", false));
        this.helpingBlocks = this.register(new Setting<Boolean>("HelpingBlocks", true));
        this.chat = this.register(new Setting<Boolean>("Chat Msgs", true));
        this.packet = this.register(new Setting<Boolean>("Packet", false));
        this.blocksPerTick = this.register(new Setting<Integer>("Blocks/Tick", 2, 1, 8));
    }

    @Override
    public void onEnable() {
        this.playerPos = new BlockPos(SelfAnvil.mc.player.posX, SelfAnvil.mc.player.posY, SelfAnvil.mc.player.posZ);
        this.placePos = this.playerPos.offset(EnumFacing.UP, 2);
        this.blockSlot = this.findBlockSlot();
        this.obbySlot = InventoryUtil.findHotbarBlock(BlockObsidian.class);
        this.lastBlock = SelfAnvil.mc.player.inventory.currentItem;
        if (!this.doFirstChecks()) {
            this.disable();
        }
    }

    @Override
    public void onTick() {
        this.blocksThisTick = 0;
        this.doSelfAnvil();
    }

    private void doHelpBlocks() {
        if (this.blocksThisTick >= this.blocksPerTick.getValue()) {
            return;
        }
        for (EnumFacing enumFacing : EnumFacing.values()) {
            if (enumFacing == EnumFacing.DOWN || BlockUtil.isPositionPlaceable(this.placePos.offset(enumFacing), false, true) != 3) continue;
            BlockUtil.placeBlock(this.placePos.offset(enumFacing), EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), false);
            ++this.blocksThisTick;
            return;
        }
        for (EnumFacing enumFacing : EnumFacing.values()) {
            if (enumFacing == EnumFacing.DOWN) continue;
            for (EnumFacing enumFacing2 : EnumFacing.values()) {
                if (BlockUtil.isPositionPlaceable(this.placePos.offset(enumFacing).offset(enumFacing2), false, true) != 3) continue;
                BlockUtil.placeBlock(this.placePos.offset(enumFacing).offset(enumFacing2), EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), false);
                ++this.blocksThisTick;
                return;
            }
        }
        for (EnumFacing enumFacing : EnumFacing.values()) {
            for (EnumFacing enumFacing2 : EnumFacing.values()) {
                for (EnumFacing enumFacing3 : EnumFacing.values()) {
                    if (BlockUtil.isPositionPlaceable(this.placePos.offset(enumFacing).offset(enumFacing2).offset(enumFacing3), false, true) != 3) continue;
                    BlockUtil.placeBlock(this.placePos.offset(enumFacing).offset(enumFacing2).offset(enumFacing3), EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), false);
                    ++this.blocksThisTick;
                    return;
                }
            }
        }
    }

    private int findBlockSlot() {
        for (int i = 0; i < 9; ++i) {
            Block block;
            ItemStack itemStack = SelfAnvil.mc.player.inventory.getStackInSlot(i);
            if (!(itemStack.getItem() instanceof ItemBlock) || !((block = Block.getBlockFromItem((Item)SelfAnvil.mc.player.inventory.getStackInSlot(i).getItem())) instanceof BlockFalling)) continue;
            return i;
        }
        return -1;
    }
}

