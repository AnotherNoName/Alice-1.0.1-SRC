//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.RenderItem
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.entity.RenderEntityItem
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.client.ForgeHooksClient
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.mixin.mixins;

import java.util.Random;
import me.snow.aclient.mixin.mixins.MixinRenderer;
import me.snow.aclient.module.modules.render.ItemPhysics;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={RenderEntityItem.class})
public abstract class MixinRenderEntityItem
extends MixinRenderer<EntityItem> {
    @Shadow
    @Final
    private RenderItem itemRenderer;
    @Shadow
    @Final
    private Random random;
    private Minecraft mc = Minecraft.getMinecraft();
    private long tick;
    private double rotation;

    @Shadow
    public abstract int getModelCount(ItemStack var1);

    @Shadow
    public abstract boolean shouldSpreadItems();

    @Shadow
    public abstract boolean shouldBob();

    @Shadow
    protected abstract ResourceLocation getEntityTexture(EntityItem var1);

    private double formPositiv(float f) {
        return f > 0.0f ? (double)f : (double)(-f);
    }

    @Overwrite
    private int transformModelCount(EntityItem entityItem, double d, double d2, double d3, float f, IBakedModel iBakedModel) {
        if (ItemPhysics.INSTANCE.isEnabled()) {
            ItemStack itemStack = entityItem.getEntityItem();
            Item item = itemStack.getItem();
            if (item == null) {
                return 0;
            }
            boolean bl = iBakedModel.isAmbientOcclusion();
            int n = this.getModelCount(itemStack);
            float f2 = 0.0f;
            GlStateManager.translate((float)((float)d), (float)((float)d2 + 0.0f + 0.1f), (float)((float)d3));
            float f3 = 0.0f;
            if (bl || this.mc.getRenderManager().options != null && this.mc.getRenderManager().options.fancyGraphics) {
                GlStateManager.rotate((float)f3, (float)0.0f, (float)1.0f, (float)0.0f);
            }
            if (!bl) {
                f3 = -0.0f * (float)(n - 1) * 0.5f;
                float f4 = -0.0f * (float)(n - 1) * 0.5f;
                float f5 = -0.046875f * (float)(n - 1) * 0.5f;
                GlStateManager.translate((float)f3, (float)f4, (float)f5);
            }
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            return n;
        }
        ItemStack itemStack = entityItem.getEntityItem();
        Item item = itemStack.getItem();
        if (item == null) {
            return 0;
        }
        boolean bl = iBakedModel.isGui3d();
        int n = this.getModelCount(itemStack);
        float f6 = this.shouldBob() ? MathHelper.sin((float)(((float)entityItem.getAge() + f) / 10.0f + entityItem.hoverStart)) * 0.1f + 0.1f : 0.0f;
        float f7 = iBakedModel.getItemCameraTransforms().getTransform((ItemCameraTransforms.TransformType)ItemCameraTransforms.TransformType.GROUND).scale.y;
        GlStateManager.translate((float)((float)d), (float)((float)d2 + f6 + 0.25f * f7), (float)((float)d3));
        if (bl || this.renderManager.options != null) {
            float f8 = (((float)entityItem.getAge() + f) / 20.0f + entityItem.hoverStart) * 57.295776f;
            GlStateManager.rotate((float)f8, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        return n;
    }

    @Overwrite
    public void doRender(EntityItem entityItem, double d, double d2, double d3, float f, float f2) {
        float f3;
        if (ItemPhysics.INSTANCE.isEnabled()) {
            EntityItem entityItem2;
            ItemStack itemStack;
            this.rotation = (double)(System.nanoTime() - this.tick) / 3000000.0 * 1.0;
            if (!this.mc.inGameHasFocus) {
                this.rotation = 0.0;
            }
            if ((itemStack = (entityItem2 = entityItem).getEntityItem()).getItem() != null) {
                this.random.setSeed(187L);
                boolean bl = false;
                if (TextureMap.LOCATION_BLOCKS_TEXTURE != null) {
                    this.mc.getRenderManager().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                    this.mc.getRenderManager().renderEngine.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
                    bl = true;
                }
                GlStateManager.enableRescaleNormal();
                GlStateManager.alphaFunc((int)516, (float)0.1f);
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
                GlStateManager.pushMatrix();
                IBakedModel iBakedModel = this.itemRenderer.getItemModelMesher().getItemModel(itemStack);
                int n = this.transformModelCount(entityItem2, d, d2, d3, f2, iBakedModel);
                BlockPos blockPos = new BlockPos((Entity)entityItem2);
                if (entityItem2.rotationPitch > 360.0f) {
                    entityItem2.rotationPitch = 0.0f;
                }
                if (!(entityItem2 == null || Double.isNaN(entityItem2.posX) || Double.isNaN(entityItem2.posY) || Double.isNaN(entityItem2.posZ) || entityItem2.world == null)) {
                    if (entityItem2.onGround) {
                        if (entityItem2.rotationPitch != 0.0f && entityItem2.rotationPitch != 90.0f && entityItem2.rotationPitch != 180.0f && entityItem2.rotationPitch != 270.0f) {
                            double d4 = this.formPositiv(entityItem2.rotationPitch);
                            double d5 = this.formPositiv(entityItem2.rotationPitch - 90.0f);
                            double d6 = this.formPositiv(entityItem2.rotationPitch - 180.0f);
                            double d7 = this.formPositiv(entityItem2.rotationPitch - 270.0f);
                            if (d4 <= d5 && d4 <= d6 && d4 <= d7) {
                                entityItem2.rotationPitch = entityItem2.rotationPitch < 0.0f ? (entityItem2.rotationPitch += (float)this.rotation) : (entityItem2.rotationPitch -= (float)this.rotation);
                            }
                            if (d5 < d4 && d5 <= d6 && d5 <= d7) {
                                entityItem2.rotationPitch = entityItem2.rotationPitch - 90.0f < 0.0f ? (entityItem2.rotationPitch += (float)this.rotation) : (entityItem2.rotationPitch -= (float)this.rotation);
                            }
                            if (d6 < d5 && d6 < d4 && d6 <= d7) {
                                entityItem2.rotationPitch = entityItem2.rotationPitch - 180.0f < 0.0f ? (entityItem2.rotationPitch += (float)this.rotation) : (entityItem2.rotationPitch -= (float)this.rotation);
                            }
                            if (d7 < d5 && d7 < d6 && d7 < d4) {
                                entityItem2.rotationPitch = entityItem2.rotationPitch - 270.0f < 0.0f ? (entityItem2.rotationPitch += (float)this.rotation) : (entityItem2.rotationPitch -= (float)this.rotation);
                            }
                        }
                    } else {
                        BlockPos blockPos2 = new BlockPos((Entity)entityItem2);
                        blockPos2.add(0, 1, 0);
                        Material material2 = entityItem2.world.getBlockState(blockPos2).getMaterial();
                        Material material3 = entityItem2.world.getBlockState(blockPos).getMaterial();
                        boolean bl2 = entityItem2.isInsideOfMaterial(Material.WATER);
                        boolean bl3 = entityItem2.isInWater();
                        entityItem2.rotationPitch = bl2 | material2 == Material.WATER | material3 == Material.WATER | bl3 ? (entityItem2.rotationPitch += (float)(this.rotation / 4.0)) : (entityItem2.rotationPitch += (float)(this.rotation * 2.0));
                    }
                }
                GL11.glRotatef((float)entityItem2.rotationYaw, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(entityItem2.rotationPitch + 90.0f), (float)1.0f, (float)0.0f, (float)0.0f);
                for (int i = 0; i < n; ++i) {
                    if (iBakedModel.isAmbientOcclusion()) {
                        GlStateManager.pushMatrix();
                        GlStateManager.scale((float)ItemPhysics.INSTANCE.Scaling.getValue().floatValue(), (float)ItemPhysics.INSTANCE.Scaling.getValue().floatValue(), (float)ItemPhysics.INSTANCE.Scaling.getValue().floatValue());
                        this.itemRenderer.renderItem(itemStack, iBakedModel);
                        GlStateManager.popMatrix();
                        continue;
                    }
                    GlStateManager.pushMatrix();
                    if (i > 0 && this.shouldSpreadItems()) {
                        GlStateManager.translate((float)0.0f, (float)0.0f, (float)(0.046875f * (float)i));
                    }
                    this.itemRenderer.renderItem(itemStack, iBakedModel);
                    if (!this.shouldSpreadItems()) {
                        GlStateManager.translate((float)0.0f, (float)0.0f, (float)0.046875f);
                    }
                    GlStateManager.popMatrix();
                }
                GlStateManager.popMatrix();
                GlStateManager.disableRescaleNormal();
                GlStateManager.disableBlend();
                this.mc.getRenderManager().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                if (bl) {
                    this.mc.getRenderManager().renderEngine.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
                }
            }
            return;
        }
        ItemStack itemStack = entityItem.getEntityItem();
        int n = itemStack.func_190926_b() ? 187 : Item.getIdFromItem((Item)itemStack.getItem()) + itemStack.getMetadata();
        this.random.setSeed(n);
        boolean bl = false;
        if (this.bindEntityTexture(entityItem)) {
            this.renderManager.renderEngine.getTexture(this.getEntityTexture(entityItem)).setBlurMipmap(false, false);
            bl = true;
        }
        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc((int)516, (float)0.1f);
        GlStateManager.enableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.pushMatrix();
        IBakedModel iBakedModel = this.itemRenderer.getItemModelWithOverrides(itemStack, entityItem.world, (EntityLivingBase)null);
        int n2 = this.transformModelCount(entityItem, d, d2, d3, f2, iBakedModel);
        boolean bl4 = iBakedModel.isGui3d();
        if (!bl4) {
            float f4 = -0.0f * (float)(n2 - 1) * 0.5f;
            float f5 = -0.0f * (float)(n2 - 1) * 0.5f;
            f3 = -0.09375f * (float)(n2 - 1) * 0.5f;
            GlStateManager.translate((float)f4, (float)f5, (float)f3);
        }
        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode((int)this.getTeamColor(entityItem));
        }
        for (int i = 0; i < n2; ++i) {
            IBakedModel iBakedModel2;
            if (bl4) {
                GlStateManager.pushMatrix();
                if (i > 0) {
                    float f6 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    f3 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    float f7 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    GlStateManager.translate((float)(this.shouldSpreadItems() ? f6 : 0.0f), (float)(this.shouldSpreadItems() ? f3 : 0.0f), (float)f7);
                }
                iBakedModel2 = ForgeHooksClient.handleCameraTransforms((IBakedModel)iBakedModel, (ItemCameraTransforms.TransformType)ItemCameraTransforms.TransformType.GROUND, (boolean)false);
                this.itemRenderer.renderItem(itemStack, iBakedModel2);
                GlStateManager.popMatrix();
                continue;
            }
            GlStateManager.pushMatrix();
            if (i > 0) {
                float f8 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.15f * 0.5f;
                f3 = (this.random.nextFloat() * 2.0f - 1.0f) * 0.15f * 0.5f;
                GlStateManager.translate((float)f8, (float)f3, (float)0.0f);
            }
            iBakedModel2 = ForgeHooksClient.handleCameraTransforms((IBakedModel)iBakedModel, (ItemCameraTransforms.TransformType)ItemCameraTransforms.TransformType.GROUND, (boolean)false);
            this.itemRenderer.renderItem(itemStack, iBakedModel2);
            GlStateManager.popMatrix();
            GlStateManager.translate((float)0.0f, (float)0.0f, (float)0.09375f);
        }
        if (this.renderOutlines) {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        this.bindEntityTexture(entityItem);
        if (bl) {
            this.renderManager.renderEngine.getTexture(this.getEntityTexture(entityItem)).restoreLastBlurMipmap();
        }
    }
}

