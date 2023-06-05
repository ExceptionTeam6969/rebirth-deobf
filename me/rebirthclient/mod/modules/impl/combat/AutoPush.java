//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.init.*;
import net.minecraft.entity.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraft.block.state.*;
import me.rebirthclient.api.util.*;
import java.util.*;

public class AutoPush extends Module
{
    private final Setting updateDelay;
    private final Setting maxSelfSpeed;
    private final Setting crystalCheck;
    private final Setting eatingPause;
    private final Setting surroundCheck;
    private final Setting maxTargetSpeed;
    private final Setting pistonPacket;
    private final Timer timer;
    private final Setting onlyBurrow;
    private final Setting checkPiston;
    private final Setting allowWeb;
    private EntityPlayer DisplayTarget;
    private final Setting placeRange;
    private final Setting autoDisable;
    public static final List canPushBlock;
    private final Setting pullBack;
    private final Setting onlyCrystal;
    private final Setting mine;
    private final Setting selfGround;
    private final Setting noEating;
    private final Setting range;
    private final Setting rotate;
    private final Setting onlyGround;
    private final Setting redStonePacket;
    private final Setting attackCrystal;
    
    private Block getBlock(final BlockPos blockPos) {
        return AutoPush.mc.world.getBlockState(blockPos).getBlock();
    }
    
