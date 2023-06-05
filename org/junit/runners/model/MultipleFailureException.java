//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runners.model;

import java.util.*;

public class MultipleFailureException extends Exception
{
    private static final long serialVersionUID = 1L;
    private final List fErrors;
    
    public MultipleFailureException(final List list) {
        this.fErrors = new ArrayList(list);
    }
    
    public List getFailures() {
        return Collections.unmodifiableList((List<?>)this.fErrors);
    }
    
    @Override
    public String getMessage() {
        final StringBuilder sb = new StringBuilder(String.format("There were %d errors:", this.fErrors.size()));
        for (final Throwable t : this.fErrors) {
            sb.append(String.format("\n  %s(%s)", t.getClass().getName(), t.getMessage()));
        }
        return sb.toString();
    }
    
    public static void assertEmpty(final List list) throws Throwable {
        if (list.isEmpty()) {
            return;
        }
        if (list.size() == 1) {
            throw (Throwable)list.get(0);
        }
        throw new org.junit.internal.runners.model.MultipleFailureException(list);
    }
}
