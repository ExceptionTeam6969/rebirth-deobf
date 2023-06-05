//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runner;

import org.junit.runners.*;
import org.junit.runners.model.*;

public class Computer
{
    public static Computer serial() {
        return new Computer();
    }
    
    public Runner getSuite(final RunnerBuilder runnerBuilder, final Class[] array) throws InitializationError {
        return new Suite(new RunnerBuilder(runnerBuilder) {
            final RunnerBuilder val$builder;
            final Computer this$0;
            
            @Override
            public Runner runnerForClass(final Class clazz) throws Throwable {
                return this.this$0.getRunner(this.val$builder, clazz);
            }
        }, array);
    }
    
    protected Runner getRunner(final RunnerBuilder runnerBuilder, final Class clazz) throws Throwable {
        return runnerBuilder.runnerForClass(clazz);
    }
}
