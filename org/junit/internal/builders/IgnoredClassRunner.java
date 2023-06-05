//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.builders;

import org.junit.runner.notification.*;
import org.junit.runner.*;

public class IgnoredClassRunner extends Runner
{
    private final Class fTestClass;
    
    public IgnoredClassRunner(final Class fTestClass) {
        this.fTestClass = fTestClass;
    }
    
    @Override
    public void run(final RunNotifier runNotifier) {
        runNotifier.fireTestIgnored(this.getDescription());
    }
    
    @Override
    public Description getDescription() {
        return Description.createSuiteDescription(this.fTestClass);
    }
}
