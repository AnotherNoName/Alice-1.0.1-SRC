//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.mixin.mixins;

import java.awt.Color;
import me.snow.aclient.AliceMain;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.module.modules.render.Chams;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={RenderLivingBase.class})
public abstract class MixinRenderLivingBase<T extends EntityLivingBase>
extends Render<T> {
    private static final ResourceLocation glint = new ResourceLocation("textures/shinechams.png");
    @Shadow
    protected ModelBase mainModel;
    @Shadow
    protected boolean renderMarker;
    float red;
    float green;
    float blue;

    public MixinRenderLivingBase(RenderManager renderManager, ModelBase modelBase, float f) {
        super(renderManager);
    }

    @Overwrite
    public void doRender(T t, double d, double d2, double d3, float f, float f2) {
        if (!MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Pre(t, (RenderLivingBase)RenderLivingBase.class.cast((Object)this), f2, d, d2, d3))) {
            boolean bl;
            GlStateManager.pushMatrix();
            GlStateManager.disableCull();
            this.mainModel.swingProgress = this.getSwingProgress(t, f2);
            this.mainModel.isRiding = bl = t.isRiding() && t.getRidingEntity() != null && t.getRidingEntity().shouldRiderSit();
            this.mainModel.isChild = t.isChild();
            try {
                float f3;
                float f4 = this.interpolateRotation(((EntityLivingBase)t).prevRenderYawOffset, ((EntityLivingBase)t).renderYawOffset, f2);
                float f5 = this.interpolateRotation(((EntityLivingBase)t).prevRotationYawHead, ((EntityLivingBase)t).rotationYawHead, f2);
                float f6 = f5 - f4;
                if (bl && t.getRidingEntity() instanceof EntityLivingBase) {
                    EntityLivingBase entityLivingBase = (EntityLivingBase)t.getRidingEntity();
                    f4 = this.interpolateRotation(entityLivingBase.prevRenderYawOffset, entityLivingBase.renderYawOffset, f2);
                    f6 = f5 - f4;
                    f3 = MathHelper.wrapDegrees((float)f6);
                    if (f3 < -85.0f) {
                        f3 = -85.0f;
                    }
                    if (f3 >= 85.0f) {
                        f3 = 85.0f;
                    }
                    f4 = f5 - f3;
                    if (f3 * f3 > 2500.0f) {
                        f4 += f3 * 0.2f;
                    }
                    f6 = f5 - f4;
                }
                float f7 = ((EntityLivingBase)t).prevRotationPitch + (((EntityLivingBase)t).rotationPitch - ((EntityLivingBase)t).prevRotationPitch) * f2;
                this.renderLivingAt(t, d, d2, d3);
                f3 = this.handleRotationFloat(t, f2);
                this.rotateCorpse(t, f3, f4, f2);
                float f8 = this.prepareScale(t, f2);
                float f9 = 0.0f;
                float f10 = 0.0f;
                if (!t.isRiding()) {
                    f9 = ((EntityLivingBase)t).prevLimbSwingAmount + (((EntityLivingBase)t).limbSwingAmount - ((EntityLivingBase)t).prevLimbSwingAmount) * f2;
                    f10 = ((EntityLivingBase)t).limbSwing - ((EntityLivingBase)t).limbSwingAmount * (1.0f - f2);
                    if (t.isChild()) {
                        f10 *= 3.0f;
                    }
                    if (f9 > 1.0f) {
                        f9 = 1.0f;
                    }
                    f6 = f5 - f4;
                }
                GlStateManager.enableAlpha();
                this.mainModel.setLivingAnimations(t, f10, f9, f2);
                this.mainModel.setRotationAngles(f10, f9, f3, f6, f7, f8, t);
                if (this.renderOutlines) {
                    boolean bl2 = this.setScoreTeamColor(t);
                    GlStateManager.enableColorMaterial();
                    GlStateManager.enableOutlineMode((int)this.getTeamColor((Entity)t));
                    if (!this.renderMarker) {
                        this.renderModel(t, f10, f9, f3, f6, f7, f8);
                    }
                    if (!(t instanceof EntityPlayer) || !((EntityPlayer)t).isSpectator()) {
                        this.renderLayers(t, f10, f9, f2, f3, f6, f7, f8);
                    }
                    GlStateManager.disableOutlineMode();
                    GlStateManager.disableColorMaterial();
                    if (bl2) {
                        this.unsetScoreTeamColor();
                    }
                } else {
                    if (Chams.getInstance().isOn() && t instanceof EntityPlayer && Chams.getInstance().solidawa.getValue().booleanValue()) {
                        Color color = Chams.getInstance().colorsync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(Chams.getInstance().red.getValue(), Chams.getInstance().green.getValue(), Chams.getInstance().blue.getValue(), Chams.getInstance().alpha.getValue().intValue());
                        GlStateManager.pushMatrix();
                        GL11.glPushAttrib((int)1048575);
                        GL11.glDisable((int)3553);
                        GL11.glDisable((int)2896);
                        GL11.glEnable((int)2848);
                        GL11.glEnable((int)3042);
                        GL11.glBlendFunc((int)770, (int)771);
                        GL11.glDisable((int)2929);
                        GL11.glDepthMask((boolean)false);
                        if (AliceMain.friendManager.isFriend(t.getName()) || t == Minecraft.getMinecraft().player) {
                            GL11.glColor4f((float)((float)Chams.getInstance().Fred.getValue().intValue() / 255.0f), (float)((float)Chams.getInstance().Fgreen.getValue().intValue() / 255.0f), (float)((float)Chams.getInstance().Fblue.getValue().intValue() / 255.0f), (float)(Chams.getInstance().alpha.getValue().floatValue() / 255.0f));
                        } else {
                            GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)(Chams.getInstance().alpha.getValue().floatValue() / 255.0f));
                        }
                        this.renderModel(t, f10, f9, f3, f6, f7, f8);
                        GL11.glDisable((int)2896);
                        GL11.glEnable((int)2929);
                        GL11.glDepthMask((boolean)true);
                        if (AliceMain.friendManager.isFriend(t.getName()) || t == Minecraft.getMinecraft().player) {
                            GL11.glColor4f((float)((float)Chams.getInstance().Fred.getValue().intValue() / 255.0f), (float)((float)Chams.getInstance().Fgreen.getValue().intValue() / 255.0f), (float)((float)Chams.getInstance().Fblue.getValue().intValue() / 255.0f), (float)(Chams.getInstance().alpha.getValue().floatValue() / 255.0f));
                        } else {
                            GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)(Chams.getInstance().alpha.getValue().floatValue() / 255.0f));
                        }
                        this.renderModel(t, f10, f9, f3, f6, f7, f8);
                        GL11.glEnable((int)2896);
                        GlStateManager.popAttrib();
                        GlStateManager.popMatrix();
                    }
                    boolean bl3 = this.setDoRenderBrightness(t, f2);
                    if (!(t instanceof EntityPlayer) || Chams.getInstance().isOn() && Chams.getInstance().wireframeawa.getValue().booleanValue() && Chams.getInstance().playerModel.getValue().booleanValue() || Chams.getInstance().isOff()) {
                        this.renderModel(t, f10, f9, f3, f6, f7, f8);
                    }
                    if (bl3) {
                        this.unsetBrightness();
                    }
                    GlStateManager.depthMask((boolean)true);
                    if (!(t instanceof EntityPlayer) || !((EntityPlayer)t).isSpectator()) {
                        this.renderLayers(t, f10, f9, f2, f3, f6, f7, f8);
                    }
                    if (Chams.getInstance().isOn() && t instanceof EntityPlayer && Chams.getInstance().wireframeawa.getValue().booleanValue()) {
                        Color color = Chams.getInstance().colorsync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(Chams.getInstance().red.getValue(), Chams.getInstance().green.getValue(), Chams.getInstance().blue.getValue(), Chams.getInstance().alpha.getValue().intValue());
                        GlStateManager.pushMatrix();
                        GL11.glPushAttrib((int)1048575);
                        GL11.glPolygonMode((int)1032, (int)6913);
                        GL11.glDisable((int)3553);
                        GL11.glDisable((int)2896);
                        GL11.glDisable((int)2929);
                        GL11.glEnable((int)2848);
                        GL11.glEnable((int)3042);
                        GL11.glBlendFunc((int)770, (int)771);
                        if (AliceMain.friendManager.isFriend(t.getName()) || t == Minecraft.getMinecraft().player) {
                            GL11.glColor4f((float)((float)Chams.getInstance().Fred.getValue().intValue() / 255.0f), (float)((float)Chams.getInstance().Fgreen.getValue().intValue() / 255.0f), (float)((float)Chams.getInstance().Fblue.getValue().intValue() / 255.0f), (float)(Chams.getInstance().alpha.getValue().floatValue() / 255.0f));
                        } else {
                            GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)(Chams.getInstance().alpha.getValue().floatValue() / 255.0f));
                        }
                        GL11.glLineWidth((float)Chams.getInstance().lineWidth.getValue().floatValue());
                        this.renderModel(t, f10, f9, f3, f6, f7, f8);
                        GL11.glEnable((int)2896);
                        GlStateManager.popAttrib();
                        GlStateManager.popMatrix();
                    }
                }
                GlStateManager.disableRescaleNormal();
            }
            catch (Exception exception) {
                AliceMain.LOGGER.error("Couldn't render entity", (Throwable)exception);
            }
            GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
            GlStateManager.enableTexture2D();
            GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
            GlStateManager.enableCull();
            GlStateManager.popMatrix();
            super.doRender(t, d, d2, d3, f, f2);
            MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Post(t, (RenderLivingBase)RenderLivingBase.class.cast((Object)this), f2, d, d2, d3));
        }
    }

    @Shadow
    protected abstract boolean func_193115_c(EntityLivingBase var1);

    @Shadow
    protected abstract float getSwingProgress(T var1, float var2);

    @Shadow
    protected abstract float interpolateRotation(float var1, float var2, float var3);

    @Shadow
    protected abstract float handleRotationFloat(T var1, float var2);

    @Shadow
    protected abstract void rotateCorpse(T var1, float var2, float var3, float var4);

    @Shadow
    public abstract float prepareScale(T var1, float var2);

    @Shadow
    protected abstract void unsetScoreTeamColor();

    @Shadow
    protected abstract boolean setScoreTeamColor(T var1);

    @Shadow
    protected abstract void renderLivingAt(T var1, double var2, double var4, double var6);

    @Shadow
    protected abstract void unsetBrightness();

    @Shadow
    protected abstract void renderModel(T var1, float var2, float var3, float var4, float var5, float var6, float var7);

    @Shadow
    protected abstract void renderLayers(T var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8);

    @Shadow
    protected abstract boolean setDoRenderBrightness(T var1, float var2);
}

