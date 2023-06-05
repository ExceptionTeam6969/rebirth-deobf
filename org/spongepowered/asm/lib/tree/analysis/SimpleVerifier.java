//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.tree.analysis;

import org.spongepowered.asm.lib.*;
import java.util.*;

public class SimpleVerifier extends BasicVerifier
{
    private final Type currentClass;
    private final Type currentSuperClass;
    private final List currentClassInterfaces;
    private final boolean isInterface;
    private ClassLoader loader;
    
    public SimpleVerifier() {
        this(null, null, false);
    }
    
    public SimpleVerifier(final Type type, final Type type2, final boolean b) {
        this(type, type2, null, b);
    }
    
    public SimpleVerifier(final Type type, final Type type2, final List list, final boolean b) {
        this(327680, type, type2, list, b);
    }
    
    protected SimpleVerifier(final int n, final Type currentClass, final Type currentSuperClass, final List currentClassInterfaces, final boolean isInterface) {
        super(n);
        this.loader = this.getClass().getClassLoader();
        this.currentClass = currentClass;
        this.currentSuperClass = currentSuperClass;
        this.currentClassInterfaces = currentClassInterfaces;
        this.isInterface = isInterface;
    }
    
    public void setClassLoader(final ClassLoader loader) {
        this.loader = loader;
    }
    
    public BasicValue newValue(final Type type) {
        if (type == null) {
            return BasicValue.UNINITIALIZED_VALUE;
        }
        final boolean b = type.getSort() == 9;
        if (b) {
            switch (type.getElementType().getSort()) {
                case 1:
                case 2:
                case 3:
                case 4: {
                    return new BasicValue(type);
                }
            }
        }
        BasicValue value = super.newValue(type);
        if (BasicValue.REFERENCE_VALUE.equals((Object)value)) {
            if (b) {
                String s = this.newValue(type.getElementType()).getType().getDescriptor();
                for (int i = 0; i < type.getDimensions(); ++i) {
                    s = '[' + s;
                }
                value = new BasicValue(Type.getType(s));
            }
            else {
                value = new BasicValue(type);
            }
        }
        return value;
    }
    
    protected boolean isArrayValue(final BasicValue basicValue) {
        final Type type = basicValue.getType();
        return type != null && ("Lnull;".equals(type.getDescriptor()) || type.getSort() == 9);
    }
    
    protected BasicValue getElementValue(final BasicValue basicValue) throws AnalyzerException {
        final Type type = basicValue.getType();
        if (type != null) {
            if (type.getSort() == 9) {
                return this.newValue(Type.getType(type.getDescriptor().substring(1)));
            }
            if ("Lnull;".equals(type.getDescriptor())) {
                return basicValue;
            }
        }
        throw new Error("Internal error");
    }
    
    protected boolean isSubTypeOf(final BasicValue basicValue, final BasicValue basicValue2) {
        final Type type = basicValue2.getType();
        final Type type2 = basicValue.getType();
        switch (type.getSort()) {
            case 5:
            case 6:
            case 7:
            case 8: {
                return type2.equals(type);
            }
            case 9:
            case 10: {
                return "Lnull;".equals(type2.getDescriptor()) || ((type2.getSort() == 10 || type2.getSort() == 9) && this.isAssignableFrom(type, type2));
            }
            default: {
                throw new Error("Internal error");
            }
        }
    }
    
