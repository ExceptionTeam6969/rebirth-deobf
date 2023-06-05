//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.matchers;

import org.hamcrest.*;

public class Each
{
    public static Matcher each(final Matcher matcher) {
        return new BaseMatcher(CoreMatchers.not(IsCollectionContaining.hasItem(CoreMatchers.not(matcher))), matcher) {
            final Matcher val$allItemsAre;
            final Matcher val$individual;
            
            public boolean matches(final Object o) {
                return this.val$allItemsAre.matches(o);
            }
            
            public void describeTo(final Description description) {
                description.appendText("each ");
                this.val$individual.describeTo(description);
            }
        };
    }
}
