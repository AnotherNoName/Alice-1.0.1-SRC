//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.ConnectionEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.Timer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Announcer
extends Module {
    private /* synthetic */ Random random;
    public static /* synthetic */ String cz;
    private /* synthetic */ Random randomEventChooser;
    private final /* synthetic */ Setting<Boolean> clientside;
    static /* synthetic */ double lastPositionY;
    private /* synthetic */ Timer announceTimer;
    private /* synthetic */ Timer welcomeTimer;
    static /* synthetic */ double lastPositionX;
    private /* synthetic */ LinkedHashMap<Type, Integer> events;
    private final /* synthetic */ Setting<Integer> welcomeDelay;
    public static final /* synthetic */ ArrayList<String> GOODBYES;
    private final /* synthetic */ Setting<Boolean> advertisement;
    public static final /* synthetic */ ArrayList<String> WELCOMES;
    private final /* synthetic */ Setting<Boolean> welcome;
    static /* synthetic */ double lastPositionZ;

    private void addEvent(Type type2) {
        if (this.events.containsKey((Object)type2)) {
            this.events.put(type2, this.events.get((Object)type2) + 1);
        } else {
            this.events.put(type2, 1);
        }
    }

    private double getBlocksWalked(double d, double d2, double d3) {
        double d4 = d - Announcer.mc.player.posX;
        double d5 = d2 - Announcer.mc.player.posY;
        double d6 = d3 - Announcer.mc.player.posZ;
        return Math.sqrt(d4 * d4 + d5 * d5 + d6 * d6);
    }

    static {
        cz = "ps://paste";
        WELCOMES = new ArrayList();
        GOODBYES = new ArrayList();
    }

    public void sendWelcome(String string) {
        String string2;
        String string3 = string2 = this.advertisement.getValue() != false ? " Alice|.gg/EtTGvzt3nS" : "";
        if (this.clientside.getValue().booleanValue()) {
            Command.sendMessage(String.valueOf(new StringBuilder().append(WELCOMES.get(this.random.nextInt(WELCOMES.size())).replace("<player>", string)).append(string2)));
        } else {
            Announcer.mc.player.sendChatMessage(String.valueOf(new StringBuilder().append(WELCOMES.get(this.random.nextInt(WELCOMES.size())).replace("<player>", string)).append(string2)));
        }
    }

    public Announcer() {
        super("Announcer", "How to get muted quick.", Module.Category.MISC, true, false, false);
        this.welcomeDelay = this.register(new Setting<Integer>("WelcomeDelay", 2, 0, 60));
        this.welcome = this.register(new Setting<Boolean>("Welcome", true));
        this.clientside = this.register(new Setting<Boolean>("ClientSide", true));
        this.advertisement = this.register(new Setting<Boolean>("Advertisement", false));
        this.random = new Random();
        this.randomEventChooser = new Random();
        this.welcomeTimer = new Timer();
        this.announceTimer = new Timer();
        this.events = new LinkedHashMap();
        WELCOMES.add("Welcome <player>!");
        WELCOMES.add("Hello <player>");
        WELCOMES.add("Nice weather isn't it, <player>");
        GOODBYES.add("Goodbye <player>!");
        GOODBYES.add("Have a good day <player>");
    }

    @Override
    public void onUpdate() {
        if (Announcer.mc.player == null || Announcer.mc.world == null) {
            return;
        }
    }

    private String getMessage(Type type2, int n) {
        String string = this.advertisement.getValue() != false ? " thanks to AliceClient! | .gg/EtTGvzt3nS" : "!";
        String string2 = n > 1 ? "s" : "";
        switch (type2) {
            case PICKUP: {
                return String.valueOf(new StringBuilder().append("I just picked up ").append(n).append(" item").append(string2).append(string));
            }
            case BREAK: {
                return String.valueOf(new StringBuilder().append("I just broke ").append(n).append(" block").append(string2).append(string));
            }
            case PLACE: {
                return String.valueOf(new StringBuilder().append("I just placed ").append(n).append(" block").append(string2).append(string));
            }
            case DROP: {
                return String.valueOf(new StringBuilder().append("I just dropped ").append(n).append(" item").append(string2).append(string));
            }
            case EAT: {
                return String.valueOf(new StringBuilder().append("I just ate ").append(n).append(" item").append(string2).append(string));
            }
        }
        return String.valueOf(new StringBuilder().append("I just did ").append((Object)type2).append(" ").append(n).append(" time").append(string2).append(string));
    }

    public void sendGoodbye(String string) {
        String string2;
        String string3 = string2 = this.advertisement.getValue() != false ? " Alice|.gg/EtTGvzt3nS" : "";
        if (this.clientside.getValue().booleanValue()) {
            Command.sendMessage(String.valueOf(new StringBuilder().append(GOODBYES.get(this.random.nextInt(GOODBYES.size())).replace("<player>", string)).append(string2)));
        } else {
            Announcer.mc.player.sendChatMessage(String.valueOf(new StringBuilder().append(GOODBYES.get(this.random.nextInt(GOODBYES.size())).replace("<player>", string)).append(string2)));
        }
    }

    @SubscribeEvent
    public void onConnection(ConnectionEvent connectionEvent) {
        EntityPlayer entityPlayer;
        if (Announcer.mc.player == null || Announcer.mc.world == null) {
            return;
        }
        if (connectionEvent.getStage() == 0) {
            if (this.welcome.getValue().booleanValue() && Announcer.mc.player.ticksExisted > 100 && connectionEvent.getUuid() != Announcer.mc.player.getUniqueID() && this.welcomeTimer.passedS(this.welcomeDelay.getValue().intValue())) {
                this.sendWelcome(connectionEvent.getName());
                this.welcomeTimer.reset2();
            }
        } else if (connectionEvent.getStage() == 1 && this.welcome.getValue().booleanValue() && Announcer.mc.player.ticksExisted > 100 && connectionEvent.getUuid() != Announcer.mc.player.getUniqueID() && this.welcomeTimer.passedS(this.welcomeDelay.getValue().intValue()) && (entityPlayer = Announcer.mc.world.getPlayerEntityByUUID(connectionEvent.getUuid())) != null) {
            this.sendGoodbye(entityPlayer.getName());
            this.welcomeTimer.reset2();
        }
    }

    private static enum Type {
        PLACE,
        BREAK,
        EAT,
        DROP,
        PICKUP;

    }
}

