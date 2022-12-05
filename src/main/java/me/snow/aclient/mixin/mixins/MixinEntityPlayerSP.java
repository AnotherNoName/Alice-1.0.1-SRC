//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.entity.MoverType
 *  net.minecraft.stats.RecipeBook
 *  net.minecraft.stats.StatisticsManager
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package me.snow.aclient.mixin.mixins;

import me.snow.aclient.AliceMain;
import me.snow.aclient.event.events.ChatEvent;
import me.snow.aclient.event.events.DismountRidingEntityEvent;
import me.snow.aclient.event.events.MoveEvent;
import me.snow.aclient.event.events.PushEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.modules.movement.GroundSpeed;
import me.snow.aclient.module.modules.movement.Sprint;
import me.snow.aclient.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.MoverType;
import net.minecraft.stats.RecipeBook;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={EntityPlayerSP.class}, priority=9998)
public abstract class MixinEntityPlayerSP
extends AbstractClientPlayer {
    public MixinEntityPlayerSP(Minecraft minecraft, World world, NetHandlerPlayClient netHandlerPlayClient, StatisticsManager statisticsManager, RecipeBook recipeBook) {
        super(world, netHandlerPlayClient.getGameProfile());
    }

    @Inject(method={"sendChatMessage"}, at={@At(value="HEAD")}, cancellable=true)
    public void sendChatMessage(String string, CallbackInfo callbackInfo) {
        ChatEvent chatEvent = new ChatEvent(string);
        MinecraftForge.EVENT_BUS.post((Event)chatEvent);
    }

    @Inject(method={"dismountRidingEntity"}, at={@At(value="HEAD")}, cancellable=true)
    public void onDismountRidingEntity(CallbackInfo callbackInfo) {
        DismountRidingEntityEvent dismountRidingEntityEvent = DismountRidingEntityEvent.get();
        MinecraftForge.EVENT_BUS.post((Event)dismountRidingEntityEvent);
        if (dismountRidingEntityEvent.isCanceled()) {
            callbackInfo.cancel();
        }
    }

    @Redirect(method={"onLivingUpdate"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/entity/EntityPlayerSP;setSprinting(Z)V", ordinal=2))
    public void onLivingUpdate(EntityPlayerSP entityPlayerSP, boolean bl) {
        if (Sprint.getInstance().isOn() && Sprint.getInstance().mode.getValue() == Sprint.Mode.RAGE && (Util.mc.player.movementInput.field_192832_b != 0.0f || Util.mc.player.movementInput.moveStrafe != 0.0f)) {
            entityPlayerSP.setSprinting(true);
        } else {
            entityPlayerSP.setSprinting(bl);
        }
    }

    @Inject(method={"pushOutOfBlocks"}, at={@At(value="HEAD")}, cancellable=true)
    private void pushOutOfBlocksHook(double d, double d2, double d3, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        PushEvent pushEvent = new PushEvent(1);
        MinecraftForge.EVENT_BUS.post((Event)pushEvent);
        if (pushEvent.isCanceled()) {
            callbackInfoReturnable.setReturnValue(false);
        }
    }

    @Inject(method={"onUpdateWalkingPlayer"}, at={@At(value="HEAD")}, cancellable=true)
    private void preMotion(CallbackInfo callbackInfo) {
        UpdateWalkingPlayerEvent updateWalkingPlayerEvent = new UpdateWalkingPlayerEvent(0);
        MinecraftForge.EVENT_BUS.post((Event)updateWalkingPlayerEvent);
        if (updateWalkingPlayerEvent.isCanceled()) {
            callbackInfo.cancel();
        }
    }

    @Redirect(method={"onUpdateWalkingPlayer"}, at=@At(value="FIELD", target="net/minecraft/util/math/AxisAlignedBB.minY:D"))
    private double minYHook(AxisAlignedBB axisAlignedBB) {
        if (GroundSpeed.getInstance().isOn() && GroundSpeed.getInstance().mode.getValue() == GroundSpeed.Mode.VANILLA && GroundSpeed.getInstance().changeY) {
            GroundSpeed.getInstance().changeY = false;
            return GroundSpeed.getInstance().minY;
        }
        return axisAlignedBB.minY;
    }

    @Inject(method={"onUpdateWalkingPlayer"}, at={@At(value="RETURN")})
    private void postMotion(CallbackInfo callbackInfo) {
        UpdateWalkingPlayerEvent updateWalkingPlayerEvent = new UpdateWalkingPlayerEvent(1);
        MinecraftForge.EVENT_BUS.post((Event)updateWalkingPlayerEvent);
    }

    @Inject(method={"Lnet/minecraft/client/entity/EntityPlayerSP;setServerBrand(Ljava/lang/String;)V"}, at={@At(value="HEAD")})
    public void getBrand(String string, CallbackInfo callbackInfo) {
        if (AliceMain.serverManager != null) {
            AliceMain.serverManager.setServerBrand(string);
        }
    }

    @Redirect(method={"move"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/entity/AbstractClientPlayer;move(Lnet/minecraft/entity/MoverType;DDD)V"))
    public void move(AbstractClientPlayer abstractClientPlayer, MoverType moverType, double d, double d2, double d3) {
        MoveEvent moveEvent = new MoveEvent(0, moverType, d, d2, d3);
        MinecraftForge.EVENT_BUS.post((Event)moveEvent);
        if (!moveEvent.isCanceled()) {
            super.moveEntity(moveEvent.getType(), moveEvent.getX(), moveEvent.getY(), moveEvent.getZ());
        }
    }
}

