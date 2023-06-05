//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import java.util.concurrent.atomic.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.managers.impl.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import me.rebirthclient.mod.modules.impl.render.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import me.rebirthclient.api.util.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import java.util.*;
import com.mojang.realmsclient.gui.*;

public class AutoReplenish extends Module
{
    public final Setting rotate;
    private final Setting autoDisable;
    private final Setting endChest;
    private final Setting crystal;
    public final Setting packet;
    public static final List shulkers;
    private final Setting place;
    private final Setting take;
    private final Setting open;
    private final Setting piston;
    private final Setting exp;
    BlockPos placePos;
    private final Setting totem;
    private final Setting minRange;
    private final Setting range;
    private final Setting web;
    private final Setting smart;
    private final Setting redStoneBlock;
    int[] stealCountList;
    private final Setting gapple;
    
    private boolean lambda$new$6(final Integer n) {
        return this.take.isOpen() && this.smart.isOpen();
    }
    
    private boolean lambda$new$3(final Integer n) {
        return this.take.isOpen() && this.smart.isOpen();
    }
    
    private boolean lambda$new$7(final Integer n) {
        return this.take.isOpen() && this.smart.isOpen();
    }
    
    public int findShulker() {
        final AtomicInteger atomicInteger = new AtomicInteger(-1);
        AutoReplenish.shulkers.forEach(AutoReplenish::lambda$findShulker$9);
        return atomicInteger.get();
    }
    
    private boolean lambda$new$2(final Integer n) {
        return this.take.isOpen() && this.smart.isOpen();
    }
    
