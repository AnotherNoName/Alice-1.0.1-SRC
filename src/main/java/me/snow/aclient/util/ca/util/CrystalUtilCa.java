//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
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
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.CombatRules
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package me.snow.aclient.util.ca.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import me.snow.aclient.command.Command;
import me.snow.aclient.util.DamageUtil;
import me.snow.aclient.util.Util;
import me.snow.aclient.util.ca.util.PlayerUtilCa;
import net.minecraft.block.Block;
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
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CrystalUtilCa
implements Util {
    public static float getDamageFromDifficulty(float f) {
        switch (CrystalUtilCa.mc.world.getDifficulty()) {
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

    public static double calculateZOffset(AxisAlignedBB axisAlignedBB, double d, AxisAlignedBB axisAlignedBB2) {
        if (axisAlignedBB.maxX > axisAlignedBB2.minX && axisAlignedBB.minX < axisAlignedBB2.maxX && axisAlignedBB.maxY > axisAlignedBB2.minY && axisAlignedBB.minY < axisAlignedBB2.maxY) {
            double d2;
            if (d > 0.0 && axisAlignedBB.maxZ <= axisAlignedBB2.minZ) {
                double d3 = axisAlignedBB2.minZ - 0.3 - axisAlignedBB.maxZ;
                if (d3 < d) {
                    d = d3;
                }
            } else if (d < 0.0 && axisAlignedBB.minZ >= axisAlignedBB2.maxZ && (d2 = axisAlignedBB2.maxZ + 0.3 - axisAlignedBB.minZ) > d) {
                d = d2;
            }
        }
        return d;
    }

    public static List<Block> getBlocks() {
        return Arrays.asList(new Block[]{Blocks.OBSIDIAN, Blocks.BEDROCK, Blocks.COMMAND_BLOCK, Blocks.BARRIER, Blocks.ENCHANTING_TABLE, Blocks.ENDER_CHEST, Blocks.END_PORTAL_FRAME, Blocks.BEACON, Blocks.ANVIL});
    }

    public static boolean isClose(int n, int n2, double d) {
        return (double)n2 + d < (double)n - d && (double)n - d < (double)n2;
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
                if (CrystalUtilCa.isOnGround(0.0, 0.0, 0.0, entity)) {
                    d3 = bl2 ? 0.4 : -0.07840015258789;
                } else {
                    d3 -= 0.08;
                    d3 *= (double)0.98f;
                }
                entityPlayer = CrystalUtilCa.placeValue(d2, d3, d4, (EntityPlayer)entity);
            } else {
                if (CrystalUtilCa.isOnGround(0.0, 0.0, 0.0, entityPlayer)) {
                    d3 = bl2 ? 0.4 : -0.07840015258789;
                } else {
                    d3 -= 0.08;
                    d3 *= (double)0.98f;
                }
                entityPlayer = CrystalUtilCa.placeValue(d2, d3, d4, entityPlayer);
            }
            ++n;
        }
        return entityPlayer;
    }

    public static double calculateXOffset(AxisAlignedBB axisAlignedBB, double d, AxisAlignedBB axisAlignedBB2) {
        if (axisAlignedBB.maxY > axisAlignedBB2.minY && axisAlignedBB.minY < axisAlignedBB2.maxY && axisAlignedBB.maxZ > axisAlignedBB2.minZ && axisAlignedBB.minZ < axisAlignedBB2.maxZ) {
            double d2;
            if (d > 0.0 && axisAlignedBB.maxX <= axisAlignedBB2.minX) {
                double d3 = axisAlignedBB2.minX - 0.3 - axisAlignedBB.maxX;
                if (d3 < d) {
                    d = d3;
                }
            } else if (d < 0.0 && axisAlignedBB.minX >= axisAlignedBB2.maxX && (d2 = axisAlignedBB2.maxX + 0.3 - axisAlignedBB.minX) > d) {
                d = d2;
            }
        }
        return d;
    }

    public static float calculateDamage(Entity entity, Entity entity2, boolean bl) {
        return CrystalUtilCa.getExplosionDamage(entity2, new Vec3d(entity.posX, entity.posY, entity.posZ), 6.0f, bl);
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
            IBlockState iBlockState = CrystalUtilCa.mc.world.getBlockState(blockPos);
            Block block = iBlockState.getBlock();
            if (iBlockState.getCollisionBoundingBox((IBlockAccess)CrystalUtilCa.mc.world, blockPos) != Block.NULL_AABB && block.canCollideCheck(iBlockState, false) && (CrystalUtilCa.getBlocks().contains((Object)block) || !bl)) {
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
                if (!(block = (iBlockState = CrystalUtilCa.mc.world.getBlockState(blockPos = new BlockPos(n = MathHelper.floor((double)vec3d.xCoord) - (enumFacing == EnumFacing.EAST ? 1 : 0), n2 = MathHelper.floor((double)vec3d.yCoord) - (enumFacing == EnumFacing.UP ? 1 : 0), n3 = MathHelper.floor((double)vec3d.zCoord) - (enumFacing == EnumFacing.SOUTH ? 1 : 0)))).getBlock()).canCollideCheck(iBlockState, false) || !CrystalUtilCa.getBlocks().contains((Object)block) && bl) continue;
                return true;
            }
        }
        return false;
    }

    public static List<BlockPos> possiblePlacePositions(float f, boolean bl, boolean bl2) {
        NonNullList nonNullList = NonNullList.func_191196_a();
        nonNullList.addAll((Collection)CrystalUtilCa.getSphere(PlayerUtilCa.getPlayerPos(), f, (int)f, false, true, 0).stream().filter(blockPos -> CrystalUtilCa.mc.world.getBlockState(blockPos).getBlock() != Blocks.AIR).filter(blockPos -> CrystalUtilCa.canPlaceCrystal(blockPos, bl, bl2)).collect(Collectors.toList()));
        return nonNullList;
    }

    public static boolean isOnGround(double d, double d2, double d3, Entity entity) {
        try {
            double d4 = d2;
            List list = CrystalUtilCa.mc.world.getCollisionBoxes(entity, entity.getEntityBoundingBox().addCoord(d, d2, d3));
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

    public static boolean canSeePos(BlockPos blockPos) {
        return CrystalUtilCa.mc.world.rayTraceBlocks(new Vec3d(CrystalUtilCa.mc.player.posX, CrystalUtilCa.mc.player.posY + (double)CrystalUtilCa.mc.player.getEyeHeight(), CrystalUtilCa.mc.player.posZ), new Vec3d((double)blockPos.getX() + 0.5, (double)(blockPos.getY() + 1), (double)blockPos.getZ() + 0.5), false, true, false) == null;
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
                    float f2;
                    float f3 = n8;
                    float f4 = f2 = bl2 ? (float)n4 + f : (float)(n4 + n);
                    if (!(f3 < f2)) break;
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

    public static float getDamageMultiplied(float f) {
        int n = DamageUtil.mc.world.getDifficulty().getDifficultyId();
        return f * (n == 0 ? 0.0f : (n == 2 ? 1.0f : (n == 1 ? 0.5f : 1.5f)));
    }

    public static boolean canPlaceCrystal(BlockPos blockPos, boolean bl, boolean bl2) {
        BlockPos blockPos2 = blockPos.add(0, 1, 0);
        BlockPos blockPos3 = blockPos.add(0, 2, 0);
        try {
            if (!bl2) {
                if (CrystalUtilCa.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && CrystalUtilCa.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                    return false;
                }
                if (CrystalUtilCa.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR || CrystalUtilCa.mc.world.getBlockState(blockPos3).getBlock() != Blocks.AIR) {
                    return false;
                }
                if (!bl) {
                    return CrystalUtilCa.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2)).isEmpty() && CrystalUtilCa.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos3)).isEmpty();
                }
                for (Entity entity : CrystalUtilCa.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2))) {
                    if (entity instanceof EntityEnderCrystal) continue;
                    return false;
                }
                for (Entity entity : CrystalUtilCa.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos3))) {
                    if (entity instanceof EntityEnderCrystal) continue;
                    return false;
                }
            } else {
                if (CrystalUtilCa.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && CrystalUtilCa.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                    return false;
                }
                if (CrystalUtilCa.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR) {
                    return false;
                }
                if (!bl) {
                    return CrystalUtilCa.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2)).isEmpty();
                }
                for (Entity entity : CrystalUtilCa.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2))) {
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
                        if (!CrystalUtilCa.rayTraceSolidCheck(vec3d4, vec3d, bl)) {
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
            f2 = CrystalUtilCa.getBlastReduction((EntityLivingBase)entity, CrystalUtilCa.getDamageFromDifficulty(f2), new Explosion((World)CrystalUtilCa.mc.world, null, vec3d.xCoord, vec3d.yCoord, vec3d.zCoord, f / 2.0f, false, true));
        }
        return f2;
    }

    public static EntityPlayer placeValue(double d, double d2, double d3, EntityPlayer entityPlayer) {
        int n;
        int n2;
        List list = CrystalUtilCa.mc.world.getCollisionBoxes((Entity)entityPlayer, entityPlayer.getEntityBoundingBox().addCoord(d, d2, d3));
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
                d = CrystalUtilCa.calculateXOffset(entityPlayer.getEntityBoundingBox(), d, (AxisAlignedBB)list.get(n));
            }
            if (d != 0.0) {
                entityPlayer.setEntityBoundingBox(entityPlayer.getEntityBoundingBox().offset(d, 0.0, 0.0));
            }
        }
        if (d3 != 0.0) {
            n2 = list.size();
            for (n = 0; n < n2; ++n) {
                d3 = CrystalUtilCa.calculateZOffset(entityPlayer.getEntityBoundingBox(), d3, (AxisAlignedBB)list.get(n));
            }
            if (d3 != 0.0) {
                entityPlayer.setEntityBoundingBox(entityPlayer.getEntityBoundingBox().offset(0.0, 0.0, d3));
            }
        }
        return entityPlayer;
    }

    public static float calculateDamage(BlockPos blockPos, Entity entity, boolean bl) {
        return CrystalUtilCa.getExplosionDamage(entity, new Vec3d((double)blockPos.getX() + 0.5, (double)(blockPos.getY() + 1), (double)blockPos.getZ() + 0.5), 6.0f, bl);
    }

    public static boolean isClose(BlockPos blockPos, BlockPos blockPos2, double d) {
        return CrystalUtilCa.isClose(blockPos.getX(), blockPos2.getX(), d) && CrystalUtilCa.isClose(blockPos.getY(), blockPos2.getY(), d) && CrystalUtilCa.isClose(blockPos.getZ(), blockPos2.getZ(), d);
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

    public static EntityEnderCrystal isCrystalStuck(BlockPos blockPos) {
        for (Entity entity : CrystalUtilCa.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos))) {
            if (CrystalUtilCa.isClose(blockPos, entity.getPosition(), 0.5)) {
                Command.sendMessage("shit too close");
                continue;
            }
            if (!(entity instanceof EntityEnderCrystal)) continue;
            return (EntityEnderCrystal)entity;
        }
        return null;
    }

    public static Boolean getArmourFucker(EntityPlayer entityPlayer, float f) {
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
}

