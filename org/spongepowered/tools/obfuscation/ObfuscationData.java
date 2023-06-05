//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import java.util.*;

public class ObfuscationData implements Iterable
{
    private final Map data;
    private final Object defaultValue;
    
    public ObfuscationData() {
        this(null);
    }
    
    public ObfuscationData(final Object defaultValue) {
        this.data = new HashMap();
        this.defaultValue = defaultValue;
    }
    
    @Deprecated
    public void add(final ObfuscationType obfuscationType, final Object o) {
        this.put(obfuscationType, o);
    }
    
    public void put(final ObfuscationType obfuscationType, final Object o) {
        this.data.put(obfuscationType, o);
    }
    
    public boolean isEmpty() {
        return this.data.isEmpty();
    }
    
    public Object get(final ObfuscationType obfuscationType) {
        final Object value = this.data.get(obfuscationType);
        return (value != null) ? value : this.defaultValue;
    }
    
    @Override
    public Iterator iterator() {
        return this.data.keySet().iterator();
    }
    
    @Override
    public String toString() {
        return String.format("ObfuscationData[%s,DEFAULT=%s]", this.listValues(), this.defaultValue);
    }
    
    public String values() {
        return "[" + this.listValues() + "]";
    }
    
    private String listValues() {
        final StringBuilder sb = new StringBuilder();
        int n = 0;
        for (final ObfuscationType obfuscationType : this.data.keySet()) {
            if (n != 0) {
                sb.append(',');
            }
            sb.append(obfuscationType.getKey()).append('=').append(this.data.get(obfuscationType));
            n = 1;
        }
        return sb.toString();
    }
}
