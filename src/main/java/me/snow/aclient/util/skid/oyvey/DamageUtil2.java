//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemAxe
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.item.ItemShield
 *  net.minecraft.item.ItemSpade
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.item.ItemTool
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.CombatRules
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package me.snow.aclient.util.skid.oyvey;

import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.Util;
import me.snow.aclient.util.skid.oyvey.EntityUtil2;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class DamageUtil2
implements Util {
    public static float calculateDamage(BlockPos blockPos, Entity entity) {
        return DamageUtil2.calculateDamage((double)blockPos.getX() + 0.5, blockPos.getY() + 1, (double)blockPos.getZ() + 0.5, entity);
    }

    public static boolean canBreakWeakness(EntityPlayer entityPlayer) {
        int n = 0;
        PotionEffect potionEffect = DamageUtil2.mc.player.getActivePotionEffect(MobEffects.STRENGTH);
        if (potionEffect != null) {
            n = potionEffect.getAmplifier();
        }
        return !DamageUtil2.mc.player.isPotionActive(MobEffects.WEAKNESS) || n >= 1 || DamageUtil2.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword || DamageUtil2.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe || DamageUtil2.mc.player.getHeldItemMainhand().getItem() instanceof ItemAxe || DamageUtil2.mc.player.getHeldItemMainhand().getItem() instanceof ItemSpade;
    }

    public static float calculateDamage(Entity entity, Entity entity2) {
        return DamageUtil2.calculateDamage(entity.posX, entity.posY, entity.posZ, entity2);
    }

    public static int getRoundedDamage(ItemStack itemStack) {
        return (int)DamageUtil2.getDamageInPercent(itemStack);
    }

    public static int getItemDamage(ItemStack itemStack) {
        return itemStack.getMaxDamage() - itemStack.getItemDamage();
    }

    public static int getCooldownByWeapon(EntityPlayer entityPlayer) {
        Item item = entityPlayer.getHeldItemMainhand().getItem();
        if (item instanceof ItemSword) {
            return 600;
        }
        if (item instanceof ItemPickaxe) {
            return 850;
        }
        if (item == Items.IRON_AXE) {
            return 1100;
        }
        if (item == Items.STONE_HOE) {
            return 500;
        }
        if (item == Items.IRON_HOE) {
            return 350;
        }
        if (item == Items.WOODEN_AXE || item == Items.STONE_AXE) {
            return 1250;
        }
        if (item instanceof ItemSpade || item == Items.GOLDEN_AXE || item == Items.DIAMOND_AXE || item == Items.WOODEN_HOE || item == Items.GOLDEN_HOE) {
            return 1000;
        }
        return 250;
    }

    public static float calculateDamage(Vec3d vec3d, Entity entity, boolean bl, int n) {
        return DamageUtil2.calculateDamage(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord, entity, bl, n);
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
            d7 = DamageUtil2.getBlastReduction((EntityLivingBase)entity, DamageUtil2.getDamageMultiplied(f2), new Explosion((World)DamageUtil2.mc.world, null, d, d2, d3, 6.0f, false, true));
        }
        return (float)d7;
    }

    public static float calculateDamage(double d, double d2, double d3, Entity entity, boolean bl, int n) {
        AxisAlignedBB axisAlignedBB;
        Vec3d vec3d;
        if (bl) {
            vec3d = MathUtil.extrapolatePlayerPosition((EntityPlayer)entity, n);
            axisAlignedBB = entity.boundingBox.offset(-entity.posX, -entity.posY, -entity.posZ).func_191194_a(vec3d);
        } else {
            axisAlignedBB = entity.boundingBox;
            vec3d = entity.getPositionVector();
        }
        float f = 12.0f;
        Vec3d vec3d2 = new Vec3d(d, d2, d3);
        double d4 = vec3d.distanceTo(vec3d2) / 12.0;
        double d5 = 0.0;
        try {
            d5 = entity.world.getBlockDensity(vec3d2, axisAlignedBB);
        }
        catch (Exception exception) {
            // empty catch block
        }
        double d6 = (1.0 - d4) * d5;
        float f2 = (float)((d6 * d6 + d6) / 2.0 * 7.0 * 12.0 + 1.0);
        double d7 = 1.0;
        if (entity instanceof EntityLivingBase) {
            d7 = DamageUtil2.getBlastReduction((EntityLivingBase)entity, DamageUtil2.getDamageMultiplied(f2), new Explosion((World)DamageUtil2.mc.world, null, d, d2, d3, 6.0f, false, true));
        }
        return (float)d7;
    }

    public static boolean hasDurability(ItemStack itemStack) {
        Item item = itemStack.getItem();
        return item instanceof ItemArmor || item instanceof ItemSword || item instanceof ItemTool || item instanceof ItemShield;
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

    public static float calculateDamage(BlockPos blockPos, Entity entity, boolean bl, int n) {
        return DamageUtil2.calculateDamage((double)blockPos.getX() + 0.5, blockPos.getY() + 1, (double)blockPos.getZ() + 0.5, entity, bl, n);
    }

    public static float calculateDamage(Entity entity, Entity entity2, boolean bl, int n) {
        return DamageUtil2.calculateDamage(entity.posX, entity.posY, entity.posZ, entity2, bl, n);
    }

    public static float getDamageInPercent(ItemStack itemStack) {
        return (float)(DamageUtil2.getItemDamage(itemStack) / itemStack.getMaxDamage()) * 100.0f;
    }

    public static boolean isNaked(EntityPlayer entityPlayer) {
        for (ItemStack itemStack : entityPlayer.inventory.armorInventory) {
            if (itemStack == null || itemStack.func_190926_b()) continue;
            return false;
        }
        return true;
    }

    public static EntityPlayer getTarget(float f) {
        EntityPlayer entityPlayer = null;
        int n = DamageUtil2.mc.world.playerEntities.size();
        for (int i = 0; i < n; ++i) {
            EntityPlayer entityPlayer2 = (EntityPlayer)DamageUtil2.mc.world.playerEntities.get(i);
            if (EntityUtil2.isntValid((Entity)entityPlayer2, f)) continue;
            if (entityPlayer == null) {
                entityPlayer = entityPlayer2;
                continue;
            }
            if (!(DamageUtil2.mc.player.getDistanceSqToEntity((Entity)entityPlayer2) < DamageUtil2.mc.player.getDistanceSqToEntity((Entity)entityPlayer))) continue;
            entityPlayer = entityPlayer2;
        }
        return entityPlayer;
    }

    public static float calculateDamage(Vec3d vec3d, Entity entity) {
        return DamageUtil2.calculateDamage(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord, entity);
    }

    public static boolean isArmorLow(EntityPlayer entityPlayer, int n) {
        for (ItemStack itemStack : entityPlayer.inventory.armorInventory) {
            if (itemStack == null) {
                return true;
            }
            if (DamageUtil2.getItemDamage(itemStack) >= n) continue;
            return true;
        }
        return false;
    }

    public static float getDamageMultiplied(float f) {
        int n = DamageUtil2.mc.world.getDifficulty().getDifficultyId();
        return f * (n == 0 ? 0.0f : (n == 2 ? 1.0f : (n == 1 ? 0.5f : 1.5f)));
    }

    public static boolean canTakeDamage(boolean bl) {
        return !DamageUtil2.mc.player.capabilities.isCreativeMode && !bl;
    }
}

