//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runner.notification;

import org.junit.runner.*;
import java.util.*;

public class RunNotifier
{
    private final List fListeners;
    private boolean fPleaseStop;
    
    public RunNotifier() {
        this.fListeners = Collections.synchronizedList(new ArrayList<Object>());
        this.fPleaseStop = false;
    }
    
    public void addListener(final RunListener runListener) {
        this.fListeners.add(runListener);
    }
    
    public void removeListener(final RunListener runListener) {
        this.fListeners.remove(runListener);
    }
    
    public void fireTestRunStarted(final Description description) {
        new SafeNotifier(description) {
            final Description val$description;
            final RunNotifier this$0;
            
            @Override
            protected void notifyListener(final RunListener runListener) throws Exception {
                runListener.testRunStarted(this.val$description);
            }
        }.run();
    }
    
    public void fireTestRunFinished(final Result result) {
        new SafeNotifier(result) {
            final Result val$result;
            final RunNotifier this$0;
            
            @Override
            protected void notifyListener(final RunListener runListener) throws Exception {
                runListener.testRunFinished(this.val$result);
            }
        }.run();
    }
    
    public void fireTestStarted(final Description description) throws StoppedByUserException {
        if (this.fPleaseStop) {
            throw new StoppedByUserException();
        }
        new SafeNotifier(description) {
            final Description val$description;
            final RunNotifier this$0;
            
            @Override
            protected void notifyListener(final RunListener runListener) throws Exception {
                runListener.testStarted(this.val$description);
            }
        }.run();
    }
    
    public void fireTestFailure(final Failure failure) {
        new SafeNotifier(failure) {
            final Failure val$failure;
            final RunNotifier this$0;
            
            @Override
            protected void notifyListener(final RunListener runListener) throws Exception {
                runListener.testFailure(this.val$failure);
            }
        }.run();
    }
    
    public void fireTestAssumptionFailed(final Failure failure) {
        new SafeNotifier(failure) {
            final Failure val$failure;
            final RunNotifier this$0;
            
            @Override
            protected void notifyListener(final RunListener runListener) throws Exception {
                runListener.testAssumptionFailure(this.val$failure);
            }
        }.run();
    }
    
    public void fireTestIgnored(final Description description) {
        new SafeNotifier(description) {
            final Description val$description;
            final RunNotifier this$0;
            
            @Override
            protected void notifyListener(final RunListener runListener) throws Exception {
                runListener.testIgnored(this.val$description);
            }
        }.run();
    }
    
    public void fireTestFinished(final Description description) {
        new SafeNotifier(description) {
            final Description val$description;
            final RunNotifier this$0;
            
            @Override
            protected void notifyListener(final RunListener runListener) throws Exception {
                runListener.testFinished(this.val$description);
            }
        }.run();
    }
    
    public void pleaseStop() {
        this.fPleaseStop = true;
    }
    
    public void addFirstListener(final RunListener runListener) {
        this.fListeners.add(0, runListener);
    }
    
    static List access$000(final RunNotifier runNotifier) {
        return runNotifier.fListeners;
    }
    
    private abstract class SafeNotifier
    {
        final RunNotifier this$0;
        
        private SafeNotifier(final RunNotifier this$0) {
            this.this$0 = this$0;
        }
        
        void run() {
            // monitorenter(access$000 = RunNotifier.access$000(this.this$0))
            final Iterator<RunListener> iterator = (Iterator<RunListener>)RunNotifier.access$000(this.this$0).iterator();
            while (iterator.hasNext()) {
                try {
                    this.notifyListener(iterator.next());
                }
                catch (Exception ex) {
                    iterator.remove();
                    this.this$0.fireTestFailure(new Failure(Description.TEST_MECHANISM, (Throwable)ex));
                }
            }
        }
        // monitorexit(access$000)
        
        protected abstract void notifyListener(final RunListener p0) throws Exception;
        
        SafeNotifier(final RunNotifier runNotifier, final RunNotifier$1 safeNotifier) {
            this(runNotifier);
        }
    }
}
