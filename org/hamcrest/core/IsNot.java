//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.hamcrest.core;

import org.hamcrest.*;

public class IsNot extends BaseMatcher
{
    private final Matcher matcher;
    
    public IsNot(final Matcher matcher) {
        this.matcher = matcher;
    }
    
    public boolean matches(final Object o) {
        return !this.matcher.matches(o);
    }
    
    public void describeTo(final Description description) {
        description.appendText("not ").appendDescriptionOf(this.matcher);
    }
    
    @Factory
    public static Matcher not(final Matcher matcher) {
        return new IsNot(matcher);
    }
    
    @Factory
    public static Matcher not(final Object o) {
        return not(IsEqual.equalTo(o));
    }
}
