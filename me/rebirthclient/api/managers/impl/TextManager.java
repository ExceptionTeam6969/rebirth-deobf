//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import me.rebirthclient.mod.*;
import me.rebirthclient.api.util.*;
import net.minecraft.util.math.*;
import com.mojang.realmsclient.gui.*;
import java.awt.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.util.render.*;
import me.rebirthclient.mod.gui.font.*;

public class TextManager extends Mod
{
    public int scaledWidth;
    public int scaleFactor;
    public final String syncCode = "¡ì(¡ì)";
    public int scaledHeight;
    private CustomFont customFont;
    private final CustomFont iconFont;
    private final Timer idleTimer;
    private boolean idling;
    
    public int getMCStringWidth(String s) {
        final NameProtect instance = NameProtect.INSTANCE;
        s = (instance.isOn() ? s.replaceAll(TextManager.mc.getSession().getUsername(), (String)instance.name.getValue()) : s);
        return TextManager.mc.fontRenderer.getStringWidth(s);
    }
    
    public int getFontHeight() {
        if (FontMod.INSTANCE.isOn()) {
            return this.customFont.getStringHeight("A");
        }
        return TextManager.mc.fontRenderer.FONT_HEIGHT;
    }
    
    public String capitalSpace(String s) {
        s = s.replace("A", " A");
        s = s.replace("B", " B");
        s = s.replace("C", " C");
        s = s.replace("D", " D");
        s = s.replace("E", " E");
        s = s.replace("F", " F");
        s = s.replace("G", " G");
        s = s.replace("H", " H");
        s = s.replace("I", " I");
        s = s.replace("J", " J");
        s = s.replace("K", " K");
        s = s.replace("L", " L");
        s = s.replace("M", " M");
        s = s.replace("N", " N");
        s = s.replace("O", " O");
        s = s.replace("P", " P");
        s = s.replace("Q", " Q");
        s = s.replace("R", " R");
        s = s.replace("S", " S");
        s = s.replace("T", " T");
        s = s.replace("U", " U");
        s = s.replace("V", " V");
        s = s.replace("W", " W");
        s = s.replace("X", " X");
        s = s.replace("Y", " Y");
        s = s.replace("Z", " Z");
        s = s.replace("T P", "TP");
        s = s.replace("T N T", "TNT");
        s = s.replace("D M G", "DMG");
        s = s.replace("H U D", "HUD");
        s = s.replace("E S P", "ESP");
        s = s.replace("F P S", "FPS");
        s = s.replace("M C F", "MCF");
        s = s.replace("2 D", "2D");
        if (s.startsWith(" ")) {
            s = s.replaceFirst(" ", "");
        }
        return s;
    }
    
    public void drawStringWithShadow(final String s, final float n, final float n2, final int n3) {
        this.drawString(s, n, n2, n3, true);
    }
    
    public void drawMCString(String s, final float n, final float n2, final int n3, final boolean b) {
        final NameProtect instance = NameProtect.INSTANCE;
        s = (instance.isOn() ? s.replaceAll(TextManager.mc.getSession().getUsername(), (String)instance.name.getValue()) : s);
        TextManager.mc.fontRenderer.drawString(s, n, n2, n3, b);
    }
    
    public void updateResolution() {
        this.scaledWidth = TextManager.mc.displayWidth;
        this.scaledHeight = TextManager.mc.displayHeight;
        this.scaleFactor = 1;
        final boolean isUnicode = TextManager.mc.isUnicode();
        int guiScale = TextManager.mc.gameSettings.guiScale;
        if (guiScale == 0) {
            guiScale = 1000;
        }
        if (this.scaleFactor < guiScale && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240) {
            ++this.scaleFactor;
            return;
        }
        if (isUnicode && this.scaleFactor % 2 != 0 && this.scaleFactor != 1) {
            --this.scaleFactor;
        }
        final double n = this.scaledWidth / (double)this.scaleFactor;
        final double n2 = this.scaledHeight / (double)this.scaleFactor;
        this.scaledWidth = MathHelper.ceil(n);
        this.scaledHeight = MathHelper.ceil(n2);
    }
    
