//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.util.perf.*;
import org.spongepowered.asm.mixin.transformer.meta.*;
import org.spongepowered.asm.mixin.transformer.throwables.*;
import org.spongepowered.asm.mixin.extensibility.*;
import org.spongepowered.asm.util.*;
import org.spongepowered.asm.lib.tree.*;
import java.util.*;
import com.google.common.base.*;
import org.spongepowered.asm.lib.*;
import org.apache.logging.log4j.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

class MixinPreProcessorStandard
{
    private static final Logger logger;
    protected final MixinInfo mixin;
    protected final MixinInfo.MixinClassNode classNode;
    protected final MixinEnvironment env;
    protected final Profiler profiler;
    private final boolean verboseLogging;
    private final boolean strictUnique;
    private boolean prepared;
    private boolean attached;
    
    MixinPreProcessorStandard(final MixinInfo mixin, final MixinInfo.MixinClassNode classNode) {
        this.profiler = MixinEnvironment.getProfiler();
        this.mixin = mixin;
        this.classNode = classNode;
        this.env = mixin.getParent().getEnvironment();
        this.verboseLogging = this.env.getOption(MixinEnvironment.Option.DEBUG_VERBOSE);
        this.strictUnique = this.env.getOption(MixinEnvironment.Option.DEBUG_UNIQUE);
    }
    
    final MixinPreProcessorStandard prepare() {
        if (this.prepared) {
            return this;
        }
        this.prepared = true;
        final Profiler.Section begin = this.profiler.begin("prepare");
        for (final MixinInfo.MixinMethodNode mixinMethodNode : this.classNode.mixinMethods) {
            this.prepareMethod(mixinMethodNode, this.mixin.getClassInfo().findMethod((MethodNode)mixinMethodNode));
        }
        final Iterator<FieldNode> iterator2 = (Iterator<FieldNode>)this.classNode.fields.iterator();
        while (iterator2.hasNext()) {
            this.prepareField(iterator2.next());
        }
        begin.end();
        return this;
    }
    
    protected void prepareMethod(final MixinInfo.MixinMethodNode mixinMethodNode, final ClassInfo.Method method) {
        this.prepareShadow(mixinMethodNode, method);
        this.prepareSoftImplements(mixinMethodNode, method);
    }
    
    protected void prepareShadow(final MixinInfo.MixinMethodNode mixinMethodNode, final ClassInfo.Method method) {
        final AnnotationNode visible = Annotations.getVisible((MethodNode)mixinMethodNode, Shadow.class);
        if (visible == null) {
            return;
        }
        final String s = (String)Annotations.getValue(visible, "prefix", Shadow.class);
        if (mixinMethodNode.name.startsWith(s)) {
            Annotations.setVisible((MethodNode)mixinMethodNode, MixinRenamed.class, "originalName", mixinMethodNode.name);
            mixinMethodNode.name = method.renameTo(mixinMethodNode.name.substring(s.length()));
        }
    }
    
