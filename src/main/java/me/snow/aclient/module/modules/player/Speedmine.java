//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Enchantments
 *  net.minecraft.init.MobEffects
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketClickWindow
 *  net.minecraft.network.play.client.CPacketConfirmTransaction
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.player;

import java.awt.Color;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.BlockEvent;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.combat.autocrystal.AutoCrystal;
import me.snow.aclient.util.cc.BlockUtilCC;
import me.snow.aclient.util.cc.InventoryManagerCC;
import me.snow.aclient.util.cc.RenderBuilderCC;
import me.snow.aclient.util.cc.RenderUtilCC;
import me.snow.aclient.util.skid.oyvey.BlockUtil2;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.network.play.client.CPacketConfirmTransaction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Speedmine
extends Module {
    public /* synthetic */ Setting<InventoryManagerCC.Switch> mineSwitch;
    public static /* synthetic */ Speedmine INSTANCE;
    private /* synthetic */ int mineBreaks;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    private static /* synthetic */ float mineDamage;
    private /* synthetic */ Setting<Boolean> reset;
    public /* synthetic */ Setting<Boolean> rotate2;
    private /* synthetic */ int previousHaste;
    private /* synthetic */ Setting<Boolean> strictReMine;
    private /* synthetic */ EnumFacing mineFacing;
    private /* synthetic */ BlockPos minePosition;
    private /* synthetic */ Setting<Boolean> strict;
    private /* synthetic */ Setting<Double> range;

    @Override
    public void onEnable() {
        if (Speedmine.mc.player.isPotionActive(MobEffects.HASTE)) {
            this.previousHaste = Speedmine.mc.player.getActivePotionEffect(MobEffects.HASTE).getDuration();
        }
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketHeldItemChange && this.strict.getValue().booleanValue()) {
            mineDamage = 0.0f;
        }
    }

    public Speedmine() {
        super("Speedmine", "Mines faster.", Module.Category.PLAYER, true, false, false);
        this.range = this.register(new Setting<Double>("Range", 6.0, 0.0, 6.0));
        this.rotate2 = this.register(new Setting<Boolean>("Rotate", true));
        this.reset = this.register(new Setting<Boolean>("Reset", false));
        this.mineSwitch = this.register(new Setting<InventoryManagerCC.Switch>("SwitchMode", InventoryManagerCC.Switch.PACKET));
        this.strict = this.register(new Setting<Boolean>("Strict", false));
        this.strictReMine = this.register(new Setting<Boolean>("LimitRetry", true));
        this.boxAlpha = this.register(new Setting<Integer>("BoxAlpha", 60, 0, 255));
        INSTANCE = this;
    }

    public float getBlockStrength(IBlockState iBlockState, BlockPos blockPos) {
        float f = iBlockState.getBlockHardness((World)Speedmine.mc.world, blockPos);
        if (f < 0.0f) {
            return 0.0f;
        }
        if (!this.canHarvestBlock(iBlockState.getBlock(), blockPos)) {
            return this.getDigSpeed(iBlockState) / f / 100.0f;
        }
        return this.getDigSpeed(iBlockState) / f / 30.0f;
    }

    public float getDigSpeed(IBlockState iBlockState) {
        ItemStack itemStack;
        int n;
        float f = this.getDestroySpeed(iBlockState);
        if (f > 1.0f && (n = EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.EFFICIENCY, (ItemStack)(itemStack = this.getEfficientItem(iBlockState)))) > 0 && !itemStack.func_190926_b()) {
            f = (float)((double)f + (StrictMath.pow(n, 2.0) + 1.0));
        }
        if (Speedmine.mc.player.isPotionActive(MobEffects.HASTE)) {
            f *= 1.0f + (float)(Speedmine.mc.player.getActivePotionEffect(MobEffects.HASTE).getAmplifier() + 1) * 0.2f;
        }
        if (Speedmine.mc.player.isPotionActive(MobEffects.MINING_FATIGUE)) {
            float f2;
            switch (Speedmine.mc.player.getActivePotionEffect(MobEffects.MINING_FATIGUE).getAmplifier()) {
                case 0: {
                    f2 = 0.3f;
                    break;
                }
                case 1: {
                    f2 = 0.09f;
                    break;
                }
                case 2: {
                    f2 = 0.0027f;
                    break;
                }
                default: {
                    f2 = 8.1E-4f;
                }
            }
            f *= f2;
        }
        if (Speedmine.mc.player.isInsideOfMaterial(Material.WATER) && !EnchantmentHelper.getAquaAffinityModifier((EntityLivingBase)Speedmine.mc.player)) {
            f /= 5.0f;
        }
        if (!Speedmine.mc.player.onGround) {
            f /= 5.0f;
        }
        return f < 0.0f ? 0.0f : f;
    }

    public float getDestroySpeed(IBlockState iBlockState) {
        float f = 1.0f;
        if (this.getEfficientItem(iBlockState) != null && !this.getEfficientItem(iBlockState).func_190926_b()) {
            f *= this.getEfficientItem(iBlockState).getStrVsBlock(iBlockState);
        }
        return f;
    }

    @Override
    public void onUpdate() {
        if (!Speedmine.mc.player.capabilities.isCreativeMode) {
            if (this.minePosition != null) {
                double d = BlockUtilCC.getDistanceToCenter((EntityPlayer)Speedmine.mc.player, this.minePosition);
                if (this.mineBreaks >= 2 && this.strictReMine.getValue().booleanValue() || d > this.range.getValue()) {
                    this.minePosition = null;
                    this.mineFacing = null;
                    mineDamage = 0.0f;
                    this.mineBreaks = 0;
                }
            }
            if (this.minePosition != null && !Speedmine.mc.world.isAirBlock(this.minePosition)) {
                if (mineDamage >= 1.0f && !AutoCrystal.getInstance().rotating) {
                    ItemStack itemStack;
                    short s;
                    int n = Speedmine.mc.player.inventory.currentItem;
                    int n2 = AliceMain.inventoryManagercc.searchSlot(this.getEfficientItem(Speedmine.mc.world.getBlockState(this.minePosition)).getItem(), InventoryManagerCC.InventoryRegion.HOTBAR) + 36;
                    if (this.strict.getValue().booleanValue()) {
                        s = Speedmine.mc.player.openContainer.getNextTransactionID(Speedmine.mc.player.inventory);
                        itemStack = Speedmine.mc.player.openContainer.slotClick(n2, Speedmine.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)Speedmine.mc.player);
                        Speedmine.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(Speedmine.mc.player.inventoryContainer.windowId, n2, Speedmine.mc.player.inventory.currentItem, ClickType.SWAP, itemStack, s));
                    } else {
                        AliceMain.inventoryManagercc.switchToItem(this.getEfficientItem(Speedmine.mc.world.getBlockState(this.minePosition)).getItem(), this.mineSwitch.getValue());
                    }
                    Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.minePosition, this.mineFacing));
                    Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.minePosition, EnumFacing.UP));
                    if (this.strict.getValue().booleanValue()) {
                        Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.minePosition, this.mineFacing));
                    }
                    Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.minePosition, this.mineFacing));
                    if (n != -1) {
                        if (this.strict.getValue().booleanValue()) {
                            s = Speedmine.mc.player.openContainer.getNextTransactionID(Speedmine.mc.player.inventory);
                            itemStack = Speedmine.mc.player.openContainer.slotClick(n2, Speedmine.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)Speedmine.mc.player);
                            Speedmine.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(Speedmine.mc.player.inventoryContainer.windowId, n2, Speedmine.mc.player.inventory.currentItem, ClickType.SWAP, itemStack, s));
                            Speedmine.mc.player.connection.sendPacket((Packet)new CPacketConfirmTransaction(Speedmine.mc.player.inventoryContainer.windowId, s, true));
                        } else {
                            AliceMain.inventoryManagercc.switchToSlot(n, InventoryManagerCC.Switch.PACKET);
                        }
                    }
                    mineDamage = 0.0f;
                    ++this.mineBreaks;
                }
                if (!AutoCrystal.getInstance().rotating && (double)mineDamage > 0.95 && this.rotate2.getValue().booleanValue()) {
                    BlockUtil2.rotatePacket(this.minePosition.getX(), this.minePosition.getY(), this.minePosition.getZ());
                }
                mineDamage += this.getBlockStrength(Speedmine.mc.world.getBlockState(this.minePosition), this.minePosition);
            }
        }
    }

    public boolean canHarvestBlock(Block block, BlockPos blockPos) {
        IBlockState iBlockState = Speedmine.mc.world.getBlockState(blockPos);
        IBlockState iBlockState2 = iBlockState.getBlock().getActualState(iBlockState, (IBlockAccess)Speedmine.mc.world, blockPos);
        if (iBlockState2.getMaterial().isToolNotRequired()) {
            return true;
        }
        ItemStack itemStack = this.getEfficientItem(iBlockState2);
        String string = block.getHarvestTool(iBlockState2);
        if (itemStack.func_190926_b() || string == null) {
            return Speedmine.mc.player.canHarvestBlock(iBlockState2);
        }
        int n = itemStack.getItem().getHarvestLevel(itemStack, string, (EntityPlayer)Speedmine.mc.player, iBlockState2);
        if (n < 0) {
            return Speedmine.mc.player.canHarvestBlock(iBlockState2);
        }
        return n >= block.getHarvestLevel(iBlockState2);
    }

    public ItemStack getEfficientItem(IBlockState iBlockState) {
        int n = -1;
        double d = 0.0;
        for (int i = 0; i < 9; ++i) {
            float f;
            if (Speedmine.mc.player.inventory.getStackInSlot(i).func_190926_b() || !((f = Speedmine.mc.player.inventory.getStackInSlot(i).getStrVsBlock(iBlockState)) > 1.0f)) continue;
            if (EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.EFFICIENCY, (ItemStack)Speedmine.mc.player.inventory.getStackInSlot(i)) > 0) {
                f = (float)((double)f + (StrictMath.pow(EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.EFFICIENCY, (ItemStack)Speedmine.mc.player.inventory.getStackInSlot(i)), 2.0) + 1.0));
            }
            if (!((double)f > d)) continue;
            d = f;
            n = i;
        }
        if (n != -1) {
            return Speedmine.mc.player.inventory.getStackInSlot(n);
        }
        return Speedmine.mc.player.inventory.getStackInSlot(Speedmine.mc.player.inventory.currentItem);
    }

    @SubscribeEvent
    public void onBlockEvent(BlockEvent blockEvent) {
        if (BlockUtilCC.isBreakable(blockEvent.pos) && !Speedmine.mc.player.capabilities.isCreativeMode && !blockEvent.pos.equals((Object)this.minePosition)) {
            this.minePosition = blockEvent.pos;
            this.mineFacing = blockEvent.facing;
            mineDamage = 0.0f;
            this.mineBreaks = 0;
            if (this.minePosition != null && this.mineFacing != null) {
                Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.minePosition, this.mineFacing));
                Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.minePosition, EnumFacing.UP));
            }
        }
    }

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        if (!Speedmine.mc.player.capabilities.isCreativeMode && this.minePosition != null && !Speedmine.mc.world.isAirBlock(this.minePosition)) {
            AxisAlignedBB axisAlignedBB = Speedmine.mc.world.getBlockState(this.minePosition).getSelectedBoundingBox((World)Speedmine.mc.world, this.minePosition);
            Vec3d vec3d = axisAlignedBB.getCenter();
            AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord, vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
            RenderUtilCC.drawBox(new RenderBuilderCC().position(axisAlignedBB2.expand((axisAlignedBB.minX - axisAlignedBB.maxX) * 0.5 * (double)MathHelper.clamp((float)mineDamage, (float)0.0f, (float)1.0f), (axisAlignedBB.minY - axisAlignedBB.maxY) * 0.5 * (double)MathHelper.clamp((float)mineDamage, (float)0.0f, (float)1.0f), (axisAlignedBB.minZ - axisAlignedBB.maxZ) * 0.5 * (double)MathHelper.clamp((float)mineDamage, (float)0.0f, (float)1.0f))).color((double)mineDamage >= 0.95 ? new Color(0, 255, 0, this.boxAlpha.getValue()) : new Color(255, 0, 0, this.boxAlpha.getValue())).box(RenderBuilderCC.Box.BOTH).setup().line(1.5f).cull(false).shade(false).alpha(false).depth(true).blend().texture());
        }
    }

    @Override
    public void onDisable() {
        if (Speedmine.mc.player.isPotionActive(MobEffects.HASTE)) {
            Speedmine.mc.player.removePotionEffect(MobEffects.HASTE);
        }
        if (this.previousHaste > 0) {
            Speedmine.mc.player.addPotionEffect(new PotionEffect(MobEffects.HASTE, this.previousHaste));
        }
        this.minePosition = null;
        this.mineFacing = null;
        mineDamage = 0.0f;
        this.mineBreaks = 0;
    }
}

