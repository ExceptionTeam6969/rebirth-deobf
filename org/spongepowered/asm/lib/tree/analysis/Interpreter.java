//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.tree.analysis;

import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.lib.tree.*;
import java.util.*;

public abstract class Interpreter
{
    protected final int api;
    
    protected Interpreter(final int api) {
        this.api = api;
    }
    
    public abstract Value newValue(final Type p0);
    
    public abstract Value newOperation(final AbstractInsnNode p0) throws AnalyzerException;
    
    public abstract Value copyOperation(final AbstractInsnNode p0, final Value p1) throws AnalyzerException;
    
    public abstract Value unaryOperation(final AbstractInsnNode p0, final Value p1) throws AnalyzerException;
    
    public abstract Value binaryOperation(final AbstractInsnNode p0, final Value p1, final Value p2) throws AnalyzerException;
    
    public abstract Value ternaryOperation(final AbstractInsnNode p0, final Value p1, final Value p2, final Value p3) throws AnalyzerException;
    
    public abstract Value naryOperation(final AbstractInsnNode p0, final List p1) throws AnalyzerException;
    
    public abstract void returnOperation(final AbstractInsnNode p0, final Value p1, final Value p2) throws AnalyzerException;
    
    public abstract Value merge(final Value p0, final Value p1);
}
