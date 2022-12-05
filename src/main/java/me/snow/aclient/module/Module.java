//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.client.event.RenderHandEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package me.snow.aclient.module;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Bind;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.ClientEvent;
import me.snow.aclient.event.events.Render2DEvent;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.manager.ModuleManager;
import me.snow.aclient.module.Feature;
import me.snow.aclient.module.modules.client.HUD;
import me.snow.aclient.util.Util;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

public class Module
extends Feature {
    public /* synthetic */ boolean alwaysListening;
    public /* synthetic */ float arrayListOffset;
    public /* synthetic */ float vOffset;
    public /* synthetic */ boolean sliding;
    private final /* synthetic */ Category category;
    public /* synthetic */ boolean hidden;
    public /* synthetic */ Setting<String> displayName;
    private final /* synthetic */ String description;
    public /* synthetic */ Setting<Boolean> enabled;
    public /* synthetic */ Animation animation;
    public /* synthetic */ Setting<Bind> bind;
    public /* synthetic */ float arrayListVOffset;
    public /* synthetic */ boolean hasListener;
    public /* synthetic */ float offset;
    public /* synthetic */ Setting<Boolean> drawn;

    public void enable() {
        this.enabled.setValue(true);
        this.onToggle();
        this.onEnable();
        if (this.isOn() && this.hasListener && !this.alwaysListening) {
            MinecraftForge.EVENT_BUS.register((Object)this);
        }
    }

    public void setBind(int n) {
        this.bind.setValue(new Bind(n));
    }

    public void onToggle() {
    }

    public void setEnabled(boolean bl) {
        if (bl) {
            this.enable();
        } else {
            this.disable();
        }
    }

    public Category getCategory() {
        return this.category;
    }

    public void onEnable() {
    }

    public void onTick() {
    }

    public String getDisplayInfo() {
        return null;
    }

    public void onRenderHand(RenderHandEvent renderHandEvent) {
    }

    public boolean isDrawn() {
        return this.drawn.getValue();
    }

    public void toggle() {
        ClientEvent clientEvent = new ClientEvent(!this.isEnabled() ? 1 : 0, this);
        MinecraftForge.EVENT_BUS.post((Event)clientEvent);
        if (!clientEvent.isCanceled()) {
            this.setEnabled(!this.isEnabled());
        }
    }

    public void setDisplayName(String string) {
        Module module = AliceMain.moduleManager.getModuleByDisplayName(string);
        Module module2 = ModuleManager.getModuleByName(string);
        if (module == null && module2 == null) {
            Command.sendMessage(String.valueOf(new StringBuilder().append(this.getDisplayName()).append(", Original name: ").append(this.getName()).append(", has been renamed to: ").append(string)));
            this.displayName.setValue(string);
            return;
        }
        Command.sendMessage("\u00a7cA module of this name already exists.");
    }

    public void setDrawn(boolean bl) {
        this.drawn.setValue(bl);
    }

    public boolean isOn() {
        return this.enabled.getValue();
    }

    public void onRender2D(Render2DEvent render2DEvent) {
    }

    public String getDescription() {
        return this.description;
    }

    public void onDisable() {
    }

    public boolean listening() {
        return this.hasListener && this.isOn() || this.alwaysListening;
    }

    public void onLoad() {
    }

    public Module(String string, String string2, Category category, boolean bl, boolean bl2, boolean bl3) {
        super(string);
        this.enabled = this.register(new Setting<Boolean>("Enabled", false));
        this.drawn = this.register(new Setting<Boolean>("Drawn", true));
        this.bind = this.register(new Setting<Bind>("Bind", new Bind(-1)));
        this.arrayListOffset = 0.0f;
        this.arrayListVOffset = 0.0f;
        this.displayName = this.register(new Setting<String>("DisplayName", string));
        this.description = string2;
        this.category = category;
        this.hasListener = bl;
        this.hidden = bl2;
        this.alwaysListening = bl3;
        this.animation = new Animation(this);
    }

    public void disable() {
        if (this.hasListener && !this.alwaysListening) {
            MinecraftForge.EVENT_BUS.unregister((Object)this);
        }
        this.enabled.setValue(false);
        this.onToggle();
        this.onDisable();
    }

    public boolean isSliding() {
        return this.sliding;
    }

    public void onUpdate() {
    }

    public boolean isOff() {
        return this.enabled.getValue() == false;
    }

    public void onLogin() {
    }

    public String getFullArrayString() {
        return String.valueOf(new StringBuilder().append(this.getDisplayName()).append("\u00a78").append(this.getDisplayInfo() != null ? String.valueOf(new StringBuilder().append(" [\u00a7r").append(this.getDisplayInfo()).append("\u00a78]")) : ""));
    }

    public void onRender3D(Render3DEvent render3DEvent) {
    }

    public String getDisplayName() {
        return this.displayName.getValue();
    }

    public String getInfo() {
        return null;
    }

    public void onUnload() {
    }

    public Bind getBind() {
        return this.bind.getValue();
    }

    public void onLogout() {
    }

    public static enum Category {
        COMBAT("Combat"),
        MISC("Misc"),
        RENDER("Render"),
        MOVEMENT("Movement"),
        PLAYER("Player"),
        CLIENT("Client");

        private final /* synthetic */ String name;

        public String getName() {
            return this.name;
        }

        private Category(String string2) {
            this.name = string2;
        }
    }

    public class Animation
    extends Thread {
        public /* synthetic */ Module module;
        /* synthetic */ ScheduledExecutorService service;
        public /* synthetic */ float vOffset;
        public /* synthetic */ String lastText;
        public /* synthetic */ float offset;
        public /* synthetic */ boolean shouldMetaSlide;

        public Animation(Module module2) {
            super("Animation");
            this.service = Executors.newSingleThreadScheduledExecutor();
            this.module = module2;
        }

        @Override
        public void run() {
            String string = HUD.getInstance().disss.getValue() != false ? " \u00a7f" : " [\u00a7f";
            String string2 = HUD.getInstance().disss.getValue() != false ? "" : "\u00a77]";
            String string3 = HUD.getInstance().disss.getValue() != false ? "\u00a7f" : "\u00a77";
            String string4 = String.valueOf(new StringBuilder().append(this.module.getDisplayName()).append(string3).append(this.module.getDisplayInfo() != null ? String.valueOf(new StringBuilder().append(string).append(this.module.getDisplayInfo()).append(string2)) : ""));
            this.module.offset = (float)Module.this.renderer.getStringWidth(string4) / HUD.getInstance().animationHorizontalTime.getValue().floatValue();
            this.module.vOffset = (float)Module.this.renderer.getFontHeight() / HUD.getInstance().animationVerticalTime.getValue().floatValue();
            if (this.module.isEnabled() && HUD.getInstance().animationHorizontalTime.getValue() != 1) {
                if (this.module.arrayListOffset > this.module.offset && Util.mc.world != null) {
                    this.module.arrayListOffset -= this.module.offset;
                    this.module.sliding = true;
                }
            } else if (this.module.isDisabled() && HUD.getInstance().animationHorizontalTime.getValue() != 1) {
                if (this.module.arrayListOffset < (float)Module.this.renderer.getStringWidth(string4) && Util.mc.world != null) {
                    this.module.arrayListOffset += this.module.offset;
                    this.module.sliding = true;
                } else {
                    this.module.sliding = false;
                }
            }
        }

        @Override
        public void start() {
            System.out.println(String.valueOf(new StringBuilder().append("Starting animation thread for ").append(this.module.getName())));
            this.service.scheduleAtFixedRate(this, 0L, 1L, TimeUnit.MILLISECONDS);
        }
    }
}

