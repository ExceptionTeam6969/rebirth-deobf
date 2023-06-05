//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners;

import java.util.*;
import org.junit.*;
import java.lang.reflect.*;

@Deprecated
public class TestMethod
{
    private final Method fMethod;
    private TestClass fTestClass;
    
    public TestMethod(final Method fMethod, final TestClass fTestClass) {
        this.fMethod = fMethod;
        this.fTestClass = fTestClass;
    }
    
    public boolean isIgnored() {
        return this.fMethod.getAnnotation(Ignore.class) != null;
    }
    
    public long getTimeout() {
        final Test test = this.fMethod.getAnnotation(Test.class);
        if (test == null) {
            return 0L;
        }
        return test.timeout();
    }
    
    protected Class getExpectedException() {
        final Test test = this.fMethod.getAnnotation(Test.class);
        if (test == null || test.expected() == Test.None.class) {
            return null;
        }
        return test.expected();
    }
    
    boolean isUnexpected(final Throwable t) {
        return !this.getExpectedException().isAssignableFrom(t.getClass());
    }
    
    boolean expectsException() {
        return this.getExpectedException() != null;
    }
    
    List getBefores() {
        return this.fTestClass.getAnnotatedMethods((Class)Before.class);
    }
    
    List getAfters() {
        return this.fTestClass.getAnnotatedMethods((Class)After.class);
    }
    
    public void invoke(final Object o) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        this.fMethod.invoke(o, new Object[0]);
    }
}
