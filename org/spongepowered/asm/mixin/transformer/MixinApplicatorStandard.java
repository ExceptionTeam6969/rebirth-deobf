//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.util.perf.*;
import org.apache.logging.log4j.*;
import org.spongepowered.asm.mixin.transformer.ext.extensions.*;
import org.spongepowered.asm.mixin.transformer.throwables.*;
import org.spongepowered.asm.mixin.refmap.*;
import org.spongepowered.asm.lib.signature.*;
import org.spongepowered.asm.lib.*;
import java.util.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.util.*;
import org.spongepowered.asm.util.throwables.*;
import org.spongepowered.asm.mixin.injection.*;
import com.google.common.collect.*;

class MixinApplicatorStandard
{
    protected static final List CONSTRAINED_ANNOTATIONS;
    protected static final int[] INITIALISER_OPCODE_BLACKLIST;
    protected final Logger logger;
    protected final TargetClassContext context;
    protected final String targetName;
    protected final ClassNode targetClass;
    protected final Profiler profiler;
    protected final boolean mergeSignatures;
    
    MixinApplicatorStandard(final TargetClassContext context) {
        this.logger = LogManager.getLogger("mixin");
        this.profiler = MixinEnvironment.getProfiler();
        this.context = context;
        this.targetName = context.getClassName();
        this.targetClass = context.getClassNode();
        this.mergeSignatures = (((ExtensionClassExporter)context.getExtensions().getExtension((Class)ExtensionClassExporter.class)).isDecompilerActive() && MixinEnvironment.getCurrentEnvironment().getOption(MixinEnvironment.Option.DEBUG_EXPORT_DECOMPILE_MERGESIGNATURES));
    }
    
