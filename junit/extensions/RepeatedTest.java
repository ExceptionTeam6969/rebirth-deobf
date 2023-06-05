//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package junit.extensions;

import junit.framework.*;

public class RepeatedTest extends TestDecorator
{
    private int fTimesRepeat;
    
    public RepeatedTest(final Test test, final int fTimesRepeat) {
        super(test);
        if (fTimesRepeat < 0) {
            throw new IllegalArgumentException("Repetition count must be >= 0");
        }
        this.fTimesRepeat = fTimesRepeat;
    }
    
    @Override
    public int countTestCases() {
        return super.countTestCases() * this.fTimesRepeat;
    }
    
    @Override
    public void run(final TestResult testResult) {
        for (int n = 0; n < this.fTimesRepeat && !testResult.shouldStop(); ++n) {
            super.run(testResult);
        }
    }
    
    @Override
    public String toString() {
        return super.toString() + "(repeated)";
    }
}
