//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.init.Items
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoBreakAnimation
extends Module {
    private /* synthetic */ EnumFacing lastFacing;
    private /* synthetic */ boolean isMining;
    private /* synthetic */ BlockPos lastPos;
    public final /* synthetic */ Setting<Boolean> onlyPickaxe;

    @Override
    public void onUpdate() {
        if (!NoBreakAnimation.mc.gameSettings.keyBindAttack.isKeyDown()) {
            this.resetMining();
            return;
        }
        if (this.isMining && this.lastPos != null && this.lastFacing != null) {
            NoBreakAnimation.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.lastPos, this.lastFacing));
        }
    }

    private void setMiningInfo(BlockPos blockPos, EnumFacing enumFacing) {
        this.lastPos = blockPos;
        this.lastFacing = enumFacing;
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        if (this.onlyPickaxe.getValue().booleanValue()) {
            if (send.getPacket() instanceof CPacketPlayerDigging && NoBreakAnimation.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_PICKAXE) {
                CPacketPlayerDigging cPacketPlayerDigging = (CPacketPlayerDigging)send.getPacket();
                for (Entity entity : NoBreakAnimation.mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(cPacketPlayerDigging.getPosition()))) {
                    if (entity instanceof EntityEnderCrystal) {
                        this.resetMining();
                        return;
                    }
                    if (!(entity instanceof EntityLivingBase)) continue;
                    this.resetMining();
                    return;
                }
                if (cPacketPlayerDigging.getAction().equals((Object)CPacketPlayerDigging.Action.START_DESTROY_BLOCK)) {
                    this.isMining = true;
                    this.setMiningInfo(cPacketPlayerDigging.getPosition(), cPacketPlayerDigging.getFacing());
                }
                if (cPacketPlayerDigging.getAction().equals((Object)CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK)) {
                    this.resetMining();
                }
            }
        } else if (send.getPacket() instanceof CPacketPlayerDigging) {
            CPacketPlayerDigging cPacketPlayerDigging = (CPacketPlayerDigging)send.getPacket();
            for (Entity entity : NoBreakAnimation.mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(cPacketPlayerDigging.getPosition()))) {
                if (entity instanceof EntityEnderCrystal) {
                    this.resetMining();
                    return;
                }
                if (!(entity instanceof EntityLivingBase)) continue;
                this.resetMining();
                return;
            }
            if (cPacketPlayerDigging.getAction().equals((Object)CPacketPlayerDigging.Action.START_DESTROY_BLOCK)) {
                this.isMining = true;
                this.setMiningInfo(cPacketPlayerDigging.getPosition(), cPacketPlayerDigging.getFacing());
            }
            if (cPacketPlayerDigging.getAction().equals((Object)CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK)) {
                this.resetMining();
            }
        }
    }

    public NoBreakAnimation() {
        super("NoBreakAnimation", "Prevents serverside break animations.", Module.Category.PLAYER, true, false, false);
        this.isMining = false;
        this.lastPos = null;
        this.lastFacing = null;
        this.onlyPickaxe = this.register(new Setting<Boolean>("OnlyPickaxe", false));
    }

    public void resetMining() {
        this.isMining = false;
        this.lastPos = null;
        this.lastFacing = null;
    }
}

