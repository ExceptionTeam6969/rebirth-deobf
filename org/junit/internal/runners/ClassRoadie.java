//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners;

import org.junit.runner.*;
import org.junit.runner.notification.*;
import java.lang.reflect.*;
import org.junit.internal.*;
import java.util.*;

@Deprecated
public class ClassRoadie
{
    private RunNotifier fNotifier;
    private TestClass fTestClass;
    private Description fDescription;
    private final Runnable fRunnable;
    
    public ClassRoadie(final RunNotifier fNotifier, final TestClass fTestClass, final Description fDescription, final Runnable fRunnable) {
        this.fNotifier = fNotifier;
        this.fTestClass = fTestClass;
        this.fDescription = fDescription;
        this.fRunnable = fRunnable;
    }
    
    protected void runUnprotected() {
        this.fRunnable.run();
    }
    
    protected void addFailure(final Throwable t) {
        this.fNotifier.fireTestFailure(new Failure(this.fDescription, t));
    }
    
    public void runProtected() {
        try {
            this.runBefores();
            this.runUnprotected();
            this.runAfters();
        }
        catch (FailedBefore failedBefore) {
            this.runAfters();
        }
    }
    
    private void runBefores() throws FailedBefore {
        try {
            try {
                final Iterator<Method> iterator = this.fTestClass.getBefores().iterator();
                while (iterator.hasNext()) {
                    iterator.next().invoke(null, new Object[0]);
                }
            }
            catch (InvocationTargetException ex) {
                throw ex.getTargetException();
            }
        }
        catch (AssumptionViolatedException ex2) {
            throw new FailedBefore();
        }
        catch (Throwable t) {
            this.addFailure(t);
            throw new FailedBefore();
        }
    }
    
    private void runAfters() {
        for (final Method method : this.fTestClass.getAfters()) {
            try {
                method.invoke(null, new Object[0]);
            }
            catch (InvocationTargetException ex) {
                this.addFailure(ex.getTargetException());
            }
            catch (Throwable t) {
                this.addFailure(t);
            }
        }
    }
}
