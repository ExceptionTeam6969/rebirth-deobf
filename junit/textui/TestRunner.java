//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package junit.textui;

import java.io.*;
import junit.runner.*;
import junit.framework.*;

public class TestRunner extends BaseTestRunner
{
    private ResultPrinter fPrinter;
    public static final int SUCCESS_EXIT = 0;
    public static final int FAILURE_EXIT = 1;
    public static final int EXCEPTION_EXIT = 2;
    
    public TestRunner() {
        this(System.out);
    }
    
    public TestRunner(final PrintStream printStream) {
        this(new ResultPrinter(printStream));
    }
    
    public TestRunner(final ResultPrinter fPrinter) {
        this.fPrinter = fPrinter;
    }
    
    public static void run(final Class clazz) {
        run((Test)new TestSuite(clazz));
    }
    
    public static TestResult run(final Test test) {
        return new TestRunner().doRun(test);
    }
    
    public static void runAndWait(final Test test) {
        new TestRunner().doRun(test, true);
    }
    
    public void testFailed(final int n, final Test test, final Throwable t) {
    }
    
    public void testStarted(final String s) {
    }
    
    public void testEnded(final String s) {
    }
    
    protected TestResult createTestResult() {
        return new TestResult();
    }
    
    public TestResult doRun(final Test test) {
        return this.doRun(test, false);
    }
    
    public TestResult doRun(final Test test, final boolean b) {
        final TestResult testResult = this.createTestResult();
        testResult.addListener((TestListener)this.fPrinter);
        final long currentTimeMillis = System.currentTimeMillis();
        test.run(testResult);
        this.fPrinter.print(testResult, System.currentTimeMillis() - currentTimeMillis);
        this.pause(b);
        return testResult;
    }
    
    protected void pause(final boolean b) {
        if (!b) {
            return;
        }
        this.fPrinter.printWaitPrompt();
        try {
            System.in.read();
        }
        catch (Exception ex) {}
    }
    
    public static void main(final String[] array) {
        final TestRunner testRunner = new TestRunner();
        try {
            if (!testRunner.start(array).wasSuccessful()) {
                System.exit(1);
            }
            System.exit(0);
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            System.exit(2);
        }
    }
    
    public TestResult start(final String[] array) throws Exception {
        String s = "";
        String substring = "";
        boolean b = false;
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equals("-wait")) {
                b = true;
            }
            else if (array[i].equals("-c")) {
                s = this.extractClassName(array[++i]);
            }
            else if (array[i].equals("-m")) {
                final String s2 = array[++i];
                final int lastIndex = s2.lastIndexOf(46);
                s = s2.substring(0, lastIndex);
                substring = s2.substring(lastIndex + 1);
            }
            else if (array[i].equals("-v")) {
                System.err.println("JUnit " + Version.id() + " by Kent Beck and Erich Gamma");
            }
            else {
                s = array[i];
            }
        }
        if (s.equals("")) {
            throw new Exception("Usage: TestRunner [-wait] testCaseName, where name is the name of the TestCase class");
        }
        try {
            if (!substring.equals("")) {
                return this.runSingleMethod(s, substring, b);
            }
            return this.doRun(this.getTest(s), b);
        }
        catch (Exception ex) {
            throw new Exception("Could not create and run test suite: " + ex);
        }
    }
    
    protected TestResult runSingleMethod(final String s, final String s2, final boolean b) throws Exception {
        return this.doRun(TestSuite.createTest((Class)this.loadSuiteClass(s).asSubclass(TestCase.class), s2), b);
    }
    
    protected void runFailed(final String s) {
        System.err.println(s);
        System.exit(1);
    }
    
    public void setPrinter(final ResultPrinter fPrinter) {
        this.fPrinter = fPrinter;
    }
}
