//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.world.World
 *  org.lwjgl.input.Keyboard
 */
package me.snow.aclient.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class Wrapper {
    public static final /* synthetic */ Minecraft mc;

    static {
        mc = Minecraft.getMinecraft();
    }

    public static Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }

    public static int getKey(String string) {
        return Keyboard.getKeyIndex((String)string.toUpperCase());
    }

    public static World getWorld() {
        return Wrapper.getMinecraft().world;
    }

    public static EntityPlayerSP getPlayer() {
        return Wrapper.getMinecraft().player;
    }
}

