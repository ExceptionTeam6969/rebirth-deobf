//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.matchers;

import org.hamcrest.*;
import java.util.*;
import org.hamcrest.core.*;

public class IsCollectionContaining extends TypeSafeMatcher
{
    private final Matcher elementMatcher;
    
    public IsCollectionContaining(final Matcher elementMatcher) {
        this.elementMatcher = elementMatcher;
    }
    
    public boolean matchesSafely(final Iterable iterable) {
        final Iterator<Object> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            if (this.elementMatcher.matches(iterator.next())) {
                return true;
            }
        }
        return false;
    }
    
    public void describeTo(final Description description) {
        description.appendText("a collection containing ").appendDescriptionOf(this.elementMatcher);
    }
    
    @Factory
    public static Matcher hasItem(final Matcher matcher) {
        return new IsCollectionContaining(matcher);
    }
    
    @Factory
    public static Matcher hasItem(final Object o) {
        return hasItem(IsEqual.equalTo(o));
    }
    
    @Factory
    public static Matcher hasItems(final Matcher... array) {
        final ArrayList<Matcher> list = new ArrayList<Matcher>(array.length);
        for (int length = array.length, i = 0; i < length; ++i) {
            list.add(hasItem(array[i]));
        }
        return AllOf.allOf(list);
    }
    
    @Factory
    public static Matcher hasItems(final Object... array) {
        final ArrayList<Matcher> list = new ArrayList<Matcher>(array.length);
        for (int length = array.length, i = 0; i < length; ++i) {
            list.add(hasItem(array[i]));
        }
        return AllOf.allOf(list);
    }
    
    @Override
    public boolean matchesSafely(final Object o) {
        return this.matchesSafely((Iterable)o);
    }
}
