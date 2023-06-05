//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import me.rebirthclient.mod.modules.impl.movement.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraft.entity.*;
import net.minecraft.block.*;
import me.rebirthclient.api.util.*;
import net.minecraft.util.*;

public class Surround extends Module
{
    private final Setting moveDisable;
    public final Setting safeHealth;
    private final Setting breakCrystal;
    private final Setting jumpDisable;
    private final Setting packet;
    private final Setting multiPlace;
    private final Setting isMoving;
    private final Setting center;
    double startZ;
    private final Setting onlyGround;
    private final Setting delay;
    BlockPos startPos;
    private final Setting inMoving;
    final Timer timer;
    public static Surround INSTANCE;
    int progress;
    double startX;
    private final Setting rotate;
    private final Setting extend;
    private final Setting strictDisable;
    public final Setting enableInHole;
    double startY;
    
    @Override
    public void onTick() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.timer:Lme/rebirthclient/api/util/Timer;
        //     4: aload_0        
        //     5: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.delay:Lme/rebirthclient/mod/modules/settings/Setting;
        //     8: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    11: checkcast       Ljava/lang/Integer;
        //    14: invokevirtual   java/lang/Integer.intValue:()I
        //    17: i2l            
        //    18: invokevirtual   me/rebirthclient/api/util/Timer.passedMs:(J)Z
        //    21: ifne            25
        //    24: return         
        //    25: aload_0        
        //    26: iconst_0       
        //    27: putfield        me/rebirthclient/mod/modules/impl/combat/Surround.progress:I
        //    30: invokestatic    me/rebirthclient/api/util/EntityUtil.getPlayerPos:()Lnet/minecraft/util/math/BlockPos;
        //    33: astore_1       
        //    34: aload_0        
        //    35: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.startPos:Lnet/minecraft/util/math/BlockPos;
        //    38: ifnull          270
        //    41: invokestatic    me/rebirthclient/api/util/EntityUtil.getPlayerPos:()Lnet/minecraft/util/math/BlockPos;
        //    44: aload_0        
        //    45: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.startPos:Lnet/minecraft/util/math/BlockPos;
        //    48: invokevirtual   net/minecraft/util/math/BlockPos.equals:(Ljava/lang/Object;)Z
        //    51: ifne            108
        //    54: aload_0        
        //    55: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.moveDisable:Lme/rebirthclient/mod/modules/settings/Setting;
        //    58: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    61: checkcast       Ljava/lang/Boolean;
        //    64: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    67: ifeq            108
        //    70: aload_0        
        //    71: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.strictDisable:Lme/rebirthclient/mod/modules/settings/Setting;
        //    74: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    77: checkcast       Ljava/lang/Boolean;
        //    80: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    83: ifeq            108
        //    86: aload_0        
        //    87: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.isMoving:Lme/rebirthclient/mod/modules/settings/Setting;
        //    90: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    93: checkcast       Ljava/lang/Boolean;
        //    96: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    99: ifeq            270
        //   102: invokestatic    me/rebirthclient/api/util/MovementUtil.isMoving:()Z
        //   105: ifne            270
        //   108: getstatic       me/rebirthclient/mod/modules/impl/combat/Surround.mc:Lnet/minecraft/client/Minecraft;
        //   111: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   114: aload_0        
        //   115: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.startX:D
        //   118: aload_0        
        //   119: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.startY:D
        //   122: aload_0        
        //   123: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.startZ:D
        //   126: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(DDD)D
        //   129: ldc2_w          1.3
        //   132: dcmpl          
        //   133: ifle            190
        //   136: aload_0        
        //   137: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.moveDisable:Lme/rebirthclient/mod/modules/settings/Setting;
        //   140: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   143: checkcast       Ljava/lang/Boolean;
        //   146: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   149: ifeq            190
        //   152: aload_0        
        //   153: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.strictDisable:Lme/rebirthclient/mod/modules/settings/Setting;
        //   156: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   159: checkcast       Ljava/lang/Boolean;
        //   162: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   165: ifne            190
        //   168: aload_0        
        //   169: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.isMoving:Lme/rebirthclient/mod/modules/settings/Setting;
        //   172: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   175: checkcast       Ljava/lang/Boolean;
        //   178: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   181: ifeq            270
        //   184: invokestatic    me/rebirthclient/api/util/MovementUtil.isMoving:()Z
        //   187: ifne            270
        //   190: aload_0        
        //   191: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.jumpDisable:Lme/rebirthclient/mod/modules/settings/Setting;
        //   194: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   197: checkcast       Ljava/lang/Boolean;
        //   200: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   203: ifeq            275
        //   206: aload_0        
        //   207: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.startY:D
        //   210: getstatic       me/rebirthclient/mod/modules/impl/combat/Surround.mc:Lnet/minecraft/client/Minecraft;
        //   213: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   216: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   219: dsub           
        //   220: ldc2_w          0.5
        //   223: dcmpl          
        //   224: ifgt            248
        //   227: aload_0        
        //   228: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.startY:D
        //   231: getstatic       me/rebirthclient/mod/modules/impl/combat/Surround.mc:Lnet/minecraft/client/Minecraft;
        //   234: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   237: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   240: dsub           
        //   241: ldc2_w          -0.5
        //   244: dcmpg          
        //   245: ifge            275
        //   248: aload_0        
        //   249: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.inMoving:Lme/rebirthclient/mod/modules/settings/Setting;
        //   252: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   255: checkcast       Ljava/lang/Boolean;
        //   258: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   261: ifeq            270
        //   264: invokestatic    me/rebirthclient/api/util/MovementUtil.isMoving:()Z
        //   267: ifeq            275
        //   270: aload_0        
        //   271: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Surround.disable:()V
        //   274: return         
        //   275: getstatic       net/minecraft/init/Blocks.OBSIDIAN:Lnet/minecraft/block/Block;
        //   278: invokestatic    me/rebirthclient/api/util/InventoryUtil.findHotbarBlock:(Lnet/minecraft/block/Block;)I
        //   281: iconst_m1      
        //   282: if_icmpne       315
        //   285: aload_0        
        //   286: new             Ljava/lang/StringBuilder;
        //   289: dup            
        //   290: invokespecial   java/lang/StringBuilder.<init>:()V
        //   293: getstatic       com/mojang/realmsclient/gui/ChatFormatting.RED:Lcom/mojang/realmsclient/gui/ChatFormatting;
        //   296: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   299: ldc             "Obsidian?"
        //   301: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   304: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   307: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Surround.sendMessage:(Ljava/lang/String;)V
        //   310: aload_0        
        //   311: invokevirtual   me/rebirthclient/mod/modules/impl/combat/Surround.disable:()V
        //   314: return         
        //   315: aload_0        
        //   316: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.onlyGround:Lme/rebirthclient/mod/modules/settings/Setting;
        //   319: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   322: checkcast       Ljava/lang/Boolean;
        //   325: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   328: ifeq            344
        //   331: getstatic       me/rebirthclient/mod/modules/impl/combat/Surround.mc:Lnet/minecraft/client/Minecraft;
        //   334: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   337: getfield        net/minecraft/client/entity/EntityPlayerSP.onGround:Z
        //   340: ifne            344
        //   343: return         
        //   344: getstatic       net/minecraft/util/EnumFacing.VALUES:[Lnet/minecraft/util/EnumFacing;
        //   347: astore_2       
        //   348: aload_2        
        //   349: arraylength    
        //   350: istore_3       
        //   351: iconst_0       
        //   352: istore          4
        //   354: iload           4
        //   356: iload_3        
        //   357: if_icmpge       616
        //   360: aload_2        
        //   361: iload           4
        //   363: aaload         
        //   364: astore          5
        //   366: aload           5
        //   368: getstatic       net/minecraft/util/EnumFacing.UP:Lnet/minecraft/util/EnumFacing;
        //   371: if_acmpne       377
        //   374: goto            612
        //   377: aload_0        
        //   378: aload_1        
        //   379: aload           5
        //   381: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //   384: aload           5
        //   386: if_acmpne       392
        //   389: goto            612
        //   392: aload_1        
        //   393: aload           5
        //   395: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //   398: astore          6
        //   400: aload           6
        //   402: invokestatic    me/rebirthclient/api/util/BlockUtil.canPlaceEnum:(Lnet/minecraft/util/math/BlockPos;)Z
        //   405: ifeq            417
        //   408: aload_0        
        //   409: aload           6
        //   411: invokespecial   me/rebirthclient/mod/modules/impl/combat/Surround.placeBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //   414: goto            434
        //   417: aload           6
        //   419: invokestatic    me/rebirthclient/api/util/BlockUtil.canReplace:(Lnet/minecraft/util/math/BlockPos;)Z
        //   422: ifeq            434
        //   425: aload_0        
        //   426: aload           6
        //   428: invokevirtual   net/minecraft/util/math/BlockPos.down:()Lnet/minecraft/util/math/BlockPos;
        //   431: invokespecial   me/rebirthclient/mod/modules/impl/combat/Surround.placeBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //   434: aload           6
        //   436: ifeq            612
        //   439: aload_0        
        //   440: getfield        me/rebirthclient/mod/modules/impl/combat/Surround.extend:Lme/rebirthclient/mod/modules/settings/Setting;
        //   443: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   446: checkcast       Ljava/lang/Boolean;
        //   449: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   452: ifeq            612
        //   455: getstatic       net/minecraft/util/EnumFacing.VALUES:[Lnet/minecraft/util/EnumFacing;
        //   458: astore          7
        //   460: aload           7
        //   462: arraylength    
        //   463: istore          8
        //   465: iconst_0       
        //   466: istore          9
        //   468: iload           9
        //   470: iload           8
        //   472: if_icmpge       612
        //   475: aload           7
        //   477: iload           9
        //   479: aaload         
        //   480: astore          10
        //   482: aload           10
        //   484: getstatic       net/minecraft/util/EnumFacing.UP:Lnet/minecraft/util/EnumFacing;
        //   487: if_acmpne       493
        //   490: goto            608
        //   493: aload           6
        //   495: aload           10
        //   497: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //   500: astore          11
        //   502: aload           11
        //   504: ifeq            586
        //   507: getstatic       net/minecraft/util/EnumFacing.VALUES:[Lnet/minecraft/util/EnumFacing;
        //   510: astore          12
        //   512: aload           12
        //   514: arraylength    
        //   515: istore          13
        //   517: iconst_0       
        //   518: istore          14
        //   520: iload           14
        //   522: iload           13
        //   524: if_icmpge       586
        //   527: aload           12
        //   529: iload           14
        //   531: aaload         
        //   532: astore          15
        //   534: aload           15
        //   536: getstatic       net/minecraft/util/EnumFacing.UP:Lnet/minecraft/util/EnumFacing;
        //   539: if_acmpne       545
        //   542: goto            582
        //   545: aload_0        
        //   546: aload           11
        //   548: invokespecial   me/rebirthclient/mod/modules/impl/combat/Surround.placeBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //   551: aload           11
        //   553: aload           15
        //   555: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //   558: astore          16
        //   560: aload_0        
        //   561: aload           16
        //   563: invokestatic    me/rebirthclient/api/util/BlockUtil.canPlaceEnum:(Lnet/minecraft/util/math/BlockPos;)Z
        //   566: ifeq            574
        //   569: aload           16
        //   571: goto            579
        //   574: aload           16
        //   576: invokevirtual   net/minecraft/util/math/BlockPos.down:()Lnet/minecraft/util/math/BlockPos;
        //   579: invokespecial   me/rebirthclient/mod/modules/impl/combat/Surround.placeBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //   582: iinc            14, 1
        //   585: return         
        //   586: aload_0        
        //   587: aload           11
        //   589: invokestatic    me/rebirthclient/api/util/BlockUtil.canPlaceEnum:(Lnet/minecraft/util/math/BlockPos;)Z
        //   592: ifeq            600
        //   595: aload           11
        //   597: goto            605
        //   600: aload           11
        //   602: invokevirtual   net/minecraft/util/math/BlockPos.down:()Lnet/minecraft/util/math/BlockPos;
        //   605: invokespecial   me/rebirthclient/mod/modules/impl/combat/Surround.placeBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //   608: iinc            9, 1
        //   611: return         
        //   612: iinc            4, 1
        //   615: return         
        //   616: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0612 (coming from #0374).
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
    
