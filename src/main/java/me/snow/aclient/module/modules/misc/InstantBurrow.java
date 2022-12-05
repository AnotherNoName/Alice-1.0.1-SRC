//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.util.math.BlockPos
 */
package me.snow.aclient.module.modules.misc;

import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.BlockInteractionHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;

public class InstantBurrow
extends Module {
    public /* synthetic */ Setting<Boolean> rotate;
    public /* synthetic */ Setting<Boolean> silent;
    public /* synthetic */ Setting<Boolean> rotate2;
    /* synthetic */ float oldTickLength;
    public /* synthetic */ Setting<Boolean> enderchest;
    public /* synthetic */ Setting<Integer> height2;
    private /* synthetic */ Setting<AMODEA> mode;
    public /* synthetic */ Setting<Double> height;
    public /* synthetic */ Setting<Boolean> silent2;

    @Override
    public void enable() {
        BlockPos blockPos;
        int n;
        if (this.mode.getValue() == AMODEA.NormalBurrow) {
            if (InstantBurrow.mc.player == null || InstantBurrow.mc.world == null) {
                return;
            }
            n = InstantBurrow.mc.player.inventory.currentItem;
            this.oldTickLength = InstantBurrow.mc.timer.field_194149_e;
            blockPos = new BlockPos(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY, InstantBurrow.mc.player.posZ);
            if (InstantBurrow.mc.world.getBlockState(new BlockPos(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY, InstantBurrow.mc.player.posZ)).getBlock().equals((Object)Blocks.OBSIDIAN) || InstantBurrow.mc.world.getBlockState(new BlockPos(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY, InstantBurrow.mc.player.posZ)).getBlock().equals((Object)Blocks.ENDER_CHEST)) {
                Command.sendMessage("Bro ur already in burrow");
                this.disable();
                return;
            }
            if (BlockInteractionHelper.isInterceptedByOther(blockPos)) {
                Command.sendMessage("Ur intercepting with another creature.");
                this.disable();
                return;
            }
            if (InstantBurrow.getHotbarSlot(Blocks.OBSIDIAN) == -1 && InstantBurrow.getHotbarSlot(Blocks.ENDER_CHEST) == -1) {
                Command.sendMessage("U need echest or obsidian.");
                this.disable();
                return;
            }
            if (!(InstantBurrow.mc.world.getBlockState(blockPos.add(0, 1, 0)).getBlock().equals((Object)Blocks.AIR) && InstantBurrow.mc.world.getBlockState(blockPos.add(0, 2, 0)).getBlock().equals((Object)Blocks.AIR) && InstantBurrow.mc.world.getBlockState(blockPos.add(0, 3, 0)).getBlock().equals((Object)Blocks.AIR))) {
                Command.sendMessage("need some space!");
                this.disable();
                return;
            }
            if (mc.isSingleplayer()) {
                Command.sendMessage("Why are u trying this in singleplayer?");
                this.disable();
                return;
            }
            if (this.silent.getValue().booleanValue()) {
                InstantBurrow.mc.timer.field_194149_e = 1.0f;
            }
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 0.41999998688698, InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 0.7531999805211997, InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 1.00133597911214, InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 1.16610926093821, InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.player.setPosition(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 1.16610926093821, InstantBurrow.mc.player.posZ);
            InstantBurrow.mc.player.inventory.currentItem = this.enderchest.getValue() != false && InstantBurrow.getHotbarSlot(Blocks.ENDER_CHEST) != -1 ? InstantBurrow.getHotbarSlot(Blocks.ENDER_CHEST) : (InstantBurrow.getHotbarSlot(Blocks.OBSIDIAN) != -1 ? InstantBurrow.getHotbarSlot(Blocks.OBSIDIAN) : InstantBurrow.getHotbarSlot(Blocks.ENDER_CHEST));
            BlockInteractionHelper.placeBlock(blockPos, this.rotate.getValue(), true, false, true, false);
            InstantBurrow.mc.player.inventory.currentItem = n;
            InstantBurrow.mc.player.setPosition(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY - 1.16610926093821, InstantBurrow.mc.player.posZ);
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + this.height.getValue(), InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.timer.field_194149_e = this.oldTickLength;
            this.disable();
        }
        if (this.mode.getValue() == AMODEA.FiveBeeBypass) {
            if (InstantBurrow.mc.player == null || InstantBurrow.mc.world == null) {
                return;
            }
            n = InstantBurrow.mc.player.inventory.currentItem;
            this.oldTickLength = InstantBurrow.mc.timer.field_194149_e;
            blockPos = new BlockPos(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY, InstantBurrow.mc.player.posZ);
            if (InstantBurrow.mc.world.getBlockState(new BlockPos(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY, InstantBurrow.mc.player.posZ)).getBlock().equals((Object)Blocks.PISTON)) {
                Command.sendMessage("Bro ur already in burrow");
                this.disable();
                return;
            }
            if (BlockInteractionHelper.isInterceptedByOther(blockPos)) {
                Command.sendMessage("Ur intercepting with another creature.");
                this.disable();
                return;
            }
            if (InstantBurrow.getHotbarSlot((Block)Blocks.PISTON) == -1) {
                Command.sendMessage("U need piston");
                this.disable();
                return;
            }
            if (!(InstantBurrow.mc.world.getBlockState(blockPos.add(0, 1, 0)).getBlock().equals((Object)Blocks.AIR) && InstantBurrow.mc.world.getBlockState(blockPos.add(0, 2, 0)).getBlock().equals((Object)Blocks.AIR) && InstantBurrow.mc.world.getBlockState(blockPos.add(0, 3, 0)).getBlock().equals((Object)Blocks.AIR))) {
                Command.sendMessage("need some space!");
                this.disable();
                return;
            }
            if (mc.isSingleplayer()) {
                Command.sendMessage("Why are u trying this in singleplayer?");
                this.disable();
                return;
            }
            if (this.silent2.getValue().booleanValue()) {
                InstantBurrow.mc.timer.field_194149_e = 1.0f;
            }
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 0.41999998688698, InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 0.7531999805211997, InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 1.00133597911214, InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 1.16610926093821, InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.player.setPosition(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + 1.16610926093821, InstantBurrow.mc.player.posZ);
            if (InstantBurrow.getHotbarSlot((Block)Blocks.PISTON) != -1) {
                InstantBurrow.mc.player.inventory.currentItem = InstantBurrow.getHotbarSlot((Block)Blocks.PISTON);
            }
            BlockInteractionHelper.placeBlock(blockPos, this.rotate2.getValue(), true, false, true, false);
            InstantBurrow.mc.player.inventory.currentItem = n;
            InstantBurrow.mc.player.setPosition(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY - 1.16610926093821, InstantBurrow.mc.player.posZ);
            InstantBurrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(InstantBurrow.mc.player.posX, InstantBurrow.mc.player.posY + (double)this.height2.getValue().intValue(), InstantBurrow.mc.player.posZ, true));
            InstantBurrow.mc.timer.field_194149_e = this.oldTickLength;
            this.disable();
        }
    }

    public static int getHotbarSlot(Block block) {
        for (int i = 0; i < 9; ++i) {
            Item item = InstantBurrow.mc.player.inventory.getStackInSlot(i).getItem();
            if (!(item instanceof ItemBlock) || !((ItemBlock)item).getBlock().equals((Object)block)) continue;
            return i;
        }
        return -1;
    }

    public InstantBurrow() {
        super("InstantBurrow", "Lags you into a block instantly.", Module.Category.MISC, true, false, true);
        this.mode = this.register(new Setting<AMODEA>("BurrowMode", AMODEA.NormalBurrow));
        this.rotate = this.register(new Setting<Boolean>("Rotate", Boolean.valueOf(true), bl -> this.mode.getValue() == AMODEA.NormalBurrow));
        this.silent = this.register(new Setting<Boolean>("Silent", Boolean.valueOf(true), bl -> this.mode.getValue() == AMODEA.NormalBurrow));
        this.enderchest = this.register(new Setting<Boolean>("EnderChest", Boolean.valueOf(true), bl -> this.mode.getValue() == AMODEA.NormalBurrow));
        this.height = this.register(new Setting<Object>("Height", Double.valueOf(1.5), Double.valueOf(-5.0), Double.valueOf(10.0), object -> this.mode.getValue() == AMODEA.NormalBurrow));
        this.rotate2 = this.register(new Setting<Boolean>("5bRotate", Boolean.valueOf(false), bl -> this.mode.getValue() == AMODEA.FiveBeeBypass));
        this.silent2 = this.register(new Setting<Boolean>("5bSilent", Boolean.valueOf(true), bl -> this.mode.getValue() == AMODEA.FiveBeeBypass));
        this.height2 = this.register(new Setting<Object>("5bHeight", Integer.valueOf(2), Integer.valueOf(-30), Integer.valueOf(30), object -> this.mode.getValue() == AMODEA.FiveBeeBypass));
        this.oldTickLength = InstantBurrow.mc.timer.field_194149_e;
    }

    private static enum AMODEA {
        FiveBeeBypass,
        NormalBurrow;

    }
}

