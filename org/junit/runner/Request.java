//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runner;

import org.junit.internal.builders.*;
import org.junit.runners.model.*;
import org.junit.internal.runners.*;
import org.junit.runner.manipulation.*;
import java.util.*;
import org.junit.internal.requests.*;

public abstract class Request
{
    public static Request method(final Class clazz, final String s) {
        return aClass(clazz).filterWith(Description.createTestDescription(clazz, s));
    }
    
    public static Request aClass(final Class clazz) {
        return (Request)new ClassRequest(clazz);
    }
    
    public static Request classWithoutSuiteMethod(final Class clazz) {
        return (Request)new ClassRequest(clazz, false);
    }
    
    public static Request classes(final Computer computer, final Class... array) {
        try {
            return runner(computer.getSuite((RunnerBuilder)new AllDefaultPossibilitiesBuilder(true), array));
        }
        catch (InitializationError initializationError) {
            throw new RuntimeException("Bug in saff's brain: Suite constructor, called as above, should always complete");
        }
    }
    
    public static Request classes(final Class... array) {
        return classes(JUnitCore.defaultComputer(), array);
    }
    
    @Deprecated
    public static Request errorReport(final Class clazz, final Throwable t) {
        return runner((Runner)new ErrorReportingRunner(clazz, t));
    }
    
    public static Request runner(final Runner runner) {
        return new Request(runner) {
            final Runner val$runner;
            
            @Override
            public Runner getRunner() {
                return this.val$runner;
            }
        };
    }
    
    public abstract Runner getRunner();
    
    public Request filterWith(final Filter filter) {
        return (Request)new FilterRequest(this, filter);
    }
    
    public Request filterWith(final Description description) {
        return this.filterWith(Filter.matchMethodDescription(description));
    }
    
    public Request sortWith(final Comparator comparator) {
        return (Request)new SortingRequest(this, comparator);
    }
}
