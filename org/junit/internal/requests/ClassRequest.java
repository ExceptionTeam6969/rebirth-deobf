//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.requests;

import org.junit.runner.*;
import org.junit.internal.builders.*;

public class ClassRequest extends Request
{
    private final Class fTestClass;
    private boolean fCanUseSuiteMethod;
    
    public ClassRequest(final Class fTestClass, final boolean fCanUseSuiteMethod) {
        this.fTestClass = fTestClass;
        this.fCanUseSuiteMethod = fCanUseSuiteMethod;
    }
    
    public ClassRequest(final Class clazz) {
        this(clazz, true);
    }
    
    @Override
    public Runner getRunner() {
        return new AllDefaultPossibilitiesBuilder(this.fCanUseSuiteMethod).safeRunnerForClass(this.fTestClass);
    }
}
