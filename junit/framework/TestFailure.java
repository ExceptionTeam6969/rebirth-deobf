//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package junit.framework;

import java.io.*;

public class TestFailure
{
    protected Test fFailedTest;
    protected Throwable fThrownException;
    
    public TestFailure(final Test fFailedTest, final Throwable fThrownException) {
        this.fFailedTest = fFailedTest;
        this.fThrownException = fThrownException;
    }
    
    public Test failedTest() {
        return this.fFailedTest;
    }
    
    public Throwable thrownException() {
        return this.fThrownException;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.fFailedTest + ": " + this.fThrownException.getMessage());
        return sb.toString();
    }
    
    public String trace() {
        final StringWriter stringWriter = new StringWriter();
        this.thrownException().printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.getBuffer().toString();
    }
    
    public String exceptionMessage() {
        return this.thrownException().getMessage();
    }
    
    public boolean isFailure() {
        return this.thrownException() instanceof AssertionFailedError;
    }
}
