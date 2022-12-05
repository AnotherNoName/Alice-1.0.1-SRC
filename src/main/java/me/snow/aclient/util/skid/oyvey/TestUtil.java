//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketEntityAction
 *  net.minecraft.network.play.client.CPacketEntityAction$Action
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  org.lwjgl.opengl.GL11
 */
package me.snow.aclient.util.skid.oyvey;

import java.awt.Color;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import me.snow.aclient.AliceMain;
import me.snow.aclient.util.EntityUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.lwjgl.opengl.GL11;

public class TestUtil {
    public static /* synthetic */ List<Block> rightclickableBlocks;
    private static /* synthetic */ boolean depth;
    public static /* synthetic */ List<Block> emptyBlocks;
    private static /* synthetic */ boolean texture;
    private static /* synthetic */ boolean bind;
    private static /* synthetic */ float[] tickRates;
    private static /* synthetic */ boolean clean;
    private static /* synthetic */ boolean override;
    private static final /* synthetic */ Minecraft mc;

    public static float[][] getBipedRotations(ModelBiped modelBiped) {
        float[][] arrarrf = new float[5][];
        float[] arrf = new float[]{modelBiped.bipedHead.rotateAngleX, modelBiped.bipedHead.rotateAngleY, modelBiped.bipedHead.rotateAngleZ};
        arrarrf[0] = arrf;
        float[] arrf2 = new float[]{modelBiped.bipedRightArm.rotateAngleX, modelBiped.bipedRightArm.rotateAngleY, modelBiped.bipedRightArm.rotateAngleZ};
        arrarrf[1] = arrf2;
        float[] arrf3 = new float[]{modelBiped.bipedLeftArm.rotateAngleX, modelBiped.bipedLeftArm.rotateAngleY, modelBiped.bipedLeftArm.rotateAngleZ};
        arrarrf[2] = arrf3;
        float[] arrf4 = new float[]{modelBiped.bipedRightLeg.rotateAngleX, modelBiped.bipedRightLeg.rotateAngleY, modelBiped.bipedRightLeg.rotateAngleZ};
        arrarrf[3] = arrf4;
        float[] arrf5 = new float[]{modelBiped.bipedLeftLeg.rotateAngleX, modelBiped.bipedLeftLeg.rotateAngleY, modelBiped.bipedLeftLeg.rotateAngleZ};
        arrarrf[4] = arrf5;
        return arrarrf;
    }

    public static Vec3d getInterpolatedRenderPos(Entity entity, float f) {
        return TestUtil.getInterpolatedPos(entity, f).subtract(EntityUtil.mc.getRenderManager().renderPosX, EntityUtil.mc.getRenderManager().renderPosY, EntityUtil.mc.getRenderManager().renderPosZ);
    }

    public static float getTickRate() {
        float f = 0.0f;
        float f2 = 0.0f;
        for (float f3 : tickRates) {
            if (!(f3 > 0.0f)) continue;
            f2 += f3;
            f += 1.0f;
        }
        return MathHelper.clamp((float)(f2 / f), (float)0.0f, (float)20.0f);
    }

