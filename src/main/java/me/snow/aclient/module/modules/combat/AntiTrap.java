//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemEndCrystal
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.combat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AntiTrap
extends Module {
    private /* synthetic */ boolean switchedItem;
    private /* synthetic */ int lastHotbarSlot;
    private final /* synthetic */ Setting<InventoryUtil.Switch> switchMode;
    private /* synthetic */ boolean offhand;
    public static /* synthetic */ Set<BlockPos> placedPos;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Integer> coolDown;
    public /* synthetic */ Setting<Boolean> sortY;
    public /* synthetic */ Setting<Rotate> rotate;
    private final /* synthetic */ Vec3d[] surroundTargets;

    @Override
    public void onDisable() {
        if (AntiTrap.fullNullCheck()) {
            return;
        }
        this.switchItem(true);
    }

    static {
        placedPos = new HashSet<BlockPos>();
    }

    private boolean switchItem(boolean bl) {
        if (this.offhand) {
            return true;
        }
        boolean[] arrbl = InventoryUtil.switchItemToItem(bl, this.lastHotbarSlot, this.switchedItem, this.switchMode.getValue(), Items.END_CRYSTAL);
        this.switchedItem = arrbl[0];
        return arrbl[1];
    }

    private void placeCrystal(BlockPos blockPos) {
        boolean bl;
        boolean bl2 = bl = AntiTrap.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL;
        if (!(bl || this.offhand || this.switchItem(false))) {
            this.disable();
            return;
        }
        RayTraceResult rayTraceResult = AntiTrap.mc.world.rayTraceBlocks(new Vec3d(AntiTrap.mc.player.posX, AntiTrap.mc.player.posY + (double)AntiTrap.mc.player.getEyeHeight(), AntiTrap.mc.player.posZ), new Vec3d((double)blockPos.getX() + 0.5, (double)blockPos.getY() - 0.5, (double)blockPos.getZ() + 0.5));
        EnumFacing enumFacing = rayTraceResult == null || rayTraceResult.sideHit == null ? EnumFacing.UP : rayTraceResult.sideHit;
        float[] arrf = MathUtil.calcAngle(AntiTrap.mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d((double)((float)blockPos.getX() + 0.5f), (double)((float)blockPos.getY() - 0.5f), (double)((float)blockPos.getZ() + 0.5f)));
        switch (this.rotate.getValue()) {
            case NONE: {
                break;
            }
            case NORMAL: {
                AliceMain.rotationManager.setPlayerRotations(arrf[0], arrf[1]);
                break;
            }
            case PACKET: {
                AntiTrap.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(arrf[0], (float)MathHelper.normalizeAngle((int)((int)arrf[1]), (int)360), AntiTrap.mc.player.onGround));
            }
        }
        placedPos.add(blockPos);
        AntiTrap.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, this.offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
        AntiTrap.mc.player.swingArm(EnumHand.MAIN_HAND);
        this.timer.reset();
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (!AntiTrap.fullNullCheck() && updateWalkingPlayerEvent.getStage() == 0) {
            this.doAntiTrap();
        }
    }

    @Override
    public void onEnable() {
        if (AntiTrap.fullNullCheck() || !this.timer.passedMs(this.coolDown.getValue().intValue())) {
            this.disable();
            return;
        }
        this.lastHotbarSlot = AntiTrap.mc.player.inventory.currentItem;
    }

    public AntiTrap() {
        super("AntiTrap", "Places a crystal to prevent you getting trapped.", Module.Category.COMBAT, true, false, false);
        this.coolDown = this.register(new Setting<Integer>("CoolDown", 400, 0, 1000));
        this.switchMode = this.register(new Setting<InventoryUtil.Switch>("Switch", InventoryUtil.Switch.NORMAL));
        this.surroundTargets = new Vec3d[]{new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, -1.0), new Vec3d(-1.0, 0.0, 1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, -1.0), new Vec3d(-1.0, 1.0, 1.0)};
        this.timer = new Timer();
        this.rotate = this.register(new Setting<Rotate>("Rotate", Rotate.NORMAL));
        this.sortY = this.register(new Setting<Boolean>("SortY", true));
        this.lastHotbarSlot = -1;
    }

    public void doAntiTrap() {
        boolean bl = this.offhand = AntiTrap.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
        if (!this.offhand && InventoryUtil.findHotbarBlock(ItemEndCrystal.class) == -1) {
            this.disable();
            return;
        }
        this.lastHotbarSlot = AntiTrap.mc.player.inventory.currentItem;
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        Collections.addAll(arrayList, BlockUtil.convertVec3ds(AntiTrap.mc.player.getPositionVector(), this.surroundTargets));
        EntityPlayer entityPlayer = EntityUtil.getClosestEnemy(6.0);
        if (entityPlayer != null) {
            arrayList.sort((vec3d, vec3d2) -> Double.compare(entityPlayer.getDistanceSq(vec3d2.xCoord, vec3d2.yCoord, vec3d2.zCoord), entityPlayer.getDistanceSq(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord)));
            if (this.sortY.getValue().booleanValue()) {
                arrayList.sort(Comparator.comparingDouble(vec3d -> vec3d.yCoord));
            }
        }
        for (Vec3d vec3d3 : arrayList) {
            BlockPos blockPos = new BlockPos(vec3d3);
            if (!BlockUtil.canPlaceCrystal(blockPos)) continue;
            this.placeCrystal(blockPos);
            this.disable();
            break;
        }
    }

    public static enum Rotate {
        NONE,
        NORMAL,
        PACKET;

    }
}

