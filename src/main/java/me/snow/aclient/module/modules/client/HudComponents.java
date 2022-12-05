//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.DestroyBlockProgress
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.module.modules.client;

import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.Render2DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.RenderUtil;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class HudComponents
extends Module {
    public /* synthetic */ Setting<Boolean> inventory;
    public /* synthetic */ Setting<Boolean> renderXCarry;
    public /* synthetic */ Setting<Integer> invX;
    public /* synthetic */ Setting<Integer> playerViewerY;
    public /* synthetic */ Setting<Boolean> holeHud;
    public /* synthetic */ Setting<Integer> holeX;
    public /* synthetic */ Setting<Float> playerScale;
    public /* synthetic */ Setting<Integer> holeY;
    public /* synthetic */ Setting<Integer> invH;
    public /* synthetic */ Setting<Integer> compassY;
    private static final /* synthetic */ ResourceLocation box;
    public /* synthetic */ Setting<Compass> compass;
    public /* synthetic */ Setting<Integer> invY;
    public /* synthetic */ Setting<Integer> fineinvY;
    public /* synthetic */ Setting<Integer> compassX;
    private static final /* synthetic */ double HALF_PI = 1.5707963267948966;
    public /* synthetic */ Setting<Integer> playerViewerX;
    public /* synthetic */ Setting<Integer> fineinvX;
    public /* synthetic */ Setting<Boolean> invtexture;
    public /* synthetic */ Setting<Integer> scale;
    public /* synthetic */ Setting<Boolean> playerViewer;

    public void drawOverlay(float f, Entity entity, int n, int n2) {
        BlockPos blockPos;
        Block block;
        BlockPos blockPos2;
        Block block2;
        BlockPos blockPos3;
        Block block3;
        float f2 = 0.0f;
        int n3 = MathHelper.floor((double)((double)(entity.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3;
        switch (n3) {
            case 1: {
                f2 = 90.0f;
                break;
            }
            case 2: {
                f2 = -180.0f;
                break;
            }
            case 3: {
                f2 = -90.0f;
            }
        }
        BlockPos blockPos4 = this.traceToBlock(f, f2, entity);
        Block block4 = this.getBlock(blockPos4);
        if (block4 != null && block4 != Blocks.AIR) {
            int n4 = this.getBlockDamage(blockPos4);
            if (n4 != 0) {
                RenderUtil.drawRect(n + 16, n2, n + 32, n2 + 16, 0x60FF0000);
            }
            this.drawBlock(block4, n + 16, n2);
        }
        if ((block3 = this.getBlock(blockPos3 = this.traceToBlock(f, f2 - 180.0f, entity))) != null && block3 != Blocks.AIR) {
            int n5 = this.getBlockDamage(blockPos3);
            if (n5 != 0) {
                RenderUtil.drawRect(n + 16, n2 + 32, n + 32, n2 + 48, 0x60FF0000);
            }
            this.drawBlock(block3, n + 16, n2 + 32);
        }
        if ((block2 = this.getBlock(blockPos2 = this.traceToBlock(f, f2 + 90.0f, entity))) != null && block2 != Blocks.AIR) {
            int n6 = this.getBlockDamage(blockPos2);
            if (n6 != 0) {
                RenderUtil.drawRect(n + 32, n2 + 16, n + 48, n2 + 32, 0x60FF0000);
            }
            this.drawBlock(block2, n + 32, n2 + 16);
        }
        if ((block = this.getBlock(blockPos = this.traceToBlock(f, f2 - 90.0f, entity))) != null && block != Blocks.AIR) {
            int n7 = this.getBlockDamage(blockPos);
            if (n7 != 0) {
                RenderUtil.drawRect(n, n2 + 16, n + 16, n2 + 32, 0x60FF0000);
            }
            this.drawBlock(block, n, n2 + 16);
        }
    }

    public static void drawCompleteImage(int n, int n2, int n3, int n4) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)n, (float)n2, (float)0.0f);
        GL11.glBegin((int)7);
        GL11.glTexCoord2f((float)0.0f, (float)0.0f);
        GL11.glVertex3f((float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glTexCoord2f((float)0.0f, (float)1.0f);
        GL11.glVertex3f((float)0.0f, (float)n4, (float)0.0f);
        GL11.glTexCoord2f((float)1.0f, (float)1.0f);
        GL11.glVertex3f((float)n3, (float)n4, (float)0.0f);
        GL11.glTexCoord2f((float)1.0f, (float)0.0f);
        GL11.glVertex3f((float)n3, (float)0.0f, (float)0.0f);
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    private BlockPos traceToBlock(float f, float f2, Entity entity) {
        Vec3d vec3d = EntityUtil.interpolateEntity(entity, f);
        Vec3d vec3d2 = MathUtil.direction(f2);
        return new BlockPos(vec3d.xCoord + vec3d2.xCoord, vec3d.yCoord, vec3d.zCoord + vec3d2.zCoord);
    }

    private Block getBlock(BlockPos blockPos) {
        Block block = HudComponents.mc.world.getBlockState(blockPos).getBlock();
        if (block == Blocks.BEDROCK || block == Blocks.OBSIDIAN) {
            return block;
        }
        return Blocks.AIR;
    }

    private int getBlockDamage(BlockPos blockPos) {
        for (DestroyBlockProgress destroyBlockProgress : HudComponents.mc.renderGlobal.damagedBlocks.values()) {
            if (destroyBlockProgress.getPosition().getX() != blockPos.getX() || destroyBlockProgress.getPosition().getY() != blockPos.getY() || destroyBlockProgress.getPosition().getZ() != blockPos.getZ()) continue;
            return destroyBlockProgress.getPartialBlockDamage();
        }
        return 0;
    }

    public HudComponents() {
        super("HudComponents", "HudComponents", Module.Category.CLIENT, false, false, true);
        this.inventory = this.register(new Setting<Boolean>("Inventory", false));
        this.invtexture = this.register(new Setting<Object>("InventoryBackGround", Boolean.valueOf(false), object -> this.inventory.getValue()));
        this.invX = this.register(new Setting<Object>("InvX", Integer.valueOf(564), Integer.valueOf(0), Integer.valueOf(1000), object -> this.inventory.getValue()));
        this.invY = this.register(new Setting<Object>("InvY", Integer.valueOf(467), Integer.valueOf(0), Integer.valueOf(1000), object -> this.inventory.getValue()));
        this.fineinvX = this.register(new Setting<Object>("InvFineX", Integer.valueOf(0), object -> this.inventory.getValue()));
        this.fineinvY = this.register(new Setting<Object>("InvFineY", Integer.valueOf(0), object -> this.inventory.getValue()));
        this.renderXCarry = this.register(new Setting<Object>("RenderXCarry", Boolean.valueOf(false), object -> this.inventory.getValue()));
        this.invH = this.register(new Setting<Object>("InvH", Integer.valueOf(3), object -> this.inventory.getValue()));
        this.holeHud = this.register(new Setting<Boolean>("HoleHUD", false));
        this.holeX = this.register(new Setting<Object>("HoleX", Integer.valueOf(279), Integer.valueOf(0), Integer.valueOf(1000), object -> this.holeHud.getValue()));
        this.holeY = this.register(new Setting<Object>("HoleY", Integer.valueOf(485), Integer.valueOf(0), Integer.valueOf(1000), object -> this.holeHud.getValue()));
        this.compass = this.register(new Setting<Compass>("Compass", Compass.NONE));
        this.compassX = this.register(new Setting<Object>("CompX", Integer.valueOf(472), Integer.valueOf(0), Integer.valueOf(1000), object -> this.compass.getValue() != Compass.NONE));
        this.compassY = this.register(new Setting<Object>("CompY", Integer.valueOf(424), Integer.valueOf(0), Integer.valueOf(1000), object -> this.compass.getValue() != Compass.NONE));
        this.scale = this.register(new Setting<Object>("Scale", Integer.valueOf(3), Integer.valueOf(0), Integer.valueOf(10), object -> this.compass.getValue() != Compass.NONE));
        this.playerViewer = this.register(new Setting<Boolean>("PlayerViewer", false));
        this.playerViewerX = this.register(new Setting<Object>("PlayerX", Integer.valueOf(752), Integer.valueOf(0), Integer.valueOf(1000), object -> this.playerViewer.getValue()));
        this.playerViewerY = this.register(new Setting<Object>("PlayerY", Integer.valueOf(497), Integer.valueOf(0), Integer.valueOf(1000), object -> this.playerViewer.getValue()));
        this.playerScale = this.register(new Setting<Object>("PlayerScale", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(2.0f), object -> this.playerViewer.getValue()));
    }

    private double getX(double d) {
        return Math.sin(d) * (double)(this.scale.getValue() * 10);
    }

    private void itemrender(NonNullList<ItemStack> nonNullList, int n, int n2) {
        int n3;
        int n4;
        for (n4 = 0; n4 < nonNullList.size() - 9; ++n4) {
            n3 = n + n4 % 9 * 18 + 8;
            int n5 = n2 + n4 / 9 * 18 + 18;
            ItemStack itemStack = (ItemStack)nonNullList.get(n4 + 9);
            HudComponents.preitemrender();
            HudComponents.mc.getRenderItem().zLevel = 501.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(itemStack, n3, n5);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HudComponents.mc.fontRendererObj, itemStack, n3, n5, null);
            HudComponents.mc.getRenderItem().zLevel = 0.0f;
            HudComponents.postitemrender();
        }
        if (this.renderXCarry.getValue().booleanValue()) {
            for (n4 = 1; n4 < 5; ++n4) {
                n3 = n + (n4 + 4) % 9 * 18 + 8;
                ItemStack itemStack = ((Slot)HudComponents.mc.player.inventoryContainer.inventorySlots.get(n4)).getStack();
                if (itemStack == null || itemStack.field_190928_g) continue;
                HudComponents.preitemrender();
                HudComponents.mc.getRenderItem().zLevel = 501.0f;
                RenderUtil.itemRender.renderItemAndEffectIntoGUI(itemStack, n3, n2 + 1);
                RenderUtil.itemRender.renderItemOverlayIntoGUI(HudComponents.mc.fontRendererObj, itemStack, n3, n2 + 1, null);
                HudComponents.mc.getRenderItem().zLevel = 0.0f;
                HudComponents.postitemrender();
            }
        }
    }

    public void drawPlayer() {
        EntityPlayerSP entityPlayerSP = HudComponents.mc.player;
        GlStateManager.pushMatrix();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel((int)7424);
        GlStateManager.enableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.rotate((float)0.0f, (float)0.0f, (float)5.0f, (float)0.0f);
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)(this.playerViewerX.getValue() + 25), (float)(this.playerViewerY.getValue() + 25), (float)50.0f);
        GlStateManager.scale((float)(-50.0f * this.playerScale.getValue().floatValue()), (float)(50.0f * this.playerScale.getValue().floatValue()), (float)(50.0f * this.playerScale.getValue().floatValue()));
        GlStateManager.rotate((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.rotate((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)(-((float)Math.atan((float)this.playerViewerY.getValue().intValue() / 40.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.translate((float)0.0f, (float)0.0f, (float)0.0f);
        RenderManager renderManager = mc.getRenderManager();
        renderManager.setPlayerViewY(180.0f);
        renderManager.setRenderShadow(false);
        try {
            renderManager.doRenderEntity((Entity)entityPlayerSP, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        }
        catch (Exception exception) {
            // empty catch block
        }
        renderManager.setRenderShadow(true);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
        GlStateManager.depthFunc((int)515);
        GlStateManager.resetColor();
        GlStateManager.disableDepth();
        GlStateManager.popMatrix();
    }

    public static EntityPlayer getClosestEnemy() {
        EntityPlayer entityPlayer = null;
        for (EntityPlayer entityPlayer2 : HudComponents.mc.world.playerEntities) {
            if (entityPlayer2 == HudComponents.mc.player || AliceMain.friendManager.isFriend(entityPlayer2)) continue;
            if (entityPlayer == null) {
                entityPlayer = entityPlayer2;
                continue;
            }
            if (!(HudComponents.mc.player.getDistanceSqToEntity((Entity)entityPlayer2) < HudComponents.mc.player.getDistanceSqToEntity((Entity)entityPlayer))) continue;
            entityPlayer = entityPlayer2;
        }
        return entityPlayer;
    }

    private static void postboxrender() {
        GlStateManager.disableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.popMatrix();
        GL11.glPopMatrix();
    }

    public void drawPlayer(EntityPlayer entityPlayer, int n, int n2) {
        EntityPlayer entityPlayer2 = entityPlayer;
        GlStateManager.pushMatrix();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel((int)7424);
        GlStateManager.enableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.rotate((float)0.0f, (float)0.0f, (float)5.0f, (float)0.0f);
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)(this.playerViewerX.getValue() + 25), (float)(this.playerViewerY.getValue() + 25), (float)50.0f);
        GlStateManager.scale((float)(-50.0f * this.playerScale.getValue().floatValue()), (float)(50.0f * this.playerScale.getValue().floatValue()), (float)(50.0f * this.playerScale.getValue().floatValue()));
        GlStateManager.rotate((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.rotate((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)(-((float)Math.atan((float)this.playerViewerY.getValue().intValue() / 40.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.translate((float)0.0f, (float)0.0f, (float)0.0f);
        RenderManager renderManager = mc.getRenderManager();
        renderManager.setPlayerViewY(180.0f);
        renderManager.setRenderShadow(false);
        try {
            renderManager.doRenderEntity((Entity)entityPlayer2, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        }
        catch (Exception exception) {
            // empty catch block
        }
        renderManager.setRenderShadow(true);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
        GlStateManager.depthFunc((int)515);
        GlStateManager.resetColor();
        GlStateManager.disableDepth();
        GlStateManager.popMatrix();
    }

    private static void preboxrender() {
        GL11.glPushMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.disableAlpha();
        GlStateManager.clear((int)256);
        GlStateManager.enableBlend();
        GlStateManager.color((float)255.0f, (float)255.0f, (float)255.0f, (float)255.0f);
    }

    public void drawCompass() {
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        if (this.compass.getValue() == Compass.LINE) {
            float f = HudComponents.mc.player.rotationYaw;
            float f2 = MathUtil.wrap(f);
            RenderUtil.drawRect(this.compassX.getValue().intValue(), this.compassY.getValue().intValue(), this.compassX.getValue() + 100, this.compassY.getValue() + this.renderer.getFontHeight(), 1963986960);
            RenderUtil.glScissor(this.compassX.getValue().intValue(), this.compassY.getValue().intValue(), this.compassX.getValue() + 100, this.compassY.getValue() + this.renderer.getFontHeight(), scaledResolution);
            GL11.glEnable((int)3089);
            float f3 = MathUtil.wrap((float)(Math.atan2(0.0 - HudComponents.mc.player.posZ, 0.0 - HudComponents.mc.player.posX) * 180.0 / Math.PI) - 90.0f);
            RenderUtil.drawLine((float)this.compassX.getValue().intValue() - f2 + 50.0f + f3, this.compassY.getValue() + 2, (float)this.compassX.getValue().intValue() - f2 + 50.0f + f3, this.compassY.getValue() + this.renderer.getFontHeight() - 2, 2.0f, -61424);
            RenderUtil.drawLine((float)this.compassX.getValue().intValue() - f2 + 50.0f + 45.0f, this.compassY.getValue() + 2, (float)this.compassX.getValue().intValue() - f2 + 50.0f + 45.0f, this.compassY.getValue() + this.renderer.getFontHeight() - 2, 2.0f, -1);
            RenderUtil.drawLine((float)this.compassX.getValue().intValue() - f2 + 50.0f - 45.0f, this.compassY.getValue() + 2, (float)this.compassX.getValue().intValue() - f2 + 50.0f - 45.0f, this.compassY.getValue() + this.renderer.getFontHeight() - 2, 2.0f, -1);
            RenderUtil.drawLine((float)this.compassX.getValue().intValue() - f2 + 50.0f + 135.0f, this.compassY.getValue() + 2, (float)this.compassX.getValue().intValue() - f2 + 50.0f + 135.0f, this.compassY.getValue() + this.renderer.getFontHeight() - 2, 2.0f, -1);
            RenderUtil.drawLine((float)this.compassX.getValue().intValue() - f2 + 50.0f - 135.0f, this.compassY.getValue() + 2, (float)this.compassX.getValue().intValue() - f2 + 50.0f - 135.0f, this.compassY.getValue() + this.renderer.getFontHeight() - 2, 2.0f, -1);
            this.renderer.drawStringWithShadow("n", (float)this.compassX.getValue().intValue() - f2 + 50.0f + 180.0f - (float)this.renderer.getStringWidth("n") / 2.0f, this.compassY.getValue().intValue(), -1);
            this.renderer.drawStringWithShadow("n", (float)this.compassX.getValue().intValue() - f2 + 50.0f - 180.0f - (float)this.renderer.getStringWidth("n") / 2.0f, this.compassY.getValue().intValue(), -1);
            this.renderer.drawStringWithShadow("e", (float)this.compassX.getValue().intValue() - f2 + 50.0f - 90.0f - (float)this.renderer.getStringWidth("e") / 2.0f, this.compassY.getValue().intValue(), -1);
            this.renderer.drawStringWithShadow("s", (float)this.compassX.getValue().intValue() - f2 + 50.0f - (float)this.renderer.getStringWidth("s") / 2.0f, this.compassY.getValue().intValue(), -1);
            this.renderer.drawStringWithShadow("w", (float)this.compassX.getValue().intValue() - f2 + 50.0f + 90.0f - (float)this.renderer.getStringWidth("w") / 2.0f, this.compassY.getValue().intValue(), -1);
            RenderUtil.drawLine(this.compassX.getValue() + 50, this.compassY.getValue() + 1, this.compassX.getValue() + 50, this.compassY.getValue() + this.renderer.getFontHeight() - 1, 2.0f, -7303024);
            GL11.glDisable((int)3089);
        } else {
            double d = this.compassX.getValue().intValue();
            double d2 = this.compassY.getValue().intValue();
            for (Direction direction : Direction.values()) {
                double d3 = HudComponents.getPosOnCompass(direction);
                this.renderer.drawStringWithShadow(direction.name(), (float)(d + this.getX(d3)), (float)(d2 + this.getY(d3)), direction == Direction.N ? -65536 : -1);
            }
        }
    }

    private void boxrender(int n, int n2) {
        HudComponents.preboxrender();
        HudComponents.mc.renderEngine.bindTexture(box);
        RenderUtil.drawTexturedRect(n, n2, 0, 0, 176, 16, 500);
        RenderUtil.drawTexturedRect(n, n2 + 16, 0, 16, 176, 54 + this.invH.getValue(), 500);
        RenderUtil.drawTexturedRect(n, n2 + 16 + 54, 0, 160, 176, 8, 500);
        HudComponents.postboxrender();
    }

    private BlockPos traceToBlock(float f, float f2) {
        Vec3d vec3d = EntityUtil.interpolateEntity((Entity)HudComponents.mc.player, f);
        Vec3d vec3d2 = MathUtil.direction(f2);
        return new BlockPos(vec3d.xCoord + vec3d2.xCoord, vec3d.yCoord, vec3d.zCoord + vec3d2.zCoord);
    }

    public void drawOverlay(float f) {
        BlockPos blockPos;
        Block block;
        BlockPos blockPos2;
        Block block2;
        BlockPos blockPos3;
        Block block3;
        float f2 = 0.0f;
        int n = MathHelper.floor((double)((double)(HudComponents.mc.player.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3;
        switch (n) {
            case 1: {
                f2 = 90.0f;
                break;
            }
            case 2: {
                f2 = -180.0f;
                break;
            }
            case 3: {
                f2 = -90.0f;
            }
        }
        BlockPos blockPos4 = this.traceToBlock(f, f2);
        Block block4 = this.getBlock(blockPos4);
        if (block4 != null && block4 != Blocks.AIR) {
            int n2 = this.getBlockDamage(blockPos4);
            if (n2 != 0) {
                RenderUtil.drawRect(this.holeX.getValue() + 16, this.holeY.getValue().intValue(), this.holeX.getValue() + 32, this.holeY.getValue() + 16, 0x60FF0000);
            }
            this.drawBlock(block4, this.holeX.getValue() + 16, this.holeY.getValue().intValue());
        }
        if ((block3 = this.getBlock(blockPos3 = this.traceToBlock(f, f2 - 180.0f))) != null && block3 != Blocks.AIR) {
            int n3 = this.getBlockDamage(blockPos3);
            if (n3 != 0) {
                RenderUtil.drawRect(this.holeX.getValue() + 16, this.holeY.getValue() + 32, this.holeX.getValue() + 32, this.holeY.getValue() + 48, 0x60FF0000);
            }
            this.drawBlock(block3, this.holeX.getValue() + 16, this.holeY.getValue() + 32);
        }
        if ((block2 = this.getBlock(blockPos2 = this.traceToBlock(f, f2 + 90.0f))) != null && block2 != Blocks.AIR) {
            int n4 = this.getBlockDamage(blockPos2);
            if (n4 != 0) {
                RenderUtil.drawRect(this.holeX.getValue() + 32, this.holeY.getValue() + 16, this.holeX.getValue() + 48, this.holeY.getValue() + 32, 0x60FF0000);
            }
            this.drawBlock(block2, this.holeX.getValue() + 32, this.holeY.getValue() + 16);
        }
        if ((block = this.getBlock(blockPos = this.traceToBlock(f, f2 - 90.0f))) != null && block != Blocks.AIR) {
            int n5 = this.getBlockDamage(blockPos);
            if (n5 != 0) {
                RenderUtil.drawRect(this.holeX.getValue().intValue(), this.holeY.getValue() + 16, this.holeX.getValue() + 16, this.holeY.getValue() + 32, 0x60FF0000);
            }
            this.drawBlock(block, this.holeX.getValue().intValue(), this.holeY.getValue() + 16);
        }
    }

    public void renderInventory() {
        if (this.invtexture.getValue().booleanValue()) {
            this.boxrender(this.invX.getValue() + this.fineinvX.getValue(), this.invY.getValue() + this.fineinvY.getValue());
        }
        this.itemrender((NonNullList<ItemStack>)HudComponents.mc.player.inventory.mainInventory, this.invX.getValue() + this.fineinvX.getValue(), this.invY.getValue() + this.fineinvY.getValue());
    }

    private static void postitemrender() {
        GlStateManager.scale((float)1.0f, (float)1.0f, (float)1.0f);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableLighting();
        GlStateManager.scale((double)0.5, (double)0.5, (double)0.5);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        GlStateManager.scale((float)2.0f, (float)2.0f, (float)2.0f);
        GL11.glPopMatrix();
    }

    @Override
    public void onRender2D(Render2DEvent render2DEvent) {
        if (HudComponents.fullNullCheck()) {
            return;
        }
        if (this.playerViewer.getValue().booleanValue()) {
            this.drawPlayer();
        }
        if (this.compass.getValue() != Compass.NONE) {
            this.drawCompass();
        }
        if (this.holeHud.getValue().booleanValue()) {
            this.drawOverlay(render2DEvent.partialTicks);
        }
        if (this.inventory.getValue().booleanValue()) {
            this.renderInventory();
        }
    }

    private double getY(double d) {
        double d2 = MathHelper.clamp((float)(HudComponents.mc.player.rotationPitch + 30.0f), (float)-90.0f, (float)90.0f);
        double d3 = Math.toRadians(d2);
        return Math.cos(d) * Math.sin(d3) * (double)(this.scale.getValue() * 10);
    }

    static {
        box = new ResourceLocation("textures/gui/container/shulker_box.png");
    }

    private void drawBlock(Block block, float f, float f2) {
        ItemStack itemStack = new ItemStack(block);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.translate((float)f, (float)f2, (float)0.0f);
        HudComponents.mc.getRenderItem().zLevel = 501.0f;
        mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, 0, 0);
        HudComponents.mc.getRenderItem().zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableBlend();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.popMatrix();
    }

    private static double getPosOnCompass(Direction direction) {
        double d = Math.toRadians(MathHelper.wrapDegrees((float)HudComponents.mc.player.rotationYaw));
        int n = direction.ordinal();
        return d + (double)n * 1.5707963267948966;
    }

    private static void preitemrender() {
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)true);
        GlStateManager.clear((int)256);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.scale((float)1.0f, (float)1.0f, (float)0.01f);
    }

    private static enum Direction {
        N,
        W,
        S,
        E;

    }

    public static enum Compass {
        NONE,
        CIRCLE,
        LINE;

    }
}

