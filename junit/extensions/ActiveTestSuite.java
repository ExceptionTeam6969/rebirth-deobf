//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package junit.extensions;

import junit.framework.*;

public class ActiveTestSuite extends TestSuite
{
    private volatile int fActiveTestDeathCount;
    
    public ActiveTestSuite() {
    }
    
    public ActiveTestSuite(final Class clazz) {
        super(clazz);
    }
    
    public ActiveTestSuite(final String s) {
        super(s);
    }
    
    public ActiveTestSuite(final Class clazz, final String s) {
        super(clazz, s);
    }
    
    @Override
    public void run(final TestResult testResult) {
        this.fActiveTestDeathCount = 0;
        super.run(testResult);
        this.waitUntilFinished();
    }
    
    @Override
    public void runTest(final Test test, final TestResult testResult) {
        new Thread(test, testResult) {
            final Test val$test;
            final TestResult val$result;
            final ActiveTestSuite this$0;
            
            @Override
            public void run() {
                this.val$test.run(this.val$result);
                this.this$0.runFinished();
            }
        }.start();
    }
    
    synchronized void waitUntilFinished() {
        while (this.fActiveTestDeathCount < this.testCount()) {
            try {
                this.wait();
                continue;
            }
            catch (InterruptedException ex) {
                return;
            }
            break;
        }
    }
    
    public synchronized void runFinished() {
        ++this.fActiveTestDeathCount;
        this.notifyAll();
    }
}
