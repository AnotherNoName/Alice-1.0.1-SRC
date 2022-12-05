//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.network.play.server.SPacketDestroyEntities
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.module.modules.render;

import java.awt.Color;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.RenderEntityModelEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.util.EntityUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.network.play.server.SPacketDestroyEntities;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class CrystalChams
extends Module {
    public /* synthetic */ Setting<Integer> hiddenGreen;
    public /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Boolean> throughWalls;
    public /* synthetic */ Setting<Boolean> chams;
    public /* synthetic */ Setting<Integer> hiddenRed;
    public /* synthetic */ Map<EntityEnderCrystal, Float> scaleMap;
    public /* synthetic */ Setting<Boolean> xqz;
    public /* synthetic */ Setting<Integer> blue;
    static final /* synthetic */ ResourceLocation RES_ITEM_GLINT;
    public /* synthetic */ Setting<Boolean> wireframe;
    public /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Boolean> glint;
    public /* synthetic */ Setting<Integer> alpha;
    public static /* synthetic */ CrystalChams INSTANCE;
    public /* synthetic */ Setting<Boolean> wireframeThroughWalls;
    public /* synthetic */ Setting<Integer> hiddenAlpha;
    public /* synthetic */ Setting<Integer> brightness;
    public /* synthetic */ Setting<Integer> speed;
    public /* synthetic */ Setting<Integer> saturation;
    public /* synthetic */ Setting<Boolean> animateScale;
    public /* synthetic */ Setting<Boolean> rainbow;
    public /* synthetic */ Setting<Integer> hiddenBlue;
    public /* synthetic */ Setting<Float> lineWidth;
    public /* synthetic */ Setting<Boolean> colorSync;
    public /* synthetic */ Setting<Float> scale;

    @SubscribeEvent
    public void onReceivePacket(PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketDestroyEntities) {
            SPacketDestroyEntities sPacketDestroyEntities = (SPacketDestroyEntities)receive.getPacket();
            for (int n : sPacketDestroyEntities.getEntityIDs()) {
                Entity entity = CrystalChams.mc.world.getEntityByID(n);
                if (!(entity instanceof EntityEnderCrystal)) continue;
                this.scaleMap.remove((Object)entity);
            }
        }
    }

    public CrystalChams() {
        super("CrystalChams", "Modifies crystal rendering in different ways", Module.Category.RENDER, true, false, false);
        this.animateScale = this.register(new Setting<Boolean>("AnimateScale", false));
        this.chams = this.register(new Setting<Boolean>("Chams", false));
        this.throughWalls = this.register(new Setting<Boolean>("ThroughWalls", true));
        this.wireframeThroughWalls = this.register(new Setting<Boolean>("WireThroughWalls", true));
        this.glint = this.register(new Setting<Object>("Glint", Boolean.FALSE, object -> this.chams.getValue()));
        this.wireframe = this.register(new Setting<Boolean>("Wireframe", false));
        this.scale = this.register(new Setting<Float>("Scale", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(10.0f)));
        this.lineWidth = this.register(new Setting<Float>("LineWidth", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(3.0f)));
        this.colorSync = this.register(new Setting<Boolean>("Sync", false));
        this.rainbow = this.register(new Setting<Boolean>("Rainbow", false));
        this.saturation = this.register(new Setting<Object>("Saturation", Integer.valueOf(50), Integer.valueOf(0), Integer.valueOf(100), object -> this.rainbow.getValue()));
        this.brightness = this.register(new Setting<Object>("Brightness", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(100), object -> this.rainbow.getValue()));
        this.speed = this.register(new Setting<Object>("Speed", Integer.valueOf(40), Integer.valueOf(1), Integer.valueOf(100), object -> this.rainbow.getValue()));
        this.xqz = this.register(new Setting<Object>("XQZ", Boolean.FALSE, object -> this.rainbow.getValue() == false && this.throughWalls.getValue() != false));
        this.red = this.register(new Setting<Object>("Red", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.rainbow.getValue() == false));
        this.green = this.register(new Setting<Object>("Green", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.rainbow.getValue() == false));
        this.blue = this.register(new Setting<Object>("Blue", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.rainbow.getValue() == false));
        this.alpha = this.register(new Setting<Integer>("Alpha", 255, 0, 255));
        this.hiddenRed = this.register(new Setting<Object>("Hidden Red", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.xqz.getValue() != false && this.rainbow.getValue() == false));
        this.hiddenGreen = this.register(new Setting<Object>("Hidden Green", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.xqz.getValue() != false && this.rainbow.getValue() == false));
        this.hiddenBlue = this.register(new Setting<Object>("Hidden Blue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.xqz.getValue() != false && this.rainbow.getValue() == false));
        this.hiddenAlpha = this.register(new Setting<Object>("Hidden Alpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.xqz.getValue() != false && this.rainbow.getValue() == false));
        this.scaleMap = new ConcurrentHashMap<EntityEnderCrystal, Float>();
        INSTANCE = this;
    }

    public void onRenderModel(RenderEntityModelEvent renderEntityModelEvent) {
        if (renderEntityModelEvent.getStage() != 0 || !(renderEntityModelEvent.entity instanceof EntityEnderCrystal) || !this.wireframe.getValue().booleanValue()) {
            return;
        }
        Color color = this.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : EntityUtil.getColor(renderEntityModelEvent.entity, this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue(), false);
        CrystalChams.mc.gameSettings.fancyGraphics = false;
        CrystalChams.mc.gameSettings.gammaSetting = 10000.0f;
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glPolygonMode((int)1032, (int)6913);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2896);
        if (this.wireframeThroughWalls.getValue().booleanValue()) {
            GL11.glDisable((int)2929);
        }
        GL11.glEnable((int)2848);
        GL11.glEnable((int)3042);
        GlStateManager.blendFunc((int)770, (int)771);
        GlStateManager.color((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        GlStateManager.glLineWidth((float)this.lineWidth.getValue().floatValue());
        renderEntityModelEvent.modelBase.render(renderEntityModelEvent.entity, renderEntityModelEvent.limbSwing, renderEntityModelEvent.limbSwingAmount, renderEntityModelEvent.age, renderEntityModelEvent.headYaw, renderEntityModelEvent.headPitch, renderEntityModelEvent.scale);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    @Override
    public void onUpdate() {
        if (CrystalChams.fullNullCheck()) {
            return;
        }
        for (Entity entity : CrystalChams.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityEnderCrystal)) continue;
            if (!this.scaleMap.containsKey((Object)entity)) {
                this.scaleMap.put((EntityEnderCrystal)entity, Float.valueOf(3.125E-4f));
            } else {
                this.scaleMap.put((EntityEnderCrystal)entity, Float.valueOf(this.scaleMap.get((Object)entity).floatValue() + 3.125E-4f));
            }
            if (!(this.scaleMap.get((Object)entity).floatValue() >= 0.0625f * this.scale.getValue().floatValue())) continue;
            this.scaleMap.remove((Object)entity);
        }
    }

    static {
        RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    }
}

