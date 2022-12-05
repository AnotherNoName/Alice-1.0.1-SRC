//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 */
package me.snow.aclient.manager;

import me.snow.aclient.module.Feature;
import me.snow.aclient.module.modules.client.Global;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.util.math.BlockPos;

public class NoStopManager
extends Feature {
    private final /* synthetic */ Timer timer;
    private /* synthetic */ boolean sentMessage;
    private /* synthetic */ String prefix;
    private /* synthetic */ BlockPos pos;
    private /* synthetic */ boolean running;
    private /* synthetic */ BlockPos lastPos;
    private /* synthetic */ boolean stopped;

    public void stop() {
        if (this.running) {
            if (NoStopManager.mc.player != null) {
                NoStopManager.mc.player.sendChatMessage(String.valueOf(new StringBuilder().append(this.prefix).append("stop")));
            }
            this.running = false;
        }
    }

    public NoStopManager() {
        this.timer = new Timer();
    }

    public void sendMessage() {
        NoStopManager.mc.player.sendChatMessage(String.valueOf(new StringBuilder().append(this.prefix).append("goto ").append(this.pos.getX()).append(" ").append(this.pos.getY()).append(" ").append(this.pos.getZ())));
    }

    public void setPrefix(String string) {
        this.prefix = string;
    }

    public void start(int n, int n2, int n3) {
        this.pos = new BlockPos(n, n2, n3);
        this.sentMessage = false;
        this.running = true;
    }

    public void onUpdateWalkingPlayer() {
        if (NoStopManager.fullNullCheck()) {
            this.stop();
            return;
        }
        if (this.running && this.pos != null) {
            BlockPos blockPos = NoStopManager.mc.player.getPosition();
            if (blockPos.equals((Object)this.pos)) {
                BlockUtil.debugPos("<Baritone> Arrived at Position: ", this.pos);
                this.running = false;
                return;
            }
            if (blockPos.equals((Object)this.lastPos)) {
                if (this.stopped && this.timer.passedS(Global.getInstance().baritoneTimeOut.getValue().intValue())) {
                    this.sendMessage();
                    this.stopped = false;
                    return;
                }
                if (!this.stopped) {
                    this.stopped = true;
                    this.timer.reset();
                }
            } else {
                this.lastPos = blockPos;
                this.stopped = false;
            }
            if (!this.sentMessage) {
                this.sendMessage();
                this.sentMessage = true;
            }
        }
    }
}

