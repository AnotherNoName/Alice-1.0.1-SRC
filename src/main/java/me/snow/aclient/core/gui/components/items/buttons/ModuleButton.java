//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundEvent
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.core.gui.components.items.buttons;

import java.util.ArrayList;
import java.util.List;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.gui.LuigiGui;
import me.snow.aclient.core.gui.components.items.Item;
import me.snow.aclient.core.gui.components.items.buttons.BindButton;
import me.snow.aclient.core.gui.components.items.buttons.BooleanButton;
import me.snow.aclient.core.gui.components.items.buttons.Button;
import me.snow.aclient.core.gui.components.items.buttons.EnumButton;
import me.snow.aclient.core.gui.components.items.buttons.Slider;
import me.snow.aclient.core.gui.components.items.buttons.StringButton;
import me.snow.aclient.core.gui.components.items.buttons.UnlimitedSlider;
import me.snow.aclient.core.setting.Bind;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.ClickGui;
import me.snow.aclient.util.ColorUtil;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import org.lwjgl.opengl.GL11;

public class ModuleButton
extends Button {
    private /* synthetic */ List<Item> items;
    private /* synthetic */ boolean subOpen;
    private final /* synthetic */ Module module;
    private final /* synthetic */ ResourceLocation gearawa;

    @Override
    public void onKeyTyped(char c, int n) {
        super.onKeyTyped(c, n);
        if (!this.items.isEmpty() && this.subOpen) {
            for (Item item : this.items) {
                if (item.isHidden()) continue;
                item.onKeyTyped(c, n);
            }
        }
    }

    @Override
    public void mouseClicked(int n, int n2, int n3) {
        super.mouseClicked(n, n2, n3);
        if (!this.items.isEmpty()) {
            if (n3 == 1 && this.isHovering(n, n2)) {
                this.subOpen = !this.subOpen;
                mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord((SoundEvent)SoundEvents.UI_BUTTON_CLICK, (float)1.0f));
            }
            if (this.subOpen) {
                for (Item item : this.items) {
                    if (item.isHidden()) continue;
                    item.mouseClicked(n, n2, n3);
                }
            }
        }
    }

    public Module getModule() {
        return this.module;
    }

    public ModuleButton(Module module) {
        super(module.getName());
        this.gearawa = new ResourceLocation("textures/gear.png");
        this.items = new ArrayList<Item>();
        this.module = module;
        this.initSettings();
    }

    @Override
    public int getHeight() {
        if (this.subOpen) {
            int n = 11;
            for (Item item : this.items) {
                if (item.isHidden()) continue;
                n += item.getHeight() + 1;
            }
            return n + 2;
        }
        return 11;
    }

    @Override
    public void toggle() {
        this.module.toggle();
    }

    @Override
    public void drawScreen(int n, int n2, float f) {
        super.drawScreen(n, n2, f);
        if (!this.items.isEmpty()) {
            ClickGui clickGui = AliceMain.moduleManager.getModuleByClass(ClickGui.class);
            AliceMain.textManager.drawStringWithShadow(clickGui.openCloseChange.getValue().booleanValue() ? (this.subOpen ? clickGui.close.getValue() : clickGui.open.getValue()) : clickGui.moduleButton.getValue(), this.x - 1.5f + (float)this.width - 7.4f, this.y - 4.0f - (float)LuigiGui.getClickGui().getTextOffset(), ColorUtil.toRGBA(ClickGui.getInstance().ARed.getValue(), ClickGui.getInstance().AGreen.getValue(), ClickGui.getInstance().ABlue.getValue(), 255));
            if (ClickGui.getInstance().futuregear.getValue().booleanValue()) {
                mc.getTextureManager().bindTexture(this.gearawa);
                ModuleButton.drawCompleteImage(this.x - 4.0f + (float)this.width - 7.4f, this.y - 4.8f - (float)LuigiGui.getClickGui().getTextOffset(), 9, 9);
            }
            if (this.subOpen) {
                float f2 = 1.0f;
                for (Item item : this.items) {
                    if (!item.isHidden()) {
                        item.setLocation(this.x + 1.0f, this.y + (f2 += 12.0f));
                        item.setHeight(11);
                        item.setWidth(this.width - 9);
                        item.drawScreen(n, n2, f);
                    }
                    item.update();
                }
            }
        }
    }

    @Override
    public boolean getState() {
        return this.module.isEnabled();
    }

    public void initSettings() {
        ArrayList<Item> arrayList = new ArrayList<Item>();
        if (!this.module.getSettings().isEmpty()) {
            for (Setting setting : this.module.getSettings()) {
                if (setting.getValue() instanceof Boolean && !setting.getName().equals("Enabled")) {
                    arrayList.add(new BooleanButton(setting));
                }
                if (setting.getValue() instanceof Bind && !this.module.getName().equalsIgnoreCase("Hud")) {
                    arrayList.add(new BindButton(setting));
                }
                if (setting.getValue() instanceof String || setting.getValue() instanceof Character) {
                    arrayList.add(new StringButton(setting));
                }
                if (setting.isNumberSetting()) {
                    if (setting.hasRestriction()) {
                        arrayList.add(new Slider(setting));
                        continue;
                    }
                    arrayList.add(new UnlimitedSlider(setting));
                }
                if (!setting.isEnumSetting()) continue;
                arrayList.add(new EnumButton(setting));
            }
        }
        this.items = arrayList;
    }

    public static void drawCompleteImage(float f, float f2, int n, int n2) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)f, (float)f2, (float)0.0f);
        GL11.glBegin((int)7);
        GL11.glTexCoord2f((float)0.0f, (float)0.0f);
        GL11.glVertex3f((float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glTexCoord2f((float)0.0f, (float)1.0f);
        GL11.glVertex3f((float)0.0f, (float)n2, (float)0.0f);
        GL11.glTexCoord2f((float)1.0f, (float)1.0f);
        GL11.glVertex3f((float)n, (float)n2, (float)0.0f);
        GL11.glTexCoord2f((float)1.0f, (float)0.0f);
        GL11.glVertex3f((float)n, (float)0.0f, (float)0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
}