    public static void placeShulker(final BlockPos blockPos, final boolean b, final boolean b2) {
        final EnumFacing down = EnumFacing.DOWN;
        final BlockPos offset = blockPos.offset(down);
        final EnumFacing getOpposite = down.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        final Block getBlock = AutoReplenish.mc.world.getBlockState(offset).getBlock();
        boolean b3 = false;
        if (!SneakManager.isSneaking && (BlockUtil.canUseList.contains(getBlock) || BlockUtil.shulkerList.contains(getBlock))) {
            AutoReplenish.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoReplenish.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            b3 = true;
        }
        if (b) {
            EntityUtil.faceVector(add);
        }
        PlaceRender.PlaceMap.put(blockPos, new PlaceRender.placePosition(blockPos));
        BlockUtil.rightClickBlock(offset, add, EnumHand.MAIN_HAND, getOpposite, b2);
        if (b3) {
            AutoReplenish.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoReplenish.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
    }
    
    private boolean lambda$new$1(final Integer n) {
        return this.take.isOpen() && this.smart.isOpen();
    }
    
    private void update() {
        this.stealCountList[0] = (int)this.crystal.getValue() - this.getItemCount(Items.END_CRYSTAL);
        this.stealCountList[1] = (int)this.exp.getValue() - this.getItemCount(Items.EXPERIENCE_BOTTLE);
        this.stealCountList[2] = (int)this.totem.getValue() - this.getItemCount(Items.TOTEM_OF_UNDYING);
        this.stealCountList[3] = (int)this.gapple.getValue() - this.getItemCount(Items.GOLDEN_APPLE);
        this.stealCountList[4] = (int)this.endChest.getValue() - this.getItemCount(Item.getItemFromBlock(Blocks.ENDER_CHEST));
        this.stealCountList[5] = (int)this.web.getValue() - this.getItemCount(Item.getItemFromBlock(Blocks.WEB));
        this.stealCountList[6] = (int)this.redStoneBlock.getValue() - this.getItemCount(Item.getItemFromBlock(Blocks.REDSTONE_BLOCK));
        this.stealCountList[7] = (int)this.piston.getValue() - this.getPistonCount();
    }
    
    public static void openShulker(BlockPos up, final boolean b, final boolean b2) {
        final EnumFacing down = EnumFacing.DOWN;
        up = up.up();
        final BlockPos offset = up.offset(down);
        final EnumFacing getOpposite = down.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        if (b) {
            EntityUtil.faceVector(add);
        }
        AutoReplenish.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoReplenish.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        BlockUtil.rightClickBlock(offset, add, EnumHand.MAIN_HAND, getOpposite, b2);
    }
    
    public AutoReplenish() {
        super("AutoReplenish", "Auto place shulker and replenish", Category.COMBAT);
        this.autoDisable = this.add(new Setting("AutoDisable", true));
        this.rotate = this.add(new Setting("Rotate", true));
        this.packet = this.add(new Setting("Packet", true));
        this.place = this.add(new Setting("Place", true));
        this.open = this.add(new Setting("Open", true));
        this.range = this.add(new Setting("Range", 4.0f, 0.0f, 6.0f));
        this.minRange = this.add(new Setting("MinRange", 1.0f, 0.0f, 3.0f));
        this.take = this.add(new Setting("Take", true).setParent());
        this.smart = this.add(new Setting("Smart", true, this::lambda$new$0).setParent());
        this.crystal = this.add(new Setting("Crystal", 6, 0, 20, this::lambda$new$1));
        this.exp = this.add(new Setting("Exp", 6, 0, 20, this::lambda$new$2));
        this.totem = this.add(new Setting("Totem", 6, 0, 30, this::lambda$new$3));
        this.gapple = this.add(new Setting("Gapple", 3, 0, 10, this::lambda$new$4));
        this.endChest = this.add(new Setting("EndChest", 1, 0, 5, this::lambda$new$5));
        this.web = this.add(new Setting("Web", 1, 0, 5, this::lambda$new$6));
        this.redStoneBlock = this.add(new Setting("RedStoneBlock", 1, 0, 5, this::lambda$new$7));
        this.piston = this.add(new Setting("Piston", 1, 0, 5, this::lambda$new$8));
        this.stealCountList = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
        this.placePos = null;
    }
    
    private boolean lambda$new$8(final Integer n) {
        return this.take.isOpen() && this.smart.isOpen();
    }
    
    @Override
    public void onTick() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   me/rebirthclient/mod/modules/impl/combat/AutoReplenish.update:()V
        //     4: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoReplenish.mc:Lnet/minecraft/client/Minecraft;
        //     7: getfield        net/minecraft/client/Minecraft.currentScreen:Lnet/minecraft/client/gui/GuiScreen;
        //    10: instanceof      Lnet/minecraft/client/gui/inventory/GuiShulkerBox;
        //    13: ifne            255
        //    16: aload_0        
        //    17: getfield        me/rebirthclient/mod/modules/impl/combat/AutoReplenish.open:Lme/rebirthclient/mod/modules/settings/Setting;
        //    20: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    23: checkcast       Ljava/lang/Boolean;
        //    26: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    29: ifeq            217
        //    32: aload_0        
        //    33: getfield        me/rebirthclient/mod/modules/impl/combat/AutoReplenish.placePos:Lnet/minecraft/util/math/BlockPos;
        //    36: ifnull          104
        //    39: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoReplenish.shulkers:Ljava/util/List;
        //    42: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoReplenish.mc:Lnet/minecraft/client/Minecraft;
        //    45: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    48: aload_0        
        //    49: getfield        me/rebirthclient/mod/modules/impl/combat/AutoReplenish.placePos:Lnet/minecraft/util/math/BlockPos;
        //    52: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //    55: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //    60: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //    65: ifeq            254
        //    68: aload_0        
        //    69: getfield        me/rebirthclient/mod/modules/impl/combat/AutoReplenish.placePos:Lnet/minecraft/util/math/BlockPos;
        //    72: aload_0        
        //    73: getfield        me/rebirthclient/mod/modules/impl/combat/AutoReplenish.rotate:Lme/rebirthclient/mod/modules/settings/Setting;
        //    76: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    79: checkcast       Ljava/lang/Boolean;
        //    82: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    85: aload_0        
        //    86: getfield        me/rebirthclient/mod/modules/impl/combat/AutoReplenish.packet:Lme/rebirthclient/mod/modules/settings/Setting;
        //    89: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    92: checkcast       Ljava/lang/Boolean;
        //    95: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    98: invokestatic    me/rebirthclient/mod/modules/impl/combat/AutoReplenish.openShulker:(Lnet/minecraft/util/math/BlockPos;ZZ)V
        //   101: goto            254
        //   104: aload_0        
        //   105: getfield        me/rebirthclient/mod/modules/impl/combat/AutoReplenish.range:Lme/rebirthclient/mod/modules/settings/Setting;
        //   108: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   111: checkcast       Ljava/lang/Float;
        //   114: invokevirtual   java/lang/Float.floatValue:()F
        //   117: invokestatic    me/rebirthclient/api/util/BlockUtil.getBox:(F)Lnet/minecraft/util/NonNullList;
        //   120: invokevirtual   net/minecraft/util/NonNullList.iterator:()Ljava/util/Iterator;
        //   123: astore_1       
        //   124: aload_1        
        //   125: invokeinterface java/util/Iterator.hasNext:()Z
        //   130: ifeq            214
        //   133: aload_1        
        //   134: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   139: checkcast       Lnet/minecraft/util/math/BlockPos;
        //   142: astore_2       
        //   143: aload_2        
        //   144: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //   147: invokestatic    me/rebirthclient/api/util/BlockUtil.isAir:(Lnet/minecraft/util/math/BlockPos;)Z
        //   150: ifne            154
        //   153: return         
        //   154: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoReplenish.shulkers:Ljava/util/List;
        //   157: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoReplenish.mc:Lnet/minecraft/client/Minecraft;
        //   160: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   163: aload_2        
        //   164: invokevirtual   net/minecraft/client/multiplayer/WorldClient.getBlockState:(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;
        //   167: invokeinterface net/minecraft/block/state/IBlockState.getBlock:()Lnet/minecraft/block/Block;
        //   172: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   177: ifeq            213
        //   180: aload_2        
        //   181: aload_0        
        //   182: getfield        me/rebirthclient/mod/modules/impl/combat/AutoReplenish.rotate:Lme/rebirthclient/mod/modules/settings/Setting;
        //   185: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   188: checkcast       Ljava/lang/Boolean;
        //   191: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   194: aload_0        
        //   195: getfield        me/rebirthclient/mod/modules/impl/combat/AutoReplenish.packet:Lme/rebirthclient/mod/modules/settings/Setting;
        //   198: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   201: checkcast       Ljava/lang/Boolean;
        //   204: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   207: invokestatic    me/rebirthclient/mod/modules/impl/combat/AutoReplenish.openShulker:(Lnet/minecraft/util/math/BlockPos;ZZ)V
        //   210: goto            214
        //   213: return         
        //   214: goto            254
        //   217: aload_0        
        //   218: getfield        me/rebirthclient/mod/modules/impl/combat/AutoReplenish.take:Lme/rebirthclient/mod/modules/settings/Setting;
        //   221: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   224: checkcast       Ljava/lang/Boolean;
        //   227: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   230: ifne            254
        //   233: aload_0        
        //   234: getfield        me/rebirthclient/mod/modules/impl/combat/AutoReplenish.autoDisable:Lme/rebirthclient/mod/modules/settings/Setting;
        //   237: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   240: checkcast       Ljava/lang/Boolean;
        //   243: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   246: ifeq            253
        //   249: aload_0        
        //   250: invokevirtual   me/rebirthclient/mod/modules/impl/combat/AutoReplenish.disable:()V
        //   253: return         
        //   254: return         
        //   255: aload_0        
        //   256: getfield        me/rebirthclient/mod/modules/impl/combat/AutoReplenish.take:Lme/rebirthclient/mod/modules/settings/Setting;
        //   259: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   262: checkcast       Ljava/lang/Boolean;
        //   265: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   268: ifne            292
        //   271: aload_0        
        //   272: getfield        me/rebirthclient/mod/modules/impl/combat/AutoReplenish.autoDisable:Lme/rebirthclient/mod/modules/settings/Setting;
        //   275: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   278: checkcast       Ljava/lang/Boolean;
        //   281: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   284: ifeq            291
        //   287: aload_0        
        //   288: invokevirtual   me/rebirthclient/mod/modules/impl/combat/AutoReplenish.disable:()V
        //   291: return         
        //   292: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoReplenish.mc:Lnet/minecraft/client/Minecraft;
        //   295: getfield        net/minecraft/client/Minecraft.currentScreen:Lnet/minecraft/client/gui/GuiScreen;
        //   298: checkcast       Lnet/minecraft/client/gui/inventory/GuiShulkerBox;
        //   301: astore_1       
        //   302: aload_1        
        //   303: checkcast       Lme/rebirthclient/asm/accessors/IGuiShulkerBox;
        //   306: invokeinterface me/rebirthclient/asm/accessors/IGuiShulkerBox.getInventory:()Lnet/minecraft/inventory/IInventory;
        //   311: astore_2       
        //   312: iconst_0       
        //   313: istore_3       
        //   314: iload_3        
        //   315: aload_2        
        //   316: invokestatic    java/util/Objects.requireNonNull:(Ljava/lang/Object;)Ljava/lang/Object;
        //   319: checkcast       Lnet/minecraft/inventory/IInventory;
        //   322: invokeinterface net/minecraft/inventory/IInventory.getSizeInventory:()I
        //   327: if_icmpge       445
        //   330: aload_2        
        //   331: iload_3        
        //   332: invokeinterface net/minecraft/inventory/IInventory.getStackInSlot:(I)Lnet/minecraft/item/ItemStack;
        //   337: astore          4
        //   339: aload           4
        //   341: getfield        net/minecraft/item/ItemStack.isEmpty:Z
        //   344: ifne            441
        //   347: aload_0        
        //   348: getfield        me/rebirthclient/mod/modules/impl/combat/AutoReplenish.smart:Lme/rebirthclient/mod/modules/settings/Setting;
        //   351: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   354: checkcast       Ljava/lang/Boolean;
        //   357: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   360: ifne            391
        //   363: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoReplenish.mc:Lnet/minecraft/client/Minecraft;
        //   366: getfield        net/minecraft/client/Minecraft.playerController:Lnet/minecraft/client/multiplayer/PlayerControllerMP;
        //   369: aload_1        
        //   370: getfield        net/minecraft/client/gui/inventory/GuiShulkerBox.inventorySlots:Lnet/minecraft/inventory/Container;
        //   373: getfield        net/minecraft/inventory/Container.windowId:I
        //   376: iload_3        
        //   377: iconst_0       
        //   378: getstatic       net/minecraft/inventory/ClickType.QUICK_MOVE:Lnet/minecraft/inventory/ClickType;
        //   381: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoReplenish.mc:Lnet/minecraft/client/Minecraft;
        //   384: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   387: invokevirtual   net/minecraft/client/multiplayer/PlayerControllerMP.windowClick:(IIILnet/minecraft/inventory/ClickType;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;
        //   390: pop            
        //   391: aload_0        
        //   392: aload_1        
        //   393: checkcast       Lme/rebirthclient/asm/accessors/IGuiShulkerBox;
        //   396: invokeinterface me/rebirthclient/asm/accessors/IGuiShulkerBox.getInventory:()Lnet/minecraft/inventory/IInventory;
        //   401: iload_3        
        //   402: invokeinterface net/minecraft/inventory/IInventory.getStackInSlot:(I)Lnet/minecraft/item/ItemStack;
        //   407: invokevirtual   net/minecraft/item/ItemStack.getItem:()Lnet/minecraft/item/Item;
        //   410: ifeq            441
        //   413: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoReplenish.mc:Lnet/minecraft/client/Minecraft;
        //   416: getfield        net/minecraft/client/Minecraft.playerController:Lnet/minecraft/client/multiplayer/PlayerControllerMP;
        //   419: aload_1        
        //   420: getfield        net/minecraft/client/gui/inventory/GuiShulkerBox.inventorySlots:Lnet/minecraft/inventory/Container;
        //   423: getfield        net/minecraft/inventory/Container.windowId:I
        //   426: iload_3        
        //   427: iconst_0       
        //   428: getstatic       net/minecraft/inventory/ClickType.QUICK_MOVE:Lnet/minecraft/inventory/ClickType;
        //   431: getstatic       me/rebirthclient/mod/modules/impl/combat/AutoReplenish.mc:Lnet/minecraft/client/Minecraft;
        //   434: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   437: invokevirtual   net/minecraft/client/multiplayer/PlayerControllerMP.windowClick:(IIILnet/minecraft/inventory/ClickType;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;
        //   440: pop            
        //   441: iinc            3, 1
        //   444: return         
        //   445: aload_0        
        //   446: getfield        me/rebirthclient/mod/modules/impl/combat/AutoReplenish.autoDisable:Lme/rebirthclient/mod/modules/settings/Setting;
        //   449: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   452: checkcast       Ljava/lang/Boolean;
        //   455: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   458: ifeq            465
        //   461: aload_0        
        //   462: invokevirtual   me/rebirthclient/mod/modules/impl/combat/AutoReplenish.disable:()V
        //   465: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0441 (coming from #0410).
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
    
    private static void lambda$findShulker$9(final AtomicInteger atomicInteger, final Block block) {
        if (InventoryUtil.findHotbarBlock(block) != -1) {
            atomicInteger.set(InventoryUtil.findHotbarBlock(block));
        }
    }
    
    private int getPistonCount() {
        int n = 0;
        if (AutoReplenish.mc.player.getHeldItemOffhand().getItem() == Item.getItemFromBlock((Block)Blocks.PISTON) || AutoReplenish.mc.player.getHeldItemOffhand().getItem() == Item.getItemFromBlock((Block)Blocks.STICKY_PISTON)) {
            ++n;
        }
        int n2 = 1;
        if (n2 < 5) {
            final ItemStack getStack = AutoReplenish.mc.player.inventoryContainer.inventorySlots.get(n2).getStack();
            if (getStack.getItem() == Item.getItemFromBlock((Block)Blocks.STICKY_PISTON) || getStack.getItem() == Item.getItemFromBlock((Block)Blocks.STICKY_PISTON)) {
                ++n;
            }
            ++n2;
            return 0;
        }
        for (final Map.Entry<K, ItemStack> entry : InventoryUtil.getInventoryAndHotbarSlots().entrySet()) {
            if (entry.getValue().getItem() == Item.getItemFromBlock((Block)Blocks.STICKY_PISTON) || entry.getValue().getItem() == Item.getItemFromBlock((Block)Blocks.PISTON)) {
                if ((int)entry.getKey() == 45) {
                    return 0;
                }
                ++n;
                return 0;
            }
        }
        return n;
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.take.isOpen();
    }
    
    static {
        shulkers = Arrays.asList(Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX);
    }
    
    private boolean lambda$new$5(final Integer n) {
        return this.take.isOpen() && this.smart.isOpen();
    }
    
    @Override
    public void onEnable() {
        this.placePos = null;
        final int currentItem = AutoReplenish.mc.player.inventory.currentItem;
        if (!(boolean)this.place.getValue()) {
            return;
        }
        if (this.findShulker() == -1) {
            this.sendMessage(ChatFormatting.RED + "No Shulker Found");
            return;
        }
        InventoryUtil.doSwap(this.findShulker());
        final double n = 100.0;
        final BlockPos placePos = null;
        final Iterator iterator = BlockUtil.getBox((float)this.range.getValue()).iterator();
        if (!iterator.hasNext()) {
            if (placePos != null) {
                placeShulker(placePos, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue());
                this.placePos = placePos;
            }
            InventoryUtil.doSwap(currentItem);
            return;
        }
        final BlockPos blockPos = iterator.next();
        if (!BlockUtil.isAir(blockPos.up())) {
            return;
        }
        if (AutoReplenish.mc.player.getDistance(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5) < (float)this.minRange.getValue()) {
            return;
        }
        if (!BlockUtil.canPlaceShulker(blockPos)) {
            return;
        }
        if (placePos == null || AutoReplenish.mc.player.getDistance(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5) < n) {
            AutoReplenish.mc.player.getDistance(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5);
        }
    }
    
    private boolean lambda$new$4(final Integer n) {
        return this.take.isOpen() && this.smart.isOpen();
    }
    
    private int getItemCount(final Item item) {
        int n = 0;
        if (AutoReplenish.mc.player.getHeldItemOffhand().getItem() == item) {
            ++n;
        }
        int n2 = 1;
        if (n2 < 5) {
            if (((Slot)AutoReplenish.mc.player.inventoryContainer.inventorySlots.get(n2)).getStack().getItem() == item) {
                ++n;
            }
            ++n2;
            return 0;
        }
        for (final Map.Entry<K, ItemStack> entry : InventoryUtil.getInventoryAndHotbarSlots().entrySet()) {
            if (entry.getValue().getItem() == item) {
                if ((int)entry.getKey() == 45) {
                    return 0;
                }
                ++n;
                return 0;
            }
        }
        return n;
    }
}
