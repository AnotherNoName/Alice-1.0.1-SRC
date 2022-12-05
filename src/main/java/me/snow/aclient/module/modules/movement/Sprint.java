//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.movement;

import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.MoveEvent;
import me.snow.aclient.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Sprint
extends Module {
    public /* synthetic */ Setting<Mode> mode;
    private static /* synthetic */ Sprint INSTANCE;

    @Override
    public void onUpdate() {
        switch (this.mode.getValue()) {
            case RAGE: {
                if (!Sprint.mc.gameSettings.keyBindForward.isKeyDown() && !Sprint.mc.gameSettings.keyBindBack.isKeyDown() && !Sprint.mc.gameSettings.keyBindLeft.isKeyDown() && !Sprint.mc.gameSettings.keyBindRight.isKeyDown() || Sprint.mc.player.isSneaking() || Sprint.mc.player.isCollidedHorizontally || (float)Sprint.mc.player.getFoodStats().getFoodLevel() <= 6.0f) break;
                Sprint.mc.player.setSprinting(true);
                break;
            }
            case LEGIT: {
                if (!Sprint.mc.gameSettings.keyBindForward.isKeyDown() || Sprint.mc.player.isSneaking() || Sprint.mc.player.isHandActive() || Sprint.mc.player.isCollidedHorizontally || (float)Sprint.mc.player.getFoodStats().getFoodLevel() <= 6.0f || Sprint.mc.currentScreen != null) break;
                Sprint.mc.player.setSprinting(true);
            }
        }
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void onDisable() {
        if (!Sprint.nullCheck()) {
            Sprint.mc.player.setSprinting(false);
        }
    }

    @SubscribeEvent
    public void onSprint(MoveEvent moveEvent) {
        if (moveEvent.getStage() == 1 && this.mode.getValue() == Mode.RAGE && (Sprint.mc.player.movementInput.field_192832_b != 0.0f || Sprint.mc.player.movementInput.moveStrafe != 0.0f)) {
            moveEvent.setCanceled(true);
        }
    }

    public static Sprint getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Sprint();
        }
        return INSTANCE;
    }

    @Override
    public String getDisplayInfo() {
        return this.mode.currentEnumName();
    }

    static {
        INSTANCE = new Sprint();
    }

    public Sprint() {
        super("Sprint", "Modifies sprinting", Module.Category.MOVEMENT, false, false, false);
        this.mode = this.register(new Setting<Mode>("Mode", Mode.LEGIT));
        this.setInstance();
    }

    public static enum Mode {
        LEGIT,
        RAGE;

    }
}

