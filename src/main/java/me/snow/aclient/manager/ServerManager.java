//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.manager;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;
import me.snow.aclient.module.Feature;
import me.snow.aclient.module.modules.client.Global;
import me.snow.aclient.util.Timer;

public class ServerManager
extends Feature {
    private /* synthetic */ float TPS;
    private /* synthetic */ long lastUpdate;
    private final /* synthetic */ DecimalFormat format;
    private /* synthetic */ String serverBrand;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ float[] tpsCounts;

    public float getTpsFactor() {
        return 20.0f / this.TPS;
    }

    public String getServerBrand() {
        return this.serverBrand;
    }

    public float getTPS() {
        return this.TPS;
    }

    public void onPacketReceived() {
        this.timer.reset();
    }

    public void update() {
        double d;
        float f;
        long l = System.currentTimeMillis();
        if (this.lastUpdate == -1L) {
            this.lastUpdate = l;
            return;
        }
        long l2 = l - this.lastUpdate;
        float f2 = (float)l2 / 20.0f;
        if (f2 == 0.0f) {
            f2 = 50.0f;
        }
        float f3 = 1000.0f / f2;
        if (f > 20.0f) {
            f3 = 20.0f;
        }
        System.arraycopy(this.tpsCounts, 0, this.tpsCounts, 1, this.tpsCounts.length - 1);
        this.tpsCounts[0] = f3;
        double d2 = 0.0;
        for (float f4 : this.tpsCounts) {
            d2 += (double)f4;
        }
        d2 /= (double)this.tpsCounts.length;
        if (d > 20.0) {
            d2 = 20.0;
        }
        this.TPS = Float.parseFloat(this.format.format(d2));
        this.lastUpdate = l;
    }

    public long serverRespondingTime() {
        return this.timer.getPassedTimeMs();
    }

    public boolean isServerNotResponding() {
        return this.timer.passedMs(Global.getInstance().respondTime.getValue().intValue());
    }

    public ServerManager() {
        this.tpsCounts = new float[10];
        this.format = new DecimalFormat("##.00#");
        this.timer = new Timer();
        this.TPS = 20.0f;
        this.lastUpdate = -1L;
        this.serverBrand = "";
    }

    public void setServerBrand(String string) {
        this.serverBrand = string;
    }

    public int getPing() {
        if (ServerManager.fullNullCheck()) {
            return 0;
        }
        try {
            return Objects.requireNonNull(mc.getConnection()).getPlayerInfo(mc.getConnection().getGameProfile().getId()).getResponseTime();
        }
        catch (Exception exception) {
            return 0;
        }
    }

    @Override
    public void reset() {
        Arrays.fill(this.tpsCounts, 20.0f);
        this.TPS = 20.0f;
    }
}

