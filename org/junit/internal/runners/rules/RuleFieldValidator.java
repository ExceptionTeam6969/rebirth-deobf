//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners.rules;

import org.junit.runners.model.*;
import java.util.*;
import org.junit.rules.*;
import org.junit.*;

public enum RuleFieldValidator
{
    CLASS_RULE_VALIDATOR("CLASS_RULE_VALIDATOR", 0, (Class)ClassRule.class, true), 
    RULE_VALIDATOR("RULE_VALIDATOR", 1, (Class)Rule.class, false);
    
    private final Class fAnnotation;
    private final boolean fOnlyStaticFields;
    private static final RuleFieldValidator[] $VALUES;
    
    private RuleFieldValidator(final String s, final int n, final Class fAnnotation, final boolean fOnlyStaticFields) {
        this.fAnnotation = fAnnotation;
        this.fOnlyStaticFields = fOnlyStaticFields;
    }
    
    public void validate(final TestClass testClass, final List list) {
        final Iterator<FrameworkField> iterator = testClass.getAnnotatedFields(this.fAnnotation).iterator();
        while (iterator.hasNext()) {
            this.validateField(iterator.next(), list);
        }
    }
    
    private void validateField(final FrameworkField frameworkField, final List list) {
        this.optionallyValidateStatic(frameworkField, list);
        this.validatePublic(frameworkField, list);
        this.validateTestRuleOrMethodRule(frameworkField, list);
    }
    
    private void optionallyValidateStatic(final FrameworkField frameworkField, final List list) {
        if (this.fOnlyStaticFields && !frameworkField.isStatic()) {
            this.addError(list, frameworkField, "must be static.");
        }
    }
    
    private void validatePublic(final FrameworkField frameworkField, final List list) {
        if (!frameworkField.isPublic()) {
            this.addError(list, frameworkField, "must be public.");
        }
    }
    
    private void validateTestRuleOrMethodRule(final FrameworkField frameworkField, final List list) {
        if (!this.isMethodRule(frameworkField) && !this.isTestRule(frameworkField)) {
            this.addError(list, frameworkField, "must implement MethodRule or TestRule.");
        }
    }
    
    private boolean isTestRule(final FrameworkField frameworkField) {
        return TestRule.class.isAssignableFrom(frameworkField.getType());
    }
    
    private boolean isMethodRule(final FrameworkField frameworkField) {
        return MethodRule.class.isAssignableFrom(frameworkField.getType());
    }
    
    private void addError(final List list, final FrameworkField frameworkField, final String s) {
        list.add(new Exception("The @" + this.fAnnotation.getSimpleName() + " '" + frameworkField.getName() + "' " + s));
    }
    
    static {
        $VALUES = new RuleFieldValidator[] { RuleFieldValidator.CLASS_RULE_VALIDATOR, RuleFieldValidator.RULE_VALIDATOR };
    }
}
