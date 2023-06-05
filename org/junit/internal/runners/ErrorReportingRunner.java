//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners;

import org.junit.runner.*;
import java.lang.reflect.*;
import org.junit.runners.model.*;
import java.util.*;
import org.junit.runner.notification.*;

public class ErrorReportingRunner extends Runner
{
    private final List fCauses;
    private final Class fTestClass;
    
    public ErrorReportingRunner(final Class fTestClass, final Throwable t) {
        this.fTestClass = fTestClass;
        this.fCauses = this.getCauses(t);
    }
    
    @Override
    public Description getDescription() {
        final Description suiteDescription = Description.createSuiteDescription(this.fTestClass);
        final Iterator<Throwable> iterator = this.fCauses.iterator();
        while (iterator.hasNext()) {
            suiteDescription.addChild(this.describeCause(iterator.next()));
        }
        return suiteDescription;
    }
    
    @Override
    public void run(final RunNotifier runNotifier) {
        final Iterator<Throwable> iterator = this.fCauses.iterator();
        while (iterator.hasNext()) {
            this.runCause(iterator.next(), runNotifier);
        }
    }
    
    private List getCauses(final Throwable t) {
        if (t instanceof InvocationTargetException) {
            return this.getCauses(t.getCause());
        }
        if (t instanceof InitializationError) {
            return ((InitializationError)t).getCauses();
        }
        if (t instanceof org.junit.internal.runners.InitializationError) {
            return ((org.junit.internal.runners.InitializationError)t).getCauses();
        }
        return Arrays.asList(t);
    }
    
    private Description describeCause(final Throwable t) {
        return Description.createTestDescription(this.fTestClass, "initializationError");
    }
    
    private void runCause(final Throwable t, final RunNotifier runNotifier) {
        final Description describeCause = this.describeCause(t);
        runNotifier.fireTestStarted(describeCause);
        runNotifier.fireTestFailure(new Failure(describeCause, t));
        runNotifier.fireTestFinished(describeCause);
    }
}
