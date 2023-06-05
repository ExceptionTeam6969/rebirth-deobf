//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runners;

import org.junit.runner.*;
import org.junit.internal.runners.rules.*;
import org.junit.runners.model.*;
import org.junit.internal.runners.statements.*;
import org.junit.*;
import org.junit.rules.*;
import org.junit.internal.runners.model.*;
import org.junit.internal.*;
import java.lang.annotation.*;
import org.junit.runner.notification.*;
import org.junit.runner.manipulation.*;
import java.util.*;

public abstract class ParentRunner extends Runner implements Filterable, Sortable
{
    private final TestClass fTestClass;
    private Sorter fSorter;
    private List fFilteredChildren;
    private RunnerScheduler fScheduler;
    
    protected ParentRunner(final Class clazz) throws InitializationError {
        this.fSorter = Sorter.NULL;
        this.fFilteredChildren = null;
        this.fScheduler = (RunnerScheduler)new RunnerScheduler() {
            final ParentRunner this$0;
            
            public void schedule(final Runnable runnable) {
                runnable.run();
            }
            
            public void finished() {
            }
        };
        this.fTestClass = new TestClass(clazz);
        this.validate();
    }
    
    protected abstract List getChildren();
    
    protected abstract Description describeChild(final Object p0);
    
    protected abstract void runChild(final Object p0, final RunNotifier p1);
    
    protected void collectInitializationErrors(final List list) {
        this.validatePublicVoidNoArgMethods(BeforeClass.class, true, list);
        this.validatePublicVoidNoArgMethods(AfterClass.class, true, list);
        this.validateClassRules(list);
    }
    
    protected void validatePublicVoidNoArgMethods(final Class clazz, final boolean b, final List list) {
        final Iterator<FrameworkMethod> iterator = this.getTestClass().getAnnotatedMethods(clazz).iterator();
        while (iterator.hasNext()) {
            iterator.next().validatePublicVoidNoArg(b, list);
        }
    }
    
    private void validateClassRules(final List list) {
        RuleFieldValidator.CLASS_RULE_VALIDATOR.validate(this.getTestClass(), list);
    }
    
    protected Statement classBlock(final RunNotifier runNotifier) {
        return this.withClassRules(this.withAfterClasses(this.withBeforeClasses(this.childrenInvoker(runNotifier))));
    }
    
    protected Statement withBeforeClasses(final Statement statement) {
        final List annotatedMethods = this.fTestClass.getAnnotatedMethods((Class)BeforeClass.class);
        return (Statement)(annotatedMethods.isEmpty() ? statement : new RunBefores(statement, annotatedMethods, (Object)null));
    }
    
    protected Statement withAfterClasses(final Statement statement) {
        final List annotatedMethods = this.fTestClass.getAnnotatedMethods((Class)AfterClass.class);
        return (Statement)(annotatedMethods.isEmpty() ? statement : new RunAfters(statement, annotatedMethods, (Object)null));
    }
    
    private Statement withClassRules(final Statement statement) {
        final List classRules = this.classRules();
        return (Statement)(classRules.isEmpty() ? statement : new RunRules(statement, (Iterable)classRules, this.getDescription()));
    }
    
    protected List classRules() {
        return this.fTestClass.getAnnotatedFieldValues((Object)null, (Class)ClassRule.class, (Class)TestRule.class);
    }
    
    protected Statement childrenInvoker(final RunNotifier runNotifier) {
        return new Statement(runNotifier) {
            final RunNotifier val$notifier;
            final ParentRunner this$0;
            
            public void evaluate() {
                ParentRunner.access$000(this.this$0, this.val$notifier);
            }
        };
    }
    
    private void runChildren(final RunNotifier runNotifier) {
        final Iterator<Object> iterator = this.getFilteredChildren().iterator();
        while (iterator.hasNext()) {
            this.fScheduler.schedule((Runnable)new Runnable(iterator.next(), runNotifier) {
                final Object val$each;
                final RunNotifier val$notifier;
                final ParentRunner this$0;
                
                public void run() {
                    this.this$0.runChild(this.val$each, this.val$notifier);
                }
            });
        }
        this.fScheduler.finished();
    }
    
