//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemShulkerBox
 *  net.minecraft.item.ItemStack
 */
package me.snow.aclient.command.commands;

import java.util.Map;
import me.snow.aclient.command.Command;
import me.snow.aclient.module.modules.misc.ToolTips;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.item.ItemStack;

public class PeekCommand
extends Command {
    public PeekCommand() {
        super("peek", new String[]{"<player>"});
    }

    @Override
    public void execute(String[] arrstring) {
        if (arrstring.length == 1) {
            ItemStack itemStack = PeekCommand.mc.player.getHeldItemMainhand();
            if (itemStack.getItem() instanceof ItemShulkerBox) {
                ToolTips.displayInv(itemStack, null);
            } else {
                Command.sendMessage("\u00a7cYou need to hold a Shulker in your mainhand.");
                return;
            }
        }
        if (arrstring.length > 1) {
            if (ToolTips.getInstance().isOn() && ToolTips.getInstance().shulkerSpy.getValue().booleanValue()) {
                for (Map.Entry entry : ToolTips.getInstance().spiedPlayers.entrySet()) {
                    if (!((EntityPlayer)entry.getKey()).getName().equalsIgnoreCase(arrstring[0])) continue;
                    ItemStack itemStack = (ItemStack)entry.getValue();
                    ToolTips.displayInv(itemStack, ((EntityPlayer)entry.getKey()).getName());
                    break;
                }
            } else {
                Command.sendMessage("\u00a7cYou need to turn on Tooltips - ShulkerSpy");
            }
        }
    }
}

