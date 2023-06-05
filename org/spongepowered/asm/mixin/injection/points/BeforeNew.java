//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.points;

import org.spongepowered.asm.mixin.injection.*;
import com.google.common.base.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.lib.tree.*;
import java.util.*;

@InjectionPoint.AtCode("NEW")
public class BeforeNew extends InjectionPoint
{
    private final String target;
    private final String desc;
    private final int ordinal;
    
    public BeforeNew(final InjectionPointData injectionPointData) {
        super(injectionPointData);
        this.ordinal = injectionPointData.getOrdinal();
        final MemberInfo andValidate = MemberInfo.parseAndValidate(Strings.emptyToNull(injectionPointData.get("class", injectionPointData.get("target", "")).replace('.', '/')), injectionPointData.getContext());
        this.target = andValidate.toCtorType();
        this.desc = andValidate.toCtorDesc();
    }
    
    public boolean hasDescriptor() {
        return this.desc != null;
    }
    
    public boolean find(final String p0, final InsnList p1, final Collection p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore          4
        //     3: iconst_0       
        //     4: istore          5
        //     6: new             Ljava/util/ArrayList;
        //     9: dup            
        //    10: invokespecial   java/util/ArrayList.<init>:()V
        //    13: astore          6
        //    15: aload_0        
        //    16: getfield        org/spongepowered/asm/mixin/injection/points/BeforeNew.desc:Ljava/lang/String;
        //    19: ifnull          27
        //    22: aload           6
        //    24: goto            28
        //    27: aload_3        
        //    28: astore          7
        //    30: aload_2        
        //    31: invokevirtual   org/spongepowered/asm/lib/tree/InsnList.iterator:()Ljava/util/ListIterator;
        //    34: astore          8
        //    36: aload           8
        //    38: invokeinterface java/util/ListIterator.hasNext:()Z
        //    43: ifeq            133
        //    46: aload           8
        //    48: invokeinterface java/util/ListIterator.next:()Ljava/lang/Object;
        //    53: checkcast       Lorg/spongepowered/asm/lib/tree/AbstractInsnNode;
        //    56: astore          9
        //    58: aload           9
        //    60: instanceof      Lorg/spongepowered/asm/lib/tree/TypeInsnNode;
        //    63: ifeq            130
        //    66: aload           9
        //    68: invokevirtual   org/spongepowered/asm/lib/tree/AbstractInsnNode.getOpcode:()I
        //    71: sipush          187
        //    74: if_icmpne       130
        //    77: aload_0        
        //    78: aload           9
        //    80: checkcast       Lorg/spongepowered/asm/lib/tree/TypeInsnNode;
        //    83: ifnull          130
        //    86: aload_0        
        //    87: getfield        org/spongepowered/asm/mixin/injection/points/BeforeNew.ordinal:I
        //    90: iconst_m1      
        //    91: if_icmpeq       103
        //    94: aload_0        
        //    95: getfield        org/spongepowered/asm/mixin/injection/points/BeforeNew.ordinal:I
        //    98: iload           5
        //   100: if_icmpne       127
        //   103: aload           7
        //   105: aload           9
        //   107: invokeinterface java/util/Collection.add:(Ljava/lang/Object;)Z
        //   112: pop            
        //   113: aload_0        
        //   114: getfield        org/spongepowered/asm/mixin/injection/points/BeforeNew.desc:Ljava/lang/String;
        //   117: ifnonnull       124
        //   120: iconst_1       
        //   121: goto            125
        //   124: iconst_0       
        //   125: istore          4
        //   127: iinc            5, 1
        //   130: goto            36
        //   133: aload_0        
        //   134: getfield        org/spongepowered/asm/mixin/injection/points/BeforeNew.desc:Ljava/lang/String;
        //   137: ifnull          193
        //   140: aload           6
        //   142: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   147: astore          9
        //   149: aload           9
        //   151: invokeinterface java/util/Iterator.hasNext:()Z
        //   156: ifeq            193
        //   159: aload           9
        //   161: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   166: checkcast       Lorg/spongepowered/asm/lib/tree/TypeInsnNode;
        //   169: astore          10
        //   171: aload_0        
        //   172: aload_2        
        //   173: aload           10
        //   175: ifeq            190
        //   178: aload_3        
        //   179: aload           10
        //   181: invokeinterface java/util/Collection.add:(Ljava/lang/Object;)Z
        //   186: pop            
        //   187: iconst_1       
        //   188: istore          4
        //   190: goto            149
        //   193: iload           4
        //   195: ireturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0149 (coming from #0190).
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
}
