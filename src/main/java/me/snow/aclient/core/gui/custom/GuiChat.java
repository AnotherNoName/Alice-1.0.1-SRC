//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ChatLine
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.GuiNewChat
 *  net.minecraft.client.gui.GuiUtilRenderComponents
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.player.EntityPlayer$EnumChatVisibility
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.logging.log4j.Logger
 */
package me.snow.aclient.core.gui.custom;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import me.snow.aclient.AliceMain;
import me.snow.aclient.module.modules.misc.ChatModifier;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.Timer;
import me.snow.aclient.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

@SideOnly(value=Side.CLIENT)
public class GuiChat
extends GuiNewChat
implements Util {
    public static /* synthetic */ long prevMillis;
    public /* synthetic */ boolean configuring;
    private /* synthetic */ boolean isScrolled;
    private /* synthetic */ int scrollPos;
    public static /* synthetic */ int newLines;
    private static final /* synthetic */ Logger LOGGER;
    private final /* synthetic */ Timer messageTimer;
    public static /* synthetic */ int messageAdd;
    private final /* synthetic */ List<ChatLine> drawnChatLines;
    private final /* synthetic */ List<String> sentMessages;
    private final /* synthetic */ List<ChatLine> chatLines;
    public static /* synthetic */ float percentComplete;
    private final /* synthetic */ Minecraft mc;

    public void resetScroll() {
        this.scrollPos = 0;
        this.isScrolled = false;
    }

    public static int calculateChatboxHeight(float f) {
        return MathHelper.floor((float)(f * 160.0f + 20.0f));
    }

    public void addToSentMessages(String string) {
        if (this.sentMessages.isEmpty() || !this.sentMessages.get(this.sentMessages.size() - 1).equals(string)) {
            this.sentMessages.add(string);
        }
    }

    public void refreshChat() {
        this.drawnChatLines.clear();
        this.resetScroll();
        for (int i = this.chatLines.size() - 1; i >= 0; --i) {
            ChatLine chatLine = this.chatLines.get(i);
            this.setChatLine(chatLine.getChatComponent(), chatLine.getChatLineID(), chatLine.getUpdatedCounter(), true);
        }
    }

    public int getChatHeight() {
        return GuiChat.calculateChatboxHeight(this.getChatOpen() ? this.mc.gameSettings.chatHeightFocused : this.mc.gameSettings.chatHeightUnfocused);
    }

    @Nullable
    public ITextComponent getChatComponent(int n, int n2) {
        if (!this.getChatOpen()) {
            return null;
        }
        ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        int n3 = scaledResolution.getScaleFactor();
        float f = this.getChatScale();
        int n4 = n / n3 - 2 - ChatModifier.getInstance().xOffset.getValue().intValue();
        int n5 = n2 / n3 - 40 + ChatModifier.getInstance().yOffset.getValue().intValue();
        n4 = MathHelper.floor((float)((float)n4 / f));
        n5 = MathHelper.floor((float)((float)n5 / f));
        if (n4 >= 0 && n5 >= 0) {
            int n6 = Math.min(this.getLineCount(), this.drawnChatLines.size());
            if (n4 <= MathHelper.floor((float)((float)this.getChatWidth() / this.getChatScale())) && n5 < this.mc.fontRendererObj.FONT_HEIGHT * n6 + n6) {
                int n7 = n5 / this.mc.fontRendererObj.FONT_HEIGHT + this.scrollPos;
                if (n7 >= 0 && n7 < this.drawnChatLines.size()) {
                    ChatLine chatLine = this.drawnChatLines.get(n7);
                    int n8 = 0;
                    for (ITextComponent iTextComponent : chatLine.getChatComponent()) {
                        if (!(iTextComponent instanceof TextComponentString) || (n8 += this.mc.fontRendererObj.getStringWidth(GuiUtilRenderComponents.removeTextColorsIfConfigured((String)((TextComponentString)iTextComponent).getText(), (boolean)false))) <= n4) continue;
                        return iTextComponent;
                    }
                }
                return null;
            }
            return null;
        }
        return null;
    }

    public void drawChat(int n) {
        if (this.configuring) {
            return;
        }
        if (prevMillis == -1L) {
            prevMillis = System.currentTimeMillis();
            return;
        }
        long l = System.currentTimeMillis();
        long l2 = l - prevMillis;
        prevMillis = l;
        GuiChat.updatePercentage(l2);
        float f = percentComplete;
        float f2 = 1.0f - (f -= 1.0f) * f * f * f;
        f2 = MathUtil.clamp(f2, 0.0f, 1.0f);
        if (this.mc.gameSettings.chatVisibility != EntityPlayer.EnumChatVisibility.HIDDEN) {
            int n2 = this.getLineCount();
            int n3 = this.drawnChatLines.size();
            float f3 = this.mc.gameSettings.chatOpacity * 0.9f + 0.1f;
            if (n3 > 0) {
                int n4;
                int n5;
                int n6;
                int n7;
                boolean bl = this.getChatOpen();
                float f4 = this.getChatScale();
                GlStateManager.pushMatrix();
                if (ChatModifier.getInstance().isOn() && ChatModifier.getInstance().smoothChat.getValue().booleanValue() && ChatModifier.getInstance().type.getValue() == ChatModifier.Type.VERTICAL && !this.isScrolled) {
                    GlStateManager.translate((float)(2.0f + ChatModifier.getInstance().xOffset.getValue().floatValue()), (float)(8.0f + ChatModifier.getInstance().yOffset.getValue().floatValue() + (9.0f - 9.0f * f2) * f4), (float)0.0f);
                } else {
                    GlStateManager.translate((float)(2.0f + ChatModifier.getInstance().xOffset.getValue().floatValue()), (float)(8.0f + ChatModifier.getInstance().yOffset.getValue().floatValue()), (float)0.0f);
                }
                GlStateManager.scale((float)f4, (float)f4, (float)1.0f);
                int n8 = 0;
                for (n7 = 0; n7 + this.scrollPos < this.drawnChatLines.size() && n7 < n2; ++n7) {
                    ChatLine chatLine = this.drawnChatLines.get(n7 + this.scrollPos);
                    if (chatLine == null || (n6 = n - chatLine.getUpdatedCounter()) >= 200 && !bl) continue;
                    double d = (double)n6 / 200.0;
                    d = 1.0 - d;
                    d *= 10.0;
                    d = MathHelper.clamp((double)d, (double)0.0, (double)1.0);
                    d *= d;
                    n5 = (int)(255.0 * d);
                    if (bl) {
                        n5 = 255;
                    }
                    n5 = (int)((float)n5 * f3);
                    ++n8;
                    if (n5 <= 3) continue;
                    n4 = 0;
                    int n9 = -n7 * 9;
                    String string = chatLine.getChatComponent().getFormattedText();
                    GlStateManager.enableBlend();
                    if (ChatModifier.getInstance().smoothChat.getValue().booleanValue() && n7 <= newLines) {
                        if (this.messageTimer.passedMs(ChatModifier.getInstance().vSpeed.getValue().intValue()) && messageAdd < 0) {
                            if ((messageAdd += ChatModifier.getInstance().vIncrements.getValue().intValue()) > 0) {
                                messageAdd = 0;
                            }
                            this.messageTimer.reset();
                        }
                        this.mc.fontRendererObj.drawStringWithShadow(string, 0.0f + (float)(ChatModifier.getInstance().type.getValue() == ChatModifier.Type.HORIZONTAL ? messageAdd : 0), (float)(n9 - 8), 0xFFFFFF + (n5 << 24));
                    } else {
                        this.mc.fontRendererObj.drawStringWithShadow(string, 0.0f + (float)(ChatModifier.getInstance().type.getValue() == ChatModifier.Type.VERTICAL ? messageAdd : 0), (float)(n9 - 8), 0xFFFFFF + (n5 << 24));
                    }
                    GlStateManager.disableAlpha();
                    GlStateManager.disableBlend();
                }
                if (bl) {
                    n7 = this.mc.fontRendererObj.FONT_HEIGHT;
                    GlStateManager.translate((float)-3.0f, (float)0.0f, (float)0.0f);
                    n6 = n3 * n7 + n3;
                    int n10 = n8 * n7 + n8;
                    int n11 = this.scrollPos * n10 / n3;
                    int n12 = n10 * n10 / n6;
                    if (n6 != n10) {
                        n5 = n11 > 0 ? 170 : 96;
                        n4 = this.isScrolled ? 0xCC3333 : 0x3333AA;
                        Gui.drawRect((int)0, (int)(-n11), (int)2, (int)(-n11 - n12), (int)(n4 + (n5 << 24)));
                        Gui.drawRect((int)2, (int)(-n11), (int)1, (int)(-n11 - n12), (int)(0xCCCCCC + (n5 << 24)));
                    }
                }
                GlStateManager.popMatrix();
            }
        }
    }

    private void setChatLine(ITextComponent iTextComponent, int n, int n2, boolean bl) {
        messageAdd = -ChatModifier.getInstance().vLength.getValue().intValue();
        if (n != 0) {
            this.deleteChatLine(n);
        }
        int n3 = MathHelper.floor((float)((float)this.getChatWidth() / this.getChatScale()));
        List list = GuiUtilRenderComponents.splitText((ITextComponent)iTextComponent, (int)n3, (FontRenderer)this.mc.fontRendererObj, (boolean)false, (boolean)false);
        boolean bl2 = this.getChatOpen();
        newLines = list.size() - 1;
        for (ITextComponent iTextComponent2 : list) {
            if (bl2 && this.scrollPos > 0) {
                this.isScrolled = true;
                this.scroll(1);
            }
            this.drawnChatLines.add(0, new ChatLine(n2, iTextComponent2, n));
        }
        while (this.drawnChatLines.size() > 100) {
            this.drawnChatLines.remove(this.drawnChatLines.size() - 1);
        }
        if (!bl) {
            this.chatLines.add(0, new ChatLine(n2, iTextComponent, n));
            while (this.chatLines.size() > 100) {
                this.chatLines.remove(this.chatLines.size() - 1);
            }
        }
    }

    public void scroll(int n) {
        this.scrollPos += n;
        int n2 = this.drawnChatLines.size();
        if (this.scrollPos > n2 - this.getLineCount()) {
            this.scrollPos = n2 - this.getLineCount();
        }
        if (this.scrollPos <= 0) {
            this.scrollPos = 0;
            this.isScrolled = false;
        }
    }

    public static int calculateChatboxWidth(float f) {
        return MathHelper.floor((float)(f * 280.0f + 40.0f));
    }

    public void printChatMessage(ITextComponent iTextComponent) {
        this.printChatMessageWithOptionalDeletion(iTextComponent, 0);
    }

    public boolean getChatOpen() {
        return this.mc.currentScreen instanceof net.minecraft.client.gui.GuiChat;
    }

    public GuiChat(Minecraft minecraft) {
        super(minecraft);
        this.messageTimer = new Timer();
        this.sentMessages = Lists.newArrayList();
        this.chatLines = Lists.newArrayList();
        this.drawnChatLines = Lists.newArrayList();
        this.mc = minecraft;
    }

    public void printChatMessageWithOptionalDeletion(ITextComponent iTextComponent, int n) {
        percentComplete = 0.0f;
        this.setChatLine(iTextComponent, n, this.mc.ingameGUI.getUpdateCounter(), false);
        LOGGER.info("[CHAT] {}", (Object)iTextComponent.getUnformattedText().replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n"));
    }

    public static void updatePercentage(long l) {
        if (percentComplete < 1.0f) {
            percentComplete += 0.004f * (float)l;
        }
        percentComplete = MathUtil.clamp(percentComplete, 0.0f, 1.0f);
    }

    public void deleteChatLine(int n) {
        ChatLine chatLine;
        Iterator<ChatLine> iterator2 = this.drawnChatLines.iterator();
        while (iterator2.hasNext()) {
            chatLine = iterator2.next();
            if (chatLine.getChatLineID() != n) continue;
            iterator2.remove();
        }
        iterator2 = this.chatLines.iterator();
        while (iterator2.hasNext()) {
            chatLine = iterator2.next();
            if (chatLine.getChatLineID() != n) continue;
            iterator2.remove();
            break;
        }
    }

    static {
        LOGGER = AliceMain.LOGGER;
        percentComplete = 0.0f;
        prevMillis = -1L;
    }

    public int getChatWidth() {
        return GuiChat.calculateChatboxWidth(this.mc.gameSettings.chatWidth);
    }

    public int getLineCount() {
        return this.getChatHeight() / 9;
    }

    public List<String> getSentMessages() {
        return this.sentMessages;
    }

    public float getChatScale() {
        return this.mc.gameSettings.chatScale;
    }
}

