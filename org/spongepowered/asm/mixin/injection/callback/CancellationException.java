//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.callback;

public class CancellationException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    
    public CancellationException() {
    }
    
    public CancellationException(final String s) {
        super(s);
    }
    
    public CancellationException(final Throwable t) {
        super(t);
    }
    
    public CancellationException(final String s, final Throwable t) {
        super(s, t);
    }
}
