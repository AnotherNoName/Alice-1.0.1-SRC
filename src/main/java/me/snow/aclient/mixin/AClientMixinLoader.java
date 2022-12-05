/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
 */
package me.snow.aclient.mixin;

import java.util.Map;
import me.snow.aclient.AliceMain;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

public class AClientMixinLoader
implements IFMLLoadingPlugin {
    private static boolean isObfuscatedEnvironment = false;

    public AClientMixinLoader() {
        AliceMain.LOGGER.info("AliceClient mixins initialized");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.alice.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        AliceMain.LOGGER.info(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
    }

    public String[] getASMTransformerClass() {
        return new String[0];
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> map2) {
        isObfuscatedEnvironment = (Boolean)map2.get("runtimeDeobfuscationEnabled");
    }

    public String getAccessTransformerClass() {
        return null;
    }
}

