//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.builders;

import org.junit.runners.model.*;
import org.junit.runner.*;
import org.junit.*;

public class IgnoredBuilder extends RunnerBuilder
{
    @Override
    public Runner runnerForClass(final Class clazz) {
        if (clazz.getAnnotation(Ignore.class) != null) {
            return new IgnoredClassRunner(clazz);
        }
        return null;
    }
}
