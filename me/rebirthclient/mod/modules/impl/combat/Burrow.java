//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraft.util.*;
import me.rebirthclient.api.util.*;

public class Burrow extends Module
{
    private final Setting placeDisable;
    private final Setting offsetY;
    private final Timer timedOut;
    private final Setting wait;
    private final Setting aboveHead;
    public final Setting safeHealth;
    private final Setting offsetX;
    public static Burrow INSTANCE;
    private final Setting airCheck;
    private final Setting offsetZ;
    private final Setting smartOffset;
    private boolean shouldWait;
    private final Setting debug;
    private final Setting switchBypass;
    private final Setting rotate;
    private final Setting onlyGround;
    private final Setting timeOut;
    private final Setting multiPlace;
    int progress;
    private final Setting delay;
    private final Timer timer;
    private final Setting breakCrystal;
    private final Setting center;
    
    @Override
    public void onUpdate() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: iconst_0       
        //     2: putfield        me/rebirthclient/mod/modules/impl/combat/Burrow.progress:I
        //     5: aload_0        
        //     6: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.switchBypass:Lme/rebirthclient/mod/modules/settings/Setting;
        //     9: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    12: checkcast       Ljava/lang/Boolean;
        //    15: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    18: ifne            60
        //    21: getstatic       net/minecraft/init/Blocks.OBSIDIAN:Lnet/minecraft/block/Block;
        //    24: invokestatic    net/minecraft/item/Item.getItemFromBlock:(Lnet/minecraft/block/Block;)Lnet/minecraft/item/Item;
        //    27: invokestatic    me/rebirthclient/api/util/InventoryUtil.getItemHotbar:(Lnet/minecraft/item/Item;)I
        //    30: iconst_m1      
        //    31: if_icmpeq       47
        //    34: getstatic       net/minecraft/init/Blocks.OBSIDIAN:Lnet/minecraft/block/Block;
        //    37: invokestatic    net/minecraft/item/Item.getItemFromBlock:(Lnet/minecraft/block/Block;)Lnet/minecraft/item/Item;
        //    40: invokestatic    me/rebirthclient/api/util/InventoryUtil.getItemHotbar:(Lnet/minecraft/item/Item;)I
        //    43: istore_1       
        //    44: goto            102
        //    47: getstatic       net/minecraft/init/Blocks.ENDER_CHEST:Lnet/minecraft/block/Block;
        //    50: invokestatic    net/minecraft/item/Item.getItemFromBlock:(Lnet/minecraft/block/Block;)Lnet/minecraft/item/Item;
        //    53: invokestatic    me/rebirthclient/api/util/InventoryUtil.getItemHotbar:(Lnet/minecraft/item/Item;)I
        //    56: istore_1       
        //    57: goto            102
        //    60: getstatic       net/minecraft/init/Blocks.OBSIDIAN:Lnet/minecraft/block/Block;
        //    63: invokestatic    net/minecraft/item/Item.getItemFromBlock:(Lnet/minecraft/block/Block;)Lnet/minecraft/item/Item;
        //    66: iconst_0       
        //    67: iconst_1       
        //    68: invokestatic    me/rebirthclient/api/util/InventoryUtil.findItemInventorySlot:(Lnet/minecraft/item/Item;ZZ)I
        //    71: iconst_m1      
        //    72: if_icmpeq       90
        //    75: getstatic       net/minecraft/init/Blocks.OBSIDIAN:Lnet/minecraft/block/Block;
        //    78: invokestatic    net/minecraft/item/Item.getItemFromBlock:(Lnet/minecraft/block/Block;)Lnet/minecraft/item/Item;
        //    81: iconst_0       
        //    82: iconst_1       
        //    83: invokestatic    me/rebirthclient/api/util/InventoryUtil.findItemInventorySlot:(Lnet/minecraft/item/Item;ZZ)I
        //    86: istore_1       
        //    87: goto            102
        //    90: getstatic       net/minecraft/init/Blocks.ENDER_CHEST:Lnet/minecraft/block/Block;
        //    93: invokestatic    net/minecraft/item/Item.getItemFromBlock:(Lnet/minecraft/block/Block;)Lnet/minecraft/item/Item;
        //    96: iconst_0       
        //    97: iconst_1       
        //    98: invokestatic    me/rebirthclient/api/util/InventoryUtil.findItemInventorySlot:(Lnet/minecraft/item/Item;ZZ)I
        //   101: istore_1       
        //   102: iload_1        
        //   103: iconst_m1      
        //   104: if_icmpne       137
        //   107: aload_0        
        //   108: new             Ljava/lang/StringBuilder;
        //   111: dup            
        //   112: invokespecial   java/lang/StringBuilder.<init>:()V
        //   115: getstatic       com/mojang/realmsclient/gui/ChatFormatting.RED:Lcom/mojang/realmsclient/gui/ChatFormatting;
        //   118: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   121: ldc             "Obsidian/Ender Chest ?"
        //   123: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   126: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   129: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.sendMessage:(Ljava/lang/String;)V
        //   132: aload_0        
        //   133: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.disable:()V
        //   136: return         
        //   137: aload_0        
        //   138: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.timedOut:Lme/rebirthclient/api/util/Timer;
        //   141: aload_0        
        //   142: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.timeOut:Lme/rebirthclient/mod/modules/settings/Setting;
        //   145: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   148: checkcast       Ljava/lang/Integer;
        //   151: invokevirtual   java/lang/Integer.intValue:()I
        //   154: i2l            
        //   155: invokevirtual   me/rebirthclient/api/util/Timer.passedMs:(J)Z
        //   158: ifeq            166
        //   161: aload_0        
        //   162: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.disable:()V
        //   165: return         
        //   166: invokestatic    me/rebirthclient/api/util/EntityUtil.getPlayerPos:()Lnet/minecraft/util/math/BlockPos;
        //   169: astore_2       
        //   170: aload_0        
        //   171: new             Lnet/minecraft/util/math/BlockPos;
        //   174: dup            
        //   175: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   178: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   181: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //   184: ldc2_w          0.3
        //   187: dadd           
        //   188: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   191: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   194: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   197: ldc2_w          0.5
        //   200: dadd           
        //   201: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   204: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   207: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   210: ldc2_w          0.3
        //   213: dadd           
        //   214: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   217: ifeq            404
        //   220: aload_0        
        //   221: new             Lnet/minecraft/util/math/BlockPos;
        //   224: dup            
        //   225: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   228: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   231: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //   234: ldc2_w          0.3
        //   237: dsub           
        //   238: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   241: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   244: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   247: ldc2_w          0.5
        //   250: dadd           
        //   251: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   254: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   257: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   260: ldc2_w          0.3
        //   263: dadd           
        //   264: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   267: ifeq            404
        //   270: aload_0        
        //   271: new             Lnet/minecraft/util/math/BlockPos;
        //   274: dup            
        //   275: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   278: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   281: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //   284: ldc2_w          0.3
        //   287: dadd           
        //   288: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   291: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   294: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   297: ldc2_w          0.5
        //   300: dadd           
        //   301: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   304: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   307: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   310: ldc2_w          0.3
        //   313: dsub           
        //   314: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   317: ifeq            404
        //   320: aload_0        
        //   321: new             Lnet/minecraft/util/math/BlockPos;
        //   324: dup            
        //   325: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   328: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   331: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //   334: ldc2_w          0.3
        //   337: dsub           
        //   338: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   341: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   344: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   347: ldc2_w          0.5
        //   350: dadd           
        //   351: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   354: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   357: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   360: ldc2_w          0.3
        //   363: dsub           
        //   364: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   367: ifeq            404
        //   370: aload_0        
        //   371: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.debug:Lme/rebirthclient/mod/modules/settings/Setting;
        //   374: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   377: checkcast       Ljava/lang/Boolean;
        //   380: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   383: ifeq            392
        //   386: aload_0        
        //   387: ldc             "cant place"
        //   389: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.sendMessage:(Ljava/lang/String;)V
        //   392: aload_0        
        //   393: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.shouldWait:Z
        //   396: ifne            403
        //   399: aload_0        
        //   400: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.disable:()V
        //   403: return         
        //   404: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   407: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   410: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.isInLava:()Z
        //   413: ifne            440
        //   416: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   419: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   422: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.isInWater:()Z
        //   425: ifne            440
        //   428: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   431: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   434: getfield        net/minecraft/client/entity/EntityPlayerSP.isInWeb:Z
        //   437: ifeq            463
        //   440: aload_0        
        //   441: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.debug:Lme/rebirthclient/mod/modules/settings/Setting;
        //   444: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   447: checkcast       Ljava/lang/Boolean;
        //   450: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   453: ifeq            462
        //   456: aload_0        
        //   457: ldc             "player stuck"
        //   459: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.sendMessage:(Ljava/lang/String;)V
        //   462: return         
        //   463: aload_0        
        //   464: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.onlyGround:Lme/rebirthclient/mod/modules/settings/Setting;
        //   467: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   470: checkcast       Ljava/lang/Boolean;
        //   473: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   476: ifeq            565
        //   479: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   482: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   485: getfield        net/minecraft/client/entity/EntityPlayerSP.onGround:Z
        //   488: ifne            514
        //   491: aload_0        
        //   492: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.debug:Lme/rebirthclient/mod/modules/settings/Setting;
        //   495: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   498: checkcast       Ljava/lang/Boolean;
        //   501: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   504: ifeq            513
        //   507: aload_0        
        //   508: ldc             "player not on ground"
        //   510: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.sendMessage:(Ljava/lang/String;)V
        //   513: return         
        //   514: aload_0        
        //   515: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.airCheck:Lme/rebirthclient/mod/modules/settings/Setting;
        //   518: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   521: checkcast       Ljava/lang/Boolean;
        //   524: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   527: ifeq            565
        //   530: invokestatic    me/rebirthclient/api/util/EntityUtil.getPlayerPos:()Lnet/minecraft/util/math/BlockPos;
        //   533: invokevirtual   net/minecraft/util/math/BlockPos.down:()Lnet/minecraft/util/math/BlockPos;
        //   536: invokestatic    me/rebirthclient/mod/modules/impl/combat/Burrow.isAir:(Lnet/minecraft/util/math/BlockPos;)Z
        //   539: ifeq            565
        //   542: aload_0        
        //   543: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.debug:Lme/rebirthclient/mod/modules/settings/Setting;
        //   546: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   549: checkcast       Ljava/lang/Boolean;
        //   552: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   555: ifeq            564
        //   558: aload_0        
        //   559: ldc             "player in air"
        //   561: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.sendMessage:(Ljava/lang/String;)V
        //   564: return         
        //   565: aload_0        
        //   566: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.timer:Lme/rebirthclient/api/util/Timer;
        //   569: aload_0        
        //   570: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.delay:Lme/rebirthclient/mod/modules/settings/Setting;
        //   573: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   576: checkcast       Ljava/lang/Integer;
        //   579: invokevirtual   java/lang/Integer.intValue:()I
        //   582: i2l            
        //   583: invokevirtual   me/rebirthclient/api/util/Timer.passedMs:(J)Z
        //   586: ifne            590
        //   589: return         
        //   590: aload_0        
        //   591: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.breakCrystal:Lme/rebirthclient/mod/modules/settings/Setting;
        //   594: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   597: checkcast       Ljava/lang/Boolean;
        //   600: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   603: ifeq            924
        //   606: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   609: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   612: invokestatic    me/rebirthclient/api/util/EntityUtil.getHealth:(Lnet/minecraft/entity/Entity;)F
        //   615: aload_0        
        //   616: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.safeHealth:Lme/rebirthclient/mod/modules/settings/Setting;
        //   619: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   622: checkcast       Ljava/lang/Float;
        //   625: invokevirtual   java/lang/Float.floatValue:()F
        //   628: fcmpl          
        //   629: iflt            924
        //   632: aload_0        
        //   633: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.debug:Lme/rebirthclient/mod/modules/settings/Setting;
        //   636: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   639: checkcast       Ljava/lang/Boolean;
        //   642: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   645: ifeq            654
        //   648: aload_0        
        //   649: ldc             "try break crystal"
        //   651: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.sendMessage:(Ljava/lang/String;)V
        //   654: aload_2        
        //   655: aload_0        
        //   656: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.rotate:Lme/rebirthclient/mod/modules/settings/Setting;
        //   659: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   662: checkcast       Ljava/lang/Boolean;
        //   665: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   668: iconst_0       
        //   669: invokestatic    me/rebirthclient/api/util/CombatUtil.attackCrystal:(Lnet/minecraft/util/math/BlockPos;ZZ)V
        //   672: new             Lnet/minecraft/util/math/BlockPos;
        //   675: dup            
        //   676: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   679: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   682: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //   685: ldc2_w          0.3
        //   688: dadd           
        //   689: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   692: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   695: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   698: ldc2_w          0.5
        //   701: dadd           
        //   702: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   705: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   708: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   711: ldc2_w          0.3
        //   714: dadd           
        //   715: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   718: aload_0        
        //   719: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.rotate:Lme/rebirthclient/mod/modules/settings/Setting;
        //   722: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   725: checkcast       Ljava/lang/Boolean;
        //   728: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   731: iconst_0       
        //   732: invokestatic    me/rebirthclient/api/util/CombatUtil.attackCrystal:(Lnet/minecraft/util/math/BlockPos;ZZ)V
        //   735: new             Lnet/minecraft/util/math/BlockPos;
        //   738: dup            
        //   739: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   742: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   745: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //   748: ldc2_w          0.3
        //   751: dadd           
        //   752: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   755: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   758: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   761: ldc2_w          0.5
        //   764: dadd           
        //   765: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   768: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   771: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   774: ldc2_w          0.3
        //   777: dsub           
        //   778: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   781: aload_0        
        //   782: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.rotate:Lme/rebirthclient/mod/modules/settings/Setting;
        //   785: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   788: checkcast       Ljava/lang/Boolean;
        //   791: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   794: iconst_0       
        //   795: invokestatic    me/rebirthclient/api/util/CombatUtil.attackCrystal:(Lnet/minecraft/util/math/BlockPos;ZZ)V
        //   798: new             Lnet/minecraft/util/math/BlockPos;
        //   801: dup            
        //   802: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   805: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   808: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //   811: ldc2_w          0.3
        //   814: dsub           
        //   815: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   818: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   821: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   824: ldc2_w          0.5
        //   827: dadd           
        //   828: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   831: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   834: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   837: ldc2_w          0.3
        //   840: dadd           
        //   841: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   844: aload_0        
        //   845: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.rotate:Lme/rebirthclient/mod/modules/settings/Setting;
        //   848: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   851: checkcast       Ljava/lang/Boolean;
        //   854: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   857: iconst_0       
        //   858: invokestatic    me/rebirthclient/api/util/CombatUtil.attackCrystal:(Lnet/minecraft/util/math/BlockPos;ZZ)V
        //   861: new             Lnet/minecraft/util/math/BlockPos;
        //   864: dup            
        //   865: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   868: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   871: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //   874: ldc2_w          0.3
        //   877: dsub           
        //   878: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   881: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   884: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   887: ldc2_w          0.5
        //   890: dadd           
        //   891: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //   894: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   897: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   900: ldc2_w          0.3
        //   903: dsub           
        //   904: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   907: aload_0        
        //   908: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.rotate:Lme/rebirthclient/mod/modules/settings/Setting;
        //   911: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   914: checkcast       Ljava/lang/Boolean;
        //   917: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   920: iconst_0       
        //   921: invokestatic    me/rebirthclient/api/util/CombatUtil.attackCrystal:(Lnet/minecraft/util/math/BlockPos;ZZ)V
        //   924: aload_0        
        //   925: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.timer:Lme/rebirthclient/api/util/Timer;
        //   928: invokevirtual   me/rebirthclient/api/util/Timer.reset:()Lme/rebirthclient/api/util/Timer;
        //   931: pop            
        //   932: aload_0        
        //   933: iconst_0       
        //   934: putfield        me/rebirthclient/mod/modules/impl/combat/Burrow.shouldWait:Z
        //   937: invokestatic    me/rebirthclient/api/util/EntityUtil.getPlayerPos:()Lnet/minecraft/util/math/BlockPos;
        //   940: iconst_2       
        //   941: invokevirtual   net/minecraft/util/math/BlockPos.up:(I)Lnet/minecraft/util/math/BlockPos;
        //   944: astore_3       
        //   945: aload_3        
        //   946: ifeq            1029
        //   949: aload_3        
        //   950: iconst_1       
        //   951: iconst_0       
        //   952: iconst_0       
        //   953: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   956: ifeq            1029
        //   959: aload_3        
        //   960: iconst_m1      
        //   961: iconst_0       
        //   962: iconst_0       
        //   963: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   966: ifeq            1029
        //   969: aload_3        
        //   970: iconst_0       
        //   971: iconst_0       
        //   972: iconst_1       
        //   973: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   976: ifeq            1029
        //   979: aload_3        
        //   980: iconst_0       
        //   981: iconst_0       
        //   982: iconst_m1      
        //   983: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   986: ifeq            1029
        //   989: aload_3        
        //   990: iconst_1       
        //   991: iconst_0       
        //   992: iconst_m1      
        //   993: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   996: ifeq            1029
        //   999: aload_3        
        //  1000: iconst_m1      
        //  1001: iconst_0       
        //  1002: iconst_m1      
        //  1003: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //  1006: ifeq            1029
        //  1009: aload_3        
        //  1010: iconst_1       
        //  1011: iconst_0       
        //  1012: iconst_1       
        //  1013: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //  1016: ifeq            1029
        //  1019: aload_3        
        //  1020: iconst_m1      
        //  1021: iconst_0       
        //  1022: iconst_1       
        //  1023: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //  1026: ifeq            1741
        //  1029: aload_0        
        //  1030: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.aboveHead:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1033: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1036: checkcast       Ljava/lang/Boolean;
        //  1039: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1042: ifne            1057
        //  1045: aload_0        
        //  1046: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.shouldWait:Z
        //  1049: ifne            1056
        //  1052: aload_0        
        //  1053: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.disable:()V
        //  1056: return         
        //  1057: iconst_0       
        //  1058: istore          4
        //  1060: aload_2        
        //  1061: astore          5
        //  1063: aload           5
        //  1065: ifeq            1172
        //  1068: aload           5
        //  1070: invokestatic    me/rebirthclient/mod/modules/impl/combat/Burrow.canReplace:(Lnet/minecraft/util/math/BlockPos;)Z
        //  1073: ifne            1172
        //  1076: aload_0        
        //  1077: aload           5
        //  1079: invokespecial   me/rebirthclient/mod/modules/impl/combat/Burrow.gotoPos:(Lnet/minecraft/util/math/BlockPos;)V
        //  1082: aload_0        
        //  1083: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.debug:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1086: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1089: checkcast       Ljava/lang/Boolean;
        //  1092: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1095: ifeq            1738
        //  1098: aload_0        
        //  1099: new             Ljava/lang/StringBuilder;
        //  1102: dup            
        //  1103: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1106: ldc             "moved to center "
        //  1108: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1111: aload           5
        //  1113: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //  1116: i2d            
        //  1117: ldc2_w          0.5
        //  1120: dadd           
        //  1121: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1124: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1127: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //  1130: dsub           
        //  1131: invokevirtual   java/lang/StringBuilder.append:(D)Ljava/lang/StringBuilder;
        //  1134: ldc_w           " "
        //  1137: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1140: aload           5
        //  1142: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //  1145: i2d            
        //  1146: ldc2_w          0.5
        //  1149: dadd           
        //  1150: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1153: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1156: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //  1159: dsub           
        //  1160: invokevirtual   java/lang/StringBuilder.append:(D)Ljava/lang/StringBuilder;
        //  1163: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1166: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.sendMessage:(Ljava/lang/String;)V
        //  1169: goto            1738
        //  1172: getstatic       net/minecraft/util/EnumFacing.VALUES:[Lnet/minecraft/util/EnumFacing;
        //  1175: astore          6
        //  1177: aload           6
        //  1179: arraylength    
        //  1180: istore          7
        //  1182: iconst_0       
        //  1183: istore          8
        //  1185: iload           8
        //  1187: iload           7
        //  1189: if_icmpge       1343
        //  1192: aload           6
        //  1194: iload           8
        //  1196: aaload         
        //  1197: astore          9
        //  1199: aload           9
        //  1201: getstatic       net/minecraft/util/EnumFacing.UP:Lnet/minecraft/util/EnumFacing;
        //  1204: if_acmpeq       1339
        //  1207: aload           9
        //  1209: getstatic       net/minecraft/util/EnumFacing.DOWN:Lnet/minecraft/util/EnumFacing;
        //  1212: if_acmpne       1218
        //  1215: goto            1339
        //  1218: aload_2        
        //  1219: aload           9
        //  1221: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //  1224: astore          5
        //  1226: aload           5
        //  1228: ifeq            1339
        //  1231: aload           5
        //  1233: invokestatic    me/rebirthclient/mod/modules/impl/combat/Burrow.canReplace:(Lnet/minecraft/util/math/BlockPos;)Z
        //  1236: ifne            1339
        //  1239: aload_0        
        //  1240: aload           5
        //  1242: invokespecial   me/rebirthclient/mod/modules/impl/combat/Burrow.gotoPos:(Lnet/minecraft/util/math/BlockPos;)V
        //  1245: iconst_1       
        //  1246: istore          4
        //  1248: aload_0        
        //  1249: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.debug:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1252: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1255: checkcast       Ljava/lang/Boolean;
        //  1258: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1261: ifeq            1343
        //  1264: aload_0        
        //  1265: new             Ljava/lang/StringBuilder;
        //  1268: dup            
        //  1269: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1272: ldc_w           "moved to block "
        //  1275: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1278: aload           5
        //  1280: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //  1283: i2d            
        //  1284: ldc2_w          0.5
        //  1287: dadd           
        //  1288: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1291: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1294: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //  1297: dsub           
        //  1298: invokevirtual   java/lang/StringBuilder.append:(D)Ljava/lang/StringBuilder;
        //  1301: ldc_w           " "
        //  1304: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1307: aload           5
        //  1309: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //  1312: i2d            
        //  1313: ldc2_w          0.5
        //  1316: dadd           
        //  1317: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1320: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1323: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //  1326: dsub           
        //  1327: invokevirtual   java/lang/StringBuilder.append:(D)Ljava/lang/StringBuilder;
        //  1330: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1333: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.sendMessage:(Ljava/lang/String;)V
        //  1336: goto            1343
        //  1339: iinc            8, 1
        //  1342: return         
        //  1343: iload           4
        //  1345: ifne            1738
        //  1348: getstatic       net/minecraft/util/EnumFacing.VALUES:[Lnet/minecraft/util/EnumFacing;
        //  1351: astore          6
        //  1353: aload           6
        //  1355: arraylength    
        //  1356: istore          7
        //  1358: iconst_0       
        //  1359: istore          8
        //  1361: iload           8
        //  1363: iload           7
        //  1365: if_icmpge       1511
        //  1368: aload           6
        //  1370: iload           8
        //  1372: aaload         
        //  1373: astore          9
        //  1375: aload           9
        //  1377: getstatic       net/minecraft/util/EnumFacing.UP:Lnet/minecraft/util/EnumFacing;
        //  1380: if_acmpeq       1507
        //  1383: aload           9
        //  1385: getstatic       net/minecraft/util/EnumFacing.DOWN:Lnet/minecraft/util/EnumFacing;
        //  1388: if_acmpne       1394
        //  1391: goto            1507
        //  1394: aload_2        
        //  1395: aload           9
        //  1397: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //  1400: astore          5
        //  1402: aload           5
        //  1404: ifeq            1507
        //  1407: aload_0        
        //  1408: aload           5
        //  1410: invokespecial   me/rebirthclient/mod/modules/impl/combat/Burrow.gotoPos:(Lnet/minecraft/util/math/BlockPos;)V
        //  1413: iconst_1       
        //  1414: istore          4
        //  1416: aload_0        
        //  1417: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.debug:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1420: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1423: checkcast       Ljava/lang/Boolean;
        //  1426: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1429: ifeq            1511
        //  1432: aload_0        
        //  1433: new             Ljava/lang/StringBuilder;
        //  1436: dup            
        //  1437: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1440: ldc_w           "moved to entity "
        //  1443: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1446: aload           5
        //  1448: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //  1451: i2d            
        //  1452: ldc2_w          0.5
        //  1455: dadd           
        //  1456: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1459: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1462: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //  1465: dsub           
        //  1466: invokevirtual   java/lang/StringBuilder.append:(D)Ljava/lang/StringBuilder;
        //  1469: ldc_w           " "
        //  1472: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1475: aload           5
        //  1477: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //  1480: i2d            
        //  1481: ldc2_w          0.5
        //  1484: dadd           
        //  1485: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1488: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1491: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //  1494: dsub           
        //  1495: invokevirtual   java/lang/StringBuilder.append:(D)Ljava/lang/StringBuilder;
        //  1498: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1501: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.sendMessage:(Ljava/lang/String;)V
        //  1504: goto            1511
        //  1507: iinc            8, 1
        //  1510: return         
        //  1511: iload           4
        //  1513: ifne            1738
        //  1516: aload_0        
        //  1517: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.center:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1520: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1523: checkcast       Ljava/lang/Boolean;
        //  1526: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1529: ifne            1544
        //  1532: aload_0        
        //  1533: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.shouldWait:Z
        //  1536: ifne            1543
        //  1539: aload_0        
        //  1540: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.disable:()V
        //  1543: return         
        //  1544: getstatic       net/minecraft/util/EnumFacing.VALUES:[Lnet/minecraft/util/EnumFacing;
        //  1547: astore          6
        //  1549: aload           6
        //  1551: arraylength    
        //  1552: istore          7
        //  1554: iconst_0       
        //  1555: istore          8
        //  1557: iload           8
        //  1559: iload           7
        //  1561: if_icmpge       1721
        //  1564: aload           6
        //  1566: iload           8
        //  1568: aaload         
        //  1569: astore          9
        //  1571: aload           9
        //  1573: getstatic       net/minecraft/util/EnumFacing.UP:Lnet/minecraft/util/EnumFacing;
        //  1576: if_acmpeq       1717
        //  1579: aload           9
        //  1581: getstatic       net/minecraft/util/EnumFacing.DOWN:Lnet/minecraft/util/EnumFacing;
        //  1584: if_acmpne       1590
        //  1587: goto            1717
        //  1590: aload_2        
        //  1591: aload           9
        //  1593: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //  1596: astore          5
        //  1598: aload           5
        //  1600: invokestatic    me/rebirthclient/mod/modules/impl/combat/Burrow.canReplace:(Lnet/minecraft/util/math/BlockPos;)Z
        //  1603: ifeq            1717
        //  1606: aload           5
        //  1608: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //  1611: invokestatic    me/rebirthclient/mod/modules/impl/combat/Burrow.canReplace:(Lnet/minecraft/util/math/BlockPos;)Z
        //  1614: ifeq            1717
        //  1617: aload_0        
        //  1618: aload           5
        //  1620: invokespecial   me/rebirthclient/mod/modules/impl/combat/Burrow.gotoPos:(Lnet/minecraft/util/math/BlockPos;)V
        //  1623: aload_0        
        //  1624: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.debug:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1627: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1630: checkcast       Ljava/lang/Boolean;
        //  1633: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1636: ifeq            1711
        //  1639: aload_0        
        //  1640: new             Ljava/lang/StringBuilder;
        //  1643: dup            
        //  1644: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1647: ldc_w           "moved to air "
        //  1650: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1653: aload           5
        //  1655: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //  1658: i2d            
        //  1659: ldc2_w          0.5
        //  1662: dadd           
        //  1663: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1666: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1669: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //  1672: dsub           
        //  1673: invokevirtual   java/lang/StringBuilder.append:(D)Ljava/lang/StringBuilder;
        //  1676: ldc_w           " "
        //  1679: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1682: aload           5
        //  1684: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //  1687: i2d            
        //  1688: ldc2_w          0.5
        //  1691: dadd           
        //  1692: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1695: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1698: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //  1701: dsub           
        //  1702: invokevirtual   java/lang/StringBuilder.append:(D)Ljava/lang/StringBuilder;
        //  1705: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1708: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.sendMessage:(Ljava/lang/String;)V
        //  1711: iconst_1       
        //  1712: istore          4
        //  1714: goto            1721
        //  1717: iinc            8, 1
        //  1720: return         
        //  1721: iload           4
        //  1723: ifne            1738
        //  1726: aload_0        
        //  1727: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.shouldWait:Z
        //  1730: ifne            1737
        //  1733: aload_0        
        //  1734: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.disable:()V
        //  1737: return         
        //  1738: goto            1968
        //  1741: aload_0        
        //  1742: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.debug:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1745: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1748: checkcast       Ljava/lang/Boolean;
        //  1751: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1754: ifeq            1764
        //  1757: aload_0        
        //  1758: ldc_w           "fake jump"
        //  1761: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.sendMessage:(Ljava/lang/String;)V
        //  1764: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1767: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1770: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //  1773: new             Lnet/minecraft/network/play/client/CPacketPlayer$Position;
        //  1776: dup            
        //  1777: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1780: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1783: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //  1786: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1789: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1792: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //  1795: ldc2_w          0.4199999868869781
        //  1798: dadd           
        //  1799: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1802: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1805: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //  1808: iconst_0       
        //  1809: invokespecial   net/minecraft/network/play/client/CPacketPlayer$Position.<init>:(DDDZ)V
        //  1812: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //  1815: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1818: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1821: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //  1824: new             Lnet/minecraft/network/play/client/CPacketPlayer$Position;
        //  1827: dup            
        //  1828: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1831: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1834: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //  1837: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1840: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1843: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //  1846: ldc2_w          0.7531999805212017
        //  1849: dadd           
        //  1850: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1853: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1856: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //  1859: iconst_0       
        //  1860: invokespecial   net/minecraft/network/play/client/CPacketPlayer$Position.<init>:(DDDZ)V
        //  1863: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //  1866: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1869: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1872: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //  1875: new             Lnet/minecraft/network/play/client/CPacketPlayer$Position;
        //  1878: dup            
        //  1879: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1882: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1885: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //  1888: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1891: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1894: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //  1897: ldc2_w          0.9999957640154541
        //  1900: dadd           
        //  1901: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1904: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1907: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //  1910: iconst_0       
        //  1911: invokespecial   net/minecraft/network/play/client/CPacketPlayer$Position.<init>:(DDDZ)V
        //  1914: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //  1917: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1920: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1923: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //  1926: new             Lnet/minecraft/network/play/client/CPacketPlayer$Position;
        //  1929: dup            
        //  1930: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1933: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1936: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //  1939: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1942: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1945: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //  1948: ldc2_w          1.1661092609382138
        //  1951: dadd           
        //  1952: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1955: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1958: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //  1961: iconst_0       
        //  1962: invokespecial   net/minecraft/network/play/client/CPacketPlayer$Position.<init>:(DDDZ)V
        //  1965: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //  1968: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  1971: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1974: getfield        net/minecraft/client/entity/EntityPlayerSP.inventory:Lnet/minecraft/entity/player/InventoryPlayer;
        //  1977: getfield        net/minecraft/entity/player/InventoryPlayer.currentItem:I
        //  1980: istore          4
        //  1982: aload_0        
        //  1983: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.switchBypass:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1986: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1989: checkcast       Ljava/lang/Boolean;
        //  1992: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1995: ifne            2005
        //  1998: iload_1        
        //  1999: invokestatic    me/rebirthclient/api/util/InventoryUtil.doSwap:(I)V
        //  2002: goto            2028
        //  2005: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2008: getfield        net/minecraft/client/Minecraft.playerController:Lnet/minecraft/client/multiplayer/PlayerControllerMP;
        //  2011: iconst_0       
        //  2012: iload_1        
        //  2013: iload           4
        //  2015: getstatic       net/minecraft/inventory/ClickType.SWAP:Lnet/minecraft/inventory/ClickType;
        //  2018: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2021: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2024: invokevirtual   net/minecraft/client/multiplayer/PlayerControllerMP.windowClick:(IIILnet/minecraft/inventory/ClickType;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;
        //  2027: pop            
        //  2028: aload_0        
        //  2029: aload_2        
        //  2030: invokespecial   me/rebirthclient/mod/modules/impl/combat/Burrow.placeBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //  2033: aload_0        
        //  2034: new             Lnet/minecraft/util/math/BlockPos;
        //  2037: dup            
        //  2038: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2041: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2044: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //  2047: ldc2_w          0.3
        //  2050: dadd           
        //  2051: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2054: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2057: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //  2060: ldc2_w          0.5
        //  2063: dadd           
        //  2064: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2067: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2070: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //  2073: ldc2_w          0.3
        //  2076: dadd           
        //  2077: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //  2080: invokespecial   me/rebirthclient/mod/modules/impl/combat/Burrow.placeBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //  2083: aload_0        
        //  2084: new             Lnet/minecraft/util/math/BlockPos;
        //  2087: dup            
        //  2088: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2091: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2094: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //  2097: ldc2_w          0.3
        //  2100: dadd           
        //  2101: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2104: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2107: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //  2110: ldc2_w          0.5
        //  2113: dadd           
        //  2114: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2117: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2120: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //  2123: ldc2_w          0.3
        //  2126: dsub           
        //  2127: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //  2130: invokespecial   me/rebirthclient/mod/modules/impl/combat/Burrow.placeBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //  2133: aload_0        
        //  2134: new             Lnet/minecraft/util/math/BlockPos;
        //  2137: dup            
        //  2138: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2141: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2144: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //  2147: ldc2_w          0.3
        //  2150: dsub           
        //  2151: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2154: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2157: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //  2160: ldc2_w          0.5
        //  2163: dadd           
        //  2164: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2167: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2170: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //  2173: ldc2_w          0.3
        //  2176: dadd           
        //  2177: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //  2180: invokespecial   me/rebirthclient/mod/modules/impl/combat/Burrow.placeBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //  2183: aload_0        
        //  2184: new             Lnet/minecraft/util/math/BlockPos;
        //  2187: dup            
        //  2188: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2191: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2194: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //  2197: ldc2_w          0.3
        //  2200: dsub           
        //  2201: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2204: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2207: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //  2210: ldc2_w          0.5
        //  2213: dadd           
        //  2214: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2217: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2220: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //  2223: ldc2_w          0.3
        //  2226: dsub           
        //  2227: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //  2230: invokespecial   me/rebirthclient/mod/modules/impl/combat/Burrow.placeBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //  2233: aload_0        
        //  2234: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.switchBypass:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2237: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2240: checkcast       Ljava/lang/Boolean;
        //  2243: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  2246: ifne            2257
        //  2249: iload           4
        //  2251: invokestatic    me/rebirthclient/api/util/InventoryUtil.doSwap:(I)V
        //  2254: goto            2280
        //  2257: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2260: getfield        net/minecraft/client/Minecraft.playerController:Lnet/minecraft/client/multiplayer/PlayerControllerMP;
        //  2263: iconst_0       
        //  2264: iload_1        
        //  2265: iload           4
        //  2267: getstatic       net/minecraft/inventory/ClickType.SWAP:Lnet/minecraft/inventory/ClickType;
        //  2270: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2273: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2276: invokevirtual   net/minecraft/client/multiplayer/PlayerControllerMP.windowClick:(IIILnet/minecraft/inventory/ClickType;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;
        //  2279: pop            
        //  2280: aload_0        
        //  2281: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.smartOffset:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2284: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2287: checkcast       Ljava/lang/Boolean;
        //  2290: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  2293: ifeq            2818
        //  2296: dconst_0       
        //  2297: dstore          5
        //  2299: aconst_null    
        //  2300: astore          7
        //  2302: ldc_w           6.0
        //  2305: invokestatic    me/rebirthclient/api/util/BlockUtil.getBox:(F)Lnet/minecraft/util/NonNullList;
        //  2308: invokevirtual   net/minecraft/util/NonNullList.iterator:()Ljava/util/Iterator;
        //  2311: astore          8
        //  2313: aload           8
        //  2315: invokeinterface java/util/Iterator.hasNext:()Z
        //  2320: ifeq            2484
        //  2323: aload           8
        //  2325: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  2330: checkcast       Lnet/minecraft/util/math/BlockPos;
        //  2333: astore          9
        //  2335: aload_0        
        //  2336: aload           9
        //  2338: ifeq            2313
        //  2341: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2344: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2347: aload           9
        //  2349: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //  2352: i2d            
        //  2353: ldc2_w          0.5
        //  2356: dadd           
        //  2357: aload           9
        //  2359: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //  2362: i2d            
        //  2363: ldc2_w          0.5
        //  2366: dadd           
        //  2367: aload           9
        //  2369: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //  2372: i2d            
        //  2373: ldc2_w          0.5
        //  2376: dadd           
        //  2377: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(DDD)D
        //  2380: ldc2_w          3.0
        //  2383: dcmpg          
        //  2384: ifgt            2388
        //  2387: return         
        //  2388: aload           7
        //  2390: ifnull          2438
        //  2393: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2396: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2399: aload           9
        //  2401: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //  2404: i2d            
        //  2405: ldc2_w          0.5
        //  2408: dadd           
        //  2409: aload           9
        //  2411: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //  2414: i2d            
        //  2415: ldc2_w          0.5
        //  2418: dadd           
        //  2419: aload           9
        //  2421: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //  2424: i2d            
        //  2425: ldc2_w          0.5
        //  2428: dadd           
        //  2429: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(DDD)D
        //  2432: dload           5
        //  2434: dcmpg          
        //  2435: ifge            2483
        //  2438: aload           9
        //  2440: astore          7
        //  2442: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2445: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2448: aload           9
        //  2450: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //  2453: i2d            
        //  2454: ldc2_w          0.5
        //  2457: dadd           
        //  2458: aload           9
        //  2460: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //  2463: i2d            
        //  2464: ldc2_w          0.5
        //  2467: dadd           
        //  2468: aload           9
        //  2470: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //  2473: i2d            
        //  2474: ldc2_w          0.5
        //  2477: dadd           
        //  2478: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(DDD)D
        //  2481: dstore          5
        //  2483: return         
        //  2484: aload           7
        //  2486: ifnull          2538
        //  2489: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2492: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2495: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //  2498: new             Lnet/minecraft/network/play/client/CPacketPlayer$Position;
        //  2501: dup            
        //  2502: aload           7
        //  2504: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //  2507: i2d            
        //  2508: ldc2_w          0.5
        //  2511: dadd           
        //  2512: aload           7
        //  2514: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //  2517: i2d            
        //  2518: aload           7
        //  2520: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //  2523: i2d            
        //  2524: ldc2_w          0.5
        //  2527: dadd           
        //  2528: iconst_0       
        //  2529: invokespecial   net/minecraft/network/play/client/CPacketPlayer$Position.<init>:(DDDZ)V
        //  2532: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //  2535: goto            2815
        //  2538: ldc_w           6.0
        //  2541: invokestatic    me/rebirthclient/api/util/BlockUtil.getBox:(F)Lnet/minecraft/util/NonNullList;
        //  2544: invokevirtual   net/minecraft/util/NonNullList.iterator:()Ljava/util/Iterator;
        //  2547: astore          8
        //  2549: aload           8
        //  2551: invokeinterface java/util/Iterator.hasNext:()Z
        //  2556: ifeq            2720
        //  2559: aload           8
        //  2561: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  2566: checkcast       Lnet/minecraft/util/math/BlockPos;
        //  2569: astore          9
        //  2571: aload_0        
        //  2572: aload           9
        //  2574: ifeq            2549
        //  2577: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2580: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2583: aload           9
        //  2585: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //  2588: i2d            
        //  2589: ldc2_w          0.5
        //  2592: dadd           
        //  2593: aload           9
        //  2595: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //  2598: i2d            
        //  2599: ldc2_w          0.5
        //  2602: dadd           
        //  2603: aload           9
        //  2605: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //  2608: i2d            
        //  2609: ldc2_w          0.5
        //  2612: dadd           
        //  2613: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(DDD)D
        //  2616: ldc2_w          2.0
        //  2619: dcmpg          
        //  2620: ifgt            2624
        //  2623: return         
        //  2624: aload           7
        //  2626: ifnull          2674
        //  2629: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2632: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2635: aload           9
        //  2637: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //  2640: i2d            
        //  2641: ldc2_w          0.5
        //  2644: dadd           
        //  2645: aload           9
        //  2647: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //  2650: i2d            
        //  2651: ldc2_w          0.5
        //  2654: dadd           
        //  2655: aload           9
        //  2657: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //  2660: i2d            
        //  2661: ldc2_w          0.5
        //  2664: dadd           
        //  2665: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(DDD)D
        //  2668: dload           5
        //  2670: dcmpg          
        //  2671: ifge            2719
        //  2674: aload           9
        //  2676: astore          7
        //  2678: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2681: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2684: aload           9
        //  2686: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //  2689: i2d            
        //  2690: ldc2_w          0.5
        //  2693: dadd           
        //  2694: aload           9
        //  2696: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //  2699: i2d            
        //  2700: ldc2_w          0.5
        //  2703: dadd           
        //  2704: aload           9
        //  2706: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //  2709: i2d            
        //  2710: ldc2_w          0.5
        //  2713: dadd           
        //  2714: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(DDD)D
        //  2717: dstore          5
        //  2719: return         
        //  2720: aload           7
        //  2722: ifnull          2774
        //  2725: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2728: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2731: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //  2734: new             Lnet/minecraft/network/play/client/CPacketPlayer$Position;
        //  2737: dup            
        //  2738: aload           7
        //  2740: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //  2743: i2d            
        //  2744: ldc2_w          0.5
        //  2747: dadd           
        //  2748: aload           7
        //  2750: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //  2753: i2d            
        //  2754: aload           7
        //  2756: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //  2759: i2d            
        //  2760: ldc2_w          0.5
        //  2763: dadd           
        //  2764: iconst_0       
        //  2765: invokespecial   net/minecraft/network/play/client/CPacketPlayer$Position.<init>:(DDDZ)V
        //  2768: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //  2771: goto            2815
        //  2774: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2777: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2780: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //  2783: new             Lnet/minecraft/network/play/client/CPacketPlayer$Position;
        //  2786: dup            
        //  2787: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2790: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2793: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //  2796: ldc2_w          -7.0
        //  2799: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2802: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2805: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //  2808: iconst_0       
        //  2809: invokespecial   net/minecraft/network/play/client/CPacketPlayer$Position.<init>:(DDDZ)V
        //  2812: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //  2815: goto            2907
        //  2818: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2821: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2824: getfield        net/minecraft/client/entity/EntityPlayerSP.connection:Lnet/minecraft/client/network/NetHandlerPlayClient;
        //  2827: new             Lnet/minecraft/network/play/client/CPacketPlayer$Position;
        //  2830: dup            
        //  2831: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2834: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2837: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //  2840: aload_0        
        //  2841: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.offsetX:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2844: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2847: checkcast       Ljava/lang/Double;
        //  2850: invokevirtual   java/lang/Double.doubleValue:()D
        //  2853: dadd           
        //  2854: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2857: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2860: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //  2863: aload_0        
        //  2864: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.offsetY:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2867: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2870: checkcast       Ljava/lang/Double;
        //  2873: invokevirtual   java/lang/Double.doubleValue:()D
        //  2876: dadd           
        //  2877: getstatic       me/rebirthclient/mod/modules/impl/combat/Burrow.mc:Lnet/minecraft/client/Minecraft;
        //  2880: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  2883: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //  2886: aload_0        
        //  2887: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.offsetZ:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2890: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2893: checkcast       Ljava/lang/Double;
        //  2896: invokevirtual   java/lang/Double.doubleValue:()D
        //  2899: dadd           
        //  2900: iconst_0       
        //  2901: invokespecial   net/minecraft/network/play/client/CPacketPlayer$Position.<init>:(DDDZ)V
        //  2904: invokevirtual   net/minecraft/client/network/NetHandlerPlayClient.sendPacket:(Lnet/minecraft/network/Packet;)V
        //  2907: aload_0        
        //  2908: getfield        me/rebirthclient/mod/modules/impl/combat/Burrow.placeDisable:Lme/rebirthclient/mod/modules/settings/Setting;
        //  2911: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  2914: checkcast       Ljava/lang/Boolean;
        //  2917: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  2920: ifeq            2927
        //  2923: aload_0        
        //  2924: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Burrow.disable:()V
        //  2927: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #2549 (coming from #2574).
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
    
