//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.network.play.server.SPacketEntityStatus
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package me.snow.aclient.manager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import me.snow.aclient.AliceMain;
import me.snow.aclient.event.EnumStages;
import me.snow.aclient.event.events.EventPacketRecieve;
import me.snow.aclient.event.events.EventTotemPop;
import me.snow.aclient.util.Util;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listenable;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TotemPopManager2
implements Util,
Listenable {
    public static /* synthetic */ ConcurrentHashMap<EntityLivingBase, Integer> totemMap;
    @EventHandler
    private final /* synthetic */ Listener<EventPacketRecieve> packetRecieveListener;

    public static int getPops(String string) {
        boolean bl = false;
        EntityLivingBase entityLivingBase = null;
        for (Entity entity : TotemPopManager2.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityLivingBase) || !entity.getName().equals(string)) continue;
            bl = true;
            entityLivingBase = (EntityLivingBase)entity;
            break;
        }
        if (bl && totemMap.containsKey((Object)entityLivingBase)) {
            return totemMap.get((Object)entityLivingBase);
        }
        return 0;
    }

    public TotemPopManager2() {
        SPacketEntityStatus[] arrsPacketEntityStatus = new SPacketEntityStatus[1];
        EntityLivingBase[] arrentityLivingBase = new EntityLivingBase[1];
        int[] arrn = new int[1];
        this.packetRecieveListener = new Listener<EventPacketRecieve>(eventPacketRecieve -> {
            if (TotemPopManager2.mc.player == null || TotemPopManager2.mc.world == null) {
                return;
            }
            if (eventPacketRecieve.getPacket() instanceof SPacketEntityStatus) {
                arrsPacketEntityStatus[0] = (SPacketEntityStatus)eventPacketRecieve.getPacket();
                arrentityLivingBase[0] = (EntityLivingBase)arrsPacketEntityStatus[0].getEntity((World)TotemPopManager2.mc.world);
                if (arrsPacketEntityStatus[0].getOpCode() == 35) {
                    if (totemMap.containsKey((Object)arrentityLivingBase[0])) {
                        arrn[0] = totemMap.get((Object)arrentityLivingBase[0]) + 1;
                        AliceMain.dispatcher.post(new EventTotemPop(EnumStages.PRE, arrentityLivingBase[0], arrn[0]));
                        totemMap.remove((Object)arrentityLivingBase[0]);
                        totemMap.put(arrentityLivingBase[0], arrn[0]);
                    } else {
                        AliceMain.dispatcher.post(new EventTotemPop(EnumStages.PRE, arrentityLivingBase[0], 1));
                        totemMap.put(arrentityLivingBase[0], 1);
                    }
                }
            }
        }, new Predicate[0]);
        totemMap = new ConcurrentHashMap();
        AliceMain.dispatcher.subscribe(this);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    private static void update() {
        for (EntityLivingBase entityLivingBase : totemMap.keySet()) {
            if (TotemPopManager2.mc.world.loadedEntityList.contains((Object)entityLivingBase)) continue;
            totemMap.remove((Object)entityLivingBase);
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent clientTickEvent) {
        if (TotemPopManager2.mc.player == null || TotemPopManager2.mc.world == null) {
            totemMap.clear();
            return;
        }
        TotemPopManager2.update();
    }

    public static int getPops(EntityLivingBase entityLivingBase) {
        if (totemMap.containsKey((Object)entityLivingBase)) {
            return totemMap.get((Object)entityLivingBase);
        }
        return 0;
    }
}

