//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.builders;

import org.junit.runners.model.*;
import org.junit.runner.*;
import org.junit.internal.runners.*;

public class SuiteMethodBuilder extends RunnerBuilder
{
    @Override
    public Runner runnerForClass(final Class clazz) throws Throwable {
        if (this.hasSuiteMethod(clazz)) {
            return new SuiteMethod(clazz);
        }
        return null;
    }
    
    public boolean hasSuiteMethod(final Class clazz) {
        try {
            clazz.getMethod("suite", (Class[])new Class[0]);
        }
        catch (NoSuchMethodException ex) {
            return false;
        }
        return true;
    }
}
