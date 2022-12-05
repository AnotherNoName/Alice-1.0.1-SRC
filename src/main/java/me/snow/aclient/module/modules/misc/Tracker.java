//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.item.EntityExpBottle
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.network.play.server.SPacketChat
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.ConnectionEvent;
import me.snow.aclient.event.events.DeathEvent;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.combat.AntiTrap;
import me.snow.aclient.module.modules.combat.autocrystal.AutoCrystal;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Tracker
extends Module {
    public /* synthetic */ Setting<Boolean> autoEnable;
    private final /* synthetic */ Set<BlockPos> manuallyPlaced;
    public /* synthetic */ Setting<Boolean> autoDisable;
    private /* synthetic */ EntityPlayer trackedPlayer;
    private /* synthetic */ int usedExp;
    private /* synthetic */ int usedCStacks;
    private /* synthetic */ int usedStacks;
    private final /* synthetic */ Timer timer;
    private /* synthetic */ int usedCrystals;
    private /* synthetic */ boolean shouldEnable;
    private static /* synthetic */ Tracker instance;

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        if (!Tracker.fullNullCheck() && this.isOn() && send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock = (CPacketPlayerTryUseItemOnBlock)send.getPacket();
            if (Tracker.mc.player.getHeldItem(cPacketPlayerTryUseItemOnBlock.hand).getItem() == Items.END_CRYSTAL && !AntiTrap.placedPos.contains((Object)cPacketPlayerTryUseItemOnBlock.position) && !AutoCrystal.placedPos.contains((Object)cPacketPlayerTryUseItemOnBlock.position)) {
                this.manuallyPlaced.add(cPacketPlayerTryUseItemOnBlock.position);
            }
        }
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (this.shouldEnable && this.timer.passedS(5.0) && this.isOff()) {
            this.enable();
        }
    }

    @Override
    public void onUpdate() {
        if (this.isOff()) {
            return;
        }
        if (this.trackedPlayer == null) {
            this.trackedPlayer = EntityUtil.getClosestEnemy(1000.0);
        } else {
            if (this.usedStacks != this.usedExp / 64) {
                this.usedStacks = this.usedExp / 64;
                Command.sendMessage(String.valueOf(new StringBuilder().append((Object)ChatFormatting.WHITE).append("<Tracker> ").append((Object)ChatFormatting.DARK_PURPLE).append(this.trackedPlayer.getName()).append((Object)ChatFormatting.LIGHT_PURPLE).append(" used ").append((Object)ChatFormatting.WHITE).append(this.usedStacks).append((Object)ChatFormatting.LIGHT_PURPLE).append(" Stacks of EXP!")));
            }
            if (this.usedCStacks != this.usedCrystals / 64) {
                this.usedCStacks = this.usedCrystals / 64;
                Command.sendMessage(String.valueOf(new StringBuilder().append((Object)ChatFormatting.WHITE).append("<Tracker> ").append((Object)ChatFormatting.DARK_PURPLE).append(this.trackedPlayer.getName()).append((Object)ChatFormatting.LIGHT_PURPLE).append(" used: ").append((Object)ChatFormatting.WHITE).append(this.usedCStacks).append((Object)ChatFormatting.LIGHT_PURPLE).append(" Stacks of Crystals!")));
            }
        }
    }

    @Override
    public String getDisplayInfo() {
        if (this.trackedPlayer != null) {
            return this.trackedPlayer.getName();
        }
        return null;
    }

    @SubscribeEvent
    public void onDeath(DeathEvent deathEvent) {
        if (this.isOn() && (deathEvent.player.equals((Object)this.trackedPlayer) || deathEvent.player.equals((Object)Tracker.mc.player))) {
            this.usedExp = 0;
            this.usedStacks = 0;
            this.usedCrystals = 0;
            this.usedCStacks = 0;
            if (this.autoDisable.getValue().booleanValue()) {
                this.disable();
            }
        }
    }

    @SubscribeEvent
    public void onConnection(ConnectionEvent connectionEvent) {
        if (this.isOff() || connectionEvent.getStage() != 1) {
            return;
        }
        String string = connectionEvent.getName();
        if (this.trackedPlayer != null && string != null && string.equals(this.trackedPlayer.getName()) && this.autoDisable.getValue().booleanValue()) {
            Command.sendMessage(String.valueOf(new StringBuilder().append(string).append(" logged, Tracker disabling.")));
            this.disable();
        }
    }

    @Override
    public void onToggle() {
        this.manuallyPlaced.clear();
        AntiTrap.placedPos.clear();
        this.shouldEnable = false;
        this.trackedPlayer = null;
        this.usedExp = 0;
        this.usedStacks = 0;
        this.usedCrystals = 0;
        this.usedCStacks = 0;
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (!Tracker.fullNullCheck() && (this.autoEnable.getValue().booleanValue() || this.autoDisable.getValue().booleanValue()) && receive.getPacket() instanceof SPacketChat) {
            SPacketChat sPacketChat = (SPacketChat)receive.getPacket();
            String string = sPacketChat.getChatComponent().getFormattedText();
            if (this.autoEnable.getValue().booleanValue() && (string.contains("has accepted your duel request") || string.contains("Accepted the duel request from")) && !string.contains("<")) {
                Command.sendMessage("Tracker will enable in 5 seconds.");
                this.timer.reset();
                this.shouldEnable = true;
            } else if (this.autoDisable.getValue().booleanValue() && string.contains("has defeated") && string.contains(Tracker.mc.player.getName()) && !string.contains("<")) {
                this.disable();
            }
        }
    }

    @Override
    public void onLogout() {
        if (this.autoDisable.getValue().booleanValue()) {
            this.disable();
        }
    }

    public void onSpawnEntity(Entity entity) {
        if (this.isOff()) {
            return;
        }
        if (entity instanceof EntityExpBottle && Objects.equals((Object)Tracker.mc.world.getClosestPlayerToEntity(entity, 3.0), (Object)this.trackedPlayer)) {
            ++this.usedExp;
        }
        if (entity instanceof EntityEnderCrystal) {
            if (AntiTrap.placedPos.contains((Object)entity.getPosition().down())) {
                AntiTrap.placedPos.remove((Object)entity.getPosition().down());
            } else if (this.manuallyPlaced.contains((Object)entity.getPosition().down())) {
                this.manuallyPlaced.remove((Object)entity.getPosition().down());
            } else if (!AutoCrystal.placedPos.contains((Object)entity.getPosition().down())) {
                ++this.usedCrystals;
            }
        }
    }

    public static Tracker getInstance() {
        if (instance == null) {
            instance = new Tracker();
        }
        return instance;
    }

    public Tracker() {
        super("Tracker", "Tracks players in 1v1s. Only good in duels tho!", Module.Category.MISC, true, false, true);
        this.timer = new Timer();
        this.manuallyPlaced = new HashSet<BlockPos>();
        this.autoEnable = this.register(new Setting<Boolean>("AutoEnable", false));
        this.autoDisable = this.register(new Setting<Boolean>("AutoDisable", true));
        instance = this;
    }
}

