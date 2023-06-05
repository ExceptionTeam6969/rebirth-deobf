//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.mirror;

import org.spongepowered.asm.obfuscation.mapping.*;

public abstract class MemberHandle
{
    private final String owner;
    private final String name;
    private final String desc;
    
    protected MemberHandle(final String owner, final String name, final String desc) {
        this.owner = owner;
        this.name = name;
        this.desc = desc;
    }
    
    public final String getOwner() {
        return this.owner;
    }
    
    public final String getName() {
        return this.name;
    }
    
    public final String getDesc() {
        return this.desc;
    }
    
    public abstract Visibility getVisibility();
    
    public abstract IMapping asMapping(final boolean p0);
}
