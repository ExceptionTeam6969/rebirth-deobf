//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.player.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import me.rebirthclient.api.managers.impl.*;
import net.minecraft.init.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import java.util.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;

public class AutoTrap extends Module
{
    private final Setting antiStep;
    private final Setting packet;
    public EntityPlayer target;
    private final Setting mode;
    private final Setting legs;
    private final Setting facing;
    private final Setting chest;
    private final Setting extend;
    private final Setting maxSelfSpeed;
    public static AutoTrap INSTANCE;
    private final Setting autoDisable;
    final Timer timer;
    int progress;
    private final Setting targetMod;
    private final Setting selfGround;
    private final Setting multiPlace;
    private final Setting placeRange;
    private final Setting onlyGround;
    private final Setting head;
    private final Setting range;
    private final Setting maxTargetSpeed;
    private final Setting rotate;
    private final Setting delay;
    
    private void placeBlock(final BlockPos blockPos) {
        if (this.progress >= (int)this.multiPlace.getValue()) {
            return;
        }
        if (!BlockUtil.canPlace(blockPos)) {
            return;
        }
        if (AutoTrap.mc.player.getDistance(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5) > (float)this.placeRange.getValue()) {
            return;
        }
        final int currentItem = AutoTrap.mc.player.inventory.currentItem;
        if (InventoryUtil.findHotbarClass((Class)BlockObsidian.class) == -1) {
            return;
        }
        InventoryUtil.doSwap(InventoryUtil.findHotbarClass((Class)BlockObsidian.class));
        BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue());
        InventoryUtil.doSwap(currentItem);
        this.timer.reset();
        ++this.progress;
    }
    
    private boolean lambda$new$4(final Boolean b) {
        return this.mode.getValue() != Mode.Piston && this.chest.isOpen();
    }
    
    private void doAuto(final BlockPos blockPos) {
        if (InventoryUtil.findHotbarClass((Class)BlockPistonBase.class) == -1) {
            this.doTrap(blockPos);
            return;
        }
        final EnumFacing[] VALUES = EnumFacing.VALUES;
        final int length = VALUES.length;
        int n = 0;
        if (n < length) {
            final EnumFacing enumFacing = VALUES[n];
            if (enumFacing != EnumFacing.UP) {
                if (enumFacing != EnumFacing.DOWN) {
                    if (AutoTrap.mc.world.getBlockState(blockPos.up().offset(enumFacing)).getBlock() instanceof BlockPistonBase) {
                        return;
                    }
                }
            }
            ++n;
            return;
        }
        final EnumFacing[] VALUES2 = EnumFacing.VALUES;
        final int length2 = VALUES2.length;
        int n2 = 0;
        if (n2 < length2) {
            final EnumFacing enumFacing2 = VALUES2[n2];
            if (enumFacing2 != EnumFacing.UP) {
                if (enumFacing2 != EnumFacing.DOWN) {
                    if (BlockUtil.canPlace(blockPos.up().offset(enumFacing2))) {
                        this.placePiston(blockPos.up().offset(enumFacing2), enumFacing2);
                        return;
                    }
                }
            }
            ++n2;
            return;
        }
        this.doTrap(blockPos);
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.mode.getValue() != Mode.Piston;
    }
    
    private boolean lambda$new$5(final Boolean b) {
        return this.mode.getValue() != Mode.Piston;
    }
    
    private void doPiston(final BlockPos p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //     6: aload_1        
        //     7: invokevirtual   net/minecraft/util/math/BlockPos.south:()Lnet/minecraft/util/math/BlockPos;
        //    10: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    13: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //    18: instanceof      Lnet/minecraft/block/BlockPistonBase;
        //    21: ifne            76
        //    24: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoTrap.mc:Lnet/minecraft/client/Minecraft;
        //    27: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    30: aload_1        
        //    31: iconst_m1      
        //    32: invokevirtual   net/minecraft/util/math/BlockPos.south:(I)Lnet/minecraft/util/math/BlockPos;
        //    35: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    38: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //    43: instanceof      Lnet/minecraft/block/BlockPistonBase;
        //    46: ifne            76
        //    49: aload_0        
        //    50: aload_1        
        //    51: invokevirtual   net/minecraft/util/math/BlockPos.south:()Lnet/minecraft/util/math/BlockPos;
        //    54: getstatic       net/minecraft/util/EnumFacing.SOUTH:Lnet/minecraft/util/EnumFacing;
        //    57: if_icmplt       76
        //    60: aload_0        
        //    61: aload_1        
        //    62: iconst_m1      
        //    63: invokevirtual   net/minecraft/util/math/BlockPos.south:(I)Lnet/minecraft/util/math/BlockPos;
        //    66: getstatic       net/minecraft/util/EnumFacing.SOUTH:Lnet/minecraft/util/EnumFacing;
        //    69: invokevirtual   net/minecraft/util/EnumFacing.getOpposite:()Lnet/minecraft/util/EnumFacing;
        //    72: invokespecial   me/rebirthclient/mod/modules/impl/combat/AutoTrap.placePiston:(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)Z
        //    75: pop            
        //    76: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoTrap.mc:Lnet/minecraft/client/Minecraft;
        //    79: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    82: aload_1        
        //    83: invokevirtual   net/minecraft/util/math/BlockPos.east:()Lnet/minecraft/util/math/BlockPos;
        //    86: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    89: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //    94: instanceof      Lnet/minecraft/block/BlockPistonBase;
        //    97: ifne            152
        //   100: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoTrap.mc:Lnet/minecraft/client/Minecraft;
        //   103: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   106: aload_1        
        //   107: iconst_m1      
        //   108: invokevirtual   net/minecraft/util/math/BlockPos.east:(I)Lnet/minecraft/util/math/BlockPos;
        //   111: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   114: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   119: instanceof      Lnet/minecraft/block/BlockPistonBase;
        //   122: ifne            152
        //   125: aload_0        
        //   126: aload_1        
        //   127: invokevirtual   net/minecraft/util/math/BlockPos.east:()Lnet/minecraft/util/math/BlockPos;
        //   130: getstatic       net/minecraft/util/EnumFacing.EAST:Lnet/minecraft/util/EnumFacing;
        //   133: if_icmplt       152
        //   136: aload_0        
        //   137: aload_1        
        //   138: iconst_m1      
        //   139: invokevirtual   net/minecraft/util/math/BlockPos.east:(I)Lnet/minecraft/util/math/BlockPos;
        //   142: getstatic       net/minecraft/util/EnumFacing.EAST:Lnet/minecraft/util/EnumFacing;
        //   145: invokevirtual   net/minecraft/util/EnumFacing.getOpposite:()Lnet/minecraft/util/EnumFacing;
        //   148: invokespecial   me/rebirthclient/mod/modules/impl/combat/AutoTrap.placePiston:(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)Z
        //   151: pop            
        //   152: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0152 (coming from #0133).
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
    
    @Override
    public String getInfo() {
        if (this.target != null) {
            return this.target.getName() + ", " + ((Mode)this.mode.getValue()).name();
        }
        return ((Mode)this.mode.getValue()).name();
    }
    
    private void doTrap(final BlockPos blockPos) {
        if ((boolean)this.antiStep.getValue() && BreakManager.isMine(blockPos.add(0, 2, 0))) {
            this.placeBlock(blockPos.add(0, 3, 0));
        }
        if (this.extend.getValue()) {
            final BlockPos add = blockPos.add(0.1, 0.0, 0.1);
            if (this.checkEntity(new BlockPos((Vec3i)add)) != null) {
                this.placeBlock(add.up(2));
            }
            final BlockPos add2 = blockPos.add(-0.1, 0.0, 0.1);
            if (this.checkEntity(new BlockPos((Vec3i)add2)) != null) {
                this.placeBlock(add2.up(2));
            }
            final BlockPos add3 = blockPos.add(0.1, 0.0, -0.1);
            if (this.checkEntity(new BlockPos((Vec3i)add3)) != null) {
                this.placeBlock(add3.up(2));
            }
            final BlockPos add4 = blockPos.add(-0.1, 0.0, -0.1);
            if (this.checkEntity(new BlockPos((Vec3i)add4)) != null) {
                this.placeBlock(add4.up(2));
            }
        }
        boolean b = false;
        if ((boolean)this.head.getValue() && AutoTrap.mc.world.getBlockState(blockPos.add(0, 2, 0)).getBlock() == Blocks.AIR) {
            if (BlockUtil.canPlace4(blockPos.up(2))) {
                this.placeBlock(blockPos.up(2));
            }
            if (!BlockUtil.canPlace4(blockPos.up(2))) {
                b = true;
            }
        }
        if (((boolean)this.chest.getValue() && (!(boolean)this.onlyGround.getValue() || this.target.onGround)) || b) {
            final EnumFacing[] VALUES = EnumFacing.VALUES;
            final int length = VALUES.length;
            int n = 0;
            if (n < length) {
                final EnumFacing enumFacing = VALUES[n];
                if (enumFacing != EnumFacing.DOWN) {
                    if (enumFacing != EnumFacing.UP) {
                        final BlockPos up = blockPos.offset(enumFacing).up();
                        this.placeBlock(up);
                        if (!BlockUtil.canPlace4(blockPos.up(2)) && BlockUtil.canReplace(blockPos.up(2))) {
                            this.placeBlock(up.up());
                        }
                        if (!BlockUtil.canBlockFacing(up) && BlockUtil.canReplace(up)) {
                            this.placeBlock(up.down());
                        }
                    }
                }
                ++n;
                return;
            }
        }
        if (this.legs.getValue()) {
            final EnumFacing[] VALUES2 = EnumFacing.VALUES;
            final int length2 = VALUES2.length;
            int n2 = 0;
            if (n2 < length2) {
                final EnumFacing enumFacing2 = VALUES2[n2];
                if (enumFacing2 != EnumFacing.DOWN) {
                    if (enumFacing2 != EnumFacing.UP) {
                        final BlockPos offset = blockPos.offset(enumFacing2);
                        if (BlockUtil.isAir(offset.up())) {
                            this.placeBlock(offset);
                            if (!BlockUtil.canBlockFacing(offset)) {
                                this.placeBlock(offset.down());
                            }
                        }
                    }
                }
                ++n2;
            }
        }
    }
    
    private void trapTarget(final EntityPlayer entityPlayer) {
        if (this.mode.getValue() == Mode.Trap) {
            this.doTrap(EntityUtil.getEntityPos((Entity)entityPlayer));
        }
        else if (this.mode.getValue() == Mode.Piston) {
            this.doPiston(EntityUtil.getEntityPos((Entity)entityPlayer).up());
        }
        else {
            this.doAuto(EntityUtil.getEntityPos((Entity)entityPlayer));
        }
    }
    
    private Entity checkEntity(final BlockPos blockPos) {
        final Entity entity = null;
        for (final Entity entity2 : AutoTrap.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos))) {
            if (entity2 instanceof EntityPlayer) {
                if (entity2 == AutoTrap.mc.player) {
                    return null;
                }
                return null;
            }
        }
        return entity;
    }
    
    @Override
    public void onTick() {
        this.progress = 0;
        if ((boolean)this.selfGround.getValue() && !AutoTrap.mc.player.onGround) {
            this.target = null;
            if (this.autoDisable.getValue()) {
                this.disable();
            }
            return;
        }
        if (Managers.SPEED.getPlayerSpeed((EntityPlayer)AutoTrap.mc.player) > (double)this.maxSelfSpeed.getValue()) {
            this.target = null;
            if (this.autoDisable.getValue()) {
                this.disable();
            }
            return;
        }
        if (!this.timer.passedMs((long)(int)this.delay.getValue())) {
            this.target = null;
            return;
        }
        if (this.targetMod.getValue() == TargetMode.Single) {
            this.target = CombatUtil.getTarget((double)(float)this.range.getValue(), (double)this.maxTargetSpeed.getMaxValue());
            if (this.target == null) {
                return;
            }
            this.trapTarget(this.target);
        }
        else if (this.targetMod.getValue() == TargetMode.Multi) {
            final boolean b = false;
            for (final EntityPlayer target : AutoTrap.mc.world.playerEntities) {
                if (Managers.SPEED.getPlayerSpeed(target) <= (double)this.maxTargetSpeed.getValue()) {
                    if (EntityUtil.invalid((Entity)target, (double)(float)this.range.getValue())) {
                        return;
                    }
                    this.trapTarget(this.target = target);
                    return;
                }
            }
            if (!b) {
                if (this.autoDisable.getValue()) {
                    this.disable();
                }
                this.target = null;
            }
        }
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.mode.getValue() != Mode.Piston;
    }
    
    public AutoTrap() {
        super("AutoTrap", "Automatically trap the enemy", Category.COMBAT);
        this.timer = new Timer();
        this.rotate = this.add(new Setting("Rotate", true));
        this.packet = this.add(new Setting("Packet", true));
        this.multiPlace = this.add(new Setting("MultiPlace", 1, 1, 8));
        this.autoDisable = this.add(new Setting("AutoDisable", true));
        this.range = this.add(new Setting("Range", 5.0f, 1.0f, 8.0f));
        this.targetMod = this.add(new Setting("TargetMode", TargetMode.Single));
        this.mode = this.add(new Setting("Mode", Mode.Trap));
        this.antiStep = this.add(new Setting("AntiStep", false, this::lambda$new$0));
        this.extend = this.add(new Setting("Extend", true, this::lambda$new$1));
        this.head = this.add(new Setting("Head", true, this::lambda$new$2));
        this.chest = this.add(new Setting("Chest", true, this::lambda$new$3).setParent());
        this.onlyGround = this.add(new Setting("OnlyGround", true, this::lambda$new$4));
        this.legs = this.add(new Setting("Legs", false, this::lambda$new$5));
        this.facing = this.add(new Setting("Facing", false, this::lambda$new$6));
        this.delay = this.add(new Setting("Delay", 50, 0, 300));
        this.placeRange = this.add(new Setting("PlaceRange", 4.0f, 1.0f, 6.0f));
        this.maxTargetSpeed = this.add(new Setting("MaxTargetSpeed", 4.0, 1.0, 30.0));
        this.selfGround = this.add(new Setting("SelfGround", true));
        this.maxSelfSpeed = this.add(new Setting("MaxSelfSpeed", 6.0, 1.0, 30.0));
        this.progress = 0;
        AutoTrap.INSTANCE = this;
    }
    
    private boolean lambda$new$3(final Boolean b) {
        return this.mode.getValue() != Mode.Piston;
    }
    
    private boolean lambda$new$2(final Boolean b) {
        return this.mode.getValue() != Mode.Piston;
    }
    
    private boolean lambda$new$6(final Boolean b) {
        return this.mode.getValue() != Mode.Trap;
    }
    
    public enum Mode
    {
        Auto("Auto", 2), 
        Piston("Piston", 1);
        
        private static final Mode[] $VALUES;
        
        Trap("Trap", 0);
        
        static {
            $VALUES = new Mode[] { Mode.Trap, Mode.Piston, Mode.Auto };
        }
        
        private Mode(final String s, final int n) {
        }
    }
    
    public enum TargetMode
    {
        Multi("Multi", 1), 
        Single("Single", 0);
        
        private static final TargetMode[] $VALUES;
        
        static {
            $VALUES = new TargetMode[] { TargetMode.Single, TargetMode.Multi };
        }
        
        private TargetMode(final String s, final int n) {
        }
    }
}
