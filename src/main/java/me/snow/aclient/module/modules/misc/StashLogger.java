//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.play.server.SPacketChunkData
 *  net.minecraft.util.SoundEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class StashLogger
extends Module {
    private final /* synthetic */ Setting<Boolean> writeToFile;
    private final /* synthetic */ Setting<Boolean> chests;
    final /* synthetic */ Iterator<NBTTagCompound> iterator;
    private final /* synthetic */ Setting<Integer> chestsValue;
    private final /* synthetic */ Setting<Boolean> Shulkers;
    private final /* synthetic */ Setting<Integer> shulkersValue;
    /* synthetic */ File mainFolder;

    public StashLogger() {
        super("StashLogger", "Logs stashes", Module.Category.MISC, true, false, false);
        this.chests = this.register(new Setting<Boolean>("Chests", true));
        this.chestsValue = this.register(new Setting<Object>("ChestsValue", Integer.valueOf(4), Integer.valueOf(1), Integer.valueOf(30), object -> this.chests.getValue()));
        this.Shulkers = this.register(new Setting<Boolean>("Shulkers", true));
        this.shulkersValue = this.register(new Setting<Object>("ShulkersValue", Integer.valueOf(4), Integer.valueOf(1), Integer.valueOf(30), object -> this.Shulkers.getValue()));
        this.writeToFile = this.register(new Setting<Boolean>("CoordsSaver", true));
        this.mainFolder = new File(String.valueOf(new StringBuilder().append(Minecraft.getMinecraft().mcDataDir).append(File.separator).append("Alice")));
        this.iterator = null;
    }

    private void SendMessage(String string, boolean bl) {
        String string2;
        String string3 = string2 = Minecraft.getMinecraft().isSingleplayer() ? "singleplayer".toUpperCase() : StashLogger.mc.getCurrentServerData().serverIP;
        if (this.writeToFile.getValue().booleanValue() && bl) {
            try {
                FileWriter fileWriter = new FileWriter(String.valueOf(new StringBuilder().append(this.mainFolder).append("/stashes.txt")), true);
                fileWriter.write(String.valueOf(new StringBuilder().append("[").append(string2).append("]: ").append(string).append("\n")));
                fileWriter.close();
            }
            catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
        mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, (float)1.0f, (float)1.0f));
        Command.sendMessage(String.valueOf(new StringBuilder().append((Object)ChatFormatting.GREEN).append(string)));
    }

    @SubscribeEvent
    public void onPacket(PacketEvent packetEvent) {
        if (StashLogger.nullCheck()) {
            return;
        }
        if (packetEvent.getPacket() instanceof SPacketChunkData) {
            SPacketChunkData sPacketChunkData = (SPacketChunkData)packetEvent.getPacket();
            int n = 0;
            int n2 = 0;
            for (NBTTagCompound nBTTagCompound : sPacketChunkData.getTileEntityTags()) {
                String string = nBTTagCompound.getString("id");
                if (string.equals("minecraft:chest") && this.chests.getValue().booleanValue()) {
                    ++n;
                    continue;
                }
                if (!string.equals("minecraft:shulker_box") || !this.Shulkers.getValue().booleanValue()) continue;
                ++n2;
            }
            if (n >= this.chestsValue.getValue()) {
                this.SendMessage(String.format("%s chests located at X: %s, Z: %s", n, sPacketChunkData.getChunkX() * 16, sPacketChunkData.getChunkZ() * 16), true);
            }
            if (n2 >= this.shulkersValue.getValue()) {
                this.SendMessage(String.format("%s shulker boxes at X: %s, Z: %s", n2, sPacketChunkData.getChunkX() * 16, sPacketChunkData.getChunkZ() * 16), true);
            }
        }
    }
}

