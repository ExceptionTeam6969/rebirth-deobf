//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.hamcrest.core;

import java.util.*;
import org.hamcrest.*;

public class AnyOf extends BaseMatcher
{
    private final Iterable matchers;
    
    public AnyOf(final Iterable matchers) {
        this.matchers = matchers;
    }
    
    public boolean matches(final Object o) {
        final Iterator<Matcher> iterator = this.matchers.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().matches(o)) {
                return true;
            }
        }
        return false;
    }
    
    public void describeTo(final Description description) {
        description.appendList("(", " or ", ")", this.matchers);
    }
    
    @Factory
    public static Matcher anyOf(final Matcher... array) {
        return anyOf(Arrays.asList(array));
    }
    
    @Factory
    public static Matcher anyOf(final Iterable iterable) {
        return new AnyOf(iterable);
    }
}
