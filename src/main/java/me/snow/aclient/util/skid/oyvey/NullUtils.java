//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.util.skid.oyvey;

import me.snow.aclient.util.Util;

public class NullUtils {
    public static boolean fullNullCheck() {
        return Util.mc.player == null || Util.mc.world == null;
    }

    public static boolean nullCheck() {
        return Util.mc.player == null || Util.mc.world == null || Util.mc.playerController == null;
    }
}

