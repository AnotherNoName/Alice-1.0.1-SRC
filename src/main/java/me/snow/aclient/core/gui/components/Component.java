//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundEvent
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.core.gui.components;

import java.awt.Color;
import java.util.ArrayList;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.gui.LuigiGui;
import me.snow.aclient.core.gui.components.items.Item;
import me.snow.aclient.core.gui.components.items.buttons.Button;
import me.snow.aclient.core.gui.components.items.buttons.ModuleButton;
import me.snow.aclient.module.Feature;
import me.snow.aclient.module.modules.client.ClickGui;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.module.modules.client.HUD;
import me.snow.aclient.util.ColorUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.RenderUtil;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import org.lwjgl.opengl.GL11;

public class Component
extends Feature {
    private final /* synthetic */ ArrayList<Item> items;
    private /* synthetic */ int height;
    private /* synthetic */ boolean open;
    private final /* synthetic */ ResourceLocation arrow;
    private /* synthetic */ int x2;
    private /* synthetic */ int y2;
    private /* synthetic */ int width;
    private /* synthetic */ int x;
    private /* synthetic */ int y;
    public /* synthetic */ boolean drag;
    private /* synthetic */ boolean hidden;

    public void setHeight(int n) {
        this.height = n;
    }

    public void onKeyTyped(char c, int n) {
        if (!this.open) {
            return;
        }
        this.getItems().forEach(item -> item.onKeyTyped(c, n));
    }

    public void setWidth(int n) {
        this.width = n;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public int getHeight() {
        return this.height;
    }

    private void drag(int n, int n2) {
        if (!this.drag) {
            return;
        }
        this.x = this.x2 + n;
        this.y = this.y2 + n2;
    }

    public void mouseClicked(int n, int n2, int n3) {
        if (n3 == 0 && this.isHovering(n, n2)) {
            this.x2 = this.x - n;
            this.y2 = this.y - n2;
            LuigiGui.getClickGui().getComponents().forEach(component -> {
                if (component.drag) {
                    component.drag = false;
                }
            });
            this.drag = true;
            return;
        }
        if (n3 == 1 && this.isHovering(n, n2)) {
            this.open = !this.open;
            mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord((SoundEvent)SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
            return;
        }
        if (!this.open) {
            return;
        }
        this.getItems().forEach(item -> item.mouseClicked(n, n2, n3));
    }

    public void addButton(Button button) {
        this.items.add(button);
    }

    public final ArrayList<Item> getItems() {
        return this.items;
    }

    public void setX(int n) {
        this.x = n;
    }

    private boolean isHovering(int n, int n2) {
        return n >= this.getX() && n <= this.getX() + this.getWidth() && n2 >= this.getY() && n2 <= this.getY() + this.getHeight() - (this.open ? 2 : 0);
    }

    private float getTotalItemHeight() {
        float f = 0.0f;
        for (Item item : this.getItems()) {
            f += (float)item.getHeight() + 2.5f;
        }
        return f;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setHidden(boolean bl) {
        this.hidden = bl;
    }

    public int getY() {
        return this.y;
    }

    public void drawScreen(int n, int n2, float f) {
        this.drag(n, n2);
        float f2 = this.open ? this.getTotalItemHeight() - 2.0f : 0.0f;
        int n3 = -7829368;
        if (this.open) {
            RenderUtil.drawRect(this.x, (float)this.y + 14.0f, this.x + this.width, (float)(this.y + this.height) + f2, 0);
            if (ClickGui.getInstance().outline2.getValue().booleanValue()) {
                this.drawOutline(ClickGui.getInstance().outlineThickness.getValue().floatValue(), n3);
            }
        }
        if (ClickGui.getInstance().frameSettings.getValue().booleanValue()) {
            int n4 = n3 = ColorUtil.toARGB(ClickGui.getInstance().frameRed.getValue(), ClickGui.getInstance().frameGreen.getValue(), ClickGui.getInstance().frameBlue.getValue(), ClickGui.getInstance().frameAlpha.getValue());
            RenderUtil.drawRect(this.x, (float)this.y + 11.0f, this.x + this.width, this.y + this.height - 6, ClickGui.getInstance().colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColor().getRGB() : ColorUtil.toARGB(ClickGui.getInstance().frameRed.getValue(), ClickGui.getInstance().frameGreen.getValue(), ClickGui.getInstance().frameBlue.getValue(), ClickGui.getInstance().frameAlpha.getValue()));
        }
        Color color = new Color(ClickGui.getInstance().TcolorSync.getValue() != false ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toARGB(ClickGui.getInstance().topRed.getValue(), ClickGui.getInstance().topGreen.getValue(), ClickGui.getInstance().topBlue.getValue(), ClickGui.getInstance().topAlpha.getValue()));
        int n5 = n3 = ColorUtil.toRGBA(color.getRed(), color.getGreen(), color.getBlue(), ClickGui.getInstance().topAlpha.getValue());
        if (ClickGui.getInstance().rainbowRolling.getValue().booleanValue() && ClickGui.getInstance().colorSync.getValue().booleanValue() && Colors.INSTANCE.rainbow.getValue().booleanValue()) {
            RenderUtil.drawGradientRect((float)this.x, (float)this.y - 1.5f, (float)this.width, (float)(this.height - 4), (int)HUD.getInstance().colorMap.get(MathUtil.clamp(this.y, 0, this.renderer.scaledHeight)), (int)HUD.getInstance().colorMap.get(MathUtil.clamp(this.y + this.height - 4, 0, this.renderer.scaledHeight)));
        } else {
            RenderUtil.drawRect(this.x, (float)this.y - 1.5f, this.x + this.width, this.y + this.height - 6, n3);
        }
        if (this.open) {
            RenderUtil.drawRect(this.x, (float)this.y + 12.5f, this.x + this.width, (float)(this.y + this.height) + f2, ColorUtil.toRGBA(ClickGui.getInstance().b_red.getValue(), ClickGui.getInstance().b_green.getValue(), ClickGui.getInstance().b_blue.getValue(), ClickGui.getInstance().b_alpha.getValue()));
        }
        if (this.open) {
            RenderUtil.drawRect(this.x, (float)this.y + 12.5f, this.x + this.width, (float)(this.y + this.height) + f2, 0);
            if (ClickGui.getInstance().outline.getValue().booleanValue()) {
                Color color2;
                if (ClickGui.getInstance().rainbowRolling.getValue().booleanValue()) {
                    GlStateManager.disableTexture2D();
                    GlStateManager.enableBlend();
                    GlStateManager.disableAlpha();
                    GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
                    GlStateManager.shadeModel((int)7425);
                    GL11.glBegin((int)1);
                    color2 = new Color(HUD.getInstance().colorMap.get(MathUtil.clamp(this.y, 0, this.renderer.scaledHeight)));
                    GL11.glColor4f((float)((float)color2.getRed() / 255.0f), (float)((float)color2.getGreen() / 255.0f), (float)((float)color2.getBlue() / 255.0f), (float)((float)color2.getAlpha() / 255.0f));
                    GL11.glVertex3f((float)(this.x + this.width), (float)((float)this.y - 1.5f), (float)0.0f);
                    GL11.glVertex3f((float)this.x, (float)((float)this.y - 1.5f), (float)0.0f);
                    GL11.glVertex3f((float)this.x, (float)((float)this.y - 1.5f), (float)0.0f);
                    float f3 = (float)this.getHeight() - 1.5f;
                    for (Item item : this.getItems()) {
                        color2 = new Color(HUD.getInstance().colorMap.get(MathUtil.clamp((int)((float)this.y + (f3 += (float)item.getHeight() + 1.5f)), 0, this.renderer.scaledHeight)));
                        GL11.glColor4f((float)((float)color2.getRed() / 255.0f), (float)((float)color2.getGreen() / 255.0f), (float)((float)color2.getBlue() / 255.0f), (float)((float)color2.getAlpha() / 255.0f));
                        GL11.glVertex3f((float)this.x, (float)((float)this.y + f3), (float)0.0f);
                        GL11.glVertex3f((float)this.x, (float)((float)this.y + f3), (float)0.0f);
                    }
                    color2 = new Color(HUD.getInstance().colorMap.get(MathUtil.clamp((int)((float)(this.y + this.height) + f2), 0, this.renderer.scaledHeight)));
                    GL11.glColor4f((float)((float)color2.getRed() / 255.0f), (float)((float)color2.getGreen() / 255.0f), (float)((float)color2.getBlue() / 255.0f), (float)((float)color2.getAlpha() / 255.0f));
                    GL11.glVertex3f((float)(this.x + this.width), (float)((float)(this.y + this.height) + f2), (float)0.0f);
                    GL11.glVertex3f((float)(this.x + this.width), (float)((float)(this.y + this.height) + f2), (float)0.0f);
                    for (Item item : this.getItems()) {
                        color2 = new Color(HUD.getInstance().colorMap.get(MathUtil.clamp((int)((float)this.y + (f3 -= (float)item.getHeight() + 1.5f)), 0, this.renderer.scaledHeight)));
                        GL11.glColor4f((float)((float)color2.getRed() / 255.0f), (float)((float)color2.getGreen() / 255.0f), (float)((float)color2.getBlue() / 255.0f), (float)((float)color2.getAlpha() / 255.0f));
                        GL11.glVertex3f((float)(this.x + this.width), (float)((float)this.y + f3), (float)0.0f);
                        GL11.glVertex3f((float)(this.x + this.width), (float)((float)this.y + f3), (float)0.0f);
                    }
                    GL11.glVertex3f((float)(this.x + this.width), (float)this.y, (float)0.0f);
                    GL11.glEnd();
                    GlStateManager.shadeModel((int)7424);
                    GlStateManager.disableBlend();
                    GlStateManager.enableAlpha();
                    GlStateManager.enableTexture2D();
                } else {
                    GlStateManager.disableTexture2D();
                    GlStateManager.enableBlend();
                    GlStateManager.disableAlpha();
                    GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
                    GlStateManager.shadeModel((int)7425);
                    GL11.glBegin((int)2);
                    color2 = ClickGui.getInstance().colorSync.getValue() != false ? new Color(Colors.INSTANCE.getCurrentColorHex()) : new Color(AliceMain.colorManager.getColorAsIntFullAlpha());
                    GL11.glColor4f((float)color2.getRed(), (float)color2.getGreen(), (float)color2.getBlue(), (float)color2.getAlpha());
                    GL11.glVertex3f((float)this.x, (float)((float)this.y - 1.5f), (float)0.0f);
                    GL11.glVertex3f((float)(this.x + this.width), (float)((float)this.y - 1.5f), (float)0.0f);
                    GL11.glVertex3f((float)(this.x + this.width), (float)((float)(this.y + this.height) + f2), (float)0.0f);
                    GL11.glVertex3f((float)this.x, (float)((float)(this.y + this.height) + f2), (float)0.0f);
                    GL11.glEnd();
                    GlStateManager.shadeModel((int)7424);
                    GlStateManager.disableBlend();
                    GlStateManager.enableAlpha();
                    GlStateManager.enableTexture2D();
                }
            }
        }
        if (ClickGui.getInstance().centerAAA.getValue().booleanValue()) {
            AliceMain.textManager.drawStringWithShadow(this.getName(), (float)this.x + (float)(this.width / 2) - (float)(this.renderer.getStringWidth(this.getName()) / 2), (float)this.y - 4.0f - (float)LuigiGui.getClickGui().getTextOffset(), -1);
        } else {
            AliceMain.textManager.drawStringWithShadow(this.getName(), (float)this.x + 3.0f, (float)this.y - 4.0f - (float)LuigiGui.getClickGui().getTextOffset(), -1);
        }
        if (this.open) {
            if (ClickGui.getInstance().toparrow.getValue().booleanValue()) {
                mc.getTextureManager().bindTexture(this.arrow);
                ModuleButton.drawCompleteImage((float)this.x - 1.5f + (float)this.width - 12.0f, (float)this.y - 5.0f - (float)LuigiGui.getClickGui().getTextOffset(), 12, 11);
            }
            float f4 = (float)(this.getY() + this.getHeight()) - 3.0f;
            for (Item item : this.getItems()) {
                if (item.isHidden()) continue;
                item.setLocation((float)this.x + 2.0f, f4);
                item.setWidth(this.getWidth() - 4);
                item.drawScreen(n, n2, f);
                f4 += (float)item.getHeight() + 2.5f;
            }
        }
    }

    public void setupItems() {
    }

    private void drawOutline(float f, int n) {
        float f2 = 0.0f;
        if (this.open) {
            f2 = this.getTotalItemHeight() - 2.0f;
        }
        RenderUtil.drawLine(this.x, (float)this.y - 1.5f, this.x, (float)(this.y + this.height) + f2, f, ClickGui.getInstance().colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA(ClickGui.getInstance().o_red.getValue(), ClickGui.getInstance().o_green.getValue(), ClickGui.getInstance().o_blue.getValue(), ClickGui.getInstance().o_alpha.getValue()));
        RenderUtil.drawLine(this.x, (float)this.y - 1.5f, this.x + this.width, (float)this.y - 1.5f, f, ClickGui.getInstance().colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA(ClickGui.getInstance().o_red.getValue(), ClickGui.getInstance().o_green.getValue(), ClickGui.getInstance().o_blue.getValue(), ClickGui.getInstance().o_alpha.getValue()));
        RenderUtil.drawLine(this.x + this.width, (float)this.y - 1.5f, this.x + this.width, (float)(this.y + this.height) + f2, f, ClickGui.getInstance().colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA(ClickGui.getInstance().o_red.getValue(), ClickGui.getInstance().o_green.getValue(), ClickGui.getInstance().o_blue.getValue(), ClickGui.getInstance().o_alpha.getValue()));
        RenderUtil.drawLine(this.x, (float)(this.y + this.height) + f2, this.x + this.width, (float)(this.y + this.height) + f2, f, ClickGui.getInstance().colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA(ClickGui.getInstance().o_red.getValue(), ClickGui.getInstance().o_green.getValue(), ClickGui.getInstance().o_blue.getValue(), ClickGui.getInstance().o_alpha.getValue()));
    }

    public void setY(int n) {
        this.y = n;
    }

    public void mouseReleased(int n, int n2, int n3) {
        if (n3 == 0) {
            this.drag = false;
        }
        if (!this.open) {
            return;
        }
        this.getItems().forEach(item -> item.mouseReleased(n, n2, n3));
    }

    public int getWidth() {
        return this.width;
    }

    public Component(String string, int n, int n2, boolean bl) {
        super(string);
        this.items = new ArrayList();
        this.arrow = new ResourceLocation("textures/arrow.png");
        this.x = n;
        this.y = n2;
        this.width = 110;
        this.height = 18;
        this.open = bl;
        this.setupItems();
    }

    public int getX() {
        return this.x;
    }
}

