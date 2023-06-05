//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util;

import net.minecraft.entity.*;
import net.minecraft.network.*;
import me.rebirthclient.mod.modules.impl.combat.*;
import net.minecraft.util.*;
import java.util.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.block.state.*;
import net.minecraft.block.*;
import me.rebirthclient.api.managers.impl.*;
import net.minecraft.network.play.client.*;
import me.rebirthclient.mod.modules.impl.render.*;
import net.minecraft.entity.item.*;

public class BlockUtil implements Wrapper
{
    public static final List canUseList;
    public static final List shulkerList;
    
    public static boolean canPlaceCrystal(final BlockPos blockPos) {
        final BlockPos down = blockPos.down();
        final BlockPos up = down.up();
        final BlockPos up = down.up(2);
        return (getBlock(down) == Blocks.BEDROCK || getBlock(down) == Blocks.OBSIDIAN) && getBlock(up) == Blocks.AIR && getBlock(up) == Blocks.AIR && BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(up)).isEmpty() && BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(up)).isEmpty();
    }
    
    public static boolean canBeClicked(final BlockPos blockPos) {
        return getBlock(blockPos).canCollideCheck(getState(blockPos), false);
    }
    
    public static List getPlacableFacings(final BlockPos p0, final boolean p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore_3       
        //     8: invokestatic    net/minecraft/util/EnumFacing.values:()[Lnet/minecraft/util/EnumFacing;
        //    11: astore          4
        //    13: aload           4
        //    15: arraylength    
        //    16: istore          5
        //    18: iconst_0       
        //    19: istore          6
        //    21: iload           6
        //    23: iload           5
        //    25: if_icmpge       57
        //    28: aload           4
        //    30: iload           6
        //    32: aaload         
        //    33: astore          7
        //    35: aload_0        
        //    36: aload           7
        //    38: ifeq            44
        //    41: goto            52
        //    44: aload_0        
        //    45: iload_1        
        //    46: aload_3        
        //    47: aload           7
        //    49: invokestatic    me/rebirthclient/api/util/BlockUtil.getPlaceFacing:(Lnet/minecraft/util/math/BlockPos;ZLjava/util/ArrayList;Lnet/minecraft/util/EnumFacing;)V
        //    52: iinc            6, 1
        //    55: aconst_null    
        //    56: areturn        
        //    57: invokestatic    net/minecraft/util/EnumFacing.values:()[Lnet/minecraft/util/EnumFacing;
        //    60: astore          4
        //    62: aload           4
        //    64: arraylength    
        //    65: istore          5
        //    67: iconst_0       
        //    68: istore          6
        //    70: iload           6
        //    72: iload           5
        //    74: if_icmpge       110
        //    77: aload           4
        //    79: iload           6
        //    81: aaload         
        //    82: astore          7
        //    84: iload_2        
        //    85: ifeq            97
        //    88: aload_0        
        //    89: aload           7
        //    91: ifeq            97
        //    94: goto            105
        //    97: aload_0        
        //    98: iload_1        
        //    99: aload_3        
        //   100: aload           7
        //   102: invokestatic    me/rebirthclient/api/util/BlockUtil.getPlaceFacing:(Lnet/minecraft/util/math/BlockPos;ZLjava/util/ArrayList;Lnet/minecraft/util/EnumFacing;)V
        //   105: iinc            6, 1
        //   108: aconst_null    
        //   109: areturn        
        //   110: aload_3        
        //   111: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0097 (coming from #0091).
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
    
    public static void rightClickBlock(final BlockPos blockPos, final Vec3d vec3d, final EnumHand enumHand, final EnumFacing enumFacing, final boolean b) {
        if (b) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, (float)(vec3d.x - blockPos.getX()), (float)(vec3d.y - blockPos.getY()), (float)(vec3d.z - blockPos.getZ())));
        }
        else {
            BlockUtil.mc.playerController.processRightClickBlock(BlockUtil.mc.player, BlockUtil.mc.world, blockPos, enumFacing, vec3d, enumHand);
        }
        BlockUtil.mc.player.swingArm(enumHand);
        BlockUtil.mc.rightClickDelayTimer = 4;
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos blockPos, final boolean b, final float n) {
        return b && BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)(blockPos.getY() + n), (double)blockPos.getZ()), false, true, false) != null;
    }
    
    public static boolean isFence(final Block block) {
        return block instanceof BlockFence || block instanceof BlockFenceGate;
    }
    
    static {
        canUseList = Arrays.asList(Blocks.ENDER_CHEST, (Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, (Block)Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER, Blocks.TRAPDOOR, Blocks.ENCHANTING_TABLE);
        shulkerList = Arrays.asList(Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX);
    }
    
    public static EnumFacing getFirstFacing(final BlockPos blockPos) {
        if (!(boolean)CombatSetting.INSTANCE.strictPlace.getValue()) {
            final Iterator<EnumFacing> iterator = getPossibleSides(blockPos).iterator();
            if (iterator.hasNext()) {
                return iterator.next();
            }
        }
        else {
            final Iterator<EnumFacing> iterator2 = getPlacableFacings(blockPos, true, (boolean)CombatSetting.INSTANCE.checkRaytrace.getValue()).iterator();
            if (iterator2.hasNext()) {
                final EnumFacing enumFacing = iterator2.next();
                if (!canClick(blockPos.offset(enumFacing))) {
                    return null;
                }
                return enumFacing;
            }
        }
        return null;
    }
    
    public static List getPossibleSides(final BlockPos blockPos) {
        final ArrayList<EnumFacing> list = new ArrayList<EnumFacing>();
        final EnumFacing[] values = EnumFacing.values();
        final int length = values.length;
        int n = 0;
        if (n < length) {
            final EnumFacing enumFacing = values[n];
            final BlockPos offset = blockPos.offset(enumFacing);
            if (getBlock(offset).canCollideCheck(getState(offset), false)) {
                if (!canReplace(offset)) {
                    list.add(enumFacing);
                }
            }
            ++n;
            return null;
        }
        return list;
    }
    
    public static boolean canPlaceEnum(final BlockPos blockPos) {
        return blockPos == 0 && strictPlaceCheck(blockPos);
    }
    
    public static BlockPos[] getHorizontalOffsets(final BlockPos blockPos) {
        return new BlockPos[] { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
    }
    
    public static int getPlaceAbility(final BlockPos p0, final boolean p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokestatic    me/rebirthclient/api/util/BlockUtil.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //     4: astore_3       
        //     5: aload_3        
        //     6: instanceof      Lnet/minecraft/block/BlockAir;
        //     9: ifne            49
        //    12: aload_3        
        //    13: instanceof      Lnet/minecraft/block/BlockLiquid;
        //    16: ifne            49
        //    19: aload_3        
        //    20: instanceof      Lnet/minecraft/block/BlockTallGrass;
        //    23: ifne            49
        //    26: aload_3        
        //    27: instanceof      Lnet/minecraft/block/BlockFire;
        //    30: ifne            49
        //    33: aload_3        
        //    34: instanceof      Lnet/minecraft/block/BlockDeadBush;
        //    37: ifne            49
        //    40: aload_3        
        //    41: instanceof      Lnet/minecraft/block/BlockSnow;
        //    44: ifne            49
        //    47: iconst_0       
        //    48: ireturn        
        //    49: iload_1        
        //    50: ifeq            60
        //    53: aload_0        
        //    54: fconst_0       
        //    55: ifeq            60
        //    58: iconst_m1      
        //    59: ireturn        
        //    60: iload_2        
        //    61: ifeq            70
        //    64: aload_0        
        //    65: ifeq            70
        //    68: iconst_1       
        //    69: ireturn        
        //    70: aload_0        
        //    71: invokestatic    me/rebirthclient/api/util/BlockUtil.getPossibleSides:(Lnet/minecraft/util/math/BlockPos;)Ljava/util/List;
        //    74: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    79: astore          4
        //    81: aload           4
        //    83: invokeinterface java/util/Iterator.hasNext:()Z
        //    88: ifeq            119
        //    91: aload           4
        //    93: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    98: checkcast       Lnet/minecraft/util/EnumFacing;
        //   101: astore          5
        //   103: aload_0        
        //   104: aload           5
        //   106: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //   109: invokestatic    me/rebirthclient/api/util/BlockUtil.canBeClicked:(Lnet/minecraft/util/math/BlockPos;)Z
        //   112: ifne            117
        //   115: iconst_0       
        //   116: ireturn        
        //   117: iconst_3       
        //   118: ireturn        
        //   119: iconst_2       
        //   120: ireturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0060 (coming from #0055).
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
    
    public static NonNullList getBox(final float n, final BlockPos blockPos) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)getSphere(blockPos, n, 0, false, true, 0));
        return create;
    }
    
    public static boolean canPlace3(final BlockPos blockPos) {
        return BlockUtil.mc.player.getDistance(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5) <= 6.0 && blockPos == 0 && canReplace(blockPos) && blockPos != 0 && blockPos != 0;
    }
    
    public static boolean isAir(final BlockPos blockPos) {
        return BlockUtil.mc.world.isAirBlock(blockPos);
    }
    
    public static boolean canPlaceCrystal(final BlockPos blockPos, final double n) {
        if (BlockUtil.mc.player.getDistance(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5) > n) {
            return false;
        }
        final BlockPos down = blockPos.down();
        final BlockPos up = down.up();
        final BlockPos up = down.up(2);
        return (getBlock(down) == Blocks.BEDROCK || getBlock(down) == Blocks.OBSIDIAN) && getBlock(up) == Blocks.AIR && getBlock(up) == Blocks.AIR && BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(up)).isEmpty() && BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(up)).isEmpty();
    }
    
    public static Vec3d blockPosToVec3(final BlockPos blockPos) {
        return new Vec3d((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ());
    }
    
    public static double distanceToXZ(final double n, final double n2) {
        final double n3 = BlockUtil.mc.player.posX - n;
        final double n4 = BlockUtil.mc.player.posZ - n2;
        return Math.sqrt(n3 * n3 + n4 * n4);
    }
    
    public static void placeCrystal(final BlockPos blockPos, final boolean b) {
        final boolean b2 = BlockUtil.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
        final BlockPos down = blockPos.down();
        final RayTraceResult rayTraceBlocks = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getY() - 0.5, blockPos.getZ() + 0.5));
        final EnumFacing enumFacing = (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) ? EnumFacing.UP : rayTraceBlocks.sideHit;
        final Vec3d add = new Vec3d((Vec3i)down).add(0.5, 0.5, 0.5).add(new Vec3d(enumFacing.getOpposite().getDirectionVec()));
        if (b) {
            EntityUtil.faceVector(add);
        }
        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(down, enumFacing, b2 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
        BlockUtil.mc.player.swingArm(b2 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
    }
    
    public static IBlockState getState(final BlockPos blockPos) {
        return BlockUtil.mc.world.getBlockState(blockPos);
    }
    
    public static int getPlaceAbility(final BlockPos blockPos, final boolean b) {
        return getPlaceAbility(blockPos, b, true);
    }
    
    public static boolean canPlace4(final BlockPos p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //     6: aload_0        
        //     7: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //    10: i2d            
        //    11: ldc2_w          0.5
        //    14: dadd           
        //    15: aload_0        
        //    16: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //    19: i2d            
        //    20: ldc2_w          0.5
        //    23: dadd           
        //    24: aload_0        
        //    25: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //    28: i2d            
        //    29: ldc2_w          0.5
        //    32: dadd           
        //    33: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(DDD)D
        //    36: ldc2_w          6.0
        //    39: dcmpl          
        //    40: ifle            45
        //    43: iconst_0       
        //    44: ireturn        
        //    45: aload_0        
        //    46: if_icmpge       51
        //    49: iconst_0       
        //    50: ireturn        
        //    51: aload_0        
        //    52: ifne            57
        //    55: iconst_0       
        //    56: ireturn        
        //    57: aload_0        
        //    58: ifeq            65
        //    61: iconst_1       
        //    62: goto            66
        //    65: iconst_0       
        //    66: ireturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static boolean canPlace2(final BlockPos blockPos, final double n) {
        return BlockUtil.mc.player.getDistance(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5) <= n && canReplace(blockPos) && blockPos != 0;
    }
    
    public static EnumFacing getRayTraceFacing(final BlockPos blockPos) {
        final RayTraceResult rayTraceBlocks = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5));
        if (rayTraceBlocks != null && rayTraceBlocks.sideHit != null) {
            return rayTraceBlocks.sideHit;
        }
        final EnumFacing enumFacing = null;
        final double n = 0.0;
        final EnumFacing[] values = EnumFacing.values();
        final int length = values.length;
        int n2 = 0;
        if (n2 < length) {
            final EnumFacing enumFacing2 = values[n2];
            if (enumFacing == null || BlockUtil.mc.player.getDistanceSq(blockPos.offset(enumFacing2)) < n) {
                BlockUtil.mc.player.getDistanceSq(blockPos.offset(enumFacing2));
            }
            ++n2;
            return null;
        }
        if (enumFacing == null) {
            return EnumFacing.UP;
        }
        return enumFacing;
    }
    
    public static boolean isStair(final Block block) {
        return block instanceof BlockStairs;
    }
    
    public static boolean isHole(final BlockPos blockPos) {
        final BlockPos[] horizontalOffsets = getHorizontalOffsets(blockPos);
        final int length = horizontalOffsets.length;
        int n = 0;
        if (n >= length) {
            return true;
        }
        final BlockPos blockPos2 = horizontalOffsets[n];
        if (getBlock(blockPos2) != Blocks.AIR && (getBlock(blockPos2) == Blocks.BEDROCK || getBlock(blockPos2) == Blocks.OBSIDIAN || getBlock(blockPos2) == Blocks.ENDER_CHEST)) {
            ++n;
            return false;
        }
        return false;
    }
    
    public static ArrayList checkAxis(final double n, final EnumFacing enumFacing, final EnumFacing enumFacing2, final boolean b) {
        final ArrayList<EnumFacing> list = new ArrayList<EnumFacing>();
        if (n < -0.5) {
            list.add(enumFacing);
        }
        if (n > 0.5) {
            list.add(enumFacing2);
        }
        if (b) {
            if (!list.contains(enumFacing)) {
                list.add(enumFacing);
            }
            if (!list.contains(enumFacing2)) {
                list.add(enumFacing2);
            }
        }
        return list;
    }
    
    public static boolean canReplace(final BlockPos blockPos) {
        return getState(blockPos).getMaterial().isReplaceable();
    }
    
    public static Block getBlock(final BlockPos blockPos) {
        return getState(blockPos).getBlock();
    }
    
    public static boolean isSafe(final Block block) {
        return !Arrays.asList(Blocks.OBSIDIAN, Blocks.BEDROCK, Blocks.ENDER_CHEST, Blocks.ANVIL).contains(block);
    }
    
    public static boolean canPlace(final BlockPos p0, final double p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //     6: aload_0        
        //     7: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //    10: i2d            
        //    11: ldc2_w          0.5
        //    14: dadd           
        //    15: aload_0        
        //    16: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //    19: i2d            
        //    20: ldc2_w          0.5
        //    23: dadd           
        //    24: aload_0        
        //    25: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //    28: i2d            
        //    29: ldc2_w          0.5
        //    32: dadd           
        //    33: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(DDD)D
        //    36: dload_1        
        //    37: dcmpl          
        //    38: ifle            43
        //    41: iconst_0       
        //    42: ireturn        
        //    43: aload_0        
        //    44: if_icmpge       49
        //    47: iconst_0       
        //    48: ireturn        
        //    49: aload_0        
        //    50: invokestatic    me/rebirthclient/api/util/BlockUtil.canReplace:(Lnet/minecraft/util/math/BlockPos;)Z
        //    53: ifne            58
        //    56: iconst_0       
        //    57: ireturn        
        //    58: aload_0        
        //    59: ifne            64
        //    62: iconst_0       
        //    63: ireturn        
        //    64: aload_0        
        //    65: ifeq            72
        //    68: iconst_1       
        //    69: goto            73
        //    72: iconst_0       
        //    73: ireturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static boolean canPlace(final BlockPos p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //     6: aload_0        
        //     7: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //    10: i2d            
        //    11: ldc2_w          0.5
        //    14: dadd           
        //    15: aload_0        
        //    16: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //    19: i2d            
        //    20: ldc2_w          0.5
        //    23: dadd           
        //    24: aload_0        
        //    25: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //    28: i2d            
        //    29: ldc2_w          0.5
        //    32: dadd           
        //    33: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(DDD)D
        //    36: ldc2_w          6.0
        //    39: dcmpl          
        //    40: ifle            45
        //    43: iconst_0       
        //    44: ireturn        
        //    45: aload_0        
        //    46: if_icmpge       51
        //    49: iconst_0       
        //    50: ireturn        
        //    51: aload_0        
        //    52: invokestatic    me/rebirthclient/api/util/BlockUtil.canReplace:(Lnet/minecraft/util/math/BlockPos;)Z
        //    55: ifne            60
        //    58: iconst_0       
        //    59: ireturn        
        //    60: aload_0        
        //    61: ifne            66
        //    64: iconst_0       
        //    65: ireturn        
        //    66: aload_0        
        //    67: ifeq            74
        //    70: iconst_1       
        //    71: goto            75
        //    74: iconst_0       
        //    75: ireturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static boolean canClick(final BlockPos blockPos) {
        return BlockUtil.mc.world.getBlockState(blockPos).getBlock().canCollideCheck(BlockUtil.mc.world.getBlockState(blockPos), false);
    }
    
    public static boolean isSlab(final Block block) {
        return block instanceof BlockSlab || block instanceof BlockCarpet || block instanceof BlockCake;
    }
    
    public static NonNullList getBox(final float n) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)getSphere(new BlockPos(Math.floor(BlockUtil.mc.player.posX), Math.floor(BlockUtil.mc.player.posY), Math.floor(BlockUtil.mc.player.posZ)), n, 0, false, true, 0));
        return create;
    }
    
    public static void placeBlock(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2) {
        final EnumFacing firstFacing = getFirstFacing(blockPos);
        if (firstFacing == null) {
            return;
        }
        final BlockPos offset = blockPos.offset(firstFacing);
        final EnumFacing getOpposite = firstFacing.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        final Block getBlock = BlockUtil.mc.world.getBlockState(offset).getBlock();
        boolean b3 = false;
        if (!SneakManager.isSneaking && (BlockUtil.canUseList.contains(getBlock) || BlockUtil.shulkerList.contains(getBlock))) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            b3 = true;
        }
        if (b) {
            EntityUtil.faceVector(add);
        }
        PlaceRender.PlaceMap.put(blockPos, new PlaceRender.placePosition(blockPos));
        rightClickBlock(offset, add, enumHand, getOpposite, b2);
        if (b3) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
    }
    
    public static EnumFacing getBestNeighboring(final BlockPos blockPos, final EnumFacing enumFacing) {
        final EnumFacing[] VALUES = EnumFacing.VALUES;
        final int length = VALUES.length;
        int n = 0;
        if (n < length) {
            final EnumFacing enumFacing2 = VALUES[n];
            if (enumFacing == null || !blockPos.offset(enumFacing2).equals((Object)blockPos.offset(enumFacing, -1))) {
                if (enumFacing2 != EnumFacing.DOWN) {
                    final Iterator iterator = getPlacableFacings(blockPos.offset(enumFacing2), true, true).iterator();
                    if (iterator.hasNext()) {
                        if (!canClick(blockPos.offset(enumFacing2).offset((EnumFacing)iterator.next()))) {
                            return null;
                        }
                        return enumFacing2;
                    }
                }
            }
            ++n;
            return null;
        }
        final Object o = null;
        final double n2 = 0.0;
        final EnumFacing[] VALUES2 = EnumFacing.VALUES;
        final int length2 = VALUES2.length;
        int n3 = 0;
        if (n3 < length2) {
            final EnumFacing enumFacing3 = VALUES2[n3];
            if (enumFacing == null || !blockPos.offset(enumFacing3).equals((Object)blockPos.offset(enumFacing, -1))) {
                if (enumFacing3 != EnumFacing.DOWN) {
                    final Iterator iterator2 = getPlacableFacings(blockPos.offset(enumFacing3), true, false).iterator();
                    if (iterator2.hasNext()) {
                        if (!canClick(blockPos.offset(enumFacing3).offset((EnumFacing)iterator2.next()))) {
                            return null;
                        }
                        if (o == null || BlockUtil.mc.player.getDistanceSq(blockPos.offset(enumFacing3)) < n2) {
                            BlockUtil.mc.player.getDistanceSq(blockPos.offset(enumFacing3));
                        }
                        return null;
                    }
                }
            }
            ++n3;
            return null;
        }
        return null;
    }
    
    public static boolean canPlaceShulker(final BlockPos blockPos) {
        if (BlockUtil.mc.player.getDistance(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5) > 6.0) {
            return false;
        }
        if (blockPos.down() != 0) {
            return false;
        }
        if (!canReplace(blockPos)) {
            return false;
        }
        if (blockPos != 0) {
            return false;
        }
        if (!(boolean)CombatSetting.INSTANCE.strictPlace.getValue()) {
            return true;
        }
        for (final EnumFacing enumFacing : getPlacableFacings(blockPos, true, (boolean)CombatSetting.INSTANCE.checkRaytrace.getValue())) {
            if (canClick(blockPos.offset(enumFacing))) {
                return enumFacing == EnumFacing.DOWN;
            }
        }
        return false;
    }
    
    public static void placeBlock(final BlockPos blockPos, final boolean b) {
        placeBlock(blockPos, EnumHand.MAIN_HAND, false, b);
    }
    
    public static List getSphere(final BlockPos blockPos, final float n, final int n2, final boolean b, final boolean b2, final int n3) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        int n4 = getX - (int)n;
        if (n4 > getX + n) {
            return list;
        }
        int n5 = getZ - (int)n;
        if (n5 > getZ + n) {
            ++n4;
            return null;
        }
        int n6 = b2 ? (getY - (int)n) : getY;
        if (n6 >= (b2 ? (getY + n) : ((float)(getY + n2)))) {
            ++n5;
            return null;
        }
        final double n7 = (getX - n4) * (getX - n4) + (getZ - n5) * (getZ - n5) + (b2 ? ((getY - n6) * (getY - n6)) : 0);
        if (n7 < n * n && (!b || n7 >= (n - 1.0f) * (n - 1.0f))) {
            list.add(new BlockPos(n4, n6 + n3, n5));
        }
        ++n6;
        return null;
    }
    
    public static void placeBlock(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        if (b3) {
            CombatUtil.attackCrystal(blockPos, b, b4);
        }
        placeBlock(blockPos, enumHand, b, b2);
    }
    
    private static void getPlaceFacing(final BlockPos blockPos, final boolean b, final ArrayList list, final EnumFacing enumFacing) {
        final BlockPos offset = blockPos.offset(enumFacing);
        if (b) {
            final Vec3d getPositionEyes = BlockUtil.mc.player.getPositionEyes(1.0f);
            final Vec3d vec3d = new Vec3d(offset.getX() + 0.5, offset.getY() + 0.5, offset.getZ() + 0.5);
            final IBlockState getBlockState = BlockUtil.mc.world.getBlockState(offset);
            final boolean b2 = getBlockState.getBlock() == Blocks.AIR || getBlockState.isFullBlock();
            final ArrayList list2 = new ArrayList();
            list2.addAll(checkAxis(getPositionEyes.x - vec3d.x, EnumFacing.WEST, EnumFacing.EAST, !b2));
            list2.addAll(checkAxis(getPositionEyes.y - vec3d.y, EnumFacing.DOWN, EnumFacing.UP, true));
            list2.addAll(checkAxis(getPositionEyes.z - vec3d.z, EnumFacing.NORTH, EnumFacing.SOUTH, !b2));
            if (!list2.contains(enumFacing.getOpposite())) {
                return;
            }
        }
        final IBlockState getBlockState2 = BlockUtil.mc.world.getBlockState(offset);
        if (!getBlockState2.getBlock().canCollideCheck(getBlockState2, false) || getBlockState2.getMaterial().isReplaceable()) {
            return;
        }
        list.add(enumFacing);
    }
    
    public static boolean posHasCrystal(final BlockPos blockPos) {
        final Iterator<Entity> iterator = BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos)).iterator();
        if (iterator.hasNext()) {
            final Entity entity = iterator.next();
            return entity instanceof EntityEnderCrystal && new BlockPos(entity.posX, entity.posY, entity.posZ).equals((Object)blockPos);
        }
        return false;
    }
    
    public static BlockPos getFlooredPosition(final Entity entity) {
        return new BlockPos(Math.floor(entity.posX), (double)Math.round(entity.posY), Math.floor(entity.posZ));
    }
    
    public static BlockPos vec3toBlockPos(final Vec3d vec3d) {
        return new BlockPos(Math.floor(vec3d.x), (double)Math.round(vec3d.y), Math.floor(vec3d.z));
    }
    
    public static boolean canPlace2(final BlockPos blockPos) {
        return BlockUtil.mc.player.getDistance(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5) <= 6.0 && canReplace(blockPos) && blockPos != 0;
    }
}
