//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos$MutableBlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.biome.Biome
 */
package me.snow.aclient.module.modules.render;

import java.util.Random;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.mixin.mixins.accessors.IEntityRenderer;
import me.snow.aclient.module.Module;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;

public class Weather
extends Module {
    public /* synthetic */ Setting<Boolean> snow;
    private static final /* synthetic */ ResourceLocation RAIN_TEXTURES;
    private final /* synthetic */ Setting<Integer> height;
    private static final /* synthetic */ ResourceLocation SNOW_TEXTURES;
    private static final /* synthetic */ Random RANDOM;
    private final /* synthetic */ Setting<Float> strength;

    public Weather() {
        super("Weather", "", Module.Category.RENDER, false, false, false);
        this.snow = this.register(new Setting<Boolean>("Snow", true));
        this.height = this.register(new Setting<Integer>("Height", 80, 0, 255));
        this.strength = this.register(new Setting<Float>("Strength", Float.valueOf(0.8f), Float.valueOf(0.1f), Float.valueOf(2.0f)));
    }

    static {
        RANDOM = new Random();
        RAIN_TEXTURES = new ResourceLocation("textures/environment/rain.png");
        SNOW_TEXTURES = new ResourceLocation("textures/environment/snow.png");
    }

    public void render(float f) {
        float f2 = this.strength.getValue().floatValue();
        EntityRenderer entityRenderer = Weather.mc.entityRenderer;
        entityRenderer.enableLightmap();
        Entity entity = mc.getRenderViewEntity();
        if (entity == null) {
            return;
        }
        WorldClient worldClient = Weather.mc.world;
        int n = MathHelper.floor((double)entity.posX);
        int n2 = MathHelper.floor((double)entity.posY);
        int n3 = MathHelper.floor((double)entity.posZ);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.disableCull();
        GlStateManager.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.alphaFunc((int)516, (float)0.1f);
        double d = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f;
        double d2 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f;
        double d3 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f;
        int n4 = MathHelper.floor((double)d2);
        int n5 = 5;
        if (Weather.mc.gameSettings.fancyGraphics) {
            n5 = 10;
        }
        int n6 = -1;
        float f3 = (float)((IEntityRenderer)entityRenderer).getRendererUpdateCount() + f;
        bufferBuilder.setTranslation(-d, -d2, -d3);
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        for (int i = n3 - n5; i <= n3 + n5; ++i) {
            for (int j = n - n5; j <= n + n5; ++j) {
                double d4;
                double d5;
                double d6;
                int n7 = (i - n3 + 16) * 32 + j - n + 16;
                double d7 = (double)((IEntityRenderer)entityRenderer).getRainXCoords()[n7] * 0.5;
                double d8 = (double)((IEntityRenderer)entityRenderer).getRainYCoords()[n7] * 0.5;
                mutableBlockPos.setPos(j, 0, i);
                Biome biome = worldClient.getBiome((BlockPos)mutableBlockPos);
                int n8 = this.height.getValue();
                int n9 = n2 - n5;
                int n10 = n2 + n5;
                if (n9 < n8) {
                    n9 = n8;
                }
                if (n10 < n8) {
                    n10 = n8;
                }
                int n11 = n8;
                if (n8 < n4) {
                    n11 = n4;
                }
                if (n9 == n10) continue;
                RANDOM.setSeed(j * j * 3121 + j * 45238971 ^ i * i * 418711 + i * 13761);
                mutableBlockPos.setPos(j, n9, i);
                float f4 = biome.getFloatTemperature((BlockPos)mutableBlockPos);
                if (!this.snow.getValue().booleanValue()) {
                    if (n6 != 0) {
                        if (n6 >= 0) {
                            tessellator.draw();
                        }
                        n6 = 0;
                        mc.getTextureManager().bindTexture(RAIN_TEXTURES);
                        bufferBuilder.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
                    }
                    d6 = -((double)(((IEntityRenderer)entityRenderer).getRendererUpdateCount() + j * j * 3121 + j * 45238971 + i * i * 418711 + i * 13761 & 0x1F) + (double)f) / 32.0 * (3.0 + RANDOM.nextDouble());
                    d5 = (double)((float)j + 0.5f) - entity.posX;
                    d4 = (double)((float)i + 0.5f) - entity.posZ;
                    float f5 = MathHelper.sqrt((double)(d5 * d5 + d4 * d4)) / (float)n5;
                    float f6 = ((1.0f - f5 * f5) * 0.5f + 0.5f) * f2;
                    mutableBlockPos.setPos(j, n11, i);
                    int n12 = worldClient.getCombinedLight((BlockPos)mutableBlockPos, 0);
                    int n13 = n12 >> 16 & 0xFFFF;
                    int n14 = n12 & 0xFFFF;
                    bufferBuilder.pos((double)j - d7 + 0.5, (double)n10, (double)i - d8 + 0.5).tex(0.0, (double)n9 * 0.25 + d6).color(1.0f, 1.0f, 1.0f, f6).lightmap(n13, n14).endVertex();
                    bufferBuilder.pos((double)j + d7 + 0.5, (double)n10, (double)i + d8 + 0.5).tex(1.0, (double)n9 * 0.25 + d6).color(1.0f, 1.0f, 1.0f, f6).lightmap(n13, n14).endVertex();
                    bufferBuilder.pos((double)j + d7 + 0.5, (double)n9, (double)i + d8 + 0.5).tex(1.0, (double)n10 * 0.25 + d6).color(1.0f, 1.0f, 1.0f, f6).lightmap(n13, n14).endVertex();
                    bufferBuilder.pos((double)j - d7 + 0.5, (double)n9, (double)i - d8 + 0.5).tex(0.0, (double)n10 * 0.25 + d6).color(1.0f, 1.0f, 1.0f, f6).lightmap(n13, n14).endVertex();
                    continue;
                }
                if (n6 != 1) {
                    if (n6 >= 0) {
                        tessellator.draw();
                    }
                    n6 = 1;
                    mc.getTextureManager().bindTexture(SNOW_TEXTURES);
                    bufferBuilder.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
                }
                d6 = -((float)(((IEntityRenderer)entityRenderer).getRendererUpdateCount() & 0x1FF) + f) / 512.0f;
                d5 = RANDOM.nextDouble() + (double)f3 * 0.01 * (double)((float)RANDOM.nextGaussian());
                d4 = RANDOM.nextDouble() + (double)(f3 * (float)RANDOM.nextGaussian()) * 0.001;
                double d9 = (double)((float)j + 0.5f) - entity.posX;
                double d10 = (double)((float)i + 0.5f) - entity.posZ;
                float f7 = MathHelper.sqrt((double)(d9 * d9 + d10 * d10)) / (float)n5;
                float f8 = ((1.0f - f7 * f7) * 0.3f + 0.5f) * f2;
                mutableBlockPos.setPos(j, n11, i);
                int n15 = (worldClient.getCombinedLight((BlockPos)mutableBlockPos, 0) * 3 + 0xF000F0) / 4;
                int n16 = n15 >> 16 & 0xFFFF;
                int n17 = n15 & 0xFFFF;
                bufferBuilder.pos((double)j - d7 + 0.5, (double)n10, (double)i - d8 + 0.5).tex(0.0 + d5, (double)n9 * 0.25 + d6 + d4).color(1.0f, 1.0f, 1.0f, f8).lightmap(n16, n17).endVertex();
                bufferBuilder.pos((double)j + d7 + 0.5, (double)n10, (double)i + d8 + 0.5).tex(1.0 + d5, (double)n9 * 0.25 + d6 + d4).color(1.0f, 1.0f, 1.0f, f8).lightmap(n16, n17).endVertex();
                bufferBuilder.pos((double)j + d7 + 0.5, (double)n9, (double)i + d8 + 0.5).tex(1.0 + d5, (double)n10 * 0.25 + d6 + d4).color(1.0f, 1.0f, 1.0f, f8).lightmap(n16, n17).endVertex();
                bufferBuilder.pos((double)j - d7 + 0.5, (double)n9, (double)i - d8 + 0.5).tex(0.0 + d5, (double)n10 * 0.25 + d6 + d4).color(1.0f, 1.0f, 1.0f, f8).lightmap(n16, n17).endVertex();
            }
        }
        if (n6 >= 0) {
            tessellator.draw();
        }
        bufferBuilder.setTranslation(0.0, 0.0, 0.0);
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.alphaFunc((int)516, (float)0.1f);
        entityRenderer.disableLightmap();
    }
}

