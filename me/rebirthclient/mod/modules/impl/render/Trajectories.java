//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import net.minecraft.item.*;
import org.lwjgl.opengl.*;
import me.rebirthclient.mod.modules.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import me.rebirthclient.api.events.impl.*;

public class Trajectories extends Module
{
    private float getGravity(final Item item) {
        if (item instanceof ItemBow || item instanceof ItemSplashPotion || item instanceof ItemLingeringPotion || item instanceof ItemExpBottle) {
            return 0.05f;
        }
        return 0.03f;
    }
    
    public void drawTracer(final double n, final double n2, final double n3) {
        GL11.glVertex3d(n, n2, n3);
    }
    
    public Trajectories() {
        super("Trajectories", "Draws trajectories", Category.RENDER);
    }
    
    private int getPitch(final Item item) {
        if (item instanceof ItemSplashPotion || item instanceof ItemLingeringPotion || item instanceof ItemExpBottle) {
            return 20;
        }
        return 0;
    }
    
    private float getVelocity(final Item item) {
        if (item instanceof ItemSplashPotion || item instanceof ItemLingeringPotion) {
            return 0.5f;
        }
        if (item instanceof ItemExpBottle) {
            return 0.59f;
        }
        return 1.5f;
    }
    
    private List getEntitiesWithinAABB(final AxisAlignedBB axisAlignedBB) {
        final ArrayList list = new ArrayList();
        final int floor = MathHelper.floor((axisAlignedBB.minX - 2.0) / 16.0);
        final int floor2 = MathHelper.floor((axisAlignedBB.maxX + 2.0) / 16.0);
        final int floor3 = MathHelper.floor((axisAlignedBB.minZ - 2.0) / 16.0);
        final int floor4 = MathHelper.floor((axisAlignedBB.maxZ + 2.0) / 16.0);
        int n = floor;
        if (n > floor2) {
            return list;
        }
        int n2 = floor3;
        if (n2 <= floor4) {
            if (Trajectories.mc.world.getChunkProvider().getLoadedChunk(n, n2) != null) {
                Trajectories.mc.world.getChunk(n, n2).getEntitiesWithinAABBForEntity((Entity)Trajectories.mc.player, axisAlignedBB, (List)list, EntitySelectors.NOT_SPECTATING);
            }
            ++n2;
            return null;
        }
        ++n;
        return null;
    }
    