    private Boolean canPush(final EntityPlayer p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_2       
        //     2: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoPush.mc:Lnet/minecraft/client/Minecraft;
        //     5: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //     8: new             Lnet/minecraft/util/math/BlockPos;
        //    11: dup            
        //    12: aload_1        
        //    13: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //    16: dconst_1       
        //    17: dadd           
        //    18: aload_1        
        //    19: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //    22: ldc2_w          0.5
        //    25: dadd           
        //    26: aload_1        
        //    27: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //    30: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //    33: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //    36: ifne            42
        //    39: iinc            2, 1
        //    42: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoPush.mc:Lnet/minecraft/client/Minecraft;
        //    45: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    48: new             Lnet/minecraft/util/math/BlockPos;
        //    51: dup            
        //    52: aload_1        
        //    53: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //    56: dconst_1       
        //    57: dsub           
        //    58: aload_1        
        //    59: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //    62: ldc2_w          0.5
        //    65: dadd           
        //    66: aload_1        
        //    67: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //    70: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //    73: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //    76: ifne            82
        //    79: iinc            2, 1
        //    82: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoPush.mc:Lnet/minecraft/client/Minecraft;
        //    85: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    88: new             Lnet/minecraft/util/math/BlockPos;
        //    91: dup            
        //    92: aload_1        
        //    93: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //    96: aload_1        
        //    97: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //   100: ldc2_w          0.5
        //   103: dadd           
        //   104: aload_1        
        //   105: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //   108: dconst_1       
        //   109: dadd           
        //   110: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   113: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //   116: ifne            122
        //   119: iinc            2, 1
        //   122: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoPush.mc:Lnet/minecraft/client/Minecraft;
        //   125: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   128: new             Lnet/minecraft/util/math/BlockPos;
        //   131: dup            
        //   132: aload_1        
        //   133: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //   136: aload_1        
        //   137: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //   140: ldc2_w          0.5
        //   143: dadd           
        //   144: aload_1        
        //   145: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //   148: dconst_1       
        //   149: dsub           
        //   150: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   153: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //   156: ifne            162
        //   159: iinc            2, 1
        //   162: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoPush.mc:Lnet/minecraft/client/Minecraft;
        //   165: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   168: new             Lnet/minecraft/util/math/BlockPos;
        //   171: dup            
        //   172: aload_1        
        //   173: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //   176: aload_1        
        //   177: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //   180: ldc2_w          2.5
        //   183: dadd           
        //   184: aload_1        
        //   185: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //   188: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   191: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //   194: ifne            369
        //   197: getstatic       net/minecraft/util/EnumFacing.VALUES:[Lnet/minecraft/util/EnumFacing;
        //   200: astore_3       
        //   201: aload_3        
        //   202: arraylength    
        //   203: istore          4
        //   205: iconst_0       
        //   206: istore          5
        //   208: iload           5
        //   210: iload           4
        //   212: if_icmpge       364
        //   215: aload_3        
        //   216: iload           5
        //   218: aaload         
        //   219: astore          6
        //   221: aload           6
        //   223: getstatic       net/minecraft/util/EnumFacing.UP:Lnet/minecraft/util/EnumFacing;
        //   226: if_acmpeq       359
        //   229: aload           6
        //   231: getstatic       net/minecraft/util/EnumFacing.DOWN:Lnet/minecraft/util/EnumFacing;
        //   234: if_acmpne       240
        //   237: goto            359
        //   240: aload_1        
        //   241: invokestatic    me/rebirthclient/api/util/EntityUtil.getEntityPos:(Lnet/minecraft/entity/Entity;)Lnet/minecraft/util/math/BlockPos;
        //   244: aload           6
        //   246: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //   249: astore          7
        //   251: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoPush.mc:Lnet/minecraft/client/Minecraft;
        //   254: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   257: aload           7
        //   259: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //   262: ifeq            282
        //   265: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoPush.mc:Lnet/minecraft/client/Minecraft;
        //   268: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   271: aload           7
        //   273: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //   276: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //   279: ifne            291
        //   282: aload           7
        //   284: aload_0        
        //   285: getfield        me/rebirthclient/mod/modules/impl/combat/AutoPush.DisplayTarget:Lnet/minecraft/entity/player/EntityPlayer;
        //   288: ifeq            359
        //   291: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoPush.mc:Lnet/minecraft/client/Minecraft;
        //   294: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   297: new             Lnet/minecraft/util/math/BlockPos;
        //   300: dup            
        //   301: aload_1        
        //   302: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //   305: aload_1        
        //   306: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //   309: ldc2_w          0.5
        //   312: dadd           
        //   313: aload_1        
        //   314: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //   317: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   320: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //   323: ifne            331
        //   326: iconst_1       
        //   327: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   330: areturn        
        //   331: iload_2        
        //   332: aload_0        
        //   333: getfield        me/rebirthclient/mod/modules/impl/combat/AutoPush.surroundCheck:Lme/rebirthclient/mod/modules/settings/Setting;
        //   336: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   339: checkcast       Ljava/lang/Integer;
        //   342: invokevirtual   java/lang/Integer.intValue:()I
        //   345: iconst_1       
        //   346: isub           
        //   347: if_icmple       354
        //   350: iconst_1       
        //   351: goto            355
        //   354: iconst_0       
        //   355: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   358: areturn        
        //   359: iinc            5, 1
        //   362: aconst_null    
        //   363: areturn        
        //   364: iconst_0       
        //   365: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   368: areturn        
        //   369: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoPush.mc:Lnet/minecraft/client/Minecraft;
        //   372: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   375: new             Lnet/minecraft/util/math/BlockPos;
        //   378: dup            
        //   379: aload_1        
        //   380: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //   383: aload_1        
        //   384: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //   387: ldc2_w          0.5
        //   390: dadd           
        //   391: aload_1        
        //   392: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //   395: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   398: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //   401: ifne            409
        //   404: iconst_1       
        //   405: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   408: areturn        
        //   409: iload_2        
        //   410: aload_0        
        //   411: getfield        me/rebirthclient/mod/modules/impl/combat/AutoPush.surroundCheck:Lme/rebirthclient/mod/modules/settings/Setting;
        //   414: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   417: checkcast       Ljava/lang/Integer;
        //   420: invokevirtual   java/lang/Integer.intValue:()I
        //   423: iconst_1       
        //   424: isub           
        //   425: if_icmple       432
        //   428: iconst_1       
        //   429: goto            433
        //   432: iconst_0       
        //   433: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   436: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0359 (coming from #0288).
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
    
    private void doPiston(final EnumFacing enumFacing, final BlockPos blockPos) {
        if (BlockUtil.canPlace(blockPos, (double)this.placeRange.getValue())) {
            if (this.rotate.getValue()) {
                EntityUtil.facePlacePos(blockPos);
            }
            pistonFacing(enumFacing);
            final int currentItem = AutoPush.mc.player.inventory.currentItem;
            InventoryUtil.doSwap(InventoryUtil.findHotbarClass((Class)BlockPistonBase.class));
            BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, false, (boolean)this.pistonPacket.getValue());
            InventoryUtil.doSwap(currentItem);
            if (this.rotate.getValue()) {
                EntityUtil.facePlacePos(blockPos);
            }
            final EnumFacing[] VALUES = EnumFacing.VALUES;
            final int length = VALUES.length;
            int n = 0;
            if (n < length) {
                final EnumFacing enumFacing2 = VALUES[n];
                if (this.getBlock(blockPos.offset(enumFacing2)) == Blocks.REDSTONE_BLOCK) {
                    if (this.mine.getValue()) {
                        this.mine(blockPos.offset(enumFacing2));
                    }
                    if (this.autoDisable.getValue()) {
                        this.disable();
                    }
                    return;
                }
                ++n;
            }
            else {
                this.doPower(blockPos);
            }
        }
    }
    
