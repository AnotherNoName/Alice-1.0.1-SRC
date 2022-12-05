//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Tuple2f
 *  javax.vecmath.Vector2f
 *  net.minecraft.client.gui.ScaledResolution
 *  org.lwjgl.input.Mouse
 */
package me.snow.aclient.core.gui.effect.particles;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import javax.vecmath.Tuple2f;
import javax.vecmath.Vector2f;
import me.snow.aclient.module.modules.client.ClickGui;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.util.ColorUtil;
import me.snow.aclient.util.RenderUtil;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;

public class Particle {
    private /* synthetic */ float size;
    private /* synthetic */ int alpha;
    private /* synthetic */ Vector2f acceleration;
    private final /* synthetic */ int maxAlpha;
    private /* synthetic */ Vector2f pos;
    private /* synthetic */ Vector2f velocity;

    public void render(int n, int n2) {
        if (Mouse.isButtonDown((int)0)) {
            float f = (float)n - this.pos.getX();
            float f2 = (float)n2 - this.pos.getY();
            if (Math.abs(f) < 50.0f && Math.abs(f2) < 50.0f) {
                this.acceleration.setX(this.acceleration.getX() + f * 0.0015f);
                this.acceleration.setY(this.acceleration.getY() + f2 * 0.0015f);
            }
        }
        Color color = ClickGui.getInstance().colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(ClickGui.getInstance().particlered.getValue(), ClickGui.getInstance().particlegreen.getValue(), ClickGui.getInstance().particleblue.getValue(), ClickGui.getInstance().alpha.getValue());
        RenderUtil.drawRect(this.pos.x, this.pos.y, this.pos.x + this.size, this.pos.y + this.size, Particle.changeAlpha(ColorUtil.toRGBA(color.getRed(), color.getGreen(), color.getBlue()), this.alpha));
    }

    public static int changeAlpha(int n, int n2) {
        return n2 << 24 | (n &= 0xFFFFFF);
    }

    public void respawn(ScaledResolution scaledResolution) {
        this.pos = new Vector2f((float)(Math.random() * (double)scaledResolution.getScaledWidth()), (float)(Math.random() * (double)scaledResolution.getScaledHeight()));
    }

    public void setAlpha(int n) {
        this.alpha = n;
    }

    public Vector2f getPos() {
        return this.pos;
    }

    public Vector2f getVelocity() {
        return this.velocity;
    }

    public Particle(Vector2f vector2f) {
        this.pos = vector2f;
        int n = -1;
        int n2 = 1;
        float f = (float)n + ThreadLocalRandom.current().nextFloat() * (float)(n2 - n);
        float f2 = (float)n + ThreadLocalRandom.current().nextFloat() * (float)(n2 - n);
        this.velocity = new Vector2f(f, f2);
        this.acceleration = new Vector2f(0.0f, 0.35f);
        this.alpha = 0;
        this.maxAlpha = ThreadLocalRandom.current().nextInt(32, 192);
        this.size = 0.5f + ThreadLocalRandom.current().nextFloat() * 1.5f;
    }

    public Vector2f getAcceleration() {
        return this.acceleration;
    }

    public void setAcceleration(Vector2f vector2f) {
        this.acceleration = vector2f;
    }

    public float getSize() {
        return this.size;
    }

    public void setPos(Vector2f vector2f) {
        this.pos = vector2f;
    }

    public void setSize(float f) {
        this.size = f;
    }

    public int getAlpha() {
        return this.alpha;
    }

    public void update() {
        if (this.alpha < this.maxAlpha) {
            this.alpha += 8;
        }
        if (this.acceleration.getX() > 0.35f) {
            this.acceleration.setX(this.acceleration.getX() * 0.975f);
        } else if (this.acceleration.getX() < -0.35f) {
            this.acceleration.setX(this.acceleration.getX() * 0.975f);
        }
        if (this.acceleration.getY() > 0.35f) {
            this.acceleration.setY(this.acceleration.getY() * 0.975f);
        } else if (this.acceleration.getY() < -0.35f) {
            this.acceleration.setY(this.acceleration.getY() * 0.975f);
        }
        this.pos.add((Tuple2f)this.acceleration);
        this.pos.add((Tuple2f)this.velocity);
    }

    public void setVelocity(Vector2f vector2f) {
        this.velocity = vector2f;
    }
}

