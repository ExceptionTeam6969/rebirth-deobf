//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal;

import org.hamcrest.*;

public class AssumptionViolatedException extends RuntimeException implements SelfDescribing
{
    private static final long serialVersionUID = 1L;
    private final Object fValue;
    private final Matcher fMatcher;
    
    public AssumptionViolatedException(final Object fValue, final Matcher fMatcher) {
        super((fValue instanceof Throwable) ? ((Throwable)fValue) : null);
        this.fValue = fValue;
        this.fMatcher = fMatcher;
    }
    
    public AssumptionViolatedException(final String s) {
        this(s, (Matcher)null);
    }
    
    @Override
    public String getMessage() {
        return StringDescription.asString(this);
    }
    
    public void describeTo(final Description description) {
        if (this.fMatcher != null) {
            description.appendText("got: ");
            description.appendValue(this.fValue);
            description.appendText(", expected: ");
            description.appendDescriptionOf(this.fMatcher);
        }
        else {
            description.appendText("failed assumption: " + this.fValue);
        }
    }
}
