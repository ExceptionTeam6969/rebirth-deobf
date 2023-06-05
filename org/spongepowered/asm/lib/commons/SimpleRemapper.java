//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.commons;

import java.util.*;

public class SimpleRemapper extends Remapper
{
    private final Map mapping;
    
    public SimpleRemapper(final Map mapping) {
        this.mapping = mapping;
    }
    
    public SimpleRemapper(final String s, final String s2) {
        this.mapping = Collections.singletonMap(s, s2);
    }
    
    public String mapMethodName(final String s, final String s2, final String s3) {
        final String map = this.map(s + '.' + s2 + s3);
        return (map == null) ? s2 : map;
    }
    
    public String mapInvokeDynamicMethodName(final String s, final String s2) {
        final String map = this.map('.' + s + s2);
        return (map == null) ? s : map;
    }
    
    public String mapFieldName(final String s, final String s2, final String s3) {
        final String map = this.map(s + '.' + s2);
        return (map == null) ? s2 : map;
    }
    
    public String map(final String s) {
        return this.mapping.get(s);
    }
}
