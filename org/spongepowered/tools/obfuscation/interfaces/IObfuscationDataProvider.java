//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.interfaces;

import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.tools.obfuscation.*;
import org.spongepowered.asm.obfuscation.mapping.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;
import org.spongepowered.tools.obfuscation.mirror.*;

public interface IObfuscationDataProvider
{
    ObfuscationData getObfEntryRecursive(final MemberInfo p0);
    
    ObfuscationData getObfEntry(final MemberInfo p0);
    
    ObfuscationData getObfEntry(final IMapping p0);
    
    ObfuscationData getObfMethodRecursive(final MemberInfo p0);
    
    ObfuscationData getObfMethod(final MemberInfo p0);
    
    ObfuscationData getRemappedMethod(final MemberInfo p0);
    
    ObfuscationData getObfMethod(final MappingMethod p0);
    
    ObfuscationData getRemappedMethod(final MappingMethod p0);
    
    ObfuscationData getObfFieldRecursive(final MemberInfo p0);
    
    ObfuscationData getObfField(final MemberInfo p0);
    
    ObfuscationData getObfField(final MappingField p0);
    
    ObfuscationData getObfClass(final TypeHandle p0);
    
    ObfuscationData getObfClass(final String p0);
}
