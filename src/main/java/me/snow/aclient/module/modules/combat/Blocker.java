//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 */
package me.snow.aclient.module.modules.combat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class Blocker
extends Module {
    private /* synthetic */ ArrayList<BlockPos> queue;
    private /* synthetic */ BlockPos b_piston;
    public /* synthetic */ Setting<Boolean> cevBreaker;
    public /* synthetic */ Setting<Boolean> packetPlace;
    public /* synthetic */ Setting<Float> range;
    public static final /* synthetic */ List<Block> blackList;
    private /* synthetic */ Setting<Boolean> piston;
    private /* synthetic */ List<BlockPos> cevPositions;

    public static void rightClickBlock(BlockPos blockPos, EnumFacing enumFacing, Vec3d vec3d, boolean bl) {
        Vec3d vec3d2 = new Vec3d((Vec3i)blockPos).add(vec3d).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
        if (bl) {
            Blocker.rightClickBlock(blockPos, vec3d2, EnumHand.MAIN_HAND, enumFacing);
        } else {
            Blocker.mc.playerController.processRightClickBlock(Blocker.mc.player, Blocker.mc.world, blockPos, enumFacing, vec3d2, EnumHand.MAIN_HAND);
            Blocker.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

    public static boolean placeBlock(BlockPos blockPos, boolean bl) {
        Block block = Blocker.mc.world.getBlockState(blockPos).getBlock();
        if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid)) {
            return false;
        }
        EnumFacing enumFacing = Blocker.getPlaceableSide(blockPos);
        if (enumFacing == null) {
            return false;
        }
        BlockPos blockPos2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing2 = enumFacing.getOpposite();
        Vec3d vec3d = new Vec3d((Vec3i)blockPos2).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing2.getDirectionVec()).scale(0.5));
        if (bl) {
            Blocker.rightClickBlock(blockPos2, vec3d, EnumHand.MAIN_HAND, enumFacing2);
        } else {
            Blocker.mc.playerController.processRightClickBlock(Blocker.mc.player, Blocker.mc.world, blockPos2, enumFacing2, vec3d, EnumHand.MAIN_HAND);
            Blocker.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        return true;
    }

    public static EnumFacing getPlaceableSide(BlockPos blockPos) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            IBlockState iBlockState;
            BlockPos blockPos2 = blockPos.offset(enumFacing);
            if (!Blocker.mc.world.getBlockState(blockPos2).getBlock().canCollideCheck(Blocker.mc.world.getBlockState(blockPos2), false) || (iBlockState = Blocker.mc.world.getBlockState(blockPos2)).getMaterial().isReplaceable() || blackList.contains((Object)Blocker.getBlock(blockPos2))) continue;
            return enumFacing;
        }
        return null;
    }

    @Override
    public void onTick() {
        int n;
        if (this.cevBreaker.getValue().booleanValue()) {
            this.blockCev();
        }
        if ((n = InventoryUtil.getBlockHotbar(Blocks.OBSIDIAN)) == -1) {
            return;
        }
        BlockPos blockPos = PlayerUtil.getPlayerPos();
        if (this.piston.getValue().booleanValue()) {
            int n2;
            BlockPos[] arrblockPos = new BlockPos[]{new BlockPos(2, 1, 0), new BlockPos(-2, 1, 0), new BlockPos(0, 1, 2), new BlockPos(0, 1, -2)};
            BlockPos blockPos2 = new BlockPos(Blocker.mc.player.posX, Blocker.mc.player.posY, Blocker.mc.player.posZ);
            for (n2 = 0; n2 < 4; ++n2) {
                for (int i = 0; i < arrblockPos.length; ++i) {
                    BlockPos blockPos3 = blockPos2.add((Vec3i)arrblockPos[i].add(0, n2, 0));
                    if (Blocker.getBlock(blockPos3) != Blocks.PISTON && Blocker.getBlock(blockPos3) != Blocks.STICKY_PISTON) continue;
                    this.b_piston = blockPos3;
                }
            }
            if (this.b_piston != null) {
                if (Blocker.getBlock(this.b_piston) == Blocks.AIR) {
                    if (Blocker.mc.player.getDistance((double)this.b_piston.getX(), (double)this.b_piston.getY(), (double)this.b_piston.getZ()) > (double)this.range.getValue().floatValue()) {
                        return;
                    }
                    n2 = Blocker.mc.player.inventory.currentItem;
                    Blocker.mc.player.inventory.currentItem = n;
                    Blocker.mc.playerController.updateController();
                    BlockUtil.placeBlock(this.b_piston, EnumHand.MAIN_HAND, true, this.packetPlace.getValue(), false);
                    Blocker.mc.player.inventory.currentItem = n2;
                    Blocker.mc.playerController.updateController();
                }
                if (Blocker.getBlock(this.b_piston) == Blocks.OBSIDIAN || Blocker.mc.player.getDistance((double)this.b_piston.getX(), (double)this.b_piston.getY(), (double)this.b_piston.getZ()) > (double)this.range.getValue().floatValue()) {
                    this.b_piston = null;
                }
            }
        }
    }

    @Override
    public void onDisable() {
        this.cevPositions = new ArrayList<BlockPos>();
    }

    public static IBlockState getBlockState(BlockPos blockPos) {
        return Blocker.mc.world.getBlockState(blockPos);
    }

    private Entity getCrystal(BlockPos blockPos) {
        return Blocker.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal).filter(entity -> EntityUtil.getEntityPos(entity).add(0, -1, 0).equals((Object)blockPos)).min(Comparator.comparing(PlayerUtil::getDistance)).orElse(null);
    }

    public void blockCev() {
        BlockPos blockPos = EntityUtil.getEntityPos((Entity)Blocker.mc.player);
        BlockPos[] arrblockPos = new BlockPos[]{new BlockPos(0, 2, 0), new BlockPos(1, 1, 0), new BlockPos(-1, 1, 0), new BlockPos(0, 1, 1), new BlockPos(0, 1, -1), new BlockPos(1, 1, 1), new BlockPos(1, 1, -1), new BlockPos(-1, 1, 1), new BlockPos(-1, 1, -1)};
        for (BlockPos blockPos2 : arrblockPos) {
            BlockPos blockPos3;
            BlockPos blockPos4 = blockPos.add((Vec3i)blockPos2);
            Entity entity = this.getCrystal(blockPos4);
            if (Objects.isNull((Object)entity) || !Blocker.getBlock(blockPos3 = EntityUtil.getEntityPos(entity).add(0, -1, 0)).equals((Object)Blocks.AIR) && !Blocker.getBlock(blockPos3).equals((Object)Blocks.OBSIDIAN)) continue;
            if (!Blocker.getBlock(blockPos.add(0, 2, 0)).equals((Object)Blocks.AIR)) {
                Blocker.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Blocker.mc.player.posX, (double)blockPos.getY() + 0.21, Blocker.mc.player.posZ, false));
            }
            Blocker.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
            if (this.cevPositions.contains((Object)blockPos3)) continue;
            this.cevPositions.add(blockPos3);
        }
        Iterator<BlockPos> iterator2 = this.cevPositions.iterator();
        while (iterator2.hasNext()) {
            int n;
            BlockPos blockPos5 = (BlockPos)iterator2.next();
            if (!Objects.isNull((Object)this.getCrystal(blockPos5)) || (n = InventoryUtil.getBlockHotbar(Blocks.OBSIDIAN)) == -1) continue;
            InventoryUtil.push();
            Blocker.mc.player.inventory.currentItem = n;
            Blocker.mc.playerController.updateController();
            if (Blocker.getBlock(blockPos5).equals((Object)Blocks.AIR)) {
                Blocker.placeBlock(blockPos5, this.packetPlace.getValue());
                Blocker.rightClickBlock(blockPos5, EnumFacing.UP, this.packetPlace.getValue());
            } else {
                Blocker.placeBlock(blockPos5.add(0, 1, 0), this.packetPlace.getValue());
                InventoryUtil.pop();
            }
            iterator2.remove();
        }
    }

    static {
        blackList = Arrays.asList(new Block[]{Blocks.ENDER_CHEST, Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER, Blocks.TRAPDOOR, Blocks.ENCHANTING_TABLE});
    }

    public Blocker() {
        super("Blocker", "Block gs attack lmao", Module.Category.COMBAT, true, false, false);
        this.piston = this.register(new Setting<Boolean>("Piston", true));
        this.range = this.register(new Setting<Float>("Range", Float.valueOf(5.0f), Float.valueOf(0.5f), Float.valueOf(10.0f)));
        this.cevBreaker = this.register(new Setting<Boolean>("CevBreaker", true));
        this.packetPlace = this.register(new Setting<Boolean>("Packet Place", true));
        this.queue = new ArrayList();
        this.b_piston = null;
        this.cevPositions = new ArrayList<BlockPos>();
    }

    public static Block getBlock(BlockPos blockPos) {
        return Blocker.getBlockState(blockPos).getBlock();
    }

    public static void rightClickBlock(BlockPos blockPos, EnumFacing enumFacing, boolean bl) {
        Vec3d vec3d = new Vec3d((Vec3i)blockPos).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
        if (bl) {
            Blocker.rightClickBlock(blockPos, vec3d, EnumHand.MAIN_HAND, enumFacing);
        } else {
            Blocker.mc.playerController.processRightClickBlock(Blocker.mc.player, Blocker.mc.world, blockPos, enumFacing, vec3d, EnumHand.MAIN_HAND);
            Blocker.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

    public static void rightClickBlock(BlockPos blockPos, Vec3d vec3d, EnumHand enumHand, EnumFacing enumFacing) {
        float f = (float)(vec3d.xCoord - (double)blockPos.getX());
        float f2 = (float)(vec3d.yCoord - (double)blockPos.getY());
        float f3 = (float)(vec3d.zCoord - (double)blockPos.getZ());
        Blocker.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, f, f2, f3));
        Blocker.mc.rightClickDelayTimer = 4;
    }
}

