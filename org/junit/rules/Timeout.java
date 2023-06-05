//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.rules;

import org.junit.runners.model.*;
import org.junit.runner.*;
import org.junit.internal.runners.statements.*;

public class Timeout implements TestRule
{
    private final int fMillis;
    
    public Timeout(final int fMillis) {
        this.fMillis = fMillis;
    }
    
    public Statement apply(final Statement statement, final Description description) {
        return (Statement)new FailOnTimeout(statement, (long)this.fMillis);
    }
}
