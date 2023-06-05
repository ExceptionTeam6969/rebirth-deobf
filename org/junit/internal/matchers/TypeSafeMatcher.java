//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.matchers;

import org.hamcrest.*;
import java.lang.reflect.*;

public abstract class TypeSafeMatcher extends BaseMatcher
{
    private Class expectedType;
    
    public abstract boolean matchesSafely(final Object p0);
    
    protected TypeSafeMatcher() {
        this.expectedType = findExpectedType(this.getClass());
    }
    
    private static Class findExpectedType(final Class clazz) {
        for (Class<? super Object> superclass = (Class<? super Object>)clazz; superclass != Object.class; superclass = superclass.getSuperclass()) {
            for (final Method method : superclass.getDeclaredMethods()) {
                if (method != 0) {
                    return method.getParameterTypes()[0];
                }
            }
        }
        throw new Error("Cannot determine correct type for matchesSafely() method.");
    }
    
    protected TypeSafeMatcher(final Class expectedType) {
        this.expectedType = expectedType;
    }
    
    public final boolean matches(final Object o) {
        return o != null && this.expectedType.isInstance(o) && this.matchesSafely(o);
    }
}
