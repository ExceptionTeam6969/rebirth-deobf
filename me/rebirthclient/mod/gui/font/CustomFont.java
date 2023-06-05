//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.gui.font;

import net.minecraft.client.renderer.texture.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;

public class CustomFont extends CFont
{
    protected final CFont.CharData[] italicChars;
    private final int[] colorCode;
    protected final CFont.CharData[] boldChars;
    protected DynamicTexture texItalicBold;
    protected DynamicTexture texBold;
    protected DynamicTexture texItalic;
    protected final CFont.CharData[] boldItalicChars;
    
    public float drawCenteredString(final String s, final float n, final float n2, final int n3) {
        return this.drawString(s, n - this.getStringWidth(s) / 2.0f, n2, n3);
    }
    
    public CustomFont(final CFont.CustomFont customFont, final boolean b, final boolean b2) {
        super(customFont, b, b2);
        this.boldChars = new CFont.CharData[256];
        this.italicChars = new CFont.CharData[256];
        this.boldItalicChars = new CFont.CharData[256];
        this.colorCode = new int[32];
        this.setupMinecraftColorcodes();
        this.setupBoldItalicIDs();
    }
    
    public void setFractionalMetrics(final boolean fractionalMetrics) {
        super.setFractionalMetrics(fractionalMetrics);
        this.setupBoldItalicIDs();
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.setupBoldItalicIDs();
    }
    
    private void setupMinecraftColorcodes() {
        int n = 0;
        if (n < 32) {
            final int n2 = (n >> 3 & 0x1) * 85;
            int n3 = (n >> 2 & 0x1) * 170 + n2;
            int n4 = (n >> 1 & 0x1) * 170 + n2;
            int n5 = (n & 0x1) * 170 + n2;
            if (n == 6) {
                n3 += 85;
            }
            if (n >= 16) {
                n3 /= 4;
                n4 /= 4;
                n5 /= 4;
            }
            this.colorCode[n] = ((n3 & 0xFF) << 16 | (n4 & 0xFF) << 8 | (n5 & 0xFF));
            ++n;
        }
    }
    
    public float drawString(final String s, final float n, final float n2, final int n3) {
        return this.drawString(s, n, n2, n3, false);
    }
    
    public float drawStringWithShadow(final String s, final double n, final double n2, final int n3) {
        return Math.max(this.drawString(s, n + 1.0, n2 + 1.0, n3, true), this.drawString(s, n, n2, n3, false));
    }
    
    public float drawCenteredStringWithShadow(final String s, final float n, final float n2, final int n3) {
        return this.drawStringWithShadow(s, n - this.getStringWidth(s) / 2.0, n2, n3);
    }
    
    public void setAntiAlias(final boolean antiAlias) {
        super.setAntiAlias(antiAlias);
        this.setupBoldItalicIDs();
    }
    
