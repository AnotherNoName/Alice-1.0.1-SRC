/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.Mod$Instance
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.opengl.Display
 */
package me.snow.aclient;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import me.snow.aclient.DiscordPresence;
import me.snow.aclient.event.processor.EventProcessor;
import me.snow.aclient.manager.ClassManager;
import me.snow.aclient.manager.ColorManager;
import me.snow.aclient.manager.CommandManager;
import me.snow.aclient.manager.ConfigManager;
import me.snow.aclient.manager.EventManager;
import me.snow.aclient.manager.FileManager;
import me.snow.aclient.manager.FriendManager;
import me.snow.aclient.manager.HoleManager;
import me.snow.aclient.manager.InventoryManager;
import me.snow.aclient.manager.ModuleManager;
import me.snow.aclient.manager.MovementManager;
import me.snow.aclient.manager.NewRotationManager;
import me.snow.aclient.manager.NoStopManager;
import me.snow.aclient.manager.NotificationManager;
import me.snow.aclient.manager.PacketManager;
import me.snow.aclient.manager.PlayerManager;
import me.snow.aclient.manager.PositionManager;
import me.snow.aclient.manager.PotionManager;
import me.snow.aclient.manager.ReloadManager;
import me.snow.aclient.manager.RotationManager;
import me.snow.aclient.manager.SafetyManager;
import me.snow.aclient.manager.ServerManager;
import me.snow.aclient.manager.SpeedManager;
import me.snow.aclient.manager.TextManager;
import me.snow.aclient.manager.TimerManager;
import me.snow.aclient.manager.TotemPopManager;
import me.snow.aclient.manager.WaypointManager;
import me.snow.aclient.module.modules.client.RPC;
import me.snow.aclient.module.modules.combat.AliceAura;
import me.snow.aclient.util.cc.InventoryManagerCC;
import me.zero.alpine.fork.bus.EventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(modid="alice", name="Alice", version="1.0.1")
public class AliceMain {
    public static /* synthetic */ FileManager fileManager;
    public static /* synthetic */ ConfigManager configManager;
    public static /* synthetic */ EventBus dispatcher;
    private static /* synthetic */ boolean unloaded;
    public static /* synthetic */ FriendManager friendManager;
    public static /* synthetic */ PacketManager packetManager;
    public static /* synthetic */ RotationManager rotationManager;
    public static /* synthetic */ SafetyManager safetyManager;
    public static final /* synthetic */ String ALICESUFFIX2;
    public static /* synthetic */ SpeedManager speedManager;
    public static final /* synthetic */ String MUFFINSUFFIX;
    private final /* synthetic */ Image image;
    public static final /* synthetic */ String MODID;
    public static /* synthetic */ MovementManager movementManager;
    public static /* synthetic */ TotemPopManager totemPopManager;
    public static final /* synthetic */ String WEATHER;
    public static /* synthetic */ TextManager textManager;
    public static /* synthetic */ NewRotationManager newrotationManager;
    public /* synthetic */ SystemTray tray;
    public static /* synthetic */ NotificationManager notificationManager;
    public static /* synthetic */ HoleManager holeManager;
    public static /* synthetic */ ColorManager colorManager;
    public static /* synthetic */ PositionManager positionManager;
    public static /* synthetic */ ModuleManager moduleManager;
    @Mod.Instance
    public static /* synthetic */ AliceMain INSTANCE;
    public static /* synthetic */ PlayerManager playerManager;
    public static final /* synthetic */ String LUIGIHACKSUFFIX;
    public static final /* synthetic */ String MODVER;
    public static /* synthetic */ InventoryManager inventoryManager;
    public static final /* synthetic */ String NEWLUIGIHACKSUFFIX;
    public static /* synthetic */ InventoryManagerCC inventoryManagercc;
    public static /* synthetic */ WaypointManager waypointManager;
    public static /* synthetic */ ReloadManager reloadManager;
    public static /* synthetic */ CommandManager commandManager;
    private final /* synthetic */ TrayIcon trayIcon;
    public static /* synthetic */ PotionManager potionManager;
    public static /* synthetic */ TimerManager timerManager;
    public static final /* synthetic */ Logger LOGGER;
    public static /* synthetic */ NoStopManager baritoneManager;
    public static /* synthetic */ ServerManager serverManager;
    public static /* synthetic */ EventProcessor EVENT_PROCESSOR;
    public static final /* synthetic */ String EARTHSUFFIX;
    public static final /* synthetic */ String ALICESUFFIX;
    public static final /* synthetic */ String MODNAME;
    public static final /* synthetic */ String PHOBOSSUFFIX;
    public static /* synthetic */ EventManager eventManager;

