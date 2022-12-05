//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.play.server.SPacketPlayerListItem
 *  net.minecraft.network.play.server.SPacketPlayerListItem$Action
 *  net.minecraft.network.play.server.SPacketPlayerListItem$AddPlayerData
 *  net.minecraft.potion.Potion
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import java.util.Objects;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.PlayerUtil;
import net.minecraft.network.play.server.SPacketPlayerListItem;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AntiEffect
extends Module {
    public /* synthetic */ Setting<Boolean> antivan;
    public /* synthetic */ Setting<Boolean> antilev;
    private final /* synthetic */ Queue<UUID> toLookUp;

    @Override
    public void onUpdate() {
        UUID uUID;
        if (PlayerUtil.timer.passedS(5.0) && (uUID = this.toLookUp.poll()) != null && this.antivan.getValue().booleanValue()) {
            try {
                String string = PlayerUtil.getNameFromUUID(uUID);
                if (string != null) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append("\u00a7c").append(string).append(" has gone into vanish.")));
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            PlayerUtil.timer.reset();
        }
        if (this.antilev.getValue().booleanValue() && AntiEffect.mc.player.isPotionActive(Objects.requireNonNull(Potion.getPotionFromResourceLocation((String)"levitation")))) {
            AntiEffect.mc.player.removeActivePotionEffect(Potion.getPotionFromResourceLocation((String)"levitation"));
        }
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        SPacketPlayerListItem sPacketPlayerListItem;
        if (receive.getPacket() instanceof SPacketPlayerListItem && (sPacketPlayerListItem = (SPacketPlayerListItem)receive.getPacket()).getAction() == SPacketPlayerListItem.Action.UPDATE_LATENCY && this.antivan.getValue().booleanValue()) {
            for (SPacketPlayerListItem.AddPlayerData addPlayerData : sPacketPlayerListItem.getEntries()) {
                try {
                    if (mc.getConnection().getPlayerInfo(addPlayerData.getProfile().getId()) != null) continue;
                    this.toLookUp.add(addPlayerData.getProfile().getId());
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                    return;
                }
            }
        }
    }

    @Override
    public void onLogout() {
        this.toLookUp.clear();
    }

    public AntiEffect() {
        super("AntiEffect", "Notifies you when players vanish", Module.Category.MISC, true, false, false);
        this.toLookUp = new ConcurrentLinkedQueue<UUID>();
        this.antilev = this.register(new Setting<Boolean>("AntiLevitate", false));
        this.antivan = this.register(new Setting<Boolean>("AntiVanish", false));
    }
}

