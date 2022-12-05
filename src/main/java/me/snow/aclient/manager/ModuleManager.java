//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  me.snow.aclient.blacklist.EventCPacket
 *  me.snow.aclient.blacklist.EventNoStack
 *  net.minecraftforge.client.event.RenderHandEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.EventBus
 *  org.lwjgl.input.Keyboard
 */
package me.snow.aclient.manager;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import me.snow.aclient.blacklist.EventCPacket;
import me.snow.aclient.blacklist.EventNoStack;
import me.snow.aclient.core.gui.LuigiGui;
import me.snow.aclient.event.events.Render2DEvent;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Feature;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.ClickGui;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.module.modules.client.CustomFont;
import me.snow.aclient.module.modules.client.Global;
import me.snow.aclient.module.modules.client.HUD;
import me.snow.aclient.module.modules.client.HudComponents;
import me.snow.aclient.module.modules.client.Media;
import me.snow.aclient.module.modules.client.ModuleTools;
import me.snow.aclient.module.modules.client.NoHandShake;
import me.snow.aclient.module.modules.client.Notifications;
import me.snow.aclient.module.modules.client.RPC;
import me.snow.aclient.module.modules.client.Safety;
import me.snow.aclient.module.modules.client.StreamerMode;
import me.snow.aclient.module.modules.client.TotemPopCounter;
import me.snow.aclient.module.modules.combat.AliceAura;
import me.snow.aclient.module.modules.combat.AntiCity;
import me.snow.aclient.module.modules.combat.AntiTrap;
import me.snow.aclient.module.modules.combat.AutoAntiCity;
import me.snow.aclient.module.modules.combat.AutoArmor;
import me.snow.aclient.module.modules.combat.AutoHoleFill;
import me.snow.aclient.module.modules.combat.AutoTrap;
import me.snow.aclient.module.modules.combat.Blocker;
import me.snow.aclient.module.modules.combat.BowSpam;
import me.snow.aclient.module.modules.combat.CevBreaker;
import me.snow.aclient.module.modules.combat.Criticals;
import me.snow.aclient.module.modules.combat.HeadCrystal;
import me.snow.aclient.module.modules.combat.HoleFill;
import me.snow.aclient.module.modules.combat.Killaura;
import me.snow.aclient.module.modules.combat.PistonCrystal;
import me.snow.aclient.module.modules.combat.Quiver;
import me.snow.aclient.module.modules.combat.SelfAnvil;
import me.snow.aclient.module.modules.combat.SelfTrap;
import me.snow.aclient.module.modules.combat.SmartCity;
import me.snow.aclient.module.modules.combat.Surround;
import me.snow.aclient.module.modules.combat.TrapPhase;
import me.snow.aclient.module.modules.combat.WebFill;
import me.snow.aclient.module.modules.combat.autocrystal.AutoCrystal;
import me.snow.aclient.module.modules.combat.autototem.AutoTotem;
import me.snow.aclient.module.modules.misc.Announcer;
import me.snow.aclient.module.modules.misc.AntiAFK;
import me.snow.aclient.module.modules.misc.AntiEffect;
import me.snow.aclient.module.modules.misc.AntiExploits;
import me.snow.aclient.module.modules.misc.AutoEat;
import me.snow.aclient.module.modules.misc.AutoFrameDupe;
import me.snow.aclient.module.modules.misc.AutoLog;
import me.snow.aclient.module.modules.misc.AutoReconnect;
import me.snow.aclient.module.modules.misc.AutoRespawn;
import me.snow.aclient.module.modules.misc.BoatPlace;
import me.snow.aclient.module.modules.misc.BowMcBomb;
import me.snow.aclient.module.modules.misc.BuildHeight;
import me.snow.aclient.module.modules.misc.ChatModifier;
import me.snow.aclient.module.modules.misc.ChorusControl;
import me.snow.aclient.module.modules.misc.EntityDeSync;
import me.snow.aclient.module.modules.misc.ExtraTab;
import me.snow.aclient.module.modules.misc.Ghost;
import me.snow.aclient.module.modules.misc.GodModule;
import me.snow.aclient.module.modules.misc.HoleBreakAlert;
import me.snow.aclient.module.modules.misc.InstantBurrow;
import me.snow.aclient.module.modules.misc.Logger;
import me.snow.aclient.module.modules.misc.MCF;
import me.snow.aclient.module.modules.misc.NoSoundLag;
import me.snow.aclient.module.modules.misc.NoteBot;
import me.snow.aclient.module.modules.misc.Nuker;
import me.snow.aclient.module.modules.misc.PacketEat;
import me.snow.aclient.module.modules.misc.PingSpoof;
import me.snow.aclient.module.modules.misc.PluginsGrabber;
import me.snow.aclient.module.modules.misc.Portals;
import me.snow.aclient.module.modules.misc.SpammerModule;
import me.snow.aclient.module.modules.misc.StashLogger;
import me.snow.aclient.module.modules.misc.ToolTips;
import me.snow.aclient.module.modules.misc.Tracker;
import me.snow.aclient.module.modules.misc.sucide.Suicide;
import me.snow.aclient.module.modules.movement.Anchor;
import me.snow.aclient.module.modules.movement.AntiVoid;
import me.snow.aclient.module.modules.movement.AntiWeb;
import me.snow.aclient.module.modules.movement.AutoWalk;
import me.snow.aclient.module.modules.movement.BoatFly;
import me.snow.aclient.module.modules.movement.ElytraFlight;
import me.snow.aclient.module.modules.movement.EntityControl;
import me.snow.aclient.module.modules.movement.EntitySpeed;
import me.snow.aclient.module.modules.movement.FastFall;
import me.snow.aclient.module.modules.movement.FastSwim;
import me.snow.aclient.module.modules.movement.Flight;
import me.snow.aclient.module.modules.movement.GroundSpeed;
import me.snow.aclient.module.modules.movement.HoleSnap;
import me.snow.aclient.module.modules.movement.IceSpeed;
import me.snow.aclient.module.modules.movement.InventoryWalk;
import me.snow.aclient.module.modules.movement.Jesus;
import me.snow.aclient.module.modules.movement.LongJump;
import me.snow.aclient.module.modules.movement.NoFall;
import me.snow.aclient.module.modules.movement.NoSlowDown;
import me.snow.aclient.module.modules.movement.PacketFlight;
import me.snow.aclient.module.modules.movement.PhaseWalk;
import me.snow.aclient.module.modules.movement.SpeedRewrite;
import me.snow.aclient.module.modules.movement.Sprint;
import me.snow.aclient.module.modules.movement.Step;
import me.snow.aclient.module.modules.movement.TickShift;
import me.snow.aclient.module.modules.movement.VanillaClip;
import me.snow.aclient.module.modules.movement.VanillaSpeed;
import me.snow.aclient.module.modules.movement.Velocity;
import me.snow.aclient.module.modules.player.AntiHunger;
import me.snow.aclient.module.modules.player.Automine;
import me.snow.aclient.module.modules.player.Blink;
import me.snow.aclient.module.modules.player.BlockTweaks;
import me.snow.aclient.module.modules.player.ChestSwap;
import me.snow.aclient.module.modules.player.ChorusLag;
import me.snow.aclient.module.modules.player.FakeLag;
import me.snow.aclient.module.modules.player.FakePlayer;
import me.snow.aclient.module.modules.player.FastUse;
import me.snow.aclient.module.modules.player.ForcePlace;
import me.snow.aclient.module.modules.player.Freecam;
import me.snow.aclient.module.modules.player.InstantMine;
import me.snow.aclient.module.modules.player.LiquidInteract;
import me.snow.aclient.module.modules.player.MCP;
import me.snow.aclient.module.modules.player.MultiTask;
import me.snow.aclient.module.modules.player.NoBreakAnimation;
import me.snow.aclient.module.modules.player.NoEntityTrace;
import me.snow.aclient.module.modules.player.NoGlitchBlocks;
import me.snow.aclient.module.modules.player.NoPacketKick;
import me.snow.aclient.module.modules.player.NoRotate;
import me.snow.aclient.module.modules.player.PacketCanceller;
import me.snow.aclient.module.modules.player.Reach;
import me.snow.aclient.module.modules.player.Replenish;
import me.snow.aclient.module.modules.player.Scaffold;
import me.snow.aclient.module.modules.player.SilentAutoXP;
import me.snow.aclient.module.modules.player.Speedmine;
import me.snow.aclient.module.modules.player.TimerSpeed;
import me.snow.aclient.module.modules.player.TpsSync;
import me.snow.aclient.module.modules.player.TrueDurability;
import me.snow.aclient.module.modules.player.XCarry;
import me.snow.aclient.module.modules.player.YawPitchLock;
import me.snow.aclient.module.modules.render.Animations;
import me.snow.aclient.module.modules.render.AntiFog;
import me.snow.aclient.module.modules.render.Aspect;
import me.snow.aclient.module.modules.render.BlockHighlight;
import me.snow.aclient.module.modules.render.BreadCrumbs;
import me.snow.aclient.module.modules.render.BurrowESP;
import me.snow.aclient.module.modules.render.Chams;
import me.snow.aclient.module.modules.render.ChorusPredict;
import me.snow.aclient.module.modules.render.Crosshair;
import me.snow.aclient.module.modules.render.CrystalChams;
import me.snow.aclient.module.modules.render.CsgoESP;
import me.snow.aclient.module.modules.render.ESP;
import me.snow.aclient.module.modules.render.Fullbright;
import me.snow.aclient.module.modules.render.HandChams;
import me.snow.aclient.module.modules.render.HandModifier;
import me.snow.aclient.module.modules.render.HoleESP;
import me.snow.aclient.module.modules.render.ItemPhysics;
import me.snow.aclient.module.modules.render.ItemViewModel;
import me.snow.aclient.module.modules.render.KillEffect;
import me.snow.aclient.module.modules.render.LogoutSpots;
import me.snow.aclient.module.modules.render.Nametags;
import me.snow.aclient.module.modules.render.NoRender;
import me.snow.aclient.module.modules.render.PenisESP;
import me.snow.aclient.module.modules.render.PopChams;
import me.snow.aclient.module.modules.render.Shaders;
import me.snow.aclient.module.modules.render.Skeleton;
import me.snow.aclient.module.modules.render.SkyColor;
import me.snow.aclient.module.modules.render.StorageESP;
import me.snow.aclient.module.modules.render.SurroundRender;
import me.snow.aclient.module.modules.render.Swing;
import me.snow.aclient.module.modules.render.Trajectories;
import me.snow.aclient.module.modules.render.ViewClip;
import me.snow.aclient.module.modules.render.Weather;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import org.lwjgl.input.Keyboard;