    private boolean lambda$new$3(final Boolean b) {
        return this.jumpDisable.isOpen();
    }
    
    @Override
    public void onEnable() {
        this.startPos = EntityUtil.getPlayerPos();
        this.startX = Surround.mc.player.posX;
        this.startY = Surround.mc.player.posY;
        this.startZ = Surround.mc.player.posZ;
        if ((boolean)this.center.getValue() && InventoryUtil.findHotbarBlock(Blocks.OBSIDIAN) != -1) {
            AutoCenter.INSTANCE.enable();
        }
    }
    
    private boolean lambda$new$0(final Float n) {
        return this.breakCrystal.isOpen();
    }
    
    private boolean lambda$new$2(final Boolean b) {
        return this.moveDisable.isOpen();
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.moveDisable.isOpen();
    }
    
    static {
        Surround.INSTANCE = new Surround();
    }
    
    public Surround() {
        super("Surround", "Surrounds you with Obsidian", Category.COMBAT);
        this.enableInHole = this.add(new Setting("EnableInHole", false));
        this.timer = new Timer();
        this.delay = this.add(new Setting("Delay", 50, 0, 500));
        this.multiPlace = this.add(new Setting("MultiPlace", 1, 1, 8));
        this.rotate = this.add(new Setting("Rotate", true));
        this.packet = this.add(new Setting("Packet", true));
        this.breakCrystal = this.add(new Setting("BreakCrystal", true).setParent());
        this.safeHealth = this.add(new Setting("SafeHealth", 16.0f, 0.0f, 36.0f, this::lambda$new$0));
        this.center = this.add(new Setting("Center", true));
        this.extend = this.add(new Setting("Extend", true));
        this.onlyGround = this.add(new Setting("OnlyGround", true));
        this.moveDisable = this.add(new Setting("MoveDisable", true).setParent());
        this.strictDisable = this.add(new Setting("StrictDisable", false, this::lambda$new$1));
        this.isMoving = this.add(new Setting("isMoving", true, this::lambda$new$2));
        this.jumpDisable = this.add(new Setting("JumpDisable", true).setParent());
        this.inMoving = this.add(new Setting("inMoving", true, this::lambda$new$3));
        this.startX = 0.0;
        this.startY = 0.0;
        this.startZ = 0.0;
        this.progress = 0;
        this.startPos = null;
        Surround.INSTANCE = this;
    }
    
