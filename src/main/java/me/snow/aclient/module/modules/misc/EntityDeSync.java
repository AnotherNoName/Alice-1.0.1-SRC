//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketVehicleMove
 *  net.minecraft.network.play.server.SPacketDestroyEntities
 *  net.minecraft.network.play.server.SPacketSetPassengers
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import me.snow.aclient.command.Command;
import me.snow.aclient.event.events.EventPlayerUpdate;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraft.network.play.server.SPacketDestroyEntities;
import net.minecraft.network.play.server.SPacketSetPassengers;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityDeSync
extends Module {
    static /* synthetic */ EntityDeSync INSTANCE;
    private /* synthetic */ Entity Riding;

    @SubscribeEvent
    public void OnPlayerUpdate(EventPlayerUpdate eventPlayerUpdate) {
        if (this.Riding == null) {
            return;
        }
        if (EntityDeSync.mc.player.isRiding()) {
            return;
        }
        EntityDeSync.mc.player.onGround = true;
        this.Riding.setPosition(EntityDeSync.mc.player.posX, EntityDeSync.mc.player.posY, EntityDeSync.mc.player.posZ);
        EntityDeSync.mc.player.connection.sendPacket((Packet)new CPacketVehicleMove(this.Riding));
    }

    public EntityDeSync() {
        super("EntityDeSync", "Forces shit with entites", Module.Category.MISC, true, false, false);
        this.Riding = null;
    }

    @SubscribeEvent
    public void OnWorldEvent(EntityJoinWorldEvent entityJoinWorldEvent) {
        if (entityJoinWorldEvent.getEntity() == EntityDeSync.mc.player) {
            Command.sendMessage(String.valueOf(new StringBuilder().append("Player ").append(entityJoinWorldEvent.getEntity().getName()).append(" joined the world!")));
        }
    }

    @SubscribeEvent
    public void onPacket(PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketSetPassengers) {
            if (this.Riding == null) {
                return;
            }
            SPacketSetPassengers sPacketSetPassengers = (SPacketSetPassengers)receive.getPacket();
            Entity entity = EntityDeSync.mc.world.getEntityByID(sPacketSetPassengers.getEntityId());
            if (entity == this.Riding) {
                for (int n : sPacketSetPassengers.getPassengerIds()) {
                    Entity entity2 = EntityDeSync.mc.world.getEntityByID(n);
                    if (entity2 != EntityDeSync.mc.player) continue;
                    return;
                }
                Command.sendMessage("You dismounted");
                this.toggle();
            }
        } else if (receive.getPacket() instanceof SPacketDestroyEntities) {
            SPacketDestroyEntities sPacketDestroyEntities = (SPacketDestroyEntities)receive.getPacket();
            for (int n : sPacketDestroyEntities.getEntityIDs()) {
                if (n != this.Riding.getEntityId()) continue;
                Command.sendMessage("Entity is now null!");
                return;
            }
        }
    }

    public void onInit() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        if (EntityDeSync.mc.player == null) {
            this.Riding = null;
            this.toggle();
            return;
        }
        if (!EntityDeSync.mc.player.isRiding()) {
            Command.sendMessage("You are not riding an entity.");
            this.Riding = null;
            this.toggle();
            return;
        }
        Command.sendMessage("Vanished");
        this.Riding = EntityDeSync.mc.player.getRidingEntity();
        EntityDeSync.mc.player.dismountRidingEntity();
        EntityDeSync.mc.world.removeEntity(this.Riding);
    }

    @Override
    public void onDisable() {
        if (this.Riding != null) {
            this.Riding.isDead = false;
            if (!EntityDeSync.mc.player.isRiding()) {
                EntityDeSync.mc.world.spawnEntityInWorld(this.Riding);
                EntityDeSync.mc.player.startRiding(this.Riding, true);
            }
            this.Riding = null;
            Command.sendMessage("Remounted.");
        }
    }
}

