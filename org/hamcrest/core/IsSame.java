//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.hamcrest.core;

import org.hamcrest.*;

public class IsSame extends BaseMatcher
{
    private final Object object;
    
    public IsSame(final Object object) {
        this.object = object;
    }
    
    public boolean matches(final Object o) {
        return o == this.object;
    }
    
    public void describeTo(final Description description) {
        description.appendText("same(").appendValue(this.object).appendText(")");
    }
    
    @Factory
    public static Matcher sameInstance(final Object o) {
        return new IsSame(o);
    }
}
