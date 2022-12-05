//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockDeadBush
 *  net.minecraft.block.BlockSoulSand
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3i
 */
package me.snow.aclient.module.modules.hidden;

import java.util.List;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.combat.AliceAura;
import me.snow.aclient.util.InteractionUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockSoulSand;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class AutoWither
extends Module {
    public /* synthetic */ Setting<Integer> actionShift;
    private /* synthetic */ int headSlot;
    private /* synthetic */ int bodySlot;
    public /* synthetic */ Setting<Integer> delay;
    private /* synthetic */ boolean shifting;
    private /* synthetic */ BlockPos placeTarget;
    private /* synthetic */ boolean rotationPlaceableZ;
    public /* synthetic */ Setting<Boolean> rotate;
    private /* synthetic */ int delayStep;
    public /* synthetic */ Setting<Integer> range;
    private /* synthetic */ int buildStage;
    public /* synthetic */ Setting<Boolean> triggerable;
    private /* synthetic */ boolean rotationPlaceableX;

    public AutoWither() {
        super("AutoWither", "Automatically places and spawns wither", Module.Category.MISC, true, false, false);
        this.rotate = this.register(new Setting<Boolean>("Rotate", true));
        this.triggerable = this.register(new Setting<Boolean>("Triggerable", true));
        this.range = this.register(new Setting<Integer>("Range", 5, 2, 10));
        this.actionShift = this.register(new Setting<Integer>("Block/Place", 1, 1, 5));
        this.delay = this.register(new Setting<Integer>("Delay", 15, 1, 30));
        this.shifting = false;
    }

    @Override
    public void onEnable() {
        if (AutoWither.mc.player == null) {
            this.toggle();
            return;
        }
        this.buildStage = 1;
        this.delayStep = 1;
        this.shifting = false;
    }

    private static EnumFacing getPlaceableSide(BlockPos blockPos) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            IBlockState iBlockState;
            BlockPos blockPos2 = blockPos.offset(enumFacing);
            if (!AutoWither.mc.world.getBlockState(blockPos2).getBlock().canCollideCheck(AutoWither.mc.world.getBlockState(blockPos2), false) || (iBlockState = AutoWither.mc.world.getBlockState(blockPos2)).getMaterial().isReplaceable() || iBlockState.getBlock() instanceof BlockTallGrass || iBlockState.getBlock() instanceof BlockDeadBush) continue;
            return enumFacing;
        }
        return null;
    }

    private boolean testWitherStructure() {
        boolean bl = true;
        this.rotationPlaceableX = true;
        this.rotationPlaceableZ = true;
        boolean bl2 = false;
        if (AutoWither.mc.world.getBlockState(this.placeTarget) == null) {
            return false;
        }
        Block block = AutoWither.mc.world.getBlockState(this.placeTarget).getBlock();
        if (block instanceof BlockTallGrass || block instanceof BlockDeadBush) {
            bl2 = true;
        }
        if (AutoWither.getPlaceableSide(this.placeTarget.up()) == null) {
            return false;
        }
        for (BlockPos blockPos : BodyParts.bodyBase) {
            if (!this.placingIsBlocked(this.placeTarget.add((Vec3i)blockPos))) continue;
            bl = false;
        }
        for (BlockPos blockPos : BodyParts.ArmsX) {
            if (!this.placingIsBlocked(this.placeTarget.add((Vec3i)blockPos)) && !this.placingIsBlocked(this.placeTarget.add((Vec3i)blockPos.down()))) continue;
            this.rotationPlaceableX = false;
        }
        for (BlockPos blockPos : BodyParts.ArmsZ) {
            if (!this.placingIsBlocked(this.placeTarget.add((Vec3i)blockPos)) && !this.placingIsBlocked(this.placeTarget.add((Vec3i)blockPos.down()))) continue;
            this.rotationPlaceableZ = false;
        }
        for (BlockPos blockPos : BodyParts.headsX) {
            if (!this.placingIsBlocked(this.placeTarget.add((Vec3i)blockPos))) continue;
            this.rotationPlaceableX = false;
        }
        for (BlockPos blockPos : BodyParts.headsZ) {
            if (!this.placingIsBlocked(this.placeTarget.add((Vec3i)blockPos))) continue;
            this.rotationPlaceableZ = false;
        }
        return !bl2 && bl && (this.rotationPlaceableX || this.rotationPlaceableZ);
    }

    @Override
    public void onUpdate() {
        if (this.buildStage == 1) {
            InteractionUtil.Placement placement;
            if (this.shifting) {
                this.shifting = false;
            } else {
                this.rotationPlaceableX = false;
                this.rotationPlaceableZ = false;
                if (!this.checkBlocksInHotbar()) {
                    this.toggle();
                    return;
                }
                List<BlockPos> list = AliceAura.getSphere(AutoWither.mc.player.getPosition().down(), this.range.getValue().intValue(), this.range.getValue(), false, true, 0);
                boolean bl = true;
                for (BlockPos blockPos : list) {
                    this.placeTarget = blockPos.down();
                    if (!this.testWitherStructure()) continue;
                    bl = false;
                    break;
                }
                if (bl) {
                    if (this.triggerable.getValue().booleanValue()) {
                        this.toggle();
                    }
                    return;
                }
            }
            if (AutoWither.mc.player.inventory.currentItem != this.bodySlot) {
                AutoWither.mc.player.inventory.currentItem = this.bodySlot;
                AutoWither.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.bodySlot));
            }
            int n = 0;
            for (BlockPos blockPos : BodyParts.bodyBase) {
                if (!InteractionUtil.canPlaceBlock(this.placeTarget.add((Vec3i)blockPos), false, true) || (placement = InteractionUtil.preparePlacement(this.placeTarget.add((Vec3i)blockPos), this.rotate.getValue(), true)) == null) continue;
                InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, false);
                if (++n < this.actionShift.getValue()) continue;
                this.shifting = true;
                return;
            }
            if (this.rotationPlaceableX) {
                for (BlockPos blockPos : BodyParts.ArmsX) {
                    if (!InteractionUtil.canPlaceBlock(this.placeTarget.add((Vec3i)blockPos), false, true) || (placement = InteractionUtil.preparePlacement(this.placeTarget.add((Vec3i)blockPos), this.rotate.getValue(), true)) == null) continue;
                    InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, false);
                    if (++n < this.actionShift.getValue()) continue;
                    this.shifting = true;
                    return;
                }
            } else if (this.rotationPlaceableZ) {
                for (BlockPos blockPos : BodyParts.ArmsZ) {
                    if (!InteractionUtil.canPlaceBlock(this.placeTarget.add((Vec3i)blockPos), false, true) || (placement = InteractionUtil.preparePlacement(this.placeTarget.add((Vec3i)blockPos), this.rotate.getValue(), true)) == null) continue;
                    InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, false);
                    if (++n < this.actionShift.getValue()) continue;
                    this.shifting = true;
                    return;
                }
            }
            this.buildStage = 2;
        } else if (this.buildStage == 2) {
            if (AutoWither.mc.player.inventory.currentItem != this.headSlot) {
                AutoWither.mc.player.inventory.currentItem = this.headSlot;
                AutoWither.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.headSlot));
            }
            int n = 0;
            if (this.rotationPlaceableX) {
                for (BlockPos blockPos : BodyParts.headsX) {
                    InteractionUtil.Placement placement;
                    if (!InteractionUtil.canPlaceBlock(this.placeTarget.add((Vec3i)blockPos), false, true) || (placement = InteractionUtil.preparePlacement(this.placeTarget.add((Vec3i)blockPos), this.rotate.getValue(), true)) == null) continue;
                    InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, false);
                    if (++n < this.actionShift.getValue()) continue;
                    return;
                }
            } else if (this.rotationPlaceableZ) {
                for (BlockPos blockPos : BodyParts.headsZ) {
                    InteractionUtil.Placement placement;
                    if (!InteractionUtil.canPlaceBlock(this.placeTarget.add((Vec3i)blockPos), false, true) || (placement = InteractionUtil.preparePlacement(this.placeTarget.add((Vec3i)blockPos), this.rotate.getValue(), true)) == null) continue;
                    InteractionUtil.placeBlock(placement, EnumHand.MAIN_HAND, false);
                    if (++n < this.actionShift.getValue()) continue;
                    return;
                }
            }
            if (this.triggerable.getValue().booleanValue()) {
                this.toggle();
            }
            this.buildStage = 3;
        } else if (this.buildStage == 3) {
            if (this.delayStep < this.delay.getValue()) {
                ++this.delayStep;
            } else {
                this.delayStep = 1;
                this.buildStage = 1;
            }
        }
    }

    private boolean placingIsBlocked(BlockPos blockPos) {
        Block block = AutoWither.mc.world.getBlockState(blockPos).getBlock();
        if (!(block instanceof BlockAir)) {
            return true;
        }
        for (Entity entity : AutoWither.mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(blockPos))) {
            if (entity instanceof EntityItem || entity instanceof EntityXPOrb) continue;
            return true;
        }
        return false;
    }

    private boolean checkBlocksInHotbar() {
        this.headSlot = -1;
        this.bodySlot = -1;
        for (int i = 0; i < 9; ++i) {
            Block block;
            ItemStack itemStack = AutoWither.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.field_190927_a) continue;
            if (itemStack.getItem() == Items.SKULL && itemStack.getItemDamage() == 1) {
                if (AutoWither.mc.player.inventory.getStackInSlot(i).func_190916_E() < 3) continue;
                this.headSlot = i;
                continue;
            }
            if (!(itemStack.getItem() instanceof ItemBlock) || !((block = ((ItemBlock)itemStack.getItem()).getBlock()) instanceof BlockSoulSand) || AutoWither.mc.player.inventory.getStackInSlot(i).func_190916_E() < 4) continue;
            this.bodySlot = i;
        }
        return this.bodySlot != -1 && this.headSlot != -1;
    }

    private static class BodyParts {
        private static final /* synthetic */ BlockPos[] headsZ;
        private static final /* synthetic */ BlockPos[] head;
        private static final /* synthetic */ BlockPos[] headsX;
        private static final /* synthetic */ BlockPos[] ArmsX;
        private static final /* synthetic */ BlockPos[] ArmsZ;
        private static final /* synthetic */ BlockPos[] bodyBase;

        static {
            bodyBase = new BlockPos[]{new BlockPos(0, 1, 0), new BlockPos(0, 2, 0)};
            ArmsX = new BlockPos[]{new BlockPos(-1, 2, 0), new BlockPos(1, 2, 0)};
            ArmsZ = new BlockPos[]{new BlockPos(0, 2, -1), new BlockPos(0, 2, 1)};
            headsX = new BlockPos[]{new BlockPos(0, 3, 0), new BlockPos(-1, 3, 0), new BlockPos(1, 3, 0)};
            headsZ = new BlockPos[]{new BlockPos(0, 3, 0), new BlockPos(0, 3, -1), new BlockPos(0, 3, 1)};
            head = new BlockPos[]{new BlockPos(0, 3, 0)};
        }

        private BodyParts() {
        }
    }
}

