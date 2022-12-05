//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockWeb
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.combat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.player.Freecam;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.block.BlockWeb;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WebFill
extends Module {
    private final /* synthetic */ Setting<InventoryUtil.Switch> switchMode;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Setting<Boolean> raytrace;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Boolean> freecam;
    private final /* synthetic */ Setting<Boolean> info;
    private /* synthetic */ int placements;
    private /* synthetic */ boolean isSneaking;
    private final /* synthetic */ Setting<TargetMode> targetMode;
    private final /* synthetic */ Setting<Boolean> packet;
    private final /* synthetic */ Setting<Integer> eventMode;
    public static /* synthetic */ boolean isPlacing;
    private final /* synthetic */ Setting<Boolean> lowerbody;
    private final /* synthetic */ Setting<Double> speed;
    private /* synthetic */ BlockPos startPos;
    private /* synthetic */ boolean switchedItem;
    public /* synthetic */ EntityPlayer target;
    private /* synthetic */ int lastHotbarSlot;
    private final /* synthetic */ Setting<Integer> blocksPerPlace;
    private final /* synthetic */ Setting<Boolean> ylower;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Boolean> antiSelf;
    private final /* synthetic */ Setting<Double> range;
    private /* synthetic */ boolean didPlace;
    private final /* synthetic */ Setting<Double> targetRange;
    private /* synthetic */ boolean smartRotate;
    private final /* synthetic */ Setting<Boolean> upperBody;
    private final /* synthetic */ Setting<Boolean> disable;

    private void placeBlock(BlockPos blockPos) {
        if (this.placements < this.blocksPerPlace.getValue() && WebFill.mc.player.getDistanceSq(blockPos) <= MathUtil.square(this.range.getValue()) && this.switchItem(false)) {
            isPlacing = true;
            this.isSneaking = this.smartRotate ? BlockUtil.placeBlockSmartRotate(blockPos, EnumHand.MAIN_HAND, true, this.packet.getValue(), this.isSneaking) : BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), this.isSneaking);
            this.didPlace = true;
            ++this.placements;
        }
    }

    private void doWebTrap() {
        List<Vec3d> list = this.getPlacements();
        this.placeList(list);
    }

    public WebFill() {
        super("WebFill", "Traps other players in webs", Module.Category.COMBAT, true, false, false);
        this.delay = this.register(new Setting<Integer>("Delay/Place", 50, 0, 250));
        this.blocksPerPlace = this.register(new Setting<Integer>("Block/Place", 8, 1, 30));
        this.targetRange = this.register(new Setting<Double>("TargetRange", 10.0, 0.0, 20.0));
        this.range = this.register(new Setting<Double>("PlaceRange", 6.0, 0.0, 10.0));
        this.targetMode = this.register(new Setting<TargetMode>("Target", TargetMode.CLOSEST));
        this.switchMode = this.register(new Setting<InventoryUtil.Switch>("Switch", InventoryUtil.Switch.NORMAL));
        this.rotate = this.register(new Setting<Boolean>("Rotate", true));
        this.raytrace = this.register(new Setting<Boolean>("Raytrace", false));
        this.speed = this.register(new Setting<Double>("Speed", 30.0, 0.0, 30.0));
        this.upperBody = this.register(new Setting<Boolean>("Upper", false));
        this.lowerbody = this.register(new Setting<Boolean>("Lower", true));
        this.ylower = this.register(new Setting<Boolean>("Y-1", false));
        this.antiSelf = this.register(new Setting<Boolean>("AntiSelf", false));
        this.eventMode = this.register(new Setting<Integer>("Updates", 3, 1, 3));
        this.freecam = this.register(new Setting<Boolean>("Freecam", false));
        this.info = this.register(new Setting<Boolean>("Info", false));
        this.disable = this.register(new Setting<Boolean>("TSelfMove", false));
        this.packet = this.register(new Setting<Boolean>("Packet", false));
        this.timer = new Timer();
        this.didPlace = false;
        this.placements = 0;
        this.smartRotate = false;
        this.startPos = null;
    }

    @Override
    public void onUpdate() {
        if (this.eventMode.getValue() == 1) {
            this.smartRotate = false;
            this.doTrap();
        }
    }

    @Override
    public void onDisable() {
        isPlacing = false;
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
        this.switchItem(true);
    }

    private void placeList(List<Vec3d> list) {
        list.sort((vec3d, vec3d2) -> Double.compare(WebFill.mc.player.getDistanceSq(vec3d2.xCoord, vec3d2.yCoord, vec3d2.zCoord), WebFill.mc.player.getDistanceSq(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord)));
        list.sort(Comparator.comparingDouble(vec3d -> vec3d.yCoord));
        for (Vec3d vec3d3 : list) {
            BlockPos blockPos = new BlockPos(vec3d3);
            int n = BlockUtil.isPositionPlaceable(blockPos, this.raytrace.getValue());
            if (n != 3 && n != 1 || this.antiSelf.getValue().booleanValue() && MathUtil.areVec3dsAligned(WebFill.mc.player.getPositionVector(), vec3d3)) continue;
            this.placeBlock(blockPos);
        }
    }

    private EntityPlayer getTarget(double d, boolean bl) {
        EntityPlayer entityPlayer = null;
        double d2 = Math.pow(d, 2.0) + 1.0;
        for (EntityPlayer entityPlayer2 : WebFill.mc.world.playerEntities) {
            if (EntityUtil.isntValid((Entity)entityPlayer2, d) || bl && entityPlayer2.isInWeb || EntityUtil.getRoundedBlockPos((Entity)WebFill.mc.player).equals((Object)EntityUtil.getRoundedBlockPos((Entity)entityPlayer2)) && this.antiSelf.getValue().booleanValue() || AliceMain.speedManager.getPlayerSpeed(entityPlayer2) > this.speed.getValue()) continue;
            if (entityPlayer == null) {
                entityPlayer = entityPlayer2;
                d2 = WebFill.mc.player.getDistanceSqToEntity((Entity)entityPlayer2);
                continue;
            }
            if (!(WebFill.mc.player.getDistanceSqToEntity((Entity)entityPlayer2) < d2)) continue;
            entityPlayer = entityPlayer2;
            d2 = WebFill.mc.player.getDistanceSqToEntity((Entity)entityPlayer2);
        }
        return entityPlayer;
    }

    @Override
    public void onEnable() {
        if (WebFill.fullNullCheck()) {
            return;
        }
        this.startPos = EntityUtil.getRoundedBlockPos((Entity)WebFill.mc.player);
        this.lastHotbarSlot = WebFill.mc.player.inventory.currentItem;
    }

    private void doTrap() {
        if (this.check()) {
            return;
        }
        this.doWebTrap();
        if (this.didPlace) {
            this.timer.reset();
        }
    }

    static {
        isPlacing = false;
    }

    private List<Vec3d> getPlacements() {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        Vec3d vec3d = this.target.getPositionVector();
        if (this.ylower.getValue().booleanValue()) {
            arrayList.add(vec3d.addVector(0.0, -1.0, 0.0));
        }
        if (this.lowerbody.getValue().booleanValue()) {
            arrayList.add(vec3d);
        }
        if (this.upperBody.getValue().booleanValue()) {
            arrayList.add(vec3d.addVector(0.0, 1.0, 0.0));
        }
        return arrayList;
    }

    @Override
    public String getDisplayInfo() {
        if (this.info.getValue().booleanValue() && this.target != null) {
            return this.target.getName();
        }
        return null;
    }

    private boolean check() {
        isPlacing = false;
        this.didPlace = false;
        this.placements = 0;
        int n = InventoryUtil.findHotbarBlock(BlockWeb.class);
        if (this.isOff()) {
            return true;
        }
        if (this.disable.getValue().booleanValue() && !this.startPos.equals((Object)EntityUtil.getRoundedBlockPos((Entity)WebFill.mc.player))) {
            this.disable();
            return true;
        }
        if (n == -1) {
            if (this.switchMode.getValue() != InventoryUtil.Switch.NONE) {
                if (this.info.getValue().booleanValue()) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append("<").append(this.getDisplayName()).append("> \u00a7cYou are out of Webs.")));
                }
                this.disable();
            }
            return true;
        }
        if (WebFill.mc.player.inventory.currentItem != this.lastHotbarSlot && WebFill.mc.player.inventory.currentItem != n) {
            this.lastHotbarSlot = WebFill.mc.player.inventory.currentItem;
        }
        this.switchItem(true);
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
        this.target = this.getTarget(this.targetRange.getValue(), this.targetMode.getValue() == TargetMode.UNTRAPPED);
        return this.target == null || AliceMain.moduleManager.getModuleByClass(Freecam.class).isEnabled() && this.freecam.getValue() == false || !this.timer.passedMs(this.delay.getValue().intValue()) || this.switchMode.getValue() == InventoryUtil.Switch.NONE && WebFill.mc.player.inventory.currentItem != InventoryUtil.findHotbarBlock(BlockWeb.class);
    }

    @Override
    public void onTick() {
        if (this.eventMode.getValue() == 3) {
            this.smartRotate = false;
            this.doTrap();
        }
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 0 && this.eventMode.getValue() == 2) {
            this.smartRotate = this.rotate.getValue() != false && this.blocksPerPlace.getValue() == 1;
            this.doTrap();
        }
    }

    private boolean switchItem(boolean bl) {
        boolean[] arrbl = InventoryUtil.switchItem(bl, this.lastHotbarSlot, this.switchedItem, this.switchMode.getValue(), BlockWeb.class);
        this.switchedItem = arrbl[0];
        return arrbl[1];
    }

    public static enum TargetMode {
        CLOSEST,
        UNTRAPPED;

    }
}

