//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runner;

import java.io.*;
import java.util.concurrent.atomic.*;
import java.util.*;
import org.junit.runner.notification.*;

public class Result implements Serializable
{
    private static final long serialVersionUID = 1L;
    private AtomicInteger fCount;
    private AtomicInteger fIgnoreCount;
    private final List fFailures;
    private long fRunTime;
    private long fStartTime;
    
    public Result() {
        this.fCount = new AtomicInteger();
        this.fIgnoreCount = new AtomicInteger();
        this.fFailures = Collections.synchronizedList(new ArrayList<Object>());
        this.fRunTime = 0L;
    }
    
    public int getRunCount() {
        return this.fCount.get();
    }
    
    public int getFailureCount() {
        return this.fFailures.size();
    }
    
    public long getRunTime() {
        return this.fRunTime;
    }
    
    public List getFailures() {
        return this.fFailures;
    }
    
    public int getIgnoreCount() {
        return this.fIgnoreCount.get();
    }
    
    public boolean wasSuccessful() {
        return this.getFailureCount() == 0;
    }
    
    public RunListener createListener() {
        return new Listener(null);
    }
    
    static long access$002(final Result result, final long fStartTime) {
        return result.fStartTime = fStartTime;
    }
    
    static long access$114(final Result result, final long n) {
        return result.fRunTime += n;
    }
    
    static long access$000(final Result result) {
        return result.fStartTime;
    }
    
    static AtomicInteger access$200(final Result result) {
        return result.fCount;
    }
    
    static List access$300(final Result result) {
        return result.fFailures;
    }
    
    static AtomicInteger access$400(final Result result) {
        return result.fIgnoreCount;
    }
    
    private class Listener extends RunListener
    {
        final Result this$0;
        
        private Listener(final Result this$0) {
            this.this$0 = this$0;
        }
        
        public void testRunStarted(final Description description) throws Exception {
            Result.access$002(this.this$0, System.currentTimeMillis());
        }
        
        public void testRunFinished(final Result result) throws Exception {
            Result.access$114(this.this$0, System.currentTimeMillis() - Result.access$000(this.this$0));
        }
        
        public void testFinished(final Description description) throws Exception {
            Result.access$200(this.this$0).getAndIncrement();
        }
        
        public void testFailure(final Failure failure) throws Exception {
            Result.access$300(this.this$0).add(failure);
        }
        
        public void testIgnored(final Description description) throws Exception {
            Result.access$400(this.this$0).getAndIncrement();
        }
        
        public void testAssumptionFailure(final Failure failure) {
        }
        
        Listener(final Result result, final Result$1 object) {
            this(result);
        }
    }
}
