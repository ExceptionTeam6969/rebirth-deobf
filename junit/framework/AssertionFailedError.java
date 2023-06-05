//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package junit.framework;

public class AssertionFailedError extends AssertionError
{
    private static final long serialVersionUID = 1L;
    
    public AssertionFailedError() {
    }
    
    public AssertionFailedError(final String s) {
        super((Object)defaultString(s));
    }
    
    private static String defaultString(final String s) {
        return (s == null) ? "" : s;
    }
}
