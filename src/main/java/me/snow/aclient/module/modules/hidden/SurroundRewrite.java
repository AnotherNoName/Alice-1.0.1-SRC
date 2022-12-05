//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockDeadBush
 *  net.minecraft.block.BlockEnderChest
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockObsidian
 *  net.minecraft.block.BlockSnow
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 */
package me.snow.aclient.module.modules.hidden;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.combat.autocrystal.AutoCrystal;
import me.snow.aclient.util.BlockUtil;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.Timer;
import me.snow.aclient.util.skid.oyvey.EntityUtil2;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class SurroundRewrite
extends Module {
    private static /* synthetic */ SurroundRewrite INSTANCE;
    private /* synthetic */ BlockPos startPos;
    private final /* synthetic */ Setting<Boolean> attack;
    private /* synthetic */ int obbySlot;
    private /* synthetic */ int placements;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Integer> retry;
    private final /* synthetic */ Timer retryTimer;
    private /* synthetic */ int lastHotbarSlot;
    private /* synthetic */ boolean didPlace;
    private /* synthetic */ int extenders;
    private final /* synthetic */ Setting<Boolean> allowEC;
    private final /* synthetic */ Setting<Center> center;
    private /* synthetic */ int isSafe;
    private final /* synthetic */ Set<Vec3d> extendingBlocks;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Setting<Integer> blocksPerTick;
    public static /* synthetic */ boolean isPlacing;
    private final /* synthetic */ Setting<Boolean> packet;
    private /* synthetic */ boolean isSneaking;
    /* synthetic */ Vec3d CenterPos;
    private final /* synthetic */ Setting<Boolean> info;
    private /* synthetic */ boolean offHand;
    private final /* synthetic */ Setting<Boolean> antiPedo;
    private final /* synthetic */ Setting<Integer> extender;
    private final /* synthetic */ Setting<Boolean> helpingBlocks;
    private final /* synthetic */ Map<BlockPos, Integer> retries;
    private /* synthetic */ boolean switchedItem;
    private final /* synthetic */ Setting<Boolean> rotate;

    public SurroundRewrite() {
        super("Surround", "awa surround", Module.Category.COMBAT, true, false, false);
        this.packet = this.register(new Setting<Boolean>("Packets", false));
        this.center = this.register(new Setting<Center>("TP Center", Center.None));
        this.rotate = this.register(new Setting<Boolean>("Rotate", false));
        this.delay = this.register(new Setting<Integer>("Delay/Place", 0, 0, 250));
        this.blocksPerTick = this.register(new Setting<Integer>("Block/Place", 12, 1, 20));
        this.allowEC = this.register(new Setting<Boolean>("AllowEChests", true));
        this.info = this.register(new Setting<Boolean>("DisplayInfo", false));
        this.helpingBlocks = this.register(new Setting<Boolean>("HelpingBlocks", true));
        this.antiPedo = this.register(new Setting<Boolean>("AlwaysHelp", false));
        this.attack = this.register(new Setting<Boolean>("Attack", false));
        this.retry = this.register(new Setting<Integer>("Retry", 4, 1, 15));
        this.extender = this.register(new Setting<Integer>("Extend", 1, 0, 4));
        this.CenterPos = Vec3d.ZERO;
        INSTANCE = this;
        this.timer = new Timer();
        this.retryTimer = new Timer();
        this.didPlace = false;
        this.placements = 0;
        this.extendingBlocks = new HashSet<Vec3d>();
        this.extenders = 1;
        this.obbySlot = -1;
        this.offHand = false;
        this.retries = new HashMap<BlockPos, Integer>();
    }

    public static Vec3d[] getOffsets(int n) {
        List<Vec3d> list = SurroundRewrite.getOffsetList(n);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])arrvec3d);
    }

    private void placeBlock(BlockPos blockPos) {
        if (this.placements < this.blocksPerTick.getValue()) {
            int n = SurroundRewrite.mc.player.inventory.currentItem;
            int n2 = InventoryUtil.findHotbarBlock(BlockObsidian.class);
            int n3 = InventoryUtil.findHotbarBlock(BlockEnderChest.class);
            if (n2 == -1 && n3 == -1) {
                this.toggle();
            }
            isPlacing = true;
            SurroundRewrite.mc.player.inventory.currentItem = n2 == -1 ? n3 : n2;
            SurroundRewrite.mc.playerController.updateController();
            this.isSneaking = BlockUtil.placeBlock(blockPos, this.offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), this.isSneaking);
            SurroundRewrite.mc.player.inventory.currentItem = n;
            SurroundRewrite.mc.playerController.updateController();
            this.didPlace = true;
            ++this.placements;
        }
    }

    public static SurroundRewrite getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SurroundRewrite();
        }
        return INSTANCE;
    }

    static {
        isPlacing = false;
    }

    public static List<Vec3d> getUnsafeBlocksFromVec3d(Vec3d vec3d, int n) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>(4);
        for (Vec3d vec3d2 : SurroundRewrite.getOffsets(n)) {
            BlockPos blockPos = new BlockPos(vec3d).add(vec3d2.xCoord, vec3d2.yCoord, vec3d2.zCoord);
            Block block = SurroundRewrite.mc.world.getBlockState(blockPos).getBlock();
            if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid) && !(block instanceof BlockTallGrass) && !(block instanceof BlockFire) && !(block instanceof BlockDeadBush) && !(block instanceof BlockSnow)) continue;
            arrayList.add(vec3d2);
        }
        return arrayList;
    }

    public static Vec3d[] getUnsafeBlockArray(Entity entity, int n) {
        List<Vec3d> list = SurroundRewrite.getUnsafeBlocks(entity, n);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])arrvec3d);
    }

    @Override
    public void onEnable() {
        if (SurroundRewrite.fullNullCheck()) {
            this.disable();
        }
        super.onEnable();
        this.lastHotbarSlot = SurroundRewrite.mc.player.inventory.currentItem;
        this.startPos = EntityUtil.getRoundedBlockPos((Entity)SurroundRewrite.mc.player);
        this.CenterPos = EntityUtil.getCenter(SurroundRewrite.mc.player.posX, SurroundRewrite.mc.player.posY, SurroundRewrite.mc.player.posZ);
        if (!EntityUtil.isPlayerSafe((EntityPlayer)SurroundRewrite.mc.player)) {
            switch (this.center.getValue()) {
                case Instant: {
                    AliceMain.positionManager.setPositionPacket((double)this.startPos.getX() + 0.5, this.startPos.getY(), (double)this.startPos.getZ() + 0.5, true, true, true);
                }
                case NCP: {
                    AliceMain.movementManager.setMotion((this.CenterPos.xCoord - SurroundRewrite.mc.player.posX) / 2.0, SurroundRewrite.mc.player.motionY, (this.CenterPos.zCoord - SurroundRewrite.mc.player.posZ) / 2.0);
                }
            }
        }
        this.retries.clear();
        this.retryTimer.reset();
    }

    public static Vec3d[] getUnsafeBlockArrayFromVec3d(Vec3d vec3d, int n) {
        List<Vec3d> list = SurroundRewrite.getUnsafeBlocksFromVec3d(vec3d, n);
        Vec3d[] arrvec3d = new Vec3d[list.size()];
        return list.toArray((T[])arrvec3d);
    }

    private void processExtendingBlocks() {
        if (this.extendingBlocks.size() == 2 && this.extenders < this.extender.getValue()) {
            Vec3d[] arrvec3d = new Vec3d[2];
            int n = 0;
            Iterator<Vec3d> iterator2 = this.extendingBlocks.iterator();
            while (iterator2.hasNext()) {
                Vec3d vec3d = arrvec3d[n] = iterator2.next();
                ++n;
            }
            if (this.attack.getValue().booleanValue()) {
                BlockUtil.doBreak(new BlockPos(this.areClose((Vec3d[])arrvec3d).xCoord, this.areClose((Vec3d[])arrvec3d).yCoord, this.areClose((Vec3d[])arrvec3d).zCoord), AutoCrystal.getInstance().maxSelfBreak.getValue().floatValue());
            }
            int n2 = this.placements;
            if (this.areClose(arrvec3d) != null) {
                this.placeBlocks(this.areClose(arrvec3d), EntityUtil2.getUnsafeBlockArrayFromVec3d(this.areClose(arrvec3d), 0, this.helpingBlocks.getValue()), true, false, true);
            }
            if (n2 < this.placements) {
                this.extendingBlocks.clear();
            }
        } else if (this.extendingBlocks.size() > 2 || this.extenders >= this.extender.getValue()) {
            this.extendingBlocks.clear();
        }
    }

    public static List<Vec3d> getOffsetList(int n) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>(4);
        arrayList.add(new Vec3d(-1.0, (double)n, 0.0));
        arrayList.add(new Vec3d(1.0, (double)n, 0.0));
        arrayList.add(new Vec3d(0.0, (double)n, -1.0));
        arrayList.add(new Vec3d(0.0, (double)n, 1.0));
        return arrayList;
    }

    @Override
    public String getDisplayInfo() {
        if (!this.info.getValue().booleanValue()) {
            return null;
        }
        switch (this.isSafe) {
            case 0: {
                return String.valueOf(new StringBuilder().append((Object)ChatFormatting.RED).append("Unsafe"));
            }
            case 1: {
                return String.valueOf(new StringBuilder().append((Object)ChatFormatting.YELLOW).append("Safe"));
            }
        }
        return String.valueOf(new StringBuilder().append((Object)ChatFormatting.GREEN).append("Safe"));
    }

    private boolean check() {
        if (SurroundRewrite.nullCheck()) {
            return true;
        }
        int n = InventoryUtil.findHotbarBlock(BlockObsidian.class);
        int n2 = InventoryUtil.findHotbarBlock(BlockEnderChest.class);
        if (n == -1 && n2 == -1) {
            this.toggle();
        }
        this.offHand = InventoryUtil.isBlock(SurroundRewrite.mc.player.getHeldItemOffhand().getItem(), BlockObsidian.class);
        isPlacing = false;
        this.didPlace = false;
        this.extenders = 1;
        this.placements = 0;
        this.obbySlot = InventoryUtil.findHotbarBlock(BlockObsidian.class);
        int n3 = InventoryUtil.findHotbarBlock(BlockEnderChest.class);
        if (this.isOff()) {
            return true;
        }
        if (this.retryTimer.passedMs(2500L)) {
            this.retries.clear();
            this.retryTimer.reset();
        }
        if (this.obbySlot == -1 && !this.offHand && n3 == -1) {
            this.obbySlot = n3;
            if (!this.allowEC.getValue().booleanValue() || n3 == -1) {
                Command.sendMessage(String.valueOf(new StringBuilder().append("Out of blocks, disabling ").append((Object)ChatFormatting.RED).append("Surround")));
                this.disable();
                return true;
            }
        }
        if (this.obbySlot == -1 && !this.offHand && n3 == -1) {
            Command.sendMessage(String.valueOf(new StringBuilder().append("<").append(this.getDisplayName()).append("> ").append((Object)ChatFormatting.RED).append("No Obsidian in hotbar disabling...")));
            this.disable();
            return true;
        }
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
        if (SurroundRewrite.mc.player.inventory.currentItem != this.lastHotbarSlot && SurroundRewrite.mc.player.inventory.currentItem != this.obbySlot && SurroundRewrite.mc.player.inventory.currentItem != n3) {
            this.lastHotbarSlot = SurroundRewrite.mc.player.inventory.currentItem;
        }
        if (!this.startPos.equals((Object)EntityUtil.getRoundedBlockPos((Entity)SurroundRewrite.mc.player))) {
            this.disable();
            return true;
        }
        return !this.timer.passedMs(this.delay.getValue().intValue());
    }

    @Override
    public void onTick() {
        this.doFeetPlace();
    }

    @Override
    public void onDisable() {
        if (SurroundRewrite.nullCheck()) {
            return;
        }
        super.onDisable();
        isPlacing = false;
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
    }

    public boolean isSafe2(Entity entity, int n) {
        return SurroundRewrite.getUnsafeBlocks(entity, n).size() == 0;
    }

    private void doFeetPlace() {
        if (this.check()) {
            return;
        }
        if (!EntityUtil2.isSafe((Entity)SurroundRewrite.mc.player, 0, true)) {
            this.isSafe = 0;
            this.placeBlocks(SurroundRewrite.mc.player.getPositionVector(), EntityUtil2.getUnsafeBlockArray((Entity)SurroundRewrite.mc.player, 0, true), true, false, false);
        } else if (!EntityUtil2.isSafe((Entity)SurroundRewrite.mc.player, -1, false)) {
            this.isSafe = 1;
            this.placeBlocks(SurroundRewrite.mc.player.getPositionVector(), EntityUtil2.getUnsafeBlockArray((Entity)SurroundRewrite.mc.player, -1, false), false, false, true);
        } else {
            this.isSafe = 2;
        }
        this.processExtendingBlocks();
        if (this.didPlace) {
            this.timer.reset();
        }
        boolean bl = SurroundRewrite.mc.world.getBlockState(new BlockPos(SurroundRewrite.mc.player.getPositionVector())).getBlock() == Blocks.ENDER_CHEST;
        boolean bl2 = bl;
        if (SurroundRewrite.mc.player.posY - (double)((int)SurroundRewrite.mc.player.posY) < 0.7) {
            bl = false;
        }
        if (!this.isSafe2((Entity)SurroundRewrite.mc.player, bl ? 1 : 0)) {
            this.placeBlocks(SurroundRewrite.mc.player.getPositionVector(), SurroundRewrite.getUnsafeBlockArray((Entity)SurroundRewrite.mc.player, bl ? 1 : 0), this.helpingBlocks.getValue(), false, false);
        } else if (!this.isSafe2((Entity)SurroundRewrite.mc.player, bl ? 0 : -1) && this.antiPedo.getValue().booleanValue()) {
            this.placeBlocks(SurroundRewrite.mc.player.getPositionVector(), SurroundRewrite.getUnsafeBlockArray((Entity)SurroundRewrite.mc.player, bl ? 0 : -1), false, false, true);
        }
    }

    private boolean placeBlocks(Vec3d vec3d, Vec3d[] arrvec3d, boolean bl, boolean bl2, boolean bl3) {
        boolean bl4 = true;
        block6: for (Vec3d vec3d2 : arrvec3d) {
            bl4 = true;
            BlockPos blockPos = new BlockPos(vec3d).add(vec3d2.xCoord, vec3d2.yCoord, vec3d2.zCoord);
            switch (BlockUtil.isPositionPlaceable(blockPos, false)) {
                case -1: {
                    continue block6;
                }
                case 1: {
                    if (this.retries.get((Object)blockPos) == null || this.retries.get((Object)blockPos) < this.retry.getValue()) {
                        this.placeBlock(blockPos);
                        this.retries.put(blockPos, this.retries.get((Object)blockPos) == null ? 1 : this.retries.get((Object)blockPos) + 1);
                        this.retryTimer.reset();
                        continue block6;
                    }
                    if (this.extender.getValue() <= 0 || bl3 || this.extenders >= this.extender.getValue()) continue block6;
                    this.placeBlocks(SurroundRewrite.mc.player.getPositionVector().add(vec3d2), EntityUtil2.getUnsafeBlockArrayFromVec3d(SurroundRewrite.mc.player.getPositionVector().add(vec3d2), 0, true), bl, false, true);
                    this.extendingBlocks.add(vec3d2);
                    ++this.extenders;
                    continue block6;
                }
                case 2: {
                    if (!bl) continue block6;
                    bl4 = this.placeBlocks(vec3d, BlockUtil.getHelpingBlocks(vec3d2), false, true, true);
                }
                case 3: {
                    if (bl4) {
                        this.placeBlock(blockPos);
                    }
                    if (!bl2) continue block6;
                    return true;
                }
            }
        }
        return false;
    }

    public static List<Vec3d> getUnsafeBlocks(Entity entity, int n) {
        return SurroundRewrite.getUnsafeBlocksFromVec3d(entity.getPositionVector(), n);
    }

    private Vec3d areClose(Vec3d[] arrvec3d) {
        int n = 0;
        for (Vec3d vec3d : arrvec3d) {
            for (Vec3d vec3d2 : EntityUtil2.getUnsafeBlockArray((Entity)SurroundRewrite.mc.player, 0, true)) {
                if (!vec3d.equals((Object)vec3d2)) continue;
                ++n;
            }
        }
        if (n == 2) {
            return SurroundRewrite.mc.player.getPositionVector().add(arrvec3d[0].add(arrvec3d[1]));
        }
        return null;
    }

    public static enum Center {
        None,
        Instant,
        NCP;

    }
}

