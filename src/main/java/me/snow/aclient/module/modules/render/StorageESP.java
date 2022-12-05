//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItemFrame
 *  net.minecraft.entity.item.EntityMinecartChest
 *  net.minecraft.item.ItemShulkerBox
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.tileentity.TileEntityDispenser
 *  net.minecraft.tileentity.TileEntityEnderChest
 *  net.minecraft.tileentity.TileEntityFurnace
 *  net.minecraft.tileentity.TileEntityHopper
 *  net.minecraft.tileentity.TileEntityShulkerBox
 *  net.minecraft.util.math.BlockPos
 */
package me.snow.aclient.module.modules.render;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.Colors;
import me.snow.aclient.util.ColorUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.RenderUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.util.math.BlockPos;

public class StorageESP
extends Module {
    private final /* synthetic */ Setting<Boolean> furnace;
    private final /* synthetic */ Setting<Boolean> shulker;
    private final /* synthetic */ Setting<Boolean> cart;
    private final /* synthetic */ Setting<Boolean> frame;
    private final /* synthetic */ Setting<Boolean> chest;
    private final /* synthetic */ Setting<Float> range;
    private final /* synthetic */ Setting<Boolean> dispenser;
    private final /* synthetic */ Setting<Boolean> box;
    private final /* synthetic */ Setting<Boolean> outline;
    private final /* synthetic */ Setting<Boolean> colorSync;
    private final /* synthetic */ Setting<Boolean> hopper;
    private final /* synthetic */ Setting<Float> lineWidth;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    private final /* synthetic */ Setting<Boolean> echest;

    public StorageESP() {
        super("StorageESP", "Highlights Containers.", Module.Category.RENDER, false, false, false);
        this.range = this.register(new Setting<Float>("Range", Float.valueOf(50.0f), Float.valueOf(1.0f), Float.valueOf(300.0f)));
        this.colorSync = this.register(new Setting<Boolean>("Sync", false));
        this.chest = this.register(new Setting<Boolean>("Chest", true));
        this.dispenser = this.register(new Setting<Boolean>("Dispenser", false));
        this.shulker = this.register(new Setting<Boolean>("Shulker", true));
        this.echest = this.register(new Setting<Boolean>("Ender Chest", true));
        this.furnace = this.register(new Setting<Boolean>("Furnace", false));
        this.hopper = this.register(new Setting<Boolean>("Hopper", false));
        this.cart = this.register(new Setting<Boolean>("Minecart", false));
        this.frame = this.register(new Setting<Boolean>("Item Frame", false));
        this.box = this.register(new Setting<Boolean>("Box", true));
        this.boxAlpha = this.register(new Setting<Object>("BoxAlpha", Integer.valueOf(70), Integer.valueOf(0), Integer.valueOf(255), object -> this.box.getValue()));
        this.outline = this.register(new Setting<Boolean>("Outline", true));
        this.lineWidth = this.register(new Setting<Object>("LineWidth", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(5.0f), object -> this.outline.getValue()));
    }

    private int getTileEntityColor(TileEntity tileEntity) {
        if (tileEntity instanceof TileEntityChest) {
            return ColorUtil.Colors.ORANGE;
        }
        if (tileEntity instanceof TileEntityShulkerBox) {
            return ColorUtil.Colors.MAGENTA;
        }
        if (tileEntity instanceof TileEntityEnderChest) {
            return ColorUtil.Colors.PURPLE;
        }
        if (tileEntity instanceof TileEntityFurnace) {
            return ColorUtil.Colors.LIGHT_GRAY;
        }
        if (tileEntity instanceof TileEntityHopper) {
            return ColorUtil.Colors.GRAY;
        }
        if (tileEntity instanceof TileEntityDispenser) {
            return ColorUtil.Colors.LIGHT_GRAY;
        }
        return -1;
    }

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        int n;
        BlockPos blockPos;
        HashMap<BlockPos, Integer> hashMap = new HashMap<BlockPos, Integer>();
        for (TileEntity object : StorageESP.mc.world.loadedTileEntityList) {
            BlockPos blockPos2;
            if (!(object instanceof TileEntityChest && this.chest.getValue() != false || object instanceof TileEntityDispenser && this.dispenser.getValue() != false || object instanceof TileEntityShulkerBox && this.shulker.getValue() != false || object instanceof TileEntityEnderChest && this.echest.getValue() != false || object instanceof TileEntityFurnace && this.furnace.getValue() != false) && (!(object instanceof TileEntityHopper) || !this.hopper.getValue().booleanValue())) continue;
            blockPos = object.getPos();
            if (!(StorageESP.mc.player.getDistanceSq(blockPos2) <= MathUtil.square(this.range.getValue().floatValue())) || (n = this.getTileEntityColor(object)) == -1) continue;
            hashMap.put(blockPos, n);
        }
        for (Entity entity : StorageESP.mc.world.loadedEntityList) {
            BlockPos blockPos3;
            if ((!(entity instanceof EntityItemFrame) || !this.frame.getValue().booleanValue()) && (!(entity instanceof EntityMinecartChest) || !this.cart.getValue().booleanValue())) continue;
            blockPos = entity.getPosition();
            if (!(StorageESP.mc.player.getDistanceSq(blockPos3) <= MathUtil.square(this.range.getValue().floatValue())) || (n = this.getEntityColor(entity)) == -1) continue;
            hashMap.put(blockPos, n);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            BlockPos blockPos4 = (BlockPos)entry.getKey();
            n = (Integer)entry.getValue();
            RenderUtil.drawBoxESP(blockPos4, this.colorSync.getValue() != false ? Colors.INSTANCE.getCurrentColor() : new Color(n), false, new Color(n), this.lineWidth.getValue().floatValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
        }
    }

    private int getEntityColor(Entity entity) {
        if (entity instanceof EntityMinecartChest) {
            return ColorUtil.Colors.ORANGE;
        }
        if (entity instanceof EntityItemFrame && ((EntityItemFrame)entity).getDisplayedItem().getItem() instanceof ItemShulkerBox) {
            return ColorUtil.Colors.YELLOW;
        }
        if (entity instanceof EntityItemFrame && !(((EntityItemFrame)entity).getDisplayedItem().getItem() instanceof ItemShulkerBox)) {
            return ColorUtil.Colors.ORANGE;
        }
        return -1;
    }
}

