//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3i
 */
package me.snow.aclient.module.modules.combat;

import java.util.Arrays;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.player.InstantMine;
import me.snow.aclient.util.skid.cr.BlockUtilsCr;
import me.snow.aclient.util.skid.cr.InventoryUtilsCr;
import me.snow.aclient.util.skid.oyvey.CrystalUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class HeadCrystal
extends Module {
    /* synthetic */ boolean breakFlag;
    /* synthetic */ int progress;
    /* synthetic */ Entity currentEntity;
    /* synthetic */ int sleep;
    public /* synthetic */ Setting<Float> range;
    /* synthetic */ boolean flag;
    /* synthetic */ int civCounter;

    public HeadCrystal() {
        super("HeadCrystal", "CevBreaker skid from crberry", Module.Category.COMBAT, true, false, false);
        this.range = this.register(new Setting<Float>("Range", Float.valueOf(6.0f), Float.valueOf(0.0f), Float.valueOf(6.0f)));
        this.progress = 0;
    }

    private int findMaterials(Block block) {
        for (int i = 0; i < 9; ++i) {
            if (!(HeadCrystal.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemBlock) || ((ItemBlock)HeadCrystal.mc.player.inventory.getStackInSlot(i).getItem()).getBlock() != block) continue;
            return i;
        }
        return -1;
    }

    @Override
    public void onTick() {
        int n = this.findItem(Items.DIAMOND_PICKAXE);
        int n2 = this.findItem(Items.END_CRYSTAL);
        int n3 = this.findMaterials(Blocks.OBSIDIAN);
        BlockPos[] arrblockPos = new BlockPos[]{new BlockPos(0, 0, 1), new BlockPos(0, 1, 1), new BlockPos(0, 1, 0)};
        int n4 = InventoryUtilsCr.getSlot();
        if (n == -1 || n2 == -1 || n3 == -1) {
            Command.sendMessage("Pix or Crystal or Obsidian No Material");
            this.disable();
            return;
        }
        if (this.currentEntity == null || (double)this.currentEntity.getDistanceToEntity((Entity)HeadCrystal.mc.player) > (double)this.range.getValue().floatValue()) {
            this.findTarget();
        }
        if (this.currentEntity != null) {
            Entity entity = this.currentEntity;
            if (entity instanceof EntityPlayer && !AliceMain.friendManager.isFriend(entity.getName())) {
                if (n2 == -1 || n2 != -1 || !((ItemStack)HeadCrystal.mc.player.inventory.offHandInventory.get(0)).getItem().getClass().equals(Item.getItemById((int)426).getClass())) {
                    // empty if block
                }
                if (this.sleep > 0) {
                    --this.sleep;
                } else {
                    switch (this.progress) {
                        case 0: {
                            BlockPos blockPos = new BlockPos(entity);
                            for (BlockPos blockPos2 : arrblockPos) {
                                if (Arrays.asList(arrblockPos).indexOf((Object)blockPos2) != -1 && this.civCounter < 1) {
                                    this.flag = true;
                                    InventoryUtilsCr.setSlot(n3);
                                } else {
                                    InventoryUtilsCr.setSlot(n3);
                                }
                                BlockUtilsCr blockUtilsCr = BlockUtilsCr.isPlaceable(blockPos.add((Vec3i)blockPos2), 0.0, true);
                                if (blockUtilsCr == null) continue;
                                blockUtilsCr.doPlace(true);
                            }
                            InventoryUtilsCr.setSlot(n2);
                            CrystalUtil.placeCrystal(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ + 1.0));
                            ++this.progress;
                            break;
                        }
                        case 1: {
                            InventoryUtilsCr.setSlot(n);
                            HeadCrystal.mc.playerController.onPlayerDamageBlock(new BlockPos(entity).add(0, 1, 1), EnumFacing.UP);
                            mc.getConnection().sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, new BlockPos(entity).add(0, 1, 1), EnumFacing.UP));
                            if (HeadCrystal.mc.world.isAirBlock(new BlockPos(entity).add(0, 1, 1))) {
                                for (Entity entity2 : HeadCrystal.mc.world.loadedEntityList) {
                                    if ((double)entity.getDistanceToEntity(entity2) > (double)this.range.getValue().floatValue() || !(entity2 instanceof EntityEnderCrystal)) continue;
                                    HeadCrystal.mc.playerController.attackEntity((EntityPlayer)HeadCrystal.mc.player, entity2);
                                }
                                this.breakFlag = true;
                            }
                            if (this.civCounter < 1) {
                                HeadCrystal.mc.playerController.onPlayerDamageBlock(new BlockPos(entity).add(0, 1, 1), EnumFacing.UP);
                                this.sleep += 30;
                            }
                            ++this.progress;
                            break;
                        }
                        case 2: {
                            int n5 = 0;
                            for (Entity entity3 : HeadCrystal.mc.world.loadedEntityList) {
                                if ((double)entity.getDistanceToEntity(entity3) > (double)this.range.getValue().floatValue() || !(entity3 instanceof EntityEnderCrystal)) continue;
                                HeadCrystal.mc.playerController.attackEntity((EntityPlayer)HeadCrystal.mc.player, entity3);
                                ++n5;
                            }
                            if (n5 != 0 && !this.flag) break;
                            ++this.progress;
                            break;
                        }
                        case 3: {
                            BlockUtilsCr.doPlace(BlockUtilsCr.isPlaceable(new BlockPos(entity.posX, entity.posY + 1.0, entity.posZ + 1.0), 0.0, true), true);
                            InventoryUtilsCr.setSlot(n3);
                            this.progress = 0;
                            ++this.civCounter;
                        }
                    }
                }
                InventoryUtilsCr.setSlot(n4);
                return;
            }
            InventoryUtilsCr.setSlot(n4);
        }
    }

    @Override
    public void onEnable() {
        if (InstantMine.getInstance().isOn()) {
            InstantMine.getInstance().disable();
        }
        this.findTarget();
        this.progress = 0;
        this.breakFlag = false;
        this.flag = false;
        this.civCounter = 0;
        this.sleep = 0;
        super.onEnable();
    }

    public void findTarget() {
        this.currentEntity = HeadCrystal.mc.world.loadedEntityList.stream().filter(entity -> entity != HeadCrystal.mc.player && entity instanceof EntityLivingBase && (double)entity.getDistanceToEntity((Entity)HeadCrystal.mc.player) < (double)this.range.getValue().floatValue() && !AliceMain.friendManager.isFriend(entity.getName())).findFirst().orElse(null);
    }

    private int findItem(Item item) {
        if (item == Items.END_CRYSTAL && HeadCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            return 999;
        }
        for (int i = 0; i < 9; ++i) {
            if (HeadCrystal.mc.player.inventory.getStackInSlot(i).getItem() != item) continue;
            return i;
        }
        return -1;
    }

    @Override
    public void onDisable() {
        if (InstantMine.getInstance().isOff()) {
            InstantMine.getInstance().enable();
        }
        super.onDisable();
    }
}

