//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit;

import java.util.*;
import org.junit.internal.matchers.*;
import org.hamcrest.*;
import org.junit.internal.*;

public class Assume
{
    public static void assumeTrue(final boolean b) {
        assumeThat(b, CoreMatchers.is(true));
    }
    
    public static void assumeNotNull(final Object... array) {
        assumeThat(Arrays.asList(array), Each.each(CoreMatchers.notNullValue()));
    }
    
    public static void assumeThat(final Object o, final Matcher matcher) {
        if (!matcher.matches(o)) {
            throw new AssumptionViolatedException(o, matcher);
        }
    }
    
    public static void assumeNoException(final Throwable t) {
        assumeThat(t, CoreMatchers.nullValue());
    }
}
