//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;
import net.minecraft.init.*;
import me.rebirthclient.mod.modules.impl.exploit.*;
import net.minecraft.potion.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.server.*;
import java.util.*;

public class HoleSnap extends Module
{
    private BlockPos holePos;
    public static final List holeBlocks;
    private static final BlockPos[] surroundOffset;
    private final Setting timeoutTicks;
    private final Setting blink;
    boolean resetMove;
    public static HoleSnap INSTANCE;
    private final Setting timer;
    private final Setting range;
    private int stuckTicks;
    private int enabledTicks;
    
    public HoleSnap() {
        super("HoleSnap", "IQ", Category.MOVEMENT);
        this.blink = this.add(new Setting("Blink", false));
        this.range = this.add(new Setting("Range", 5, 1, 50));
        this.timer = this.add(new Setting("Timer", 1.6f, 1.0f, 8.0f));
        this.timeoutTicks = this.add(new Setting("TimeOutTicks", 10, 0, 30));
        this.resetMove = false;
        HoleSnap.INSTANCE = this;
    }
    
    public double applySpeedPotionEffects(final EntityLivingBase entityLivingBase) {
        double n;
        if (entityLivingBase.getActivePotionEffect(MobEffects.SPEED) == null) {
            n = 0.2873;
        }
        else {
            n = 0.2873 * this.getSpeedEffectMultiplier(entityLivingBase);
        }
        return n;
    }
    
    @SubscribeEvent
    public void onInput(final InputUpdateEvent inputUpdateEvent) {
        if (inputUpdateEvent.getMovementInput() instanceof MovementInputFromOptions && this.holePos != null) {
            this.resetMove(inputUpdateEvent.getMovementInput());
        }
    }
    
    private void resetMove(final MovementInput movementInput) {
        movementInput.moveForward = 0.0f;
        movementInput.moveStrafe = 0.0f;
        movementInput.forwardKeyDown = false;
        movementInput.backKeyDown = false;
        movementInput.leftKeyDown = false;
        movementInput.rightKeyDown = false;
    }
    
    public List getBlockPositionsInArea(final BlockPos blockPos, final BlockPos blockPos2) {
        return this.getBlockPos(Math.min(blockPos.getX(), blockPos2.getX()), Math.max(blockPos.getX(), blockPos2.getX()), Math.min(blockPos.getY(), blockPos2.getY()), Math.max(blockPos.getY(), blockPos2.getY()), Math.min(blockPos.getZ(), blockPos2.getZ()), Math.max(blockPos.getZ(), blockPos2.getZ()));
    }
    
    public Vec3d toVec3dCenter(final Vec3i vec3i) {
        return this.toVec3dCenter(vec3i, 0.0, 0.0, 0.0);
    }
    
    public Vec3d toVec3dCenter(final Vec3i vec3i, final double n, final double n2, final double n3) {
        return new Vec3d(vec3i.getX() + 0.5 + n, vec3i.getY() + 0.5 + n2, vec3i.getZ() + 0.5 + n3);
    }
    
