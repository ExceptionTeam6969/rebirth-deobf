//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.rules;

import org.junit.runners.model.*;
import org.junit.runner.*;
import org.junit.internal.*;

public abstract class TestWatcher implements TestRule
{
    public Statement apply(final Statement statement, final Description description) {
        return new Statement(description, statement) {
            final Description val$description;
            final Statement val$base;
            final TestWatcher this$0;
            
            @Override
            public void evaluate() throws Throwable {
                this.this$0.starting(this.val$description);
                try {
                    this.val$base.evaluate();
                    this.this$0.succeeded(this.val$description);
                    this.this$0.finished(this.val$description);
                }
                catch (AssumptionViolatedException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    this.this$0.failed(t, this.val$description);
                    throw t;
                }
            }
        };
    }
    
    protected void succeeded(final Description description) {
    }
    
    protected void failed(final Throwable t, final Description description) {
    }
    
    protected void starting(final Description description) {
    }
    
    protected void finished(final Description description) {
    }
}
