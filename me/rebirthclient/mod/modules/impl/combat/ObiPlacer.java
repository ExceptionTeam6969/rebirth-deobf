//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.init.*;
import net.minecraft.world.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import me.rebirthclient.api.util.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraft.block.*;

public class ObiPlacer extends Module
{
    private final Timer delayTimer;
    public final Setting render;
    private final Timer renderTimer;
    BlockPos placePos;
    private final Setting rotate;
    public final Setting outlineAlpha;
    private final Setting minDmg;
    public final Setting boxAlpha;
    private final Setting placeRange;
    public final Setting box;
    private final Setting renderTime;
    public final Setting outline;
    private final Setting packet;
    private final Setting color;
    private final Setting shrinkTime;
    private final Setting placeDelay;
    private final Setting range;
    private FadeUtils shrinkTimer;
    private final Setting wallsRange;
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (this.placePos == null || this.renderTimer.passedMs((long)(int)this.renderTime.getValue()) || this.renderTimer.passedMs((long)(int)this.renderTime.getValue()) || !(boolean)this.render.getValue()) {
            return;
        }
        if (ObiPlacer.mc.world.getBlockState(this.placePos).getBlock() == Blocks.AIR) {
            return;
        }
        if (ObiPlacer.mc.world.getBlockState(this.placePos).getBlock() == Blocks.FIRE) {
            return;
        }
        final AxisAlignedBB grow = ObiPlacer.mc.world.getBlockState(this.placePos).getSelectedBoundingBox((World)ObiPlacer.mc.world, this.placePos).grow(this.shrinkTimer.easeInQuad() / 2.0 - 1.0);
        if (this.outline.getValue()) {
            RenderUtil.drawBBBox(grow, (Color)this.color.getValue(), (int)this.outlineAlpha.getValue());
        }
        if (this.box.getValue()) {
            RenderUtil.drawBBFill(grow, (Color)this.color.getValue(), (int)this.boxAlpha.getValue());
        }
    }
    
    private BlockPos getPlaceTarget(final Entity p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: aload_1        
        //     3: invokestatic    me/rebirthclient/api/util/EntityUtil.getEntityPos:(Lnet/minecraft/entity/Entity;)Lnet/minecraft/util/math/BlockPos;
        //     6: invokevirtual   net/minecraft/util/math/BlockPos.down:()Lnet/minecraft/util/math/BlockPos;
        //     9: invokestatic    me/rebirthclient/api/util/BlockUtil.getBox:(FLnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/NonNullList;
        //    12: invokevirtual   net/minecraft/util/NonNullList.iterator:()Ljava/util/Iterator;
        //    15: astore_2       
        //    16: aload_2        
        //    17: invokeinterface java/util/Iterator.hasNext:()Z
        //    22: ifeq            140
        //    25: aload_2        
        //    26: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    31: checkcast       Lnet/minecraft/util/math/BlockPos;
        //    34: astore_3       
        //    35: aload_0        
        //    36: aload_3        
        //    37: ifeq            42
        //    40: aconst_null    
        //    41: areturn        
        //    42: aload_3        
        //    43: invokestatic    me/rebirthclient/mod/modules/impl/combat/CatCrystal.behindWall:(Lnet/minecraft/util/math/BlockPos;)Z
        //    46: ifeq            51
        //    49: aconst_null    
        //    50: areturn        
        //    51: getstatic       me/rebirthclient/mod/modules/impl/combat/ObiPlacer.mc:Lnet/minecraft/client/Minecraft;
        //    54: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    57: aload_3        
        //    58: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //    61: i2d            
        //    62: ldc2_w          0.5
        //    65: dadd           
        //    66: aload_3        
        //    67: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //    70: i2d            
        //    71: ldc2_w          0.5
        //    74: dadd           
        //    75: aload_3        
        //    76: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //    79: i2d            
        //    80: ldc2_w          0.5
        //    83: dadd           
        //    84: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(DDD)D
        //    87: aload_0        
        //    88: getfield        me/rebirthclient/mod/modules/impl/combat/ObiPlacer.placeRange:Lme/rebirthclient/mod/modules/settings/Setting;
        //    91: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    94: checkcast       Ljava/lang/Float;
        //    97: invokevirtual   java/lang/Float.floatValue:()F
        //   100: f2d            
        //   101: dcmpl          
        //   102: ifle            107
        //   105: aconst_null    
        //   106: areturn        
        //   107: aload_3        
        //   108: invokevirtual   net/minecraft/util/math/BlockPos.down:()Lnet/minecraft/util/math/BlockPos;
        //   111: aload_1        
        //   112: invokestatic    me/rebirthclient/api/util/DamageUtil.calculateDamage:(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;)F
        //   115: fstore          4
        //   117: fload           4
        //   119: aload_0        
        //   120: getfield        me/rebirthclient/mod/modules/impl/combat/ObiPlacer.minDmg:Lme/rebirthclient/mod/modules/settings/Setting;
        //   123: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   126: checkcast       Ljava/lang/Float;
        //   129: invokevirtual   java/lang/Float.floatValue:()F
        //   132: fcmpl          
        //   133: ifle            138
        //   136: aconst_null    
        //   137: areturn        
        //   138: aconst_null    
        //   139: areturn        
        //   140: aconst_null    
        //   141: astore_2       
        //   142: fconst_0       
        //   143: fstore_3       
        //   144: aload_0        
        //   145: getfield        me/rebirthclient/mod/modules/impl/combat/ObiPlacer.range:Lme/rebirthclient/mod/modules/settings/Setting;
        //   148: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   151: checkcast       Ljava/lang/Float;
        //   154: invokevirtual   java/lang/Float.floatValue:()F
        //   157: invokestatic    me/rebirthclient/api/util/BlockUtil.getBox:(F)Lnet/minecraft/util/NonNullList;
        //   160: invokevirtual   net/minecraft/util/NonNullList.iterator:()Ljava/util/Iterator;
        //   163: astore          4
        //   165: aload           4
        //   167: invokeinterface java/util/Iterator.hasNext:()Z
        //   172: ifeq            473
        //   175: aload           4
        //   177: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   182: checkcast       Lnet/minecraft/util/math/BlockPos;
        //   185: astore          5
        //   187: getstatic       me/rebirthclient/mod/modules/impl/combat/ObiPlacer.mc:Lnet/minecraft/client/Minecraft;
        //   190: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   193: aload           5
        //   195: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //   198: ifeq            165
        //   201: getstatic       me/rebirthclient/mod/modules/impl/combat/ObiPlacer.mc:Lnet/minecraft/client/Minecraft;
        //   204: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   207: aload           5
        //   209: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //   212: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //   215: ifeq            165
        //   218: getstatic       me/rebirthclient/mod/modules/impl/combat/ObiPlacer.mc:Lnet/minecraft/client/Minecraft;
        //   221: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   224: aload           5
        //   226: iconst_2       
        //   227: invokevirtual   net/minecraft/util/math/BlockPos.up:(I)Lnet/minecraft/util/math/BlockPos;
        //   230: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //   233: ifne            238
        //   236: aconst_null    
        //   237: areturn        
        //   238: getstatic       me/rebirthclient/mod/modules/impl/combat/ObiPlacer.mc:Lnet/minecraft/client/Minecraft;
        //   241: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   244: aload           5
        //   246: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //   249: i2d            
        //   250: ldc2_w          0.5
        //   253: dadd           
        //   254: aload           5
        //   256: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //   259: i2d            
        //   260: ldc2_w          0.5
        //   263: dadd           
        //   264: aload           5
        //   266: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //   269: i2d            
        //   270: ldc2_w          0.5
        //   273: dadd           
        //   274: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistance:(DDD)D
        //   277: aload_0        
        //   278: getfield        me/rebirthclient/mod/modules/impl/combat/ObiPlacer.placeRange:Lme/rebirthclient/mod/modules/settings/Setting;
        //   281: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   284: checkcast       Ljava/lang/Float;
        //   287: invokevirtual   java/lang/Float.floatValue:()F
        //   290: f2d            
        //   291: dcmpl          
        //   292: ifle            297
        //   295: aconst_null    
        //   296: areturn        
        //   297: aload_0        
        //   298: aload           5
        //   300: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //   303: ifeq            308
        //   306: aconst_null    
        //   307: areturn        
        //   308: aload_0        
        //   309: aload           5
        //   311: iconst_2       
        //   312: invokevirtual   net/minecraft/util/math/BlockPos.up:(I)Lnet/minecraft/util/math/BlockPos;
        //   315: ifeq            320
        //   318: aconst_null    
        //   319: areturn        
        //   320: aload           5
        //   322: invokestatic    me/rebirthclient/api/util/BlockUtil.canPlace:(Lnet/minecraft/util/math/BlockPos;)Z
        //   325: ifne            330
        //   328: aconst_null    
        //   329: areturn        
        //   330: aload           5
        //   332: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //   335: i2d            
        //   336: new             Lnet/minecraft/util/math/BlockPos;
        //   339: dup            
        //   340: aload_1        
        //   341: getfield        net/minecraft/entity/Entity.posX:D
        //   344: aload_1        
        //   345: getfield        net/minecraft/entity/Entity.posY:D
        //   348: ldc2_w          0.5
        //   351: dadd           
        //   352: aload_1        
        //   353: getfield        net/minecraft/entity/Entity.posZ:D
        //   356: invokespecial   net/minecraft/util/math/BlockPos.<init>:(DDD)V
        //   359: invokevirtual   net/minecraft/util/math/BlockPos.getY:()I
        //   362: i2d            
        //   363: ldc2_w          0.5
        //   366: dsub           
        //   367: dcmpl          
        //   368: ifle            373
        //   371: aconst_null    
        //   372: areturn        
        //   373: aload           5
        //   375: aload_1        
        //   376: invokestatic    me/rebirthclient/api/util/DamageUtil.calculateDamage:(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;)F
        //   379: fstore          6
        //   381: fload           6
        //   383: aload_0        
        //   384: getfield        me/rebirthclient/mod/modules/impl/combat/ObiPlacer.minDmg:Lme/rebirthclient/mod/modules/settings/Setting;
        //   387: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   390: checkcast       Ljava/lang/Float;
        //   393: invokevirtual   java/lang/Float.floatValue:()F
        //   396: fcmpg          
        //   397: iflt            165
        //   400: getstatic       me/rebirthclient/mod/modules/impl/combat/ObiPlacer.mc:Lnet/minecraft/client/Minecraft;
        //   403: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   406: aload           5
        //   408: invokevirtual   net/minecraft/client/entity/EntityPlayerSP.getDistanceSq:(Lnet/minecraft/util/math/BlockPos;)D
        //   411: aload_0        
        //   412: getfield        me/rebirthclient/mod/modules/impl/combat/ObiPlacer.wallsRange:Lme/rebirthclient/mod/modules/settings/Setting;
        //   415: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //   418: checkcast       Ljava/lang/Float;
        //   421: invokevirtual   java/lang/Float.floatValue:()F
        //   424: f2d            
        //   425: invokestatic    me/rebirthclient/api/util/math/MathUtil.square:(D)D
        //   428: dcmpl          
        //   429: iflt            444
        //   432: aload           5
        //   434: iconst_1       
        //   435: fconst_1       
        //   436: invokestatic    me/rebirthclient/api/util/BlockUtil.rayTracePlaceCheck:(Lnet/minecraft/util/math/BlockPos;ZF)Z
        //   439: ifeq            444
        //   442: aconst_null    
        //   443: areturn        
        //   444: aload_2        
        //   445: ifnonnull       456
        //   448: fload           6
        //   450: fstore_3       
        //   451: aload           5
        //   453: astore_2       
        //   454: aconst_null    
        //   455: areturn        
        //   456: fload           6
        //   458: fload_3        
        //   459: fcmpg          
        //   460: ifge            465
        //   463: aconst_null    
        //   464: areturn        
        //   465: fload           6
        //   467: fstore_3       
        //   468: aload           5
        //   470: astore_2       
        //   471: aconst_null    
        //   472: areturn        
        //   473: aload_2        
        //   474: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0165 (coming from #0397).
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
    
    private boolean lambda$new$0(final Boolean b) {
        return this.render.isOpen();
    }
    
    private void placeBlock(final BlockPos blockPos) {
        if (!BlockUtil.canPlace(blockPos)) {
            return;
        }
        final int currentItem = ObiPlacer.mc.player.inventory.currentItem;
        if (InventoryUtil.findHotbarClass((Class)BlockObsidian.class) == -1) {
            return;
        }
        InventoryUtil.doSwap(InventoryUtil.findHotbarClass((Class)BlockObsidian.class));
        BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue());
        InventoryUtil.doSwap(currentItem);
    }
    
    private boolean lambda$new$4(final Color color) {
        return this.render.isOpen();
    }
    
    @Override
    public String getInfo() {
        if (fullNullCheck()) {
            return null;
        }
        final EntityPlayer target = CombatUtil.getTarget((double)(float)this.range.getValue());
        if (target != null) {
            return target.getName();
        }
        return null;
    }
    
    private boolean lambda$new$5(final Integer n) {
        return this.render.isOpen();
    }
    
    private boolean lambda$new$6(final Integer n) {
        return this.render.isOpen();
    }
    
    private boolean lambda$new$3(final Integer n) {
        return this.render.isOpen() && this.box.isOpen();
    }
    
    public ObiPlacer() {
        super("ObiPlacer", "auto place obi of crystal", Category.COMBAT);
        this.render = this.add(new Setting("Render", true).setParent());
        this.outline = this.add(new Setting("Outline", true, this::lambda$new$0).setParent());
        this.outlineAlpha = this.add(new Setting("OutlineAlpha", 150, 0, 255, this::lambda$new$1));
        this.box = this.add(new Setting("Box", true, this::lambda$new$2).setParent());
        this.boxAlpha = this.add(new Setting("BoxAlpha", 70, 0, 255, this::lambda$new$3));
        this.color = this.add(new Setting("Color", new Color(255, 255, 255), this::lambda$new$4).hideAlpha());
        this.renderTime = this.add(new Setting("RenderTime", 3000, 0, 5000, this::lambda$new$5));
        this.shrinkTime = this.add(new Setting("ShrinkTime", 600, 0, 5000, this::lambda$new$6));
        this.shrinkTimer = new FadeUtils((long)(int)this.shrinkTime.getValue());
        this.delayTimer = new Timer();
        this.renderTimer = new Timer();
        this.rotate = this.add(new Setting("Rotate", true));
        this.packet = this.add(new Setting("Packet", true));
        this.range = this.add(new Setting("Range", 6.0f, 0.0f, 10.0f));
        this.wallsRange = this.add(new Setting("WallsRange", 3.5f, 0.0f, 10.0f));
        this.placeDelay = this.add(new Setting("PlaceDelay", 100, 0, 2000));
        this.minDmg = this.add(new Setting("MinDmg", 6.0f, 0.0f, 10.0f));
        this.placeRange = this.add(new Setting("PlaceRange", 4.0f, 1.0f, 6.0f));
    }
    
    private Block getBlock(final BlockPos blockPos) {
        return ObiPlacer.mc.world.getBlockState(blockPos).getBlock();
    }
    
    private boolean lambda$new$2(final Boolean b) {
        return this.render.isOpen();
    }
    
    @Override
    public void onUpdate() {
        final EntityPlayer target = CombatUtil.getTarget((double)(float)this.range.getValue());
        if (!this.delayTimer.passedMs((long)(int)this.placeDelay.getValue()) || target == null) {
            return;
        }
        this.placePos = this.getPlaceTarget((Entity)target);
        if (this.placePos != null && BlockUtil.canPlace(this.placePos)) {
            this.shrinkTimer = new FadeUtils((long)(int)this.shrinkTime.getValue());
            this.delayTimer.reset();
            this.renderTimer.reset();
            this.placeBlock(this.placePos);
        }
    }
    
    private boolean lambda$new$1(final Integer n) {
        return this.render.isOpen() && this.outline.isOpen();
    }
}
