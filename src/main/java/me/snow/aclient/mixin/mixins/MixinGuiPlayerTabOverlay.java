//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiPlayerTabOverlay
 *  net.minecraft.client.network.NetworkPlayerInfo
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package me.snow.aclient.mixin.mixins;

import java.util.List;
import kotlin.collections.CollectionsKt;
import me.snow.aclient.event.events.RenderPlayerInTabEvent;
import me.snow.aclient.module.modules.misc.ExtraTab;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={GuiPlayerTabOverlay.class}, priority=2147463647)
public class MixinGuiPlayerTabOverlay {
    private List<NetworkPlayerInfo> preSubList = CollectionsKt.emptyList();

    @ModifyVariable(method={"renderPlayerlist"}, at=@At(value="STORE", ordinal=0), ordinal=0)
    public List<NetworkPlayerInfo> renderPlayerlistStorePlayerListPre(List<NetworkPlayerInfo> list) {
        this.preSubList = list;
        return list;
    }

    @ModifyVariable(method={"renderPlayerlist"}, at=@At(value="STORE", ordinal=1), ordinal=0)
    public List<NetworkPlayerInfo> renderPlayerlistStorePlayerListPost(List<NetworkPlayerInfo> list) {
        return ExtraTab.subList(this.preSubList, list);
    }

    @Redirect(method={"renderPlayerlist"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;FFI)I"))
    public int onRenderPlayerName(FontRenderer fontRenderer, String string, float f, float f2, int n) {
        RenderPlayerInTabEvent renderPlayerInTabEvent = new RenderPlayerInTabEvent(string);
        MinecraftForge.EVENT_BUS.post((Event)renderPlayerInTabEvent);
        return fontRenderer.drawStringWithShadow(renderPlayerInTabEvent.getName(), f, f2, n);
    }
}

