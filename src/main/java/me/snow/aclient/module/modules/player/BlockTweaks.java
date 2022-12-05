//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Enchantments
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.item.ItemTool
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.player.AttackEntityEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$LeftClickBlock
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.EntityUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockTweaks
extends Module {
    public /* synthetic */ Setting<Boolean> noFriendAttack;
    private /* synthetic */ int lastHotbarSlot;
    private static /* synthetic */ BlockTweaks INSTANCE;
    public /* synthetic */ Setting<Boolean> autoTool;
    public /* synthetic */ Setting<Boolean> noGhost;
    public /* synthetic */ Setting<Boolean> destroy;
    private /* synthetic */ int currentTargetSlot;
    private /* synthetic */ boolean switched;
    public /* synthetic */ Setting<Boolean> autoWeapon;

    @SubscribeEvent
    public void onBreak(BlockEvent.BreakEvent breakEvent) {
        if (BlockTweaks.fullNullCheck() || !this.noGhost.getValue().booleanValue() || !this.destroy.getValue().booleanValue()) {
            return;
        }
        if (!(BlockTweaks.mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock)) {
            BlockPos blockPos = BlockTweaks.mc.player.getPosition();
            this.removeGlitchBlocks(blockPos);
        }
    }

    public void equipBestWeapon(Entity entity) {
        int n = -1;
        double d = 0.0;
        EnumCreatureAttribute enumCreatureAttribute = EnumCreatureAttribute.UNDEFINED;
        if (EntityUtil.isLiving(entity)) {
            EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            enumCreatureAttribute = entityLivingBase.getCreatureAttribute();
        }
        for (int i = 0; i < 9; ++i) {
            double d2;
            ItemStack itemStack = BlockTweaks.mc.player.inventory.getStackInSlot(i);
            if (itemStack.field_190928_g) continue;
            if (itemStack.getItem() instanceof ItemTool) {
                d2 = (double)((ItemTool)itemStack.getItem()).damageVsEntity + (double)EnchantmentHelper.getModifierForCreature((ItemStack)itemStack, (EnumCreatureAttribute)enumCreatureAttribute);
                if (!(d2 > d)) continue;
                d = d2;
                n = i;
                continue;
            }
            if (!(itemStack.getItem() instanceof ItemSword) || !((d2 = (double)((ItemSword)itemStack.getItem()).getDamageVsEntity() + (double)EnchantmentHelper.getModifierForCreature((ItemStack)itemStack, (EnumCreatureAttribute)enumCreatureAttribute)) > d)) continue;
            d = d2;
            n = i;
        }
        this.equip(n, true);
    }

    private void equipBestTool(IBlockState iBlockState) {
        int n = -1;
        double d = 0.0;
        for (int i = 0; i < 9; ++i) {
            int n2;
            float f;
            ItemStack itemStack = BlockTweaks.mc.player.inventory.getStackInSlot(i);
            if (itemStack.field_190928_g) continue;
            float f2 = itemStack.getStrVsBlock(iBlockState);
            if (!(f > 1.0f) || !((double)(f2 = (float)((double)f2 + ((n2 = EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.EFFICIENCY, (ItemStack)itemStack)) > 0 ? Math.pow(n2, 2.0) + 1.0 : 0.0))) > d)) continue;
            d = f2;
            n = i;
        }
        this.equip(n, true);
    }

    public static BlockTweaks getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new BlockTweaks();
        }
        return INSTANCE;
    }

    @Override
    public void onDisable() {
        if (this.switched) {
            this.equip(this.lastHotbarSlot, false);
        }
        this.lastHotbarSlot = -1;
        this.currentTargetSlot = -1;
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        Entity entity;
        if (BlockTweaks.fullNullCheck()) {
            return;
        }
        if (this.noFriendAttack.getValue().booleanValue() && send.getPacket() instanceof CPacketUseEntity && (entity = ((CPacketUseEntity)send.getPacket()).getEntityFromWorld((World)BlockTweaks.mc.world)) != null && AliceMain.friendManager.isFriend(entity.getName())) {
            send.setCanceled(true);
        }
    }

    public BlockTweaks() {
        super("BlockTweaks", "Some tweaks for blocks.", Module.Category.PLAYER, true, false, false);
        this.autoTool = this.register(new Setting<Boolean>("AutoTool", false));
        this.autoWeapon = this.register(new Setting<Boolean>("AutoWeapon", false));
        this.noFriendAttack = this.register(new Setting<Boolean>("NoFriendAttack", false));
        this.noGhost = this.register(new Setting<Boolean>("NoGlitchBlocks", false));
        this.destroy = this.register(new Setting<Object>("Destroy", Boolean.valueOf(false), object -> this.noGhost.getValue()));
        this.lastHotbarSlot = -1;
        this.currentTargetSlot = -1;
        this.setInstance();
    }

    static {
        INSTANCE = new BlockTweaks();
    }

    @SubscribeEvent
    public void onAttack(AttackEntityEvent attackEntityEvent) {
        if (this.autoWeapon.getValue().booleanValue() && !BlockTweaks.fullNullCheck() && attackEntityEvent.getTarget() != null) {
            this.equipBestWeapon(attackEntityEvent.getTarget());
        }
    }

    @SubscribeEvent
    public void onBlockInteract(PlayerInteractEvent.LeftClickBlock leftClickBlock) {
        if (this.autoTool.getValue().booleanValue() && !BlockTweaks.fullNullCheck() && leftClickBlock.getPos() != null) {
            this.equipBestTool(BlockTweaks.mc.world.getBlockState(leftClickBlock.getPos()));
        }
    }

    @Override
    public void onUpdate() {
        if (!BlockTweaks.fullNullCheck()) {
            if (BlockTweaks.mc.player.inventory.currentItem != this.lastHotbarSlot && BlockTweaks.mc.player.inventory.currentItem != this.currentTargetSlot) {
                this.lastHotbarSlot = BlockTweaks.mc.player.inventory.currentItem;
            }
            if (!BlockTweaks.mc.gameSettings.keyBindAttack.isKeyDown() && this.switched) {
                this.equip(this.lastHotbarSlot, false);
            }
        }
    }

    private void removeGlitchBlocks(BlockPos blockPos) {
        for (int i = -4; i <= 4; ++i) {
            for (int j = -4; j <= 4; ++j) {
                for (int k = -4; k <= 4; ++k) {
                    BlockPos blockPos2 = new BlockPos(blockPos.getX() + i, blockPos.getY() + j, blockPos.getZ() + k);
                    if (!BlockTweaks.mc.world.getBlockState(blockPos2).getBlock().equals((Object)Blocks.AIR)) continue;
                    BlockTweaks.mc.playerController.processRightClickBlock(BlockTweaks.mc.player, BlockTweaks.mc.world, blockPos2, EnumFacing.DOWN, new Vec3d(0.5, 0.5, 0.5), EnumHand.MAIN_HAND);
                }
            }
        }
    }

    private void equip(int n, boolean bl) {
        if (n != -1) {
            if (n != BlockTweaks.mc.player.inventory.currentItem) {
                this.lastHotbarSlot = BlockTweaks.mc.player.inventory.currentItem;
            }
            this.currentTargetSlot = n;
            BlockTweaks.mc.player.inventory.currentItem = n;
            BlockTweaks.mc.playerController.syncCurrentPlayItem();
            this.switched = bl;
        }
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

