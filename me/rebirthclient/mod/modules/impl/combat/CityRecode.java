//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import net.minecraft.entity.player.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import java.awt.*;
import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import java.util.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.world.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;

public class CityRecode extends Module
{
    private final Setting mineTrap;
    private final Setting prefer;
    private final Setting outlineAlpha;
    private static EntityPlayer target;
    private BlockPos breakPos;
    private final Setting targetRange;
    private final Setting debug;
    private final Setting keyMode;
    private final Setting box;
    private final Setting autoDisable;
    private final Timer renderTimer;
    private final Setting boxAlpha;
    private final Setting keyBind;
    private FadeUtils shrinkTimer;
    private final Setting mineBurrow;
    private final Setting outline;
    private final List godBlocks;
    private final Setting color;
    private final Setting shrinkTime;
    private final Setting onlyBurrow;
    private final Setting breakRange;
    private final Setting render;
    
    public CityRecode() {
        super("CityRecode", "", Category.COMBAT);
        this.mineBurrow = this.add(new Setting("MineBurrow", true).setParent());
        this.onlyBurrow = this.add(new Setting("OnlyBurrow", false, this::lambda$new$0));
        this.mineTrap = this.add(new Setting("MineTrap", true).setParent());
        this.prefer = this.add(new Setting("Prefer", true, this::lambda$new$1));
        this.render = this.add(new Setting("Render", true).setParent());
        this.outline = this.add(new Setting("Outline", true, this::lambda$new$2).setParent());
        this.outlineAlpha = this.add(new Setting("OutlineAlpha", 150, 0, 255, this::lambda$new$3));
        this.box = this.add(new Setting("Box", true, this::lambda$new$4).setParent());
        this.boxAlpha = this.add(new Setting("BoxAlpha", 70, 0, 255, this::lambda$new$5));
        this.targetRange = this.add(new Setting("TargetRange", 5.0f, 1.0f, 8.0f));
        this.breakRange = this.add(new Setting("BreakRange", 4.5, 1.0, 8.0));
        this.color = this.add(new Setting("Color", new Color(255, 255, 255), this::lambda$new$6).hideAlpha());
        this.shrinkTime = this.add(new Setting("ShrinkTime", 1200, 0, 3000, this::lambda$new$7));
        this.shrinkTimer = new FadeUtils((long)(int)this.shrinkTime.getValue());
        this.keyMode = this.add(new Setting("KeyMode", true));
        this.keyBind = this.add(new Setting("Enable", new Bind(-1), this::lambda$new$8));
        this.autoDisable = this.add(new Setting("autoDisable", false, this::lambda$new$9));
        this.debug = this.add(new Setting("Debug", false));
        this.renderTimer = new Timer();
        this.godBlocks = Arrays.asList(Blocks.AIR, (Block)Blocks.FLOWING_LAVA, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_WATER, (Block)Blocks.WATER, Blocks.BEDROCK);
    }
    
    private boolean lambda$new$5(final Integer n) {
        return this.render.isOpen() && this.box.isOpen();
    }
    
    private boolean lambda$new$4(final Boolean b) {
        return this.render.isOpen();
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.mineTrap.isOpen();
    }
    
