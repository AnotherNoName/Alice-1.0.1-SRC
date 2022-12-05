//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\1.12.2"!

/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package me.snow.aclient.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import me.snow.aclient.core.setting.Setting;
import me.snow.aclient.module.Feature;
import me.snow.aclient.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayer;

public class FriendManager
extends Feature {
    private final /* synthetic */ Map<String, UUID> friends;

    public Friend getFriendByName(String string) {
        UUID uUID = PlayerUtil.getUUIDFromName(string);
        if (uUID != null) {
            return new Friend(string, uUID);
        }
        return null;
    }

    public void onLoad() {
        this.friends.clear();
        this.clearSettings();
    }

    public void addFriend(Friend friend) {
        this.friends.put(friend.getUsername(), friend.getUuid());
    }

    public void addFriend(String string) {
        Friend friend = this.getFriendByName(string);
        if (friend != null) {
            this.friends.put(friend.getUsername(), friend.getUuid());
        }
    }

    public Map<String, UUID> getFriends() {
        return this.friends;
    }

    public boolean isFriend(String string) {
        return this.friends.get(string) != null;
    }

    public FriendManager() {
        super("Friends");
        this.friends = new HashMap<String, UUID>();
    }

    public boolean isFriend(EntityPlayer entityPlayer) {
        return this.isFriend(entityPlayer.getName());
    }

    public void removeFriend(String string) {
        this.friends.remove(string);
    }

    public void saveFriends() {
        this.clearSettings();
        for (Map.Entry<String, UUID> entry : this.friends.entrySet()) {
            this.register(new Setting<String>(entry.getValue().toString(), entry.getKey()));
        }
    }

    public static class Friend {
        private final /* synthetic */ String username;
        private final /* synthetic */ UUID uuid;

        public Friend(String string, UUID uUID) {
            this.username = string;
            this.uuid = uUID;
        }

        public UUID getUuid() {
            return this.uuid;
        }

        public boolean equals(Object object) {
            return object instanceof Friend && ((Friend)object).getUsername().equals(this.username) && ((Friend)object).getUuid().equals(this.uuid);
        }

        public String getUsername() {
            return this.username;
        }

        public int hashCode() {
            return this.username.hashCode() + this.uuid.hashCode();
        }
    }
}