    private static boolean isAir(final BlockPos blockPos) {
        return Burrow.mc.world.isAirBlock(blockPos);
    }
    
    private boolean lambda$new$3(final Double n) {
        return !(boolean)this.smartOffset.getValue();
    }
    
    private boolean lambda$new$2(final Float n) {
        return this.breakCrystal.isOpen();
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.onlyGround.isOpen();
    }
    
    private boolean lambda$new$4(final Double n) {
        return !(boolean)this.smartOffset.getValue();
    }
    
    private boolean lambda$new$5(final Double n) {
        return !(boolean)this.smartOffset.getValue();
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.aboveHead.isOpen();
    }
    
    private void gotoPos(final BlockPos blockPos) {
        if (Math.abs(blockPos.getX() + 0.5 - Burrow.mc.player.posX) < Math.abs(blockPos.getZ() + 0.5 - Burrow.mc.player.posZ)) {
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 0.2, Burrow.mc.player.posZ + (blockPos.getZ() + 0.5 - Burrow.mc.player.posZ), true));
        }
        else {
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX + (blockPos.getX() + 0.5 - Burrow.mc.player.posX), Burrow.mc.player.posY + 0.2, Burrow.mc.player.posZ, true));
        }
    }
    
    @Override
    public void onEnable() {
        this.timedOut.reset();
        this.shouldWait = (boolean)this.wait.getValue();
    }
    
    public Burrow() {
        super("Burrow", "unknown", Category.COMBAT);
        this.placeDisable = this.add(new Setting("PlaceDisable", false));
        this.wait = this.add(new Setting("Wait", true));
        this.switchBypass = this.add(new Setting("SwitchBypass", true));
        this.rotate = this.add(new Setting("Rotate", true));
        this.onlyGround = this.add(new Setting("OnlyGround", true).setParent());
        this.airCheck = this.add(new Setting("AirCheck", true, this::lambda$new$0));
        this.aboveHead = this.add(new Setting("AboveHead", true).setParent());
        this.center = this.add(new Setting("Center", false, this::lambda$new$1));
        this.breakCrystal = this.add(new Setting("BreakCrystal", true).setParent());
        this.safeHealth = this.add(new Setting("SafeHealth", 16.0f, 0.0f, 36.0f, this::lambda$new$2));
        this.multiPlace = this.add(new Setting("MultiPlace", 1, 1, 4));
        this.timeOut = this.add(new Setting("TimeOut", 500, 0, 2000));
        this.delay = this.add(new Setting("delay", 300, 0, 1000));
        this.smartOffset = this.add(new Setting("SmartOffset", true));
        this.offsetX = this.add(new Setting("OffsetX", -7.0, -14.0, 14.0, this::lambda$new$3));
        this.offsetY = this.add(new Setting("OffsetY", -7.0, -14.0, 14.0, this::lambda$new$4));
        this.offsetZ = this.add(new Setting("OffsetZ", -7.0, -14.0, 14.0, this::lambda$new$5));
        this.debug = this.add(new Setting("Debug", false));
        this.progress = 0;
        this.timer = new Timer();
        this.timedOut = new Timer();
        this.shouldWait = false;
        Burrow.INSTANCE = this;
    }
    
    public static boolean canReplace(final BlockPos blockPos) {
        return Burrow.mc.world.getBlockState(blockPos).getMaterial().isReplaceable();
    }
    
    @Override
    public void onDisable() {
        this.timer.reset();
        this.shouldWait = false;
    }
    
    private void placeBlock(final BlockPos blockPos) {
        if (this.progress >= (int)this.multiPlace.getValue()) {
            return;
        }
        if (blockPos == 0) {
            BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), true, (boolean)this.breakCrystal.getValue(), false);
            ++this.progress;
        }
    }
}
