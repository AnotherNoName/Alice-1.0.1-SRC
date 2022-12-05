//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.client.settings.IKeyConflictContext
 *  net.minecraftforge.client.settings.KeyConflictContext
 *  org.lwjgl.input.Keyboard
 */
package me.snow.aclient.module.modules.movement;

import java.util.function.Predicate;
import me.snow.aclient.event.events.GuiScreenEvent;
import me.snow.aclient.module.Module;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.input.Keyboard;

public class InventoryWalk
extends Module {
    @EventHandler
    public /* synthetic */ Listener<GuiScreenEvent.Displayed> listener;
    private static final /* synthetic */ KeyBinding[] MOVEMENT_KEYS;

    @Override
    public void onUpdate() {
        if (InventoryWalk.mc.currentScreen == null) {
            return;
        }
        if (InventoryWalk.mc.currentScreen instanceof GuiChat) {
            return;
        }
        InventoryWalk.mc.player.rotationYaw += Keyboard.isKeyDown((int)205) ? 4.0f : (Keyboard.isKeyDown((int)203) ? -4.0f : 0.0f);
        InventoryWalk.mc.player.rotationPitch = (float)((double)InventoryWalk.mc.player.rotationPitch + (double)(Keyboard.isKeyDown((int)208) ? 4 : (Keyboard.isKeyDown((int)200) ? -4 : 0)) * 0.75);
        InventoryWalk.mc.player.rotationPitch = MathHelper.clamp((float)InventoryWalk.mc.player.rotationPitch, (float)-90.0f, (float)90.0f);
        this.runCheck();
    }

    private void runCheck() {
        for (KeyBinding keyBinding : MOVEMENT_KEYS) {
            if (Keyboard.isKeyDown((int)keyBinding.getKeyCode())) {
                if (keyBinding.getKeyConflictContext() != KeyConflictContext.UNIVERSAL) {
                    keyBinding.setKeyConflictContext((IKeyConflictContext)KeyConflictContext.UNIVERSAL);
                }
                KeyBinding.setKeyBindState((int)keyBinding.getKeyCode(), (boolean)true);
                continue;
            }
            KeyBinding.setKeyBindState((int)keyBinding.getKeyCode(), (boolean)false);
        }
    }

    static {
        MOVEMENT_KEYS = new KeyBinding[]{InventoryWalk.mc.gameSettings.keyBindForward, InventoryWalk.mc.gameSettings.keyBindRight, InventoryWalk.mc.gameSettings.keyBindBack, InventoryWalk.mc.gameSettings.keyBindLeft, InventoryWalk.mc.gameSettings.keyBindJump, InventoryWalk.mc.gameSettings.keyBindSprint};
    }

    public InventoryWalk() {
        super("GUIMove", "Allows you to Move while in GUIs", Module.Category.MOVEMENT, true, false, false);
        this.listener = new Listener<GuiScreenEvent.Displayed>(displayed -> {
            if (InventoryWalk.mc.currentScreen == null) {
                return;
            }
            if (InventoryWalk.mc.currentScreen instanceof GuiChat) {
                return;
            }
            this.runCheck();
        }, new Predicate[0]);
    }
}

