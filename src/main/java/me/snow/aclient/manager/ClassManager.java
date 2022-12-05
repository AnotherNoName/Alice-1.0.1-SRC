/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.launchwrapper.Launch
 */
package me.snow.aclient.manager;

import me.snow.aclient.util.ReflectUtil;
import net.minecraft.launchwrapper.Launch;

public class ClassManager {
    public ClassManager() {
        try {
            if (Launch.classLoader.findClass("cat.yoink.dumper.Main") != null || Launch.classLoader.findClass("me.crystallinqq.dumper") != null || Launch.classLoader.findClass("tech.mmmax.dumper") != null || Launch.classLoader.findClass("fuck.you.multihryack") != null) {
                throw new ReflectUtil();
            }
        }
        catch (ClassNotFoundException classNotFoundException) {
            // empty catch block
        }
    }
}

