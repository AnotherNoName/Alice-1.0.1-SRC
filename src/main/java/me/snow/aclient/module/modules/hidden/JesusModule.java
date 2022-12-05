//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.hidden;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.JesusEvent;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.mixin.mixins.accessors.ICPacketPlayer;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.movement.Jesus;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class JesusModule
extends Module {
    private final /* synthetic */ Timer offsetTimer;
    private /* synthetic */ double floatOffset;
    public static /* synthetic */ JesusModule INSTANCE;
    private /* synthetic */ double ncpStrictY;
    public /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ int floatUpTimer;
    private /* synthetic */ int floatTicks;

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketPlayer && ((ICPacketPlayer)send.getPacket()).isMoving() && !JesusModule.isInLiquid() && this.isStandingOnLiquid()) {
            ((ICPacketPlayer)send.getPacket()).setOnGround(false);
            if (this.mode.getValue().equals((Object)Mode.SOLID)) {
                if (this.offsetTimer.passedMs(50L)) {
                    this.floatOffset = 0.0;
                    this.offsetTimer.reset();
                } else {
                    this.floatOffset = 0.2;
                }
            } else if (this.mode.getValue().equals((Object)Mode.SOLID_STRICT)) {
                ((ICPacketPlayer)send.getPacket()).setY(((CPacketPlayer)send.getPacket()).getY(JesusModule.mc.player.posY) - this.floatOffset);
                this.floatOffset += 0.12;
                if (this.floatOffset > 0.4) {
                    this.floatOffset = 0.2;
                }
            }
        }
    }

    private boolean isAboveLiquid() {
        for (double d = 0.0; d < 1.0; d += 0.1) {
            BlockPos blockPos = new BlockPos(JesusModule.mc.player.posX, JesusModule.mc.player.posY - d, JesusModule.mc.player.posZ);
            if (!(JesusModule.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid)) continue;
            return true;
        }
        return false;
    }

    @SubscribeEvent
    public void onBoundingBoxCollision(JesusEvent jesusEvent) {
        if (jesusEvent.getStage() == 0 && JesusModule.nullCheck() && (this.mode.getValue() == Mode.SOLID || this.mode.getValue() == Mode.SOLID_STRICT) && JesusModule.mc.world != null && JesusModule.mc.player != null && EntityUtil.checkCollide() && !(Jesus.mc.player.motionY >= (double)0.1f) && (double)jesusEvent.getPos().getY() < Jesus.mc.player.posY - (double)0.05f && (this.mode.getValue().equals((Object)Mode.SOLID) || this.mode.getValue().equals((Object)Mode.SOLID_STRICT))) {
            if (JesusModule.isInLiquid() || !this.isStandingOnLiquid()) {
                return;
            }
            if (JesusModule.mc.player.isBurning()) {
                return;
            }
            if (JesusModule.mc.player.fallDistance > 3.0f || JesusModule.mc.player.isSneaking() || JesusModule.mc.player.isRowingBoat()) {
                return;
            }
            if (Jesus.mc.player.getRidingEntity() != null) {
                jesusEvent.setBoundingBox(new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, (double)0.95f, 1.0));
            } else {
                jesusEvent.setBoundingBox(Block.FULL_BLOCK_AABB);
            }
            if (this.mode.getValue().equals((Object)Mode.SOLID_STRICT)) {
                // empty if block
            }
        }
        jesusEvent.setCanceled(true);
    }

    public boolean isStandingOnLiquid() {
        return JesusModule.mc.world.handleMaterialAcceleration(JesusModule.mc.player.getEntityBoundingBox().expand(0.0, -3.0, 0.0).contract(0.001), Material.WATER, (Entity)JesusModule.mc.player) || JesusModule.mc.world.handleMaterialAcceleration(JesusModule.mc.player.getEntityBoundingBox().expand(0.0, -3.0, 0.0).contract(0.001), Material.LAVA, (Entity)JesusModule.mc.player);
    }

    @Override
    public String getDisplayInfo() {
        return this.mode.currentEnumName();
    }

    @SubscribeEvent
    public void updateWalkingPlayer(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 0 && JesusModule.nullCheck() && this.mode.getValue() == Mode.SOLID_STRICT && JesusModule.mc.world != null && JesusModule.mc.player != null && this.isAboveLiquid() && !JesusModule.isInLiquid() && !JesusModule.mc.gameSettings.keyBindSneak.isKeyDown()) {
            if (this.ncpStrictY >= 0.5) {
                this.ncpStrictY = 0.0;
            }
            this.ncpStrictY += 0.1 + Math.random() / 10.0;
            updateWalkingPlayerEvent.setY(updateWalkingPlayerEvent.getY() - this.ncpStrictY);
        }
    }

    public static boolean isInLiquid() {
        return JesusModule.mc.player.isInLava() || JesusModule.mc.player.isInWater();
    }

    public JesusModule() {
        super("JesusTest", "Allows you to walk on water", Module.Category.MOVEMENT, true, false, false);
        this.mode = this.register(new Setting<Mode>("Mode", Mode.SOLID));
        this.floatTicks = 1000;
        this.offsetTimer = new Timer();
        this.ncpStrictY = 0.0;
        this.floatUpTimer = 0;
        INSTANCE = this;
    }

    @Override
    public void onUpdate() {
        if (this.mode.getValue().equals((Object)Mode.SOLID) || this.mode.getValue().equals((Object)Mode.SOLID_STRICT)) {
            if (JesusModule.isInLiquid() || JesusModule.mc.player.fallDistance > 3.0f || JesusModule.mc.player.isSneaking()) {
                this.floatOffset = 0.0;
            }
            if (!JesusModule.mc.player.isSneaking()) {
                if (JesusModule.isInLiquid()) {
                    JesusModule.mc.player.motionY = 0.1;
                    this.floatTicks = 0;
                } else if (this.floatTicks > 0 && this.floatTicks < 4) {
                    JesusModule.mc.player.motionY = 0.1;
                }
                ++this.floatTicks;
            }
        } else if (this.mode.getValue().equals((Object)Mode.DOLPHIN) && JesusModule.isInLiquid() && !JesusModule.mc.player.isSneaking()) {
            KeyBinding.setKeyBindState((int)JesusModule.mc.gameSettings.keyBindJump.getKeyCode(), (boolean)true);
        }
    }

    @Override
    public void onDisable() {
        this.floatOffset = 0.0;
        this.ncpStrictY = 0.0;
        this.floatTicks = 1000;
        this.offsetTimer.reset();
        KeyBinding.setKeyBindState((int)JesusModule.mc.gameSettings.keyBindJump.getKeyCode(), (boolean)false);
    }

    public static enum Mode {
        SOLID,
        SOLID_STRICT,
        DOLPHIN;

    }
}

