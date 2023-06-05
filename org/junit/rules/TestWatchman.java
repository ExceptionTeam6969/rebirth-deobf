//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.rules;

import org.junit.runners.model.*;
import org.junit.internal.*;

@Deprecated
public class TestWatchman implements MethodRule
{
    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        return new Statement(frameworkMethod, statement) {
            final FrameworkMethod val$method;
            final Statement val$base;
            final TestWatchman this$0;
            
            @Override
            public void evaluate() throws Throwable {
                this.this$0.starting(this.val$method);
                try {
                    this.val$base.evaluate();
                    this.this$0.succeeded(this.val$method);
                    this.this$0.finished(this.val$method);
                }
                catch (AssumptionViolatedException ex) {
                    throw ex;
                }
                catch (Throwable t) {
                    this.this$0.failed(t, this.val$method);
                    throw t;
                }
            }
        };
    }
    
    public void succeeded(final FrameworkMethod frameworkMethod) {
    }
    
    public void failed(final Throwable t, final FrameworkMethod frameworkMethod) {
    }
    
    public void starting(final FrameworkMethod frameworkMethod) {
    }
    
    public void finished(final FrameworkMethod frameworkMethod) {
    }
}
