//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockDeadBush
 *  net.minecraft.block.BlockEnderChest
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockObsidian
 *  net.minecraft.block.BlockSnow
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.DestroyBlockProgress
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 */
package me.snow.aclient.util.skid.oyvey;

import com.google.common.util.concurrent.AtomicDouble;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.PlayerUtil;
import me.snow.aclient.util.RotationUtil;
import me.snow.aclient.util.Util;
import me.snow.aclient.util.skid.oyvey.CrystalUtil;
import me.snow.aclient.util.skid.oyvey.TestUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class BlockUtil2
implements Util {
    public static /* synthetic */ List<Block> unSolidBlocks;
    public static /* synthetic */ List<Block> emptyBlocks;
    public static final /* synthetic */ List<Block> unSafeBlocks;
    public static final /* synthetic */ List<Block> blackList;
    public static final /* synthetic */ List<Block> shulkerList;
    public static /* synthetic */ List<Block> rightclickableBlocks;

    public static boolean canBlockBeSeen(double d, double d2, double d3) {
        return BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + (double)BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d(d, d2 + 1.7, d3), false, true, false) == null;
    }

    public static boolean isIntercepted(BlockPos blockPos) {
        for (Entity entity : BlockUtil2.mc.world.loadedEntityList) {
            BlockPos blockPos2 = blockPos;
            if (entity instanceof EntityItem || entity instanceof EntityEnderCrystal || !new AxisAlignedBB(blockPos2).intersectsWith(entity.getEntityBoundingBox())) continue;
            return true;
        }
        return false;
    }

    public static boolean canBeClicked(BlockPos blockPos) {
        return BlockUtil2.getBlock(blockPos).canCollideCheck(BlockUtil2.getState(blockPos), false);
    }

    public static List<BlockPos> possiblePlacePositions(float f) {
        NonNullList nonNullList = NonNullList.func_191196_a();
        nonNullList.addAll((Collection)BlockUtil2.getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil2.mc.player), f, (int)f, false, true, 0).stream().filter(BlockUtil2::canPlaceCrystal).collect(Collectors.toList()));
        return nonNullList;
    }

    public static Vec3d getEyesPos() {
        return new Vec3d(BlockUtil2.mc.player.posX, BlockUtil2.mc.player.posY + (double)BlockUtil2.mc.player.getEyeHeight(), BlockUtil2.mc.player.posZ);
    }

    public static boolean placeBlocks(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3) {
        Block block = BlockUtil2.mc.world.getBlockState(blockPos).getBlock();
        boolean bl4 = false;
        if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid)) {
            return false;
        }
        EnumFacing enumFacing = BlockUtil2.getPlaceableSide(blockPos);
        if (enumFacing == null) {
            return false;
        }
        BlockPos blockPos2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing2 = enumFacing.getOpposite();
        Vec3d vec3d = new Vec3d((Vec3i)blockPos2).add(new Vec3d(0.5, 0.5, 0.5)).add(new Vec3d(enumFacing2.getDirectionVec()).scale(0.5));
        Block block2 = BlockUtil2.mc.world.getBlockState(blockPos2).getBlock();
        if (!BlockUtil2.mc.player.isSneaking() && (blackList.contains((Object)block2) || shulkerList.contains((Object)block2))) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtil2.mc.player.setSneaking(true);
            bl4 = true;
        }
        if (bl) {
            RotationUtil.faceVectorPacketInstant(vec3d);
        }
        BlockUtil2.rightClickBlock(blockPos, vec3d, enumHand, enumFacing, bl2);
        BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        return bl4 || bl3;
    }

    public static boolean placeBlock(BlockPos blockPos, int n, boolean bl, boolean bl2, boolean bl3) {
        if (TestUtil.isBlockEmpty(blockPos)) {
            EnumFacing[] arrenumFacing;
            int n2 = -1;
            if (n != BlockUtil2.mc.player.inventory.currentItem) {
                n2 = BlockUtil2.mc.player.inventory.currentItem;
                BlockUtil2.mc.player.inventory.currentItem = n;
            }
            for (EnumFacing enumFacing : arrenumFacing = EnumFacing.values()) {
                Block block = BlockUtil2.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock();
                Vec3d vec3d = new Vec3d((double)blockPos.getX() + 0.5 + (double)enumFacing.getFrontOffsetX() * 0.5, (double)blockPos.getY() + 0.5 + (double)enumFacing.getFrontOffsetY() * 0.5, (double)blockPos.getZ() + 0.5 + (double)enumFacing.getFrontOffsetZ() * 0.5);
                if (emptyBlocks.contains((Object)block) || !(BlockUtil2.mc.player.getPositionEyes(mc.getRenderPartialTicks()).distanceTo(vec3d) <= 4.25)) continue;
                float[] arrf = new float[]{BlockUtil2.mc.player.rotationYaw, BlockUtil2.mc.player.rotationPitch};
                if (bl) {
                    BlockUtil2.rotatePacket(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
                }
                if (rightclickableBlocks.contains((Object)block)) {
                    BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                }
                BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                if (rightclickableBlocks.contains((Object)block)) {
                    BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                }
                if (bl2) {
                    BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(arrf[0], arrf[1], BlockUtil2.mc.player.onGround));
                }
                if (bl3) {
                    BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
                }
                if (n2 != -1) {
                    BlockUtil2.mc.player.inventory.currentItem = n2;
                }
                return true;
            }
            if (n2 != -1) {
                BlockUtil2.mc.player.inventory.currentItem = n2;
            }
        }
        return false;
    }

    public static boolean canBreak(BlockPos blockPos) {
        IBlockState iBlockState = BlockUtil2.mc.world.getBlockState(blockPos);
        Block block = iBlockState.getBlock();
        return block.getBlockHardness(iBlockState, (World)BlockUtil2.mc.world, blockPos) != -1.0f;
    }

    public static boolean isBothHole(BlockPos blockPos) {
        for (BlockPos blockPos2 : BlockUtil2.getTouchingBlocks(blockPos)) {
            IBlockState iBlockState = BlockUtil2.mc.world.getBlockState(blockPos2);
            if (iBlockState.getBlock() != Blocks.AIR && (iBlockState.getBlock() == Blocks.BEDROCK || iBlockState.getBlock() == Blocks.OBSIDIAN)) continue;
            return false;
        }
        return true;
    }

    public static boolean isBlockBelowEntitySolid(Entity entity) {
        if (entity != null) {
            BlockPos blockPos = new BlockPos(entity.posX, entity.posY - 1.0, entity.posZ);
            return BlockUtil2.isBlockSolid(blockPos);
        }
        return false;
    }

    public static List<BlockPos> getSphere(double d, BlockPos blockPos, boolean bl, boolean bl2) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        int n = blockPos.getX();
        int n2 = blockPos.getY();
        int n3 = blockPos.getZ();
        int n4 = n - (int)d;
        while ((double)n4 <= (double)n + d) {
            int n5 = n3 - (int)d;
            while ((double)n5 <= (double)n3 + d) {
                int n6 = bl ? n2 - (int)d : n2;
                while (true) {
                    double d2;
                    double d3 = n6;
                    double d4 = d2 = bl ? (double)n2 + d : (double)n2 + d;
                    if (!(d3 < d2)) break;
                    double d5 = (n - n4) * (n - n4) + (n3 - n5) * (n3 - n5) + (bl ? (n2 - n6) * (n2 - n6) : 0);
                    if (!(!(d5 < d * d) || bl2 && d5 < (d - 1.0) * (d - 1.0))) {
                        BlockPos blockPos2 = new BlockPos(n4, n6, n5);
                        arrayList.add(blockPos2);
                    }
                    ++n6;
                }
                ++n5;
            }
            ++n4;
        }
        return arrayList;
    }

    public static Vec3d posToVec3d(BlockPos blockPos) {
        return new Vec3d((Vec3i)blockPos);
    }

    public static int isPositionPlaceable(BlockPos blockPos, boolean bl) {
        return BlockUtil2.isPositionPlaceable(blockPos, bl, true);
    }

    public static void rightClickBlock1(BlockPos blockPos, EnumFacing enumFacing, Vec3d vec3d, boolean bl) {
        Vec3d vec3d2 = new Vec3d((Vec3i)blockPos).add(vec3d).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
        if (bl) {
            BlockUtil2.rightClickBlock3(blockPos, vec3d2, EnumHand.MAIN_HAND, enumFacing);
        } else {
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos, enumFacing, vec3d2, EnumHand.MAIN_HAND);
            BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

    public static boolean isBlocking(BlockPos blockPos, EntityPlayer entityPlayer) {
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos);
        return entityPlayer.getEntityBoundingBox().addCoord(-0.0625, -0.0625, -0.0625).intersectsWith(axisAlignedBB);
    }

    public static BlockPos GetLocalPlayerPosFloored() {
        return new BlockPos(Math.floor(BlockUtil2.mc.player.posX), Math.floor(BlockUtil2.mc.player.posY), Math.floor(BlockUtil2.mc.player.posZ));
    }

    public static List<BlockPos> getSphere(BlockPos blockPos, float f, int n, boolean bl, boolean bl2, int n2) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        int n3 = blockPos.getX();
        int n4 = blockPos.getY();
        int n5 = blockPos.getZ();
        int n6 = n3 - (int)f;
        while ((float)n6 <= (float)n3 + f) {
            int n7 = n5 - (int)f;
            while ((float)n7 <= (float)n5 + f) {
                int n8 = bl2 ? n4 - (int)f : n4;
                while (true) {
                    float f2 = n8;
                    float f3 = bl2 ? (float)n4 + f : (float)(n4 + n);
                    float f4 = f3;
                    if (!(f2 < f3)) break;
                    double d = (n3 - n6) * (n3 - n6) + (n5 - n7) * (n5 - n7) + (bl2 ? (n4 - n8) * (n4 - n8) : 0);
                    if (d < (double)(f * f) && (!bl || d >= (double)((f - 1.0f) * (f - 1.0f)))) {
                        BlockPos blockPos2 = new BlockPos(n6, n8 + n2, n7);
                        arrayList.add(blockPos2);
                    }
                    ++n8;
                }
                ++n7;
            }
            ++n6;
        }
        return arrayList;
    }

    public static Block getBlock(BlockPos blockPos) {
        return BlockUtil2.getState(blockPos).getBlock();
    }

    public static int isPositionPlaceable(BlockPos blockPos, boolean bl, boolean bl2) {
        Block block = BlockUtil2.mc.world.getBlockState(blockPos).getBlock();
        if (!(block instanceof BlockAir || block instanceof BlockLiquid || block instanceof BlockTallGrass || block instanceof BlockFire || block instanceof BlockDeadBush || block instanceof BlockSnow)) {
            return 0;
        }
        if (!BlockUtil2.rayTracePlaceCheck(blockPos, bl, 0.0f)) {
            return -1;
        }
        if (bl2) {
            for (EnumFacing enumFacing : BlockUtil2.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos))) {
                if (enumFacing instanceof EntityItem || enumFacing instanceof EntityXPOrb) continue;
                return 1;
            }
        }
        for (EnumFacing enumFacing : BlockUtil2.getPossibleSides(blockPos)) {
            if (!BlockUtil2.canBeClicked(blockPos.offset(enumFacing))) continue;
            return 3;
        }
        return 2;
    }

    public static boolean canPlaceCrystal(BlockPos blockPos, boolean bl, boolean bl2, boolean bl3) {
        boolean bl4 = bl;
        boolean bl5 = bl2;
        BlockPos blockPos2 = blockPos;
        if (CrystalUtil.mc.world.getBlockState(blockPos2).getBlock() != Blocks.BEDROCK && CrystalUtil.mc.world.getBlockState(blockPos2).getBlock() != Blocks.OBSIDIAN) {
            return false;
        }
        if (CrystalUtil.mc.world.getBlockState(blockPos2.add(0, 1, 0)).getBlock() != Blocks.AIR || !bl5 && CrystalUtil.mc.world.getBlockState(blockPos2.add(0, 2, 0)).getBlock() != Blocks.AIR) {
            return false;
        }
        if (bl4) {
            return CrystalUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2.add(0, 1, 0))).isEmpty() && !bl5 && CrystalUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2.add(0, 2, 0))).isEmpty();
        }
        for (Entity entity : CrystalUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2.add(0, 1, 0)))) {
            if (entity instanceof EntityEnderCrystal) continue;
            return false;
        }
        if (!bl5) {
            for (Entity entity : CrystalUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2.add(0, 2, 0)))) {
                boolean bl6 = bl5;
                if (entity instanceof EntityEnderCrystal || bl6 && entity instanceof EntityPlayer) continue;
                return false;
            }
        }
        return true;
    }

    public static BlockPos vec3dToPos(Vec3d vec3d) {
        return new BlockPos(vec3d);
    }

    public static float getBlockDamage(BlockPos blockPos) {
        try {
            Field field = ReflectionHelper.findField(RenderGlobal.class, (String[])new String[]{"damagedBlocks", "damagedBlocks"});
            field.setAccessible(true);
            HashMap hashMap = (HashMap)field.get((Object)Minecraft.getMinecraft().renderGlobal);
            for (DestroyBlockProgress destroyBlockProgress : hashMap.values()) {
                if (!destroyBlockProgress.getPosition().equals((Object)blockPos) || destroyBlockProgress.getPartialBlockDamage() < 0 || destroyBlockProgress.getPartialBlockDamage() > 10) continue;
                return (float)destroyBlockProgress.getPartialBlockDamage() / 10.0f;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0.0f;
    }

    public static boolean isInterceptedByOther(BlockPos blockPos) {
        for (Entity entity : BlockUtil2.mc.world.loadedEntityList) {
            if (entity.equals((Object)BlockUtil2.mc.player) || !new AxisAlignedBB(blockPos).intersectsWith(entity.getEntityBoundingBox())) continue;
            return true;
        }
        return false;
    }

    public static List<BlockPos> getSphereRealth(float f, boolean bl) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        BlockPos blockPos = new BlockPos(BlockUtil2.mc.player.getPositionVector());
        int n = blockPos.getX();
        int n2 = blockPos.getY();
        int n3 = blockPos.getZ();
        int n4 = (int)f;
        int n5 = n - n4;
        while ((float)n5 <= (float)n + f) {
            int n6 = n3 - n4;
            while ((float)n6 <= (float)n3 + f) {
                int n7 = n2 - n4;
                while ((float)n7 < (float)n2 + f) {
                    if ((float)((n - n5) * (n - n5) + (n3 - n6) * (n3 - n6) + (n2 - n7) * (n2 - n7)) < f * f) {
                        BlockPos blockPos2 = new BlockPos(n5, n7, n6);
                        if (!bl || BlockUtil2.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR) {
                            arrayList.add(blockPos2);
                        }
                    }
                    ++n7;
                }
                ++n6;
            }
            ++n5;
        }
        return arrayList;
    }

    public static void placeCrystalOnBlock(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3) {
        RayTraceResult rayTraceResult = BlockUtil2.mc.world.rayTraceBlocks(new Vec3d(BlockUtil2.mc.player.posX, BlockUtil2.mc.player.posY + (double)BlockUtil2.mc.player.getEyeHeight(), BlockUtil2.mc.player.posZ), new Vec3d((double)blockPos.getX() + 0.5, (double)blockPos.getY() - 0.5, (double)blockPos.getZ() + 0.5));
        EnumFacing enumFacing = rayTraceResult == null || rayTraceResult.sideHit == null ? EnumFacing.UP : rayTraceResult.sideHit;
        int n = BlockUtil2.mc.player.inventory.currentItem;
        int n2 = InventoryUtil.getItemHotbar(Items.END_CRYSTAL);
        if (enumHand == EnumHand.MAIN_HAND && bl3 && n2 != -1 && n2 != BlockUtil2.mc.player.inventory.currentItem) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n2));
        }
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, 0.0f, 0.0f, 0.0f));
        if (enumHand == EnumHand.MAIN_HAND && bl3 && n2 != -1 && n2 != BlockUtil2.mc.player.inventory.currentItem) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
        }
        if (bl) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketAnimation(bl2 ? enumHand : EnumHand.MAIN_HAND));
        }
    }

    public static void debugPos(String string, BlockPos blockPos) {
        Command.sendMessage(String.valueOf(new StringBuilder().append(string).append(blockPos.getX()).append("x, ").append(blockPos.getY()).append("y, ").append(blockPos.getZ()).append("z")));
    }

    public static List<BlockPos> getSphere(float f, boolean bl) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        BlockPos blockPos = new BlockPos(BlockUtil2.mc.player.getPositionVector());
        int n = blockPos.getX();
        int n2 = blockPos.getY();
        int n3 = blockPos.getZ();
        int n4 = (int)f;
        int n5 = n - n4;
        while ((float)n5 <= (float)n + f) {
            int n6 = n3 - n4;
            while ((float)n6 <= (float)n3 + f) {
                int n7 = n2 - n4;
                while ((float)n7 < (float)n2 + f) {
                    if ((float)((n - n5) * (n - n5) + (n3 - n6) * (n3 - n6) + (n2 - n7) * (n2 - n7)) < f * f) {
                        BlockPos blockPos2 = new BlockPos(n5, n7, n6);
                        if (!bl || BlockUtil2.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR) {
                            arrayList.add(blockPos2);
                        }
                    }
                    ++n7;
                }
                ++n6;
            }
            ++n5;
        }
        return arrayList;
    }

    public static void placeCrystalOnBlock(BlockPos blockPos, EnumHand enumHand) {
        RayTraceResult rayTraceResult = BlockUtil2.mc.world.rayTraceBlocks(new Vec3d(BlockUtil2.mc.player.posX, BlockUtil2.mc.player.posY + (double)BlockUtil2.mc.player.getEyeHeight(), BlockUtil2.mc.player.posZ), new Vec3d((double)blockPos.getX() + 0.5, (double)blockPos.getY() - 0.5, (double)blockPos.getZ() + 0.5));
        EnumFacing enumFacing = rayTraceResult == null || rayTraceResult.sideHit == null ? EnumFacing.UP : rayTraceResult.sideHit;
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, 0.0f, 0.0f, 0.0f));
    }

    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(BlockUtil2.mc.player.posX), Math.floor(BlockUtil2.mc.player.posY), Math.floor(BlockUtil2.mc.player.posZ));
    }

    public static boolean isBlockUnSolid(BlockPos blockPos) {
        return BlockUtil2.isBlockUnSolid(BlockUtil2.mc.world.getBlockState(blockPos).getBlock());
    }

    public static Vec3d[] convertVec3ds(EntityPlayer entityPlayer, Vec3d[] arrvec3d) {
        return BlockUtil2.convertVec3ds(entityPlayer.getPositionVector(), arrvec3d);
    }

    public static boolean isBlockSolid(BlockPos blockPos) {
        return !BlockUtil2.isBlockUnSolid(blockPos);
    }

    public static BlockPos[] getTouchingBlocks(BlockPos blockPos) {
        return new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()};
    }

    public static boolean isBlockUnSolid(Block block) {
        return unSolidBlocks.contains((Object)block);
    }

    public static boolean placeBlockM1nt(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3, boolean bl4, EnumHand enumHand2) {
        boolean bl5 = false;
        EnumFacing enumFacing = BlockUtil2.getFirstFacing(blockPos);
        if (enumFacing == null) {
            return bl3;
        }
        BlockPos blockPos2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing2 = enumFacing.getOpposite();
        Vec3d vec3d = new Vec3d((Vec3i)blockPos2).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing2.getDirectionVec()).scale(0.5));
        Block block = Util.mc.world.getBlockState(blockPos2).getBlock();
        if (!Util.mc.player.isSneaking() && (blackList.contains((Object)block) || shulkerList.contains((Object)block))) {
            Util.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Util.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            Util.mc.player.setSneaking(true);
            bl5 = true;
        }
        if (bl) {
            PlayerUtil.faceVector(vec3d, true);
        }
        BlockUtil2.rightClickBlock(blockPos2, vec3d, enumHand, enumFacing2, bl2);
        if (bl4) {
            Util.mc.player.swingArm(enumHand2);
        }
        Util.mc.rightClickDelayTimer = 4;
        return bl5 || bl3;
    }

    public static boolean isBlockAboveEntitySolid(Entity entity) {
        if (entity != null) {
            BlockPos blockPos = new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ);
            return BlockUtil2.isBlockSolid(blockPos);
        }
        return false;
    }

    public static boolean canPlaceCrystal(BlockPos blockPos, boolean bl) {
        block7: {
            BlockPos blockPos2 = blockPos.add(0, 1, 0);
            BlockPos blockPos3 = blockPos.add(0, 2, 0);
            try {
                if (BlockUtil2.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && BlockUtil2.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                    return false;
                }
                if (BlockUtil2.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR || BlockUtil2.mc.world.getBlockState(blockPos3).getBlock() != Blocks.AIR) {
                    return false;
                }
                if (bl) {
                    for (Entity entity : BlockUtil2.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2))) {
                        if (entity instanceof EntityEnderCrystal) continue;
                        return false;
                    }
                    for (Entity entity : BlockUtil2.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos3))) {
                        if (entity instanceof EntityEnderCrystal) continue;
                        return false;
                    }
                    break block7;
                }
                return BlockUtil2.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2)).isEmpty() && BlockUtil2.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos3)).isEmpty();
            }
            catch (Exception exception) {
                return false;
            }
        }
        return true;
    }

    public static Boolean isPosInFov(BlockPos blockPos) {
        int n = RotationUtil.getDirection4D();
        if (n == 0 && (double)blockPos.getZ() - BlockUtil2.mc.player.getPositionVector().zCoord < 0.0) {
            return false;
        }
        if (n == 1 && (double)blockPos.getX() - BlockUtil2.mc.player.getPositionVector().xCoord > 0.0) {
            return false;
        }
        if (n == 2 && (double)blockPos.getZ() - BlockUtil2.mc.player.getPositionVector().zCoord > 0.0) {
            return false;
        }
        return n != 3 || (double)blockPos.getX() - BlockUtil2.mc.player.getPositionVector().xCoord >= 0.0;
    }

    public static EnumFacing getRayTraceFacing(BlockPos blockPos) {
        RayTraceResult rayTraceResult = BlockUtil2.mc.world.rayTraceBlocks(new Vec3d(BlockUtil2.mc.player.posX, BlockUtil2.mc.player.posY + (double)BlockUtil2.mc.player.getEyeHeight(), BlockUtil2.mc.player.posZ), new Vec3d((double)blockPos.getX() + 0.5, (double)blockPos.getX() - 0.5, (double)blockPos.getX() + 0.5));
        if (rayTraceResult == null || rayTraceResult.sideHit == null) {
            return EnumFacing.UP;
        }
        return rayTraceResult.sideHit;
    }

    public static List<BlockPos> getBlockSphere(float f, Class class_) {
        NonNullList nonNullList = NonNullList.func_191196_a();
        nonNullList.addAll((Collection)BlockUtil2.getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil2.mc.player), f, (int)f, false, true, 0).stream().filter(blockPos -> class_.isInstance((Object)BlockUtil2.mc.world.getBlockState(blockPos).getBlock())).collect(Collectors.toList()));
        return nonNullList;
    }

    public static void placeBlockStopSneaking(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3) {
        boolean bl4 = BlockUtil2.placeBlockSmartRotate(blockPos, enumHand, bl, bl2, bl3);
        if (!bl3 && bl4) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
    }

    public static void rightClickBlock(BlockPos blockPos, Vec3d vec3d, EnumHand enumHand, EnumFacing enumFacing, boolean bl) {
        if (bl) {
            float f = (float)(vec3d.xCoord - (double)blockPos.getX());
            float f2 = (float)(vec3d.yCoord - (double)blockPos.getY());
            float f3 = (float)(vec3d.zCoord - (double)blockPos.getZ());
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, f, f2, f3));
        } else {
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos, enumFacing, vec3d, enumHand);
        }
        BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil2.mc.rightClickDelayTimer = 4;
    }

    public static boolean rayTracePlaceCheck(BlockPos blockPos) {
        return BlockUtil2.rayTracePlaceCheck(blockPos, true);
    }

    public static boolean isValidBlock(BlockPos blockPos) {
        Block block = BlockUtil2.mc.world.getBlockState(blockPos).getBlock();
        return !(block instanceof BlockLiquid) && block.getMaterial(null) != Material.AIR;
    }

    public static EnumFacing getDirection(BlockPos blockPos) {
        RayTraceResult rayTraceResult = BlockUtil2.mc.world.rayTraceBlocks(new Vec3d(BlockUtil2.mc.player.posX, BlockUtil2.mc.player.posY + (double)BlockUtil2.mc.player.eyeHeight, BlockUtil2.mc.player.posZ), new Vec3d((Vec3i)blockPos));
        if (rayTraceResult == null) {
            return EnumFacing.UP;
        }
        return rayTraceResult.sideHit;
    }

    public static int findObiInHotbar() {
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = BlockUtil2.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.field_190927_a || !(itemStack.getItem() instanceof ItemBlock)) continue;
            Block block = ((ItemBlock)itemStack.getItem()).getBlock();
            if (block instanceof BlockEnderChest) {
                return i;
            }
            if (!(block instanceof BlockObsidian)) continue;
            return i;
        }
        return -1;
    }

    public static EnumFacing getPlaceableSide(BlockPos blockPos) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            IBlockState iBlockState;
            BlockPos blockPos2 = blockPos.offset(enumFacing);
            if (!BlockUtil2.mc.world.getBlockState(blockPos2).getBlock().canCollideCheck(BlockUtil2.mc.world.getBlockState(blockPos2), false) || (iBlockState = BlockUtil2.mc.world.getBlockState(blockPos2)).getMaterial().isReplaceable()) continue;
            return enumFacing;
        }
        return null;
    }

    public static void rightClickBlock3(BlockPos blockPos, Vec3d vec3d, EnumHand enumHand, EnumFacing enumFacing) {
        float f = (float)(vec3d.xCoord - (double)blockPos.getX());
        float f2 = (float)(vec3d.yCoord - (double)blockPos.getY());
        float f3 = (float)(vec3d.zCoord - (double)blockPos.getZ());
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, f, f2, f3));
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketAnimation(enumHand));
        BlockUtil2.mc.rightClickDelayTimer = 4;
    }

    public static boolean isScaffoldPos(BlockPos blockPos) {
        return BlockUtil2.mc.world.isAirBlock(blockPos) || BlockUtil2.mc.world.getBlockState(blockPos).getBlock() == Blocks.SNOW_LAYER || BlockUtil2.mc.world.getBlockState(blockPos).getBlock() == Blocks.TALLGRASS || BlockUtil2.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid;
    }

    public static boolean placeBlock(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3) {
        boolean bl4 = false;
        EnumFacing enumFacing = BlockUtil2.getFirstFacing(blockPos);
        if (enumFacing == null) {
            return bl3;
        }
        BlockPos blockPos2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing2 = enumFacing.getOpposite();
        Vec3d vec3d = new Vec3d((Vec3i)blockPos2).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing2.getDirectionVec()).scale(0.5));
        Block block = BlockUtil2.mc.world.getBlockState(blockPos2).getBlock();
        if (!BlockUtil2.mc.player.isSneaking() && (blackList.contains((Object)block) || shulkerList.contains((Object)block))) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtil2.mc.player.setSneaking(true);
            bl4 = true;
        }
        if (bl) {
            RotationUtil.faceVector(vec3d, true);
        }
        BlockUtil2.rightClickBlock(blockPos2, vec3d, enumHand, enumFacing2, bl2);
        BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil2.mc.rightClickDelayTimer = 4;
        return bl4 || bl3;
    }

    private static float[] getNeededRotations2(Vec3d vec3d) {
        Vec3d vec3d2 = BlockUtil2.getEyesPos();
        double d = vec3d.xCoord - vec3d2.xCoord;
        double d2 = vec3d.yCoord - vec3d2.yCoord;
        double d3 = vec3d.zCoord - vec3d2.zCoord;
        double d4 = Math.sqrt(d * d + d3 * d3);
        float f = (float)Math.toDegrees(Math.atan2(d3, d)) - 90.0f;
        float f2 = (float)(-Math.toDegrees(Math.atan2(d2, d4)));
        return new float[]{BlockUtil2.mc.player.rotationYaw + MathHelper.wrapDegrees((float)(f - BlockUtil2.mc.player.rotationYaw)), BlockUtil2.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(f2 - BlockUtil2.mc.player.rotationPitch))};
    }

    public static boolean checkForNeighbours(BlockPos blockPos) {
        if (!BlockUtil2.hasNeighbour(blockPos)) {
            for (EnumFacing enumFacing : EnumFacing.values()) {
                BlockPos blockPos2 = blockPos.offset(enumFacing);
                if (!BlockUtil2.hasNeighbour(blockPos2)) continue;
                return true;
            }
            return false;
        }
        return true;
    }

    public static List<Vec3d> getOffsetList(int n, boolean bl) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        arrayList.add(new Vec3d(-1.0, (double)n, 0.0));
        arrayList.add(new Vec3d(1.0, (double)n, 0.0));
        arrayList.add(new Vec3d(0.0, (double)n, -1.0));
        arrayList.add(new Vec3d(0.0, (double)n, 1.0));
        if (bl) {
            arrayList.add(new Vec3d(0.0, (double)(n - 1), 0.0));
        }
        return arrayList;
    }

    public static Vec3d[] convertVec3ds(Vec3d vec3d, Vec3d[] arrvec3d) {
        Vec3d[] arrvec3d2 = new Vec3d[arrvec3d.length];
        for (int i = 0; i < arrvec3d.length; ++i) {
            arrvec3d2[i] = vec3d.add(arrvec3d[i]);
        }
        return arrvec3d2;
    }

    public static List<EnumFacing> getPossibleSides(BlockPos blockPos) {
        ArrayList<EnumFacing> arrayList = new ArrayList<EnumFacing>();
        for (EnumFacing enumFacing : EnumFacing.values()) {
            IBlockState iBlockState;
            BlockPos blockPos2 = blockPos.offset(enumFacing);
            if (!BlockUtil2.mc.world.getBlockState(blockPos2).getBlock().canCollideCheck(BlockUtil2.mc.world.getBlockState(blockPos2), false) || (iBlockState = BlockUtil2.mc.world.getBlockState(blockPos2)).getMaterial().isReplaceable()) continue;
            arrayList.add(enumFacing);
        }
        return arrayList;
    }

    public static BlockPos[] toBlockPos(Vec3d[] arrvec3d) {
        BlockPos[] arrblockPos = new BlockPos[arrvec3d.length];
        for (int i = 0; i < arrvec3d.length; ++i) {
            arrblockPos[i] = new BlockPos(arrvec3d[i]);
        }
        return arrblockPos;
    }

    public static boolean isBlockUnSafe(Block block) {
        return unSafeBlocks.contains((Object)block);
    }

    public static boolean canPlaceInPosition(BlockPos blockPos, boolean bl, boolean bl2) {
        if (!BlockUtil2.mc.world.getBlockState(blockPos).getBlock().isReplaceable((IBlockAccess)BlockUtil2.mc.world, blockPos)) {
            return false;
        }
        if (bl) {
            for (Entity entity : BlockUtil2.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos))) {
                if (entity instanceof EntityItem || entity instanceof EntityXPOrb) continue;
                return false;
            }
        }
        return !bl2 || BlockUtil2.getPlaceableSide(blockPos) != null;
    }

    public static ValidResult valid(BlockPos blockPos) {
        if (!BlockUtil2.mc.world.checkNoEntityCollision(new AxisAlignedBB(blockPos))) {
            return ValidResult.NoEntityCollision;
        }
        if (!BlockUtil2.checkForNeighbours(blockPos)) {
            return ValidResult.NoNeighbors;
        }
        IBlockState iBlockState = BlockUtil2.mc.world.getBlockState(blockPos);
        if (iBlockState.getBlock() == Blocks.AIR) {
            BlockPos[] arrblockPos = new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.up(), blockPos.down()};
            for (BlockPos blockPos2 : arrblockPos) {
                IBlockState iBlockState2 = BlockUtil2.mc.world.getBlockState(blockPos2);
                if (iBlockState2.getBlock() == Blocks.AIR) continue;
                for (EnumFacing enumFacing : EnumFacing.values()) {
                    BlockPos blockPos3 = blockPos.offset(enumFacing);
                    if (!BlockUtil2.mc.world.getBlockState(blockPos3).getBlock().canCollideCheck(BlockUtil2.mc.world.getBlockState(blockPos3), false)) continue;
                    return ValidResult.Ok;
                }
            }
            return ValidResult.NoNeighbors;
        }
        return ValidResult.AlreadyBlockThere;
    }

    public static boolean isPositionPlaceable(BlockPos blockPos, boolean bl, boolean bl2, boolean bl3) {
        if (!BlockUtil2.mc.world.getBlockState(blockPos).getBlock().isReplaceable((IBlockAccess)BlockUtil2.mc.world, blockPos)) {
            return false;
        }
        if (bl) {
            for (Entity entity : BlockUtil2.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos))) {
                if (entity instanceof EntityItem || entity instanceof EntityXPOrb || entity instanceof EntityEnderCrystal && bl3) continue;
                return false;
            }
        }
        return !bl2 || BlockUtil2.getPlaceableSide(blockPos) != null;
    }

    public static boolean canPlaceCrystal(BlockPos blockPos, boolean bl, boolean bl2) {
        BlockPos blockPos2 = blockPos.add(0, 1, 0);
        BlockPos blockPos3 = blockPos.add(0, 2, 0);
        try {
            if (BlockUtil2.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && BlockUtil2.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
            if (!bl2 && BlockUtil2.mc.world.getBlockState(blockPos3).getBlock() != Blocks.AIR || BlockUtil2.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR) {
                return false;
            }
            for (Entity entity : BlockUtil2.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2))) {
                if (entity.isDead || bl && entity instanceof EntityEnderCrystal) continue;
                return false;
            }
            if (!bl2) {
                for (Entity entity : BlockUtil2.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos3))) {
                    if (entity.isDead || bl && entity instanceof EntityEnderCrystal) continue;
                    return false;
                }
            }
        }
        catch (Exception exception) {
            return false;
        }
        return true;
    }

    public static void clickBlock(BlockPos blockPos, EnumFacing enumFacing, EnumHand enumHand, boolean bl) {
        if (bl) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.offset(enumFacing), enumFacing.getOpposite(), enumHand, Float.intBitsToFloat(Float.floatToIntBits(17.0f)), Float.intBitsToFloat(Float.floatToIntBits(26.0f)), Float.intBitsToFloat(Float.floatToIntBits(3.0f))));
        } else {
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), enumHand);
        }
    }

    public static void rightClickBlockLegit(BlockPos blockPos, float f, boolean bl, EnumHand enumHand, AtomicDouble atomicDouble, AtomicDouble atomicDouble2, AtomicBoolean atomicBoolean) {
        Vec3d vec3d = RotationUtil.getEyesPos();
        Vec3d vec3d2 = new Vec3d((Vec3i)blockPos).addVector(0.5, 0.5, 0.5);
        double d = vec3d.squareDistanceTo(vec3d2);
        for (EnumFacing enumFacing : EnumFacing.values()) {
            Vec3d vec3d3 = vec3d2.add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
            double d2 = vec3d.squareDistanceTo(vec3d3);
            if (!(d2 <= MathUtil.square(f)) || !(d2 < d) || BlockUtil2.mc.world.rayTraceBlocks(vec3d, vec3d3, false, true, false) != null) continue;
            if (bl) {
                float[] arrf = RotationUtil.getLegitRotations(vec3d3);
                atomicDouble.set((double)arrf[0]);
                atomicDouble2.set((double)arrf[1]);
                atomicBoolean.set(true);
            }
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos, enumFacing, vec3d3, enumHand);
            BlockUtil2.mc.player.swingArm(enumHand);
            BlockUtil2.mc.rightClickDelayTimer = 4;
            break;
        }
    }

    public static void placeBlocksss(BlockPos blockPos, EnumHand enumHand, boolean bl) {
        boolean bl2 = bl;
        EnumHand enumHand2 = enumHand;
        BlockPos blockPos2 = blockPos;
        if (!BlockUtil2.mc.world.getBlockState(blockPos2).getBlock().isReplaceable((IBlockAccess)BlockUtil2.mc.world, blockPos2)) {
            return;
        }
        if (BlockUtil2.getPlaceableSide(blockPos2) == null) {
            return;
        }
        BlockUtil2.clickBlock(blockPos2, BlockUtil2.getPlaceableSide(blockPos2), enumHand2, bl2);
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketAnimation(enumHand2));
    }

    public static boolean placeBlockSmartRotate(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3) {
        boolean bl4 = false;
        EnumFacing enumFacing = BlockUtil2.getFirstFacing(blockPos);
        Command.sendMessage(enumFacing.toString());
        if (enumFacing == null) {
            return bl3;
        }
        BlockPos blockPos2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing2 = enumFacing.getOpposite();
        Vec3d vec3d = new Vec3d((Vec3i)blockPos2).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing2.getDirectionVec()).scale(0.5));
        Block block = BlockUtil2.mc.world.getBlockState(blockPos2).getBlock();
        if (!BlockUtil2.mc.player.isSneaking() && (blackList.contains((Object)block) || shulkerList.contains((Object)block))) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            bl4 = true;
        }
        if (bl) {
            AliceMain.rotationManager.lookAtVec3d(vec3d);
        }
        BlockUtil2.rightClickBlock(blockPos2, vec3d, enumHand, enumFacing2, bl2);
        BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil2.mc.rightClickDelayTimer = 4;
        return bl4 || bl3;
    }

    public static void placeClient(BlockPos blockPos, EnumHand enumHand, EnumFacing enumFacing, float f, float f2, float f3) {
        ItemStack itemStack = BlockUtil2.mc.player.getHeldItemMainhand();
        if (itemStack.getItem() instanceof ItemBlock) {
            int n;
            IBlockState iBlockState;
            ItemBlock itemBlock = (ItemBlock)itemStack.getItem();
            Block block = itemBlock.getBlock();
            IBlockState iBlockState2 = BlockUtil2.mc.world.getBlockState(blockPos);
            Block block2 = iBlockState2.getBlock();
            if (!block2.isReplaceable((IBlockAccess)BlockUtil2.mc.world, blockPos)) {
                blockPos = blockPos.offset(enumFacing);
            }
            if (!itemStack.func_190926_b() && BlockUtil2.mc.player.canPlayerEdit(blockPos, enumFacing, itemStack) && BlockUtil2.mc.world.func_190527_a(block, blockPos, false, enumFacing, null) && itemBlock.placeBlockAt(itemStack, (EntityPlayer)BlockUtil2.mc.player, (World)BlockUtil2.mc.world, blockPos, enumFacing, f, f2, f3, iBlockState = block.getStateForPlacement((World)BlockUtil2.mc.world, blockPos, enumFacing, f, f2, f3, n = itemBlock.getMetadata(itemStack.getMetadata()), (EntityLivingBase)BlockUtil2.mc.player, enumHand))) {
                iBlockState = BlockUtil2.mc.world.getBlockState(blockPos);
                SoundType soundType = iBlockState.getBlock().getSoundType(iBlockState, (World)BlockUtil2.mc.world, blockPos, (Entity)BlockUtil2.mc.player);
                BlockUtil2.mc.world.playSound((EntityPlayer)BlockUtil2.mc.player, blockPos, soundType.getPlaceSound(), SoundCategory.BLOCKS, (soundType.getVolume() + 1.0f) / 2.0f, soundType.getPitch() * 0.8f);
                if (!BlockUtil2.mc.player.isCreative()) {
                    itemStack.func_190918_g(1);
                }
            }
        }
    }

    public static void rotatePacket(double d, double d2, double d3) {
        double d4 = d - BlockUtil2.mc.player.posX;
        double d5 = d2 - (BlockUtil2.mc.player.posY + (double)BlockUtil2.mc.player.getEyeHeight());
        double d6 = d3 - BlockUtil2.mc.player.posZ;
        double d7 = Math.sqrt(d4 * d4 + d6 * d6);
        float f = (float)Math.toDegrees(Math.atan2(d6, d4)) - 90.0f;
        float f2 = (float)(-Math.toDegrees(Math.atan2(d5, d7)));
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(f, f2, BlockUtil2.mc.player.onGround));
    }

    public static List<BlockPos> possiblePlacePosition(float f, boolean bl, boolean bl2) {
        NonNullList nonNullList = NonNullList.func_191196_a();
        nonNullList.addAll((Collection)BlockUtil2.getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil2.mc.player), f, (int)f, false, true, 0).stream().filter(blockPos -> BlockUtil2.canPlaceCrystal(blockPos, bl, bl2)).collect(Collectors.toList()));
        return nonNullList;
    }

    public static boolean rayTracePlaceCheck(BlockPos blockPos, boolean bl) {
        return BlockUtil2.rayTracePlaceCheck(blockPos, bl, 1.0f);
    }

    public static boolean isBlockNotEmpty(BlockPos blockPos) {
        if (emptyBlocks.contains((Object)BlockUtil2.mc.world.getBlockState(blockPos).getBlock())) {
            Entity entity;
            AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos);
            Iterator iterator2 = BlockUtil2.mc.world.loadedEntityList.iterator();
            do {
                if (iterator2.hasNext()) continue;
                return true;
            } while (!((entity = (Entity)iterator2.next()) instanceof EntityLivingBase) || !axisAlignedBB.intersectsWith(entity.getEntityBoundingBox()));
        }
        return false;
    }

    public static void rightClickBlock2(BlockPos blockPos, EnumFacing enumFacing, boolean bl) {
        Vec3d vec3d = new Vec3d((Vec3i)blockPos).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
        if (bl) {
            BlockUtil2.rightClickBlock3(blockPos, vec3d, EnumHand.MAIN_HAND, enumFacing);
        } else {
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos, enumFacing, vec3d, EnumHand.MAIN_HAND);
            BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

    public static BlockPos placeBlockSHGR(BlockPos blockPos, boolean bl) {
        Block block = BlockUtil2.mc.world.getBlockState(blockPos).getBlock();
        if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid)) {
            return null;
        }
        EnumFacing enumFacing = BlockUtil2.getPlaceableSide(blockPos);
        if (enumFacing == null) {
            return null;
        }
        BlockPos blockPos2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing2 = enumFacing.getOpposite();
        Vec3d vec3d = new Vec3d((Vec3i)blockPos2).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing2.getDirectionVec()).scale(0.5));
        if (bl) {
            BlockUtil2.rightClickBlock3(blockPos2, vec3d, EnumHand.MAIN_HAND, enumFacing2);
        } else {
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos2, enumFacing2, vec3d, EnumHand.MAIN_HAND);
            BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        return blockPos2;
    }

    public static boolean canRightClickBlock(BlockPos blockPos) {
        return BlockUtil2.isBlockBreakable(blockPos) && !blackList.contains((Object)BlockUtil2.getBlock(blockPos));
    }

    public static boolean isBlockAir(BlockPos blockPos) {
        Block block = BlockUtil2.getBlock(blockPos);
        return block instanceof BlockAir || block instanceof BlockLiquid;
    }

    private static boolean hasNeighbour(BlockPos blockPos) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            BlockPos blockPos2 = blockPos.offset(enumFacing);
            if (BlockUtil2.mc.world.getBlockState(blockPos2).getMaterial().isReplaceable()) continue;
            return true;
        }
        return false;
    }

    public static boolean canPlaceCrystal(BlockPos blockPos) {
        BlockPos blockPos2 = blockPos.add(0, 1, 0);
        BlockPos blockPos3 = blockPos.add(0, 2, 0);
        try {
            return (BlockUtil2.mc.world.getBlockState(blockPos).getBlock() == Blocks.BEDROCK || BlockUtil2.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN) && BlockUtil2.mc.world.getBlockState(blockPos2).getBlock() == Blocks.AIR && BlockUtil2.mc.world.getBlockState(blockPos3).getBlock() == Blocks.AIR && BlockUtil2.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2)).isEmpty() && BlockUtil2.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos3)).isEmpty();
        }
        catch (Exception exception) {
            return false;
        }
    }

    public static List<BlockPos> getCock(float f, boolean bl) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        BlockPos blockPos = new BlockPos(BlockUtil.mc.player.getPositionVector());
        int n = blockPos.getX();
        int n2 = blockPos.getY();
        int n3 = blockPos.getZ();
        int n4 = (int)f;
        int n5 = n - n4;
        while ((float)n5 <= (float)n + f) {
            int n6 = n3 - n4;
            while ((float)n6 <= (float)n3 + f) {
                int n7 = n2 - n4;
                while ((float)n7 < (float)n2 + f) {
                    BlockPos blockPos2;
                    double d = (n - n5) * (n - n5) + (n3 - n6) * (n3 - n6) + (n2 - n7) * (n2 - n7);
                    if (d < (double)(f * f) && (BlockUtil.mc.world.getBlockState(blockPos2 = new BlockPos(n5, n7, n6)).getBlock() != Blocks.AIR || !bl)) {
                        arrayList.add(blockPos2);
                    }
                    ++n7;
                }
                ++n6;
            }
            ++n5;
        }
        return arrayList;
    }

    public static boolean placeBlock(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        boolean bl5 = false;
        EnumFacing enumFacing = BlockUtil2.calculateSide(blockPos);
        if (enumFacing == null) {
            return bl4;
        }
        BlockPos blockPos2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing2 = enumFacing.getOpposite();
        Vec3d vec3d = new Vec3d((Vec3i)blockPos2).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing2.getDirectionVec()).scale(0.5));
        Block block = BlockUtil2.mc.world.getBlockState(blockPos2).getBlock();
        float f = (float)(vec3d.xCoord - (double)blockPos.getX());
        float f2 = (float)(vec3d.yCoord - (double)blockPos.getY());
        float f3 = (float)(vec3d.zCoord - (double)blockPos.getZ());
        if (!BlockUtil2.mc.player.isSneaking() && (blackList.contains((Object)block) || shulkerList.contains((Object)block))) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtil2.mc.player.setSneaking(true);
            bl5 = true;
        }
        if (bl) {
            RotationUtil.faceVector(vec3d, true);
        }
        if (bl3) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos2, enumFacing2, enumHand, f, f2, f3));
        }
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.START_SNEAKING));
        if (TestUtil.isBlockEmpty(blockPos)) {
            BlockUtil2.placeClient(blockPos2, enumHand, enumFacing2, (float)vec3d.xCoord, (float)vec3d.yCoord, (float)vec3d.zCoord);
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos2, enumFacing2, vec3d, enumHand);
            EnumActionResult enumActionResult = BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos2, enumFacing2, vec3d, enumHand);
            enumActionResult = EnumActionResult.SUCCESS;
            BlockUtil2.rightClickBlock(blockPos2, vec3d, enumHand, enumFacing2, bl2);
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos2, enumFacing2, vec3d, enumHand);
        } else {
            BlockUtil2.placeClient(blockPos2, enumHand, enumFacing2, (float)vec3d.xCoord, (float)vec3d.yCoord, (float)vec3d.zCoord);
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos2, enumFacing2, vec3d, enumHand);
            EnumActionResult enumActionResult = BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos2, enumFacing2, vec3d, enumHand);
            enumActionResult = EnumActionResult.SUCCESS;
            BlockUtil2.rightClickBlock(blockPos2, vec3d, enumHand, enumFacing2, bl2);
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos2, enumFacing2, vec3d, enumHand);
        }
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        return bl5 || bl4;
    }

    public static Vec3d[] getOffsets(int n, boolean bl) {
        List<Vec3d> list = BlockUtil2.getOffsetList(n, bl);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])new Vec3d[0]);
    }

    public static void faceVectorPacketInstant(Vec3d vec3d) {
        float[] arrf = BlockUtil2.getNeededRotations2(vec3d);
        BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(arrf[0], arrf[1], BlockUtil2.mc.player.onGround));
    }

    private static IBlockState getState(BlockPos blockPos) {
        return BlockUtil2.mc.world.getBlockState(blockPos);
    }

    public static EnumFacing getFirstFacing(BlockPos blockPos) {
        Iterator<EnumFacing> iterator2 = BlockUtil2.getPossibleSides(blockPos).iterator();
        if (iterator2.hasNext()) {
            EnumFacing enumFacing = iterator2.next();
            return enumFacing;
        }
        return null;
    }

    public static EnumFacing calculateSide(BlockPos blockPos) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            IBlockState iBlockState = BlockUtil2.mc.world.getBlockState(blockPos.offset(enumFacing));
            boolean bl = iBlockState.getBlock().onBlockActivated((World)BlockUtil2.mc.world, blockPos, iBlockState, (EntityPlayer)BlockUtil2.mc.player, EnumHand.MAIN_HAND, enumFacing, 0.0f, 0.0f, 0.0f);
            if (bl) {
                mc.getConnection().sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            }
            if (!iBlockState.getBlock().canCollideCheck(iBlockState, false) || iBlockState.getMaterial().isReplaceable()) continue;
            return enumFacing;
        }
        return null;
    }

    public static boolean isBlockBreakable(BlockPos blockPos) {
        Block block = BlockUtil2.getBlock(blockPos);
        if (block instanceof BlockAir) {
            return false;
        }
        return !(block instanceof BlockLiquid);
    }

    public static boolean rayTracePlaceCheck(BlockPos blockPos, boolean bl, float f) {
        return !bl || BlockUtil2.mc.world.rayTraceBlocks(new Vec3d(BlockUtil2.mc.player.posX, BlockUtil2.mc.player.posY + (double)BlockUtil2.mc.player.getEyeHeight(), BlockUtil2.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)((float)blockPos.getY() + f), (double)blockPos.getZ()), false, true, false) == null;
    }

    static {
        rightclickableBlocks = Arrays.asList(new Block[]{Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.field_190977_dl, Blocks.field_190978_dm, Blocks.field_190979_dn, Blocks.field_190980_do, Blocks.field_190981_dp, Blocks.field_190982_dq, Blocks.field_190983_dr, Blocks.field_190984_ds, Blocks.field_190985_dt, Blocks.field_190986_du, Blocks.field_190987_dv, Blocks.field_190988_dw, Blocks.field_190989_dx, Blocks.field_190990_dy, Blocks.field_190991_dz, Blocks.field_190975_dA, Blocks.ANVIL, Blocks.WOODEN_BUTTON, Blocks.STONE_BUTTON, Blocks.UNPOWERED_COMPARATOR, Blocks.UNPOWERED_REPEATER, Blocks.POWERED_REPEATER, Blocks.POWERED_COMPARATOR, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.ACACIA_FENCE_GATE, Blocks.BREWING_STAND, Blocks.DISPENSER, Blocks.DROPPER, Blocks.LEVER, Blocks.NOTEBLOCK, Blocks.JUKEBOX, Blocks.BEACON, Blocks.BED, Blocks.FURNACE, Blocks.OAK_DOOR, Blocks.SPRUCE_DOOR, Blocks.BIRCH_DOOR, Blocks.JUNGLE_DOOR, Blocks.ACACIA_DOOR, Blocks.DARK_OAK_DOOR, Blocks.CAKE, Blocks.ENCHANTING_TABLE, Blocks.DRAGON_EGG, Blocks.HOPPER, Blocks.REPEATING_COMMAND_BLOCK, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.CRAFTING_TABLE});
        blackList = Arrays.asList(new Block[]{Blocks.ENDER_CHEST, Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER, Blocks.TRAPDOOR, Blocks.ENCHANTING_TABLE});
        shulkerList = Arrays.asList(new Block[]{Blocks.field_190977_dl, Blocks.field_190978_dm, Blocks.field_190979_dn, Blocks.field_190980_do, Blocks.field_190981_dp, Blocks.field_190982_dq, Blocks.field_190983_dr, Blocks.field_190984_ds, Blocks.field_190985_dt, Blocks.field_190986_du, Blocks.field_190987_dv, Blocks.field_190988_dw, Blocks.field_190989_dx, Blocks.field_190990_dy, Blocks.field_190991_dz, Blocks.field_190975_dA});
        unSafeBlocks = Arrays.asList(new Block[]{Blocks.OBSIDIAN, Blocks.BEDROCK, Blocks.ENDER_CHEST, Blocks.ANVIL});
        unSolidBlocks = Arrays.asList(new Block[]{Blocks.FLOWING_LAVA, Blocks.FLOWER_POT, Blocks.SNOW, Blocks.CARPET, Blocks.END_ROD, Blocks.SKULL, Blocks.FLOWER_POT, Blocks.TRIPWIRE, Blocks.TRIPWIRE_HOOK, Blocks.WOODEN_BUTTON, Blocks.LEVER, Blocks.STONE_BUTTON, Blocks.LADDER, Blocks.UNPOWERED_COMPARATOR, Blocks.POWERED_COMPARATOR, Blocks.UNPOWERED_REPEATER, Blocks.POWERED_REPEATER, Blocks.UNLIT_REDSTONE_TORCH, Blocks.REDSTONE_TORCH, Blocks.REDSTONE_WIRE, Blocks.AIR, Blocks.PORTAL, Blocks.END_PORTAL, Blocks.WATER, Blocks.FLOWING_WATER, Blocks.LAVA, Blocks.FLOWING_LAVA, Blocks.SAPLING, Blocks.RED_FLOWER, Blocks.YELLOW_FLOWER, Blocks.BROWN_MUSHROOM, Blocks.RED_MUSHROOM, Blocks.WHEAT, Blocks.CARROTS, Blocks.POTATOES, Blocks.BEETROOTS, Blocks.REEDS, Blocks.PUMPKIN_STEM, Blocks.MELON_STEM, Blocks.WATERLILY, Blocks.NETHER_WART, Blocks.COCOA, Blocks.CHORUS_FLOWER, Blocks.CHORUS_PLANT, Blocks.TALLGRASS, Blocks.DEADBUSH, Blocks.VINE, Blocks.FIRE, Blocks.RAIL, Blocks.ACTIVATOR_RAIL, Blocks.DETECTOR_RAIL, Blocks.GOLDEN_RAIL, Blocks.TORCH});
        emptyBlocks = Arrays.asList(new Block[]{Blocks.AIR, Blocks.FLOWING_LAVA, Blocks.LAVA, Blocks.FLOWING_WATER, Blocks.WATER, Blocks.VINE, Blocks.SNOW_LAYER, Blocks.TALLGRASS, Blocks.FIRE});
    }

    public static void placeBlockss(BlockPos blockPos, boolean bl, boolean bl2, boolean bl3) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            boolean bl4 = bl;
            boolean bl5 = bl2;
            boolean bl6 = bl3;
            BlockPos blockPos2 = blockPos;
            if (BlockUtil2.mc.world.getBlockState(blockPos2.offset(enumFacing)).getBlock().equals((Object)Blocks.AIR) || BlockUtil2.isIntercepted(blockPos2)) continue;
            if (bl5) {
                BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos2.offset(enumFacing), enumFacing.getOpposite(), EnumHand.MAIN_HAND, Float.intBitsToFloat(Float.floatToIntBits(2.7f)), Float.intBitsToFloat(Float.floatToIntBits(3.8f)), Float.intBitsToFloat(Float.floatToIntBits(30.0f))));
            } else {
                BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos2.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos2), EnumHand.MAIN_HAND);
            }
            if (bl4) {
                BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
            if (bl6) {
                RotationUtil.faceVector(new Vec3d((Vec3i)blockPos2), true);
            }
            return;
        }
    }

    public static List<BlockPos> possiblePlacePositions(float f, boolean bl) {
        NonNullList nonNullList = NonNullList.func_191196_a();
        nonNullList.addAll((Collection)BlockUtil2.getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil2.mc.player), f, (int)f, false, true, 0).stream().filter(blockPos -> BlockUtil2.canPlaceCrystal(blockPos, bl)).collect(Collectors.toList()));
        return nonNullList;
    }

    public static Vec3d[] getHelpingBlocks(Vec3d vec3d) {
        return new Vec3d[]{new Vec3d(vec3d.xCoord, vec3d.yCoord - 1.0, vec3d.zCoord), new Vec3d(vec3d.xCoord != 0.0 ? vec3d.xCoord * 2.0 : vec3d.xCoord, vec3d.yCoord, vec3d.xCoord != 0.0 ? vec3d.zCoord : vec3d.zCoord * 2.0), new Vec3d(vec3d.xCoord == 0.0 ? vec3d.xCoord + 1.0 : vec3d.xCoord, vec3d.yCoord, vec3d.xCoord == 0.0 ? vec3d.zCoord : vec3d.zCoord + 1.0), new Vec3d(vec3d.xCoord == 0.0 ? vec3d.xCoord - 1.0 : vec3d.xCoord, vec3d.yCoord, vec3d.xCoord == 0.0 ? vec3d.zCoord : vec3d.zCoord - 1.0), new Vec3d(vec3d.xCoord, vec3d.yCoord + 1.0, vec3d.zCoord)};
    }

    public static boolean isPositionPlaceable(BlockPos blockPos, boolean bl, double d) {
        Block block = BlockUtil2.mc.world.getBlockState(blockPos).getBlock();
        if (!(block instanceof BlockAir || block instanceof BlockLiquid || block instanceof BlockTallGrass || block instanceof BlockFire || block instanceof BlockDeadBush || block instanceof BlockSnow)) {
            return false;
        }
        if (bl) {
            for (Entity entity : BlockUtil2.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos))) {
                if ((double)BlockUtil2.mc.player.getDistanceToEntity(entity) > d || entity instanceof EntityItem || entity instanceof EntityXPOrb) continue;
                return false;
            }
        }
        return true;
    }

    public static BlockPos placeBlockS(BlockPos blockPos, boolean bl) {
        Block block = BlockUtil2.mc.world.getBlockState(blockPos).getBlock();
        if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid)) {
            return null;
        }
        EnumFacing enumFacing = BlockUtil2.getPlaceableSide(blockPos);
        if (enumFacing == null) {
            return null;
        }
        BlockPos blockPos2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing2 = enumFacing.getOpposite();
        Vec3d vec3d = new Vec3d((Vec3i)blockPos2).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing2.getDirectionVec()).scale(0.5));
        if (bl) {
            BlockUtil2.rightClickBlock3(blockPos2, vec3d, EnumHand.MAIN_HAND, enumFacing2);
        } else {
            BlockUtil2.mc.playerController.processRightClickBlock(BlockUtil2.mc.player, BlockUtil2.mc.world, blockPos2, enumFacing2, vec3d, EnumHand.MAIN_HAND);
            BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
        return blockPos2;
    }

    public static boolean placeBlock2(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3) {
        boolean bl4 = false;
        EnumFacing enumFacing = BlockUtil2.getFirstFacing(blockPos);
        if (enumFacing == null) {
            return bl3;
        }
        BlockPos blockPos2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing2 = enumFacing.getOpposite();
        Vec3d vec3d = new Vec3d((Vec3i)blockPos2).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing2.getDirectionVec()).scale(0.5));
        if (!BlockUtil2.mc.player.isSneaking() && bl3) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtil2.mc.player.setSneaking(true);
            bl4 = true;
        }
        BlockUtil2.rightClickBlock(blockPos2, vec3d, enumHand, enumFacing2, bl2);
        BlockUtil2.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil2.mc.rightClickDelayTimer = 4;
        if (!BlockUtil2.mc.player.isSneaking() && bl3) {
            BlockUtil2.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil2.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            BlockUtil2.mc.player.setSneaking(false);
            bl4 = false;
        }
        return bl4 || bl3;
    }

    public static enum ValidResult {
        NoEntityCollision,
        AlreadyBlockThere,
        NoNeighbors,
        Ok;

    }
}

