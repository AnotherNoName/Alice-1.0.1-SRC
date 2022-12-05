//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.gui.GuiNewChat
 *  net.minecraft.network.play.client.CPacketChatMessage
 *  net.minecraft.network.play.server.SPacketChat
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.fml.common.ObfuscationReflectionHelper
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import me.snow.aclient.core.gui.custom.GuiChat;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.Global;
import me.snow.aclient.util.TextUtil;
import me.snow.aclient.util.Timer;
import me.snow.turok.task.Font;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatModifier
extends Module {
    private final /* synthetic */ Timer timer;
    public /* synthetic */ Setting<Type> type;
    public /* synthetic */ Setting<Double> vSpeed;
    public /* synthetic */ Setting<TextUtil.Color> bracket;
    public /* synthetic */ Setting<Boolean> rainbowTimeStamps;
    public /* synthetic */ Setting<Boolean> space;
    public static /* synthetic */ GuiNewChat guiChat;
    public /* synthetic */ Setting<Boolean> clean;
    public static /* synthetic */ GuiChat guiChatSmooth;
    public /* synthetic */ Setting<Suffix> suffix;
    public /* synthetic */ Setting<TextUtil.Color> timeStamps;
    public /* synthetic */ Setting<Double> xOffset;
    /* synthetic */ String[] randomAA;
    public /* synthetic */ Setting<Boolean> infinite;
    public /* synthetic */ Setting<Double> vIncrements;
    private static /* synthetic */ ChatModifier INSTANCE;
    public /* synthetic */ Setting<Boolean> smoothChat;
    public /* synthetic */ Setting<String> customSuffix;
    public /* synthetic */ Setting<Boolean> all;
    public /* synthetic */ Setting<Double> yOffset;
    public /* synthetic */ Setting<Double> vLength;

    public ChatModifier() {
        super("ChatTweaks", "Modifies your chat", Module.Category.MISC, true, false, false);
        this.timer = new Timer();
        this.suffix = this.register(new Setting<Suffix>("Suffix", Suffix.NONE, "Your Suffix."));
        this.customSuffix = this.register(new Setting<String>("", " | LuigiHack", string -> this.suffix.getValue() == Suffix.CUSTOM));
        this.clean = this.register(new Setting<Boolean>("CleanChat", Boolean.FALSE, "Cleans your chat"));
        this.infinite = this.register(new Setting<Boolean>("Infinite", Boolean.FALSE, "Makes your chat infinite."));
        this.timeStamps = this.register(new Setting<TextUtil.Color>("Time", TextUtil.Color.NONE));
        this.rainbowTimeStamps = this.register(new Setting<Object>("RainbowTimeStamps", Boolean.FALSE, object -> this.timeStamps.getValue() != TextUtil.Color.NONE));
        this.bracket = this.register(new Setting<Object>("Bracket", (Object)TextUtil.Color.WHITE, object -> this.timeStamps.getValue() != TextUtil.Color.NONE));
        this.space = this.register(new Setting<Object>("Space", Boolean.TRUE, object -> this.timeStamps.getValue() != TextUtil.Color.NONE));
        this.all = this.register(new Setting<Object>("All", Boolean.FALSE, object -> this.timeStamps.getValue() != TextUtil.Color.NONE));
        this.smoothChat = this.register(new Setting<Boolean>("SmoothChat", false));
        this.xOffset = this.register(new Setting<Object>("XOffset", Double.valueOf(0.0), Double.valueOf(0.0), Integer.valueOf(600), object -> this.smoothChat.getValue()));
        this.yOffset = this.register(new Setting<Object>("YOffset", Double.valueOf(0.0), Double.valueOf(0.0), Double.valueOf(30.0), object -> this.smoothChat.getValue()));
        this.vSpeed = this.register(new Setting<Object>("VSpeed", Double.valueOf(30.0), Double.valueOf(1.0), Double.valueOf(100.0), object -> this.smoothChat.getValue()));
        this.vLength = this.register(new Setting<Object>("VLength", Double.valueOf(10.0), Double.valueOf(5.0), Double.valueOf(100.0), object -> this.smoothChat.getValue()));
        this.vIncrements = this.register(new Setting<Object>("VIncrements", Double.valueOf(1.0), Double.valueOf(1.0), Double.valueOf(5.0), object -> this.smoothChat.getValue()));
        this.type = this.register(new Setting<Object>("Type", (Object)Type.HORIZONTAL, object -> this.smoothChat.getValue()));
        this.randomAA = new String[]{" \u23d0 3\u1d00\u0280\u1d1b\u029c\u029c4\u1d04\u1d0b", " \u23d0 \u1d18\u029c\u1d0f\u0299\u1d0f\ua731", " \u23d0 \u1d21\u1d07\u1d00\u1d1b\u029c\u1d07\u0280 \u2764 \u2744", " \u23d0 \u029f\u1d1c\u026a\u0262\u026a\u029c\u1d00\u1d04\u1d0b \u0e05\u2022\u03c9\u2022\u0e05 meow", " \u23d0 \u029f\u1d1c\u026a\u0262\u026a\u029c\u1d00\u1d04\u1d0b+ \u262a", " : \u1d04\u0280\u1d07\u1d18\u1d07 \u1d04\u029f\u026a\u1d07\u0274\u1d1b \u1d18\u029f\u1d1c\ua731 \u03b20.9", " : s\u1d1b\u0280\u1d00\u1d21\u0299\u1d07\u0280\u0280\u028f \u029c\u1d00\u1d04\u1d0b \u03b20.2"};
        this.setInstance();
    }

    public String getTimeString(String string) {
        String string2 = new SimpleDateFormat("k:mm").format(new Date());
        if (this.rainbowTimeStamps.getValue().booleanValue()) {
            String string3 = String.valueOf(new StringBuilder().append("<").append(string2).append(">").append(this.space.getValue() != false ? " " : ""));
            StringBuilder stringBuilder = new StringBuilder(string3);
            stringBuilder.insert(0, "\u00a7+");
            if (!string.contains(Global.getInstance().getRainbowCommandMessage())) {
                stringBuilder.append("\u00a7r");
            }
            return String.valueOf(stringBuilder);
        }
        return String.valueOf(new StringBuilder().append(this.bracket.getValue() == TextUtil.Color.NONE ? "" : TextUtil.coloredString("<", this.bracket.getValue())).append(TextUtil.coloredString(string2, this.timeStamps.getValue())).append(this.bracket.getValue() == TextUtil.Color.NONE ? "" : TextUtil.coloredString(">", this.bracket.getValue())).append(this.space.getValue() != false ? " " : "").append("\u00a7r"));
    }

    @Override
    public void onDisable() {
        guiChat = new GuiNewChat(mc);
        ObfuscationReflectionHelper.setPrivateValue(GuiIngame.class, (Object)ChatModifier.mc.ingameGUI, (Object)guiChat, (String[])new String[]{"persistantChatGUI"});
    }

    @SubscribeEvent
    public void onChatPacketReceive(PacketEvent.Receive receive) {
        if (receive.getStage() == 0) {
            receive.getPacket();
        }
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.convert_base(this.random_string(this.randomAA)));
        if (send.getStage() == 0 && send.getPacket() instanceof CPacketChatMessage) {
            CPacketChatMessage cPacketChatMessage = (CPacketChatMessage)send.getPacket();
            String string = cPacketChatMessage.getMessage();
            if (string.startsWith("/")) {
                return;
            }
            switch (this.suffix.getValue()) {
                case ALICE: {
                    string = String.valueOf(new StringBuilder().append(string).append(" \u1d00\u029f\u026a\u1d04\u1d07 \u269b").append(""));
                    break;
                }
                case ALICE2: {
                    string = String.valueOf(new StringBuilder().append(string).append(" | Alice").append(""));
                    break;
                }
                case EARTH: {
                    string = String.valueOf(new StringBuilder().append(string).append(" \u23d0 3\u1d00\u0280\u1d1b\u029c\u029c4\u1d04\u1d0b").append(""));
                    break;
                }
                case PHOBOS: {
                    string = String.valueOf(new StringBuilder().append(string).append(" \u23d0 \u1d18\u029c\u1d0f\u0299\u1d0f\ua731").append(""));
                    break;
                }
                case LUIGIHACK: {
                    string = String.valueOf(new StringBuilder().append(string).append(" \u23d0 \u029f\u1d1c\u026a\u0262\u026a\u029c\u1d00\u1d04\u1d0b \u0e05\u2022\u03c9\u2022\u0e05 meow").append(""));
                    break;
                }
                case NEWLUIGIHACK: {
                    string = String.valueOf(new StringBuilder().append(string).append(" \u23d0 \u029f\u1d1c\u026a\u0262\u026a\u029c\u1d00\u1d04\u1d0b+ \u262a").append(""));
                    break;
                }
                case MUFFIN: {
                    string = String.valueOf(new StringBuilder().append(string).append(" \u30c4 \u2c98\u03c5\u2a0d\u2a0d\u2aef\ufb21").append(""));
                    break;
                }
                case CUSTOM: {
                    string = String.valueOf(new StringBuilder().append(string).append(this.customSuffix.getValue()));
                    break;
                }
                case Random: {
                    string = String.valueOf(new StringBuilder().append(string).append((Object)stringBuilder));
                }
            }
            if (string.length() >= 256) {
                string = string.substring(0, 256);
            }
            cPacketChatMessage.message = string;
        }
    }

    static {
        INSTANCE = new ChatModifier();
    }

    public static ChatModifier getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ChatModifier();
        }
        return INSTANCE;
    }

    public static String smoth(String string) {
        return Font.smoth(string);
    }

    public String random_string(String[] arrstring) {
        return arrstring[new Random().nextInt(arrstring.length)];
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (receive.getStage() == 0 && this.timeStamps.getValue() != TextUtil.Color.NONE && receive.getPacket() instanceof SPacketChat) {
            if (!((SPacketChat)receive.getPacket()).isSystem()) {
                return;
            }
            String string = ((SPacketChat)receive.getPacket()).chatComponent.getFormattedText();
            String string2 = String.valueOf(new StringBuilder().append(this.getTimeString(string)).append(string));
            ((SPacketChat)receive.getPacket()).chatComponent = new TextComponentString(string2);
        }
    }

    @Override
    public void onLogin() {
        if (this.isEnabled()) {
            this.disable();
            this.enable();
        }
    }

    public String convert_base(String string) {
        return ChatModifier.smoth(string);
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        guiChatSmooth = new GuiChat(mc);
        ObfuscationReflectionHelper.setPrivateValue(GuiIngame.class, (Object)ChatModifier.mc.ingameGUI, (Object)guiChatSmooth, (String[])new String[]{"persistantChatGUI"});
    }

    public static enum Type {
        HORIZONTAL,
        VERTICAL;

    }

    public static enum Suffix {
        NONE,
        ALICE,
        ALICE2,
        LUIGIHACK,
        NEWLUIGIHACK,
        MUFFIN,
        PHOBOS,
        EARTH,
        CUSTOM,
        Random;

    }
}

