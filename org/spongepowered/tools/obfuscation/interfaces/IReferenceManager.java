//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.interfaces;

import org.spongepowered.asm.mixin.refmap.*;
import org.spongepowered.tools.obfuscation.*;
import org.spongepowered.asm.mixin.injection.struct.*;

public interface IReferenceManager
{
    void setAllowConflicts(final boolean p0);
    
    boolean getAllowConflicts();
    
    void write();
    
    ReferenceMapper getMapper();
    
    void addMethodMapping(final String p0, final String p1, final ObfuscationData p2);
    
    void addMethodMapping(final String p0, final String p1, final MemberInfo p2, final ObfuscationData p3);
    
    void addFieldMapping(final String p0, final String p1, final MemberInfo p2, final ObfuscationData p3);
    
    void addClassMapping(final String p0, final String p1, final ObfuscationData p2);
}
