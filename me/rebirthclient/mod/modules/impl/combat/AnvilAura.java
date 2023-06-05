//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.combat;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import me.rebirthclient.api.util.*;

public class AnvilAura extends Module
{
    public final Setting rotate;
    private final Setting multiPlace;
    public final Setting targetRange;
    private final Timer delayTimer;
    private int progress;
    public final Setting packet;
    private final Setting delay;
    private final Setting maxTargetSpeed;
    public final Setting placeRange;
    
    public AnvilAura() {
        super("AnvilAura", "Useless", Category.COMBAT);
        this.targetRange = this.add(new Setting("TargetRange", 5.0f, 0.0f, 10.0f));
        this.placeRange = this.add(new Setting("PlaceRange", 5.0f, 0.0f, 10.0f));
        this.rotate = this.add(new Setting("Rotate", true));
        this.packet = this.add(new Setting("Packet", true));
        this.delay = this.add(new Setting("Delay", 50, 0, 2000));
        this.multiPlace = this.add(new Setting("MultiPlace", 1, 1, 8));
        this.maxTargetSpeed = this.add(new Setting("MaxTargetSpeed", 10.0, 0.0, 30.0));
        this.delayTimer = new Timer();
        this.progress = 0;
    }
    
    @Override
    public void onTick() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        me/rebirthclient/mod/modules/impl/combat/AnvilAura.delayTimer:Lme/rebirthclient/api/util/Timer;
        //     4: aload_0        
        //     5: getfield        me/rebirthclient/mod/modules/impl/combat/AnvilAura.delay:Lme/rebirthclient/mod/modules/settings/Setting;
        //     8: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    11: checkcast       Ljava/lang/Integer;
        //    14: invokevirtual   java/lang/Integer.intValue:()I
        //    17: i2l            
        //    18: invokevirtual   me/rebirthclient/api/util/Timer.passedMs:(J)Z
        //    21: ifne            25
        //    24: return         
        //    25: aload_0        
        //    26: iconst_0       
        //    27: putfield        me/rebirthclient/mod/modules/impl/combat/AnvilAura.progress:I
        //    30: getstatic       me/rebirthclient/mod/modules/impl/combat/AnvilAura.mc:Lnet/minecraft/client/Minecraft;
        //    33: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //    36: getfield        net/minecraft/client/multiplayer/WorldClient.playerEntities:Ljava/util/List;
        //    39: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    44: astore_1       
        //    45: aload_1        
        //    46: invokeinterface java/util/Iterator.hasNext:()Z
        //    51: ifeq            177
        //    54: aload_1        
        //    55: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    60: checkcast       Lnet/minecraft/entity/player/EntityPlayer;
        //    63: astore_2       
        //    64: getstatic       me/rebirthclient/api/managers/Managers.SPEED:Lme/rebirthclient/api/managers/impl/SpeedManager;
        //    67: aload_2        
        //    68: invokevirtual   me/rebirthclient/api/managers/impl/SpeedManager.getPlayerSpeed:(Lnet/minecraft/entity/player/EntityPlayer;)D
        //    71: aload_0        
        //    72: getfield        me/rebirthclient/mod/modules/impl/combat/AnvilAura.maxTargetSpeed:Lme/rebirthclient/mod/modules/settings/Setting;
        //    75: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    78: checkcast       Ljava/lang/Double;
        //    81: invokevirtual   java/lang/Double.doubleValue:()D
        //    84: dcmpl          
        //    85: ifgt            45
        //    88: aload_2        
        //    89: aload_0        
        //    90: getfield        me/rebirthclient/mod/modules/impl/combat/AnvilAura.targetRange:Lme/rebirthclient/mod/modules/settings/Setting;
        //    93: invokevirtual   me/rebirthclient/mod/modules/settings/Setting.getValue:()Ljava/lang/Object;
        //    96: checkcast       Ljava/lang/Float;
        //    99: invokevirtual   java/lang/Float.floatValue:()F
        //   102: f2d            
        //   103: invokestatic    me/rebirthclient/api/util/EntityUtil.invalid:(Lnet/minecraft/entity/Entity;D)Z
        //   106: ifeq            110
        //   109: return         
        //   110: aload_2        
        //   111: invokestatic    me/rebirthclient/api/util/EntityUtil.getEntityPos:(Lnet/minecraft/entity/Entity;)Lnet/minecraft/util/math/BlockPos;
        //   114: astore_3       
        //   115: getstatic       me/rebirthclient/mod/modules/impl/combat/AnvilAura.mc:Lnet/minecraft/client/Minecraft;
        //   118: getfield        net/minecraft/client/Minecraft.world:Lnet/minecraft/client/multiplayer/WorldClient;
        //   121: aload_3        
        //   122: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //   125: invokevirtual   net/minecraft/client/multiplayer/WorldClient.isAirBlock:(Lnet/minecraft/util/math/BlockPos;)Z
        //   128: ifne            132
        //   131: return         
        //   132: bipush          10
        //   134: istore          4
        //   136: iload           4
        //   138: iconst_1       
        //   139: if_icmple       176
        //   142: aload_0        
        //   143: aload_3        
        //   144: iload           4
        //   146: invokevirtual   net/minecraft/util/math/BlockPos.up:(I)Lnet/minecraft/util/math/BlockPos;
        //   149: aload_3        
        //   150: invokevirtual   net/minecraft/util/math/BlockPos.up:()Lnet/minecraft/util/math/BlockPos;
        //   153: ifeq            159
        //   156: goto            172
        //   159: aload_0        
        //   160: aload_3        
        //   161: iload           4
        //   163: invokevirtual   net/minecraft/util/math/BlockPos.up:(I)Lnet/minecraft/util/math/BlockPos;
        //   166: invokespecial   me/rebirthclient/mod/modules/impl/combat/AnvilAura.placeAnvil:(Lnet/minecraft/util/math/BlockPos;)V
        //   169: goto            176
        //   172: iinc            4, -1
        //   175: return         
        //   176: return         
        //   177: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0176 (coming from #0169).
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
    
    private void placeAnvil(final BlockPos blockPos) {
        if (this.progress >= (int)this.multiPlace.getValue()) {
            return;
        }
        if (InventoryUtil.findHotbarBlock(Blocks.ANVIL) == -1) {
            return;
        }
        if (blockPos == 0) {
            return;
        }
        final int currentItem = AnvilAura.mc.player.inventory.currentItem;
        this.delayTimer.reset();
        ++this.progress;
        InventoryUtil.doSwap(InventoryUtil.findHotbarBlock(Blocks.ANVIL));
        BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, (boolean)this.rotate.getValue(), (boolean)this.packet.getValue());
        InventoryUtil.doSwap(currentItem);
    }
}
