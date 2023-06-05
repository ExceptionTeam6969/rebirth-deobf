//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.hamcrest.core;

import org.hamcrest.*;

public class IsInstanceOf extends BaseMatcher
{
    private final Class theClass;
    
    public IsInstanceOf(final Class theClass) {
        this.theClass = theClass;
    }
    
    public boolean matches(final Object o) {
        return this.theClass.isInstance(o);
    }
    
    public void describeTo(final Description description) {
        description.appendText("an instance of ").appendText(this.theClass.getName());
    }
    
    @Factory
    public static Matcher instanceOf(final Class clazz) {
        return new IsInstanceOf(clazz);
    }
}
