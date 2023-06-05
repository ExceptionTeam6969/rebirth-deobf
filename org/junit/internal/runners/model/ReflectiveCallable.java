//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners.model;

import java.lang.reflect.*;

public abstract class ReflectiveCallable
{
    public Object run() throws Throwable {
        try {
            return this.runReflectiveCall();
        }
        catch (InvocationTargetException ex) {
            throw ex.getTargetException();
        }
    }
    
    protected abstract Object runReflectiveCall() throws Throwable;
}
