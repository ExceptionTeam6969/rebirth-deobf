//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.rules;

import org.junit.runners.model.*;
import org.junit.runner.*;
import java.util.*;

public class RunRules extends Statement
{
    private final Statement statement;
    
    public RunRules(final Statement statement, final Iterable iterable, final Description description) {
        this.statement = applyAll(statement, iterable, description);
    }
    
    @Override
    public void evaluate() throws Throwable {
        this.statement.evaluate();
    }
    
    private static Statement applyAll(Statement apply, final Iterable iterable, final Description description) {
        final Iterator<TestRule> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            apply = iterator.next().apply(apply, description);
        }
        return apply;
    }
}