    private void placeBlock(final BlockPos blockPos) {
        if (!BlockUtil.canPlace3(blockPos)) {
            return;
        }
        if ((!(boolean)this.breakCrystal.getValue() || EntityUtil.getHealth((Entity)Surround.mc.player) < (float)this.safeHealth.getValue()) && !BlockUtil.canPlace(blockPos)) {
            return;
        }
        if (this.progress >= (int)this.multiPlace.getValue()) {
            return;
        }
        final int currentItem = Surround.mc.player.inventory.currentItem;
        if (InventoryUtil.findHotbarClass((Class)BlockObsidian.class) == -1) {
            return;
        }
        if ((boolean)this.breakCrystal.getValue() && EntityUtil.getHealth((Entity)Surround.mc.player) >= (float)this.safeHealth.getValue()) {
            CombatUtil.attackCrystal(blockPos, (boolean)this.rotate.getValue(), false);
        }
        InventoryUtil.doSwap(InventoryUtil.findHotbarClass((Class)BlockObsidian.class));
        BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue(), (boolean)this.breakCrystal.getValue() && EntityUtil.getHealth((Entity)Surround.mc.player) >= (float)this.safeHealth.getValue(), false);
        InventoryUtil.doSwap(currentItem);
        ++this.progress;
        this.timer.reset();
    }
}
