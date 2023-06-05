//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runner.manipulation;

import java.util.*;
import org.junit.runner.*;

public class Sorter implements Comparator
{
    public static Sorter NULL;
    private final Comparator fComparator;
    
    public Sorter(final Comparator fComparator) {
        this.fComparator = fComparator;
    }
    
    public void apply(final Object o) {
        if (o instanceof Sortable) {
            ((Sortable)o).sort(this);
        }
    }
    
    public int compare(final Description description, final Description description2) {
        return this.fComparator.compare(description, description2);
    }
    
    public int compare(final Object o, final Object o2) {
        return this.compare((Description)o, (Description)o2);
    }
    
    static {
        Sorter.NULL = new Sorter(new Comparator() {
            public int compare(final Description description, final Description description2) {
                return 0;
            }
            
            public int compare(final Object o, final Object o2) {
                return this.compare((Description)o, (Description)o2);
            }
        });
    }
}
