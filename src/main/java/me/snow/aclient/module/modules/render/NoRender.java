//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.gui.BossInfoClient
 *  net.minecraft.client.gui.GuiBossOverlay
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.passive.EntityBat
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.play.server.SPacketExplosion
 *  net.minecraft.network.play.server.SPacketTimeUpdate
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos$MutableBlockPos
 *  net.minecraft.world.BossInfo
 *  net.minecraft.world.GameType
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Post
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.event.entity.PlaySoundAtEntityEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.module.modules.render;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.BossInfoClient;
import net.minecraft.client.gui.GuiBossOverlay;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfo;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class NoRender
extends Module {
    public /* synthetic */ Setting<Boolean> advancements;
    public /* synthetic */ Setting<NoArmor> noArmor;
    private static /* synthetic */ NoRender INSTANCE;
    public /* synthetic */ Setting<Boss> boss;
    public /* synthetic */ Setting<Boolean> barriers;
    public /* synthetic */ Setting<Boolean> fire;
    public /* synthetic */ Setting<Boolean> noWeather;
    public /* synthetic */ Setting<Boolean> timeChange;
    public /* synthetic */ Setting<Boolean> pumpkin;
    public /* synthetic */ Setting<Boolean> blocks;
    public /* synthetic */ Setting<Boolean> items;
    public /* synthetic */ Setting<Boolean> bats;
    public /* synthetic */ Setting<Boolean> portal;
    public /* synthetic */ Setting<Boolean> totemPops;
    public /* synthetic */ Setting<Boolean> pigmen;
    public /* synthetic */ Setting<Integer> time;
    public /* synthetic */ Setting<Boolean> nausea;
    public /* synthetic */ Setting<Boolean> explosions;
    public /* synthetic */ Setting<Boolean> hurtcam;
    public /* synthetic */ Setting<Float> scale;

    @SubscribeEvent
    public void onPlaySound(PlaySoundAtEntityEvent playSoundAtEntityEvent) {
        if (this.bats.getValue().booleanValue() && playSoundAtEntityEvent.getSound().equals((Object)SoundEvents.ENTITY_BAT_AMBIENT) || playSoundAtEntityEvent.getSound().equals((Object)SoundEvents.ENTITY_BAT_DEATH) || playSoundAtEntityEvent.getSound().equals((Object)SoundEvents.ENTITY_BAT_HURT) || playSoundAtEntityEvent.getSound().equals((Object)SoundEvents.ENTITY_BAT_LOOP) || playSoundAtEntityEvent.getSound().equals((Object)SoundEvents.ENTITY_BAT_TAKEOFF)) {
            playSoundAtEntityEvent.setVolume(0.0f);
            playSoundAtEntityEvent.setPitch(0.0f);
            playSoundAtEntityEvent.setCanceled(true);
        }
    }

    @Override
    public void onUpdate() {
        if (this.items.getValue().booleanValue()) {
            NoRender.mc.world.loadedEntityList.stream().filter(EntityItem.class::isInstance).map(EntityItem.class::cast).forEach(Entity::setDead);
        }
        if (this.noWeather.getValue().booleanValue() && NoRender.mc.world.isRaining()) {
            NoRender.mc.world.setRainStrength(0.0f);
        }
        if (this.timeChange.getValue().booleanValue()) {
            NoRender.mc.world.setWorldTime((long)this.time.getValue().intValue());
        }
    }

    public static NoRender getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NoRender();
        }
        return INSTANCE;
    }

    public void doVoidFogParticles(int n, int n2, int n3) {
        Random random = new Random();
        ItemStack itemStack = NoRender.mc.player.getHeldItemMainhand();
        boolean bl = this.barriers.getValue() == false || NoRender.mc.playerController.getCurrentGameType() == GameType.CREATIVE && !itemStack.func_190926_b() && itemStack.getItem() == Item.getItemFromBlock((Block)Blocks.BARRIER);
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        for (int i = 0; i < 667; ++i) {
            this.showBarrierParticles(n, n2, n3, 16, random, bl, mutableBlockPos);
            this.showBarrierParticles(n, n2, n3, 32, random, bl, mutableBlockPos);
        }
    }

    @SubscribeEvent
    public void onRenderLiving(RenderLivingEvent.Pre<?> pre) {
        if (this.bats.getValue().booleanValue() && pre.getEntity() instanceof EntityBat) {
            pre.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onRenderPost(RenderGameOverlayEvent.Post post) {
        block7: {
            block8: {
                if (post.getType() != RenderGameOverlayEvent.ElementType.BOSSINFO || this.boss.getValue() == Boss.NONE) break block7;
                if (this.boss.getValue() != Boss.MINIMIZE) break block8;
                Map map2 = NoRender.mc.ingameGUI.getBossOverlay().mapBossInfos;
                if (map2 == null) {
                    return;
                }
                ScaledResolution scaledResolution = new ScaledResolution(mc);
                int n = scaledResolution.getScaledWidth();
                int n2 = 12;
                for (Map.Entry entry : map2.entrySet()) {
                    BossInfoClient bossInfoClient = (BossInfoClient)entry.getValue();
                    String string = bossInfoClient.getName().getFormattedText();
                    int n3 = (int)((float)n / this.scale.getValue().floatValue() / 2.0f - 91.0f);
                    GL11.glScaled((double)this.scale.getValue().floatValue(), (double)this.scale.getValue().floatValue(), (double)1.0);
                    if (!post.isCanceled()) {
                        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                        mc.getTextureManager().bindTexture(GuiBossOverlay.GUI_BARS_TEXTURES);
                        NoRender.mc.ingameGUI.getBossOverlay().render(n3, n2, (BossInfo)bossInfoClient);
                        NoRender.mc.fontRendererObj.drawStringWithShadow(string, (float)n / this.scale.getValue().floatValue() / 2.0f - (float)(NoRender.mc.fontRendererObj.getStringWidth(string) / 2), (float)(n2 - 9), 0xFFFFFF);
                    }
                    GL11.glScaled((double)(1.0 / (double)this.scale.getValue().floatValue()), (double)(1.0 / (double)this.scale.getValue().floatValue()), (double)1.0);
                    n2 += 10 + NoRender.mc.fontRendererObj.FONT_HEIGHT;
                }
                break block7;
            }
            if (this.boss.getValue() != Boss.STACK) break block7;
            Map map3 = NoRender.mc.ingameGUI.getBossOverlay().mapBossInfos;
            HashMap hashMap = new HashMap();
            for (Map.Entry entry : map3.entrySet()) {
                Pair pair;
                String string = ((BossInfoClient)entry.getValue()).getName().getFormattedText();
                if (hashMap.containsKey(string)) {
                    pair = (Pair)hashMap.get(string);
                    pair = new Pair(pair.getKey(), pair.getValue() + 1);
                    hashMap.put(string, pair);
                    continue;
                }
                pair = new Pair(entry.getValue(), 1);
                hashMap.put(string, pair);
            }
            ScaledResolution scaledResolution = new ScaledResolution(mc);
            int n = scaledResolution.getScaledWidth();
            int n4 = 12;
            for (Map.Entry entry : hashMap.entrySet()) {
                String string = (String)entry.getKey();
                BossInfoClient bossInfoClient = (BossInfoClient)((Pair)entry.getValue()).getKey();
                int n5 = (Integer)((Pair)entry.getValue()).getValue();
                string = String.valueOf(new StringBuilder().append(string).append(" x").append(n5));
                int n6 = (int)((float)n / this.scale.getValue().floatValue() / 2.0f - 91.0f);
                GL11.glScaled((double)this.scale.getValue().floatValue(), (double)this.scale.getValue().floatValue(), (double)1.0);
                if (!post.isCanceled()) {
                    GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    mc.getTextureManager().bindTexture(GuiBossOverlay.GUI_BARS_TEXTURES);
                    NoRender.mc.ingameGUI.getBossOverlay().render(n6, n4, (BossInfo)bossInfoClient);
                    NoRender.mc.fontRendererObj.drawStringWithShadow(string, (float)n / this.scale.getValue().floatValue() / 2.0f - (float)(NoRender.mc.fontRendererObj.getStringWidth(string) / 2), (float)(n4 - 9), 0xFFFFFF);
                }
                GL11.glScaled((double)(1.0 / (double)this.scale.getValue().floatValue()), (double)(1.0 / (double)this.scale.getValue().floatValue()), (double)1.0);
                n4 += 10 + NoRender.mc.fontRendererObj.FONT_HEIGHT;
            }
        }
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketTimeUpdate & this.timeChange.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketExplosion & this.explosions.getValue()) {
            receive.setCanceled(true);
        }
    }

    public void showBarrierParticles(int n, int n2, int n3, int n4, Random random, boolean bl, BlockPos.MutableBlockPos mutableBlockPos) {
        int n5 = n + NoRender.mc.world.rand.nextInt(n4) - NoRender.mc.world.rand.nextInt(n4);
        int n6 = n2 + NoRender.mc.world.rand.nextInt(n4) - NoRender.mc.world.rand.nextInt(n4);
        int n7 = n3 + NoRender.mc.world.rand.nextInt(n4) - NoRender.mc.world.rand.nextInt(n4);
        mutableBlockPos.setPos(n5, n6, n7);
        IBlockState iBlockState = NoRender.mc.world.getBlockState((BlockPos)mutableBlockPos);
        iBlockState.getBlock().randomDisplayTick(iBlockState, (World)NoRender.mc.world, (BlockPos)mutableBlockPos, random);
        if (!bl && iBlockState.getBlock() == Blocks.BARRIER) {
            NoRender.mc.world.spawnParticle(EnumParticleTypes.BARRIER, (double)((float)n5 + 0.5f), (double)((float)n6 + 0.5f), (double)((float)n7 + 0.5f), 0.0, 0.0, 0.0, new int[0]);
        }
    }

    private void setInstance() {
        INSTANCE = this;
    }

    public NoRender() {
        super("NoRender", "Allows you to stop rendering stuff", Module.Category.RENDER, true, false, false);
        this.fire = this.register(new Setting<Boolean>("Fire", Boolean.FALSE, "Removes the portal overlay."));
        this.portal = this.register(new Setting<Boolean>("Portal", Boolean.FALSE, "Removes the portal overlay."));
        this.pumpkin = this.register(new Setting<Boolean>("Pumpkin", Boolean.FALSE, "Removes the pumpkin overlay."));
        this.totemPops = this.register(new Setting<Boolean>("TotemPop", Boolean.FALSE, "Removes the Totem overlay."));
        this.items = this.register(new Setting<Boolean>("Items", Boolean.FALSE, "Removes items on the ground."));
        this.nausea = this.register(new Setting<Boolean>("Nausea", Boolean.FALSE, "Removes Portal Nausea."));
        this.hurtcam = this.register(new Setting<Boolean>("HurtCam", Boolean.FALSE, "Removes shaking after taking damage."));
        this.explosions = this.register(new Setting<Boolean>("Explosions", Boolean.FALSE, "Removes crystal explosions."));
        this.noWeather = this.register(new Setting<Boolean>("Weather", Boolean.FALSE, "AntiWeather"));
        this.boss = this.register(new Setting<Boss>("BossBars", Boss.NONE, "Modifies the bossbars."));
        this.scale = this.register(new Setting<Object>("Scale", Float.valueOf(0.5f), Float.valueOf(0.5f), Float.valueOf(1.0f), object -> this.boss.getValue() == Boss.MINIMIZE || this.boss.getValue() == Boss.STACK, "Scale of the bars."));
        this.bats = this.register(new Setting<Boolean>("Bats", Boolean.FALSE, "Removes bats."));
        this.noArmor = this.register(new Setting<NoArmor>("NoArmor", NoArmor.NONE, "Doesnt Render Armor on players."));
        this.barriers = this.register(new Setting<Boolean>("Barriers", Boolean.FALSE, "Barriers"));
        this.blocks = this.register(new Setting<Boolean>("Blocks", Boolean.FALSE, "Blocks"));
        this.advancements = this.register(new Setting<Boolean>("Advancements", false));
        this.pigmen = this.register(new Setting<Boolean>("Pigmen", false));
        this.timeChange = this.register(new Setting<Boolean>("TimeChange", false));
        this.time = this.register(new Setting<Object>("Time", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(23000), object -> this.timeChange.getValue()));
        this.setInstance();
    }

    static {
        INSTANCE = new NoRender();
        INSTANCE = new NoRender();
    }

    @SubscribeEvent
    public void onRenderPre(RenderGameOverlayEvent.Pre pre) {
        if (pre.getType() == RenderGameOverlayEvent.ElementType.BOSSINFO && this.boss.getValue() != Boss.NONE) {
            pre.setCanceled(true);
        }
    }

    public static enum Skylight {
        NONE,
        WORLD,
        ENTITY,
        ALL;

    }

    public static enum Boss {
        NONE,
        REMOVE,
        STACK,
        MINIMIZE;

    }

    public static class Pair<T, S> {
        private /* synthetic */ S value;
        private /* synthetic */ T key;

        public S getValue() {
            return this.value;
        }

        public void setValue(S s) {
            this.value = s;
        }

        public void setKey(T t) {
            this.key = t;
        }

        public Pair(T t, S s) {
            this.key = t;
            this.value = s;
        }

        public T getKey() {
            return this.key;
        }
    }

    public static enum NoArmor {
        NONE,
        ALL,
        HELMET;

    }
}

