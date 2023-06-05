//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.rules;

import java.util.*;
import org.junit.runners.model.*;
import org.hamcrest.*;
import java.util.concurrent.*;
import org.junit.*;

public class ErrorCollector extends Verifier
{
    private List errors;
    
    public ErrorCollector() {
        this.errors = new ArrayList();
    }
    
    @Override
    protected void verify() throws Throwable {
        MultipleFailureException.assertEmpty(this.errors);
    }
    
    public void addError(final Throwable t) {
        this.errors.add(t);
    }
    
    public void checkThat(final Object o, final Matcher matcher) {
        this.checkThat("", o, matcher);
    }
    
    public void checkThat(final String s, final Object o, final Matcher matcher) {
        this.checkSucceeds(new Callable(s, o, matcher) {
            final String val$reason;
            final Object val$value;
            final Matcher val$matcher;
            final ErrorCollector this$0;
            
            public Object call() throws Exception {
                Assert.assertThat(this.val$reason, this.val$value, this.val$matcher);
                return this.val$value;
            }
        });
    }
    
    public Object checkSucceeds(final Callable callable) {
        try {
            return callable.call();
        }
        catch (Throwable t) {
            this.addError(t);
            return null;
        }
    }
}
