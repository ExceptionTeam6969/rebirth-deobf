//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.render;

import java.awt.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.managers.*;

public class ColorUtil
{
    public static int toHex(final int n, final int n2, final int n3) {
        return 0xFF000000 | (n & 0xFF) << 16 | (n2 & 0xFF) << 8 | (n3 & 0xFF);
    }
    
    public static int toARGB(final int n, final int n2, final int n3, final int n4) {
        return new Color(n, n2, n3, n4).getRGB();
    }
    
    public static Color gradientColor(final Color color, final Color color2, double n) {
        if (n > 1.0) {
            final double n2 = n % 1.0;
            n = (((int)n % 2 == 0) ? n2 : (1.0 - n2));
        }
        final double n3 = 1.0 - n;
        return new Color((int)(color.getRed() * n3 + color2.getRed() * n), (int)(color.getGreen() * n3 + color2.getGreen() * n), (int)(color.getBlue() * n3 + color2.getBlue() * n));
    }
    
    public static float[] toArray(final int n) {
        return new float[] { (n >> 16 & 0xFF) / 255.0f, (n >> 8 & 0xFF) / 255.0f, (n & 0xFF) / 255.0f, (n >> 24 & 0xFF) / 255.0f };
    }
    
    public static Color injectAlpha(final Color color, final int n) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), n);
    }
    
    public static Color rainbow(final int n) {
        final double ceil = Math.ceil(((((int)ClickGui.INSTANCE.rainbowSpeed.getValue() == 0) ? System.currentTimeMillis() : Managers.COLORS.rainbowProgress) + n) / 20.0);
        if (ClickGui.INSTANCE.rainbowMode.getValue() == ClickGui.Rainbow.DOUBLE) {
            return gradientColor((Color)ClickGui.INSTANCE.color.getValue(), (Color)ClickGui.INSTANCE.secondColor.getValue(), Math.abs(((((int)ClickGui.INSTANCE.rainbowSpeed.getValue() == 0) ? System.currentTimeMillis() : Managers.COLORS.rainbowProgress) % 2000L / 1000.0f + 20.0f / (n / 15 * 2 + 10) * 2.0f) % 2.0f - 1.0f));
        }
        if (ClickGui.INSTANCE.rainbowMode.getValue() == ClickGui.Rainbow.PLAIN) {
            return pulseColor((Color)ClickGui.INSTANCE.color.getValue(), 50, n);
        }
        return Color.getHSBColor((float)(ceil % 360.0 / 360.0), (float)ClickGui.INSTANCE.rainbowSaturation.getValue() / 255.0f, 1.0f);
    }
    
    public static Color interpolate(final float n, final Color color, final Color color2) {
        return new Color(color.getRed() / 255.0f * n + color2.getRed() / 255.0f * (1.0f - n), color.getGreen() / 255.0f * n + color2.getGreen() / 255.0f * (1.0f - n), color.getBlue() / 255.0f * n + color2.getBlue() / 255.0f * (1.0f - n), color.getAlpha() / 255.0f * n + color2.getAlpha() / 255.0f * (1.0f - n));
    }
    
    public static int toRGBA(final float n, final float n2, final float n3, final float n4) {
        return toRGBA((int)(n * 255.0f), (int)(n2 * 255.0f), (int)(n3 * 255.0f), (int)(n4 * 255.0f));
    }
    
    public static Color pulseColor(final Color color, final int n, final int n2) {
        final float[] array = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), array);
        array[2] = (Float.intBitsToFloat(Float.floatToIntBits(18.996923f) ^ 0x7E97F9B3) + Float.intBitsToFloat(Float.floatToIntBits(2.7958195f) ^ 0x7F32EEB5) * Math.abs((System.currentTimeMillis() % 2000L / Float.intBitsToFloat(Float.floatToIntBits(0.0013786979f) ^ 0x7ECEB56D) + n / (float)n2 * Float.intBitsToFloat(Float.floatToIntBits(0.09192204f) ^ 0x7DBC419F)) % Float.intBitsToFloat(Float.floatToIntBits(0.7858098f) ^ 0x7F492AD5) - Float.intBitsToFloat(Float.floatToIntBits(6.46708f) ^ 0x7F4EF252))) % Float.intBitsToFloat(Float.floatToIntBits(0.8992331f) ^ 0x7F663424);
        return new Color(Color.HSBtoRGB(array[0], array[1], array[2]));
    }
    
    public static int injectAlpha(final int n, final int n2) {
        return toRGBA(new Color(n).getRed(), new Color(n).getGreen(), new Color(n).getBlue(), n2);
    }
    
    public static int toRGBA(final int n, final int n2, final int n3, final int n4) {
        return (n << 16) + (n2 << 8) + n3 + (n4 << 24);
    }
    
    public static int toRGBA(final int n, final int n2, final int n3) {
        return toRGBA(n, n2, n3, 255);
    }
    
    public static int toRGBA(final Color color) {
        return toRGBA(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
    
    public static Color getGradientOffset(final Color color, final Color color2, double n) {
        if (n > 1.0) {
            final double n2 = n % 1.0;
            n = (((int)n % 2 == 0) ? n2 : (1.0 - n2));
        }
        final double n3 = 1.0 - n;
        return new Color((int)(color.getRed() * n3 + color2.getRed() * n), (int)(color.getGreen() * n3 + color2.getGreen() * n), (int)(color.getBlue() * n3 + color2.getBlue() * n));
    }
    
    public static class Colors
    {
        public static final int ORANGE;
        public static final int YELLOW;
        public static final int PURPLE;
        public static final int GREEN;
        public static final int RED;
        public static final int GRAY;
        public static final int BLACK;
        public static final int RAINBOW;
        public static final int DARK_RED;
        public static final int WHITE;
        public static final int BLUE;
        
        static {
            RAINBOW = Integer.MIN_VALUE;
            WHITE = ColorUtil.toRGBA(255, 255, 255, 255);
            BLACK = ColorUtil.toRGBA(0, 0, 0, 255);
            RED = ColorUtil.toRGBA(255, 0, 0, 255);
            GREEN = ColorUtil.toRGBA(0, 255, 0, 255);
            BLUE = ColorUtil.toRGBA(0, 0, 255, 255);
            ORANGE = ColorUtil.toRGBA(255, 128, 0, 255);
            PURPLE = ColorUtil.toRGBA(163, 73, 163, 255);
            GRAY = ColorUtil.toRGBA(127, 127, 127, 255);
            DARK_RED = ColorUtil.toRGBA(64, 0, 0, 255);
            YELLOW = ColorUtil.toRGBA(255, 255, 0, 255);
        }
    }
}
