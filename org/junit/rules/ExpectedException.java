//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.rules;

import org.junit.runners.model.*;
import org.junit.runner.*;
import org.junit.matchers.*;
import org.junit.internal.matchers.*;
import org.junit.*;
import org.hamcrest.*;

public class ExpectedException implements TestRule
{
    private Matcher fMatcher;
    
    public static ExpectedException none() {
        return new ExpectedException();
    }
    
    private ExpectedException() {
        this.fMatcher = null;
    }
    
    public Statement apply(final Statement statement, final Description description) {
        return new ExpectedExceptionStatement(statement);
    }
    
    public void expect(final Matcher fMatcher) {
        if (this.fMatcher == null) {
            this.fMatcher = fMatcher;
        }
        else {
            this.fMatcher = (Matcher)JUnitMatchers.both(this.fMatcher).and(fMatcher);
        }
    }
    
    public void expect(final Class clazz) {
        this.expect(CoreMatchers.instanceOf(clazz));
    }
    
    public void expectMessage(final String s) {
        this.expectMessage(JUnitMatchers.containsString(s));
    }
    
    public void expectMessage(final Matcher matcher) {
        this.expect(this.hasMessage(matcher));
    }
    
    private Matcher hasMessage(final Matcher matcher) {
        return (Matcher)new TypeSafeMatcher(matcher) {
            final Matcher val$matcher;
            final ExpectedException this$0;
            
            public void describeTo(final org.hamcrest.Description description) {
                description.appendText("exception with message ");
                description.appendDescriptionOf(this.val$matcher);
            }
            
            public boolean matchesSafely(final Throwable t) {
                return this.val$matcher.matches(t.getMessage());
            }
            
            public boolean matchesSafely(final Object o) {
                return this.matchesSafely((Throwable)o);
            }
        };
    }
    
    static Matcher access$000(final ExpectedException ex) {
        return ex.fMatcher;
    }
    
    private class ExpectedExceptionStatement extends Statement
    {
        private final Statement fNext;
        final ExpectedException this$0;
        
        public ExpectedExceptionStatement(final ExpectedException this$0, final Statement fNext) {
            this.this$0 = this$0;
            this.fNext = fNext;
        }
        
        @Override
        public void evaluate() throws Throwable {
            try {
                this.fNext.evaluate();
            }
            catch (Throwable t) {
                if (ExpectedException.access$000(this.this$0) == null) {
                    throw t;
                }
                Assert.assertThat((Object)t, ExpectedException.access$000(this.this$0));
                return;
            }
            if (ExpectedException.access$000(this.this$0) != null) {
                throw new AssertionError((Object)("Expected test to throw " + StringDescription.toString(ExpectedException.access$000(this.this$0))));
            }
        }
    }
}