    @Override
    public void onTick() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: dup            
        //     2: getfield        me/rebirthclient/mod/modules/impl/movement/HoleSnap.enabledTicks:I
        //     5: iconst_1       
        //     6: iadd           
        //     7: putfield        me/rebirthclient/mod/modules/impl/movement/HoleSnap.enabledTicks:I
        //    10: aload_0        
        //    11: getfield        me/rebirthclient/mod/modules/impl/movement/HoleSnap.enabledTicks:I
        //    14: aload_0        
        //    15: getfield        me/rebirthclient/mod/modules/impl/movement/HoleSnap.timeoutTicks:Lme/rebirthclient/mod/modules/settings/Setting;
        //    18: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    21: checkcast       Ljava/lang/Integer;
        //    24: invokevirtual   java/lang/Integer.intValue:()I
        //    27: iconst_1       
        //    28: isub           
        //    29: if_icmple       37
        //    32: aload_0        
        //    33: invokevirtual   me/rebirthclient/mod/modules/impl/movement/HoleSnap.disable:()V
        //    36: return         
        //    37: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //    40: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    43: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.isEntityAlive:()Z
        //    46: ifeq            602
        //    49: aload_0        
        //    50: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //    53: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    56: ifeq            595
        //    59: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //    62: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    65: astore_1       
        //    66: aload_0        
        //    67: aload_1        
        //    68: invokevirtual   me/rebirthclient/mod/modules/impl/movement/HoleSnap.getSpeed:(Lnet/minecraft/entity/Entity;)D
        //    71: dstore_2       
        //    72: aload_0        
        //    73: dload_2        
        //    74: ifeq            82
        //    77: aload_0        
        //    78: invokevirtual   me/rebirthclient/mod/modules/impl/movement/HoleSnap.disable:()V
        //    81: return         
        //    82: aload_0        
        //    83: invokespecial   me/rebirthclient/mod/modules/impl/movement/HoleSnap.getHole:()Lnet/minecraft/util/math/BlockPos;
        //    86: astore          4
        //    88: aload           4
        //    90: ifnull          588
        //    93: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //    96: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    99: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   102: aload           4
        //   104: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //   107: i2d            
        //   108: dsub           
        //   109: ldc2_w          0.5
        //   112: dcmpg          
        //   113: ifge            213
        //   116: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   119: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   122: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //   125: aload           4
        //   127: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //   130: i2d            
        //   131: dsub           
        //   132: ldc2_w          0.65
        //   135: dcmpg          
        //   136: ifgt            213
        //   139: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   142: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   145: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //   148: aload           4
        //   150: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //   153: i2d            
        //   154: dsub           
        //   155: ldc2_w          0.35
        //   158: dcmpl          
        //   159: iflt            213
        //   162: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   165: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   168: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   171: aload           4
        //   173: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //   176: i2d            
        //   177: dsub           
        //   178: ldc2_w          0.65
        //   181: dcmpg          
        //   182: ifgt            213
        //   185: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   188: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   191: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   194: aload           4
        //   196: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //   199: i2d            
        //   200: dsub           
        //   201: ldc2_w          0.35
        //   204: dcmpl          
        //   205: iflt            213
        //   208: aload_0        
        //   209: invokevirtual   me/rebirthclient/mod/modules/impl/movement/HoleSnap.disable:()V
        //   212: return         
        //   213: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   216: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   219: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //   222: aload           4
        //   224: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //   227: i2d            
        //   228: dsub           
        //   229: ldc2_w          0.65
        //   232: dcmpg          
        //   233: ifgt            326
        //   236: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   239: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   242: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //   245: aload           4
        //   247: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //   250: i2d            
        //   251: dsub           
        //   252: ldc2_w          0.35
        //   255: dcmpl          
        //   256: iflt            326
        //   259: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   262: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   265: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   268: aload           4
        //   270: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //   273: i2d            
        //   274: dsub           
        //   275: ldc2_w          0.65
        //   278: dcmpg          
        //   279: ifgt            326
        //   282: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   285: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   288: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   291: aload           4
        //   293: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //   296: i2d            
        //   297: dsub           
        //   298: ldc2_w          0.35
        //   301: dcmpl          
        //   302: iflt            326
        //   305: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   308: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   311: dconst_0       
        //   312: putfield        net/minecraft/client/entity/EntityPlayerSP.motionX:D
        //   315: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   318: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   321: dconst_0       
        //   322: putfield        net/minecraft/client/entity/EntityPlayerSP.motionZ:D
        //   325: return         
        //   326: aload_0        
        //   327: iconst_1       
        //   328: putfield        me/rebirthclient/mod/modules/impl/movement/HoleSnap.resetMove:Z
        //   331: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   334: getfield        net/minecraft/client/Minecraft.timer:Lnet/minecraft/util/Timer;
        //   337: astore          5
        //   339: aload_0        
        //   340: getfield        me/rebirthclient/mod/modules/impl/movement/HoleSnap.timer:Lme/rebirthclient/mod/modules/settings/Setting;
        //   343: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   346: checkcast       Ljava/lang/Float;
        //   349: astore          6
        //   351: aload           5
        //   353: ldc_w           50.0
        //   356: aload           6
        //   358: invokevirtual   java/lang/Float.floatValue:()F
        //   361: fdiv           
        //   362: putfield        net/minecraft/util/Timer.tickLength:F
        //   365: aload_0        
        //   366: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   369: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   372: aload           4
        //   374: ifeq            585
        //   377: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   380: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   383: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getPositionVector:()Lnet/minecraft/util/math/Vec3d;
        //   386: astore          7
        //   388: new             Lnet/minecraft/util/math/Vec3d;
        //   391: dup            
        //   392: aload           4
        //   394: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //   397: i2d            
        //   398: ldc2_w          0.5
        //   401: dadd           
        //   402: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   405: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   408: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   411: aload           4
        //   413: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //   416: i2d            
        //   417: ldc2_w          0.5
        //   420: dadd           
        //   421: invokespecial   net/minecraft/util/math/Vec3d.<init>:(DDD)V
        //   424: astore          8
        //   426: aload_0        
        //   427: aload           7
        //   429: aload           8
        //   431: invokespecial   me/rebirthclient/mod/modules/impl/movement/HoleSnap.getRotationTo:(Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;)Lnet/minecraft/util/math/Vec2f;
        //   434: getfield        net/minecraft/util/math/Vec2f.x:F
        //   437: fstore          9
        //   439: fload           9
        //   441: ldc_w           180.0
        //   444: fdiv           
        //   445: ldc_w           3.1415927
        //   448: fmul           
        //   449: fstore          10
        //   451: aload           7
        //   453: aload           8
        //   455: invokevirtual   net/minecraft/util/math/Vec3d.distanceTo:(Lnet/minecraft/util/math/Vec3d;)D
        //   458: dstore          11
        //   460: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   463: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   466: astore          13
        //   468: aload_0        
        //   469: aload           13
        //   471: invokevirtual   me/rebirthclient/mod/modules/impl/movement/HoleSnap.applySpeedPotionEffects:(Lnet/minecraft/entity/EntityLivingBase;)D
        //   474: dstore          14
        //   476: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   479: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   482: getfield        net/minecraft/client/entity/EntityPlayerSP.onGround:Z
        //   485: ifeq            493
        //   488: dload           14
        //   490: goto            503
        //   493: dload_2        
        //   494: ldc2_w          0.02
        //   497: dadd           
        //   498: dload           14
        //   500: invokestatic    java/lang/Math.max:(DD)D
        //   503: dstore          16
        //   505: dload           16
        //   507: dload           11
        //   509: invokestatic    java/lang/Math.min:(DD)D
        //   512: dstore          18
        //   514: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   517: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   520: fload           10
        //   522: f2d            
        //   523: invokestatic    java/lang/Math.sin:(D)D
        //   526: d2f            
        //   527: fneg           
        //   528: f2d            
        //   529: dload           18
        //   531: dmul           
        //   532: putfield        net/minecraft/client/entity/EntityPlayerSP.motionX:D
        //   535: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   538: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   541: fload           10
        //   543: f2d            
        //   544: invokestatic    java/lang/Math.cos:(D)D
        //   547: d2f            
        //   548: f2d            
        //   549: dload           18
        //   551: dmul           
        //   552: putfield        net/minecraft/client/entity/EntityPlayerSP.motionZ:D
        //   555: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   558: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   561: getfield        net/minecraft/client/entity/EntityPlayerSP.collidedHorizontally:Z
        //   564: ifeq            580
        //   567: aload_0        
        //   568: dup            
        //   569: getfield        me/rebirthclient/mod/modules/impl/movement/HoleSnap.stuckTicks:I
        //   572: iconst_1       
        //   573: iadd           
        //   574: putfield        me/rebirthclient/mod/modules/impl/movement/HoleSnap.stuckTicks:I
        //   577: goto            585
        //   580: aload_0        
        //   581: iconst_0       
        //   582: putfield        me/rebirthclient/mod/modules/impl/movement/HoleSnap.stuckTicks:I
        //   585: goto            592
        //   588: aload_0        
        //   589: invokevirtual   me/rebirthclient/mod/modules/impl/movement/HoleSnap.disable:()V
        //   592: goto            606
        //   595: aload_0        
        //   596: invokevirtual   me/rebirthclient/mod/modules/impl/movement/HoleSnap.disable:()V
        //   599: goto            606
        //   602: aload_0        
        //   603: invokevirtual   me/rebirthclient/mod/modules/impl/movement/HoleSnap.disable:()V
        //   606: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0606 (coming from #0599).
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
    
