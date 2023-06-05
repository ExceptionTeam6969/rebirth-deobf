//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners;

import org.junit.runner.*;
import java.lang.annotation.*;
import java.lang.reflect.*;
import org.junit.runner.notification.*;
import org.junit.runner.manipulation.*;
import java.util.*;

@Deprecated
public class JUnit4ClassRunner extends Runner implements Filterable, Sortable
{
    private final List fTestMethods;
    private TestClass fTestClass;
    
    public JUnit4ClassRunner(final Class clazz) throws InitializationError {
        this.fTestClass = new TestClass(clazz);
        this.fTestMethods = this.getTestMethods();
        this.validate();
    }
    
    protected List getTestMethods() {
        return this.fTestClass.getTestMethods();
    }
    
    protected void validate() throws InitializationError {
        final MethodValidator methodValidator = new MethodValidator(this.fTestClass);
        methodValidator.validateMethodsForDefaultRunner();
        methodValidator.assertValid();
    }
    
    @Override
    public void run(final RunNotifier runNotifier) {
        new ClassRoadie(runNotifier, this.fTestClass, this.getDescription(), (Runnable)new Runnable(runNotifier) {
            final RunNotifier val$notifier;
            final JUnit4ClassRunner this$0;
            
            public void run() {
                this.this$0.runMethods(this.val$notifier);
            }
        }).runProtected();
    }
    
    protected void runMethods(final RunNotifier runNotifier) {
        final Iterator<Method> iterator = this.fTestMethods.iterator();
        while (iterator.hasNext()) {
            this.invokeTestMethod(iterator.next(), runNotifier);
        }
    }
    
    @Override
    public Description getDescription() {
        final Description suiteDescription = Description.createSuiteDescription(this.getName(), this.classAnnotations());
        final Iterator<Method> iterator = this.fTestMethods.iterator();
        while (iterator.hasNext()) {
            suiteDescription.addChild(this.methodDescription(iterator.next()));
        }
        return suiteDescription;
    }
    
    protected Annotation[] classAnnotations() {
        return this.fTestClass.getJavaClass().getAnnotations();
    }
    
    protected String getName() {
        return this.getTestClass().getName();
    }
    
    protected Object createTest() throws Exception {
        return this.getTestClass().getConstructor().newInstance(new Object[0]);
    }
    
    protected void invokeTestMethod(final Method method, final RunNotifier runNotifier) {
        final Description methodDescription = this.methodDescription(method);
        Object test;
        try {
            test = this.createTest();
        }
        catch (InvocationTargetException ex) {
            this.testAborted(runNotifier, methodDescription, ex.getCause());
            return;
        }
        catch (Exception ex2) {
            this.testAborted(runNotifier, methodDescription, ex2);
            return;
        }
        new MethodRoadie(test, this.wrapMethod(method), runNotifier, methodDescription).run();
    }
    
    private void testAborted(final RunNotifier runNotifier, final Description description, final Throwable t) {
        runNotifier.fireTestStarted(description);
        runNotifier.fireTestFailure(new Failure(description, t));
        runNotifier.fireTestFinished(description);
    }
    
    protected TestMethod wrapMethod(final Method method) {
        return new TestMethod(method, this.fTestClass);
    }
    
    protected String testName(final Method method) {
        return method.getName();
    }
    
    protected Description methodDescription(final Method method) {
        return Description.createTestDescription(this.getTestClass().getJavaClass(), this.testName(method), this.testAnnotations(method));
    }
    
    protected Annotation[] testAnnotations(final Method method) {
        return method.getAnnotations();
    }
    
    public void filter(final Filter filter) throws NoTestsRemainException {
        final Iterator<Method> iterator = (Iterator<Method>)this.fTestMethods.iterator();
        while (iterator.hasNext()) {
            if (!filter.shouldRun(this.methodDescription(iterator.next()))) {
                iterator.remove();
            }
        }
        if (this.fTestMethods.isEmpty()) {
            throw new NoTestsRemainException();
        }
    }
    
    public void sort(final Sorter sorter) {
        Collections.sort((List<Object>)this.fTestMethods, new Comparator(sorter) {
            final Sorter val$sorter;
            final JUnit4ClassRunner this$0;
            
            public int compare(final Method method, final Method method2) {
                return this.val$sorter.compare(this.this$0.methodDescription(method), this.this$0.methodDescription(method2));
            }
            
            public int compare(final Object o, final Object o2) {
                return this.compare((Method)o, (Method)o2);
            }
        });
    }
    
    protected TestClass getTestClass() {
        return this.fTestClass;
    }
}
