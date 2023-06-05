//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners;

import org.junit.runner.*;
import java.util.concurrent.*;
import org.junit.internal.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.runner.notification.*;

@Deprecated
public class MethodRoadie
{
    private final Object fTest;
    private final RunNotifier fNotifier;
    private final Description fDescription;
    private TestMethod fTestMethod;
    
    public MethodRoadie(final Object fTest, final TestMethod fTestMethod, final RunNotifier fNotifier, final Description fDescription) {
        this.fTest = fTest;
        this.fNotifier = fNotifier;
        this.fDescription = fDescription;
        this.fTestMethod = fTestMethod;
    }
    
    public void run() {
        if (this.fTestMethod.isIgnored()) {
            this.fNotifier.fireTestIgnored(this.fDescription);
            return;
        }
        this.fNotifier.fireTestStarted(this.fDescription);
        final long timeout = this.fTestMethod.getTimeout();
        if (timeout > 0L) {
            this.runWithTimeout(timeout);
        }
        else {
            this.runTest();
        }
        this.fNotifier.fireTestFinished(this.fDescription);
    }
    
    private void runWithTimeout(final long n) {
        this.runBeforesThenTestThenAfters(new Runnable(n) {
            final long val$timeout;
            final MethodRoadie this$0;
            
            public void run() {
                final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
                final Future<Object> submit = singleThreadExecutor.submit((Callable<Object>)new Callable() {
                    final MethodRoadie$1 this$1;
                    
                    public Object call() throws Exception {
                        this.this$1.this$0.runTestMethod();
                        return null;
                    }
                });
                singleThreadExecutor.shutdown();
                try {
                    if (!singleThreadExecutor.awaitTermination(this.val$timeout, TimeUnit.MILLISECONDS)) {
                        singleThreadExecutor.shutdownNow();
                    }
                    submit.get(0L, TimeUnit.MILLISECONDS);
                }
                catch (TimeoutException ex2) {
                    this.this$0.addFailure(new Exception(String.format("test timed out after %d milliseconds", this.val$timeout)));
                }
                catch (Exception ex) {
                    this.this$0.addFailure(ex);
                }
            }
        });
    }
    
    public void runTest() {
        this.runBeforesThenTestThenAfters(new Runnable() {
            final MethodRoadie this$0;
            
            public void run() {
                this.this$0.runTestMethod();
            }
        });
    }
    
    public void runBeforesThenTestThenAfters(final Runnable runnable) {
        try {
            this.runBefores();
            runnable.run();
            this.runAfters();
        }
        catch (FailedBefore failedBefore) {
            this.runAfters();
        }
        catch (Exception ex) {
            throw new RuntimeException("test should never throw an exception to this level");
        }
    }
    
    protected void runTestMethod() {
        try {
            this.fTestMethod.invoke(this.fTest);
            if (this.fTestMethod.expectsException()) {
                this.addFailure(new AssertionError((Object)("Expected exception: " + this.fTestMethod.getExpectedException().getName())));
            }
        }
        catch (InvocationTargetException ex) {
            final Throwable targetException = ex.getTargetException();
            if (targetException instanceof AssumptionViolatedException) {
                return;
            }
            if (!this.fTestMethod.expectsException()) {
                this.addFailure(targetException);
            }
            else if (this.fTestMethod.isUnexpected(targetException)) {
                this.addFailure(new Exception("Unexpected exception, expected<" + this.fTestMethod.getExpectedException().getName() + "> but was<" + targetException.getClass().getName() + ">", targetException));
            }
        }
        catch (Throwable t) {
            this.addFailure(t);
        }
    }
    
    private void runBefores() throws FailedBefore {
        try {
            try {
                final Iterator<Method> iterator = this.fTestMethod.getBefores().iterator();
                while (iterator.hasNext()) {
                    iterator.next().invoke(this.fTest, new Object[0]);
                }
            }
            catch (InvocationTargetException ex) {
                throw ex.getTargetException();
            }
        }
        catch (AssumptionViolatedException ex2) {
            throw new FailedBefore();
        }
        catch (Throwable t) {
            this.addFailure(t);
            throw new FailedBefore();
        }
    }
    
    private void runAfters() {
        for (final Method method : this.fTestMethod.getAfters()) {
            try {
                method.invoke(this.fTest, new Object[0]);
            }
            catch (InvocationTargetException ex) {
                this.addFailure(ex.getTargetException());
            }
            catch (Throwable t) {
                this.addFailure(t);
            }
        }
    }
    
    protected void addFailure(final Throwable t) {
        this.fNotifier.fireTestFailure(new Failure(this.fDescription, t));
    }
}
