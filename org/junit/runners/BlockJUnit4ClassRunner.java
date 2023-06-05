//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runners;

import org.junit.runner.notification.*;
import org.junit.runner.*;
import org.junit.internal.runners.rules.*;
import org.junit.runners.model.*;
import org.junit.internal.runners.model.*;
import org.junit.internal.runners.statements.*;
import java.util.*;
import org.junit.*;
import org.junit.rules.*;

public class BlockJUnit4ClassRunner extends ParentRunner
{
    public BlockJUnit4ClassRunner(final Class clazz) throws InitializationError {
        super(clazz);
    }
    
    protected void runChild(final FrameworkMethod frameworkMethod, final RunNotifier runNotifier) {
        final Description describeChild = this.describeChild(frameworkMethod);
        if (frameworkMethod.getAnnotation(Ignore.class) != null) {
            runNotifier.fireTestIgnored(describeChild);
        }
        else {
            this.runLeaf(this.methodBlock(frameworkMethod), describeChild, runNotifier);
        }
    }
    
    protected Description describeChild(final FrameworkMethod frameworkMethod) {
        return Description.createTestDescription(this.getTestClass().getJavaClass(), this.testName(frameworkMethod), frameworkMethod.getAnnotations());
    }
    
    @Override
    protected List getChildren() {
        return this.computeTestMethods();
    }
    
    protected List computeTestMethods() {
        return this.getTestClass().getAnnotatedMethods(Test.class);
    }
    
    @Override
    protected void collectInitializationErrors(final List list) {
        super.collectInitializationErrors(list);
        this.validateNoNonStaticInnerClass(list);
        this.validateConstructor(list);
        this.validateInstanceMethods(list);
        this.validateFields(list);
    }
    
    protected void validateNoNonStaticInnerClass(final List list) {
        if (this.getTestClass().isANonStaticInnerClass()) {
            list.add(new Exception("The inner class " + this.getTestClass().getName() + " is not static."));
        }
    }
    
    protected void validateConstructor(final List list) {
        this.validateOnlyOneConstructor(list);
        this.validateZeroArgConstructor(list);
    }
    
