//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runners.model;

import java.util.*;
import org.junit.*;
import java.lang.annotation.*;
import java.lang.reflect.*;

public class TestClass
{
    private final Class fClass;
    private Map fMethodsForAnnotations;
    private Map fFieldsForAnnotations;
    
    public TestClass(final Class fClass) {
        this.fMethodsForAnnotations = new HashMap();
        this.fFieldsForAnnotations = new HashMap();
        this.fClass = fClass;
        if (fClass != null && fClass.getConstructors().length > 1) {
            throw new IllegalArgumentException("Test class can only have one constructor");
        }
        for (final Class clazz : this.getSuperClasses(this.fClass)) {
            final Method[] declaredMethods = clazz.getDeclaredMethods();
            for (int length = declaredMethods.length, i = 0; i < length; ++i) {
                this.addToAnnotationLists((FrameworkMember)new FrameworkMethod(declaredMethods[i]), this.fMethodsForAnnotations);
            }
            final Field[] declaredFields = clazz.getDeclaredFields();
            for (int length2 = declaredFields.length, j = 0; j < length2; ++j) {
                this.addToAnnotationLists((FrameworkMember)new FrameworkField(declaredFields[j]), this.fFieldsForAnnotations);
            }
        }
    }
    
    private void addToAnnotationLists(final FrameworkMember p0, final Map p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   org/junit/runners/model/FrameworkMember.getAnnotations:()[Ljava/lang/annotation/Annotation;
        //     4: astore_3       
        //     5: aload_3        
        //     6: arraylength    
        //     7: istore          4
        //     9: iconst_0       
        //    10: istore          5
        //    12: iload           5
        //    14: iload           4
        //    16: if_icmpge       86
        //    19: aload_3        
        //    20: iload           5
        //    22: aaload         
        //    23: astore          6
        //    25: aload           6
        //    27: invokeinterface java/lang/annotation/Annotation.annotationType:()Ljava/lang/Class;
        //    32: astore          7
        //    34: aload_0        
        //    35: aload_2        
        //    36: aload           7
        //    38: invokespecial   org/junit/runners/model/TestClass.getAnnotatedMembers:(Ljava/util/Map;Ljava/lang/Class;)Ljava/util/List;
        //    41: astore          8
        //    43: aload_1        
        //    44: aload           8
        //    46: invokevirtual   org/junit/runners/model/FrameworkMember.isShadowedBy:(Ljava/util/List;)Z
        //    49: ifeq            53
        //    52: return         
        //    53: aload_0        
        //    54: aload           7
        //    56: ifne            71
        //    59: aload           8
        //    61: iconst_0       
        //    62: aload_1        
        //    63: invokeinterface java/util/List.add:(ILjava/lang/Object;)V
        //    68: goto            80
        //    71: aload           8
        //    73: aload_1        
        //    74: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    79: pop            
        //    80: iinc            5, 1
        //    83: goto            12
        //    86: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0012 (coming from #0083).
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
    
    public List getAnnotatedMethods(final Class clazz) {
        return this.getAnnotatedMembers(this.fMethodsForAnnotations, clazz);
    }
    
    public List getAnnotatedFields(final Class clazz) {
        return this.getAnnotatedMembers(this.fFieldsForAnnotations, clazz);
    }
    
    private List getAnnotatedMembers(final Map map, final Class clazz) {
        if (!map.containsKey(clazz)) {
            map.put(clazz, new ArrayList());
        }
        return map.get(clazz);
    }
    
    private List getSuperClasses(final Class clazz) {
        final ArrayList<Class> list = new ArrayList<Class>();
        for (Class superclass = clazz; superclass != null; superclass = superclass.getSuperclass()) {
            list.add(superclass);
        }
        return list;
    }
    
    public Class getJavaClass() {
        return this.fClass;
    }
    
    public String getName() {
        if (this.fClass == null) {
            return "null";
        }
        return this.fClass.getName();
    }
    
    public Constructor getOnlyConstructor() {
        final Constructor[] constructors = this.fClass.getConstructors();
        Assert.assertEquals(1L, (long)constructors.length);
        return constructors[0];
    }
    
    public Annotation[] getAnnotations() {
        if (this.fClass == null) {
            return new Annotation[0];
        }
        return this.fClass.getAnnotations();
    }
    
    public List getAnnotatedFieldValues(final Object o, final Class clazz, final Class clazz2) {
        final ArrayList<Object> list = new ArrayList<Object>();
        for (final FrameworkField frameworkField : this.getAnnotatedFields(clazz)) {
            try {
                final Object value = frameworkField.get(o);
                if (!clazz2.isInstance(value)) {
                    continue;
                }
                list.add(clazz2.cast(value));
            }
            catch (IllegalAccessException ex) {
                throw new RuntimeException("How did getFields return a field we couldn't access?", ex);
            }
        }
        return list;
    }
    
    public boolean isANonStaticInnerClass() {
        return this.fClass.isMemberClass() && !Modifier.isStatic(this.fClass.getModifiers());
    }
}
