//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemBoat
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItem
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  org.lwjgl.input.Mouse
 */
package me.snow.aclient.module.modules.misc;

import me.snow.aclient.module.Module;
import net.minecraft.item.ItemBoat;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import org.lwjgl.input.Mouse;

public class BoatPlace
extends Module {
    @Override
    public void onUpdate() {
        if (BoatPlace.mc.player.getHeldItemMainhand().getItem() instanceof ItemBoat && Mouse.isButtonDown((int)1)) {
            mc.getConnection().sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
            mc.getConnection().sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(BoatPlace.mc.objectMouseOver.getBlockPos(), EnumFacing.SOUTH, EnumHand.MAIN_HAND, 1.0f, 1.0f, 1.0f));
        }
    }

    public BoatPlace() {
        super("BoatPlace", "Tries to bypass Anti Boat placement", Module.Category.MISC, true, false, false);
    }
}

