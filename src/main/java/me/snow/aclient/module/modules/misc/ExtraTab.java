//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.network.NetworkPlayerInfo
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.RenderPlayerInTabEvent;
import me.snow.aclient.module.Module;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ExtraTab
extends Module {
    private static /* synthetic */ ExtraTab INSTANCE;
    public static /* synthetic */ Setting<SortMode> sortMode;
    public static /* synthetic */ Setting<Boolean> onlyFriends;
    public static /* synthetic */ Setting<Integer> maxSize;

    static {
        INSTANCE = new ExtraTab();
    }

    public static List<NetworkPlayerInfo> subList(List<NetworkPlayerInfo> list, List<NetworkPlayerInfo> list2) {
        if (AliceMain.moduleManager.getModuleByClass(ExtraTab.class).isEnabled()) {
            if (sortMode.getValue() == SortMode.VANILLA) {
                return list.stream().filter(networkPlayerInfo -> onlyFriends.getValue() == false || AliceMain.friendManager.isFriend(networkPlayerInfo.getGameProfile().getId().toString())).limit(maxSize.getValue().intValue()).collect(Collectors.toList());
            }
            if (sortMode.getValue() == SortMode.PING) {
                return list.stream().filter(networkPlayerInfo -> onlyFriends.getValue() == false || AliceMain.friendManager.isFriend(networkPlayerInfo.getGameProfile().getId().toString())).sorted(Comparator.comparing(NetworkPlayerInfo::getResponseTime)).limit(maxSize.getValue().intValue()).collect(Collectors.toList());
            }
            return list.stream().filter(networkPlayerInfo -> onlyFriends.getValue() == false || AliceMain.friendManager.isFriend(networkPlayerInfo.getGameProfile().getId().toString())).sorted(Comparator.comparing(networkPlayerInfo -> networkPlayerInfo.getGameProfile().getName().length())).limit(maxSize.getValue().intValue()).collect(Collectors.toList());
        }
        return list2;
    }

    @SubscribeEvent
    public void onPlayerRender(RenderPlayerInTabEvent renderPlayerInTabEvent) {
        if (AliceMain.friendManager.isFriend(renderPlayerInTabEvent.getName())) {
            renderPlayerInTabEvent.setName(String.valueOf(new StringBuilder().append("\u00a7b").append(renderPlayerInTabEvent.getName())));
        }
    }

    public ExtraTab() {
        super("ExtraTab", "Extends Tab.", Module.Category.MISC, false, false, false);
        maxSize = this.register(new Setting<Integer>("MaxSize", 250, 1, 400));
        sortMode = this.register(new Setting<SortMode>("SortMode", SortMode.VANILLA));
        onlyFriends = this.register(new Setting<Boolean>("OnlyFriends", false));
    }

    private static enum SortMode {
        VANILLA,
        PING,
        LENGTH;

    }
}

