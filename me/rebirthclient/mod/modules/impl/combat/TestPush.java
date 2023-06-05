//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import java.util.function.*;
import java.util.stream.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.init.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.block.*;
import java.util.*;
import me.rebirthclient.mod.modules.*;
import net.minecraft.block.state.*;

public class TestPush extends Module
{
    private final Timer timer;
    private final Setting placeRange;
    private final Setting checkCrystal;
    private final Setting pistonCheck;
    int progress;
    private final Setting maxTargetSpeed;
    private final Setting targetGround;
    private final Setting pistonPacket;
    private final Setting multiPlace;
    private final Setting pullBack;
    private final Setting breakCrystal;
    private EntityPlayer displayTarget;
    private final Setting selfGround;
    private final Setting powerPacket;
    private final Setting maxSelfSpeed;
    public final Setting surroundCheck;
    public static final List canPushBlock2;
    private final Setting updateDelay;
    private final Setting onlyBurrow;
    private final Setting range;
    private final Setting noEating;
    public static final List canPushBlock;
    private final Setting minePower;
    private final Setting autoDisable;
    
    private boolean lambda$new$0(final Boolean b) {
        return this.pullBack.isOpen();
    }
    
    private Boolean canPush(final EntityPlayer entityPlayer) {
        int n = 0;
        if (!TestPush.mc.world.isAirBlock(new BlockPos(entityPlayer.posX, entityPlayer.posY + 0.5, entityPlayer.posZ))) {
            return true;
        }
        if (!TestPush.mc.world.isAirBlock(new BlockPos(entityPlayer.posX + 1.0, entityPlayer.posY + 0.5, entityPlayer.posZ))) {
            ++n;
        }
        if (!TestPush.mc.world.isAirBlock(new BlockPos(entityPlayer.posX - 1.0, entityPlayer.posY + 0.5, entityPlayer.posZ))) {
            ++n;
        }
        if (!TestPush.mc.world.isAirBlock(new BlockPos(entityPlayer.posX, entityPlayer.posY + 0.5, entityPlayer.posZ + 1.0))) {
            ++n;
        }
        if (!TestPush.mc.world.isAirBlock(new BlockPos(entityPlayer.posX, entityPlayer.posY + 0.5, entityPlayer.posZ - 1.0))) {
            ++n;
        }
        return n > (int)this.surroundCheck.getValue() - 1;
    }
    
    @Override
    public String getInfo() {
        if (this.displayTarget != null) {
            return this.displayTarget.getName();
        }
        return null;
    }
    
