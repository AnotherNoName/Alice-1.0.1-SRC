//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.ClientEvent;
import me.snow.aclient.event.events.Render2DEvent;
import me.snow.aclient.manager.TextManager;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.module.modules.combat.AliceAura;
import me.snow.aclient.module.modules.combat.AutoTrap;
import me.snow.aclient.module.modules.combat.Surround;
import me.snow.aclient.module.modules.combat.autocrystal.AutoCrystal;
import me.snow.aclient.module.modules.misc.ToolTips;
import me.snow.aclient.module.modules.player.InstantMine;
import me.snow.aclient.util.ColorUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.RenderUtil;
import me.snow.aclient.util.Timer;
import me.snow.aclient.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HUD
extends Module {
    public /* synthetic */ Setting<Integer> rainbowSaturation;
    private /* synthetic */ int hitMarkerTimer;
    private final /* synthetic */ Setting<Boolean> modeVer;
    private final /* synthetic */ Setting<Boolean> totems;
    private final /* synthetic */ Setting<Boolean> armor;
    private static final /* synthetic */ ResourceLocation codHitmarker;
    private final /* synthetic */ Setting<Boolean> fps;
    private final /* synthetic */ Setting<Boolean> durability;
    private static final /* synthetic */ ItemStack totem;
    private static final /* synthetic */ ResourceLocation csgoHitmarker;
    private final /* synthetic */ Setting<Boolean> tps;
    private static final /* synthetic */ ItemStack exp;
    private final /* synthetic */ Setting<Float> watermarkYawa;
    public /* synthetic */ Setting<Integer> rainbowSpeed;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Timer moduleTimer;
    private static final /* synthetic */ ItemStack totem2;
    public /* synthetic */ Setting<Integer> animationVerticalTime;
    private final /* synthetic */ Setting<Boolean> potions;
    private final /* synthetic */ Setting<LagNotify> lag;
    private static final /* synthetic */ ItemStack crystals;
    private /* synthetic */ Map<String, Integer> players;
    private final /* synthetic */ Setting<Float> serveripXX;
    public /* synthetic */ Setting<Integer> rainbowBrightness;
    private final /* synthetic */ Map<Potion, Color> potionColorMap;
    private final /* synthetic */ Setting<Boolean> serverip;
    public final /* synthetic */ Setting<Boolean> flowingArrayList;
    public /* synthetic */ Setting<Integer> hudBlue;
    public /* synthetic */ Setting<Integer> animationHorizontalTime;
    private final /* synthetic */ Setting<Boolean> direction;
    private final /* synthetic */ Setting<Boolean> ItemTextInfoRW;
    public /* synthetic */ Setting<Boolean> potionIcons;
    public /* synthetic */ Setting<Boolean> time;
    public /* synthetic */ Setting<Integer> hudRed;
    public /* synthetic */ Map<Integer, Integer> colorMap;
    private final /* synthetic */ Setting<WaterMark> watermark;
    private final /* synthetic */ Setting<Boolean> arrayList;
    private final /* synthetic */ Setting<String> customWatermark;
    private final /* synthetic */ Setting<Boolean> renderingUp;
    private final /* synthetic */ Setting<Boolean> pvp;
    private final /* synthetic */ Setting<Boolean> ping;
    public /* synthetic */ Setting<Boolean> colorSync;
    private final /* synthetic */ Setting<String> spoofGreeter;
    public /* synthetic */ Setting<Integer> factor;
    private static final /* synthetic */ ResourceLocation box;
    private final /* synthetic */ Setting<Boolean> percent;
    private final /* synthetic */ Setting<Greeter> greeter;
    private final /* synthetic */ Setting<Integer> arryListYawa;
    public /* synthetic */ Setting<Boolean> disss;
    public /* synthetic */ Setting<Boolean> textRadar;
    public /* synthetic */ Setting<Boolean> rolling;
    private final /* synthetic */ Setting<Boolean> alphabeticalSorting;
    private final /* synthetic */ Setting<Boolean> itemInfo;
    private final /* synthetic */ Setting<Boolean> arrayListSideLine;
    private /* synthetic */ int color;
    private static final /* synthetic */ ItemStack gapples;
    private static /* synthetic */ HUD INSTANCE;
    public static final /* synthetic */ SoundEvent CSGO_EVENT;
    public static final /* synthetic */ SoundEvent COD_EVENT;
    private final /* synthetic */ Setting<Float> serveripYY;
    private final /* synthetic */ Setting<Boolean> speed;
    public /* synthetic */ Setting<Integer> hudGreen;
    public /* synthetic */ Setting<Boolean> shadow;
    public /* synthetic */ Map<Module, Float> moduleProgressMap;
    public /* synthetic */ Setting<Boolean> rainbow;
    private /* synthetic */ boolean shouldIncrement;
    private final /* synthetic */ Setting<Boolean> altPotionsColors;
    private final /* synthetic */ Setting<Boolean> coords;

    public void renderLag() {
        int n = this.renderer.scaledWidth;
        if (AliceMain.serverManager.isServerNotResponding()) {
            String string = String.valueOf(new StringBuilder().append(this.lag.getValue() == LagNotify.GRAY ? "\u00a77" : "\u00a7c").append("Server not responding: ").append(MathUtil.round((float)AliceMain.serverManager.serverRespondingTime() / 1000.0f, 1)).append("s."));
            this.renderer.drawString(string, (float)n / 2.0f - (float)this.renderer.getStringWidth(string) / 2.0f + 2.0f, 20.0f, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(20) : this.color, true);
        }
    }

    public void drawTextRadar(int n) {
        if (!this.players.isEmpty()) {
            int n2 = this.renderer.getFontHeight() + 7 + n;
            for (Map.Entry<String, Integer> entry : this.players.entrySet()) {
                String string = String.valueOf(new StringBuilder().append(entry.getKey()).append(" "));
                int n3 = this.renderer.getFontHeight() + 1;
                this.renderer.drawString(string, 2.0f, n2, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(n2) : this.color, true);
                n2 += n3;
            }
        }
    }

    public void renderTotemHUD2() {
        int n = HUD.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.field_190929_cY).mapToInt(ItemStack::func_190916_E).sum();
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.field_190929_cY) {
            n += HUD.mc.player.getHeldItemOffhand().func_190916_E();
        }
        if (n > 0) {
            GlStateManager.enableTexture2D();
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(totem2, 0, 20);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HUD.mc.fontRendererObj, totem2, 0, 20, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(n).append("")), 10.0f, 30.0f, 0xFFFFFF);
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
    }

    public void renderPvpInfo() {
        if (AliceMain.moduleManager.getModuleByClass(AutoCrystal.class).isEnabled()) {
            this.renderer.drawString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.GREEN).append("CA")), 2.0f, 210.0f, this.color, true);
        }
        if (AliceMain.moduleManager.getModuleByClass(AutoTrap.class).isEnabled()) {
            this.renderer.drawString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.GREEN).append("Trap")), 2.0f, 220.0f, this.color, true);
        }
        if (AliceMain.moduleManager.getModuleByClass(Surround.class).isEnabled()) {
            this.renderer.drawString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.GREEN).append("SU")), 2.0f, 230.0f, this.color, true);
        }
        if (AliceMain.moduleManager.getModuleByClass(InstantMine.class).isEnabled()) {
            this.renderer.drawString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.GREEN).append("Mine")), 2.0f, 240.0f, this.color, true);
        }
        if (AliceMain.moduleManager.getModuleByClass(AliceAura.class).isDisabled()) {
            this.renderer.drawString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.DARK_RED).append("CA")), 2.0f, 210.0f, this.color, true);
        }
        if (AliceMain.moduleManager.getModuleByClass(AutoTrap.class).isDisabled()) {
            this.renderer.drawString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.DARK_RED).append("Trap")), 2.0f, 220.0f, this.color, true);
        }
        if (AliceMain.moduleManager.getModuleByClass(Surround.class).isDisabled()) {
            this.renderer.drawString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.DARK_RED).append("SU")), 2.0f, 230.0f, this.color, true);
        }
        if (AliceMain.moduleManager.getModuleByClass(InstantMine.class).isDisabled()) {
            this.renderer.drawString(String.valueOf(new StringBuilder().append((Object)ChatFormatting.DARK_RED).append("Mine")), 2.0f, 240.0f, this.color, true);
        }
    }

    public void renderItemTextInfoShort() {
        int n = Util.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.END_CRYSTAL).mapToInt(ItemStack::func_190916_E).sum();
        int n2 = Util.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.GOLDEN_APPLE).mapToInt(ItemStack::func_190916_E).sum();
        int n3 = HUD.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.field_190929_cY).mapToInt(ItemStack::func_190916_E).sum();
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.field_190929_cY) {
            n3 += HUD.mc.player.getHeldItemOffhand().func_190916_E();
        }
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            n += HUD.mc.player.getHeldItemOffhand().func_190916_E();
        }
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE) {
            n2 += HUD.mc.player.getHeldItemOffhand().func_190916_E();
        }
        int n4 = HUD.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.EXPERIENCE_BOTTLE).mapToInt(ItemStack::func_190916_E).sum();
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE) {
            n4 += HUD.mc.player.getHeldItemOffhand().func_190916_E();
        }
        this.renderer.drawString(String.valueOf(new StringBuilder().append("T ").append((Object)ChatFormatting.WHITE).append(n3)), 2.0f, 250.0f, this.color, true);
        this.renderer.drawString(String.valueOf(new StringBuilder().append("C ").append((Object)ChatFormatting.WHITE).append(n)), 2.0f, 260.0f, this.color, true);
        this.renderer.drawString(String.valueOf(new StringBuilder().append("X ").append((Object)ChatFormatting.WHITE).append(n4)), 2.0f, 270.0f, this.color, true);
        this.renderer.drawString(String.valueOf(new StringBuilder().append("G ").append((Object)ChatFormatting.WHITE).append(n2)), 2.0f, 280.0f, this.color, true);
    }

    public void renderArmorHUD(boolean bl) {
        int n = this.renderer.scaledWidth;
        int n2 = this.renderer.scaledHeight;
        GlStateManager.enableTexture2D();
        int n3 = n / 2;
        int n4 = 0;
        int n5 = n2 - 55 - (HUD.mc.player.isInWater() && HUD.mc.playerController.gameIsSurvivalOrAdventure() ? 10 : 0);
        for (ItemStack itemStack : HUD.mc.player.inventory.armorInventory) {
            ++n4;
            if (itemStack.func_190926_b()) continue;
            int n6 = n3 - 90 + (9 - n4) * 20 + 2;
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(itemStack, n6, n5);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HUD.mc.fontRendererObj, itemStack, n6, n5, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            String string = itemStack.func_190916_E() > 1 ? String.valueOf(new StringBuilder().append(itemStack.func_190916_E()).append("")) : "";
            this.renderer.drawStringWithShadow(string, n6 + 19 - 2 - this.renderer.getStringWidth(string), n5 + 9, 0xFFFFFF);
            if (!bl) continue;
            float f = ((float)itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / (float)itemStack.getMaxDamage();
            float f2 = 1.0f - f;
            int n7 = 100 - (int)(f2 * 100.0f);
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(n7).append("")), n6 + 8 - this.renderer.getStringWidth(String.valueOf(new StringBuilder().append(n7).append(""))) / 2, n5 - 11, ColorUtil.toRGBA((int)(f2 * 255.0f), (int)(f * 255.0f), 0));
        }
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
    }

    public void renderGreeter() {
        Color color;
        int n = this.renderer.scaledWidth;
        String string = "";
        switch (this.greeter.getValue()) {
            case TIME: {
                string = String.valueOf(new StringBuilder().append(string).append(MathUtil.getTimeOfDay()).append(HUD.mc.player.getDisplayNameString()));
                break;
            }
            case LONG: {
                string = String.valueOf(new StringBuilder().append(string).append("Welcome to LuigiHack ").append(HUD.mc.player.getDisplayNameString()).append(" "));
                break;
            }
            case CHRISTMAS: {
                string = String.valueOf(new StringBuilder().append(string).append("Merry Christmas ").append(HUD.mc.player.getDisplayNameString()).append(" :^)"));
                break;
            }
            case CUSTOM: {
                string = String.valueOf(new StringBuilder().append(string).append(this.spoofGreeter.getValue()));
                break;
            }
            default: {
                string = String.valueOf(new StringBuilder().append(string).append("Welcome ").append(HUD.mc.player.getDisplayNameString()));
            }
        }
        Color color2 = color = this.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(HUD.getInstance().hudRed.getValue(), HUD.getInstance().hudGreen.getValue(), HUD.getInstance().hudBlue.getValue());
        if (HUD.getInstance().rainbow.getValue().booleanValue()) {
            this.renderer.drawString(string, (float)n / 2.0f - (float)this.renderer.getStringWidth(string) / 2.0f + 2.0f, 2.0f, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(2) : this.color, true);
        } else {
            this.renderer.drawString(string, (float)n / 2.0f - (float)this.renderer.getStringWidth(string) / 2.0f + 2.0f, 2.0f, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow(0.89f, color) : this.color, true);
        }
    }

    public HUD() {
        super("HUD", "HUD Elements rendered on your screen", Module.Category.CLIENT, true, false, false);
        this.renderingUp = this.register(new Setting<Boolean>("RenderingUp", Boolean.TRUE, "Orientation of the HUD-Elements."));
        this.watermark = this.register(new Setting<WaterMark>("Logo", WaterMark.ALICE, "WaterMark"));
        this.watermarkYawa = this.register(new Setting<Float>("WatermarkPosY", Float.valueOf(2.0f), Float.valueOf(0.0f), Float.valueOf(20.0f)));
        this.customWatermark = this.register(new Setting<String>("WatermarkName", "sweet client 0.0.4"));
        this.modeVer = this.register(new Setting<Object>("Version", Boolean.FALSE, object -> this.watermark.getValue() != WaterMark.NONE));
        this.arrayList = this.register(new Setting<Boolean>("ActiveModules", Boolean.FALSE, "Lists the active modules."));
        this.disss = this.register(new Setting<Object>("NoBrackets", Boolean.TRUE, object -> this.arrayList.getValue()));
        this.arrayListSideLine = this.register(new Setting<Object>("ActiveModules Line", Boolean.FALSE, object -> this.arrayList.getValue()));
        this.arryListYawa = this.register(new Setting<Object>("ActiveModules PosY", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(20), object -> this.arrayList.getValue() != false && this.renderingUp.getValue() != false));
        this.alphabeticalSorting = this.register(new Setting<Object>("AlphabeticalSorting", Boolean.FALSE, object -> this.arrayList.getValue()));
        this.ping = this.register(new Setting<Boolean>("Ping", Boolean.FALSE, "Your response time to the server."));
        this.tps = this.register(new Setting<Boolean>("TPS", Boolean.FALSE, "Ticks per second of the server."));
        this.fps = this.register(new Setting<Boolean>("FPS", Boolean.FALSE, "Your frames per second."));
        this.coords = this.register(new Setting<Boolean>("Coords", Boolean.FALSE, "Your current coordinates"));
        this.direction = this.register(new Setting<Boolean>("Direction", Boolean.FALSE, "The Direction you are facing."));
        this.speed = this.register(new Setting<Boolean>("Speed", Boolean.FALSE, "Your Speed"));
        this.potions = this.register(new Setting<Boolean>("Potions", Boolean.FALSE, "Active potion effects"));
        this.altPotionsColors = this.register(new Setting<Object>("AltPotionColors", Boolean.FALSE, object -> this.potions.getValue()));
        this.armor = this.register(new Setting<Boolean>("Armor", Boolean.FALSE, "ArmorHUD"));
        this.durability = this.register(new Setting<Boolean>("Durability", Boolean.FALSE, "Durability"));
        this.percent = this.register(new Setting<Object>("Percent", Boolean.TRUE, object -> this.armor.getValue()));
        this.totems = this.register(new Setting<Boolean>("Totems", Boolean.FALSE, "TotemHUD"));
        this.itemInfo = this.register(new Setting<Boolean>("ItemInfo", Boolean.FALSE, "ItemInfo"));
        this.pvp = this.register(new Setting<Boolean>("PvpInfo", true));
        this.ItemTextInfoRW = this.register(new Setting<Boolean>("ItemTextInfoRewrite", true));
        this.greeter = this.register(new Setting<Greeter>("Welcomer", Greeter.NONE, "Greets you."));
        this.spoofGreeter = this.register(new Setting<Object>("GreeterName", "3arthqu4ke", object -> this.greeter.getValue() == Greeter.CUSTOM));
        this.lag = this.register(new Setting<LagNotify>("Lag", LagNotify.GRAY, "Lag Notifier"));
        this.serverip = this.register(new Setting<Boolean>("Server ip", false));
        this.serveripXX = this.register(new Setting<Object>("Server ip PosYX", Float.valueOf(2.0f), Float.valueOf(0.0f), Float.valueOf(1000.0f), object -> this.serverip.getValue()));
        this.serveripYY = this.register(new Setting<Object>("Server ip PosY", Float.valueOf(10.0f), Float.valueOf(0.0f), Float.valueOf(1000.0f), object -> this.serverip.getValue()));
        this.timer = new Timer();
        this.moduleTimer = new Timer();
        this.potionColorMap = new HashMap<Potion, Color>();
        this.colorSync = this.register(new Setting<Boolean>("Sync", Boolean.FALSE, "Universal colors for HUD."));
        this.rainbow = this.register(new Setting<Boolean>("Rainbow", Boolean.FALSE, "Rainbow HUD."));
        this.flowingArrayList = this.register(new Setting<Boolean>("Static Rainbow", true));
        this.factor = this.register(new Setting<Object>("Factor", Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(20), object -> this.rainbow.getValue()));
        this.rolling = this.register(new Setting<Object>("Rolling", Boolean.FALSE, object -> this.rainbow.getValue()));
        this.rainbowSpeed = this.register(new Setting<Object>("RSpeed", Integer.valueOf(20), Integer.valueOf(0), Integer.valueOf(100), object -> this.rainbow.getValue()));
        this.rainbowSaturation = this.register(new Setting<Object>("Saturation", Integer.valueOf(200), Integer.valueOf(0), Integer.valueOf(255), object -> this.rainbow.getValue()));
        this.rainbowBrightness = this.register(new Setting<Object>("Brightness", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.rainbow.getValue()));
        this.potionIcons = this.register(new Setting<Boolean>("PotionIcons", Boolean.TRUE, "Draws Potion Icons."));
        this.shadow = this.register(new Setting<Boolean>("NoShadow", Boolean.TRUE, "Draws the text with a shadow."));
        this.animationHorizontalTime = this.register(new Setting<Object>("AnimationHTime", Integer.valueOf(500), Integer.valueOf(1), Integer.valueOf(1000), object -> this.arrayList.getValue()));
        this.animationVerticalTime = this.register(new Setting<Object>("AnimationVTime", Integer.valueOf(50), Integer.valueOf(1), Integer.valueOf(500), object -> this.arrayList.getValue()));
        this.textRadar = this.register(new Setting<Boolean>("TextRadar", Boolean.FALSE, "A TextRadar"));
        this.time = this.register(new Setting<Boolean>("Time", Boolean.FALSE, "The time"));
        this.hudRed = this.register(new Setting<Object>("Red", Integer.valueOf(36), Integer.valueOf(0), Integer.valueOf(255), object -> this.rainbow.getValue() == false));
        this.hudGreen = this.register(new Setting<Object>("Green", Integer.valueOf(150), Integer.valueOf(0), Integer.valueOf(255), object -> this.rainbow.getValue() == false));
        this.hudBlue = this.register(new Setting<Object>("Blue", Integer.valueOf(190), Integer.valueOf(0), Integer.valueOf(255), object -> this.rainbow.getValue() == false));
        this.moduleProgressMap = new HashMap<Module, Float>();
        this.colorMap = new HashMap<Integer, Integer>();
        this.players = new HashMap<String, Integer>();
        this.setInstance();
        this.potionColorMap.put(MobEffects.SPEED, new Color(124, 175, 198));
        this.potionColorMap.put(MobEffects.SLOWNESS, new Color(90, 108, 129));
        this.potionColorMap.put(MobEffects.HASTE, new Color(217, 192, 67));
        this.potionColorMap.put(MobEffects.MINING_FATIGUE, new Color(74, 66, 23));
        this.potionColorMap.put(MobEffects.STRENGTH, new Color(147, 36, 35));
        this.potionColorMap.put(MobEffects.INSTANT_HEALTH, new Color(67, 10, 9));
        this.potionColorMap.put(MobEffects.INSTANT_DAMAGE, new Color(67, 10, 9));
        this.potionColorMap.put(MobEffects.JUMP_BOOST, new Color(34, 255, 76));
        this.potionColorMap.put(MobEffects.NAUSEA, new Color(85, 29, 74));
        this.potionColorMap.put(MobEffects.REGENERATION, new Color(205, 92, 171));
        this.potionColorMap.put(MobEffects.RESISTANCE, new Color(153, 69, 58));
        this.potionColorMap.put(MobEffects.FIRE_RESISTANCE, new Color(228, 154, 58));
        this.potionColorMap.put(MobEffects.WATER_BREATHING, new Color(46, 82, 153));
        this.potionColorMap.put(MobEffects.INVISIBILITY, new Color(127, 131, 146));
        this.potionColorMap.put(MobEffects.BLINDNESS, new Color(31, 31, 35));
        this.potionColorMap.put(MobEffects.NIGHT_VISION, new Color(31, 31, 161));
        this.potionColorMap.put(MobEffects.HUNGER, new Color(88, 118, 83));
        this.potionColorMap.put(MobEffects.WEAKNESS, new Color(72, 77, 72));
        this.potionColorMap.put(MobEffects.POISON, new Color(78, 147, 49));
        this.potionColorMap.put(MobEffects.WITHER, new Color(53, 42, 39));
        this.potionColorMap.put(MobEffects.HEALTH_BOOST, new Color(248, 125, 35));
        this.potionColorMap.put(MobEffects.ABSORPTION, new Color(37, 82, 165));
        this.potionColorMap.put(MobEffects.SATURATION, new Color(248, 36, 35));
        this.potionColorMap.put(MobEffects.GLOWING, new Color(148, 160, 97));
        this.potionColorMap.put(MobEffects.LEVITATION, new Color(206, 255, 255));
        this.potionColorMap.put(MobEffects.LUCK, new Color(51, 153, 0));
        this.potionColorMap.put(MobEffects.UNLUCK, new Color(192, 164, 77));
    }

    @Override
    public void onRender2D(Render2DEvent render2DEvent) {
        int n;
        String string;
        int n2;
        int n3;
        String string2;
        Object object2;
        int n4;
        if (HUD.fullNullCheck()) {
            return;
        }
        Color color = this.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(HUD.getInstance().hudRed.getValue(), HUD.getInstance().hudGreen.getValue(), HUD.getInstance().hudBlue.getValue());
        int n5 = 101 - this.rainbowSpeed.getValue();
        float f = this.colorSync.getValue() != false ? Colors.INSTANCE.hue : (float)(System.currentTimeMillis() % (long)(360 * n5)) / (360.0f * (float)n5);
        int n6 = this.renderer.scaledWidth;
        int n7 = this.renderer.scaledHeight;
        float f2 = f;
        for (n4 = 0; n4 <= n7; ++n4) {
            if (this.colorSync.getValue().booleanValue()) {
                this.colorMap.put(n4, Color.HSBtoRGB(f2, (float)Colors.INSTANCE.rainbowSaturation.getValue().intValue() / 255.0f, (float)Colors.INSTANCE.rainbowBrightness.getValue().intValue() / 255.0f));
            } else {
                this.colorMap.put(n4, Color.HSBtoRGB(f2, (float)this.rainbowSaturation.getValue().intValue() / 255.0f, (float)this.rainbowBrightness.getValue().intValue() / 255.0f));
            }
            f2 += 1.0f / (float)n7 * (float)this.factor.getValue().intValue();
        }
        GlStateManager.pushMatrix();
        if (this.rainbow.getValue().booleanValue() && !this.rolling.getValue().booleanValue()) {
            this.color = this.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColorHex() : Color.HSBtoRGB(f, (float)this.rainbowSaturation.getValue().intValue() / 255.0f, (float)this.rainbowBrightness.getValue().intValue() / 255.0f);
        } else if (!this.rainbow.getValue().booleanValue()) {
            int n8 = this.color = this.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColorHex() : ColorUtil.toRGBA(this.hudRed.getValue(), this.hudGreen.getValue(), this.hudBlue.getValue());
        }
        if (this.pvp.getValue().booleanValue()) {
            this.renderPvpInfo();
        }
        if (this.serverip.getValue().booleanValue()) {
            String string3;
            String string4 = string3 = Minecraft.getMinecraft().isSingleplayer() ? "singleplayer".toLowerCase() : HUD.mc.getCurrentServerData().serverIP.toLowerCase();
            if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                this.renderer.drawString(String.valueOf(new StringBuilder().append("IP: ").append(string3)), this.serveripXX.getValue().floatValue(), this.serveripYY.getValue().floatValue(), this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(2) : this.color, true);
            } else {
                this.renderer.drawString(String.valueOf(new StringBuilder().append("IP: ").append(string3)), this.serveripXX.getValue().floatValue(), this.serveripYY.getValue().floatValue(), this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow(0.89f, color) : this.color, true);
            }
        }
        switch (this.watermark.getValue()) {
            case LUIGI: {
                if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                    this.renderer.drawString(String.valueOf(new StringBuilder().append("LuigiHack ").append(this.modeVer.getValue() != false ? "1.0.1" : "")), 2.0f, this.watermarkYawa.getValue().floatValue(), this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(2) : this.color, true);
                    break;
                }
                this.renderer.drawString(String.valueOf(new StringBuilder().append("LuigiHack ").append(this.modeVer.getValue() != false ? "1.0.1" : "")), 2.0f, this.watermarkYawa.getValue().floatValue(), this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow(0.89f, color) : this.color, true);
                break;
            }
            case ALICE: {
                if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                    this.renderer.drawString(String.valueOf(new StringBuilder().append("Alice ").append(this.modeVer.getValue() != false ? "1.0.1" : "")), 2.0f, this.watermarkYawa.getValue().floatValue(), this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(2) : this.color, true);
                    break;
                }
                this.renderer.drawString(String.valueOf(new StringBuilder().append("Alice ").append(this.modeVer.getValue() != false ? "1.0.1" : "")), 2.0f, this.watermarkYawa.getValue().floatValue(), this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow(0.89f, color) : this.color, true);
                break;
            }
            case WhiteVersion: {
                if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                    this.renderer.drawString(String.valueOf(new StringBuilder().append("LuigiHack ").append(this.modeVer.getValue() != false ? String.valueOf(new StringBuilder().append((Object)ChatFormatting.WHITE).append("1.0.1")) : "")), 2.0f, this.watermarkYawa.getValue().floatValue(), this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(2) : this.color, true);
                    break;
                }
                this.renderer.drawString(String.valueOf(new StringBuilder().append("LuigiHack ").append(this.modeVer.getValue() != false ? String.valueOf(new StringBuilder().append((Object)ChatFormatting.WHITE).append("1.0.1")) : "")), 2.0f, this.watermarkYawa.getValue().floatValue(), this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow(0.89f, color) : this.color, true);
                break;
            }
            case CUSTOM: {
                if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                    this.renderer.drawString(String.valueOf(new StringBuilder().append(this.customWatermark.getValue()).append(" ").append(this.modeVer.getValue() != false ? "1.0.1" : "")), 2.0f, this.watermarkYawa.getValue().floatValue(), this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(2) : this.color, true);
                    break;
                }
                this.renderer.drawString(String.valueOf(new StringBuilder().append(this.customWatermark.getValue()).append(" ").append(this.modeVer.getValue() != false ? "1.0.1" : "")), 2.0f, this.watermarkYawa.getValue().floatValue(), this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow(0.89f, color) : this.color, true);
            }
        }
        if (this.textRadar.getValue().booleanValue()) {
            this.drawTextRadar(ToolTips.getInstance().isOff() || ToolTips.getInstance().shulkerSpy.getValue() == false || ToolTips.getInstance().render.getValue() == false ? 0 : ToolTips.getInstance().getTextRadarY());
        }
        int n9 = this.renderingUp.getValue() != false ? this.arryListYawa.getValue() : (n4 = HUD.mc.currentScreen instanceof GuiChat ? 14 : 0);
        if (this.arrayList.getValue().booleanValue()) {
            String string5;
            Module module;
            String string6;
            if (this.renderingUp.getValue().booleanValue()) {
                string6 = this.arrayListSideLine.getValue() != false ? "|" : "";
                object2 = this.disss.getValue() != false ? " \u00a7f" : " [\u00a7f";
                String object3 = this.disss.getValue() != false ? "" : "\u00a77]";
                string2 = this.disss.getValue() != false ? "\u00a7f" : "\u00a77";
                for (n3 = 0; n3 < (this.alphabeticalSorting.getValue() != false ? AliceMain.moduleManager.alphabeticallySortedModules.size() : AliceMain.moduleManager.sortedModules.size()); ++n3) {
                    module = this.alphabeticalSorting.getValue() != false ? AliceMain.moduleManager.alphabeticallySortedModules.get(n3) : AliceMain.moduleManager.sortedModules.get(n3);
                    string5 = String.valueOf(new StringBuilder().append(string6).append(module.getDisplayName()).append(string2).append(module.getDisplayInfo() != null ? String.valueOf(new StringBuilder().append((String)object2).append(module.getDisplayInfo()).append(object3)) : ""));
                    if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                        this.renderer.drawString(string5, (float)(n6 - 2 - this.renderer.getStringWidth(string5)) + (this.animationHorizontalTime.getValue() == 1 ? 0.0f : module.arrayListOffset), 2 + n4 * 10, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(MathUtil.clamp(2 + n4 * 10, 0, n7)) : this.color, true);
                    } else {
                        this.renderer.drawString(string5, (float)(n6 - 2 - this.renderer.getStringWidth(string5)) + (this.animationHorizontalTime.getValue() == 1 ? 0.0f : module.arrayListOffset), 2 + n4 * 10, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                    }
                    ++n4;
                }
            } else {
                string6 = this.arrayListSideLine.getValue() != false ? "|" : "";
                object2 = this.disss.getValue() != false ? " \u00a7f" : " [\u00a7f";
                String n20 = this.disss.getValue() != false ? "" : "\u00a77]";
                string2 = this.disss.getValue() != false ? "\u00a7f" : "\u00a77";
                for (n3 = 0; n3 < (this.alphabeticalSorting.getValue() != false ? AliceMain.moduleManager.alphabeticallySortedModules.size() : AliceMain.moduleManager.sortedModules.size()); ++n3) {
                    module = this.alphabeticalSorting.getValue() != false ? AliceMain.moduleManager.alphabeticallySortedModules.get(AliceMain.moduleManager.alphabeticallySortedModules.size() - 1 - n3) : AliceMain.moduleManager.sortedModules.get(n3);
                    string5 = String.valueOf(new StringBuilder().append(string6).append(module.getDisplayName()).append(string2).append(module.getDisplayInfo() != null ? String.valueOf(new StringBuilder().append((String)object2).append(module.getDisplayInfo()).append(n20)) : ""));
                    TextManager textManager = this.renderer;
                    float f3 = (float)(n6 - 2 - this.renderer.getStringWidth(string5)) + (this.animationHorizontalTime.getValue() == 1 ? 0.0f : module.arrayListOffset);
                    n4 += 10;
                    if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                        textManager.drawString(string5, f3, n7 - n4, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(MathUtil.clamp(n7 - n4, 0, n7)) : this.color, true);
                        continue;
                    }
                    this.renderer.drawString(string5, f3, n7 - n4, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                }
            }
        }
        int n10 = this.renderingUp.getValue().booleanValue() ? (HUD.mc.currentScreen instanceof GuiChat ? 0 : 0) : (n2 = 0);
        if (this.renderingUp.getValue().booleanValue()) {
            int n11;
            if (this.potions.getValue().booleanValue()) {
                for (PotionEffect potionEffect : AliceMain.potionManager.getOwnPotions()) {
                    string2 = this.altPotionsColors.getValue() != false ? AliceMain.potionManager.getPotionString(potionEffect) : AliceMain.potionManager.getColoredPotionString(potionEffect);
                    TextManager textManager = this.renderer;
                    float f4 = n6 - (this.renderer.getStringWidth(string2) + 2);
                    int n12 = n7 - 2;
                    textManager.drawString(string2, f4, n12 - (n2 += 10), this.altPotionsColors.getValue() != false ? this.potionColorMap.get((Object)potionEffect.getPotion()).getRGB() : this.color, true);
                }
            }
            if (this.speed.getValue().booleanValue()) {
                object2 = String.valueOf(new StringBuilder().append("Speed \u00a7f").append(AliceMain.speedManager.getSpeedKpH()).append(" km/h"));
                TextManager textManager = this.renderer;
                float f5 = n6 - (this.renderer.getStringWidth((String)object2) + 2);
                n3 = n7 - 2;
                n2 += 10;
                if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                    textManager.drawString((String)object2, f5, n3 - n2, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(n7 - n2) : this.color, true);
                } else {
                    textManager.drawString((String)object2, f5, n3 - n2, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                }
            }
            if (this.time.getValue().booleanValue()) {
                object2 = String.valueOf(new StringBuilder().append("Time \u00a7f").append(new SimpleDateFormat("h:mm a").format(new Date())));
                TextManager textManager = this.renderer;
                float f6 = n6 - (this.renderer.getStringWidth((String)object2) + 2);
                n3 = n7 - 2;
                n2 += 10;
                if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                    textManager.drawString((String)object2, f6, n3 - n2, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(n7 - n2) : this.color, true);
                } else {
                    textManager.drawString((String)object2, f6, n3 - n2, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                }
            }
            if (this.durability.getValue().booleanValue() && (n11 = HUD.mc.player.getHeldItemMainhand().getMaxDamage() - HUD.mc.player.getHeldItemMainhand().getItemDamage()) > 0) {
                String string6 = String.valueOf(new StringBuilder().append("Durability \u00a7a").append(n11));
                TextManager textManager = this.renderer;
                float f7 = n6 - (this.renderer.getStringWidth(string6) + 2);
                int n13 = n7 - 2;
                n2 += 10;
                if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                    textManager.drawString(string6, f7, n13 - n2, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(n7 - n2) : this.color, true);
                } else {
                    textManager.drawString(string6, f7, n13 - n2, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                }
            }
            if (this.tps.getValue().booleanValue()) {
                String string7 = String.valueOf(new StringBuilder().append("TPS \u00a7f").append(AliceMain.serverManager.getTPS()));
                TextManager textManager = this.renderer;
                float f8 = n6 - (this.renderer.getStringWidth(string7) + 2);
                int n14 = n7 - 2;
                n2 += 10;
                if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                    textManager.drawString(string7, f8, n14 - n2, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(n7 - n2) : this.color, true);
                } else {
                    textManager.drawString(string7, f8, n14 - n2, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                }
            }
            string = String.valueOf(new StringBuilder().append("FPS \u00a7f").append(Minecraft.debugFPS));
            String string7 = String.valueOf(new StringBuilder().append("Ping \u00a7f").append(AliceMain.serverManager.getPing()));
            if (this.renderer.getStringWidth(string7) > this.renderer.getStringWidth(string)) {
                if (this.ping.getValue().booleanValue()) {
                    TextManager textManager = this.renderer;
                    float f9 = n6 - (this.renderer.getStringWidth(string7) + 2);
                    int n15 = n7 - 2;
                    n2 += 10;
                    if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                        textManager.drawString(string7, f9, n15 - n2, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(n7 - n2) : this.color, true);
                    } else {
                        textManager.drawString(string7, f9, n15 - n2, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                    }
                }
                if (this.fps.getValue().booleanValue()) {
                    TextManager textManager = this.renderer;
                    float f10 = n6 - (this.renderer.getStringWidth(string) + 2);
                    int n16 = n7 - 2;
                    n2 += 10;
                    if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                        textManager.drawString(string, f10, n16 - n2, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(n7 - n2) : this.color, true);
                    } else {
                        textManager.drawString(string, f10, n16 - n2, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                    }
                }
            } else {
                if (this.fps.getValue().booleanValue()) {
                    TextManager textManager = this.renderer;
                    float f11 = n6 - (this.renderer.getStringWidth(string) + 2);
                    int n17 = n7 - 2;
                    n2 += 10;
                    if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                        textManager.drawString(string, f11, n17 - n2, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(n7 - n2) : this.color, true);
                    } else {
                        textManager.drawString(string, f11, n17 - n2, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                    }
                }
                if (this.ping.getValue().booleanValue()) {
                    TextManager textManager = this.renderer;
                    float f12 = n6 - (this.renderer.getStringWidth(string7) + 2);
                    int n18 = n7 - 2;
                    n2 += 10;
                    if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                        textManager.drawString(string7, f12, n18 - n2, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(n7 - n2) : this.color, true);
                    } else {
                        textManager.drawString(string7, f12, n18 - n2, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                    }
                }
            }
        } else {
            int n19;
            if (this.potions.getValue().booleanValue()) {
                for (PotionEffect potionEffect : AliceMain.potionManager.getOwnPotions()) {
                    string2 = this.altPotionsColors.getValue() != false ? AliceMain.potionManager.getPotionString(potionEffect) : AliceMain.potionManager.getColoredPotionString(potionEffect);
                    this.renderer.drawString(string2, n6 - (this.renderer.getStringWidth(string2) + 2), 2 + n2++ * 10, this.altPotionsColors.getValue() != false ? this.potionColorMap.get((Object)potionEffect.getPotion()).getRGB() : this.color, true);
                }
            }
            if (this.speed.getValue().booleanValue()) {
                object2 = String.valueOf(new StringBuilder().append("Speed \u00a7f").append(AliceMain.speedManager.getSpeedKpH()).append(" km/h"));
                if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                    this.renderer.drawString((String)object2, n6 - (this.renderer.getStringWidth((String)object2) + 2), 2 + n2++ * 10, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(2 + n2 * 10) : this.color, true);
                } else {
                    this.renderer.drawString((String)object2, n6 - (this.renderer.getStringWidth((String)object2) + 2), 2 + n2++ * 10, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                }
            }
            if (this.time.getValue().booleanValue()) {
                object2 = String.valueOf(new StringBuilder().append("Time \u00a7f").append(new SimpleDateFormat("h:mm a").format(new Date())));
                if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                    this.renderer.drawString((String)object2, n6 - (this.renderer.getStringWidth((String)object2) + 2), 2 + n2++ * 10, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(2 + n2 * 10) : this.color, true);
                } else {
                    this.renderer.drawString((String)object2, n6 - (this.renderer.getStringWidth((String)object2) + 2), 2 + n2++ * 10, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                }
            }
            if (this.durability.getValue().booleanValue() && (n19 = HUD.mc.player.getHeldItemMainhand().getMaxDamage() - HUD.mc.player.getHeldItemMainhand().getItemDamage()) > 0) {
                String string8 = String.valueOf(new StringBuilder().append("Durability \u00a7a").append(n19));
                if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                    this.renderer.drawString(string8, n6 - (this.renderer.getStringWidth(string8) + 2), 2 + n2++ * 10, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(2 + n2 * 10) : this.color, true);
                } else {
                    this.renderer.drawString(string8, n6 - (this.renderer.getStringWidth(string8) + 2), 2 + n2++ * 10, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                }
            }
            if (this.tps.getValue().booleanValue()) {
                String string8 = String.valueOf(new StringBuilder().append("TPS \u00a7f").append(AliceMain.serverManager.getTPS()));
                if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                    this.renderer.drawString(string8, n6 - (this.renderer.getStringWidth(string8) + 2), 2 + n2++ * 10, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(2 + n2 * 10) : this.color, true);
                } else {
                    this.renderer.drawString(string8, n6 - (this.renderer.getStringWidth(string8) + 2), 2 + n2++ * 10, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                }
            }
            string = String.valueOf(new StringBuilder().append("FPS \u00a7f").append(Minecraft.debugFPS));
            String string9 = String.valueOf(new StringBuilder().append("Ping \u00a7f").append(AliceMain.serverManager.getPing()));
            if (this.renderer.getStringWidth(string9) > this.renderer.getStringWidth(string)) {
                if (this.ping.getValue().booleanValue()) {
                    if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                        this.renderer.drawString(string9, n6 - (this.renderer.getStringWidth(string9) + 2), 2 + n2++ * 10, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(2 + n2 * 10) : this.color, true);
                    } else {
                        this.renderer.drawString(string9, n6 - (this.renderer.getStringWidth(string9) + 2), 2 + n2++ * 10, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                    }
                }
                if (this.fps.getValue().booleanValue()) {
                    if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                        this.renderer.drawString(string, n6 - (this.renderer.getStringWidth(string) + 2), 2 + n2++ * 10, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(2 + n2 * 10) : this.color, true);
                    } else {
                        this.renderer.drawString(string, n6 - (this.renderer.getStringWidth(string) + 2), 2 + n2++ * 10, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                    }
                }
            } else {
                if (this.fps.getValue().booleanValue()) {
                    if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                        this.renderer.drawString(string, n6 - (this.renderer.getStringWidth(string) + 2), 2 + n2++ * 10, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(2 + n2 * 10) : this.color, true);
                    } else {
                        this.renderer.drawString(string, n6 - (this.renderer.getStringWidth(string) + 2), 2 + n2++ * 10, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                    }
                }
                if (this.ping.getValue().booleanValue()) {
                    if (HUD.getInstance().rainbow.getValue().booleanValue()) {
                        this.renderer.drawString(string9, n6 - (this.renderer.getStringWidth(string9) + 2), 2 + n2++ * 10, this.rolling.getValue() != false && this.rainbow.getValue() != false ? this.colorMap.get(2 + n2 * 10) : this.color, true);
                    } else {
                        this.renderer.drawString(string9, n6 - (this.renderer.getStringWidth(string9) + 2), 2 + n2++ * 10, this.flowingArrayList.getValue() != false ? ColorUtil.staticRainbow((float)(n4 + 1) * 0.89f, color) : this.color, true);
                    }
                }
            }
        }
        boolean bl = HUD.mc.world.getBiome(HUD.mc.player.getPosition()).getBiomeName().equals("Hell");
        int n19 = (int)HUD.mc.player.posX;
        int n20 = (int)HUD.mc.player.posY;
        int n21 = (int)HUD.mc.player.posZ;
        float f13 = bl ? 8.0f : 0.125f;
        int n22 = (int)(HUD.mc.player.posX * (double)f13);
        int n23 = (int)(HUD.mc.player.posZ * (double)f13);
        if (this.renderingUp.getValue().booleanValue()) {
            AliceMain.notificationManager.handleNotifications(n7 - (n2 + 16));
        } else {
            AliceMain.notificationManager.handleNotifications(n7 - (n4 + 16));
        }
        n2 = HUD.mc.currentScreen instanceof GuiChat ? 14 : 0;
        String string10 = String.valueOf(new StringBuilder().append("XYZ \u00a7f").append(n19).append(", ").append(n20).append(", ").append(n21).append(" \u00a7r[\u00a7f").append(n22).append(", ").append(n23).append("\u00a7r]"));
        String string11 = String.valueOf(new StringBuilder().append(this.direction.getValue() != false ? String.valueOf(new StringBuilder().append(AliceMain.rotationManager.getDirection4D(false)).append(" ")) : "").append(this.coords.getValue() != false ? string10 : "").append(""));
        TextManager textManager = this.renderer;
        float f14 = 2.0f;
        float f15 = n7 - (n2 += 10);
        if (this.rolling.getValue().booleanValue() && this.rainbow.getValue().booleanValue()) {
            Map<Integer, Integer> map2 = this.colorMap;
            n = map2.get(n7 - (n2 += 10));
        } else {
            n = this.color;
        }
        textManager.drawString(string11, 2.0f, f15, n, true);
        if (this.armor.getValue().booleanValue()) {
            this.renderArmorHUD(this.percent.getValue());
        }
        if (this.totems.getValue().booleanValue()) {
            this.renderTotemHUD();
        }
        if (this.greeter.getValue() != Greeter.NONE) {
            this.renderGreeter();
        }
        if (this.lag.getValue() != LagNotify.NONE) {
            this.renderLag();
        }
        GlStateManager.popMatrix();
        if (this.itemInfo.getValue().booleanValue()) {
            this.renderTotemHUD2();
            this.renderCrystalHud();
            this.renderExpHud();
            this.renderGapsHud();
        }
        if (this.ItemTextInfoRW.getValue().booleanValue()) {
            this.renderItemTextInfoShort();
        }
    }

    public static HUD getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HUD();
        }
        return INSTANCE;
    }

    @Override
    public void onUpdate() {
        for (Module module : AliceMain.moduleManager.sortedModules) {
            if (!module.isDisabled() || module.arrayListOffset != 0.0f) continue;
            module.sliding = true;
        }
        if (this.timer.passedMs(0L)) {
            this.players = this.getTextRadarPlayers();
            this.timer.reset();
        }
        if (this.shouldIncrement) {
            ++this.hitMarkerTimer;
        }
        if (this.hitMarkerTimer == 10) {
            this.hitMarkerTimer = 0;
            this.shouldIncrement = false;
        }
    }

    public Map<String, Integer> getTextRadarPlayers() {
        return EntityUtil.getTextRadarPlayers();
    }

    public void renderGapsHud() {
        int n = HUD.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.GOLDEN_APPLE).mapToInt(ItemStack::func_190916_E).sum();
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE) {
            n += HUD.mc.player.getHeldItemOffhand().func_190916_E();
        }
        if (n > 0) {
            GlStateManager.enableTexture2D();
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(gapples, 0, 69);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HUD.mc.fontRendererObj, gapples, 0, 69, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(n).append("")), 10.0f, 79.0f, 0xFFFFFF);
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
    }

    public void renderExpHud() {
        int n = HUD.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.EXPERIENCE_BOTTLE).mapToInt(ItemStack::func_190916_E).sum();
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE) {
            n += HUD.mc.player.getHeldItemOffhand().func_190916_E();
        }
        if (n > 0) {
            GlStateManager.enableTexture2D();
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(exp, 0, 54);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HUD.mc.fontRendererObj, exp, 10, 54, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(n).append("")), 10.0f, 64.0f, 0xFFFFFF);
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
    }

    public void renderTotemHUD() {
        int n = this.renderer.scaledWidth;
        int n2 = this.renderer.scaledHeight;
        int n3 = HUD.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.field_190929_cY).mapToInt(ItemStack::func_190916_E).sum();
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.field_190929_cY) {
            n3 += HUD.mc.player.getHeldItemOffhand().func_190916_E();
        }
        if (n3 > 0) {
            GlStateManager.enableTexture2D();
            int n4 = n / 2;
            int n5 = n2 - 55 - (HUD.mc.player.isInWater() && HUD.mc.playerController.gameIsSurvivalOrAdventure() ? 10 : 0);
            int n6 = n4 - 189 + 180 + 2;
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(totem, n6, n5);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HUD.mc.fontRendererObj, totem, n6, n5, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(n3).append("")), n6 + 19 - 2 - this.renderer.getStringWidth(String.valueOf(new StringBuilder().append(n3).append(""))), n5 + 9, 0xFFFFFF);
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
    }

    private void setInstance() {
        INSTANCE = this;
    }

    static {
        box = new ResourceLocation("textures/gui/container/shulker_box.png");
        totem = new ItemStack(Items.field_190929_cY);
        totem2 = new ItemStack(Items.field_190929_cY);
        crystals = new ItemStack(Items.END_CRYSTAL);
        gapples = new ItemStack(Items.GOLDEN_APPLE);
        exp = new ItemStack(Items.EXPERIENCE_BOTTLE);
        codHitmarker = new ResourceLocation("earthhack", "cod_hitmarker");
        COD_EVENT = new SoundEvent(codHitmarker);
        csgoHitmarker = new ResourceLocation("earthhack", "csgo_hitmarker");
        CSGO_EVENT = new SoundEvent(csgoHitmarker);
        INSTANCE = new HUD();
    }

    @SubscribeEvent
    public void onModuleToggle(ClientEvent clientEvent) {
        block4: {
            block5: {
                if (!(clientEvent.getFeature() instanceof Module)) break block4;
                if (clientEvent.getStage() != 0) break block5;
                for (float f = 0.0f; f <= (float)this.renderer.getStringWidth(((Module)clientEvent.getFeature()).getDisplayName()); f += (float)this.renderer.getStringWidth(((Module)clientEvent.getFeature()).getDisplayName()) / 500.0f) {
                    if (this.moduleTimer.passedMs(1L)) {
                        this.moduleProgressMap.put((Module)clientEvent.getFeature(), Float.valueOf((float)this.renderer.getStringWidth(((Module)clientEvent.getFeature()).getDisplayName()) - f));
                    }
                    this.timer.reset();
                }
                break block4;
            }
            if (clientEvent.getStage() != 1) break block4;
            for (float f = 0.0f; f <= (float)this.renderer.getStringWidth(((Module)clientEvent.getFeature()).getDisplayName()); f += (float)this.renderer.getStringWidth(((Module)clientEvent.getFeature()).getDisplayName()) / 500.0f) {
                if (this.moduleTimer.passedMs(1L)) {
                    this.moduleProgressMap.put((Module)clientEvent.getFeature(), Float.valueOf((float)this.renderer.getStringWidth(((Module)clientEvent.getFeature()).getDisplayName()) - f));
                }
                this.timer.reset();
            }
        }
    }

    public void renderCrystalHud() {
        int n = HUD.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.END_CRYSTAL).mapToInt(ItemStack::func_190916_E).sum();
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            n += HUD.mc.player.getHeldItemOffhand().func_190916_E();
        }
        if (n > 0) {
            GlStateManager.enableTexture2D();
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(crystals, 0, 37);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HUD.mc.fontRendererObj, crystals, 0, 37, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(n).append("")), 10.0f, 47.0f, 0xFFFFFF);
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
    }

    public static enum WaterMark {
        NONE,
        ALICE,
        LUIGI,
        WhiteVersion,
        CUSTOM;

    }

    public static enum Greeter {
        NONE,
        NAME,
        TIME,
        CHRISTMAS,
        LONG,
        CUSTOM;

    }

    public static enum LagNotify {
        NONE,
        RED,
        GRAY;

    }

    public static enum Sound {
        NONE,
        COD,
        CSGO;

    }
}