    public static boolean rayTracePlaceCheck(BlockPos blockPos, boolean bl, float f) {
        return !bl || TestUtil.mc.world.rayTraceBlocks(new Vec3d(TestUtil.mc.player.posX, TestUtil.mc.player.posY + (double)TestUtil.mc.player.getEyeHeight(), TestUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)((float)blockPos.getY() + f), (double)blockPos.getZ()), false, true, false) == null;
    }

    public static Vec3d getInterpolatedAmount(Entity entity, float f) {
        return TestUtil.getInterpolatedAmount(entity, f, f, f);
    }

    public static Color getColor(Entity entity, int n, int n2, int n3, int n4, boolean bl) {
        Color color = new Color((float)n / 255.0f, (float)n2 / 255.0f, (float)n3 / 255.0f, (float)n4 / 255.0f);
        if (entity instanceof EntityPlayer && bl && AliceMain.friendManager.isFriend((EntityPlayer)entity)) {
            color = new Color(0.33333334f, 1.0f, 1.0f, (float)n4 / 255.0f);
        }
        return color;
    }

    public static void openBlock(BlockPos blockPos) {
        EnumFacing[] arrenumFacing;
        for (EnumFacing enumFacing : arrenumFacing = EnumFacing.values()) {
            Block block = TestUtil.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock();
            if (!emptyBlocks.contains((Object)block)) continue;
            TestUtil.mc.playerController.processRightClickBlock(TestUtil.mc.player, TestUtil.mc.world, blockPos, enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
            return;
        }
    }

    public static boolean canSeeBlock(BlockPos blockPos) {
        return TestUtil.mc.player != null && TestUtil.mc.world.rayTraceBlocks(new Vec3d(TestUtil.mc.player.posX, TestUtil.mc.player.posY + (double)TestUtil.mc.player.getEyeHeight(), TestUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()), false, true, false) == null;
    }

    public static boolean canPlaceBlock(BlockPos blockPos) {
        if (TestUtil.isBlockEmpty(blockPos)) {
            EnumFacing[] arrenumFacing;
            for (EnumFacing enumFacing : arrenumFacing = EnumFacing.values()) {
                if (emptyBlocks.contains((Object)TestUtil.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock())) continue;
                Vec3d vec3d = new Vec3d((double)blockPos.getX() + 0.5 + (double)enumFacing.getFrontOffsetX() * 0.5, (double)blockPos.getY() + 0.5 + (double)enumFacing.getFrontOffsetY() * 0.5, (double)blockPos.getZ() + 0.5 + (double)enumFacing.getFrontOffsetZ() * 0.5);
                if (!(TestUtil.mc.player.getPositionEyes(mc.getRenderPartialTicks()).distanceTo(vec3d) <= 4.25)) continue;
                return true;
            }
        }
        return false;
    }

    public static Vec3d getInterpolatedAmount(Entity entity, double d, double d2, double d3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * d, (entity.posY - entity.lastTickPosY) * d2, (entity.posZ - entity.lastTickPosZ) * d3);
    }

    public static void GlPost() {
        TestUtil.GLPost(depth, texture, clean, bind, override);
    }

    public static boolean placeBlock(BlockPos blockPos) {
        if (TestUtil.isBlockEmpty(blockPos)) {
            EnumFacing[] arrenumFacing;
            for (EnumFacing enumFacing : arrenumFacing = EnumFacing.values()) {
                Block block = TestUtil.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock();
                Vec3d vec3d = new Vec3d((double)blockPos.getX() + 0.5 + (double)enumFacing.getFrontOffsetX() * 0.5, (double)blockPos.getY() + 0.5 + (double)enumFacing.getFrontOffsetY() * 0.5, (double)blockPos.getZ() + 0.5 + (double)enumFacing.getFrontOffsetZ() * 0.5);
                if (emptyBlocks.contains((Object)block) || !(TestUtil.mc.player.getPositionEyes(mc.getRenderPartialTicks()).distanceTo(vec3d) <= 4.25)) continue;
                float[] arrf = new float[]{TestUtil.mc.player.rotationYaw, TestUtil.mc.player.rotationPitch};
                if (rightclickableBlocks.contains((Object)block)) {
                    TestUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)TestUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                }
                TestUtil.mc.playerController.processRightClickBlock(TestUtil.mc.player, TestUtil.mc.world, blockPos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                if (rightclickableBlocks.contains((Object)block)) {
                    TestUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)TestUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                }
                TestUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
                return true;
            }
        }
        return false;
    }

    private static void GLPre(boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, float f) {
        if (bl) {
            GL11.glDisable((int)2896);
        }
        if (!bl2) {
            GL11.glEnable((int)3042);
        }
        GL11.glLineWidth((float)f);
        if (bl3) {
            GL11.glDisable((int)3553);
        }
        if (bl4) {
            GL11.glDisable((int)2929);
        }
        if (!bl5) {
            GL11.glEnable((int)2848);
        }
        GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GL11.glHint((int)3154, (int)4354);
        GlStateManager.depthMask((boolean)false);
    }

    public static Vec3d getInterpolatedPos(Entity entity, float f) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(TestUtil.getInterpolatedAmount(entity, f));
    }

    public static boolean isBlockEmpty(BlockPos blockPos) {
        try {
            if (emptyBlocks.contains((Object)TestUtil.mc.world.getBlockState(blockPos).getBlock())) {
                Entity entity;
                AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos);
                Iterator iterator2 = TestUtil.mc.world.loadedEntityList.iterator();
                do {
                    if (iterator2.hasNext()) continue;
                    return true;
                } while (!((entity = (Entity)iterator2.next()) instanceof EntityLivingBase) || !axisAlignedBB.intersectsWith(entity.getEntityBoundingBox()));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return false;
    }

    public static void GLPre(float f) {
        depth = GL11.glIsEnabled((int)2896);
        texture = GL11.glIsEnabled((int)3042);
        clean = GL11.glIsEnabled((int)3553);
        bind = GL11.glIsEnabled((int)2929);
        override = GL11.glIsEnabled((int)2848);
        TestUtil.GLPre(depth, texture, clean, bind, override, f);
    }

    public static boolean rayTracePlaceCheck(BlockPos blockPos, boolean bl) {
        return TestUtil.rayTracePlaceCheck(blockPos, bl, 1.0f);
    }

    static {
        mc = Minecraft.getMinecraft();
        tickRates = new float[20];
        emptyBlocks = Arrays.asList(new Block[]{Blocks.AIR, Blocks.FLOWING_LAVA, Blocks.LAVA, Blocks.FLOWING_WATER, Blocks.WATER, Blocks.VINE, Blocks.SNOW_LAYER, Blocks.TALLGRASS, Blocks.FIRE});
        rightclickableBlocks = Arrays.asList(new Block[]{Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.field_190977_dl, Blocks.field_190978_dm, Blocks.field_190979_dn, Blocks.field_190980_do, Blocks.field_190981_dp, Blocks.field_190982_dq, Blocks.field_190983_dr, Blocks.field_190984_ds, Blocks.field_190985_dt, Blocks.field_190986_du, Blocks.field_190987_dv, Blocks.field_190988_dw, Blocks.field_190989_dx, Blocks.field_190990_dy, Blocks.field_190991_dz, Blocks.field_190975_dA, Blocks.ANVIL, Blocks.WOODEN_BUTTON, Blocks.STONE_BUTTON, Blocks.UNPOWERED_COMPARATOR, Blocks.UNPOWERED_REPEATER, Blocks.POWERED_REPEATER, Blocks.POWERED_COMPARATOR, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.ACACIA_FENCE_GATE, Blocks.BREWING_STAND, Blocks.DISPENSER, Blocks.DROPPER, Blocks.LEVER, Blocks.NOTEBLOCK, Blocks.JUKEBOX, Blocks.BEACON, Blocks.BED, Blocks.FURNACE, Blocks.OAK_DOOR, Blocks.SPRUCE_DOOR, Blocks.BIRCH_DOOR, Blocks.JUNGLE_DOOR, Blocks.ACACIA_DOOR, Blocks.DARK_OAK_DOOR, Blocks.CAKE, Blocks.ENCHANTING_TABLE, Blocks.DRAGON_EGG, Blocks.HOPPER, Blocks.REPEATING_COMMAND_BLOCK, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.CRAFTING_TABLE});
        depth = GL11.glIsEnabled((int)2896);
        texture = GL11.glIsEnabled((int)3042);
        clean = GL11.glIsEnabled((int)3553);
        bind = GL11.glIsEnabled((int)2929);
        override = GL11.glIsEnabled((int)2848);
    }

    public static void placeCrystalOnBlock(BlockPos blockPos, EnumHand enumHand) {
        RayTraceResult rayTraceResult = TestUtil.mc.world.rayTraceBlocks(new Vec3d(TestUtil.mc.player.posX, TestUtil.mc.player.posY + (double)TestUtil.mc.player.getEyeHeight(), TestUtil.mc.player.posZ), new Vec3d((double)blockPos.getX() + 0.5, (double)blockPos.getY() - 0.5, (double)blockPos.getZ() + 0.5));
        EnumFacing enumFacing = rayTraceResult == null || rayTraceResult.sideHit == null ? EnumFacing.UP : rayTraceResult.sideHit;
        TestUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, 0.0f, 0.0f, 0.0f));
    }

    private static void GLPost(boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5) {
        GlStateManager.depthMask((boolean)true);
        if (!bl5) {
            GL11.glDisable((int)2848);
        }
        if (bl4) {
            GL11.glEnable((int)2929);
        }
        if (bl3) {
            GL11.glEnable((int)3553);
        }
        if (!bl2) {
            GL11.glDisable((int)3042);
        }
        if (bl) {
            GL11.glEnable((int)2896);
        }
    }
}

