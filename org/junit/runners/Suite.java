//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runners;

import java.util.*;
import org.junit.runners.model.*;
import org.junit.internal.builders.*;
import org.junit.runner.*;
import org.junit.runner.notification.*;
import java.lang.annotation.*;

public class Suite extends ParentRunner
{
    private final List fRunners;
    
    public static Runner emptySuite() {
        try {
            return (Runner)new Suite((Class)null, new Class[0]);
        }
        catch (InitializationError initializationError) {
            throw new RuntimeException("This shouldn't be possible");
        }
    }
    
    private static Class[] getAnnotatedClasses(final Class clazz) throws InitializationError {
        final SuiteClasses suiteClasses = clazz.getAnnotation(SuiteClasses.class);
        if (suiteClasses == null) {
            throw new InitializationError(String.format("class '%s' must have a SuiteClasses annotation", clazz.getName()));
        }
        return suiteClasses.value();
    }
    
    public Suite(final Class clazz, final RunnerBuilder runnerBuilder) throws InitializationError {
        this(runnerBuilder, clazz, getAnnotatedClasses(clazz));
    }
    
    public Suite(final RunnerBuilder runnerBuilder, final Class[] array) throws InitializationError {
        this(null, runnerBuilder.runners((Class)null, array));
    }
    
    protected Suite(final Class clazz, final Class[] array) throws InitializationError {
        this((RunnerBuilder)new AllDefaultPossibilitiesBuilder(true), clazz, array);
    }
    
    protected Suite(final RunnerBuilder runnerBuilder, final Class clazz, final Class[] array) throws InitializationError {
        this(clazz, runnerBuilder.runners(clazz, array));
    }
    
    protected Suite(final Class clazz, final List fRunners) throws InitializationError {
        super(clazz);
        this.fRunners = fRunners;
    }
    
    protected List getChildren() {
        return this.fRunners;
    }
    
    protected Description describeChild(final Runner runner) {
        return runner.getDescription();
    }
    
    protected void runChild(final Runner runner, final RunNotifier runNotifier) {
        runner.run(runNotifier);
    }
    
    protected void runChild(final Object o, final RunNotifier runNotifier) {
        this.runChild((Runner)o, runNotifier);
    }
    
    protected Description describeChild(final Object o) {
        return this.describeChild((Runner)o);
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.TYPE })
    @Inherited
    public @interface SuiteClasses {
        Class[] value();
    }
}
