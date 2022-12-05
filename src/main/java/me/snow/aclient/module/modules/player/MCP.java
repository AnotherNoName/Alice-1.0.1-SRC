//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemEnderPearl
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.world.World
 *  org.lwjgl.input.Mouse
 */
package me.snow.aclient.module.modules.player;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.InventoryUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;

public class MCP
extends Module {
    private final /* synthetic */ Setting<Boolean> skyonly;
    private /* synthetic */ boolean clicked;
    private final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<Boolean> skyonly2;
    private final /* synthetic */ Setting<Boolean> antiFriend;

    public MCP() {
        super("MCP", "Throws a pearl", Module.Category.PLAYER, false, false, false);
        this.mode = this.register(new Setting<Mode>("Mode", Mode.MIDDLECLICK));
        this.antiFriend = this.register(new Setting<Boolean>("AntiFriend", true));
        this.skyonly = this.register(new Setting<Boolean>("AboveHorizon", false));
        this.skyonly2 = this.register(new Setting<Boolean>("Skyonly", false));
    }

    private void throwPearl() {
        boolean bl;
        RayTraceResult rayTraceResult;
        if (this.antiFriend.getValue().booleanValue() && (rayTraceResult = MCP.mc.objectMouseOver) != null && rayTraceResult.typeOfHit == RayTraceResult.Type.ENTITY && rayTraceResult.entityHit instanceof EntityPlayer) {
            return;
        }
        if (this.skyonly.getValue().booleanValue() && MCP.mc.player.rotationPitch > 0.0f) {
            return;
        }
        if (this.skyonly2.getValue().booleanValue() && (rayTraceResult = MCP.mc.objectMouseOver) != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
            return;
        }
        int n = InventoryUtil.findHotbarBlock(ItemEnderPearl.class);
        boolean bl2 = bl = MCP.mc.player.getHeldItemOffhand().getItem() == Items.ENDER_PEARL;
        if (n != -1 || bl) {
            int n2 = MCP.mc.player.inventory.currentItem;
            if (!bl) {
                InventoryUtil.switchToHotbarSlot(n, false);
            }
            MCP.mc.playerController.processRightClick((EntityPlayer)MCP.mc.player, (World)MCP.mc.world, bl ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
            if (!bl) {
                InventoryUtil.switchToHotbarSlot(n2, false);
            }
        }
    }

    @Override
    public void onTick() {
        if (this.mode.getValue() == Mode.MIDDLECLICK) {
            if (Mouse.isButtonDown((int)2)) {
                if (!this.clicked) {
                    this.throwPearl();
                }
                this.clicked = true;
            } else {
                this.clicked = false;
            }
        }
    }

    @Override
    public void onEnable() {
        if (!MCP.fullNullCheck() && this.mode.getValue() == Mode.TOGGLE) {
            this.throwPearl();
            this.disable();
        }
    }

    public static enum Mode {
        TOGGLE,
        MIDDLECLICK;

    }
}

