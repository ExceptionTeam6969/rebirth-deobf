//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners;

import org.junit.*;
import java.lang.reflect.*;
import java.util.*;

@Deprecated
public class MethodValidator
{
    private final List fErrors;
    private TestClass fTestClass;
    
    public MethodValidator(final TestClass fTestClass) {
        this.fErrors = new ArrayList();
        this.fTestClass = fTestClass;
    }
    
    public void validateInstanceMethods() {
        this.validateTestMethods(After.class, false);
        this.validateTestMethods(Before.class, false);
        this.validateTestMethods(Test.class, false);
        if (this.fTestClass.getAnnotatedMethods(Test.class).size() == 0) {
            this.fErrors.add(new Exception("No runnable methods"));
        }
    }
    
    public void validateStaticMethods() {
        this.validateTestMethods(BeforeClass.class, true);
        this.validateTestMethods(AfterClass.class, true);
    }
    
    public List validateMethodsForDefaultRunner() {
        this.validateNoArgConstructor();
        this.validateStaticMethods();
        this.validateInstanceMethods();
        return this.fErrors;
    }
    
    public void assertValid() throws InitializationError {
        if (!this.fErrors.isEmpty()) {
            throw new InitializationError(this.fErrors);
        }
    }
    
    public void validateNoArgConstructor() {
        try {
            this.fTestClass.getConstructor();
        }
        catch (Exception ex) {
            this.fErrors.add(new Exception("Test class should have public zero-argument constructor", ex));
        }
    }
    
    private void validateTestMethods(final Class clazz, final boolean b) {
        for (final Method method : this.fTestClass.getAnnotatedMethods(clazz)) {
            if (Modifier.isStatic(method.getModifiers()) != b) {
                this.fErrors.add(new Exception("Method " + method.getName() + "() " + (b ? "should" : "should not") + " be static"));
            }
            if (!Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
                this.fErrors.add(new Exception("Class " + method.getDeclaringClass().getName() + " should be public"));
            }
            if (!Modifier.isPublic(method.getModifiers())) {
                this.fErrors.add(new Exception("Method " + method.getName() + " should be public"));
            }
            if (method.getReturnType() != Void.TYPE) {
                this.fErrors.add(new Exception("Method " + method.getName() + " should be void"));
            }
            if (method.getParameterTypes().length != 0) {
                this.fErrors.add(new Exception("Method " + method.getName() + " should have no parameters"));
            }
        }
    }
}
