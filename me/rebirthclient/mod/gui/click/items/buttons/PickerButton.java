//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.gui.click.items.buttons;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.*;
import me.rebirthclient.api.managers.*;
import java.awt.*;
import java.io.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.util.render.*;
import me.rebirthclient.mod.gui.screen.*;
import org.lwjgl.opengl.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import java.awt.datatransfer.*;
import me.rebirthclient.mod.commands.*;
import java.util.*;

public class PickerButton extends Button
{
    public static final Tessellator tessellator;
    private Color finalColor;
    public static final BufferBuilder builder;
    boolean pickingAlpha;
    private float hueX;
    private float prevAlphaX;
    private float alphaX;
    private float prevHueX;
    boolean pickingColor;
    final Setting setting;
    boolean pickingHue;
    
    public static void drawLeftGradientRect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        PickerButton.builder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        PickerButton.builder.pos((double)n3, (double)n2, 0.0).color((n6 >> 24 & 0xFF) / 255.0f, (n6 >> 16 & 0xFF) / 255.0f, (n6 >> 8 & 0xFF) / 255.0f, (n6 >> 24 & 0xFF) / 255.0f).endVertex();
        PickerButton.builder.pos((double)n, (double)n2, 0.0).color((n5 >> 16 & 0xFF) / 255.0f, (n5 >> 8 & 0xFF) / 255.0f, (n5 & 0xFF) / 255.0f, (n5 >> 24 & 0xFF) / 255.0f).endVertex();
        PickerButton.builder.pos((double)n, (double)n4, 0.0).color((n5 >> 16 & 0xFF) / 255.0f, (n5 >> 8 & 0xFF) / 255.0f, (n5 & 0xFF) / 255.0f, (n5 >> 24 & 0xFF) / 255.0f).endVertex();
        PickerButton.builder.pos((double)n3, (double)n4, 0.0).color((n6 >> 24 & 0xFF) / 255.0f, (n6 >> 16 & 0xFF) / 255.0f, (n6 >> 8 & 0xFF) / 255.0f, (n6 >> 24 & 0xFF) / 255.0f).endVertex();
        PickerButton.tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public void setAlphaX(final float alphaX) {
        if (this.alphaX == alphaX) {
            return;
        }
        this.prevAlphaX = this.alphaX;
        this.alphaX = alphaX;
    }
    
    public float getAlphaX() {
        if (Managers.FPS.getFPS() < 20) {
            return this.alphaX;
        }
        return this.alphaX = this.prevAlphaX + (this.alphaX - this.prevAlphaX) * PickerButton.mc.getRenderPartialTicks() / (8.0f * (Math.min(240, Managers.FPS.getFPS()) / 240.0f));
    }
    
    public PickerButton(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.finalColor = (Color)setting.getValue();
    }
    
    public float getHueX() {
        if (Managers.FPS.getFPS() < 20) {
            return this.hueX;
        }
        return this.hueX = this.prevHueX + (this.hueX - this.prevHueX) * PickerButton.mc.getRenderPartialTicks() / (8.0f * (Math.min(240, Managers.FPS.getFPS()) / 240.0f));
    }
    
    public boolean isInsideRainbow(final int n, final int n2) {
        return mouseOver((int)((int)this.x + 2.3f), (int)this.y + 124, (int)((int)this.x + 2.3f) + Managers.TEXT.getStringWidth("rainbow"), (int)(this.y + 123.0f) + Managers.TEXT.getFontHeight(), n, n2);
    }
    
    public boolean isInsideCopy(final int n, final int n2) {
        return mouseOver((int)((int)this.x + 2.3f), (int)this.y + 113, (int)((int)this.x + 2.3f) + Managers.TEXT.getStringWidth("copy"), (int)(this.y + 112.0f) + Managers.TEXT.getFontHeight(), n, n2);
    }
    
