//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.util.EnumFacing
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BuildHeight
extends Module {
    private final /* synthetic */ Setting<Boolean> crystals;
    private final /* synthetic */ Setting<Integer> height;

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock;
        if (send.getStage() == 0 && send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock && (cPacketPlayerTryUseItemOnBlock = (CPacketPlayerTryUseItemOnBlock)send.getPacket()).getPos().getY() >= this.height.getValue() && (!this.crystals.getValue().booleanValue() || BuildHeight.mc.player.getHeldItem(cPacketPlayerTryUseItemOnBlock.getHand()).getItem() == Items.END_CRYSTAL) && cPacketPlayerTryUseItemOnBlock.getDirection() == EnumFacing.UP) {
            cPacketPlayerTryUseItemOnBlock.placedBlockDirection = EnumFacing.DOWN;
        }
    }

    public BuildHeight() {
        super("BuildHeight", "Allows you to place at build height", Module.Category.MISC, true, false, false);
        this.height = this.register(new Setting<Integer>("Height", 255, 0, 255));
        this.crystals = this.register(new Setting<Boolean>("CrystalOnly", false));
    }
}

