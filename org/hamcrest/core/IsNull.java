//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.hamcrest.core;

import org.hamcrest.*;

public class IsNull extends BaseMatcher
{
    public boolean matches(final Object o) {
        return o == null;
    }
    
    public void describeTo(final Description description) {
        description.appendText("null");
    }
    
    @Factory
    public static Matcher nullValue() {
        return new IsNull();
    }
    
    @Factory
    public static Matcher notNullValue() {
        return IsNot.not(nullValue());
    }
    
    @Factory
    public static Matcher nullValue(final Class clazz) {
        return nullValue();
    }
    
    @Factory
    public static Matcher notNullValue(final Class clazz) {
        return notNullValue();
    }
}