    public void drawHueSlider(final int n, int n2, final int n3, final int n4, final float n5) {
        int n6 = 0;
        if (n4 > n3) {
            RenderUtil.drawRect((float)n, (float)n2, (float)(n + n3), (float)(n2 + 4), -65536);
            n2 += 4;
            int n7 = 0;
            if (n7 < 6) {
                drawGradientRect((float)n, n2 + n6 * (n4 / 6.0f), (float)(n + n3), n2 + (n6 + 1) * (n4 / 6.0f), Color.HSBtoRGB(n6 / 6.0f, 1.0f, 1.0f), Color.HSBtoRGB((n6 + 1) / 6.0f, 1.0f, 1.0f), false);
                ++n6;
                ++n7;
                return;
            }
            final int n8 = (int)(n2 + n4 * n5) - 4;
            RenderUtil.drawRect((float)n, (float)(n8 - 1), (float)(n + n3), (float)(n8 + 1), -1);
            drawOutlineRect(n, n8 - 1, n + n3, n8 + 1, Color.BLACK, 1.0f);
        }
        else {
            int n9 = 0;
            if (n9 < 6) {
                gradient(n + n6 * (n3 / 6), n2, n + (n6 + 1) * (n3 / 6) + 3, n2 + n4, Color.HSBtoRGB(n6 / 6.0f, 1.0f, 1.0f), Color.HSBtoRGB((n6 + 1) / 6.0f, 1.0f, 1.0f), true);
                ++n6;
                ++n9;
                return;
            }
            final int n10 = (int)(n + n3 * n5);
            RenderUtil.drawRect((float)(n10 - 1), n2 - 1.2f, (float)(n10 + 1), n2 + n4 + 1.2f, -1);
            drawOutlineRect(n10 - 1.2, n2 - 1.2, n10 + 1.2, n2 + n4 + 1.2, Color.BLACK, 0.1f);
        }
    }
    
