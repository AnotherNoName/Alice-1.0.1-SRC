//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.server.SPacketEntityStatus
 *  net.minecraft.network.play.server.SPacketPlayerListItem
 *  net.minecraft.network.play.server.SPacketPlayerListItem$Action
 *  net.minecraft.network.play.server.SPacketSoundEffect
 *  net.minecraft.network.play.server.SPacketTimeUpdate
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.ClientChatEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Post
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Text
 *  net.minecraftforge.client.event.RenderHandEvent
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.fml.common.eventhandler.Event
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientConnectedToServerEvent
 *  net.minecraftforge.fml.common.network.FMLNetworkEvent$ClientDisconnectionFromServerEvent
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.manager;

import com.google.common.base.Strings;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import me.snow.aclient.AliceMain;
import me.snow.aclient.command.Command;
import me.snow.aclient.event.events.ChorusEvent;
import me.snow.aclient.event.events.ConnectionEvent;
import me.snow.aclient.event.events.DeathEvent;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.Render2DEvent;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.event.events.TotemPopEvent;
import me.snow.aclient.event.events.UpdateWalkingPlayerEvent;
import me.snow.aclient.module.Feature;
import me.snow.aclient.module.modules.client.TotemPopCounter;
import me.snow.aclient.module.modules.combat.autocrystal.AutoCrystal;
import me.snow.aclient.util.GLUProjection;
import me.snow.aclient.util.Timer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.network.play.server.SPacketPlayerListItem;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.lwjgl.opengl.GL11;

