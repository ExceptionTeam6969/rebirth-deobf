//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package junit.framework;

import org.junit.runner.*;

public class JUnit4TestCaseFacade implements Test, Describable
{
    private final Description fDescription;
    
    JUnit4TestCaseFacade(final Description fDescription) {
        this.fDescription = fDescription;
    }
    
    @Override
    public String toString() {
        return this.getDescription().toString();
    }
    
    public int countTestCases() {
        return 1;
    }
    
    public void run(final TestResult testResult) {
        throw new RuntimeException("This test stub created only for informational purposes.");
    }
    
    public Description getDescription() {
        return this.fDescription;
    }
}