    public void drawPicker(final Setting p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: newarray        F
        //     3: dup            
        //     4: iconst_0       
        //     5: fconst_0       
        //     6: fastore        
        //     7: dup            
        //     8: iconst_1       
        //     9: fconst_0       
        //    10: fastore        
        //    11: dup            
        //    12: iconst_2       
        //    13: fconst_0       
        //    14: fastore        
        //    15: dup            
        //    16: iconst_3       
        //    17: fconst_0       
        //    18: fastore        
        //    19: astore          11
        //    21: iconst_4       
        //    22: newarray        F
        //    24: dup            
        //    25: iconst_0       
        //    26: aload_1        
        //    27: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    30: checkcast       Ljava/awt/Color;
        //    33: invokevirtual   java/awt/Color.getRed:()I
        //    36: aload_1        
        //    37: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    40: checkcast       Ljava/awt/Color;
        //    43: invokevirtual   java/awt/Color.getGreen:()I
        //    46: aload_1        
        //    47: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    50: checkcast       Ljava/awt/Color;
        //    53: invokevirtual   java/awt/Color.getBlue:()I
        //    56: aconst_null    
        //    57: invokestatic    java/awt/Color.RGBtoHSB:(III[F)[F
        //    60: iconst_0       
        //    61: faload         
        //    62: fastore        
        //    63: dup            
        //    64: iconst_1       
        //    65: aload_1        
        //    66: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    69: checkcast       Ljava/awt/Color;
        //    72: invokevirtual   java/awt/Color.getRed:()I
        //    75: aload_1        
        //    76: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    79: checkcast       Ljava/awt/Color;
        //    82: invokevirtual   java/awt/Color.getGreen:()I
        //    85: aload_1        
        //    86: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    89: checkcast       Ljava/awt/Color;
        //    92: invokevirtual   java/awt/Color.getBlue:()I
        //    95: aconst_null    
        //    96: invokestatic    java/awt/Color.RGBtoHSB:(III[F)[F
        //    99: iconst_1       
        //   100: faload         
        //   101: fastore        
        //   102: dup            
        //   103: iconst_2       
        //   104: aload_1        
        //   105: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   108: checkcast       Ljava/awt/Color;
        //   111: invokevirtual   java/awt/Color.getRed:()I
        //   114: aload_1        
        //   115: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   118: checkcast       Ljava/awt/Color;
        //   121: invokevirtual   java/awt/Color.getGreen:()I
        //   124: aload_1        
        //   125: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   128: checkcast       Ljava/awt/Color;
        //   131: invokevirtual   java/awt/Color.getBlue:()I
        //   134: aconst_null    
        //   135: invokestatic    java/awt/Color.RGBtoHSB:(III[F)[F
        //   138: iconst_2       
        //   139: faload         
        //   140: fastore        
        //   141: dup            
        //   142: iconst_3       
        //   143: aload_1        
        //   144: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   147: checkcast       Ljava/awt/Color;
        //   150: invokevirtual   java/awt/Color.getAlpha:()I
        //   153: i2f            
        //   154: ldc             255.0
        //   156: fdiv           
        //   157: fastore        
        //   158: astore          11
        //   160: goto            176
        //   163: astore          12
        //   165: getstatic       me/rebirthclient/Rebirth.LOGGER:Lorg/apache/logging/log4j/Logger;
        //   168: ldc_w           "rebirth color picker says it's a bad color!"
        //   171: invokeinterface org/apache/logging/log4j/Logger.info:(Ljava/lang/String;)V
        //   176: aload_0        
        //   177: getfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.width:I
        //   180: i2f            
        //   181: ldc_w           7.4
        //   184: fadd           
        //   185: f2i            
        //   186: istore          12
        //   188: bipush          78
        //   190: istore          13
        //   192: iload           12
        //   194: iconst_3       
        //   195: iadd           
        //   196: istore          14
        //   198: bipush          7
        //   200: istore          15
        //   202: bipush          7
        //   204: istore          16
        //   206: aload_0        
        //   207: getfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.pickingColor:Z
        //   210: ifeq            242
        //   213: iconst_0       
        //   214: invokestatic    org/lwjgl/input/Mouse.isButtonDown:(I)Z
        //   217: ifeq            237
        //   220: iload_2        
        //   221: iload_3        
        //   222: iload_2        
        //   223: iload           12
        //   225: iadd           
        //   226: iload_3        
        //   227: iload           13
        //   229: iadd           
        //   230: iload           8
        //   232: iload           9
        //   234: ifeq            242
        //   237: aload_0        
        //   238: iconst_0       
        //   239: putfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.pickingColor:Z
        //   242: aload_0        
        //   243: getfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.pickingHue:Z
        //   246: ifeq            282
        //   249: iconst_0       
        //   250: invokestatic    org/lwjgl/input/Mouse.isButtonDown:(I)Z
        //   253: ifeq            277
        //   256: iload           4
        //   258: iload           5
        //   260: iload           4
        //   262: iload           14
        //   264: iadd           
        //   265: iload           5
        //   267: iload           15
        //   269: iadd           
        //   270: iload           8
        //   272: iload           9
        //   274: ifeq            282
        //   277: aload_0        
        //   278: iconst_0       
        //   279: putfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.pickingHue:Z
        //   282: aload_0        
        //   283: getfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.pickingAlpha:Z
        //   286: ifeq            322
        //   289: iconst_0       
        //   290: invokestatic    org/lwjgl/input/Mouse.isButtonDown:(I)Z
        //   293: ifeq            317
        //   296: iload           6
        //   298: iload           7
        //   300: iload           6
        //   302: iload           12
        //   304: iadd           
        //   305: iload           7
        //   307: iload           16
        //   309: iadd           
        //   310: iload           8
        //   312: iload           9
        //   314: ifeq            322
        //   317: aload_0        
        //   318: iconst_0       
        //   319: putfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.pickingAlpha:Z
        //   322: iconst_0       
        //   323: invokestatic    org/lwjgl/input/Mouse.isButtonDown:(I)Z
        //   326: ifeq            351
        //   329: iload_2        
        //   330: iload_3        
        //   331: iload_2        
        //   332: iload           12
        //   334: iadd           
        //   335: iload_3        
        //   336: iload           13
        //   338: iadd           
        //   339: iload           8
        //   341: iload           9
        //   343: ifeq            351
        //   346: aload_0        
        //   347: iconst_1       
        //   348: putfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.pickingColor:Z
        //   351: iconst_0       
        //   352: invokestatic    org/lwjgl/input/Mouse.isButtonDown:(I)Z
        //   355: ifeq            384
        //   358: iload           4
        //   360: iload           5
        //   362: iload           4
        //   364: iload           14
        //   366: iadd           
        //   367: iload           5
        //   369: iload           15
        //   371: iadd           
        //   372: iload           8
        //   374: iload           9
        //   376: ifeq            384
        //   379: aload_0        
        //   380: iconst_1       
        //   381: putfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.pickingHue:Z
        //   384: iconst_0       
        //   385: invokestatic    org/lwjgl/input/Mouse.isButtonDown:(I)Z
        //   388: ifeq            417
        //   391: iload           6
        //   393: iload           7
        //   395: iload           6
        //   397: iload           12
        //   399: iadd           
        //   400: iload           7
        //   402: iload           16
        //   404: iadd           
        //   405: iload           8
        //   407: iload           9
        //   409: ifeq            417
        //   412: aload_0        
        //   413: iconst_1       
        //   414: putfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.pickingAlpha:Z
        //   417: aload_0        
        //   418: getfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.pickingHue:Z
        //   421: ifeq            456
        //   424: iload           4
        //   426: iload           8
        //   428: invokestatic    java/lang/Math.max:(II)I
        //   431: iload           4
        //   433: iload           14
        //   435: iadd           
        //   436: invokestatic    java/lang/Math.min:(II)I
        //   439: i2f            
        //   440: fstore          10
        //   442: aload           11
        //   444: iconst_0       
        //   445: fload           10
        //   447: iload           4
        //   449: i2f            
        //   450: fsub           
        //   451: iload           14
        //   453: i2f            
        //   454: fdiv           
        //   455: fastore        
        //   456: aload_0        
        //   457: getfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.pickingAlpha:Z
        //   460: ifeq            504
        //   463: aload_1        
        //   464: getfield        me/rebirthclient/mod/modules/settings/Setting.hideAlpha:Z
        //   467: ifne            504
        //   470: iload           6
        //   472: iload           8
        //   474: invokestatic    java/lang/Math.max:(II)I
        //   477: iload           6
        //   479: iload           12
        //   481: iadd           
        //   482: invokestatic    java/lang/Math.min:(II)I
        //   485: i2f            
        //   486: fstore          10
        //   488: aload           11
        //   490: iconst_3       
        //   491: fconst_1       
        //   492: fload           10
        //   494: iload           6
        //   496: i2f            
        //   497: fsub           
        //   498: iload           12
        //   500: i2f            
        //   501: fdiv           
        //   502: fsub           
        //   503: fastore        
        //   504: aload_0        
        //   505: getfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.pickingColor:Z
        //   508: ifeq            571
        //   511: iload_2        
        //   512: iload           8
        //   514: invokestatic    java/lang/Math.max:(II)I
        //   517: iload_2        
        //   518: iload           12
        //   520: iadd           
        //   521: invokestatic    java/lang/Math.min:(II)I
        //   524: i2f            
        //   525: fstore          10
        //   527: iload_3        
        //   528: iload           9
        //   530: invokestatic    java/lang/Math.max:(II)I
        //   533: iload_3        
        //   534: iload           13
        //   536: iadd           
        //   537: invokestatic    java/lang/Math.min:(II)I
        //   540: i2f            
        //   541: fstore          17
        //   543: aload           11
        //   545: iconst_1       
        //   546: fload           10
        //   548: iload_2        
        //   549: i2f            
        //   550: fsub           
        //   551: iload           12
        //   553: i2f            
        //   554: fdiv           
        //   555: fastore        
        //   556: aload           11
        //   558: iconst_2       
        //   559: fconst_1       
        //   560: fload           17
        //   562: iload_3        
        //   563: i2f            
        //   564: fsub           
        //   565: iload           13
        //   567: i2f            
        //   568: fdiv           
        //   569: fsub           
        //   570: fastore        
        //   571: aload           11
        //   573: iconst_0       
        //   574: faload         
        //   575: fconst_1       
        //   576: fconst_1       
        //   577: invokestatic    java/awt/Color.HSBtoRGB:(FFF)I
        //   580: istore          17
        //   582: iload           17
        //   584: bipush          16
        //   586: ishr           
        //   587: sipush          255
        //   590: iand           
        //   591: i2f            
        //   592: ldc             255.0
        //   594: fdiv           
        //   595: fstore          18
        //   597: iload           17
        //   599: bipush          8
        //   601: ishr           
        //   602: sipush          255
        //   605: iand           
        //   606: i2f            
        //   607: ldc             255.0
        //   609: fdiv           
        //   610: fstore          19
        //   612: iload           17
        //   614: sipush          255
        //   617: iand           
        //   618: i2f            
        //   619: ldc             255.0
        //   621: fdiv           
        //   622: fstore          20
        //   624: iload_2        
        //   625: iload_3        
        //   626: iload           12
        //   628: iload           13
        //   630: fload           18
        //   632: fload           19
        //   634: fload           20
        //   636: aload           11
        //   638: iconst_3       
        //   639: faload         
        //   640: invokestatic    me/rebirthclient/mod/gui/click/items/buttons/PickerButton.drawPickerBase:(IIIIFFFF)V
        //   643: aload_0        
        //   644: iload           4
        //   646: iload           5
        //   648: iload           12
        //   650: iconst_1       
        //   651: iadd           
        //   652: iload           15
        //   654: aload           11
        //   656: iconst_0       
        //   657: faload         
        //   658: invokevirtual   me/rebirthclient/mod/gui/click/items/buttons/PickerButton.drawHueSlider:(IIIIF)V
        //   661: iload_2        
        //   662: i2f            
        //   663: aload           11
        //   665: iconst_1       
        //   666: faload         
        //   667: iload           12
        //   669: i2f            
        //   670: fmul           
        //   671: fadd           
        //   672: f2i            
        //   673: istore          21
        //   675: iload_3        
        //   676: iload           13
        //   678: iadd           
        //   679: i2f            
        //   680: aload           11
        //   682: iconst_2       
        //   683: faload         
        //   684: iload           13
        //   686: i2f            
        //   687: fmul           
        //   688: fsub           
        //   689: f2i            
        //   690: istore          22
        //   692: aload_0        
        //   693: getfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.pickingColor:Z
        //   696: ifeq            758
        //   699: iload           21
        //   701: i2f            
        //   702: iload           22
        //   704: i2f            
        //   705: ldc_w           6.4
        //   708: getstatic       java/awt/Color.BLACK:Ljava/awt/Color;
        //   711: invokevirtual   java/awt/Color.getRGB:()I
        //   714: invokestatic    me/rebirthclient/api/util/render/RenderUtil.drawCircle:(FFFI)V
        //   717: iload           21
        //   719: i2f            
        //   720: iload           22
        //   722: i2f            
        //   723: ldc             6.0
        //   725: aload_0        
        //   726: getfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.finalColor:Ljava/awt/Color;
        //   729: invokevirtual   java/awt/Color.getRed:()I
        //   732: aload_0        
        //   733: getfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.finalColor:Ljava/awt/Color;
        //   736: invokevirtual   java/awt/Color.getGreen:()I
        //   739: aload_0        
        //   740: getfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.finalColor:Ljava/awt/Color;
        //   743: invokevirtual   java/awt/Color.getBlue:()I
        //   746: sipush          255
        //   749: invokestatic    me/rebirthclient/api/util/render/ColorUtil.toARGB:(IIII)I
        //   752: invokestatic    me/rebirthclient/api/util/render/RenderUtil.drawCircle:(FFFI)V
        //   755: goto            789
        //   758: iload           21
        //   760: i2f            
        //   761: iload           22
        //   763: i2f            
        //   764: ldc_w           3.4
        //   767: getstatic       java/awt/Color.BLACK:Ljava/awt/Color;
        //   770: invokevirtual   java/awt/Color.getRGB:()I
        //   773: invokestatic    me/rebirthclient/api/util/render/RenderUtil.drawCircle:(FFFI)V
        //   776: iload           21
        //   778: i2f            
        //   779: iload           22
        //   781: i2f            
        //   782: ldc_w           3.0
        //   785: iconst_m1      
        //   786: invokestatic    me/rebirthclient/api/util/render/RenderUtil.drawCircle:(FFFI)V
        //   789: aload_1        
        //   790: getfield        me/rebirthclient/mod/modules/settings/Setting.hideAlpha:Z
        //   793: ifne            820
        //   796: aload_0        
        //   797: iload           6
        //   799: iload           7
        //   801: iload           12
        //   803: iconst_1       
        //   804: isub           
        //   805: iload           16
        //   807: fload           18
        //   809: fload           19
        //   811: fload           20
        //   813: aload           11
        //   815: iconst_3       
        //   816: faload         
        //   817: invokevirtual   me/rebirthclient/mod/gui/click/items/buttons/PickerButton.drawAlphaSlider:(IIIIFFFF)V
        //   820: aload_0        
        //   821: new             Ljava/awt/Color;
        //   824: dup            
        //   825: aload           11
        //   827: iconst_0       
        //   828: faload         
        //   829: aload           11
        //   831: iconst_1       
        //   832: faload         
        //   833: aload           11
        //   835: iconst_2       
        //   836: faload         
        //   837: invokestatic    java/awt/Color.HSBtoRGB:(FFF)I
        //   840: invokespecial   java/awt/Color.<init>:(I)V
        //   843: aload           11
        //   845: iconst_3       
        //   846: faload         
        //   847: invokestatic    me/rebirthclient/mod/gui/click/items/buttons/PickerButton.getColor:(Ljava/awt/Color;F)Ljava/awt/Color;
        //   850: putfield        me/rebirthclient/mod/gui/click/items/buttons/PickerButton.finalColor:Ljava/awt/Color;
        //   853: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  21     160    163    176    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0417 (coming from #0409).
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2183)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.lang.Thread.run(Thread.java:750)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean isInsidePaste(final int n, final int n2) {
        return mouseOver((int)(this.x + this.width - 2.3f - Managers.TEXT.getStringWidth("paste") + 11.7f - 4.6f), (int)this.y + 113, (int)(this.x + this.width - 2.3f - Managers.TEXT.getStringWidth("paste") + 11.7f - 4.6f) + Managers.TEXT.getStringWidth("paste"), (int)(this.y + 112.0f) + Managers.TEXT.getFontHeight(), n, n2);
    }
    
