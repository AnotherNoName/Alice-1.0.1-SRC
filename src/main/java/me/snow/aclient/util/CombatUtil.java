//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.client.Minecraft
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.util.CombatRules
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package me.snow.aclient.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import me.snow.aclient.AliceMain;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.HoleUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayer;
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
import net.minecraft.world.World;

public class CombatUtil {
    private static final /* synthetic */ List<Integer> invalidSlots;
    public static final /* synthetic */ Vec3d[] cityOffsets;
    public static final /* synthetic */ List<Block> shulkerList;
    private static /* synthetic */ Minecraft mc;
    public static final /* synthetic */ List<Block> blackList;

    public static boolean checkFriends(BlockPos blockPos, double d, double d2, boolean bl) {
        for (EntityPlayer entityPlayer : CombatUtil.mc.world.playerEntities) {
            if (CombatUtil.mc.player.getDistanceSqToEntity((Entity)entityPlayer) > d2 * d2) continue;
            if ((double)CombatUtil.calculateDamage(blockPos, (Entity)entityPlayer) > d) {
                return false;
            }
            if (!(CombatUtil.calculateDamage(blockPos, (Entity)entityPlayer) > entityPlayer.getHealth() + entityPlayer.getAbsorptionAmount()) || !bl) continue;
            return false;
        }
        return true;
    }

    public static void switchOffhandStrict(int n, int n2) {
        switch (n2) {
            case 0: {
                CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
                break;
            }
            case 1: {
                CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
                break;
            }
            case 2: {
                CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
                CombatUtil.mc.playerController.updateController();
            }
        }
    }

    public static BlockPos getClosestValidPos(EntityPlayer entityPlayer, double d, double d2, double d3, double d4, double d5, double d6, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        double d7 = -1.0;
        BlockPos blockPos2 = null;
        if (entityPlayer == null) {
            return null;
        }
        List<BlockPos> list = CombatUtil.getSphere(new BlockPos((Vec3i)CombatUtil.getFlooredPosition((Entity)CombatUtil.mc.player)), (float)d4, (int)d4, false, true, 0);
        list.sort(Comparator.comparing(blockPos -> CombatUtil.mc.player.getDistanceSq(blockPos)));
        for (BlockPos blockPos3 : list) {
            double d8;
            if (!CombatUtil.canPlaceCrystal(blockPos3, d4, d5, bl) || bl && !CombatUtil.rayTraceRangeCheck(blockPos3, d5, 0.0) || (d8 = (double)CombatUtil.calculateDamage(blockPos3, (Entity)entityPlayer)) < d3 || !CombatUtil.checkFriends(blockPos3, d2, d6, bl3) || !CombatUtil.checkSelf(blockPos3, d, bl2)) continue;
            if (d8 > 15.0) {
                return blockPos3;
            }
            if (!(d8 > d7)) continue;
            d7 = d8;
            blockPos2 = blockPos3;
        }
        return blockPos2;
    }

    public static BlockPos getClosestValidPosMultiThread(EntityPlayer entityPlayer, double d, double d2, double d3, double d4, double d5, double d6, boolean bl, boolean bl2, boolean bl3) {
        CopyOnWriteArrayList<ValidPosThread> copyOnWriteArrayList = new CopyOnWriteArrayList<ValidPosThread>();
        BlockPos blockPos = null;
        for (BlockPos object : CombatUtil.getSphere(new BlockPos(entityPlayer.getPositionVector()), 13.0f, 13, false, true, 0)) {
            ValidPosThread validPosThread2 = new ValidPosThread(entityPlayer, object, d, d2, d3, d4, d5, d6, bl, bl2, bl3);
            copyOnWriteArrayList.add(validPosThread2);
            validPosThread2.start();
        }
        boolean bl4 = false;
        do {
            for (ValidPosThread validPosThread2 : copyOnWriteArrayList) {
                if (!validPosThread2.isInterrupted() || !validPosThread2.isValid) continue;
                blockPos = validPosThread2.pos;
            }
            bl4 = copyOnWriteArrayList.stream().noneMatch(validPosThread -> validPosThread.isValid && validPosThread.isInterrupted());
        } while (blockPos == null && !bl4);
        AliceMain.LOGGER.info(blockPos == null ? "pos was null" : blockPos.toString());
        return blockPos;
    }

