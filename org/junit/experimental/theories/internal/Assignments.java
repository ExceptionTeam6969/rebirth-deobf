//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.experimental.theories.internal;

import org.junit.runners.model.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.experimental.theories.*;

public class Assignments
{
    private List fAssigned;
    private final List fUnassigned;
    private final TestClass fClass;
    
    private Assignments(final List fAssigned, final List fUnassigned, final TestClass fClass) {
        this.fUnassigned = fUnassigned;
        this.fAssigned = fAssigned;
        this.fClass = fClass;
    }
    
    public static Assignments allUnassigned(final Method method, final TestClass testClass) throws Exception {
        final List signatures = ParameterSignature.signatures(testClass.getOnlyConstructor());
        signatures.addAll(ParameterSignature.signatures(method));
        return new Assignments(new ArrayList(), signatures, testClass);
    }
    
    public boolean isComplete() {
        return this.fUnassigned.size() == 0;
    }
    
    public ParameterSignature nextUnassigned() {
        return this.fUnassigned.get(0);
    }
    
    public Assignments assignNext(final PotentialAssignment potentialAssignment) {
        final ArrayList<PotentialAssignment> list = new ArrayList<PotentialAssignment>(this.fAssigned);
        list.add(potentialAssignment);
        return new Assignments(list, this.fUnassigned.subList(1, this.fUnassigned.size()), this.fClass);
    }
    
    public Object[] getActualValues(final int n, final int n2, final boolean b) throws PotentialAssignment.CouldNotGenerateValueException {
        final Object[] array = new Object[n2 - n];
        for (int i = n; i < n2; ++i) {
            final Object value = this.fAssigned.get(i).getValue();
            if (value == null && !b) {
                throw new PotentialAssignment.CouldNotGenerateValueException();
            }
            array[i - n] = value;
        }
        return array;
    }
    
    public List potentialsForNextUnassigned() throws InstantiationException, IllegalAccessException {
        final ParameterSignature nextUnassigned = this.nextUnassigned();
        return this.getSupplier(nextUnassigned).getValueSources(nextUnassigned);
    }
    
    public ParameterSupplier getSupplier(final ParameterSignature parameterSignature) throws InstantiationException, IllegalAccessException {
        final ParameterSupplier annotatedSupplier = this.getAnnotatedSupplier(parameterSignature);
        if (annotatedSupplier != null) {
            return annotatedSupplier;
        }
        return (ParameterSupplier)new AllMembersSupplier(this.fClass);
    }
    
    public ParameterSupplier getAnnotatedSupplier(final ParameterSignature parameterSignature) throws InstantiationException, IllegalAccessException {
        final ParametersSuppliedBy parametersSuppliedBy = (ParametersSuppliedBy)parameterSignature.findDeepAnnotation(ParametersSuppliedBy.class);
        if (parametersSuppliedBy == null) {
            return null;
        }
        return (ParameterSupplier)parametersSuppliedBy.value().newInstance();
    }
    
    public Object[] getConstructorArguments(final boolean b) throws PotentialAssignment.CouldNotGenerateValueException {
        return this.getActualValues(0, this.getConstructorParameterCount(), b);
    }
    
    public Object[] getMethodArguments(final boolean b) throws PotentialAssignment.CouldNotGenerateValueException {
        return this.getActualValues(this.getConstructorParameterCount(), this.fAssigned.size(), b);
    }
    
    public Object[] getAllArguments(final boolean b) throws PotentialAssignment.CouldNotGenerateValueException {
        return this.getActualValues(0, this.fAssigned.size(), b);
    }
    
    private int getConstructorParameterCount() {
        return ParameterSignature.signatures(this.fClass.getOnlyConstructor()).size();
    }
    
    public Object[] getArgumentStrings(final boolean b) throws PotentialAssignment.CouldNotGenerateValueException {
        final Object[] array = new Object[this.fAssigned.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = ((PotentialAssignment)this.fAssigned.get(i)).getDescription();
        }
        return array;
    }
}
