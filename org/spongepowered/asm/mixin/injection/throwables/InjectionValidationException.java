//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn��ǿ��������\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.throwables;

import org.spongepowered.asm.mixin.injection.struct.*;

public class InjectionValidationException extends Exception
{
    private static final long serialVersionUID = 1L;
    private final InjectorGroupInfo group;
    
    public InjectionValidationException(final InjectorGroupInfo group, final String s) {
        super(s);
        this.group = group;
    }
    
    public InjectorGroupInfo getGroup() {
        return this.group;
    }
}
