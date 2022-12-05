//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 */
package me.snow.aclient.manager;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import me.snow.aclient.module.Feature;
import me.snow.aclient.module.modules.client.Global;
import me.snow.aclient.module.modules.client.HUD;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionManager
extends Feature {
    private final /* synthetic */ Map<EntityPlayer, PotionList> potions;

    public void onTotemPop(EntityPlayer entityPlayer) {
        PotionList potionList = new PotionList();
        this.potions.put(entityPlayer, potionList);
    }

    public void update() {
        this.updatePlayer();
        if (HUD.getInstance().isOn() && HUD.getInstance().textRadar.getValue().booleanValue() && Global.getInstance().potions.getValue().booleanValue()) {
            ArrayList<EntityPlayer> arrayList = new ArrayList<EntityPlayer>();
            for (Map.Entry<EntityPlayer, PotionList> entityPlayer : this.potions.entrySet()) {
                boolean bl = true;
                for (EntityPlayer entityPlayer2 : PotionManager.mc.world.playerEntities) {
                    if (this.potions.get((Object)entityPlayer2) == null) {
                        PotionList potionList = new PotionList();
                        for (PotionEffect potionEffect : entityPlayer2.getActivePotionEffects()) {
                            potionList.addEffect(potionEffect);
                        }
                        this.potions.put(entityPlayer2, potionList);
                        bl = false;
                    }
                    if (!entityPlayer.getKey().equals((Object)entityPlayer2)) continue;
                    bl = false;
                }
                if (!bl) continue;
                arrayList.add(entityPlayer.getKey());
            }
            for (EntityPlayer entityPlayer : arrayList) {
                this.potions.remove((Object)entityPlayer);
            }
        }
    }

    public String getTextRadarPotion(EntityPlayer entityPlayer) {
        PotionEffect[] arrpotionEffect = this.getImportantPotions(entityPlayer);
        PotionEffect potionEffect = arrpotionEffect[0];
        PotionEffect potionEffect2 = arrpotionEffect[1];
        PotionEffect potionEffect3 = arrpotionEffect[2];
        return String.valueOf(new StringBuilder().append("").append(potionEffect != null ? String.valueOf(new StringBuilder().append("\u00a7c S").append(potionEffect.getAmplifier() + 1).append(" ")) : "").append(potionEffect2 != null ? "\u00a78 W " : "").append(potionEffect3 != null ? String.valueOf(new StringBuilder().append("\u00a7b S").append(potionEffect3.getAmplifier() + 1).append(" ")) : ""));
    }

    public List<PotionEffect> getPlayerPotions(EntityPlayer entityPlayer) {
        PotionList potionList = this.potions.get((Object)entityPlayer);
        List<PotionEffect> list = new ArrayList<PotionEffect>();
        if (potionList != null) {
            list = potionList.getEffects();
        }
        return list;
    }

    public String getTextRadarPotionWithDuration(EntityPlayer entityPlayer) {
        PotionEffect[] arrpotionEffect = this.getImportantPotions(entityPlayer);
        PotionEffect potionEffect = arrpotionEffect[0];
        PotionEffect potionEffect2 = arrpotionEffect[1];
        PotionEffect potionEffect3 = arrpotionEffect[2];
        return String.valueOf(new StringBuilder().append("").append(potionEffect != null ? String.valueOf(new StringBuilder().append("\u00a7c S").append(potionEffect.getAmplifier() + 1).append(" ").append(Potion.getPotionDurationString((PotionEffect)potionEffect, (float)1.0f))) : "").append(potionEffect2 != null ? String.valueOf(new StringBuilder().append("\u00a78 W ").append(Potion.getPotionDurationString((PotionEffect)potionEffect2, (float)1.0f))) : "").append(potionEffect3 != null ? String.valueOf(new StringBuilder().append("\u00a7b S").append(potionEffect3.getAmplifier() + 1).append(" ").append(Potion.getPotionDurationString((PotionEffect)Objects.requireNonNull(potionEffect2), (float)1.0f))) : ""));
    }

    public void updatePlayer() {
        PotionList potionList = new PotionList();
        for (PotionEffect potionEffect : PotionManager.mc.player.getActivePotionEffects()) {
            potionList.addEffect(potionEffect);
        }
        this.potions.put((EntityPlayer)PotionManager.mc.player, potionList);
    }

    public List<PotionEffect> getOwnPotions() {
        return this.getPlayerPotions((EntityPlayer)PotionManager.mc.player);
    }

    public PotionManager() {
        this.potions = new ConcurrentHashMap<EntityPlayer, PotionList>();
    }

    public String getColoredPotionString(PotionEffect potionEffect) {
        Potion potion = potionEffect.getPotion();
        switch (I18n.format((String)potion.getName(), (Object[])new Object[0])) {
            case "Jump Boost": 
            case "Speed": {
                return String.valueOf(new StringBuilder().append("\u00a7b").append(this.getPotionString(potionEffect)));
            }
            case "Resistance": 
            case "Strength": {
                return String.valueOf(new StringBuilder().append("\u00a7c").append(this.getPotionString(potionEffect)));
            }
            case "Wither": 
            case "Slowness": 
            case "Weakness": {
                return String.valueOf(new StringBuilder().append("\u00a70").append(this.getPotionString(potionEffect)));
            }
            case "Absorption": {
                return String.valueOf(new StringBuilder().append("\u00a79").append(this.getPotionString(potionEffect)));
            }
            case "Haste": 
            case "Fire Resistance": {
                return String.valueOf(new StringBuilder().append("\u00a76").append(this.getPotionString(potionEffect)));
            }
            case "Regeneration": {
                return String.valueOf(new StringBuilder().append("\u00a7d").append(this.getPotionString(potionEffect)));
            }
            case "Night Vision": 
            case "Poison": {
                return String.valueOf(new StringBuilder().append("\u00a7a").append(this.getPotionString(potionEffect)));
            }
        }
        return String.valueOf(new StringBuilder().append("\u00a7f").append(this.getPotionString(potionEffect)));
    }

    public PotionEffect[] getImportantPotions(EntityPlayer entityPlayer) {
        PotionEffect[] arrpotionEffect = new PotionEffect[3];
        for (PotionEffect potionEffect : this.getPlayerPotions(entityPlayer)) {
            Potion potion = potionEffect.getPotion();
            switch (I18n.format((String)potion.getName(), (Object[])new Object[0]).toLowerCase()) {
                case "strength": {
                    arrpotionEffect[0] = potionEffect;
                    break;
                }
                case "weakness": {
                    arrpotionEffect[1] = potionEffect;
                    break;
                }
                case "speed": {
                    arrpotionEffect[2] = potionEffect;
                }
            }
        }
        return arrpotionEffect;
    }

    public String getPotionString(PotionEffect potionEffect) {
        Potion potion = potionEffect.getPotion();
        return String.valueOf(new StringBuilder().append(I18n.format((String)potion.getName(), (Object[])new Object[0])).append(" ").append(potionEffect.getAmplifier() + 1).append(" ").append((Object)ChatFormatting.WHITE).append(Potion.getPotionDurationString((PotionEffect)potionEffect, (float)1.0f)));
    }

    public void onLogout() {
        this.potions.clear();
    }

    public static class PotionList {
        private final /* synthetic */ List<PotionEffect> effects;

        public void addEffect(PotionEffect potionEffect) {
            if (potionEffect != null) {
                this.effects.add(potionEffect);
            }
        }

        public PotionList() {
            this.effects = new ArrayList<PotionEffect>();
        }

        public List<PotionEffect> getEffects() {
            return this.effects;
        }
    }
}