    protected void prepareSoftImplements(final MixinInfo.MixinMethodNode mixinMethodNode, final ClassInfo.Method method) {
        final Iterator<InterfaceInfo> iterator = this.mixin.getSoftImplements().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().renameMethod((MethodNode)mixinMethodNode)) {
                method.renameTo(mixinMethodNode.name);
            }
        }
    }
    
    protected void prepareField(final FieldNode fieldNode) {
    }
    
    final MixinPreProcessorStandard conform(final TargetClassContext targetClassContext) {
        return this.conform(targetClassContext.getClassInfo());
    }
    
    final MixinPreProcessorStandard conform(final ClassInfo classInfo) {
        final Profiler.Section begin = this.profiler.begin("conform");
        for (final MixinInfo.MixinMethodNode mixinMethodNode : this.classNode.mixinMethods) {
            if (mixinMethodNode.isInjector()) {
                this.conformInjector(classInfo, mixinMethodNode, this.mixin.getClassInfo().findMethod((MethodNode)mixinMethodNode, 10));
            }
        }
        begin.end();
        return this;
    }
    
    private void conformInjector(final ClassInfo classInfo, final MixinInfo.MixinMethodNode mixinMethodNode, final ClassInfo.Method method) {
        classInfo.getMethodMapper().remapHandlerMethod(this.mixin, (MethodNode)mixinMethodNode, method);
    }
    
    MixinTargetContext createContextFor(final TargetClassContext targetClassContext) {
        final MixinTargetContext mixinTargetContext = new MixinTargetContext(this.mixin, (ClassNode)this.classNode, targetClassContext);
        this.conform(targetClassContext);
        this.attach(mixinTargetContext);
        return mixinTargetContext;
    }
    
    final MixinPreProcessorStandard attach(final MixinTargetContext mixinTargetContext) {
        if (this.attached) {
            throw new IllegalStateException("Preprocessor was already attached");
        }
        this.attached = true;
        final Profiler.Section begin = this.profiler.begin("attach");
        final Profiler.Section begin2 = this.profiler.begin("methods");
        this.attachMethods(mixinTargetContext);
        final Profiler.Section next = begin2.next("fields");
        this.attachFields(mixinTargetContext);
        final Profiler.Section next2 = next.next("transform");
        this.transform(mixinTargetContext);
        next2.end();
        begin.end();
        return this;
    }
    
    protected void attachMethods(final MixinTargetContext p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.classNode:Lorg/spongepowered/asm/mixin/transformer/MixinInfo$MixinClassNode;
        //     4: getfield        org/spongepowered/asm/mixin/transformer/MixinInfo$MixinClassNode.mixinMethods:Ljava/util/List;
        //     7: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    12: astore_2       
        //    13: aload_2        
        //    14: invokeinterface java/util/Iterator.hasNext:()Z
        //    19: ifeq            151
        //    22: aload_2        
        //    23: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    28: checkcast       Lorg/spongepowered/asm/mixin/transformer/MixinInfo$MixinMethodNode;
        //    31: astore_3       
        //    32: aload_0        
        //    33: aload_1        
        //    34: aload_3        
        //    35: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.validateMethod:(Lorg/spongepowered/asm/mixin/transformer/MixinTargetContext;Lorg/spongepowered/asm/mixin/transformer/MixinInfo$MixinMethodNode;)Z
        //    38: ifne            50
        //    41: aload_2        
        //    42: invokeinterface java/util/Iterator.remove:()V
        //    47: goto            13
        //    50: aload_0        
        //    51: aload_1        
        //    52: aload_3        
        //    53: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.attachInjectorMethod:(Lorg/spongepowered/asm/mixin/transformer/MixinTargetContext;Lorg/spongepowered/asm/mixin/transformer/MixinInfo$MixinMethodNode;)Z
        //    56: ifeq            67
        //    59: aload_1        
        //    60: aload_3        
        //    61: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.addMixinMethod:(Lorg/spongepowered/asm/lib/tree/MethodNode;)V
        //    64: goto            13
        //    67: aload_0        
        //    68: aload_1        
        //    69: aload_3        
        //    70: ifne            82
        //    73: aload_2        
        //    74: invokeinterface java/util/Iterator.remove:()V
        //    79: goto            13
        //    82: aload_0        
        //    83: aload_1        
        //    84: aload_3        
        //    85: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.attachShadowMethod:(Lorg/spongepowered/asm/mixin/transformer/MixinTargetContext;Lorg/spongepowered/asm/mixin/transformer/MixinInfo$MixinMethodNode;)Z
        //    88: ifeq            105
        //    91: aload_1        
        //    92: aload_3        
        //    93: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.addShadowMethod:(Lorg/spongepowered/asm/lib/tree/MethodNode;)V
        //    96: aload_2        
        //    97: invokeinterface java/util/Iterator.remove:()V
        //   102: goto            13
        //   105: aload_0        
        //   106: aload_1        
        //   107: aload_3        
        //   108: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.attachOverwriteMethod:(Lorg/spongepowered/asm/mixin/transformer/MixinTargetContext;Lorg/spongepowered/asm/mixin/transformer/MixinInfo$MixinMethodNode;)Z
        //   111: ifeq            122
        //   114: aload_1        
        //   115: aload_3        
        //   116: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.addMixinMethod:(Lorg/spongepowered/asm/lib/tree/MethodNode;)V
        //   119: goto            13
        //   122: aload_0        
        //   123: aload_1        
        //   124: aload_3        
        //   125: ifnull          137
        //   128: aload_2        
        //   129: invokeinterface java/util/Iterator.remove:()V
        //   134: goto            13
        //   137: aload_0        
        //   138: aload_1        
        //   139: aload_3        
        //   140: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.attachMethod:(Lorg/spongepowered/asm/mixin/transformer/MixinTargetContext;Lorg/spongepowered/asm/mixin/transformer/MixinInfo$MixinMethodNode;)V
        //   143: aload_1        
        //   144: aload_3        
        //   145: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.addMixinMethod:(Lorg/spongepowered/asm/lib/tree/MethodNode;)V
        //   148: goto            13
        //   151: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0013 (coming from #0148).
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
    
    protected boolean validateMethod(final MixinTargetContext mixinTargetContext, final MixinInfo.MixinMethodNode mixinMethodNode) {
        return true;
    }
    
    protected boolean attachInjectorMethod(final MixinTargetContext mixinTargetContext, final MixinInfo.MixinMethodNode mixinMethodNode) {
        return mixinMethodNode.isInjector();
    }
    
    protected boolean attachShadowMethod(final MixinTargetContext mixinTargetContext, final MixinInfo.MixinMethodNode mixinMethodNode) {
        return this.attachSpecialMethod(mixinTargetContext, mixinMethodNode, SpecialMethod.SHADOW);
    }
    
    protected boolean attachOverwriteMethod(final MixinTargetContext mixinTargetContext, final MixinInfo.MixinMethodNode mixinMethodNode) {
        return this.attachSpecialMethod(mixinTargetContext, mixinMethodNode, SpecialMethod.OVERWRITE);
    }
    
    protected boolean attachSpecialMethod(final MixinTargetContext mixinTargetContext, final MixinInfo.MixinMethodNode mixinMethodNode, final SpecialMethod specialMethod) {
        final AnnotationNode visibleAnnotation = mixinMethodNode.getVisibleAnnotation(specialMethod.annotation);
        if (visibleAnnotation == null) {
            return false;
        }
        if (specialMethod.isOverwrite) {
            this.checkMixinNotUnique(mixinMethodNode, specialMethod);
        }
        final ClassInfo.Method specialMethod2 = this.getSpecialMethod(mixinMethodNode, specialMethod);
        MethodNode methodNode = mixinTargetContext.findMethod((MethodNode)mixinMethodNode, visibleAnnotation);
        if (methodNode == null) {
            if (specialMethod.isOverwrite) {
                return false;
            }
            methodNode = mixinTargetContext.findRemappedMethod((MethodNode)mixinMethodNode);
            if (methodNode == null) {
                throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("%s method %s in %s was not located in the target class %s. %s%s", specialMethod, mixinMethodNode.name, this.mixin, mixinTargetContext.getTarget(), mixinTargetContext.getReferenceMapper().getStatus(), getDynamicInfo((MethodNode)mixinMethodNode)));
            }
            mixinMethodNode.name = specialMethod2.renameTo(methodNode.name);
        }
        if ("<init>".equals(methodNode.name)) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("Nice try! %s in %s cannot alias a constructor", mixinMethodNode.name, this.mixin));
        }
        if (!Bytecode.compareFlags((MethodNode)mixinMethodNode, methodNode, 8)) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("STATIC modifier of %s method %s in %s does not match the target", specialMethod, mixinMethodNode.name, this.mixin));
        }
        this.conformVisibility(mixinTargetContext, mixinMethodNode, specialMethod, methodNode);
        if (!methodNode.name.equals(mixinMethodNode.name)) {
            if (specialMethod.isOverwrite && (methodNode.access & 0x2) == 0x0) {
                throw new InvalidMixinException((IMixinInfo)this.mixin, "Non-private method cannot be aliased. Found " + methodNode.name);
            }
            mixinMethodNode.name = specialMethod2.renameTo(methodNode.name);
        }
        return true;
    }
    
    private void conformVisibility(final MixinTargetContext mixinTargetContext, final MixinInfo.MixinMethodNode mixinMethodNode, final SpecialMethod specialMethod, final MethodNode methodNode) {
        final Bytecode.Visibility visibility = Bytecode.getVisibility(methodNode);
        final Bytecode.Visibility visibility2 = Bytecode.getVisibility((MethodNode)mixinMethodNode);
        if (visibility2.ordinal() >= visibility.ordinal()) {
            if (visibility == Bytecode.Visibility.PRIVATE && visibility2.ordinal() > Bytecode.Visibility.PRIVATE.ordinal()) {
                mixinTargetContext.getTarget().addUpgradedMethod(methodNode);
            }
            return;
        }
        final String format = String.format("%s %s method %s in %s cannot reduce visibiliy of %s target method", visibility2, specialMethod, mixinMethodNode.name, this.mixin, visibility);
        if (specialMethod.isOverwrite && !this.mixin.getParent().conformOverwriteVisibility()) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, format);
        }
        if (visibility2 == Bytecode.Visibility.PRIVATE) {
            if (specialMethod.isOverwrite) {
                MixinPreProcessorStandard.logger.warn("Static binding violation: {}, visibility will be upgraded.", new Object[] { format });
            }
            mixinTargetContext.addUpgradedMethod((MethodNode)mixinMethodNode);
            Bytecode.setVisibility((MethodNode)mixinMethodNode, visibility);
        }
    }
    
    protected ClassInfo.Method getSpecialMethod(final MixinInfo.MixinMethodNode mixinMethodNode, final SpecialMethod specialMethod) {
        final ClassInfo.Method method = this.mixin.getClassInfo().findMethod((MethodNode)mixinMethodNode, 10);
        this.checkMethodNotUnique(method, specialMethod);
        return method;
    }
    
    protected void checkMethodNotUnique(final ClassInfo.Method method, final SpecialMethod specialMethod) {
        if (method.isUnique()) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("%s method %s in %s cannot be @Unique", specialMethod, method.getName(), this.mixin));
        }
    }
    
    protected void checkMixinNotUnique(final MixinInfo.MixinMethodNode mixinMethodNode, final SpecialMethod specialMethod) {
        if (this.mixin.isUnique()) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, String.format("%s method %s found in a @Unique mixin %s", specialMethod, mixinMethodNode.name, this.mixin));
        }
    }
    
    protected void attachMethod(final MixinTargetContext mixinTargetContext, final MixinInfo.MixinMethodNode mixinMethodNode) {
        final ClassInfo.Method method = this.mixin.getClassInfo().findMethod((MethodNode)mixinMethodNode);
        if (method == null) {
            return;
        }
        final ClassInfo.Method methodInHierarchy = this.mixin.getClassInfo().findMethodInHierarchy((MethodNode)mixinMethodNode, ClassInfo.SearchType.SUPER_CLASSES_ONLY);
        if (methodInHierarchy != null && methodInHierarchy.isRenamed()) {
            mixinMethodNode.name = method.renameTo(methodInHierarchy.getName());
        }
        final MethodNode method2 = mixinTargetContext.findMethod((MethodNode)mixinMethodNode, null);
        if (method2 != null) {
            this.conformVisibility(mixinTargetContext, mixinMethodNode, SpecialMethod.MERGE, method2);
        }
    }
    
    protected void attachFields(final MixinTargetContext p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.classNode:Lorg/spongepowered/asm/mixin/transformer/MixinInfo$MixinClassNode;
        //     4: getfield        org/spongepowered/asm/mixin/transformer/MixinInfo$MixinClassNode.fields:Ljava/util/List;
        //     7: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    12: astore_2       
        //    13: aload_2        
        //    14: invokeinterface java/util/Iterator.hasNext:()Z
        //    19: ifeq            709
        //    22: aload_2        
        //    23: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    28: checkcast       Lorg/spongepowered/asm/lib/tree/FieldNode;
        //    31: astore_3       
        //    32: aload_3        
        //    33: ldc             Lorg/spongepowered/asm/mixin/Shadow;.class
        //    35: invokestatic    org/spongepowered/asm/util/Annotations.getVisible:(Lorg/spongepowered/asm/lib/tree/FieldNode;Ljava/lang/Class;)Lorg/spongepowered/asm/lib/tree/AnnotationNode;
        //    38: astore          4
        //    40: aload           4
        //    42: ifnull          49
        //    45: iconst_1       
        //    46: goto            50
        //    49: iconst_0       
        //    50: istore          5
        //    52: aload_0        
        //    53: aload_1        
        //    54: aload_3        
        //    55: aload           4
        //    57: ifeq            69
        //    60: aload_2        
        //    61: invokeinterface java/util/Iterator.remove:()V
        //    66: goto            13
        //    69: aload_0        
        //    70: getfield        org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.mixin:Lorg/spongepowered/asm/mixin/transformer/MixinInfo;
        //    73: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinInfo.getClassInfo:()Lorg/spongepowered/asm/mixin/transformer/ClassInfo;
        //    76: aload_3        
        //    77: invokevirtual   org/spongepowered/asm/mixin/transformer/ClassInfo.findField:(Lorg/spongepowered/asm/lib/tree/FieldNode;)Lorg/spongepowered/asm/mixin/transformer/ClassInfo$Field;
        //    80: astore          6
        //    82: aload_1        
        //    83: aload_3        
        //    84: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.transformDescriptor:(Lorg/spongepowered/asm/lib/tree/FieldNode;)V
        //    87: aload           6
        //    89: aload_3        
        //    90: getfield        org/spongepowered/asm/lib/tree/FieldNode.desc:Ljava/lang/String;
        //    93: invokevirtual   org/spongepowered/asm/mixin/transformer/ClassInfo$Field.remapTo:(Ljava/lang/String;)Ljava/lang/String;
        //    96: pop            
        //    97: aload           6
        //    99: invokevirtual   org/spongepowered/asm/mixin/transformer/ClassInfo$Field.isUnique:()Z
        //   102: ifeq            139
        //   105: iload           5
        //   107: ifeq            139
        //   110: new             Lorg/spongepowered/asm/mixin/transformer/throwables/InvalidMixinException;
        //   113: dup            
        //   114: aload_0        
        //   115: getfield        org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.mixin:Lorg/spongepowered/asm/mixin/transformer/MixinInfo;
        //   118: ldc_w           "@Shadow field %s cannot be @Unique"
        //   121: iconst_1       
        //   122: anewarray       Ljava/lang/Object;
        //   125: dup            
        //   126: iconst_0       
        //   127: aload_3        
        //   128: getfield        org/spongepowered/asm/lib/tree/FieldNode.name:Ljava/lang/String;
        //   131: aastore        
        //   132: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   135: invokespecial   org/spongepowered/asm/mixin/transformer/throwables/InvalidMixinException.<init>:(Lorg/spongepowered/asm/mixin/extensibility/IMixinInfo;Ljava/lang/String;)V
        //   138: athrow         
        //   139: aload_1        
        //   140: aload_3        
        //   141: aload           4
        //   143: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.findField:(Lorg/spongepowered/asm/lib/tree/FieldNode;Lorg/spongepowered/asm/lib/tree/AnnotationNode;)Lorg/spongepowered/asm/lib/tree/FieldNode;
        //   146: astore          7
        //   148: aload           7
        //   150: ifnonnull       242
        //   153: aload           4
        //   155: ifnonnull       161
        //   158: goto            13
        //   161: aload_1        
        //   162: aload_3        
        //   163: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.findRemappedField:(Lorg/spongepowered/asm/lib/tree/FieldNode;)Lorg/spongepowered/asm/lib/tree/FieldNode;
        //   166: astore          7
        //   168: aload           7
        //   170: ifnonnull       228
        //   173: new             Lorg/spongepowered/asm/mixin/transformer/throwables/InvalidMixinException;
        //   176: dup            
        //   177: aload_0        
        //   178: getfield        org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.mixin:Lorg/spongepowered/asm/mixin/transformer/MixinInfo;
        //   181: ldc_w           "Shadow field %s was not located in the target class %s. %s%s"
        //   184: iconst_4       
        //   185: anewarray       Ljava/lang/Object;
        //   188: dup            
        //   189: iconst_0       
        //   190: aload_3        
        //   191: getfield        org/spongepowered/asm/lib/tree/FieldNode.name:Ljava/lang/String;
        //   194: aastore        
        //   195: dup            
        //   196: iconst_1       
        //   197: aload_1        
        //   198: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.getTarget:()Lorg/spongepowered/asm/mixin/transformer/TargetClassContext;
        //   201: aastore        
        //   202: dup            
        //   203: iconst_2       
        //   204: aload_1        
        //   205: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.getReferenceMapper:()Lorg/spongepowered/asm/mixin/refmap/IReferenceMapper;
        //   208: invokeinterface org/spongepowered/asm/mixin/refmap/IReferenceMapper.getStatus:()Ljava/lang/String;
        //   213: aastore        
        //   214: dup            
        //   215: iconst_3       
        //   216: aload_3        
        //   217: invokestatic    org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.getDynamicInfo:(Lorg/spongepowered/asm/lib/tree/FieldNode;)Ljava/lang/String;
        //   220: aastore        
        //   221: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   224: invokespecial   org/spongepowered/asm/mixin/transformer/throwables/InvalidMixinException.<init>:(Lorg/spongepowered/asm/mixin/extensibility/IMixinInfo;Ljava/lang/String;)V
        //   227: athrow         
        //   228: aload_3        
        //   229: aload           6
        //   231: aload           7
        //   233: getfield        org/spongepowered/asm/lib/tree/FieldNode.name:Ljava/lang/String;
        //   236: invokevirtual   org/spongepowered/asm/mixin/transformer/ClassInfo$Field.renameTo:(Ljava/lang/String;)Ljava/lang/String;
        //   239: putfield        org/spongepowered/asm/lib/tree/FieldNode.name:Ljava/lang/String;
        //   242: aload_3        
        //   243: aload           7
        //   245: bipush          8
        //   247: invokestatic    org/spongepowered/asm/util/Bytecode.compareFlags:(Lorg/spongepowered/asm/lib/tree/FieldNode;Lorg/spongepowered/asm/lib/tree/FieldNode;I)Z
        //   250: ifne            289
        //   253: new             Lorg/spongepowered/asm/mixin/transformer/throwables/InvalidMixinException;
        //   256: dup            
        //   257: aload_0        
        //   258: getfield        org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.mixin:Lorg/spongepowered/asm/mixin/transformer/MixinInfo;
        //   261: ldc_w           "STATIC modifier of @Shadow field %s in %s does not match the target"
        //   264: iconst_2       
        //   265: anewarray       Ljava/lang/Object;
        //   268: dup            
        //   269: iconst_0       
        //   270: aload_3        
        //   271: getfield        org/spongepowered/asm/lib/tree/FieldNode.name:Ljava/lang/String;
        //   274: aastore        
        //   275: dup            
        //   276: iconst_1       
        //   277: aload_0        
        //   278: getfield        org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.mixin:Lorg/spongepowered/asm/mixin/transformer/MixinInfo;
        //   281: aastore        
        //   282: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   285: invokespecial   org/spongepowered/asm/mixin/transformer/throwables/InvalidMixinException.<init>:(Lorg/spongepowered/asm/mixin/extensibility/IMixinInfo;Ljava/lang/String;)V
        //   288: athrow         
        //   289: aload           6
        //   291: invokevirtual   org/spongepowered/asm/mixin/transformer/ClassInfo$Field.isUnique:()Z
        //   294: ifeq            487
        //   297: aload_3        
        //   298: getfield        org/spongepowered/asm/lib/tree/FieldNode.access:I
        //   301: bipush          6
        //   303: iand           
        //   304: ifeq            376
        //   307: aload_1        
        //   308: aload_3        
        //   309: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.getUniqueName:(Lorg/spongepowered/asm/lib/tree/FieldNode;)Ljava/lang/String;
        //   312: astore          8
        //   314: getstatic       org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.logger:Lorg/apache/logging/log4j/Logger;
        //   317: aload_0        
        //   318: getfield        org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.mixin:Lorg/spongepowered/asm/mixin/transformer/MixinInfo;
        //   321: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinInfo.getLoggingLevel:()Lorg/apache/logging/log4j/Level;
        //   324: ldc_w           "Renaming @Unique field {}{} to {} in {}"
        //   327: iconst_4       
        //   328: anewarray       Ljava/lang/Object;
        //   331: dup            
        //   332: iconst_0       
        //   333: aload_3        
        //   334: getfield        org/spongepowered/asm/lib/tree/FieldNode.name:Ljava/lang/String;
        //   337: aastore        
        //   338: dup            
        //   339: iconst_1       
        //   340: aload_3        
        //   341: getfield        org/spongepowered/asm/lib/tree/FieldNode.desc:Ljava/lang/String;
        //   344: aastore        
        //   345: dup            
        //   346: iconst_2       
        //   347: aload           8
        //   349: aastore        
        //   350: dup            
        //   351: iconst_3       
        //   352: aload_0        
        //   353: getfield        org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.mixin:Lorg/spongepowered/asm/mixin/transformer/MixinInfo;
        //   356: aastore        
        //   357: invokeinterface org/apache/logging/log4j/Logger.log:(Lorg/apache/logging/log4j/Level;Ljava/lang/String;[Ljava/lang/Object;)V
        //   362: aload_3        
        //   363: aload           6
        //   365: aload           8
        //   367: invokevirtual   org/spongepowered/asm/mixin/transformer/ClassInfo$Field.renameTo:(Ljava/lang/String;)Ljava/lang/String;
        //   370: putfield        org/spongepowered/asm/lib/tree/FieldNode.name:Ljava/lang/String;
        //   373: goto            13
        //   376: aload_0        
        //   377: getfield        org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.strictUnique:Z
        //   380: ifeq            442
        //   383: new             Lorg/spongepowered/asm/mixin/transformer/throwables/InvalidMixinException;
        //   386: dup            
        //   387: aload_0        
        //   388: getfield        org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.mixin:Lorg/spongepowered/asm/mixin/transformer/MixinInfo;
        //   391: ldc_w           "Field conflict, @Unique field %s in %s cannot overwrite %s%s in %s"
        //   394: iconst_5       
        //   395: anewarray       Ljava/lang/Object;
        //   398: dup            
        //   399: iconst_0       
        //   400: aload_3        
        //   401: getfield        org/spongepowered/asm/lib/tree/FieldNode.name:Ljava/lang/String;
        //   404: aastore        
        //   405: dup            
        //   406: iconst_1       
        //   407: aload_0        
        //   408: getfield        org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.mixin:Lorg/spongepowered/asm/mixin/transformer/MixinInfo;
        //   411: aastore        
        //   412: dup            
        //   413: iconst_2       
        //   414: aload           7
        //   416: getfield        org/spongepowered/asm/lib/tree/FieldNode.name:Ljava/lang/String;
        //   419: aastore        
        //   420: dup            
        //   421: iconst_3       
        //   422: aload           7
        //   424: getfield        org/spongepowered/asm/lib/tree/FieldNode.desc:Ljava/lang/String;
        //   427: aastore        
        //   428: dup            
        //   429: iconst_4       
        //   430: aload_1        
        //   431: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.getTarget:()Lorg/spongepowered/asm/mixin/transformer/TargetClassContext;
        //   434: aastore        
        //   435: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   438: invokespecial   org/spongepowered/asm/mixin/transformer/throwables/InvalidMixinException.<init>:(Lorg/spongepowered/asm/mixin/extensibility/IMixinInfo;Ljava/lang/String;)V
        //   441: athrow         
        //   442: getstatic       org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.logger:Lorg/apache/logging/log4j/Logger;
        //   445: ldc_w           "Discarding @Unique public field {} in {} because it already exists in {}. Note that declared FIELD INITIALISERS will NOT be removed!"
        //   448: iconst_3       
        //   449: anewarray       Ljava/lang/Object;
        //   452: dup            
        //   453: iconst_0       
        //   454: aload_3        
        //   455: getfield        org/spongepowered/asm/lib/tree/FieldNode.name:Ljava/lang/String;
        //   458: aastore        
        //   459: dup            
        //   460: iconst_1       
        //   461: aload_0        
        //   462: getfield        org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.mixin:Lorg/spongepowered/asm/mixin/transformer/MixinInfo;
        //   465: aastore        
        //   466: dup            
        //   467: iconst_2       
        //   468: aload_1        
        //   469: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.getTarget:()Lorg/spongepowered/asm/mixin/transformer/TargetClassContext;
        //   472: aastore        
        //   473: invokeinterface org/apache/logging/log4j/Logger.warn:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   478: aload_2        
        //   479: invokeinterface java/util/Iterator.remove:()V
        //   484: goto            13
        //   487: aload           7
        //   489: getfield        org/spongepowered/asm/lib/tree/FieldNode.desc:Ljava/lang/String;
        //   492: aload_3        
        //   493: getfield        org/spongepowered/asm/lib/tree/FieldNode.desc:Ljava/lang/String;
        //   496: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   499: ifne            531
        //   502: new             Lorg/spongepowered/asm/mixin/transformer/throwables/InvalidMixinException;
        //   505: dup            
        //   506: aload_0        
        //   507: getfield        org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.mixin:Lorg/spongepowered/asm/mixin/transformer/MixinInfo;
        //   510: ldc_w           "The field %s in the target class has a conflicting signature"
        //   513: iconst_1       
        //   514: anewarray       Ljava/lang/Object;
        //   517: dup            
        //   518: iconst_0       
        //   519: aload_3        
        //   520: getfield        org/spongepowered/asm/lib/tree/FieldNode.name:Ljava/lang/String;
        //   523: aastore        
        //   524: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   527: invokespecial   org/spongepowered/asm/mixin/transformer/throwables/InvalidMixinException.<init>:(Lorg/spongepowered/asm/mixin/extensibility/IMixinInfo;Ljava/lang/String;)V
        //   530: athrow         
        //   531: aload           7
        //   533: getfield        org/spongepowered/asm/lib/tree/FieldNode.name:Ljava/lang/String;
        //   536: aload_3        
        //   537: getfield        org/spongepowered/asm/lib/tree/FieldNode.name:Ljava/lang/String;
        //   540: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   543: ifne            618
        //   546: aload           7
        //   548: getfield        org/spongepowered/asm/lib/tree/FieldNode.access:I
        //   551: iconst_2       
        //   552: iand           
        //   553: ifne            604
        //   556: aload           7
        //   558: getfield        org/spongepowered/asm/lib/tree/FieldNode.access:I
        //   561: sipush          4096
        //   564: iand           
        //   565: ifne            604
        //   568: new             Lorg/spongepowered/asm/mixin/transformer/throwables/InvalidMixinException;
        //   571: dup            
        //   572: aload_0        
        //   573: getfield        org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.mixin:Lorg/spongepowered/asm/mixin/transformer/MixinInfo;
        //   576: new             Ljava/lang/StringBuilder;
        //   579: dup            
        //   580: invokespecial   java/lang/StringBuilder.<init>:()V
        //   583: ldc_w           "Non-private field cannot be aliased. Found "
        //   586: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   589: aload           7
        //   591: getfield        org/spongepowered/asm/lib/tree/FieldNode.name:Ljava/lang/String;
        //   594: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   597: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   600: invokespecial   org/spongepowered/asm/mixin/transformer/throwables/InvalidMixinException.<init>:(Lorg/spongepowered/asm/mixin/extensibility/IMixinInfo;Ljava/lang/String;)V
        //   603: athrow         
        //   604: aload_3        
        //   605: aload           6
        //   607: aload           7
        //   609: getfield        org/spongepowered/asm/lib/tree/FieldNode.name:Ljava/lang/String;
        //   612: invokevirtual   org/spongepowered/asm/mixin/transformer/ClassInfo$Field.renameTo:(Ljava/lang/String;)Ljava/lang/String;
        //   615: putfield        org/spongepowered/asm/lib/tree/FieldNode.name:Ljava/lang/String;
        //   618: aload_2        
        //   619: invokeinterface java/util/Iterator.remove:()V
        //   624: iload           5
        //   626: ifeq            706
        //   629: aload           6
        //   631: invokevirtual   org/spongepowered/asm/mixin/transformer/ClassInfo$Field.isDecoratedFinal:()Z
        //   634: istore          8
        //   636: aload_0        
        //   637: getfield        org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.verboseLogging:Z
        //   640: ifeq            699
        //   643: aload           7
        //   645: bipush          16
        //   647: invokestatic    org/spongepowered/asm/util/Bytecode.hasFlag:(Lorg/spongepowered/asm/lib/tree/FieldNode;I)Z
        //   650: iload           8
        //   652: if_icmpeq       699
        //   655: iload           8
        //   657: ifeq            666
        //   660: ldc_w           "@Shadow field {}::{} is decorated with @Final but target is not final"
        //   663: goto            669
        //   666: ldc_w           "@Shadow target {}::{} is final but shadow is not decorated with @Final"
        //   669: astore          9
        //   671: getstatic       org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.logger:Lorg/apache/logging/log4j/Logger;
        //   674: aload           9
        //   676: iconst_2       
        //   677: anewarray       Ljava/lang/Object;
        //   680: dup            
        //   681: iconst_0       
        //   682: aload_0        
        //   683: getfield        org/spongepowered/asm/mixin/transformer/MixinPreProcessorStandard.mixin:Lorg/spongepowered/asm/mixin/transformer/MixinInfo;
        //   686: aastore        
        //   687: dup            
        //   688: iconst_1       
        //   689: aload_3        
        //   690: getfield        org/spongepowered/asm/lib/tree/FieldNode.name:Ljava/lang/String;
        //   693: aastore        
        //   694: invokeinterface org/apache/logging/log4j/Logger.warn:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   699: aload_1        
        //   700: aload_3        
        //   701: aload           6
        //   703: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.addShadowField:(Lorg/spongepowered/asm/lib/tree/FieldNode;Lorg/spongepowered/asm/mixin/transformer/ClassInfo$Field;)V
        //   706: goto            13
        //   709: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0013 (coming from #0706).
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
    
    protected void transform(final MixinTargetContext mixinTargetContext) {
        final Iterator<MethodNode> iterator = this.classNode.methods.iterator();
        while (iterator.hasNext()) {
            for (final AbstractInsnNode abstractInsnNode : iterator.next().instructions) {
                if (abstractInsnNode instanceof MethodInsnNode) {
                    this.transformMethod((MethodInsnNode)abstractInsnNode);
                }
                else {
                    if (!(abstractInsnNode instanceof FieldInsnNode)) {
                        continue;
                    }
                    this.transformField((FieldInsnNode)abstractInsnNode);
                }
            }
        }
    }
    
    protected void transformMethod(final MethodInsnNode methodInsnNode) {
        final Profiler.Section begin = this.profiler.begin("meta");
        final ClassInfo forName = ClassInfo.forName(methodInsnNode.owner);
        if (forName == null) {
            throw new RuntimeException(new ClassNotFoundException(methodInsnNode.owner.replace('/', '.')));
        }
        final ClassInfo.Method methodInHierarchy = forName.findMethodInHierarchy(methodInsnNode, ClassInfo.SearchType.ALL_CLASSES, 2);
        begin.end();
        if (methodInHierarchy != null && methodInHierarchy.isRenamed()) {
            methodInsnNode.name = methodInHierarchy.getName();
        }
    }
    
    protected void transformField(final FieldInsnNode fieldInsnNode) {
        final Profiler.Section begin = this.profiler.begin("meta");
        final ClassInfo forName = ClassInfo.forName(fieldInsnNode.owner);
        if (forName == null) {
            throw new RuntimeException(new ClassNotFoundException(fieldInsnNode.owner.replace('/', '.')));
        }
        final ClassInfo.Field field = forName.findField(fieldInsnNode, 2);
        begin.end();
        if (field != null && field.isRenamed()) {
            fieldInsnNode.name = field.getName();
        }
    }
    
    protected static String getDynamicInfo(final MethodNode methodNode) {
        return getDynamicInfo("Method", Annotations.getInvisible(methodNode, Dynamic.class));
    }
    
    protected static String getDynamicInfo(final FieldNode fieldNode) {
        return getDynamicInfo("Field", Annotations.getInvisible(fieldNode, Dynamic.class));
    }
    
    private static String getDynamicInfo(final String s, final AnnotationNode annotationNode) {
        String s2 = Strings.nullToEmpty((String)Annotations.getValue(annotationNode));
        final Type type = (Type)Annotations.getValue(annotationNode, "mixin");
        if (type != null) {
            s2 = String.format("{%s} %s", type.getClassName(), s2).trim();
        }
        return (s2.length() > 0) ? String.format(" %s is @Dynamic(%s)", s, s2) : "";
    }
    
    static {
        logger = LogManager.getLogger("mixin");
    }
    
    enum SpecialMethod
    {
        MERGE("MERGE", 0, true), 
        OVERWRITE("OVERWRITE", 1, true, (Class)Overwrite.class), 
        SHADOW("SHADOW", 2, false, (Class)Shadow.class), 
        ACCESSOR("ACCESSOR", 3, false, (Class)Accessor.class), 
        INVOKER("INVOKER", 4, false, (Class)Invoker.class);
        
        final boolean isOverwrite;
        final Class annotation;
        final String description;
        private static final SpecialMethod[] $VALUES;
        
        private SpecialMethod(final String s, final int n, final boolean isOverwrite, final Class annotation) {
            this.isOverwrite = isOverwrite;
            this.annotation = annotation;
            this.description = "@" + Bytecode.getSimpleName(annotation);
        }
        
        private SpecialMethod(final String s, final int n, final boolean isOverwrite) {
            this.isOverwrite = isOverwrite;
            this.annotation = null;
            this.description = "overwrite";
        }
        
        @Override
        public String toString() {
            return this.description;
        }
        
        static {
            $VALUES = new SpecialMethod[] { SpecialMethod.MERGE, SpecialMethod.OVERWRITE, SpecialMethod.SHADOW, SpecialMethod.ACCESSOR, SpecialMethod.INVOKER };
        }
    }
}
