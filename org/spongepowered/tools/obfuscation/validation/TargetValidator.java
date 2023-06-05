//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.validation;

import org.spongepowered.tools.obfuscation.*;
import org.spongepowered.tools.obfuscation.interfaces.*;
import javax.lang.model.element.*;
import org.spongepowered.asm.mixin.gen.*;
import java.util.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import javax.lang.model.type.*;

public class TargetValidator extends MixinValidator
{
    public TargetValidator(final IMixinAnnotationProcessor mixinAnnotationProcessor) {
        super(mixinAnnotationProcessor, IMixinValidator.ValidationPass.LATE);
    }
    
    public boolean validate(final TypeElement typeElement, final AnnotationHandle annotationHandle, final Collection collection) {
        if ("true".equalsIgnoreCase(this.options.getOption("disableTargetValidator"))) {
            return true;
        }
        if (typeElement.getKind() == ElementKind.INTERFACE) {
            this.validateInterfaceMixin(typeElement, collection);
        }
        else {
            this.validateClassMixin(typeElement, collection);
        }
        return true;
    }
    
    private void validateInterfaceMixin(final TypeElement typeElement, final Collection collection) {
        boolean b = false;
        for (final Element element : typeElement.getEnclosedElements()) {
            if (element.getKind() == ElementKind.METHOD) {
                final boolean exists = AnnotationHandle.of(element, (Class)Accessor.class).exists();
                final boolean exists2 = AnnotationHandle.of(element, (Class)Invoker.class).exists();
                b |= (!exists && !exists2);
            }
        }
        if (!b) {
            return;
        }
        for (final TypeHandle typeHandle : collection) {
            final TypeElement element2 = typeHandle.getElement();
            if (element2 != null && element2.getKind() != ElementKind.INTERFACE) {
                this.error("Targetted type '" + typeHandle + " of " + typeElement + " is not an interface", (Element)typeElement);
            }
        }
    }
    
    private void validateClassMixin(final TypeElement p0, final Collection p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokeinterface javax/lang/model/element/TypeElement.getSuperclass:()Ljavax/lang/model/type/TypeMirror;
        //     6: astore_3       
        //     7: aload_2        
        //     8: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //    13: astore          4
        //    15: aload           4
        //    17: invokeinterface java/util/Iterator.hasNext:()Z
        //    22: ifeq            102
        //    25: aload           4
        //    27: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    32: checkcast       Lorg/spongepowered/tools/obfuscation/mirror/TypeHandle;
        //    35: astore          5
        //    37: aload           5
        //    39: invokevirtual   org/spongepowered/tools/obfuscation/mirror/TypeHandle.getType:()Ljavax/lang/model/type/TypeMirror;
        //    42: astore          6
        //    44: aload           6
        //    46: ifnull          99
        //    49: aload_0        
        //    50: aload           6
        //    52: aload_3        
        //    53: ifeq            99
        //    56: aload_0        
        //    57: new             Ljava/lang/StringBuilder;
        //    60: dup            
        //    61: invokespecial   java/lang/StringBuilder.<init>:()V
        //    64: ldc             "Superclass "
        //    66: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    69: aload_3        
        //    70: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    73: ldc             " of "
        //    75: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    78: aload_1        
        //    79: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    82: ldc             " was not found in the hierarchy of target class "
        //    84: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    87: aload           6
        //    89: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    92: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    95: aload_1        
        //    96: invokevirtual   org/spongepowered/tools/obfuscation/validation/TargetValidator.error:(Ljava/lang/String;Ljavax/lang/model/element/Element;)V
        //    99: goto            15
        //   102: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0099 (coming from #0053).
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
    
    private boolean validateSuperClassRecursive(final TypeMirror typeMirror, final TypeMirror typeMirror2) {
        if (!(typeMirror instanceof DeclaredType)) {
            return false;
        }
        if (TypeUtils.isAssignable(this.processingEnv, typeMirror, typeMirror2)) {
            return true;
        }
        final TypeMirror superclass = ((TypeElement)((DeclaredType)typeMirror).asElement()).getSuperclass();
        return superclass.getKind() != TypeKind.NONE && (typeMirror2 != 0 || this.validateSuperClassRecursive(superclass, typeMirror2));
    }
}
