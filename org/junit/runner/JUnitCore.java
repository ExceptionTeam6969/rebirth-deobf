//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runner;

import junit.runner.*;
import java.lang.annotation.*;
import org.junit.internal.*;
import org.junit.runner.notification.*;
import java.util.*;
import junit.framework.*;
import org.junit.internal.runners.*;

public class JUnitCore
{
    private RunNotifier fNotifier;
    
    public JUnitCore() {
        this.fNotifier = new RunNotifier();
    }
    
    public static void main(final String... array) {
        runMainAndExit((JUnitSystem)new RealSystem(), array);
    }
    
    public static void runMainAndExit(final JUnitSystem unitSystem, final String... array) {
        unitSystem.exit((int)(new JUnitCore().runMain(unitSystem, array).wasSuccessful() ? 0 : 1));
    }
    
    public static Result runClasses(final Computer computer, final Class... array) {
        return new JUnitCore().run(computer, array);
    }
    
    public static Result runClasses(final Class... array) {
        return new JUnitCore().run(defaultComputer(), array);
    }
    
    public Result runMain(final JUnitSystem unitSystem, final String... array) {
        unitSystem.out().println("JUnit version " + Version.id());
        final ArrayList<Class<?>> list = new ArrayList<Class<?>>();
        final ArrayList<Failure> list2 = new ArrayList<Failure>();
        for (final String s : array) {
            try {
                list.add(Class.forName(s));
            }
            catch (ClassNotFoundException ex) {
                unitSystem.out().println("Could not find class: " + s);
                list2.add(new Failure(Description.createSuiteDescription(s, new Annotation[0]), ex));
            }
        }
        this.addListener((RunListener)new TextListener(unitSystem));
        final Result run = this.run((Class[])list.toArray(new Class[0]));
        final Iterator<Object> iterator = list2.iterator();
        while (iterator.hasNext()) {
            run.getFailures().add(iterator.next());
        }
        return run;
    }
    
    public String getVersion() {
        return Version.id();
    }
    
    public Result run(final Class... array) {
        return this.run(Request.classes(defaultComputer(), array));
    }
    
    public Result run(final Computer computer, final Class... array) {
        return this.run(Request.classes(computer, array));
    }
    
    public Result run(final Request request) {
        return this.run(request.getRunner());
    }
    
    public Result run(final Test test) {
        return this.run((Runner)new JUnit38ClassRunner(test));
    }
    
    public Result run(final Runner runner) {
        final Result result = new Result();
        final RunListener listener = result.createListener();
        this.fNotifier.addFirstListener(listener);
        this.fNotifier.fireTestRunStarted(runner.getDescription());
        runner.run(this.fNotifier);
        this.fNotifier.fireTestRunFinished(result);
        this.removeListener(listener);
        return result;
    }
    
    public void addListener(final RunListener runListener) {
        this.fNotifier.addListener(runListener);
    }
    
    public void removeListener(final RunListener runListener) {
        this.fNotifier.removeListener(runListener);
    }
    
    static Computer defaultComputer() {
        return new Computer();
    }
}
