//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal;

import org.junit.*;

public class InexactComparisonCriteria extends ComparisonCriteria
{
    public double fDelta;
    
    public InexactComparisonCriteria(final double fDelta) {
        this.fDelta = fDelta;
    }
    
    protected void assertElementsEqual(final Object o, final Object o2) {
        if (o instanceof Double) {
            Assert.assertEquals((double)o, (double)o2, this.fDelta);
        }
        else {
            Assert.assertEquals((double)(float)o, (double)(float)o2, this.fDelta);
        }
    }
}
