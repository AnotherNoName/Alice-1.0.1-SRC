/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.module.modules.client;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.TextUtil;

public class ModuleTools
extends Module {
    public /* synthetic */ Setting<TextUtil.Color> Abysscolorrr;
    public /* synthetic */ Setting<Notifier> notifier;
    public /* synthetic */ Setting<PopNotifier> popNotifier;
    private static /* synthetic */ ModuleTools INSTANCE;

    public static ModuleTools getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ModuleTools();
        }
        return INSTANCE;
    }

    public ModuleTools() {
        super("ModuleTools", "Change settings", Module.Category.CLIENT, true, false, false);
        this.notifier = this.register(new Setting<Notifier>("ModuleNotifier", Notifier.FUTURE));
        this.popNotifier = this.register(new Setting<PopNotifier>("PopNotifier", PopNotifier.FUTURE));
        this.Abysscolorrr = this.register(new Setting<TextUtil.Color>("Abyss text color", TextUtil.Color.AQUA, color -> this.notifier.getValue() == Notifier.ABYSS));
        INSTANCE = this;
    }

    public static enum Notifier {
        FUTURE,
        MUFFIN,
        BOLD,
        SNOW,
        ABYSS,
        NEWLUIGI,
        OYVEY;

    }

    public static enum PopNotifier {
        PHOBOS,
        FUTURE,
        DOTGOD,
        NONE;

    }
}

