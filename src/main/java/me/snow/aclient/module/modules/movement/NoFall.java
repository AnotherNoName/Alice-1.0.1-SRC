//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketClickWindow
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.server.SPacketSetSlot
 *  net.minecraft.network.play.server.SPacketWindowItems
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.movement;

import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.Timer;
import me.snow.aclient.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketSetSlot;
import net.minecraft.network.play.server.SPacketWindowItems;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoFall
extends Module {
    private final /* synthetic */ Setting<Boolean> glide;
    private final /* synthetic */ Setting<Boolean> bypass;
    private /* synthetic */ boolean equipped;
    private final /* synthetic */ Setting<Integer> distance;
    private final /* synthetic */ Setting<Boolean> silent;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ State currentState;
    private static /* synthetic */ int ogslot;
    private /* synthetic */ boolean gotElytra;
    private static final /* synthetic */ Timer bypassTimer;

    static {
        bypassTimer = new Timer();
        ogslot = -1;
    }

    public NoFall() {
        super("NoFall", "Prevents fall damage.", Module.Category.MOVEMENT, true, false, false);
        this.mode = this.register(new Setting<Mode>("Mode", Mode.PACKET));
        this.distance = this.register(new Setting<Object>("Distance", Integer.valueOf(15), Integer.valueOf(0), Integer.valueOf(50), object -> this.mode.getValue() == Mode.BUCKET));
        this.glide = this.register(new Setting<Object>("Glide", Boolean.FALSE, object -> this.mode.getValue() == Mode.ELYTRA));
        this.silent = this.register(new Setting<Object>("Silent", Boolean.TRUE, object -> this.mode.getValue() == Mode.ELYTRA));
        this.bypass = this.register(new Setting<Object>("Bypass", Boolean.FALSE, object -> this.mode.getValue() == Mode.ELYTRA));
        this.timer = new Timer();
        this.currentState = State.FALL_CHECK;
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        CPacketPlayer cPacketPlayer;
        if (NoFall.fullNullCheck()) {
            return;
        }
        if (this.mode.getValue() == Mode.ELYTRA) {
            if (this.bypass.getValue().booleanValue()) {
                this.currentState = this.currentState.onSend(send);
            } else if (!this.equipped && send.getPacket() instanceof CPacketPlayer && NoFall.mc.player.fallDistance >= 3.0f) {
                cPacketPlayer = null;
                if (!this.glide.getValue().booleanValue()) {
                    cPacketPlayer = NoFall.mc.world.rayTraceBlocks(NoFall.mc.player.getPositionVector(), NoFall.mc.player.getPositionVector().addVector(0.0, -3.0, 0.0), true, true, false);
                }
                if (this.glide.getValue().booleanValue() || cPacketPlayer != null && cPacketPlayer.typeOfHit == RayTraceResult.Type.BLOCK) {
                    if (NoFall.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem().equals((Object)Items.ELYTRA)) {
                        NoFall.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)NoFall.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                    } else if (this.silent.getValue().booleanValue()) {
                        int n = InventoryUtil.getItemHotbar(Items.ELYTRA);
                        if (n != -1) {
                            NoFall.mc.playerController.windowClick(NoFall.mc.player.inventoryContainer.windowId, 6, n, ClickType.SWAP, (EntityPlayer)NoFall.mc.player);
                            NoFall.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)NoFall.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                        }
                        ogslot = n;
                        this.equipped = true;
                    }
                }
            }
        }
        if (this.mode.getValue() == Mode.PACKET && send.getPacket() instanceof CPacketPlayer) {
            if (NoFall.mc.player.isElytraFlying() || NoFall.mc.player.fallDistance < 3.0f) {
                return;
            }
            cPacketPlayer = (CPacketPlayer)send.getPacket();
            cPacketPlayer.onGround = true;
        }
    }

    @Override
    public void onEnable() {
        ogslot = -1;
        this.currentState = State.FALL_CHECK;
    }

    @Override
    public void onUpdate() {
        if (NoFall.fullNullCheck()) {
            return;
        }
        if (this.mode.getValue() == Mode.ELYTRA) {
            int n;
            if (this.bypass.getValue().booleanValue()) {
                this.currentState = this.currentState.onUpdate();
            } else if (this.silent.getValue().booleanValue() && this.equipped && this.gotElytra) {
                NoFall.mc.playerController.windowClick(NoFall.mc.player.inventoryContainer.windowId, 6, ogslot, ClickType.SWAP, (EntityPlayer)NoFall.mc.player);
                NoFall.mc.playerController.updateController();
                this.equipped = false;
                this.gotElytra = false;
            } else if (this.silent.getValue().booleanValue() && InventoryUtil.getItemHotbar(Items.ELYTRA) == -1 && (n = InventoryUtil.findStackInventory(Items.ELYTRA)) != -1 && ogslot != -1) {
                System.out.printf("Moving %d to hotbar %d%n", n, ogslot);
                NoFall.mc.playerController.windowClick(NoFall.mc.player.inventoryContainer.windowId, n, ogslot, ClickType.SWAP, (EntityPlayer)NoFall.mc.player);
                NoFall.mc.playerController.updateController();
            }
        }
    }

    @Override
    public String getDisplayInfo() {
        return this.mode.currentEnumName();
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (NoFall.fullNullCheck()) {
            return;
        }
        if ((this.equipped || this.bypass.getValue().booleanValue()) && this.mode.getValue() == Mode.ELYTRA && (receive.getPacket() instanceof SPacketWindowItems || receive.getPacket() instanceof SPacketSetSlot)) {
            if (this.bypass.getValue().booleanValue()) {
                this.currentState = this.currentState.onReceive(receive);
            } else {
                this.gotElytra = true;
            }
        }
    }

    @Override
    public void onTick() {
        Vec3d vec3d;
        RayTraceResult rayTraceResult;
        if (NoFall.fullNullCheck()) {
            return;
        }
        if (this.mode.getValue() == Mode.BUCKET && NoFall.mc.player.fallDistance >= (float)this.distance.getValue().intValue() && !EntityUtil.isAboveWater((Entity)NoFall.mc.player) && this.timer.passedMs(100L) && (rayTraceResult = NoFall.mc.world.rayTraceBlocks(vec3d = NoFall.mc.player.getPositionVector(), vec3d.addVector(0.0, (double)-5.33f, 0.0), true, true, false)) != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
            EnumHand enumHand = EnumHand.MAIN_HAND;
            if (NoFall.mc.player.getHeldItemOffhand().getItem() == Items.WATER_BUCKET) {
                enumHand = EnumHand.OFF_HAND;
            } else if (NoFall.mc.player.getHeldItemMainhand().getItem() != Items.WATER_BUCKET) {
                for (int i = 0; i < 9; ++i) {
                    if (NoFall.mc.player.inventory.getStackInSlot(i).getItem() != Items.WATER_BUCKET) continue;
                    NoFall.mc.player.inventory.currentItem = i;
                    NoFall.mc.player.rotationPitch = 90.0f;
                    this.timer.reset();
                    return;
                }
                return;
            }
            NoFall.mc.player.rotationPitch = 90.0f;
            NoFall.mc.playerController.processRightClick((EntityPlayer)NoFall.mc.player, (World)NoFall.mc.world, enumHand);
            this.timer.reset();
        }
    }

    public static enum State {
        FALL_CHECK{

            @Override
            public State onSend(PacketEvent.Send send) {
                RayTraceResult rayTraceResult = Util.mc.world.rayTraceBlocks(Util.mc.player.getPositionVector(), Util.mc.player.getPositionVector().addVector(0.0, -3.0, 0.0), true, true, false);
                if (send.getPacket() instanceof CPacketPlayer && Util.mc.player.fallDistance >= 3.0f && rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
                    int n = InventoryUtil.getItemHotbar(Items.ELYTRA);
                    if (n != -1) {
                        Util.mc.playerController.windowClick(Util.mc.player.inventoryContainer.windowId, 6, n, ClickType.SWAP, (EntityPlayer)Util.mc.player);
                        ogslot = n;
                        Util.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Util.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                        return WAIT_FOR_ELYTRA_DEQUIP;
                    }
                    return this;
                }
                return this;
            }
        }
        ,
        WAIT_FOR_ELYTRA_DEQUIP{

            @Override
            public State onReceive(PacketEvent.Receive receive) {
                if (receive.getPacket() instanceof SPacketWindowItems || receive.getPacket() instanceof SPacketSetSlot) {
                    return REEQUIP_ELYTRA;
                }
                return this;
            }
        }
        ,
        REEQUIP_ELYTRA{

            @Override
            public State onUpdate() {
                Util.mc.playerController.windowClick(Util.mc.player.inventoryContainer.windowId, 6, ogslot, ClickType.SWAP, (EntityPlayer)Util.mc.player);
                Util.mc.playerController.updateController();
                int n = InventoryUtil.findStackInventory(Items.ELYTRA, true);
                if (n == -1) {
                    Command.sendMessage("\u00a7cElytra not found after regain?");
                    return WAIT_FOR_NEXT_REQUIP;
                }
                Util.mc.playerController.windowClick(Util.mc.player.inventoryContainer.windowId, n, ogslot, ClickType.SWAP, (EntityPlayer)Util.mc.player);
                Util.mc.playerController.updateController();
                bypassTimer.reset();
                return RESET_TIME;
            }
        }
        ,
        WAIT_FOR_NEXT_REQUIP{

            @Override
            public State onUpdate() {
                if (bypassTimer.passedMs(250L)) {
                    return REEQUIP_ELYTRA;
                }
                return this;
            }
        }
        ,
        RESET_TIME{

            @Override
            public State onUpdate() {
                if (Util.mc.player.onGround || bypassTimer.passedMs(250L)) {
                    Util.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(0, 0, 0, ClickType.PICKUP, new ItemStack(Blocks.BEDROCK), 1337));
                    return FALL_CHECK;
                }
                return this;
            }
        };


        public State onSend(PacketEvent.Send send) {
            return this;
        }

        public State onReceive(PacketEvent.Receive receive) {
            return this;
        }

        public State onUpdate() {
            return this;
        }
    }

    public static enum Mode {
        PACKET,
        BUCKET,
        ELYTRA;

    }
}

