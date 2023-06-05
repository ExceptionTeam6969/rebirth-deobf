//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.experimental.theories.internal;

import org.junit.runners.model.*;
import java.util.*;
import org.junit.experimental.theories.*;
import java.lang.reflect.*;

public class AllMembersSupplier extends ParameterSupplier
{
    private final TestClass fClass;
    
    public AllMembersSupplier(final TestClass fClass) {
        this.fClass = fClass;
    }
    
    @Override
    public List getValueSources(final ParameterSignature parameterSignature) {
        final ArrayList list = new ArrayList();
        this.addFields(parameterSignature, list);
        this.addSinglePointMethods(parameterSignature, list);
        this.addMultiPointMethods(list);
        return list;
    }
    
    private void addMultiPointMethods(final List list) {
        for (final FrameworkMethod frameworkMethod : this.fClass.getAnnotatedMethods(DataPoints.class)) {
            try {
                this.addArrayValues(frameworkMethod.getName(), list, frameworkMethod.invokeExplosively(null, new Object[0]));
            }
            catch (Throwable t) {}
        }
    }
    
    private void addSinglePointMethods(final ParameterSignature parameterSignature, final List list) {
        for (final FrameworkMethod frameworkMethod : this.fClass.getAnnotatedMethods(DataPoint.class)) {
            if (frameworkMethod.producesType(parameterSignature.getType())) {
                list.add(new MethodParameterValue(frameworkMethod, null));
            }
        }
    }
    
    private void addFields(final ParameterSignature parameterSignature, final List list) {
        for (final Field field : this.fClass.getJavaClass().getFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                final Class<?> type = field.getType();
                if (parameterSignature.canAcceptArrayType(type) && field.getAnnotation(DataPoints.class) != null) {
                    this.addArrayValues(field.getName(), list, this.getStaticFieldValue(field));
                }
                else if (parameterSignature.canAcceptType(type) && field.getAnnotation(DataPoint.class) != null) {
                    list.add(PotentialAssignment.forValue(field.getName(), this.getStaticFieldValue(field)));
                }
            }
        }
    }
    
    private void addArrayValues(final String s, final List list, final Object o) {
        for (int i = 0; i < Array.getLength(o); ++i) {
            list.add(PotentialAssignment.forValue(s + "[" + i + "]", Array.get(o, i)));
        }
    }
    
    private Object getStaticFieldValue(final Field field) {
        try {
            return field.get(null);
        }
        catch (IllegalArgumentException ex) {
            throw new RuntimeException("unexpected: field from getClass doesn't exist on object");
        }
        catch (IllegalAccessException ex2) {
            throw new RuntimeException("unexpected: getFields returned an inaccessible field");
        }
    }
    
    static class MethodParameterValue extends PotentialAssignment
    {
        private final FrameworkMethod fMethod;
        
        private MethodParameterValue(final FrameworkMethod fMethod) {
            this.fMethod = fMethod;
        }
        
        @Override
        public Object getValue() throws CouldNotGenerateValueException {
            try {
                return this.fMethod.invokeExplosively(null, new Object[0]);
            }
            catch (IllegalArgumentException ex) {
                throw new RuntimeException("unexpected: argument length is checked");
            }
            catch (IllegalAccessException ex2) {
                throw new RuntimeException("unexpected: getMethods returned an inaccessible method");
            }
            catch (Throwable t) {
                throw new CouldNotGenerateValueException();
            }
        }
        
        @Override
        public String getDescription() throws CouldNotGenerateValueException {
            return this.fMethod.getName();
        }
        
        MethodParameterValue(final FrameworkMethod frameworkMethod, final AllMembersSupplier$1 object) {
            this(frameworkMethod);
        }
    }
}
