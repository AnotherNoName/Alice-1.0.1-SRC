//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  org.lwjgl.input.Mouse
 */
package me.snow.aclient.core.gui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.gui.components.Component;
import me.snow.aclient.core.gui.components.items.Item;
import me.snow.aclient.core.gui.components.items.buttons.ModuleButton;
import me.snow.aclient.core.gui.effect.Snow;
import me.snow.aclient.core.gui.effect.particles.ParticleSystem;
import me.snow.aclient.module.Feature;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.ClickGui;
import me.snow.aclient.module.modules.client.Colors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;

public class LuigiGui
extends GuiScreen {
    public /* synthetic */ ParticleSystem particleSystem;
    final /* synthetic */ ScaledResolution res;
    private static /* synthetic */ LuigiGui INSTANCE;
    private /* synthetic */ ArrayList<Snow> _snowList;
    /* synthetic */ Random random;
    private final /* synthetic */ ArrayList<Component> components;

    static {
        INSTANCE = new LuigiGui();
    }

    public void mouseReleased(int n, int n2, int n3) {
        this.components.forEach(component -> component.mouseReleased(n, n2, n3));
    }

    public static LuigiGui getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LuigiGui();
        }
        return INSTANCE;
    }

    public LuigiGui() {
        this.res = new ScaledResolution(Minecraft.getMinecraft());
        this.components = new ArrayList();
        this.random = new Random();
        this._snowList = new ArrayList();
        this.setInstance();
        this.load();
    }

    public void checkMouseWheel() {
        int n = Mouse.getDWheel();
        if (n < 0) {
            this.components.forEach(component -> component.setY(component.getY() - 10));
        } else if (n > 0) {
            this.components.forEach(component -> component.setY(component.getY() + 10));
        }
    }

    public Component getComponentByName(String string) {
        for (Component component : this.components) {
            if (!component.getName().equalsIgnoreCase(string)) continue;
            return component;
        }
        return null;
    }

    public int getTextOffset() {
        return -6;
    }

    public final ArrayList<Component> getComponents() {
        return this.components;
    }

    public void updateModule(Module module) {
        block0: for (Component component : this.components) {
            for (Item item : component.getItems()) {
                if (!(item instanceof ModuleButton)) continue;
                ModuleButton moduleButton = (ModuleButton)item;
                Module module2 = moduleButton.getModule();
                if (module == null || !module.equals(module2)) continue;
                moduleButton.initSettings();
                continue block0;
            }
        }
    }

    public void drawScreen(int n, int n2, float f) {
        if (ClickGui.getInstance().dark.getValue().booleanValue()) {
            this.drawDefaultBackground();
        }
        ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        this.checkMouseWheel();
        this.components.forEach(component -> component.drawScreen(n, n2, f));
        ScaledResolution scaledResolution2 = new ScaledResolution(this.mc);
        if (ClickGui.getInstance().gradiant.getValue().booleanValue()) {
            Color color = ClickGui.getInstance().colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(ClickGui.getInstance().gradiantred.getValue(), ClickGui.getInstance().gradiantgreen.getValue(), ClickGui.getInstance().gradiantblue.getValue(), ClickGui.getInstance().gradiantalpha.getValue());
            this.drawGradientRect(0, 0, scaledResolution2.getScaledWidth(), scaledResolution2.getScaledHeight(), 0, ClickGui.getInstance().colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColorHex() : new Color(color.getRed(), color.getGreen(), color.getBlue(), ClickGui.getInstance().gradiantalpha.getValue() / 2).getRGB());
        }
        this.checkMouseWheel();
        this.components.forEach(component -> component.drawScreen(n, n2, f));
        if (!this._snowList.isEmpty() && ClickGui.getInstance().snowing.getValue().booleanValue()) {
            this._snowList.forEach(snow -> snow.Update(scaledResolution));
        }
        if (this.particleSystem != null && ClickGui.getInstance().particles.getValue().booleanValue()) {
            this.particleSystem.render(n, n2);
        } else {
            this.particleSystem = new ParticleSystem(new ScaledResolution(this.mc));
        }
    }

    public void keyTyped(char c, int n) throws IOException {
        super.keyTyped(c, n);
        this.components.forEach(component -> component.onKeyTyped(c, n));
    }

    public void updateScreen() {
        if (this.particleSystem != null) {
            this.particleSystem.update();
        }
    }

    private void setInstance() {
        INSTANCE = this;
    }

    public void mouseClicked(int n, int n2, int n3) {
        this.components.forEach(component -> component.mouseClicked(n, n2, n3));
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    private void load() {
        int n;
        for (n = 0; n < 100; ++n) {
            for (int i = 0; i < 3; ++i) {
                Object object = new Snow(25 * n, i * -50, this.random.nextInt(3) + 1, this.random.nextInt(2) + 1);
                this._snowList.add((Snow)object);
            }
        }
        n = -112;
        for (Object object : AliceMain.moduleManager.getCategories()) {
            this.components.add(new Component(object.getName(), n += 114, 6, true, (Module.Category)((Object)object)){
                final /* synthetic */ Module.Category val$category;

                @Override
                public void setupItems() {
                    AliceMain.moduleManager.getModulesByCategory(this.val$category).forEach(module -> {
                        if (!module.hidden) {
                            this.addButton(new ModuleButton((Module)module));
                        }
                    });
                }
                {
                    this.val$category = category;
                    super(string, n, n2, bl);
                }
            });
        }
        this.components.forEach(component -> component.getItems().sort(Comparator.comparing(Feature::getName)));
    }

    public static LuigiGui getClickGui() {
        return LuigiGui.getInstance();
    }
}

