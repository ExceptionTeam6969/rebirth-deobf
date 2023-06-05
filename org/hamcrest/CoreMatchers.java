//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.hamcrest;

import org.hamcrest.core.*;

public class CoreMatchers
{
    public static Matcher is(final Matcher matcher) {
        return Is.is(matcher);
    }
    
    public static Matcher is(final Object o) {
        return Is.is(o);
    }
    
    public static Matcher is(final Class clazz) {
        return Is.is(clazz);
    }
    
    public static Matcher not(final Matcher matcher) {
        return IsNot.not(matcher);
    }
    
    public static Matcher not(final Object o) {
        return IsNot.not(o);
    }
    
    public static Matcher equalTo(final Object o) {
        return IsEqual.equalTo(o);
    }
    
    public static Matcher instanceOf(final Class clazz) {
        return IsInstanceOf.instanceOf(clazz);
    }
    
    public static Matcher allOf(final Matcher... array) {
        return AllOf.allOf(array);
    }
    
    public static Matcher allOf(final Iterable iterable) {
        return AllOf.allOf(iterable);
    }
    
    public static Matcher anyOf(final Matcher... array) {
        return AnyOf.anyOf(array);
    }
    
    public static Matcher anyOf(final Iterable iterable) {
        return AnyOf.anyOf(iterable);
    }
    
    public static Matcher sameInstance(final Object o) {
        return IsSame.sameInstance(o);
    }
    
    public static Matcher anything() {
        return IsAnything.anything();
    }
    
    public static Matcher anything(final String s) {
        return IsAnything.anything(s);
    }
    
    public static Matcher any(final Class clazz) {
        return IsAnything.any(clazz);
    }
    
    public static Matcher nullValue() {
        return IsNull.nullValue();
    }
    
    public static Matcher nullValue(final Class clazz) {
        return IsNull.nullValue(clazz);
    }
    
    public static Matcher notNullValue() {
        return IsNull.notNullValue();
    }
    
    public static Matcher notNullValue(final Class clazz) {
        return IsNull.notNullValue(clazz);
    }
    
    public static Matcher describedAs(final String s, final Matcher matcher, final Object... array) {
        return DescribedAs.describedAs(s, matcher, array);
    }
}
