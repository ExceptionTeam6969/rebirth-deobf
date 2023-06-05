//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners.statements;

import org.junit.runners.model.*;
import java.util.*;

public class RunAfters extends Statement
{
    private final Statement fNext;
    private final Object fTarget;
    private final List fAfters;
    
    public RunAfters(final Statement fNext, final List fAfters, final Object fTarget) {
        this.fNext = fNext;
        this.fAfters = fAfters;
        this.fTarget = fTarget;
    }
    
    @Override
    public void evaluate() throws Throwable {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore_1       
        //     8: aload_0        
        //     9: getfield        org/junit/internal/runners/statements/RunAfters.fNext:Lorg/junit/runners/model/Statement;
        //    12: invokevirtual   org/junit/runners/model/Statement.evaluate:()V
        //    15: aload_0        
        //    16: getfield        org/junit/internal/runners/statements/RunAfters.fAfters:Ljava/util/List;
        //    19: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    24: astore_2       
        //    25: aload_2        
        //    26: invokeinterface java/util/Iterator.hasNext:()Z
        //    31: ifeq            74
        //    34: aload_2        
        //    35: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    40: checkcast       Lorg/junit/runners/model/FrameworkMethod;
        //    43: astore_3       
        //    44: aload_3        
        //    45: aload_0        
        //    46: getfield        org/junit/internal/runners/statements/RunAfters.fTarget:Ljava/lang/Object;
        //    49: iconst_0       
        //    50: anewarray       Ljava/lang/Object;
        //    53: invokevirtual   org/junit/runners/model/FrameworkMethod.invokeExplosively:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    56: pop            
        //    57: goto            71
        //    60: astore          4
        //    62: aload_1        
        //    63: aload           4
        //    65: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    70: pop            
        //    71: goto            25
        //    74: goto            217
        //    77: astore_2       
        //    78: aload_1        
        //    79: aload_2        
        //    80: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    85: pop            
        //    86: aload_0        
        //    87: getfield        org/junit/internal/runners/statements/RunAfters.fAfters:Ljava/util/List;
        //    90: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    95: astore_2       
        //    96: aload_2        
        //    97: invokeinterface java/util/Iterator.hasNext:()Z
        //   102: ifeq            145
        //   105: aload_2        
        //   106: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   111: checkcast       Lorg/junit/runners/model/FrameworkMethod;
        //   114: astore_3       
        //   115: aload_3        
        //   116: aload_0        
        //   117: getfield        org/junit/internal/runners/statements/RunAfters.fTarget:Ljava/lang/Object;
        //   120: iconst_0       
        //   121: anewarray       Ljava/lang/Object;
        //   124: invokevirtual   org/junit/runners/model/FrameworkMethod.invokeExplosively:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //   127: pop            
        //   128: goto            142
        //   131: astore          4
        //   133: aload_1        
        //   134: aload           4
        //   136: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   141: pop            
        //   142: goto            96
        //   145: goto            217
        //   148: astore          5
        //   150: aload_0        
        //   151: getfield        org/junit/internal/runners/statements/RunAfters.fAfters:Ljava/util/List;
        //   154: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   159: astore          6
        //   161: aload           6
        //   163: invokeinterface java/util/Iterator.hasNext:()Z
        //   168: ifeq            214
        //   171: aload           6
        //   173: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   178: checkcast       Lorg/junit/runners/model/FrameworkMethod;
        //   181: astore          7
        //   183: aload           7
        //   185: aload_0        
        //   186: getfield        org/junit/internal/runners/statements/RunAfters.fTarget:Ljava/lang/Object;
        //   189: iconst_0       
        //   190: anewarray       Ljava/lang/Object;
        //   193: invokevirtual   org/junit/runners/model/FrameworkMethod.invokeExplosively:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //   196: pop            
        //   197: goto            211
        //   200: astore          8
        //   202: aload_1        
        //   203: aload           8
        //   205: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   210: pop            
        //   211: goto            161
        //   214: aload           5
        //   216: athrow         
        //   217: aload_1        
        //   218: invokestatic    org/junit/runners/model/MultipleFailureException.assertEmpty:(Ljava/util/List;)V
        //   221: return         
        //    Exceptions:
        //  throws java.lang.Throwable
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  44     57     60     71     Ljava/lang/Throwable;
        //  8      15     77     148    Ljava/lang/Throwable;
        //  115    128    131    142    Ljava/lang/Throwable;
        //  183    197    200    211    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.util.ConcurrentModificationException
        //     at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:911)
        //     at java.util.ArrayList$Itr.next(ArrayList.java:861)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2863)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
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
}
