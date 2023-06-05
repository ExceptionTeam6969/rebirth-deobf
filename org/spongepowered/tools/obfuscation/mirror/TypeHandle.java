//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.mirror;

import javax.lang.model.type.*;
import com.google.common.collect.*;
import java.util.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;
import org.spongepowered.tools.obfuscation.mirror.mapping.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import javax.lang.model.element.*;

public class TypeHandle
{
    private final String name;
    private final PackageElement pkg;
    private final TypeElement element;
    private TypeReference reference;
    
    public TypeHandle(final PackageElement pkg, final String s) {
        this.name = s.replace('.', '/');
        this.pkg = pkg;
        this.element = null;
    }
    
    public TypeHandle(final TypeElement element) {
        this.pkg = TypeUtils.getPackage(element);
        this.name = TypeUtils.getInternalName(element);
        this.element = element;
    }
    
    public TypeHandle(final DeclaredType declaredType) {
        this((TypeElement)declaredType.asElement());
    }
    
    @Override
    public final String toString() {
        return this.name.replace('/', '.');
    }
    
    public final String getName() {
        return this.name;
    }
    
    public final PackageElement getPackage() {
        return this.pkg;
    }
    
    public final TypeElement getElement() {
        return this.element;
    }
    
    protected TypeElement getTargetElement() {
        return this.element;
    }
    
    public AnnotationHandle getAnnotation(final Class clazz) {
        return AnnotationHandle.of((Element)this.getTargetElement(), clazz);
    }
    
    public final List getEnclosedElements() {
        return getEnclosedElements(this.getTargetElement());
    }
    
    public List getEnclosedElements(final ElementKind... array) {
        return getEnclosedElements(this.getTargetElement(), array);
    }
    
    public TypeMirror getType() {
        return (this.getTargetElement() != null) ? this.getTargetElement().asType() : null;
    }
    
    public TypeHandle getSuperclass() {
        final TypeElement targetElement = this.getTargetElement();
        if (targetElement == null) {
            return null;
        }
        final TypeMirror superclass = targetElement.getSuperclass();
        if (superclass == null || superclass.getKind() == TypeKind.NONE) {
            return null;
        }
        return new TypeHandle((DeclaredType)superclass);
    }
    
    public List getInterfaces() {
        if (this.getTargetElement() == null) {
            return Collections.emptyList();
        }
        final ImmutableList.Builder builder = ImmutableList.builder();
        final Iterator<? extends TypeMirror> iterator = this.getTargetElement().getInterfaces().iterator();
        while (iterator.hasNext()) {
            builder.add((Object)new TypeHandle((DeclaredType)iterator.next()));
        }
        return (List)builder.build();
    }
    
    public boolean isPublic() {
        return this.getTargetElement() != null && this.getTargetElement().getModifiers().contains(Modifier.PUBLIC);
    }
    
    public boolean isImaginary() {
        return this.getTargetElement() == null;
    }
    
    public boolean isSimulated() {
        return false;
    }
    
    public final TypeReference getReference() {
        if (this.reference == null) {
            this.reference = new TypeReference(this);
        }
        return this.reference;
    }
    
    public MappingMethod getMappingMethod(final String s, final String s2) {
        return (MappingMethod)new ResolvableMappingMethod(this, s, s2);
    }
    
    public String findDescriptor(final MemberInfo memberInfo) {
        String s = memberInfo.desc;
        if (s == null) {
            for (final ExecutableElement executableElement : this.getEnclosedElements(ElementKind.METHOD)) {
                if (executableElement.getSimpleName().toString().equals(memberInfo.name)) {
                    s = TypeUtils.getDescriptor(executableElement);
                    break;
                }
            }
        }
        return s;
    }
    
    public final FieldHandle findField(final VariableElement variableElement) {
        return this.findField(variableElement, true);
    }
    
    public final FieldHandle findField(final VariableElement variableElement, final boolean b) {
        return this.findField(variableElement.getSimpleName().toString(), TypeUtils.getTypeName(variableElement.asType()), b);
    }
    
    public final FieldHandle findField(final String s, final String s2) {
        return this.findField(s, s2, true);
    }
    
