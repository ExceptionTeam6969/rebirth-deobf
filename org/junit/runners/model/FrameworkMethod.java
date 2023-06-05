//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runners.model;

import org.junit.internal.runners.model.*;
import java.util.*;
import java.lang.reflect.*;
import java.lang.annotation.*;

public class FrameworkMethod extends FrameworkMember
{
    final Method fMethod;
    
    public FrameworkMethod(final Method fMethod) {
        this.fMethod = fMethod;
    }
    
    public Method getMethod() {
        return this.fMethod;
    }
    
    public Object invokeExplosively(final Object o, final Object... array) throws Throwable {
        return new ReflectiveCallable(o, array) {
            final Object val$target;
            final Object[] val$params;
            final FrameworkMethod this$0;
            
            protected Object runReflectiveCall() throws Throwable {
                return this.this$0.fMethod.invoke(this.val$target, this.val$params);
            }
        }.run();
    }
    
    public String getName() {
        return this.fMethod.getName();
    }
    
    public void validatePublicVoidNoArg(final boolean b, final List list) {
        this.validatePublicVoid(b, list);
        if (this.fMethod.getParameterTypes().length != 0) {
            list.add(new Exception("Method " + this.fMethod.getName() + " should have no parameters"));
        }
    }
    
    public void validatePublicVoid(final boolean b, final List list) {
        if (Modifier.isStatic(this.fMethod.getModifiers()) != b) {
            list.add(new Exception("Method " + this.fMethod.getName() + "() " + (b ? "should" : "should not") + " be static"));
        }
        if (!Modifier.isPublic(this.fMethod.getDeclaringClass().getModifiers())) {
            list.add(new Exception("Class " + this.fMethod.getDeclaringClass().getName() + " should be public"));
        }
        if (!Modifier.isPublic(this.fMethod.getModifiers())) {
            list.add(new Exception("Method " + this.fMethod.getName() + "() should be public"));
        }
        if (this.fMethod.getReturnType() != Void.TYPE) {
            list.add(new Exception("Method " + this.fMethod.getName() + "() should be void"));
        }
    }
    
    public void validateNoTypeParametersOnArgs(final List list) {
        new NoGenericTypeParametersValidator(this.fMethod).validate(list);
    }
    
    public boolean isShadowedBy(final FrameworkMethod frameworkMethod) {
        if (!frameworkMethod.getName().equals(this.getName())) {
            return false;
        }
        if (frameworkMethod.getParameterTypes().length != this.getParameterTypes().length) {
            return false;
        }
        for (int i = 0; i < frameworkMethod.getParameterTypes().length; ++i) {
            if (!frameworkMethod.getParameterTypes()[i].equals(this.getParameterTypes()[i])) {
                return false;
            }
        }
        return true;
    }
    
    public boolean equals(final Object o) {
        return FrameworkMethod.class.isInstance(o) && ((FrameworkMethod)o).fMethod.equals(this.fMethod);
    }
    
    public int hashCode() {
        return this.fMethod.hashCode();
    }
    
    @Deprecated
    public boolean producesType(final Type type) {
        return this.getParameterTypes().length == 0 && type instanceof Class && ((Class)type).isAssignableFrom(this.fMethod.getReturnType());
    }
    
    private Class[] getParameterTypes() {
        return this.fMethod.getParameterTypes();
    }
    
    public Annotation[] getAnnotations() {
        return this.fMethod.getAnnotations();
    }
    
    public Annotation getAnnotation(final Class clazz) {
        return this.fMethod.getAnnotation((Class<Annotation>)clazz);
    }
    
    public boolean isShadowedBy(final FrameworkMember frameworkMember) {
        return this.isShadowedBy((FrameworkMethod)frameworkMember);
    }
}
