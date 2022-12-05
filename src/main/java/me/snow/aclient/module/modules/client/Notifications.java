//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.client.gui.GuiGameOver
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderPearl
 *  net.minecraft.entity.monster.EntityGhast
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.entity.passive.EntityDonkey
 *  net.minecraft.entity.passive.EntityLlama
 *  net.minecraft.entity.passive.EntityMule
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.MobEffects
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.ClientEvent;
import me.snow.aclient.manager.FileManager;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.ModuleTools;
import me.snow.aclient.module.modules.misc.AutoRespawn;
import me.snow.aclient.util.TextUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityDonkey;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.entity.passive.EntityMule;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Notifications
extends Module {
    private final /* synthetic */ Set<Entity> cow;
    private /* synthetic */ List<EntityPlayer> knownPlayers;
    private static final /* synthetic */ List<String> modules;
    public /* synthetic */ Setting<Boolean> Chat;
    public /* synthetic */ Setting<Boolean> entitynotif;
    public /* synthetic */ Setting<Integer> delay;
    private /* synthetic */ boolean hasAnnouncedWeakness;
    private final /* synthetic */ Timer timer;
    public final /* synthetic */ Setting<Boolean> thirtytwokay;
    public /* synthetic */ Setting<Boolean> moduleMessage;
    /* synthetic */ List<Entity> burrowedPlayers;
    public /* synthetic */ Setting<Boolean> Cows;
    private /* synthetic */ boolean flag;
    private final /* synthetic */ ConcurrentHashMap<EntityPlayer, Integer> players2;
    /* synthetic */ List<EntityPlayer> anti_spam;
    public /* synthetic */ Setting<Boolean> Sound;
    public /* synthetic */ Setting<Boolean> visualRange;
    public /* synthetic */ Timer totemAnnounce;
    private final /* synthetic */ Set<Entity> ghasts;
    private /* synthetic */ boolean hasAnnouncedSlowness;
    private static /* synthetic */ Notifications INSTANCE;
    public /* synthetic */ Setting<Boolean> burroww;
    public final /* synthetic */ Setting<Boolean> slowness;
    private final /* synthetic */ Setting<Boolean> readfile;
    public /* synthetic */ Setting<Boolean> list;
    public /* synthetic */ Setting<Boolean> leaving;
    public /* synthetic */ Setting<Boolean> popUp;
    private /* synthetic */ boolean check;
    public /* synthetic */ Setting<Boolean> Llamas;
    public /* synthetic */ Setting<Boolean> pearlnotif;
    public /* synthetic */ Setting<Boolean> clearOnLogout;
    public /* synthetic */ Setting<Boolean> totemNoti;
    public final /* synthetic */ Setting<Boolean> weakness;
    private final /* synthetic */ Set<Entity> mules;
    public /* synthetic */ Setting<Boolean> crash;
    public /* synthetic */ Setting<Boolean> Sheep;
    private final /* synthetic */ Set<Entity> donkeys;
    public /* synthetic */ Setting<Boolean> Mules;
    private final /* synthetic */ TrayIcon icon;
    public /* synthetic */ Setting<Boolean> Ghasts;
    private final /* synthetic */ Set<Entity> sheep;
    private static final /* synthetic */ String fileName;
    public /* synthetic */ Setting<Boolean> deathcorrdsnotif;
    public /* synthetic */ Setting<Boolean> VisualRangeSound;
    public /* synthetic */ Setting<Boolean> Donkeys;
    public /* synthetic */ Setting<Boolean> Desktop;
    private final /* synthetic */ Image image;
    public /* synthetic */ Setting<Boolean> coords;
    private final /* synthetic */ Set<Entity> llamas;
    public /* synthetic */ Setting<Boolean> totemPops;
    private final /* synthetic */ Set<EntityPlayer> sword;

    private boolean is32k(ItemStack itemStack) {
        if (itemStack.getItem() instanceof ItemSword) {
            NBTTagList nBTTagList = itemStack.getEnchantmentTagList();
            for (int i = 0; i < nBTTagList.tagCount(); ++i) {
                if (nBTTagList.getCompoundTagAt(i).getShort("lvl") < 32767) continue;
                return true;
            }
        }
        return false;
    }

    public TextComponentString getNotifierOn(Module module) {
        if (ModuleTools.getInstance().isEnabled()) {
            switch (ModuleTools.getInstance().notifier.getValue()) {
                case NEWLUIGI: {
                    TextComponentString textComponentString = new TextComponentString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.GREEN).append("[LuigiHack] ").append((Object)ChatFormatting.GRAY).append(module.getDisplayName()).append(" toggled ").append((Object)ChatFormatting.GREEN).append("on").append((Object)ChatFormatting.GRAY).append(".")));
                    return textComponentString;
                }
                case FUTURE: {
                    TextComponentString textComponentString = new TextComponentString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.RED).append("[Future] ").append((Object)ChatFormatting.GRAY).append(module.getDisplayName()).append(" toggled ").append((Object)ChatFormatting.GREEN).append("on").append((Object)ChatFormatting.GRAY).append(".")));
                    return textComponentString;
                }
                case SNOW: {
                    TextComponentString textComponentString = new TextComponentString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.BLUE).append("[").append((Object)ChatFormatting.AQUA).append("Snow").append((Object)ChatFormatting.BLUE).append("] [").append((Object)ChatFormatting.DARK_AQUA).append(module.getDisplayName()).append((Object)ChatFormatting.BLUE).append("] ").append((Object)ChatFormatting.GREEN).append("enabled")));
                    return textComponentString;
                }
                case MUFFIN: {
                    TextComponentString textComponentString = new TextComponentString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.LIGHT_PURPLE).append("[").append((Object)ChatFormatting.DARK_PURPLE).append("Muffin").append((Object)ChatFormatting.LIGHT_PURPLE).append("] ").append((Object)ChatFormatting.LIGHT_PURPLE).append(module.getDisplayName()).append((Object)ChatFormatting.DARK_PURPLE).append(" was ").append((Object)ChatFormatting.GREEN).append("enabled.")));
                    return textComponentString;
                }
                case ABYSS: {
                    TextComponentString textComponentString = new TextComponentString(String.valueOf(new StringBuilder().append(TextUtil.coloredString("[Abyss] ", ModuleTools.getInstance().Abysscolorrr.getPlannedValue())).append((Object)ChatFormatting.WHITE).append(module.getDisplayName()).append((Object)ChatFormatting.GREEN).append(" ON")));
                    return textComponentString;
                }
                case BOLD: {
                    TextComponentString textComponentString = new TextComponentString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.BOLD).append(module.getDisplayName()).append((Object)ChatFormatting.RESET).append((Object)ChatFormatting.GREEN).append(" enabled.")));
                    return textComponentString;
                }
                case OYVEY: {
                    TextComponentString textComponentString = new TextComponentString(String.valueOf(new StringBuilder().append(AliceMain.commandManager.getClientMessage()).append(" ").append((Object)ChatFormatting.GREEN).append(module.getDisplayName()).append(" enabled.")));
                    return textComponentString;
                }
            }
        }
        TextComponentString textComponentString = new TextComponentString(String.valueOf(new StringBuilder().append(AliceMain.commandManager.getClientMessage()).append((Object)ChatFormatting.RESET).append(" ").append(module.getDisplayName()).append((Object)ChatFormatting.GREEN).append(" enabled")));
        return textComponentString;
    }

    @Override
    public void onTick() {
        if (Notifications.fullNullCheck()) {
            return;
        }
        if (this.thirtytwokay.getValue().booleanValue()) {
            int n = 0;
            for (EntityPlayer entityPlayer : Notifications.mc.world.playerEntities) {
                if (entityPlayer.equals((Object)Notifications.mc.player)) continue;
                if (this.is32k(entityPlayer.getHeldItem(EnumHand.MAIN_HAND)) && !this.sword.contains((Object)entityPlayer)) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append((Object)ChatFormatting.RED).append(entityPlayer.getDisplayNameString()).append(" is holding a 32k")));
                    this.sword.add(entityPlayer);
                }
                if (this.is32k(entityPlayer.getHeldItem(EnumHand.MAIN_HAND))) {
                    if (n > 0) {
                        return;
                    }
                    ++n;
                }
                if (!this.sword.contains((Object)entityPlayer) || this.is32k(entityPlayer.getHeldItem(EnumHand.MAIN_HAND))) continue;
                Command.sendMessage(String.valueOf(new StringBuilder().append((Object)ChatFormatting.GREEN).append(entityPlayer.getDisplayNameString()).append(" is no longer holding a 32k")));
                this.sword.remove((Object)entityPlayer);
            }
        }
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @SubscribeEvent
    public void onDisplayDeathScreen(GuiOpenEvent guiOpenEvent) {
        if (this.deathcorrdsnotif.getValue().booleanValue() && guiOpenEvent.getGui() instanceof GuiGameOver && guiOpenEvent.getGui() instanceof GuiGameOver) {
            Command.sendMessage(String.format("You died at x %d y %d z %d", (int)AutoRespawn.mc.player.posX, (int)AutoRespawn.mc.player.posY, (int)AutoRespawn.mc.player.posZ));
        }
    }

    @Override
    public void onLoad() {
        this.check = true;
        this.loadFile();
        this.check = false;
    }

    public Notifications() {
        super("Notifications", "Sends Messages.", Module.Category.CLIENT, true, false, false);
        this.players2 = new ConcurrentHashMap();
        this.sword = Collections.newSetFromMap(new WeakHashMap());
        this.anti_spam = new ArrayList<EntityPlayer>();
        this.burrowedPlayers = new ArrayList<Entity>();
        this.hasAnnouncedWeakness = false;
        this.hasAnnouncedSlowness = false;
        this.ghasts = new HashSet<Entity>();
        this.donkeys = new HashSet<Entity>();
        this.mules = new HashSet<Entity>();
        this.llamas = new HashSet<Entity>();
        this.cow = new HashSet<Entity>();
        this.sheep = new HashSet<Entity>();
        this.image = Toolkit.getDefaultToolkit().getImage("resources/LuigiA.png");
        this.icon = new TrayIcon(this.image, "AliceClient");
        this.timer = new Timer();
        this.totemPops = this.register(new Setting<Boolean>("TotemPops", false));
        this.totemNoti = this.register(new Setting<Object>("TotemNoti", Boolean.TRUE, object -> this.totemPops.getValue()));
        this.delay = this.register(new Setting<Object>("Delay", 2000, 0, 5000, object -> this.totemPops.getValue(), "Delays messages."));
        this.clearOnLogout = this.register(new Setting<Boolean>("LogoutClear", false));
        this.moduleMessage = this.register(new Setting<Boolean>("ModuleMessage", true));
        this.list = this.register(new Setting<Object>("List", Boolean.FALSE, object -> this.moduleMessage.getValue()));
        this.readfile = this.register(new Setting<Object>("LoadFile", Boolean.valueOf(false), object -> this.moduleMessage.getValue()));
        this.visualRange = this.register(new Setting<Boolean>("VisualRange", false));
        this.VisualRangeSound = this.register(new Setting<Boolean>("VisualRangeSound", false));
        this.coords = this.register(new Setting<Object>("Coords", Boolean.TRUE, object -> this.visualRange.getValue()));
        this.leaving = this.register(new Setting<Object>("Leaving", Boolean.FALSE, object -> this.visualRange.getValue()));
        this.popUp = this.register(new Setting<Boolean>("PopUpVisualRange", false));
        this.pearlnotif = this.register(new Setting<Boolean>("PearlNotif", false));
        this.burroww = this.register(new Setting<Boolean>("Burrow", false));
        this.deathcorrdsnotif = this.register(new Setting<Boolean>("DeathCoords", false));
        this.weakness = this.register(new Setting<Boolean>("Weakness", true));
        this.slowness = this.register(new Setting<Boolean>("Slowness", true));
        this.thirtytwokay = this.register(new Setting<Boolean>("32kAlert", false));
        this.crash = this.register(new Setting<Boolean>("Crash", false));
        this.entitynotif = this.register(new Setting<Boolean>("EntityNotifier", false));
        this.Chat = this.register(new Setting<Boolean>("Chat", Boolean.valueOf(true), bl -> this.entitynotif.getValue()));
        this.Sound = this.register(new Setting<Boolean>("Sound", Boolean.valueOf(true), bl -> this.entitynotif.getValue()));
        this.Desktop = this.register(new Setting<Boolean>("DesktopNotifs", Boolean.valueOf(false), bl -> this.entitynotif.getValue()));
        this.Ghasts = this.register(new Setting<Boolean>("Ghasts", Boolean.valueOf(true), bl -> this.entitynotif.getValue()));
        this.Cows = this.register(new Setting<Boolean>("Cow", Boolean.valueOf(false), bl -> this.entitynotif.getValue()));
        this.Sheep = this.register(new Setting<Boolean>("Sheep", Boolean.valueOf(false), bl -> this.entitynotif.getValue()));
        this.Donkeys = this.register(new Setting<Boolean>("Donkeys", Boolean.valueOf(false), bl -> this.entitynotif.getValue()));
        this.Mules = this.register(new Setting<Boolean>("Mules", Boolean.valueOf(false), bl -> this.entitynotif.getValue()));
        this.Llamas = this.register(new Setting<Boolean>("Llamas", Boolean.valueOf(false), bl -> this.entitynotif.getValue()));
        this.totemAnnounce = new Timer();
        this.knownPlayers = new ArrayList<EntityPlayer>();
        this.setInstance();
        this.icon.setImageAutoSize(true);
        try {
            SystemTray systemTray = SystemTray.getSystemTray();
            systemTray.add(this.icon);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void onUpdate() {
        Iterator<Object> iterator222;
        Object object2;
        if (this.readfile.getValue().booleanValue()) {
            if (!this.check) {
                Command.sendMessage("Loading File...");
                this.timer.reset();
                this.loadFile();
            }
            this.check = true;
        }
        if (this.check && this.timer.passedMs(750L)) {
            this.readfile.setValue(false);
            this.check = false;
        }
        if (this.visualRange.getValue().booleanValue()) {
            object2 = new ArrayList(Notifications.mc.world.playerEntities);
            if (((ArrayList)object2).size() > 0) {
                iterator222 = ((ArrayList)object2).iterator();
                while (iterator222.hasNext()) {
                    EntityPlayer object3 = (EntityPlayer)iterator222.next();
                    if (object3.getName().equals(Notifications.mc.player.getName()) || this.knownPlayers.contains((Object)object3)) continue;
                    this.knownPlayers.add(object3);
                    if (AliceMain.friendManager.isFriend(object3)) {
                        Command.sendMessage(String.valueOf(new StringBuilder().append("Your Friend ").append((Object)ChatFormatting.AQUA).append(object3.getName()).append("\u00a7r entered your visual range").append(this.coords.getValue() != false ? String.valueOf(new StringBuilder().append(" at (").append((int)object3.posX).append(", ").append((int)object3.posY).append(", ").append((int)object3.posZ).append(")!")) : "!")), this.popUp.getValue());
                    } else {
                        Command.sendMessage(String.valueOf(new StringBuilder().append("Player ").append((Object)ChatFormatting.RED).append(object3.getName()).append("\u00a7r").append((Object)ChatFormatting.RED).append(" entered ").append((Object)ChatFormatting.RESET).append("your visual range").append(this.coords.getValue() != false ? String.valueOf(new StringBuilder().append(" at (").append((int)object3.posX).append(", ").append((int)object3.posY).append(", ").append((int)object3.posZ).append(")!")) : "!")), this.popUp.getValue());
                    }
                    if (this.VisualRangeSound.getValue().booleanValue()) {
                        Notifications.mc.player.playSound(SoundEvents.BLOCK_ANVIL_LAND, 1.0f, 1.0f);
                    }
                    return;
                }
            }
            if (this.knownPlayers.size() > 0) {
                for (EntityPlayer entityPlayer : this.knownPlayers) {
                    if (((ArrayList)object2).contains((Object)entityPlayer)) continue;
                    this.knownPlayers.remove((Object)entityPlayer);
                    if (this.leaving.getValue().booleanValue()) {
                        if (AliceMain.friendManager.isFriend(entityPlayer)) {
                            Command.sendMessage(String.valueOf(new StringBuilder().append("Your Friend ").append((Object)ChatFormatting.AQUA).append(entityPlayer.getName()).append("\u00a7r left your visual range").append(this.coords.getValue() != false ? String.valueOf(new StringBuilder().append(" at (").append((int)entityPlayer.posX).append(", ").append((int)entityPlayer.posY).append(", ").append((int)entityPlayer.posZ).append(")!")) : "!")), this.popUp.getValue());
                        } else {
                            Command.sendMessage(String.valueOf(new StringBuilder().append("Player ").append((Object)ChatFormatting.RED).append(entityPlayer.getName()).append("\u00a7r").append((Object)ChatFormatting.GREEN).append(" left ").append((Object)ChatFormatting.RESET).append("your visual range").append(this.coords.getValue() != false ? String.valueOf(new StringBuilder().append(" at (").append((int)entityPlayer.posX).append(", ").append((int)entityPlayer.posY).append(", ").append((int)entityPlayer.posZ).append(")!")) : "!")), this.popUp.getValue());
                        }
                    }
                    return;
                }
            }
        }
        if (this.pearlnotif.getValue().booleanValue()) {
            if (Notifications.mc.world == null || Notifications.mc.player == null) {
                return;
            }
            object2 = null;
            for (Entity entity2 : Notifications.mc.world.loadedEntityList) {
                if (!(entity2 instanceof EntityEnderPearl)) continue;
                object2 = entity2;
                break;
            }
            if (object2 == null) {
                this.flag = true;
                return;
            }
            iterator222 = null;
            for (EntityPlayer entityPlayer : Notifications.mc.world.playerEntities) {
                if (iterator222 == null) {
                    iterator222 = entityPlayer;
                    continue;
                }
                if (iterator222.getDistanceToEntity((Entity)object2) <= entityPlayer.getDistanceToEntity(object2)) continue;
                iterator222 = entityPlayer;
            }
            if (iterator222 == Notifications.mc.player) {
                this.flag = false;
            }
            if (iterator222 != null && this.flag) {
                void var3_12;
                String string = object2.getHorizontalFacing().toString();
                if (string.equals("west")) {
                    String string2 = "east";
                } else if (string.equals("east")) {
                    String string3 = "west";
                }
                Command.sendMessage(AliceMain.friendManager.isFriend(iterator222.getName()) ? String.valueOf(new StringBuilder().append((Object)ChatFormatting.AQUA).append(iterator222.getName()).append((Object)ChatFormatting.DARK_GRAY).append(" has just thrown a pearl heading ").append((String)var3_12).append("!")) : String.valueOf(new StringBuilder().append((Object)ChatFormatting.RED).append(iterator222.getName()).append((Object)ChatFormatting.DARK_GRAY).append(" has just thrown a pearl heading ").append((String)var3_12).append("!")));
                this.flag = false;
            }
        }
        if (this.burroww.getValue().booleanValue()) {
            if (Notifications.mc.player == null || Notifications.mc.world == null) {
                return;
            }
            for (Iterator<Object> iterator222 : Notifications.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityPlayer).collect(Collectors.toList())) {
                if (!(iterator222 instanceof EntityPlayer)) continue;
                if (!this.burrowedPlayers.contains(iterator222) && this.isBurrowed((Entity)iterator222)) {
                    this.burrowedPlayers.add((Entity)iterator222);
                    Command.sendMessage(String.valueOf(new StringBuilder().append((Object)ChatFormatting.RED).append(iterator222.getName()).append(" has just burrowed!")));
                    continue;
                }
                if (!this.burrowedPlayers.contains(iterator222) || this.isBurrowed((Entity)iterator222)) continue;
                this.burrowedPlayers.remove(iterator222);
                Command.sendMessage(String.valueOf(new StringBuilder().append((Object)ChatFormatting.GREEN).append(iterator222.getName()).append(" is no longer burrowed!")));
            }
        }
        if (this.weakness.getValue().booleanValue()) {
            if (Notifications.mc.player.isPotionActive(MobEffects.WEAKNESS) && !this.hasAnnouncedWeakness) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("You now have ").append((Object)ChatFormatting.RED).append((Object)ChatFormatting.BOLD).append("Weakness").append((Object)ChatFormatting.RESET).append("!")));
                this.hasAnnouncedWeakness = true;
            }
            if (!Notifications.mc.player.isPotionActive(MobEffects.WEAKNESS) && this.hasAnnouncedWeakness) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("You no longer have ").append((Object)ChatFormatting.RED).append((Object)ChatFormatting.BOLD).append("Weakness").append((Object)ChatFormatting.RESET).append("!")));
                this.hasAnnouncedWeakness = false;
            }
        }
        if (this.slowness.getValue().booleanValue()) {
            if (Notifications.mc.player.isPotionActive(MobEffects.SLOWNESS) && !this.hasAnnouncedSlowness) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("You now have ").append((Object)ChatFormatting.DARK_RED).append((Object)ChatFormatting.BOLD).append("Slowness").append((Object)ChatFormatting.RESET).append("!")));
                this.hasAnnouncedSlowness = true;
            }
            if (!Notifications.mc.player.isPotionActive(MobEffects.SLOWNESS) && this.hasAnnouncedSlowness) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("You no longer have ").append((Object)ChatFormatting.DARK_RED).append((Object)ChatFormatting.BOLD).append("Slowness").append((Object)ChatFormatting.RESET).append("!")));
                this.hasAnnouncedSlowness = false;
            }
        }
        if (this.entitynotif.getValue().booleanValue() && this.Ghasts.getValue().booleanValue()) {
            for (Iterator<Object> iterator222 : Notifications.mc.world.getLoadedEntityList()) {
                if (!(iterator222 instanceof EntityGhast) || this.ghasts.contains(iterator222)) continue;
                if (this.Chat.getValue().booleanValue()) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append("Ghast Detected at: ").append(Math.round(iterator222.getPosition().getX())).append("x, ").append(Math.round(iterator222.getPosition().getY())).append("y, ").append(Math.round(iterator222.getPosition().getZ())).append("z.")));
                }
                this.ghasts.add((Entity)iterator222);
                if (!this.Desktop.getValue().booleanValue()) continue;
                this.icon.displayMessage("WeatherHack", String.valueOf(new StringBuilder().append("I found a ghast at: ").append(Math.round(iterator222.getPosition().getX())).append("x, ").append(Math.round(iterator222.getPosition().getY())).append("y, ").append(Math.round(iterator222.getPosition().getZ())).append("z.")), TrayIcon.MessageType.INFO);
                if (!this.Sound.getValue().booleanValue()) continue;
                Notifications.mc.player.playSound(SoundEvents.BLOCK_ANVIL_DESTROY, 1.0f, 1.0f);
            }
        }
        if (this.entitynotif.getValue().booleanValue() && this.Donkeys.getValue().booleanValue()) {
            for (Iterator<Object> iterator222 : Notifications.mc.world.getLoadedEntityList()) {
                if (!(iterator222 instanceof EntityDonkey) || this.donkeys.contains(iterator222)) continue;
                if (this.Chat.getValue().booleanValue()) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append("Donkey Detected at: ").append(Math.round(iterator222.getPosition().getX())).append("x, ").append(Math.round(iterator222.getPosition().getY())).append("y, ").append(Math.round(iterator222.getPosition().getZ())).append("z.")));
                }
                this.donkeys.add((Entity)iterator222);
                if (!this.Desktop.getValue().booleanValue()) continue;
                this.icon.displayMessage("WeatherHack", String.valueOf(new StringBuilder().append("I found a donkey at: ").append(Math.round(iterator222.getPosition().getX())).append("x, ").append(Math.round(iterator222.getPosition().getY())).append("y, ").append(Math.round(iterator222.getPosition().getZ())).append("z.")), TrayIcon.MessageType.INFO);
                if (!this.Sound.getValue().booleanValue()) continue;
                Notifications.mc.player.playSound(SoundEvents.BLOCK_ANVIL_DESTROY, 1.0f, 1.0f);
            }
        }
        if (this.entitynotif.getValue().booleanValue() && this.Mules.getValue().booleanValue()) {
            for (Iterator<Object> iterator222 : Notifications.mc.world.getLoadedEntityList()) {
                if (!(iterator222 instanceof EntityMule) || this.mules.contains(iterator222)) continue;
                if (this.Chat.getValue().booleanValue()) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append("Mule Detected at: ").append(Math.round(iterator222.getPosition().getX())).append("x, ").append(Math.round(iterator222.getPosition().getY())).append("y, ").append(Math.round(iterator222.getPosition().getZ())).append("z.")));
                }
                this.mules.add((Entity)iterator222);
                if (!this.Desktop.getValue().booleanValue()) continue;
                this.icon.displayMessage("WeatherHack", String.valueOf(new StringBuilder().append("I found a mule at: ").append(Math.round(iterator222.getPosition().getX())).append("x, ").append(Math.round(iterator222.getPosition().getY())).append("y, ").append(Math.round(iterator222.getPosition().getZ())).append("z.")), TrayIcon.MessageType.INFO);
                if (!this.Sound.getValue().booleanValue()) continue;
                Notifications.mc.player.playSound(SoundEvents.BLOCK_ANVIL_DESTROY, 1.0f, 1.0f);
            }
        }
        if (this.entitynotif.getValue().booleanValue() && this.Llamas.getValue().booleanValue()) {
            for (Iterator<Object> iterator222 : Notifications.mc.world.getLoadedEntityList()) {
                if (!(iterator222 instanceof EntityLlama) || this.llamas.contains(iterator222)) continue;
                if (this.Chat.getValue().booleanValue()) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append("Llama Detected at: ").append(Math.round(iterator222.getPosition().getX())).append("x, ").append(Math.round(iterator222.getPosition().getY())).append("y, ").append(Math.round(iterator222.getPosition().getZ())).append("z.")));
                }
                this.llamas.add((Entity)iterator222);
                if (!this.Desktop.getValue().booleanValue()) continue;
                this.icon.displayMessage("WeatherHack", String.valueOf(new StringBuilder().append("I found a llama at: ").append(Math.round(iterator222.getPosition().getX())).append("x, ").append(Math.round(iterator222.getPosition().getY())).append("y, ").append(Math.round(iterator222.getPosition().getZ())).append("z.")), TrayIcon.MessageType.INFO);
                if (!this.Sound.getValue().booleanValue()) continue;
                Notifications.mc.player.playSound(SoundEvents.BLOCK_ANVIL_DESTROY, 1.0f, 1.0f);
            }
        }
        if (this.entitynotif.getValue().booleanValue() && this.Cows.getValue().booleanValue()) {
            for (Iterator<Object> iterator222 : Notifications.mc.world.getLoadedEntityList()) {
                if (!(iterator222 instanceof EntityCow) || this.cow.contains(iterator222)) continue;
                if (this.Chat.getValue().booleanValue()) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append("Cow Detected at: ").append(Math.round(iterator222.getPosition().getX())).append("x, ").append(Math.round(iterator222.getPosition().getY())).append("y, ").append(Math.round(iterator222.getPosition().getZ())).append("z.")));
                }
                this.cow.add((Entity)iterator222);
                if (!this.Desktop.getValue().booleanValue()) continue;
                this.icon.displayMessage("WeatherHack", String.valueOf(new StringBuilder().append("I found a cow at: ").append(Math.round(iterator222.getPosition().getX())).append("x, ").append(Math.round(iterator222.getPosition().getY())).append("y, ").append(Math.round(iterator222.getPosition().getZ())).append("z.")), TrayIcon.MessageType.INFO);
                if (!this.Sound.getValue().booleanValue()) continue;
                Notifications.mc.player.playSound(SoundEvents.BLOCK_ANVIL_DESTROY, 1.0f, 1.0f);
            }
        }
        if (this.entitynotif.getValue().booleanValue() && this.Sheep.getValue().booleanValue()) {
            for (Iterator<Object> iterator222 : Notifications.mc.world.getLoadedEntityList()) {
                if (!(iterator222 instanceof EntitySheep) || this.sheep.contains(iterator222)) continue;
                if (this.Chat.getValue().booleanValue()) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append("Sheep Detected at: ").append(Math.round(iterator222.getPosition().getX())).append("x, ").append(Math.round(iterator222.getPosition().getY())).append("y, ").append(Math.round(iterator222.getPosition().getZ())).append("z.")));
                }
                this.sheep.add((Entity)iterator222);
                if (!this.Desktop.getValue().booleanValue()) continue;
                this.icon.displayMessage("WeatherHack", String.valueOf(new StringBuilder().append("I found a Sheep at: ").append(Math.round(iterator222.getPosition().getX())).append("x, ").append(Math.round(iterator222.getPosition().getY())).append("y, ").append(Math.round(iterator222.getPosition().getZ())).append("z.")), TrayIcon.MessageType.INFO);
                if (!this.Sound.getValue().booleanValue()) continue;
                Notifications.mc.player.playSound(SoundEvents.BLOCK_ANVIL_DESTROY, 1.0f, 1.0f);
            }
        }
    }

    private double roundValueToCenter(double d) {
        double d2 = Math.round(d);
        if (d2 > d) {
            d2 -= 0.5;
        } else if (d2 <= d) {
            d2 += 0.5;
        }
        return d2;
    }

    public TextComponentString getNotifierOff(Module module) {
        if (ModuleTools.getInstance().isEnabled()) {
            switch (ModuleTools.getInstance().notifier.getValue()) {
                case NEWLUIGI: {
                    TextComponentString textComponentString = new TextComponentString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.GREEN).append("[LuigiHack] ").append((Object)ChatFormatting.GRAY).append(module.getDisplayName()).append(" toggled ").append((Object)ChatFormatting.RED).append("off").append((Object)ChatFormatting.GRAY).append(".")));
                    return textComponentString;
                }
                case FUTURE: {
                    TextComponentString textComponentString = new TextComponentString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.RED).append("[Future] ").append((Object)ChatFormatting.GRAY).append(module.getDisplayName()).append(" toggled ").append((Object)ChatFormatting.RED).append("off").append((Object)ChatFormatting.GRAY).append(".")));
                    return textComponentString;
                }
                case SNOW: {
                    TextComponentString textComponentString = new TextComponentString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.BLUE).append("[").append((Object)ChatFormatting.AQUA).append("Snow").append((Object)ChatFormatting.BLUE).append("] [").append((Object)ChatFormatting.DARK_AQUA).append(module.getDisplayName()).append((Object)ChatFormatting.BLUE).append("] ").append((Object)ChatFormatting.RED).append("disabled")));
                    return textComponentString;
                }
                case MUFFIN: {
                    TextComponentString textComponentString = new TextComponentString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.LIGHT_PURPLE).append("[").append((Object)ChatFormatting.DARK_PURPLE).append("Muffin").append((Object)ChatFormatting.LIGHT_PURPLE).append("] ").append((Object)ChatFormatting.LIGHT_PURPLE).append(module.getDisplayName()).append((Object)ChatFormatting.DARK_PURPLE).append(" was ").append((Object)ChatFormatting.RED).append("disabled.")));
                    return textComponentString;
                }
                case BOLD: {
                    TextComponentString textComponentString = new TextComponentString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.BOLD).append(module.getDisplayName()).append((Object)ChatFormatting.RESET).append((Object)ChatFormatting.RED).append(" disabled.")));
                    return textComponentString;
                }
                case ABYSS: {
                    TextComponentString textComponentString = new TextComponentString(String.valueOf(new StringBuilder().append(TextUtil.coloredString("[Abyss] ", ModuleTools.getInstance().Abysscolorrr.getPlannedValue())).append((Object)ChatFormatting.WHITE).append(module.getDisplayName()).append((Object)ChatFormatting.RED).append(" OFF")));
                    return textComponentString;
                }
                case OYVEY: {
                    TextComponentString textComponentString = new TextComponentString(String.valueOf(new StringBuilder().append(AliceMain.commandManager.getClientMessage()).append(" ").append((Object)ChatFormatting.RED).append(module.getDisplayName()).append(" disabled.")));
                    return textComponentString;
                }
            }
        }
        TextComponentString textComponentString = new TextComponentString(String.valueOf(new StringBuilder().append(AliceMain.commandManager.getClientMessage()).append((Object)ChatFormatting.RESET).append(" ").append(module.getDisplayName()).append((Object)ChatFormatting.RED).append(" disabled")));
        return textComponentString;
    }

    public static void displayCrash(Exception exception) {
        Command.sendMessage(String.valueOf(new StringBuilder().append("\u00a7cException caught: ").append(exception.getMessage())));
    }

    private boolean isBurrowed(Entity entity) {
        BlockPos blockPos = new BlockPos(this.roundValueToCenter(entity.posX), entity.posY + 0.2, this.roundValueToCenter(entity.posZ));
        return Notifications.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN || Notifications.mc.world.getBlockState(blockPos).getBlock() == Blocks.ENDER_CHEST;
    }

    public static Notifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Notifications();
        }
        return INSTANCE;
    }

    static {
        fileName = "Alice/util/ModuleMessage_List.txt";
        modules = new ArrayList<String>();
        INSTANCE = new Notifications();
    }

    public void loadFile() {
        List<String> list = FileManager.readTextFileAllLines("Alice/util/ModuleMessage_List.txt");
        Iterator<String> iterator2 = list.iterator();
        modules.clear();
        while (iterator2.hasNext()) {
            String string = iterator2.next();
            if (string.replaceAll("\\s", "").isEmpty()) continue;
            modules.add(string);
        }
    }

    @Override
    public void onEnable() {
        this.knownPlayers = new ArrayList<EntityPlayer>();
        if (!this.check) {
            this.loadFile();
        }
        this.flag = true;
        this.players2.clear();
        this.anti_spam.clear();
    }

    @SubscribeEvent
    public void onToggleModule(ClientEvent clientEvent) {
        int n;
        Module module;
        if (!this.moduleMessage.getValue().booleanValue()) {
            return;
        }
        if (!(clientEvent.getStage() != 0 || (module = (Module)clientEvent.getFeature()).equals(this) || !modules.contains(module.getDisplayName()) && this.list.getValue().booleanValue())) {
            n = 0;
            for (char c : module.getDisplayName().toCharArray()) {
                n += c;
                n *= 10;
            }
            Notifications.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)this.getNotifierOff(module), n);
        }
        if (clientEvent.getStage() == 1 && (modules.contains((module = (Module)clientEvent.getFeature()).getDisplayName()) || !this.list.getValue().booleanValue())) {
            n = 0;
            for (char c : module.getDisplayName().toCharArray()) {
                n += c;
                n *= 10;
            }
            Notifications.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)this.getNotifierOn(module), n);
        }
    }
}

