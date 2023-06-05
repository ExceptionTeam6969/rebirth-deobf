//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.render;

import java.util.*;
import com.mojang.realmsclient.gui.*;
import java.util.regex.*;

public class TextUtil
{
    public static final String DARK_GREEN;
    public static final String DARK_RED;
    public static final String DARK_GRAY;
    public static final String RESET;
    public static final String YELLOW;
    public static final String BLUE;
    public static final String GOLD;
    public static final String RED;
    public static final String GREEN;
    public static final String STRIKE;
    public static final String ITALIC;
    private static final Random rand;
    public static final String DARK_AQUA;
    public static final String OBFUSCATED;
    public static final String DARK_BLUE;
    public static final String BOLD;
    public static final String DARK_PURPLE;
    public static final String LIGHT_PURPLE;
    public static final String GRAY;
    public static final String AQUA;
    public static final String BLACK;
    public static final String UNDERLINE;
    public static final String WHITE;
    
    public static String coloredString(final String s, final Color color) {
        String s2 = s;
        switch (color) {
            case AQUA: {
                s2 = ChatFormatting.AQUA + s2 + ChatFormatting.RESET;
                break;
            }
            case WHITE: {
                s2 = ChatFormatting.WHITE + s2 + ChatFormatting.RESET;
                break;
            }
            case BLACK: {
                s2 = ChatFormatting.BLACK + s2 + ChatFormatting.RESET;
                break;
            }
            case DARK_BLUE: {
                s2 = ChatFormatting.DARK_BLUE + s2 + ChatFormatting.RESET;
                break;
            }
            case DARK_GREEN: {
                s2 = ChatFormatting.DARK_GREEN + s2 + ChatFormatting.RESET;
                break;
            }
            case DARK_AQUA: {
                s2 = ChatFormatting.DARK_AQUA + s2 + ChatFormatting.RESET;
                break;
            }
            case DARK_RED: {
                s2 = ChatFormatting.DARK_RED + s2 + ChatFormatting.RESET;
                break;
            }
            case DARK_PURPLE: {
                s2 = ChatFormatting.DARK_PURPLE + s2 + ChatFormatting.RESET;
                break;
            }
            case GOLD: {
                s2 = ChatFormatting.GOLD + s2 + ChatFormatting.RESET;
                break;
            }
            case DARK_GRAY: {
                s2 = ChatFormatting.DARK_GRAY + s2 + ChatFormatting.RESET;
                break;
            }
            case GRAY: {
                s2 = ChatFormatting.GRAY + s2 + ChatFormatting.RESET;
                break;
            }
            case BLUE: {
                s2 = ChatFormatting.BLUE + s2 + ChatFormatting.RESET;
                break;
            }
            case RED: {
                s2 = ChatFormatting.RED + s2 + ChatFormatting.RESET;
                break;
            }
            case GREEN: {
                s2 = ChatFormatting.GREEN + s2 + ChatFormatting.RESET;
                break;
            }
            case LIGHT_PURPLE: {
                s2 = ChatFormatting.LIGHT_PURPLE + s2 + ChatFormatting.RESET;
                break;
            }
            case YELLOW: {
                s2 = ChatFormatting.YELLOW + s2 + ChatFormatting.RESET;
                break;
            }
        }
        return s2;
    }
    
    public static String stripColor(final String s) {
        if (s != null) {
            return Pattern.compile("(?i)¡ì[0-9A-FK-OR]").matcher(s).replaceAll("");
        }
        return "";
    }
    
    public static String cropMaxLengthMessage(final String s, final int n) {
        String substring = "";
        if (s.length() >= 256 - n) {
            substring = s.substring(0, 256 - n);
        }
        return substring;
    }
    
    static {
        BLACK = String.valueOf(ChatFormatting.BLACK);
        DARK_BLUE = String.valueOf(ChatFormatting.DARK_BLUE);
        DARK_GREEN = String.valueOf(ChatFormatting.DARK_GREEN);
        DARK_AQUA = String.valueOf(ChatFormatting.DARK_AQUA);
        DARK_RED = String.valueOf(ChatFormatting.DARK_RED);
        DARK_PURPLE = String.valueOf(ChatFormatting.DARK_PURPLE);
        GOLD = String.valueOf(ChatFormatting.GOLD);
        GRAY = String.valueOf(ChatFormatting.GRAY);
        DARK_GRAY = String.valueOf(ChatFormatting.DARK_GRAY);
        BLUE = String.valueOf(ChatFormatting.BLUE);
        GREEN = String.valueOf(ChatFormatting.GREEN);
        AQUA = String.valueOf(ChatFormatting.AQUA);
        RED = String.valueOf(ChatFormatting.RED);
        LIGHT_PURPLE = String.valueOf(ChatFormatting.LIGHT_PURPLE);
        YELLOW = String.valueOf(ChatFormatting.YELLOW);
        WHITE = String.valueOf(ChatFormatting.WHITE);
        OBFUSCATED = String.valueOf(ChatFormatting.OBFUSCATED);
        BOLD = String.valueOf(ChatFormatting.BOLD);
        STRIKE = String.valueOf(ChatFormatting.STRIKETHROUGH);
        UNDERLINE = String.valueOf(ChatFormatting.UNDERLINE);
        ITALIC = String.valueOf(ChatFormatting.ITALIC);
        RESET = String.valueOf(ChatFormatting.RESET);
        rand = new Random();
    }
    
    public enum Color
    {
        DARK_RED("DARK_RED", 6), 
        BLUE("BLUE", 11), 
        DARK_GREEN("DARK_GREEN", 4), 
        GRAY("GRAY", 9), 
        WHITE("WHITE", 1), 
        DARK_AQUA("DARK_AQUA", 5), 
        NONE("NONE", 0), 
        DARK_BLUE("DARK_BLUE", 3), 
        DARK_GRAY("DARK_GRAY", 10), 
        GOLD("GOLD", 8), 
        LIGHT_PURPLE("LIGHT_PURPLE", 15), 
        GREEN("GREEN", 12), 
        DARK_PURPLE("DARK_PURPLE", 7), 
        RED("RED", 14), 
        BLACK("BLACK", 2), 
        YELLOW("YELLOW", 16), 
        AQUA("AQUA", 13);
        
        private static final Color[] $VALUES;
        
        private Color(final String s, final int n) {
        }
        
        static {
            $VALUES = new Color[] { Color.NONE, Color.WHITE, Color.BLACK, Color.DARK_BLUE, Color.DARK_GREEN, Color.DARK_AQUA, Color.DARK_RED, Color.DARK_PURPLE, Color.GOLD, Color.GRAY, Color.DARK_GRAY, Color.BLUE, Color.GREEN, Color.AQUA, Color.RED, Color.LIGHT_PURPLE, Color.YELLOW };
        }
    }
}
