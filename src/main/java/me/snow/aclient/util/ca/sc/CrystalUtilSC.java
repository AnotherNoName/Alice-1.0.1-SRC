//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.util.CombatRules
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package me.snow.aclient.util.ca.sc;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import me.snow.aclient.module.modules.combat.AliceAura;
import me.snow.aclient.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CrystalUtilSC {
    public static /* synthetic */ Minecraft mc;
    private static /* synthetic */ List<Block> valid;

    @Nullable
    public static RayTraceResult rayTraceBlocks(Vec3d vec3d, Vec3d vec3d2, boolean bl, boolean bl2, boolean bl3) {
        if (!(Double.isNaN(vec3d.xCoord) || Double.isNaN(vec3d.yCoord) || Double.isNaN(vec3d.zCoord))) {
            if (!(Double.isNaN(vec3d2.xCoord) || Double.isNaN(vec3d2.yCoord) || Double.isNaN(vec3d2.zCoord))) {
                RayTraceResult rayTraceResult;
                int n;
                int n2;
                int n3 = MathHelper.floor((double)vec3d2.xCoord);
                int n4 = MathHelper.floor((double)vec3d2.yCoord);
                int n5 = MathHelper.floor((double)vec3d2.zCoord);
                int n6 = MathHelper.floor((double)vec3d.xCoord);
                BlockPos blockPos = new BlockPos(n6, n2 = MathHelper.floor((double)vec3d.yCoord), n = MathHelper.floor((double)vec3d.zCoord));
                IBlockState iBlockState = CrystalUtilSC.mc.world.getBlockState(blockPos);
                Block block = iBlockState.getBlock();
                if (!valid.contains((Object)block)) {
                    block = Blocks.AIR;
                    iBlockState = Blocks.AIR.getBlockState().getBaseState();
                }
                if ((!bl2 || iBlockState.getCollisionBoundingBox((IBlockAccess)CrystalUtilSC.mc.world, blockPos) != Block.NULL_AABB) && block.canCollideCheck(iBlockState, bl) && (rayTraceResult = iBlockState.collisionRayTrace((World)CrystalUtilSC.mc.world, blockPos, vec3d, vec3d2)) != null) {
                    return rayTraceResult;
                }
                RayTraceResult rayTraceResult2 = null;
                int n7 = 200;
                while (n7-- >= 0) {
                    EnumFacing enumFacing;
                    if (Double.isNaN(vec3d.xCoord) || Double.isNaN(vec3d.yCoord) || Double.isNaN(vec3d.zCoord)) {
                        return null;
                    }
                    if (n6 == n3 && n2 == n4 && n == n5) {
                        return bl3 ? rayTraceResult2 : null;
                    }
                    boolean bl4 = true;
                    boolean bl5 = true;
                    boolean bl6 = true;
                    double d = 999.0;
                    double d2 = 999.0;
                    double d3 = 999.0;
                    if (n3 > n6) {
                        d = (double)n6 + 1.0;
                    } else if (n3 < n6) {
                        d = (double)n6 + 0.0;
                    } else {
                        bl4 = false;
                    }
                    if (n4 > n2) {
                        d2 = (double)n2 + 1.0;
                    } else if (n4 < n2) {
                        d2 = (double)n2 + 0.0;
                    } else {
                        bl5 = false;
                    }
                    if (n5 > n) {
                        d3 = (double)n + 1.0;
                    } else if (n5 < n) {
                        d3 = (double)n + 0.0;
                    } else {
                        bl6 = false;
                    }
                    double d4 = 999.0;
                    double d5 = 999.0;
                    double d6 = 999.0;
                    double d7 = vec3d2.xCoord - vec3d.xCoord;
                    double d8 = vec3d2.yCoord - vec3d.yCoord;
                    double d9 = vec3d2.zCoord - vec3d.zCoord;
                    if (bl4) {
                        d4 = (d - vec3d.xCoord) / d7;
                    }
                    if (bl5) {
                        d5 = (d2 - vec3d.yCoord) / d8;
                    }
                    if (bl6) {
                        d6 = (d3 - vec3d.zCoord) / d9;
                    }
                    if (d4 == -0.0) {
                        d4 = -1.0E-4;
                    }
                    if (d5 == -0.0) {
                        d5 = -1.0E-4;
                    }
                    if (d6 == -0.0) {
                        d6 = -1.0E-4;
                    }
                    if (d4 < d5 && d4 < d6) {
                        enumFacing = n3 > n6 ? EnumFacing.WEST : EnumFacing.EAST;
                        vec3d = new Vec3d(d, vec3d.yCoord + d8 * d4, vec3d.zCoord + d9 * d4);
                    } else if (d5 < d6) {
                        enumFacing = n4 > n2 ? EnumFacing.DOWN : EnumFacing.UP;
                        vec3d = new Vec3d(vec3d.xCoord + d7 * d5, d2, vec3d.zCoord + d9 * d5);
                    } else {
                        enumFacing = n5 > n ? EnumFacing.NORTH : EnumFacing.SOUTH;
                        vec3d = new Vec3d(vec3d.xCoord + d7 * d6, vec3d.yCoord + d8 * d6, d3);
                    }
                    n6 = MathHelper.floor((double)vec3d.xCoord) - (enumFacing == EnumFacing.EAST ? 1 : 0);
                    n2 = MathHelper.floor((double)vec3d.yCoord) - (enumFacing == EnumFacing.UP ? 1 : 0);
                    n = MathHelper.floor((double)vec3d.zCoord) - (enumFacing == EnumFacing.SOUTH ? 1 : 0);
                    blockPos = new BlockPos(n6, n2, n);
                    IBlockState iBlockState2 = CrystalUtilSC.mc.world.getBlockState(blockPos);
                    Block block2 = iBlockState2.getBlock();
                    if (!valid.contains((Object)block2)) {
                        block2 = Blocks.AIR;
                        iBlockState2 = Blocks.AIR.getBlockState().getBaseState();
                    }
                    if (bl2 && iBlockState2.getMaterial() != Material.PORTAL && iBlockState2.getCollisionBoundingBox((IBlockAccess)CrystalUtilSC.mc.world, blockPos) == Block.NULL_AABB) continue;
                    if (block2.canCollideCheck(iBlockState2, bl)) {
                        RayTraceResult rayTraceResult3 = iBlockState2.collisionRayTrace((World)CrystalUtilSC.mc.world, blockPos, vec3d, vec3d2);
                        if (rayTraceResult3 == null) continue;
                        return rayTraceResult3;
                    }
                    rayTraceResult2 = new RayTraceResult(RayTraceResult.Type.MISS, vec3d, enumFacing, blockPos);
                }
                return bl3 ? rayTraceResult2 : null;
            }
            return null;
        }
        return null;
    }

    public static int ping() {
        if (mc.getConnection() == null) {
            return 50;
        }
        if (CrystalUtilSC.mc.player == null) {
            return 50;
        }
        try {
            return mc.getConnection().getPlayerInfo(CrystalUtilSC.mc.player.getUniqueID()).getResponseTime();
        }
        catch (NullPointerException nullPointerException) {
            return 50;
        }
    }

    public static float calculateDamage(BlockPos blockPos, Entity entity) {
        return CrystalUtilSC.calculateDamage((double)blockPos.getX() + 0.5, blockPos.getY() + 1, (double)blockPos.getZ() + 0.5, entity);
    }

    public static boolean canPlaceCrystal(BlockPos blockPos) {
        BlockPos blockPos2 = blockPos.add(0, 1, 0);
        BlockPos blockPos3 = blockPos.add(0, 2, 0);
        try {
            if (CrystalUtilSC.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && CrystalUtilSC.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
            if (CrystalUtilSC.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR || CrystalUtilSC.mc.world.getBlockState(blockPos3).getBlock() != Blocks.AIR) {
                return false;
            }
            for (Entity entity : CrystalUtilSC.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2))) {
                if (entity instanceof EntityEnderCrystal) continue;
                return false;
            }
            for (Entity entity : CrystalUtilSC.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos3))) {
                if (entity instanceof EntityEnderCrystal) continue;
                return false;
            }
        }
        catch (Exception exception) {
            return false;
        }
        return true;
    }

    public static boolean rayTracePlace(BlockPos blockPos) {
        if (AliceAura.getInstance().directionMode.getValue() != AliceAura.DirectionMode.VANILLA) {
            double d = 0.45;
            double d2 = 0.05;
            double d3 = 0.95;
            Vec3d vec3d = new Vec3d(CrystalUtilSC.mc.player.posX, CrystalUtilSC.mc.player.getEntityBoundingBox().minY + (double)CrystalUtilSC.mc.player.getEyeHeight(), CrystalUtilSC.mc.player.posZ);
            for (double d4 = d2; d4 <= d3; d4 += d) {
                for (double d5 = d2; d5 <= d3; d5 += d) {
                    for (double d6 = d2; d6 <= d3; d6 += d) {
                        Vec3d vec3d2 = new Vec3d((Vec3i)blockPos).addVector(d4, d5, d6);
                        double d7 = vec3d.distanceTo(vec3d2);
                        if (AliceAura.getInstance().strictDirection.getValue().booleanValue() && d7 > (double)AliceAura.getInstance().placeRange.getValue().floatValue()) continue;
                        double d8 = vec3d2.xCoord - vec3d.xCoord;
                        double d9 = vec3d2.yCoord - vec3d.yCoord;
                        double d10 = vec3d2.zCoord - vec3d.zCoord;
                        double d11 = MathHelper.sqrt((double)(d8 * d8 + d10 * d10));
                        double[] arrd = new double[]{MathHelper.wrapDegrees((float)((float)Math.toDegrees(Math.atan2(d10, d8)) - 90.0f)), MathHelper.wrapDegrees((float)((float)(-Math.toDegrees(Math.atan2(d9, d11)))))};
                        float f = MathHelper.cos((float)((float)(-arrd[0] * 0.01745329238474369 - 3.1415927410125732)));
                        float f2 = MathHelper.sin((float)((float)(-arrd[0] * 0.01745329238474369 - 3.1415927410125732)));
                        float f3 = -MathHelper.cos((float)((float)(-arrd[1] * 0.01745329238474369)));
                        float f4 = MathHelper.sin((float)((float)(-arrd[1] * 0.01745329238474369)));
                        Vec3d vec3d3 = new Vec3d((double)(f2 * f3), (double)f4, (double)(f * f3));
                        Vec3d vec3d4 = vec3d.addVector(vec3d3.xCoord * d7, vec3d3.yCoord * d7, vec3d3.zCoord * d7);
                        RayTraceResult rayTraceResult = CrystalUtilSC.mc.world.rayTraceBlocks(vec3d, vec3d4, false, false, false);
                        if (rayTraceResult == null || rayTraceResult.typeOfHit != RayTraceResult.Type.BLOCK || !rayTraceResult.getBlockPos().equals((Object)blockPos)) continue;
                        return true;
                    }
                }
            }
            return false;
        }
        for (EnumFacing enumFacing : EnumFacing.values()) {
            RayTraceResult rayTraceResult;
            Vec3d vec3d = new Vec3d((double)blockPos.getX() + 0.5 + (double)enumFacing.getDirectionVec().getX() * 0.5, (double)blockPos.getY() + 0.5 + (double)enumFacing.getDirectionVec().getY() * 0.5, (double)blockPos.getZ() + 0.5 + (double)enumFacing.getDirectionVec().getZ() * 0.5);
            if (AliceAura.getInstance().strictDirection.getValue().booleanValue() && CrystalUtilSC.mc.player.getPositionVector().addVector(0.0, (double)CrystalUtilSC.mc.player.getEyeHeight(), 0.0).distanceTo(vec3d) > (double)AliceAura.getInstance().placeRange.getValue().floatValue() || (rayTraceResult = CrystalUtilSC.mc.world.rayTraceBlocks(new Vec3d(CrystalUtilSC.mc.player.posX, CrystalUtilSC.mc.player.posY + (double)CrystalUtilSC.mc.player.getEyeHeight(), CrystalUtilSC.mc.player.posZ), vec3d, false, true, false)) == null || !rayTraceResult.typeOfHit.equals((Object)RayTraceResult.Type.BLOCK) || !rayTraceResult.getBlockPos().equals((Object)blockPos)) continue;
            return true;
        }
        return false;
    }

    public static Vec3d getMotionVec(Entity entity, int n) {
        double d = entity.posX - entity.prevPosX;
        double d2 = entity.posZ - entity.prevPosZ;
        double d3 = 0.0;
        double d4 = 0.0;
        if (AliceAura.getInstance().collision.getValue().booleanValue()) {
            for (int i = 1; i <= n && CrystalUtilSC.mc.world.getBlockState(new BlockPos(entity.posX + d * (double)i, entity.posY, entity.posZ + d2 * (double)i)).getBlock() instanceof BlockAir; ++i) {
                d3 = d * (double)i;
                d4 = d2 * (double)i;
            }
        } else {
            d3 = d * (double)n;
            d4 = d2 * (double)n;
        }
        return new Vec3d(d3, 0.0, d4);
    }

    @Nullable
    public static RayTraceResult rayTraceBlocks(Vec3d vec3d, Vec3d vec3d2) {
        return CrystalUtilSC.rayTraceBlocks(vec3d, vec3d2, false, false, false);
    }

    public static boolean isVisible(Vec3d vec3d) {
        Vec3d vec3d2 = new Vec3d(CrystalUtilSC.mc.player.posX, CrystalUtilSC.mc.player.getEntityBoundingBox().minY + (double)CrystalUtilSC.mc.player.getEyeHeight(), CrystalUtilSC.mc.player.posZ);
        return CrystalUtilSC.mc.world.rayTraceBlocks(vec3d2, vec3d) == null;
    }

    public static void breakCrystalPacket(Entity entity) {
        CrystalUtilSC.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        CrystalUtilSC.mc.player.swingArm(EnumHand.MAIN_HAND);
    }

    public static float getBlockDensity(Vec3d vec3d, AxisAlignedBB axisAlignedBB) {
        double d = 1.0 / ((axisAlignedBB.maxX - axisAlignedBB.minX) * 2.0 + 1.0);
        double d2 = 1.0 / ((axisAlignedBB.maxY - axisAlignedBB.minY) * 2.0 + 1.0);
        double d3 = 1.0 / ((axisAlignedBB.maxZ - axisAlignedBB.minZ) * 2.0 + 1.0);
        double d4 = (1.0 - Math.floor(1.0 / d) * d) / 2.0;
        double d5 = (1.0 - Math.floor(1.0 / d3) * d3) / 2.0;
        if (d >= 0.0 && d2 >= 0.0 && d3 >= 0.0) {
            int n = 0;
            int n2 = 0;
            float f = 0.0f;
            while (f <= 1.0f) {
                float f2 = 0.0f;
                while (f2 <= 1.0f) {
                    float f3 = 0.0f;
                    while (f3 <= 1.0f) {
                        double d6 = axisAlignedBB.minX + (axisAlignedBB.maxX - axisAlignedBB.minX) * (double)f;
                        double d7 = axisAlignedBB.minY + (axisAlignedBB.maxY - axisAlignedBB.minY) * (double)f2;
                        double d8 = axisAlignedBB.minZ + (axisAlignedBB.maxZ - axisAlignedBB.minZ) * (double)f3;
                        if (CrystalUtilSC.rayTraceBlocks(new Vec3d(d6 + d4, d7, d8 + d5), vec3d) == null) {
                            ++n;
                        }
                        ++n2;
                        f3 = (float)((double)f3 + d3);
                    }
                    f2 = (float)((double)f2 + d2);
                }
                f = (float)((double)f + d);
            }
            return (float)n / (float)n2;
        }
        return 0.0f;
    }

    public static float getBlastReduction(EntityLivingBase entityLivingBase, float f, Explosion explosion) {
        float f2 = f;
        if (entityLivingBase instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
            DamageSource damageSource = DamageSource.causeExplosionDamage((Explosion)explosion);
            f2 = CombatRules.getDamageAfterAbsorb((float)f2, (float)entityPlayer.getTotalArmorValue(), (float)((float)entityPlayer.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue()));
            int n = 0;
            try {
                n = EnchantmentHelper.getEnchantmentModifierDamage((Iterable)entityPlayer.getArmorInventoryList(), (DamageSource)damageSource);
            }
            catch (Exception exception) {
                // empty catch block
            }
            float f3 = MathHelper.clamp((float)n, (float)0.0f, (float)20.0f);
            f2 *= 1.0f - f3 / 25.0f;
            if (entityLivingBase.isPotionActive(MobEffects.RESISTANCE)) {
                f2 -= f2 / 4.0f;
            }
            f2 = Math.max(f2, 0.0f);
            return f2;
        }
        f2 = CombatRules.getDamageAfterAbsorb((float)f2, (float)entityLivingBase.getTotalArmorValue(), (float)((float)entityLivingBase.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue()));
        return f2;
    }

    public static boolean rayTraceBreak(double d, double d2, double d3) {
        if (CrystalUtilSC.mc.world.rayTraceBlocks(new Vec3d(CrystalUtilSC.mc.player.posX, CrystalUtilSC.mc.player.posY + (double)CrystalUtilSC.mc.player.getEyeHeight(), CrystalUtilSC.mc.player.posZ), new Vec3d(d, d2 + 1.8, d3), false, true, false) == null) {
            return true;
        }
        if (CrystalUtilSC.mc.world.rayTraceBlocks(new Vec3d(CrystalUtilSC.mc.player.posX, CrystalUtilSC.mc.player.posY + (double)CrystalUtilSC.mc.player.getEyeHeight(), CrystalUtilSC.mc.player.posZ), new Vec3d(d, d2 + 1.5, d3), false, true, false) == null) {
            return true;
        }
        return CrystalUtilSC.mc.world.rayTraceBlocks(new Vec3d(CrystalUtilSC.mc.player.posX, CrystalUtilSC.mc.player.posY + (double)CrystalUtilSC.mc.player.getEyeHeight(), CrystalUtilSC.mc.player.posZ), new Vec3d(d, d2, d3), false, true, false) == null;
    }

    static {
        mc = Minecraft.getMinecraft();
        valid = Arrays.asList(new Block[]{Blocks.OBSIDIAN, Blocks.BEDROCK, Blocks.ENDER_CHEST, Blocks.ANVIL});
    }

    public static int getCrystalSlot() {
        int n = -1;
        if (Util.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) {
            n = Util.mc.player.inventory.currentItem;
        }
        if (n == -1) {
            for (int i = 0; i < 9; ++i) {
                if (Util.mc.player.inventory.getStackInSlot(i).getItem() != Items.END_CRYSTAL) continue;
                n = i;
                break;
            }
        }
        return n;
    }

    public static int getSwordSlot() {
        int n = -1;
        if (Util.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_SWORD) {
            n = Util.mc.player.inventory.currentItem;
        }
        if (n == -1) {
            for (int i = 0; i < 9; ++i) {
                if (Util.mc.player.inventory.getStackInSlot(i).getItem() != Items.DIAMOND_SWORD) continue;
                n = i;
                break;
            }
        }
        return n;
    }

    public static void breakCrystal(Entity entity) {
        CrystalUtilSC.mc.playerController.attackEntity((EntityPlayer)CrystalUtilSC.mc.player, entity);
        CrystalUtilSC.mc.player.swingArm(EnumHand.MAIN_HAND);
    }

    public static float calculateDamage2(BlockPos blockPos, Entity entity) {
        return CrystalUtilSC.calculateDamage(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ(), entity);
    }

    public static float calculateDamage(EntityEnderCrystal entityEnderCrystal, Entity entity) {
        return CrystalUtilSC.calculateDamage(entityEnderCrystal.posX, entityEnderCrystal.posY, entityEnderCrystal.posZ, entity);
    }

    public static Vec3d getEntityPosVec(Entity entity, int n) {
        return entity.getPositionVector().add(CrystalUtilSC.getMotionVec(entity, n));
    }

    public static float calculateDamage(double d, double d2, double d3, Entity entity) {
        float f = 12.0f;
        Vec3d vec3d = CrystalUtilSC.getEntityPosVec(entity, AliceAura.getInstance().predictTicks.getValue() > 0 ? AliceAura.getInstance().predictTicks.getValue() : 0);
        double d4 = vec3d.distanceTo(new Vec3d(d, d2, d3)) / (double)f;
        Vec3d vec3d2 = new Vec3d(d, d2, d3);
        double d5 = 0.0;
        try {
            d5 = AliceAura.getInstance().terrainIgnore.getValue() != false ? (double)CrystalUtilSC.getBlockDensity(vec3d2, AliceAura.getInstance().predictTicks.getValue() > 0 ? entity.getEntityBoundingBox().func_191194_a(CrystalUtilSC.getMotionVec(entity, AliceAura.getInstance().predictTicks.getValue())) : entity.getEntityBoundingBox()) : (double)entity.world.getBlockDensity(vec3d2, AliceAura.getInstance().predictTicks.getValue() > 0 ? entity.getEntityBoundingBox().func_191194_a(CrystalUtilSC.getMotionVec(entity, AliceAura.getInstance().predictTicks.getValue())) : entity.getEntityBoundingBox());
        }
        catch (Exception exception) {
            // empty catch block
        }
        double d6 = (1.0 - d4) * d5;
        float f2 = (int)((d6 * d6 + d6) / 2.0 * 7.0 * (double)f + 1.0);
        double d7 = 1.0;
        if (entity instanceof EntityLivingBase) {
            d7 = CrystalUtilSC.getBlastReduction((EntityLivingBase)entity, CrystalUtilSC.getDamageMultiplied(f2), new Explosion((World)CrystalUtilSC.mc.world, (Entity)CrystalUtilSC.mc.player, d, d2, d3, 6.0f, false, true));
        }
        return (float)d7;
    }

    public static float getDamageMultiplied(float f) {
        int n = CrystalUtilSC.mc.world.getDifficulty().getDifficultyId();
        return f * (n == 0 ? 0.0f : (n == 2 ? 1.0f : (n == 1 ? 0.5f : 1.5f)));
    }

    public static class CrystalBad
    extends RuntimeException {
        public CrystalBad() {
            this.setStackTrace(new StackTraceElement[0]);
        }

        @Override
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }
}

