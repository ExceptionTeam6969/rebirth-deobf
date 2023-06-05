//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.mapping.*;
import java.util.*;
import org.spongepowered.asm.obfuscation.mapping.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;

class Mappings implements IMappingConsumer
{
    private final Map fieldMappings;
    private final Map methodMappings;
    private UniqueMappings unique;
    
    public Mappings() {
        this.fieldMappings = new HashMap();
        this.methodMappings = new HashMap();
        this.init();
    }
    
    private void init() {
        for (final ObfuscationType obfuscationType : ObfuscationType.types()) {
            this.fieldMappings.put(obfuscationType, new IMappingConsumer.MappingSet());
            this.methodMappings.put(obfuscationType, new IMappingConsumer.MappingSet());
        }
    }
    
    public IMappingConsumer asUnique() {
        if (this.unique == null) {
            this.unique = new UniqueMappings((IMappingConsumer)this);
        }
        return (IMappingConsumer)this.unique;
    }
    
    public IMappingConsumer.MappingSet getFieldMappings(final ObfuscationType obfuscationType) {
        final IMappingConsumer.MappingSet set = this.fieldMappings.get(obfuscationType);
        return (set != null) ? set : new IMappingConsumer.MappingSet();
    }
    
    public IMappingConsumer.MappingSet getMethodMappings(final ObfuscationType obfuscationType) {
        final IMappingConsumer.MappingSet set = this.methodMappings.get(obfuscationType);
        return (set != null) ? set : new IMappingConsumer.MappingSet();
    }
    
    public void clear() {
        this.fieldMappings.clear();
        this.methodMappings.clear();
        if (this.unique != null) {
            this.unique.clearMaps();
        }
        this.init();
    }
    
    public void addFieldMapping(final ObfuscationType obfuscationType, final MappingField mappingField, final MappingField mappingField2) {
        IMappingConsumer.MappingSet set = this.fieldMappings.get(obfuscationType);
        if (set == null) {
            set = new IMappingConsumer.MappingSet();
            this.fieldMappings.put(obfuscationType, set);
        }
        set.add((Object)new IMappingConsumer.MappingSet.Pair((IMapping)mappingField, (IMapping)mappingField2));
    }
    
    public void addMethodMapping(final ObfuscationType obfuscationType, final MappingMethod mappingMethod, final MappingMethod mappingMethod2) {
        IMappingConsumer.MappingSet set = this.methodMappings.get(obfuscationType);
        if (set == null) {
            set = new IMappingConsumer.MappingSet();
            this.methodMappings.put(obfuscationType, set);
        }
        set.add((Object)new IMappingConsumer.MappingSet.Pair((IMapping)mappingMethod, (IMapping)mappingMethod2));
    }
    
    static class UniqueMappings implements IMappingConsumer
    {
        private final IMappingConsumer mappings;
        private final Map fields;
        private final Map methods;
        
        public UniqueMappings(final IMappingConsumer mappings) {
            this.fields = new HashMap();
            this.methods = new HashMap();
            this.mappings = mappings;
        }
        
        public void clear() {
            this.clearMaps();
            this.mappings.clear();
        }
        
        protected void clearMaps() {
            this.fields.clear();
            this.methods.clear();
        }
        
        public void addFieldMapping(final ObfuscationType obfuscationType, final MappingField mappingField, final MappingField mappingField2) {
            if (this.fields == null) {
                this.mappings.addFieldMapping(obfuscationType, mappingField, mappingField2);
            }
        }
        
        public void addMethodMapping(final ObfuscationType obfuscationType, final MappingMethod mappingMethod, final MappingMethod mappingMethod2) {
            if (this.methods == null) {
                this.mappings.addMethodMapping(obfuscationType, mappingMethod, mappingMethod2);
            }
        }
        
        public IMappingConsumer.MappingSet getFieldMappings(final ObfuscationType obfuscationType) {
            return this.mappings.getFieldMappings(obfuscationType);
        }
        
        public IMappingConsumer.MappingSet getMethodMappings(final ObfuscationType obfuscationType) {
            return this.mappings.getMethodMappings(obfuscationType);
        }
    }
    
    public static class MappingConflictException extends RuntimeException
    {
        private static final long serialVersionUID = 1L;
        private final IMapping oldMapping;
        private final IMapping newMapping;
        
        public MappingConflictException(final IMapping oldMapping, final IMapping newMapping) {
            this.oldMapping = oldMapping;
            this.newMapping = newMapping;
        }
        
        public IMapping getOld() {
            return this.oldMapping;
        }
        
        public IMapping getNew() {
            return this.newMapping;
        }
    }
}
