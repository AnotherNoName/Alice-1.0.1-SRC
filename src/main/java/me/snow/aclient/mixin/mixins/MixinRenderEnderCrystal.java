//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelEnderCrystal
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderEnderCrystal
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.mixin.mixins;

import java.awt.Color;
import javax.annotation.Nullable;
import me.snow.aclient.event.events.RenderEntityModelEvent;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.module.modules.render.CrystalChams;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.RenderUtil;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEnderCrystal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={RenderEnderCrystal.class})
public class MixinRenderEnderCrystal {
    private static final ResourceLocation glint = new ResourceLocation("textures/glint.png");
    @Shadow
    @Final
    private static ResourceLocation ENDER_CRYSTAL_TEXTURES;
    @Shadow
    private final ModelBase modelEnderCrystal = new ModelEnderCrystal(0.0f, true);
    @Shadow
    private final ModelBase modelEnderCrystalNoBase = new ModelEnderCrystal(0.0f, false);

    @Redirect(method={"doRender"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
    public void renderModelBaseHook(ModelBase modelBase, Entity entity, float f, float f2, float f3, float f4, float f5, float f6) {
        Object object;
        if (CrystalChams.INSTANCE.isEnabled()) {
            if (CrystalChams.INSTANCE.animateScale.getValue().booleanValue() && CrystalChams.INSTANCE.scaleMap.containsKey((Object)entity)) {
                GlStateManager.scale((float)CrystalChams.INSTANCE.scaleMap.get((Object)entity).floatValue(), (float)CrystalChams.INSTANCE.scaleMap.get((Object)entity).floatValue(), (float)CrystalChams.INSTANCE.scaleMap.get((Object)entity).floatValue());
            } else {
                GlStateManager.scale((float)CrystalChams.INSTANCE.scale.getValue().floatValue(), (float)CrystalChams.INSTANCE.scale.getValue().floatValue(), (float)CrystalChams.INSTANCE.scale.getValue().floatValue());
            }
        }
        if (CrystalChams.INSTANCE.isEnabled() && CrystalChams.INSTANCE.wireframe.getValue().booleanValue()) {
            object = new RenderEntityModelEvent(0, modelBase, entity, f, f2, f3, f4, f5, f6);
            CrystalChams.INSTANCE.onRenderModel((RenderEntityModelEvent)((Object)object));
        }
        if (CrystalChams.INSTANCE.isEnabled() && CrystalChams.INSTANCE.chams.getValue().booleanValue()) {
            GL11.glPushAttrib((int)1048575);
            GL11.glDisable((int)3008);
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glLineWidth((float)1.5f);
            GL11.glEnable((int)2960);
            if (CrystalChams.INSTANCE.rainbow.getValue().booleanValue()) {
                object = CrystalChams.INSTANCE.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(RenderUtil.getRainbow(CrystalChams.INSTANCE.speed.getValue() * 100, 0, (float)CrystalChams.INSTANCE.saturation.getValue().intValue() / 100.0f, (float)CrystalChams.INSTANCE.brightness.getValue().intValue() / 100.0f));
                Color color = EntityUtil.getColor(entity, ((Color)object).getRed(), ((Color)object).getGreen(), ((Color)object).getBlue(), CrystalChams.INSTANCE.alpha.getValue(), true);
                if (CrystalChams.INSTANCE.throughWalls.getValue().booleanValue()) {
                    GL11.glDisable((int)2929);
                    GL11.glDepthMask((boolean)false);
                }
                GL11.glEnable((int)10754);
                GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)CrystalChams.INSTANCE.alpha.getValue().intValue() / 255.0f));
                modelBase.render(entity, f, f2, f3, f4, f5, f6);
                if (CrystalChams.INSTANCE.throughWalls.getValue().booleanValue()) {
                    GL11.glEnable((int)2929);
                    GL11.glDepthMask((boolean)true);
                }
            } else if (CrystalChams.INSTANCE.xqz.getValue().booleanValue() && CrystalChams.INSTANCE.throughWalls.getValue().booleanValue()) {
                Color color = CrystalChams.INSTANCE.colorSync.getValue() != false ? EntityUtil.getColor(entity, CrystalChams.INSTANCE.hiddenRed.getValue(), CrystalChams.INSTANCE.hiddenGreen.getValue(), CrystalChams.INSTANCE.hiddenBlue.getValue(), CrystalChams.INSTANCE.hiddenAlpha.getValue(), true) : EntityUtil.getColor(entity, CrystalChams.INSTANCE.hiddenRed.getValue(), CrystalChams.INSTANCE.hiddenGreen.getValue(), CrystalChams.INSTANCE.hiddenBlue.getValue(), CrystalChams.INSTANCE.hiddenAlpha.getValue(), true);
                Object object2 = object = CrystalChams.INSTANCE.colorSync.getValue() != false ? EntityUtil.getColor(entity, CrystalChams.INSTANCE.red.getValue(), CrystalChams.INSTANCE.green.getValue(), CrystalChams.INSTANCE.blue.getValue(), CrystalChams.INSTANCE.alpha.getValue(), true) : EntityUtil.getColor(entity, CrystalChams.INSTANCE.red.getValue(), CrystalChams.INSTANCE.green.getValue(), CrystalChams.INSTANCE.blue.getValue(), CrystalChams.INSTANCE.alpha.getValue(), true);
                if (CrystalChams.INSTANCE.throughWalls.getValue().booleanValue()) {
                    GL11.glDisable((int)2929);
                    GL11.glDepthMask((boolean)false);
                }
                GL11.glEnable((int)10754);
                GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)CrystalChams.INSTANCE.alpha.getValue().intValue() / 255.0f));
                modelBase.render(entity, f, f2, f3, f4, f5, f6);
                if (CrystalChams.INSTANCE.throughWalls.getValue().booleanValue()) {
                    GL11.glEnable((int)2929);
                    GL11.glDepthMask((boolean)true);
                }
                GL11.glColor4f((float)((float)((Color)object).getRed() / 255.0f), (float)((float)((Color)object).getGreen() / 255.0f), (float)((float)((Color)object).getBlue() / 255.0f), (float)((float)CrystalChams.INSTANCE.alpha.getValue().intValue() / 255.0f));
                modelBase.render(entity, f, f2, f3, f4, f5, f6);
            } else {
                Object object3 = object = CrystalChams.INSTANCE.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : EntityUtil.getColor(entity, CrystalChams.INSTANCE.red.getValue(), CrystalChams.INSTANCE.green.getValue(), CrystalChams.INSTANCE.blue.getValue(), CrystalChams.INSTANCE.alpha.getValue(), true);
                if (CrystalChams.INSTANCE.throughWalls.getValue().booleanValue()) {
                    GL11.glDisable((int)2929);
                    GL11.glDepthMask((boolean)false);
                }
                GL11.glEnable((int)10754);
                GL11.glColor4f((float)((float)((Color)object).getRed() / 255.0f), (float)((float)((Color)object).getGreen() / 255.0f), (float)((float)((Color)object).getBlue() / 255.0f), (float)((float)CrystalChams.INSTANCE.alpha.getValue().intValue() / 255.0f));
                modelBase.render(entity, f, f2, f3, f4, f5, f6);
                if (CrystalChams.INSTANCE.throughWalls.getValue().booleanValue()) {
                    GL11.glEnable((int)2929);
                    GL11.glDepthMask((boolean)true);
                }
            }
            GL11.glEnable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
            GL11.glEnable((int)3008);
            GL11.glPopAttrib();
            if (CrystalChams.INSTANCE.glint.getValue().booleanValue()) {
                GL11.glDisable((int)2929);
                GL11.glDepthMask((boolean)false);
                GlStateManager.enableAlpha();
                GlStateManager.color((float)1.0f, (float)0.0f, (float)0.0f, (float)0.13f);
                modelBase.render(entity, f, f2, f3, f4, f5, f6);
                GlStateManager.disableAlpha();
                GL11.glEnable((int)2929);
                GL11.glDepthMask((boolean)true);
            }
        } else {
            modelBase.render(entity, f, f2, f3, f4, f5, f6);
        }
        if (CrystalChams.INSTANCE.isEnabled()) {
            if (CrystalChams.INSTANCE.animateScale.getValue().booleanValue() && CrystalChams.INSTANCE.scaleMap.containsKey((Object)entity)) {
                GlStateManager.scale((float)(1.0f / CrystalChams.INSTANCE.scaleMap.get((Object)entity).floatValue()), (float)(1.0f / CrystalChams.INSTANCE.scaleMap.get((Object)entity).floatValue()), (float)(1.0f / CrystalChams.INSTANCE.scaleMap.get((Object)entity).floatValue()));
            } else {
                GlStateManager.scale((float)(1.0f / CrystalChams.INSTANCE.scale.getValue().floatValue()), (float)(1.0f / CrystalChams.INSTANCE.scale.getValue().floatValue()), (float)(1.0f / CrystalChams.INSTANCE.scale.getValue().floatValue()));
            }
        }
    }

    @Nullable
    protected ResourceLocation getEntityTexture(EntityEnderCrystal entityEnderCrystal) {
        return null;
    }
}

