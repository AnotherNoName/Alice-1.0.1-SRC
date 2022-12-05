//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.ChunkRenderContainer
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package me.snow.aclient.mixin.mixins;

import me.snow.aclient.event.events.BlockBreakingEvent;
import me.snow.aclient.module.modules.movement.GroundSpeed;
import net.minecraft.client.renderer.ChunkRenderContainer;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={RenderGlobal.class})
public abstract class MixinRenderGlobal {
    @Redirect(method={"setupTerrain"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/renderer/ChunkRenderContainer;initialize(DDD)V"))
    public void initializeHook(ChunkRenderContainer chunkRenderContainer, double d, double d2, double d3) {
        double d4 = d2;
        if (GroundSpeed.getInstance().isOn() && GroundSpeed.getInstance().noShake.getValue().booleanValue() && GroundSpeed.getInstance().mode.getValue() != GroundSpeed.Mode.INSTANT && GroundSpeed.getInstance().antiShake) {
            d4 = GroundSpeed.getInstance().startY;
        }
        chunkRenderContainer.initialize(d, d4, d3);
    }

    @Redirect(method={"renderEntities"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/renderer/entity/RenderManager;setRenderPosition(DDD)V"))
    public void setRenderPositionHook(RenderManager renderManager, double d, double d2, double d3) {
        double d4 = d2;
        if (GroundSpeed.getInstance().isOn() && GroundSpeed.getInstance().noShake.getValue().booleanValue() && GroundSpeed.getInstance().mode.getValue() != GroundSpeed.Mode.INSTANT && GroundSpeed.getInstance().antiShake) {
            d4 = GroundSpeed.getInstance().startY;
        }
        TileEntityRendererDispatcher.staticPlayerY = d4;
        renderManager.setRenderPosition(d, d4, d3);
    }

    @Redirect(method={"drawSelectionBox"}, at=@At(value="INVOKE", target="Lnet/minecraft/util/math/AxisAlignedBB;offset(DDD)Lnet/minecraft/util/math/AxisAlignedBB;"))
    public AxisAlignedBB offsetHook(AxisAlignedBB axisAlignedBB, double d, double d2, double d3) {
        if (GroundSpeed.getInstance().isOn() && GroundSpeed.getInstance().noShake.getValue().booleanValue() && GroundSpeed.getInstance().mode.getValue() != GroundSpeed.Mode.INSTANT) {
            GroundSpeed.getInstance();
        }
        return axisAlignedBB.offset(d, d2, d3);
    }

    @Inject(method={"sendBlockBreakProgress"}, at={@At(value="HEAD")})
    public void sendBlockBreakProgress(int n, BlockPos blockPos, int n2, CallbackInfo callbackInfo) {
        BlockBreakingEvent blockBreakingEvent = new BlockBreakingEvent(blockPos, n, n2);
        MinecraftForge.EVENT_BUS.post((Event)blockBreakingEvent);
    }
}

