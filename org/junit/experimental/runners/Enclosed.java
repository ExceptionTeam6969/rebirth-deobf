//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.experimental.runners;

import org.junit.runners.*;
import org.junit.runners.model.*;

public class Enclosed extends Suite
{
    public Enclosed(final Class clazz, final RunnerBuilder runnerBuilder) throws Throwable {
        super(runnerBuilder, clazz, clazz.getClasses());
    }
}