    public String normalizeCases(final Object o) {
        return Character.toUpperCase(o.toString().charAt(0)) + o.toString().toLowerCase().substring(1);
    }
    
    public int getStringWidth(String s) {
        final NameProtect instance = NameProtect.INSTANCE;
        s = (instance.isOn() ? s.replaceAll(TextManager.mc.getSession().getUsername(), (String)instance.name.getValue()) : s);
        if (FontMod.INSTANCE.isOn()) {
            return this.customFont.getStringWidth(s);
        }
        return TextManager.mc.fontRenderer.getStringWidth(s);
    }
    
    public String getPrefix() {
        return "¡ì(¡ì)¡ìf[¡ìrRebirth¡ìf] " + ChatFormatting.RESET;
    }
    
    public void drawStringIcon(String s, final float n, final float n2, final int n3) {
        final NameProtect instance = NameProtect.INSTANCE;
        s = (instance.isOn() ? s.replaceAll(TextManager.mc.getSession().getUsername(), (String)instance.name.getValue()) : s);
        this.iconFont.drawStringWithShadow(s, n, n2, n3);
    }
    
    public void init() {
        if (FontMod.INSTANCE == null) {
            FontMod.INSTANCE = new FontMod();
        }
        final FontMod instance = FontMod.INSTANCE;
        try {
            this.setFontRenderer(new Font((String)instance.font.getValue(), instance.getFont(), (int)instance.size.getValue()), (boolean)instance.antiAlias.getValue(), (boolean)instance.metrics.getValue());
        }
        catch (Exception ex) {}
    }
    
    public void setFontRenderer(final Font font, final boolean b, final boolean b2) {
        this.customFont = new CustomFont(font, b, b2);
    }
    
    public Font getCurrentFont() {
        return this.customFont.getFont();
    }
    
    public void drawRollingRainbowString(final String s, final float n, final float n2, final boolean b) {
        final int[] array = { 1 };
        final char[] charArray = s.toCharArray();
        final float n3 = 0.0f + n;
        final char[] array2 = charArray;
        final int length = array2.length;
        int n4 = 0;
        if (n4 < length) {
            final char c = array2[n4];
            this.drawString(String.valueOf(c), n3, n2, ColorUtil.rainbow(array[0] * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB(), b);
            final float n5 = n3 + this.getStringWidth(String.valueOf(c));
            ++array[0];
            ++n4;
        }
    }
    
    public int getFontHeight2() {
        if (FontMod.INSTANCE.isOn()) {
            return this.customFont.getStringHeight("A") + 3;
        }
        return TextManager.mc.fontRenderer.FONT_HEIGHT;
    }
    
    public String getIdleSign() {
        if (this.idleTimer.passedMs(500L)) {
            this.idling = !this.idling;
            this.idleTimer.reset();
        }
        if (this.idling) {
            return "_";
        }
        return "";
    }
    
    public TextManager() {
        this.idleTimer = new Timer();
        this.iconFont = new CustomFont(new CFont.CustomFont("/assets/minecraft/textures/rebirth/fonts/IconFont.ttf", 19.0f, 0), true, false);
        this.customFont = new CustomFont(new Font("Verdana", 0, 17), true, true);
        this.updateResolution();
    }
    
    public float drawString(String s, final float n, final float n2, final int n3, final boolean b) {
        final NameProtect instance = NameProtect.INSTANCE;
        s = (instance.isOn() ? s.replaceAll(TextManager.mc.getSession().getUsername(), (String)instance.name.getValue()) : s);
        if (FontMod.INSTANCE.isOn()) {
            if (b) {
                this.customFont.drawStringWithShadow(s, n, n2, n3);
            }
            else {
                this.customFont.drawString(s, n, n2, n3);
            }
            return n;
        }
        TextManager.mc.fontRenderer.drawString(s, n, n2, n3, b);
        return n;
    }
}