    public static boolean checkSelf(BlockPos blockPos, double d, boolean bl) {
        boolean bl2 = CombatUtil.calculateDamage(blockPos, (Entity)CombatUtil.mc.player) > CombatUtil.mc.player.getHealth() + CombatUtil.mc.player.getAbsorptionAmount();
        boolean bl3 = (double)CombatUtil.calculateDamage(blockPos, (Entity)CombatUtil.mc.player) > d;
        return (!bl || !bl2) && !bl3;
    }

    public static boolean isPosValid(EntityPlayer entityPlayer, BlockPos blockPos, double d, double d2, double d3, double d4, double d5, double d6, boolean bl, boolean bl2, boolean bl3) {
        if (blockPos == null) {
            return false;
        }
        if (!CombatUtil.isHard(CombatUtil.mc.world.getBlockState(blockPos).getBlock())) {
            return false;
        }
        if (!CombatUtil.canPlaceCrystal(blockPos, d4, d5, bl)) {
            return false;
        }
        if (!CombatUtil.checkFriends(blockPos, d2, d6, bl3)) {
            return false;
        }
        if (!CombatUtil.checkSelf(blockPos, d, bl2)) {
            return false;
        }
        double d7 = CombatUtil.calculateDamage(blockPos, (Entity)entityPlayer);
        if (d7 < d3) {
            return false;
        }
        return !bl || CombatUtil.rayTraceRangeCheck(blockPos, d5, 0.0);
    }

