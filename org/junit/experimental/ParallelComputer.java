//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.experimental;

import org.junit.runner.*;
import org.junit.runners.*;
import java.util.concurrent.*;
import java.util.*;
import org.junit.runners.model.*;

public class ParallelComputer extends Computer
{
    private final boolean fClasses;
    private final boolean fMethods;
    
    public ParallelComputer(final boolean fClasses, final boolean fMethods) {
        this.fClasses = fClasses;
        this.fMethods = fMethods;
    }
    
    public static Computer classes() {
        return new ParallelComputer(true, false);
    }
    
    public static Computer methods() {
        return new ParallelComputer(false, true);
    }
    
    private static Runner parallelize(final Runner runner) {
        if (runner instanceof ParentRunner) {
            ((ParentRunner)runner).setScheduler(new RunnerScheduler() {
                private final List fResults = new ArrayList();
                private final ExecutorService fService = Executors.newCachedThreadPool();
                
                public void schedule(final Runnable runnable) {
                    this.fResults.add(this.fService.submit((Callable<Object>)new Callable(runnable) {
                        final Runnable val$childStatement;
                        final ParallelComputer$1 this$0;
                        
                        public Object call() throws Exception {
                            this.val$childStatement.run();
                            return null;
                        }
                    }));
                }
                
                public void finished() {
                    for (final Future future : this.fResults) {
                        try {
                            future.get();
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
        }
        return runner;
    }
    
    @Override
    public Runner getSuite(final RunnerBuilder runnerBuilder, final Class[] array) throws InitializationError {
        final Runner suite = super.getSuite(runnerBuilder, array);
        return this.fClasses ? parallelize(suite) : suite;
    }
    
    @Override
    protected Runner getRunner(final RunnerBuilder runnerBuilder, final Class clazz) throws Throwable {
        final Runner runner = super.getRunner(runnerBuilder, clazz);
        return this.fMethods ? parallelize(runner) : runner;
    }
}
