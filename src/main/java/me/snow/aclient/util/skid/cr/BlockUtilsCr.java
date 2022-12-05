//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.client.Minecraft
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.IBlockAccess
 */
package me.snow.aclient.util.skid.cr;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;

public class BlockUtilsCr {
    public /* synthetic */ double dist;
    public /* synthetic */ int a;
    public /* synthetic */ double roty;
    public /* synthetic */ EnumFacing f;
    protected static /* synthetic */ Minecraft mc;
    public /* synthetic */ BlockPos pos;
    public /* synthetic */ double rotx;

    public static BlockUtilsCr isPlaceable(BlockPos blockPos, double d, boolean bl) {
        BlockUtilsCr blockUtilsCr = new BlockUtilsCr(blockPos, 0, null, d);
        if (!BlockUtilsCr.isAir(blockPos)) {
            return null;
        }
        if (!(BlockUtilsCr.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBlock)) {
            return null;
        }
        AxisAlignedBB axisAlignedBB = ((ItemBlock)BlockUtilsCr.mc.player.inventory.getCurrentItem().getItem()).getBlock().getDefaultState().getCollisionBoundingBox((IBlockAccess)BlockUtilsCr.mc.world, blockPos);
        if (!BlockUtilsCr.isAir(blockPos) && BlockUtilsCr.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid) {
            Block block = BlockUtilsCr.mc.world.getBlockState(blockPos.offset(EnumFacing.UP)).getBlock();
            if (block instanceof BlockLiquid) {
                blockUtilsCr.f = EnumFacing.DOWN;
                blockUtilsCr.pos.offset(EnumFacing.UP);
                return blockUtilsCr;
            }
            blockUtilsCr.f = EnumFacing.UP;
            blockUtilsCr.pos.offset(EnumFacing.DOWN);
            return blockUtilsCr;
        }
        for (EnumFacing enumFacing : EnumFacing.values()) {
            if (BlockUtilsCr.isAir(new BlockPos(blockPos.getX() - enumFacing.getDirectionVec().getX(), blockPos.getY() - enumFacing.getDirectionVec().getY(), blockPos.getZ() - enumFacing.getDirectionVec().getZ()))) continue;
            blockUtilsCr.f = enumFacing;
            if (bl && axisAlignedBB != Block.NULL_AABB && !BlockUtilsCr.mc.world.checkNoEntityCollision(axisAlignedBB.offset(blockPos), null)) {
                return null;
            }
            return blockUtilsCr;
        }
        if (BlockUtilsCr.isRePlaceable(blockPos)) {
            blockUtilsCr.f = EnumFacing.UP;
            blockUtilsCr.pos.offset(EnumFacing.UP);
            blockPos.offset(EnumFacing.DOWN);
            if (bl && axisAlignedBB != Block.NULL_AABB && !BlockUtilsCr.mc.world.checkNoEntityCollision(axisAlignedBB.offset(blockPos), null)) {
                return null;
            }
            return blockUtilsCr;
        }
        return null;
    }

    static {
        mc = Minecraft.getMinecraft();
    }

    public static boolean isAir(BlockPos blockPos) {
        Block block = BlockUtilsCr.mc.world.getBlockState(blockPos).getBlock();
        return block instanceof BlockAir;
    }

    public void doBreak() {
        BlockUtilsCr.mc.playerController.onPlayerDamageBlock(new BlockPos(this.pos.getX() - this.f.getDirectionVec().getX(), this.pos.getY() - this.f.getDirectionVec().getY(), this.pos.getZ() - this.f.getDirectionVec().getZ()), this.f);
    }

    public boolean doPlace(boolean bl) {
        double d = (double)this.pos.getX() + 0.5 - BlockUtilsCr.mc.player.posX - (double)this.f.getDirectionVec().getX() / 2.0;
        double d2 = (double)this.pos.getY() + 0.5 - BlockUtilsCr.mc.player.posY - (double)this.f.getDirectionVec().getY() / 2.0 - (double)BlockUtilsCr.mc.player.getEyeHeight();
        double d3 = (double)this.pos.getZ() + 0.5 - BlockUtilsCr.mc.player.posZ - (double)this.f.getDirectionVec().getZ() / 2.0;
        double d4 = BlockUtilsCr.getDirection2D(d3, d);
        double d5 = BlockUtilsCr.getDirection2D(d2, Math.sqrt(d * d + d3 * d3));
        Vec3d vec3d = this.getVectorForRotation(-d5, d4 - 90.0);
        this.roty = -d5;
        this.rotx = d4 - 90.0;
        EnumActionResult enumActionResult = BlockUtilsCr.mc.playerController.processRightClickBlock(BlockUtilsCr.mc.player, BlockUtilsCr.mc.world, new BlockPos(this.pos.getX() - this.f.getDirectionVec().getX(), this.pos.getY() - this.f.getDirectionVec().getY(), this.pos.getZ() - this.f.getDirectionVec().getZ()), this.f, vec3d, EnumHand.MAIN_HAND);
        if (enumActionResult == EnumActionResult.SUCCESS) {
            if (bl) {
                BlockUtilsCr.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
            return true;
        }
        return false;
    }

    protected final Vec3d getVectorForRotation(double d, double d2) {
        float f = MathHelper.cos((float)((float)(-d2 * 0.01745329238474369 - 3.1415927410125732)));
        float f2 = MathHelper.sin((float)((float)(-d2 * 0.01745329238474369 - 3.1415927410125732)));
        float f3 = -MathHelper.cos((float)((float)(-d * 0.01745329238474369)));
        float f4 = MathHelper.sin((float)((float)(-d * 0.01745329238474369)));
        return new Vec3d((double)(f2 * f3), (double)f4, (double)(f * f3));
    }

    public static boolean doPlace(BlockUtilsCr blockUtilsCr, boolean bl) {
        if (blockUtilsCr == null) {
            return false;
        }
        return blockUtilsCr.doPlace(bl);
    }

    public static boolean isRePlaceable(BlockPos blockPos) {
        Block block = BlockUtilsCr.mc.world.getBlockState(blockPos).getBlock();
        return block.isReplaceable((IBlockAccess)BlockUtilsCr.mc.world, blockPos) && !(block instanceof BlockAir);
    }

    public static boolean doBreak(BlockPos blockPos, EnumFacing enumFacing) {
        return BlockUtilsCr.mc.playerController.clickBlock(blockPos, enumFacing);
    }

    public BlockUtilsCr(BlockPos blockPos, int n, EnumFacing enumFacing, double d) {
        this.pos = blockPos;
        this.a = n;
        this.f = enumFacing;
        this.dist = d;
    }

    public static double getDirection2D(double d, double d2) {
        double d3;
        if (d2 == 0.0) {
            d3 = d > 0.0 ? 90.0 : -90.0;
        } else {
            d3 = Math.atan(d / d2) * 57.2957796;
            if (d2 < 0.0) {
                d3 = d > 0.0 ? (d3 = d3 + 180.0) : (d < 0.0 ? (d3 = d3 - 180.0) : 180.0);
            }
        }
        return d3;
    }

    protected final Vec3d getVectorForRotation(float f, float f2) {
        float f3 = MathHelper.cos((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f4 = MathHelper.sin((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f5 = -MathHelper.cos((float)(-f * ((float)Math.PI / 180)));
        float f6 = MathHelper.sin((float)(-f * ((float)Math.PI / 180)));
        return new Vec3d((double)(f4 * f5), (double)f6, (double)(f3 * f5));
    }
}

