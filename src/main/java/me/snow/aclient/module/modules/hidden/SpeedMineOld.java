//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockEndPortalFrame
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Enchantments
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketAnimation
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.hidden;

import java.awt.Color;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.BlockEvent;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.RenderUtil;
import me.snow.aclient.util.Timer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SpeedMineOld
extends Module {
    public /* synthetic */ Setting<Boolean> doubleBreak;
    public /* synthetic */ Setting<Boolean> render;
    private final /* synthetic */ Timer timer;
    private /* synthetic */ boolean isMining;
    public /* synthetic */ Setting<Boolean> box;
    public /* synthetic */ Setting<Boolean> noGapTrace;
    private /* synthetic */ BlockPos lastPos;
    public /* synthetic */ Setting<Boolean> silentSwitch;
    public /* synthetic */ Setting<Boolean> noBreakAnim;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    public /* synthetic */ Setting<Boolean> illegal;
    public /* synthetic */ Setting<Boolean> noDelay;
    public /* synthetic */ Setting<Boolean> noSwing;
    public /* synthetic */ Setting<Boolean> allow;
    public /* synthetic */ Setting<Mode> mode;
    public /* synthetic */ Setting<Boolean> reset;
    public /* synthetic */ IBlockState currentBlockState;
    public /* synthetic */ BlockPos currentPos;
    public /* synthetic */ Setting<Float> damage;
    private static /* synthetic */ SpeedMineOld INSTANCE;
    public /* synthetic */ Setting<Boolean> outline;
    private final /* synthetic */ Setting<Float> range;
    public /* synthetic */ Setting<Boolean> tweaks;
    public /* synthetic */ Setting<Boolean> webSwitch;
    public /* synthetic */ Setting<Boolean> pickaxe;
    private /* synthetic */ EnumFacing lastFacing;
    public /* synthetic */ Setting<Boolean> noTrace;
    private final /* synthetic */ Setting<Float> lineWidth;

    public boolean canHarvestBlock(Block block, BlockPos blockPos) {
        IBlockState iBlockState = SpeedMineOld.mc.world.getBlockState(blockPos);
        IBlockState iBlockState2 = iBlockState.getBlock().getActualState(iBlockState, (IBlockAccess)SpeedMineOld.mc.world, blockPos);
        if (iBlockState2.getMaterial().isToolNotRequired()) {
            return true;
        }
        ItemStack itemStack = this.getEfficientItem(iBlockState2);
        String string = block.getHarvestTool(iBlockState2);
        if (itemStack.func_190926_b() || string == null) {
            return SpeedMineOld.mc.player.canHarvestBlock(iBlockState2);
        }
        int n = itemStack.getItem().getHarvestLevel(itemStack, string, (EntityPlayer)SpeedMineOld.mc.player, iBlockState2);
        if (n < 0) {
            return SpeedMineOld.mc.player.canHarvestBlock(iBlockState2);
        }
        return n >= block.getHarvestLevel(iBlockState2);
    }

    public void showAnimation() {
        this.showAnimation(false, null, null);
    }

    @Override
    public void onTick() {
        if (this.currentPos != null) {
            if (SpeedMineOld.mc.player != null && SpeedMineOld.mc.player.getDistanceSq(this.currentPos) > MathUtil.square(this.range.getValue().floatValue())) {
                this.currentPos = null;
                this.currentBlockState = null;
                return;
            }
            if (SpeedMineOld.mc.player != null && this.silentSwitch.getValue().booleanValue() && this.timer.passedMs((int)(2000.0f * AliceMain.serverManager.getTpsFactor())) && this.getPickSlot() != -1) {
                SpeedMineOld.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.getPickSlot()));
            }
            if (!SpeedMineOld.mc.world.getBlockState(this.currentPos).equals((Object)this.currentBlockState) || SpeedMineOld.mc.world.getBlockState(this.currentPos).getBlock() == Blocks.AIR) {
                this.currentPos = null;
                this.currentBlockState = null;
            } else if (this.webSwitch.getValue().booleanValue() && this.currentBlockState.getBlock() == Blocks.WEB && SpeedMineOld.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
                InventoryUtil.switchToHotbarSlot(ItemSword.class, false);
            }
        }
    }

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        if (this.render.getValue().booleanValue() && this.currentPos != null) {
            Color color = new Color(this.timer.passedMs((int)(2000.0f * AliceMain.serverManager.getTpsFactor())) ? 0 : 255, this.timer.passedMs((int)(2000.0f * AliceMain.serverManager.getTpsFactor())) ? 255 : 0, 0, 255);
            RenderUtil.drawBoxESP(this.currentPos, color, false, color, this.lineWidth.getValue().floatValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
        }
    }

    public static SpeedMineOld getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SpeedMineOld();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    static {
        INSTANCE = new SpeedMineOld();
    }

    public float getDestroySpeed(IBlockState iBlockState) {
        float f = 1.0f;
        if (this.getEfficientItem(iBlockState) != null && !this.getEfficientItem(iBlockState).func_190926_b()) {
            f *= this.getEfficientItem(iBlockState).getStrVsBlock(iBlockState);
        }
        return f;
    }

    @SubscribeEvent
    public void onBlockEvent(BlockEvent blockEvent) {
        if (SpeedMineOld.fullNullCheck()) {
            return;
        }
        if (blockEvent.getStage() == 3 && SpeedMineOld.mc.world.getBlockState(blockEvent.pos).getBlock() instanceof BlockEndPortalFrame) {
            SpeedMineOld.mc.world.getBlockState(blockEvent.pos).getBlock().setHardness(50.0f);
        }
        if (blockEvent.getStage() == 3 && this.reset.getValue().booleanValue() && SpeedMineOld.mc.playerController.curBlockDamageMP > 0.1f) {
            SpeedMineOld.mc.playerController.isHittingBlock = true;
        }
        if (blockEvent.getStage() == 4 && this.tweaks.getValue().booleanValue()) {
            BlockPos blockPos;
            if (BlockUtil.canBreak(blockEvent.pos)) {
                if (this.reset.getValue().booleanValue()) {
                    SpeedMineOld.mc.playerController.isHittingBlock = false;
                }
                switch (this.mode.getValue()) {
                    case PACKET: {
                        if (this.currentPos == null) {
                            this.currentPos = blockEvent.pos;
                            this.currentBlockState = SpeedMineOld.mc.world.getBlockState(this.currentPos);
                            this.timer.reset();
                        }
                        SpeedMineOld.mc.player.swingArm(EnumHand.MAIN_HAND);
                        SpeedMineOld.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockEvent.pos, blockEvent.facing));
                        SpeedMineOld.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockEvent.pos, blockEvent.facing));
                        blockEvent.setCanceled(true);
                        break;
                    }
                    case DAMAGE: {
                        if (!(SpeedMineOld.mc.playerController.curBlockDamageMP >= this.damage.getValue().floatValue())) break;
                        SpeedMineOld.mc.playerController.curBlockDamageMP = 1.0f;
                        break;
                    }
                    case INSTANT: {
                        SpeedMineOld.mc.player.swingArm(EnumHand.MAIN_HAND);
                        SpeedMineOld.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockEvent.pos, blockEvent.facing));
                        SpeedMineOld.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockEvent.pos, blockEvent.facing));
                        SpeedMineOld.mc.playerController.onPlayerDestroyBlock(blockEvent.pos);
                        SpeedMineOld.mc.world.setBlockToAir(blockEvent.pos);
                    }
                }
            }
            if (this.doubleBreak.getValue().booleanValue() && BlockUtil.canBreak(blockPos = blockEvent.pos.add(0, 1, 0)) && SpeedMineOld.mc.player.getDistance((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()) <= 5.0) {
                SpeedMineOld.mc.player.swingArm(EnumHand.MAIN_HAND);
                SpeedMineOld.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos, blockEvent.facing));
                SpeedMineOld.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos, blockEvent.facing));
                SpeedMineOld.mc.playerController.onPlayerDestroyBlock(blockPos);
                SpeedMineOld.mc.world.setBlockToAir(blockPos);
            }
        }
    }

    @Override
    public void onUpdate() {
        if (SpeedMineOld.fullNullCheck()) {
            return;
        }
        if (this.noDelay.getValue().booleanValue()) {
            SpeedMineOld.mc.playerController.blockHitDelay = 0;
        }
        if (this.isMining && this.lastPos != null && this.lastFacing != null && this.noBreakAnim.getValue().booleanValue()) {
            SpeedMineOld.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.lastPos, this.lastFacing));
        }
        if (this.reset.getValue().booleanValue() && SpeedMineOld.mc.gameSettings.keyBindUseItem.isKeyDown() && !this.allow.getValue().booleanValue()) {
            SpeedMineOld.mc.playerController.isHittingBlock = false;
        }
    }

    @Override
    public String getDisplayInfo() {
        return this.mode.currentEnumName();
    }

    public ItemStack getEfficientItem(IBlockState iBlockState) {
        int n = -1;
        double d = 0.0;
        for (int i = 0; i < 9; ++i) {
            float f;
            if (SpeedMineOld.mc.player.inventory.getStackInSlot(i).func_190926_b() || !((f = SpeedMineOld.mc.player.inventory.getStackInSlot(i).getStrVsBlock(iBlockState)) > 1.0f)) continue;
            if (EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.EFFICIENCY, (ItemStack)SpeedMineOld.mc.player.inventory.getStackInSlot(i)) > 0) {
                f = (float)((double)f + (StrictMath.pow(EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.EFFICIENCY, (ItemStack)SpeedMineOld.mc.player.inventory.getStackInSlot(i)), 2.0) + 1.0));
            }
            if (!((double)f > d)) continue;
            d = f;
            n = i;
        }
        if (n != -1) {
            return SpeedMineOld.mc.player.inventory.getStackInSlot(n);
        }
        return SpeedMineOld.mc.player.inventory.getStackInSlot(SpeedMineOld.mc.player.inventory.currentItem);
    }

    private int getPickSlot() {
        for (int i = 0; i < 9; ++i) {
            if (SpeedMineOld.mc.player.inventory.getStackInSlot(i).getItem() != Items.DIAMOND_PICKAXE) continue;
            return i;
        }
        return -1;
    }

    public float getDigSpeed(IBlockState iBlockState) {
        ItemStack itemStack;
        int n;
        float f = this.getDestroySpeed(iBlockState);
        if (f > 1.0f && (n = EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.EFFICIENCY, (ItemStack)(itemStack = this.getEfficientItem(iBlockState)))) > 0 && !itemStack.func_190926_b()) {
            f = (float)((double)f + (StrictMath.pow(n, 2.0) + 1.0));
        }
        if (SpeedMineOld.mc.player.isPotionActive(MobEffects.HASTE)) {
            f *= 1.0f + (float)(SpeedMineOld.mc.player.getActivePotionEffect(MobEffects.HASTE).getAmplifier() + 1) * 0.2f;
        }
        if (SpeedMineOld.mc.player.isPotionActive(MobEffects.MINING_FATIGUE)) {
            float f2;
            switch (SpeedMineOld.mc.player.getActivePotionEffect(MobEffects.MINING_FATIGUE).getAmplifier()) {
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
        if (SpeedMineOld.mc.player.isInsideOfMaterial(Material.WATER) && !EnchantmentHelper.getAquaAffinityModifier((EntityLivingBase)SpeedMineOld.mc.player)) {
            f /= 5.0f;
        }
        if (!SpeedMineOld.mc.player.onGround) {
            f /= 5.0f;
        }
        return f < 0.0f ? 0.0f : f;
    }

    public SpeedMineOld() {
        super("SpeedmineOld", "Speeds up mining.", Module.Category.PLAYER, true, false, false);
        this.range = this.register(new Setting<Float>("Range", Float.valueOf(10.0f), Float.valueOf(0.0f), Float.valueOf(50.0f)));
        this.timer = new Timer();
        this.tweaks = this.register(new Setting<Boolean>("Tweaks", true));
        this.mode = this.register(new Setting<Object>("Mode", (Object)Mode.PACKET, object -> this.tweaks.getValue()));
        this.reset = this.register(new Setting<Boolean>("Reset", true));
        this.damage = this.register(new Setting<Object>("Damage", Float.valueOf(0.7f), Float.valueOf(0.0f), Float.valueOf(1.0f), object -> this.mode.getValue() == Mode.DAMAGE && this.tweaks.getValue() != false));
        this.noBreakAnim = this.register(new Setting<Boolean>("NoBreakAnim", false));
        this.noDelay = this.register(new Setting<Boolean>("NoDelay", false));
        this.noSwing = this.register(new Setting<Boolean>("NoSwing", false));
        this.noTrace = this.register(new Setting<Boolean>("NoTrace", false));
        this.noGapTrace = this.register(new Setting<Object>("NoGapTrace", Boolean.valueOf(false), object -> this.noTrace.getValue()));
        this.allow = this.register(new Setting<Boolean>("AllowMultiTask", false));
        this.pickaxe = this.register(new Setting<Object>("Pickaxe", Boolean.valueOf(true), object -> this.noTrace.getValue()));
        this.doubleBreak = this.register(new Setting<Boolean>("DoubleBreak", false));
        this.webSwitch = this.register(new Setting<Boolean>("WebSwitch", false));
        this.silentSwitch = this.register(new Setting<Boolean>("SilentSwitch", false));
        this.illegal = this.register(new Setting<Boolean>("IllegalMine", false));
        this.render = this.register(new Setting<Boolean>("Render", false));
        this.box = this.register(new Setting<Object>("Box", Boolean.valueOf(false), object -> this.render.getValue()));
        this.boxAlpha = this.register(new Setting<Object>("BoxAlpha", Integer.valueOf(85), Integer.valueOf(0), Integer.valueOf(255), object -> this.box.getValue() != false && this.render.getValue() != false));
        this.outline = this.register(new Setting<Object>("Outline", Boolean.valueOf(true), object -> this.render.getValue()));
        this.lineWidth = this.register(new Setting<Object>("LineWidth", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(5.0f), object -> this.outline.getValue() != false && this.render.getValue() != false));
        this.isMining = false;
        this.lastPos = null;
        this.lastFacing = null;
        this.setInstance();
    }

    private void showAnimation(boolean bl, BlockPos blockPos, EnumFacing enumFacing) {
        this.isMining = bl;
        this.lastPos = blockPos;
        this.lastFacing = enumFacing;
    }

    public float getBlockStrength(IBlockState iBlockState, BlockPos blockPos) {
        float f = iBlockState.getBlockHardness((World)SpeedMineOld.mc.world, blockPos);
        if (f < 0.0f) {
            return 0.0f;
        }
        if (!this.canHarvestBlock(iBlockState.getBlock(), blockPos)) {
            return this.getDigSpeed(iBlockState) / f / 100.0f;
        }
        return this.getDigSpeed(iBlockState) / f / 30.0f;
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        if (SpeedMineOld.fullNullCheck()) {
            return;
        }
        if (send.getStage() == 0) {
            CPacketPlayerDigging cPacketPlayerDigging;
            if (this.noSwing.getValue().booleanValue() && send.getPacket() instanceof CPacketAnimation) {
                send.setCanceled(true);
            }
            if (this.noBreakAnim.getValue().booleanValue() && send.getPacket() instanceof CPacketPlayerDigging && (cPacketPlayerDigging = (CPacketPlayerDigging)send.getPacket()) != null && cPacketPlayerDigging.getPosition() != null) {
                try {
                    for (Entity entity : SpeedMineOld.mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(cPacketPlayerDigging.getPosition()))) {
                        if (!(entity instanceof EntityEnderCrystal)) continue;
                        this.showAnimation();
                        return;
                    }
                }
                catch (Exception exception) {
                    // empty catch block
                }
                if (cPacketPlayerDigging.getAction().equals((Object)CPacketPlayerDigging.Action.START_DESTROY_BLOCK)) {
                    this.showAnimation(true, cPacketPlayerDigging.getPosition(), cPacketPlayerDigging.getFacing());
                }
                if (cPacketPlayerDigging.getAction().equals((Object)CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK)) {
                    this.showAnimation();
                }
            }
        }
    }

    public static enum Mode {
        PACKET,
        DAMAGE,
        INSTANT;

    }
}

