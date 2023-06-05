//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.builders;

import org.junit.runners.model.*;
import org.junit.runner.*;
import org.junit.internal.runners.*;
import junit.framework.*;

public class JUnit3Builder extends RunnerBuilder
{
    @Override
    public Runner runnerForClass(final Class clazz) throws Throwable {
        if (this.isPre4Test(clazz)) {
            return new JUnit38ClassRunner(clazz);
        }
        return null;
    }
    
    boolean isPre4Test(final Class clazz) {
        return TestCase.class.isAssignableFrom(clazz);
    }
}
