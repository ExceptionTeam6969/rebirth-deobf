//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners.statements;

import org.junit.runners.model.*;
import org.junit.internal.*;

public class ExpectException extends Statement
{
    private Statement fNext;
    private final Class fExpected;
    
    public ExpectException(final Statement fNext, final Class fExpected) {
        this.fNext = fNext;
        this.fExpected = fExpected;
    }
    
    @Override
    public void evaluate() throws Exception {
        boolean b = false;
        try {
            this.fNext.evaluate();
            b = true;
        }
        catch (AssumptionViolatedException ex) {
            throw ex;
        }
        catch (Throwable t) {
            if (!this.fExpected.isAssignableFrom(t.getClass())) {
                throw new Exception("Unexpected exception, expected<" + this.fExpected.getName() + "> but was<" + t.getClass().getName() + ">", t);
            }
        }
        if (b) {
            throw new AssertionError((Object)("Expected exception: " + this.fExpected.getName()));
        }
    }
}
