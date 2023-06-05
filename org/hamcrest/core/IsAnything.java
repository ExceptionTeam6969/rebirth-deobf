//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.hamcrest.core;

import org.hamcrest.*;

public class IsAnything extends BaseMatcher
{
    private final String description;
    
    public IsAnything() {
        this("ANYTHING");
    }
    
    public IsAnything(final String description) {
        this.description = description;
    }
    
    public boolean matches(final Object o) {
        return true;
    }
    
    public void describeTo(final Description description) {
        description.appendText(this.description);
    }
    
    @Factory
    public static Matcher anything() {
        return new IsAnything();
    }
    
    @Factory
    public static Matcher anything(final String s) {
        return new IsAnything(s);
    }
    
    @Factory
    public static Matcher any(final Class clazz) {
        return new IsAnything();
    }
}
