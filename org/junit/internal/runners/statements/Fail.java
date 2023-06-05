//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners.statements;

import org.junit.runners.model.*;

public class Fail extends Statement
{
    private final Throwable fError;
    
    public Fail(final Throwable fError) {
        this.fError = fError;
    }
    
    @Override
    public void evaluate() throws Throwable {
        throw this.fError;
    }
}
