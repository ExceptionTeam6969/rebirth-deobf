//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners;

import java.lang.annotation.*;
import org.junit.runner.*;
import junit.extensions.*;
import org.junit.runner.manipulation.*;
import org.junit.runner.notification.*;
import junit.framework.*;

public class JUnit38ClassRunner extends Runner implements Filterable, Sortable
{
    private Test fTest;
    
    public JUnit38ClassRunner(final Class clazz) {
        this((Test)new TestSuite((Class)clazz.asSubclass(TestCase.class)));
    }
    
    public JUnit38ClassRunner(final Test test) {
        this.setTest(test);
    }
    
    @Override
    public void run(final RunNotifier runNotifier) {
        final TestResult testResult = new TestResult();
        testResult.addListener(this.createAdaptingListener(runNotifier));
        this.getTest().run(testResult);
    }
    
    public TestListener createAdaptingListener(final RunNotifier runNotifier) {
        return (TestListener)new OldTestClassAdaptingListener(runNotifier, null);
    }
    
    @Override
    public Description getDescription() {
        return makeDescription(this.getTest());
    }
    
    private static Description makeDescription(final Test test) {
        if (test instanceof TestCase) {
            final TestCase testCase = (TestCase)test;
            return Description.createTestDescription(testCase.getClass(), testCase.getName());
        }
        if (test instanceof TestSuite) {
            final TestSuite testSuite = (TestSuite)test;
            final Description suiteDescription = Description.createSuiteDescription((testSuite.getName() == null) ? createSuiteDescription(testSuite) : testSuite.getName(), new Annotation[0]);
            for (int testCount = testSuite.testCount(), i = 0; i < testCount; ++i) {
                suiteDescription.addChild(makeDescription(testSuite.testAt(i)));
            }
            return suiteDescription;
        }
        if (test instanceof Describable) {
            return ((Describable)test).getDescription();
        }
        if (test instanceof TestDecorator) {
            return makeDescription(((TestDecorator)test).getTest());
        }
        return Description.createSuiteDescription(test.getClass());
    }
    
    private static String createSuiteDescription(final TestSuite testSuite) {
        final int countTestCases = testSuite.countTestCases();
        return String.format("TestSuite with %s tests%s", countTestCases, (countTestCases == 0) ? "" : String.format(" [example: %s]", testSuite.testAt(0)));
    }
    
    public void filter(final Filter filter) throws NoTestsRemainException {
        if (this.getTest() instanceof Filterable) {
            ((Filterable)this.getTest()).filter(filter);
        }
        else if (this.getTest() instanceof TestSuite) {
            final TestSuite testSuite = (TestSuite)this.getTest();
            final TestSuite test = new TestSuite(testSuite.getName());
            for (int testCount = testSuite.testCount(), i = 0; i < testCount; ++i) {
                final Test test2 = testSuite.testAt(i);
                if (filter.shouldRun(makeDescription(test2))) {
                    test.addTest(test2);
                }
            }
            this.setTest((Test)test);
        }
    }
    
    public void sort(final Sorter sorter) {
        if (this.getTest() instanceof Sortable) {
            ((Sortable)this.getTest()).sort(sorter);
        }
    }
    
    private void setTest(final Test fTest) {
        this.fTest = fTest;
    }
    
    private Test getTest() {
        return this.fTest;
    }
    
    private final class OldTestClassAdaptingListener implements TestListener
    {
        private final RunNotifier fNotifier;
        final JUnit38ClassRunner this$0;
        
        private OldTestClassAdaptingListener(final JUnit38ClassRunner this$0, final RunNotifier fNotifier) {
            this.this$0 = this$0;
            this.fNotifier = fNotifier;
        }
        
        public void endTest(final Test test) {
            this.fNotifier.fireTestFinished(this.asDescription(test));
        }
        
        public void startTest(final Test test) {
            this.fNotifier.fireTestStarted(this.asDescription(test));
        }
        
        public void addError(final Test test, final Throwable t) {
            this.fNotifier.fireTestFailure(new Failure(this.asDescription(test), t));
        }
        
        private Description asDescription(final Test test) {
            if (test instanceof Describable) {
                return ((Describable)test).getDescription();
            }
            return Description.createTestDescription(this.getEffectiveClass(test), this.getName(test));
        }
        
        private Class getEffectiveClass(final Test test) {
            return test.getClass();
        }
        
        private String getName(final Test test) {
            if (test instanceof TestCase) {
                return ((TestCase)test).getName();
            }
            return test.toString();
        }
        
        public void addFailure(final Test test, final AssertionFailedError assertionFailedError) {
            this.addError(test, (Throwable)assertionFailedError);
        }
        
        OldTestClassAdaptingListener(final JUnit38ClassRunner unit38ClassRunner, final RunNotifier runNotifier, final JUnit38ClassRunner$1 object) {
            this(unit38ClassRunner, runNotifier);
        }
    }
}
