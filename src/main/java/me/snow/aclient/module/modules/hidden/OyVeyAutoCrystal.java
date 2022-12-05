//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.ItemEndCrystal
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayer
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.network.play.client.CPacketUseEntity$Action
 *  net.minecraft.network.play.server.SPacketSoundEffect
 *  net.minecraft.network.play.server.SPacketSpawnObject
 *  net.minecraft.util.CombatRules
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.hidden;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.event.events.Render3DEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.EntityUtil;
import me.snow.aclient.util.InventoryUtil;
import me.snow.aclient.util.MathUtil;
import me.snow.aclient.util.RenderUtil;
import me.snow.aclient.util.Timer;
import me.snow.aclient.util.skid.oyvey.RenderUtil2;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.network.play.server.SPacketSpawnObject;
import net.minecraft.util.CombatRules;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OyVeyAutoCrystal
extends Module {
    public /* synthetic */ Setting<Boolean> render;
    public /* synthetic */ Setting<Boolean> sound;
    private /* synthetic */ int hotBarSlot;
    private /* synthetic */ boolean armor;
    private /* synthetic */ EntityLivingBase target;
    private /* synthetic */ float pitch;
    public /* synthetic */ Setting<Float> breakRange;
    private /* synthetic */ BlockPos pos;
    private final /* synthetic */ Setting<Float> lineWidth;
    private /* synthetic */ float yaw;
    public /* synthetic */ Setting<Float> placeRange;
    private /* synthetic */ boolean packetCalc;
    public /* synthetic */ Setting<Boolean> place;
    public /* synthetic */ Setting<Boolean> box;
    private /* synthetic */ boolean armorTarget;
    public /* synthetic */ Setting<Boolean> packetBreak;
    public /* synthetic */ Setting<Float> minArmor;
    private /* synthetic */ boolean rotating;
    public /* synthetic */ Setting<Float> breakMaxSelfDamage;
    private final /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Float> minDamage;
    public /* synthetic */ Setting<Integer> wasteAmount;
    private final /* synthetic */ Setting<Integer> cRed;
    public /* synthetic */ Setting<SwitchMode> switchmode;
    private final /* synthetic */ Timer breakTimer;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    public /* synthetic */ Setting<Boolean> suicide;
    private final /* synthetic */ Setting<Integer> cAlpha;
    public /* synthetic */ Setting<Float> facePlace;
    private final /* synthetic */ Setting<Integer> red;
    private final /* synthetic */ Setting<Integer> cBlue;
    private final /* synthetic */ Setting<Integer> attackFactor;
    public /* synthetic */ Setting<Boolean> rotate;
    public /* synthetic */ Setting<Boolean> outline;
    private /* synthetic */ int predictPackets;
    public /* synthetic */ Setting<SwingMode> swingMode;
    public /* synthetic */ Setting<Boolean> silentSwitch;
    public /* synthetic */ Setting<Float> placeDelay;
    /* synthetic */ EntityEnderCrystal crystal;
    public /* synthetic */ Setting<Boolean> renderDmg;
    public /* synthetic */ Setting<Boolean> opPlace;
    private final /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Boolean> autoswitch;
    public /* synthetic */ Setting<Boolean> facePlaceSword;
    public /* synthetic */ Setting<Float> breakDelay;
    private /* synthetic */ int predictWait;
    private /* synthetic */ int predict;
    public /* synthetic */ Setting<Boolean> predicts;
    public /* synthetic */ Setting<Float> breakMinDmg;
    private /* synthetic */ int crystalCount;
    private final /* synthetic */ Timer preditTimer;
    public /* synthetic */ Setting<Boolean> ignoreUseAmount;
    private final /* synthetic */ Timer placeTimer;
    private final /* synthetic */ Setting<Integer> alpha;
    private /* synthetic */ EntityLivingBase realTarget;
    private final /* synthetic */ Timer manualTimer;
    public /* synthetic */ Setting<Float> targetRange;
    public /* synthetic */ Setting<Float> breakWallRange;
    public /* synthetic */ Setting<Boolean> explode;
    private final /* synthetic */ Setting<Integer> cGreen;

    private float getBlastReduction(EntityLivingBase entityLivingBase, float f, Explosion explosion) {
        float f2 = f;
        if (entityLivingBase instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
            DamageSource damageSource = DamageSource.causeExplosionDamage((Explosion)explosion);
            f2 = CombatRules.getDamageAfterAbsorb((float)f2, (float)entityPlayer.getTotalArmorValue(), (float)((float)entityPlayer.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue()));
            int n = 0;
            try {
                n = EnchantmentHelper.getEnchantmentModifierDamage((Iterable)entityPlayer.getArmorInventoryList(), (DamageSource)damageSource);
            }
            catch (Exception exception) {
                // empty catch block
            }
            float f3 = MathHelper.clamp((float)n, (float)0.0f, (float)20.0f);
            f2 *= 1.0f - f3 / 25.0f;
            if (entityLivingBase.isPotionActive(MobEffects.RESISTANCE)) {
                f2 -= f2 / 4.0f;
            }
            f2 = Math.max(f2, 0.0f);
            return f2;
        }
        f2 = CombatRules.getDamageAfterAbsorb((float)f2, (float)entityLivingBase.getTotalArmorValue(), (float)((float)entityLivingBase.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue()));
        return f2;
    }

    private float calculateDamage(double d, double d2, double d3, Entity entity) {
        float f = 12.0f;
        double d4 = entity.getDistance(d, d2, d3) / 12.0;
        Vec3d vec3d = new Vec3d(d, d2, d3);
        double d5 = 0.0;
        try {
            d5 = entity.world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
        }
        catch (Exception exception) {
            // empty catch block
        }
        double d6 = (1.0 - d4) * d5;
        float f2 = (int)((d6 * d6 + d6) / 2.0 * 7.0 * 12.0 + 1.0);
        double d7 = 1.0;
        if (entity instanceof EntityLivingBase) {
            d7 = this.getBlastReduction((EntityLivingBase)entity, this.getDamageMultiplied(f2), new Explosion((World)OyVeyAutoCrystal.mc.world, null, d, d2, d3, 6.0f, false, true));
        }
        return (float)d7;
    }

    public OyVeyAutoCrystal() {
        super("CrystalAura", "skitty ac best ac", Module.Category.COMBAT, true, false, false);
        this.placeTimer = new Timer();
        this.breakTimer = new Timer();
        this.preditTimer = new Timer();
        this.manualTimer = new Timer();
        this.attackFactor = this.register(new Setting<Integer>("PredictDelay", 0, 0, 200));
        this.red = this.register(new Setting<Integer>("Red", 0, 0, 255));
        this.green = this.register(new Setting<Integer>("Green", 255, 0, 255));
        this.blue = this.register(new Setting<Integer>("Blue", 0, 0, 255));
        this.alpha = this.register(new Setting<Integer>("Alpha", 255, 0, 255));
        this.boxAlpha = this.register(new Setting<Integer>("BoxAlpha", 125, 0, 255));
        this.lineWidth = this.register(new Setting<Float>("LineWidth", Float.valueOf(1.0f), Float.valueOf(0.1f), Float.valueOf(5.0f)));
        this.sound = this.register(new Setting<Boolean>("Sequential", true));
        this.place = this.register(new Setting<Boolean>("Place", true));
        this.placeDelay = this.register(new Setting<Float>("PlaceDelay", Float.valueOf(4.0f), Float.valueOf(0.0f), Float.valueOf(300.0f)));
        this.placeRange = this.register(new Setting<Float>("PlaceRange", Float.valueOf(6.0f), Float.valueOf(0.1f), Float.valueOf(7.0f)));
        this.explode = this.register(new Setting<Boolean>("Break", true));
        this.packetBreak = this.register(new Setting<Boolean>("PacketBreak", true));
        this.predicts = this.register(new Setting<Boolean>("Predict", true));
        this.rotate = this.register(new Setting<Boolean>("Rotate", true));
        this.breakDelay = this.register(new Setting<Float>("BreakDelay", Float.valueOf(4.0f), Float.valueOf(0.0f), Float.valueOf(300.0f)));
        this.breakRange = this.register(new Setting<Float>("BreakRange", Float.valueOf(6.0f), Float.valueOf(0.1f), Float.valueOf(7.0f)));
        this.breakWallRange = this.register(new Setting<Float>("BreakWallRange", Float.valueOf(4.0f), Float.valueOf(0.1f), Float.valueOf(7.0f)));
        this.opPlace = this.register(new Setting<Boolean>("1.13 Place", false));
        this.suicide = this.register(new Setting<Boolean>("AntiSuicide", true));
        this.autoswitch = this.register(new Setting<Boolean>("AutoSwitch", true));
        this.switchmode = this.register(new Setting<SwitchMode>("SwitchMode", SwitchMode.Silent, switchMode -> this.autoswitch.getValue()));
        this.silentSwitch = this.register(new Setting<Boolean>("SilentSwitchHand", Boolean.valueOf(true), bl -> this.switchmode.getValue() == SwitchMode.Silent));
        this.ignoreUseAmount = this.register(new Setting<Boolean>("IgnoreUseAmount", true));
        this.wasteAmount = this.register(new Setting<Integer>("UseAmount", 4, 1, 5));
        this.facePlaceSword = this.register(new Setting<Boolean>("FacePlaceSword", false));
        this.targetRange = this.register(new Setting<Float>("TargetRange", Float.valueOf(10.0f), Float.valueOf(1.0f), Float.valueOf(12.0f)));
        this.minDamage = this.register(new Setting<Float>("MinDamage", Float.valueOf(2.0f), Float.valueOf(0.1f), Float.valueOf(20.0f)));
        this.facePlace = this.register(new Setting<Float>("FacePlaceHP", Float.valueOf(36.0f), Float.valueOf(0.0f), Float.valueOf(36.0f)));
        this.breakMaxSelfDamage = this.register(new Setting<Float>("BreakMaxSelf", Float.valueOf(8.0f), Float.valueOf(0.1f), Float.valueOf(12.0f)));
        this.breakMinDmg = this.register(new Setting<Float>("BreakMinDmg", Float.valueOf(2.0f), Float.valueOf(0.1f), Float.valueOf(7.0f)));
        this.minArmor = this.register(new Setting<Float>("MinArmor", Float.valueOf(4.0f), Float.valueOf(0.1f), Float.valueOf(80.0f)));
        this.swingMode = this.register(new Setting<SwingMode>("Swing", SwingMode.None));
        this.render = this.register(new Setting<Boolean>("Render", true));
        this.renderDmg = this.register(new Setting<Boolean>("RenderDmg", true));
        this.box = this.register(new Setting<Boolean>("Box", true));
        this.outline = this.register(new Setting<Boolean>("Outline", true));
        this.cRed = this.register(new Setting<Object>("OL-Red", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.outline.getValue()));
        this.cGreen = this.register(new Setting<Object>("OL-Green", Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(255), object -> this.outline.getValue()));
        this.cBlue = this.register(new Setting<Object>("OL-Blue", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.outline.getValue()));
        this.cAlpha = this.register(new Setting<Object>("OL-Alpha", Integer.valueOf(255), Integer.valueOf(0), Integer.valueOf(255), object -> this.outline.getValue()));
        this.yaw = 0.0f;
        this.pitch = 0.0f;
        this.rotating = false;
    }

    @Override
    public String getDisplayInfo() {
        if (this.realTarget != null) {
            return this.realTarget.getName();
        }
        return null;
    }

    @Override
    public void onEnable() {
        this.placeTimer.reset();
        this.breakTimer.reset();
        this.predictWait = 0;
        this.hotBarSlot = -1;
        this.pos = null;
        this.crystal = null;
        this.predict = 0;
        this.predictPackets = 1;
        this.target = null;
        this.packetCalc = false;
        this.realTarget = null;
        this.armor = false;
        this.armorTarget = false;
    }

    private void rotateToPos(BlockPos blockPos) {
        if (this.rotate.getValue().booleanValue()) {
            float[] arrf = MathUtil.calcAngle(OyVeyAutoCrystal.mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d((double)((float)blockPos.getX() + 0.5f), (double)((float)blockPos.getY() - 0.5f), (double)((float)blockPos.getZ() + 0.5f)));
            this.yaw = arrf[0];
            this.pitch = arrf[1];
            this.rotating = true;
        }
    }

    private boolean isPredicting(SPacketSpawnObject sPacketSpawnObject) {
        double d;
        BlockPos blockPos = new BlockPos(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ());
        if (OyVeyAutoCrystal.mc.player.getDistance(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ()) > (double)this.breakRange.getValue().floatValue()) {
            return false;
        }
        if (!this.canSeePos(blockPos) && OyVeyAutoCrystal.mc.player.getDistance(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ()) > (double)this.breakWallRange.getValue().floatValue()) {
            return false;
        }
        double d2 = this.calculateDamage(sPacketSpawnObject.getX() + 0.5, sPacketSpawnObject.getY() + 1.0, sPacketSpawnObject.getZ() + 0.5, (Entity)this.target);
        if (EntityUtil.isInHole((Entity)OyVeyAutoCrystal.mc.player) && d2 >= 1.0) {
            return true;
        }
        double d3 = this.calculateDamage(sPacketSpawnObject.getX() + 0.5, sPacketSpawnObject.getY() + 1.0, sPacketSpawnObject.getZ() + 0.5, (Entity)OyVeyAutoCrystal.mc.player);
        double d4 = d = this.suicide.getValue() != false ? 2.0 : 0.5;
        if (d3 + d < (double)(OyVeyAutoCrystal.mc.player.getHealth() + OyVeyAutoCrystal.mc.player.getAbsorptionAmount()) && d2 >= (double)(this.target.getAbsorptionAmount() + this.target.getHealth())) {
            return true;
        }
        this.armorTarget = false;
        for (ItemStack itemStack : this.target.getArmorInventoryList()) {
            float f = ((float)itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / (float)itemStack.getMaxDamage();
            float f2 = 1.0f - f;
            int n = 100 - (int)(f2 * 100.0f);
            if (!((float)n <= this.minArmor.getValue().floatValue())) continue;
            this.armorTarget = true;
        }
        if (d2 >= (double)this.breakMinDmg.getValue().floatValue() && d3 <= (double)this.breakMaxSelfDamage.getValue().floatValue()) {
            return true;
        }
        return EntityUtil.isInHole((Entity)this.target) && this.target.getHealth() + this.target.getAbsorptionAmount() <= this.facePlace.getValue().floatValue();
    }

    @SubscribeEvent
    public void onPacketSend(PacketEvent.Send send) {
        if (send.getStage() == 0 && this.rotate.getValue().booleanValue() && this.rotating && send.getPacket() instanceof CPacketPlayer) {
            CPacketPlayer cPacketPlayer = (CPacketPlayer)send.getPacket();
            cPacketPlayer.yaw = this.yaw;
            cPacketPlayer.pitch = this.pitch;
            this.rotating = false;
        }
    }

    private void rotateTo(Entity entity) {
        if (this.rotate.getValue().booleanValue()) {
            float[] arrf = MathUtil.calcAngle(OyVeyAutoCrystal.mc.player.getPositionEyes(mc.getRenderPartialTicks()), entity.getPositionVector());
            this.yaw = arrf[0];
            this.pitch = arrf[1];
            this.rotating = true;
        }
    }

    @Override
    public void onTick() {
        this.onCrystal();
    }

    @Override
    public void onRender3D(Render3DEvent render3DEvent) {
        if (this.pos != null && this.render.getValue().booleanValue() && this.target != null) {
            RenderUtil.drawBoxESP(this.pos, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue().floatValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
            if (this.renderDmg.getValue().booleanValue()) {
                double d = this.calculateDamage((double)this.pos.getX() + 0.5, (double)this.pos.getY() + 1.0, (double)this.pos.getZ() + 0.5, (Entity)this.target);
                RenderUtil2.drawText(this.pos, String.valueOf(new StringBuilder().append(Math.floor(d) == d ? Integer.valueOf((int)d) : String.format("%.1f", d)).append("")));
            }
        }
    }

    public void onCrystal() {
        if (OyVeyAutoCrystal.mc.world == null || OyVeyAutoCrystal.mc.player == null) {
            return;
        }
        this.realTarget = null;
        this.manualBreaker();
        this.crystalCount = 0;
        if (!this.ignoreUseAmount.getValue().booleanValue()) {
            for (Entity entity2 : OyVeyAutoCrystal.mc.world.loadedEntityList) {
                if (!(entity2 instanceof EntityEnderCrystal) || !this.IsValidCrystal(entity2)) continue;
                boolean bl = false;
                double d = this.calculateDamage((double)this.target.getPosition().getX() + 0.5, (double)this.target.getPosition().getY() + 1.0, (double)this.target.getPosition().getZ() + 0.5, (Entity)this.target);
                if (d >= (double)this.minDamage.getValue().floatValue()) {
                    bl = true;
                }
                if (!bl) continue;
                ++this.crystalCount;
            }
        }
        this.hotBarSlot = -1;
        if (OyVeyAutoCrystal.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
            int n;
            int n2 = n = OyVeyAutoCrystal.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL ? OyVeyAutoCrystal.mc.player.inventory.currentItem : -1;
            if (n == -1) {
                for (int i = 0; i < 9; ++i) {
                    if (OyVeyAutoCrystal.mc.player.inventory.getStackInSlot(i).getItem() != Items.END_CRYSTAL) continue;
                    n = i;
                    this.hotBarSlot = i;
                    break;
                }
            }
            if (n == -1) {
                this.pos = null;
                this.target = null;
                return;
            }
        }
        if (OyVeyAutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE && OyVeyAutoCrystal.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL) {
            this.pos = null;
            this.target = null;
            return;
        }
        if (this.target == null) {
            this.target = this.getTarget();
        }
        if (this.target == null) {
            this.crystal = null;
            return;
        }
        if (this.target.getDistanceToEntity((Entity)OyVeyAutoCrystal.mc.player) > 12.0f) {
            this.crystal = null;
            this.target = null;
        }
        this.crystal = OyVeyAutoCrystal.mc.world.loadedEntityList.stream().filter(this::IsValidCrystal).map(entity -> (EntityEnderCrystal)entity).min(Comparator.comparing(entityEnderCrystal -> Float.valueOf(this.target.getDistanceToEntity((Entity)entityEnderCrystal)))).orElse(null);
        if (this.crystal != null && this.explode.getValue().booleanValue() && this.breakTimer.passedMs(this.breakDelay.getValue().longValue())) {
            this.breakTimer.reset();
            if (this.packetBreak.getValue().booleanValue()) {
                this.rotateTo((Entity)this.crystal);
                OyVeyAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketUseEntity((Entity)this.crystal));
            } else {
                this.rotateTo((Entity)this.crystal);
                OyVeyAutoCrystal.mc.playerController.attackEntity((EntityPlayer)OyVeyAutoCrystal.mc.player, (Entity)this.crystal);
            }
            if (this.swingMode.getValue() == SwingMode.MainHand) {
                OyVeyAutoCrystal.mc.player.swingArm(EnumHand.MAIN_HAND);
            } else if (this.swingMode.getValue() == SwingMode.OffHand) {
                OyVeyAutoCrystal.mc.player.swingArm(EnumHand.OFF_HAND);
            }
        }
        if (this.placeTimer.passedMs(this.placeDelay.getValue().longValue()) && this.place.getValue().booleanValue()) {
            this.placeTimer.reset();
            double d = 0.5;
            for (BlockPos blockPos : this.placePostions(this.placeRange.getValue().floatValue())) {
                double d2;
                double d3;
                if (blockPos == null || this.target == null || !OyVeyAutoCrystal.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos)).isEmpty() || (d3 = this.target.getDistance((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ())) > (double)this.targetRange.getValue().floatValue() || this.target.isDead || this.target.getHealth() + this.target.getAbsorptionAmount() <= 0.0f) continue;
                double d4 = this.calculateDamage((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1.0, (double)blockPos.getZ() + 0.5, (Entity)this.target);
                this.armor = false;
                for (ItemStack itemStack : this.target.getArmorInventoryList()) {
                    float f = ((float)itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / (float)itemStack.getMaxDamage();
                    float f2 = 1.0f - f;
                    int n = 100 - (int)(f2 * 100.0f);
                    if (!((float)n <= this.minArmor.getValue().floatValue())) continue;
                    this.armor = true;
                }
                if (d4 < (double)this.minDamage.getValue().floatValue() && (this.facePlaceSword.getValue() != false ? this.target.getAbsorptionAmount() + this.target.getHealth() > this.facePlace.getValue().floatValue() : OyVeyAutoCrystal.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword || this.target.getAbsorptionAmount() + this.target.getHealth() > this.facePlace.getValue().floatValue()) && (this.facePlaceSword.getValue() == false ? OyVeyAutoCrystal.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword || !this.armor : !this.armor)) continue;
                double d5 = this.calculateDamage((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1.0, (double)blockPos.getZ() + 0.5, (Entity)OyVeyAutoCrystal.mc.player);
                if (d2 + (this.suicide.getValue() != false ? 2.0 : 0.5) >= (double)(OyVeyAutoCrystal.mc.player.getHealth() + OyVeyAutoCrystal.mc.player.getAbsorptionAmount()) && d5 >= d4 && d4 < (double)(this.target.getHealth() + this.target.getAbsorptionAmount()) || !(d < d4)) continue;
                this.pos = blockPos;
                d = d4;
            }
            if (d == 0.5) {
                this.pos = null;
                this.target = null;
                this.realTarget = null;
                return;
            }
            this.realTarget = this.target;
            if (this.hotBarSlot != -1 && this.autoswitch.getValue().booleanValue() && !OyVeyAutoCrystal.mc.player.isPotionActive(MobEffects.WEAKNESS) && this.switchmode.getValue() == SwitchMode.Normal && !this.silentSwitch.getValue().booleanValue()) {
                OyVeyAutoCrystal.mc.player.inventory.currentItem = this.hotBarSlot;
            }
            int n = InventoryUtil.findHotbarBlock(ItemEndCrystal.class);
            int n3 = OyVeyAutoCrystal.mc.player.inventory.currentItem;
            EnumHand enumHand = null;
            if (this.switchmode.getValue() == SwitchMode.Silent && n != -1) {
                if (OyVeyAutoCrystal.mc.player.isHandActive() && this.silentSwitch.getValue().booleanValue()) {
                    enumHand = OyVeyAutoCrystal.mc.player.getActiveHand();
                }
                OyVeyAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n));
            }
            if (!this.ignoreUseAmount.getValue().booleanValue()) {
                int n4 = this.wasteAmount.getValue();
                if (this.crystalCount >= n4) {
                    return;
                }
                if (d < (double)this.minDamage.getValue().floatValue()) {
                    n4 = 1;
                }
                if (this.crystalCount < n4 && this.pos != null) {
                    this.rotateToPos(this.pos);
                    OyVeyAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.pos, EnumFacing.UP, OyVeyAutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                }
            } else if (this.pos != null) {
                this.rotateToPos(this.pos);
                OyVeyAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.pos, EnumFacing.UP, OyVeyAutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            }
            if (this.switchmode.getValue() == SwitchMode.Silent && n != -1) {
                if (this.silentSwitch.getValue().booleanValue() && enumHand != null) {
                    OyVeyAutoCrystal.mc.player.setActiveHand(enumHand);
                }
                OyVeyAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(n3));
            }
        }
    }

    EntityPlayer getTarget() {
        EntityPlayer entityPlayer = null;
        for (EntityPlayer entityPlayer2 : OyVeyAutoCrystal.mc.world.playerEntities) {
            if (OyVeyAutoCrystal.mc.player == null || OyVeyAutoCrystal.mc.player.isDead || entityPlayer2.isDead || entityPlayer2 == OyVeyAutoCrystal.mc.player || AliceMain.friendManager.isFriend(entityPlayer2.getName()) || entityPlayer2.getDistanceToEntity((Entity)OyVeyAutoCrystal.mc.player) > 12.0f) continue;
            this.armorTarget = false;
            for (ItemStack itemStack : entityPlayer2.getArmorInventoryList()) {
                float f = ((float)itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / (float)itemStack.getMaxDamage();
                float f2 = 1.0f - f;
                int n = 100 - (int)(f2 * 100.0f);
                if (!((float)n <= this.minArmor.getValue().floatValue())) continue;
                this.armorTarget = true;
            }
            if (EntityUtil.isInHole((Entity)entityPlayer2) && entityPlayer2.getAbsorptionAmount() + entityPlayer2.getHealth() > this.facePlace.getValue().floatValue() && !this.armorTarget && this.minDamage.getValue().floatValue() > 2.2f) continue;
            if (entityPlayer == null) {
                entityPlayer = entityPlayer2;
                continue;
            }
            if (!(entityPlayer.getDistanceToEntity((Entity)OyVeyAutoCrystal.mc.player) > entityPlayer2.getDistanceToEntity((Entity)OyVeyAutoCrystal.mc.player))) continue;
            entityPlayer = entityPlayer2;
        }
        return entityPlayer;
    }

    private void manualBreaker() {
        RayTraceResult rayTraceResult;
        if (this.manualTimer.passedMs(200L) && OyVeyAutoCrystal.mc.gameSettings.keyBindUseItem.isKeyDown() && OyVeyAutoCrystal.mc.player.getHeldItemOffhand().getItem() != Items.GOLDEN_APPLE && OyVeyAutoCrystal.mc.player.inventory.getCurrentItem().getItem() != Items.GOLDEN_APPLE && OyVeyAutoCrystal.mc.player.inventory.getCurrentItem().getItem() != Items.BOW && OyVeyAutoCrystal.mc.player.inventory.getCurrentItem().getItem() != Items.EXPERIENCE_BOTTLE && (rayTraceResult = OyVeyAutoCrystal.mc.objectMouseOver) != null) {
            if (rayTraceResult.typeOfHit.equals((Object)RayTraceResult.Type.ENTITY)) {
                Entity entity = rayTraceResult.entityHit;
                if (entity instanceof EntityEnderCrystal) {
                    if (this.packetBreak.getValue().booleanValue()) {
                        OyVeyAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
                    } else {
                        OyVeyAutoCrystal.mc.playerController.attackEntity((EntityPlayer)OyVeyAutoCrystal.mc.player, entity);
                    }
                    this.manualTimer.reset();
                }
            } else if (rayTraceResult.typeOfHit.equals((Object)RayTraceResult.Type.BLOCK)) {
                BlockPos blockPos = new BlockPos((double)OyVeyAutoCrystal.mc.objectMouseOver.getBlockPos().getX(), (double)OyVeyAutoCrystal.mc.objectMouseOver.getBlockPos().getY() + 1.0, (double)OyVeyAutoCrystal.mc.objectMouseOver.getBlockPos().getZ());
                for (Entity entity : OyVeyAutoCrystal.mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(blockPos))) {
                    if (!(entity instanceof EntityEnderCrystal)) continue;
                    if (this.packetBreak.getValue().booleanValue()) {
                        OyVeyAutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
                    } else {
                        OyVeyAutoCrystal.mc.playerController.attackEntity((EntityPlayer)OyVeyAutoCrystal.mc.player, entity);
                    }
                    this.manualTimer.reset();
                }
            }
        }
    }

    private NonNullList<BlockPos> placePostions(float f) {
        NonNullList nonNullList = NonNullList.func_191196_a();
        nonNullList.addAll((Collection)OyVeyAutoCrystal.getSphere(new BlockPos(Math.floor(OyVeyAutoCrystal.mc.player.posX), Math.floor(OyVeyAutoCrystal.mc.player.posY), Math.floor(OyVeyAutoCrystal.mc.player.posZ)), f, (int)f, false, true, 0).stream().filter(blockPos -> this.canPlaceCrystal((BlockPos)blockPos, true)).collect(Collectors.toList()));
        return nonNullList;
    }

    private float getDamageMultiplied(float f) {
        int n = OyVeyAutoCrystal.mc.world.getDifficulty().getDifficultyId();
        return f * (n == 0 ? 0.0f : (n == 2 ? 1.0f : (n == 1 ? 0.5f : 1.5f)));
    }

    private boolean canSeePos(BlockPos blockPos) {
        return OyVeyAutoCrystal.mc.world.rayTraceBlocks(new Vec3d(OyVeyAutoCrystal.mc.player.posX, OyVeyAutoCrystal.mc.player.posY + (double)OyVeyAutoCrystal.mc.player.getEyeHeight(), OyVeyAutoCrystal.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()), false, true, false) == null;
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST, receiveCanceled=true)
    public void onPacketReceive(PacketEvent.Receive receive) {
        SPacketSpawnObject sPacketSpawnObject;
        if (receive.getPacket() instanceof SPacketSpawnObject && (sPacketSpawnObject = (SPacketSpawnObject)receive.getPacket()).getType() == 51 && this.predicts.getValue().booleanValue() && this.preditTimer.passedMs(this.attackFactor.getValue().longValue()) && this.predicts.getValue().booleanValue() && this.explode.getValue().booleanValue() && this.packetBreak.getValue().booleanValue() && this.target != null) {
            if (!this.isPredicting(sPacketSpawnObject)) {
                return;
            }
            CPacketUseEntity cPacketUseEntity = new CPacketUseEntity();
            cPacketUseEntity.entityId = sPacketSpawnObject.getEntityID();
            cPacketUseEntity.action = CPacketUseEntity.Action.ATTACK;
            OyVeyAutoCrystal.mc.player.connection.sendPacket((Packet)cPacketUseEntity);
        }
    }

    public static List<BlockPos> getSphere(BlockPos blockPos, float f, int n, boolean bl, boolean bl2, int n2) {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        int n3 = blockPos.getX();
        int n4 = blockPos.getY();
        int n5 = blockPos.getZ();
        int n6 = n3 - (int)f;
        while ((float)n6 <= (float)n3 + f) {
            int n7 = n5 - (int)f;
            while ((float)n7 <= (float)n5 + f) {
                int n8 = bl2 ? n4 - (int)f : n4;
                while (true) {
                    float f2 = bl2 ? (float)n4 + f : (float)(n4 + n);
                    float f3 = f2;
                    if (!((float)n8 < f2)) break;
                    double d = (n3 - n6) * (n3 - n6) + (n5 - n7) * (n5 - n7) + (bl2 ? (n4 - n8) * (n4 - n8) : 0);
                    if (!(!(d < (double)(f * f)) || bl && d < (double)((f - 1.0f) * (f - 1.0f)))) {
                        BlockPos blockPos2 = new BlockPos(n6, n8 + n2, n7);
                        arrayList.add(blockPos2);
                    }
                    ++n8;
                }
                ++n7;
            }
            ++n6;
        }
        return arrayList;
    }

    private boolean IsValidCrystal(Entity entity) {
        double d;
        if (entity == null) {
            return false;
        }
        if (!(entity instanceof EntityEnderCrystal)) {
            return false;
        }
        if (this.target == null) {
            return false;
        }
        if (entity.getDistanceToEntity((Entity)OyVeyAutoCrystal.mc.player) > this.breakRange.getValue().floatValue()) {
            return false;
        }
        if (!OyVeyAutoCrystal.mc.player.canEntityBeSeen(entity) && entity.getDistanceToEntity((Entity)OyVeyAutoCrystal.mc.player) > this.breakWallRange.getValue().floatValue()) {
            return false;
        }
        if (this.target.isDead || this.target.getHealth() + this.target.getAbsorptionAmount() <= 0.0f) {
            return false;
        }
        double d2 = this.calculateDamage((double)entity.getPosition().getX() + 0.5, (double)entity.getPosition().getY() + 1.0, (double)entity.getPosition().getZ() + 0.5, (Entity)this.target);
        if (EntityUtil.isInHole((Entity)OyVeyAutoCrystal.mc.player) && d2 >= 1.0) {
            return true;
        }
        double d3 = this.calculateDamage((double)entity.getPosition().getX() + 0.5, (double)entity.getPosition().getY() + 1.0, (double)entity.getPosition().getZ() + 0.5, (Entity)OyVeyAutoCrystal.mc.player);
        double d4 = d = this.suicide.getValue() != false ? 2.0 : 0.5;
        if (d3 + d < (double)(OyVeyAutoCrystal.mc.player.getHealth() + OyVeyAutoCrystal.mc.player.getAbsorptionAmount()) && d2 >= (double)(this.target.getAbsorptionAmount() + this.target.getHealth())) {
            return true;
        }
        this.armorTarget = false;
        for (ItemStack itemStack : this.target.getArmorInventoryList()) {
            float f = ((float)itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / (float)itemStack.getMaxDamage();
            float f2 = 1.0f - f;
            int n = 100 - (int)(f2 * 100.0f);
            if (!((float)n <= this.minArmor.getValue().floatValue())) continue;
            this.armorTarget = true;
        }
        if (d2 >= (double)this.breakMinDmg.getValue().floatValue() && d3 <= (double)this.breakMaxSelfDamage.getValue().floatValue()) {
            return true;
        }
        return EntityUtil.isInHole((Entity)this.target) && this.target.getHealth() + this.target.getAbsorptionAmount() <= this.facePlace.getValue().floatValue();
    }

    @SubscribeEvent(priority=EventPriority.HIGH, receiveCanceled=true)
    public void onSoundPacket(PacketEvent.Receive receive) {
        SPacketSoundEffect sPacketSoundEffect;
        if (OyVeyAutoCrystal.fullNullCheck()) {
            return;
        }
        if (receive.getPacket() instanceof SPacketSoundEffect && this.sound.getValue().booleanValue() && (sPacketSoundEffect = (SPacketSoundEffect)receive.getPacket()).getCategory() == SoundCategory.BLOCKS && sPacketSoundEffect.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
            ArrayList arrayList = new ArrayList(OyVeyAutoCrystal.mc.world.loadedEntityList);
            int n = arrayList.size();
            for (int i = 0; i < n; ++i) {
                Entity entity = (Entity)arrayList.get(i);
                if (!(entity instanceof EntityEnderCrystal) || !(entity.getDistanceSq(sPacketSoundEffect.getX(), sPacketSoundEffect.getY(), sPacketSoundEffect.getZ()) < 36.0)) continue;
                entity.setDead();
            }
        }
    }

    @Override
    public void onDisable() {
        this.rotating = false;
    }

    private boolean canPlaceCrystal(BlockPos blockPos, boolean bl) {
        BlockPos blockPos2 = blockPos.add(0, 1, 0);
        BlockPos blockPos3 = blockPos.add(0, 2, 0);
        try {
            if (!this.opPlace.getValue().booleanValue()) {
                if (OyVeyAutoCrystal.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && OyVeyAutoCrystal.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                    return false;
                }
                if (OyVeyAutoCrystal.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR || OyVeyAutoCrystal.mc.world.getBlockState(blockPos3).getBlock() != Blocks.AIR) {
                    return false;
                }
                if (!bl) {
                    return OyVeyAutoCrystal.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2)).isEmpty() && OyVeyAutoCrystal.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos3)).isEmpty();
                }
                for (Entity entity : OyVeyAutoCrystal.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2))) {
                    if (entity instanceof EntityEnderCrystal) continue;
                    return false;
                }
                for (Entity entity : OyVeyAutoCrystal.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos3))) {
                    if (entity instanceof EntityEnderCrystal) continue;
                    return false;
                }
            } else {
                if (OyVeyAutoCrystal.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && OyVeyAutoCrystal.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                    return false;
                }
                if (OyVeyAutoCrystal.mc.world.getBlockState(blockPos2).getBlock() != Blocks.AIR) {
                    return false;
                }
                if (!bl) {
                    return OyVeyAutoCrystal.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2)).isEmpty();
                }
                for (Entity entity : OyVeyAutoCrystal.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(blockPos2))) {
                    if (entity instanceof EntityEnderCrystal) continue;
                    return false;
                }
            }
        }
        catch (Exception exception) {
            return false;
        }
        return true;
    }

    public static enum SwitchMode {
        Normal,
        Silent;

    }

    public static enum SwingMode {
        MainHand,
        OffHand,
        None;

    }
}

