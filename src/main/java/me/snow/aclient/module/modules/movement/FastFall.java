//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 */
package me.snow.aclient.module.modules.movement;

import java.util.Arrays;
import java.util.List;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.movement.ElytraFlight;
import me.snow.aclient.module.modules.movement.Flight;
import me.snow.aclient.module.modules.movement.LongJump;
import me.snow.aclient.module.modules.movement.PacketFlight;
import me.snow.aclient.module.modules.player.Freecam;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class FastFall
extends Module {
    public /* synthetic */ Setting<Boolean> noLag;
    public /* synthetic */ Setting<Double> speed;
    /* synthetic */ List<Block> incelBlocks;
    public /* synthetic */ Setting<Double> height;

    boolean shouldReturn() {
        return FastFall.mc.player.isElytraFlying() || FastFall.isClipping() || EntityUtil.isInLiquid() || FastFall.mc.player.isOnLadder() || FastFall.mc.player.capabilities.isFlying || FastFall.mc.player.motionY > 0.0 || FastFall.mc.gameSettings.keyBindJump.isKeyDown() || FastFall.mc.player.isEntityInsideOpaqueBlock() || FastFall.mc.player.noClip || !FastFall.mc.player.onGround || Freecam.getInstance().isEnabled() || AliceMain.moduleManager.getModuleByClass(PacketFlight.class).isEnabled() || AliceMain.moduleManager.getModuleByClass(LongJump.class).isEnabled() || AliceMain.moduleManager.getModuleByClass(Flight.class).isEnabled() || AliceMain.moduleManager.getModuleByClass(ElytraFlight.class).isEnabled();
    }

    @Override
    public void onUpdate() {
        if (FastFall.fullNullCheck() || this.shouldReturn()) {
            return;
        }
        if (this.noLag.getValue().booleanValue() && AliceMain.packetManager.caughtPlayerPosLook()) {
            return;
        }
        RayTraceResult rayTraceResult = FastFall.mc.world.rayTraceBlocks(FastFall.mc.player.getPositionVector(), new Vec3d(FastFall.mc.player.posX, FastFall.mc.player.posY - this.height.getValue(), FastFall.mc.player.posZ), false, false, false);
        if (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK && FastFall.mc.world.getBlockState(new BlockPos(FastFall.mc.player.posX, FastFall.mc.player.posY - 0.1, FastFall.mc.player.posZ)).getBlock() != this.incelBlocks) {
            FastFall.mc.player.motionY = -this.speed.getValue().doubleValue();
        }
    }

    public FastFall() {
        super("FastFall", "Fast fall", Module.Category.MOVEMENT, true, false, false);
        this.speed = this.register(new Setting<Double>("Speed", 3.0, 0.1, 10.0));
        this.height = this.register(new Setting<Double>("Height", 5.0, 0.1, 20.0));
        this.noLag = this.register(new Setting<Boolean>("NoLag", true));
        this.incelBlocks = Arrays.asList(new Block[]{Blocks.BED, Blocks.SLIME_BLOCK});
    }

    public static boolean isClipping() {
        return !PlayerUtil.mc.world.getCollisionBoxes((Entity)PlayerUtil.mc.player, PlayerUtil.mc.player.getEntityBoundingBox()).isEmpty();
    }
}

