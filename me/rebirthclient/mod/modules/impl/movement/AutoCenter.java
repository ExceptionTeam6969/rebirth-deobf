//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AutoCenter extends Module
{
    private final Setting reset;
    public static AutoCenter INSTANCE;
    
    public AutoCenter() {
        super("AutoCenter", "move center", Category.MOVEMENT);
        this.reset = this.add(new Setting("Reset", true));
        AutoCenter.INSTANCE = this;
    }
    
    @Override
    public void onTick() {
        this.doCenter();
    }
    
    @Override
    public void onEnable() {
        this.doCenter();
    }
    
    private void doCenter() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: invokevirtual   me/rebirthclient/mod/modules/impl/exploit/Clip.isOn:()Z
        //     6: ifne            19
        //     9: aload_0        
        //    10: getstatic       me/rebirthclient/mod/modules/impl/movement/AutoCenter.mc:Lnet/minecraft/client/Minecraft;
        //    13: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    16: ifne            24
        //    19: aload_0        
        //    20: invokevirtual   me/rebirthclient/mod/modules/impl/movement/AutoCenter.disable:()V
        //    23: return         
        //    24: invokestatic    me/rebirthclient/api/util/EntityUtil.getPlayerPos:()Lnet/minecraft/util/math/BlockPos;
        //    27: astore_1       
        //    28: getstatic       me/rebirthclient/mod/modules/impl/movement/AutoCenter.mc:Lnet/minecraft/client/Minecraft;
        //    31: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    34: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //    37: aload_1        
        //    38: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //    41: i2d            
        //    42: dsub           
        //    43: ldc2_w          0.5
        //    46: dsub           
        //    47: ldc2_w          0.2
        //    50: dcmpg          
        //    51: ifgt            139
        //    54: getstatic       me/rebirthclient/mod/modules/impl/movement/AutoCenter.mc:Lnet/minecraft/client/Minecraft;
        //    57: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    60: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //    63: aload_1        
        //    64: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //    67: i2d            
        //    68: dsub           
        //    69: ldc2_w          0.5
        //    72: dsub           
        //    73: ldc2_w          -0.2
        //    76: dcmpl          
        //    77: iflt            139
        //    80: getstatic       me/rebirthclient/mod/modules/impl/movement/AutoCenter.mc:Lnet/minecraft/client/Minecraft;
        //    83: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //    86: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //    89: aload_1        
        //    90: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //    93: i2d            
        //    94: dsub           
        //    95: ldc2_w          0.5
        //    98: dsub           
        //    99: ldc2_w          0.2
        //   102: dcmpg          
        //   103: ifgt            139
        //   106: getstatic       me/rebirthclient/mod/modules/impl/movement/AutoCenter.mc:Lnet/minecraft/client/Minecraft;
        //   109: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   112: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   115: ldc2_w          0.5
        //   118: dsub           
        //   119: aload_1        
        //   120: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //   123: i2d            
        //   124: dsub           
        //   125: ldc2_w          -0.2
        //   128: dcmpl          
        //   129: iflt            139
        //   132: aload_0        
        //   133: invokevirtual   me/rebirthclient/mod/modules/impl/movement/AutoCenter.disable:()V
        //   136: goto            203
        //   139: getstatic       me/rebirthclient/mod/modules/impl/movement/AutoCenter.mc:Lnet/minecraft/client/Minecraft;
        //   142: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   145: aload_1        
        //   146: invokevirtual   net/minecraft/util/math/BlockPos.getX:()I
        //   149: i2d            
        //   150: ldc2_w          0.5
        //   153: dadd           
        //   154: getstatic       me/rebirthclient/mod/modules/impl/movement/AutoCenter.mc:Lnet/minecraft/client/Minecraft;
        //   157: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   160: getfield        net/minecraft/client/entity/EntityPlayerSP.posX:D
        //   163: dsub           
        //   164: ldc2_w          2.0
        //   167: ddiv           
        //   168: putfield        net/minecraft/client/entity/EntityPlayerSP.motionX:D
        //   171: getstatic       me/rebirthclient/mod/modules/impl/movement/AutoCenter.mc:Lnet/minecraft/client/Minecraft;
        //   174: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   177: aload_1        
        //   178: invokevirtual   net/minecraft/util/math/BlockPos.getZ:()I
        //   181: i2d            
        //   182: ldc2_w          0.5
        //   185: dadd           
        //   186: getstatic       me/rebirthclient/mod/modules/impl/movement/AutoCenter.mc:Lnet/minecraft/client/Minecraft;
        //   189: getfield        net/minecraft/client/Minecraft.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   192: getfield        net/minecraft/client/entity/EntityPlayerSP.posZ:D
        //   195: dsub           
        //   196: ldc2_w          2.0
        //   199: ddiv           
        //   200: putfield        net/minecraft/client/entity/EntityPlayerSP.motionZ:D
        //   203: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0019 (coming from #0016).
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
    
    @SubscribeEvent
    public void onInput(final InputUpdateEvent inputUpdateEvent) {
        if (inputUpdateEvent.getMovementInput() instanceof MovementInputFromOptions) {
            final MovementInput movementInput = inputUpdateEvent.getMovementInput();
            movementInput.moveForward = 0.0f;
            movementInput.moveStrafe = 0.0f;
            movementInput.forwardKeyDown = false;
            movementInput.backKeyDown = false;
            movementInput.leftKeyDown = false;
            movementInput.rightKeyDown = false;
        }
    }
    
    @Override
    public void onDisable() {
        if (!(boolean)this.reset.getValue()) {
            return;
        }
        AutoCenter.mc.player.motionX = 0.0;
        AutoCenter.mc.player.motionZ = 0.0;
    }
}
