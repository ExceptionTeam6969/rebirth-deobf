//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import me.rebirthclient.mod.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.player.*;
import java.util.*;
import java.util.function.*;

public class FriendManager extends Mod
{
    private List friends;
    
    private static boolean lambda$isFriend$0(final String s, final Friend friend) {
        return Integer.valueOf(s.toUpperCase().hashCode()).equals(Friend.access$000(friend).toUpperCase().hashCode());
    }
    
    public FriendManager() {
        super("Friends");
        this.friends = new ArrayList();
    }
    
    public void addFriend(final Friend friend) {
        this.friends.add(friend);
    }
    
    private static boolean lambda$cleanFriends$1(final Friend friend) {
        return friend.getUsername() != null;
    }
    
    public Friend getFriendByName(final String s) {
        final UUID uuidFromName = ProfileUtil.getUUIDFromName(s);
        if (uuidFromName != null) {
            return new Friend(s, uuidFromName);
        }
        return null;
    }
    
    public void saveFriends() {
        this.resetSettings();
        this.cleanFriends();
        final Iterator<Friend> iterator = this.friends.iterator();
        if (iterator.hasNext()) {
            final Friend friend = iterator.next();
            this.add(new Setting(friend.getUuid().toString(), friend.getUsername()));
        }
    }
    
    public boolean isCool(final String s) {
        return Arrays.asList("iMadCat").contains(s);
    }
    
    public void addFriend(final String s) {
        final Friend friendByName = this.getFriendByName(s);
        if (friendByName != null) {
            this.friends.add(friendByName);
        }
        this.cleanFriends();
    }
    
    public List getFriends() {
        this.cleanFriends();
        return this.friends;
    }
    
    public boolean isFriend(final EntityPlayer entityPlayer) {
        return this.isFriend(entityPlayer.getName());
    }
    
    public void onLoad() {
        this.friends = new ArrayList();
        this.resetSettings();
    }
    
    public void cleanFriends() {
        this.friends.stream().filter(Objects::nonNull).filter(FriendManager::lambda$cleanFriends$1);
    }
    
    public boolean isFriend(final String s) {
        if (Integer.valueOf("¡ìaYou".hashCode()).equals(s.hashCode())) {
            return true;
        }
        this.cleanFriends();
        return this.friends.stream().anyMatch(FriendManager::lambda$isFriend$0);
    }
    
    public void removeFriend(final String s) {
        this.cleanFriends();
        final Iterator<Friend> iterator = this.friends.iterator();
        if (iterator.hasNext()) {
            final Friend friend = iterator.next();
            if (!Integer.valueOf(s.toUpperCase().hashCode()).equals(friend.getUsername().toUpperCase().hashCode())) {
                return;
            }
            this.friends.remove(friend);
        }
    }
    
    public static class Friend
    {
        private final UUID uuid;
        private final String username;
        
        public String getUsername() {
            return this.username;
        }
        
        public Friend(final String username, final UUID uuid) {
            this.username = username;
            this.uuid = uuid;
        }
        
        static String access$000(final Friend friend) {
            return friend.username;
        }
        
        public UUID getUuid() {
            return this.uuid;
        }
    }
}
