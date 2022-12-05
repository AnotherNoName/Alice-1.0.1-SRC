//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemPotion
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItem
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.client.event.InputUpdateEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickItem
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.movement;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoSlowDown
extends Module {
    private /* synthetic */ boolean sneaking;
    public /* synthetic */ Setting<Boolean> strict;
    public /* synthetic */ Setting<Boolean> noSlow;
    private static final /* synthetic */ KeyBinding[] keys;
    public /* synthetic */ Setting<Boolean> soulSand;
    private static /* synthetic */ NoSlowDown INSTANCE;
    public /* synthetic */ Setting<Boolean> endPortal;
    public /* synthetic */ Setting<Boolean> sneakPacket;
    public /* synthetic */ Setting<Boolean> superStrict;

    @SubscribeEvent
    public void onUseItem(PlayerInteractEvent.RightClickItem rightClickItem) {
        Item item = NoSlowDown.mc.player.getHeldItem(rightClickItem.getHand()).getItem();
        if ((item instanceof ItemFood || item instanceof ItemBow || item instanceof ItemPotion && this.sneakPacket.getValue().booleanValue()) && !this.sneaking) {
            NoSlowDown.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)NoSlowDown.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            this.sneaking = true;
        }
    }

    static {
        keys = new KeyBinding[]{NoSlowDown.mc.gameSettings.keyBindForward, NoSlowDown.mc.gameSettings.keyBindBack, NoSlowDown.mc.gameSettings.keyBindLeft, NoSlowDown.mc.gameSettings.keyBindRight, NoSlowDown.mc.gameSettings.keyBindJump, NoSlowDown.mc.gameSettings.keyBindSprint};
        INSTANCE = new NoSlowDown();
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @SubscribeEvent
    public void onInput(InputUpdateEvent inputUpdateEvent) {
        if (this.noSlow.getValue().booleanValue() && NoSlowDown.mc.player.isHandActive() && !NoSlowDown.mc.player.isRiding()) {
            inputUpdateEvent.getMovementInput().moveStrafe *= 5.0f;
            inputUpdateEvent.getMovementInput().field_192832_b *= 5.0f;
        }
    }

    @Override
    public void onUpdate() {
        NoSlowDown.mc.player.getActiveItemStack().getItem();
        if (this.sneaking && !NoSlowDown.mc.player.isHandActive() && this.sneakPacket.getValue().booleanValue()) {
            NoSlowDown.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)NoSlowDown.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.sneaking = false;
        }
    }

    @SubscribeEvent
    public void onPacket(PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketPlayer && this.strict.getValue().booleanValue() && this.noSlow.getValue().booleanValue() && NoSlowDown.mc.player.isHandActive() && !NoSlowDown.mc.player.isRiding()) {
            NoSlowDown.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, new BlockPos(Math.floor(NoSlowDown.mc.player.posX), Math.floor(NoSlowDown.mc.player.posY), Math.floor(NoSlowDown.mc.player.posZ)), EnumFacing.DOWN));
        }
        if (send.getPacket() instanceof CPacketPlayerTryUseItem || send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            Item item = NoSlowDown.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem();
            if (this.superStrict.getValue().booleanValue() && (item instanceof ItemFood || item instanceof ItemBow || item instanceof ItemPotion)) {
                NoSlowDown.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(NoSlowDown.mc.player.inventory.currentItem));
            }
        }
    }

    public NoSlowDown() {
        super("NoSlowDown", "Prevents you from getting slowed down.", Module.Category.MOVEMENT, true, false, false);
        this.noSlow = this.register(new Setting<Boolean>("NoSlow", true));
        this.soulSand = this.register(new Setting<Boolean>("SoulSand", true));
        this.strict = this.register(new Setting<Boolean>("StrictOld", false));
        this.sneakPacket = this.register(new Setting<Boolean>("SneakPacket", false));
        this.endPortal = this.register(new Setting<Boolean>("EndPortal", false));
        this.superStrict = this.register(new Setting<Boolean>("2b2t", false));
        this.setInstance();
    }

    public static NoSlowDown getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NoSlowDown();
        }
        return INSTANCE;
    }
}

