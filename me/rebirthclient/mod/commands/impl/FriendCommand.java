//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.commands.impl;

import me.rebirthclient.mod.commands.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.managers.impl.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;

public class FriendCommand extends Command
{
    public FriendCommand() {
        super("friend", new String[] { "<add/del/name/clear>", "<name>" });
    }
    
    public void execute(final String[] array) {
        if (array.length == 1) {
            if (Managers.FRIENDS.getFriends().isEmpty()) {
                sendMessage("Friend list empty D:.");
            }
            else {
                final String s = "Friends: ";
                final Iterator<FriendManager.Friend> iterator = Managers.FRIENDS.getFriends().iterator();
                if (iterator.hasNext()) {
                    final FriendManager.Friend friend = iterator.next();
                    try {
                        new StringBuilder().append(s).append(friend.getUsername()).append(", ").toString();
                    }
                    catch (Exception ex) {}
                    return;
                }
                sendMessage(s);
            }
            return;
        }
        if (array.length != 2) {
            if (array.length >= 2) {
                final String s2 = array[0];
                int n = -1;
                switch (s2.hashCode()) {
                    case 96417: {
                        if (Integer.valueOf("add".hashCode()).equals(s2.hashCode())) {
                            n = 0;
                            break;
                        }
                        break;
                    }
                    case 99339: {
                        if (Integer.valueOf("del".hashCode()).equals(s2.hashCode())) {
                            n = 1;
                            break;
                        }
                        break;
                    }
                }
                switch (n) {
                    case 0: {
                        Managers.FRIENDS.addFriend(array[1]);
                        sendMessage(ChatFormatting.GREEN + array[1] + " has been friended");
                    }
                    case 1: {
                        Managers.FRIENDS.removeFriend(array[1]);
                        sendMessage(ChatFormatting.RED + array[1] + " has been unfriended");
                    }
                    default: {
                        sendMessage("Unknown Command, try friend add/del (name)");
                        break;
                    }
                }
            }
            return;
        }
        if (Integer.valueOf(array[0].hashCode()).equals("reset".hashCode())) {
            Managers.FRIENDS.onLoad();
            sendMessage("Friends got reset.");
            return;
        }
        sendMessage(array[0] + (Managers.FRIENDS.isFriend(array[0]) ? " is friended." : " isn't friended."));
    }
}
