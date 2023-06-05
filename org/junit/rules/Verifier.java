//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.rules;

import org.junit.runners.model.*;
import org.junit.runner.*;

public class Verifier implements TestRule
{
    public Statement apply(final Statement statement, final Description description) {
        return new Statement(statement) {
            final Statement val$base;
            final Verifier this$0;
            
            @Override
            public void evaluate() throws Throwable {
                this.val$base.evaluate();
                this.this$0.verify();
            }
        };
    }
    
    protected void verify() throws Throwable {
    }
}
