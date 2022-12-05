//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 */
package me.snow.aclient.module.modules.combat;

import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Feature;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.skid.oyvey.BlockUtil2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class TrapPhase
extends Module {
    public /* synthetic */ Setting<Boolean> silentSwitch;
    private /* synthetic */ EnumHand oldhand;
    public /* synthetic */ Setting<Boolean> packetPlace;
    public /* synthetic */ Setting<Boolean> faceObsidian;
    public /* synthetic */ Setting<Float> offset;
    private /* synthetic */ int oldslot;

    public boolean entityCheck(BlockPos blockPos) {
        return TrapPhase.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos), entity -> entity instanceof EntityEnderCrystal || entity instanceof EntityPlayer).isEmpty();
    }

    @Override
    public void onTick() {
        if (Feature.nullCheck()) {
            return;
        }
        int n = InventoryUtil.getBlockHotbar(Blocks.IRON_TRAPDOOR);
        int n2 = InventoryUtil.getBlockHotbar(Blocks.OBSIDIAN);
        if (this.faceObsidian.getValue().booleanValue()) {
            if (n == -1 || n2 == -1) {
                Command.sendMessage("Cannot find materials! disabling");
                this.disable();
                return;
            }
        } else if (n == -1) {
            Command.sendMessage("Cannot find materials! disabling");
            this.disable();
            return;
        }
        BlockPos[] arrblockPos = new BlockPos[]{new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1)};
        BlockPos blockPos = EntityUtil.getEntityPos((Entity)TrapPhase.mc.player);
        BlockPos blockPos2 = null;
        for (BlockPos blockPos3 : arrblockPos) {
            BlockPos blockPos4 = blockPos.add((Vec3i)blockPos3);
            if (!this.entityCheck(blockPos4) || BlockUtil2.getBlock(blockPos4).equals((Object)Blocks.AIR)) continue;
            blockPos2 = blockPos4;
        }
        if (blockPos2 == null) {
            Command.sendMessage("Cannot find space! disabling");
            this.disable();
            return;
        }
        this.setItem(n);
        double d = TrapPhase.mc.player.posX;
        double d2 = TrapPhase.mc.player.posY;
        double d3 = TrapPhase.mc.player.posZ;
        TrapPhase.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(d, d2 + (double)this.offset.getValue().floatValue(), d3, TrapPhase.mc.player.onGround));
        EnumFacing enumFacing = null;
        for (EnumFacing enumFacing2 : EnumFacing.values()) {
            if (!blockPos2.add(enumFacing2.getDirectionVec()).equals((Object)blockPos)) continue;
            enumFacing = enumFacing2;
            break;
        }
        if (this.faceObsidian.getValue().booleanValue()) {
            BlockUtil2.rightClickBlock1(blockPos2, enumFacing, new Vec3d(0.5, 0.8, 0.5), this.packetPlace.getValue());
            TrapPhase.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(d, d2, d3, TrapPhase.mc.player.onGround));
            this.setItem(n2);
            TrapPhase.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(d, d2 - 0.8, d3, false));
            BlockUtil2.rightClickBlock2(blockPos, EnumFacing.UP, this.packetPlace.getValue());
            TrapPhase.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(d, d2, d3, false));
            this.restoreItem();
            this.disable();
        } else {
            BlockUtil2.rightClickBlock1(blockPos2, enumFacing, new Vec3d(0.5, 0.8, 0.5), this.packetPlace.getValue());
            TrapPhase.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(d, d2, d3, TrapPhase.mc.player.onGround));
            this.restoreItem();
            this.disable();
        }
    }

    public TrapPhase() {
        super("TrapPhase", "Lags you into a block instantly.", Module.Category.COMBAT, true, false, true);
        this.offset = this.register(new Setting<Float>("Offset", Float.valueOf(0.2f), Float.valueOf(0.0f), Float.valueOf(1.0f)));
        this.silentSwitch = this.register(new Setting<Boolean>("SilentSwitch", true));
        this.packetPlace = this.register(new Setting<Boolean>("PacketPlace", true));
        this.faceObsidian = this.register(new Setting<Boolean>("FaceObsidian", false));
        this.oldhand = null;
        this.oldslot = -1;
    }

    public void restoreItem() {
        if (this.oldslot != -1 && this.silentSwitch.getValue().booleanValue()) {
            if (this.oldhand != null) {
                TrapPhase.mc.player.setActiveHand(this.oldhand);
            }
            TrapPhase.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.oldslot));
            this.oldslot = -1;
            this.oldhand = null;
        }
    }

    public void setItem(int n) {
        if (this.silentSwitch.getValue().booleanValue()) {
            this.oldhand = null;
            if (TrapPhase.mc.player.isHandActive()) {
                this.oldhand = TrapPhase.mc.player.getActiveHand();
            }
            this.oldslot = TrapPhase.mc.player.inventory.currentItem;
            TrapPhase.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
        } else {
            TrapPhase.mc.player.inventory.currentItem = n;
            TrapPhase.mc.playerController.updateController();
        }
    }
}

