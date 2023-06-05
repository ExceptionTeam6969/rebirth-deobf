//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package junit.framework;

import java.util.*;

public class TestResult
{
    protected List fFailures;
    protected List fErrors;
    protected List fListeners;
    protected int fRunTests;
    private boolean fStop;
    
    public TestResult() {
        this.fFailures = new ArrayList();
        this.fErrors = new ArrayList();
        this.fListeners = new ArrayList();
        this.fRunTests = 0;
        this.fStop = false;
    }
    
    public synchronized void addError(final Test test, final Throwable t) {
        this.fErrors.add(new TestFailure(test, t));
        final Iterator<TestListener> iterator = this.cloneListeners().iterator();
        while (iterator.hasNext()) {
            iterator.next().addError(test, t);
        }
    }
    
    public synchronized void addFailure(final Test test, final AssertionFailedError assertionFailedError) {
        this.fFailures.add(new TestFailure(test, (Throwable)assertionFailedError));
        final Iterator<TestListener> iterator = this.cloneListeners().iterator();
        while (iterator.hasNext()) {
            iterator.next().addFailure(test, assertionFailedError);
        }
    }
    
    public synchronized void addListener(final TestListener testListener) {
        this.fListeners.add(testListener);
    }
    
    public synchronized void removeListener(final TestListener testListener) {
        this.fListeners.remove(testListener);
    }
    
    private synchronized List cloneListeners() {
        final ArrayList list = new ArrayList();
        list.addAll(this.fListeners);
        return list;
    }
    
    public void endTest(final Test test) {
        final Iterator<TestListener> iterator = this.cloneListeners().iterator();
        while (iterator.hasNext()) {
            iterator.next().endTest(test);
        }
    }
    
    public synchronized int errorCount() {
        return this.fErrors.size();
    }
    
    public synchronized Enumeration errors() {
        return Collections.enumeration((Collection<Object>)this.fErrors);
    }
    
    public synchronized int failureCount() {
        return this.fFailures.size();
    }
    
    public synchronized Enumeration failures() {
        return Collections.enumeration((Collection<Object>)this.fFailures);
    }
    
    protected void run(final TestCase testCase) {
        this.startTest((Test)testCase);
        this.runProtected((Test)testCase, (Protectable)new Protectable(testCase) {
            final TestCase val$test;
            final TestResult this$0;
            
            public void protect() throws Throwable {
                this.val$test.runBare();
            }
        });
        this.endTest((Test)testCase);
    }
    
    public synchronized int runCount() {
        return this.fRunTests;
    }
    
    public void runProtected(final Test test, final Protectable protectable) {
        try {
            protectable.protect();
        }
        catch (AssertionFailedError assertionFailedError) {
            this.addFailure(test, assertionFailedError);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            this.addError(test, t);
        }
    }
    
    public synchronized boolean shouldStop() {
        return this.fStop;
    }
    
    public void startTest(final Test test) {
        final int countTestCases = test.countTestCases();
        // monitorenter(this)
        this.fRunTests += countTestCases;
        // monitorexit(this)
        final Iterator<TestListener> iterator = this.cloneListeners().iterator();
        while (iterator.hasNext()) {
            iterator.next().startTest(test);
        }
    }
    
    public synchronized void stop() {
        this.fStop = true;
    }
    
    public synchronized boolean wasSuccessful() {
        return this.failureCount() == 0 && this.errorCount() == 0;
    }
}
