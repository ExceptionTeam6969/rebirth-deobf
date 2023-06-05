//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.hamcrest.core;

import org.hamcrest.*;

public class IsEqual extends BaseMatcher
{
    private final Object object;
    
    public IsEqual(final Object object) {
        this.object = object;
    }
    
    public boolean matches(final Object o) {
        return areEqual(this.object, o);
    }
    
    public void describeTo(final Description description) {
        description.appendValue(this.object);
    }
    
    private static boolean isArray(final Object o) {
        return o.getClass().isArray();
    }
    
    @Factory
    public static Matcher equalTo(final Object o) {
        return new IsEqual(o);
    }
}
