//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package junit.framework;

import org.junit.runner.*;
import org.junit.runner.notification.*;
import java.util.*;

public class JUnit4TestAdapterCache extends HashMap
{
    private static final long serialVersionUID = 1L;
    private static final JUnit4TestAdapterCache fInstance;
    
    public static JUnit4TestAdapterCache getDefault() {
        return JUnit4TestAdapterCache.fInstance;
    }
    
    public Test asTest(final Description description) {
        if (description.isSuite()) {
            return this.createTest(description);
        }
        if (!this.containsKey(description)) {
            this.put(description, this.createTest(description));
        }
        return this.get(description);
    }
    
    Test createTest(final Description description) {
        if (description.isTest()) {
            return new JUnit4TestCaseFacade(description);
        }
        final TestSuite testSuite = new TestSuite(description.getDisplayName());
        final Iterator<Description> iterator = description.getChildren().iterator();
        while (iterator.hasNext()) {
            testSuite.addTest(this.asTest(iterator.next()));
        }
        return testSuite;
    }
    
    public RunNotifier getNotifier(final TestResult testResult, final JUnit4TestAdapter unit4TestAdapter) {
        final RunNotifier runNotifier = new RunNotifier();
        runNotifier.addListener((RunListener)new RunListener(testResult) {
            final TestResult val$result;
            final JUnit4TestAdapterCache this$0;
            
            public void testFailure(final Failure failure) throws Exception {
                this.val$result.addError(this.this$0.asTest(failure.getDescription()), failure.getException());
            }
            
            public void testFinished(final Description description) throws Exception {
                this.val$result.endTest(this.this$0.asTest(description));
            }
            
            public void testStarted(final Description description) throws Exception {
                this.val$result.startTest(this.this$0.asTest(description));
            }
        });
        return runNotifier;
    }
    
    public List asTestList(final Description description) {
        if (description.isTest()) {
            return Arrays.asList(this.asTest(description));
        }
        final ArrayList<Test> list = new ArrayList<Test>();
        final Iterator<Description> iterator = description.getChildren().iterator();
        while (iterator.hasNext()) {
            list.add(this.asTest(iterator.next()));
        }
        return list;
    }
    
    static {
        fInstance = new JUnit4TestAdapterCache();
    }
}