    public boolean attackCrystal(final BlockPos blockPos) {
        for (final Entity entity : (List)TestPush.mc.world.loadedEntityList.stream().filter(TestPush::lambda$attackCrystal$1).sorted(Comparator.comparing((Function<? super T, ? extends Comparable>)TestPush::lambda$attackCrystal$2)).collect(Collectors.toList())) {
            if (entity instanceof EntityEnderCrystal) {
                if (entity.getDistance((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()) >= (this.checkCrystal.getValue() ? 4 : 2)) {
                    return false;
                }
                CombatUtil.attackCrystal(entity, true, true);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void onUpdate() {
        this.progress = 0;
        this.displayTarget = null;
        if (InventoryUtil.findHotbarBlock(Blocks.REDSTONE_BLOCK) == -1 || InventoryUtil.findHotbarClass((Class)BlockPistonBase.class) == -1) {
            if (this.autoDisable.getValue()) {
                this.disable();
            }
            return;
        }
        if (!this.timer.passedMs((long)(int)this.updateDelay.getValue())) {
            return;
        }
        if ((boolean)this.noEating.getValue() && EntityUtil.isEating()) {
            return;
        }
        if (Managers.SPEED.getPlayerSpeed((EntityPlayer)TestPush.mc.player) > (double)this.maxSelfSpeed.getValue()) {
            return;
        }
        if ((boolean)this.selfGround.getValue() && !TestPush.mc.player.onGround) {
            return;
        }
        for (final EntityPlayer displayTarget : TestPush.mc.world.playerEntities) {
            if (this.canPush(displayTarget) && EntityUtil.isValid((Entity)displayTarget, (double)this.range.getValue()) && (displayTarget.onGround || !(boolean)this.targetGround.getValue())) {
                if (Managers.SPEED.getPlayerSpeed(displayTarget) > (double)this.maxTargetSpeed.getValue()) {
                    return;
                }
                if (this.progress >= (int)this.multiPlace.getValue()) {
                    return;
                }
                this.doPush(this.displayTarget = displayTarget);
                return;
            }
        }
        if ((this.displayTarget == null || this.progress == 0) && (boolean)this.autoDisable.getValue()) {
            this.disable();
        }
    }
    
    private static boolean lambda$attackCrystal$1(final Entity entity) {
        return entity instanceof EntityEnderCrystal && !entity.isDead;
    }
    
    private void doPush(final EntityPlayer p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_1        
        //     5: getfield        net/minecraft/entity/player/EntityPlayer.posX:D
        //     8: aload_1        
        //     9: getfield        net/minecraft/entity/player/EntityPlayer.posY:D
        //    12: ldc2_w          0.5
        //    15: dadd           
        //    16: aload_1        
        //    17: getfield        net/minecraft/entity/player/EntityPlayer.posZ:D
        //    20: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //    23: astore_2       
        //    24: aload_0        
        //    25: aload_2        
        //    26: iconst_0       
        //    27: iconst_2       
        //    28: iconst_0       
        //    29: invokevirtual   net/minecraft/util/math/BlockPos.add:(III)Lnet/minecraft/util/math/BlockPos;
        //    32: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //    35: getstatic       net/minecraft/init/Blocks.AIR:Lnet/minecraft/block/Block;
        //    38: if_acmpne       1150
        //    41: getstatic       net/minecraft/util/EnumFacing.VALUES:[Lnet/minecraft/util/EnumFacing;
        //    44: astore_3       
        //    45: aload_3        
        //    46: arraylength    
        //    47: istore          4
        //    49: iconst_0       
        //    50: istore          5
        //    52: iload           5
        //    54: iload           4
        //    56: if_icmpge       221
        //    59: aload_3        
        //    60: iload           5
        //    62: aaload         
        //    63: astore          6
        //    65: aload           6
        //    67: getstatic       net/minecraft/util/EnumFacing.DOWN:Lnet/minecraft/util/EnumFacing;
        //    70: if_acmpeq       217
        //    73: aload           6
        //    75: getstatic       net/minecraft/util/EnumFacing.UP:Lnet/minecraft/util/EnumFacing;
        //    78: if_acmpne       84
        //    81: goto            217
        //    84: aload_2        
        //    85: aload           6
        //    87: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //    90: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //    93: astore          7
        //    95: aload_0        
        //    96: aload           7
        //    98: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //   101: instanceof      Lnet/minecraft/block/BlockPistonBase;
        //   104: ifeq            217
        //   107: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.canPushBlock:Ljava/util/List;
        //   110: aload_0        
        //   111: aload           7
        //   113: aload           6
        //   115: bipush          -2
        //   117: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;I)Lnet/minecraft/util/math/BlockPos;
        //   120: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //   123: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   128: ifeq            217
        //   131: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.canPushBlock:Ljava/util/List;
        //   134: aload_0        
        //   135: aload           7
        //   137: aload           6
        //   139: bipush          -2
        //   141: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;I)Lnet/minecraft/util/math/BlockPos;
        //   144: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //   147: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //   150: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   155: ifeq            217
        //   158: aload_0        
        //   159: aload           7
        //   161: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   164: getstatic       net/minecraft/block/BlockDirectional.FACING:Lnet/minecraft/block/properties/PropertyDirection;
        //   167: invokeinterface net/minecraft/block/state/IBlockState.getValue:(Lnet/minecraft/block/properties/IProperty;)Ljava/lang/Comparable;
        //   172: checkcast       Lnet/minecraft/util/EnumFacing;
        //   175: invokevirtual   net/minecraft/util/EnumFacing.getOpposite:()Lnet/minecraft/util/EnumFacing;
        //   178: aload           6
        //   180: if_acmpeq       186
        //   183: goto            217
        //   186: aload_0        
        //   187: getfield        me/rebirthclient/mod/modules/impl/combat/TestPush.breakCrystal:Lme/rebirthclient/mod/modules/settings/Setting;
        //   190: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   193: checkcast       Ljava/lang/Boolean;
        //   196: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   199: ifeq            209
        //   202: aload_0        
        //   203: aload           7
        //   205: invokevirtual   me/rebirthclient/mod/modules/impl/combat/TestPush.attackCrystal:(Lnet/minecraft/util/math/BlockPos;)Z
        //   208: pop            
        //   209: aload_0        
        //   210: aload           7
        //   212: iconst_1       
        //   213: if_icmplt       217
        //   216: return         
        //   217: iinc            5, 1
        //   220: return         
        //   221: getstatic       net/minecraft/util/EnumFacing.VALUES:[Lnet/minecraft/util/EnumFacing;
        //   224: astore_3       
        //   225: aload_3        
        //   226: arraylength    
        //   227: istore          4
        //   229: iconst_0       
        //   230: istore          5
        //   232: iload           5
        //   234: iload           4
        //   236: if_icmpge       480
        //   239: aload_3        
        //   240: iload           5
        //   242: aaload         
        //   243: astore          6
        //   245: aload           6
        //   247: getstatic       net/minecraft/util/EnumFacing.DOWN:Lnet/minecraft/util/EnumFacing;
        //   250: if_acmpeq       476
        //   253: aload           6
        //   255: getstatic       net/minecraft/util/EnumFacing.UP:Lnet/minecraft/util/EnumFacing;
        //   258: if_acmpne       264
        //   261: goto            476
        //   264: aload_2        
        //   265: aload           6
        //   267: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //   270: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //   273: astore          7
        //   275: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.mc:Lnet/minecraft/client/Minecraft;
        //   278: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   281: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   284: aload_2        
        //   285: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //   288: i2d            
        //   289: dsub           
        //   290: ldc2_w          -1.0
        //   293: dcmpg          
        //   294: ifle            319
        //   297: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.mc:Lnet/minecraft/client/Minecraft;
        //   300: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   303: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   306: aload_2        
        //   307: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //   310: i2d            
        //   311: dsub           
        //   312: ldc2_w          2.0
        //   315: dcmpl          
        //   316: iflt            362
        //   319: aload           7
        //   321: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //   324: i2d            
        //   325: ldc2_w          0.5
        //   328: dadd           
        //   329: aload           7
        //   331: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //   334: i2d            
        //   335: ldc2_w          0.5
        //   338: dadd           
        //   339: invokestatic    me/rebirthclient/api/util/BlockUtil.distanceToXZ:(DD)D
        //   342: aload_0        
        //   343: getfield        me/rebirthclient/mod/modules/impl/combat/TestPush.pistonCheck:Lme/rebirthclient/mod/modules/settings/Setting;
        //   346: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   349: checkcast       Ljava/lang/Double;
        //   352: invokevirtual   java/lang/Double.doubleValue:()D
        //   355: dcmpg          
        //   356: ifge            362
        //   359: goto            476
        //   362: aload           7
        //   364: aload_0        
        //   365: getfield        me/rebirthclient/mod/modules/impl/combat/TestPush.placeRange:Lme/rebirthclient/mod/modules/settings/Setting;
        //   368: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   371: checkcast       Ljava/lang/Double;
        //   374: invokevirtual   java/lang/Double.doubleValue:()D
        //   377: invokestatic    me/rebirthclient/api/util/BlockUtil.canPlace2:(Lnet/minecraft/util/math/BlockPos;D)Z
        //   380: ifeq            476
        //   383: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.canPushBlock:Ljava/util/List;
        //   386: aload_0        
        //   387: aload           7
        //   389: aload           6
        //   391: bipush          -2
        //   393: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;I)Lnet/minecraft/util/math/BlockPos;
        //   396: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //   399: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   404: ifeq            476
        //   407: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.canPushBlock:Ljava/util/List;
        //   410: aload_0        
        //   411: aload           7
        //   413: aload           6
        //   415: bipush          -2
        //   417: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;I)Lnet/minecraft/util/math/BlockPos;
        //   420: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //   423: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //   426: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   431: ifeq            476
        //   434: aload_0        
        //   435: getfield        me/rebirthclient/mod/modules/impl/combat/TestPush.breakCrystal:Lme/rebirthclient/mod/modules/settings/Setting;
        //   438: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   441: checkcast       Ljava/lang/Boolean;
        //   444: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   447: ifeq            457
        //   450: aload_0        
        //   451: aload           7
        //   453: invokevirtual   me/rebirthclient/mod/modules/impl/combat/TestPush.attackCrystal:(Lnet/minecraft/util/math/BlockPos;)Z
        //   456: pop            
        //   457: aload_0        
        //   458: aload           7
        //   460: ifeq            466
        //   463: goto            476
        //   466: aload_0        
        //   467: aload           6
        //   469: aload           7
        //   471: ifeq            475
        //   474: return         
        //   475: return         
        //   476: iinc            5, 1
        //   479: return         
        //   480: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.canPushBlock:Ljava/util/List;
        //   483: aload_0        
        //   484: aload_2        
        //   485: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //   488: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   493: ifeq            512
        //   496: aload_0        
        //   497: getfield        me/rebirthclient/mod/modules/impl/combat/TestPush.onlyBurrow:Lme/rebirthclient/mod/modules/settings/Setting;
        //   500: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   503: checkcast       Ljava/lang/Boolean;
        //   506: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   509: ifne            528
        //   512: aload_0        
        //   513: getfield        me/rebirthclient/mod/modules/impl/combat/TestPush.pullBack:Lme/rebirthclient/mod/modules/settings/Setting;
        //   516: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   519: checkcast       Ljava/lang/Boolean;
        //   522: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   525: ifne            529
        //   528: return         
        //   529: getstatic       net/minecraft/util/EnumFacing.VALUES:[Lnet/minecraft/util/EnumFacing;
        //   532: astore_3       
        //   533: aload_3        
        //   534: arraylength    
        //   535: istore          4
        //   537: iconst_0       
        //   538: istore          5
        //   540: iload           5
        //   542: iload           4
        //   544: if_icmpge       741
        //   547: aload_3        
        //   548: iload           5
        //   550: aaload         
        //   551: astore          6
        //   553: aload           6
        //   555: getstatic       net/minecraft/util/EnumFacing.DOWN:Lnet/minecraft/util/EnumFacing;
        //   558: if_acmpeq       737
        //   561: aload           6
        //   563: getstatic       net/minecraft/util/EnumFacing.UP:Lnet/minecraft/util/EnumFacing;
        //   566: if_acmpne       572
        //   569: goto            737
        //   572: aload_2        
        //   573: aload           6
        //   575: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //   578: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //   581: astore          7
        //   583: aload_0        
        //   584: aload           7
        //   586: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //   589: instanceof      Lnet/minecraft/block/BlockPistonBase;
        //   592: ifeq            737
        //   595: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.canPushBlock:Ljava/util/List;
        //   598: aload_0        
        //   599: aload           7
        //   601: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //   604: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //   607: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   612: ifne            630
        //   615: aload_0        
        //   616: aload           7
        //   618: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //   621: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //   624: getstatic       net/minecraft/init/Blocks.REDSTONE_BLOCK:Lnet/minecraft/block/Block;
        //   627: if_acmpne       737
        //   630: aload_0        
        //   631: aload           7
        //   633: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   636: getstatic       net/minecraft/block/BlockDirectional.FACING:Lnet/minecraft/block/properties/PropertyDirection;
        //   639: invokeinterface net/minecraft/block/state/IBlockState.getValue:(Lnet/minecraft/block/properties/IProperty;)Ljava/lang/Comparable;
        //   644: checkcast       Lnet/minecraft/util/EnumFacing;
        //   647: invokevirtual   net/minecraft/util/EnumFacing.getOpposite:()Lnet/minecraft/util/EnumFacing;
        //   650: aload           6
        //   652: if_acmpeq       658
        //   655: goto            737
        //   658: getstatic       net/minecraft/util/EnumFacing.VALUES:[Lnet/minecraft/util/EnumFacing;
        //   661: astore          8
        //   663: aload           8
        //   665: arraylength    
        //   666: istore          9
        //   668: iconst_0       
        //   669: istore          10
        //   671: iload           10
        //   673: iload           9
        //   675: if_icmpge       737
        //   678: aload           8
        //   680: iload           10
        //   682: aaload         
        //   683: astore          11
        //   685: aload_0        
        //   686: aload           7
        //   688: aload           11
        //   690: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //   693: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //   696: getstatic       net/minecraft/init/Blocks.REDSTONE_BLOCK:Lnet/minecraft/block/Block;
        //   699: if_acmpne       733
        //   702: aload           7
        //   704: aload           11
        //   706: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //   709: invokestatic    me/rebirthclient/api/util/CombatUtil.mineBlock:(Lnet/minecraft/util/math/BlockPos;)V
        //   712: aload_0        
        //   713: getfield        me/rebirthclient/mod/modules/impl/combat/TestPush.autoDisable:Lme/rebirthclient/mod/modules/settings/Setting;
        //   716: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   719: checkcast       Ljava/lang/Boolean;
        //   722: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   725: ifeq            732
        //   728: aload_0        
        //   729: invokevirtual   me/rebirthclient/mod/modules/impl/combat/TestPush.disable:()V
        //   732: return         
        //   733: iinc            10, 1
        //   736: return         
        //   737: iinc            5, 1
        //   740: return         
        //   741: getstatic       net/minecraft/util/EnumFacing.VALUES:[Lnet/minecraft/util/EnumFacing;
        //   744: astore_3       
        //   745: aload_3        
        //   746: arraylength    
        //   747: istore          4
        //   749: iconst_0       
        //   750: istore          5
        //   752: iload           5
        //   754: iload           4
        //   756: if_icmpge       905
        //   759: aload_3        
        //   760: iload           5
        //   762: aaload         
        //   763: astore          6
        //   765: aload           6
        //   767: getstatic       net/minecraft/util/EnumFacing.DOWN:Lnet/minecraft/util/EnumFacing;
        //   770: if_acmpeq       901
        //   773: aload           6
        //   775: getstatic       net/minecraft/util/EnumFacing.UP:Lnet/minecraft/util/EnumFacing;
        //   778: if_acmpne       784
        //   781: goto            901
        //   784: aload_2        
        //   785: aload           6
        //   787: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //   790: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //   793: astore          7
        //   795: aload_0        
        //   796: aload           7
        //   798: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //   801: instanceof      Lnet/minecraft/block/BlockPistonBase;
        //   804: ifeq            901
        //   807: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.canPushBlock:Ljava/util/List;
        //   810: aload_0        
        //   811: aload           7
        //   813: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //   816: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //   819: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   824: ifne            842
        //   827: aload_0        
        //   828: aload           7
        //   830: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //   833: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //   836: getstatic       net/minecraft/init/Blocks.REDSTONE_BLOCK:Lnet/minecraft/block/Block;
        //   839: if_acmpne       901
        //   842: aload_0        
        //   843: aload           7
        //   845: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   848: getstatic       net/minecraft/block/BlockDirectional.FACING:Lnet/minecraft/block/properties/PropertyDirection;
        //   851: invokeinterface net/minecraft/block/state/IBlockState.getValue:(Lnet/minecraft/block/properties/IProperty;)Ljava/lang/Comparable;
        //   856: checkcast       Lnet/minecraft/util/EnumFacing;
        //   859: invokevirtual   net/minecraft/util/EnumFacing.getOpposite:()Lnet/minecraft/util/EnumFacing;
        //   862: aload           6
        //   864: if_acmpeq       870
        //   867: goto            901
        //   870: aload_0        
        //   871: getfield        me/rebirthclient/mod/modules/impl/combat/TestPush.breakCrystal:Lme/rebirthclient/mod/modules/settings/Setting;
        //   874: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   877: checkcast       Ljava/lang/Boolean;
        //   880: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   883: ifeq            893
        //   886: aload_0        
        //   887: aload           7
        //   889: invokevirtual   me/rebirthclient/mod/modules/impl/combat/TestPush.attackCrystal:(Lnet/minecraft/util/math/BlockPos;)Z
        //   892: pop            
        //   893: aload_0        
        //   894: aload           7
        //   896: iconst_0       
        //   897: if_icmplt       901
        //   900: return         
        //   901: iinc            5, 1
        //   904: return         
        //   905: getstatic       net/minecraft/util/EnumFacing.VALUES:[Lnet/minecraft/util/EnumFacing;
        //   908: astore_3       
        //   909: aload_3        
        //   910: arraylength    
        //   911: istore          4
        //   913: iconst_0       
        //   914: istore          5
        //   916: iload           5
        //   918: iload           4
        //   920: if_icmpge       1147
        //   923: aload_3        
        //   924: iload           5
        //   926: aaload         
        //   927: astore          6
        //   929: aload           6
        //   931: getstatic       net/minecraft/util/EnumFacing.DOWN:Lnet/minecraft/util/EnumFacing;
        //   934: if_acmpeq       1143
        //   937: aload           6
        //   939: getstatic       net/minecraft/util/EnumFacing.UP:Lnet/minecraft/util/EnumFacing;
        //   942: if_acmpne       948
        //   945: goto            1143
        //   948: aload_2        
        //   949: aload           6
        //   951: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //   954: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //   957: astore          7
        //   959: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.mc:Lnet/minecraft/client/Minecraft;
        //   962: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   965: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   968: aload_2        
        //   969: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //   972: i2d            
        //   973: dsub           
        //   974: ldc2_w          -1.0
        //   977: dcmpg          
        //   978: ifle            1003
        //   981: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.mc:Lnet/minecraft/client/Minecraft;
        //   984: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   987: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //   990: aload_2        
        //   991: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //   994: i2d            
        //   995: dsub           
        //   996: ldc2_w          2.0
        //   999: dcmpl          
        //  1000: iflt            1046
        //  1003: aload           7
        //  1005: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //  1008: i2d            
        //  1009: ldc2_w          0.5
        //  1012: dadd           
        //  1013: aload           7
        //  1015: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //  1018: i2d            
        //  1019: ldc2_w          0.5
        //  1022: dadd           
        //  1023: invokestatic    me/rebirthclient/api/util/BlockUtil.distanceToXZ:(DD)D
        //  1026: aload_0        
        //  1027: getfield        me/rebirthclient/mod/modules/impl/combat/TestPush.pistonCheck:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1030: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1033: checkcast       Ljava/lang/Double;
        //  1036: invokevirtual   java/lang/Double.doubleValue:()D
        //  1039: dcmpg          
        //  1040: ifge            1046
        //  1043: goto            1143
        //  1046: aload           7
        //  1048: aload_0        
        //  1049: getfield        me/rebirthclient/mod/modules/impl/combat/TestPush.placeRange:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1052: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1055: checkcast       Ljava/lang/Double;
        //  1058: invokevirtual   java/lang/Double.doubleValue:()D
        //  1061: invokestatic    me/rebirthclient/api/util/BlockUtil.canPlace2:(Lnet/minecraft/util/math/BlockPos;D)Z
        //  1064: ifeq            1143
        //  1067: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.canPushBlock:Ljava/util/List;
        //  1070: aload_0        
        //  1071: aload           7
        //  1073: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //  1076: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //  1079: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //  1084: ifne            1102
        //  1087: aload_0        
        //  1088: aload           7
        //  1090: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //  1093: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //  1096: getstatic       net/minecraft/init/Blocks.REDSTONE_BLOCK:Lnet/minecraft/block/Block;
        //  1099: if_acmpne       1143
        //  1102: aload_0        
        //  1103: getfield        me/rebirthclient/mod/modules/impl/combat/TestPush.breakCrystal:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1106: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1109: checkcast       Ljava/lang/Boolean;
        //  1112: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1115: ifeq            1125
        //  1118: aload_0        
        //  1119: aload           7
        //  1121: invokevirtual   me/rebirthclient/mod/modules/impl/combat/TestPush.attackCrystal:(Lnet/minecraft/util/math/BlockPos;)Z
        //  1124: pop            
        //  1125: aload_0        
        //  1126: aload           7
        //  1128: ifeq            1134
        //  1131: goto            1143
        //  1134: aload_0        
        //  1135: aload           6
        //  1137: aload           7
        //  1139: ifeq            1143
        //  1142: return         
        //  1143: iinc            5, 1
        //  1146: return         
        //  1147: goto            1588
        //  1150: getstatic       net/minecraft/util/EnumFacing.VALUES:[Lnet/minecraft/util/EnumFacing;
        //  1153: astore_3       
        //  1154: aload_3        
        //  1155: arraylength    
        //  1156: istore          4
        //  1158: iconst_0       
        //  1159: istore          5
        //  1161: iload           5
        //  1163: iload           4
        //  1165: if_icmpge       1330
        //  1168: aload_3        
        //  1169: iload           5
        //  1171: aaload         
        //  1172: astore          6
        //  1174: aload           6
        //  1176: getstatic       net/minecraft/util/EnumFacing.DOWN:Lnet/minecraft/util/EnumFacing;
        //  1179: if_acmpeq       1326
        //  1182: aload           6
        //  1184: getstatic       net/minecraft/util/EnumFacing.UP:Lnet/minecraft/util/EnumFacing;
        //  1187: if_acmpne       1193
        //  1190: goto            1326
        //  1193: aload_2        
        //  1194: aload           6
        //  1196: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //  1199: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //  1202: astore          7
        //  1204: aload_0        
        //  1205: aload           7
        //  1207: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //  1210: instanceof      Lnet/minecraft/block/BlockPistonBase;
        //  1213: ifeq            1326
        //  1216: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.canPushBlock2:Ljava/util/List;
        //  1219: aload_0        
        //  1220: aload           7
        //  1222: aload           6
        //  1224: bipush          -2
        //  1226: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;I)Lnet/minecraft/util/math/BlockPos;
        //  1229: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //  1232: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //  1237: ifeq            1326
        //  1240: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.canPushBlock2:Ljava/util/List;
        //  1243: aload_0        
        //  1244: aload           7
        //  1246: aload           6
        //  1248: bipush          -2
        //  1250: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;I)Lnet/minecraft/util/math/BlockPos;
        //  1253: invokevirtual   net/minecraft/util/math/BlockPos.down:()Lnet/minecraft/util/math/BlockPos;
        //  1256: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //  1259: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //  1264: ifeq            1326
        //  1267: aload_0        
        //  1268: aload           7
        //  1270: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //  1273: getstatic       net/minecraft/block/BlockDirectional.FACING:Lnet/minecraft/block/properties/PropertyDirection;
        //  1276: invokeinterface net/minecraft/block/state/IBlockState.getValue:(Lnet/minecraft/block/properties/IProperty;)Ljava/lang/Comparable;
        //  1281: checkcast       Lnet/minecraft/util/EnumFacing;
        //  1284: invokevirtual   net/minecraft/util/EnumFacing.getOpposite:()Lnet/minecraft/util/EnumFacing;
        //  1287: aload           6
        //  1289: if_acmpeq       1295
        //  1292: goto            1326
        //  1295: aload_0        
        //  1296: getfield        me/rebirthclient/mod/modules/impl/combat/TestPush.breakCrystal:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1299: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1302: checkcast       Ljava/lang/Boolean;
        //  1305: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1308: ifeq            1318
        //  1311: aload_0        
        //  1312: aload           7
        //  1314: invokevirtual   me/rebirthclient/mod/modules/impl/combat/TestPush.attackCrystal:(Lnet/minecraft/util/math/BlockPos;)Z
        //  1317: pop            
        //  1318: aload_0        
        //  1319: aload           7
        //  1321: iconst_1       
        //  1322: if_icmplt       1326
        //  1325: return         
        //  1326: iinc            5, 1
        //  1329: return         
        //  1330: getstatic       net/minecraft/util/EnumFacing.VALUES:[Lnet/minecraft/util/EnumFacing;
        //  1333: astore_3       
        //  1334: aload_3        
        //  1335: arraylength    
        //  1336: istore          4
        //  1338: iconst_0       
        //  1339: istore          5
        //  1341: iload           5
        //  1343: iload           4
        //  1345: if_icmpge       1588
        //  1348: aload_3        
        //  1349: iload           5
        //  1351: aaload         
        //  1352: astore          6
        //  1354: aload           6
        //  1356: getstatic       net/minecraft/util/EnumFacing.DOWN:Lnet/minecraft/util/EnumFacing;
        //  1359: if_acmpeq       1584
        //  1362: aload           6
        //  1364: getstatic       net/minecraft/util/EnumFacing.UP:Lnet/minecraft/util/EnumFacing;
        //  1367: if_acmpne       1373
        //  1370: goto            1584
        //  1373: aload_2        
        //  1374: aload           6
        //  1376: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/math/BlockPos;
        //  1379: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //  1382: astore          7
        //  1384: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.mc:Lnet/minecraft/client/Minecraft;
        //  1387: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1390: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //  1393: aload_2        
        //  1394: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //  1397: i2d            
        //  1398: dsub           
        //  1399: ldc2_w          -1.0
        //  1402: dcmpg          
        //  1403: ifle            1428
        //  1406: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.mc:Lnet/minecraft/client/Minecraft;
        //  1409: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //  1412: getfield        net/minecraft/client/entity/EntityPlayerSP.posY:D
        //  1415: aload_2        
        //  1416: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //  1419: i2d            
        //  1420: dsub           
        //  1421: ldc2_w          2.0
        //  1424: dcmpl          
        //  1425: iflt            1471
        //  1428: aload           7
        //  1430: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //  1433: i2d            
        //  1434: ldc2_w          0.5
        //  1437: dadd           
        //  1438: aload           7
        //  1440: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //  1443: i2d            
        //  1444: ldc2_w          0.5
        //  1447: dadd           
        //  1448: invokestatic    me/rebirthclient/api/util/BlockUtil.distanceToXZ:(DD)D
        //  1451: aload_0        
        //  1452: getfield        me/rebirthclient/mod/modules/impl/combat/TestPush.pistonCheck:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1455: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1458: checkcast       Ljava/lang/Double;
        //  1461: invokevirtual   java/lang/Double.doubleValue:()D
        //  1464: dcmpg          
        //  1465: ifge            1471
        //  1468: goto            1584
        //  1471: aload           7
        //  1473: aload_0        
        //  1474: getfield        me/rebirthclient/mod/modules/impl/combat/TestPush.placeRange:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1477: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1480: checkcast       Ljava/lang/Double;
        //  1483: invokevirtual   java/lang/Double.doubleValue:()D
        //  1486: invokestatic    me/rebirthclient/api/util/BlockUtil.canPlace2:(Lnet/minecraft/util/math/BlockPos;D)Z
        //  1489: ifeq            1584
        //  1492: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.canPushBlock2:Ljava/util/List;
        //  1495: aload_0        
        //  1496: aload           7
        //  1498: aload           6
        //  1500: bipush          -2
        //  1502: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;I)Lnet/minecraft/util/math/BlockPos;
        //  1505: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //  1508: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //  1513: ifeq            1584
        //  1516: getstatic       me/rebirthclient/mod/modules/impl/combat/TestPush.canPushBlock2:Ljava/util/List;
        //  1519: aload_0        
        //  1520: aload           7
        //  1522: aload           6
        //  1524: bipush          -2
        //  1526: invokevirtual   net/minecraft/util/math/BlockPos.offset:(Lnet/minecraft/util/EnumFacing;I)Lnet/minecraft/util/math/BlockPos;
        //  1529: invokevirtual   net/minecraft/util/math/BlockPos.down:()Lnet/minecraft/util/math/BlockPos;
        //  1532: invokespecial   me/rebirthclient/mod/modules/impl/combat/TestPush.getBlock:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/Block;
        //  1535: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //  1540: ifeq            1584
        //  1543: aload_0        
        //  1544: getfield        me/rebirthclient/mod/modules/impl/combat/TestPush.breakCrystal:Lme/rebirthclient/mod/modules/settings/Setting;
        //  1547: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //  1550: checkcast       Ljava/lang/Boolean;
        //  1553: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1556: ifeq            1566
        //  1559: aload_0        
        //  1560: aload           7
        //  1562: invokevirtual   me/rebirthclient/mod/modules/impl/combat/TestPush.attackCrystal:(Lnet/minecraft/util/math/BlockPos;)Z
        //  1565: pop            
        //  1566: aload_0        
        //  1567: aload           7
        //  1569: ifeq            1575
        //  1572: goto            1584
        //  1575: aload_0        
        //  1576: aload           6
        //  1578: aload           7
        //  1580: ifeq            1584
        //  1583: return         
        //  1584: iinc            5, 1
        //  1587: return         
        //  1588: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #1584 (coming from #1580).
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
    
    private static Float lambda$attackCrystal$2(final Entity entity) {
        return TestPush.mc.player.getDistance(entity);
    }
    
    private Block getBlock(final BlockPos blockPos) {
        return TestPush.mc.world.getBlockState(blockPos).getBlock();
    }
    
    static {
        canPushBlock = Arrays.asList(Blocks.AIR, Blocks.ENDER_CHEST, Blocks.STANDING_SIGN, Blocks.WALL_SIGN, (Block)Blocks.REDSTONE_WIRE, Blocks.TRIPWIRE);
        canPushBlock2 = Arrays.asList(Blocks.AIR, Blocks.STANDING_SIGN, Blocks.WALL_SIGN, (Block)Blocks.REDSTONE_WIRE, Blocks.TRIPWIRE);
    }
    
    public TestPush() {
        super("TestPush", "2b2t.xin", Category.COMBAT);
        this.surroundCheck = this.add(new Setting("SurroundCheck", 2, 0, 4));
        this.updateDelay = this.add(new Setting("PlaceDelay", 100, 0, 500));
        this.range = this.add(new Setting("TargetRange", 6.0, 0.0, 8.0));
        this.placeRange = this.add(new Setting("PlaceRange", 6.0, 0.0, 8.0));
        this.pistonCheck = this.add(new Setting("AntiDisturb", 2.6, 0.0, 8.0));
        this.autoDisable = this.add(new Setting("AutoDisable", true));
        this.noEating = this.add(new Setting("NoEating", true));
        this.minePower = this.add(new Setting("MinePower", true));
        this.pullBack = this.add(new Setting("PullBack", true).setParent());
        this.onlyBurrow = this.add(new Setting("OnlyBurrow", true, this::lambda$new$0));
        this.targetGround = this.add(new Setting("TargetGround", false));
        this.pistonPacket = this.add(new Setting("PistonPacket", false));
        this.powerPacket = this.add(new Setting("PowerPacket", true));
        this.checkCrystal = this.add(new Setting("CheckCrystal", true));
        this.breakCrystal = this.add(new Setting("BreakCrystal", true));
        this.selfGround = this.add(new Setting("SelfGround", true));
        this.maxSelfSpeed = this.add(new Setting("MaxSelfSpeed", 6.0, 1.0, 30.0));
        this.maxTargetSpeed = this.add(new Setting("MaxTargetSpeed", 4.0, 1.0, 15.0));
        this.multiPlace = this.add(new Setting("MultiPlace", 2, 1, 8));
        this.timer = new Timer();
        this.progress = 0;
    }
    
    private IBlockState getBlockState(final BlockPos blockPos) {
        return TestPush.mc.world.getBlockState(blockPos);
    }
}
