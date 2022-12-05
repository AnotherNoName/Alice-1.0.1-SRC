//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemEgg
 *  net.minecraft.item.ItemEnderPearl
 *  net.minecraft.item.ItemSnowball
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItem
 *  net.minecraft.util.EnumHand
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BowMcBomb
extends Module {
    private /* synthetic */ long lastShootTime;
    public /* synthetic */ Setting<Integer> Timeout;
    public /* synthetic */ Setting<Boolean> bypass;
    public /* synthetic */ Setting<Boolean> debug;
    public /* synthetic */ Setting<Boolean> pearls;
    public /* synthetic */ Setting<Integer> spoofs;
    public /* synthetic */ Setting<Boolean> Bows;
    private /* synthetic */ boolean shooting;
    public /* synthetic */ Setting<Boolean> snowballs;
    public /* synthetic */ Setting<Boolean> eggs;

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        ItemStack itemStack;
        CPacketPlayerTryUseItem cPacketPlayerTryUseItem;
        if (send.getStage() != 0) {
            return;
        }
        if (send.getPacket() instanceof CPacketPlayerDigging) {
            ItemStack itemStack2;
            CPacketPlayerDigging cPacketPlayerDigging = (CPacketPlayerDigging)send.getPacket();
            if (cPacketPlayerDigging.getAction() == CPacketPlayerDigging.Action.RELEASE_USE_ITEM && !(itemStack2 = BowMcBomb.mc.player.getHeldItem(EnumHand.MAIN_HAND)).func_190926_b() && itemStack2.getItem() != null && itemStack2.getItem() instanceof ItemBow && this.Bows.getValue().booleanValue()) {
                this.doSpoofs();
                if (this.debug.getValue().booleanValue()) {
                    Command.sendMessage("trying to spoof");
                }
            }
        } else if (send.getPacket() instanceof CPacketPlayerTryUseItem && (cPacketPlayerTryUseItem = (CPacketPlayerTryUseItem)send.getPacket()).getHand() == EnumHand.MAIN_HAND && !(itemStack = BowMcBomb.mc.player.getHeldItem(EnumHand.MAIN_HAND)).func_190926_b() && itemStack.getItem() != null) {
            if (itemStack.getItem() instanceof ItemEgg && this.eggs.getValue().booleanValue()) {
                this.doSpoofs();
            } else if (itemStack.getItem() instanceof ItemEnderPearl && this.pearls.getValue().booleanValue()) {
                this.doSpoofs();
            } else if (itemStack.getItem() instanceof ItemSnowball && this.snowballs.getValue().booleanValue()) {
                this.doSpoofs();
            }
        }
    }

    private void doSpoofs() {
        if (System.currentTimeMillis() - this.lastShootTime >= (long)this.Timeout.getValue().intValue()) {
            this.shooting = true;
            this.lastShootTime = System.currentTimeMillis();
            BowMcBomb.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BowMcBomb.mc.player, CPacketEntityAction.Action.START_SPRINTING));
            for (int i = 0; i < this.spoofs.getValue(); ++i) {
                if (this.bypass.getValue().booleanValue()) {
                    BowMcBomb.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowMcBomb.mc.player.posX, BowMcBomb.mc.player.posY + 1.0E-10, BowMcBomb.mc.player.posZ, false));
                    BowMcBomb.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowMcBomb.mc.player.posX, BowMcBomb.mc.player.posY - 1.0E-10, BowMcBomb.mc.player.posZ, true));
                    continue;
                }
                BowMcBomb.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowMcBomb.mc.player.posX, BowMcBomb.mc.player.posY - 1.0E-10, BowMcBomb.mc.player.posZ, true));
                BowMcBomb.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowMcBomb.mc.player.posX, BowMcBomb.mc.player.posY + 1.0E-10, BowMcBomb.mc.player.posZ, false));
            }
            if (this.debug.getValue().booleanValue()) {
                Command.sendMessage("Spoofed");
            }
            this.shooting = false;
        }
    }

    public BowMcBomb() {
        super("BowExploit", "Uno hitter w bows", Module.Category.MISC, true, false, false);
        this.Bows = this.register(new Setting<Boolean>("Bows", true));
        this.pearls = this.register(new Setting<Boolean>("Pearls", false));
        this.eggs = this.register(new Setting<Boolean>("Eggs", false));
        this.snowballs = this.register(new Setting<Boolean>("SnowBallz", false));
        this.Timeout = this.register(new Setting<Integer>("Timeout", 5000, 100, 20000));
        this.spoofs = this.register(new Setting<Integer>("Spoofs", 10, 1, 300));
        this.bypass = this.register(new Setting<Boolean>("Bypass", true));
        this.debug = this.register(new Setting<Boolean>("Debug", false));
    }

    @Override
    public void onEnable() {
        if (this.isEnabled()) {
            this.shooting = false;
            this.lastShootTime = System.currentTimeMillis();
        }
    }
}

