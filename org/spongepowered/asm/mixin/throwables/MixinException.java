//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.throwables;

public class MixinException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    
    public MixinException() {
    }
    
    public MixinException(final String s) {
        super(s);
    }
    
    public MixinException(final Throwable t) {
        super(t);
    }
    
    public MixinException(final String s, final Throwable t) {
        super(s, t);
    }
}
