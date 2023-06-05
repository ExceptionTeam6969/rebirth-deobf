//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.throwables;

public class InjectionError extends Error
{
    private static final long serialVersionUID = 1L;
    
    public InjectionError() {
    }
    
    public InjectionError(final String s) {
        super(s);
    }
    
    public InjectionError(final Throwable t) {
        super(t);
    }
    
    public InjectionError(final String s, final Throwable t) {
        super(s, t);
    }
}
