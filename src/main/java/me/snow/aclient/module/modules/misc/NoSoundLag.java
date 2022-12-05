//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.network.play.server.SPacketSoundEffect
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.misc;

import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Set;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.module.Module;
import me.snow.aclient.module.modules.combat.autocrystal.AutoCrystal;
import me.snow.aclient.util.MathUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoSoundLag
extends Module {
    private static /* synthetic */ NoSoundLag instance;
    public /* synthetic */ Setting<Boolean> crystals;
    private static final /* synthetic */ Set<SoundEvent> BLACKLIST;
    public /* synthetic */ Setting<Float> soundRange;
    public /* synthetic */ Setting<Boolean> armor;

    public static NoSoundLag getInstance() {
        if (instance == null) {
            instance = new NoSoundLag();
        }
        return instance;
    }

    public static void removeEntities(SPacketSoundEffect sPacketSoundEffect, float f) {
        BlockPos blockPos = new BlockPos(sPacketSoundEffect.getX(), sPacketSoundEffect.getY(), sPacketSoundEffect.getZ());
        ArrayList<Entity> arrayList = new ArrayList<Entity>();
        if (NoSoundLag.fullNullCheck()) {
            return;
        }
        for (Entity entity : NoSoundLag.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityEnderCrystal) || !(entity.getDistanceSq(blockPos) <= MathUtil.square(f))) continue;
            arrayList.add(entity);
        }
        for (Entity entity : arrayList) {
            entity.setDead();
        }
    }

    public NoSoundLag() {
        super("NoSoundLag", "Prevents Lag through sound spam.", Module.Category.MISC, true, false, false);
        this.crystals = this.register(new Setting<Boolean>("Crystals", true));
        this.armor = this.register(new Setting<Boolean>("Armor", true));
        this.soundRange = this.register(new Setting<Float>("SoundRange", Float.valueOf(12.0f), Float.valueOf(0.0f), Float.valueOf(12.0f)));
        instance = this;
    }

    @SubscribeEvent
    public void onPacketReceived(PacketEvent.Receive receive) {
        if (receive != null && receive.getPacket() != null && NoSoundLag.mc.player != null && NoSoundLag.mc.world != null && receive.getPacket() instanceof SPacketSoundEffect) {
            SPacketSoundEffect sPacketSoundEffect = (SPacketSoundEffect)receive.getPacket();
            if (this.crystals.getValue().booleanValue() && sPacketSoundEffect.getCategory() == SoundCategory.BLOCKS && sPacketSoundEffect.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE && (AutoCrystal.getInstance().isOff() || !AutoCrystal.getInstance().sound.getValue().booleanValue() && AutoCrystal.getInstance().threadMode.getValue() != AutoCrystal.ThreadMode.SOUND)) {
                NoSoundLag.removeEntities(sPacketSoundEffect, this.soundRange.getValue().floatValue());
            }
            if (BLACKLIST.contains((Object)sPacketSoundEffect.getSound()) && this.armor.getValue().booleanValue()) {
                receive.setCanceled(true);
            }
        }
    }

    static {
        BLACKLIST = Sets.newHashSet((Object[])new SoundEvent[]{SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundEvents.field_191258_p, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, SoundEvents.ITEM_ARMOR_EQUIP_IRON, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER});
    }
}

