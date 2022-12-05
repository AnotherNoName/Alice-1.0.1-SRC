//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketChatMessage
 *  net.minecraft.network.play.client.CPacketClientStatus
 *  net.minecraft.network.play.client.CPacketConfirmTeleport
 *  net.minecraft.network.play.client.CPacketKeepAlive
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketTabComplete
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.module.modules.player;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.mixin.mixins.accessors.IRenderManager;
import me.snow.aclient.module.Module;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketClientStatus;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketKeepAlive;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketTabComplete;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class Blink
extends Module {
    private /* synthetic */ Queue<Packet> storedPackets;
    public final /* synthetic */ Setting<Integer> red1;
    public /* synthetic */ Setting<Boolean> fill;
    public /* synthetic */ Setting<Float> circleWidth;
    private /* synthetic */ AtomicBoolean sending;
    public /* synthetic */ Setting<Boolean> cPacketPlayer;
    public final /* synthetic */ Setting<Integer> blue1;
    public /* synthetic */ Setting<Float> factor;
    public final /* synthetic */ Setting<Integer> green1;
    public /* synthetic */ Setting<Boolean> pulse;
    private /* synthetic */ Vec3d lastPos;
    public final /* synthetic */ Setting<Integer> alpha1;
    public /* synthetic */ Setting<Boolean> strict;
    public /* synthetic */ Setting<Boolean> render;

    @SubscribeEvent
    public void onPacket(PacketEvent.Send send) {
        Object t = send.getPacket();
        if (this.sending.get()) {
            return;
        }
        if (this.pulse.getValue().booleanValue()) {
            if (send.getPacket() instanceof CPacketPlayer) {
                if (this.strict.getValue().booleanValue() && !((CPacketPlayer)send.getPacket()).isOnGround()) {
                    this.sending.set(true);
                    while (!this.storedPackets.isEmpty()) {
                        Packet packet = this.storedPackets.poll();
                        Blink.mc.player.connection.sendPacket(packet);
                        if (!(packet instanceof CPacketPlayer)) continue;
                        this.lastPos = new Vec3d(((CPacketPlayer)packet).getX(Blink.mc.player.posX), ((CPacketPlayer)packet).getY(Blink.mc.player.posY), ((CPacketPlayer)packet).getZ(Blink.mc.player.posZ));
                    }
                    this.sending.set(false);
                    this.storedPackets.clear();
                } else {
                    send.isCanceled();
                    this.storedPackets.add((Packet)send.getPacket());
                }
            }
        } else if (!(t instanceof CPacketChatMessage || t instanceof CPacketConfirmTeleport || t instanceof CPacketKeepAlive || t instanceof CPacketTabComplete || t instanceof CPacketClientStatus)) {
            send.isCanceled();
            this.storedPackets.add((Packet)send.getPacket());
        }
    }

    @Override
    public void onDisable() {
        if (Blink.mc.world == null || Blink.mc.player == null) {
            return;
        }
        while (!this.storedPackets.isEmpty()) {
            Blink.mc.player.connection.sendPacket(this.storedPackets.poll());
        }
    }

    @Override
    public String getDisplayInfo() {
        return String.valueOf(new StringBuilder().append(this.storedPackets.size()).append(""));
    }

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        if (Blink.mc.player == null || Blink.mc.world == null) {
            return;
        }
        if (this.render.getValue().booleanValue() && this.lastPos != null) {
            int n;
            int n2;
            int n3;
            float f;
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            GL11.glEnable((int)3008);
            GL11.glBlendFunc((int)770, (int)771);
            GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
            IRenderManager iRenderManager = (IRenderManager)mc.getRenderManager();
            float[] arrf = Color.RGBtoHSB(this.red1.getValue(), this.green1.getValue(), this.blue1.getValue(), null);
            float f2 = f = (float)(System.currentTimeMillis() % 7200L) / 7200.0f;
            int n4 = Color.getHSBColor(f2, arrf[1], arrf[2]).getRGB();
            ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
            double d = this.lastPos.xCoord - iRenderManager.getRenderPosX();
            double d2 = this.lastPos.yCoord - iRenderManager.getRenderPosY();
            double d3 = this.lastPos.zCoord - iRenderManager.getRenderPosZ();
            GL11.glShadeModel((int)7425);
            GlStateManager.disableCull();
            GL11.glLineWidth((float)this.circleWidth.getValue().floatValue());
            GL11.glBegin((int)1);
            for (n3 = 0; n3 <= 360; ++n3) {
                Vec3d vec3d = new Vec3d(d + Math.sin((double)n3 * Math.PI / 180.0) * 0.5, d2 + 0.01, d3 + Math.cos((double)n3 * Math.PI / 180.0) * 0.5);
                arrayList.add(vec3d);
            }
            for (n3 = 0; n3 < arrayList.size() - 1; ++n3) {
                int n5 = n4 >> 16 & 0xFF;
                n2 = n4 >> 8 & 0xFF;
                n = n4 & 0xFF;
                GL11.glColor4f((float)((float)this.red1.getValue().intValue() / 255.0f), (float)((float)this.green1.getValue().intValue() / 255.0f), (float)((float)this.blue1.getValue().intValue() / 255.0f), (float)(this.fill.getValue() != false ? 1.0f : (float)this.alpha1.getValue().intValue() / 255.0f));
                GL11.glVertex3d((double)((Vec3d)arrayList.get((int)n3)).xCoord, (double)((Vec3d)arrayList.get((int)n3)).yCoord, (double)((Vec3d)arrayList.get((int)n3)).zCoord);
                GL11.glVertex3d((double)((Vec3d)arrayList.get((int)(n3 + 1))).xCoord, (double)((Vec3d)arrayList.get((int)(n3 + 1))).yCoord, (double)((Vec3d)arrayList.get((int)(n3 + 1))).zCoord);
                n4 = Color.getHSBColor(f2 += 0.0027777778f, arrf[1], arrf[2]).getRGB();
            }
            GL11.glEnd();
            if (this.fill.getValue().booleanValue()) {
                f2 = f;
                GL11.glBegin((int)9);
                for (n3 = 0; n3 < arrayList.size() - 1; ++n3) {
                    int n6 = n4 >> 16 & 0xFF;
                    n2 = n4 >> 8 & 0xFF;
                    n = n4 & 0xFF;
                    GL11.glColor4f((float)((float)this.red1.getValue().intValue() / 255.0f), (float)((float)this.green1.getValue().intValue() / 255.0f), (float)((float)this.blue1.getValue().intValue() / 255.0f), (float)((float)this.alpha1.getValue().intValue() / 255.0f));
                    GL11.glVertex3d((double)((Vec3d)arrayList.get((int)n3)).xCoord, (double)((Vec3d)arrayList.get((int)n3)).yCoord, (double)((Vec3d)arrayList.get((int)n3)).zCoord);
                    GL11.glVertex3d((double)((Vec3d)arrayList.get((int)(n3 + 1))).xCoord, (double)((Vec3d)arrayList.get((int)(n3 + 1))).yCoord, (double)((Vec3d)arrayList.get((int)(n3 + 1))).zCoord);
                    n4 = Color.getHSBColor(f2 += 0.0027777778f, arrf[1], arrf[2]).getRGB();
                }
                GL11.glEnd();
            }
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)3008);
            GlStateManager.enableCull();
            GL11.glShadeModel((int)7424);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    public Blink() {
        super("Blink", "Fakelag.", Module.Category.PLAYER, true, false, false);
        this.pulse = this.register(new Setting<Boolean>("Pulse", false));
        this.strict = this.register(new Setting<Boolean>("Strict", false));
        this.cPacketPlayer = this.register(new Setting<Boolean>("CPacketPlayer", true));
        this.factor = this.register(new Setting<Float>("Factor", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(10.0f)));
        this.render = this.register(new Setting<Boolean>("Render", true));
        this.fill = this.register(new Setting<Boolean>("Fill", true));
        this.circleWidth = this.register(new Setting<Float>("Width", Float.valueOf(2.5f), Float.valueOf(0.1f), Float.valueOf(5.0f), f -> this.render.getValue()));
        this.red1 = this.register(new Setting<Integer>("Red", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), n -> this.render.getValue()));
        this.green1 = this.register(new Setting<Integer>("Green", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), n -> this.render.getValue()));
        this.blue1 = this.register(new Setting<Integer>("Blue", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), n -> this.render.getValue()));
        this.alpha1 = this.register(new Setting<Integer>("Alpha", Integer.valueOf(200), Integer.valueOf(0), Integer.valueOf(255), n -> this.render.getValue()));
        this.storedPackets = new LinkedList<Packet>();
        this.lastPos = new Vec3d((Vec3i)BlockPos.ORIGIN);
        this.sending = new AtomicBoolean(false);
    }

    @Override
    @SubscribeEvent
    public void onUpdate() {
        if (this.pulse.getValue().booleanValue() && Blink.mc.player != null && Blink.mc.world != null && (float)this.storedPackets.size() >= this.factor.getValue().floatValue() * 10.0f) {
            this.sending.set(true);
            while (!this.storedPackets.isEmpty()) {
                Packet packet = this.storedPackets.poll();
                Blink.mc.player.connection.sendPacket(packet);
                if (!(packet instanceof CPacketPlayer)) continue;
                this.lastPos = new Vec3d(((CPacketPlayer)packet).getX(Blink.mc.player.posX), ((CPacketPlayer)packet).getY(Blink.mc.player.posY), ((CPacketPlayer)packet).getZ(Blink.mc.player.posZ));
            }
            this.sending.set(false);
            this.storedPackets.clear();
        }
    }

    @Override
    public void onEnable() {
        if (Blink.mc.player == null || Blink.mc.world == null || mc.isIntegratedServerRunning()) {
            this.toggle();
            return;
        }
        this.lastPos = Blink.mc.player.getPositionVector();
        this.sending.set(false);
        this.storedPackets.clear();
    }
}

