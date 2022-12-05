//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$KeyInputEvent
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 */
package me.snow.aclient.module.modules.misc;

import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.core.gui.LuigiGui;
import me.snow.aclient.core.setting.Bind;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.client.HUD;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class MCF
extends Module {
    private final /* synthetic */ Setting<Boolean> sendmsg;
    private /* synthetic */ boolean clicked;
    private final /* synthetic */ Setting<Bind> key;
    private final /* synthetic */ Setting<Boolean> middleClick;
    private final /* synthetic */ Setting<Boolean> keyboard;

    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onKeyInput(InputEvent.KeyInputEvent keyInputEvent) {
        if (this.keyboard.getValue().booleanValue() && Keyboard.getEventKeyState() && !(MCF.mc.currentScreen instanceof LuigiGui) && this.key.getValue().getKey() == Keyboard.getEventKey()) {
            this.onClick();
        }
    }

    public MCF() {
        super("MCF", "Middleclick Friends.", Module.Category.MISC, true, false, false);
        this.middleClick = this.register(new Setting<Boolean>("MiddleClick", true));
        this.keyboard = this.register(new Setting<Boolean>("Keyboard", false));
        this.sendmsg = this.register(new Setting<Boolean>("Send Msgs", true));
        this.key = this.register(new Setting<Object>("KeyBind", new Bind(-1), object -> this.keyboard.getValue()));
    }

    private void onClick() {
        Entity entity;
        RayTraceResult rayTraceResult = MCF.mc.objectMouseOver;
        if (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.ENTITY && (entity = rayTraceResult.entityHit) instanceof EntityPlayer) {
            if (AliceMain.friendManager.isFriend(entity.getName())) {
                AliceMain.friendManager.removeFriend(entity.getName());
                Command.sendMessage(String.valueOf(new StringBuilder().append("\u00a7c").append(entity.getName()).append("\u00a7r unfriended.")));
                if (this.sendmsg.getValue().booleanValue()) {
                    // empty if block
                }
            } else {
                AliceMain.friendManager.addFriend(entity.getName());
                Command.sendMessage(String.valueOf(new StringBuilder().append("\u00a7b").append(entity.getName()).append("\u00a7r friended.")));
                if (this.sendmsg.getValue().booleanValue()) {
                    MCF.mc.player.sendChatMessage(String.valueOf(new StringBuilder().append("/msg ").append(entity.getName()).append(" You got added to ").append(HUD.mc.player.getDisplayNameString()).append("'s friends list.")));
                }
            }
        }
        this.clicked = true;
    }

    @Override
    public void onUpdate() {
        if (Mouse.isButtonDown((int)2)) {
            if (!this.clicked && this.middleClick.getValue().booleanValue() && MCF.mc.currentScreen == null) {
                this.onClick();
            }
            this.clicked = true;
        } else {
            this.clicked = false;
        }
    }
}

