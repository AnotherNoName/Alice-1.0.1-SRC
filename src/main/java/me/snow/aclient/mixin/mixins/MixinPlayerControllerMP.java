//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemFood
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package me.snow.aclient.mixin.mixins;

import me.snow.aclient.AliceMain;
import me.snow.aclient.event.events.BlockEvent;
import me.snow.aclient.event.events.ProcessRightClickBlockEvent;
import me.snow.aclient.module.modules.misc.PacketEat;
import me.snow.aclient.module.modules.player.Reach;
import me.snow.aclient.module.modules.player.TpsSync;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={PlayerControllerMP.class})
public abstract class MixinPlayerControllerMP {
    @Shadow
    public int currentPlayerItem;

    @Redirect(method={"onPlayerDamageBlock"}, at=@At(value="INVOKE", target="Lnet/minecraft/block/state/IBlockState;getPlayerRelativeBlockHardness(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)F"))
    public float getPlayerRelativeBlockHardnessHook(IBlockState iBlockState, EntityPlayer entityPlayer, World world, BlockPos blockPos) {
        return iBlockState.getPlayerRelativeBlockHardness(entityPlayer, world, blockPos) * (TpsSync.getInstance().isOn() && TpsSync.getInstance().mining.getValue() != false ? 1.0f / AliceMain.serverManager.getTpsFactor() : 1.0f);
    }

    @Shadow
    public abstract void syncCurrentPlayItem();

    @Inject(method={"clickBlock"}, at={@At(value="HEAD")}, cancellable=true)
    private void clickBlockHook(BlockPos blockPos, EnumFacing enumFacing, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        BlockEvent blockEvent = new BlockEvent(3, blockPos, enumFacing);
        MinecraftForge.EVENT_BUS.post((Event)blockEvent);
    }

    @Inject(method={"onPlayerDamageBlock"}, at={@At(value="HEAD")}, cancellable=true)
    private void onPlayerDamageBlockHook(BlockPos blockPos, EnumFacing enumFacing, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        BlockEvent blockEvent = new BlockEvent(4, blockPos, enumFacing);
        MinecraftForge.EVENT_BUS.post((Event)blockEvent);
    }

    @Inject(method={"onStoppedUsingItem"}, at={@At(value="HEAD")}, cancellable=true)
    public void onStoppedUsingItem(EntityPlayer entityPlayer, CallbackInfo callbackInfo) {
        if (entityPlayer.getHeldItem(entityPlayer.getActiveHand()).getItem() instanceof ItemFood && AliceMain.moduleManager.getModuleByClass(PacketEat.class).isEnabled()) {
            this.syncCurrentPlayItem();
            entityPlayer.stopActiveHand();
            callbackInfo.cancel();
        }
    }

    @Inject(method={"getBlockReachDistance"}, at={@At(value="RETURN")}, cancellable=true)
    private void getReachDistanceHook(CallbackInfoReturnable<Float> callbackInfoReturnable) {
        if (Reach.getInstance().isOn()) {
            float f = callbackInfoReturnable.getReturnValue().floatValue();
            callbackInfoReturnable.setReturnValue(Reach.getInstance().override.getValue() != false ? Reach.getInstance().reach.getValue() : Float.valueOf(f + Reach.getInstance().add.getValue().floatValue()));
        }
    }

    @Inject(method={"processRightClickBlock"}, at={@At(value="HEAD")}, cancellable=true)
    public void processRightClickBlock(EntityPlayerSP entityPlayerSP, WorldClient worldClient, BlockPos blockPos, EnumFacing enumFacing, Vec3d vec3d, EnumHand enumHand, CallbackInfoReturnable<EnumActionResult> callbackInfoReturnable) {
        ProcessRightClickBlockEvent processRightClickBlockEvent = new ProcessRightClickBlockEvent(blockPos, enumHand, Minecraft.getMinecraft().player.getHeldItem(enumHand));
        MinecraftForge.EVENT_BUS.post((Event)processRightClickBlockEvent);
        if (processRightClickBlockEvent.isCanceled()) {
            callbackInfoReturnable.cancel();
        }
    }
}

