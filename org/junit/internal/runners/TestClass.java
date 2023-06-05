//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners;

import org.junit.*;
import java.util.*;
import java.lang.reflect.*;

@Deprecated
public class TestClass
{
    private final Class fClass;
    
    public TestClass(final Class fClass) {
        this.fClass = fClass;
    }
    
    public List getTestMethods() {
        return this.getAnnotatedMethods(Test.class);
    }
    
    List getBefores() {
        return this.getAnnotatedMethods(BeforeClass.class);
    }
    
    List getAfters() {
        return this.getAnnotatedMethods(AfterClass.class);
    }
    
    public List getAnnotatedMethods(final Class p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore_2       
        //     8: aload_0        
        //     9: aload_0        
        //    10: getfield        org/junit/internal/runners/TestClass.fClass:Ljava/lang/Class;
        //    13: invokespecial   org/junit/internal/runners/TestClass.getSuperClasses:(Ljava/lang/Class;)Ljava/util/List;
        //    16: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    21: astore_3       
        //    22: aload_3        
        //    23: invokeinterface java/util/Iterator.hasNext:()Z
        //    28: ifeq            113
        //    31: aload_3        
        //    32: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    37: checkcast       Ljava/lang/Class;
        //    40: astore          4
        //    42: aload           4
        //    44: invokevirtual   java/lang/Class.getDeclaredMethods:()[Ljava/lang/reflect/Method;
        //    47: astore          5
        //    49: aload           5
        //    51: astore          6
        //    53: aload           6
        //    55: arraylength    
        //    56: istore          7
        //    58: iconst_0       
        //    59: istore          8
        //    61: iload           8
        //    63: iload           7
        //    65: if_icmpge       110
        //    68: aload           6
        //    70: iload           8
        //    72: aaload         
        //    73: astore          9
        //    75: aload           9
        //    77: aload_1        
        //    78: invokevirtual   java/lang/reflect/Method.getAnnotation:(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
        //    81: astore          10
        //    83: aload           10
        //    85: ifnull          104
        //    88: aload_0        
        //    89: aload           9
        //    91: aload_2        
        //    92: ifeq            104
        //    95: aload_2        
        //    96: aload           9
        //    98: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   103: pop            
        //   104: iinc            8, 1
        //   107: goto            61
        //   110: goto            22
        //   113: aload_0        
        //   114: aload_1        
        //   115: ifne            122
        //   118: aload_2        
        //   119: invokestatic    java/util/Collections.reverse:(Ljava/util/List;)V
        //   122: aload_2        
        //   123: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0104 (coming from #0092).
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
    
    private List getSuperClasses(final Class clazz) {
        final ArrayList<Class> list = new ArrayList<Class>();
        for (Class superclass = clazz; superclass != null; superclass = superclass.getSuperclass()) {
            list.add(superclass);
        }
        return list;
    }
    
    public Constructor getConstructor() throws SecurityException, NoSuchMethodException {
        return this.fClass.getConstructor((Class[])new Class[0]);
    }
    
    public Class getJavaClass() {
        return this.fClass;
    }
    
    public String getName() {
        return this.fClass.getName();
    }
}
