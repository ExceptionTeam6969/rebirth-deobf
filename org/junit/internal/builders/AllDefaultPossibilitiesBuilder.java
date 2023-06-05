//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.builders;

import org.junit.runners.model.*;
import org.junit.runner.*;
import java.util.*;

public class AllDefaultPossibilitiesBuilder extends RunnerBuilder
{
    private final boolean fCanUseSuiteMethod;
    
    public AllDefaultPossibilitiesBuilder(final boolean fCanUseSuiteMethod) {
        this.fCanUseSuiteMethod = fCanUseSuiteMethod;
    }
    
    @Override
    public Runner runnerForClass(final Class clazz) throws Throwable {
        final Iterator<RunnerBuilder> iterator = Arrays.asList(this.ignoredBuilder(), this.annotatedBuilder(), this.suiteMethodBuilder(), this.junit3Builder(), this.junit4Builder()).iterator();
        while (iterator.hasNext()) {
            final Runner safeRunnerForClass = iterator.next().safeRunnerForClass(clazz);
            if (safeRunnerForClass != null) {
                return safeRunnerForClass;
            }
        }
        return null;
    }
    
    protected JUnit4Builder junit4Builder() {
        return new JUnit4Builder();
    }
    
    protected JUnit3Builder junit3Builder() {
        return new JUnit3Builder();
    }
    
    protected AnnotatedBuilder annotatedBuilder() {
        return new AnnotatedBuilder(this);
    }
    
    protected IgnoredBuilder ignoredBuilder() {
        return new IgnoredBuilder();
    }
    
    protected RunnerBuilder suiteMethodBuilder() {
        if (this.fCanUseSuiteMethod) {
            return new SuiteMethodBuilder();
        }
        return new NullBuilder();
    }
}
