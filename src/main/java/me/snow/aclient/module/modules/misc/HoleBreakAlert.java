//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.play.server.SPacketBlockBreakAnim
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import java.util.Objects;
import me.snow.aclient.command.Command;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.SPacketBlockBreakAnim;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HoleBreakAlert
extends Module {
    public static String getBlockDirectionFromPlayer(BlockPos blockPos) {
        double d = Math.floor(HoleBreakAlert.mc.player.posX);
        double d2 = Math.floor(HoleBreakAlert.mc.player.posZ);
        double d3 = d - (double)blockPos.getX();
        double d4 = d2 - (double)blockPos.getZ();
        switch (HoleBreakAlert.mc.player.getHorizontalFacing()) {
            case SOUTH: {
                if (d3 == 1.0) {
                    return "right";
                }
                if (d3 == -1.0) {
                    return "left";
                }
                if (d4 == 1.0) {
                    return "back";
                }
                if (d4 != -1.0) break;
                return "front";
            }
            case WEST: {
                if (d3 == 1.0) {
                    return "front";
                }
                if (d3 == -1.0) {
                    return "back";
                }
                if (d4 == 1.0) {
                    return "right";
                }
                if (d4 != -1.0) break;
                return "left";
            }
            case NORTH: {
                if (d3 == 1.0) {
                    return "left";
                }
                if (d3 == -1.0) {
                    return "right";
                }
                if (d4 == 1.0) {
                    return "front";
                }
                if (d4 != -1.0) break;
                return "back";
            }
            case EAST: {
                if (d3 == 1.0) {
                    return "back";
                }
                if (d3 == -1.0) {
                    return "front";
                }
                if (d4 == 1.0) {
                    return "left";
                }
                if (d4 != -1.0) break;
                return "right";
            }
            default: {
                return "undetermined";
            }
        }
        return null;
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        SPacketBlockBreakAnim sPacketBlockBreakAnim;
        if (receive.getPacket() instanceof SPacketBlockBreakAnim && this.isHoleBlock((sPacketBlockBreakAnim = (SPacketBlockBreakAnim)receive.getPacket()).getPosition())) {
            Command.sendMessage(String.valueOf(new StringBuilder().append("The hole block to your ").append(HoleBreakAlert.getBlockDirectionFromPlayer(sPacketBlockBreakAnim.getPosition())).append(" is being broken by ").append(Objects.requireNonNull(HoleBreakAlert.mc.world.getEntityByID(sPacketBlockBreakAnim.getBreakerId())).getName())));
        }
    }

    private boolean isHoleBlock(BlockPos blockPos) {
        double d = Math.floor(HoleBreakAlert.mc.player.posX);
        double d2 = Math.floor(HoleBreakAlert.mc.player.posZ);
        Block block = HoleBreakAlert.mc.world.getBlockState(blockPos).getBlock();
        if (block == Blocks.BEDROCK || block == Blocks.OBSIDIAN) {
            if ((double)blockPos.getX() == d + 1.0 && blockPos.getY() == HoleBreakAlert.mc.player.getPosition().getY()) {
                return true;
            }
            if ((double)blockPos.getX() == d - 1.0 && blockPos.getY() == HoleBreakAlert.mc.player.getPosition().getY()) {
                return true;
            }
            if ((double)blockPos.getZ() == d2 + 1.0 && blockPos.getY() == HoleBreakAlert.mc.player.getPosition().getY()) {
                return true;
            }
            return (double)blockPos.getZ() == d2 - 1.0 && blockPos.getY() == HoleBreakAlert.mc.player.getPosition().getY();
        }
        return false;
    }

    public HoleBreakAlert() {
        super("HoleBreakAlert", "HoleBreakNotifier.", Module.Category.MISC, true, false, false);
    }
}

