//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockEmptyDrops
 *  net.minecraft.block.BlockObsidian
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.CombatRules
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package me.snow.aclient.util.skid.oyvey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import me.snow.aclient.AliceMain;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.PlayerUtil;
import me.snow.aclient.util.Util;
import me.snow.aclient.util.skid.oyvey.InventoryUtil2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEmptyDrops;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CrystalUtil
implements Util {
    public static final /* synthetic */ AxisAlignedBB CRYSTAL_AABB;

    protected static final Vec3d getVectorForRotation(double d, double d2) {
        float f = MathHelper.cos((float)((float)(-d2 * 0.01745329238474369 - 3.1415927410125732)));
        float f2 = MathHelper.sin((float)((float)(-d2 * 0.01745329238474369 - 3.1415927410125732)));
        float f3 = -MathHelper.cos((float)((float)(-d * 0.01745329238474369)));
        float f4 = MathHelper.sin((float)((float)(-d * 0.01745329238474369)));
        return new Vec3d((double)(f2 * f3), (double)f4, (double)(f * f3));
    }

    public static boolean placeCrystalSilent(BlockPos blockPos) {
        blockPos.offset(EnumFacing.DOWN);
        double d = (double)blockPos.getX() + 0.5 - CrystalUtil.mc.player.posX;
        double d2 = (double)blockPos.getY() + 0.5 - CrystalUtil.mc.player.posY - 0.5 - (double)CrystalUtil.mc.player.getEyeHeight();
        double d3 = (double)blockPos.getZ() + 0.5 - CrystalUtil.mc.player.posZ;
        double d4 = CrystalUtil.getDirection2D(d3, d);
        double d5 = CrystalUtil.getDirection2D(d2, Math.sqrt(d * d + d3 * d3));
        int n = InventoryUtil2.pickItem(426, false);
        if (n == -1 && ((ItemStack)CrystalUtil.mc.player.inventory.offHandInventory.get(0)).getItem() != Items.END_CRYSTAL) {
            return false;
        }
        Vec3d vec3d = CrystalUtil.getVectorForRotation(-d5, d4 - 90.0);
        if (((ItemStack)CrystalUtil.mc.player.inventory.offHandInventory.get(0)).getItem() == Items.END_CRYSTAL) {
            mc.getConnection().sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.offset(EnumFacing.DOWN), EnumFacing.UP, EnumHand.OFF_HAND, 0.0f, 0.0f, 0.0f));
        } else if (InventoryUtil2.pickItem(426, false) != -1) {
            mc.getConnection().sendPacket((Packet)new CPacketHeldItemChange(n));
            mc.getConnection().sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.offset(EnumFacing.DOWN), EnumFacing.UP, EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
        }
        return true;
    }

    public static List<Block> getBlocks() {
        return Arrays.asList(new Block[]{Blocks.OBSIDIAN, Blocks.BEDROCK, Blocks.COMMAND_BLOCK, Blocks.BARRIER, Blocks.ENCHANTING_TABLE, Blocks.ENDER_CHEST, Blocks.END_PORTAL_FRAME, Blocks.BEACON, Blocks.ANVIL});
    }

    public static EntityPlayer getTarget(float f) {
        EntityPlayer entityPlayer = null;
        for (EntityPlayer entityPlayer2 : new ArrayList(CrystalUtil.mc.world.playerEntities)) {
            if (CrystalUtil.mc.player.getDistanceSqToEntity((Entity)entityPlayer2) > MathUtil.square(f) || entityPlayer2 == CrystalUtil.mc.player || AliceMain.friendManager.isFriend(entityPlayer2.getName()) || entityPlayer2.isDead || entityPlayer2.getHealth() <= Float.intBitsToFloat(Float.floatToIntBits(1.2784752E38f) ^ 0x7EC05D13)) continue;
            if (entityPlayer == null) {
                entityPlayer = entityPlayer2;
                continue;
            }
            if (CrystalUtil.mc.player.getDistanceSqToEntity((Entity)entityPlayer2) >= CrystalUtil.mc.player.getDistanceSqToEntity((Entity)entityPlayer)) continue;
            entityPlayer = entityPlayer2;
        }
        return entityPlayer;
    }

    public static float getDamageFromDifficulty(float f) {
        switch (CrystalUtil.mc.world.getDifficulty()) {
            case PEACEFUL: {
                return 0.0f;
            }
            case EASY: {
                return Math.min(f / 2.0f + 1.0f, f);
            }
            case HARD: {
                return f * 3.0f / 2.0f;
            }
        }
        return f;
    }

    public static boolean isOnGround(double d, double d2, double d3, Entity entity) {
        try {
            double d4 = d2;
            List list = CrystalUtil.mc.world.getCollisionBoxes(entity, entity.getEntityBoundingBox().addCoord(d, d2, d3));
            if (d2 != 0.0) {
                int n = list.size();
                for (int i = 0; i < n; ++i) {
                    d2 = ((AxisAlignedBB)list.get(i)).calculateYOffset(entity.getEntityBoundingBox(), d2);
                }
            }
            return d4 != d2 && d4 < 0.0;
        }
        catch (Exception exception) {
            return false;
        }
    }

    public static EnumActionResult placeCrystal(BlockPos blockPos) {
        blockPos.offset(EnumFacing.DOWN);
        double d = (double)blockPos.getX() + 0.5 - CrystalUtil.mc.player.posX;
        double d2 = (double)blockPos.getY() + 0.5 - CrystalUtil.mc.player.posY - 0.5 - (double)CrystalUtil.mc.player.getEyeHeight();
        double d3 = (double)blockPos.getZ() + 0.5 - CrystalUtil.mc.player.posZ;
        double d4 = CrystalUtil.getDirection2D(d3, d);
        double d5 = CrystalUtil.getDirection2D(d2, Math.sqrt(d * d + d3 * d3));
        Vec3d vec3d = CrystalUtil.getVectorForRotation(-d5, d4 - 90.0);
        if (((ItemStack)CrystalUtil.mc.player.inventory.offHandInventory.get(0)).getItem().getClass().equals(Item.getItemById((int)426).getClass())) {
            return CrystalUtil.mc.playerController.processRightClickBlock(CrystalUtil.mc.player, CrystalUtil.mc.world, blockPos.offset(EnumFacing.DOWN), EnumFacing.UP, vec3d, EnumHand.OFF_HAND);
        }
        if (InventoryUtil2.pickItem(426, false) != -1) {
            InventoryUtil2.setSlot(InventoryUtil2.pickItem(426, false));
            return CrystalUtil.mc.playerController.processRightClickBlock(CrystalUtil.mc.player, CrystalUtil.mc.world, blockPos.offset(EnumFacing.DOWN), EnumFacing.UP, vec3d, EnumHand.MAIN_HAND);
        }
        return EnumActionResult.FAIL;
    }

    public static EntityPlayer placeValue(double d, double d2, double d3, EntityPlayer entityPlayer) {
        int n;
        int n2;
        List list = CrystalUtil.mc.world.getCollisionBoxes((Entity)entityPlayer, entityPlayer.getEntityBoundingBox().addCoord(d, d2, d3));
        if (d2 != 0.0) {
            n2 = list.size();
            for (n = 0; n < n2; ++n) {
                d2 = ((AxisAlignedBB)list.get(n)).calculateYOffset(entityPlayer.getEntityBoundingBox(), d2);
            }
            if (d2 != 0.0) {
                entityPlayer.setEntityBoundingBox(entityPlayer.getEntityBoundingBox().offset(0.0, d2, 0.0));
            }
        }
        if (d != 0.0) {
            n2 = list.size();
            for (n = 0; n < n2; ++n) {
                d = CrystalUtil.calculateXOffset(entityPlayer.getEntityBoundingBox(), d, (AxisAlignedBB)list.get(n));
            }
            if (d != 0.0) {
                entityPlayer.setEntityBoundingBox(entityPlayer.getEntityBoundingBox().offset(d, 0.0, 0.0));
            }
        }
        if (d3 != 0.0) {
            n2 = list.size();
            for (n = 0; n < n2; ++n) {
                d3 = CrystalUtil.calculateZOffset(entityPlayer.getEntityBoundingBox(), d3, (AxisAlignedBB)list.get(n));
            }
            if (d3 != 0.0) {
                entityPlayer.setEntityBoundingBox(entityPlayer.getEntityBoundingBox().offset(0.0, 0.0, d3));
            }
        }
        return entityPlayer;
    }

    static {
        CRYSTAL_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 2.0, 1.0);
    }

    public static boolean canSeePos(BlockPos blockPos) {
        return CrystalUtil.mc.world.rayTraceBlocks(new Vec3d(CrystalUtil.mc.player.posX, CrystalUtil.mc.player.posY + (double)CrystalUtil.mc.player.getEyeHeight(), CrystalUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()), false, true, false) == null;
    }

    public static float calculateDamage(Entity entity, Entity entity2, boolean bl) {
        return CrystalUtil.getExplosionDamage(entity2, new Vec3d(entity.posX, entity.posY, entity.posZ), 6.0f, bl);
    }

    public static double calculateXOffset(AxisAlignedBB axisAlignedBB, double d, AxisAlignedBB axisAlignedBB2) {
        if (axisAlignedBB.maxY > axisAlignedBB2.minY && axisAlignedBB.minY < axisAlignedBB2.maxY && axisAlignedBB.maxZ > axisAlignedBB2.minZ && axisAlignedBB.minZ < axisAlignedBB2.maxZ) {
            if (d > 0.0 && axisAlignedBB.maxX <= axisAlignedBB2.minX) {
                double d2 = axisAlignedBB2.minX - 0.3 - axisAlignedBB.maxX;
                if (d2 < d) {
                    d = d2;
                }
            } else if (d < 0.0 && axisAlignedBB.minX >= axisAlignedBB2.maxX) {
                double d3;
                double d4 = axisAlignedBB2.maxX + 0.3 - axisAlignedBB.minX;
                if (d3 > d) {
                    d = d4;
                }
            }
        }
        return d;
    }

    public static boolean canPlace(BlockPos blockPos) {
        if (!(CrystalUtil.mc.world.getBlockState(blockPos.offset(EnumFacing.DOWN)).getBlock() instanceof BlockEmptyDrops)) {
            return false;
        }
        if (!(CrystalUtil.mc.world.getBlockState(blockPos.offset(EnumFacing.DOWN)).getBlock() instanceof BlockObsidian)) {
            return false;
        }
        return CrystalUtil.mc.world.checkNoEntityCollision(new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 2.0, 1.0).offset(blockPos), (Entity)null);
    }

    public static boolean rayTraceSolidCheck(Vec3d vec3d, Vec3d vec3d2, boolean bl) {
        if (!(Double.isNaN(vec3d.xCoord) || Double.isNaN(vec3d.yCoord) || Double.isNaN(vec3d.zCoord) || Double.isNaN(vec3d2.xCoord) || Double.isNaN(vec3d2.yCoord) || Double.isNaN(vec3d2.zCoord))) {
            int n = MathHelper.floor((double)vec3d.xCoord);
            int n2 = MathHelper.floor((double)vec3d.yCoord);
            int n3 = MathHelper.floor((double)vec3d.zCoord);
            int n4 = MathHelper.floor((double)vec3d2.xCoord);
            int n5 = MathHelper.floor((double)vec3d2.yCoord);
            int n6 = MathHelper.floor((double)vec3d2.zCoord);
            BlockPos blockPos = new BlockPos(n, n2, n3);
            IBlockState iBlockState = CrystalUtil.mc.world.getBlockState(blockPos);
            Block block = iBlockState.getBlock();
            if (iBlockState.getCollisionBoundingBox((IBlockAccess)CrystalUtil.mc.world, blockPos) != Block.NULL_AABB && block.canCollideCheck(iBlockState, false) && (CrystalUtil.getBlocks().contains((Object)block) || !bl)) {
                return true;
            }
            double d = vec3d2.xCoord - vec3d.xCoord;
            double d2 = vec3d2.yCoord - vec3d.yCoord;
            double d3 = vec3d2.zCoord - vec3d.zCoord;
            int n7 = 200;
            while (n7-- >= 0) {
                EnumFacing enumFacing;
                if (Double.isNaN(vec3d.xCoord) || Double.isNaN(vec3d.yCoord) || Double.isNaN(vec3d.zCoord)) {
                    return false;
                }
                if (n == n4 && n2 == n5 && n3 == n6) {
                    return false;
                }
                boolean bl2 = true;
                boolean bl3 = true;
                boolean bl4 = true;
                double d4 = 999.0;
                double d5 = 999.0;
                double d6 = 999.0;
                double d7 = 999.0;
                double d8 = 999.0;
                double d9 = 999.0;
                if (n4 > n) {
                    d4 = (double)n + 1.0;
                } else if (n4 < n) {
                    d4 = n;
                } else {
                    bl2 = false;
                }
                if (n5 > n2) {
                    d5 = (double)n2 + 1.0;
                } else if (n5 < n2) {
                    d5 = n2;
                } else {
                    bl3 = false;
                }
                if (n6 > n3) {
                    d6 = (double)n3 + 1.0;
                } else if (n6 < n3) {
                    d6 = n3;
                } else {
                    bl4 = false;
                }
                if (bl2) {
                    d7 = (d4 - vec3d.xCoord) / d;
                }
                if (bl3) {
                    d8 = (d5 - vec3d.yCoord) / d2;
                }
                if (bl4) {
                    d9 = (d6 - vec3d.zCoord) / d3;
                }
                if (d7 == 0.0) {
                    d7 = -1.0E-4;
                }
                if (d8 == 0.0) {
                    d8 = -1.0E-4;
                }
                if (d9 == 0.0) {
                    d9 = -1.0E-4;
                }
                if (d7 < d8 && d7 < d9) {
                    enumFacing = n4 > n ? EnumFacing.WEST : EnumFacing.EAST;
                    vec3d = new Vec3d(d4, vec3d.yCoord + d2 * d7, vec3d.zCoord + d3 * d7);
                } else if (d8 < d9) {
                    enumFacing = n5 > n2 ? EnumFacing.DOWN : EnumFacing.UP;
                    vec3d = new Vec3d(vec3d.xCoord + d * d8, d5, vec3d.zCoord + d3 * d8);
                } else {
                    enumFacing = n6 > n3 ? EnumFacing.NORTH : EnumFacing.SOUTH;
                    vec3d = new Vec3d(vec3d.xCoord + d * d9, vec3d.yCoord + d2 * d9, d6);
                }
                n = MathHelper.floor((double)vec3d.xCoord) - (enumFacing == EnumFacing.EAST ? 1 : 0);
                n2 = MathHelper.floor((double)vec3d.yCoord) - (enumFacing == EnumFacing.UP ? 1 : 0);
                n3 = MathHelper.floor((double)vec3d.zCoord) - (enumFacing == EnumFacing.SOUTH ? 1 : 0);
                blockPos = new BlockPos(n, n2, n3);
                iBlockState = CrystalUtil.mc.world.getBlockState(blockPos);
                block = iBlockState.getBlock();
                if (!block.canCollideCheck(iBlockState, false) || !CrystalUtil.getBlocks().contains((Object)block) && bl) continue;
                return true;
            }
        }
        return false;
    }

    public static float getDamageMultiplied(float f) {
        int n = CrystalUtil.mc.world.getDifficulty().getDifficultyId();
        return f * (n == 0 ? 0.0f : (n == 2 ? 1.0f : (n == 1 ? 0.5f : 1.5f)));
    }

    public static List<BlockPos> possiblePlacePositions(float f, boolean bl, boolean bl2) {
        NonNullList nonNullList = NonNullList.func_191196_a();
        nonNullList.addAll((Collection)CrystalUtil.getSphere(PlayerUtil.getPlayerPos(), f, (int)f, false, true, 0).stream().filter(blockPos -> CrystalUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.AIR).filter(blockPos -> CrystalUtil.canPlaceCrystal(blockPos, bl, bl2)).collect(Collectors.toList()));
        return nonNullList;
    }

    public static float getBlastReduction(EntityLivingBase entityLivingBase, float f, Explosion explosion) {
        f = CombatRules.getDamageAfterAbsorb((float)f, (float)entityLivingBase.getTotalArmorValue(), (float)((float)entityLivingBase.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue()));
        float f2 = 0.0f;
        try {
            f2 = EnchantmentHelper.getEnchantmentModifierDamage((Iterable)entityLivingBase.getArmorInventoryList(), (DamageSource)DamageSource.causeExplosionDamage((Explosion)explosion));
        }
        catch (Exception exception) {
            // empty catch block
        }
        f2 = MathHelper.clamp((float)f2, (float)0.0f, (float)20.0f);
        f *= 1.0f - f2 / 25.0f;
        PotionEffect potionEffect = entityLivingBase.getActivePotionEffect(MobEffects.RESISTANCE);
        if (entityLivingBase.isPotionActive(MobEffects.RESISTANCE) && potionEffect != null) {
            f = f * (25.0f - (float)(potionEffect.getAmplifier() + 1) * 5.0f) / 25.0f;
        }
        f = Math.max(f, 0.0f);
        return f;
    }

    public static double getRange(Vec3d vec3d, double d, double d2, double d3) {
        double d4 = vec3d.xCoord - d;
        double d5 = vec3d.yCoord - d2;
        double d6 = vec3d.zCoord - d3;
        return Math.sqrt(d4 * d4 + d5 * d5 + d6 * d6);
    }

    public static double getDamage(Vec3d vec3d, @Nullable Entity entity) {
        Entity entity2 = entity == null ? CrystalUtil.mc.player : entity;
        float f = 6.0f;
        float f2 = f * 2.0f;
        Vec3d vec3d2 = vec3d;
        if (!entity2.isImmuneToExplosions()) {
            double d;
            double d2;
            double d3;
            double d4;
            double d5;
            double d6 = entity2.getDistance(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord) / (double)f2;
            if (d5 <= 1.0 && (d4 = (double)MathHelper.sqrt((double)((d3 = entity2.posX - vec3d.xCoord) * d3 + (d2 = entity2.posY + (double)entity2.getEyeHeight() - vec3d.yCoord) * d2 + (d = entity2.posZ - vec3d.zCoord) * d))) != 0.0) {
                d3 /= d4;
                d2 /= d4;
                d /= d4;
                double d7 = CrystalUtil.mc.world.getBlockDensity(vec3d, entity2.getEntityBoundingBox());
                double d8 = (1.0 - d6) * d7;
                return (int)((d8 * d8 + d8) / 2.0 * 7.0 * (double)f2 + 1.0);
            }
        }
        return 0.0;
    }

    public static boolean canPlaceCrystal(BlockPos blockPos, boolean bl, boolean bl2) {
        BlockPos blockPos2 = blockPos.add(0, 1, 0);
        BlockPos blockPos3 = blockPos.add(0, 2, 0);
        try {
            if (!bl2) {
                if (CrystalUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && CrystalUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                    return false;
                }
                if (CrystalUtil.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR || CrystalUtil.mc.world.getBlockState(blockPos3).getBlock() != Blocks.AIR) {
                    return false;
                }
                if (!bl) {
                    return CrystalUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2)).isEmpty() && CrystalUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos3)).isEmpty();
                }
                for (Entity entity : CrystalUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2))) {
                    if (entity instanceof EntityEnderCrystal) continue;
                    return false;
                }
                for (Entity entity : CrystalUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos3))) {
                    if (entity instanceof EntityEnderCrystal) continue;
                    return false;
                }
            } else {
                if (CrystalUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && CrystalUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                    return false;
                }
                if (CrystalUtil.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR) {
                    return false;
                }
                if (!bl) {
                    return CrystalUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2)).isEmpty();
                }
                for (Entity entity : CrystalUtil.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2))) {
                    if (entity instanceof EntityEnderCrystal) continue;
                    return false;
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    protected static final double getDirection2D(double d, double d2) {
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

    public static void placeCrystalOnBlock(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2) {
        RayTraceResult rayTraceResult = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + (double)BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d((double)blockPos.getX() + 0.5, (double)blockPos.getY() - 0.5, (double)blockPos.getZ() + 0.5));
        EnumFacing enumFacing = rayTraceResult == null || rayTraceResult.sideHit == null ? EnumFacing.UP : rayTraceResult.sideHit;
        int n = BlockUtil.mc.player.inventory.currentItem;
        int n2 = InventoryUtil2.getItemHotbar(Items.END_CRYSTAL);
        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, 0.0f, 0.0f, 0.0f));
        if (bl) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketAnimation(bl2 ? enumHand : EnumHand.MAIN_HAND));
        }
    }

    public static List<BlockPos> getSphere(Vec3d vec3d, double d, double d2, boolean bl, boolean bl2, int n) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        int n2 = (int)vec3d.xCoord;
        int n3 = (int)vec3d.yCoord;
        int n4 = (int)vec3d.zCoord;
        int n5 = n2 - (int)d;
        while ((double)n5 <= (double)n2 + d) {
            int n6 = n4 - (int)d;
            while ((double)n6 <= (double)n4 + d) {
                int n7 = bl2 ? n3 - (int)d : n3;
                while (true) {
                    double d3;
                    double d4 = n7;
                    double d5 = d3 = bl2 ? (double)n3 + d : (double)n3 + d2;
                    if (!(d4 < d3)) break;
                    double d6 = (n2 - n5) * (n2 - n5) + (n4 - n6) * (n4 - n6) + (bl2 ? (n3 - n7) * (n3 - n7) : 0);
                    if (!(!(d6 < d * d) || bl && d6 < (d - 1.0) * (d - 1.0))) {
                        BlockPos blockPos = new BlockPos(n5, n7 + n, n6);
                        arrayList.add(blockPos);
                    }
                    ++n7;
                }
                ++n6;
            }
            ++n5;
        }
        return arrayList;
    }

    public static List<BlockPos> getSphere(float f, boolean bl, boolean bl2) {
        float f2 = f;
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        int n = CrystalUtil.mc.player.getPosition().getX() - (int)f2;
        while ((float)n <= (float)CrystalUtil.mc.player.getPosition().getX() + f2) {
            int n2 = CrystalUtil.mc.player.getPosition().getZ() - (int)f2;
            while ((float)n2 <= (float)CrystalUtil.mc.player.getPosition().getZ() + f2) {
                int n3;
                boolean bl3 = bl;
                int n4 = n3 = bl3 ? CrystalUtil.mc.player.getPosition().getY() - (int)f2 : CrystalUtil.mc.player.getPosition().getY();
                while ((float)n3 < (float)CrystalUtil.mc.player.getPosition().getY() + f2) {
                    boolean bl4 = bl2;
                    double d = (CrystalUtil.mc.player.getPosition().getX() - n) * (CrystalUtil.mc.player.getPosition().getX() - n) + (CrystalUtil.mc.player.getPosition().getZ() - n2) * (CrystalUtil.mc.player.getPosition().getZ() - n2) + (bl3 ? (CrystalUtil.mc.player.getPosition().getY() - n3) * (CrystalUtil.mc.player.getPosition().getY() - n3) : 0);
                    if (d < (double)(f2 * f2) && (!bl4 || d >= ((double)f2 - Double.longBitsToDouble(Double.doubleToLongBits(638.4060856917202) ^ 0x7F73F33FA9DAEA7FL)) * ((double)f2 - Double.longBitsToDouble(Double.doubleToLongBits(13.015128470890444) ^ 0x7FDA07BEEB3F6D07L)))) {
                        arrayList.add(new BlockPos(n, n3, n2));
                    }
                    ++n3;
                }
                ++n2;
            }
            ++n;
        }
        return arrayList;
    }

    public static void placeCrystalOnBlock(BlockPos blockPos, EnumHand enumHand, boolean bl, boolean bl2, boolean bl3) {
        RayTraceResult rayTraceResult = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + (double)BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d((double)blockPos.getX() + 0.5, (double)blockPos.getY() - 0.5, (double)blockPos.getZ() + 0.5));
        EnumFacing enumFacing = rayTraceResult == null || rayTraceResult.sideHit == null ? EnumFacing.UP : rayTraceResult.sideHit;
        int n = BlockUtil.mc.player.inventory.currentItem;
        int n2 = InventoryUtil2.getItemHotbar(Items.END_CRYSTAL);
        if (enumHand == EnumHand.MAIN_HAND && bl3 && n2 != -1 && n2 != BlockUtil.mc.player.inventory.currentItem) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n2));
        }
        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, 0.0f, 0.0f, 0.0f));
        if (enumHand == EnumHand.MAIN_HAND && bl3 && n2 != -1 && n2 != BlockUtil.mc.player.inventory.currentItem) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
        }
        if (bl) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketAnimation(bl2 ? enumHand : EnumHand.MAIN_HAND));
        }
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

    public static float calculateDamage(BlockPos blockPos, Entity entity, boolean bl) {
        return CrystalUtil.getExplosionDamage(entity, new Vec3d((double)blockPos.getX() + 0.5, (double)(blockPos.getY() + 1), (double)blockPos.getZ() + 0.5), 6.0f, bl);
    }

    public static Entity getPredictedPosition(Entity entity, double d) {
        if (d == 0.0) {
            return entity;
        }
        EntityPlayer entityPlayer = null;
        double d2 = entity.posX - entity.lastTickPosX;
        double d3 = entity.posY - entity.lastTickPosY;
        double d4 = entity.posZ - entity.lastTickPosZ;
        boolean bl = false;
        boolean bl2 = false;
        double d5 = Math.sqrt(Math.pow(d2, 2.0) + Math.pow(d4, 2.0) + Math.pow(d3, 2.0));
        if (d5 > 0.1) {
            bl = true;
        }
        if (!bl) {
            return entity;
        }
        if (d5 > 0.31) {
            bl2 = true;
        }
        int n = 0;
        while ((double)n < d) {
            if (entityPlayer == null) {
                if (CrystalUtil.isOnGround(0.0, 0.0, 0.0, entity)) {
                    d3 = bl2 ? 0.4 : -0.07840015258789;
                } else {
                    d3 -= 0.08;
                    d3 *= (double)0.98f;
                }
                entityPlayer = CrystalUtil.placeValue(d2, d3, d4, (EntityPlayer)entity);
            } else {
                if (CrystalUtil.isOnGround(0.0, 0.0, 0.0, entityPlayer)) {
                    d3 = bl2 ? 0.4 : -0.07840015258789;
                } else {
                    d3 -= 0.08;
                    d3 *= (double)0.98f;
                }
                entityPlayer = CrystalUtil.placeValue(d2, d3, d4, entityPlayer);
            }
            ++n;
        }
        return entityPlayer;
    }

    public static Boolean getArmorBreaker(EntityPlayer entityPlayer, float f) {
        for (ItemStack itemStack : entityPlayer.getArmorInventoryList()) {
            if (itemStack == null || itemStack.getItem() == Items.field_190931_a) {
                return true;
            }
            float f2 = (float)(itemStack.getMaxDamage() - itemStack.getItemDamage()) / (float)itemStack.getMaxDamage() * 100.0f;
            if (!(f >= f2) || itemStack.stackSize >= 2) continue;
            return true;
        }
        return false;
    }

    public static float getExplosionDamage(Entity entity, Vec3d vec3d, float f, boolean bl) {
        Vec3d vec3d2 = new Vec3d(entity.posX, entity.posY, entity.posZ);
        if (entity.isImmuneToExplosions()) {
            return 0.0f;
        }
        double d = vec3d2.distanceTo(vec3d) / (double)(f *= 2.0f);
        double d2 = 0.0;
        AxisAlignedBB axisAlignedBB = entity.getEntityBoundingBox().func_191194_a(entity.getPositionVector().subtract(vec3d2));
        Vec3d vec3d3 = new Vec3d(1.0 / ((axisAlignedBB.maxX - axisAlignedBB.minX) * 2.0 + 1.0), 1.0 / ((axisAlignedBB.maxY - axisAlignedBB.minY) * 2.0 + 1.0), 1.0 / ((axisAlignedBB.maxZ - axisAlignedBB.minZ) * 2.0 + 1.0));
        double d3 = (1.0 - Math.floor(1.0 / vec3d3.xCoord) * vec3d3.xCoord) / 2.0;
        double d4 = (1.0 - Math.floor(1.0 / vec3d3.zCoord) * vec3d3.zCoord) / 2.0;
        if (vec3d3.xCoord >= 0.0 && vec3d3.yCoord >= 0.0 && vec3d3.zCoord >= 0.0) {
            int n = 0;
            int n2 = 0;
            for (double d5 = 0.0; d5 <= 1.0; d5 += vec3d3.xCoord) {
                for (double d6 = 0.0; d6 <= 1.0; d6 += vec3d3.yCoord) {
                    for (double d7 = 0.0; d7 <= 1.0; d7 += vec3d3.zCoord) {
                        Vec3d vec3d4 = new Vec3d(d3 + axisAlignedBB.minX + (axisAlignedBB.maxX - axisAlignedBB.minX) * d5, axisAlignedBB.minY + (axisAlignedBB.maxY - axisAlignedBB.minY) * d6, d4 + axisAlignedBB.minZ + (axisAlignedBB.maxZ - axisAlignedBB.minZ) * d7);
                        if (!CrystalUtil.rayTraceSolidCheck(vec3d4, vec3d, bl)) {
                            ++n;
                        }
                        ++n2;
                    }
                }
            }
            d2 = (double)n / (double)n2;
        }
        double d8 = (1.0 - d) * d2;
        float f2 = (int)((d8 * d8 + d8) / 2.0 * 7.0 * (double)f + 1.0);
        if (entity instanceof EntityLivingBase) {
            f2 = CrystalUtil.getBlastReduction((EntityLivingBase)entity, CrystalUtil.getDamageFromDifficulty(f2), new Explosion((World)CrystalUtil.mc.world, null, vec3d.xCoord, vec3d.yCoord, vec3d.zCoord, f / 2.0f, false, true));
        }
        return f2;
    }

    public static float calculateDamage(double d, double d2, double d3, Entity entity) {
        Vec3d vec3d = new Vec3d(entity.posX, entity.posY, entity.posZ);
        return CrystalUtil.calculateDamage(d, d2, d3, entity, vec3d);
    }

    public static double calculateZOffset(AxisAlignedBB axisAlignedBB, double d, AxisAlignedBB axisAlignedBB2) {
        if (axisAlignedBB.maxX > axisAlignedBB2.minX && axisAlignedBB.minX < axisAlignedBB2.maxX && axisAlignedBB.maxY > axisAlignedBB2.minY && axisAlignedBB.minY < axisAlignedBB2.maxY) {
            if (d > 0.0 && axisAlignedBB.maxZ <= axisAlignedBB2.minZ) {
                double d2 = axisAlignedBB2.minZ - 0.3 - axisAlignedBB.maxZ;
                if (d2 < d) {
                    d = d2;
                }
            } else if (d < 0.0 && axisAlignedBB.minZ >= axisAlignedBB2.maxZ) {
                double d3;
                double d4 = axisAlignedBB2.maxZ + 0.3 - axisAlignedBB.minZ;
                if (d3 > d) {
                    d = d4;
                }
            }
        }
        return d;
    }

    public static float calculateDamage(double d, double d2, double d3, Entity entity, Vec3d vec3d) {
        float f = 12.0f;
        double d4 = CrystalUtil.getRange(vec3d, d, d2, d3) / (double)f;
        Vec3d vec3d2 = new Vec3d(d, d2, d3);
        double d5 = entity.world.getBlockDensity(vec3d2, entity.getEntityBoundingBox());
        double d6 = (1.0 - d4) * d5;
        float f2 = (int)((d6 * d6 + d6) / 2.0 * 7.0 * (double)f + 1.0);
        double d7 = 1.0;
        if (entity instanceof EntityLivingBase) {
            d7 = CrystalUtil.getBlastReduction((EntityLivingBase)entity, CrystalUtil.getDamageMultiplied(f2), new Explosion((World)CrystalUtil.mc.world, null, d, d2, d3, 6.0f, false, true));
        }
        return (float)d7;
    }

    public static EnumActionResult doPlace(BlockPos blockPos) {
        double d = (double)blockPos.getX() + 0.5 - CrystalUtil.mc.player.posX;
        double d2 = (double)(blockPos.getY() - 1) + 0.5 - CrystalUtil.mc.player.posY - 0.5 - (double)CrystalUtil.mc.player.getEyeHeight();
        double d3 = (double)blockPos.getZ() + 0.5 - CrystalUtil.mc.player.posZ;
        double d4 = CrystalUtil.getDirection2D(d3, d);
        double d5 = CrystalUtil.getDirection2D(d2, Math.sqrt(d * d + d3 * d3));
        Vec3d vec3d = CrystalUtil.getVectorForRotation(-d5, d4 - 90.0);
        return CrystalUtil.mc.playerController.processRightClickBlock(CrystalUtil.mc.player, CrystalUtil.mc.world, blockPos.offset(EnumFacing.DOWN), EnumFacing.UP, vec3d, CrystalUtil.mc.player.getActiveHand());
    }
}