    public FieldHandle findField(final String p0, final String p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokestatic    org/spongepowered/tools/obfuscation/mirror/TypeUtils.stripGenerics:(Ljava/lang/String;)Ljava/lang/String;
        //     4: astore          4
        //     6: aload_0        
        //     7: iconst_1       
        //     8: anewarray       Ljavax/lang/model/element/ElementKind;
        //    11: dup            
        //    12: iconst_0       
        //    13: getstatic       javax/lang/model/element/ElementKind.FIELD:Ljavax/lang/model/element/ElementKind;
        //    16: aastore        
        //    17: invokevirtual   org/spongepowered/tools/obfuscation/mirror/TypeHandle.getEnclosedElements:([Ljavax/lang/model/element/ElementKind;)Ljava/util/List;
        //    20: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    25: astore          5
        //    27: aload           5
        //    29: invokeinterface java/util/Iterator.hasNext:()Z
        //    34: ifeq            98
        //    37: aload           5
        //    39: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    44: checkcast       Ljavax/lang/model/element/VariableElement;
        //    47: astore          6
        //    49: aload           6
        //    51: aload_1        
        //    52: aload_2        
        //    53: iload_3        
        //    54: ifeq            71
        //    57: new             Lorg/spongepowered/tools/obfuscation/mirror/FieldHandle;
        //    60: dup            
        //    61: aload_0        
        //    62: invokevirtual   org/spongepowered/tools/obfuscation/mirror/TypeHandle.getTargetElement:()Ljavax/lang/model/element/TypeElement;
        //    65: aload           6
        //    67: invokespecial   org/spongepowered/tools/obfuscation/mirror/FieldHandle.<init>:(Ljavax/lang/model/element/TypeElement;Ljavax/lang/model/element/VariableElement;)V
        //    70: areturn        
        //    71: aload           6
        //    73: aload_1        
        //    74: aload           4
        //    76: iload_3        
        //    77: ifeq            95
        //    80: new             Lorg/spongepowered/tools/obfuscation/mirror/FieldHandle;
        //    83: dup            
        //    84: aload_0        
        //    85: invokevirtual   org/spongepowered/tools/obfuscation/mirror/TypeHandle.getTargetElement:()Ljavax/lang/model/element/TypeElement;
        //    88: aload           6
        //    90: iconst_1       
        //    91: invokespecial   org/spongepowered/tools/obfuscation/mirror/FieldHandle.<init>:(Ljavax/lang/model/element/TypeElement;Ljavax/lang/model/element/VariableElement;Z)V
        //    94: areturn        
        //    95: goto            27
        //    98: aconst_null    
        //    99: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0027 (coming from #0095).
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
    
    public final MethodHandle findMethod(final ExecutableElement executableElement) {
        return this.findMethod(executableElement, true);
    }
    
    public final MethodHandle findMethod(final ExecutableElement executableElement, final boolean b) {
        return this.findMethod(executableElement.getSimpleName().toString(), TypeUtils.getJavaSignature(executableElement), b);
    }
    
    public final MethodHandle findMethod(final String s, final String s2) {
        return this.findMethod(s, s2, true);
    }
    
    public MethodHandle findMethod(final String s, final String s2, final boolean b) {
        return findMethod(this, s, s2, TypeUtils.stripGenerics(s2), b);
    }
    
    protected static MethodHandle findMethod(final TypeHandle p0, final String p1, final String p2, final String p3, final boolean p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   org/spongepowered/tools/obfuscation/mirror/TypeHandle.getTargetElement:()Ljavax/lang/model/element/TypeElement;
        //     4: iconst_2       
        //     5: anewarray       Ljavax/lang/model/element/ElementKind;
        //     8: dup            
        //     9: iconst_0       
        //    10: getstatic       javax/lang/model/element/ElementKind.CONSTRUCTOR:Ljavax/lang/model/element/ElementKind;
        //    13: aastore        
        //    14: dup            
        //    15: iconst_1       
        //    16: getstatic       javax/lang/model/element/ElementKind.METHOD:Ljavax/lang/model/element/ElementKind;
        //    19: aastore        
        //    20: invokestatic    org/spongepowered/tools/obfuscation/mirror/TypeHandle.getEnclosedElements:(Ljavax/lang/model/element/TypeElement;[Ljavax/lang/model/element/ElementKind;)Ljava/util/List;
        //    23: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    28: astore          5
        //    30: aload           5
        //    32: invokeinterface java/util/Iterator.hasNext:()Z
        //    37: ifeq            84
        //    40: aload           5
        //    42: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    47: checkcast       Ljavax/lang/model/element/ExecutableElement;
        //    50: astore          6
        //    52: aload           6
        //    54: aload_1        
        //    55: aload_2        
        //    56: iload           4
        //    58: ifeq            70
        //    61: aload           6
        //    63: aload_1        
        //    64: aload_3        
        //    65: iload           4
        //    67: ifeq            81
        //    70: new             Lorg/spongepowered/tools/obfuscation/mirror/MethodHandle;
        //    73: dup            
        //    74: aload_0        
        //    75: aload           6
        //    77: invokespecial   org/spongepowered/tools/obfuscation/mirror/MethodHandle.<init>:(Lorg/spongepowered/tools/obfuscation/mirror/TypeHandle;Ljavax/lang/model/element/ExecutableElement;)V
        //    80: areturn        
        //    81: goto            30
        //    84: aconst_null    
        //    85: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0070 (coming from #0067).
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
    
    protected static List getEnclosedElements(final TypeElement typeElement, final ElementKind... array) {
        if (array == null || array.length < 1) {
            return getEnclosedElements(typeElement);
        }
        if (typeElement == null) {
            return Collections.emptyList();
        }
        final ImmutableList.Builder builder = ImmutableList.builder();
        for (final Element element : typeElement.getEnclosedElements()) {
            for (int length = array.length, i = 0; i < length; ++i) {
                if (element.getKind() == array[i]) {
                    builder.add((Object)element);
                    break;
                }
            }
        }
        return (List)builder.build();
    }
    
    protected static List getEnclosedElements(final TypeElement typeElement) {
        return (typeElement != null) ? typeElement.getEnclosedElements() : Collections.emptyList();
    }
}
