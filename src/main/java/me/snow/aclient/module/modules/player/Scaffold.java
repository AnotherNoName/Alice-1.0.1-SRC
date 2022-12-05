//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.player;

import java.awt.Color;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.MoveEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.Timer;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Scaffold
extends Module {
    public /* synthetic */ Setting<Boolean> rotate;
    public /* synthetic */ Setting<Boolean> autoswap;
    public /* synthetic */ Setting<Boolean> render;
    public /* synthetic */ Color color;
    private /* synthetic */ Timer timer;
    public /* synthetic */ Setting<Boolean> echestholding;
    private /* synthetic */ BlockPosWithFacing currentblock;
    public /* synthetic */ Setting<Boolean> safewalk;
    public /* synthetic */ Setting<Boolean> tower;

    private boolean isOffsetBBEmpty(double d, double d2, double d3) {
        return Scaffold.mc.world.getCollisionBoxes((Entity)Scaffold.mc.player, Scaffold.mc.player.getEntityBoundingBox().offset(d, d2, d3)).isEmpty();
    }

    private boolean isBlockValid(Block block) {
        return block.getDefaultState().getMaterial().isSolid();
    }

    @SubscribeEvent
    public void onMove(MoveEvent moveEvent) {
        if (Scaffold.nullCheck()) {
            return;
        }
        if (this.safewalk.getValue().booleanValue()) {
            this.doSafeWalk(moveEvent);
        }
    }

    private int findBlockToPlace() {
        if (Scaffold.mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock && this.isBlockValid(((ItemBlock)Scaffold.mc.player.getHeldItemMainhand().getItem()).getBlock())) {
            return Scaffold.mc.player.inventory.currentItem;
        }
        int n = 0;
        int n2 = 0;
        while (n2 < 9) {
            if (Scaffold.mc.player.inventory.getStackInSlot(n).func_190916_E() != 0 && Scaffold.mc.player.inventory.getStackInSlot(n).getItem() instanceof ItemBlock && (!this.echestholding.getValue().booleanValue() || this.echestholding.getValue().booleanValue() && !Scaffold.mc.player.inventory.getStackInSlot(n).getItem().equals((Object)Item.getItemFromBlock((Block)Blocks.ENDER_CHEST))) && this.isBlockValid(((ItemBlock)Scaffold.mc.player.inventory.getStackInSlot(n).getItem()).getBlock())) {
                return n;
            }
            n2 = ++n;
        }
        return -1;
    }

    private Vec3d getEyePosition() {
        return new Vec3d(Scaffold.mc.player.posX, Scaffold.mc.player.posY + (double)Scaffold.mc.player.getEyeHeight(), Scaffold.mc.player.posZ);
    }

    public Scaffold() {
        super("Scaffold", "sCaff0ld", Module.Category.PLAYER, true, false, false);
        this.color = new Color(Color.CYAN.getRed(), Color.CYAN.getGreen(), Color.CYAN.getBlue(), 50);
        this.rotate = this.register(new Setting<Boolean>("Rotate", true));
        this.autoswap = this.register(new Setting<Boolean>("AutoSwap", true));
        this.tower = this.register(new Setting<Boolean>("Tower", true));
        this.safewalk = this.register(new Setting<Boolean>("SafeWalk", true));
        this.echestholding = this.register(new Setting<Boolean>("EchestHolding", false));
        this.render = this.register(new Setting<Boolean>("Render", true));
        this.timer = new Timer();
    }

    private BlockPosWithFacing checkNearBlocksExtended(BlockPos blockPos) {
        BlockPosWithFacing blockPosWithFacing = null;
        blockPosWithFacing = this.checkNearBlocks(blockPos);
        if (blockPosWithFacing != null) {
            return blockPosWithFacing;
        }
        blockPosWithFacing = this.checkNearBlocks(blockPos.add(-1, 0, 0));
        if (blockPosWithFacing != null) {
            return blockPosWithFacing;
        }
        blockPosWithFacing = this.checkNearBlocks(blockPos.add(1, 0, 0));
        if (blockPosWithFacing != null) {
            return blockPosWithFacing;
        }
        blockPosWithFacing = this.checkNearBlocks(blockPos.add(0, 0, 1));
        if (blockPosWithFacing != null) {
            return blockPosWithFacing;
        }
        blockPosWithFacing = this.checkNearBlocks(blockPos.add(0, 0, -1));
        if (blockPosWithFacing != null) {
            return blockPosWithFacing;
        }
        blockPosWithFacing = this.checkNearBlocks(blockPos.add(-2, 0, 0));
        if (blockPosWithFacing != null) {
            return blockPosWithFacing;
        }
        blockPosWithFacing = this.checkNearBlocks(blockPos.add(2, 0, 0));
        if (blockPosWithFacing != null) {
            return blockPosWithFacing;
        }
        blockPosWithFacing = this.checkNearBlocks(blockPos.add(0, 0, 2));
        if (blockPosWithFacing != null) {
            return blockPosWithFacing;
        }
        blockPosWithFacing = this.checkNearBlocks(blockPos.add(0, 0, -2));
        if (blockPosWithFacing != null) {
            return blockPosWithFacing;
        }
        blockPosWithFacing = this.checkNearBlocks(blockPos.add(0, -1, 0));
        BlockPos blockPos2 = blockPos.add(0, -1, 0);
        if (blockPosWithFacing != null) {
            return blockPosWithFacing;
        }
        blockPosWithFacing = this.checkNearBlocks(blockPos2.add(1, 0, 0));
        if (blockPosWithFacing != null) {
            return blockPosWithFacing;
        }
        blockPosWithFacing = this.checkNearBlocks(blockPos2.add(-1, 0, 0));
        if (blockPosWithFacing != null) {
            return blockPosWithFacing;
        }
        blockPosWithFacing = this.checkNearBlocks(blockPos2.add(0, 0, 1));
        if (blockPosWithFacing != null) {
            return blockPosWithFacing;
        }
        return this.checkNearBlocks(blockPos2.add(0, 0, -1));
    }

    private int countValidBlocks() {
        int n = 0;
        for (int i = 36; i < 45; ++i) {
            ItemStack itemStack;
            if (!Scaffold.mc.player.inventoryContainer.getSlot(i).getHasStack() || !((itemStack = Scaffold.mc.player.inventoryContainer.getSlot(i).getStack()).getItem() instanceof ItemBlock) || !this.isBlockValid(((ItemBlock)itemStack.getItem()).getBlock())) continue;
            n += itemStack.func_190916_E();
        }
        return n;
    }

    private BlockPosWithFacing checkNearBlocks(BlockPos blockPos) {
        if (this.isBlockValid(Scaffold.mc.world.getBlockState(blockPos.add(0, -1, 0)).getBlock())) {
            return new BlockPosWithFacing(blockPos.add(0, -1, 0), EnumFacing.UP);
        }
        if (this.isBlockValid(Scaffold.mc.world.getBlockState(blockPos.add(-1, 0, 0)).getBlock())) {
            return new BlockPosWithFacing(blockPos.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (this.isBlockValid(Scaffold.mc.world.getBlockState(blockPos.add(1, 0, 0)).getBlock())) {
            return new BlockPosWithFacing(blockPos.add(1, 0, 0), EnumFacing.WEST);
        }
        if (this.isBlockValid(Scaffold.mc.world.getBlockState(blockPos.add(0, 0, 1)).getBlock())) {
            return new BlockPosWithFacing(blockPos.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (this.isBlockValid(Scaffold.mc.world.getBlockState(blockPos.add(0, 0, -1)).getBlock())) {
            return new BlockPosWithFacing(blockPos.add(0, 0, -1), EnumFacing.SOUTH);
        }
        return null;
    }

    private float[] getRotations(BlockPos blockPos, EnumFacing enumFacing) {
        Vec3d vec3d = new Vec3d((double)blockPos.getX() + 0.5, Scaffold.mc.world.getBlockState((BlockPos)blockPos).getSelectedBoundingBox((World)Scaffold.mc.world, (BlockPos)blockPos).maxY - 0.01, (double)blockPos.getZ() + 0.5);
        vec3d = vec3d.add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
        Vec3d vec3d2 = this.getEyePosition();
        double d = vec3d.xCoord - vec3d2.xCoord;
        double d2 = vec3d.yCoord - vec3d2.yCoord;
        double d3 = vec3d.zCoord - vec3d2.zCoord;
        double d4 = d;
        double d5 = d3;
        double d6 = Math.sqrt(d4 * d4 + d5 * d5);
        float f = (float)(Math.toDegrees(Math.atan2(d3, d)) - 90.0);
        float f2 = (float)(-Math.toDegrees(Math.atan2(d2, d6)));
        float[] arrf = new float[]{Scaffold.mc.player.rotationYaw + MathHelper.wrapDegrees((float)(f - Scaffold.mc.player.rotationYaw)), Scaffold.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(f2 - Scaffold.mc.player.rotationPitch))};
        return arrf;
    }

    @SubscribeEvent
    public void doSafeWalk(MoveEvent moveEvent) {
        double d = moveEvent.x;
        double d2 = moveEvent.y;
        double d3 = moveEvent.z;
        if (Scaffold.mc.player.onGround && !Scaffold.mc.player.noClip) {
            double d4 = 0.05;
            while (d != 0.0 && this.isOffsetBBEmpty(d, -2.0, 0.0)) {
                if (d < d4 && d >= -d4) {
                    d = 0.0;
                    continue;
                }
                if (d > 0.0) {
                    d -= d4;
                    continue;
                }
                d += d4;
            }
            while (d3 != 0.0 && this.isOffsetBBEmpty(0.0, -2.0, d3)) {
                if (d3 < d4 && d3 >= -d4) {
                    d3 = 0.0;
                    continue;
                }
                if (d3 > 0.0) {
                    d3 -= d4;
                    continue;
                }
                d3 += d4;
            }
            while (d != 0.0 && d3 != 0.0 && this.isOffsetBBEmpty(d, -2.0, d3)) {
                double d5 = d < d4 && d >= -d4 ? 0.0 : (d = d > 0.0 ? (d = d - d4) : (d = d + d4));
                if (d3 < d4 && d3 >= -d4) {
                    d3 = 0.0;
                    continue;
                }
                if (d3 > 0.0) {
                    d3 -= d4;
                    continue;
                }
                d3 += d4;
            }
        }
        moveEvent.x = d;
        moveEvent.y = d2;
        moveEvent.z = d3;
    }

    @SubscribeEvent
    public void onUpdateWalkingPlayerPost(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        block9: {
            BlockPos blockPos;
            Scaffold scaffold;
            Object object;
            int n;
            int n2;
            block12: {
                block11: {
                    block10: {
                        block8: {
                            Block block;
                            boolean bl;
                            double d;
                            BlockPos blockPos2;
                            Item item;
                            int n3;
                            if (Scaffold.nullCheck()) {
                                return;
                            }
                            if (this.countValidBlocks() <= 0 || Double.compare(Scaffold.mc.player.posY, 257.0) > 0) {
                                this.currentblock = null;
                                return;
                            }
                            if (this.countValidBlocks() <= 0 || !this.autoswap.getValue().booleanValue() && !(Scaffold.mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock)) {
                                return;
                            }
                            if (updateWalkingPlayerEvent.getStage() != 0) break block8;
                            this.currentblock = null;
                            if (!Scaffold.mc.player.isSneaking() && (n3 = this.findBlockToPlace()) != -1 && (item = Scaffold.mc.player.inventory.getStackInSlot(n3).getItem()) instanceof ItemBlock && Scaffold.mc.world.getBlockState(blockPos2 = new BlockPos(Scaffold.mc.player.posX, Scaffold.mc.player.posY - (d = (bl = (block = ((ItemBlock)item).getBlock()).getDefaultState().isFullBlock()) ? 1.0 : 0.01), Scaffold.mc.player.posZ)).getMaterial().isReplaceable() && (bl || this.someblockcheck(n3))) {
                                Scaffold scaffold2 = this;
                                scaffold2.currentblock = this.checkNearBlocksExtended(blockPos2);
                                if (scaffold2.currentblock != null && this.rotate.getValue().booleanValue()) {
                                    float[] arrf = this.getRotations(this.currentblock.blockPos, this.currentblock.enumFacing);
                                    Scaffold.mc.player.rotationYaw = arrf[0];
                                    Scaffold.mc.player.rotationPitch = arrf[1];
                                    return;
                                }
                            }
                            break block9;
                        }
                        if (this.currentblock == null) break block9;
                        n2 = Scaffold.mc.player.inventory.currentItem;
                        if (!(Scaffold.mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock && this.isBlockValid(((ItemBlock)Scaffold.mc.player.getHeldItemMainhand().getItem()).getBlock()) || !this.autoswap.getValue().booleanValue() || (n = this.findBlockToPlace()) == -1)) {
                            Scaffold.mc.player.inventory.currentItem = n;
                            Scaffold.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(Scaffold.mc.player.inventory.currentItem));
                        }
                        if (!Scaffold.mc.player.movementInput.jump || Scaffold.mc.player.field_191988_bg != 0.0f || Scaffold.mc.player.moveStrafing != 0.0f || !this.tower.getValue().booleanValue()) break block10;
                        Scaffold.mc.player.setVelocity(0.0, 0.42, 0.0);
                        Object[] arrobject = new Object[1];
                        if (!this.timer.passed(1500.0)) break block11;
                        Scaffold.mc.player.motionY = -0.28;
                        object = this;
                        scaffold = object;
                        this.timer.reset();
                        break block12;
                    }
                    this.timer.reset();
                }
                scaffold = this;
            }
            object = blockPos = scaffold.currentblock.blockPos;
            n = Scaffold.mc.world.getBlockState(blockPos).getBlock().onBlockActivated((World)Scaffold.mc.world, object, Scaffold.mc.world.getBlockState(object), (EntityPlayer)Scaffold.mc.player, EnumHand.MAIN_HAND, EnumFacing.DOWN, 0.0f, 0.0f, 0.0f) ? 1 : 0;
            if (n != 0) {
                Scaffold.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Scaffold.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            }
            Scaffold.mc.playerController.processRightClickBlock(Scaffold.mc.player, Scaffold.mc.world, blockPos, this.currentblock.enumFacing, new Vec3d((double)blockPos.getX() + Math.random(), Scaffold.mc.world.getBlockState((BlockPos)blockPos).getSelectedBoundingBox((World)Scaffold.mc.world, (BlockPos)blockPos).maxY - 0.01, (double)blockPos.getZ() + Math.random()), EnumHand.MAIN_HAND);
            Scaffold.mc.player.swingArm(EnumHand.MAIN_HAND);
            if (n != 0) {
                Scaffold.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Scaffold.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            }
            Scaffold.mc.player.inventory.currentItem = n2;
            Scaffold.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(Scaffold.mc.player.inventory.currentItem));
        }
    }

    private boolean someblockcheck(int n) {
        Item item = Scaffold.mc.player.inventory.getStackInSlot(n).getItem();
        if (item instanceof ItemBlock) {
            Vec3d vec3d = Scaffold.mc.player.getPositionVector();
            Block block = ((ItemBlock)item).getBlock();
            return Scaffold.mc.world.rayTraceBlocks(vec3d, vec3d.addVector(0.0, -block.getDefaultState().getSelectedBoundingBox((World)Scaffold.mc.world, (BlockPos)BlockPos.ORIGIN).maxY, 0.0), false, true, false) == null;
        }
        return false;
    }

    public class BlockPosWithFacing {
        public /* synthetic */ EnumFacing enumFacing;
        public /* synthetic */ BlockPos blockPos;

        public BlockPosWithFacing(BlockPos blockPos, EnumFacing enumFacing) {
            this.blockPos = blockPos;
            this.enumFacing = enumFacing;
        }
    }
}

