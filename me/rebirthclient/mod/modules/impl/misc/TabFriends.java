//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.client.network.*;
import net.minecraft.scoreboard.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.mod.modules.*;

public class TabFriends extends Module
{
    public static String color;
    public final Setting mode;
    public static TabFriends INSTANCE;
    public static Setting prefix;
    
    @Override
    public void onUpdate() {
        switch ((FriendColor)this.mode.getValue()) {
            case White: {
                TabFriends.color = "¡ìf";
                break;
            }
            case DarkRed: {
                TabFriends.color = "¡ì4";
                break;
            }
            case Red: {
                TabFriends.color = "¡ìc";
                break;
            }
            case Gold: {
                TabFriends.color = "¡ì6";
                break;
            }
            case Yellow: {
                TabFriends.color = "¡ìe";
                break;
            }
            case DarkGreen: {
                TabFriends.color = "¡ì2";
                break;
            }
            case Green: {
                TabFriends.color = "¡ìa";
                break;
            }
            case Aqua: {
                TabFriends.color = "¡ìb";
                break;
            }
            case DarkAqua: {
                TabFriends.color = "¡ì3";
                break;
            }
            case DarkBlue: {
                TabFriends.color = "¡ì1";
                break;
            }
            case Blue: {
                TabFriends.color = "¡ì9";
                break;
            }
            case LightPurple: {
                TabFriends.color = "¡ìd";
                break;
            }
            case DarkPurple: {
                TabFriends.color = "¡ì5";
                break;
            }
            case Gray: {
                TabFriends.color = "¡ì7";
                break;
            }
            case DarkGray: {
                TabFriends.color = "¡ì8";
                break;
            }
            case Black: {
                TabFriends.color = "¡ì0";
                break;
            }
            case None: {
                TabFriends.color = "";
                break;
            }
        }
    }
    
    static {
        TabFriends.color = "";
    }
    
    public static String getPlayerName(final NetworkPlayerInfo networkPlayerInfo) {
        final String s = (networkPlayerInfo.getDisplayName() != null) ? networkPlayerInfo.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName((Team)networkPlayerInfo.getPlayerTeam(), networkPlayerInfo.getGameProfile().getName());
        if (!Managers.FRIENDS.isFriend(s)) {
            return s;
        }
        if (TabFriends.prefix.getValue()) {
            return "¡ì7[" + TabFriends.color + "F¡ì7] " + TabFriends.color + s;
        }
        return TabFriends.color + s;
    }
    
    public TabFriends() {
        super("TabFriends", "Renders your friends differently in the tablist", Category.MISC);
        this.mode = this.add(new Setting("Color", FriendColor.Green));
        TabFriends.prefix = this.add(new Setting("Prefix", true));
        TabFriends.INSTANCE = this;
    }
    
    public enum FriendColor
    {
        DarkPurple("DarkPurple", 11), 
        Gray("Gray", 12), 
        White("White", 15), 
        DarkGray("DarkGray", 13), 
        Yellow("Yellow", 3), 
        LightPurple("LightPurple", 10), 
        Aqua("Aqua", 6), 
        Green("Green", 5), 
        Red("Red", 1), 
        Gold("Gold", 2), 
        DarkBlue("DarkBlue", 8), 
        Black("Black", 14), 
        DarkRed("DarkRed", 0), 
        DarkGreen("DarkGreen", 4);
        
        private static final FriendColor[] $VALUES;
        
        Blue("Blue", 9), 
        DarkAqua("DarkAqua", 7), 
        None("None", 16);
        
        private FriendColor(final String s, final int n) {
        }
        
        static {
            $VALUES = new FriendColor[] { FriendColor.DarkRed, FriendColor.Red, FriendColor.Gold, FriendColor.Yellow, FriendColor.DarkGreen, FriendColor.Green, FriendColor.Aqua, FriendColor.DarkAqua, FriendColor.DarkBlue, FriendColor.Blue, FriendColor.LightPurple, FriendColor.DarkPurple, FriendColor.Gray, FriendColor.DarkGray, FriendColor.Black, FriendColor.White, FriendColor.None };
        }
    }
}
