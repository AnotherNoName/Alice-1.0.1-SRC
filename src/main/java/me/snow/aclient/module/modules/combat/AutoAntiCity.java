//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 */
package me.snow.aclient.module.modules.combat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.skid.oyvey.HoleUtilStay;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class AutoAntiCity
extends Module {
    private final /* synthetic */ Setting<Boolean> rotate;
    private /* synthetic */ int swapBlock;
    /* synthetic */ List<Vec3d> city;
    private final /* synthetic */ Setting<Boolean> noGhost;
    private final /* synthetic */ Setting<Double> range;

    private void switchToSlot(int n) {
        AutoAntiCity.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
        AutoAntiCity.mc.player.inventory.currentItem = n;
        AutoAntiCity.mc.playerController.updateController();
    }

    @Override
    public void onUpdate() {
        if (AutoAntiCity.mc.player == null || AutoAntiCity.mc.world == null) {
            return;
        }
        this.swapBlock = InventoryUtil.findHotbarBlock(Blocks.OBSIDIAN);
        if (this.swapBlock == -1) {
            return;
        }
        BlockPos blockPos = new BlockPos(AutoAntiCity.mc.player.posX, AutoAntiCity.mc.player.posY, AutoAntiCity.mc.player.posZ);
        if (blockPos == null) {
            return;
        }
        if (!HoleUtilStay.isHole(blockPos) || this.getTarget(this.range.getValue()) == null) {
            return;
        }
        if (AutoAntiCity.mc.world.getBlockState(blockPos.north()).getBlock() == Blocks.BEDROCK && AutoAntiCity.mc.world.getBlockState(blockPos.east()).getBlock() == Blocks.BEDROCK && AutoAntiCity.mc.world.getBlockState(blockPos.west()).getBlock() == Blocks.BEDROCK && AutoAntiCity.mc.world.getBlockState(blockPos.south()).getBlock() == Blocks.BEDROCK && AutoAntiCity.mc.world.getBlockState(blockPos.down()).getBlock() == Blocks.BEDROCK) {
            return;
        }
        for (Vec3d vec3d : this.city) {
            BlockPos blockPos2 = new BlockPos(vec3d.addVector((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()));
            if (AutoAntiCity.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR) continue;
            this.paley(blockPos2);
        }
    }

    private EntityPlayer getTarget(double d) {
        EntityPlayer entityPlayer = null;
        double d2 = Math.pow(d, 2.0) + 1.0;
        for (EntityPlayer entityPlayer2 : AutoAntiCity.mc.world.playerEntities) {
            if (EntityUtil.isntValid((Entity)entityPlayer2, d) || AliceMain.speedManager.getPlayerSpeed(entityPlayer2) > 10.0) continue;
            if (entityPlayer == null) {
                entityPlayer = entityPlayer2;
                d2 = AutoAntiCity.mc.player.getDistanceSqToEntity((Entity)entityPlayer2);
                continue;
            }
            if (!(AutoAntiCity.mc.player.getDistanceSqToEntity((Entity)entityPlayer2) < d2)) continue;
            entityPlayer = entityPlayer2;
            d2 = AutoAntiCity.mc.player.getDistanceSqToEntity((Entity)entityPlayer2);
        }
        return entityPlayer;
    }

    private void paley(BlockPos blockPos) {
        int n = AutoAntiCity.mc.player.inventory.currentItem;
        this.switchToSlot(this.swapBlock);
        BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, this.rotate.getValue(), true, false);
        this.switchToSlot(n);
    }

    public AutoAntiCity() {
        super("AutoAntiCity", "AutoAntiCity", Module.Category.COMBAT, true, false, false);
        this.rotate = this.register(new Setting<Boolean>("Rotate", true));
        this.noGhost = this.register(new Setting<Boolean>("Packet", false));
        this.range = this.register(new Setting<Double>("Range", 8.0, 1.0, 20.0));
        this.swapBlock = -1;
        this.city = new ArrayList<Vec3d>(Arrays.asList(new Vec3d[]{new Vec3d(2.0, 0.0, 0.0), new Vec3d(2.0, 1.0, 0.0), new Vec3d(-2.0, 0.0, 0.0), new Vec3d(-2.0, 1.0, 0.0), new Vec3d(0.0, 0.0, 2.0), new Vec3d(0.0, 1.0, 2.0), new Vec3d(0.0, 0.0, -2.0), new Vec3d(0.0, 1.0, -2.0)}));
    }
}