    protected void validateOnlyOneConstructor(final List p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: if_icmpne       22
        //     4: ldc             "Test class should have exactly one public constructor"
        //     6: astore_2       
        //     7: aload_1        
        //     8: new             Ljava/lang/Exception;
        //    11: dup            
        //    12: aload_2        
        //    13: invokespecial   java/lang/Exception.<init>:(Ljava/lang/String;)V
        //    16: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    21: pop            
        //    22: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected void validateZeroArgConstructor(final List p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   org/junit/runners/BlockJUnit4ClassRunner.getTestClass:()Lorg/junit/runners/model/TestClass;
        //     4: invokevirtual   org/junit/runners/model/TestClass.isANonStaticInnerClass:()Z
        //     7: ifne            46
        //    10: aload_0        
        //    11: if_icmpne       46
        //    14: aload_0        
        //    15: invokevirtual   org/junit/runners/BlockJUnit4ClassRunner.getTestClass:()Lorg/junit/runners/model/TestClass;
        //    18: invokevirtual   org/junit/runners/model/TestClass.getOnlyConstructor:()Ljava/lang/reflect/Constructor;
        //    21: invokevirtual   java/lang/reflect/Constructor.getParameterTypes:()[Ljava/lang/Class;
        //    24: arraylength    
        //    25: ifeq            46
        //    28: ldc             "Test class should have exactly one public zero-argument constructor"
        //    30: astore_2       
        //    31: aload_1        
        //    32: new             Ljava/lang/Exception;
        //    35: dup            
        //    36: aload_2        
        //    37: invokespecial   java/lang/Exception.<init>:(Ljava/lang/String;)V
        //    40: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    45: pop            
        //    46: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Deprecated
    protected void validateInstanceMethods(final List list) {
        this.validatePublicVoidNoArgMethods(After.class, false, list);
        this.validatePublicVoidNoArgMethods(Before.class, false, list);
        this.validateTestMethods(list);
        if (this.computeTestMethods().size() == 0) {
            list.add(new Exception("No runnable methods"));
        }
    }
    
    private void validateFields(final List list) {
        RuleFieldValidator.RULE_VALIDATOR.validate(this.getTestClass(), list);
    }
    
    protected void validateTestMethods(final List list) {
        this.validatePublicVoidNoArgMethods(Test.class, false, list);
    }
    
    protected Object createTest() throws Exception {
        return this.getTestClass().getOnlyConstructor().newInstance(new Object[0]);
    }
    
    protected String testName(final FrameworkMethod frameworkMethod) {
        return frameworkMethod.getName();
    }
    
    protected Statement methodBlock(final FrameworkMethod frameworkMethod) {
        Object run;
        try {
            run = new ReflectiveCallable() {
                final BlockJUnit4ClassRunner this$0;
                
                protected Object runReflectiveCall() throws Throwable {
                    return this.this$0.createTest();
                }
            }.run();
        }
        catch (Throwable t) {
            return (Statement)new Fail(t);
        }
        return this.withRules(frameworkMethod, run, this.withAfters(frameworkMethod, run, this.withBefores(frameworkMethod, run, this.withPotentialTimeout(frameworkMethod, run, this.possiblyExpectingExceptions(frameworkMethod, run, this.methodInvoker(frameworkMethod, run))))));
    }
    
    protected Statement methodInvoker(final FrameworkMethod frameworkMethod, final Object o) {
        return (Statement)new InvokeMethod(frameworkMethod, o);
    }
    
    @Deprecated
    protected Statement possiblyExpectingExceptions(final FrameworkMethod frameworkMethod, final Object o, final Statement statement) {
        final Test test = (Test)frameworkMethod.getAnnotation(Test.class);
        return (Statement)((test != null) ? new ExpectException(statement, this.getExpectedException(test)) : statement);
    }
    
    @Deprecated
    protected Statement withPotentialTimeout(final FrameworkMethod frameworkMethod, final Object o, final Statement statement) {
        final long timeout = this.getTimeout((Test)frameworkMethod.getAnnotation(Test.class));
        return (Statement)((timeout > 0L) ? new FailOnTimeout(statement, timeout) : statement);
    }
    
    @Deprecated
    protected Statement withBefores(final FrameworkMethod frameworkMethod, final Object o, final Statement statement) {
        final List annotatedMethods = this.getTestClass().getAnnotatedMethods(Before.class);
        return (Statement)(annotatedMethods.isEmpty() ? statement : new RunBefores(statement, annotatedMethods, o));
    }
    
    @Deprecated
    protected Statement withAfters(final FrameworkMethod frameworkMethod, final Object o, final Statement statement) {
        final List annotatedMethods = this.getTestClass().getAnnotatedMethods(After.class);
        return (Statement)(annotatedMethods.isEmpty() ? statement : new RunAfters(statement, annotatedMethods, o));
    }
    
    private Statement withRules(final FrameworkMethod frameworkMethod, final Object o, final Statement statement) {
        return this.withTestRules(frameworkMethod, o, this.withMethodRules(frameworkMethod, o, statement));
    }
    
    private Statement withMethodRules(final FrameworkMethod frameworkMethod, final Object o, Statement apply) {
        final List testRules = this.getTestRules(o);
        for (final MethodRule methodRule : this.getMethodRules(o)) {
            if (!testRules.contains(methodRule)) {
                apply = methodRule.apply(apply, frameworkMethod, o);
            }
        }
        return apply;
    }
    
    private List getMethodRules(final Object o) {
        return this.rules(o);
    }
    
    @Deprecated
    protected List rules(final Object o) {
        return this.getTestClass().getAnnotatedFieldValues(o, Rule.class, MethodRule.class);
    }
    
    private Statement withTestRules(final FrameworkMethod frameworkMethod, final Object o, final Statement statement) {
        final List testRules = this.getTestRules(o);
        return (Statement)(testRules.isEmpty() ? statement : new RunRules(statement, (Iterable)testRules, this.describeChild(frameworkMethod)));
    }
    
    protected List getTestRules(final Object o) {
        return this.getTestClass().getAnnotatedFieldValues(o, Rule.class, TestRule.class);
    }
    
    private Class getExpectedException(final Test test) {
        if (test == null || test.expected() == Test.None.class) {
            return null;
        }
        return test.expected();
    }
    
    private long getTimeout(final Test test) {
        if (test == null) {
            return 0L;
        }
        return test.timeout();
    }
    
    @Override
    protected void runChild(final Object o, final RunNotifier runNotifier) {
        this.runChild((FrameworkMethod)o, runNotifier);
    }
    
    @Override
    protected Description describeChild(final Object o) {
        return this.describeChild((FrameworkMethod)o);
    }
}
