//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.util.asm;

import org.spongepowered.asm.lib.tree.analysis.*;
import org.spongepowered.asm.lib.*;
import java.util.*;
import org.spongepowered.asm.mixin.transformer.*;

public class MixinVerifier extends SimpleVerifier
{
    private Type currentClass;
    private Type currentSuperClass;
    private List currentClassInterfaces;
    private boolean isInterface;
    
    public MixinVerifier(final Type currentClass, final Type currentSuperClass, final List currentClassInterfaces, final boolean isInterface) {
        super(currentClass, currentSuperClass, currentClassInterfaces, isInterface);
        this.currentClass = currentClass;
        this.currentSuperClass = currentSuperClass;
        this.currentClassInterfaces = currentClassInterfaces;
        this.isInterface = isInterface;
    }
    
    protected boolean isInterface(final Type type) {
        if (this.currentClass != null && type.equals((Object)this.currentClass)) {
            return this.isInterface;
        }
        return ClassInfo.forType(type).isInterface();
    }
    
    protected Type getSuperClass(final Type type) {
        if (this.currentClass != null && type.equals((Object)this.currentClass)) {
            return this.currentSuperClass;
        }
        final ClassInfo superClass = ClassInfo.forType(type).getSuperClass();
        return (superClass == null) ? null : Type.getType("L" + superClass.getName() + ";");
    }
}
