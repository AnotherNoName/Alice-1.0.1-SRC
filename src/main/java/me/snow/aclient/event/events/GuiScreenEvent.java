/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 */
package me.snow.aclient.event.events;

import net.minecraft.client.gui.GuiScreen;

public class GuiScreenEvent {
    private /* synthetic */ GuiScreen screen;

    public GuiScreenEvent(GuiScreen guiScreen) {
        this.screen = guiScreen;
    }

    public void setScreen(GuiScreen guiScreen) {
        this.screen = guiScreen;
    }

    public GuiScreen getScreen() {
        return this.screen;
    }

    public static class Closed
    extends GuiScreenEvent {
        public Closed(GuiScreen guiScreen) {
            super(guiScreen);
        }
    }

    public static class Displayed
    extends GuiScreenEvent {
        public Displayed(GuiScreen guiScreen) {
            super(guiScreen);
        }
    }
}

