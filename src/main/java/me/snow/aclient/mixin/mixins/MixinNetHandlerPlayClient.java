//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.play.server.SPacketEntityMetadata
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package me.snow.aclient.mixin.mixins;

import me.snow.aclient.AliceMain;
import me.snow.aclient.event.events.DeathEvent;
import me.snow.aclient.util.Util;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketEntityMetadata;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={NetHandlerPlayClient.class})
public class MixinNetHandlerPlayClient {
    @Inject(method={"handleEntityMetadata"}, at={@At(value="RETURN")}, cancellable=true)
    private void handleEntityMetadataHook(SPacketEntityMetadata sPacketEntityMetadata, CallbackInfo callbackInfo) {
        Entity entity;
        if (Util.mc.world != null && (entity = Util.mc.world.getEntityByID(sPacketEntityMetadata.getEntityId())) instanceof EntityPlayer) {
            EntityPlayer entityPlayer;
            EntityPlayer entityPlayer2 = (EntityPlayer)entity;
            if (entityPlayer.getHealth() <= 0.0f) {
                MinecraftForge.EVENT_BUS.post((Event)new DeathEvent(entityPlayer2));
                if (AliceMain.totemPopManager != null) {
                    AliceMain.totemPopManager.onDeath(entityPlayer2);
                }
            }
        }
    }
}