public class EventManager
extends Feature {
    private final /* synthetic */ Timer chorusTimer;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Timer logoutTimer;
    private final /* synthetic */ Timer switchTimer;
    private /* synthetic */ boolean keyTimeout;
    private final /* synthetic */ AtomicBoolean tickOngoing;

    @SubscribeEvent
    public void onClientDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent clientDisconnectionFromServerEvent) {
        AliceMain.moduleManager.onLogout();
        AliceMain.totemPopManager.onLogout();
        AliceMain.potionManager.onLogout();
    }

    public void onUnload() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketHeldItemChange) {
            this.switchTimer.reset();
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onChatSent(ClientChatEvent clientChatEvent) {
        if (clientChatEvent.getMessage().startsWith(Command.getCommandPrefix())) {
            clientChatEvent.setCanceled(true);
            try {
                EventManager.mc.ingameGUI.getChatGUI().addToSentMessages(clientChatEvent.getMessage());
                if (clientChatEvent.getMessage().length() > 1) {
                    AliceMain.commandManager.executeCommand(clientChatEvent.getMessage().substring(Command.getCommandPrefix().length() - 1));
                } else {
                    Command.sendMessage("Please enter a command.");
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
                Command.sendMessage("\u00a7cAn error occurred while running this command. Check the log!");
            }
            clientChatEvent.setMessage("");
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onTickHighest(TickEvent.ClientTickEvent clientTickEvent) {
        if (clientTickEvent.phase == TickEvent.Phase.START) {
            this.tickOngoing.set(true);
        }
    }

    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent livingUpdateEvent) {
        if (!EventManager.fullNullCheck() && livingUpdateEvent.getEntity().getEntityWorld().isRemote && livingUpdateEvent.getEntityLiving().equals((Object)EventManager.mc.player)) {
            AliceMain.potionManager.update();
            AliceMain.totemPopManager.onUpdate();
            AliceMain.inventoryManager.update();
            AliceMain.holeManager.update();
            AliceMain.safetyManager.onUpdate();
            AliceMain.moduleManager.onUpdate();
            AliceMain.timerManager.update();
            if (this.timer.passedMs(0L)) {
                AliceMain.moduleManager.sortModules(true);
                AliceMain.moduleManager.alphabeticallySortModules();
                this.timer.reset();
            }
        }
    }

    @SubscribeEvent
    public void onClientConnect(FMLNetworkEvent.ClientConnectedToServerEvent clientConnectedToServerEvent) {
        this.logoutTimer.reset();
        AliceMain.moduleManager.onLogin();
    }

    @SubscribeEvent
    public void onWorldRender(RenderWorldLastEvent renderWorldLastEvent) {
        if (renderWorldLastEvent.isCanceled()) {
            return;
        }
        EventManager.mc.mcProfiler.startSection("phobos");
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.shadeModel((int)7425);
        GlStateManager.disableDepth();
        GlStateManager.glLineWidth((float)1.0f);
        Render3DEvent render3DEvent = new Render3DEvent(renderWorldLastEvent.getPartialTicks());
        GLUProjection gLUProjection = GLUProjection.getInstance();
        IntBuffer intBuffer = GLAllocation.createDirectIntBuffer((int)16);
        FloatBuffer floatBuffer = GLAllocation.createDirectFloatBuffer((int)16);
        FloatBuffer floatBuffer2 = GLAllocation.createDirectFloatBuffer((int)16);
        GL11.glGetFloat((int)2982, (FloatBuffer)floatBuffer);
        GL11.glGetFloat((int)2983, (FloatBuffer)floatBuffer2);
        GL11.glGetInteger((int)2978, (IntBuffer)intBuffer);
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        gLUProjection.updateMatrices(intBuffer, floatBuffer, floatBuffer2, (double)scaledResolution.getScaledWidth() / (double)Minecraft.getMinecraft().displayWidth, (double)scaledResolution.getScaledHeight() / (double)Minecraft.getMinecraft().displayHeight);
        AliceMain.moduleManager.onRender3D(render3DEvent);
        GlStateManager.glLineWidth((float)1.0f);
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        GlStateManager.enableCull();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
        EventManager.mc.mcProfiler.endSection();
    }

    public boolean isOnSwitchCoolDown() {
        return !this.switchTimer.passedMs(500L);
    }

    public boolean ticksOngoing() {
        return this.tickOngoing.get();
    }

    @SubscribeEvent
    public void onRenderHand(RenderHandEvent renderHandEvent) {
        AliceMain.moduleManager.onRenderHand(renderHandEvent);
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onTickLowest(TickEvent.ClientTickEvent clientTickEvent) {
        if (clientTickEvent.phase == TickEvent.Phase.END) {
            this.tickOngoing.set(false);
            AutoCrystal.getInstance().postTick();
        }
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (receive.getStage() != 0) {
            return;
        }
        AliceMain.serverManager.onPacketReceived();
        if (receive.getPacket() instanceof SPacketEntityStatus) {
            SPacketEntityStatus sPacketEntityStatus = (SPacketEntityStatus)receive.getPacket();
            if (sPacketEntityStatus.getOpCode() == 35 && sPacketEntityStatus.getEntity((World)EventManager.mc.world) instanceof EntityPlayer) {
                EntityPlayer entityPlayer = (EntityPlayer)sPacketEntityStatus.getEntity((World)EventManager.mc.world);
                MinecraftForge.EVENT_BUS.post((Event)new TotemPopEvent(entityPlayer));
                TotemPopCounter.getInstance().onTotemPop(entityPlayer);
                AliceMain.totemPopManager.onTotemPop(entityPlayer);
                AliceMain.potionManager.onTotemPop(entityPlayer);
            }
        } else if (receive.getPacket() instanceof SPacketPlayerListItem && !EventManager.fullNullCheck() && this.logoutTimer.passedS(1.0)) {
            SPacketPlayerListItem sPacketPlayerListItem = (SPacketPlayerListItem)receive.getPacket();
            if (!SPacketPlayerListItem.Action.ADD_PLAYER.equals((Object)sPacketPlayerListItem.getAction()) && !SPacketPlayerListItem.Action.REMOVE_PLAYER.equals((Object)sPacketPlayerListItem.getAction())) {
                return;
            }
            sPacketPlayerListItem.getEntries().stream().filter(Objects::nonNull).filter(addPlayerData -> !Strings.isNullOrEmpty((String)addPlayerData.getProfile().getName()) || addPlayerData.getProfile().getId() != null).forEach(addPlayerData -> {
                UUID uUID = addPlayerData.getProfile().getId();
                switch (sPacketPlayerListItem.getAction()) {
                    case ADD_PLAYER: {
                        String string = addPlayerData.getProfile().getName();
                        MinecraftForge.EVENT_BUS.post((Event)new ConnectionEvent(0, uUID, string));
                        break;
                    }
                    case REMOVE_PLAYER: {
                        EntityPlayer entityPlayer = EventManager.mc.world.getPlayerEntityByUUID(uUID);
                        if (entityPlayer != null) {
                            String string = entityPlayer.getName();
                            MinecraftForge.EVENT_BUS.post((Event)new ConnectionEvent(1, entityPlayer, uUID, string));
                            break;
                        }
                        MinecraftForge.EVENT_BUS.post((Event)new ConnectionEvent(2, uUID, null));
                    }
                }
            });
        } else if (receive.getPacket() instanceof SPacketTimeUpdate) {
            AliceMain.serverManager.update();
        } else if (receive.getPacket() instanceof SPacketSoundEffect && ((SPacketSoundEffect)receive.getPacket()).getSound() == SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT) {
            if (!this.chorusTimer.passedMs(100L)) {
                MinecraftForge.EVENT_BUS.post((Event)new ChorusEvent(((SPacketSoundEffect)receive.getPacket()).getX(), ((SPacketSoundEffect)receive.getPacket()).getY(), ((SPacketSoundEffect)receive.getPacket()).getZ()));
            }
            this.chorusTimer.reset();
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent clientTickEvent) {
        if (EventManager.fullNullCheck()) {
            return;
        }
        AliceMain.moduleManager.onTick();
        for (EntityPlayer entityPlayer : EventManager.mc.world.playerEntities) {
            if (entityPlayer == null || entityPlayer.getHealth() > 0.0f) continue;
            MinecraftForge.EVENT_BUS.post((Event)new DeathEvent(entityPlayer));
            TotemPopCounter.getInstance().onDeath(entityPlayer);
        }
    }

    public void init() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent(priority=EventPriority.LOW)
    public void onRenderGameOverlayEvent(RenderGameOverlayEvent.Text text) {
        if (text.getType().equals((Object)RenderGameOverlayEvent.ElementType.TEXT)) {
            ScaledResolution scaledResolution = new ScaledResolution(mc);
            Render2DEvent render2DEvent = new Render2DEvent(text.getPartialTicks(), scaledResolution);
            AliceMain.moduleManager.onRender2D(render2DEvent);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    @SubscribeEvent
    public void renderHUD(RenderGameOverlayEvent.Post post) {
        if (post.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            AliceMain.textManager.updateResolution();
        }
    }

    public EventManager() {
        this.timer = new Timer();
        this.logoutTimer = new Timer();
        this.switchTimer = new Timer();
        this.chorusTimer = new Timer();
        this.tickOngoing = new AtomicBoolean(false);
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onUpdateWalkingPlayer(UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (EventManager.fullNullCheck()) {
            return;
        }
        if (updateWalkingPlayerEvent.getStage() == 0) {
            AliceMain.baritoneManager.onUpdateWalkingPlayer();
            AliceMain.speedManager.updateValues();
            AliceMain.rotationManager.updateRotations();
            AliceMain.positionManager.updatePosition();
        }
        if (updateWalkingPlayerEvent.getStage() == 1) {
            AliceMain.rotationManager.restoreRotations();
            AliceMain.positionManager.restorePosition();
        }
    }
}

