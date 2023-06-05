//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.matchers;

import org.hamcrest.*;

public class CombinableMatcher extends BaseMatcher
{
    private final Matcher fMatcher;
    
    public CombinableMatcher(final Matcher fMatcher) {
        this.fMatcher = fMatcher;
    }
    
    public boolean matches(final Object o) {
        return this.fMatcher.matches(o);
    }
    
    public void describeTo(final Description description) {
        description.appendDescriptionOf(this.fMatcher);
    }
    
    public CombinableMatcher and(final Matcher matcher) {
        return new CombinableMatcher(CoreMatchers.allOf(matcher, this.fMatcher));
    }
    
    public CombinableMatcher or(final Matcher matcher) {
        return new CombinableMatcher(CoreMatchers.anyOf(matcher, this.fMatcher));
    }
}
