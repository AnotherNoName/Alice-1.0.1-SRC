//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.MoverType
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.init.MobEffects
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.network.play.server.SPacketDestroyEntities
 *  net.minecraft.network.play.server.SPacketEntityStatus
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package me.snow.aclient.module.modules.player;

import com.mojang.authlib.GameProfile;
import java.util.Random;
import java.util.UUID;
import me.snow.aclient.AliceMain;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.event.EnumStages;
import me.snow.aclient.event.events.EventTotemPop;
import me.snow.aclient.event.events.PacketEvent;
import me.snow.aclient.manager.TotemPopManager2;
import me.snow.aclient.module.Module;
import me.snow.aclient.util.skid.oyvey.DamageUtil2;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketDestroyEntities;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FakePlayer
extends Module {
    public final /* synthetic */ Setting<Boolean> pop;
    private /* synthetic */ EntityOtherPlayerMP fakePlayer;
    public final /* synthetic */ Setting<Boolean> inv;
    public final /* synthetic */ Setting<Boolean> move;

    @Override
    public void onEnable() {
        if (FakePlayer.nullCheck()) {
            return;
        }
        this.fakePlayer = new EntityOtherPlayerMP((World)FakePlayer.mc.world, new GameProfile(UUID.fromString("fdee323e-7f0c-4c15-8d1c-0f277442342a"), "Fit"));
        this.fakePlayer.copyLocationAndAnglesFrom((Entity)FakePlayer.mc.player);
        this.fakePlayer.rotationYawHead = FakePlayer.mc.player.rotationYawHead;
        if (this.inv.getValue().booleanValue()) {
            this.fakePlayer.inventory.copyInventory(FakePlayer.mc.player.inventory);
        }
        FakePlayer.mc.world.addEntityToWorld(-100, (Entity)this.fakePlayer);
    }

    @Override
    public void onDisable() {
        try {
            if (FakePlayer.nullCheck()) {
                return;
            }
            FakePlayer.mc.world.removeEntity((Entity)this.fakePlayer);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive receive) {
        block12: {
            if (this.pop.getValue().booleanValue()) {
                try {
                    if (!(receive.getPacket() instanceof SPacketDestroyEntities)) break block12;
                    SPacketDestroyEntities sPacketDestroyEntities = (SPacketDestroyEntities)receive.getPacket();
                    for (int n : sPacketDestroyEntities.getEntityIDs()) {
                        Entity entity = FakePlayer.mc.world.getEntityByID(n);
                        if (!(entity instanceof EntityEnderCrystal) || !(entity.getDistanceSqToEntity((Entity)this.fakePlayer) < 144.0)) continue;
                        float f = DamageUtil2.calculateDamage(entity.getPositionVector(), (Entity)this.fakePlayer);
                        float f2 = this.fakePlayer.getAbsorptionAmount() - f;
                        boolean bl = f2 < 0.0f;
                        float f3 = this.fakePlayer.getHealth() + f2;
                        if (bl && f3 > 0.0f) {
                            this.fakePlayer.setHealth(f3);
                            this.fakePlayer.setAbsorptionAmount(0.0f);
                        } else if (f3 > 0.0f) {
                            this.fakePlayer.setAbsorptionAmount(f2);
                        } else {
                            this.fakePlayer.setHealth(2.0f);
                            this.fakePlayer.setAbsorptionAmount(8.0f);
                            this.fakePlayer.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 5));
                            this.fakePlayer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1));
                            try {
                                FakePlayer.mc.player.connection.handleEntityStatus(new SPacketEntityStatus((Entity)this.fakePlayer, 35));
                            }
                            catch (Exception exception) {
                                // empty catch block
                            }
                            if (TotemPopManager2.totemMap.containsKey((Object)this.fakePlayer)) {
                                int n2 = TotemPopManager2.totemMap.get((Object)this.fakePlayer) + 1;
                                AliceMain.dispatcher.post(new EventTotemPop(EnumStages.PRE, (EntityLivingBase)this.fakePlayer, n2));
                                TotemPopManager2.totemMap.remove((Object)this.fakePlayer);
                                TotemPopManager2.totemMap.put((EntityLivingBase)this.fakePlayer, n2);
                            } else {
                                AliceMain.dispatcher.post(new EventTotemPop(EnumStages.PRE, (EntityLivingBase)this.fakePlayer, 1));
                                TotemPopManager2.totemMap.put((EntityLivingBase)this.fakePlayer, 1);
                            }
                        }
                        this.fakePlayer.hurtTime = 5;
                    }
                }
                catch (Exception exception) {
                    return;
                }
            }
        }
    }

    private void doPop(Entity entity) {
        FakePlayer.mc.effectRenderer.func_191271_a(entity, EnumParticleTypes.TOTEM, 30);
        FakePlayer.mc.world.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.field_191263_gW, entity.getSoundCategory(), 1.0f, 1.0f, false);
    }

    @Override
    public void onUpdate() {
        if (FakePlayer.nullCheck()) {
            this.setEnabled(false);
        }
    }

    @Override
    public void onLogout() {
        this.disable();
    }

    public FakePlayer() {
        super("FakePlayer", "Spawns in a fake player for testing purposes", Module.Category.PLAYER, true, false, false);
        this.inv = this.register(new Setting<Boolean>("Copy Inventory", true));
        this.pop = this.register(new Setting<Boolean>("Can Pop Totems", true));
        this.move = this.register(new Setting<Boolean>("Can Move", true));
    }

    public void travel(float f, float f2, float f3) {
        double d = this.fakePlayer.posY;
        float f4 = 0.8f;
        float f5 = 0.02f;
        float f6 = EnchantmentHelper.getDepthStriderModifier((EntityLivingBase)this.fakePlayer);
        if (f6 > 3.0f) {
            f6 = 3.0f;
        }
        if (!this.fakePlayer.onGround) {
            f6 *= 0.5f;
        }
        if (f6 > 0.0f) {
            f4 += (0.54600006f - f4) * f6 / 3.0f;
            f5 += (this.fakePlayer.getAIMoveSpeed() - f5) * f6 / 4.0f;
        }
        this.fakePlayer.func_191958_b(f, f2, f3, f5);
        this.fakePlayer.moveEntity(MoverType.SELF, this.fakePlayer.motionX, this.fakePlayer.motionY, this.fakePlayer.motionZ);
        this.fakePlayer.motionX *= (double)f4;
        this.fakePlayer.motionY *= (double)0.8f;
        this.fakePlayer.motionZ *= (double)f4;
        if (!this.fakePlayer.hasNoGravity()) {
            this.fakePlayer.motionY -= 0.02;
        }
        if (this.fakePlayer.isCollidedHorizontally && this.fakePlayer.isOffsetPositionInLiquid(this.fakePlayer.motionX, this.fakePlayer.motionY + (double)0.6f - this.fakePlayer.posY + d, this.fakePlayer.motionZ)) {
            this.fakePlayer.motionY = 0.3f;
        }
    }

    @Override
    public void onTick() {
        if (this.fakePlayer != null) {
            Random random = new Random();
            this.fakePlayer.field_191988_bg = FakePlayer.mc.player.field_191988_bg + (float)random.nextInt(5) / 10.0f;
            this.fakePlayer.moveStrafing = FakePlayer.mc.player.moveStrafing + (float)random.nextInt(5) / 10.0f;
            if (this.move.getValue().booleanValue()) {
                this.travel(this.fakePlayer.moveStrafing, this.fakePlayer.moveForward, this.fakePlayer.field_191988_bg);
            }
        }
    }
}

