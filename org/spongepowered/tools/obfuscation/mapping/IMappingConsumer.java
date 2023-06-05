//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.mapping;

import org.spongepowered.tools.obfuscation.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;
import java.util.*;
import org.spongepowered.asm.obfuscation.mapping.*;
import com.google.common.base.*;

public interface IMappingConsumer
{
    void clear();
    
    void addFieldMapping(final ObfuscationType p0, final MappingField p1, final MappingField p2);
    
    void addMethodMapping(final ObfuscationType p0, final MappingMethod p1, final MappingMethod p2);
    
    MappingSet getFieldMappings(final ObfuscationType p0);
    
    MappingSet getMethodMappings(final ObfuscationType p0);
    
    public static class MappingSet extends LinkedHashSet
    {
        private static final long serialVersionUID = 1L;
        
        public static class Pair
        {
            public final IMapping from;
            public final IMapping to;
            
            public Pair(final IMapping from, final IMapping to) {
                this.from = from;
                this.to = to;
            }
            
            @Override
            public boolean equals(final Object o) {
                if (!(o instanceof Pair)) {
                    return false;
                }
                final Pair pair = (Pair)o;
                return Objects.equal((Object)this.from, (Object)pair.from) && Objects.equal((Object)this.to, (Object)pair.to);
            }
            
            @Override
            public int hashCode() {
                return Objects.hashCode(new Object[] { this.from, this.to });
            }
            
            @Override
            public String toString() {
                return String.format("%s -> %s", this.from, this.to);
            }
        }
    }
}