    @Override
    public void onUpdate() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        me/rebirthclient/mod/modules/impl/combat/CityRecode.keyMode:Lme/rebirthclient/mod/modules/settings/Setting;
        //     4: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //     7: checkcast       Ljava/lang/Boolean;
        //    10: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    13: ifeq            33
        //    16: aload_0        
        //    17: getfield        me/rebirthclient/mod/modules/impl/combat/CityRecode.keyBind:Lme/rebirthclient/mod/modules/settings/Setting;
        //    20: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    23: checkcast       Lme/rebirthclient/mod/modules/settings/Bind;
        //    26: invokevirtual   me/rebirthclient/mod/modules/settings/Bind.isDown:()Z
        //    29: ifne            33
        //    32: return         
        //    33: aload_0        
        //    34: getfield        me/rebirthclient/mod/modules/impl/combat/CityRecode.targetRange:Lme/rebirthclient/mod/modules/settings/Setting;
        //    37: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    40: checkcast       Ljava/lang/Float;
        //    43: invokevirtual   java/lang/Float.floatValue:()F
        //    46: f2d            
        //    47: ldc2_w          10.0
        //    50: invokestatic    me/rebirthclient/api/util/CombatUtil.getTarget:(DD)Lnet/minecraft/entity/player/EntityPlayer;
        //    53: putstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //    56: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //    59: ifnonnull       63
        //    62: return         
        //    63: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //    66: invokestatic    me/rebirthclient/api/util/EntityUtil.getEntityPos:(Lnet/minecraft/entity/Entity;)Lnet/minecraft/util/math/BlockPos;
        //    69: astore_1       
        //    70: aload_0        
        //    71: aload_1        
        //    72: ifeq            99
        //    75: aload_0        
        //    76: getfield        me/rebirthclient/mod/modules/impl/combat/CityRecode.mineBurrow:Lme/rebirthclient/mod/modules/settings/Setting;
        //    79: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    82: checkcast       Ljava/lang/Boolean;
        //    85: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    88: ifeq            99
        //    91: aload_0        
        //    92: aload_1        
        //    93: invokespecial   me/rebirthclient/mod/modules/impl/combat/CityRecode.mineBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //    96: goto            868
        //    99: aload_0        
        //   100: new             Lnet/minecraft/util/math/BlockPos;
        //   103: dup            
        //   104: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   107: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //   110: ldc2_w          0.1
        //   113: dadd           
        //   114: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   117: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //   120: ldc2_w          0.5
        //   123: dadd           
        //   124: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   127: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //   130: ldc2_w          0.1
        //   133: dadd           
        //   134: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   137: ifeq            252
        //   140: aload_0        
        //   141: getfield        me/rebirthclient/mod/modules/impl/combat/CityRecode.mineBurrow:Lme/rebirthclient/mod/modules/settings/Setting;
        //   144: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   147: checkcast       Ljava/lang/Boolean;
        //   150: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   153: ifeq            252
        //   156: getstatic       me/rebirthclient/mod/modules/impl/combat/PacketMine.godBlocks:Ljava/util/List;
        //   159: aload_0        
        //   160: new             Lnet/minecraft/util/math/BlockPos;
        //   163: dup            
        //   164: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   167: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //   170: ldc2_w          0.1
        //   173: dadd           
        //   174: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   177: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //   180: ldc2_w          0.5
        //   183: dadd           
        //   184: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   187: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //   190: ldc2_w          0.1
        //   193: dadd           
        //   194: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   197: invokespecial   me/rebirthclient/mod/modules/impl/combat/CityRecode.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //   200: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   205: ifne            252
        //   208: aload_0        
        //   209: new             Lnet/minecraft/util/math/BlockPos;
        //   212: dup            
        //   213: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   216: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //   219: ldc2_w          0.1
        //   222: dadd           
        //   223: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   226: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //   229: ldc2_w          0.5
        //   232: dadd           
        //   233: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   236: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //   239: ldc2_w          0.1
        //   242: dadd           
        //   243: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   246: invokespecial   me/rebirthclient/mod/modules/impl/combat/CityRecode.mineBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //   249: goto            868
        //   252: aload_0        
        //   253: new             Lnet/minecraft/util/math/BlockPos;
        //   256: dup            
        //   257: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   260: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //   263: ldc2_w          0.1
        //   266: dadd           
        //   267: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   270: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //   273: ldc2_w          0.5
        //   276: dadd           
        //   277: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   280: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //   283: ldc2_w          0.1
        //   286: dsub           
        //   287: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   290: ifeq            405
        //   293: aload_0        
        //   294: getfield        me/rebirthclient/mod/modules/impl/combat/CityRecode.mineBurrow:Lme/rebirthclient/mod/modules/settings/Setting;
        //   297: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   300: checkcast       Ljava/lang/Boolean;
        //   303: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   306: ifeq            405
        //   309: getstatic       me/rebirthclient/mod/modules/impl/combat/PacketMine.godBlocks:Ljava/util/List;
        //   312: aload_0        
        //   313: new             Lnet/minecraft/util/math/BlockPos;
        //   316: dup            
        //   317: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   320: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //   323: ldc2_w          0.1
        //   326: dadd           
        //   327: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   330: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //   333: ldc2_w          0.5
        //   336: dadd           
        //   337: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   340: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //   343: ldc2_w          0.1
        //   346: dsub           
        //   347: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   350: invokespecial   me/rebirthclient/mod/modules/impl/combat/CityRecode.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //   353: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   358: ifne            405
        //   361: aload_0        
        //   362: new             Lnet/minecraft/util/math/BlockPos;
        //   365: dup            
        //   366: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   369: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //   372: ldc2_w          0.1
        //   375: dadd           
        //   376: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   379: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //   382: ldc2_w          0.5
        //   385: dadd           
        //   386: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   389: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //   392: ldc2_w          0.1
        //   395: dsub           
        //   396: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   399: invokespecial   me/rebirthclient/mod/modules/impl/combat/CityRecode.mineBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //   402: goto            868
        //   405: aload_0        
        //   406: new             Lnet/minecraft/util/math/BlockPos;
        //   409: dup            
        //   410: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   413: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //   416: ldc2_w          0.1
        //   419: dsub           
        //   420: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   423: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //   426: ldc2_w          0.5
        //   429: dadd           
        //   430: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   433: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //   436: ldc2_w          0.1
        //   439: dadd           
        //   440: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   443: ifeq            558
        //   446: aload_0        
        //   447: getfield        me/rebirthclient/mod/modules/impl/combat/CityRecode.mineBurrow:Lme/rebirthclient/mod/modules/settings/Setting;
        //   450: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   453: checkcast       Ljava/lang/Boolean;
        //   456: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   459: ifeq            558
        //   462: getstatic       me/rebirthclient/mod/modules/impl/combat/PacketMine.godBlocks:Ljava/util/List;
        //   465: aload_0        
        //   466: new             Lnet/minecraft/util/math/BlockPos;
        //   469: dup            
        //   470: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   473: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //   476: ldc2_w          0.1
        //   479: dsub           
        //   480: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   483: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //   486: ldc2_w          0.5
        //   489: dadd           
        //   490: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   493: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //   496: ldc2_w          0.1
        //   499: dadd           
        //   500: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   503: invokespecial   me/rebirthclient/mod/modules/impl/combat/CityRecode.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //   506: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   511: ifne            558
        //   514: aload_0        
        //   515: new             Lnet/minecraft/util/math/BlockPos;
        //   518: dup            
        //   519: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   522: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //   525: ldc2_w          0.1
        //   528: dsub           
        //   529: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   532: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //   535: ldc2_w          0.5
        //   538: dadd           
        //   539: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   542: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //   545: ldc2_w          0.1
        //   548: dadd           
        //   549: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   552: invokespecial   me/rebirthclient/mod/modules/impl/combat/CityRecode.mineBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //   555: goto            868
        //   558: aload_0        
        //   559: new             Lnet/minecraft/util/math/BlockPos;
        //   562: dup            
        //   563: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   566: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //   569: ldc2_w          0.1
        //   572: dsub           
        //   573: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   576: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //   579: ldc2_w          0.5
        //   582: dadd           
        //   583: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   586: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //   589: ldc2_w          0.1
        //   592: dsub           
        //   593: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   596: ifeq            711
        //   599: aload_0        
        //   600: getfield        me/rebirthclient/mod/modules/impl/combat/CityRecode.mineBurrow:Lme/rebirthclient/mod/modules/settings/Setting;
        //   603: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   606: checkcast       Ljava/lang/Boolean;
        //   609: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   612: ifeq            711
        //   615: getstatic       me/rebirthclient/mod/modules/impl/combat/PacketMine.godBlocks:Ljava/util/List;
        //   618: aload_0        
        //   619: new             Lnet/minecraft/util/math/BlockPos;
        //   622: dup            
        //   623: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   626: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //   629: ldc2_w          0.1
        //   632: dsub           
        //   633: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   636: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //   639: ldc2_w          0.5
        //   642: dadd           
        //   643: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   646: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //   649: ldc2_w          0.1
        //   652: dsub           
        //   653: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   656: invokespecial   me/rebirthclient/mod/modules/impl/combat/CityRecode.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //   659: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   664: ifne            711
        //   667: aload_0        
        //   668: new             Lnet/minecraft/util/math/BlockPos;
        //   671: dup            
        //   672: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   675: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //   678: ldc2_w          0.1
        //   681: dsub           
        //   682: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   685: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //   688: ldc2_w          0.5
        //   691: dadd           
        //   692: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   695: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //   698: ldc2_w          0.1
        //   701: dsub           
        //   702: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   705: invokespecial   me/rebirthclient/mod/modules/impl/combat/CityRecode.mineBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //   708: goto            868
        //   711: aload_0        
        //   712: getfield        me/rebirthclient/mod/modules/impl/combat/CityRecode.mineBurrow:Lme/rebirthclient/mod/modules/settings/Setting;
        //   715: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   718: checkcast       Ljava/lang/Boolean;
        //   721: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   724: ifeq            743
        //   727: aload_0        
        //   728: getfield        me/rebirthclient/mod/modules/impl/combat/CityRecode.onlyBurrow:Lme/rebirthclient/mod/modules/settings/Setting;
        //   731: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   734: checkcast       Ljava/lang/Boolean;
        //   737: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   740: ifne            868
        //   743: aload_0        
        //   744: getstatic       me/rebirthclient/mod/modules/impl/combat/CityRecode.target:Lnet/minecraft/entity/player/EntityPlayer;
        //   747: aload_1        
        //   748: ifeq            868
        //   751: aload_0        
        //   752: getfield        me/rebirthclient/mod/modules/impl/combat/CityRecode.debug:Lme/rebirthclient/mod/modules/settings/Setting;
        //   755: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   758: checkcast       Ljava/lang/Boolean;
        //   761: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   764: ifeq            774
        //   767: aload_0        
        //   768: ldc_w           "do mine surround"
        //   771: invokevirtual   me/rebirthclient/mod/modules/impl/combat/CityRecode.sendMessage:(Ljava/lang/String;)V
        //   774: aload_0        
        //   775: aload_1        
        //   776: ifeq            868
        //   779: aload_0        
        //   780: aload_1        
        //   781: iconst_0       
        //   782: iconst_0       
        //   783: iconst_1       
        //   784: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   787: ifeq            868
        //   790: aload_0        
        //   791: aload_1        
        //   792: iconst_0       
        //   793: iconst_0       
        //   794: iconst_m1      
        //   795: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   798: ifeq            868
        //   801: aload_0        
        //   802: aload_1        
        //   803: iconst_1       
        //   804: iconst_0       
        //   805: iconst_0       
        //   806: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   809: ifeq            868
        //   812: aload_0        
        //   813: aload_1        
        //   814: iconst_m1      
        //   815: iconst_0       
        //   816: iconst_0       
        //   817: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   820: ifeq            868
        //   823: aload_0        
        //   824: aload_1        
        //   825: iconst_1       
        //   826: iconst_0       
        //   827: iconst_1       
        //   828: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   831: ifeq            868
        //   834: aload_0        
        //   835: aload_1        
        //   836: iconst_m1      
        //   837: iconst_0       
        //   838: iconst_m1      
        //   839: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   842: ifeq            868
        //   845: aload_0        
        //   846: aload_1        
        //   847: iconst_m1      
        //   848: iconst_0       
        //   849: iconst_1       
        //   850: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   853: ifeq            868
        //   856: aload_0        
        //   857: aload_1        
        //   858: iconst_1       
        //   859: iconst_0       
        //   860: iconst_m1      
        //   861: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //   864: invokespecial   me/rebirthclient/mod/modules/impl/combat/CityRecode.doMine:(Lnet/minecraft/util/math/BlockPos;)Z
        //   867: pop            
        //   868: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0868 (coming from #0776).
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
    
