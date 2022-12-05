//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemEndCrystal
 *  net.minecraft.item.ItemExpBottle
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.network.play.client.CPacketUseEntity$Action
 *  net.minecraft.network.play.server.SPacketSpawnExperienceOrb
 *  net.minecraft.network.play.server.SPacketSpawnGlobalEntity
 *  net.minecraft.network.play.server.SPacketSpawnMob
 *  net.minecraft.network.play.server.SPacketSpawnObject
 *  net.minecraft.network.play.server.SPacketSpawnPainting
 *  net.minecraft.network.play.server.SPacketSpawnPlayer
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.server.SPacketSpawnExperienceOrb;
import net.minecraft.network.play.server.SPacketSpawnGlobalEntity;
import net.minecraft.network.play.server.SPacketSpawnMob;
import net.minecraft.network.play.server.SPacketSpawnObject;
import net.minecraft.network.play.server.SPacketSpawnPainting;
import net.minecraft.network.play.server.SPacketSpawnPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GodModule
extends Module {
    private /* synthetic */ boolean rotating;
    public /* synthetic */ Setting<Boolean> render;
    public /* synthetic */ Setting<Integer> attacks;
    private /* synthetic */ int rotationPacketsSpoofed;
    public /* synthetic */ Setting<Boolean> antiIllegal;
    public /* synthetic */ Setting<Boolean> rotate;
    private /* synthetic */ int highestID;
    public /* synthetic */ Setting<Boolean> entitycheck;
    public /* synthetic */ Setting<Boolean> oneDot15;
    private /* synthetic */ float yaw;
    public /* synthetic */ Setting<Integer> rotations;
    public /* synthetic */ Setting<Integer> delay;
    public /* synthetic */ Setting<Boolean> checkPos;
    private /* synthetic */ float pitch;

    public void rotateTo(BlockPos blockPos) {
        float[] arrf = MathUtil.calcAngle(GodModule.mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d((Vec3i)blockPos));
        this.yaw = arrf[0];
        this.pitch = arrf[1];
        this.rotating = true;
    }

    @Override
    public void onLogout() {
        this.resetFields();
    }

    private boolean checkItem(ItemStack itemStack) {
        return itemStack.getItem() instanceof ItemBow || itemStack.getItem() instanceof ItemExpBottle || itemStack.getItem() == Items.STRING;
    }

    private void checkID(int n) {
        if (n > this.highestID) {
            this.highestID = n;
        }
    }

    private void attackID(BlockPos blockPos, int n) {
        Entity entity = GodModule.mc.world.getEntityByID(n);
        if (entity == null || entity instanceof EntityEnderCrystal) {
            AttackThread attackThread = new AttackThread(n, blockPos, this.delay.getValue(), this);
            attackThread.start();
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onSendPacket(PacketEvent.Send send) {
        CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock;
        if (send.getStage() == 0 && send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            cPacketPlayerTryUseItemOnBlock = (CPacketPlayerTryUseItemOnBlock)send.getPacket();
            if (GodModule.mc.player.getHeldItem(cPacketPlayerTryUseItemOnBlock.hand).getItem() instanceof ItemEndCrystal) {
                if (this.checkPos.getValue().booleanValue() && !BlockUtil.canPlaceCrystal(cPacketPlayerTryUseItemOnBlock.position, this.entitycheck.getValue(), this.oneDot15.getValue()) || this.checkPlayers()) {
                    return;
                }
                this.updateEntityID();
                for (int i = 1; i < this.attacks.getValue(); ++i) {
                    this.attackID(cPacketPlayerTryUseItemOnBlock.position, this.highestID + i);
                }
            }
        }
        if (send.getStage() == 0 && this.rotating && this.rotate.getValue().booleanValue() && send.getPacket() instanceof CPacketPlayer) {
            cPacketPlayerTryUseItemOnBlock = (CPacketPlayer)send.getPacket();
            cPacketPlayerTryUseItemOnBlock.yaw = this.yaw;
            cPacketPlayerTryUseItemOnBlock.pitch = this.pitch;
            ++this.rotationPacketsSpoofed;
            if (this.rotationPacketsSpoofed >= this.rotations.getValue()) {
                this.rotating = false;
                this.rotationPacketsSpoofed = 0;
            }
        }
    }

    private void resetFields() {
        this.rotating = false;
        this.highestID = -1000000;
    }

    public void updateEntityID() {
        for (Entity entity : GodModule.mc.world.loadedEntityList) {
            if (entity.getEntityId() <= this.highestID) continue;
            this.highestID = entity.getEntityId();
        }
    }

    @Override
    public void onToggle() {
        this.resetFields();
        if (GodModule.mc.world != null) {
            this.updateEntityID();
        }
    }

    @Override
    public void onUpdate() {
        if (this.render.getValue().booleanValue()) {
            for (Entity entity : GodModule.mc.world.loadedEntityList) {
                if (!(entity instanceof EntityEnderCrystal)) continue;
                entity.setCustomNameTag(String.valueOf(entity.entityId));
                entity.setAlwaysRenderNameTag(true);
            }
        }
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketSpawnObject) {
            this.checkID(((SPacketSpawnObject)receive.getPacket()).getEntityID());
        } else if (receive.getPacket() instanceof SPacketSpawnExperienceOrb) {
            this.checkID(((SPacketSpawnExperienceOrb)receive.getPacket()).getEntityID());
        } else if (receive.getPacket() instanceof SPacketSpawnPlayer) {
            this.checkID(((SPacketSpawnPlayer)receive.getPacket()).getEntityID());
        } else if (receive.getPacket() instanceof SPacketSpawnGlobalEntity) {
            this.checkID(((SPacketSpawnGlobalEntity)receive.getPacket()).getEntityId());
        } else if (receive.getPacket() instanceof SPacketSpawnPainting) {
            this.checkID(((SPacketSpawnPainting)receive.getPacket()).getEntityID());
        } else if (receive.getPacket() instanceof SPacketSpawnMob) {
            this.checkID(((SPacketSpawnMob)receive.getPacket()).getEntityID());
        }
    }

    public GodModule() {
        super("GodModule", "Wow", Module.Category.MISC, true, false, false);
        this.rotations = this.register(new Setting<Integer>("Spoofs", 1, 1, 20));
        this.rotate = this.register(new Setting<Boolean>("Rotate", false));
        this.render = this.register(new Setting<Boolean>("Render", false));
        this.antiIllegal = this.register(new Setting<Boolean>("AntiIllegal", true));
        this.checkPos = this.register(new Setting<Boolean>("CheckPos", true));
        this.oneDot15 = this.register(new Setting<Boolean>("1.15", false));
        this.entitycheck = this.register(new Setting<Boolean>("EntityCheck", false));
        this.attacks = this.register(new Setting<Integer>("Attacks", 1, 1, 10));
        this.delay = this.register(new Setting<Integer>("Delay", 0, 0, 50));
        this.yaw = 0.0f;
        this.pitch = 0.0f;
        this.highestID = -100000;
    }

    private boolean checkPlayers() {
        if (this.antiIllegal.getValue().booleanValue()) {
            for (EntityPlayer entityPlayer : GodModule.mc.world.playerEntities) {
                if (!this.checkItem(entityPlayer.getHeldItemMainhand()) && !this.checkItem(entityPlayer.getHeldItemOffhand())) continue;
                return false;
            }
        }
        return true;
    }

    public static class AttackThread
    extends Thread {
        private final /* synthetic */ GodModule godModule;
        private final /* synthetic */ int id;
        private final /* synthetic */ BlockPos pos;
        private final /* synthetic */ int delay;

        public AttackThread(int n, BlockPos blockPos, int n2, GodModule godModule) {
            this.id = n;
            this.pos = blockPos;
            this.delay = n2;
            this.godModule = godModule;
        }

        @Override
        public void run() {
            try {
                this.wait(this.delay);
                CPacketUseEntity cPacketUseEntity = new CPacketUseEntity();
                cPacketUseEntity.entityId = this.id;
                cPacketUseEntity.action = CPacketUseEntity.Action.ATTACK;
                this.godModule.rotateTo(this.pos.up());
                Util.mc.player.connection.sendPacket((Packet)cPacketUseEntity);
                Util.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }
}