    @Override
    public void onUpdate() {
        if (!this.timer.passedMs((long)(int)this.updateDelay.getValue())) {
            return;
        }
        if ((boolean)this.selfGround.getValue() && !AutoPush.mc.player.onGround) {
            if (this.autoDisable.getValue()) {
                this.disable();
            }
            return;
        }
        if (InventoryUtil.findHotbarBlock(Blocks.REDSTONE_BLOCK) == -1 || InventoryUtil.findHotbarClass((Class)BlockPistonBase.class) == -1) {
            if (this.autoDisable.getValue()) {
                this.disable();
            }
            return;
        }
        if (Managers.SPEED.getPlayerSpeed((EntityPlayer)AutoPush.mc.player) > (double)this.maxSelfSpeed.getValue()) {
            if (this.autoDisable.getValue()) {
                this.disable();
            }
            return;
        }
        if ((boolean)this.noEating.getValue() && EntityUtil.isEating()) {
            return;
        }
        if ((boolean)this.onlyCrystal.getValue() && !AutoPush.mc.player.getHeldItemOffhand().getItem().equals(Items.END_CRYSTAL) && !AutoPush.mc.player.getHeldItemMainhand().getItem().equals(Items.END_CRYSTAL)) {
            return;
        }
        this.timer.reset();
        for (final EntityPlayer displayTarget : AutoPush.mc.world.playerEntities) {
            if (!EntityUtil.invalid((Entity)displayTarget, (double)this.range.getValue()) && this.canPush(displayTarget) && (displayTarget.onGround || !(boolean)this.onlyGround.getValue())) {
                if (Managers.SPEED.getPlayerSpeed(displayTarget) > (double)this.maxTargetSpeed.getValue()) {
                    return;
                }
                if (displayTarget != 0 && !(boolean)this.allowWeb.getValue()) {
                    return;
                }
                this.DisplayTarget = displayTarget;
                new BlockPos(displayTarget.posX + 0.1, displayTarget.posY + 0.5, displayTarget.posZ + 0.1);
                if (displayTarget != 0) {
                    return;
                }
                new BlockPos(displayTarget.posX - 0.1, displayTarget.posY + 0.5, displayTarget.posZ + 0.1);
                if (displayTarget != 0) {
                    return;
                }
                new BlockPos(displayTarget.posX + 0.1, displayTarget.posY + 0.5, displayTarget.posZ - 0.1);
                if (displayTarget != 0) {
                    return;
                }
                new BlockPos(displayTarget.posX - 0.1, displayTarget.posY + 0.5, displayTarget.posZ - 0.1);
                if (displayTarget != 0) {
                    return;
                }
                return;
            }
        }
        if (this.autoDisable.getValue()) {
            this.disable();
        }
        this.DisplayTarget = null;
    }
    
    public AutoPush() {
        super("AutoPush", "use piston push hole fag", Category.COMBAT);
        this.rotate = this.add(new Setting("Rotate", true));
        this.pistonPacket = this.add(new Setting("PistonPacket", false));
        this.redStonePacket = this.add(new Setting("RedStonePacket", true));
        this.noEating = this.add(new Setting("NoEating", true));
        this.onlyCrystal = this.add(new Setting("OnlyCrystal", false));
        this.crystalCheck = this.add(new Setting("CrystalCheck", true));
        this.attackCrystal = this.add(new Setting("BreakCrystal", true).setParent());
        this.eatingPause = this.add(new Setting("EatingPause", true, this::lambda$new$0));
        this.mine = this.add(new Setting("Mine", true));
        this.allowWeb = this.add(new Setting("AllowWeb", true));
        this.updateDelay = this.add(new Setting("UpdateDelay", 100, 0, 500));
        this.selfGround = this.add(new Setting("SelfGround", true));
        this.maxSelfSpeed = this.add(new Setting("MaxSelfSpeed", 6.0, 1.0, 30.0));
        this.maxTargetSpeed = this.add(new Setting("MaxTargetSpeed", 4.0, 1.0, 15.0));
        this.onlyGround = this.add(new Setting("OnlyGround", false));
        this.checkPiston = this.add(new Setting("CheckPiston", false));
        this.autoDisable = this.add(new Setting("AutoDisable", true));
        this.pullBack = this.add(new Setting("PullBack", true).setParent());
        this.onlyBurrow = this.add(new Setting("OnlyBurrow", true, this::lambda$new$1));
        this.range = this.add(new Setting("Range", 5.0, 0.0, 6.0));
        this.placeRange = this.add(new Setting("PlaceRange", 5.0, 0.0, 6.0));
        this.surroundCheck = this.add(new Setting("SurroundCheck", 2, 0, 4));
        this.timer = new Timer();
        this.DisplayTarget = null;
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.pullBack.isOpen();
    }
    
    private IBlockState getBlockState(final BlockPos blockPos) {
        return AutoPush.mc.world.getBlockState(blockPos);
    }
    
    @Override
    public String getInfo() {
        if (this.DisplayTarget != null) {
            return this.DisplayTarget.getName();
        }
        return null;
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.attackCrystal.isOpen();
    }
    
    private void mine(final BlockPos blockPos) {
        CombatUtil.mineBlock(blockPos);
    }
    
    public static void pistonFacing(final EnumFacing enumFacing) {
        if (enumFacing == EnumFacing.EAST) {
            EntityUtil.faceYawAndPitch(-90.0f, 5.0f);
        }
        else if (enumFacing == EnumFacing.WEST) {
            EntityUtil.faceYawAndPitch(90.0f, 5.0f);
        }
        else if (enumFacing == EnumFacing.NORTH) {
            EntityUtil.faceYawAndPitch(180.0f, 5.0f);
        }
        else if (enumFacing == EnumFacing.SOUTH) {
            EntityUtil.faceYawAndPitch(0.0f, 5.0f);
        }
    }
    
    static {
        canPushBlock = Arrays.asList(Blocks.AIR, Blocks.ENDER_CHEST, Blocks.STANDING_SIGN, Blocks.WALL_SIGN, (Block)Blocks.REDSTONE_WIRE, Blocks.TRIPWIRE);
    }
}
