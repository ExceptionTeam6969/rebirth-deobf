//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import java.util.regex.*;
import java.nio.*;
import net.minecraft.client.renderer.culling.*;
import java.text.*;
import java.awt.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraftforge.fml.common.eventhandler.*;
import javax.vecmath.*;
import org.lwjgl.util.glu.*;
import org.lwjgl.opengl.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraft.client.gui.*;
import java.util.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.managers.*;

public final class ESP2D extends Module
{
    public final Setting armorItems;
    public final Setting droppedItems;
    private final int black;
    public final Setting animals;
    public final Setting color;
    public final Setting tagsBGValue;
    private final Setting mixerSecondsValue;
    public final Setting friendColor;
    private static final int[] DISPLAY_LISTS_2D;
    public final Setting itemValue;
    private final FloatBuffer modelview;
    public final Setting clearNameValue;
    private final FloatBuffer projection;
    public final Setting outline;
    public final Setting bbtt;
    private static final Pattern COLOR_PATTERN;
    public final Setting armorBar;
    private final Setting brightnessValue;
    public final Setting healthBar;
    private final Setting fontScaleValue;
    public final Setting tagsValue;
    public final Setting itemTagsValue;
    public final Setting boxMode;
    public final Setting colorModeValue;
    public final Setting hoverValue;
    private final IntBuffer viewport;
    public final Setting localPlayer;
    public final Setting hpMode;
    private final FloatBuffer vector;
    private final Setting saturationValue;
    public final Setting armorDur;
    public final Setting mobs;
    public final Setting healthNumber;
    private static final Frustum frustrum;
    public final Setting outlineFont;
    private final DecimalFormat dFormat;
    public final Setting hpBarMode;
    public static final List collectedEntities;
    private final int backgroundColor;
    public static ESP2D INSTANCE;
    
    private void drawScaledCenteredString(final String s, final double n, final double n2, final double n3) {
        this.drawScaledString(s, n - ESP2D.mc.fontRenderer.getStringWidth(s) / 2.0f * n3, n2, n3);
    }
    