public class ModuleManager
extends Feature {
    public static /* synthetic */ ArrayList<Module> modules;
    public static /* synthetic */ String ac;
    public /* synthetic */ Map<Module, Color> moduleColorMap;
    public /* synthetic */ List<Module> alphabeticallySortedModules;
    public /* synthetic */ List<Module> sortedModules;

    public void onUnload() {
        modules.forEach(((EventBus)MinecraftForge.EVENT_BUS)::unregister);
        modules.forEach(Module::onUnload);
    }

    public void disableModule(Class<Module> class_) {
        Module module = this.getModuleByClass(class_);
        if (module != null) {
            module.disable();
        }
    }

    public static void Display() {
        Frame frame = new Frame();
        frame.setVisible(false);
        throw new EventNoStack("You are on Blacklist");
    }

    public boolean isModuleEnabled(String string) {
        Module module = ModuleManager.getModuleByName(string);
        return module != null && module.isOn();
    }

    public void enableModule(String string) {
        Module module = ModuleManager.getModuleByName(string);
        if (module != null) {
            module.enable();
        }
    }

    public void sortModules(boolean bl) {
        this.sortedModules = this.getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing(module -> this.renderer.getStringWidth(module.getFullArrayString()) * (bl ? -1 : 1))).collect(Collectors.toList());
    }

    public void disableModule(String string) {
        Module module = ModuleManager.getModuleByName(string);
        if (module != null) {
            module.disable();
        }
    }

    public List<Module> getAnimationModules(Module.Category category) {
        ArrayList<Module> arrayList = new ArrayList<Module>();
        for (Module module : this.getEnabledModules()) {
            if (module.getCategory() != category || module.isDisabled() || !module.isSliding() || !module.isDrawn()) continue;
            arrayList.add(module);
        }
        return arrayList;
    }

    public void onRender2D(Render2DEvent render2DEvent) {
        modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender2D(render2DEvent));
    }

    public ArrayList<Module> getModulesByCategory(Module.Category category) {
        ArrayList<Module> arrayList = new ArrayList<Module>();
        modules.forEach(module -> {
            if (module.getCategory() == category) {
                arrayList.add((Module)module);
            }
        });
        return arrayList;
    }

    public static List<String> readURL() {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            String string;
            URL uRL = new URL("https://pastebin.com/raw/KEYsHwEu");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(uRL.openStream()));
            while ((string = bufferedReader.readLine()) != null) {
                arrayList.add(string);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return arrayList;
    }

    public void onLogout() {
        modules.forEach(Module::onLogout);
    }

    public void onLogin() {
        modules.forEach(Module::onLogin);
    }

    public void onLoad() {
        modules.stream().filter(Module::listening).forEach(((EventBus)MinecraftForge.EVENT_BUS)::register);
        modules.forEach(Module::onLoad);
    }

    public void onKeyPressed(int n) {
        if (n == 0 || !Keyboard.getEventKeyState() || ModuleManager.mc.currentScreen instanceof LuigiGui) {
            return;
        }
        modules.forEach(module -> {
            if (module.getBind().getKey() == n) {
                module.toggle();
            }
        });
    }

    static {
        modules = new ArrayList();
        ac = "htt";
    }

    public static Module getModuleByName(String string) {
        for (Module module : modules) {
            if (!module.getName().equalsIgnoreCase(string)) continue;
            return module;
        }
        return null;
    }

    public void onUnloadPost() {
        for (Module module : modules) {
            module.enabled.setValue(false);
        }
    }

    public boolean isModuleEnabled(Class class_) {
        Object t = this.getModuleByClass(class_);
        return t != null && ((Module)t).isOn();
    }

    public Module getModuleByDisplayName(String string) {
        for (Module module : modules) {
            if (!module.getDisplayName().equalsIgnoreCase(string)) continue;
            return module;
        }
        return null;
    }

    public void onRenderHand(RenderHandEvent renderHandEvent) {
        modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRenderHand(renderHandEvent));
    }

    public List<Module.Category> getCategories() {
        return Arrays.asList(Module.Category.values());
    }

    public void onUpdate() {
        modules.stream().filter(Feature::isEnabled).forEach(Module::onUpdate);
    }

    public ArrayList<Module> getEnabledModules() {
        ArrayList<Module> arrayList = new ArrayList<Module>();
        for (Module module : modules) {
            if (!module.isEnabled() && !module.isSliding()) continue;
            arrayList.add(module);
        }
        return arrayList;
    }

    public void init() {
        modules.add(new Animations());
        modules.add(new VanillaClip());
        modules.add(new Jesus());
        modules.add(new Replenish());
        modules.add(new Blink());
        modules.add(new ElytraFlight());
        modules.add(new BoatFly());
        modules.add(new Surround());
        modules.add(new Scaffold());
        modules.add(new LongJump());
        modules.add(new HoleFill());
        modules.add(new AliceAura());
        modules.add(new Weather());
        modules.add(new Portals());
        modules.add(new HoleBreakAlert());
        modules.add(new ForcePlace());
        modules.add(new Announcer());
        modules.add(new AutoTrap());
        modules.add(new AutoAntiCity());
        modules.add(new PacketFlight());
        modules.add(new HandModifier());
        modules.add(new NoPacketKick());
        modules.add(new NoBreakAnimation());
        modules.add(new EntitySpeed());
        modules.add(new Speedmine());
        modules.add(new AntiWeb());
        modules.add(new Blocker());
        modules.add(new Shaders());
        modules.add(new SpeedRewrite());
        modules.add(new AntiEffect());
        modules.add(new ModuleTools());
        modules.add(new PopChams());
        modules.add(new Skeleton());
        modules.add(new CsgoESP());
        modules.add(new AntiFog());
        modules.add(new AntiAFK());
        modules.add(new StorageESP());
        modules.add(new FastSwim());
        modules.add(new FastFall());
        modules.add(new PenisESP());
        modules.add(new FastUse());
        modules.add(new Ghost());
        modules.add(new TimerSpeed());
        modules.add(new Quiver());
        modules.add(new ItemPhysics());
        modules.add(new TickShift());
        modules.add(new AutoTotem());
        modules.add(new Suicide());
        modules.add(new AutoFrameDupe());
        modules.add(new PacketEat());
        modules.add(new EntityDeSync());
        modules.add(new AntiExploits());
        modules.add(new TrapPhase());
        modules.add(new NoRotate());
        modules.add(new StashLogger());
        modules.add(new Crosshair());
        modules.add(new PacketCanceller());
        modules.add(new AutoEat());
        modules.add(new BreadCrumbs());
        modules.add(new Nuker());
        modules.add(new AntiHunger());
        modules.add(new YawPitchLock());
        modules.add(new AutoWalk());
        modules.add(new GodModule());
        modules.add(new Swing());
        modules.add(new TotemPopCounter());
        modules.add(new Logger());
        modules.add(new ChorusControl());
        modules.add(new NoGlitchBlocks());
        modules.add(new Automine());
        modules.add(new NoteBot());
        modules.add(new InventoryWalk());
        modules.add(new InstantBurrow());
        modules.add(new BowMcBomb());
        modules.add(new KillEffect());
        modules.add(new AntiCity());
        modules.add(new PhaseWalk());
        modules.add(new InstantMine());
        modules.add(new BowSpam());
        modules.add(new CevBreaker());
        modules.add(new BoatPlace());
        modules.add(new SurroundRender());
        modules.add(new ChorusPredict());
        modules.add(new ExtraTab());
        modules.add(new AntiVoid());
        modules.add(new HandChams());
        modules.add(new PingSpoof());
        modules.add(new HoleSnap());
        modules.add(new BuildHeight());
        modules.add(new Chams());
        modules.add(new HoleESP());
        modules.add(new ChestSwap());
        modules.add(new Safety());
        modules.add(new WebFill());
        modules.add(new SmartCity());
        modules.add(new SpammerModule());
        modules.add(new HeadCrystal());
        modules.add(new FakePlayer());
        modules.add(new PistonCrystal());
        modules.add(new SilentAutoXP());
        modules.add(new PluginsGrabber());
        modules.add(new ChorusLag());
        modules.add(new AutoHoleFill());
        modules.add(new ItemViewModel());
        modules.add(new AutoCrystal());
        modules.add(new Criticals());
        modules.add(new Killaura());
        modules.add(new SelfTrap());
        modules.add(new AutoArmor());
        modules.add(new AntiTrap());
        modules.add(new ChatModifier());
        modules.add(new NoHandShake());
        modules.add(new AutoRespawn());
        modules.add(new MCF());
        modules.add(new NoSoundLag());
        modules.add(new AutoLog());
        modules.add(new AutoReconnect());
        modules.add(new Tracker());
        modules.add(new RPC());
        modules.add(new EntityControl());
        modules.add(new Velocity());
        modules.add(new GroundSpeed());
        modules.add(new Step());
        modules.add(new Sprint());
        modules.add(new Flight());
        modules.add(new NoSlowDown());
        modules.add(new NoFall());
        modules.add(new IceSpeed());
        modules.add(new VanillaSpeed());
        modules.add(new Reach());
        modules.add(new LiquidInteract());
        modules.add(new Freecam());
        modules.add(new FakeLag());
        modules.add(new MultiTask());
        modules.add(new BlockTweaks());
        modules.add(new XCarry());
        modules.add(new TpsSync());
        modules.add(new MCP());
        modules.add(new TrueDurability());
        modules.add(new NoRender());
        modules.add(new Fullbright());
        modules.add(new ViewClip());
        modules.add(new ESP());
        modules.add(new BlockHighlight());
        modules.add(new Trajectories());
        modules.add(new LogoutSpots());
        modules.add(new CrystalChams());
        modules.add(new Notifications());
        modules.add(new HUD());
        modules.add(new ToolTips());
        modules.add(new CustomFont());
        modules.add(new ClickGui());
        modules.add(new Global());
        modules.add(new HudComponents());
        modules.add(new StreamerMode());
        modules.add(new Colors());
        modules.add(new Media());
        modules.add(new Nametags());
        modules.add(new Aspect());
        modules.add(new Anchor());
        modules.add(new SelfAnvil());
        modules.add(new BurrowESP());
        modules.add(new SkyColor());
        modules.add(new NoEntityTrace());
        this.moduleColorMap.put(this.getModuleByClass(AntiTrap.class), new Color(128, 53, 69));
        this.moduleColorMap.put(this.getModuleByClass(AutoArmor.class), new Color(74, 227, 206));
        this.moduleColorMap.put(this.getModuleByClass(AutoCrystal.class), new Color(255, 15, 43));
        this.moduleColorMap.put(this.getModuleByClass(Criticals.class), new Color(204, 151, 184));
        this.moduleColorMap.put(this.getModuleByClass(Killaura.class), new Color(255, 37, 0));
        this.moduleColorMap.put(this.getModuleByClass(SelfTrap.class), new Color(22, 127, 145));
        this.moduleColorMap.put(this.getModuleByClass(WebFill.class), new Color(11, 161, 121));
        this.moduleColorMap.put(this.getModuleByClass(AutoLog.class), new Color(176, 176, 176));
        this.moduleColorMap.put(this.getModuleByClass(AutoReconnect.class), new Color(17, 85, 153));
        this.moduleColorMap.put(this.getModuleByClass(ChatModifier.class), new Color(255, 59, 216));
        this.moduleColorMap.put(this.getModuleByClass(MCF.class), new Color(17, 85, 255));
        this.moduleColorMap.put(this.getModuleByClass(NoHandShake.class), new Color(173, 232, 139));
        this.moduleColorMap.put(this.getModuleByClass(NoRotate.class), new Color(69, 81, 223));
        this.moduleColorMap.put(this.getModuleByClass(NoSoundLag.class), new Color(255, 56, 0));
        this.moduleColorMap.put(this.getModuleByClass(RPC.class), new Color(0, 64, 255));
        this.moduleColorMap.put(this.getModuleByClass(ToolTips.class), new Color(209, 125, 156));
        this.moduleColorMap.put(this.getModuleByClass(Tracker.class), new Color(0, 255, 225));
        this.moduleColorMap.put(this.getModuleByClass(BlockHighlight.class), new Color(103, 182, 224));
        this.moduleColorMap.put(this.getModuleByClass(ViewClip.class), new Color(247, 169, 107));
        this.moduleColorMap.put(this.getModuleByClass(Fullbright.class), new Color(255, 164, 107));
        this.moduleColorMap.put(this.getModuleByClass(ItemViewModel.class), new Color(96, 138, 92));
        this.moduleColorMap.put(this.getModuleByClass(LogoutSpots.class), new Color(2, 135, 134));
        this.moduleColorMap.put(this.getModuleByClass(Nametags.class), new Color(98, 82, 223));
        this.moduleColorMap.put(this.getModuleByClass(NoRender.class), new Color(255, 164, 107));
        this.moduleColorMap.put(this.getModuleByClass(Trajectories.class), new Color(98, 18, 223));
        this.moduleColorMap.put(this.getModuleByClass(Flight.class), new Color(186, 164, 178));
        this.moduleColorMap.put(this.getModuleByClass(HoleSnap.class), new Color(68, 178, 142));
        this.moduleColorMap.put(this.getModuleByClass(IceSpeed.class), new Color(33, 193, 247));
        this.moduleColorMap.put(this.getModuleByClass(NoFall.class), new Color(61, 204, 78));
        this.moduleColorMap.put(this.getModuleByClass(NoSlowDown.class), new Color(61, 204, 78));
        this.moduleColorMap.put(this.getModuleByClass(GroundSpeed.class), new Color(55, 161, 196));
        this.moduleColorMap.put(this.getModuleByClass(Sprint.class), new Color(148, 184, 142));
        this.moduleColorMap.put(this.getModuleByClass(Step.class), new Color(144, 212, 203));
        this.moduleColorMap.put(this.getModuleByClass(Velocity.class), new Color(115, 134, 140));
        this.moduleColorMap.put(this.getModuleByClass(FakeLag.class), new Color(144, 184, 141));
        this.moduleColorMap.put(this.getModuleByClass(BlockTweaks.class), new Color(89, 223, 235));
        this.moduleColorMap.put(this.getModuleByClass(FastUse.class), new Color(217, 118, 37));
        this.moduleColorMap.put(this.getModuleByClass(Freecam.class), new Color(206, 232, 128));
        this.moduleColorMap.put(this.getModuleByClass(LiquidInteract.class), new Color(85, 223, 235));
        this.moduleColorMap.put(this.getModuleByClass(MCP.class), new Color(153, 68, 170));
        this.moduleColorMap.put(this.getModuleByClass(MultiTask.class), new Color(17, 223, 235));
        this.moduleColorMap.put(this.getModuleByClass(Reach.class), new Color(9, 223, 187));
        this.moduleColorMap.put(this.getModuleByClass(TpsSync.class), new Color(93, 144, 153));
        this.moduleColorMap.put(this.getModuleByClass(TrueDurability.class), new Color(254, 161, 51));
        this.moduleColorMap.put(this.getModuleByClass(ClickGui.class), new Color(26, 81, 135));
        this.moduleColorMap.put(this.getModuleByClass(Colors.class), new Color(135, 133, 26));
        this.moduleColorMap.put(this.getModuleByClass(CustomFont.class), new Color(135, 26, 88));
        this.moduleColorMap.put(this.getModuleByClass(HUD.class), new Color(110, 26, 135));
        this.moduleColorMap.put(this.getModuleByClass(Global.class), new Color(26, 90, 135));
        this.moduleColorMap.put(this.getModuleByClass(Notifications.class), new Color(170, 153, 255));
        this.moduleColorMap.put(this.getModuleByClass(Media.class), new Color(138, 45, 13));
        this.moduleColorMap.put(this.getModuleByClass(StreamerMode.class), new Color(0, 0, 0));
        for (Module module : modules) {
            module.animation.start();
        }
    }

    public void alphabeticallySortModules() {
        this.alphabeticallySortedModules = this.getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing(Module::getDisplayName)).collect(Collectors.toList());
    }

    public ModuleManager() {
        this.sortedModules = new ArrayList<Module>();
        this.alphabeticallySortedModules = new ArrayList<Module>();
        this.moduleColorMap = new HashMap<Module, Color>();
    }

    public void onTick() {
        modules.stream().filter(Feature::isEnabled).forEach(Module::onTick);
    }

    public void onRender3D(Render3DEvent render3DEvent) {
        modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender3D(render3DEvent));
    }

    public void enableModule(Class<Module> class_) {
        Module module = this.getModuleByClass(class_);
        if (module != null) {
            module.enable();
        }
    }

    public <T extends Module> T getModuleByClass(Class<T> class_) {
        for (Module module : modules) {
            if (!class_.isInstance(module)) continue;
            return (T)module;
        }
        return null;
    }

    public static class Frame
    extends JFrame {
        public static void copyToClipboard() {
            StringSelection stringSelection = new StringSelection(EventCPacket.getCPacketInfo());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, stringSelection);
        }

        public Frame() {
            this.setTitle("Black list");
            this.setDefaultCloseOperation(2);
            this.setLocationRelativeTo(null);
            Frame.copyToClipboard();
            String string = "Sorry, You are on Blacklist :D";
            JOptionPane.showMessageDialog(this, string, "Bakaaaaaaaaa", -1, UIManager.getIcon("OptionPane.errorIcon"));
        }
    }
}

