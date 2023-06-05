//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal;

import java.util.*;

public class ArrayComparisonFailure extends AssertionError
{
    private static final long serialVersionUID = 1L;
    private List fIndices;
    private final String fMessage;
    private final AssertionError fCause;
    
    public ArrayComparisonFailure(final String fMessage, final AssertionError fCause, final int n) {
        this.fIndices = new ArrayList();
        this.fMessage = fMessage;
        this.fCause = fCause;
        this.addDimension(n);
    }
    
    public void addDimension(final int n) {
        this.fIndices.add(0, n);
    }
    
    @Override
    public String getMessage() {
        final StringBuilder sb = new StringBuilder();
        if (this.fMessage != null) {
            sb.append(this.fMessage);
        }
        sb.append("arrays first differed at element ");
        for (final int intValue : this.fIndices) {
            sb.append("[");
            sb.append(intValue);
            sb.append("]");
        }
        sb.append("; ");
        sb.append(this.fCause.getMessage());
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return this.getMessage();
    }
}
