//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.interfaces.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import javax.lang.model.element.*;

class AnnotatedMixinElementHandlerOverwrite extends AnnotatedMixinElementHandler
{
    AnnotatedMixinElementHandlerOverwrite(final IMixinAnnotationProcessor mixinAnnotationProcessor, final AnnotatedMixin annotatedMixin) {
        super(mixinAnnotationProcessor, annotatedMixin);
    }
    
    public void registerMerge(final ExecutableElement executableElement) {
        this.validateTargetMethod(executableElement, (AnnotationHandle)null, new AnnotatedMixinElementHandler.AliasedElementName((Element)executableElement, AnnotationHandle.MISSING), "overwrite", true, true);
    }
    
    public void registerOverwrite(final AnnotatedElementOverwrite p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_1        
        //     5: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getElement:()Ljavax/lang/model/element/Element;
        //     8: aload_1        
        //     9: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getAnnotation:()Lorg/spongepowered/tools/obfuscation/mirror/AnnotationHandle;
        //    12: invokespecial   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandler$AliasedElementName.<init>:(Ljavax/lang/model/element/Element;Lorg/spongepowered/tools/obfuscation/mirror/AnnotationHandle;)V
        //    15: astore_2       
        //    16: aload_0        
        //    17: aload_1        
        //    18: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getElement:()Ljavax/lang/model/element/Element;
        //    21: checkcast       Ljavax/lang/model/element/ExecutableElement;
        //    24: aload_1        
        //    25: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getAnnotation:()Lorg/spongepowered/tools/obfuscation/mirror/AnnotationHandle;
        //    28: aload_2        
        //    29: ldc             "@Overwrite"
        //    31: iconst_1       
        //    32: iconst_0       
        //    33: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite.validateTargetMethod:(Ljavax/lang/model/element/ExecutableElement;Lorg/spongepowered/tools/obfuscation/mirror/AnnotationHandle;Lorg/spongepowered/tools/obfuscation/AnnotatedMixinElementHandler$AliasedElementName;Ljava/lang/String;ZZ)V
        //    36: aload_0        
        //    37: aload_1        
        //    38: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getElement:()Ljavax/lang/model/element/Element;
        //    41: checkcast       Ljavax/lang/model/element/ExecutableElement;
        //    44: aload_1        
        //    45: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getAnnotation:()Lorg/spongepowered/tools/obfuscation/mirror/AnnotationHandle;
        //    48: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite.checkConstraints:(Ljavax/lang/model/element/ExecutableElement;Lorg/spongepowered/tools/obfuscation/mirror/AnnotationHandle;)V
        //    51: aload_1        
        //    52: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.shouldRemap:()Z
        //    55: ifeq            102
        //    58: aload_0        
        //    59: getfield        org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite.mixin:Lorg/spongepowered/tools/obfuscation/AnnotatedMixin;
        //    62: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixin.getTargets:()Ljava/util/List;
        //    65: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    70: astore_3       
        //    71: aload_3        
        //    72: invokeinterface java/util/Iterator.hasNext:()Z
        //    77: ifeq            102
        //    80: aload_3        
        //    81: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    86: checkcast       Lorg/spongepowered/tools/obfuscation/mirror/TypeHandle;
        //    89: astore          4
        //    91: aload_0        
        //    92: aload_1        
        //    93: aload           4
        //    95: ifeq            99
        //    98: return         
        //    99: goto            71
        //   102: ldc             "true"
        //   104: aload_0        
        //   105: getfield        org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite.ap:Lorg/spongepowered/tools/obfuscation/interfaces/IMixinAnnotationProcessor;
        //   108: ldc             "disableOverwriteChecker"
        //   110: invokeinterface org/spongepowered/tools/obfuscation/interfaces/IMixinAnnotationProcessor.getOption:(Ljava/lang/String;)Ljava/lang/String;
        //   115: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   118: ifne            250
        //   121: ldc             "error"
        //   123: aload_0        
        //   124: getfield        org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite.ap:Lorg/spongepowered/tools/obfuscation/interfaces/IMixinAnnotationProcessor;
        //   127: ldc             "overwriteErrorLevel"
        //   129: invokeinterface org/spongepowered/tools/obfuscation/interfaces/IMixinAnnotationProcessor.getOption:(Ljava/lang/String;)Ljava/lang/String;
        //   134: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   137: ifeq            146
        //   140: getstatic       javax/tools/Diagnostic$Kind.ERROR:Ljavax/tools/Diagnostic$Kind;
        //   143: goto            149
        //   146: getstatic       javax/tools/Diagnostic$Kind.WARNING:Ljavax/tools/Diagnostic$Kind;
        //   149: astore_3       
        //   150: aload_0        
        //   151: getfield        org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite.ap:Lorg/spongepowered/tools/obfuscation/interfaces/IMixinAnnotationProcessor;
        //   154: invokeinterface org/spongepowered/tools/obfuscation/interfaces/IMixinAnnotationProcessor.getJavadocProvider:()Lorg/spongepowered/tools/obfuscation/interfaces/IJavadocProvider;
        //   159: aload_1        
        //   160: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getElement:()Ljavax/lang/model/element/Element;
        //   163: invokeinterface org/spongepowered/tools/obfuscation/interfaces/IJavadocProvider.getJavadoc:(Ljavax/lang/model/element/Element;)Ljava/lang/String;
        //   168: astore          4
        //   170: aload           4
        //   172: ifnonnull       192
        //   175: aload_0        
        //   176: getfield        org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite.ap:Lorg/spongepowered/tools/obfuscation/interfaces/IMixinAnnotationProcessor;
        //   179: aload_3        
        //   180: ldc             "@Overwrite is missing javadoc comment"
        //   182: aload_1        
        //   183: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getElement:()Ljavax/lang/model/element/Element;
        //   186: invokeinterface org/spongepowered/tools/obfuscation/interfaces/IMixinAnnotationProcessor.printMessage:(Ljavax/tools/Diagnostic$Kind;Ljava/lang/CharSequence;Ljavax/lang/model/element/Element;)V
        //   191: return         
        //   192: aload           4
        //   194: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
        //   197: ldc             "@author"
        //   199: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   202: ifne            221
        //   205: aload_0        
        //   206: getfield        org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite.ap:Lorg/spongepowered/tools/obfuscation/interfaces/IMixinAnnotationProcessor;
        //   209: aload_3        
        //   210: ldc             "@Overwrite is missing an @author tag"
        //   212: aload_1        
        //   213: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getElement:()Ljavax/lang/model/element/Element;
        //   216: invokeinterface org/spongepowered/tools/obfuscation/interfaces/IMixinAnnotationProcessor.printMessage:(Ljavax/tools/Diagnostic$Kind;Ljava/lang/CharSequence;Ljavax/lang/model/element/Element;)V
        //   221: aload           4
        //   223: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
        //   226: ldc             "@reason"
        //   228: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   231: ifne            250
        //   234: aload_0        
        //   235: getfield        org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite.ap:Lorg/spongepowered/tools/obfuscation/interfaces/IMixinAnnotationProcessor;
        //   238: aload_3        
        //   239: ldc             "@Overwrite is missing an @reason tag"
        //   241: aload_1        
        //   242: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerOverwrite$AnnotatedElementOverwrite.getElement:()Ljavax/lang/model/element/Element;
        //   245: invokeinterface org/spongepowered/tools/obfuscation/interfaces/IMixinAnnotationProcessor.printMessage:(Ljavax/tools/Diagnostic$Kind;Ljava/lang/CharSequence;Ljavax/lang/model/element/Element;)V
        //   250: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0071 (coming from #0099).
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
    
    static class AnnotatedElementOverwrite extends AnnotatedMixinElementHandler.AnnotatedElement
    {
        private final boolean shouldRemap;
        
        public AnnotatedElementOverwrite(final ExecutableElement executableElement, final AnnotationHandle annotationHandle, final boolean shouldRemap) {
            super((Element)executableElement, annotationHandle);
            this.shouldRemap = shouldRemap;
        }
        
        public boolean shouldRemap() {
            return this.shouldRemap;
        }
    }
}
