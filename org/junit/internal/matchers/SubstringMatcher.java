//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.matchers;

import org.hamcrest.*;

public abstract class SubstringMatcher extends TypeSafeMatcher
{
    protected final String substring;
    
    protected SubstringMatcher(final String substring) {
        this.substring = substring;
    }
    
    public boolean matchesSafely(final String s) {
        return this.evalSubstringOf(s);
    }
    
    public void describeTo(final Description description) {
        description.appendText("a string ").appendText(this.relationship()).appendText(" ").appendValue(this.substring);
    }
    
    protected abstract boolean evalSubstringOf(final String p0);
    
    protected abstract String relationship();
    
    @Override
    public boolean matchesSafely(final Object o) {
        return this.matchesSafely((String)o);
    }
}
