//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.builders;

import org.junit.runner.*;
import org.junit.runners.model.*;

public class AnnotatedBuilder extends RunnerBuilder
{
    private static final String CONSTRUCTOR_ERROR_FORMAT = "Custom runner class %s should have a public constructor with signature %s(Class testClass)";
    private RunnerBuilder fSuiteBuilder;
    
    public AnnotatedBuilder(final RunnerBuilder fSuiteBuilder) {
        this.fSuiteBuilder = fSuiteBuilder;
    }
    
    @Override
    public Runner runnerForClass(final Class clazz) throws Exception {
        final RunWith runWith = clazz.getAnnotation(RunWith.class);
        if (runWith != null) {
            return this.buildRunner(runWith.value(), clazz);
        }
        return null;
    }
    
    public Runner buildRunner(final Class clazz, final Class clazz2) throws Exception {
        try {
            return clazz.getConstructor(Class.class).newInstance(clazz2);
        }
        catch (NoSuchMethodException ex) {
            try {
                return clazz.getConstructor(Class.class, RunnerBuilder.class).newInstance(clazz2, this.fSuiteBuilder);
            }
            catch (NoSuchMethodException ex2) {
                final String simpleName = clazz.getSimpleName();
                throw new InitializationError(String.format("Custom runner class %s should have a public constructor with signature %s(Class testClass)", simpleName, simpleName));
            }
        }
    }
}