    @Override
    public void onRender3D(final Render3DEvent p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //     6: ifnull          31
        //     9: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //    12: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    15: ifnull          31
        //    18: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //    21: getfield        net/minecraft/client/Minecraft.gameSettings:Lnet/minecraft/client/settings/GameSettings;
        //    24: getfield        net/minecraft/client/settings/GameSettings.thirdPersonView:I
        //    27: iconst_2       
        //    28: if_icmpne       32
        //    31: return         
        //    32: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //    35: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    38: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getHeldItemMainhand:()Lnet/minecraft/item/ItemStack;
        //    41: getstatic       net/minecraft/item/ItemStack.EMPTY:Lnet/minecraft/item/ItemStack;
        //    44: if_acmpeq       65
        //    47: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //    50: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    53: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getHeldItemMainhand:()Lnet/minecraft/item/ItemStack;
        //    56: invokevirtual   net/minecraft/item/ItemStack.getItem:()Lnet/minecraft/item/Item;
        //    59: instanceof      Lnet/minecraft/item/ItemBow;
        //    62: ifne            135
        //    65: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //    68: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    71: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getHeldItemMainhand:()Lnet/minecraft/item/ItemStack;
        //    74: getstatic       net/minecraft/item/ItemStack.EMPTY:Lnet/minecraft/item/ItemStack;
        //    77: if_acmpeq       96
        //    80: aload_0        
        //    81: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //    84: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    87: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getHeldItemMainhand:()Lnet/minecraft/item/ItemStack;
        //    90: invokevirtual   net/minecraft/item/ItemStack.getItem:()Lnet/minecraft/item/Item;
        //    93: ifne            135
        //    96: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //    99: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   102: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getHeldItemOffhand:()Lnet/minecraft/item/ItemStack;
        //   105: getstatic       net/minecraft/item/ItemStack.EMPTY:Lnet/minecraft/item/ItemStack;
        //   108: if_acmpeq       127
        //   111: aload_0        
        //   112: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   115: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   118: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getHeldItemOffhand:()Lnet/minecraft/item/ItemStack;
        //   121: invokevirtual   net/minecraft/item/ItemStack.getItem:()Lnet/minecraft/item/Item;
        //   124: ifne            135
        //   127: iconst_2       
        //   128: invokestatic    org/lwjgl/input/Mouse.isButtonDown:(I)Z
        //   131: ifne            135
        //   134: return         
        //   135: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   138: invokevirtual   net/minecraft/client/Minecraft.getRenderManager:()Lnet/minecraft/client/renderer/entity/RenderManager;
        //   141: getfield        net/minecraft/client/renderer/entity/RenderManager.renderPosX:D
        //   144: dstore_2       
        //   145: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   148: invokevirtual   net/minecraft/client/Minecraft.getRenderManager:()Lnet/minecraft/client/renderer/entity/RenderManager;
        //   151: getfield        net/minecraft/client/renderer/entity/RenderManager.renderPosY:D
        //   154: dstore          4
        //   156: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   159: invokevirtual   net/minecraft/client/Minecraft.getRenderManager:()Lnet/minecraft/client/renderer/entity/RenderManager;
        //   162: getfield        net/minecraft/client/renderer/entity/RenderManager.renderPosZ:D
        //   165: dstore          6
        //   167: aconst_null    
        //   168: astore          8
        //   170: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   173: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   176: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getHeldItemMainhand:()Lnet/minecraft/item/ItemStack;
        //   179: getstatic       net/minecraft/item/ItemStack.EMPTY:Lnet/minecraft/item/ItemStack;
        //   182: if_acmpeq       236
        //   185: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   188: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   191: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getHeldItemMainhand:()Lnet/minecraft/item/ItemStack;
        //   194: invokevirtual   net/minecraft/item/ItemStack.getItem:()Lnet/minecraft/item/Item;
        //   197: instanceof      Lnet/minecraft/item/ItemBow;
        //   200: ifne            219
        //   203: aload_0        
        //   204: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   207: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   210: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getHeldItemMainhand:()Lnet/minecraft/item/ItemStack;
        //   213: invokevirtual   net/minecraft/item/ItemStack.getItem:()Lnet/minecraft/item/Item;
        //   216: ifne            236
        //   219: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   222: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   225: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getHeldItemMainhand:()Lnet/minecraft/item/ItemStack;
        //   228: invokevirtual   net/minecraft/item/ItemStack.getItem:()Lnet/minecraft/item/Item;
        //   231: astore          8
        //   233: goto            281
        //   236: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   239: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   242: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getHeldItemOffhand:()Lnet/minecraft/item/ItemStack;
        //   245: getstatic       net/minecraft/item/ItemStack.EMPTY:Lnet/minecraft/item/ItemStack;
        //   248: if_acmpeq       281
        //   251: aload_0        
        //   252: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   255: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   258: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getHeldItemOffhand:()Lnet/minecraft/item/ItemStack;
        //   261: invokevirtual   net/minecraft/item/ItemStack.getItem:()Lnet/minecraft/item/Item;
        //   264: ifne            281
        //   267: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   270: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   273: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getHeldItemOffhand:()Lnet/minecraft/item/ItemStack;
        //   276: invokevirtual   net/minecraft/item/ItemStack.getItem:()Lnet/minecraft/item/Item;
        //   279: astore          8
        //   281: aload           8
        //   283: ifnonnull       301
        //   286: iconst_2       
        //   287: invokestatic    org/lwjgl/input/Mouse.isButtonDown:(I)Z
        //   290: ifeq            301
        //   293: getstatic       net/minecraft/init/Items.ENDER_PEARL:Lnet/minecraft/item/Item;
        //   296: astore          8
        //   298: goto            307
        //   301: aload           8
        //   303: ifnonnull       307
        //   306: return         
        //   307: ldc             1048575
        //   309: invokestatic    org/lwjgl/opengl/GL11.glPushAttrib:(I)V
        //   312: invokestatic    org/lwjgl/opengl/GL11.glPushMatrix:()V
        //   315: sipush          3008
        //   318: invokestatic    org/lwjgl/opengl/GL11.glDisable:(I)V
        //   321: sipush          3042
        //   324: invokestatic    org/lwjgl/opengl/GL11.glEnable:(I)V
        //   327: sipush          770
        //   330: sipush          771
        //   333: invokestatic    org/lwjgl/opengl/GL11.glBlendFunc:(II)V
        //   336: sipush          3553
        //   339: invokestatic    org/lwjgl/opengl/GL11.glDisable:(I)V
        //   342: sipush          2929
        //   345: invokestatic    org/lwjgl/opengl/GL11.glDisable:(I)V
        //   348: iconst_0       
        //   349: invokestatic    org/lwjgl/opengl/GL11.glDepthMask:(Z)V
        //   352: sipush          2884
        //   355: invokestatic    org/lwjgl/opengl/GL11.glEnable:(I)V
        //   358: sipush          2848
        //   361: invokestatic    org/lwjgl/opengl/GL11.glEnable:(I)V
        //   364: sipush          3154
        //   367: sipush          4353
        //   370: invokestatic    org/lwjgl/opengl/GL11.glHint:(II)V
        //   373: sipush          2896
        //   376: invokestatic    org/lwjgl/opengl/GL11.glDisable:(I)V
        //   379: dload_2        
        //   380: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   383: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   386: getfield        net/minecraft/client/entity/EntityPlayerSP.rotationYaw:F
        //   389: ldc             180.0
        //   391: fdiv           
        //   392: ldc             3.1415927
        //   394: fmul           
        //   395: invokestatic    net/minecraft/util/math/MathHelper.cos:(F)F
        //   398: ldc             0.16
        //   400: fmul           
        //   401: f2d            
        //   402: dsub           
        //   403: dstore          9
        //   405: dload           4
        //   407: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   410: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   413: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getEyeHeight:()F
        //   416: f2d            
        //   417: dadd           
        //   418: ldc2_w          0.1000000014901161
        //   421: dsub           
        //   422: dstore          11
        //   424: dload           6
        //   426: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   429: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   432: getfield        net/minecraft/client/entity/EntityPlayerSP.rotationYaw:F
        //   435: ldc             180.0
        //   437: fdiv           
        //   438: ldc             3.1415927
        //   440: fmul           
        //   441: invokestatic    net/minecraft/util/math/MathHelper.sin:(F)F
        //   444: ldc             0.16
        //   446: fmul           
        //   447: f2d            
        //   448: dsub           
        //   449: dstore          13
        //   451: aload_0        
        //   452: aload           8
        //   454: invokespecial   me/rebirthclient/mod/modules/impl/render/Trajectories.getDistance:(Lnet/minecraft/item/Item;)F
        //   457: fstore          15
        //   459: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   462: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   465: getfield        net/minecraft/client/entity/EntityPlayerSP.rotationYaw:F
        //   468: ldc             180.0
        //   470: fdiv           
        //   471: ldc             3.1415927
        //   473: fmul           
        //   474: invokestatic    net/minecraft/util/math/MathHelper.sin:(F)F
        //   477: fneg           
        //   478: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   481: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   484: getfield        net/minecraft/client/entity/EntityPlayerSP.rotationPitch:F
        //   487: ldc             180.0
        //   489: fdiv           
        //   490: ldc             3.1415927
        //   492: fmul           
        //   493: invokestatic    net/minecraft/util/math/MathHelper.cos:(F)F
        //   496: fmul           
        //   497: fload           15
        //   499: fmul           
        //   500: f2d            
        //   501: dstore          16
        //   503: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   506: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   509: getfield        net/minecraft/client/entity/EntityPlayerSP.rotationPitch:F
        //   512: aload_0        
        //   513: aload           8
        //   515: invokespecial   me/rebirthclient/mod/modules/impl/render/Trajectories.getPitch:(Lnet/minecraft/item/Item;)I
        //   518: i2f            
        //   519: fsub           
        //   520: ldc             180.0
        //   522: fdiv           
        //   523: ldc             3.141593
        //   525: fmul           
        //   526: invokestatic    net/minecraft/util/math/MathHelper.sin:(F)F
        //   529: fneg           
        //   530: fload           15
        //   532: fmul           
        //   533: f2d            
        //   534: dstore          18
        //   536: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   539: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   542: getfield        net/minecraft/client/entity/EntityPlayerSP.rotationYaw:F
        //   545: ldc             180.0
        //   547: fdiv           
        //   548: ldc             3.1415927
        //   550: fmul           
        //   551: invokestatic    net/minecraft/util/math/MathHelper.cos:(F)F
        //   554: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   557: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   560: getfield        net/minecraft/client/entity/EntityPlayerSP.rotationPitch:F
        //   563: ldc             180.0
        //   565: fdiv           
        //   566: ldc             3.1415927
        //   568: fmul           
        //   569: invokestatic    net/minecraft/util/math/MathHelper.cos:(F)F
        //   572: fmul           
        //   573: fload           15
        //   575: fmul           
        //   576: f2d            
        //   577: dstore          20
        //   579: ldc             72000
        //   581: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   584: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   587: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getItemInUseCount:()I
        //   590: isub           
        //   591: istore          22
        //   593: iload           22
        //   595: i2f            
        //   596: ldc             20.0
        //   598: fdiv           
        //   599: fstore          23
        //   601: fload           23
        //   603: fload           23
        //   605: fmul           
        //   606: fload           23
        //   608: fconst_2       
        //   609: fmul           
        //   610: fadd           
        //   611: ldc             3.0
        //   613: fdiv           
        //   614: fstore          23
        //   616: fload           23
        //   618: fconst_1       
        //   619: fcmpl          
        //   620: ifle            626
        //   623: fconst_1       
        //   624: fstore          23
        //   626: dload           16
        //   628: dload           16
        //   630: dmul           
        //   631: dload           18
        //   633: dload           18
        //   635: dmul           
        //   636: dadd           
        //   637: dload           20
        //   639: dload           20
        //   641: dmul           
        //   642: dadd           
        //   643: invokestatic    net/minecraft/util/math/MathHelper.sqrt:(D)F
        //   646: fstore          24
        //   648: dload           16
        //   650: fload           24
        //   652: f2d            
        //   653: ddiv           
        //   654: dstore          16
        //   656: dload           18
        //   658: fload           24
        //   660: f2d            
        //   661: ddiv           
        //   662: dstore          18
        //   664: dload           20
        //   666: fload           24
        //   668: f2d            
        //   669: ddiv           
        //   670: dstore          20
        //   672: aload           8
        //   674: instanceof      Lnet/minecraft/item/ItemBow;
        //   677: ifeq            687
        //   680: fload           23
        //   682: fconst_2       
        //   683: fmul           
        //   684: goto            688
        //   687: fconst_1       
        //   688: aload_0        
        //   689: aload           8
        //   691: invokespecial   me/rebirthclient/mod/modules/impl/render/Trajectories.getVelocity:(Lnet/minecraft/item/Item;)F
        //   694: fmul           
        //   695: fstore          25
        //   697: dload           16
        //   699: fload           25
        //   701: f2d            
        //   702: dmul           
        //   703: dstore          16
        //   705: dload           18
        //   707: fload           25
        //   709: f2d            
        //   710: dmul           
        //   711: dstore          18
        //   713: dload           20
        //   715: fload           25
        //   717: f2d            
        //   718: dmul           
        //   719: dstore          20
        //   721: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   724: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   727: getfield        net/minecraft/client/entity/EntityPlayerSP.onGround:Z
        //   730: ifne            747
        //   733: dload           18
        //   735: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   738: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   741: getfield        net/minecraft/client/entity/EntityPlayerSP.motionY:D
        //   744: dadd           
        //   745: dstore          18
        //   747: getstatic       me/rebirthclient/api/managers/Managers.COLORS:Lme/rebirthclient/api/managers/impl/ColorManager;
        //   750: invokevirtual   me/rebirthclient/api/managers/impl/ColorManager.getCurrent:()Ljava/awt/Color;
        //   753: invokestatic    me/rebirthclient/api/util/render/RenderUtil.glColor:(Ljava/awt/Color;)V
        //   756: sipush          2848
        //   759: invokestatic    org/lwjgl/opengl/GL11.glEnable:(I)V
        //   762: aload           8
        //   764: instanceof      Lnet/minecraft/item/ItemBow;
        //   767: ifeq            776
        //   770: ldc2_w          0.3
        //   773: goto            779
        //   776: ldc2_w          0.25
        //   779: d2f            
        //   780: fstore          26
        //   782: iconst_0       
        //   783: istore          27
        //   785: aconst_null    
        //   786: astore          28
        //   788: aconst_null    
        //   789: astore          29
        //   791: iconst_3       
        //   792: invokestatic    org/lwjgl/opengl/GL11.glBegin:(I)V
        //   795: iload           27
        //   797: ifne            1139
        //   800: dload           11
        //   802: dconst_0       
        //   803: dcmpl          
        //   804: ifle            1139
        //   807: new             Lnet/minecraft/util/math/Vec3d;
        //   810: dup            
        //   811: dload           9
        //   813: dload           11
        //   815: dload           13
        //   817: invokespecial   net/minecraft/util/math/Vec3d.<init>:(DDD)V
        //   820: astore          30
        //   822: new             Lnet/minecraft/util/math/Vec3d;
        //   825: dup            
        //   826: dload           9
        //   828: dload           16
        //   830: dadd           
        //   831: dload           11
        //   833: dload           18
        //   835: dadd           
        //   836: dload           13
        //   838: dload           20
        //   840: dadd           
        //   841: invokespecial   net/minecraft/util/math/Vec3d.<init>:(DDD)V
        //   844: astore          31
        //   846: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   849: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   852: aload           30
        //   854: aload           31
        //   856: iconst_0       
        //   857: iconst_1       
        //   858: iconst_0       
        //   859: invokevirtual   net/minecraft/client/multiplayer/WorldClient.rayTraceBlocks:(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;ZZZ)Lnet/minecraft/util/math/RayTraceResult;
        //   862: astore          32
        //   864: aload           32
        //   866: ifnull          887
        //   869: aload           32
        //   871: getfield        net/minecraft/util/math/RayTraceResult.typeOfHit:Lnet/minecraft/util/math/RayTraceResult$Type;
        //   874: getstatic       net/minecraft/util/math/RayTraceResult$Type.MISS:Lnet/minecraft/util/math/RayTraceResult$Type;
        //   877: if_acmpeq       887
        //   880: aload           32
        //   882: astore          29
        //   884: iconst_1       
        //   885: istore          27
        //   887: new             Lnet/minecraft/util/math/AxisAlignedBB;
        //   890: dup            
        //   891: dload           9
        //   893: fload           26
        //   895: f2d            
        //   896: dsub           
        //   897: dload           11
        //   899: fload           26
        //   901: f2d            
        //   902: dsub           
        //   903: dload           13
        //   905: fload           26
        //   907: f2d            
        //   908: dsub           
        //   909: dload           9
        //   911: fload           26
        //   913: f2d            
        //   914: dadd           
        //   915: dload           11
        //   917: fload           26
        //   919: f2d            
        //   920: dadd           
        //   921: dload           13
        //   923: fload           26
        //   925: f2d            
        //   926: dadd           
        //   927: invokespecial   net/minecraft/util/math/AxisAlignedBB.<init>:(DDDDDD)V
        //   930: astore          33
        //   932: aload_0        
        //   933: aload           33
        //   935: dload           16
        //   937: dload           18
        //   939: dload           20
        //   941: invokevirtual   net/minecraft/util/math/AxisAlignedBB.offset:(DDD)Lnet/minecraft/util/math/AxisAlignedBB;
        //   944: dconst_1       
        //   945: dconst_1       
        //   946: dconst_1       
        //   947: invokevirtual   net/minecraft/util/math/AxisAlignedBB.expand:(DDD)Lnet/minecraft/util/math/AxisAlignedBB;
        //   950: invokespecial   me/rebirthclient/mod/modules/impl/render/Trajectories.getEntitiesWithinAABB:(Lnet/minecraft/util/math/AxisAlignedBB;)Ljava/util/List;
        //   953: astore          34
        //   955: aload           34
        //   957: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   962: astore          35
        //   964: aload           35
        //   966: invokeinterface java/util/Iterator.hasNext:()Z
        //   971: ifeq            1058
        //   974: aload           35
        //   976: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   981: checkcast       Lnet/minecraft/entity/Entity;
        //   984: astore          36
        //   986: aload           36
        //   988: invokevirtual   net/minecraft/entity/Entity.canBeCollidedWith:()Z
        //   991: ifeq            1057
        //   994: aload           36
        //   996: getstatic       me/rebirthclient/mod/modules/impl/render/Trajectories.mc:Lnet/minecraft/client/Minecraft;
        //   999: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1002: if_acmpeq       1057
        //  1005: ldc_w           0.3
        //  1008: fstore          37
        //  1010: aload           36
        //  1012: invokevirtual   net/minecraft/entity/Entity.getEntityBoundingBox:()Lnet/minecraft/util/math/AxisAlignedBB;
        //  1015: fload           37
        //  1017: f2d            
        //  1018: fload           37
        //  1020: f2d            
        //  1021: fload           37
        //  1023: f2d            
        //  1024: invokevirtual   net/minecraft/util/math/AxisAlignedBB.expand:(DDD)Lnet/minecraft/util/math/AxisAlignedBB;
        //  1027: astore          38
        //  1029: aload           38
        //  1031: aload           30
        //  1033: aload           31
        //  1035: invokevirtual   net/minecraft/util/math/AxisAlignedBB.calculateIntercept:(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/RayTraceResult;
        //  1038: astore          39
        //  1040: aload           39
        //  1042: ifnonnull       1046
        //  1045: return         
        //  1046: iconst_1       
        //  1047: istore          27
        //  1049: aload           36
        //  1051: astore          28
        //  1053: aload           39
        //  1055: astore          29
        //  1057: return         
        //  1058: dload           9
        //  1060: dload           16
        //  1062: dadd           
        //  1063: dstore          9
        //  1065: dload           11
        //  1067: dload           18
        //  1069: dadd           
        //  1070: dstore          11
        //  1072: dload           13
        //  1074: dload           20
        //  1076: dadd           
        //  1077: dstore          13
        //  1079: ldc_w           0.99
        //  1082: fstore          35
        //  1084: dload           16
        //  1086: ldc2_w          0.9900000095367432
        //  1089: dmul           
        //  1090: dstore          16
        //  1092: dload           18
        //  1094: ldc2_w          0.9900000095367432
        //  1097: dmul           
        //  1098: dstore          18
        //  1100: dload           20
        //  1102: ldc2_w          0.9900000095367432
        //  1105: dmul           
        //  1106: dstore          20
        //  1108: dload           18
        //  1110: aload_0        
        //  1111: aload           8
        //  1113: invokespecial   me/rebirthclient/mod/modules/impl/render/Trajectories.getGravity:(Lnet/minecraft/item/Item;)F
        //  1116: f2d            
        //  1117: dsub           
        //  1118: dstore          18
        //  1120: aload_0        
        //  1121: dload           9
        //  1123: dload_2        
        //  1124: dsub           
        //  1125: dload           11
        //  1127: dload           4
        //  1129: dsub           
        //  1130: dload           13
        //  1132: dload           6
        //  1134: dsub           
        //  1135: invokevirtual   me/rebirthclient/mod/modules/impl/render/Trajectories.drawTracer:(DDD)V
        //  1138: return         
        //  1139: invokestatic    org/lwjgl/opengl/GL11.glEnd:()V
        //  1142: aload           29
        //  1144: ifnull          1328
        //  1147: aload           29
        //  1149: getfield        net/minecraft/util/math/RayTraceResult.typeOfHit:Lnet/minecraft/util/math/RayTraceResult$Type;
        //  1152: getstatic       net/minecraft/util/math/RayTraceResult$Type.BLOCK:Lnet/minecraft/util/math/RayTraceResult$Type;
        //  1155: if_acmpne       1328
        //  1158: dload           9
        //  1160: dload_2        
        //  1161: dsub           
        //  1162: dload           11
        //  1164: dload           4
        //  1166: dsub           
        //  1167: dload           13
        //  1169: dload           6
        //  1171: dsub           
        //  1172: invokestatic    net/minecraft/client/renderer/GlStateManager.translate:(DDD)V
        //  1175: aload           29
        //  1177: getfield        net/minecraft/util/math/RayTraceResult.sideHit:Lnet/minecraft/util/EnumFacing;
        //  1180: invokevirtual   net/minecraft/util/EnumFacing.getIndex:()I
        //  1183: istore          30
        //  1185: iload           30
        //  1187: iconst_2       
        //  1188: if_icmpne       1203
        //  1191: ldc_w           90.0
        //  1194: fconst_1       
        //  1195: fconst_0       
        //  1196: fconst_0       
        //  1197: invokestatic    net/minecraft/client/renderer/GlStateManager.rotate:(FFFF)V
        //  1200: goto            1254
        //  1203: iload           30
        //  1205: iconst_3       
        //  1206: if_icmpne       1221
        //  1209: ldc_w           90.0
        //  1212: fconst_1       
        //  1213: fconst_0       
        //  1214: fconst_0       
        //  1215: invokestatic    net/minecraft/client/renderer/GlStateManager.rotate:(FFFF)V
        //  1218: goto            1254
        //  1221: iload           30
        //  1223: iconst_4       
        //  1224: if_icmpne       1239
        //  1227: ldc_w           90.0
        //  1230: fconst_0       
        //  1231: fconst_0       
        //  1232: fconst_1       
        //  1233: invokestatic    net/minecraft/client/renderer/GlStateManager.rotate:(FFFF)V
        //  1236: goto            1254
        //  1239: iload           30
        //  1241: iconst_5       
        //  1242: if_icmpne       1254
        //  1245: ldc_w           90.0
        //  1248: fconst_0       
        //  1249: fconst_0       
        //  1250: fconst_1       
        //  1251: invokestatic    net/minecraft/client/renderer/GlStateManager.rotate:(FFFF)V
        //  1254: new             Lorg/lwjgl/util/glu/Cylinder;
        //  1257: dup            
        //  1258: invokespecial   org/lwjgl/util/glu/Cylinder.<init>:()V
        //  1261: astore          31
        //  1263: ldc_w           -90.0
        //  1266: fconst_1       
        //  1267: fconst_0       
        //  1268: fconst_0       
        //  1269: invokestatic    net/minecraft/client/renderer/GlStateManager.rotate:(FFFF)V
        //  1272: aload           31
        //  1274: ldc_w           100011
        //  1277: invokevirtual   org/lwjgl/util/glu/Cylinder.setDrawStyle:(I)V
        //  1280: aload           31
        //  1282: ldc             0.5
        //  1284: ldc_w           0.2
        //  1287: fconst_0       
        //  1288: iconst_4       
        //  1289: iconst_1       
        //  1290: invokevirtual   org/lwjgl/util/glu/Cylinder.draw:(FFFII)V
        //  1293: aload           31
        //  1295: ldc_w           100012
        //  1298: invokevirtual   org/lwjgl/util/glu/Cylinder.setDrawStyle:(I)V
        //  1301: getstatic       me/rebirthclient/api/managers/Managers.COLORS:Lme/rebirthclient/api/managers/impl/ColorManager;
        //  1304: invokevirtual   me/rebirthclient/api/managers/impl/ColorManager.getCurrent:()Ljava/awt/Color;
        //  1307: bipush          100
        //  1309: invokestatic    me/rebirthclient/api/util/render/ColorUtil.injectAlpha:(Ljava/awt/Color;I)Ljava/awt/Color;
        //  1312: invokestatic    me/rebirthclient/api/util/render/RenderUtil.glColor:(Ljava/awt/Color;)V
        //  1315: aload           31
        //  1317: ldc             0.5
        //  1319: ldc_w           0.2
        //  1322: fconst_0       
        //  1323: iconst_4       
        //  1324: iconst_1       
        //  1325: invokevirtual   org/lwjgl/util/glu/Cylinder.draw:(FFFII)V
        //  1328: sipush          2896
        //  1331: invokestatic    org/lwjgl/opengl/GL11.glEnable:(I)V
        //  1334: sipush          2848
        //  1337: invokestatic    org/lwjgl/opengl/GL11.glDisable:(I)V
        //  1340: sipush          3553
        //  1343: invokestatic    org/lwjgl/opengl/GL11.glEnable:(I)V
        //  1346: sipush          2929
        //  1349: invokestatic    org/lwjgl/opengl/GL11.glEnable:(I)V
        //  1352: sipush          3042
        //  1355: invokestatic    org/lwjgl/opengl/GL11.glDisable:(I)V
        //  1358: sipush          3008
        //  1361: invokestatic    org/lwjgl/opengl/GL11.glEnable:(I)V
        //  1364: iconst_1       
        //  1365: invokestatic    org/lwjgl/opengl/GL11.glDepthMask:(Z)V
        //  1368: sipush          1029
        //  1371: invokestatic    org/lwjgl/opengl/GL11.glCullFace:(I)V
        //  1374: invokestatic    org/lwjgl/opengl/GL11.glPopMatrix:()V
        //  1377: invokestatic    org/lwjgl/opengl/GL11.glPopAttrib:()V
        //  1380: aload           28
        //  1382: ifnull          1410
        //  1385: aload           28
        //  1387: getstatic       me/rebirthclient/api/managers/Managers.COLORS:Lme/rebirthclient/api/managers/impl/ColorManager;
        //  1390: invokevirtual   me/rebirthclient/api/managers/impl/ColorManager.getCurrent:()Ljava/awt/Color;
        //  1393: iconst_0       
        //  1394: new             Ljava/awt/Color;
        //  1397: dup            
        //  1398: iconst_m1      
        //  1399: invokespecial   java/awt/Color.<init>:(I)V
        //  1402: fconst_1       
        //  1403: iconst_0       
        //  1404: iconst_1       
        //  1405: bipush          100
        //  1407: invokestatic    me/rebirthclient/api/util/render/RenderUtil.drawEntityBoxESP:(Lnet/minecraft/entity/Entity;Ljava/awt/Color;ZLjava/awt/Color;FZZI)V
        //  1410: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0281 (coming from #0264).
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
    
    private float getDistance(final Item item) {
        return (item instanceof ItemBow) ? 1.0f : 0.4f;
    }
}
