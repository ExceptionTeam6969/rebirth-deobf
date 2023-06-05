//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.experimental.theories;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

public class ParameterSignature
{
    private final Class type;
    private final Annotation[] annotations;
    
    public static ArrayList signatures(final Method method) {
        return signatures(method.getParameterTypes(), method.getParameterAnnotations());
    }
    
    public static List signatures(final Constructor constructor) {
        return signatures(constructor.getParameterTypes(), constructor.getParameterAnnotations());
    }
    
    private static ArrayList signatures(final Class[] array, final Annotation[][] array2) {
        final ArrayList<ParameterSignature> list = new ArrayList<ParameterSignature>();
        for (int i = 0; i < array.length; ++i) {
            list.add(new ParameterSignature(array[i], array2[i]));
        }
        return list;
    }
    
    private ParameterSignature(final Class type, final Annotation[] annotations) {
        this.type = type;
        this.annotations = annotations;
    }
    
    public boolean canAcceptType(final Class clazz) {
        return this.type.isAssignableFrom(clazz);
    }
    
    public Class getType() {
        return this.type;
    }
    
    public List getAnnotations() {
        return Arrays.asList(this.annotations);
    }
    
    public boolean canAcceptArrayType(final Class clazz) {
        return clazz.isArray() && this.canAcceptType(clazz.getComponentType());
    }
    
    public boolean hasAnnotation(final Class clazz) {
        return this.getAnnotation(clazz) != null;
    }
    
    public Annotation findDeepAnnotation(final Class clazz) {
        return this.findDeepAnnotation(this.annotations, clazz, 3);
    }
    
    private Annotation findDeepAnnotation(final Annotation[] array, final Class clazz, final int n) {
        if (n == 0) {
            return null;
        }
        for (final Annotation annotation : array) {
            if (clazz.isInstance(annotation)) {
                return clazz.cast(annotation);
            }
            final Annotation deepAnnotation = this.findDeepAnnotation(annotation.annotationType().getAnnotations(), clazz, n - 1);
            if (deepAnnotation != null) {
                return clazz.cast(deepAnnotation);
            }
        }
        return null;
    }
    
    public Annotation getAnnotation(final Class clazz) {
        for (final Annotation annotation : this.getAnnotations()) {
            if (clazz.isInstance(annotation)) {
                return clazz.cast(annotation);
            }
        }
        return null;
    }
}
