//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockEnderChest
 *  net.minecraft.block.BlockObsidian
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemEndCrystal
 *  net.minecraft.item.ItemExpBottle
 *  net.minecraft.item.ItemMinecart
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.InventoryUtil;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockObsidian;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.item.ItemMinecart;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class FastUse
extends Module {
    private /* synthetic */ BlockPos mousePos;
    private /* synthetic */ Setting<Boolean> all;
    private /* synthetic */ Setting<Boolean> packetCrystal;
    private /* synthetic */ Setting<Boolean> crystals;
    private /* synthetic */ Setting<Boolean> strict;
    private /* synthetic */ Setting<Boolean> exp;
    private /* synthetic */ Setting<Boolean> enderc;
    private /* synthetic */ Setting<Boolean> minecart;
    private /* synthetic */ Setting<Boolean> obby;

    @Override
    public void onUpdate() {
        if (this.strict.getValue().booleanValue() && FastUse.mc.player.ticksExisted % 2 == 0) {
            return;
        }
        if (FastUse.fullNullCheck()) {
            return;
        }
        if (InventoryUtil.holdingItem(ItemExpBottle.class) && this.exp.getValue().booleanValue()) {
            FastUse.mc.rightClickDelayTimer = 0;
        }
        if (InventoryUtil.holdingItem(BlockObsidian.class) && this.obby.getValue().booleanValue()) {
            FastUse.mc.rightClickDelayTimer = 0;
        }
        if (InventoryUtil.holdingItem(BlockEnderChest.class) && this.enderc.getValue().booleanValue()) {
            FastUse.mc.rightClickDelayTimer = 0;
        }
        if (InventoryUtil.holdingItem(ItemMinecart.class) && this.minecart.getValue().booleanValue()) {
            FastUse.mc.rightClickDelayTimer = 0;
        }
        if (this.all.getValue().booleanValue()) {
            FastUse.mc.rightClickDelayTimer = 0;
        }
        if (InventoryUtil.holdingItem(ItemEndCrystal.class) && (this.crystals.getValue().booleanValue() || this.all.getValue().booleanValue())) {
            FastUse.mc.rightClickDelayTimer = 0;
        }
        if (this.packetCrystal.getValue().booleanValue() && FastUse.mc.gameSettings.keyBindUseItem.isKeyDown()) {
            boolean bl = FastUse.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
            boolean bl2 = bl;
            if (bl || FastUse.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) {
                RayTraceResult rayTraceResult = FastUse.mc.objectMouseOver;
                if (rayTraceResult == null) {
                    return;
                }
                switch (rayTraceResult.typeOfHit) {
                    case MISS: {
                        this.mousePos = null;
                        break;
                    }
                    case BLOCK: {
                        this.mousePos = FastUse.mc.objectMouseOver.getBlockPos();
                        break;
                    }
                    case ENTITY: {
                        Entity entity;
                        if (this.mousePos == null || (entity = rayTraceResult.entityHit) == null || !this.mousePos.equals((Object)new BlockPos(entity.posX, entity.posY - 1.0, entity.posZ))) break;
                        FastUse.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.mousePos, EnumFacing.DOWN, bl ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                    }
                }
            }
        }
    }

    public FastUse() {
        super("FastUse", "Allows you to use items faster", Module.Category.PLAYER, true, false, false);
        this.all = this.register(new Setting<Boolean>("All", false));
        this.obby = this.register(new Setting<Object>("Obsidian", Boolean.valueOf(false), object -> this.all.getValue() == false));
        this.enderc = this.register(new Setting<Object>("EnderChest", Boolean.valueOf(false), object -> this.all.getValue() == false));
        this.crystals = this.register(new Setting<Object>("Crystals", Boolean.valueOf(false), object -> this.all.getValue() == false));
        this.exp = this.register(new Setting<Object>("Experience", Boolean.valueOf(false), object -> this.all.getValue() == false));
        this.minecart = this.register(new Setting<Object>("Minecarts", Boolean.valueOf(false), object -> this.all.getValue() == false));
        this.packetCrystal = this.register(new Setting<Boolean>("PacketCrystal", false));
        this.strict = this.register(new Setting<Boolean>("Strict", false));
        this.mousePos = null;
    }
}

