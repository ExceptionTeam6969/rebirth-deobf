//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.experimental.theories.internal;

import java.util.*;

public class ParameterizedAssertionError extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    
    public ParameterizedAssertionError(final Throwable t, final String s, final Object... array) {
        super(String.format("%s(%s)", s, join(", ", array)), t);
    }
    
    @Override
    public boolean equals(final Object o) {
        return this.toString().equals(o.toString());
    }
    
    public static String join(final String s, final Object... array) {
        return join(s, Arrays.asList(array));
    }
    
    public static String join(final String s, final Collection collection) {
        final StringBuffer sb = new StringBuffer();
        final Iterator<Object> iterator = collection.iterator();
        while (iterator.hasNext()) {
            sb.append(stringValueOf(iterator.next()));
            if (iterator.hasNext()) {
                sb.append(s);
            }
        }
        return sb.toString();
    }
    
    private static String stringValueOf(final Object o) {
        try {
            return String.valueOf(o);
        }
        catch (Throwable t) {
            return "[toString failed]";
        }
    }
}