    private void setupBoldItalicIDs() {
        this.texBold = this.setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
        this.texItalic = this.setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
        this.texItalicBold = this.setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics, this.boldItalicChars);
    }
    
    public int getStringWidth(final String s) {
        if (s == null) {
            return 0;
        }
        final int n = 0;
        final CFont.CharData[] charData = this.charData;
        final int length = s.length();
        int n2 = 0;
        if (n2 < length) {
            final char char1 = s.charAt(n2);
            if (char1 == '¡ì') {
                ++n2;
            }
            else if (char1 < charData.length) {
                final int n3 = n + (charData[char1].width - 8 + this.charOffset);
            }
            ++n2;
            return 0;
        }
        return n / 2;
    }
    
    private void drawLine(final double n, final double n2, final double n3, final double n4) {
        GL11.glDisable(3553);
        GL11.glLineWidth(1.0f);
        GL11.glBegin(1);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n3, n4);
        GL11.glEnd();
        GL11.glEnable(3553);
    }
    
    public CustomFont(final Font font, final boolean b, final boolean b2) {
        super(font, b, b2);
        this.boldChars = new CFont.CharData[256];
        this.italicChars = new CFont.CharData[256];
        this.boldItalicChars = new CFont.CharData[256];
        this.colorCode = new int[32];
        this.setupMinecraftColorcodes();
        this.setupBoldItalicIDs();
    }
    
    public float drawString(final String s, double n, double n2, int n3, final boolean b) {
        --n;
        n2 -= 2.0;
        if (s == null) {
            return 0.0f;
        }
        if (n3 == 553648127) {
            n3 = 16777215;
        }
        if ((n3 & 0xFC000000) == 0x0) {
            n3 |= 0xFF000000;
        }
        if (b) {
            n3 = ((n3 & 0xFCFCFC) >> 2 | (n3 & 0xFF000000));
        }
        final CFont.CharData[] charData = this.charData;
        final float n4 = (n3 >> 24 & 0xFF) / 255.0f;
        final boolean b2 = false;
        final boolean b3 = false;
        final boolean b4 = false;
        final boolean b5 = false;
        n *= 2.0;
        n2 *= 2.0;
        GL11.glPushMatrix();
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.color((n3 >> 16 & 0xFF) / 255.0f, (n3 >> 8 & 0xFF) / 255.0f, (n3 & 0xFF) / 255.0f, n4);
        final int length = s.length();
        GlStateManager.enableTexture2D();
        GlStateManager.bindTexture(this.tex.getGlTextureId());
        GL11.glBindTexture(3553, this.tex.getGlTextureId());
        int n5 = 0;
        if (n5 < length) {
            final char char1 = s.charAt(n5);
            if (char1 == '¡ì') {
                int index = 21;
                try {
                    index = "0123456789abcdefklmnor".indexOf(s.charAt(n5 + 1));
                }
                catch (Exception ex) {}
                if (index < 16) {
                    GlStateManager.bindTexture(this.tex.getGlTextureId());
                    final CFont.CharData[] charData2 = this.charData;
                    if (index < 0) {
                        index = 15;
                    }
                    if (b) {
                        index += 16;
                    }
                    final int n6 = this.colorCode[index];
                    GlStateManager.color((n6 >> 16 & 0xFF) / 255.0f, (n6 >> 8 & 0xFF) / 255.0f, (n6 & 0xFF) / 255.0f, n4);
                }
                else if (index == 17) {
                    if (b3) {
                        GlStateManager.bindTexture(this.texItalicBold.getGlTextureId());
                        final CFont.CharData[] boldItalicChars = this.boldItalicChars;
                    }
                    else {
                        GlStateManager.bindTexture(this.texBold.getGlTextureId());
                        final CFont.CharData[] boldChars = this.boldChars;
                    }
                }
                else if (index != 18) {
                    if (index != 19) {
                        if (index == 20) {
                            if (b2) {
                                GlStateManager.bindTexture(this.texItalicBold.getGlTextureId());
                                final CFont.CharData[] boldItalicChars2 = this.boldItalicChars;
                            }
                            else {
                                GlStateManager.bindTexture(this.texItalic.getGlTextureId());
                                final CFont.CharData[] italicChars = this.italicChars;
                            }
                        }
                        else if (index == 21) {
                            GlStateManager.color((n3 >> 16 & 0xFF) / 255.0f, (n3 >> 8 & 0xFF) / 255.0f, (n3 & 0xFF) / 255.0f, n4);
                            GlStateManager.bindTexture(this.tex.getGlTextureId());
                            final CFont.CharData[] charData3 = this.charData;
                        }
                    }
                }
                ++n5;
            }
            else if (char1 < charData.length) {
                GL11.glBegin(4);
                this.drawChar(charData, char1, (float)n, (float)n2);
                GL11.glEnd();
                if (b4) {
                    this.drawLine(n, n2 + charData[char1].height / 2.0, n + charData[char1].width - 8.0, n2 + charData[char1].height / 2.0);
                }
                if (b5) {
                    this.drawLine(n, n2 + charData[char1].height - 2.0, n + charData[char1].width - 8.0, n2 + charData[char1].height - 2.0);
                }
                n += charData[char1].width - 8 + this.charOffset;
            }
            ++n5;
            return 0.0f;
        }
        GL11.glHint(3155, 4352);
        GL11.glPopMatrix();
        return (float)n / 2.0f;
    }
}
