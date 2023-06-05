//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runners;

import java.lang.reflect.*;
import java.util.*;
import org.junit.runner.notification.*;
import org.junit.runners.model.*;
import java.lang.annotation.*;

public class Parameterized extends Suite
{
    private final ArrayList runners;
    
    public Parameterized(final Class clazz) throws Throwable {
        super(clazz, Collections.emptyList());
        this.runners = new ArrayList();
        final List parametersList = this.getParametersList(this.getTestClass());
        for (int i = 0; i < parametersList.size(); ++i) {
            this.runners.add(new TestClassRunnerForParameters(this.getTestClass().getJavaClass(), parametersList, i));
        }
    }
    
    @Override
    protected List getChildren() {
        return this.runners;
    }
    
    private List getParametersList(final TestClass testClass) throws Throwable {
        return (List)this.getParametersMethod(testClass).invokeExplosively((Object)null, new Object[0]);
    }
    
    private FrameworkMethod getParametersMethod(final TestClass testClass) throws Exception {
        for (final FrameworkMethod frameworkMethod : testClass.getAnnotatedMethods((Class)Parameters.class)) {
            final int modifiers = frameworkMethod.getMethod().getModifiers();
            if (Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers)) {
                return frameworkMethod;
            }
        }
        throw new Exception("No public static parameters method on class " + testClass.getName());
    }
    
    static FrameworkMethod access$000(final Parameterized parameterized, final TestClass testClass) throws Exception {
        return parameterized.getParametersMethod(testClass);
    }
    
    private class TestClassRunnerForParameters extends BlockJUnit4ClassRunner
    {
        private final int fParameterSetNumber;
        private final List fParameterList;
        final Parameterized this$0;
        
        TestClassRunnerForParameters(final Parameterized this$0, final Class clazz, final List fParameterList, final int fParameterSetNumber) throws InitializationError {
            this.this$0 = this$0;
            super(clazz);
            this.fParameterList = fParameterList;
            this.fParameterSetNumber = fParameterSetNumber;
        }
        
        public Object createTest() throws Exception {
            return this.getTestClass().getOnlyConstructor().newInstance(this.computeParams());
        }
        
        private Object[] computeParams() throws Exception {
            try {
                return this.fParameterList.get(this.fParameterSetNumber);
            }
            catch (ClassCastException ex) {
                throw new Exception(String.format("%s.%s() must return a Collection of arrays.", this.getTestClass().getName(), Parameterized.access$000(this.this$0, this.getTestClass()).getName()));
            }
        }
        
        protected String getName() {
            return String.format("[%s]", this.fParameterSetNumber);
        }
        
        protected String testName(final FrameworkMethod frameworkMethod) {
            return String.format("%s[%s]", frameworkMethod.getName(), this.fParameterSetNumber);
        }
        
        protected void validateConstructor(final List list) {
            this.validateOnlyOneConstructor(list);
        }
        
        protected Statement classBlock(final RunNotifier runNotifier) {
            return this.childrenInvoker(runNotifier);
        }
        
        protected Annotation[] getRunnerAnnotations() {
            return new Annotation[0];
        }
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.METHOD })
    public @interface Parameters {
    }
}
