//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.util.throwables;

public class InvalidConstraintException extends IllegalArgumentException
{
    private static final long serialVersionUID = 1L;
    
    public InvalidConstraintException() {
    }
    
    public InvalidConstraintException(final String s) {
        super(s);
    }
    
    public InvalidConstraintException(final Throwable t) {
        super(t);
    }
    
    public InvalidConstraintException(final String s, final Throwable t) {
        super(s, t);
    }
}
