//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentProtection
 *  net.minecraft.enchantment.EnchantmentProtection$Type
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemTool
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.text.TextFormatting
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.module.modules.render;

import java.awt.Color;
import java.util.Iterator;
import java.util.Objects;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.util.ColorUtil;
import me.snow.aclient.util.DamageUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.RotationUtil;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;

public class Nametags
extends Module {
    private final /* synthetic */ Setting<Integer> blueSetting;
    private final /* synthetic */ Setting<Boolean> scaleing;
    private final /* synthetic */ Setting<Integer> alphaSetting;
    private final /* synthetic */ Setting<Boolean> background;
    private final /* synthetic */ Setting<Boolean> health;
    private final /* synthetic */ Setting<Boolean> totemPops;
    private final /* synthetic */ Setting<Boolean> colorSync;
    private final /* synthetic */ Setting<Boolean> armor;
    private final /* synthetic */ Setting<Float> lineWidth;
    private final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<Boolean> ping;
    private final /* synthetic */ Setting<Boolean> entityID;
    private final /* synthetic */ Setting<Boolean> invisibles;
    private final /* synthetic */ Setting<Integer> greenSetting;
    private static /* synthetic */ Nametags INSTANCE;
    private final /* synthetic */ Setting<Boolean> smartScale;
    private final /* synthetic */ Setting<Float> scaling;
    private final /* synthetic */ Setting<Integer> redSetting;
    private final /* synthetic */ Setting<Boolean> heldStackName;
    private final /* synthetic */ Setting<Boolean> outline;
    private final /* synthetic */ Setting<Boolean> onlyFov;
    private final /* synthetic */ Setting<Boolean> gamemode;
    private final /* synthetic */ Setting<Float> factor;

    private float getBiggestArmorTag(EntityPlayer entityPlayer) {
        ItemStack itemStack;
        Enchantment enchantment;
        short s;
        int n;
        float f = 0.0f;
        boolean bl = false;
        for (ItemStack itemStack2 : entityPlayer.inventory.armorInventory) {
            float f2 = 0.0f;
            if (itemStack2 != null) {
                NBTTagList nBTTagList = itemStack2.getEnchantmentTagList();
                for (n = 0; n < nBTTagList.tagCount(); ++n) {
                    s = nBTTagList.getCompoundTagAt(n).getShort("id");
                    enchantment = Enchantment.getEnchantmentByID((int)s);
                    if (enchantment == null) continue;
                    f2 += 8.0f;
                    bl = true;
                }
            }
            if (!(f2 > f)) continue;
            f = f2;
        }
        Iterator iterator2 = entityPlayer.getHeldItemMainhand().copy();
        if (iterator2.hasEffect()) {
            float f3 = 0.0f;
            NBTTagList nBTTagList = iterator2.getEnchantmentTagList();
            for (int i = 0; i < nBTTagList.tagCount(); ++i) {
                s = nBTTagList.getCompoundTagAt(i).getShort("id");
                Enchantment enchantment2 = Enchantment.getEnchantmentByID((int)s);
                if (enchantment2 == null) continue;
                f3 += 8.0f;
                bl = true;
            }
            if (f3 > f) {
                f = f3;
            }
        }
        if ((itemStack = entityPlayer.getHeldItemOffhand().copy()).hasEffect()) {
            float f4 = 0.0f;
            NBTTagList nBTTagList = itemStack.getEnchantmentTagList();
            for (n = 0; n < nBTTagList.tagCount(); ++n) {
                short s2 = nBTTagList.getCompoundTagAt(n).getShort("id");
                enchantment = Enchantment.getEnchantmentByID((int)s2);
                if (enchantment == null) continue;
                f4 += 8.0f;
                bl = true;
            }
            if (f4 > f) {
                f = f4;
            }
        }
        return (float)(bl ? 0 : 20) + f;
    }

    public void drawRect(float f, float f2, float f3, float f4, int n) {
        float f5 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(n & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth((float)this.lineWidth.getValue().floatValue());
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos((double)f, (double)f4, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)f3, (double)f4, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)f3, (double)f2, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)f, (double)f2, 0.0).color(f6, f7, f8, f5).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    private double interpolate(double d, double d2, float f) {
        return d + (d2 - d) * (double)f;
    }

    public Nametags() {
        super("Nametags", "Better Nametags.", Module.Category.RENDER, false, false, false);
        this.health = this.register(new Setting<Boolean>("Health", true));
        this.armor = this.register(new Setting<Boolean>("Armor", true));
        this.mode = this.register(new Setting<Mode>("Mode", Mode.MINIMAL));
        this.invisibles = this.register(new Setting<Boolean>("Invisibles", false));
        this.ping = this.register(new Setting<Boolean>("Ping", true));
        this.totemPops = this.register(new Setting<Boolean>("TotemPops", true));
        this.gamemode = this.register(new Setting<Boolean>("Gamemode", false));
        this.entityID = this.register(new Setting<Boolean>("ID", false));
        this.background = this.register(new Setting<Boolean>("Background", true));
        this.outline = this.register(new Setting<Boolean>("Outline", Boolean.FALSE));
        this.colorSync = this.register(new Setting<Object>("Sync", Boolean.FALSE, object -> this.outline.getValue()));
        this.redSetting = this.register(new Setting<Object>("Red", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.outline.getValue()));
        this.greenSetting = this.register(new Setting<Object>("Green", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.outline.getValue()));
        this.blueSetting = this.register(new Setting<Object>("Blue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.outline.getValue()));
        this.alphaSetting = this.register(new Setting<Object>("Alpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.outline.getValue()));
        this.lineWidth = this.register(new Setting<Object>("LineWidth", Float.valueOf(1.5f), Float.valueOf(0.1f), Float.valueOf(5.0f), object -> this.outline.getValue()));
        this.heldStackName = this.register(new Setting<Boolean>("StackName", false));
        this.onlyFov = this.register(new Setting<Boolean>("OnlyFov", false));
        this.scaleing = this.register(new Setting<Boolean>("Scale", true));
        this.scaling = this.register(new Setting<Float>("Size", Float.valueOf(3.0f), Float.valueOf(0.1f), Float.valueOf(20.0f)));
        this.factor = this.register(new Setting<Object>("Factor", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(1.0f), object -> this.scaleing.getValue()));
        this.smartScale = this.register(new Setting<Object>("SmartScale", Boolean.TRUE, object -> this.scaleing.getValue()));
        this.setInstance();
    }

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        if (!Nametags.fullNullCheck()) {
            for (EntityPlayer entityPlayer : Nametags.mc.world.playerEntities) {
                if (entityPlayer == null || entityPlayer.equals((Object)Nametags.mc.player) || !entityPlayer.isEntityAlive() || entityPlayer.isInvisible() && !this.invisibles.getValue().booleanValue() || this.onlyFov.getValue().booleanValue() && RotationUtil.isInFov((Entity)entityPlayer)) continue;
                double d = this.interpolate(entityPlayer.lastTickPosX, entityPlayer.posX, render3DEvent.getPartialTicks()) - Nametags.mc.getRenderManager().renderPosX;
                double d2 = this.interpolate(entityPlayer.lastTickPosY, entityPlayer.posY, render3DEvent.getPartialTicks()) - Nametags.mc.getRenderManager().renderPosY;
                double d3 = this.interpolate(entityPlayer.lastTickPosZ, entityPlayer.posZ, render3DEvent.getPartialTicks()) - Nametags.mc.getRenderManager().renderPosZ;
                this.renderNameTag(entityPlayer, d, d2, d3, render3DEvent.getPartialTicks());
            }
        }
    }

    private int getDisplayColour(EntityPlayer entityPlayer) {
        int n = -5592406;
        n = -1;
        if (AliceMain.friendManager.isFriend(entityPlayer)) {
            return -10027009;
        }
        if (entityPlayer.isInvisible()) {
            n = -1113785;
        } else if (entityPlayer.isSneaking()) {
            n = ColorUtil.toRGBA(221, 124, 30, 255);
        }
        return n;
    }

    static {
        INSTANCE = new Nametags();
    }

    private void renderEnchantmentText(ItemStack itemStack, int n) {
        int n2;
        int n3 = -34;
        if (itemStack.getItem() == Items.GOLDEN_APPLE && itemStack.hasEffect()) {
            this.renderer.drawStringWithShadow("god", n * 2, n3, -3977919);
            n3 -= 8;
        }
        NBTTagList nBTTagList = itemStack.getEnchantmentTagList();
        for (n2 = 0; n2 < nBTTagList.tagCount(); ++n2) {
            Object object;
            short s = nBTTagList.getCompoundTagAt(n2).getShort("id");
            short s2 = nBTTagList.getCompoundTagAt(n2).getShort("lvl");
            Enchantment enchantment = Enchantment.getEnchantmentByID((int)s);
            if (enchantment == null) continue;
            if (this.mode.getValue() == Mode.MINIMAL) {
                if (!(enchantment instanceof EnchantmentProtection)) continue;
                object = (EnchantmentProtection)enchantment;
                if (object.protectionType != EnchantmentProtection.Type.EXPLOSION && object.protectionType != EnchantmentProtection.Type.ALL) continue;
            }
            object = enchantment.func_190936_d() ? String.valueOf(new StringBuilder().append((Object)TextFormatting.RED).append(enchantment.getTranslatedName((int)s2).substring(11).substring(0, 1).toLowerCase())) : enchantment.getTranslatedName((int)s2).substring(0, 1).toLowerCase();
            object = String.valueOf(new StringBuilder().append((String)object).append(s2));
            this.renderer.drawStringWithShadow((String)object, n * 2, n3, -1);
            n3 -= 8;
        }
        if (DamageUtil.hasDurability(itemStack)) {
            n2 = DamageUtil.getRoundedDamage(itemStack);
            String string = n2 >= 60 ? "\u00a7a" : (n2 >= 25 ? "\u00a7e" : "\u00a7c");
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(string).append(n2).append("%")), n * 2, n3, -1);
        }
    }

    private void renderNameTag(EntityPlayer entityPlayer, double d, double d2, double d3, float f) {
        double d4 = d2;
        d4 += entityPlayer.isSneaking() ? 0.5 : 0.7;
        Entity entity = mc.getRenderViewEntity();
        assert (entity != null);
        double d5 = entity.posX;
        double d6 = entity.posY;
        double d7 = entity.posZ;
        entity.posX = this.interpolate(entity.prevPosX, entity.posX, f);
        entity.posY = this.interpolate(entity.prevPosY, entity.posY, f);
        entity.posZ = this.interpolate(entity.prevPosZ, entity.posZ, f);
        String string = this.getDisplayTag(entityPlayer);
        double d8 = entity.getDistance(d + Nametags.mc.getRenderManager().viewerPosX, d2 + Nametags.mc.getRenderManager().viewerPosY, d3 + Nametags.mc.getRenderManager().viewerPosZ);
        int n = this.renderer.getStringWidth(string) / 2;
        double d9 = (0.0018 + (double)this.scaling.getValue().floatValue() * (d8 * (double)this.factor.getValue().floatValue())) / 1000.0;
        if (d8 <= 8.0 && this.smartScale.getValue().booleanValue()) {
            d9 = 0.0245;
        }
        if (!this.scaleing.getValue().booleanValue()) {
            d9 = (double)this.scaling.getValue().floatValue() / 100.0;
        }
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset((float)1.0f, (float)-1500000.0f);
        GlStateManager.disableLighting();
        GlStateManager.translate((float)((float)d), (float)((float)d4 + 1.4f), (float)((float)d3));
        GlStateManager.rotate((float)(-Nametags.mc.getRenderManager().playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)Nametags.mc.getRenderManager().playerViewX, (float)(Nametags.mc.gameSettings.thirdPersonView == 2 ? -1.0f : 1.0f), (float)0.0f, (float)0.0f);
        GlStateManager.scale((double)(-d9), (double)(-d9), (double)d9);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.enableBlend();
        if (this.background.getValue().booleanValue()) {
            this.drawRect(-n - 2, -(this.renderer.getFontHeight() + 1), (float)n + 2.0f, 1.5f, 0x55000000);
        }
        if (this.outline.getValue().booleanValue()) {
            int n2 = this.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColorHex() : new Color(this.redSetting.getValue(), this.greenSetting.getValue(), this.blueSetting.getValue(), this.alphaSetting.getValue()).getRGB();
            this.drawOutlineRect(-n - 2, -(Nametags.mc.fontRendererObj.FONT_HEIGHT + 1), (float)n + 2.0f, 1.5f, n2);
        }
        GlStateManager.disableBlend();
        ItemStack itemStack = entityPlayer.getHeldItemMainhand().copy();
        if (itemStack.hasEffect() && (itemStack.getItem() instanceof ItemTool || itemStack.getItem() instanceof ItemArmor)) {
            itemStack.stackSize = 1;
        }
        if (this.heldStackName.getValue().booleanValue() && !itemStack.field_190928_g && itemStack.getItem() != Items.field_190931_a) {
            String string2 = itemStack.getDisplayName();
            int n3 = this.renderer.getStringWidth(string2) / 2;
            GL11.glPushMatrix();
            GL11.glScalef((float)0.75f, (float)0.75f, (float)0.0f);
            this.renderer.drawStringWithShadow(string2, -n3, -(this.getBiggestArmorTag(entityPlayer) + 20.0f), -1);
            GL11.glScalef((float)1.5f, (float)1.5f, (float)1.0f);
            GL11.glPopMatrix();
        }
        if (this.armor.getValue().booleanValue()) {
            GlStateManager.pushMatrix();
            int n4 = -8;
            for (ItemStack itemStack2 : entityPlayer.inventory.armorInventory) {
                if (itemStack2 == null) continue;
                n4 -= 8;
            }
            Iterator iterator2 = entityPlayer.getHeldItemOffhand().copy();
            this.renderItemStack(itemStack, n4 -= 8);
            n4 += 16;
            for (int i = 3; i >= 0; --i) {
                ItemStack itemStack3 = (ItemStack)entityPlayer.inventory.armorInventory.get(i);
                if (itemStack3 == null) continue;
                ItemStack itemStack4 = itemStack3.copy();
                this.renderItemStack(itemStack4, n4);
                n4 += 16;
            }
            this.renderItemStack((ItemStack)iterator2, n4);
            GlStateManager.popMatrix();
        }
        this.renderer.drawStringWithShadow(string, -n, -(this.renderer.getFontHeight() - 1), this.getDisplayColour(entityPlayer));
        entity.posX = d5;
        entity.posY = d6;
        entity.posZ = d7;
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.disablePolygonOffset();
        GlStateManager.doPolygonOffset((float)1.0f, (float)1500000.0f);
        GlStateManager.popMatrix();
    }

    public void drawOutlineRect(float f, float f2, float f3, float f4, int n) {
        float f5 = (float)(n >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(n >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(n >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(n & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth((float)this.lineWidth.getValue().floatValue());
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        bufferBuilder.begin(2, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos((double)f, (double)f4, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)f3, (double)f4, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)f3, (double)f2, 0.0).color(f6, f7, f8, f5).endVertex();
        bufferBuilder.pos((double)f, (double)f2, 0.0).color(f6, f7, f8, f5).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    private void renderItemStack(ItemStack itemStack, int n) {
        GlStateManager.pushMatrix();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.clear((int)256);
        RenderHelper.enableStandardItemLighting();
        Nametags.mc.getRenderItem().zLevel = -150.0f;
        GlStateManager.disableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.disableCull();
        mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, n, -26);
        mc.getRenderItem().renderItemOverlays(Nametags.mc.fontRendererObj, itemStack, n, -26);
        Nametags.mc.getRenderItem().zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.scale((float)0.5f, (float)0.5f, (float)0.5f);
        GlStateManager.disableDepth();
        if (this.mode.getValue() != Mode.NONE) {
            this.renderEnchantmentText(itemStack, n);
        }
        GlStateManager.enableDepth();
        GlStateManager.scale((float)2.0f, (float)2.0f, (float)2.0f);
        GlStateManager.popMatrix();
    }

    private String getDisplayTag(EntityPlayer entityPlayer) {
        String string;
        String string2 = entityPlayer.getDisplayName().getFormattedText();
        if (string2.contains(mc.getSession().getUsername())) {
            string2 = Nametags.mc.player.getDisplayNameString();
        }
        if (!this.health.getValue().booleanValue()) {
            return string2;
        }
        float f = EntityUtil.getHealth((Entity)entityPlayer);
        String string3 = f > 18.0f ? "\u00a7a" : (f > 16.0f ? "\u00a72" : (f > 12.0f ? "\u00a7e" : (f > 8.0f ? "\u00a76" : (f > 5.0f ? "\u00a7c" : "\u00a74"))));
        String string4 = "";
        if (this.ping.getValue().booleanValue()) {
            try {
                int n = Objects.requireNonNull(mc.getConnection()).getPlayerInfo(entityPlayer.getUniqueID()).getResponseTime();
                string4 = String.valueOf(new StringBuilder().append(string4).append(" ").append(n).append("ms"));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        String string5 = " ";
        if (this.totemPops.getValue().booleanValue()) {
            string5 = String.valueOf(new StringBuilder().append(string5).append(AliceMain.totemPopManager.getTotemPopString(entityPlayer)));
        }
        String string6 = "";
        if (this.entityID.getValue().booleanValue()) {
            string6 = String.valueOf(new StringBuilder().append(string6).append(" ID: ").append(entityPlayer.getEntityId()).append(" "));
        }
        String string7 = "";
        if (this.gamemode.getValue().booleanValue()) {
            string = entityPlayer.isCreative() ? String.valueOf(new StringBuilder().append(string7).append("[C] ")) : (string7 = entityPlayer.isSpectator() || entityPlayer.isInvisible() ? String.valueOf(new StringBuilder().append(string7).append("[I] ")) : String.valueOf(new StringBuilder().append(string7).append("[S] ")));
        }
        string = "";
        string = Math.floor(f) == (double)f ? String.valueOf(new StringBuilder().append(string3).append(" ").append(f > 0.0f ? Integer.valueOf((int)Math.floor(f)) : "dead")) : String.valueOf(new StringBuilder().append(string3).append(" ").append(f > 0.0f ? Integer.valueOf((int)f) : "dead"));
        return String.valueOf(new StringBuilder().append(string2).append(string6).append(string7).append(string4).append(string).append(string5));
    }

    public static Nametags getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Nametags();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    public static enum Mode {
        FULL,
        MINIMAL,
        NONE;

    }
}

