//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.hamcrest.internal;

import java.util.*;
import org.hamcrest.*;

public class SelfDescribingValueIterator implements Iterator
{
    private Iterator values;
    
    public SelfDescribingValueIterator(final Iterator values) {
        this.values = values;
    }
    
    public boolean hasNext() {
        return this.values.hasNext();
    }
    
    public SelfDescribing next() {
        return new SelfDescribingValue(this.values.next());
    }
    
    public void remove() {
        this.values.remove();
    }
    
    public Object next() {
        return this.next();
    }
}
