//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.interfaces.*;
import javax.tools.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import org.spongepowered.tools.obfuscation.struct.*;
import java.util.*;
import javax.lang.model.element.*;
import org.spongepowered.asm.mixin.injection.*;

class AnnotatedMixinElementHandlerInjector extends AnnotatedMixinElementHandler
{
    AnnotatedMixinElementHandlerInjector(final IMixinAnnotationProcessor mixinAnnotationProcessor, final AnnotatedMixin annotatedMixin) {
        super(mixinAnnotationProcessor, annotatedMixin);
    }
    
    public void registerInjector(final AnnotatedElementInjector p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerInjector.mixin:Lorg/spongepowered/tools/obfuscation/AnnotatedMixin;
        //     4: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixin.isInterface:()Z
        //     7: ifeq            28
        //    10: aload_0        
        //    11: getfield        org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerInjector.ap:Lorg/spongepowered/tools/obfuscation/interfaces/IMixinAnnotationProcessor;
        //    14: getstatic       javax/tools/Diagnostic$Kind.ERROR:Ljavax/tools/Diagnostic$Kind;
        //    17: ldc             "Injector in interface is unsupported"
        //    19: aload_1        
        //    20: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerInjector$AnnotatedElementInjector.getElement:()Ljavax/lang/model/element/Element;
        //    23: invokeinterface org/spongepowered/tools/obfuscation/interfaces/IMixinAnnotationProcessor.printMessage:(Ljavax/tools/Diagnostic$Kind;Ljava/lang/CharSequence;Ljavax/lang/model/element/Element;)V
        //    28: aload_1        
        //    29: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerInjector$AnnotatedElementInjector.getAnnotation:()Lorg/spongepowered/tools/obfuscation/mirror/AnnotationHandle;
        //    32: ldc             "method"
        //    34: invokevirtual   org/spongepowered/tools/obfuscation/mirror/AnnotationHandle.getList:(Ljava/lang/String;)Ljava/util/List;
        //    37: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    42: astore_2       
        //    43: aload_2        
        //    44: invokeinterface java/util/Iterator.hasNext:()Z
        //    49: ifeq            200
        //    52: aload_2        
        //    53: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    58: checkcast       Ljava/lang/String;
        //    61: astore_3       
        //    62: aload_3        
        //    63: invokestatic    org/spongepowered/asm/mixin/injection/struct/MemberInfo.parse:(Ljava/lang/String;)Lorg/spongepowered/asm/mixin/injection/struct/MemberInfo;
        //    66: astore          4
        //    68: aload           4
        //    70: getfield        org/spongepowered/asm/mixin/injection/struct/MemberInfo.name:Ljava/lang/String;
        //    73: ifnonnull       79
        //    76: goto            43
        //    79: aload           4
        //    81: invokevirtual   org/spongepowered/asm/mixin/injection/struct/MemberInfo.validate:()Lorg/spongepowered/asm/mixin/injection/struct/MemberInfo;
        //    84: pop            
        //    85: goto            106
        //    88: astore          5
        //    90: aload_1        
        //    91: aload_0        
        //    92: getfield        org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerInjector.ap:Lorg/spongepowered/tools/obfuscation/interfaces/IMixinAnnotationProcessor;
        //    95: getstatic       javax/tools/Diagnostic$Kind.ERROR:Ljavax/tools/Diagnostic$Kind;
        //    98: aload           5
        //   100: invokevirtual   org/spongepowered/asm/mixin/injection/struct/InvalidMemberDescriptorException.getMessage:()Ljava/lang/String;
        //   103: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerInjector$AnnotatedElementInjector.printMessage:(Ljavax/annotation/processing/Messager;Ljavax/tools/Diagnostic$Kind;Ljava/lang/CharSequence;)V
        //   106: aload           4
        //   108: getfield        org/spongepowered/asm/mixin/injection/struct/MemberInfo.desc:Ljava/lang/String;
        //   111: ifnull          135
        //   114: aload_0        
        //   115: aload_1        
        //   116: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerInjector$AnnotatedElementInjector.getElement:()Ljavax/lang/model/element/Element;
        //   119: checkcast       Ljavax/lang/model/element/ExecutableElement;
        //   122: aload_1        
        //   123: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerInjector$AnnotatedElementInjector.getAnnotation:()Lorg/spongepowered/tools/obfuscation/mirror/AnnotationHandle;
        //   126: aload           4
        //   128: aload_1        
        //   129: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerInjector$AnnotatedElementInjector.toString:()Ljava/lang/String;
        //   132: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerInjector.validateReferencedTarget:(Ljavax/lang/model/element/ExecutableElement;Lorg/spongepowered/tools/obfuscation/mirror/AnnotationHandle;Lorg/spongepowered/asm/mixin/injection/struct/MemberInfo;Ljava/lang/String;)V
        //   135: aload_1        
        //   136: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerInjector$AnnotatedElementInjector.shouldRemap:()Z
        //   139: ifne            145
        //   142: goto            43
        //   145: aload_0        
        //   146: getfield        org/spongepowered/tools/obfuscation/AnnotatedMixinElementHandlerInjector.mixin:Lorg/spongepowered/tools/obfuscation/AnnotatedMixin;
        //   149: invokevirtual   org/spongepowered/tools/obfuscation/AnnotatedMixin.getTargets:()Ljava/util/List;
        //   152: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   157: astore          5
        //   159: aload           5
        //   161: invokeinterface java/util/Iterator.hasNext:()Z
        //   166: ifeq            197
        //   169: aload           5
        //   171: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   176: checkcast       Lorg/spongepowered/tools/obfuscation/mirror/TypeHandle;
        //   179: astore          6
        //   181: aload_0        
        //   182: aload_1        
        //   183: aload_3        
        //   184: aload           4
        //   186: aload           6
        //   188: ifnonnull       194
        //   191: goto            197
        //   194: goto            159
        //   197: goto            43
        //   200: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                                           
        //  -----  -----  -----  -----  -------------------------------------------------------------------------------
        //  79     85     88     106    Lorg/spongepowered/asm/mixin/injection/struct/InvalidMemberDescriptorException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0159 (coming from #0194).
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
    
    public void registerInjectionPoint(final AnnotatedElementInjectionPoint annotatedElementInjectionPoint, final String s) {
        if (this.mixin.isInterface()) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, "Injector in interface is unsupported", annotatedElementInjectionPoint.getElement());
        }
        if (!annotatedElementInjectionPoint.shouldRemap()) {
            return;
        }
        final String type = InjectionPointData.parseType((String)annotatedElementInjectionPoint.getAt().getValue("value"));
        final String s2 = (String)annotatedElementInjectionPoint.getAt().getValue("target");
        if ("NEW".equals(type)) {
            this.remapNewTarget(String.format(s, type + ".<target>"), s2, annotatedElementInjectionPoint);
            this.remapNewTarget(String.format(s, type + ".args[class]"), annotatedElementInjectionPoint.getAtArg("class"), annotatedElementInjectionPoint);
        }
        else {
            this.remapReference(String.format(s, type + ".<target>"), s2, annotatedElementInjectionPoint);
        }
    }
    
    protected final void remapNewTarget(final String s, final String s2, final AnnotatedElementInjectionPoint annotatedElementInjectionPoint) {
        if (s2 == null) {
            return;
        }
        final MemberInfo parse = MemberInfo.parse(s2);
        final String ctorType = parse.toCtorType();
        if (ctorType != null) {
            final String ctorDesc = parse.toCtorDesc();
            final ObfuscationData remappedMethod = this.obf.getDataProvider().getRemappedMethod(new MappingMethod(ctorType, ".", (ctorDesc != null) ? ctorDesc : "()V"));
            if (remappedMethod.isEmpty()) {
                this.ap.printMessage(Diagnostic.Kind.WARNING, "Cannot find class mapping for " + s + " '" + ctorType + "'", annotatedElementInjectionPoint.getElement(), annotatedElementInjectionPoint.getAnnotation().asMirror());
                return;
            }
            final ObfuscationData obfuscationData = new ObfuscationData();
            for (final ObfuscationType obfuscationType : remappedMethod) {
                final MappingMethod mappingMethod = (MappingMethod)remappedMethod.get(obfuscationType);
                if (ctorDesc == null) {
                    obfuscationData.put(obfuscationType, mappingMethod.getOwner());
                }
                else {
                    obfuscationData.put(obfuscationType, mappingMethod.getDesc().replace(")V", ")L" + mappingMethod.getOwner() + ";"));
                }
            }
            this.obf.getReferenceManager().addClassMapping(this.classRef, s2, obfuscationData);
        }
        annotatedElementInjectionPoint.notifyRemapped();
    }
    
    protected final void remapReference(final String s, final String s2, final AnnotatedElementInjectionPoint annotatedElementInjectionPoint) {
        if (s2 == null) {
            return;
        }
        final AnnotationMirror mirror = ((this.ap.getCompilerEnvironment() == IMixinAnnotationProcessor.CompilerEnvironment.JDT) ? annotatedElementInjectionPoint.getAt() : annotatedElementInjectionPoint.getAnnotation()).asMirror();
        final MemberInfo parse = MemberInfo.parse(s2);
        if (!parse.isFullyQualified()) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, s + " is not fully qualified, missing " + ((parse.owner == null) ? ((parse.desc == null) ? "owner and signature" : "owner") : "signature"), annotatedElementInjectionPoint.getElement(), mirror);
            return;
        }
        try {
            parse.validate();
        }
        catch (InvalidMemberDescriptorException ex) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, ex.getMessage(), annotatedElementInjectionPoint.getElement(), mirror);
        }
        try {
            if (parse.isField()) {
                final ObfuscationData obfFieldRecursive = this.obf.getDataProvider().getObfFieldRecursive(parse);
                if (obfFieldRecursive.isEmpty()) {
                    this.ap.printMessage(Diagnostic.Kind.WARNING, "Cannot find field mapping for " + s + " '" + s2 + "'", annotatedElementInjectionPoint.getElement(), mirror);
                    return;
                }
                this.obf.getReferenceManager().addFieldMapping(this.classRef, s2, parse, obfFieldRecursive);
            }
            else {
                final ObfuscationData obfMethodRecursive = this.obf.getDataProvider().getObfMethodRecursive(parse);
                if (obfMethodRecursive.isEmpty() && (parse.owner == null || !parse.owner.startsWith("java/lang/"))) {
                    this.ap.printMessage(Diagnostic.Kind.WARNING, "Cannot find method mapping for " + s + " '" + s2 + "'", annotatedElementInjectionPoint.getElement(), mirror);
                    return;
                }
                this.obf.getReferenceManager().addMethodMapping(this.classRef, s2, parse, obfMethodRecursive);
            }
        }
        catch (ReferenceManager.ReferenceConflictException ex2) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, "Unexpected reference conflict for " + s + ": " + s2 + " -> " + ex2.getNew() + " previously defined as " + ex2.getOld(), annotatedElementInjectionPoint.getElement(), mirror);
            return;
        }
        annotatedElementInjectionPoint.notifyRemapped();
    }
    
    static class AnnotatedElementInjectionPoint extends AnnotatedMixinElementHandler.AnnotatedElement
    {
        private final AnnotationHandle at;
        private Map args;
        private final InjectorRemap state;
        
        public AnnotatedElementInjectionPoint(final ExecutableElement executableElement, final AnnotationHandle annotationHandle, final AnnotationHandle at, final InjectorRemap state) {
            super((Element)executableElement, annotationHandle);
            this.at = at;
            this.state = state;
        }
        
        public boolean shouldRemap() {
            return this.at.getBoolean("remap", this.state.shouldRemap());
        }
        
        public AnnotationHandle getAt() {
            return this.at;
        }
        
        public String getAtArg(final String s) {
            if (this.args == null) {
                this.args = new HashMap();
                for (final String s2 : this.at.getList("args")) {
                    if (s2 == null) {
                        continue;
                    }
                    final int index = s2.indexOf(61);
                    if (index > -1) {
                        this.args.put(s2.substring(0, index), s2.substring(index + 1));
                    }
                    else {
                        this.args.put(s2, "");
                    }
                }
            }
            return this.args.get(s);
        }
        
        public void notifyRemapped() {
            this.state.notifyRemapped();
        }
    }
    
    static class AnnotatedElementInjector extends AnnotatedMixinElementHandler.AnnotatedElement
    {
        private final InjectorRemap state;
        
        public AnnotatedElementInjector(final ExecutableElement executableElement, final AnnotationHandle annotationHandle, final InjectorRemap state) {
            super((Element)executableElement, annotationHandle);
            this.state = state;
        }
        
        public boolean shouldRemap() {
            return this.state.shouldRemap();
        }
        
        public boolean hasCoerceArgument() {
            if (!this.annotation.toString().equals("@Inject")) {
                return false;
            }
            final Iterator<? extends VariableElement> iterator = ((ExecutableElement)this.element).getParameters().iterator();
            return iterator.hasNext() && AnnotationHandle.of((Element)iterator.next(), Coerce.class).exists();
        }
        
        public void addMessage(final Diagnostic.Kind kind, final CharSequence charSequence, final Element element, final AnnotationHandle annotationHandle) {
            this.state.addMessage(kind, charSequence, element, annotationHandle);
        }
        
        public String toString() {
            return this.getAnnotation().toString();
        }
    }
}