    private void mineBlock(final BlockPos breakPos) {
        if (this.getBlock(breakPos) == Blocks.REDSTONE_WIRE) {
            if (this.debug.getValue()) {
                this.sendMessage("redstone!");
            }
            return;
        }
        if (this.debug.getValue()) {
            this.sendMessage("mine block");
        }
        if (PacketMine.godBlocks.contains(this.getBlock(breakPos))) {
            return;
        }
        if (!breakPos.equals((Object)PacketMine.breakPos) && this.renderTimer.passedMs(200L)) {
            this.breakPos = breakPos;
            this.renderTimer.reset();
            this.shrinkTimer = new FadeUtils((long)(int)this.shrinkTime.getValue());
        }
        if ((boolean)this.autoDisable.getValue() && !(boolean)this.keyMode.getValue()) {
            this.disable();
        }
        CombatUtil.mineBlock(breakPos);
    }
    
    private boolean lambda$new$8(final Bind bind) {
        return (boolean)this.keyMode.getValue();
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (this.breakPos == null || this.renderTimer.passedMs((long)(int)this.shrinkTime.getValue()) || !(boolean)this.render.getValue()) {
            this.shrinkTimer = new FadeUtils((long)(int)this.shrinkTime.getValue());
            return;
        }
        final AxisAlignedBB grow = CityRecode.mc.world.getBlockState(this.breakPos).getSelectedBoundingBox((World)CityRecode.mc.world, this.breakPos).grow(this.shrinkTimer.easeInQuad() / 2.0 - 1.0);
        if (this.outline.getValue()) {
            RenderUtil.drawBBBox(grow, (Color)this.color.getValue(), (int)this.outlineAlpha.getValue());
        }
        if (this.box.getValue()) {
            RenderUtil.drawBBFill(grow, (Color)this.color.getValue(), (int)this.boxAlpha.getValue());
        }
    }
    
    private Block getBlock(final BlockPos blockPos) {
        return CityRecode.mc.world.getBlockState(blockPos).getBlock();
    }
    
    private boolean lambda$new$3(final Integer n) {
        return this.render.isOpen() && this.outline.isOpen();
    }
    
    private boolean lambda$new$2(final Boolean b) {
        return this.render.isOpen();
    }
    
    private boolean lambda$new$6(final Color color) {
        return this.render.isOpen();
    }
    
    @Override
    public String getInfo() {
        if (CityRecode.target != null) {
            return CityRecode.target.getName();
        }
        return null;
    }
    
    private boolean lambda$new$9(final Boolean b) {
        return !(boolean)this.keyMode.getValue();
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.mineBurrow.isOpen();
    }
    
    private boolean lambda$new$7(final Integer n) {
        return this.render.isOpen();
    }
    
    private boolean MineSurround(final BlockPos blockPos, final EnumFacing enumFacing) {
        if (this == blockPos.offset(enumFacing) && !this.godBlocks.contains(this.getBlock(blockPos.offset(enumFacing)))) {
            this.mineBlock(blockPos.offset(enumFacing));
            return true;
        }
        return false;
    }
}
