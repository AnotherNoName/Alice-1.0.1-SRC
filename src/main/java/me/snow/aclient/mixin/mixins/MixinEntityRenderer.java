//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  javax.annotation.Nullable
 *  javax.vecmath.Vector3f
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 *  org.lwjgl.util.glu.Project
 */
package me.snow.aclient.mixin.mixins;

import com.google.common.base.Predicate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.vecmath.Vector3f;
import me.snow.aclient.AliceMain;
import me.snow.aclient.event.events.PerspectiveEvent;
import me.snow.aclient.module.modules.client.Notifications;
import me.snow.aclient.module.modules.player.NoEntityTrace;
import me.snow.aclient.module.modules.render.AntiFog;
import me.snow.aclient.module.modules.render.NoRender;
import me.snow.aclient.module.modules.render.ViewClip;
import me.snow.aclient.module.modules.render.Weather;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.lwjgl.util.glu.Project;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={EntityRenderer.class})
public abstract class MixinEntityRenderer {
    @Shadow
    public ItemStack field_190566_ab;
    @Shadow
    @Final
    public Minecraft mc;
    private boolean injection = true;
    @Final
    private int[] lightmapColors;

    @Shadow
    public abstract void getMouseOver(float var1);

    private Vector3f mix(Vector3f vector3f, Vector3f vector3f2, float f) {
        return new Vector3f(vector3f.x * (1.0f - f) + vector3f2.x * f, vector3f.y * (1.0f - f) + vector3f2.y * f, vector3f.z * (1.0f - f) + vector3f.z * f);
    }

    @Inject(method={"renderItemActivation"}, at={@At(value="HEAD")}, cancellable=true)
    public void renderItemActivationHook(CallbackInfo callbackInfo) {
        if (this.field_190566_ab != null && NoRender.getInstance().isOn() && NoRender.getInstance().totemPops.getValue().booleanValue() && this.field_190566_ab.getItem() == Items.field_190929_cY) {
            callbackInfo.cancel();
        }
    }

    @Inject(method={"renderWorldPass"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/renderer/EntityRenderer;renderRainSnow(F)V")})
    public void weatherHook(int n, float f, long l, CallbackInfo callbackInfo) {
        if (AliceMain.moduleManager.getModuleByClass(Weather.class).isOn()) {
            AliceMain.moduleManager.getModuleByClass(Weather.class).render(f);
        }
    }

    @Inject(method={"getMouseOver(F)V"}, at={@At(value="HEAD")}, cancellable=true)
    public void getMouseOverHook(float f, CallbackInfo callbackInfo) {
        if (this.injection) {
            block3: {
                callbackInfo.cancel();
                this.injection = false;
                try {
                    this.getMouseOver(f);
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                    if (!Notifications.getInstance().isOn()) break block3;
                    Notifications.displayCrash(exception);
                }
            }
            this.injection = true;
        }
    }

    @Redirect(method={"setupCameraTransform"}, at=@At(value="FIELD", target="Lnet/minecraft/client/entity/EntityPlayerSP;prevTimeInPortal:F"))
    public float prevTimeInPortalHook(EntityPlayerSP entityPlayerSP) {
        if (NoRender.getInstance().isOn() && NoRender.getInstance().nausea.getValue().booleanValue()) {
            return -3.4028235E38f;
        }
        return entityPlayerSP.prevTimeInPortal;
    }

    @Redirect(method={"setupCameraTransform"}, at=@At(value="INVOKE", target="Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"))
    private void onSetupCameraTransform(float f, float f2, float f3, float f4) {
        PerspectiveEvent perspectiveEvent = new PerspectiveEvent((float)this.mc.displayWidth / (float)this.mc.displayHeight);
        MinecraftForge.EVENT_BUS.post((Event)perspectiveEvent);
        Project.gluPerspective((float)f, (float)perspectiveEvent.getAspect(), (float)f3, (float)f4);
    }

    @Redirect(method={"renderWorldPass"}, at=@At(value="INVOKE", target="Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"))
    private void onRenderWorldPass(float f, float f2, float f3, float f4) {
        PerspectiveEvent perspectiveEvent = new PerspectiveEvent((float)this.mc.displayWidth / (float)this.mc.displayHeight);
        MinecraftForge.EVENT_BUS.post((Event)perspectiveEvent);
        Project.gluPerspective((float)f, (float)perspectiveEvent.getAspect(), (float)f3, (float)f4);
    }

    @Redirect(method={"renderCloudsCheck"}, at=@At(value="INVOKE", target="Lorg/lwjgl/util/glu/Project;gluPerspective(FFFF)V"))
    private void onRenderCloudsCheck(float f, float f2, float f3, float f4) {
        PerspectiveEvent perspectiveEvent = new PerspectiveEvent((float)this.mc.displayWidth / (float)this.mc.displayHeight);
        MinecraftForge.EVENT_BUS.post((Event)perspectiveEvent);
        Project.gluPerspective((float)f, (float)perspectiveEvent.getAspect(), (float)f3, (float)f4);
    }

    @Inject(method={"setupFog"}, at={@At(value="TAIL")})
    private void fogHook(int n, float f, CallbackInfo callbackInfo) {
        if (AliceMain.moduleManager.getModuleByClass(AntiFog.class).isEnabled()) {
            GlStateManager.disableFog();
        }
    }

    @Inject(method={"hurtCameraEffect"}, at={@At(value="HEAD")}, cancellable=true)
    public void hurtCameraEffectHook(float f, CallbackInfo callbackInfo) {
        if (NoRender.getInstance().isOn() && NoRender.getInstance().hurtcam.getValue().booleanValue()) {
            callbackInfo.cancel();
        }
    }

    @Redirect(method={"getMouseOver"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/multiplayer/WorldClient;getEntitiesInAABBexcluding(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;Lcom/google/common/base/Predicate;)Ljava/util/List;"))
    public List<Entity> getEntitiesInAABBexcludingHook(WorldClient worldClient, @Nullable Entity entity, AxisAlignedBB axisAlignedBB, @Nullable Predicate<? super Entity> predicate) {
        if (NoEntityTrace.getINSTANCE().isOn() && NoEntityTrace.getINSTANCE().noTrace) {
            return new ArrayList<Entity>();
        }
        return worldClient.getEntitiesInAABBexcluding(entity, axisAlignedBB, predicate);
    }

    @ModifyVariable(method={"orientCamera"}, ordinal=3, at=@At(value="STORE", ordinal=0), require=1)
    public double changeCameraDistanceHook(double d) {
        return ViewClip.getInstance().isEnabled() && ViewClip.getInstance().extend.getValue() != false ? ViewClip.getInstance().distance.getValue() : d;
    }

    @ModifyVariable(method={"orientCamera"}, ordinal=7, at=@At(value="STORE", ordinal=0), require=1)
    public double orientCameraHook(double d) {
        return ViewClip.getInstance().isEnabled() && ViewClip.getInstance().extend.getValue() != false ? ViewClip.getInstance().distance.getValue() : (ViewClip.getInstance().isEnabled() && ViewClip.getInstance().extend.getValue() == false ? 4.0 : d);
    }
}

