//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.ScaledResolution
 */
package me.snow.aclient.core.gui.effect;

import java.util.Random;
import me.snow.aclient.util.RenderUtil;
import net.minecraft.client.gui.ScaledResolution;

public class Snow {
    private /* synthetic */ int _size;
    private /* synthetic */ int _x;
    private /* synthetic */ int _y;
    private /* synthetic */ int _fallingSpeed;

    public int getY() {
        return this._y;
    }

    public void setY(int n) {
        this._y = n;
    }

    public void Update(ScaledResolution scaledResolution) {
        RenderUtil.drawRect(this.getX(), this.getY(), this.getX() + this._size, this.getY() + this._size, -1714829883);
        this.setY(this.getY() + this._fallingSpeed);
        if (this.getY() > scaledResolution.getScaledHeight() + 10 || this.getY() < -10) {
            this.setY(-10);
            Random random = new Random();
            this._fallingSpeed = random.nextInt(5) + 1;
            this._size = random.nextInt(4) + 1;
        }
    }

    public void setX(int n) {
        this._x = n;
    }

    public int getX() {
        return this._x;
    }

    public Snow(int n, int n2, int n3, int n4) {
        this._x = n;
        this._y = n2;
        this._fallingSpeed = n3;
        this._size = n4;
    }
}

