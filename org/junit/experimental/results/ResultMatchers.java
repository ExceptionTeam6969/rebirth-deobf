//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.experimental.results;

import org.junit.internal.matchers.*;
import org.hamcrest.*;

public class ResultMatchers
{
    public static Matcher isSuccessful() {
        return failureCountIs(0);
    }
    
    public static Matcher failureCountIs(final int n) {
        return new TypeSafeMatcher(n) {
            final int val$count;
            
            public void describeTo(final Description description) {
                description.appendText("has " + this.val$count + " failures");
            }
            
            public boolean matchesSafely(final PrintableResult printableResult) {
                return printableResult.failureCount() == this.val$count;
            }
            
            @Override
            public boolean matchesSafely(final Object o) {
                return this.matchesSafely((PrintableResult)o);
            }
        };
    }
    
    public static Matcher hasSingleFailureContaining(final String s) {
        return new BaseMatcher(s) {
            final String val$string;
            
            public boolean matches(final Object o) {
                return o.toString().contains(this.val$string) && ResultMatchers.failureCountIs(1).matches(o);
            }
            
            public void describeTo(final Description description) {
                description.appendText("has single failure containing " + this.val$string);
            }
        };
    }
    
    public static Matcher hasFailureContaining(final String s) {
        return new BaseMatcher(s) {
            final String val$string;
            
            public boolean matches(final Object o) {
                return o.toString().contains(this.val$string);
            }
            
            public void describeTo(final Description description) {
                description.appendText("has failure containing " + this.val$string);
            }
        };
    }
}
