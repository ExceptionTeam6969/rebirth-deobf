//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn��ǿ��������\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.modify;

import org.spongepowered.asm.mixin.throwables.*;

public class InvalidImplicitDiscriminatorException extends MixinException
{
    private static final long serialVersionUID = 1L;
    
    public InvalidImplicitDiscriminatorException(final String s) {
        super(s);
    }
    
    public InvalidImplicitDiscriminatorException(final String s, final Throwable t) {
        super(s, t);
    }
}