    public BasicValue merge(final BasicValue p0, final BasicValue p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aload_2        
        //     2: invokevirtual   org/spongepowered/asm/lib/tree/analysis/BasicValue.equals:(Ljava/lang/Object;)Z
        //     5: ifne            149
        //     8: aload_1        
        //     9: invokevirtual   org/spongepowered/asm/lib/tree/analysis/BasicValue.getType:()Lorg/spongepowered/asm/lib/Type;
        //    12: astore_3       
        //    13: aload_2        
        //    14: invokevirtual   org/spongepowered/asm/lib/tree/analysis/BasicValue.getType:()Lorg/spongepowered/asm/lib/Type;
        //    17: astore          4
        //    19: aload_3        
        //    20: ifnull          145
        //    23: aload_3        
        //    24: invokevirtual   org/spongepowered/asm/lib/Type.getSort:()I
        //    27: bipush          10
        //    29: if_icmpeq       41
        //    32: aload_3        
        //    33: invokevirtual   org/spongepowered/asm/lib/Type.getSort:()I
        //    36: bipush          9
        //    38: if_icmpne       145
        //    41: aload           4
        //    43: ifnull          145
        //    46: aload           4
        //    48: invokevirtual   org/spongepowered/asm/lib/Type.getSort:()I
        //    51: bipush          10
        //    53: if_icmpeq       66
        //    56: aload           4
        //    58: invokevirtual   org/spongepowered/asm/lib/Type.getSort:()I
        //    61: bipush          9
        //    63: if_icmpne       145
        //    66: ldc             "Lnull;"
        //    68: aload_3        
        //    69: invokevirtual   org/spongepowered/asm/lib/Type.getDescriptor:()Ljava/lang/String;
        //    72: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    75: ifeq            80
        //    78: aload_2        
        //    79: areturn        
        //    80: ldc             "Lnull;"
        //    82: aload           4
        //    84: invokevirtual   org/spongepowered/asm/lib/Type.getDescriptor:()Ljava/lang/String;
        //    87: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    90: ifeq            95
        //    93: aload_1        
        //    94: areturn        
        //    95: aload_0        
        //    96: aload_3        
        //    97: aload           4
        //    99: ifeq            104
        //   102: aload_1        
        //   103: areturn        
        //   104: aload_0        
        //   105: aload           4
        //   107: aload_3        
        //   108: ifeq            113
        //   111: aload_2        
        //   112: areturn        
        //   113: aload_3        
        //   114: ifnull          122
        //   117: aload_0        
        //   118: aload_3        
        //   119: ifnull          126
        //   122: getstatic       org/spongepowered/asm/lib/tree/analysis/BasicValue.REFERENCE_VALUE:Lorg/spongepowered/asm/lib/tree/analysis/BasicValue;
        //   125: areturn        
        //   126: aload_0        
        //   127: aload_3        
        //   128: invokevirtual   org/spongepowered/asm/lib/tree/analysis/SimpleVerifier.getSuperClass:(Lorg/spongepowered/asm/lib/Type;)Lorg/spongepowered/asm/lib/Type;
        //   131: astore_3       
        //   132: aload_0        
        //   133: aload_3        
        //   134: aload           4
        //   136: ifeq            113
        //   139: aload_0        
        //   140: aload_3        
        //   141: invokevirtual   org/spongepowered/asm/lib/tree/analysis/SimpleVerifier.newValue:(Lorg/spongepowered/asm/lib/Type;)Lorg/spongepowered/asm/lib/tree/analysis/BasicValue;
        //   144: areturn        
        //   145: getstatic       org/spongepowered/asm/lib/tree/analysis/BasicValue.UNINITIALIZED_VALUE:Lorg/spongepowered/asm/lib/tree/analysis/BasicValue;
        //   148: areturn        
        //   149: aload_1        
        //   150: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0122 (coming from #0119).
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
    
    protected Type getSuperClass(final Type type) {
        if (this.currentClass != null && type.equals(this.currentClass)) {
            return this.currentSuperClass;
        }
        final Class superclass = this.getClass(type).getSuperclass();
        return (superclass == null) ? null : Type.getType(superclass);
    }
    
    protected Class getClass(final Type type) {
        try {
            if (type.getSort() == 9) {
                return Class.forName(type.getDescriptor().replace('/', '.'), false, this.loader);
            }
            return Class.forName(type.getClassName(), false, this.loader);
        }
        catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex.toString());
        }
    }
    
    public Value merge(final Value value, final Value value2) {
        return (Value)this.merge((BasicValue)value, (BasicValue)value2);
    }
    
    public Value newValue(final Type type) {
        return (Value)this.newValue(type);
    }
}
