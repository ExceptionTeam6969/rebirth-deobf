//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package junit.extensions;

import junit.framework.*;

public class TestSetup extends TestDecorator
{
    public TestSetup(final Test test) {
        super(test);
    }
    
    public void run(final TestResult testResult) {
        testResult.runProtected((Test)this, new Protectable(testResult) {
            final TestResult val$result;
            final TestSetup this$0;
            
            public void protect() throws Exception {
                this.this$0.setUp();
                this.this$0.basicRun(this.val$result);
                this.this$0.tearDown();
            }
        });
    }
    
    protected void setUp() throws Exception {
    }
    
    protected void tearDown() throws Exception {
    }
}
