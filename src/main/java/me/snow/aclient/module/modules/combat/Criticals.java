//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.network.play.client.CPacketUseEntity$Action
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.combat;

import java.util.Objects;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.Timer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Criticals
extends Module {
    private static /* synthetic */ Criticals INSTANCE;
    public /* synthetic */ Setting<Boolean> noDesync;
    private final /* synthetic */ Setting<Integer> packets;
    private /* synthetic */ boolean resetTimer;
    private final /* synthetic */ Timer timer32k;
    private final /* synthetic */ Timer timer;
    public /* synthetic */ Setting<Boolean> cancelFirst;
    public /* synthetic */ Setting<Integer> delay32k;
    private /* synthetic */ boolean firstCanceled;
    public final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<Integer> desyncDelay;

    public static Criticals getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Criticals();
        }
        return INSTANCE;
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        CPacketUseEntity cPacketUseEntity;
        if (send.getPacket() instanceof CPacketUseEntity && (cPacketUseEntity = (CPacketUseEntity)send.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK) {
            if (this.firstCanceled) {
                this.timer32k.reset();
                this.resetTimer = true;
                this.timer.setMs(this.desyncDelay.getValue() + 1);
                this.firstCanceled = false;
                return;
            }
            if (this.resetTimer && !this.timer32k.passedMs(this.delay32k.getValue().intValue())) {
                return;
            }
            if (this.resetTimer && this.timer32k.passedMs(this.delay32k.getValue().intValue())) {
                this.resetTimer = false;
            }
            if (!this.timer.passedMs(this.desyncDelay.getValue().intValue())) {
                return;
            }
            if (!(!Criticals.mc.player.onGround || Criticals.mc.gameSettings.keyBindJump.isKeyDown() || !(cPacketUseEntity.getEntityFromWorld((World)Criticals.mc.world) instanceof EntityLivingBase) && this.noDesync.getValue().booleanValue() || Criticals.mc.player.isInWater() || Criticals.mc.player.isInLava())) {
                if (this.mode.getValue() == Mode.PACKET) {
                    switch (this.packets.getValue()) {
                        case 1: {
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + (double)0.1f, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            break;
                        }
                        case 2: {
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.0625101, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 1.1E-5, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            break;
                        }
                        case 3: {
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.0625101, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.0125, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            break;
                        }
                        case 4: {
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.05, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.03, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            break;
                        }
                        case 5: {
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.1625, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 4.0E-6, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 1.0E-6, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                            Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer());
                            Criticals.mc.player.onCriticalHit(Objects.requireNonNull(cPacketUseEntity.getEntityFromWorld((World)Criticals.mc.world)));
                        }
                    }
                }
                if (this.mode.getValue() == Mode.NCPStrict) {
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.11, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 0.1100013579, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + 1.3579E-6, Criticals.mc.player.posZ, false));
                    Criticals.mc.player.onCriticalHit(Objects.requireNonNull(cPacketUseEntity.getEntityFromWorld((World)Criticals.mc.world)));
                }
                if (this.mode.getValue() == Mode.JUMP) {
                    Criticals.mc.player.jump();
                }
                if (this.mode.getValue() == Mode.MINIJUMP) {
                    Criticals.mc.player.motionY /= 2.0;
                }
                this.timer.reset();
            }
        }
    }

    @Override
    public String getDisplayInfo() {
        switch (this.mode.getValue()) {
            case JUMP: {
                return "Jump";
            }
            case MINIJUMP: {
                return "MiniJump";
            }
            case PACKET: {
                return "Packet";
            }
            case NCPStrict: {
                return "NCPStrict";
            }
        }
        return null;
    }

    public Criticals() {
        super("Criticals", "Scores criticals for you", Module.Category.COMBAT, true, false, false);
        this.mode = this.register(new Setting<Mode>("Mode", Mode.PACKET));
        this.packets = this.register(new Setting<Object>("Packets", 2, 1, 5, object -> this.mode.getValue() == Mode.PACKET, "Amount of packets you want to send."));
        this.desyncDelay = this.register(new Setting<Object>("DesyncDelay", 10, 0, 500, object -> this.mode.getValue() == Mode.PACKET, "Amount of packets you want to send."));
        this.timer = new Timer();
        this.timer32k = new Timer();
        this.noDesync = this.register(new Setting<Boolean>("NoDesync", true));
        this.cancelFirst = this.register(new Setting<Boolean>("CancelFirst32k", true));
        this.delay32k = this.register(new Setting<Object>("32kDelay", Integer.valueOf(25), Integer.valueOf(0), Integer.valueOf(500), object -> this.cancelFirst.getValue()));
    }

    public static enum Mode {
        JUMP,
        MINIJUMP,
        PACKET,
        NCPStrict;

    }
}

