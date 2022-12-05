//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Tuple2f
 *  javax.vecmath.Vector2f
 *  net.minecraft.client.gui.ScaledResolution
 */
package me.snow.aclient.core.gui.effect.particles;

import java.awt.Color;
import javax.vecmath.Tuple2f;
import javax.vecmath.Vector2f;
import me.snow.aclient.core.gui.effect.particles.Particle;
import me.snow.aclient.module.modules.client.ClickGui;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.util.ColorUtil;
import me.snow.aclient.util.RenderUtil;
import net.minecraft.client.gui.ScaledResolution;

public final class ParticleSystem {
    private /* synthetic */ ScaledResolution scaledResolution;
    private final /* synthetic */ Particle[] particles;
    private final /* synthetic */ int PARTS = 200;

    public void update() {
        for (int i = 0; i < 200; ++i) {
            Particle particle = this.particles[i];
            if (this.scaledResolution != null) {
                boolean bl = particle.getPos().x > (float)this.scaledResolution.getScaledWidth() || particle.getPos().x < 0.0f;
                boolean bl2 = particle.getPos().y > (float)this.scaledResolution.getScaledHeight() || particle.getPos().y < 0.0f;
                boolean bl3 = bl2;
                if (bl || bl2) {
                    particle.respawn(this.scaledResolution);
                }
            }
            particle.update();
        }
    }

    public void setScaledResolution(ScaledResolution scaledResolution) {
        this.scaledResolution = scaledResolution;
    }

    public void render(int n, int n2) {
        if (!ClickGui.getInstance().particles.getValue().booleanValue()) {
            return;
        }
        for (int i = 0; i < 200; ++i) {
            Particle particle = this.particles[i];
            for (int j = 1; j < 200; ++j) {
                int n3;
                if (i == j) continue;
                Particle particle2 = this.particles[j];
                Vector2f vector2f = new Vector2f(particle.getPos());
                vector2f.sub((Tuple2f)particle2.getPos());
                float f = vector2f.length();
                Color color = ClickGui.getInstance().colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(ClickGui.getInstance().particlered.getValue(), ClickGui.getInstance().particlegreen.getValue(), ClickGui.getInstance().particleblue.getValue(), ClickGui.getInstance().alpha.getValue());
                int n4 = ClickGui.getInstance().particleLength.getValue() / (this.scaledResolution.getScaleFactor() <= 1 ? 3 : this.scaledResolution.getScaleFactor());
                if (!(f < (float)n4) || (n3 = (int)ParticleSystem.map(f, n4, 0.0, 0.0, 127.0)) <= 8) continue;
                RenderUtil.drawLine(particle.getPos().x + particle.getSize() / 2.0f, particle.getPos().y + particle.getSize() / 2.0f, particle2.getPos().x + particle2.getSize() / 2.0f, particle2.getPos().y + particle2.getSize() / 2.0f, 1.0f, Particle.changeAlpha(ColorUtil.toRGBA(color.getRed(), color.getGreen(), color.getBlue()), n3));
            }
            particle.render(n, n2);
        }
    }

    public ParticleSystem(ScaledResolution scaledResolution) {
        this.PARTS = 200;
        this.particles = new Particle[200];
        this.scaledResolution = scaledResolution;
        for (int i = 0; i < 200; ++i) {
            this.particles[i] = new Particle(new Vector2f((float)(Math.random() * (double)scaledResolution.getScaledWidth()), (float)(Math.random() * (double)scaledResolution.getScaledHeight())));
        }
    }

    public static double map(double d, double d2, double d3, double d4, double d5) {
        d = (d - d2) / (d3 - d2);
        return d4 + d * (d5 - d4);
    }

    public ScaledResolution getScaledResolution() {
        return this.scaledResolution;
    }
}

