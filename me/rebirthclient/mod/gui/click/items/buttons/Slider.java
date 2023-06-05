//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.gui.click.items.buttons;

import me.rebirthclient.mod.modules.settings.*;
import org.lwjgl.input.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.managers.*;

public class Slider extends Button
{
    private final Number min;
    private float renderWidth;
    private float prevRenderWidth;
    private final int difference;
    private final Number max;
    public final Setting setting;
    
    private float part() {
        return ((Number)this.setting.getValue()).floatValue() - this.min.floatValue();
    }
    
    public void drawScreen(final int p0, final int p1, final float p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: getfield        me/rebirthclient/mod/modules/impl/client/ClickGui.style:Lme/rebirthclient/mod/modules/settings/Setting;
        //     6: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //     9: getstatic       me/rebirthclient/mod/modules/impl/client/ClickGui$Style.NEW:Lme/rebirthclient/mod/modules/impl/client/ClickGui$Style;
        //    12: if_acmpne       19
        //    15: iconst_1       
        //    16: goto            20
        //    19: iconst_0       
        //    20: istore          4
        //    22: getstatic       me/rebirthclient/mod/modules/impl/client/ClickGui.INSTANCE:Lme/rebirthclient/mod/modules/impl/client/ClickGui;
        //    25: getfield        me/rebirthclient/mod/modules/impl/client/ClickGui.style:Lme/rebirthclient/mod/modules/settings/Setting;
        //    28: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    31: getstatic       me/rebirthclient/mod/modules/impl/client/ClickGui$Style.FUTURE:Lme/rebirthclient/mod/modules/impl/client/ClickGui$Style;
        //    34: if_acmpne       41
        //    37: iconst_1       
        //    38: goto            42
        //    41: iconst_0       
        //    42: istore          5
        //    44: getstatic       me/rebirthclient/mod/modules/impl/client/ClickGui.INSTANCE:Lme/rebirthclient/mod/modules/impl/client/ClickGui;
        //    47: getfield        me/rebirthclient/mod/modules/impl/client/ClickGui.style:Lme/rebirthclient/mod/modules/settings/Setting;
        //    50: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    53: getstatic       me/rebirthclient/mod/modules/impl/client/ClickGui$Style.DOTGOD:Lme/rebirthclient/mod/modules/impl/client/ClickGui$Style;
        //    56: if_acmpne       63
        //    59: iconst_1       
        //    60: goto            64
        //    63: iconst_0       
        //    64: istore          6
        //    66: aload_0        
        //    67: iload_1        
        //    68: iload_2        
        //    69: invokespecial   me/rebirthclient/mod/gui/click/items/buttons/Slider.dragSetting:(II)V
        //    72: aload_0        
        //    73: aload_0        
        //    74: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.x:F
        //    77: aload_0        
        //    78: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.width:I
        //    81: i2f            
        //    82: ldc             7.4
        //    84: fadd           
        //    85: aload_0        
        //    86: invokespecial   me/rebirthclient/mod/gui/click/items/buttons/Slider.partialMultiplier:()F
        //    89: fmul           
        //    90: fadd           
        //    91: invokevirtual   me/rebirthclient/mod/gui/click/items/buttons/Slider.setRenderWidth:(F)V
        //    94: aload_0        
        //    95: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.x:F
        //    98: aload_0        
        //    99: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.y:F
        //   102: aload_0        
        //   103: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.x:F
        //   106: aload_0        
        //   107: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.width:I
        //   110: i2f            
        //   111: fadd           
        //   112: ldc             7.4
        //   114: fadd           
        //   115: aload_0        
        //   116: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.y:F
        //   119: aload_0        
        //   120: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.height:I
        //   123: i2f            
        //   124: fadd           
        //   125: ldc             0.5
        //   127: fsub           
        //   128: aload_0        
        //   129: iload_1        
        //   130: iload_2        
        //   131: ifeq            139
        //   134: ldc             290805077
        //   136: goto            141
        //   139: ldc             -2007673515
        //   141: invokestatic    me/rebirthclient/api/util/render/RenderUtil.drawRect:(FFFFI)V
        //   144: iload           5
        //   146: ifeq            236
        //   149: aload_0        
        //   150: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.x:F
        //   153: aload_0        
        //   154: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.y:F
        //   157: aload_0        
        //   158: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   161: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   164: checkcast       Ljava/lang/Number;
        //   167: invokevirtual   java/lang/Number.floatValue:()F
        //   170: aload_0        
        //   171: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.min:Ljava/lang/Number;
        //   174: invokevirtual   java/lang/Number.floatValue:()F
        //   177: fcmpg          
        //   178: ifgt            188
        //   181: aload_0        
        //   182: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.x:F
        //   185: goto            192
        //   188: aload_0        
        //   189: invokevirtual   me/rebirthclient/mod/gui/click/items/buttons/Slider.getRenderWidth:()F
        //   192: aload_0        
        //   193: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.y:F
        //   196: aload_0        
        //   197: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.height:I
        //   200: i2f            
        //   201: fadd           
        //   202: ldc             0.5
        //   204: fsub           
        //   205: aload_0        
        //   206: iload_1        
        //   207: iload_2        
        //   208: ifeq            222
        //   211: getstatic       me/rebirthclient/api/managers/Managers.COLORS:Lme/rebirthclient/api/managers/impl/ColorManager;
        //   214: bipush          99
        //   216: invokevirtual   me/rebirthclient/api/managers/impl/ColorManager.getCurrentWithAlpha:(I)I
        //   219: goto            230
        //   222: getstatic       me/rebirthclient/api/managers/Managers.COLORS:Lme/rebirthclient/api/managers/impl/ColorManager;
        //   225: bipush          120
        //   227: invokevirtual   me/rebirthclient/api/managers/impl/ColorManager.getCurrentWithAlpha:(I)I
        //   230: invokestatic    me/rebirthclient/api/util/render/RenderUtil.drawRect:(FFFFI)V
        //   233: goto            661
        //   236: iload           6
        //   238: ifeq            328
        //   241: aload_0        
        //   242: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.x:F
        //   245: aload_0        
        //   246: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.y:F
        //   249: aload_0        
        //   250: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   253: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   256: checkcast       Ljava/lang/Number;
        //   259: invokevirtual   java/lang/Number.floatValue:()F
        //   262: aload_0        
        //   263: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.min:Ljava/lang/Number;
        //   266: invokevirtual   java/lang/Number.floatValue:()F
        //   269: fcmpg          
        //   270: ifgt            280
        //   273: aload_0        
        //   274: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.x:F
        //   277: goto            284
        //   280: aload_0        
        //   281: invokevirtual   me/rebirthclient/mod/gui/click/items/buttons/Slider.getRenderWidth:()F
        //   284: aload_0        
        //   285: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.y:F
        //   288: aload_0        
        //   289: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.height:I
        //   292: i2f            
        //   293: fadd           
        //   294: ldc             0.5
        //   296: fsub           
        //   297: aload_0        
        //   298: iload_1        
        //   299: iload_2        
        //   300: ifeq            314
        //   303: getstatic       me/rebirthclient/api/managers/Managers.COLORS:Lme/rebirthclient/api/managers/impl/ColorManager;
        //   306: bipush          65
        //   308: invokevirtual   me/rebirthclient/api/managers/impl/ColorManager.getCurrentWithAlpha:(I)I
        //   311: goto            322
        //   314: getstatic       me/rebirthclient/api/managers/Managers.COLORS:Lme/rebirthclient/api/managers/impl/ColorManager;
        //   317: bipush          90
        //   319: invokevirtual   me/rebirthclient/api/managers/impl/ColorManager.getCurrentWithAlpha:(I)I
        //   322: invokestatic    me/rebirthclient/api/util/render/RenderUtil.drawRect:(FFFFI)V
        //   325: goto            661
        //   328: aload_0        
        //   329: iload_1        
        //   330: iload_2        
        //   331: ifeq            533
        //   334: iconst_0       
        //   335: invokestatic    org/lwjgl/input/Mouse.isButtonDown:(I)Z
        //   338: ifeq            533
        //   341: aload_0        
        //   342: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.x:F
        //   345: aload_0        
        //   346: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.y:F
        //   349: aload_0        
        //   350: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   353: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   356: checkcast       Ljava/lang/Number;
        //   359: invokevirtual   java/lang/Number.floatValue:()F
        //   362: aload_0        
        //   363: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.min:Ljava/lang/Number;
        //   366: invokevirtual   java/lang/Number.floatValue:()F
        //   369: fcmpg          
        //   370: ifgt            380
        //   373: aload_0        
        //   374: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.x:F
        //   377: goto            384
        //   380: aload_0        
        //   381: invokevirtual   me/rebirthclient/mod/gui/click/items/buttons/Slider.getRenderWidth:()F
        //   384: aload_0        
        //   385: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.y:F
        //   388: aload_0        
        //   389: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.height:I
        //   392: i2f            
        //   393: fadd           
        //   394: ldc             0.5
        //   396: fsub           
        //   397: new             Ljava/awt/Color;
        //   400: dup            
        //   401: getstatic       me/rebirthclient/mod/modules/impl/client/ClickGui.INSTANCE:Lme/rebirthclient/mod/modules/impl/client/ClickGui;
        //   404: getfield        me/rebirthclient/mod/modules/impl/client/ClickGui.color:Lme/rebirthclient/mod/modules/settings/Setting;
        //   407: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   410: checkcast       Ljava/awt/Color;
        //   413: invokevirtual   java/awt/Color.getRed:()I
        //   416: getstatic       me/rebirthclient/mod/modules/impl/client/ClickGui.INSTANCE:Lme/rebirthclient/mod/modules/impl/client/ClickGui;
        //   419: getfield        me/rebirthclient/mod/modules/impl/client/ClickGui.color:Lme/rebirthclient/mod/modules/settings/Setting;
        //   422: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   425: checkcast       Ljava/awt/Color;
        //   428: invokevirtual   java/awt/Color.getGreen:()I
        //   431: getstatic       me/rebirthclient/mod/modules/impl/client/ClickGui.INSTANCE:Lme/rebirthclient/mod/modules/impl/client/ClickGui;
        //   434: getfield        me/rebirthclient/mod/modules/impl/client/ClickGui.color:Lme/rebirthclient/mod/modules/settings/Setting;
        //   437: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   440: checkcast       Ljava/awt/Color;
        //   443: invokevirtual   java/awt/Color.getBlue:()I
        //   446: sipush          200
        //   449: invokespecial   java/awt/Color.<init>:(IIII)V
        //   452: bipush          50
        //   454: iconst_1       
        //   455: invokestatic    me/rebirthclient/api/util/render/ColorUtil.pulseColor:(Ljava/awt/Color;II)Ljava/awt/Color;
        //   458: invokevirtual   java/awt/Color.getRGB:()I
        //   461: new             Ljava/awt/Color;
        //   464: dup            
        //   465: getstatic       me/rebirthclient/mod/modules/impl/client/ClickGui.INSTANCE:Lme/rebirthclient/mod/modules/impl/client/ClickGui;
        //   468: getfield        me/rebirthclient/mod/modules/impl/client/ClickGui.color:Lme/rebirthclient/mod/modules/settings/Setting;
        //   471: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   474: checkcast       Ljava/awt/Color;
        //   477: invokevirtual   java/awt/Color.getRed:()I
        //   480: getstatic       me/rebirthclient/mod/modules/impl/client/ClickGui.INSTANCE:Lme/rebirthclient/mod/modules/impl/client/ClickGui;
        //   483: getfield        me/rebirthclient/mod/modules/impl/client/ClickGui.color:Lme/rebirthclient/mod/modules/settings/Setting;
        //   486: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   489: checkcast       Ljava/awt/Color;
        //   492: invokevirtual   java/awt/Color.getGreen:()I
        //   495: getstatic       me/rebirthclient/mod/modules/impl/client/ClickGui.INSTANCE:Lme/rebirthclient/mod/modules/impl/client/ClickGui;
        //   498: getfield        me/rebirthclient/mod/modules/impl/client/ClickGui.color:Lme/rebirthclient/mod/modules/settings/Setting;
        //   501: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   504: checkcast       Ljava/awt/Color;
        //   507: invokevirtual   java/awt/Color.getBlue:()I
        //   510: sipush          200
        //   513: invokespecial   java/awt/Color.<init>:(IIII)V
        //   516: bipush          50
        //   518: sipush          1000
        //   521: invokestatic    me/rebirthclient/api/util/render/ColorUtil.pulseColor:(Ljava/awt/Color;II)Ljava/awt/Color;
        //   524: invokevirtual   java/awt/Color.getRGB:()I
        //   527: invokestatic    me/rebirthclient/api/util/render/RenderUtil.drawHGradientRect:(FFFFII)V
        //   530: goto            618
        //   533: aload_0        
        //   534: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.x:F
        //   537: aload_0        
        //   538: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.y:F
        //   541: aload_0        
        //   542: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   545: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   548: checkcast       Ljava/lang/Number;
        //   551: invokevirtual   java/lang/Number.floatValue:()F
        //   554: aload_0        
        //   555: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.min:Ljava/lang/Number;
        //   558: invokevirtual   java/lang/Number.floatValue:()F
        //   561: fcmpg          
        //   562: ifgt            572
        //   565: aload_0        
        //   566: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.x:F
        //   569: goto            576
        //   572: aload_0        
        //   573: invokevirtual   me/rebirthclient/mod/gui/click/items/buttons/Slider.getRenderWidth:()F
        //   576: aload_0        
        //   577: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.y:F
        //   580: aload_0        
        //   581: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.height:I
        //   584: i2f            
        //   585: fadd           
        //   586: ldc             0.5
        //   588: fsub           
        //   589: aload_0        
        //   590: iload_1        
        //   591: iload_2        
        //   592: ifeq            606
        //   595: getstatic       me/rebirthclient/api/managers/Managers.COLORS:Lme/rebirthclient/api/managers/impl/ColorManager;
        //   598: bipush          120
        //   600: invokevirtual   me/rebirthclient/api/managers/impl/ColorManager.getCurrentWithAlpha:(I)I
        //   603: goto            615
        //   606: getstatic       me/rebirthclient/api/managers/Managers.COLORS:Lme/rebirthclient/api/managers/impl/ColorManager;
        //   609: sipush          200
        //   612: invokevirtual   me/rebirthclient/api/managers/impl/ColorManager.getCurrentWithAlpha:(I)I
        //   615: invokestatic    me/rebirthclient/api/util/render/RenderUtil.drawRect:(FFFFI)V
        //   618: aload_0        
        //   619: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.x:F
        //   622: fconst_1       
        //   623: fadd           
        //   624: aload_0        
        //   625: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.y:F
        //   628: aload_0        
        //   629: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.x:F
        //   632: fconst_1       
        //   633: fadd           
        //   634: aload_0        
        //   635: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.y:F
        //   638: aload_0        
        //   639: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.height:I
        //   642: i2f            
        //   643: fadd           
        //   644: ldc             0.5
        //   646: fsub           
        //   647: ldc             0.9
        //   649: getstatic       me/rebirthclient/api/managers/Managers.COLORS:Lme/rebirthclient/api/managers/impl/ColorManager;
        //   652: sipush          255
        //   655: invokevirtual   me/rebirthclient/api/managers/impl/ColorManager.getCurrentWithAlpha:(I)I
        //   658: invokestatic    me/rebirthclient/api/util/render/RenderUtil.drawLine:(FFFFFI)V
        //   661: iload           6
        //   663: ifeq            784
        //   666: getstatic       me/rebirthclient/api/managers/Managers.TEXT:Lme/rebirthclient/api/managers/impl/TextManager;
        //   669: new             Ljava/lang/StringBuilder;
        //   672: dup            
        //   673: invokespecial   java/lang/StringBuilder.<init>:()V
        //   676: aload_0        
        //   677: invokevirtual   me/rebirthclient/mod/gui/click/items/buttons/Slider.getName:()Ljava/lang/String;
        //   680: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
        //   683: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   686: ldc             ":"
        //   688: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   691: ldc             " "
        //   693: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   696: getstatic       com/mojang/realmsclient/gui/ChatFormatting.GRAY:Lcom/mojang/realmsclient/gui/ChatFormatting;
        //   699: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   702: aload_0        
        //   703: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   706: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   709: instanceof      Ljava/lang/Float;
        //   712: ifeq            725
        //   715: aload_0        
        //   716: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   719: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   722: goto            741
        //   725: aload_0        
        //   726: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   729: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   732: checkcast       Ljava/lang/Number;
        //   735: invokevirtual   java/lang/Number.doubleValue:()D
        //   738: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //   741: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   744: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   747: aload_0        
        //   748: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.x:F
        //   751: ldc             2.3
        //   753: fadd           
        //   754: aload_0        
        //   755: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.y:F
        //   758: ldc             1.7
        //   760: fsub           
        //   761: getstatic       me/rebirthclient/mod/gui/screen/Gui.INSTANCE:Lme/rebirthclient/mod/gui/screen/Gui;
        //   764: invokevirtual   me/rebirthclient/mod/gui/screen/Gui.getTextOffset:()I
        //   767: i2f            
        //   768: fsub           
        //   769: getstatic       me/rebirthclient/api/managers/Managers.COLORS:Lme/rebirthclient/api/managers/impl/ColorManager;
        //   772: sipush          240
        //   775: invokevirtual   me/rebirthclient/api/managers/impl/ColorManager.getCurrentGui:(I)I
        //   778: invokevirtual   me/rebirthclient/api/managers/impl/TextManager.drawStringWithShadow:(Ljava/lang/String;FFI)V
        //   781: goto            916
        //   784: getstatic       me/rebirthclient/api/managers/Managers.TEXT:Lme/rebirthclient/api/managers/impl/TextManager;
        //   787: new             Ljava/lang/StringBuilder;
        //   790: dup            
        //   791: invokespecial   java/lang/StringBuilder.<init>:()V
        //   794: iload           4
        //   796: ifeq            827
        //   799: new             Ljava/lang/StringBuilder;
        //   802: dup            
        //   803: invokespecial   java/lang/StringBuilder.<init>:()V
        //   806: aload_0        
        //   807: invokevirtual   me/rebirthclient/mod/gui/click/items/buttons/Slider.getName:()Ljava/lang/String;
        //   810: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
        //   813: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   816: ldc             ":"
        //   818: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   821: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   824: goto            831
        //   827: aload_0        
        //   828: invokevirtual   me/rebirthclient/mod/gui/click/items/buttons/Slider.getName:()Ljava/lang/String;
        //   831: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   834: ldc             " "
        //   836: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   839: getstatic       com/mojang/realmsclient/gui/ChatFormatting.GRAY:Lcom/mojang/realmsclient/gui/ChatFormatting;
        //   842: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   845: aload_0        
        //   846: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   849: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   852: instanceof      Ljava/lang/Float;
        //   855: ifeq            868
        //   858: aload_0        
        //   859: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   862: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   865: goto            884
        //   868: aload_0        
        //   869: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.setting:Lme/rebirthclient/mod/modules/settings/Setting;
        //   872: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   875: checkcast       Ljava/lang/Number;
        //   878: invokevirtual   java/lang/Number.doubleValue:()D
        //   881: invokestatic    java/lang/Double.valueOf:(D)Ljava/lang/Double;
        //   884: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   887: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   890: aload_0        
        //   891: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.x:F
        //   894: ldc             2.3
        //   896: fadd           
        //   897: aload_0        
        //   898: getfield        me/rebirthclient/mod/gui/click/items/buttons/Slider.y:F
        //   901: ldc             1.7
        //   903: fsub           
        //   904: getstatic       me/rebirthclient/mod/gui/screen/Gui.INSTANCE:Lme/rebirthclient/mod/gui/screen/Gui;
        //   907: invokevirtual   me/rebirthclient/mod/gui/screen/Gui.getTextOffset:()I
        //   910: i2f            
        //   911: fsub           
        //   912: iconst_m1      
        //   913: invokevirtual   me/rebirthclient/api/managers/impl/TextManager.drawStringWithShadow:(Ljava/lang/String;FFI)V
        //   916: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0618 (coming from #0530).
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
    
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    private float partialMultiplier() {
        return this.part() / this.middle();
    }
    
    private void dragSetting(final int settingFromX, final int n) {
        if (n != 0 && Mouse.isButtonDown(0)) {
            this.setSettingFromX(settingFromX);
        }
    }
    
    public void mouseClicked(final int settingFromX, final int n, final int n2) {
        super.mouseClicked(settingFromX, n, n2);
        if (n != 0) {
            this.setSettingFromX(settingFromX);
        }
    }
    
    public Slider(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.min = (Number)setting.getMinValue();
        this.max = (Number)setting.getMaxValue();
        this.difference = this.max.intValue() - this.min.intValue();
        this.width = 15;
    }
    
    private void setSettingFromX(final int n) {
        final float n2 = (n - this.x) / (this.width + 7.4f);
        if (this.setting.getValue() instanceof Double) {
            this.setting.setValue(Math.round(10.0 * ((double)this.setting.getMinValue() + this.difference * n2)) / 10.0);
        }
        else if (this.setting.getValue() instanceof Float) {
            this.setting.setValue(Math.round(10.0f * ((float)this.setting.getMinValue() + this.difference * n2)) / 10.0f);
        }
        else if (this.setting.getValue() instanceof Integer) {
            this.setting.setValue((int)this.setting.getMinValue() + (int)(this.difference * n2));
        }
    }
    
    private float middle() {
        return this.max.floatValue() - this.min.floatValue();
    }
    
    public int getHeight() {
        return ClickGui.INSTANCE.getButtonHeight() - 1;
    }
    
    public void setRenderWidth(final float renderWidth) {
        if (this.renderWidth == renderWidth) {
            return;
        }
        this.prevRenderWidth = this.renderWidth;
        this.renderWidth = renderWidth;
    }
    
    public float getRenderWidth() {
        if (Managers.FPS.getFPS() < 20) {
            return this.renderWidth;
        }
        return this.renderWidth = this.prevRenderWidth + (this.renderWidth - this.prevRenderWidth) * Slider.mc.getRenderPartialTicks() / (8.0f * (Math.min(240, Managers.FPS.getFPS()) / 240.0f));
    }
}
