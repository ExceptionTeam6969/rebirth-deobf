//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.matchers;

import org.hamcrest.*;
import org.junit.internal.matchers.*;

public class JUnitMatchers
{
    public static Matcher hasItem(final Object o) {
        return IsCollectionContaining.hasItem(o);
    }
    
    public static Matcher hasItem(final Matcher matcher) {
        return IsCollectionContaining.hasItem(matcher);
    }
    
    public static Matcher hasItems(final Object... array) {
        return IsCollectionContaining.hasItems(array);
    }
    
    public static Matcher hasItems(final Matcher... array) {
        return IsCollectionContaining.hasItems(array);
    }
    
    public static Matcher everyItem(final Matcher matcher) {
        return Each.each(matcher);
    }
    
    public static Matcher containsString(final String s) {
        return StringContains.containsString(s);
    }
    
    public static CombinableMatcher both(final Matcher matcher) {
        return new CombinableMatcher(matcher);
    }
    
    public static CombinableMatcher either(final Matcher matcher) {
        return new CombinableMatcher(matcher);
    }
}
