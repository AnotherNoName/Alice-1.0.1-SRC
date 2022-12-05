//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ForcePlace
extends Module {
    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock;
        if (send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock && (ForcePlace.mc.world.getBlockState((cPacketPlayerTryUseItemOnBlock = (CPacketPlayerTryUseItemOnBlock)send.getPacket()).getPos()).getBlock() == Blocks.ENDER_CHEST || ForcePlace.mc.world.getBlockState(cPacketPlayerTryUseItemOnBlock.getPos()).getBlock() == Blocks.CHEST || ForcePlace.mc.world.getBlockState(cPacketPlayerTryUseItemOnBlock.getPos()).getBlock() == Blocks.TRAPPED_CHEST || ForcePlace.mc.world.getBlockState(cPacketPlayerTryUseItemOnBlock.getPos()).getBlock() == Blocks.DISPENSER || ForcePlace.mc.world.getBlockState(cPacketPlayerTryUseItemOnBlock.getPos()).getBlock() == Blocks.DROPPER)) {
            send.setCanceled(true);
        }
    }

    public ForcePlace() {
        super("ForcePlace", ".", Module.Category.PLAYER, true, false, false);
    }
}