    public static void betterRotate(BlockPos blockPos, EnumFacing enumFacing, boolean bl) {
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        switch (CombatUtil.getPlaceSide(blockPos)) {
            case UP: {
                f = 0.5f;
                f3 = 0.5f;
                f2 = 0.0f;
                break;
            }
            case DOWN: {
                f = 0.5f;
                f3 = 0.5f;
                f2 = -0.5f;
                break;
            }
            case NORTH: {
                f3 = 0.5f;
                f2 = -0.5f;
                f = -0.5f;
                break;
            }
            case EAST: {
                f3 = 0.5f;
                f2 = -0.5f;
                f = 0.5f;
                break;
            }
            case SOUTH: {
                f3 = 0.5f;
                f2 = -0.5f;
                f = 0.5f;
                break;
            }
            case WEST: {
                f3 = -0.5f;
                f2 = -0.5f;
                f = 0.5f;
            }
        }
        float[] arrf = CombatUtil.getLegitRotations(CombatUtil.getHitAddition(f3, f2, f, blockPos, enumFacing));
        CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(arrf[0], arrf[1], CombatUtil.mc.player.onGround));
    }

    public static float[] getLegitRotations(Vec3d vec3d) {
        Vec3d vec3d2 = new Vec3d(CombatUtil.mc.player.posX, CombatUtil.mc.player.posY + (double)CombatUtil.mc.player.getEyeHeight(), CombatUtil.mc.player.posZ);
        double d = vec3d.xCoord - vec3d2.xCoord;
        double d2 = vec3d.yCoord - vec3d2.yCoord;
        double d3 = vec3d.zCoord - vec3d2.zCoord;
        double d4 = Math.sqrt(d * d + d3 * d3);
        float f = (float)Math.toDegrees(Math.atan2(d3, d)) - 90.0f;
        float f2 = (float)(-Math.toDegrees(Math.atan2(d2, d4)));
        return new float[]{CombatUtil.mc.player.rotationYaw + MathHelper.wrapDegrees((float)(f - CombatUtil.mc.player.rotationYaw)), CombatUtil.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(f2 - CombatUtil.mc.player.rotationPitch))};
    }

    public static EntityPlayer getTarget(float f) {
        EntityPlayer entityPlayer = null;
        int n = CombatUtil.mc.world.playerEntities.size();
        for (int i = 0; i < n; ++i) {
            EntityPlayer entityPlayer2 = (EntityPlayer)CombatUtil.mc.world.playerEntities.get(i);
            if (EntityUtil.isntValid((Entity)entityPlayer2, f)) continue;
            if (entityPlayer == null) {
                entityPlayer = entityPlayer2;
                continue;
            }
            if (!(CombatUtil.mc.player.getDistanceSqToEntity((Entity)entityPlayer2) < CombatUtil.mc.player.getDistanceSqToEntity((Entity)entityPlayer))) continue;
            entityPlayer = entityPlayer2;
        }
        return entityPlayer;
    }

    public static boolean requiredDangerSwitch(double d) {
        int n = (int)CombatUtil.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal).filter(entity -> (double)CombatUtil.mc.player.getDistanceToEntity(entity) <= d).filter(entity -> CombatUtil.calculateDamage(entity.posX, entity.posY, entity.posZ, (Entity)CombatUtil.mc.player) >= CombatUtil.mc.player.getHealth() + CombatUtil.mc.player.getAbsorptionAmount()).count();
        return n > 0;
    }

    public static boolean isInCity(EntityPlayer entityPlayer, double d, double d2, boolean bl, boolean bl2, boolean bl3, boolean bl4) {
        BlockPos blockPos = new BlockPos(entityPlayer.getPositionVector());
        for (EnumFacing enumFacing : EnumFacing.values()) {
            if (enumFacing == EnumFacing.UP || enumFacing == EnumFacing.DOWN) continue;
            BlockPos blockPos2 = blockPos.offset(enumFacing);
            BlockPos blockPos3 = blockPos2.offset(enumFacing);
            if (!(CombatUtil.mc.world.getBlockState(blockPos2).getBlock() == Blocks.AIR && (CombatUtil.mc.world.getBlockState(blockPos3).getBlock() == Blocks.AIR && CombatUtil.isHard(CombatUtil.mc.world.getBlockState(blockPos3.up()).getBlock()) || !bl) && !bl4 || CombatUtil.mc.player.getDistanceSq(blockPos3) <= d2 * d2 && CombatUtil.mc.player.getDistanceSqToEntity((Entity)entityPlayer) <= d * d && CombatUtil.isHard(CombatUtil.mc.world.getBlockState(blockPos.up(3)).getBlock()) || !bl2)) continue;
            return true;
        }
        return false;
    }

    public static boolean checkCanPlace(BlockPos blockPos) {
        if (!(CombatUtil.mc.world.getBlockState(blockPos).getBlock() instanceof BlockAir) && !(CombatUtil.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid)) {
            return false;
        }
        for (Entity entity : CombatUtil.mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(blockPos))) {
            if (entity instanceof EntityItem || entity instanceof EntityXPOrb || entity instanceof EntityArrow) continue;
            return false;
        }
        return CombatUtil.getPlaceSide(blockPos) != null;
    }

    public static int findCrapple() {
        if (CombatUtil.mc.player == null) {
            return -1;
        }
        for (int i = 0; i < CombatUtil.mc.player.inventoryContainer.getInventory().size(); ++i) {
            ItemStack itemStack;
            if (invalidSlots.contains(i) || (itemStack = (ItemStack)CombatUtil.mc.player.inventoryContainer.getInventory().get(i)).func_190926_b() || !itemStack.getItem().equals((Object)Items.GOLDEN_APPLE) || itemStack.getItemDamage() == 1) continue;
            return i;
        }
        return -1;
    }

    public static int findItemSlot(Item item) {
        if (CombatUtil.mc.player == null) {
            return -1;
        }
        for (int i = 0; i < CombatUtil.mc.player.inventoryContainer.getInventory().size(); ++i) {
            ItemStack itemStack;
            if (invalidSlots.contains(i) || (itemStack = (ItemStack)CombatUtil.mc.player.inventoryContainer.getInventory().get(i)).func_190926_b() || !itemStack.getItem().equals((Object)item)) continue;
            return i;
        }
        return -1;
    }

    public static int getSafetyFactor(BlockPos blockPos) {
        return 0;
    }

    public static boolean rayTraceRangeCheck(BlockPos blockPos, double d, double d2) {
        RayTraceResult rayTraceResult = CombatUtil.mc.world.rayTraceBlocks(new Vec3d(CombatUtil.mc.player.posX, CombatUtil.mc.player.posY + (double)CombatUtil.mc.player.getEyeHeight(), CombatUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)blockPos.getY() + d2, (double)blockPos.getZ()), false, true, false);
        return rayTraceResult == null || CombatUtil.mc.player.getDistanceSq(blockPos) <= d * d;
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

    public static boolean checkFriends(EntityEnderCrystal entityEnderCrystal, double d, double d2, boolean bl) {
        for (EntityPlayer entityPlayer : CombatUtil.mc.world.playerEntities) {
            if (CombatUtil.mc.player.getDistanceSqToEntity((Entity)entityPlayer) > d2 * d2) continue;
            if ((double)CombatUtil.calculateDamage((Entity)entityEnderCrystal, (Entity)entityPlayer) > d) {
                return false;
            }
            if (!(CombatUtil.calculateDamage((Entity)entityEnderCrystal, (Entity)entityPlayer) > entityPlayer.getHealth() + entityPlayer.getAbsorptionAmount()) || !bl) continue;
            return false;
        }
        return true;
    }

    public static int getVulnerability(EntityPlayer entityPlayer, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, int n, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5) {
        if (CombatUtil.isInCity(entityPlayer, d, d2, true, true, true, false) && bl) {
            return 5;
        }
        if (CombatUtil.getClosestValidPos(entityPlayer, d4, d5, d6, d2, d3, d7, bl2, bl4, bl5, true) != null) {
            return 4;
        }
        if ((double)(entityPlayer.getHealth() + entityPlayer.getAbsorptionAmount()) <= d8) {
            return 3;
        }
        if (CombatUtil.isArmorLow(entityPlayer, n, true) && bl3) {
            return 2;
        }
        return 0;
    }

    public static Map<BlockPos, Double> mapBlockDamage(EntityPlayer entityPlayer, double d, double d2, double d3, double d4, double d5, double d6, boolean bl, boolean bl2, boolean bl3) {
        HashMap<BlockPos, Double> hashMap = new HashMap<BlockPos, Double>();
        for (BlockPos blockPos : CombatUtil.getSphere(new BlockPos((Vec3i)CombatUtil.getFlooredPosition((Entity)CombatUtil.mc.player)), (float)d4, (int)d4, false, true, 0)) {
            double d7;
            if (!CombatUtil.canPlaceCrystal(blockPos, d4, d5, bl) || !CombatUtil.checkFriends(blockPos, d2, d6, bl3) || !CombatUtil.checkSelf(blockPos, d, bl2) || bl && !CombatUtil.rayTraceRangeCheck(blockPos, d5, 0.0)) continue;
            double d8 = CombatUtil.calculateDamage(blockPos, (Entity)entityPlayer);
            if (d7 < d3) continue;
            hashMap.put(blockPos, d8);
        }
        return hashMap;
    }

    public static float getDamageMultiplied(float f) {
        int n = Minecraft.getMinecraft().world.getDifficulty().getDifficultyId();
        return f * (n == 0 ? 0.0f : (n == 2 ? 1.0f : (n == 1 ? 0.5f : 1.5f)));
    }

    public static boolean isArmorLow(EntityPlayer entityPlayer, int n, boolean bl) {
        for (ItemStack itemStack : entityPlayer.inventory.armorInventory) {
            if (itemStack == null) {
                return true;
            }
            if (!bl || CombatUtil.getItemDamage(itemStack) >= n) continue;
            return true;
        }
        return false;
    }

    public static int findItemSlotDamage1(Item item) {
        if (CombatUtil.mc.player == null) {
            return -1;
        }
        for (int i = 0; i < CombatUtil.mc.player.inventoryContainer.getInventory().size(); ++i) {
            ItemStack itemStack;
            if (invalidSlots.contains(i) || (itemStack = (ItemStack)CombatUtil.mc.player.inventoryContainer.getInventory().get(i)).func_190926_b() || !itemStack.getItem().equals((Object)item) || itemStack.getItemDamage() != 1) continue;
            return i;
        }
        return -1;
    }

    public static Object getClosestValidCrystal(EntityPlayer entityPlayer, double d, double d2, double d3, double d4, double d5, double d6, boolean bl, boolean bl2, boolean bl3) {
        if (entityPlayer == null) {
            return null;
        }
        List list = CombatUtil.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal).filter(entity -> CombatUtil.mc.player.getDistanceSqToEntity(entity) <= d4 * d4).sorted(Comparator.comparingDouble(entity -> CombatUtil.mc.player.getDistanceSqToEntity(entity))).map(entity -> (EntityEnderCrystal)entity).collect(Collectors.toList());
        for (Object t : list) {
            if (bl && !CombatUtil.rayTraceRangeCheck((Entity)t, d5) || (double)CombatUtil.calculateDamage((Entity)t, (Entity)entityPlayer) < d3 || !CombatUtil.checkSelf((EntityEnderCrystal)t, d, bl2) || !CombatUtil.checkFriends((EntityEnderCrystal)t, d2, d6, bl3)) continue;
            return t;
        }
        return null;
    }

    public static float calculateDamage(double d, double d2, double d3, Entity entity) {
        float f = 12.0f;
        double d4 = entity.getDistance(d, d2, d3) / (double)f;
        Vec3d vec3d = new Vec3d(d, d2, d3);
        double d5 = 0.0;
        try {
            d5 = entity.world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
        }
        catch (Exception exception) {
            // empty catch block
        }
        double d6 = (1.0 - d4) * d5;
        float f2 = (int)((d6 * d6 + d6) / 2.0 * 7.0 * (double)f + 1.0);
        double d7 = 1.0;
        if (entity instanceof EntityLivingBase) {
            d7 = CombatUtil.getBlastReduction((EntityLivingBase)entity, CombatUtil.getDamageMultiplied(f2), new Explosion((World)Minecraft.getMinecraft().world, null, d, d2, d3, 6.0f, false, true));
        }
        return (float)d7;
    }

    public static boolean placeBlock(BlockPos blockPos, boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, int n) {
        Object object;
        if (!CombatUtil.checkCanPlace(blockPos)) {
            return false;
        }
        EnumFacing enumFacing = CombatUtil.getPlaceSide(blockPos);
        BlockPos blockPos2 = blockPos.offset(enumFacing);
        EnumFacing enumFacing2 = enumFacing.getOpposite();
        if (!CombatUtil.mc.world.getBlockState(blockPos2).getBlock().canCollideCheck(CombatUtil.mc.world.getBlockState(blockPos2), false)) {
            return false;
        }
        if (bl4) {
            if (bl5) {
                CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
            } else if (CombatUtil.mc.player.inventory.currentItem != n) {
                CombatUtil.mc.player.inventory.currentItem = n;
            }
        }
        boolean bl6 = false;
        if (blackList.contains((Object)CombatUtil.mc.world.getBlockState(blockPos2).getBlock()) || shulkerList.contains((Object)CombatUtil.mc.world.getBlockState(blockPos2).getBlock())) {
            CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)CombatUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            bl6 = true;
        }
        Vec3d vec3d = CombatUtil.getHitVector(blockPos2, enumFacing2);
        if (bl2) {
            object = CombatUtil.getLegitRotations(vec3d);
            CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(object[0], object[1], CombatUtil.mc.player.onGround));
        }
        object = (Object)(bl ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
        CombatUtil.mc.playerController.processRightClickBlock(CombatUtil.mc.player, CombatUtil.mc.world, blockPos2, enumFacing2, vec3d, (EnumHand)object);
        CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketAnimation((EnumHand)object));
        if (bl6) {
            CombatUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)CombatUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        return true;
    }

    static {
        blackList = Arrays.asList(new Block[]{Blocks.TALLGRASS, Blocks.ENDER_CHEST, Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER, Blocks.TRAPDOOR});
        shulkerList = Arrays.asList(new Block[]{Blocks.field_190977_dl, Blocks.field_190978_dm, Blocks.field_190979_dn, Blocks.field_190980_do, Blocks.field_190981_dp, Blocks.field_190982_dq, Blocks.field_190983_dr, Blocks.field_190984_ds, Blocks.field_190985_dt, Blocks.field_190986_du, Blocks.field_190987_dv, Blocks.field_190988_dw, Blocks.field_190989_dx, Blocks.field_190990_dy, Blocks.field_190991_dz, Blocks.field_190975_dA});
        mc = Minecraft.getMinecraft();
        cityOffsets = new Vec3d[]{new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 2.0), new Vec3d(-2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -2.0)};
        invalidSlots = Arrays.asList(0, 5, 6, 7, 8);
    }

    public static BlockPos getFlooredPosition(Entity entity) {
        return new BlockPos(Math.floor(entity.posX), Math.floor(entity.posY), Math.floor(entity.posZ));
    }

    public static boolean isHard(Block block) {
        return block == Blocks.OBSIDIAN || block == Blocks.BEDROCK;
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

    public static boolean canLegPlace(EntityPlayer entityPlayer, double d) {
        int n = 0;
        int n2 = 0;
        for (Vec3d vec3d : HoleUtil.awacityOffsets) {
            BlockPos blockPos = CombatUtil.getFlooredPosition((Entity)entityPlayer).add(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
            if (CombatUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN || CombatUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.BEDROCK) {
                ++n;
            }
            if (!(CombatUtil.mc.player.getDistanceSq(blockPos) >= d * d)) continue;
            ++n2;
        }
        return n == 4 && n2 >= 1;
    }

    public static void switchOffhandTotemNotStrict() {
        int n = CombatUtil.findItemSlot(Items.field_190929_cY);
        if (n != -1) {
            CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
            CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
            CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
            CombatUtil.mc.playerController.updateController();
        }
    }

    public static Vec3d interpolateEntity(Entity entity) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)mc.getRenderPartialTicks(), entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)mc.getRenderPartialTicks(), entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)mc.getRenderPartialTicks());
    }

    public static float calculateDamage(Entity entity, Entity entity2) {
        return CombatUtil.calculateDamage(entity.posX, entity.posY, entity.posZ, entity2);
    }

    private static Vec3d getHitVector(BlockPos blockPos, EnumFacing enumFacing) {
        return new Vec3d((Vec3i)blockPos).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
    }

    public static void switchOffhandNonStrict(int n) {
        CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
        CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
        CombatUtil.mc.playerController.windowClick(CombatUtil.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)CombatUtil.mc.player);
        CombatUtil.mc.playerController.updateController();
    }

    public static List<BlockPos> getDisc(BlockPos blockPos, float f) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        int n = blockPos.getX();
        int n2 = blockPos.getY();
        int n3 = blockPos.getZ();
        int n4 = n - (int)f;
        while ((float)n4 <= (float)n + f) {
            int n5 = n3 - (int)f;
            while ((float)n5 <= (float)n3 + f) {
                double d = (n - n4) * (n - n4) + (n3 - n5) * (n3 - n5);
                if (d < (double)(f * f)) {
                    BlockPos blockPos2 = new BlockPos(n4, n2, n5);
                    arrayList.add(blockPos2);
                }
                ++n5;
            }
            ++n4;
        }
        return arrayList;
    }

    public static boolean isHoldingCrystal(boolean bl) {
        if (bl) {
            return CombatUtil.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL;
        }
        return CombatUtil.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || CombatUtil.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
    }

    public static boolean canSeeBlock(BlockPos blockPos) {
        return CombatUtil.mc.world.rayTraceBlocks(new Vec3d(CombatUtil.mc.player.posX, CombatUtil.mc.player.posY + (double)CombatUtil.mc.player.getEyeHeight(), CombatUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)((float)blockPos.getY() + 1.0f), (double)blockPos.getZ()), false, true, false) == null;
    }

    public static float calculateDamage(BlockPos blockPos, Entity entity) {
        return CombatUtil.calculateDamage((double)blockPos.getX() + 0.5, blockPos.getY() + 1, (double)blockPos.getZ() + 0.5, entity);
    }

    public static boolean passesOffhandCheck(double d, Item item, boolean bl) {
        double d2 = CombatUtil.mc.player.getHealth() + CombatUtil.mc.player.getAbsorptionAmount();
        if (!bl ? CombatUtil.findItemSlot(item) == -1 : CombatUtil.findCrapple() == -1) {
            return false;
        }
        return !(d2 < d);
    }

    public static int getItemDamage(ItemStack itemStack) {
        return itemStack.getMaxDamage() - itemStack.getItemDamage();
    }

    private static EnumFacing getPlaceSide(BlockPos blockPos) {
        EnumFacing enumFacing = null;
        for (EnumFacing enumFacing2 : EnumFacing.values()) {
            BlockPos blockPos2 = blockPos.offset(enumFacing2);
            if (!CombatUtil.mc.world.getBlockState(blockPos2).getBlock().canCollideCheck(CombatUtil.mc.world.getBlockState(blockPos2), false) || CombatUtil.mc.world.getBlockState(blockPos2).getMaterial().isReplaceable()) continue;
            enumFacing = enumFacing2;
        }
        return enumFacing;
    }

    public static boolean checkSelf(EntityEnderCrystal entityEnderCrystal, double d, boolean bl) {
        boolean bl2 = CombatUtil.calculateDamage((Entity)entityEnderCrystal, (Entity)CombatUtil.mc.player) > CombatUtil.mc.player.getHealth() + CombatUtil.mc.player.getAbsorptionAmount();
        boolean bl3 = (double)CombatUtil.calculateDamage((Entity)entityEnderCrystal, (Entity)CombatUtil.mc.player) > d;
        return (!bl || !bl2) && !bl3;
    }

    public static boolean rayTraceRangeCheck(Entity entity, double d) {
        boolean bl = CombatUtil.mc.player.canEntityBeSeen(entity);
        return !bl || CombatUtil.mc.player.getDistanceSqToEntity(entity) <= d * d;
    }

    public static Vec3d getHitAddition(double d, double d2, double d3, BlockPos blockPos, EnumFacing enumFacing) {
        return new Vec3d((Vec3i)blockPos).addVector(0.5, 0.5, 0.5).add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
    }

    public static boolean canPlaceCrystal(BlockPos blockPos, double d, double d2, boolean bl) {
        BlockPos blockPos2 = blockPos.up();
        BlockPos blockPos3 = blockPos2.up();
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos2).addCoord(0.0, 1.0, 0.0);
        return (CombatUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN || CombatUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.BEDROCK) && CombatUtil.mc.world.getBlockState(blockPos2).getBlock() == Blocks.AIR && CombatUtil.mc.world.getBlockState(blockPos3).getBlock() == Blocks.AIR && CombatUtil.mc.world.getEntitiesWithinAABB(Entity.class, axisAlignedBB).isEmpty() && CombatUtil.mc.player.getDistanceSq(blockPos) <= d * d && !bl || CombatUtil.rayTraceRangeCheck(blockPos, d2, 0.0);
    }

    public static float[] calcAngle(Vec3d vec3d, Vec3d vec3d2) {
        double d = vec3d2.xCoord - vec3d.xCoord;
        double d2 = (vec3d2.yCoord - vec3d.yCoord) * -1.0;
        double d3 = vec3d2.zCoord - vec3d.zCoord;
        double d4 = MathHelper.sqrt((double)(d * d + d3 * d3));
        return new float[]{(float)MathHelper.wrapDegrees((double)(Math.toDegrees(Math.atan2(d3, d)) - 90.0)), (float)MathHelper.wrapDegrees((double)Math.toDegrees(Math.atan2(d2, d4)))};
    }

    public static class CombatPosInfo {
        public /* synthetic */ EntityPlayer player;
        public /* synthetic */ float damage;
        public /* synthetic */ BlockPos pos;

        public CombatPosInfo(EntityPlayer entityPlayer, BlockPos blockPos, float f) {
            this.pos = blockPos;
            this.damage = f;
            this.player = entityPlayer;
        }
    }

    public static class ValidPosThread
    extends Thread {
        /* synthetic */ EntityPlayer player;
        /* synthetic */ double wallsRange;
        public /* synthetic */ float damage;
        /* synthetic */ double minDamage;
        /* synthetic */ double maxFriendDamage;
        /* synthetic */ double placeRange;
        /* synthetic */ double maxSelfDamage;
        /* synthetic */ boolean antiFriendPop;
        /* synthetic */ double friendRange;
        /* synthetic */ boolean rayTrace;
        public /* synthetic */ CombatPosInfo info;
        /* synthetic */ boolean antiSuicide;
        public /* synthetic */ boolean isValid;
        /* synthetic */ BlockPos pos;

        public ValidPosThread(EntityPlayer entityPlayer, BlockPos blockPos, double d, double d2, double d3, double d4, double d5, double d6, boolean bl, boolean bl2, boolean bl3) {
            super("Break");
            this.pos = blockPos;
            this.maxSelfDamage = d;
            this.maxFriendDamage = d2;
            this.minDamage = d3;
            this.placeRange = d4;
            this.wallsRange = d5;
            this.friendRange = d6;
            this.rayTrace = bl;
            this.antiSuicide = bl2;
            this.antiFriendPop = bl3;
            this.player = entityPlayer;
        }

        @Override
        public void run() {
            if (!(mc.player.getDistanceSq(this.pos) > this.placeRange * this.placeRange) && CombatUtil.canPlaceCrystal(this.pos, this.placeRange, this.wallsRange, this.rayTrace) && CombatUtil.checkFriends(this.pos, this.maxFriendDamage, this.friendRange, this.antiFriendPop) && CombatUtil.checkSelf(this.pos, this.maxSelfDamage, this.antiSuicide)) {
                this.damage = CombatUtil.calculateDamage(this.pos, (Entity)this.player);
                if ((double)this.damage >= this.minDamage && (!this.rayTrace || CombatUtil.rayTraceRangeCheck(this.pos, this.wallsRange, 0.0))) {
                    this.isValid = true;
                    this.info = new CombatPosInfo(this.player, this.pos, this.damage);
                    AliceMain.LOGGER.info("Pos was valid.");
                    return;
                }
            }
            this.isValid = false;
            this.info = new CombatPosInfo(this.player, this.pos, -1.0f);
            AliceMain.LOGGER.info("Pos was invalid.");
        }
    }
}

