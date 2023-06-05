//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runners.model;

import java.lang.annotation.*;
import java.lang.reflect.*;

public class FrameworkField extends FrameworkMember
{
    private final Field fField;
    
    FrameworkField(final Field fField) {
        this.fField = fField;
    }
    
    public String getName() {
        return this.getField().getName();
    }
    
    public Annotation[] getAnnotations() {
        return this.fField.getAnnotations();
    }
    
    public boolean isPublic() {
        return Modifier.isPublic(this.fField.getModifiers());
    }
    
    public boolean isShadowedBy(final FrameworkField frameworkField) {
        return frameworkField.getName().equals(this.getName());
    }
    
    public boolean isStatic() {
        return Modifier.isStatic(this.fField.getModifiers());
    }
    
    public Field getField() {
        return this.fField;
    }
    
    public Class getType() {
        return this.fField.getType();
    }
    
    public Object get(final Object o) throws IllegalArgumentException, IllegalAccessException {
        return this.fField.get(o);
    }
    
    public boolean isShadowedBy(final FrameworkMember frameworkMember) {
        return this.isShadowedBy((FrameworkField)frameworkMember);
    }
}