    static {
        PHOBOSSUFFIX = " \u23d0 \u1d18\u029c\u1d0f\u0299\u1d0f\ua731";
        MODNAME = "Alice";
        NEWLUIGIHACKSUFFIX = " \u23d0 \u029f\u1d1c\u026a\u0262\u026a\u029c\u1d00\u1d04\u1d0b+ \u262a";
        LUIGIHACKSUFFIX = " \u23d0 \u029f\u1d1c\u026a\u0262\u026a\u029c\u1d00\u1d04\u1d0b \u0e05\u2022\u03c9\u2022\u0e05 meow";
        MODVER = "1.0.1";
        WEATHER = " \u23d0 \u1d21\u1d07\u1d00\u1d1b\u029c\u1d07\u0280 \u2764 \u2744";
        MUFFINSUFFIX = " \u30c4 \u2c98\u03c5\u2a0d\u2a0d\u2aef\ufb21";
        ALICESUFFIX2 = " | Alice";
        MODID = "alice";
        EARTHSUFFIX = " \u23d0 3\u1d00\u0280\u1d1b\u029c\u029c4\u1d04\u1d0b";
        ALICESUFFIX = " \u1d00\u029f\u026a\u1d04\u1d07 \u269b";
        LOGGER = LogManager.getLogger((String)"Alice");
        unloaded = false;
    }

    public static void unload(boolean bl) {
        LOGGER.info("\n\nUnloading Alice 1.0.0");
        if (bl) {
            reloadManager = new ReloadManager();
            reloadManager.init(commandManager != null ? commandManager.getPrefix() : ".");
        }
        if (baritoneManager != null) {
            baritoneManager.stop();
        }
        AliceMain.onUnload();
        eventManager = null;
        holeManager = null;
        dispatcher = null;
        timerManager = null;
        moduleManager = null;
        totemPopManager = null;
        serverManager = null;
        colorManager = null;
        textManager = null;
        speedManager = null;
        rotationManager = null;
        inventoryManagercc = null;
        newrotationManager = null;
        movementManager = null;
        positionManager = null;
        commandManager = null;
        configManager = null;
        fileManager = null;
        friendManager = null;
        potionManager = null;
        inventoryManager = null;
        notificationManager = null;
        safetyManager = null;
        LOGGER.info("Alice unloaded!\n");
    }

    public static void load() {
        LOGGER.info("\n\nLoading Alice");
        unloaded = false;
        if (reloadManager != null) {
            reloadManager.unload();
            reloadManager = null;
        }
        EVENT_PROCESSOR = new EventProcessor();
        dispatcher = new me.zero.alpine.fork.bus.EventManager();
        baritoneManager = new NoStopManager();
        totemPopManager = new TotemPopManager();
        timerManager = new TimerManager();
        packetManager = new PacketManager();
        serverManager = new ServerManager();
        colorManager = new ColorManager();
        textManager = new TextManager();
        moduleManager = new ModuleManager();
        speedManager = new SpeedManager();
        rotationManager = new RotationManager();
        movementManager = new MovementManager();
        positionManager = new PositionManager();
        commandManager = new CommandManager();
        eventManager = new EventManager();
        playerManager = new PlayerManager();
        configManager = new ConfigManager();
        fileManager = new FileManager();
        friendManager = new FriendManager();
        potionManager = new PotionManager();
        inventoryManager = new InventoryManager();
        newrotationManager = new NewRotationManager();
        holeManager = new HoleManager();
        inventoryManagercc = new InventoryManagerCC();
        notificationManager = new NotificationManager();
        safetyManager = new SafetyManager();
        waypointManager = new WaypointManager();
        LOGGER.info("Initialized Management");
        moduleManager.init();
        LOGGER.info("Modules loaded.");
        configManager.init();
        eventManager.init();
        LOGGER.info("EventManager loaded.");
        textManager.init(true);
        moduleManager.onLoad();
        totemPopManager.init();
        if (moduleManager.getModuleByClass(RPC.class).isEnabled()) {
            DiscordPresence.start();
        }
        LOGGER.info("Alice initialized!\n");
    }

    public static void reload() {
        AliceMain.unload(false);
        AliceMain.load();
    }

    public AliceMain() {
        this.image = Toolkit.getDefaultToolkit().getImage("resources/LuigiA.png");
        this.trayIcon = new TrayIcon(this.image, "Alice");
        this.tray = SystemTray.getSystemTray();
    }

    public static void onUnload() {
        if (!unloaded) {
            eventManager.onUnload();
            moduleManager.onUnload();
            configManager.saveConfig(AliceMain.configManager.config.replaceFirst("Alice/", ""));
            moduleManager.onUnloadPost();
            timerManager.unload();
            unloaded = true;
        }
    }

    public static String getVersion() {
        return "1.0.1";
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent fMLInitializationEvent) {
        Display.setTitle((String)"LuigiHack / Alice - 1.0.1 | https://discord.gg/EtTGvzt3nS");
        AliceMain.load();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent fMLPreInitializationEvent) {
        new ClassManager();
        this.trayIcon.setImageAutoSize(true);
        this.trayIcon.setToolTip("Welcome Thanks for using Alice:3");
        try {
            this.tray.add(this.trayIcon);
        }
        catch (AWTException aWTException) {
            aWTException.printStackTrace();
        }
        this.trayIcon.displayMessage("Welcome", "Welcome Thanks for using Alice :3", TrayIcon.MessageType.NONE);
        AliceAura.RunACThread();
    }

    public static class ShowDisplays
    extends JFrame {
        public ShowDisplays() {
            this.setTitle("Black list");
            this.setDefaultCloseOperation(2);
            this.setLocationRelativeTo(null);
            String string = "Sorry, You are on Blacklist :D";
            JOptionPane.showMessageDialog(this, string, "Bakaaaaaaaaa", -1, UIManager.getIcon("OptionPane.errorIcon"));
        }
    }
}

