//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.hamcrest.core;

import java.util.*;
import org.hamcrest.*;

public class AllOf extends BaseMatcher
{
    private final Iterable matchers;
    
    public AllOf(final Iterable matchers) {
        this.matchers = matchers;
    }
    
    public boolean matches(final Object o) {
        final Iterator<Matcher> iterator = this.matchers.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().matches(o)) {
                return false;
            }
        }
        return true;
    }
    
    public void describeTo(final Description description) {
        description.appendList("(", " and ", ")", this.matchers);
    }
    
    @Factory
    public static Matcher allOf(final Matcher... array) {
        return allOf(Arrays.asList(array));
    }
    
    @Factory
    public static Matcher allOf(final Iterable iterable) {
        return new AllOf(iterable);
    }
}