    private double normalizeAngle(final double n) {
        double n2;
        if ((n2 = n % 360.0) >= 180.0) {
            n2 -= 360.0;
        }
        if (n2 < -180.0) {
            n2 += 360.0;
        }
        return n2;
    }
    
    private BlockPos getHole() {
        if (HoleSnap.mc.player.ticksExisted % 10 == 0 && !this.getFlooredPosition((Entity)HoleSnap.mc.player).equals((Object)this.holePos)) {
            return this.findHole();
        }
        final BlockPos holePos;
        if ((holePos = this.holePos) != null) {
            return holePos;
        }
        return this.findHole();
    }
    
    public static BlockResistance getBlockResistance(final BlockPos blockPos) {
        if (HoleSnap.mc.world.isAirBlock(blockPos)) {
            return BlockResistance.Blank;
        }
        if (HoleSnap.mc.world.getBlockState(blockPos).getBlock().getBlockHardness(HoleSnap.mc.world.getBlockState(blockPos), (World)HoleSnap.mc.world, blockPos) != -1.0f && !HoleSnap.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.OBSIDIAN) && !HoleSnap.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.ANVIL) && !HoleSnap.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.ENCHANTING_TABLE) && !HoleSnap.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.ENDER_CHEST)) {
            return BlockResistance.Breakable;
        }
        if (HoleSnap.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.OBSIDIAN) || HoleSnap.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.ANVIL) || HoleSnap.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.ENCHANTING_TABLE) || HoleSnap.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.ENDER_CHEST)) {
            return BlockResistance.Resistant;
        }
        if (HoleSnap.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.BEDROCK)) {
            return BlockResistance.Unbreakable;
        }
        return null;
    }
    
    private BlockPos findHole() {
        final Pair pair = new Pair(69.69, BlockPos.ORIGIN);
        final BlockPos flooredPosition = this.getFlooredPosition((Entity)HoleSnap.mc.player);
        final Integer n = (Integer)this.range.getValue();
        final Iterator iterator = this.getBlockPositionsInArea(flooredPosition.add((int)n, -1, (int)n), flooredPosition.add(-n, -1, -n)).iterator();
        if (iterator.hasNext()) {
            final BlockPos blockPos = iterator.next();
            final double distanceTo = this.distanceTo((Entity)HoleSnap.mc.player, (Vec3i)blockPos);
            if (distanceTo <= (int)this.range.getValue()) {
                if (distanceTo > (double)pair.getLeft()) {
                    return null;
                }
                int n2 = 0;
                final BlockPos add;
                if (n2 < 6 && HoleSnap.mc.world.isAirBlock((add = blockPos.add(0, -(n2++), 0)).up())) {
                    if (add != 0 && this.checkHole(add) == HoleType.NONE) {
                        return null;
                    }
                    final Pair pair2 = new Pair(distanceTo, add);
                    return null;
                }
            }
            return null;
        }
        BlockPos blockPos2;
        if (pair.getRight() != BlockPos.ORIGIN) {
            final BlockPos holePos = (BlockPos)pair.getRight();
            this.holePos = holePos;
            blockPos2 = holePos;
        }
        else {
            blockPos2 = null;
        }
        return blockPos2;
    }
    
    public BlockPos getFlooredPosition(final Entity entity) {
        return new BlockPos((int)Math.floor(entity.posX), (int)Math.floor(entity.posY), (int)Math.floor(entity.posZ));
    }
    
    public static BlockPos is2Hole(final BlockPos p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: if_acmpne       6
        //     4: aconst_null    
        //     5: areturn        
        //     6: aconst_null    
        //     7: astore_1       
        //     8: iconst_0       
        //     9: istore_2       
        //    10: iconst_0       
        //    11: istore_3       
        //    12: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //    15: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    18: aload_0        
        //    19: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    22: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //    27: getstatic       net/minecraft/init/Blocks.AIR:Lnet/minecraft/block/Block;
        //    30: if_acmpeq       35
        //    33: aconst_null    
        //    34: areturn        
        //    35: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.holeBlocks:Ljava/util/List;
        //    38: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    43: astore          4
        //    45: aload           4
        //    47: invokeinterface java/util/Iterator.hasNext:()Z
        //    52: ifeq            143
        //    55: aload           4
        //    57: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    62: checkcast       Lnet/minecraft/util/math/BlockPos;
        //    65: astore          5
        //    67: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //    70: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    73: aload_0        
        //    74: aload           5
        //    76: invokevirtual   net/minecraft/util/math/BlockPos.add:(Lnet/minecraft/util/math/Vec3i;)Lnet/minecraft/util/math/BlockPos;
        //    79: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    82: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //    87: getstatic       net/minecraft/init/Blocks.AIR:Lnet/minecraft/block/Block;
        //    90: if_acmpne       141
        //    93: aload_0        
        //    94: aload           5
        //    96: invokevirtual   net/minecraft/util/math/BlockPos.add:(Lnet/minecraft/util/math/Vec3i;)Lnet/minecraft/util/math/BlockPos;
        //    99: new             Lnet/minecraft/util/math/BlockPos;
        //   102: dup            
        //   103: aload           5
        //   105: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //   108: aload           5
        //   110: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //   113: iconst_1       
        //   114: isub           
        //   115: aload           5
        //   117: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //   120: invokespecial   net/minecraft/util/math/BlockPos.<init>:(III)V
        //   123: invokevirtual   net/minecraft/util/math/BlockPos.equals:(Ljava/lang/Object;)Z
        //   126: ifeq            131
        //   129: aconst_null    
        //   130: areturn        
        //   131: aload_0        
        //   132: aload           5
        //   134: invokevirtual   net/minecraft/util/math/BlockPos.add:(Lnet/minecraft/util/math/Vec3i;)Lnet/minecraft/util/math/BlockPos;
        //   137: astore_1       
        //   138: iinc            2, 1
        //   141: aconst_null    
        //   142: areturn        
        //   143: iload_2        
        //   144: iconst_1       
        //   145: if_icmpne       330
        //   148: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.holeBlocks:Ljava/util/List;
        //   151: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   156: astore          4
        //   158: aload           4
        //   160: invokeinterface java/util/Iterator.hasNext:()Z
        //   165: ifeq            239
        //   168: aload           4
        //   170: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   175: checkcast       Lnet/minecraft/util/math/BlockPos;
        //   178: astore          5
        //   180: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   183: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   186: aload_0        
        //   187: aload           5
        //   189: invokevirtual   net/minecraft/util/math/BlockPos.add:(Lnet/minecraft/util/math/Vec3i;)Lnet/minecraft/util/math/BlockPos;
        //   192: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   195: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   200: getstatic       net/minecraft/init/Blocks.BEDROCK:Lnet/minecraft/block/Block;
        //   203: if_acmpeq       234
        //   206: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   209: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   212: aload_0        
        //   213: aload           5
        //   215: invokevirtual   net/minecraft/util/math/BlockPos.add:(Lnet/minecraft/util/math/Vec3i;)Lnet/minecraft/util/math/BlockPos;
        //   218: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   221: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   226: getstatic       net/minecraft/init/Blocks.OBSIDIAN:Lnet/minecraft/block/Block;
        //   229: if_acmpeq       234
        //   232: aconst_null    
        //   233: areturn        
        //   234: iinc            3, 1
        //   237: aconst_null    
        //   238: areturn        
        //   239: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.holeBlocks:Ljava/util/List;
        //   242: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   247: astore          4
        //   249: aload           4
        //   251: invokeinterface java/util/Iterator.hasNext:()Z
        //   256: ifeq            330
        //   259: aload           4
        //   261: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   266: checkcast       Lnet/minecraft/util/math/BlockPos;
        //   269: astore          5
        //   271: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   274: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   277: aload_1        
        //   278: aload           5
        //   280: invokevirtual   net/minecraft/util/math/BlockPos.add:(Lnet/minecraft/util/math/Vec3i;)Lnet/minecraft/util/math/BlockPos;
        //   283: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   286: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   291: getstatic       net/minecraft/init/Blocks.BEDROCK:Lnet/minecraft/block/Block;
        //   294: if_acmpeq       325
        //   297: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //   300: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   303: aload_1        
        //   304: aload           5
        //   306: invokevirtual   net/minecraft/util/math/BlockPos.add:(Lnet/minecraft/util/math/Vec3i;)Lnet/minecraft/util/math/BlockPos;
        //   309: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   312: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   317: getstatic       net/minecraft/init/Blocks.OBSIDIAN:Lnet/minecraft/block/Block;
        //   320: if_acmpeq       325
        //   323: aconst_null    
        //   324: areturn        
        //   325: iinc            3, 1
        //   328: aconst_null    
        //   329: areturn        
        //   330: iload_3        
        //   331: bipush          8
        //   333: if_icmpne       338
        //   336: aload_1        
        //   337: areturn        
        //   338: aconst_null    
        //   339: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void onDisable() {
        if ((boolean)this.blink.getValue() && Blink.INSTANCE.isOn()) {
            Blink.INSTANCE.disable();
        }
        if (this.resetMove) {
            HoleSnap.mc.player.motionX = 0.0;
            HoleSnap.mc.player.motionZ = 0.0;
        }
        this.holePos = null;
        this.stuckTicks = 0;
        this.enabledTicks = 0;
        HoleSnap.mc.timer.tickLength = 50.0f;
    }
    
    public HoleType checkHole(final BlockPos p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //     6: aload_1        
        //     7: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //    10: ifeq            48
        //    13: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //    16: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    19: aload_1        
        //    20: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //    23: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //    26: ifeq            48
        //    29: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //    32: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    35: aload_1        
        //    36: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //    39: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //    42: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //    45: ifne            52
        //    48: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap$HoleType.NONE:Lme/rebirthclient/mod/modules/impl/movement/HoleSnap$HoleType;
        //    51: areturn        
        //    52: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap$HoleType.BEDROCK:Lme/rebirthclient/mod/modules/impl/movement/HoleSnap$HoleType;
        //    55: astore_2       
        //    56: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.surroundOffset:[Lnet/minecraft/util/math/BlockPos;
        //    59: astore_3       
        //    60: aload_3        
        //    61: arraylength    
        //    62: istore          4
        //    64: iconst_0       
        //    65: istore          5
        //    67: iload           5
        //    69: iload           4
        //    71: if_icmpge       120
        //    74: aload_3        
        //    75: iload           5
        //    77: aaload         
        //    78: astore          6
        //    80: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap.mc:Lnet/minecraft/client/Minecraft;
        //    83: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    86: aload_1        
        //    87: aload           6
        //    89: invokevirtual   net/minecraft/util/math/BlockPos.add:(Lnet/minecraft/util/math/Vec3i;)Lnet/minecraft/util/math/BlockPos;
        //    92: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    95: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   100: astore          7
        //   102: aload_0        
        //   103: aload           7
        //   105: ifeq            115
        //   108: getstatic       me/rebirthclient/mod/modules/impl/movement/HoleSnap$HoleType.NONE:Lme/rebirthclient/mod/modules/impl/movement/HoleSnap$HoleType;
        //   111: astore_2       
        //   112: goto            120
        //   115: iinc            5, 1
        //   118: aconst_null    
        //   119: areturn        
        //   120: aload_2        
        //   121: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0120 (coming from #0112).
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
    
    private double getSpeedEffectMultiplier(final EntityLivingBase entityLivingBase) {
        final PotionEffect getActivePotionEffect = entityLivingBase.getActivePotionEffect(MobEffects.SPEED);
        double n;
        if (getActivePotionEffect == null) {
            n = 1.0;
        }
        else {
            n = 1.0 + (getActivePotionEffect.getAmplifier() + 1.0) * 0.2;
        }
        return n;
    }
    
    public double getSpeed(final Entity entity) {
        return Math.hypot(entity.motionX, entity.motionZ);
    }
    
    private Vec2f getRotationTo(final Vec3d vec3d, final Vec3d vec3d2) {
        return this.getRotationFromVec(vec3d2.subtract(vec3d));
    }
    
    private Vec2f getRotationFromVec(final Vec3d vec3d) {
        return new Vec2f((float)this.normalizeAngle(Math.toDegrees(Math.atan2(vec3d.z, vec3d.x)) - 90.0), (float)this.normalizeAngle(Math.toDegrees(-Math.atan2(vec3d.y, Math.hypot(vec3d.x, vec3d.z)))));
    }
    
    public double distanceTo(final Entity entity, final Vec3i vec3i) {
        final double n = vec3i.getX() + 0.5 - entity.posX;
        final double n2 = vec3i.getY() + 0.5 - entity.posY;
        final double n3 = vec3i.getZ() + 0.5 - entity.posZ;
        return Math.sqrt(n * n + n2 * n2 + n3 * n3);
    }
    
    public HoleType checkHole(final Entity entity) {
        return this.checkHole(this.getFlooredPosition(entity));
    }
    
    @SubscribeEvent
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketPlayerPosLook) {
            this.disable();
        }
    }
    
    @Override
    public void onEnable() {
        if ((boolean)this.blink.getValue() && Blink.INSTANCE.isOff()) {
            Blink.INSTANCE.enable();
        }
        this.resetMove = false;
    }
    
    static {
        holeBlocks = Arrays.asList(new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(-1, 0, 0), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1));
        surroundOffset = new BlockPos[] { new BlockPos(0, -1, 0), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(-1, 0, 0) };
    }
    
    public BlockPos toBlockPos(final Vec3d vec3d) {
        return new BlockPos((int)Math.floor(vec3d.x), (int)Math.floor(vec3d.y), (int)Math.floor(vec3d.z));
    }
    
    private List getBlockPos(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        int n7 = n;
        if (n7 <= n2) {
            int i;
            do {
                i = n7++;
                int n8 = n5;
                if (n8 > n6) {
                    continue;
                }
                int j;
                do {
                    j = n8++;
                    int n9 = n3;
                    if (n9 > n4) {
                        continue;
                    }
                    int k;
                    do {
                        k = n9++;
                        list.add(new BlockPos(i, k, j));
                    } while (k != n4);
                } while (j != n6);
            } while (i != n2);
        }
        return list;
    }
    
    public enum HoleType
    {
        OBBY("OBBY", 1), 
        BEDROCK("BEDROCK", 2);
        
        private static final HoleType[] $VALUES;
        
        NONE("NONE", 0);
        
        private HoleType(final String s, final int n) {
        }
        
        static {
            $VALUES = new HoleType[] { HoleType.NONE, HoleType.OBBY, HoleType.BEDROCK };
        }
    }
    
    public enum BlockResistance
    {
        Unbreakable("Unbreakable", 3), 
        Resistant("Resistant", 2), 
        Blank("Blank", 0), 
        Breakable("Breakable", 1);
        
        private static final BlockResistance[] $VALUES;
        
        static {
            $VALUES = new BlockResistance[] { BlockResistance.Blank, BlockResistance.Breakable, BlockResistance.Resistant, BlockResistance.Unbreakable };
        }
        
        private BlockResistance(final String s, final int n) {
        }
    }
    
    public static class Pair
    {
        Object right;
        Object left;
        
        public Object getRight() {
            return this.right;
        }
        
        public Pair(final Object left, final Object right) {
            this.left = left;
            this.right = right;
        }
        
        public Pair setRight(final Object right) {
            this.right = right;
            return this;
        }
        
        public Pair setLeft(final Object left) {
            this.left = left;
            return this;
        }
        
        public Object getLeft() {
            return this.left;
        }
    }
}
