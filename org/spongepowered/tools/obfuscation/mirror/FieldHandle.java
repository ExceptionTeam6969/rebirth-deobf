//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.mirror;

import javax.lang.model.element.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;
import com.google.common.base.*;
import org.spongepowered.asm.obfuscation.mapping.*;

public class FieldHandle extends MemberHandle
{
    private final VariableElement element;
    private final boolean rawType;
    
    public FieldHandle(final TypeElement typeElement, final VariableElement variableElement) {
        this(TypeUtils.getInternalName(typeElement), variableElement);
    }
    
    public FieldHandle(final String s, final VariableElement variableElement) {
        this(s, variableElement, false);
    }
    
    public FieldHandle(final TypeElement typeElement, final VariableElement variableElement, final boolean b) {
        this(TypeUtils.getInternalName(typeElement), variableElement, b);
    }
    
    public FieldHandle(final String s, final VariableElement variableElement, final boolean b) {
        this(s, variableElement, b, TypeUtils.getName(variableElement), TypeUtils.getInternalName(variableElement));
    }
    
    public FieldHandle(final String s, final String s2, final String s3) {
        this(s, null, false, s2, s3);
    }
    
    private FieldHandle(final String s, final VariableElement element, final boolean rawType, final String s2, final String s3) {
        super(s, s2, s3);
        this.element = element;
        this.rawType = rawType;
    }
    
    public boolean isImaginary() {
        return this.element == null;
    }
    
    public VariableElement getElement() {
        return this.element;
    }
    
    @Override
    public Visibility getVisibility() {
        return TypeUtils.getVisibility(this.element);
    }
    
    public boolean isRawType() {
        return this.rawType;
    }
    
    public MappingField asMapping(final boolean b) {
        return new MappingField(b ? this.getOwner() : null, this.getName(), this.getDesc());
    }
    
    @Override
    public String toString() {
        return String.format("%s%s:%s", (this.getOwner() != null) ? ("L" + this.getOwner() + ";") : "", Strings.nullToEmpty(this.getName()), Strings.nullToEmpty(this.getDesc()));
    }
    
    @Override
    public IMapping asMapping(final boolean b) {
        return (IMapping)this.asMapping(b);
    }
}
