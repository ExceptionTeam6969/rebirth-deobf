//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.hamcrest.core;

import org.hamcrest.*;

public class Is extends BaseMatcher
{
    private final Matcher matcher;
    
    public Is(final Matcher matcher) {
        this.matcher = matcher;
    }
    
    public boolean matches(final Object o) {
        return this.matcher.matches(o);
    }
    
    public void describeTo(final Description description) {
        description.appendText("is ").appendDescriptionOf(this.matcher);
    }
    
    @Factory
    public static Matcher is(final Matcher matcher) {
        return new Is(matcher);
    }
    
    @Factory
    public static Matcher is(final Object o) {
        return is(IsEqual.equalTo(o));
    }
    
    @Factory
    public static Matcher is(final Class clazz) {
        return is(IsInstanceOf.instanceOf(clazz));
    }
}
