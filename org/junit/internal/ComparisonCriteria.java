//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal;

import org.junit.*;
import java.lang.reflect.*;

public abstract class ComparisonCriteria
{
    public void arrayEquals(final String p0, final Object p1, final Object p2) throws ArrayComparisonFailure {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aload_3        
        //     2: if_acmpne       6
        //     5: return         
        //     6: aload_1        
        //     7: ifnonnull       15
        //    10: ldc             ""
        //    12: goto            34
        //    15: new             Ljava/lang/StringBuilder;
        //    18: dup            
        //    19: invokespecial   java/lang/StringBuilder.<init>:()V
        //    22: aload_1        
        //    23: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    26: ldc             ": "
        //    28: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    31: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    34: astore          4
        //    36: aload_0        
        //    37: aload_2        
        //    38: aload_3        
        //    39: aload           4
        //    41: invokespecial   org/junit/internal/ComparisonCriteria.assertArraysAreSameLength:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;)I
        //    44: istore          5
        //    46: iconst_0       
        //    47: istore          6
        //    49: iload           6
        //    51: iload           5
        //    53: if_icmpge       141
        //    56: aload_2        
        //    57: iload           6
        //    59: invokestatic    java/lang/reflect/Array.get:(Ljava/lang/Object;I)Ljava/lang/Object;
        //    62: astore          7
        //    64: aload_3        
        //    65: iload           6
        //    67: invokestatic    java/lang/reflect/Array.get:(Ljava/lang/Object;I)Ljava/lang/Object;
        //    70: astore          8
        //    72: aload_0        
        //    73: aload           7
        //    75: ifnull          108
        //    78: aload_0        
        //    79: aload           8
        //    81: ifnull          108
        //    84: aload_0        
        //    85: aload_1        
        //    86: aload           7
        //    88: aload           8
        //    90: invokevirtual   org/junit/internal/ComparisonCriteria.arrayEquals:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //    93: goto            135
        //    96: astore          9
        //    98: aload           9
        //   100: iload           6
        //   102: invokevirtual   org/junit/internal/ArrayComparisonFailure.addDimension:(I)V
        //   105: aload           9
        //   107: athrow         
        //   108: aload_0        
        //   109: aload           7
        //   111: aload           8
        //   113: invokevirtual   org/junit/internal/ComparisonCriteria.assertElementsEqual:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   116: goto            135
        //   119: astore          9
        //   121: new             Lorg/junit/internal/ArrayComparisonFailure;
        //   124: dup            
        //   125: aload           4
        //   127: aload           9
        //   129: iload           6
        //   131: invokespecial   org/junit/internal/ArrayComparisonFailure.<init>:(Ljava/lang/String;Ljava/lang/AssertionError;I)V
        //   134: athrow         
        //   135: iinc            6, 1
        //   138: goto            49
        //   141: return         
        //    Exceptions:
        //  throws org.junit.internal.ArrayComparisonFailure
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                       
        //  -----  -----  -----  -----  -------------------------------------------
        //  84     93     96     108    Lorg/junit/internal/ArrayComparisonFailure;
        //  108    116    119    135    Ljava/lang/AssertionError;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0049 (coming from #0138).
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
    
    private int assertArraysAreSameLength(final Object o, final Object o2, final String s) {
        if (o == null) {
            Assert.fail(s + "expected array was null");
        }
        if (o2 == null) {
            Assert.fail(s + "actual array was null");
        }
        final int length = Array.getLength(o2);
        final int length2 = Array.getLength(o);
        if (length != length2) {
            Assert.fail(s + "array lengths differed, expected.length=" + length2 + " actual.length=" + length);
        }
        return length2;
    }
    
    protected abstract void assertElementsEqual(final Object p0, final Object p1);
}
