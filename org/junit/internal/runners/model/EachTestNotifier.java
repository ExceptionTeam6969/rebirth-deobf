//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners.model;

import org.junit.runner.*;
import org.junit.runners.model.*;
import org.junit.runner.notification.*;
import java.util.*;
import org.junit.internal.*;

public class EachTestNotifier
{
    private final RunNotifier fNotifier;
    private final Description fDescription;
    
    public EachTestNotifier(final RunNotifier fNotifier, final Description fDescription) {
        this.fNotifier = fNotifier;
        this.fDescription = fDescription;
    }
    
    public void addFailure(final Throwable t) {
        if (t instanceof MultipleFailureException) {
            this.addMultipleFailureException((MultipleFailureException)t);
        }
        else {
            this.fNotifier.fireTestFailure(new Failure(this.fDescription, t));
        }
    }
    
    private void addMultipleFailureException(final MultipleFailureException ex) {
        final Iterator<Throwable> iterator = ex.getFailures().iterator();
        while (iterator.hasNext()) {
            this.addFailure(iterator.next());
        }
    }
    
    public void addFailedAssumption(final AssumptionViolatedException ex) {
        this.fNotifier.fireTestAssumptionFailed(new Failure(this.fDescription, (Throwable)ex));
    }
    
    public void fireTestFinished() {
        this.fNotifier.fireTestFinished(this.fDescription);
    }
    
    public void fireTestStarted() {
        this.fNotifier.fireTestStarted(this.fDescription);
    }
    
    public void fireTestIgnored() {
        this.fNotifier.fireTestIgnored(this.fDescription);
    }
}
