//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockEnderChest
 *  net.minecraft.block.BlockObsidian
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.network.play.server.SPacketBlockChange
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.combat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.manager.RotationManager;
import me.snow.aclient.mixin.mixins.accessors.IEntityPlayerSP;
import me.snow.aclient.mixin.mixins.accessors.IMinecraft;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.InteractionUtil;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.Timer;
import me.snow.aclient.util.ca.sc.BlockUtilSC;
import me.snow.aclient.util.ca.sc.CrystalUtilSC;
import me.snow.aclient.util.ca.sc.PlayerUtilSC;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockObsidian;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Surround
extends Module {
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Boolean> eChest;
    private final /* synthetic */ Setting<Boolean> strict;
    private final /* synthetic */ Setting<Boolean> clear;
    public /* synthetic */ Timer delayTimer;
    private final /* synthetic */ Setting<Boolean> test;
    public /* synthetic */ boolean isSneaking;
    private final /* synthetic */ Setting<Float> delay;
    private final /* synthetic */ Setting<Boolean> center;
    private final /* synthetic */ Setting<Boolean> force;
    public static /* synthetic */ boolean switchee;
    public /* synthetic */ ConcurrentHashMap<BlockPos, Long> B2poss;
    private final /* synthetic */ Setting<Boolean> full;
    public /* synthetic */ int placecount;
    private final /* synthetic */ Setting<Boolean> ncpcenter;
    public /* synthetic */ boolean offHand;
    public /* synthetic */ int item1;
    private final /* synthetic */ Setting<Boolean> autoDisable;
    public /* synthetic */ List<Vec3d> Pattern;
    private final /* synthetic */ Setting<Boolean> packet;
    private final /* synthetic */ Setting<Boolean> swing;
    public /* synthetic */ int prevSlot;
    private final /* synthetic */ Setting<Integer> bpt;
    public /* synthetic */ BlockPos bpos;
    public /* synthetic */ BlockPos Bposs2;
    private final /* synthetic */ Setting<Boolean> queue;

    @Override
    public void onDisable() {
        if (Surround.mc.player == null || Surround.mc.world == null) {
            return;
        }
        this.bpos = null;
        switchee = false;
        this.isSneaking = this.CheckSneakk(this.isSneaking);
    }

    public boolean CheckSneakk(boolean bl) {
        if (bl && Surround.mc.player != null) {
            Surround.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Surround.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        return false;
    }

    @Override
    public void onEnable() {
        if (Surround.mc.player == null || Surround.mc.world == null) {
            this.toggle();
            return;
        }
        this.item1 = Surround.mc.player.inventory.currentItem;
        this.Bposs2 = new BlockPos((Entity)Surround.mc.player);
        if (this.center.getValue().booleanValue()) {
            PlayerUtilSC.moveToBlockCenterLuigi(this.ncpcenter.getValue());
        }
        this.B2poss.clear();
        if (this.test.getValue().booleanValue()) {
            this.delayTimer.reset();
        }
    }

    public boolean onPlace(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3) {
        EnumFacing enumFacing3;
        boolean bl4 = false;
        EnumFacing enumFacing2 = null;
        double d = 69420.0;
        for (EnumFacing enumFacing3 : InteractionUtil.getPlacableFacings(blockPos, this.strict.getValue(), false)) {
            Vec3d vec3d = new Vec3d((Vec3i)blockPos.offset(enumFacing3));
            Vec3d vec3d2 = new Vec3d(enumFacing3.getDirectionVec());
            if (!(Surround.mc.player.getPositionVector().addVector(0.0, (double)Surround.mc.player.getEyeHeight(), 0.0).distanceTo(vec3d.addVector(0.5, 0.5, 0.5).add(vec3d2.scale(0.5))) < 69420.0)) continue;
            enumFacing2 = enumFacing3;
        }
        if (enumFacing2 == null) {
            enumFacing2 = EnumFacing.DOWN;
        }
        BlockPos blockPos2 = blockPos.offset(enumFacing2);
        enumFacing3 = enumFacing2.getOpposite();
        Vec3d vec3d = new Vec3d((Vec3i)blockPos2).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing3.getDirectionVec()).scale(0.5));
        if (!Surround.mc.player.isSneaking() && BlockUtilSC.shouldSneakWhileRightClicking(blockPos2)) {
            Surround.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Surround.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            Surround.mc.player.setSneaking(true);
            bl4 = true;
        }
        if (bl) {
            PlayerUtilSC.Method1081(vec3d);
        }
        InteractionUtil.rightClickBlock(blockPos2, vec3d, enumHand, enumFacing3, bl2, this.swing.getValue());
        Surround.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
        if (!this.force.getValue().booleanValue()) {
            this.B2poss.put(blockPos, System.currentTimeMillis());
        }
        ((IMinecraft)mc).setRightClickDelayTimer(0);
        return bl4 || bl3;
    }

    public boolean check() {
        if (Surround.mc.player == null || Surround.mc.world == null) {
            return true;
        }
        switchee = false;
        this.placecount = 0;
        this.prevSlot = this.getBlockInHotbar();
        if (!this.isEnabled()) {
            return true;
        }
        if (this.prevSlot == -1) {
            this.toggle();
            return true;
        }
        this.isSneaking = this.CheckSneakk(this.isSneaking);
        if (Surround.mc.player.inventory.currentItem != this.item1 && Surround.mc.player.inventory.currentItem != this.prevSlot) {
            this.item1 = Surround.mc.player.inventory.currentItem;
        }
        if (this.autoDisable.getValue().booleanValue() && !this.Bposs2.equals((Object)new BlockPos((Entity)Surround.mc.player))) {
            this.toggle();
            return true;
        }
        return !this.delayTimer.passedMs((long)(this.delay.getValue().floatValue() * 10.0f));
    }

    public boolean findPos(Entity entity) {
        BlockPos blockPos = new BlockPos(entity.posX, entity.posY, entity.posZ);
        return Surround.mc.world.getBlockState(blockPos).getBlock().equals((Object)Blocks.OBSIDIAN) || Surround.mc.world.getBlockState(blockPos).getBlock().equals((Object)Blocks.ENDER_CHEST);
    }

    public void findObiInHotbar(BlockPos blockPos) {
        try {
            int n = Surround.mc.player.inventory.currentItem;
            if (this.prevSlot == -1) {
                this.toggle();
                return;
            }
            switchee = true;
            Surround.mc.player.inventory.currentItem = this.prevSlot;
            Surround.mc.playerController.updateController();
            this.isSneaking = this.onPlace(blockPos, this.offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), this.isSneaking);
            Surround.mc.player.inventory.currentItem = n;
            Surround.mc.playerController.updateController();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public Surround() {
        super("Surround", "awa surround", Module.Category.COMBAT, true, false, false);
        this.packet = this.register(new Setting<Boolean>("Packet", true));
        this.center = this.register(new Setting<Boolean>("TPCenter", true));
        this.ncpcenter = this.register(new Setting<Boolean>("NCPCenter", Boolean.valueOf(true), bl -> this.center.getValue()));
        this.rotate = this.register(new Setting<Boolean>("Rotate", true));
        this.bpt = this.register(new Setting<Integer>("Blocks/Tick", 3, 1, 8));
        this.delay = this.register(new Setting<Float>("Delay", Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(10.0f)));
        this.swing = this.register(new Setting<Boolean>("Swing", true));
        this.force = this.register(new Setting<Boolean>("ForcePlace", false));
        this.strict = this.register(new Setting<Boolean>("StrictDirection", false));
        this.autoDisable = this.register(new Setting<Boolean>("Toggle", true));
        this.eChest = this.register(new Setting<Boolean>("AllowEChests", true));
        this.clear = this.register(new Setting<Boolean>("Clear", false));
        this.queue = this.register(new Setting<Boolean>("Queue", true));
        this.full = this.register(new Setting<Boolean>("Full", true));
        this.test = this.register(new Setting<Boolean>("Test", true));
        this.delayTimer = new Timer();
        this.placecount = 0;
        this.prevSlot = -1;
        this.offHand = false;
        this.Pattern = new ArrayList<Vec3d>();
        this.B2poss = new ConcurrentHashMap();
    }

    public void CheckDelay(BlockPos blockPos, Long l) {
        if (System.currentTimeMillis() - l > (long)(CrystalUtilSC.ping() + 40)) {
            this.B2poss.remove((Object)blockPos);
        }
    }

    @SubscribeEvent
    public void PacketSend(PacketEvent packetEvent) {
        if (Surround.mc.world == null || Surround.mc.player == null) {
            return;
        }
        if (packetEvent.getPacket() instanceof SPacketBlockChange && this.queue.getValue().booleanValue()) {
            SPacketBlockChange sPacketBlockChange = (SPacketBlockChange)packetEvent.getPacket();
            if (sPacketBlockChange.blockState.getBlock() == Blocks.AIR && Surround.mc.player.getDistance((double)sPacketBlockChange.getBlockPosition().getX(), (double)sPacketBlockChange.getBlockPosition().getY(), (double)sPacketBlockChange.getBlockPosition().getZ()) < 1.75) {
                mc.addScheduledTask(this::gettt);
            }
        }
    }

    public void getSurroundVec3d() {
        if (this.check()) {
            return;
        }
        this.bpos = null;
        this.Pattern = new ArrayList<Vec3d>();
        if (this.doNormal()) {
            if (this.full.getValue().booleanValue()) {
                this.Pattern.add(Surround.mc.player.getPositionVector().addVector(1.0, 0.0, 0.0));
                this.Pattern.add(Surround.mc.player.getPositionVector().addVector(-1.0, 0.0, 0.0));
                this.Pattern.add(Surround.mc.player.getPositionVector().addVector(0.0, 0.0, 1.0));
                this.Pattern.add(Surround.mc.player.getPositionVector().addVector(0.0, 0.0, -1.0));
            }
            this.Pattern.add(Surround.mc.player.getPositionVector().addVector(1.0, 1.0, 0.0));
            this.Pattern.add(Surround.mc.player.getPositionVector().addVector(-1.0, 1.0, 0.0));
            this.Pattern.add(Surround.mc.player.getPositionVector().addVector(0.0, 1.0, 1.0));
            this.Pattern.add(Surround.mc.player.getPositionVector().addVector(0.0, 1.0, -1.0));
        } else {
            this.Pattern.add(Surround.mc.player.getPositionVector().addVector(0.0, -1.0, 0.0));
            if (this.full.getValue().booleanValue()) {
                this.Pattern.add(Surround.mc.player.getPositionVector().addVector(1.0, -1.0, 0.0));
                this.Pattern.add(Surround.mc.player.getPositionVector().addVector(-1.0, -1.0, 0.0));
                this.Pattern.add(Surround.mc.player.getPositionVector().addVector(0.0, -1.0, 1.0));
                this.Pattern.add(Surround.mc.player.getPositionVector().addVector(0.0, -1.0, -1.0));
            }
            this.Pattern.add(Surround.mc.player.getPositionVector().addVector(1.0, 0.0, 0.0));
            this.Pattern.add(Surround.mc.player.getPositionVector().addVector(-1.0, 0.0, 0.0));
            this.Pattern.add(Surround.mc.player.getPositionVector().addVector(0.0, 0.0, 1.0));
            this.Pattern.add(Surround.mc.player.getPositionVector().addVector(0.0, 0.0, -1.0));
        }
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        Iterator<Vec3d> iterator2 = this.Pattern.iterator();
        while (iterator2.hasNext()) {
            BlockPos blockPos = new BlockPos(iterator2.next());
            if (Surround.mc.world.getBlockState(blockPos).getBlock() != Blocks.AIR) continue;
            arrayList.add(blockPos);
        }
        if (arrayList.isEmpty()) {
            return;
        }
        for (BlockPos blockPos : arrayList) {
            if (this.placecount > this.bpt.getValue()) {
                return;
            }
            if (this.B2poss.containsKey((Object)blockPos) || this.CrystalCheckk(blockPos)) continue;
            if (this.EntityCheckidk(blockPos)) {
                if (!this.clear.getValue().booleanValue()) continue;
                Entity entity = null;
                for (Entity entity2 : Surround.mc.world.loadedEntityList) {
                    if (entity2 == null || (double)Surround.mc.player.getDistanceToEntity(entity2) > 2.4 || !(entity2 instanceof EntityEnderCrystal) || entity2.isDead) continue;
                    entity = entity2;
                }
                if (entity != null) {
                    if (this.rotate.getValue().booleanValue()) {
                        Object object = RotationManager.calculateAngle(Surround.mc.player.getPositionEyes(mc.getRenderPartialTicks()), ((EntityEnderCrystal)entity).getPositionEyes(mc.getRenderPartialTicks()));
                        Surround.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation((float)object[0], (float)MathHelper.normalizeAngle((int)((int)object[1]), (int)360), Surround.mc.player.onGround));
                        ((IEntityPlayerSP)Surround.mc.player).setLastReportedYaw((float)object[0]);
                        ((IEntityPlayerSP)Surround.mc.player).setLastReportedPitch(MathHelper.normalizeAngle((int)((int)object[1]), (int)360));
                    }
                    mc.getConnection().sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
                    mc.getConnection().sendPacket((Packet)new CPacketUseEntity(entity));
                }
            }
            this.bpos = blockPos;
            this.findObiInHotbar(this.bpos);
            ++this.placecount;
        }
    }

    public int getBlockInHotbar() {
        Block block;
        ItemStack itemStack;
        int n;
        int n2 = -1;
        int n3 = InventoryUtil.findHotbarBlock(BlockObsidian.class);
        if (this.eChest.getValue().booleanValue() && n3 == -1) {
            for (n = 0; n < 9; ++n) {
                itemStack = Surround.mc.player.inventory.getStackInSlot(n);
                if (itemStack == ItemStack.field_190927_a || !(itemStack.getItem() instanceof ItemBlock) || !((block = ((ItemBlock)itemStack.getItem()).getBlock()) instanceof BlockEnderChest)) continue;
                n2 = n;
                return n2;
            }
        }
        for (n = 0; n < 9; ++n) {
            itemStack = Surround.mc.player.inventory.getStackInSlot(n);
            if (itemStack == ItemStack.field_190927_a || !(itemStack.getItem() instanceof ItemBlock) || !((block = ((ItemBlock)itemStack.getItem()).getBlock()) instanceof BlockObsidian)) continue;
            n2 = n;
            break;
        }
        return n2;
    }

    @Override
    public void onTick() {
        if (Surround.mc.player == null || Surround.mc.world == null) {
            this.toggle();
            return;
        }
        this.B2poss.forEach((arg_0, arg_1) -> this.CheckDelay(arg_0, arg_1));
        mc.addScheduledTask(this::Method2104);
    }

    public void Method2104() {
        this.getSurroundVec3d();
    }

    public boolean doNormal() {
        return !this.areClose() && this.findPos((Entity)Surround.mc.player);
    }

    static {
        switchee = false;
    }

    public boolean CrystalCheckk(BlockPos blockPos) {
        for (Entity entity : Surround.mc.world.loadedEntityList) {
            if (entity instanceof EntityEnderCrystal || entity instanceof EntityItem || !new AxisAlignedBB(blockPos).intersectsWith(entity.getEntityBoundingBox())) continue;
            return true;
        }
        return false;
    }

    public boolean areClose() {
        Block block = Surround.mc.world.getBlockState(new BlockPos(Surround.mc.player.getPositionVector().addVector(0.0, 0.2, 0.0))).getBlock();
        return block == Blocks.OBSIDIAN || block == Blocks.ENDER_CHEST;
    }

    public void gettt() {
        this.getSurroundVec3d();
    }

    public boolean EntityCheckidk(BlockPos blockPos) {
        for (Entity entity : Surround.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityEnderCrystal) || entity.equals((Object)Surround.mc.player) || entity instanceof EntityItem || !new AxisAlignedBB(blockPos).intersectsWith(entity.getEntityBoundingBox())) continue;
            return true;
        }
        return false;
    }
}

