//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.experimental.theories;

import org.junit.runners.*;
import org.junit.runners.model.*;
import java.util.*;
import org.junit.*;
import org.junit.internal.*;
import java.lang.reflect.*;
import org.junit.experimental.theories.internal.*;

public class Theories extends BlockJUnit4ClassRunner
{
    public Theories(final Class clazz) throws InitializationError {
        super(clazz);
    }
    
    @Override
    protected void collectInitializationErrors(final List list) {
        super.collectInitializationErrors(list);
        this.validateDataPointFields(list);
    }
    
    private void validateDataPointFields(final List list) {
        for (final Field field : this.getTestClass().getJavaClass().getDeclaredFields()) {
            if (field.getAnnotation(DataPoint.class) != null && !Modifier.isStatic(field.getModifiers())) {
                list.add(new Error("DataPoint field " + field.getName() + " must be static"));
            }
        }
    }
    
    @Override
    protected void validateConstructor(final List list) {
        this.validateOnlyOneConstructor(list);
    }
    
    @Override
    protected void validateTestMethods(final List list) {
        for (final FrameworkMethod frameworkMethod : this.computeTestMethods()) {
            if (frameworkMethod.getAnnotation(Theory.class) != null) {
                frameworkMethod.validatePublicVoid(false, list);
            }
            else {
                frameworkMethod.validatePublicVoidNoArg(false, list);
            }
        }
    }
    
    @Override
    protected List computeTestMethods() {
        final List computeTestMethods = super.computeTestMethods();
        final List annotatedMethods = this.getTestClass().getAnnotatedMethods(Theory.class);
        computeTestMethods.removeAll(annotatedMethods);
        computeTestMethods.addAll(annotatedMethods);
        return computeTestMethods;
    }
    
    public Statement methodBlock(final FrameworkMethod frameworkMethod) {
        return new TheoryAnchor(frameworkMethod, this.getTestClass());
    }
    
    public static class TheoryAnchor extends Statement
    {
        private int successes;
        private FrameworkMethod fTestMethod;
        private TestClass fTestClass;
        private List fInvalidParameters;
        
        public TheoryAnchor(final FrameworkMethod fTestMethod, final TestClass fTestClass) {
            this.successes = 0;
            this.fInvalidParameters = new ArrayList();
            this.fTestMethod = fTestMethod;
            this.fTestClass = fTestClass;
        }
        
        private TestClass getTestClass() {
            return this.fTestClass;
        }
        
        @Override
        public void evaluate() throws Throwable {
            this.runWithAssignment(Assignments.allUnassigned(this.fTestMethod.getMethod(), this.getTestClass()));
            if (this.successes == 0) {
                Assert.fail("Never found parameters that satisfied method assumptions.  Violated assumptions: " + this.fInvalidParameters);
            }
        }
        
        protected void runWithAssignment(final Assignments assignments) throws Throwable {
            if (!assignments.isComplete()) {
                this.runWithIncompleteAssignment(assignments);
            }
            else {
                this.runWithCompleteAssignment(assignments);
            }
        }
        
        protected void runWithIncompleteAssignment(final Assignments assignments) throws InstantiationException, IllegalAccessException, Throwable {
            final Iterator<PotentialAssignment> iterator = assignments.potentialsForNextUnassigned().iterator();
            while (iterator.hasNext()) {
                this.runWithAssignment(assignments.assignNext((PotentialAssignment)iterator.next()));
            }
        }
        
        protected void runWithCompleteAssignment(final Assignments assignments) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, Throwable {
            new BlockJUnit4ClassRunner(this.getTestClass().getJavaClass(), assignments) {
                final Assignments val$complete;
                final TheoryAnchor this$0;
                
                @Override
                protected void collectInitializationErrors(final List list) {
                }
                
                public Statement methodBlock(final FrameworkMethod frameworkMethod) {
                    return new Statement(super.methodBlock(frameworkMethod)) {
                        final Statement val$statement;
                        final Theories$TheoryAnchor$1 this$1;
                        
                        @Override
                        public void evaluate() throws Throwable {
                            try {
                                this.val$statement.evaluate();
                                this.this$1.this$0.handleDataPointSuccess();
                            }
                            catch (AssumptionViolatedException ex) {
                                this.this$1.this$0.handleAssumptionViolation(ex);
                            }
                            catch (Throwable t) {
                                this.this$1.this$0.reportParameterizedError(t, this.this$1.val$complete.getArgumentStrings(TheoryAnchor.access$000(this.this$1.this$0)));
                            }
                        }
                    };
                }
                
                @Override
                protected Statement methodInvoker(final FrameworkMethod frameworkMethod, final Object o) {
                    return TheoryAnchor.access$100(this.this$0, frameworkMethod, this.val$complete, o);
                }
                
                public Object createTest() throws Exception {
                    return this.getTestClass().getOnlyConstructor().newInstance(this.val$complete.getConstructorArguments(TheoryAnchor.access$000(this.this$0)));
                }
            }.methodBlock(this.fTestMethod).evaluate();
        }
        
        private Statement methodCompletesWithParameters(final FrameworkMethod frameworkMethod, final Assignments assignments, final Object o) {
            return new Statement(assignments, frameworkMethod, o) {
                final Assignments val$complete;
                final FrameworkMethod val$method;
                final Object val$freshInstance;
                final TheoryAnchor this$0;
                
                @Override
                public void evaluate() throws Throwable {
                    try {
                        this.val$method.invokeExplosively(this.val$freshInstance, this.val$complete.getMethodArguments(TheoryAnchor.access$000(this.this$0)));
                    }
                    catch (PotentialAssignment.CouldNotGenerateValueException ex) {}
                }
            };
        }
        
        protected void handleAssumptionViolation(final AssumptionViolatedException ex) {
            this.fInvalidParameters.add(ex);
        }
        
        protected void reportParameterizedError(final Throwable t, final Object... array) throws Throwable {
            if (array.length == 0) {
                throw t;
            }
            throw new ParameterizedAssertionError(t, this.fTestMethod.getName(), array);
        }
        
        private boolean nullsOk() {
            final Theory theory = this.fTestMethod.getMethod().getAnnotation(Theory.class);
            return theory != null && theory.nullsAccepted();
        }
        
        protected void handleDataPointSuccess() {
            ++this.successes;
        }
        
        static boolean access$000(final TheoryAnchor theoryAnchor) {
            return theoryAnchor.nullsOk();
        }
        
        static Statement access$100(final TheoryAnchor theoryAnchor, final FrameworkMethod frameworkMethod, final Assignments assignments, final Object o) {
            return theoryAnchor.methodCompletesWithParameters(frameworkMethod, assignments, o);
        }
    }
}