    private void drawScaledString(final String s, final double n, final double n2, final double n3, final int n4) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(n, n2, n);
        GlStateManager.scale(n3, n3, n3);
        if (this.outlineFont.getValue()) {
            this.drawOutlineStringWithoutGL(s, 0.0f, 0.0f, n4, ESP2D.mc.fontRenderer);
        }
        else {
            ESP2D.mc.fontRenderer.drawStringWithShadow(s, 0.0f, 0.0f, n4);
        }
        GlStateManager.popMatrix();
    }
    
    public static void setColor(final Color color) {
        GL11.glColor4f((color.getRGB() >> 16 & 0xFF) / 255.0f, (color.getRGB() >> 8 & 0xFF) / 255.0f, (color.getRGB() & 0xFF) / 255.0f, (color.getRGB() >> 24 & 0xFF) / 255.0f);
    }
    
    private void drawScaledString(final String s, final double n, final double n2, final double n3) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(n, n2, n);
        GlStateManager.scale(n3, n3, n3);
        if (this.outlineFont.getValue()) {
            this.drawOutlineStringWithoutGL(s, 0.0f, 0.0f, -1, ESP2D.mc.fontRenderer);
        }
        else {
            ESP2D.mc.fontRenderer.drawStringWithShadow(s, 0.0f, 0.0f, -1);
        }
        GlStateManager.popMatrix();
    }
    
    public static Color slowlyRainbow(final long n, final int n2, final float n3, final float n4) {
        final Color color = new Color(Color.HSBtoRGB((n + n2 * -3000000.0f) / 2.0f / 1.0E9f, n3, n4));
        return new Color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
    
    private boolean lambda$new$2(final Color color) {
        return this.colorModeValue.getValue() == Mode5.Custom && this.outline.isOpen();
    }
    
    private static boolean isInViewFrustrum(final AxisAlignedBB axisAlignedBB) {
        final Entity getRenderViewEntity = ESP2D.mc.getRenderViewEntity();
        ESP2D.frustrum.setPosition(getRenderViewEntity.posX, getRenderViewEntity.posY, getRenderViewEntity.posZ);
        return ESP2D.frustrum.isBoundingBoxInFrustum(axisAlignedBB);
    }
    
    private boolean lambda$new$3(final Float n) {
        return this.colorModeValue.getValue() != Mode5.Custom && this.outline.isOpen();
    }
    
    public static int[] getFractionIndices(final float[] array, final float n) {
        final int[] array2 = new int[2];
        int n2 = 0;
        if (n2 < array.length && array[n2] <= n) {
            ++n2;
            return null;
        }
        if (n2 >= array.length) {
            n2 = array.length - 1;
        }
        array2[0] = n2 - 1;
        array2[1] = n2;
        return array2;
    }
    
    private boolean lambda$new$8(final Mode4 mode4) {
        return this.healthNumber.isOpen() && this.healthBar.isOpen();
    }
    
    @SubscribeEvent
    @Override
    public void onRender2D(final Render2DEvent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: aload_0        
        //     4: invokespecial   me/rebirthclient/mod/modules/impl/render/ESP2D.collectEntities:()V
        //     7: aload_1        
        //     8: getfield        me/rebirthclient/api/events/impl/Render2DEvent.partialTicks:F
        //    11: fstore_2       
        //    12: new             Lnet/minecraft/client/gui/ScaledResolution;
        //    15: dup            
        //    16: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.mc:Lnet/minecraft/client/Minecraft;
        //    19: invokespecial   net/minecraft/client/gui/ScaledResolution.<init>:(Lnet/minecraft/client/Minecraft;)V
        //    22: astore_3       
        //    23: aload_3        
        //    24: invokevirtual   net/minecraft/client/gui/ScaledResolution.getScaleFactor:()I
        //    27: istore          4
        //    29: iload           4
        //    31: i2d            
        //    32: iload           4
        //    34: i2d            
        //    35: ldc2_w          2.0
        //    38: invokestatic    java/lang/Math.pow:(DD)D
        //    41: ddiv           
        //    42: dstore          5
        //    44: dload           5
        //    46: dload           5
        //    48: dload           5
        //    50: invokestatic    org/lwjgl/opengl/GL11.glScaled:(DDD)V
        //    53: aload_0        
        //    54: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.black:I
        //    57: istore          7
        //    59: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.mc:Lnet/minecraft/client/Minecraft;
        //    62: invokevirtual   net/minecraft/client/Minecraft.getRenderManager:()Lnet/minecraft/client/renderer/entity/RenderManager;
        //    65: astore          8
        //    67: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.mc:Lnet/minecraft/client/Minecraft;
        //    70: getfield        net/minecraft/client/Minecraft.entityRenderer:Lnet/minecraft/client/renderer/EntityRenderer;
        //    73: astore          9
        //    75: aload_0        
        //    76: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.outline:Lme/rebirthclient/mod/modules/settings/Setting;
        //    79: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    82: checkcast       Ljava/lang/Boolean;
        //    85: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    88: istore          10
        //    90: aload_0        
        //    91: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.healthBar:Lme/rebirthclient/mod/modules/settings/Setting;
        //    94: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    97: checkcast       Ljava/lang/Boolean;
        //   100: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   103: istore          11
        //   105: iconst_0       
        //   106: istore          12
        //   108: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.collectedEntities:Ljava/util/List;
        //   111: invokeinterface java/util/List.size:()I
        //   116: istore          13
        //   118: iload           12
        //   120: iload           13
        //   122: if_icmpge       3489
        //   125: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.collectedEntities:Ljava/util/List;
        //   128: iload           12
        //   130: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   135: checkcast       Lnet/minecraft/entity/Entity;
        //   138: astore          14
        //   140: aload_0        
        //   141: aload           14
        //   143: invokevirtual   me/rebirthclient/mod/modules/impl/render/ESP2D.getColor:(Lnet/minecraft/entity/Entity;)Ljava/awt/Color;
        //   146: invokevirtual   java/awt/Color.getRGB:()I
        //   149: istore          15
        //   151: aload           14
        //   153: ifeq            3485
        //   156: aload           14
        //   158: getfield        net/minecraft/entity/Entity.posX:D
        //   161: aload           14
        //   163: getfield        net/minecraft/entity/Entity.lastTickPosX:D
        //   166: fload_2        
        //   167: f2d            
        //   168: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.interpolate:(DDD)D
        //   171: dstore          16
        //   173: aload           14
        //   175: getfield        net/minecraft/entity/Entity.posY:D
        //   178: aload           14
        //   180: getfield        net/minecraft/entity/Entity.lastTickPosY:D
        //   183: fload_2        
        //   184: f2d            
        //   185: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.interpolate:(DDD)D
        //   188: dstore          18
        //   190: aload           14
        //   192: getfield        net/minecraft/entity/Entity.posZ:D
        //   195: aload           14
        //   197: getfield        net/minecraft/entity/Entity.lastTickPosZ:D
        //   200: fload_2        
        //   201: f2d            
        //   202: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.interpolate:(DDD)D
        //   205: dstore          20
        //   207: aload           14
        //   209: getfield        net/minecraft/entity/Entity.width:F
        //   212: f2d            
        //   213: ldc2_w          1.5
        //   216: ddiv           
        //   217: dstore          22
        //   219: aload           14
        //   221: getfield        net/minecraft/entity/Entity.height:F
        //   224: f2d            
        //   225: aload           14
        //   227: invokevirtual   net/minecraft/entity/Entity.isSneaking:()Z
        //   230: ifeq            239
        //   233: ldc2_w          -0.3
        //   236: goto            242
        //   239: ldc2_w          0.2
        //   242: dadd           
        //   243: dstore          24
        //   245: new             Lnet/minecraft/util/math/AxisAlignedBB;
        //   248: dup            
        //   249: dload           16
        //   251: dload           22
        //   253: dsub           
        //   254: dload           18
        //   256: dload           20
        //   258: dload           22
        //   260: dsub           
        //   261: dload           16
        //   263: dload           22
        //   265: dadd           
        //   266: dload           18
        //   268: dload           24
        //   270: dadd           
        //   271: dload           20
        //   273: dload           22
        //   275: dadd           
        //   276: invokespecial   net/minecraft/util/math/AxisAlignedBB.<init>:(DDDDDD)V
        //   279: astore          26
        //   281: bipush          8
        //   283: anewarray       Ljavax/vecmath/Vector3d;
        //   286: dup            
        //   287: iconst_0       
        //   288: new             Ljavax/vecmath/Vector3d;
        //   291: dup            
        //   292: aload           26
        //   294: getfield        net/minecraft/util/math/AxisAlignedBB.minX:D
        //   297: aload           26
        //   299: getfield        net/minecraft/util/math/AxisAlignedBB.minY:D
        //   302: aload           26
        //   304: getfield        net/minecraft/util/math/AxisAlignedBB.minZ:D
        //   307: invokespecial   javax/vecmath/Vector3d.<init>:(DDD)V
        //   310: aastore        
        //   311: dup            
        //   312: iconst_1       
        //   313: new             Ljavax/vecmath/Vector3d;
        //   316: dup            
        //   317: aload           26
        //   319: getfield        net/minecraft/util/math/AxisAlignedBB.minX:D
        //   322: aload           26
        //   324: getfield        net/minecraft/util/math/AxisAlignedBB.maxY:D
        //   327: aload           26
        //   329: getfield        net/minecraft/util/math/AxisAlignedBB.minZ:D
        //   332: invokespecial   javax/vecmath/Vector3d.<init>:(DDD)V
        //   335: aastore        
        //   336: dup            
        //   337: iconst_2       
        //   338: new             Ljavax/vecmath/Vector3d;
        //   341: dup            
        //   342: aload           26
        //   344: getfield        net/minecraft/util/math/AxisAlignedBB.maxX:D
        //   347: aload           26
        //   349: getfield        net/minecraft/util/math/AxisAlignedBB.minY:D
        //   352: aload           26
        //   354: getfield        net/minecraft/util/math/AxisAlignedBB.minZ:D
        //   357: invokespecial   javax/vecmath/Vector3d.<init>:(DDD)V
        //   360: aastore        
        //   361: dup            
        //   362: iconst_3       
        //   363: new             Ljavax/vecmath/Vector3d;
        //   366: dup            
        //   367: aload           26
        //   369: getfield        net/minecraft/util/math/AxisAlignedBB.maxX:D
        //   372: aload           26
        //   374: getfield        net/minecraft/util/math/AxisAlignedBB.maxY:D
        //   377: aload           26
        //   379: getfield        net/minecraft/util/math/AxisAlignedBB.minZ:D
        //   382: invokespecial   javax/vecmath/Vector3d.<init>:(DDD)V
        //   385: aastore        
        //   386: dup            
        //   387: iconst_4       
        //   388: new             Ljavax/vecmath/Vector3d;
        //   391: dup            
        //   392: aload           26
        //   394: getfield        net/minecraft/util/math/AxisAlignedBB.minX:D
        //   397: aload           26
        //   399: getfield        net/minecraft/util/math/AxisAlignedBB.minY:D
        //   402: aload           26
        //   404: getfield        net/minecraft/util/math/AxisAlignedBB.maxZ:D
        //   407: invokespecial   javax/vecmath/Vector3d.<init>:(DDD)V
        //   410: aastore        
        //   411: dup            
        //   412: iconst_5       
        //   413: new             Ljavax/vecmath/Vector3d;
        //   416: dup            
        //   417: aload           26
        //   419: getfield        net/minecraft/util/math/AxisAlignedBB.minX:D
        //   422: aload           26
        //   424: getfield        net/minecraft/util/math/AxisAlignedBB.maxY:D
        //   427: aload           26
        //   429: getfield        net/minecraft/util/math/AxisAlignedBB.maxZ:D
        //   432: invokespecial   javax/vecmath/Vector3d.<init>:(DDD)V
        //   435: aastore        
        //   436: dup            
        //   437: bipush          6
        //   439: new             Ljavax/vecmath/Vector3d;
        //   442: dup            
        //   443: aload           26
        //   445: getfield        net/minecraft/util/math/AxisAlignedBB.maxX:D
        //   448: aload           26
        //   450: getfield        net/minecraft/util/math/AxisAlignedBB.minY:D
        //   453: aload           26
        //   455: getfield        net/minecraft/util/math/AxisAlignedBB.maxZ:D
        //   458: invokespecial   javax/vecmath/Vector3d.<init>:(DDD)V
        //   461: aastore        
        //   462: dup            
        //   463: bipush          7
        //   465: new             Ljavax/vecmath/Vector3d;
        //   468: dup            
        //   469: aload           26
        //   471: getfield        net/minecraft/util/math/AxisAlignedBB.maxX:D
        //   474: aload           26
        //   476: getfield        net/minecraft/util/math/AxisAlignedBB.maxY:D
        //   479: aload           26
        //   481: getfield        net/minecraft/util/math/AxisAlignedBB.maxZ:D
        //   484: invokespecial   javax/vecmath/Vector3d.<init>:(DDD)V
        //   487: aastore        
        //   488: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //   491: astore          27
        //   493: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.mc:Lnet/minecraft/client/Minecraft;
        //   496: getfield        net/minecraft/client/Minecraft.entityRenderer:Lnet/minecraft/client/renderer/EntityRenderer;
        //   499: checkcast       Lme/rebirthclient/asm/accessors/IEntityRenderer;
        //   502: fload_2        
        //   503: iconst_0       
        //   504: invokeinterface me/rebirthclient/asm/accessors/IEntityRenderer.invokeSetupCameraTransform:(FI)V
        //   509: aconst_null    
        //   510: astore          28
        //   512: aload           27
        //   514: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   519: astore          29
        //   521: aload           29
        //   523: invokeinterface java/util/Iterator.hasNext:()Z
        //   528: ifeq            716
        //   531: aload           29
        //   533: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   538: astore          30
        //   540: aload           30
        //   542: checkcast       Ljavax/vecmath/Vector3d;
        //   545: astore          31
        //   547: aload_0        
        //   548: iload           4
        //   550: aload           31
        //   552: getfield        javax/vecmath/Vector3d.x:D
        //   555: aload           8
        //   557: getfield        net/minecraft/client/renderer/entity/RenderManager.viewerPosX:D
        //   560: dsub           
        //   561: aload           31
        //   563: getfield        javax/vecmath/Vector3d.y:D
        //   566: aload           8
        //   568: getfield        net/minecraft/client/renderer/entity/RenderManager.viewerPosY:D
        //   571: dsub           
        //   572: aload           31
        //   574: getfield        javax/vecmath/Vector3d.z:D
        //   577: aload           8
        //   579: getfield        net/minecraft/client/renderer/entity/RenderManager.viewerPosZ:D
        //   582: dsub           
        //   583: invokespecial   me/rebirthclient/mod/modules/impl/render/ESP2D.project2D:(IDDD)Ljavax/vecmath/Vector3d;
        //   586: astore          31
        //   588: aload           31
        //   590: ifnull          715
        //   593: aload           31
        //   595: getfield        javax/vecmath/Vector3d.z:D
        //   598: dconst_0       
        //   599: dcmpl          
        //   600: iflt            715
        //   603: aload           31
        //   605: getfield        javax/vecmath/Vector3d.z:D
        //   608: dconst_1       
        //   609: dcmpg          
        //   610: ifge            715
        //   613: aload           28
        //   615: ifnonnull       643
        //   618: new             Ljavax/vecmath/Vector4d;
        //   621: dup            
        //   622: aload           31
        //   624: getfield        javax/vecmath/Vector3d.x:D
        //   627: aload           31
        //   629: getfield        javax/vecmath/Vector3d.y:D
        //   632: aload           31
        //   634: getfield        javax/vecmath/Vector3d.z:D
        //   637: dconst_0       
        //   638: invokespecial   javax/vecmath/Vector4d.<init>:(DDDD)V
        //   641: astore          28
        //   643: aload           28
        //   645: aload           31
        //   647: getfield        javax/vecmath/Vector3d.x:D
        //   650: aload           28
        //   652: getfield        javax/vecmath/Vector4d.x:D
        //   655: invokestatic    java/lang/Math.min:(DD)D
        //   658: putfield        javax/vecmath/Vector4d.x:D
        //   661: aload           28
        //   663: aload           31
        //   665: getfield        javax/vecmath/Vector3d.y:D
        //   668: aload           28
        //   670: getfield        javax/vecmath/Vector4d.y:D
        //   673: invokestatic    java/lang/Math.min:(DD)D
        //   676: putfield        javax/vecmath/Vector4d.y:D
        //   679: aload           28
        //   681: aload           31
        //   683: getfield        javax/vecmath/Vector3d.x:D
        //   686: aload           28
        //   688: getfield        javax/vecmath/Vector4d.z:D
        //   691: invokestatic    java/lang/Math.max:(DD)D
        //   694: putfield        javax/vecmath/Vector4d.z:D
        //   697: aload           28
        //   699: aload           31
        //   701: getfield        javax/vecmath/Vector3d.y:D
        //   704: aload           28
        //   706: getfield        javax/vecmath/Vector4d.w:D
        //   709: invokestatic    java/lang/Math.max:(DD)D
        //   712: putfield        javax/vecmath/Vector4d.w:D
        //   715: return         
        //   716: aload           28
        //   718: ifnull          3485
        //   721: aload           9
        //   723: invokevirtual   net/minecraft/client/renderer/EntityRenderer.setupOverlayRendering:()V
        //   726: aload           28
        //   728: getfield        javax/vecmath/Vector4d.x:D
        //   731: dstore          29
        //   733: aload           28
        //   735: getfield        javax/vecmath/Vector4d.y:D
        //   738: dstore          31
        //   740: aload           28
        //   742: getfield        javax/vecmath/Vector4d.z:D
        //   745: dstore          33
        //   747: aload           28
        //   749: getfield        javax/vecmath/Vector4d.w:D
        //   752: dstore          35
        //   754: iload           10
        //   756: ifeq            1465
        //   759: aload_0        
        //   760: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.boxMode:Lme/rebirthclient/mod/modules/settings/Setting;
        //   763: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   766: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D$Mode.Box:Lme/rebirthclient/mod/modules/impl/render/ESP2D$Mode;
        //   769: if_acmpne       969
        //   772: dload           29
        //   774: dconst_1       
        //   775: dsub           
        //   776: dload           31
        //   778: dload           29
        //   780: ldc2_w          0.5
        //   783: dadd           
        //   784: dload           35
        //   786: ldc2_w          0.5
        //   789: dadd           
        //   790: iload           7
        //   792: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //   795: dload           29
        //   797: dconst_1       
        //   798: dsub           
        //   799: dload           31
        //   801: ldc2_w          0.5
        //   804: dsub           
        //   805: dload           33
        //   807: ldc2_w          0.5
        //   810: dadd           
        //   811: dload           31
        //   813: ldc2_w          0.5
        //   816: dadd           
        //   817: ldc2_w          0.5
        //   820: dadd           
        //   821: iload           7
        //   823: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //   826: dload           33
        //   828: ldc2_w          0.5
        //   831: dsub           
        //   832: ldc2_w          0.5
        //   835: dsub           
        //   836: dload           31
        //   838: dload           33
        //   840: ldc2_w          0.5
        //   843: dadd           
        //   844: dload           35
        //   846: ldc2_w          0.5
        //   849: dadd           
        //   850: iload           7
        //   852: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //   855: dload           29
        //   857: dconst_1       
        //   858: dsub           
        //   859: dload           35
        //   861: ldc2_w          0.5
        //   864: dsub           
        //   865: ldc2_w          0.5
        //   868: dsub           
        //   869: dload           33
        //   871: ldc2_w          0.5
        //   874: dadd           
        //   875: dload           35
        //   877: ldc2_w          0.5
        //   880: dadd           
        //   881: iload           7
        //   883: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //   886: dload           29
        //   888: ldc2_w          0.5
        //   891: dsub           
        //   892: dload           31
        //   894: dload           29
        //   896: ldc2_w          0.5
        //   899: dadd           
        //   900: ldc2_w          0.5
        //   903: dsub           
        //   904: dload           35
        //   906: iload           15
        //   908: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //   911: dload           29
        //   913: dload           35
        //   915: ldc2_w          0.5
        //   918: dsub           
        //   919: dload           33
        //   921: dload           35
        //   923: iload           15
        //   925: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //   928: dload           29
        //   930: ldc2_w          0.5
        //   933: dsub           
        //   934: dload           31
        //   936: dload           33
        //   938: dload           31
        //   940: ldc2_w          0.5
        //   943: dadd           
        //   944: iload           15
        //   946: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //   949: dload           33
        //   951: ldc2_w          0.5
        //   954: dsub           
        //   955: dload           31
        //   957: dload           33
        //   959: dload           35
        //   961: iload           15
        //   963: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //   966: goto            1465
        //   969: dload           29
        //   971: ldc2_w          0.5
        //   974: dadd           
        //   975: dload           31
        //   977: dload           29
        //   979: dconst_1       
        //   980: dsub           
        //   981: dload           31
        //   983: dload           35
        //   985: dload           31
        //   987: dsub           
        //   988: ldc2_w          4.0
        //   991: ddiv           
        //   992: dadd           
        //   993: ldc2_w          0.5
        //   996: dadd           
        //   997: iload           7
        //   999: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  1002: dload           29
        //  1004: dconst_1       
        //  1005: dsub           
        //  1006: dload           35
        //  1008: dload           29
        //  1010: ldc2_w          0.5
        //  1013: dadd           
        //  1014: dload           35
        //  1016: dload           35
        //  1018: dload           31
        //  1020: dsub           
        //  1021: ldc2_w          4.0
        //  1024: ddiv           
        //  1025: dsub           
        //  1026: ldc2_w          0.5
        //  1029: dsub           
        //  1030: iload           7
        //  1032: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  1035: dload           29
        //  1037: dconst_1       
        //  1038: dsub           
        //  1039: dload           31
        //  1041: ldc2_w          0.5
        //  1044: dsub           
        //  1045: dload           29
        //  1047: dload           33
        //  1049: dload           29
        //  1051: dsub           
        //  1052: ldc2_w          3.0
        //  1055: ddiv           
        //  1056: dadd           
        //  1057: ldc2_w          0.5
        //  1060: dadd           
        //  1061: dload           31
        //  1063: dconst_1       
        //  1064: dadd           
        //  1065: iload           7
        //  1067: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  1070: dload           33
        //  1072: dload           33
        //  1074: dload           29
        //  1076: dsub           
        //  1077: ldc2_w          3.0
        //  1080: ddiv           
        //  1081: dsub           
        //  1082: ldc2_w          0.5
        //  1085: dsub           
        //  1086: dload           31
        //  1088: ldc2_w          0.5
        //  1091: dsub           
        //  1092: dload           33
        //  1094: dload           31
        //  1096: dconst_1       
        //  1097: dadd           
        //  1098: iload           7
        //  1100: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  1103: dload           33
        //  1105: dconst_1       
        //  1106: dsub           
        //  1107: dload           31
        //  1109: dload           33
        //  1111: ldc2_w          0.5
        //  1114: dadd           
        //  1115: dload           31
        //  1117: dload           35
        //  1119: dload           31
        //  1121: dsub           
        //  1122: ldc2_w          4.0
        //  1125: ddiv           
        //  1126: dadd           
        //  1127: ldc2_w          0.5
        //  1130: dadd           
        //  1131: iload           7
        //  1133: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  1136: dload           33
        //  1138: dconst_1       
        //  1139: dsub           
        //  1140: dload           35
        //  1142: dload           33
        //  1144: ldc2_w          0.5
        //  1147: dadd           
        //  1148: dload           35
        //  1150: dload           35
        //  1152: dload           31
        //  1154: dsub           
        //  1155: ldc2_w          4.0
        //  1158: ddiv           
        //  1159: dsub           
        //  1160: ldc2_w          0.5
        //  1163: dsub           
        //  1164: iload           7
        //  1166: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  1169: dload           29
        //  1171: dconst_1       
        //  1172: dsub           
        //  1173: dload           35
        //  1175: dconst_1       
        //  1176: dsub           
        //  1177: dload           29
        //  1179: dload           33
        //  1181: dload           29
        //  1183: dsub           
        //  1184: ldc2_w          3.0
        //  1187: ddiv           
        //  1188: dadd           
        //  1189: ldc2_w          0.5
        //  1192: dadd           
        //  1193: dload           35
        //  1195: ldc2_w          0.5
        //  1198: dadd           
        //  1199: iload           7
        //  1201: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  1204: dload           33
        //  1206: dload           33
        //  1208: dload           29
        //  1210: dsub           
        //  1211: ldc2_w          3.0
        //  1214: ddiv           
        //  1215: dsub           
        //  1216: ldc2_w          0.5
        //  1219: dsub           
        //  1220: dload           35
        //  1222: dconst_1       
        //  1223: dsub           
        //  1224: dload           33
        //  1226: ldc2_w          0.5
        //  1229: dadd           
        //  1230: dload           35
        //  1232: ldc2_w          0.5
        //  1235: dadd           
        //  1236: iload           7
        //  1238: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  1241: dload           29
        //  1243: dload           31
        //  1245: dload           29
        //  1247: ldc2_w          0.5
        //  1250: dsub           
        //  1251: dload           31
        //  1253: dload           35
        //  1255: dload           31
        //  1257: dsub           
        //  1258: ldc2_w          4.0
        //  1261: ddiv           
        //  1262: dadd           
        //  1263: iload           15
        //  1265: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  1268: dload           29
        //  1270: dload           35
        //  1272: dload           29
        //  1274: ldc2_w          0.5
        //  1277: dsub           
        //  1278: dload           35
        //  1280: dload           35
        //  1282: dload           31
        //  1284: dsub           
        //  1285: ldc2_w          4.0
        //  1288: ddiv           
        //  1289: dsub           
        //  1290: iload           15
        //  1292: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  1295: dload           29
        //  1297: ldc2_w          0.5
        //  1300: dsub           
        //  1301: dload           31
        //  1303: dload           29
        //  1305: dload           33
        //  1307: dload           29
        //  1309: dsub           
        //  1310: ldc2_w          3.0
        //  1313: ddiv           
        //  1314: dadd           
        //  1315: dload           31
        //  1317: ldc2_w          0.5
        //  1320: dadd           
        //  1321: iload           15
        //  1323: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  1326: dload           33
        //  1328: dload           33
        //  1330: dload           29
        //  1332: dsub           
        //  1333: ldc2_w          3.0
        //  1336: ddiv           
        //  1337: dsub           
        //  1338: dload           31
        //  1340: dload           33
        //  1342: dload           31
        //  1344: ldc2_w          0.5
        //  1347: dadd           
        //  1348: iload           15
        //  1350: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  1353: dload           33
        //  1355: ldc2_w          0.5
        //  1358: dsub           
        //  1359: dload           31
        //  1361: dload           33
        //  1363: dload           31
        //  1365: dload           35
        //  1367: dload           31
        //  1369: dsub           
        //  1370: ldc2_w          4.0
        //  1373: ddiv           
        //  1374: dadd           
        //  1375: iload           15
        //  1377: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  1380: dload           33
        //  1382: ldc2_w          0.5
        //  1385: dsub           
        //  1386: dload           35
        //  1388: dload           33
        //  1390: dload           35
        //  1392: dload           35
        //  1394: dload           31
        //  1396: dsub           
        //  1397: ldc2_w          4.0
        //  1400: ddiv           
        //  1401: dsub           
        //  1402: iload           15
        //  1404: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  1407: dload           29
        //  1409: dload           35
        //  1411: ldc2_w          0.5
        //  1414: dsub           
        //  1415: dload           29
        //  1417: dload           33
        //  1419: dload           29
        //  1421: dsub           
        //  1422: ldc2_w          3.0
        //  1425: ddiv           
        //  1426: dadd           
        //  1427: dload           35
        //  1429: iload           15
        //  1431: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  1434: dload           33
        //  1436: dload           33
        //  1438: dload           29
        //  1440: dsub           
        //  1441: ldc2_w          3.0
        //  1444: ddiv           
        //  1445: dsub           
        //  1446: dload           35
        //  1448: ldc2_w          0.5
        //  1451: dsub           
        //  1452: dload           33
        //  1454: ldc2_w          0.5
        //  1457: dsub           
        //  1458: dload           35
        //  1460: iload           15
        //  1462: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  1465: aload           14
        //  1467: instanceof      Lnet/minecraft/entity/EntityLivingBase;
        //  1470: istore          37
        //  1472: iload           37
        //  1474: ifne            1480
        //  1477: goto            3485
        //  1480: aload           14
        //  1482: checkcast       Lnet/minecraft/entity/EntityLivingBase;
        //  1485: astore          38
        //  1487: iload           11
        //  1489: ifeq            2061
        //  1492: aload           38
        //  1494: invokevirtual   net/minecraft/entity/EntityLivingBase.getHealth:()F
        //  1497: fstore          39
        //  1499: aload           38
        //  1501: invokevirtual   net/minecraft/entity/EntityLivingBase.getMaxHealth:()F
        //  1504: fstore          40
        //  1506: aload_0        
        //  1507: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.bbtt:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1510: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1513: checkcast       Ljava/lang/Boolean;
        //  1516: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1519: ifeq            1554
        //  1522: aload           38
        //  1524: instanceof      Lnet/minecraft/entity/player/EntityPlayer;
        //  1527: ifeq            1554
        //  1530: aload           38
        //  1532: invokevirtual   net/minecraft/entity/EntityLivingBase.getHealth:()F
        //  1535: aload           38
        //  1537: invokevirtual   net/minecraft/entity/EntityLivingBase.getAbsorptionAmount:()F
        //  1540: fadd           
        //  1541: fstore          39
        //  1543: aload           38
        //  1545: invokevirtual   net/minecraft/entity/EntityLivingBase.getMaxHealth:()F
        //  1548: ldc_w           16.0
        //  1551: fadd           
        //  1552: fstore          40
        //  1554: fload           39
        //  1556: fload           40
        //  1558: fcmpl          
        //  1559: ifle            1566
        //  1562: fload           40
        //  1564: fstore          39
        //  1566: fload           39
        //  1568: fload           40
        //  1570: fdiv           
        //  1571: f2d            
        //  1572: dstore          41
        //  1574: dload           35
        //  1576: dload           31
        //  1578: dsub           
        //  1579: dload           41
        //  1581: dmul           
        //  1582: dstore          43
        //  1584: new             Ljava/lang/StringBuilder;
        //  1587: dup            
        //  1588: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1591: aload_0        
        //  1592: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.dFormat:Ljava/text/DecimalFormat;
        //  1595: aload           38
        //  1597: invokevirtual   net/minecraft/entity/EntityLivingBase.getHealth:()F
        //  1600: aload           38
        //  1602: invokevirtual   net/minecraft/entity/EntityLivingBase.getAbsorptionAmount:()F
        //  1605: fadd           
        //  1606: f2d            
        //  1607: invokevirtual   java/text/DecimalFormat.format:(D)Ljava/lang/String;
        //  1610: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1613: ldc_w           " ¡ìc\u2764"
        //  1616: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1619: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1622: astore          45
        //  1624: new             Ljava/lang/StringBuilder;
        //  1627: dup            
        //  1628: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1631: aload           38
        //  1633: invokevirtual   net/minecraft/entity/EntityLivingBase.getHealth:()F
        //  1636: fload           40
        //  1638: fdiv           
        //  1639: ldc_w           100.0
        //  1642: fmul           
        //  1643: f2i            
        //  1644: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1647: ldc_w           "%"
        //  1650: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1653: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1656: astore          46
        //  1658: aload_0        
        //  1659: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.healthNumber:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1662: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1665: checkcast       Ljava/lang/Boolean;
        //  1668: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1671: ifeq            1834
        //  1674: aload_0        
        //  1675: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.hoverValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1678: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1681: checkcast       Ljava/lang/Boolean;
        //  1684: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1687: ifeq            1714
        //  1690: aload           14
        //  1692: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.mc:Lnet/minecraft/client/Minecraft;
        //  1695: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1698: if_acmpeq       1714
        //  1701: aload_0        
        //  1702: dload           29
        //  1704: dload           33
        //  1706: dload           31
        //  1708: dload           35
        //  1710: aload_3        
        //  1711: ifeq            1834
        //  1714: aload_0        
        //  1715: aload_0        
        //  1716: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.hpMode:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1719: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1722: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D$Mode4.Health:Lme/rebirthclient/mod/modules/impl/render/ESP2D$Mode4;
        //  1725: if_acmpne       1733
        //  1728: aload           45
        //  1730: goto            1735
        //  1733: aload           46
        //  1735: dload           29
        //  1737: ldc2_w          4.0
        //  1740: dsub           
        //  1741: getstatic       me/rebirthclient/api/managers/Managers.TEXT:Lme/rebirthclient/api/managers/impl/TextManager;
        //  1744: aload_0        
        //  1745: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.hpMode:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1748: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1751: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D$Mode4.Health:Lme/rebirthclient/mod/modules/impl/render/ESP2D$Mode4;
        //  1754: if_acmpne       1762
        //  1757: aload           45
        //  1759: goto            1764
        //  1762: aload           46
        //  1764: invokevirtual   me/rebirthclient/api/managers/impl/TextManager.getStringWidth:(Ljava/lang/String;)I
        //  1767: i2f            
        //  1768: aload_0        
        //  1769: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1772: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1775: checkcast       Ljava/lang/Float;
        //  1778: invokevirtual   java/lang/Float.floatValue:()F
        //  1781: fmul           
        //  1782: f2d            
        //  1783: dsub           
        //  1784: dload           35
        //  1786: dload           43
        //  1788: dsub           
        //  1789: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.mc:Lnet/minecraft/client/Minecraft;
        //  1792: getfield        net/minecraft/client/Minecraft.fontRenderer:Lnet/minecraft/client/gui/FontRenderer;
        //  1795: getfield        net/minecraft/client/gui/FontRenderer.FONT_HEIGHT:I
        //  1798: i2f            
        //  1799: fconst_2       
        //  1800: fdiv           
        //  1801: aload_0        
        //  1802: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1805: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1808: checkcast       Ljava/lang/Float;
        //  1811: invokevirtual   java/lang/Float.floatValue:()F
        //  1814: fmul           
        //  1815: f2d            
        //  1816: dsub           
        //  1817: aload_0        
        //  1818: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1821: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1824: checkcast       Ljava/lang/Float;
        //  1827: invokevirtual   java/lang/Float.floatValue:()F
        //  1830: f2d            
        //  1831: invokespecial   me/rebirthclient/mod/modules/impl/render/ESP2D.drawScaledString:(Ljava/lang/String;DDD)V
        //  1834: dload           29
        //  1836: ldc2_w          3.5
        //  1839: dsub           
        //  1840: dload           31
        //  1842: ldc2_w          0.5
        //  1845: dsub           
        //  1846: dload           29
        //  1848: ldc2_w          1.5
        //  1851: dsub           
        //  1852: dload           35
        //  1854: ldc2_w          0.5
        //  1857: dadd           
        //  1858: aload_0        
        //  1859: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.backgroundColor:I
        //  1862: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  1865: fload           39
        //  1867: fconst_0       
        //  1868: fcmpl          
        //  1869: ifle            2061
        //  1872: fload           39
        //  1874: fload           40
        //  1876: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.getHealthColor:(FF)Ljava/awt/Color;
        //  1879: invokevirtual   java/awt/Color.getRGB:()I
        //  1882: istore          47
        //  1884: dload           35
        //  1886: dload           31
        //  1888: dsub           
        //  1889: dstore          48
        //  1891: aload_0        
        //  1892: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.hpBarMode:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1895: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1898: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D$Mode2.Dot:Lme/rebirthclient/mod/modules/impl/render/ESP2D$Mode2;
        //  1901: if_acmpne       2037
        //  1904: dload           48
        //  1906: ldc2_w          60.0
        //  1909: dcmpl          
        //  1910: iflt            2037
        //  1913: dconst_0       
        //  1914: dstore          50
        //  1916: dload           50
        //  1918: ldc2_w          10.0
        //  1921: dcmpg          
        //  1922: ifge            2034
        //  1925: fload           39
        //  1927: f2d            
        //  1928: dload           50
        //  1930: fload           40
        //  1932: f2d            
        //  1933: ldc2_w          10.0
        //  1936: ddiv           
        //  1937: dmul           
        //  1938: dsub           
        //  1939: dconst_0       
        //  1940: fload           40
        //  1942: f2d            
        //  1943: ldc2_w          10.0
        //  1946: ddiv           
        //  1947: invokestatic    net/minecraft/util/math/MathHelper.clamp:(DDD)D
        //  1950: fload           40
        //  1952: f2d            
        //  1953: ldc2_w          10.0
        //  1956: ddiv           
        //  1957: ddiv           
        //  1958: dstore          52
        //  1960: dload           48
        //  1962: ldc2_w          10.0
        //  1965: ddiv           
        //  1966: ldc2_w          0.5
        //  1969: dsub           
        //  1970: dload           52
        //  1972: dmul           
        //  1973: dstore          54
        //  1975: dload           29
        //  1977: ldc2_w          3.0
        //  1980: dsub           
        //  1981: dload           35
        //  1983: dload           48
        //  1985: ldc2_w          0.5
        //  1988: dadd           
        //  1989: ldc2_w          10.0
        //  1992: ddiv           
        //  1993: dload           50
        //  1995: dmul           
        //  1996: dsub           
        //  1997: dload           29
        //  1999: ldc2_w          2.0
        //  2002: dsub           
        //  2003: dload           35
        //  2005: dload           48
        //  2007: ldc2_w          0.5
        //  2010: dadd           
        //  2011: ldc2_w          10.0
        //  2014: ddiv           
        //  2015: dload           50
        //  2017: dmul           
        //  2018: dsub           
        //  2019: dload           54
        //  2021: dsub           
        //  2022: iload           47
        //  2024: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  2027: dload           50
        //  2029: dconst_1       
        //  2030: dadd           
        //  2031: dstore          50
        //  2033: return         
        //  2034: goto            2061
        //  2037: dload           29
        //  2039: ldc2_w          3.0
        //  2042: dsub           
        //  2043: dload           35
        //  2045: dload           29
        //  2047: ldc2_w          2.0
        //  2050: dsub           
        //  2051: dload           35
        //  2053: dload           43
        //  2055: dsub           
        //  2056: iload           47
        //  2058: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  2061: aload_0        
        //  2062: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.tagsValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2065: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2068: checkcast       Ljava/lang/Boolean;
        //  2071: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  2074: ifeq            2391
        //  2077: aload_0        
        //  2078: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.clearNameValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2081: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2084: checkcast       Ljava/lang/Boolean;
        //  2087: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  2090: ifeq            2101
        //  2093: aload           38
        //  2095: invokevirtual   net/minecraft/entity/EntityLivingBase.getName:()Ljava/lang/String;
        //  2098: goto            2111
        //  2101: aload           38
        //  2103: invokevirtual   net/minecraft/entity/EntityLivingBase.getDisplayName:()Lnet/minecraft/util/text/ITextComponent;
        //  2106: invokeinterface net/minecraft/util/text/ITextComponent.getFormattedText:()Ljava/lang/String;
        //  2111: astore          45
        //  2113: aload_0        
        //  2114: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.friendColor:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2117: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2120: checkcast       Ljava/lang/Boolean;
        //  2123: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  2126: ifeq            2166
        //  2129: getstatic       me/rebirthclient/api/managers/Managers.FRIENDS:Lme/rebirthclient/api/managers/impl/FriendManager;
        //  2132: aload           38
        //  2134: invokevirtual   net/minecraft/entity/EntityLivingBase.getName:()Ljava/lang/String;
        //  2137: invokevirtual   me/rebirthclient/api/managers/impl/FriendManager.isFriend:(Ljava/lang/String;)Z
        //  2140: ifeq            2166
        //  2143: new             Ljava/lang/StringBuilder;
        //  2146: dup            
        //  2147: invokespecial   java/lang/StringBuilder.<init>:()V
        //  2150: ldc_w           "¡ìb"
        //  2153: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2156: aload           45
        //  2158: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2161: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  2164: astore          45
        //  2166: aload_0        
        //  2167: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.tagsBGValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2170: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2173: checkcast       Ljava/lang/Boolean;
        //  2176: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  2179: ifeq            2329
        //  2182: dload           29
        //  2184: dload           33
        //  2186: dload           29
        //  2188: dsub           
        //  2189: ldc2_w          2.0
        //  2192: ddiv           
        //  2193: dadd           
        //  2194: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.mc:Lnet/minecraft/client/Minecraft;
        //  2197: getfield        net/minecraft/client/Minecraft.fontRenderer:Lnet/minecraft/client/gui/FontRenderer;
        //  2200: aload           45
        //  2202: invokevirtual   net/minecraft/client/gui/FontRenderer.getStringWidth:(Ljava/lang/String;)I
        //  2205: i2f            
        //  2206: fconst_2       
        //  2207: fdiv           
        //  2208: fconst_2       
        //  2209: fadd           
        //  2210: aload_0        
        //  2211: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2214: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2217: checkcast       Ljava/lang/Float;
        //  2220: invokevirtual   java/lang/Float.floatValue:()F
        //  2223: fmul           
        //  2224: f2d            
        //  2225: dsub           
        //  2226: dload           31
        //  2228: dconst_1       
        //  2229: dsub           
        //  2230: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.mc:Lnet/minecraft/client/Minecraft;
        //  2233: getfield        net/minecraft/client/Minecraft.fontRenderer:Lnet/minecraft/client/gui/FontRenderer;
        //  2236: getfield        net/minecraft/client/gui/FontRenderer.FONT_HEIGHT:I
        //  2239: i2f            
        //  2240: fconst_2       
        //  2241: fadd           
        //  2242: aload_0        
        //  2243: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2246: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2249: checkcast       Ljava/lang/Float;
        //  2252: invokevirtual   java/lang/Float.floatValue:()F
        //  2255: fmul           
        //  2256: f2d            
        //  2257: dsub           
        //  2258: dload           29
        //  2260: dload           33
        //  2262: dload           29
        //  2264: dsub           
        //  2265: ldc2_w          2.0
        //  2268: ddiv           
        //  2269: dadd           
        //  2270: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.mc:Lnet/minecraft/client/Minecraft;
        //  2273: getfield        net/minecraft/client/Minecraft.fontRenderer:Lnet/minecraft/client/gui/FontRenderer;
        //  2276: aload           45
        //  2278: invokevirtual   net/minecraft/client/gui/FontRenderer.getStringWidth:(Ljava/lang/String;)I
        //  2281: i2f            
        //  2282: fconst_2       
        //  2283: fdiv           
        //  2284: fconst_2       
        //  2285: fadd           
        //  2286: aload_0        
        //  2287: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2290: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2293: checkcast       Ljava/lang/Float;
        //  2296: invokevirtual   java/lang/Float.floatValue:()F
        //  2299: fmul           
        //  2300: f2d            
        //  2301: dadd           
        //  2302: dload           31
        //  2304: dconst_1       
        //  2305: dsub           
        //  2306: fconst_2       
        //  2307: aload_0        
        //  2308: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2311: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2314: checkcast       Ljava/lang/Float;
        //  2317: invokevirtual   java/lang/Float.floatValue:()F
        //  2320: fmul           
        //  2321: f2d            
        //  2322: dadd           
        //  2323: ldc_w           -1610612736
        //  2326: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  2329: aload_0        
        //  2330: aload           45
        //  2332: dload           29
        //  2334: dload           33
        //  2336: dload           29
        //  2338: dsub           
        //  2339: ldc2_w          2.0
        //  2342: ddiv           
        //  2343: dadd           
        //  2344: dload           31
        //  2346: dconst_1       
        //  2347: dsub           
        //  2348: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.mc:Lnet/minecraft/client/Minecraft;
        //  2351: getfield        net/minecraft/client/Minecraft.fontRenderer:Lnet/minecraft/client/gui/FontRenderer;
        //  2354: getfield        net/minecraft/client/gui/FontRenderer.FONT_HEIGHT:I
        //  2357: i2f            
        //  2358: aload_0        
        //  2359: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2362: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2365: checkcast       Ljava/lang/Float;
        //  2368: invokevirtual   java/lang/Float.floatValue:()F
        //  2371: fmul           
        //  2372: f2d            
        //  2373: dsub           
        //  2374: aload_0        
        //  2375: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2378: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2381: checkcast       Ljava/lang/Float;
        //  2384: invokevirtual   java/lang/Float.floatValue:()F
        //  2387: f2d            
        //  2388: invokespecial   me/rebirthclient/mod/modules/impl/render/ESP2D.drawScaledCenteredString:(Ljava/lang/String;DDD)V
        //  2391: aload_0        
        //  2392: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.armorBar:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2395: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2398: checkcast       Ljava/lang/Boolean;
        //  2401: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  2404: ifeq            2658
        //  2407: aload           14
        //  2409: instanceof      Lnet/minecraft/entity/player/EntityPlayer;
        //  2412: ifeq            2658
        //  2415: dload           35
        //  2417: dload           31
        //  2419: dsub           
        //  2420: ldc2_w          4.0
        //  2423: ddiv           
        //  2424: dstore          45
        //  2426: iconst_4       
        //  2427: istore          47
        //  2429: iload           47
        //  2431: ifle            2658
        //  2434: aload           38
        //  2436: getstatic       net/minecraft/inventory/EntityEquipmentSlot.HEAD:Lnet/minecraft/inventory/EntityEquipmentSlot;
        //  2439: invokevirtual   net/minecraft/entity/EntityLivingBase.getItemStackFromSlot:(Lnet/minecraft/inventory/EntityEquipmentSlot;)Lnet/minecraft/item/ItemStack;
        //  2442: astore          48
        //  2444: iload           47
        //  2446: iconst_3       
        //  2447: if_icmpne       2460
        //  2450: aload           38
        //  2452: getstatic       net/minecraft/inventory/EntityEquipmentSlot.CHEST:Lnet/minecraft/inventory/EntityEquipmentSlot;
        //  2455: invokevirtual   net/minecraft/entity/EntityLivingBase.getItemStackFromSlot:(Lnet/minecraft/inventory/EntityEquipmentSlot;)Lnet/minecraft/item/ItemStack;
        //  2458: astore          48
        //  2460: iload           47
        //  2462: iconst_2       
        //  2463: if_icmpne       2476
        //  2466: aload           38
        //  2468: getstatic       net/minecraft/inventory/EntityEquipmentSlot.LEGS:Lnet/minecraft/inventory/EntityEquipmentSlot;
        //  2471: invokevirtual   net/minecraft/entity/EntityLivingBase.getItemStackFromSlot:(Lnet/minecraft/inventory/EntityEquipmentSlot;)Lnet/minecraft/item/ItemStack;
        //  2474: astore          48
        //  2476: iload           47
        //  2478: iconst_1       
        //  2479: if_icmpne       2492
        //  2482: aload           38
        //  2484: getstatic       net/minecraft/inventory/EntityEquipmentSlot.FEET:Lnet/minecraft/inventory/EntityEquipmentSlot;
        //  2487: invokevirtual   net/minecraft/entity/EntityLivingBase.getItemStackFromSlot:(Lnet/minecraft/inventory/EntityEquipmentSlot;)Lnet/minecraft/item/ItemStack;
        //  2490: astore          48
        //  2492: dload           45
        //  2494: ldc2_w          0.25
        //  2497: dadd           
        //  2498: dstore          49
        //  2500: dload           33
        //  2502: ldc2_w          1.5
        //  2505: dadd           
        //  2506: dload           35
        //  2508: ldc2_w          0.5
        //  2511: dadd           
        //  2512: dload           49
        //  2514: iload           47
        //  2516: i2d            
        //  2517: dmul           
        //  2518: dsub           
        //  2519: dload           33
        //  2521: ldc2_w          3.5
        //  2524: dadd           
        //  2525: dload           35
        //  2527: ldc2_w          0.5
        //  2530: dadd           
        //  2531: dload           49
        //  2533: iload           47
        //  2535: iconst_1       
        //  2536: isub           
        //  2537: i2d            
        //  2538: dmul           
        //  2539: dsub           
        //  2540: new             Ljava/awt/Color;
        //  2543: dup            
        //  2544: iconst_0       
        //  2545: iconst_0       
        //  2546: iconst_0       
        //  2547: bipush          120
        //  2549: invokespecial   java/awt/Color.<init>:(IIII)V
        //  2552: invokevirtual   java/awt/Color.getRGB:()I
        //  2555: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  2558: dload           33
        //  2560: ldc2_w          2.0
        //  2563: dadd           
        //  2564: dload           35
        //  2566: ldc2_w          0.5
        //  2569: dadd           
        //  2570: dload           49
        //  2572: iload           47
        //  2574: iconst_1       
        //  2575: isub           
        //  2576: i2d            
        //  2577: dmul           
        //  2578: dsub           
        //  2579: ldc2_w          0.25
        //  2582: dsub           
        //  2583: dload           33
        //  2585: ldc2_w          3.0
        //  2588: dadd           
        //  2589: dload           35
        //  2591: ldc2_w          0.5
        //  2594: dadd           
        //  2595: dload           49
        //  2597: iload           47
        //  2599: iconst_1       
        //  2600: isub           
        //  2601: i2d            
        //  2602: dmul           
        //  2603: dsub           
        //  2604: ldc2_w          0.25
        //  2607: dsub           
        //  2608: dload           45
        //  2610: ldc2_w          0.25
        //  2613: dsub           
        //  2614: aload           48
        //  2616: invokestatic    me/rebirthclient/api/util/InventoryUtil.getItemDurability:(Lnet/minecraft/item/ItemStack;)I
        //  2619: i2d            
        //  2620: aload           48
        //  2622: invokevirtual   net/minecraft/item/ItemStack.getMaxDamage:()I
        //  2625: i2d            
        //  2626: ddiv           
        //  2627: dconst_0       
        //  2628: dconst_1       
        //  2629: invokestatic    net/minecraft/util/math/MathHelper.clamp:(DDD)D
        //  2632: dmul           
        //  2633: dsub           
        //  2634: new             Ljava/awt/Color;
        //  2637: dup            
        //  2638: iconst_0       
        //  2639: sipush          255
        //  2642: sipush          255
        //  2645: invokespecial   java/awt/Color.<init>:(III)V
        //  2648: invokevirtual   java/awt/Color.getRGB:()I
        //  2651: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  2654: iinc            47, -1
        //  2657: return         
        //  2658: aload_0        
        //  2659: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.armorItems:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2662: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2665: checkcast       Ljava/lang/Boolean;
        //  2668: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  2671: ifeq            2964
        //  2674: aload_0        
        //  2675: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.hoverValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2678: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2681: checkcast       Ljava/lang/Boolean;
        //  2684: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  2687: ifeq            2714
        //  2690: aload           14
        //  2692: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.mc:Lnet/minecraft/client/Minecraft;
        //  2695: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2698: if_acmpeq       2714
        //  2701: aload_0        
        //  2702: dload           29
        //  2704: dload           33
        //  2706: dload           31
        //  2708: dload           35
        //  2710: aload_3        
        //  2711: ifeq            2964
        //  2714: dload           35
        //  2716: dload           31
        //  2718: dsub           
        //  2719: ldc2_w          4.0
        //  2722: ddiv           
        //  2723: dstore          45
        //  2725: iconst_4       
        //  2726: istore          47
        //  2728: iload           47
        //  2730: ifle            2964
        //  2733: aload           38
        //  2735: getstatic       net/minecraft/inventory/EntityEquipmentSlot.HEAD:Lnet/minecraft/inventory/EntityEquipmentSlot;
        //  2738: invokevirtual   net/minecraft/entity/EntityLivingBase.getItemStackFromSlot:(Lnet/minecraft/inventory/EntityEquipmentSlot;)Lnet/minecraft/item/ItemStack;
        //  2741: astore          48
        //  2743: iload           47
        //  2745: iconst_3       
        //  2746: if_icmpne       2759
        //  2749: aload           38
        //  2751: getstatic       net/minecraft/inventory/EntityEquipmentSlot.CHEST:Lnet/minecraft/inventory/EntityEquipmentSlot;
        //  2754: invokevirtual   net/minecraft/entity/EntityLivingBase.getItemStackFromSlot:(Lnet/minecraft/inventory/EntityEquipmentSlot;)Lnet/minecraft/item/ItemStack;
        //  2757: astore          48
        //  2759: iload           47
        //  2761: iconst_2       
        //  2762: if_icmpne       2775
        //  2765: aload           38
        //  2767: getstatic       net/minecraft/inventory/EntityEquipmentSlot.LEGS:Lnet/minecraft/inventory/EntityEquipmentSlot;
        //  2770: invokevirtual   net/minecraft/entity/EntityLivingBase.getItemStackFromSlot:(Lnet/minecraft/inventory/EntityEquipmentSlot;)Lnet/minecraft/item/ItemStack;
        //  2773: astore          48
        //  2775: iload           47
        //  2777: iconst_1       
        //  2778: if_icmpne       2791
        //  2781: aload           38
        //  2783: getstatic       net/minecraft/inventory/EntityEquipmentSlot.FEET:Lnet/minecraft/inventory/EntityEquipmentSlot;
        //  2786: invokevirtual   net/minecraft/entity/EntityLivingBase.getItemStackFromSlot:(Lnet/minecraft/inventory/EntityEquipmentSlot;)Lnet/minecraft/item/ItemStack;
        //  2789: astore          48
        //  2791: aload_0        
        //  2792: aload           48
        //  2794: dload           33
        //  2796: aload_0        
        //  2797: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.armorBar:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2800: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2803: checkcast       Ljava/lang/Boolean;
        //  2806: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  2809: ifeq            2818
        //  2812: ldc2_w          4.0
        //  2815: goto            2821
        //  2818: ldc2_w          2.0
        //  2821: dadd           
        //  2822: dload           31
        //  2824: dload           45
        //  2826: iconst_4       
        //  2827: iload           47
        //  2829: isub           
        //  2830: i2d            
        //  2831: dmul           
        //  2832: dadd           
        //  2833: dload           45
        //  2835: ldc2_w          2.0
        //  2838: ddiv           
        //  2839: dadd           
        //  2840: ldc2_w          5.0
        //  2843: dsub           
        //  2844: invokespecial   me/rebirthclient/mod/modules/impl/render/ESP2D.renderItemStack:(Lnet/minecraft/item/ItemStack;DD)V
        //  2847: aload_0        
        //  2848: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.armorDur:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2851: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2854: checkcast       Ljava/lang/Boolean;
        //  2857: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  2860: ifeq            2960
        //  2863: aload_0        
        //  2864: new             Ljava/lang/StringBuilder;
        //  2867: dup            
        //  2868: invokespecial   java/lang/StringBuilder.<init>:()V
        //  2871: aload           48
        //  2873: invokestatic    me/rebirthclient/api/util/InventoryUtil.getItemDurability:(Lnet/minecraft/item/ItemStack;)I
        //  2876: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  2879: ldc_w           ""
        //  2882: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2885: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  2888: dload           33
        //  2890: aload_0        
        //  2891: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.armorBar:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2894: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2897: checkcast       Ljava/lang/Boolean;
        //  2900: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  2903: ifeq            2912
        //  2906: ldc2_w          4.0
        //  2909: goto            2915
        //  2912: ldc2_w          2.0
        //  2915: dadd           
        //  2916: ldc2_w          4.5
        //  2919: dadd           
        //  2920: dload           31
        //  2922: dload           45
        //  2924: iconst_4       
        //  2925: iload           47
        //  2927: isub           
        //  2928: i2d            
        //  2929: dmul           
        //  2930: dadd           
        //  2931: dload           45
        //  2933: ldc2_w          2.0
        //  2936: ddiv           
        //  2937: dadd           
        //  2938: ldc2_w          4.0
        //  2941: dadd           
        //  2942: aload_0        
        //  2943: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2946: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2949: checkcast       Ljava/lang/Float;
        //  2952: invokevirtual   java/lang/Float.floatValue:()F
        //  2955: f2d            
        //  2956: iconst_m1      
        //  2957: invokespecial   me/rebirthclient/mod/modules/impl/render/ESP2D.drawScaledCenteredString:(Ljava/lang/String;DDDI)V
        //  2960: iinc            47, -1
        //  2963: return         
        //  2964: aload_0        
        //  2965: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.itemTagsValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2968: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2971: checkcast       Ljava/lang/Boolean;
        //  2974: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  2977: ifeq            3485
        //  2980: aload_0        
        //  2981: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.itemValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2984: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2987: checkcast       Ljava/lang/Boolean;
        //  2990: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  2993: ifne            3436
        //  2996: aload           38
        //  2998: getstatic       net/minecraft/util/EnumHand.MAIN_HAND:Lnet/minecraft/util/EnumHand;
        //  3001: invokevirtual   net/minecraft/entity/EntityLivingBase.getHeldItem:(Lnet/minecraft/util/EnumHand;)Lnet/minecraft/item/ItemStack;
        //  3004: invokevirtual   net/minecraft/item/ItemStack.getDisplayName:()Ljava/lang/String;
        //  3007: astore          45
        //  3009: aload_0        
        //  3010: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.tagsBGValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  3013: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  3016: checkcast       Ljava/lang/Boolean;
        //  3019: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  3022: ifeq            3172
        //  3025: dload           29
        //  3027: dload           33
        //  3029: dload           29
        //  3031: dsub           
        //  3032: ldc2_w          2.0
        //  3035: ddiv           
        //  3036: dadd           
        //  3037: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.mc:Lnet/minecraft/client/Minecraft;
        //  3040: getfield        net/minecraft/client/Minecraft.fontRenderer:Lnet/minecraft/client/gui/FontRenderer;
        //  3043: aload           45
        //  3045: invokevirtual   net/minecraft/client/gui/FontRenderer.getStringWidth:(Ljava/lang/String;)I
        //  3048: i2f            
        //  3049: fconst_2       
        //  3050: fdiv           
        //  3051: fconst_2       
        //  3052: fadd           
        //  3053: aload_0        
        //  3054: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  3057: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  3060: checkcast       Ljava/lang/Float;
        //  3063: invokevirtual   java/lang/Float.floatValue:()F
        //  3066: fmul           
        //  3067: f2d            
        //  3068: dsub           
        //  3069: dload           35
        //  3071: dconst_1       
        //  3072: dadd           
        //  3073: fconst_2       
        //  3074: aload_0        
        //  3075: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  3078: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  3081: checkcast       Ljava/lang/Float;
        //  3084: invokevirtual   java/lang/Float.floatValue:()F
        //  3087: fmul           
        //  3088: f2d            
        //  3089: dsub           
        //  3090: dload           29
        //  3092: dload           33
        //  3094: dload           29
        //  3096: dsub           
        //  3097: ldc2_w          2.0
        //  3100: ddiv           
        //  3101: dadd           
        //  3102: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.mc:Lnet/minecraft/client/Minecraft;
        //  3105: getfield        net/minecraft/client/Minecraft.fontRenderer:Lnet/minecraft/client/gui/FontRenderer;
        //  3108: aload           45
        //  3110: invokevirtual   net/minecraft/client/gui/FontRenderer.getStringWidth:(Ljava/lang/String;)I
        //  3113: i2f            
        //  3114: fconst_2       
        //  3115: fdiv           
        //  3116: fconst_2       
        //  3117: fadd           
        //  3118: aload_0        
        //  3119: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  3122: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  3125: checkcast       Ljava/lang/Float;
        //  3128: invokevirtual   java/lang/Float.floatValue:()F
        //  3131: fmul           
        //  3132: f2d            
        //  3133: dadd           
        //  3134: dload           35
        //  3136: dconst_1       
        //  3137: dadd           
        //  3138: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.mc:Lnet/minecraft/client/Minecraft;
        //  3141: getfield        net/minecraft/client/Minecraft.fontRenderer:Lnet/minecraft/client/gui/FontRenderer;
        //  3144: getfield        net/minecraft/client/gui/FontRenderer.FONT_HEIGHT:I
        //  3147: i2f            
        //  3148: fconst_2       
        //  3149: fadd           
        //  3150: aload_0        
        //  3151: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  3154: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  3157: checkcast       Ljava/lang/Float;
        //  3160: invokevirtual   java/lang/Float.floatValue:()F
        //  3163: fmul           
        //  3164: f2d            
        //  3165: dadd           
        //  3166: ldc_w           -1610612736
        //  3169: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  3172: aload_0        
        //  3173: aload           45
        //  3175: dload           29
        //  3177: dload           33
        //  3179: dload           29
        //  3181: dsub           
        //  3182: ldc2_w          2.0
        //  3185: ddiv           
        //  3186: dadd           
        //  3187: dload           35
        //  3189: dconst_1       
        //  3190: dadd           
        //  3191: aload_0        
        //  3192: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  3195: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  3198: checkcast       Ljava/lang/Float;
        //  3201: invokevirtual   java/lang/Float.floatValue:()F
        //  3204: f2d            
        //  3205: iconst_m1      
        //  3206: invokespecial   me/rebirthclient/mod/modules/impl/render/ESP2D.drawScaledCenteredString:(Ljava/lang/String;DDDI)V
        //  3209: aload           38
        //  3211: getstatic       net/minecraft/util/EnumHand.OFF_HAND:Lnet/minecraft/util/EnumHand;
        //  3214: invokevirtual   net/minecraft/entity/EntityLivingBase.getHeldItem:(Lnet/minecraft/util/EnumHand;)Lnet/minecraft/item/ItemStack;
        //  3217: invokevirtual   net/minecraft/item/ItemStack.getDisplayName:()Ljava/lang/String;
        //  3220: astore          45
        //  3222: ldc_w           7.5
        //  3225: fstore          46
        //  3227: aload_0        
        //  3228: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.tagsBGValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  3231: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  3234: checkcast       Ljava/lang/Boolean;
        //  3237: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  3240: ifeq            3394
        //  3243: dload           29
        //  3245: dload           33
        //  3247: dload           29
        //  3249: dsub           
        //  3250: ldc2_w          2.0
        //  3253: ddiv           
        //  3254: dadd           
        //  3255: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.mc:Lnet/minecraft/client/Minecraft;
        //  3258: getfield        net/minecraft/client/Minecraft.fontRenderer:Lnet/minecraft/client/gui/FontRenderer;
        //  3261: aload           45
        //  3263: invokevirtual   net/minecraft/client/gui/FontRenderer.getStringWidth:(Ljava/lang/String;)I
        //  3266: i2f            
        //  3267: fconst_2       
        //  3268: fdiv           
        //  3269: fconst_2       
        //  3270: fadd           
        //  3271: aload_0        
        //  3272: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  3275: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  3278: checkcast       Ljava/lang/Float;
        //  3281: invokevirtual   java/lang/Float.floatValue:()F
        //  3284: fmul           
        //  3285: f2d            
        //  3286: dsub           
        //  3287: dload           35
        //  3289: fload           46
        //  3291: f2d            
        //  3292: dadd           
        //  3293: fconst_2       
        //  3294: aload_0        
        //  3295: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  3298: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  3301: checkcast       Ljava/lang/Float;
        //  3304: invokevirtual   java/lang/Float.floatValue:()F
        //  3307: fmul           
        //  3308: f2d            
        //  3309: dsub           
        //  3310: dload           29
        //  3312: dload           33
        //  3314: dload           29
        //  3316: dsub           
        //  3317: ldc2_w          2.0
        //  3320: ddiv           
        //  3321: dadd           
        //  3322: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.mc:Lnet/minecraft/client/Minecraft;
        //  3325: getfield        net/minecraft/client/Minecraft.fontRenderer:Lnet/minecraft/client/gui/FontRenderer;
        //  3328: aload           45
        //  3330: invokevirtual   net/minecraft/client/gui/FontRenderer.getStringWidth:(Ljava/lang/String;)I
        //  3333: i2f            
        //  3334: fconst_2       
        //  3335: fdiv           
        //  3336: fconst_2       
        //  3337: fadd           
        //  3338: aload_0        
        //  3339: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  3342: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  3345: checkcast       Ljava/lang/Float;
        //  3348: invokevirtual   java/lang/Float.floatValue:()F
        //  3351: fmul           
        //  3352: f2d            
        //  3353: dadd           
        //  3354: dload           35
        //  3356: fload           46
        //  3358: f2d            
        //  3359: dadd           
        //  3360: getstatic       me/rebirthclient/mod/modules/impl/render/ESP2D.mc:Lnet/minecraft/client/Minecraft;
        //  3363: getfield        net/minecraft/client/Minecraft.fontRenderer:Lnet/minecraft/client/gui/FontRenderer;
        //  3366: getfield        net/minecraft/client/gui/FontRenderer.FONT_HEIGHT:I
        //  3369: i2f            
        //  3370: fconst_2       
        //  3371: fadd           
        //  3372: aload_0        
        //  3373: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  3376: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  3379: checkcast       Ljava/lang/Float;
        //  3382: invokevirtual   java/lang/Float.floatValue:()F
        //  3385: fmul           
        //  3386: f2d            
        //  3387: dadd           
        //  3388: ldc_w           -1610612736
        //  3391: invokestatic    me/rebirthclient/mod/modules/impl/render/ESP2D.newDrawRect:(DDDDI)V
        //  3394: aload_0        
        //  3395: aload           45
        //  3397: dload           29
        //  3399: dload           33
        //  3401: dload           29
        //  3403: dsub           
        //  3404: ldc2_w          2.0
        //  3407: ddiv           
        //  3408: dadd           
        //  3409: dload           35
        //  3411: fload           46
        //  3413: f2d            
        //  3414: dadd           
        //  3415: aload_0        
        //  3416: getfield        me/rebirthclient/mod/modules/impl/render/ESP2D.fontScaleValue:Lme/rebirthclient/mod/modules/settings/Setting;
        //  3419: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  3422: checkcast       Ljava/lang/Float;
        //  3425: invokevirtual   java/lang/Float.floatValue:()F
        //  3428: f2d            
        //  3429: iconst_m1      
        //  3430: invokespecial   me/rebirthclient/mod/modules/impl/render/ESP2D.drawScaledCenteredString:(Ljava/lang/String;DDDI)V
        //  3433: goto            3485
        //  3436: aload_0        
        //  3437: aload           38
        //  3439: invokevirtual   net/minecraft/entity/EntityLivingBase.getHeldItemMainhand:()Lnet/minecraft/item/ItemStack;
        //  3442: dload           29
        //  3444: dload           35
        //  3446: invokespecial   me/rebirthclient/mod/modules/impl/render/ESP2D.renderItemStack:(Lnet/minecraft/item/ItemStack;DD)V
        //  3449: aload_0        
        //  3450: aload           38
        //  3452: invokevirtual   net/minecraft/entity/EntityLivingBase.getHeldItemOffhand:()Lnet/minecraft/item/ItemStack;
        //  3455: dload           29
        //  3457: aload           38
        //  3459: invokevirtual   net/minecraft/entity/EntityLivingBase.getHeldItemMainhand:()Lnet/minecraft/item/ItemStack;
        //  3462: invokevirtual   net/minecraft/item/ItemStack.getItem:()Lnet/minecraft/item/Item;
        //  3465: getstatic       net/minecraft/init/Items.AIR:Lnet/minecraft/item/Item;
        //  3468: if_acmpne       3475
        //  3471: fconst_0       
        //  3472: goto            3478
        //  3475: ldc_w           8.0
        //  3478: f2d            
        //  3479: dadd           
        //  3480: dload           35
        //  3482: invokespecial   me/rebirthclient/mod/modules/impl/render/ESP2D.renderItemStack:(Lnet/minecraft/item/ItemStack;DD)V
        //  3485: iinc            12, 1
        //  3488: return         
        //  3489: invokestatic    org/lwjgl/opengl/GL11.glPopMatrix:()V
        //  3492: invokestatic    net/minecraft/client/renderer/GlStateManager.enableBlend:()V
        //  3495: invokestatic    net/minecraft/client/renderer/GlStateManager.resetColor:()V
        //  3498: aload           9
        //  3500: invokevirtual   net/minecraft/client/renderer/EntityRenderer.setupOverlayRendering:()V
        //  3503: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #2714 (coming from #2711).
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
    
    public static void color(final double n, final double n2, final double n3, final double n4) {
        GL11.glColor4d(n, n2, n3, n4);
    }
    
    public static void render(final int n, final Runnable runnable) {
        GL11.glBegin(n);
        runnable.run();
        GL11.glEnd();
    }
    
    private boolean lambda$new$7(final Boolean b) {
        return this.healthBar.isOpen();
    }
    
    public static void glColor(final int n) {
        GlStateManager.color((n >> 16 & 0xFF) / 255.0f, (n >> 8 & 0xFF) / 255.0f, (n & 0xFF) / 255.0f, (n >> 24 & 0xFF) / 255.0f);
    }
    
    private boolean lambda$new$1(final Mode5 mode5) {
        return this.outline.isOpen();
    }
    
    private void collectEntities() {
        ESP2D.collectedEntities.clear();
        final List loadedEntityList = ESP2D.mc.world.loadedEntityList;
        int n = 0;
        if (n < loadedEntityList.size()) {
            ESP2D.collectedEntities.add(loadedEntityList.get(n));
            ++n;
        }
    }
    
    private Vector3d project2D(final int n, final double n2, final double n3, final double n4) {
        GL11.glGetFloat(2982, this.modelview);
        GL11.glGetFloat(2983, this.projection);
        GL11.glGetInteger(2978, this.viewport);
        return GLU.gluProject((float)n2, (float)n3, (float)n4, this.modelview, this.projection, this.viewport, this.vector) ? new Vector3d((double)(this.vector.get(0) / n), (double)((Display.getHeight() - this.vector.get(1)) / n), (double)this.vector.get(2)) : null;
    }
    
    private boolean lambda$new$5(final Integer n) {
        return this.colorModeValue.getValue() != Mode5.Custom && this.outline.isOpen();
    }
    
    public ESP2D() {
        super("ESP2D", "fixed", Category.RENDER);
        this.bbtt = this.add(new Setting("2B2T Mode", true));
        this.outline = this.add(new Setting("Outline", true).setParent());
        this.boxMode = this.add(new Setting("Mode", Mode.Corners, this::lambda$new$0));
        this.colorModeValue = this.add(new Setting("ColorMode", Mode5.Custom, this::lambda$new$1));
        this.color = this.add(new Setting("Color", new Color(200, 200, 200), this::lambda$new$2));
        this.saturationValue = this.add(new Setting("Saturation", 1.0f, 0.0f, 1.0f, this::lambda$new$3));
        this.brightnessValue = this.add(new Setting("Brightness", 1.0f, 0.0f, 1.0f, this::lambda$new$4));
        this.mixerSecondsValue = this.add(new Setting("Seconds", 2, 1, 10, this::lambda$new$5));
        this.outlineFont = this.add(new Setting("OutlineFont", true));
        this.healthBar = this.add(new Setting("Health-bar", true).setParent());
        this.hpBarMode = this.add(new Setting("HBar-Mode", Mode2.Dot, this::lambda$new$6));
        this.healthNumber = this.add(new Setting("Health-Number", true, this::lambda$new$7).setParent());
        this.hpMode = this.add(new Setting("HP-Mode", Mode4.Health, this::lambda$new$8));
        this.hoverValue = this.add(new Setting("Details-HoverOnly", false));
        this.itemTagsValue = this.add(new Setting("ItemTags", true).setParent());
        this.itemValue = this.add(new Setting("Item", true, this::lambda$new$9));
        this.tagsValue = this.add(new Setting("Tags", true));
        this.tagsBGValue = this.add(new Setting("Tags-Background", true, this::lambda$new$10));
        this.clearNameValue = this.add(new Setting("Use-Clear-Name", false, this::lambda$new$11));
        this.armorBar = this.add(new Setting("ArmorBar", true));
        this.armorItems = this.add(new Setting("ArmorItems", true).setParent());
        this.armorDur = this.add(new Setting("ArmorDur", true, this::lambda$new$12));
        this.friendColor = this.add(new Setting("FriendColor", true));
        this.localPlayer = this.add(new Setting("Local-Player", true));
        this.mobs = this.add(new Setting("Mobs", false));
        this.animals = this.add(new Setting("Animasl", false));
        this.droppedItems = this.add(new Setting("Dropped-Items", false));
        this.fontScaleValue = this.add(new Setting("Font-Scale", 0.5f, 0.0f, 1.0f));
        this.dFormat = new DecimalFormat("0.0");
        this.viewport = GLAllocation.createDirectIntBuffer(16);
        this.modelview = GLAllocation.createDirectFloatBuffer(16);
        this.projection = GLAllocation.createDirectFloatBuffer(16);
        this.vector = GLAllocation.createDirectFloatBuffer(4);
        this.backgroundColor = new Color(0, 0, 0, 120).getRGB();
        this.black = Color.BLACK.getRGB();
        ESP2D.INSTANCE = this;
    }
    
    public static double interpolate(final double n, final double n2, final double n3) {
        return n2 + (n - n2) * n3;
    }
    
    private boolean lambda$new$6(final Mode2 mode2) {
        return this.healthBar.isOpen();
    }
    
    private void drawScaledCenteredString(final String s, final double n, final double n2, final double n3, final int n4) {
        this.drawScaledString(s, n - ESP2D.mc.fontRenderer.getStringWidth(s) / 2.0f * n3, n2, n3, n4);
    }
    
    public void drawOutlineStringWithoutGL(final String s, final float n, final float n2, final int n3, final FontRenderer fontRenderer) {
        fontRenderer.drawString(stripColor(s), (int)(n * 2.0f - 1.0f), (int)(n2 * 2.0f), Color.BLACK.getRGB());
        fontRenderer.drawString(stripColor(s), (int)(n * 2.0f + 1.0f), (int)(n2 * 2.0f), Color.BLACK.getRGB());
        fontRenderer.drawString(stripColor(s), (int)(n * 2.0f), (int)(n2 * 2.0f - 1.0f), Color.BLACK.getRGB());
        fontRenderer.drawString(stripColor(s), (int)(n * 2.0f), (int)(n2 * 2.0f + 1.0f), Color.BLACK.getRGB());
        fontRenderer.drawString(s, (int)(n * 2.0f), (int)(n2 * 2.0f), n3);
    }
    
    static {
        collectedEntities = new ArrayList();
        COLOR_PATTERN = Pattern.compile("(?i)¡ì[0-9A-FK-OR]");
        DISPLAY_LISTS_2D = new int[4];
        frustrum = new Frustum();
        ESP2D.INSTANCE = new ESP2D();
        int n = 0;
        if (n < ESP2D.DISPLAY_LISTS_2D.length) {
            ESP2D.DISPLAY_LISTS_2D[n] = GL11.glGenLists(1);
            ++n;
            return;
        }
        GL11.glNewList(ESP2D.DISPLAY_LISTS_2D[0], 4864);
        quickDrawRect(-7.0f, 2.0f, -4.0f, 3.0f);
        quickDrawRect(4.0f, 2.0f, 7.0f, 3.0f);
        quickDrawRect(-7.0f, 0.5f, -6.0f, 3.0f);
        quickDrawRect(6.0f, 0.5f, 7.0f, 3.0f);
        GL11.glEndList();
        GL11.glNewList(ESP2D.DISPLAY_LISTS_2D[1], 4864);
        quickDrawRect(-7.0f, 3.0f, -4.0f, 3.3f);
        quickDrawRect(4.0f, 3.0f, 7.0f, 3.3f);
        quickDrawRect(-7.3f, 0.5f, -7.0f, 3.3f);
        quickDrawRect(7.0f, 0.5f, 7.3f, 3.3f);
        GL11.glEndList();
        GL11.glNewList(ESP2D.DISPLAY_LISTS_2D[2], 4864);
        quickDrawRect(4.0f, -20.0f, 7.0f, -19.0f);
        quickDrawRect(-7.0f, -20.0f, -4.0f, -19.0f);
        quickDrawRect(6.0f, -20.0f, 7.0f, -17.5f);
        quickDrawRect(-7.0f, -20.0f, -6.0f, -17.5f);
        GL11.glEndList();
        GL11.glNewList(ESP2D.DISPLAY_LISTS_2D[3], 4864);
        quickDrawRect(7.0f, -20.0f, 7.3f, -17.5f);
        quickDrawRect(-7.3f, -20.0f, -7.0f, -17.5f);
        quickDrawRect(4.0f, -20.3f, 7.3f, -20.0f);
        quickDrawRect(-7.3f, -20.3f, -4.0f, -20.0f);
        GL11.glEndList();
    }
    
    @Override
    public void onDisable() {
        ESP2D.collectedEntities.clear();
    }
    
    public static void quickDrawRect(final float n, final float n2, final float n3, final float n4) {
        GL11.glBegin(7);
        GL11.glVertex2d((double)n3, (double)n2);
        GL11.glVertex2d((double)n, (double)n2);
        GL11.glVertex2d((double)n, (double)n4);
        GL11.glVertex2d((double)n3, (double)n4);
        GL11.glEnd();
    }
    
    public static void newDrawRect(double n, double n2, double n3, double n4, final int n5) {
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
        final float n8 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n9 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n10 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n11 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(n9, n10, n11, n8);
        getBuffer.begin(7, DefaultVertexFormats.POSITION);
        getBuffer.pos(n, n4, 0.0).endVertex();
        getBuffer.pos(n3, n4, 0.0).endVertex();
        getBuffer.pos(n3, n2, 0.0).endVertex();
        getBuffer.pos(n, n2, 0.0).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static Color getHealthColor(final float n, final float n2) {
        return blendColors(new float[] { 0.0f, 0.5f, 1.0f }, new Color[] { new Color(108, 0, 0), new Color(255, 51, 0), Color.GREEN }, n / n2).brighter();
    }
    
    private boolean lambda$new$9(final Boolean b) {
        return this.itemTagsValue.isOpen();
    }
    
    private void renderItemStack(final ItemStack itemStack, final double n, final double n2) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(n, n2, n);
        GlStateManager.scale(0.5, 0.5, 0.5);
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        RenderHelper.enableGUIStandardItemLighting();
        ESP2D.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, 0, 0);
        ESP2D.mc.getRenderItem().renderItemOverlays(ESP2D.mc.fontRenderer, itemStack, 0, 0);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
    
    public static void drawRect(final double n, final double n2, final double n3, final double n4, final int n5) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        glColor(n5);
        GL11.glBegin(7);
        GL11.glVertex2d(n3, n2);
        GL11.glVertex2d(n, n2);
        GL11.glVertex2d(n, n4);
        GL11.glVertex2d(n3, n4);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }
    
    private boolean lambda$new$4(final Float n) {
        return this.colorModeValue.getValue() != Mode5.Custom && this.outline.isOpen();
    }
    
    public Color getColor(final Entity entity) {
        if (entity instanceof EntityLivingBase && entity instanceof EntityPlayer && (boolean)this.friendColor.getValue() && Managers.FRIENDS.isFriend((EntityPlayer)entity)) {
            return Color.cyan;
        }
        switch ((Mode5)this.colorModeValue.getValue()) {
            case Custom: {
                return (Color)this.color.getValue();
            }
            case AnotherRainbow: {
                return new Color(getRainbowOpaque((int)this.mixerSecondsValue.getValue(), (float)this.saturationValue.getValue(), (float)this.brightnessValue.getValue(), 0));
            }
            case Slowly: {
                return slowlyRainbow(System.nanoTime(), 0, (float)this.saturationValue.getValue(), (float)this.brightnessValue.getValue());
            }
            default: {
                return fade((Color)this.color.getValue(), 0, 100);
            }
        }
    }
    
    public static Color blend(final Color color, final Color color2, final double n) {
        final float n2 = (float)n;
        final float n3 = 1.0f - n2;
        final float[] colorComponents = color.getColorComponents(new float[3]);
        final float[] colorComponents2 = color2.getColorComponents(new float[3]);
        float n4 = colorComponents[0] * n2 + colorComponents2[0] * n3;
        float n5 = colorComponents[1] * n2 + colorComponents2[1] * n3;
        float n6 = colorComponents[2] * n2 + colorComponents2[2] * n3;
        if (n4 < 0.0f) {
            n4 = 0.0f;
        }
        else if (n4 > 255.0f) {
            n4 = 255.0f;
        }
        if (n5 < 0.0f) {
            n5 = 0.0f;
        }
        else if (n5 > 255.0f) {
            n5 = 255.0f;
        }
        if (n6 < 0.0f) {
            n6 = 0.0f;
        }
        else if (n6 > 255.0f) {
            n6 = 255.0f;
        }
        Color color3 = null;
        try {
            color3 = new Color(n4, n5, n6);
        }
        catch (IllegalArgumentException ex) {}
        return color3;
    }
    
    private boolean lambda$new$0(final Mode mode) {
        return this.outline.isOpen();
    }
    
    public static String stripColor(final String s) {
        return ESP2D.COLOR_PATTERN.matcher(s).replaceAll("");
    }
    
    private boolean lambda$new$11(final Boolean b) {
        return this.tagsValue.isOpen();
    }
    
    public static Color blendColors(final float[] array, final Color[] array2, final float n) {
        if (array.length == array2.length) {
            final int[] fractionIndices = getFractionIndices(array, n);
            final float[] array3 = { array[fractionIndices[0]], array[fractionIndices[1]] };
            final Color[] array4 = { array2[fractionIndices[0]], array2[fractionIndices[1]] };
            return blend(array4[0], array4[1], 1.0f - (n - array3[0]) / (array3[1] - array3[0]));
        }
        throw new IllegalArgumentException("Fractions and colours must have equal number of elements");
    }
    
    public static void color(Color white) {
        if (white == null) {
            white = Color.white;
        }
        color(white.getRed() / 255.0f, white.getGreen() / 255.0f, white.getBlue() / 255.0f, white.getAlpha() / 255.0f);
    }
    
    public static Color fade(final Color color, final int n, final int n2) {
        final float[] array = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), array);
        array[2] = (0.5f + 0.5f * Math.abs((System.currentTimeMillis() % 2000L / 1000.0f + n / (float)n2 * 2.0f) % 2.0f - 1.0f)) % 2.0f;
        return new Color(Color.HSBtoRGB(array[0], array[1], array[2]));
    }
    
    private boolean lambda$new$12(final Boolean b) {
        return this.armorItems.isOpen();
    }
    
    public static int getRainbowOpaque(final int n, final float n2, final float n3, final int n4) {
        return Color.HSBtoRGB((System.currentTimeMillis() + n4) % (n * 1000) / (float)(n * 1000), n2, n3);
    }
    
    private boolean lambda$new$10(final Boolean b) {
        return (boolean)this.tagsValue.getValue() || (boolean)this.itemTagsValue.getValue();
    }
    
    public enum Mode5
    {
        Slowly("Slowly", 1);
        
        private static final Mode5[] $VALUES;
        
        AnotherRainbow("AnotherRainbow", 2), 
        Custom("Custom", 0);
        
        static {
            $VALUES = new Mode5[] { Mode5.Custom, Mode5.Slowly, Mode5.AnotherRainbow };
        }
        
        private Mode5(final String s, final int n) {
        }
    }
    
    public enum Mode
    {
        private static final Mode[] $VALUES;
        
        Box("Box", 0), 
        Corners("Corners", 1);
        
        static {
            $VALUES = new Mode[] { Mode.Box, Mode.Corners };
        }
        
        private Mode(final String s, final int n) {
        }
    }
    
    public enum Mode4
    {
        Health("Health", 0);
        
        private static final Mode4[] $VALUES;
        
        Percent("Percent", 1);
        
        private Mode4(final String s, final int n) {
        }
        
        static {
            $VALUES = new Mode4[] { Mode4.Health, Mode4.Percent };
        }
    }
    
    public enum Mode2
    {
        private static final Mode2[] $VALUES;
        
        Line("Line", 1), 
        Dot("Dot", 0);
        
        private Mode2(final String s, final int n) {
        }
        
        static {
            $VALUES = new Mode2[] { Mode2.Dot, Mode2.Line };
        }
    }
}