    void apply(final SortedSet set) {
        final ArrayList<MixinTargetContext> list = new ArrayList<MixinTargetContext>();
        for (final MixinInfo mixinInfo : set) {
            this.logger.log(mixinInfo.getLoggingLevel(), "Mixing {} from {} into {}", new Object[] { mixinInfo.getName(), mixinInfo.getParent(), this.targetName });
            list.add(mixinInfo.createContextFor(this.context));
        }
        Object o = null;
        try {
            final Iterator<Object> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                ((MixinTargetContext)(o = iterator2.next())).preApply(this.targetName, this.targetClass);
            }
            for (final ApplicatorPass applicatorPass : ApplicatorPass.values()) {
                final Profiler.Section begin = this.profiler.begin("pass", applicatorPass.name().toLowerCase());
                final Iterator<Object> iterator3 = list.iterator();
                while (iterator3.hasNext()) {
                    this.applyMixin((MixinTargetContext)(o = iterator3.next()), applicatorPass);
                }
                begin.end();
            }
            final Iterator<Object> iterator4 = list.iterator();
            while (iterator4.hasNext()) {
                ((MixinTargetContext)(o = iterator4.next())).postApply(this.targetName, this.targetClass);
            }
        }
        catch (InvalidMixinException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new InvalidMixinException((IMixinContext)o, "Unexpecteded " + ex2.getClass().getSimpleName() + " whilst applying the mixin class: " + ex2.getMessage(), ex2);
        }
        this.applySourceMap(this.context);
        this.context.processDebugTasks();
    }
    
    protected final void applyMixin(final MixinTargetContext mixinTargetContext, final ApplicatorPass applicatorPass) {
        switch (applicatorPass) {
            case MAIN: {
                this.applySignature(mixinTargetContext);
                this.applyInterfaces(mixinTargetContext);
                this.applyAttributes(mixinTargetContext);
                this.applyAnnotations(mixinTargetContext);
                this.applyFields(mixinTargetContext);
                this.applyMethods(mixinTargetContext);
                this.applyInitialisers(mixinTargetContext);
                break;
            }
            case PREINJECT: {
                this.prepareInjections(mixinTargetContext);
                break;
            }
            case INJECT: {
                this.applyAccessors(mixinTargetContext);
                this.applyInjections(mixinTargetContext);
                break;
            }
            default: {
                throw new IllegalStateException("Invalid pass specified " + applicatorPass);
            }
        }
    }
    
    protected void applySignature(final MixinTargetContext mixinTargetContext) {
        if (this.mergeSignatures) {
            this.context.mergeSignature(mixinTargetContext.getSignature());
        }
    }
    
    protected void applyInterfaces(final MixinTargetContext mixinTargetContext) {
        for (final String s : mixinTargetContext.getInterfaces()) {
            if (!this.targetClass.interfaces.contains(s)) {
                this.targetClass.interfaces.add(s);
                mixinTargetContext.getTargetClassInfo().addInterface(s);
            }
        }
    }
    
    protected void applyAttributes(final MixinTargetContext mixinTargetContext) {
        if (mixinTargetContext.shouldSetSourceFile()) {
            this.targetClass.sourceFile = mixinTargetContext.getSourceFile();
        }
        this.targetClass.version = Math.max(this.targetClass.version, mixinTargetContext.getMinRequiredClassVersion());
    }
    
    protected void applyAnnotations(final MixinTargetContext mixinTargetContext) {
        Bytecode.mergeAnnotations(mixinTargetContext.getClassNode(), this.targetClass);
    }
    
    protected void applyFields(final MixinTargetContext mixinTargetContext) {
        this.mergeShadowFields(mixinTargetContext);
        this.mergeNewFields(mixinTargetContext);
    }
    
    protected void mergeShadowFields(final MixinTargetContext mixinTargetContext) {
        for (final Map.Entry<FieldNode, V> entry : mixinTargetContext.getShadowFields()) {
            final FieldNode fieldNode = entry.getKey();
            final FieldNode targetField = this.findTargetField(fieldNode);
            if (targetField != null) {
                Bytecode.mergeAnnotations(fieldNode, targetField);
                if (!((ClassInfo.Field)entry.getValue()).isDecoratedMutable() || Bytecode.hasFlag(targetField, 2)) {
                    continue;
                }
                final FieldNode fieldNode2 = targetField;
                fieldNode2.access &= 0xFFFFFFEF;
            }
        }
    }
    
    protected void mergeNewFields(final MixinTargetContext mixinTargetContext) {
        for (final FieldNode fieldNode : mixinTargetContext.getFields()) {
            if (this.findTargetField(fieldNode) == null) {
                this.targetClass.fields.add(fieldNode);
                if (fieldNode.signature == null) {
                    continue;
                }
                if (this.mergeSignatures) {
                    final SignatureVisitor remapper = mixinTargetContext.getSignature().getRemapper();
                    new SignatureReader(fieldNode.signature).accept(remapper);
                    fieldNode.signature = remapper.toString();
                }
                else {
                    fieldNode.signature = null;
                }
            }
        }
    }
    
    protected void applyMethods(final MixinTargetContext mixinTargetContext) {
        final Iterator<MethodNode> iterator = mixinTargetContext.getShadowMethods().iterator();
        while (iterator.hasNext()) {
            this.applyShadowMethod(mixinTargetContext, iterator.next());
        }
        final Iterator<MethodNode> iterator2 = mixinTargetContext.getMethods().iterator();
        while (iterator2.hasNext()) {
            this.applyNormalMethod(mixinTargetContext, iterator2.next());
        }
    }
    
    protected void applyShadowMethod(final MixinTargetContext mixinTargetContext, final MethodNode methodNode) {
        final MethodNode targetMethod = this.findTargetMethod(methodNode);
        if (targetMethod != null) {
            Bytecode.mergeAnnotations(methodNode, targetMethod);
        }
    }
    
    protected void applyNormalMethod(final MixinTargetContext mixinTargetContext, final MethodNode methodNode) {
        mixinTargetContext.transformMethod(methodNode);
        if (!methodNode.name.startsWith("<")) {
            this.checkMethodVisibility(mixinTargetContext, methodNode);
            this.checkMethodConstraints(mixinTargetContext, methodNode);
            this.mergeMethod(mixinTargetContext, methodNode);
        }
        else if ("<clinit>".equals(methodNode.name)) {
            this.appendInsns(mixinTargetContext, methodNode);
        }
    }
    
    protected void mergeMethod(final MixinTargetContext p0, final MethodNode p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc_w           Lorg/spongepowered/asm/mixin/Overwrite;.class
        //     4: invokestatic    org/spongepowered/asm/util/Annotations.getVisible:(Lorg/spongepowered/asm/lib/tree/MethodNode;Ljava/lang/Class;)Lorg/spongepowered/asm/lib/tree/AnnotationNode;
        //     7: ifnull          14
        //    10: iconst_1       
        //    11: goto            15
        //    14: iconst_0       
        //    15: istore_3       
        //    16: aload_0        
        //    17: aload_2        
        //    18: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinApplicatorStandard.findTargetMethod:(Lorg/spongepowered/asm/lib/tree/MethodNode;)Lorg/spongepowered/asm/lib/tree/MethodNode;
        //    21: astore          4
        //    23: aload           4
        //    25: ifnull          148
        //    28: aload_0        
        //    29: aload_1        
        //    30: aload_2        
        //    31: iload_3        
        //    32: aload           4
        //    34: ifnonnull       38
        //    37: return         
        //    38: aload_2        
        //    39: ldc_w           Lorg/spongepowered/asm/mixin/Intrinsic;.class
        //    42: invokestatic    org/spongepowered/asm/util/Annotations.getInvisible:(Lorg/spongepowered/asm/lib/tree/MethodNode;Ljava/lang/Class;)Lorg/spongepowered/asm/lib/tree/AnnotationNode;
        //    45: astore          5
        //    47: aload           5
        //    49: ifnull          72
        //    52: aload_0        
        //    53: aload_1        
        //    54: aload_2        
        //    55: iload_3        
        //    56: aload           4
        //    58: aload           5
        //    60: ifeq            145
        //    63: aload_1        
        //    64: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.getTarget:()Lorg/spongepowered/asm/mixin/transformer/TargetClassContext;
        //    67: aload_2        
        //    68: invokevirtual   org/spongepowered/asm/mixin/transformer/TargetClassContext.methodMerged:(Lorg/spongepowered/asm/lib/tree/MethodNode;)V
        //    71: return         
        //    72: aload_1        
        //    73: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.requireOverwriteAnnotations:()Z
        //    76: ifeq            130
        //    79: iload_3        
        //    80: ifne            130
        //    83: new             Lorg/spongepowered/asm/mixin/transformer/throwables/InvalidMixinException;
        //    86: dup            
        //    87: aload_1        
        //    88: ldc_w           "%s%s in %s cannot overwrite method in %s because @Overwrite is required by the parent configuration"
        //    91: iconst_4       
        //    92: anewarray       Ljava/lang/Object;
        //    95: dup            
        //    96: iconst_0       
        //    97: aload_2        
        //    98: getfield        org/spongepowered/asm/lib/tree/MethodNode.name:Ljava/lang/String;
        //   101: aastore        
        //   102: dup            
        //   103: iconst_1       
        //   104: aload_2        
        //   105: getfield        org/spongepowered/asm/lib/tree/MethodNode.desc:Ljava/lang/String;
        //   108: aastore        
        //   109: dup            
        //   110: iconst_2       
        //   111: aload_1        
        //   112: aastore        
        //   113: dup            
        //   114: iconst_3       
        //   115: aload_1        
        //   116: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.getTarget:()Lorg/spongepowered/asm/mixin/transformer/TargetClassContext;
        //   119: invokevirtual   org/spongepowered/asm/mixin/transformer/TargetClassContext.getClassName:()Ljava/lang/String;
        //   122: aastore        
        //   123: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   126: invokespecial   org/spongepowered/asm/mixin/transformer/throwables/InvalidMixinException.<init>:(Lorg/spongepowered/asm/mixin/refmap/IMixinContext;Ljava/lang/String;)V
        //   129: athrow         
        //   130: aload_0        
        //   131: getfield        org/spongepowered/asm/mixin/transformer/MixinApplicatorStandard.targetClass:Lorg/spongepowered/asm/lib/tree/ClassNode;
        //   134: getfield        org/spongepowered/asm/lib/tree/ClassNode.methods:Ljava/util/List;
        //   137: aload           4
        //   139: invokeinterface java/util/List.remove:(Ljava/lang/Object;)Z
        //   144: pop            
        //   145: goto            185
        //   148: iload_3        
        //   149: ifeq            185
        //   152: new             Lorg/spongepowered/asm/mixin/transformer/throwables/InvalidMixinException;
        //   155: dup            
        //   156: aload_1        
        //   157: ldc_w           "Overwrite target \"%s\" was not located in target class %s"
        //   160: iconst_2       
        //   161: anewarray       Ljava/lang/Object;
        //   164: dup            
        //   165: iconst_0       
        //   166: aload_2        
        //   167: getfield        org/spongepowered/asm/lib/tree/MethodNode.name:Ljava/lang/String;
        //   170: aastore        
        //   171: dup            
        //   172: iconst_1       
        //   173: aload_1        
        //   174: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.getTargetClassRef:()Ljava/lang/String;
        //   177: aastore        
        //   178: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   181: invokespecial   org/spongepowered/asm/mixin/transformer/throwables/InvalidMixinException.<init>:(Lorg/spongepowered/asm/mixin/refmap/IMixinContext;Ljava/lang/String;)V
        //   184: athrow         
        //   185: aload_0        
        //   186: getfield        org/spongepowered/asm/mixin/transformer/MixinApplicatorStandard.targetClass:Lorg/spongepowered/asm/lib/tree/ClassNode;
        //   189: getfield        org/spongepowered/asm/lib/tree/ClassNode.methods:Ljava/util/List;
        //   192: aload_2        
        //   193: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   198: pop            
        //   199: aload_1        
        //   200: aload_2        
        //   201: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.methodMerged:(Lorg/spongepowered/asm/lib/tree/MethodNode;)V
        //   204: aload_2        
        //   205: getfield        org/spongepowered/asm/lib/tree/MethodNode.signature:Ljava/lang/String;
        //   208: ifnull          260
        //   211: aload_0        
        //   212: getfield        org/spongepowered/asm/mixin/transformer/MixinApplicatorStandard.mergeSignatures:Z
        //   215: ifeq            255
        //   218: aload_1        
        //   219: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTargetContext.getSignature:()Lorg/spongepowered/asm/util/ClassSignature;
        //   222: invokevirtual   org/spongepowered/asm/util/ClassSignature.getRemapper:()Lorg/spongepowered/asm/lib/signature/SignatureVisitor;
        //   225: astore          5
        //   227: new             Lorg/spongepowered/asm/lib/signature/SignatureReader;
        //   230: dup            
        //   231: aload_2        
        //   232: getfield        org/spongepowered/asm/lib/tree/MethodNode.signature:Ljava/lang/String;
        //   235: invokespecial   org/spongepowered/asm/lib/signature/SignatureReader.<init>:(Ljava/lang/String;)V
        //   238: aload           5
        //   240: invokevirtual   org/spongepowered/asm/lib/signature/SignatureReader.accept:(Lorg/spongepowered/asm/lib/signature/SignatureVisitor;)V
        //   243: aload_2        
        //   244: aload           5
        //   246: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //   249: putfield        org/spongepowered/asm/lib/tree/MethodNode.signature:Ljava/lang/String;
        //   252: goto            260
        //   255: aload_2        
        //   256: aconst_null    
        //   257: putfield        org/spongepowered/asm/lib/tree/MethodNode.signature:Ljava/lang/String;
        //   260: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0185 (coming from #0145).
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
    
    protected void displaceIntrinsic(final MixinTargetContext mixinTargetContext, final MethodNode methodNode, final MethodNode methodNode2) {
        final String string = "proxy+" + methodNode2.name;
        for (final AbstractInsnNode abstractInsnNode : methodNode.instructions) {
            if (abstractInsnNode instanceof MethodInsnNode && abstractInsnNode.getOpcode() != 184) {
                final MethodInsnNode methodInsnNode = (MethodInsnNode)abstractInsnNode;
                if (!methodInsnNode.owner.equals(this.targetClass.name) || !methodInsnNode.name.equals(methodNode2.name) || !methodInsnNode.desc.equals(methodNode2.desc)) {
                    continue;
                }
                methodInsnNode.name = string;
            }
        }
        methodNode2.name = string;
    }
    
    protected final void appendInsns(final MixinTargetContext mixinTargetContext, final MethodNode methodNode) {
        if (Type.getReturnType(methodNode.desc) != Type.VOID_TYPE) {
            throw new IllegalArgumentException("Attempted to merge insns from a method which does not return void");
        }
        final MethodNode targetMethod = this.findTargetMethod(methodNode);
        if (targetMethod != null) {
            final AbstractInsnNode insn = Bytecode.findInsn(targetMethod, 177);
            if (insn != null) {
                for (final AbstractInsnNode abstractInsnNode : methodNode.instructions) {
                    if (!(abstractInsnNode instanceof LineNumberNode) && abstractInsnNode.getOpcode() != 177) {
                        targetMethod.instructions.insertBefore(insn, abstractInsnNode);
                    }
                }
                targetMethod.maxLocals = Math.max(targetMethod.maxLocals, methodNode.maxLocals);
                targetMethod.maxStack = Math.max(targetMethod.maxStack, methodNode.maxStack);
            }
            return;
        }
        this.targetClass.methods.add(methodNode);
    }
    
    protected void applyInitialisers(final MixinTargetContext mixinTargetContext) {
        final MethodNode constructor = this.getConstructor(mixinTargetContext);
        if (constructor == null) {
            return;
        }
        final Deque initialiser = this.getInitialiser(mixinTargetContext, constructor);
        if (initialiser == null || initialiser.size() == 0) {
            return;
        }
        for (final MethodNode methodNode : this.targetClass.methods) {
            if ("<init>".equals(methodNode.name)) {
                methodNode.maxStack = Math.max(methodNode.maxStack, constructor.maxStack);
                this.injectInitialiser(mixinTargetContext, methodNode, initialiser);
            }
        }
    }
    
    protected MethodNode getConstructor(final MixinTargetContext mixinTargetContext) {
        MethodNode methodNode = null;
        for (final MethodNode methodNode2 : mixinTargetContext.getMethods()) {
            if ("<init>".equals(methodNode2.name) && Bytecode.methodHasLineNumbers(methodNode2)) {
                if (methodNode == null) {
                    methodNode = methodNode2;
                }
                else {
                    this.logger.warn(String.format("Mixin %s has multiple constructors, %s was selected\n", mixinTargetContext, methodNode.desc));
                }
            }
        }
        return methodNode;
    }
    
    private Range getConstructorRange(final MethodNode methodNode) {
        boolean b = false;
        AbstractInsnNode abstractInsnNode = null;
        int line = 0;
        int n = 0;
        int n2 = 0;
        int index = -1;
        for (final AbstractInsnNode abstractInsnNode2 : methodNode.instructions) {
            if (abstractInsnNode2 instanceof LineNumberNode) {
                line = ((LineNumberNode)abstractInsnNode2).line;
                b = true;
            }
            else if (abstractInsnNode2 instanceof MethodInsnNode) {
                if (abstractInsnNode2.getOpcode() != 183 || !"<init>".equals(((MethodInsnNode)abstractInsnNode2).name) || index != -1) {
                    continue;
                }
                index = methodNode.instructions.indexOf(abstractInsnNode2);
                n = line;
            }
            else if (abstractInsnNode2.getOpcode() == 181) {
                b = false;
            }
            else {
                if (abstractInsnNode2.getOpcode() != 177) {
                    continue;
                }
                if (b) {
                    n2 = line;
                }
                else {
                    n2 = n;
                    abstractInsnNode = abstractInsnNode2;
                }
            }
        }
        if (abstractInsnNode != null) {
            final LabelNode labelNode = new LabelNode(new Label());
            methodNode.instructions.insertBefore(abstractInsnNode, (AbstractInsnNode)labelNode);
            methodNode.instructions.insertBefore(abstractInsnNode, (AbstractInsnNode)new LineNumberNode(n, labelNode));
        }
        return new Range(n, n2, index);
    }
    
    protected final Deque getInitialiser(final MixinTargetContext mixinTargetContext, final MethodNode methodNode) {
        final Range constructorRange = this.getConstructorRange(methodNode);
        if (!constructorRange.isValid()) {
            return null;
        }
        final ArrayDeque<Object> arrayDeque = new ArrayDeque<Object>();
        boolean excludes = false;
        int n = -1;
        Object o = null;
        final ListIterator iterator = methodNode.instructions.iterator(constructorRange.marker);
        while (iterator.hasNext()) {
            final AbstractInsnNode abstractInsnNode = iterator.next();
            if (abstractInsnNode instanceof LineNumberNode) {
                final int line = ((LineNumberNode)abstractInsnNode).line;
                final AbstractInsnNode value = methodNode.instructions.get(methodNode.instructions.indexOf(abstractInsnNode) + 1);
                if (line == constructorRange.end && value.getOpcode() != 177) {
                    excludes = true;
                    n = 177;
                }
                else {
                    excludes = constructorRange.excludes(line);
                    n = -1;
                }
            }
            else {
                if (!excludes) {
                    continue;
                }
                if (o != null) {
                    arrayDeque.add(o);
                    o = null;
                }
                if (abstractInsnNode instanceof LabelNode) {
                    o = abstractInsnNode;
                }
                else {
                    final int opcode = abstractInsnNode.getOpcode();
                    if (opcode == n) {
                        n = -1;
                    }
                    else {
                        final int[] initialiser_OPCODE_BLACKLIST = MixinApplicatorStandard.INITIALISER_OPCODE_BLACKLIST;
                        for (int length = initialiser_OPCODE_BLACKLIST.length, i = 0; i < length; ++i) {
                            if (opcode == initialiser_OPCODE_BLACKLIST[i]) {
                                throw new InvalidMixinException((IMixinContext)mixinTargetContext, "Cannot handle " + Bytecode.getOpcodeName(opcode) + " opcode (0x" + Integer.toHexString(opcode).toUpperCase() + ") in class initialiser");
                            }
                        }
                        arrayDeque.add(abstractInsnNode);
                    }
                }
            }
        }
        final AbstractInsnNode abstractInsnNode2 = arrayDeque.peekLast();
        if (abstractInsnNode2 != null && abstractInsnNode2.getOpcode() != 181) {
            throw new InvalidMixinException((IMixinContext)mixinTargetContext, "Could not parse initialiser, expected 0xB5, found 0x" + Integer.toHexString(abstractInsnNode2.getOpcode()) + " in " + mixinTargetContext);
        }
        return arrayDeque;
    }
    
    protected final void injectInitialiser(final MixinTargetContext mixinTargetContext, final MethodNode methodNode, final Deque deque) {
        final Map cloneLabels = Bytecode.cloneLabels(methodNode.instructions);
        AbstractInsnNode initialiserInjectionPoint = this.findInitialiserInjectionPoint(mixinTargetContext, methodNode, deque);
        if (initialiserInjectionPoint == null) {
            this.logger.warn("Failed to locate initialiser injection point in <init>{}, initialiser was not mixed in.", new Object[] { methodNode.desc });
            return;
        }
        for (final AbstractInsnNode abstractInsnNode : deque) {
            if (abstractInsnNode instanceof LabelNode) {
                continue;
            }
            if (abstractInsnNode instanceof JumpInsnNode) {
                throw new InvalidMixinException((IMixinContext)mixinTargetContext, "Unsupported JUMP opcode in initialiser in " + mixinTargetContext);
            }
            final AbstractInsnNode clone = abstractInsnNode.clone(cloneLabels);
            methodNode.instructions.insert(initialiserInjectionPoint, clone);
            initialiserInjectionPoint = clone;
        }
    }
    
    protected AbstractInsnNode findInitialiserInjectionPoint(final MixinTargetContext mixinTargetContext, final MethodNode methodNode, final Deque deque) {
        final HashSet<String> set = new HashSet<String>();
        for (final AbstractInsnNode abstractInsnNode : deque) {
            if (abstractInsnNode.getOpcode() == 181) {
                set.add(fieldKey((FieldInsnNode)abstractInsnNode));
            }
        }
        final InitialiserInjectionMode initialiserInjectionMode = this.getInitialiserInjectionMode(mixinTargetContext.getEnvironment());
        final String name = mixinTargetContext.getTargetClassInfo().getName();
        final String superName = mixinTargetContext.getTargetClassInfo().getSuperName();
        AbstractInsnNode abstractInsnNode2 = null;
        for (final AbstractInsnNode abstractInsnNode3 : methodNode.instructions) {
            if (abstractInsnNode3.getOpcode() == 183 && "<init>".equals(((MethodInsnNode)abstractInsnNode3).name)) {
                final String owner = ((MethodInsnNode)abstractInsnNode3).owner;
                if (!owner.equals(name) && !owner.equals(superName)) {
                    continue;
                }
                abstractInsnNode2 = abstractInsnNode3;
                if (initialiserInjectionMode == InitialiserInjectionMode.SAFE) {
                    break;
                }
                continue;
            }
            else {
                if (abstractInsnNode3.getOpcode() != 181 || initialiserInjectionMode != InitialiserInjectionMode.DEFAULT || !set.contains(fieldKey((FieldInsnNode)abstractInsnNode3))) {
                    continue;
                }
                abstractInsnNode2 = abstractInsnNode3;
            }
        }
        return abstractInsnNode2;
    }
    
    private InitialiserInjectionMode getInitialiserInjectionMode(final MixinEnvironment mixinEnvironment) {
        final String optionValue = mixinEnvironment.getOptionValue(MixinEnvironment.Option.INITIALISER_INJECTION_MODE);
        if (optionValue == null) {
            return InitialiserInjectionMode.DEFAULT;
        }
        try {
            return InitialiserInjectionMode.valueOf(optionValue.toUpperCase());
        }
        catch (Exception ex) {
            this.logger.warn("Could not parse unexpected value \"{}\" for mixin.initialiserInjectionMode, reverting to DEFAULT", new Object[] { optionValue });
            return InitialiserInjectionMode.DEFAULT;
        }
    }
    
    private static String fieldKey(final FieldInsnNode fieldInsnNode) {
        return String.format("%s:%s", fieldInsnNode.desc, fieldInsnNode.name);
    }
    
    protected void prepareInjections(final MixinTargetContext mixinTargetContext) {
        mixinTargetContext.prepareInjections();
    }
    
    protected void applyInjections(final MixinTargetContext mixinTargetContext) {
        mixinTargetContext.applyInjections();
    }
    
    protected void applyAccessors(final MixinTargetContext mixinTargetContext) {
        for (final MethodNode methodNode : mixinTargetContext.generateAccessors()) {
            if (!methodNode.name.startsWith("<")) {
                this.mergeMethod(mixinTargetContext, methodNode);
            }
        }
    }
    
    protected void checkMethodVisibility(final MixinTargetContext mixinTargetContext, final MethodNode methodNode) {
        if (Bytecode.hasFlag(methodNode, 8) && !Bytecode.hasFlag(methodNode, 2) && !Bytecode.hasFlag(methodNode, 4096) && Annotations.getVisible(methodNode, Overwrite.class) == null) {
            throw new InvalidMixinException((IMixinContext)mixinTargetContext, String.format("Mixin %s contains non-private static method %s", mixinTargetContext, methodNode));
        }
    }
    
    protected void applySourceMap(final TargetClassContext targetClassContext) {
        this.targetClass.sourceDebug = targetClassContext.getSourceMap().toString();
    }
    
    protected void checkMethodConstraints(final MixinTargetContext mixinTargetContext, final MethodNode methodNode) {
        final Iterator<Class> iterator = MixinApplicatorStandard.CONSTRAINED_ANNOTATIONS.iterator();
        while (iterator.hasNext()) {
            final AnnotationNode visible = Annotations.getVisible(methodNode, iterator.next());
            if (visible != null) {
                this.checkConstraints(mixinTargetContext, methodNode, visible);
            }
        }
    }
    
    protected final void checkConstraints(final MixinTargetContext mixinTargetContext, final MethodNode methodNode, final AnnotationNode annotationNode) {
        try {
            final ConstraintParser.Constraint parse = ConstraintParser.parse(annotationNode);
            try {
                parse.check((ITokenProvider)mixinTargetContext.getEnvironment());
            }
            catch (ConstraintViolationException ex) {
                final String format = String.format("Constraint violation: %s on %s in %s", ex.getMessage(), methodNode, mixinTargetContext);
                this.logger.warn(format);
                if (!mixinTargetContext.getEnvironment().getOption(MixinEnvironment.Option.IGNORE_CONSTRAINTS)) {
                    throw new InvalidMixinException((IMixinContext)mixinTargetContext, format, ex);
                }
            }
        }
        catch (InvalidConstraintException ex2) {
            throw new InvalidMixinException((IMixinContext)mixinTargetContext, ex2.getMessage());
        }
    }
    
    protected final MethodNode findTargetMethod(final MethodNode methodNode) {
        for (final MethodNode methodNode2 : this.targetClass.methods) {
            if (methodNode2.name.equals(methodNode.name) && methodNode2.desc.equals(methodNode.desc)) {
                return methodNode2;
            }
        }
        return null;
    }
    
    protected final FieldNode findTargetField(final FieldNode fieldNode) {
        for (final FieldNode fieldNode2 : this.targetClass.fields) {
            if (fieldNode2.name.equals(fieldNode.name)) {
                return fieldNode2;
            }
        }
        return null;
    }
    
    static {
        CONSTRAINED_ANNOTATIONS = (List)ImmutableList.of((Object)Overwrite.class, (Object)Inject.class, (Object)ModifyArg.class, (Object)ModifyArgs.class, (Object)Redirect.class, (Object)ModifyVariable.class, (Object)ModifyConstant.class);
        INITIALISER_OPCODE_BLACKLIST = new int[] { 177, 21, 22, 23, 24, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 79, 80, 81, 82, 83, 84, 85, 86 };
    }
    
    class Range
    {
        final int start;
        final int end;
        final int marker;
        final MixinApplicatorStandard this$0;
        
        Range(final MixinApplicatorStandard this$0, final int start, final int end, final int marker) {
            this.this$0 = this$0;
            this.start = start;
            this.end = end;
            this.marker = marker;
        }
        
        boolean isValid() {
            return this.start != 0 && this.end != 0 && this.end >= this.start;
        }
        
        boolean contains(final int n) {
            return n >= this.start && n <= this.end;
        }
        
        boolean excludes(final int n) {
            return n < this.start || n > this.end;
        }
        
        @Override
        public String toString() {
            return String.format("Range[%d-%d,%d,valid=%s)", this.start, this.end, this.marker, this.isValid());
        }
    }
    
    enum InitialiserInjectionMode
    {
        DEFAULT("DEFAULT", 0), 
        SAFE("SAFE", 1);
        
        private static final InitialiserInjectionMode[] $VALUES;
        
        private InitialiserInjectionMode(final String s, final int n) {
        }
        
        static {
            $VALUES = new InitialiserInjectionMode[] { InitialiserInjectionMode.DEFAULT, InitialiserInjectionMode.SAFE };
        }
    }
    
    enum ApplicatorPass
    {
        MAIN("MAIN", 0), 
        PREINJECT("PREINJECT", 1), 
        INJECT("INJECT", 2);
        
        private static final ApplicatorPass[] $VALUES;
        
        private ApplicatorPass(final String s, final int n) {
        }
        
        static {
            $VALUES = new ApplicatorPass[] { ApplicatorPass.MAIN, ApplicatorPass.PREINJECT, ApplicatorPass.INJECT };
        }
    }
}
