//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runners.model;

import org.junit.runner.*;
import org.junit.internal.runners.*;
import java.util.*;

public abstract class RunnerBuilder
{
    private final Set parents;
    
    public RunnerBuilder() {
        this.parents = new HashSet();
    }
    
    public abstract Runner runnerForClass(final Class p0) throws Throwable;
    
    public Runner safeRunnerForClass(final Class clazz) {
        try {
            return this.runnerForClass(clazz);
        }
        catch (Throwable t) {
            return (Runner)new ErrorReportingRunner(clazz, t);
        }
    }
    
    Class addParent(final Class clazz) throws InitializationError {
        if (!this.parents.add(clazz)) {
            throw new InitializationError(String.format("class '%s' (possibly indirectly) contains itself as a SuiteClass", clazz.getName()));
        }
        return clazz;
    }
    
    void removeParent(final Class clazz) {
        this.parents.remove(clazz);
    }
    
    public List runners(final Class clazz, final Class[] array) throws InitializationError {
        this.addParent(clazz);
        final List runners = this.runners(array);
        this.removeParent(clazz);
        return runners;
    }
    
    public List runners(final Class clazz, final List list) throws InitializationError {
        return this.runners(clazz, list.toArray(new Class[0]));
    }
    
    private List runners(final Class[] array) {
        final ArrayList<Runner> list = new ArrayList<Runner>();
        for (int length = array.length, i = 0; i < length; ++i) {
            final Runner safeRunnerForClass = this.safeRunnerForClass(array[i]);
            if (safeRunnerForClass != null) {
                list.add(safeRunnerForClass);
            }
        }
        return list;
    }
}
