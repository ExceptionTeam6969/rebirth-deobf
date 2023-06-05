//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.gui.font;

import net.minecraft.client.renderer.texture.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.io.*;
import java.awt.*;
import org.lwjgl.opengl.*;

public class CFont
{
    protected int fontHeight;
    protected boolean antiAlias;
    protected boolean fractionalMetrics;
    protected Font font;
    private final float imgSize = 512.0f;
    protected final CharData[] charData;
    protected DynamicTexture tex;
    protected int charOffset;
    
    public int getHeight() {
        return (this.fontHeight - 8) / 2;
    }
    
    protected BufferedImage generateFontImage(final Font font, final boolean b, final boolean b2, final CharData[] array) {
        this.getClass();
        final int n = 512;
        final BufferedImage bufferedImage = new BufferedImage(n, n, 2);
        final Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
        graphics2D.setFont(font);
        graphics2D.setColor(new Color(255, 255, 255, 0));
        graphics2D.fillRect(0, 0, n, n);
        graphics2D.setColor(Color.WHITE);
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, b2 ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, b ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, b ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        final FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int n2 = 0;
        int storedX = 0;
        int storedY = 1;
        int n3 = 0;
        if (n3 < array.length) {
            final char c = (char)n3;
            final CharData charData = new CharData();
            final Rectangle2D stringBounds = fontMetrics.getStringBounds(String.valueOf(c), graphics2D);
            charData.width = stringBounds.getBounds().width + 8;
            charData.height = stringBounds.getBounds().height;
            if (storedX + charData.width >= n) {
                storedX = 0;
                storedY += n2;
                n2 = 0;
            }
            if (charData.height > n2) {
                final int height = charData.height;
            }
            charData.storedX = storedX;
            charData.storedY = storedY;
            if (charData.height > this.fontHeight) {
                this.fontHeight = charData.height;
            }
            array[n3] = charData;
            graphics2D.drawString(String.valueOf(c), storedX + 2, storedY + fontMetrics.getAscent());
            final int n4 = storedX + charData.width;
            ++n3;
            return null;
        }
        return bufferedImage;
    }
    
    public boolean isFractionalMetrics() {
        return this.fractionalMetrics;
    }
    
    public void drawChar(final CharData[] array, final char c, final float n, final float n2) throws ArrayIndexOutOfBoundsException {
        try {
            this.drawQuad(n, n2, (float)array[c].width, (float)array[c].height, (float)array[c].storedX, (float)array[c].storedY, (float)array[c].width, (float)array[c].height);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected DynamicTexture setupTexture(final Font font, final boolean b, final boolean b2, final CharData[] array) {
        final BufferedImage generateFontImage = this.generateFontImage(font, b, b2, array);
        try {
            return new DynamicTexture(generateFontImage);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void setFont(final Font font) {
        this.font = font;
        this.tex = this.setupTexture(font, this.antiAlias, this.fractionalMetrics, this.charData);
    }
    
    public int getStringWidth(final String s) {
        final int n = 0;
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n2 = 0;
        if (n2 < length) {
            final char c = charArray[n2];
            if (c < this.charData.length) {
                final int n3 = n + (this.charData[c].width - 8 + this.charOffset);
            }
            ++n2;
            return 0;
        }
        return n / 2;
    }
    
    public void setAntiAlias(final boolean antiAlias) {
        if (this.antiAlias != antiAlias) {
            this.antiAlias = antiAlias;
            this.tex = this.setupTexture(this.font, antiAlias, this.fractionalMetrics, this.charData);
        }
    }
    
    public CFont(final CustomFont customFont, final boolean antiAlias, final boolean fractionalMetrics) {
        this.charData = new CharData[256];
        this.fontHeight = -1;
        try {
            final Font deriveFont = Font.createFont(0, CFont.class.getResourceAsStream(customFont.getFile())).deriveFont(customFont.getSize()).deriveFont(customFont.getType());
            this.font = deriveFont;
            this.antiAlias = antiAlias;
            this.fractionalMetrics = fractionalMetrics;
            this.tex = this.setupTexture(deriveFont, antiAlias, fractionalMetrics, this.charData);
        }
        catch (IOException ex) {}
        catch (FontFormatException ex2) {}
    }
    
    protected void drawQuad(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        final float n9 = n5 / 512.0f;
        final float n10 = n6 / 512.0f;
        final float n11 = n7 / 512.0f;
        final float n12 = n8 / 512.0f;
        GL11.glTexCoord2f(n9 + n11, n10);
        GL11.glVertex2d((double)(n + n3), (double)n2);
        GL11.glTexCoord2f(n9, n10);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glTexCoord2f(n9, n10 + n12);
        GL11.glVertex2d((double)n, (double)(n2 + n4));
        GL11.glTexCoord2f(n9, n10 + n12);
        GL11.glVertex2d((double)n, (double)(n2 + n4));
        GL11.glTexCoord2f(n9 + n11, n10 + n12);
        GL11.glVertex2d((double)(n + n3), (double)(n2 + n4));
        GL11.glTexCoord2f(n9 + n11, n10);
        GL11.glVertex2d((double)(n + n3), (double)n2);
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void setFractionalMetrics(final boolean fractionalMetrics) {
        if (this.fractionalMetrics != fractionalMetrics) {
            this.fractionalMetrics = fractionalMetrics;
            this.tex = this.setupTexture(this.font, this.antiAlias, fractionalMetrics, this.charData);
        }
    }
    
    public CFont(final Font font, final boolean antiAlias, final boolean fractionalMetrics) {
        this.charData = new CharData[256];
        this.fontHeight = -1;
        this.font = font;
        this.antiAlias = antiAlias;
        this.fractionalMetrics = fractionalMetrics;
        this.tex = this.setupTexture(font, antiAlias, fractionalMetrics, this.charData);
    }
    
    public boolean isAntiAlias() {
        return this.antiAlias;
    }
    
    public int getStringHeight(final String s) {
        return this.getHeight();
    }
    
    public static class CustomFont
    {
        final String file;
        final int style;
        final float size;
        
        public int getType() {
            if (this.style > 3) {
                return 3;
            }
            return Math.max(this.style, 0);
        }
        
        public float getSize() {
            return this.size;
        }
        
        public String getFile() {
            return this.file;
        }
        
        public CustomFont(final String file, final float size, final int style) {
            this.file = file;
            this.size = size;
            this.style = style;
        }
    }
    
    protected static class CharData
    {
        public int storedX;
        public int storedY;
        public int height;
        public int width;
    }
}
