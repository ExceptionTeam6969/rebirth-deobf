//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package junit.extensions;

import junit.framework.*;

public class TestDecorator extends Assert implements Test
{
    protected Test fTest;
    
    public TestDecorator(final Test fTest) {
        this.fTest = fTest;
    }
    
    public void basicRun(final TestResult testResult) {
        this.fTest.run(testResult);
    }
    
    public int countTestCases() {
        return this.fTest.countTestCases();
    }
    
    public void run(final TestResult testResult) {
        this.basicRun(testResult);
    }
    
    @Override
    public String toString() {
        return this.fTest.toString();
    }
    
    public Test getTest() {
        return this.fTest;
    }
}
