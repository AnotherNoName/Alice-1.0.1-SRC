//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.combat;

import java.awt.Color;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.RenderUtil;
import me.snow.aclient.util.Timer;
import me.snow.aclient.util.skid.oyvey.EntityUtil2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SmartCity
extends Module {
    public final /* synthetic */ Setting<Boolean> oneFifteen;
    public final /* synthetic */ Setting<Integer> cAlpha;
    public final /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Timer timer;
    public final /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ BlockPos renderPos;
    public final /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Boolean> cSync;
    public /* synthetic */ Setting<Boolean> box;
    public final /* synthetic */ Setting<Boolean> rotate;
    public final /* synthetic */ Setting<Boolean> render;
    public final /* synthetic */ Setting<Integer> boxAlpha;
    public final /* synthetic */ Setting<Boolean> pickOnly;
    public /* synthetic */ Setting<Boolean> outline;
    public final /* synthetic */ Setting<Integer> range;
    public final /* synthetic */ Setting<Boolean> prePlaceCrystal;
    public final /* synthetic */ Setting<Float> lineWidth;
    public final /* synthetic */ Setting<Integer> cBlue;
    public final /* synthetic */ Setting<Integer> cRed;
    public final /* synthetic */ Setting<Boolean> silentPlace;
    public /* synthetic */ EntityPlayer target;
    public final /* synthetic */ Setting<Boolean> crystalCheck;
    public final /* synthetic */ Setting<Integer> cGreen;
    public final /* synthetic */ Setting<Integer> alpha;

    public boolean canPlaceCrystal(BlockPos blockPos, boolean bl) {
        return bl ? SmartCity.mc.world.getBlockState(blockPos).getBlock() instanceof BlockAir && SmartCity.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())).getBlock() == Blocks.OBSIDIAN || SmartCity.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())).getBlock() == Blocks.BEDROCK : SmartCity.mc.world.getBlockState(blockPos).getBlock() instanceof BlockAir && SmartCity.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ())).getBlock() instanceof BlockAir && SmartCity.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())).getBlock() == Blocks.OBSIDIAN || SmartCity.mc.world.getBlockState(new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ())).getBlock() == Blocks.BEDROCK;
    }

    public boolean isPlayerOccupied() {
        return SmartCity.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe && SmartCity.mc.player.isHandActive();
    }

    @Override
    public void onUpdate() {
        if (SmartCity.nullCheck()) {
            return;
        }
        this.target = (EntityPlayer)EntityUtil2.getTarget(true, false, false, false, false, 10.0, EntityUtil2.toMode("Closest"));
        if (this.target == null) {
            return;
        }
        Double d = this.range.getValue().doubleValue();
        Vec3d vec3d = this.target.getPositionVector();
        if (this.pickOnly.getValue().booleanValue() && !(SmartCity.mc.player.inventory.getCurrentItem().getItem() instanceof ItemPickaxe)) {
            return;
        }
        if (SmartCity.mc.player.getPositionVector().distanceTo(vec3d) <= d) {
            BlockPos blockPos = new BlockPos(vec3d.addVector(1.0, 0.0, 0.0));
            BlockPos blockPos2 = new BlockPos(vec3d.addVector(-1.0, 0.0, 0.0));
            BlockPos blockPos3 = new BlockPos(vec3d.addVector(0.0, 0.0, 1.0));
            BlockPos blockPos4 = new BlockPos(vec3d.addVector(0.0, 0.0, -1.0));
            BlockPos blockPos5 = new BlockPos(vec3d.addVector(2.0, 0.0, 0.0));
            BlockPos blockPos6 = new BlockPos(vec3d.addVector(-2.0, 0.0, 0.0));
            BlockPos blockPos7 = new BlockPos(vec3d.addVector(0.0, 0.0, 2.0));
            BlockPos blockPos8 = new BlockPos(vec3d.addVector(0.0, 0.0, -2.0));
            if (!this.isPlayerOccupied() && !this.crystalCheck.getValue().booleanValue()) {
                if (SmartCity.isBlockValid(blockPos)) {
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos, EnumFacing.DOWN));
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos, EnumFacing.DOWN));
                }
                if (!SmartCity.isBlockValid(blockPos) && SmartCity.isBlockValid(blockPos2)) {
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos2, EnumFacing.DOWN));
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos2, EnumFacing.DOWN));
                }
                if (!SmartCity.isBlockValid(blockPos) && !SmartCity.isBlockValid(blockPos2) && SmartCity.isBlockValid(blockPos3)) {
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos3, EnumFacing.DOWN));
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos3, EnumFacing.DOWN));
                }
                if (!SmartCity.isBlockValid(blockPos) && !SmartCity.isBlockValid(blockPos2) && !SmartCity.isBlockValid(blockPos3) && SmartCity.isBlockValid(blockPos4)) {
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos4, EnumFacing.DOWN));
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos4, EnumFacing.DOWN));
                }
                if (!SmartCity.isBlockValid(blockPos) && !SmartCity.isBlockValid(blockPos2) && !SmartCity.isBlockValid(blockPos3) && !SmartCity.isBlockValid(blockPos4) || SmartCity.mc.player.getPositionVector().distanceTo(vec3d) > d) {
                    return;
                }
            }
            if (this.crystalCheck.getValue().booleanValue() && this.target != null) {
                if (this.canPlaceCrystal(blockPos5, this.oneFifteen.getValue()) && SmartCity.isBlockValid(blockPos)) {
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos, EnumFacing.DOWN));
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos, EnumFacing.DOWN));
                    this.renderPos = blockPos;
                } else if (this.canPlaceCrystal(blockPos6, this.oneFifteen.getValue()) && SmartCity.isBlockValid(blockPos2)) {
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos2, EnumFacing.DOWN));
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos2, EnumFacing.DOWN));
                    this.renderPos = blockPos2;
                } else if (this.canPlaceCrystal(blockPos7, this.oneFifteen.getValue()) && SmartCity.isBlockValid(blockPos3)) {
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos3, EnumFacing.DOWN));
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos3, EnumFacing.DOWN));
                    this.renderPos = blockPos3;
                } else if (this.canPlaceCrystal(blockPos8, this.oneFifteen.getValue()) && SmartCity.isBlockValid(blockPos4)) {
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos4, EnumFacing.DOWN));
                    SmartCity.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos4, EnumFacing.DOWN));
                    this.renderPos = blockPos4;
                } else {
                    if (SmartCity.mc.player.getPositionVector().distanceTo(vec3d) > d) {
                        this.renderPos = null;
                        return;
                    }
                    this.renderPos = null;
                    return;
                }
            }
            if (this.prePlaceCrystal.getValue().booleanValue() && this.target != null) {
                if (this.canPlaceCrystal(blockPos5, this.oneFifteen.getValue())) {
                    BlockUtil.placeCrystalOnBlock(blockPos5, EnumHand.MAIN_HAND, true, false, this.silentPlace.getValue());
                }
                if (!this.canPlaceCrystal(blockPos5, this.oneFifteen.getValue()) && this.canPlaceCrystal(blockPos6, this.oneFifteen.getValue())) {
                    BlockUtil.placeCrystalOnBlock(blockPos6, EnumHand.MAIN_HAND, true, false, this.silentPlace.getValue());
                }
                if (!this.canPlaceCrystal(blockPos5, this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos6, this.oneFifteen.getValue()) && this.canPlaceCrystal(blockPos7, this.oneFifteen.getValue())) {
                    BlockUtil.placeCrystalOnBlock(blockPos7, EnumHand.MAIN_HAND, true, false, this.silentPlace.getValue());
                }
                if (!this.canPlaceCrystal(blockPos5, this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos6, this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos7, this.oneFifteen.getValue()) && this.canPlaceCrystal(blockPos8, this.oneFifteen.getValue())) {
                    BlockUtil.placeCrystalOnBlock(blockPos8, EnumHand.MAIN_HAND, true, false, this.silentPlace.getValue());
                }
                if (!this.canPlaceCrystal(blockPos5, this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos6, this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos7, this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos8, this.oneFifteen.getValue()) || SmartCity.mc.player.getPositionVector().distanceTo(vec3d) > d) {
                    return;
                }
            }
        }
    }

    @Override
    public String getDisplayInfo() {
        if (this.target != null) {
            return this.target.getName();
        }
        return null;
    }

    @Override
    @SubscribeEvent
    public void onRender3D(Render3DEvent render3DEvent) {
        if (this.pickOnly.getValue().booleanValue() && !(SmartCity.mc.player.inventory.getCurrentItem().getItem() instanceof ItemPickaxe)) {
            return;
        }
        if (SmartCity.nullCheck()) {
            return;
        }
        this.target = (EntityPlayer)EntityUtil2.getTarget(true, false, false, false, false, 10.0, EntityUtil2.toMode("Closest"));
        if (this.target == null) {
            return;
        }
        Double d = this.range.getValue().doubleValue();
        Vec3d vec3d = this.target.getPositionVector();
        if (SmartCity.mc.player.getPositionVector().distanceTo(vec3d) <= d) {
            BlockPos blockPos = new BlockPos(vec3d.addVector(1.0, 0.0, 0.0));
            BlockPos blockPos2 = new BlockPos(vec3d.addVector(-1.0, 0.0, 0.0));
            BlockPos blockPos3 = new BlockPos(vec3d.addVector(0.0, 0.0, 1.0));
            BlockPos blockPos4 = new BlockPos(vec3d.addVector(0.0, 0.0, -1.0));
            BlockPos blockPos5 = new BlockPos(vec3d.addVector(2.0, 0.0, 0.0));
            BlockPos blockPos6 = new BlockPos(vec3d.addVector(-2.0, 0.0, 0.0));
            BlockPos blockPos7 = new BlockPos(vec3d.addVector(0.0, 0.0, 2.0));
            BlockPos blockPos8 = new BlockPos(vec3d.addVector(0.0, 0.0, -2.0));
            if (!this.isPlayerOccupied() && !this.crystalCheck.getValue().booleanValue()) {
                if (SmartCity.isBlockValid(blockPos)) {
                    RenderUtil.drawBoxESP(blockPos, this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue().floatValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
                }
                if (!SmartCity.isBlockValid(blockPos) && SmartCity.isBlockValid(blockPos2)) {
                    RenderUtil.drawBoxESP(blockPos2, this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue().floatValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
                }
                if (!SmartCity.isBlockValid(blockPos) && !SmartCity.isBlockValid(blockPos2) && SmartCity.isBlockValid(blockPos3)) {
                    RenderUtil.drawBoxESP(blockPos3, this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue().floatValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
                }
                if (!SmartCity.isBlockValid(blockPos) && !SmartCity.isBlockValid(blockPos2) && !SmartCity.isBlockValid(blockPos3) && SmartCity.isBlockValid(blockPos4)) {
                    RenderUtil.drawBoxESP(blockPos4, this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue().floatValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
                }
                if (!SmartCity.isBlockValid(blockPos) && !SmartCity.isBlockValid(blockPos2) && !SmartCity.isBlockValid(blockPos3) && !SmartCity.isBlockValid(blockPos4) || SmartCity.mc.player.getPositionVector().distanceTo(vec3d) > d) {
                    return;
                }
            }
            if (this.crystalCheck.getValue().booleanValue() && this.target != null && this.renderPos != null) {
                RenderUtil.drawBoxESP(this.renderPos, this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue().floatValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
            }
            if (this.prePlaceCrystal.getValue().booleanValue() && this.target != null) {
                if (this.canPlaceCrystal(blockPos5, this.oneFifteen.getValue())) {
                    RenderUtil.drawBoxESP(blockPos5, this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue().floatValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
                }
                if (!this.canPlaceCrystal(blockPos5, this.oneFifteen.getValue()) && this.canPlaceCrystal(blockPos6, this.oneFifteen.getValue())) {
                    RenderUtil.drawBoxESP(blockPos6, this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue().floatValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
                }
                if (!this.canPlaceCrystal(blockPos5, this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos6, this.oneFifteen.getValue()) && this.canPlaceCrystal(blockPos7, this.oneFifteen.getValue())) {
                    RenderUtil.drawBoxESP(blockPos7, this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue().floatValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
                }
                if (!this.canPlaceCrystal(blockPos5, this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos6, this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos7, this.oneFifteen.getValue()) && this.canPlaceCrystal(blockPos8, this.oneFifteen.getValue())) {
                    RenderUtil.drawBoxESP(blockPos8, this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), this.cSync.getValue() != false ? Colors.getInstance().getCurrentColor() : new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue().floatValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
                }
                if (!this.canPlaceCrystal(blockPos5, this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos6, this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos7, this.oneFifteen.getValue()) && !this.canPlaceCrystal(blockPos8, this.oneFifteen.getValue()) || SmartCity.mc.player.getPositionVector().distanceTo(vec3d) > d) {
                    return;
                }
            }
        }
    }

    public SmartCity() {
        super("CityBoss", "Automatically mines city blocks of opponents", Module.Category.COMBAT, true, false, false);
        this.range = this.register(new Setting<Integer>("Range", 5, 1, 10));
        this.rotate = this.register(new Setting<Boolean>("Rotate", false));
        this.oneFifteen = this.register(new Setting<Boolean>("1.15", false));
        this.render = this.register(new Setting<Boolean>("Render", true));
        this.crystalCheck = this.register(new Setting<Boolean>("Crystal Check", true));
        this.pickOnly = this.register(new Setting<Boolean>("Pickaxe Check", true));
        this.silentPlace = this.register(new Setting<Boolean>("Silent Place", true));
        this.prePlaceCrystal = this.register(new Setting<Boolean>("PrePlace Crystals", false));
        this.outline = this.register(new Setting<Boolean>("Outline", Boolean.valueOf(true), bl -> this.render.getValue()));
        this.cSync = this.register(new Setting<Object>("Color Sync", Boolean.valueOf(true), object -> this.render.getValue()));
        this.cRed = this.register(new Setting<Object>("OL-Red", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.render.getValue()));
        this.cGreen = this.register(new Setting<Object>("OL-Green", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.render.getValue()));
        this.cBlue = this.register(new Setting<Object>("OL-Blue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.render.getValue()));
        this.cAlpha = this.register(new Setting<Object>("OL-Alpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.render.getValue()));
        this.red = this.register(new Setting<Integer>("Red", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), n -> this.render.getValue()));
        this.green = this.register(new Setting<Integer>("Green", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), n -> this.render.getValue()));
        this.blue = this.register(new Setting<Integer>("Blue", Integer.valueOf(155), Integer.valueOf(0), Integer.valueOf(255), n -> this.render.getValue()));
        this.alpha = this.register(new Setting<Integer>("Alpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), n -> this.render.getValue()));
        this.box = this.register(new Setting<Boolean>("Box", Boolean.valueOf(true), bl -> this.render.getValue()));
        this.boxAlpha = this.register(new Setting<Integer>("BoxAlpha", Integer.valueOf(125), Integer.valueOf(0), Integer.valueOf(255), n -> this.render.getValue()));
        this.lineWidth = this.register(new Setting<Float>("LineWidth", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(5.0f), f -> this.render.getValue()));
        this.renderPos = null;
        this.timer = new Timer();
    }

    public static boolean isBlockValid(BlockPos blockPos) {
        IBlockState iBlockState = SmartCity.mc.world.getBlockState(blockPos);
        Block block = iBlockState.getBlock();
        return block.getBlockHardness(iBlockState, (World)SmartCity.mc.world, blockPos) != -1.0f;
    }
}

