//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityEgg
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.World
 */
package me.snow.aclient.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.snow.aclient.util.Wrapper;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public class BlockInteractionHelper {
    public static final /* synthetic */ List shulkerList;
    public static final /* synthetic */ List blackList;
    private static final /* synthetic */ Minecraft mc;

    public static boolean canBeClicked(BlockPos blockPos) {
        return BlockInteractionHelper.getBlock(blockPos).canCollideCheck(BlockInteractionHelper.getState(blockPos), false);
    }

    static {
        blackList = Arrays.asList(new Block[]{Blocks.ENDER_CHEST, Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER});
        shulkerList = Arrays.asList(new Block[]{Blocks.field_190977_dl, Blocks.field_190978_dm, Blocks.field_190979_dn, Blocks.field_190980_do, Blocks.field_190981_dp, Blocks.field_190982_dq, Blocks.field_190983_dr, Blocks.field_190984_ds, Blocks.field_190985_dt, Blocks.field_190986_du, Blocks.field_190987_dv, Blocks.field_190988_dw, Blocks.field_190989_dx, Blocks.field_190990_dy, Blocks.field_190991_dz, Blocks.field_190975_dA});
        mc = Minecraft.getMinecraft();
    }

    private static Vec3d getEyesPos() {
        return new Vec3d(Wrapper.getPlayer().posX, Wrapper.getPlayer().posY + (double)Wrapper.getPlayer().getEyeHeight(), Wrapper.getPlayer().posZ);
    }

    public static BlockResistance getBlockResistance(BlockPos blockPos) {
        if (BlockInteractionHelper.mc.world.isAirBlock(blockPos)) {
            return BlockResistance.Blank;
        }
        if (!(BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().getBlockHardness(BlockInteractionHelper.mc.world.getBlockState(blockPos), (World)BlockInteractionHelper.mc.world, blockPos) == -1.0f || BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals((Object)Blocks.OBSIDIAN) || BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals((Object)Blocks.ANVIL) || BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals((Object)Blocks.ENCHANTING_TABLE) || BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals((Object)Blocks.ENDER_CHEST))) {
            return BlockResistance.Breakable;
        }
        if (BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals((Object)Blocks.OBSIDIAN) || BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals((Object)Blocks.ANVIL) || BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals((Object)Blocks.ENCHANTING_TABLE) || BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals((Object)Blocks.ENDER_CHEST)) {
            return BlockResistance.Resistant;
        }
        if (BlockInteractionHelper.mc.world.getBlockState(blockPos).getBlock().equals((Object)Blocks.BEDROCK)) {
            return BlockResistance.Unbreakable;
        }
        return null;
    }

    public static float[] getDirectionToBlock(int n, int n2, int n3, EnumFacing enumFacing) {
        EntityEgg entityEgg = new EntityEgg((World)BlockInteractionHelper.mc.world);
        entityEgg.posX = (double)n + 0.5;
        entityEgg.posY = (double)n2 + 0.5;
        entityEgg.posZ = (double)n3 + 0.5;
        EntityEgg entityEgg2 = entityEgg;
        entityEgg2.posX += (double)enumFacing.getDirectionVec().getX() * 0.25;
        EntityEgg entityEgg3 = entityEgg;
        entityEgg3.posY += (double)enumFacing.getDirectionVec().getY() * 0.25;
        EntityEgg entityEgg4 = entityEgg;
        entityEgg4.posZ += (double)enumFacing.getDirectionVec().getZ() * 0.25;
        return BlockInteractionHelper.getDirectionToEntity((Entity)entityEgg);
    }

    private static PlayerControllerMP getPlayerController() {
        return Minecraft.getMinecraft().playerController;
    }

    private static void processRightClickBlock(BlockPos blockPos, EnumFacing enumFacing, Vec3d vec3d) {
        BlockInteractionHelper.getPlayerController().processRightClickBlock(Wrapper.getPlayer(), BlockInteractionHelper.mc.world, blockPos, enumFacing, vec3d, EnumHand.MAIN_HAND);
    }

    public static void faceVectorPacketInstant(Vec3d vec3d) {
        float[] arrf = BlockInteractionHelper.getLegitRotations(vec3d);
        Wrapper.getPlayer().connection.sendPacket((Packet)new CPacketPlayer.Rotation(arrf[0], arrf[1], Wrapper.getPlayer().onGround));
    }

    public static float getYaw(Entity entity) {
        double d = entity.posX - BlockInteractionHelper.mc.player.posX;
        double d2 = entity.posZ - BlockInteractionHelper.mc.player.posZ;
        double d3 = d2 < 0.0 && d < 0.0 ? 90.0 + Math.toDegrees(Math.atan(d2 / d)) : (d2 < 0.0 && d > 0.0 ? -90.0 + Math.toDegrees(Math.atan(d2 / d)) : Math.toDegrees(-Math.atan(d / d2)));
        return MathHelper.wrapDegrees((float)(-(BlockInteractionHelper.mc.player.rotationYaw - (float)d3)));
    }

    public static boolean checkForNeighbours(BlockPos blockPos) {
        if (!BlockInteractionHelper.hasNeighbour(blockPos)) {
            for (EnumFacing enumFacing : EnumFacing.values()) {
                BlockPos blockPos2 = blockPos.offset(enumFacing);
                if (!BlockInteractionHelper.hasNeighbour(blockPos2)) continue;
                return true;
            }
            return false;
        }
        return true;
    }

    public static void rotate(double[] arrd) {
        Minecraft.getMinecraft().player.rotationYaw = (float)arrd[0];
        Minecraft.getMinecraft().player.rotationPitch = (float)arrd[1];
    }

    public static float[] getLegitRotations(Vec3d vec3d) {
        Vec3d vec3d2 = BlockInteractionHelper.getEyesPos();
        double d = vec3d.xCoord - vec3d2.xCoord;
        double d2 = vec3d.yCoord - vec3d2.yCoord;
        double d3 = vec3d.zCoord - vec3d2.zCoord;
        double d4 = Math.sqrt(d * d + d3 * d3);
        float f = (float)Math.toDegrees(Math.atan2(d3, d)) - 90.0f;
        float f2 = (float)(-Math.toDegrees(Math.atan2(d2, d4)));
        return new float[]{Wrapper.getPlayer().rotationYaw + MathHelper.wrapDegrees((float)(f - Wrapper.getPlayer().rotationYaw)), Wrapper.getPlayer().rotationPitch + MathHelper.wrapDegrees((float)(f2 - Wrapper.getPlayer().rotationPitch))};
    }

    public static float getPitch(Entity entity) {
        double d = entity.posX - BlockInteractionHelper.mc.player.posX;
        double d2 = entity.posZ - BlockInteractionHelper.mc.player.posZ;
        double d3 = entity.posY - 1.6 + (double)entity.getEyeHeight() - BlockInteractionHelper.mc.player.posY;
        double d4 = MathHelper.sqrt((double)(d * d + d2 * d2));
        double d5 = -Math.toDegrees(Math.atan(d3 / d4));
        return -MathHelper.wrapDegrees((float)(BlockInteractionHelper.mc.player.rotationPitch - (float)d5));
    }

    public static void placeBlock(BlockPos blockPos, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            if (BlockInteractionHelper.getBlockResistance(blockPos.offset(enumFacing)) == BlockResistance.Blank || BlockInteractionHelper.isIntercepted(blockPos)) continue;
            Vec3d vec3d = new Vec3d((double)blockPos.getX() + 0.5 + (double)enumFacing.getFrontOffsetX() * 0.5, (double)blockPos.getY() + 0.5 + (double)enumFacing.getFrontOffsetY() * 0.5, (double)blockPos.getZ() + 0.5 + (double)enumFacing.getFrontOffsetZ() * 0.5);
            float[] arrf = new float[]{BlockInteractionHelper.mc.player.rotationYaw, BlockInteractionHelper.mc.player.rotationPitch};
            if (bl) {
                BlockInteractionHelper.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation((float)Math.toDegrees(Math.atan2(vec3d.zCoord - BlockInteractionHelper.mc.player.posZ, vec3d.xCoord - BlockInteractionHelper.mc.player.posX)) - 90.0f, (float)(-Math.toDegrees(Math.atan2(vec3d.yCoord - (BlockInteractionHelper.mc.player.posY + (double)BlockInteractionHelper.mc.player.getEyeHeight()), Math.sqrt((vec3d.xCoord - BlockInteractionHelper.mc.player.posX) * (vec3d.xCoord - BlockInteractionHelper.mc.player.posX) + (vec3d.zCoord - BlockInteractionHelper.mc.player.posZ) * (vec3d.zCoord - BlockInteractionHelper.mc.player.posZ))))), BlockInteractionHelper.mc.player.onGround));
            }
            BlockInteractionHelper.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockInteractionHelper.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            if (bl3) {
                BlockInteractionHelper.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, bl2 ? enumFacing : EnumFacing.UP, EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            } else {
                BlockInteractionHelper.mc.playerController.processRightClickBlock(BlockInteractionHelper.mc.player, BlockInteractionHelper.mc.world, blockPos.offset(enumFacing), bl2 ? enumFacing.getOpposite() : EnumFacing.UP, new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
            }
            if (bl4) {
                BlockInteractionHelper.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
            BlockInteractionHelper.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockInteractionHelper.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            if (bl) {
                BlockInteractionHelper.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(arrf[0], arrf[1], BlockInteractionHelper.mc.player.onGround));
            }
            if (bl5) {
                BlockInteractionHelper.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos.offset(enumFacing), enumFacing.getOpposite()));
            }
            return;
        }
    }

    public static boolean isInterceptedByOther(BlockPos blockPos) {
        for (Entity entity : BlockInteractionHelper.mc.world.loadedEntityList) {
            if (entity.equals((Object)BlockInteractionHelper.mc.player) || !new AxisAlignedBB(blockPos).intersectsWith(entity.getEntityBoundingBox())) continue;
            return true;
        }
        return false;
    }

    private static Block getBlock(BlockPos blockPos) {
        return BlockInteractionHelper.getState(blockPos).getBlock();
    }

    private static IBlockState getState(BlockPos blockPos) {
        return Wrapper.getWorld().getBlockState(blockPos);
    }

    public static EnumFacing getPlaceableSide(BlockPos blockPos) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            IBlockState iBlockState;
            BlockPos blockPos2 = blockPos.offset(enumFacing);
            if (!BlockInteractionHelper.mc.world.getBlockState(blockPos2).getBlock().canCollideCheck(BlockInteractionHelper.mc.world.getBlockState(blockPos2), false) || (iBlockState = BlockInteractionHelper.mc.world.getBlockState(blockPos2)).getMaterial().isReplaceable()) continue;
            return enumFacing;
        }
        return null;
    }

    public static double[] calculateLookAt(double d, double d2, double d3, EntityPlayer entityPlayer) {
        double d4 = entityPlayer.posX - d;
        double d5 = entityPlayer.posY - d2;
        double d6 = entityPlayer.posZ - d3;
        double d7 = Math.sqrt(d4 * d4 + d5 * d5 + d6 * d6);
        double d8 = Math.asin(d5 /= d7);
        double d9 = Math.atan2(d6 /= d7, d4 /= d7);
        d8 = d8 * 180.0 / Math.PI;
        d9 = d9 * 180.0 / Math.PI;
        return new double[]{d9 += 90.0, d8};
    }

    private static boolean hasNeighbour(BlockPos blockPos) {
        for (EnumFacing enumFacing : EnumFacing.values()) {
            BlockPos blockPos2 = blockPos.offset(enumFacing);
            if (Wrapper.getWorld().getBlockState(blockPos2).getMaterial().isReplaceable()) continue;
            return true;
        }
        return false;
    }

    public static boolean hotbarSlotCheckEmpty(ItemStack itemStack) {
        return itemStack != ItemStack.field_190927_a;
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

    private static float wrapAngleTo180(float f) {
        f %= 360.0f;
        while (f >= 180.0f) {
            f -= 360.0f;
        }
        while (f < -180.0f) {
            f += 360.0f;
        }
        return f;
    }

    public static void rotate(float f, float f2) {
        Minecraft.getMinecraft().player.rotationYaw = f;
        Minecraft.getMinecraft().player.rotationPitch = f2;
    }

    public static boolean blockCheckNonBlock(ItemStack itemStack) {
        return itemStack.getItem() instanceof ItemBlock;
    }

    public static List<BlockPos> getCircle(BlockPos blockPos, int n, float f, boolean bl) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        int n2 = blockPos.getX();
        int n3 = blockPos.getZ();
        int n4 = n2 - (int)f;
        while ((float)n4 <= (float)n2 + f) {
            int n5 = n3 - (int)f;
            while ((float)n5 <= (float)n3 + f) {
                double d = (n2 - n4) * (n2 - n4) + (n3 - n5) * (n3 - n5);
                if (d < (double)(f * f) && (!bl || d >= (double)((f - 1.0f) * (f - 1.0f)))) {
                    BlockPos blockPos2 = new BlockPos(n4, n, n5);
                    arrayList.add(blockPos2);
                }
                ++n5;
            }
            ++n4;
        }
        return arrayList;
    }

    public static boolean isIntercepted(BlockPos blockPos) {
        for (Entity entity : BlockInteractionHelper.mc.world.loadedEntityList) {
            if (!new AxisAlignedBB(blockPos).intersectsWith(entity.getEntityBoundingBox())) continue;
            return true;
        }
        return false;
    }

    private static float[] getDirectionToEntity(Entity entity) {
        return new float[]{BlockInteractionHelper.getYaw(entity) + BlockInteractionHelper.mc.player.rotationYaw, BlockInteractionHelper.getPitch(entity) + BlockInteractionHelper.mc.player.rotationPitch};
    }

    public static List<BlockPos> getSphereRain(BlockPos blockPos, float f, int n, boolean bl, boolean bl2, int n2) {
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
                    if (!(f2 < f3)) break;
                    double d = (n3 - n6) * (n3 - n6) + (n5 - n7) * (n5 - n7) + (bl2 ? (n4 - n8) * (n4 - n8) : 0);
                    if (!(!(d < (double)(f * f)) || bl && d < (double)((f - 1.0f) * (f - 1.0f)))) {
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

    public static void lookAtBlock(BlockPos blockPos) {
        BlockInteractionHelper.rotate(BlockInteractionHelper.calculateLookAt(blockPos.getX(), blockPos.getY(), blockPos.getZ(), (EntityPlayer)Minecraft.getMinecraft().player));
    }

    public static void placeBlockScaffold(BlockPos blockPos) {
        Vec3d vec3d = new Vec3d(Wrapper.getPlayer().posX, Wrapper.getPlayer().posY + (double)Wrapper.getPlayer().getEyeHeight(), Wrapper.getPlayer().posZ);
        for (EnumFacing enumFacing : EnumFacing.values()) {
            Vec3d vec3d2;
            BlockPos blockPos2 = blockPos.offset(enumFacing);
            EnumFacing enumFacing2 = enumFacing.getOpposite();
            if (!BlockInteractionHelper.canBeClicked(blockPos2) || !(vec3d.squareDistanceTo(vec3d2 = new Vec3d((Vec3i)blockPos2).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing2.getDirectionVec()).rotatePitch(0.5f))) <= 18.0625)) continue;
            BlockInteractionHelper.faceVectorPacketInstant(vec3d2);
            BlockInteractionHelper.processRightClickBlock(blockPos2, enumFacing2, vec3d2);
            Wrapper.getPlayer().swingArm(EnumHand.MAIN_HAND);
            BlockInteractionHelper.mc.rightClickDelayTimer = 4;
            return;
        }
    }

    public static float[] getRotationNeededForBlock(EntityPlayer entityPlayer, BlockPos blockPos) {
        double d = (double)blockPos.getX() - entityPlayer.posX;
        double d2 = (double)blockPos.getY() + 0.5 - (entityPlayer.posY + (double)entityPlayer.getEyeHeight());
        double d3 = (double)blockPos.getZ() - entityPlayer.posZ;
        double d4 = Math.sqrt(d * d + d3 * d3);
        float f = (float)(Math.atan2(d3, d) * 180.0 / Math.PI) - 90.0f;
        float f2 = (float)(-(Math.atan2(d2, d4) * 180.0 / Math.PI));
        return new float[]{f, f2};
    }

    public static enum BlockResistance {
        Blank,
        Breakable,
        Resistant,
        Unbreakable;

    }
}

