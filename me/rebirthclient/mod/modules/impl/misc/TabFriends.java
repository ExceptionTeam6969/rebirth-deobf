//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn��ǿ��������\1.12 stable mappings"!

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
                TabFriends.color = "��f";
                break;
            }
            case DarkRed: {
                TabFriends.color = "��4";
                break;
            }
            case Red: {
                TabFriends.color = "��c";
                break;
            }
            case Gold: {
                TabFriends.color = "��6";
                break;
            }
            case Yellow: {
                TabFriends.color = "��e";
                break;
            }
            case DarkGreen: {
                TabFriends.color = "��2";
                break;
            }
            case Green: {
                TabFriends.color = "��a";
                break;
            }
            case Aqua: {
                TabFriends.color = "��b";
                break;
            }
            case DarkAqua: {
                TabFriends.color = "��3";
                break;
            }
            case DarkBlue: {
                TabFriends.color = "��1";
                break;
            }
            case Blue: {
                TabFriends.color = "��9";
                break;
            }
            case LightPurple: {
                TabFriends.color = "��d";
                break;
            }
            case DarkPurple: {
                TabFriends.color = "��5";
                break;
            }
            case Gray: {
                TabFriends.color = "��7";
                break;
            }
            case DarkGray: {
                TabFriends.color = "��8";
                break;
            }
            case Black: {
                TabFriends.color = "��0";
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
            return "��7[" + TabFriends.color + "F��7] " + TabFriends.color + s;
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
