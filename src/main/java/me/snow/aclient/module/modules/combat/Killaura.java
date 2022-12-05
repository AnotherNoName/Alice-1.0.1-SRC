//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.item.ItemSword
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.util.EnumHand
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.combat;

import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.DamageUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Killaura
extends Module {
    public /* synthetic */ Setting<Boolean> delay;
    public /* synthetic */ Setting<Boolean> projectiles;
    public /* synthetic */ Setting<Boolean> animals;
    public /* synthetic */ Setting<Boolean> tps;
    public /* synthetic */ Setting<Boolean> players;
    public /* synthetic */ Setting<Boolean> onlySharp;
    public /* synthetic */ Setting<Boolean> autoSwitch;
    public static /* synthetic */ Entity target;
    public /* synthetic */ Setting<Float> range;
    public /* synthetic */ Setting<Boolean> eating;
    public /* synthetic */ Setting<Float> health;
    private final /* synthetic */ Setting<TargetMode> targetMode;
    public /* synthetic */ Setting<Boolean> armorBreak;
    public /* synthetic */ Setting<Boolean> stay;
    public /* synthetic */ Setting<Boolean> vehicles;
    public /* synthetic */ Setting<Boolean> packet;
    public /* synthetic */ Setting<Float> raytrace;
    public /* synthetic */ Setting<Boolean> mobs;
    public /* synthetic */ Setting<Boolean> sneak;
    public /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Timer timer;

    @SubscribeEvent
    public void onUpdateWalkingPlayerEvent(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 0 && this.rotate.getValue().booleanValue()) {
            if (this.stay.getValue().booleanValue() && target != null) {
                AliceMain.rotationManager.lookAtEntity(target);
            }
            this.doKillaura();
        }
    }

    @Override
    public String getDisplayInfo() {
        if (target instanceof EntityPlayer) {
            return String.valueOf(new StringBuilder().append(target.getName()).append(""));
        }
        return null;
    }

    @Override
    public void onTick() {
        if (!this.rotate.getValue().booleanValue()) {
            this.doKillaura();
        }
    }

    private Entity getTarget() {
        Entity entity = null;
        double d = this.range.getValue().floatValue();
        double d2 = 36.0;
        for (Entity entity2 : Killaura.mc.world.loadedEntityList) {
            if (!(this.players.getValue() != false && entity2 instanceof EntityPlayer || this.animals.getValue() != false && EntityUtil.isPassive(entity2) || this.mobs.getValue() != false && EntityUtil.isMobAggressive(entity2) || this.vehicles.getValue() != false && EntityUtil.isVehicle(entity2)) && (!this.projectiles.getValue().booleanValue() || !EntityUtil.isProjectile(entity2)) || entity2 instanceof EntityLivingBase && EntityUtil.isntValid(entity2, d) || !Killaura.mc.player.canEntityBeSeen(entity2) && !EntityUtil.canEntityFeetBeSeen(entity2) && Killaura.mc.player.getDistanceSqToEntity(entity2) > MathUtil.square(this.raytrace.getValue().floatValue())) continue;
            if (entity == null) {
                entity = entity2;
                d = Killaura.mc.player.getDistanceSqToEntity(entity2);
                d2 = EntityUtil.getHealth(entity2);
                continue;
            }
            if (entity2 instanceof EntityPlayer && DamageUtil.isArmorLow((EntityPlayer)entity2, 18)) {
                entity = entity2;
                break;
            }
            if (this.targetMode.getValue() == TargetMode.SMART && EntityUtil.getHealth(entity2) < this.health.getValue().floatValue()) {
                entity = entity2;
                break;
            }
            if (this.targetMode.getValue() != TargetMode.HEALTH && Killaura.mc.player.getDistanceSqToEntity(entity2) < d) {
                entity = entity2;
                d = Killaura.mc.player.getDistanceSqToEntity(entity2);
                d2 = EntityUtil.getHealth(entity2);
            }
            if (this.targetMode.getValue() != TargetMode.HEALTH || !((double)EntityUtil.getHealth(entity2) < d2)) continue;
            entity = entity2;
            d = Killaura.mc.player.getDistanceSqToEntity(entity2);
            d2 = EntityUtil.getHealth(entity2);
        }
        return entity;
    }

    public Killaura() {
        super("Killaura", "Kills aura.", Module.Category.COMBAT, true, false, false);
        this.timer = new Timer();
        this.range = this.register(new Setting<Float>("Range", Float.valueOf(6.0f), Float.valueOf(0.1f), Float.valueOf(7.0f)));
        this.raytrace = this.register(new Setting<Float>("WallRange", Float.valueOf(6.0f), Float.valueOf(0.1f), Float.valueOf(7.0f), "Wall Range."));
        this.targetMode = this.register(new Setting<TargetMode>("Target", TargetMode.CLOSEST));
        this.health = this.register(new Setting<Object>("Health", Float.valueOf(6.0f), Float.valueOf(0.1f), Float.valueOf(36.0f), object -> this.targetMode.getValue() == TargetMode.SMART));
        this.autoSwitch = this.register(new Setting<Boolean>("AutoSwitch", false));
        this.delay = this.register(new Setting<Boolean>("Delay", true));
        this.rotate = this.register(new Setting<Boolean>("Rotate", true));
        this.stay = this.register(new Setting<Object>("Stay", Boolean.TRUE, object -> this.rotate.getValue()));
        this.armorBreak = this.register(new Setting<Boolean>("ArmorBreak", false));
        this.eating = this.register(new Setting<Boolean>("While-Eating", true));
        this.onlySharp = this.register(new Setting<Boolean>("Axe/Sword", true));
        this.players = this.register(new Setting<Boolean>("Players", true));
        this.mobs = this.register(new Setting<Boolean>("Monsters", false));
        this.animals = this.register(new Setting<Boolean>("Animals", false));
        this.vehicles = this.register(new Setting<Boolean>("Entities", false));
        this.projectiles = this.register(new Setting<Boolean>("Projectiles", false));
        this.tps = this.register(new Setting<Boolean>("TPS-Sync", true));
        this.packet = this.register(new Setting<Boolean>("Packet-Attack", false));
        this.sneak = this.register(new Setting<Boolean>("Sneak", false));
    }

    private void doKillaura() {
        int n;
        int n2;
        if (this.onlySharp.getValue().booleanValue() && !EntityUtil.holdingWeapon((EntityPlayer)Killaura.mc.player)) {
            target = null;
            return;
        }
        int n3 = this.delay.getValue() == false ? 0 : (n2 = (int)((float)DamageUtil.getCooldownByWeapon((EntityPlayer)Killaura.mc.player) * (this.tps.getValue() != false ? AliceMain.serverManager.getTpsFactor() : 1.0f)));
        if (!this.timer.passedMs(n2) || !this.eating.getValue().booleanValue() && Killaura.mc.player.isHandActive() && (!Killaura.mc.player.getHeldItemOffhand().getItem().equals((Object)Items.SHIELD) || Killaura.mc.player.getActiveHand() != EnumHand.OFF_HAND)) {
            return;
        }
        if (this.targetMode.getValue() != TargetMode.FOCUS || target == null || !(Killaura.mc.player.getDistanceSqToEntity(target) < MathUtil.square(this.range.getValue().floatValue())) || !Killaura.mc.player.canEntityBeSeen(target) && !EntityUtil.canEntityFeetBeSeen(target) && !(Killaura.mc.player.getDistanceSqToEntity(target) < MathUtil.square(this.raytrace.getValue().floatValue()))) {
            target = this.getTarget();
        }
        if (target == null) {
            return;
        }
        if (this.autoSwitch.getValue().booleanValue() && (n = InventoryUtil.findHotbarBlock(ItemSword.class)) != -1) {
            InventoryUtil.switchToHotbarSlot(n, false);
        }
        if (this.rotate.getValue().booleanValue()) {
            AliceMain.rotationManager.lookAtEntity(target);
        }
        if (this.armorBreak.getValue().booleanValue()) {
            Killaura.mc.playerController.windowClick(Killaura.mc.player.inventoryContainer.windowId, 9, Killaura.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)Killaura.mc.player);
            EntityUtil.attackEntity(target, this.packet.getValue(), true);
            Killaura.mc.playerController.windowClick(Killaura.mc.player.inventoryContainer.windowId, 9, Killaura.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)Killaura.mc.player);
            EntityUtil.attackEntity(target, this.packet.getValue(), true);
        } else {
            boolean bl = Killaura.mc.player.isSneaking();
            boolean bl2 = Killaura.mc.player.isSprinting();
            if (this.sneak.getValue().booleanValue()) {
                if (bl) {
                    Killaura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Killaura.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                }
                if (bl2) {
                    Killaura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Killaura.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
                }
            }
            EntityUtil.attackEntity(target, this.packet.getValue(), true);
            if (this.sneak.getValue().booleanValue()) {
                if (bl2) {
                    Killaura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Killaura.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                }
                if (bl) {
                    Killaura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Killaura.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                }
            }
        }
        this.timer.reset();
    }

    public static enum TargetMode {
        FOCUS,
        CLOSEST,
        HEALTH,
        SMART;

    }
}