    public static Color getColor(final Color color, final float n) {
        return new Color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, n);
    }
    
    public static String readClipboard() {
        try {
            return (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        }
        catch (UnsupportedFlavorException | IOException ex) {
            return null;
        }
    }
    
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        final boolean b = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.NEW;
        final boolean b2 = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.FUTURE;
        final boolean b3 = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.DOTGOD;
        RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, (b2 || b3) ? ((this.setting.hasBoolean && this.setting.booleanValue) ? (this.isHovering(n, n2) ? Managers.COLORS.getCurrentWithAlpha(90) : Managers.COLORS.getCurrentWithAlpha(65)) : (this.isHovering(n, n2) ? Managers.COLORS.getCurrentWithAlpha(55) : Managers.COLORS.getCurrentWithAlpha(26))) : (this.setting.hasBoolean ? (this.setting.booleanValue ? (this.isHovering(n, n2) ? Managers.COLORS.getCurrentWithAlpha(200) : Managers.COLORS.getCurrentWithAlpha(120)) : (this.isHovering(n, n2) ? -2007673515 : 290805077)) : (this.isHovering(n, n2) ? -2007673515 : 290805077)));
        try {
            RenderUtil.drawRect(this.x - 1.5f + this.width + 0.6f - 0.5f, this.y + 5.0f, this.x + this.width + 7.0f - 2.5f, this.y + this.height - 4.0f, (this.setting.isRainbow && !this.setting.noRainbow) ? ColorUtil.injectAlpha(Managers.COLORS.getRainbow(), this.finalColor.getAlpha()).getRGB() : this.finalColor.getRGB());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        Managers.TEXT.drawStringWithShadow((b || b3) ? this.getName().toLowerCase() : this.getName(), this.x + 2.3f, this.y - 1.7f - Gui.INSTANCE.getTextOffset(), (b3 && this.setting.hasBoolean && this.setting.booleanValue) ? Managers.COLORS.getCurrentGui(240) : (b3 ? 11579568 : -1));
        if (this.setting.open) {
            this.drawPicker(this.setting, (int)this.x, (int)this.y + 15, (int)this.x, this.setting.hideAlpha ? ((int)this.y + 100) : ((int)this.y + 103), (int)this.x, (int)this.y + 95, n, n2);
            Managers.TEXT.drawStringWithShadow("copy", this.x + 2.3f, this.y + 113.0f, this.isInsideCopy(n, n2) ? -1 : -5592406);
            Managers.TEXT.drawStringWithShadow("paste", this.x + this.width - 2.3f - Managers.TEXT.getStringWidth("paste") + 11.7f - 4.6f, this.y + 113.0f, this.isInsidePaste(n, n2) ? -1 : -5592406);
            if (!this.setting.noRainbow) {
                Managers.TEXT.drawStringWithShadow("rainbow", this.x + 2.3f, this.y + 124.0f, this.setting.isRainbow ? Managers.COLORS.getRainbow().getRGB() : (this.isInsideRainbow(n, n2) ? -1 : -5592406));
            }
            this.setting.setValue(this.finalColor);
        }
    }
    
    public void setHueX(final float hueX) {
        if (this.hueX == hueX) {
            return;
        }
        this.prevHueX = this.hueX;
        this.hueX = hueX;
    }
    
    static {
        tessellator = Tessellator.getInstance();
        builder = PickerButton.tessellator.getBuffer();
    }
    
    public static int gradientColor(final int n, final int n2) {
        return new Color(((n & 0xFF0000) >> 16) * (100 + n2) / 100, ((n & 0xFF00) >> 8) * (100 + n2) / 100, (n & 0xFF) * (100 + n2) / 100).hashCode();
    }
    
    public static void drawPickerBase(final int n, final int n2, final int n3, final int n4, final float n5, final float n6, final float n7, final float n8) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glShadeModel(7425);
        GL11.glBegin(9);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glVertex2f((float)n, (float)n2);
        GL11.glVertex2f((float)n, (float)(n2 + n4));
        GL11.glColor4f(n5, n6, n7, n8);
        GL11.glVertex2f((float)(n + n3), (float)(n2 + n4));
        GL11.glVertex2f((float)(n + n3), (float)n2);
        GL11.glEnd();
        GL11.glDisable(3008);
        GL11.glBegin(9);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glVertex2f((float)n, (float)n2);
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
        GL11.glVertex2f((float)n, (float)(n2 + n4));
        GL11.glVertex2f((float)(n + n3), (float)(n2 + n4));
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
        GL11.glVertex2f((float)(n + n3), (float)n2);
        GL11.glEnd();
        GL11.glEnable(3008);
        GL11.glShadeModel(7424);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
    
    public void drawAlphaSlider(final int n, final int n2, final int n3, final int n4, final float n5, final float n6, final float n7, final float n8) {
        final boolean b = true;
        final int n9 = n4 / 2;
        final int n10 = -n9;
        if (n10 < n3) {
            if (!b) {
                RenderUtil.drawRect((float)(n + n10), (float)n2, (float)(n + n10 + n9), (float)(n2 + n4), -1);
                RenderUtil.drawRect((float)(n + n10), (float)(n2 + n9), (float)(n + n10 + n9), (float)(n2 + n4), -7303024);
                if (n10 < n3 - n9) {
                    final int n11 = n + n10 + n9;
                    final int min = Math.min(n + n3, n + n10 + n9 * 2);
                    RenderUtil.drawRect((float)n11, (float)n2, (float)min, (float)(n2 + n4), -7303024);
                    RenderUtil.drawRect((float)n11, (float)(n2 + n9), (float)min, (float)(n2 + n4), -1);
                }
            }
            final boolean b2 = !b;
            return;
        }
        drawLeftGradientRect(n, n2, n + n3, n2 + n4, new Color(n5, n6, n7, 1.0f).getRGB(), 0);
        final int n12 = (int)(n + n3 - n3 * n8);
        RenderUtil.drawRect((float)(n12 - 1), n2 - 1.2f, (float)(n12 + 1), n2 + n4 + 1.2f, -1);
        drawOutlineRect(n12 - 1.2, n2 - 1.2, n12 + 1.2, n2 + n4 + 1.2, Color.BLACK, 0.1f);
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
        this.pickingAlpha = false;
        this.pickingHue = false;
        this.pickingColor = false;
    }
    
    public static void drawOutlineRect(double n, double n2, double n3, double n4, final Color color, final float n5) {
        if (n < n3) {
            final double n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n2 < n4) {
            final double n7 = n2;
            n2 = n4;
            n4 = n7;
        }
        final float n8 = (color.getRGB() >> 24 & 0xFF) / 255.0f;
        final float n9 = (color.getRGB() >> 16 & 0xFF) / 255.0f;
        final float n10 = (color.getRGB() >> 8 & 0xFF) / 255.0f;
        final float n11 = (color.getRGB() & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GL11.glPolygonMode(1032, 6913);
        GL11.glLineWidth(n5);
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(n9, n10, n11, n8);
        getBuffer.begin(7, DefaultVertexFormats.POSITION);
        getBuffer.pos(n, n4, 0.0).endVertex();
        getBuffer.pos(n3, n4, 0.0).endVertex();
        getBuffer.pos(n3, n2, 0.0).endVertex();
        getBuffer.pos(n, n2, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GL11.glPolygonMode(1032, 6914);
    }
    
    public static void drawGradientRect(final float n, final float n2, final float n3, final float n4, int gradientColor, int gradientColor2, final boolean b) {
        if (b) {
            gradientColor = gradientColor(gradientColor, -20);
            gradientColor2 = gradientColor(gradientColor2, -20);
        }
        final float n5 = (gradientColor >> 24 & 0xFF) / 255.0f;
        final float n6 = (gradientColor >> 16 & 0xFF) / 255.0f;
        final float n7 = (gradientColor >> 8 & 0xFF) / 255.0f;
        final float n8 = (gradientColor & 0xFF) / 255.0f;
        final float n9 = (gradientColor2 >> 24 & 0xFF) / 255.0f;
        final float n10 = (gradientColor2 >> 16 & 0xFF) / 255.0f;
        final float n11 = (gradientColor2 >> 8 & 0xFF) / 255.0f;
        final float n12 = (gradientColor2 & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n3, (double)n2, 0.0).color(n6, n7, n8, n5).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).color(n6, n7, n8, n5).endVertex();
        getBuffer.pos((double)n, (double)n4, 0.0).color(n10, n11, n12, n9).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n10, n11, n12, n9).endVertex();
        getInstance.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    
    public static void gradient(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b) {
        if (b) {
            final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
            final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
            final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
            final float n10 = (n5 & 0xFF) / 255.0f;
            final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
            final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
            final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
            final float n14 = (n6 & 0xFF) / 255.0f;
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glBlendFunc(770, 771);
            GL11.glShadeModel(7425);
            GL11.glBegin(9);
            GL11.glColor4f(n8, n9, n10, n7);
            GL11.glVertex2f((float)n, (float)n2);
            GL11.glVertex2f((float)n, (float)n4);
            GL11.glColor4f(n12, n13, n14, n11);
            GL11.glVertex2f((float)n3, (float)n4);
            GL11.glVertex2f((float)n3, (float)n2);
            GL11.glEnd();
            GL11.glShadeModel(7424);
            GL11.glEnable(3553);
            GL11.glDisable(3042);
        }
        else {
            drawGradientRect(n, n2, n3, n4, n5, n6);
        }
    }
    
    public static void drawGradientRect(final double n, final double n2, final double n3, final double n4, final int n5, final int n6) {
        final float n7 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n10 = (n5 & 0xFF) / 255.0f;
        final float n11 = (n6 >> 24 & 0xFF) / 255.0f;
        final float n12 = (n6 >> 16 & 0xFF) / 255.0f;
        final float n13 = (n6 >> 8 & 0xFF) / 255.0f;
        final float n14 = (n6 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(n8, n9, n10, n7);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n, n4);
        GL11.glColor4f(n12, n13, n14, n11);
        GL11.glVertex2d(n3, n4);
        GL11.glVertex2d(n3, n2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        if (n3 == 1 && this.isHovering(n, n2)) {
            PickerButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            this.setting.open = !this.setting.open;
        }
        if (n3 == 0 && this.isHovering(n, n2)) {
            PickerButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            this.setting.booleanValue = !this.setting.booleanValue;
        }
        if (n3 == 0 && this.isInsideRainbow(n, n2) && this.setting.open) {
            this.setting.isRainbow = !this.setting.isRainbow;
        }
        if (n3 == 0 && this.isInsideCopy(n, n2) && this.setting.open) {
            PickerButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            final StringSelection stringSelection = new StringSelection(String.format("#%02x%02x%02x%02x", this.finalColor.getAlpha(), this.finalColor.getRed(), this.finalColor.getGreen(), this.finalColor.getBlue()));
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
            Command.sendMessage("Copied the color to your clipboard.");
        }
        if (n3 == 0 && this.isInsidePaste(n, n2) && this.setting.open) {
            try {
                if (readClipboard() != null) {
                    if (Objects.requireNonNull(readClipboard()).startsWith("#")) {
                        final String s = Objects.requireNonNull(readClipboard());
                        final int intValue = Integer.valueOf(s.substring(1, 3), 16);
                        final int intValue2 = Integer.valueOf(s.substring(3, 5), 16);
                        final int intValue3 = Integer.valueOf(s.substring(5, 7), 16);
                        final int intValue4 = Integer.valueOf(s.substring(7, 9), 16);
                        if (this.setting.hideAlpha) {
                            this.setting.setValue(new Color(intValue2, intValue3, intValue4));
                        }
                        else {
                            this.setting.setValue(new Color(intValue2, intValue3, intValue4, intValue));
                        }
                    }
                    else {
                        final String[] split = readClipboard().split(",");
                        this.setting.setValue(new Color(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])));
                    }
                }
            }
            catch (NumberFormatException ex) {
                Command.sendMessage("Bad color format! Use Hex (#FFFFFFFF)");
            }
        }
    }
}