    protected String getName() {
        return this.fTestClass.getName();
    }
    
    public final TestClass getTestClass() {
        return this.fTestClass;
    }
    
    protected final void runLeaf(final Statement statement, final Description description, final RunNotifier runNotifier) {
        final EachTestNotifier eachTestNotifier = new EachTestNotifier(runNotifier, description);
        eachTestNotifier.fireTestStarted();
        try {
            statement.evaluate();
            eachTestNotifier.fireTestFinished();
        }
        catch (AssumptionViolatedException ex) {
            eachTestNotifier.addFailedAssumption(ex);
            eachTestNotifier.fireTestFinished();
        }
        catch (Throwable t) {
            eachTestNotifier.addFailure(t);
            eachTestNotifier.fireTestFinished();
        }
    }
    
    protected Annotation[] getRunnerAnnotations() {
        return this.fTestClass.getAnnotations();
    }
    
    public Description getDescription() {
        final Description suiteDescription = Description.createSuiteDescription(this.getName(), this.getRunnerAnnotations());
        final Iterator<Object> iterator = this.getFilteredChildren().iterator();
        while (iterator.hasNext()) {
            suiteDescription.addChild(this.describeChild(iterator.next()));
        }
        return suiteDescription;
    }
    
    public void run(final RunNotifier runNotifier) {
        final EachTestNotifier eachTestNotifier = new EachTestNotifier(runNotifier, this.getDescription());
        try {
            this.classBlock(runNotifier).evaluate();
        }
        catch (AssumptionViolatedException ex2) {
            eachTestNotifier.fireTestIgnored();
        }
        catch (StoppedByUserException ex) {
            throw ex;
        }
        catch (Throwable t) {
            eachTestNotifier.addFailure(t);
        }
    }
    
    public void filter(final Filter filter) throws NoTestsRemainException {
        final Iterator<Object> iterator = (Iterator<Object>)this.getFilteredChildren().iterator();
        while (iterator.hasNext()) {
            final Object next = iterator.next();
            if (this.shouldRun(filter, next)) {
                try {
                    filter.apply(next);
                }
                catch (NoTestsRemainException ex) {
                    iterator.remove();
                }
            }
            else {
                iterator.remove();
            }
        }
        if (this.getFilteredChildren().isEmpty()) {
            throw new NoTestsRemainException();
        }
    }
    
    public void sort(final Sorter fSorter) {
        this.fSorter = fSorter;
        final Iterator<Object> iterator = this.getFilteredChildren().iterator();
        while (iterator.hasNext()) {
            this.sortChild(iterator.next());
        }
        Collections.sort((List<Object>)this.getFilteredChildren(), this.comparator());
    }
    
    private void validate() throws InitializationError {
        final ArrayList list = new ArrayList();
        this.collectInitializationErrors(list);
        if (!list.isEmpty()) {
            throw new InitializationError((List)list);
        }
    }
    
    private List getFilteredChildren() {
        if (this.fFilteredChildren == null) {
            this.fFilteredChildren = new ArrayList(this.getChildren());
        }
        return this.fFilteredChildren;
    }
    
    private void sortChild(final Object o) {
        this.fSorter.apply(o);
    }
    
    private boolean shouldRun(final Filter filter, final Object o) {
        return filter.shouldRun(this.describeChild(o));
    }
    
    private Comparator comparator() {
        return new Comparator() {
            final ParentRunner this$0;
            
            public int compare(final Object o, final Object o2) {
                return ParentRunner.access$100(this.this$0).compare(this.this$0.describeChild(o), this.this$0.describeChild(o2));
            }
        };
    }
    
    public void setScheduler(final RunnerScheduler fScheduler) {
        this.fScheduler = fScheduler;
    }
    
    static void access$000(final ParentRunner parentRunner, final RunNotifier runNotifier) {
        parentRunner.runChildren(runNotifier);
    }
    
    static Sorter access$100(final ParentRunner parentRunner) {
        return parentRunner.fSorter;
    }
}
