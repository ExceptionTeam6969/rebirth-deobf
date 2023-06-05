//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners;

import java.util.*;

@Deprecated
public class InitializationError extends Exception
{
    private static final long serialVersionUID = 1L;
    private final List fErrors;
    
    public InitializationError(final List fErrors) {
        this.fErrors = fErrors;
    }
    
    public InitializationError(final Throwable... array) {
        this(Arrays.asList(array));
    }
    
    public InitializationError(final String s) {
        this(new Throwable[] { new Exception(s) });
    }
    
    public List getCauses() {
        return this.fErrors;
    }
}
