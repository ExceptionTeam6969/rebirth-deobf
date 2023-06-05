//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.rules;

import org.junit.runners.model.*;
import org.junit.runner.*;

public abstract class ExternalResource implements TestRule
{
    public Statement apply(final Statement statement, final Description description) {
        return this.statement(statement);
    }
    
    private Statement statement(final Statement statement) {
        return new Statement(statement) {
            final Statement val$base;
            final ExternalResource this$0;
            
            @Override
            public void evaluate() throws Throwable {
                this.this$0.before();
                this.val$base.evaluate();
                this.this$0.after();
            }
        };
    }
    
    protected void before() throws Throwable {
    }
    
    protected void after() {
    }
}
