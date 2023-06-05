//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package junit.framework;

public class ComparisonFailure extends AssertionFailedError
{
    private static final int MAX_CONTEXT_LENGTH = 20;
    private static final long serialVersionUID = 1L;
    private String fExpected;
    private String fActual;
    
    public ComparisonFailure(final String s, final String fExpected, final String fActual) {
        super(s);
        this.fExpected = fExpected;
        this.fActual = fActual;
    }
    
    public String getMessage() {
        return new ComparisonCompactor(20, this.fExpected, this.fActual).compact(super.getMessage());
    }
    
    public String getActual() {
        return this.fActual;
    }
    
    public String getExpected() {
        return this.fExpected;
    }
}
