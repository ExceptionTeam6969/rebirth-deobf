//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.runners.statements;

import org.junit.runners.model.*;
import java.util.*;

public class RunBefores extends Statement
{
    private final Statement fNext;
    private final Object fTarget;
    private final List fBefores;
    
    public RunBefores(final Statement fNext, final List fBefores, final Object fTarget) {
        this.fNext = fNext;
        this.fBefores = fBefores;
        this.fTarget = fTarget;
    }
    
    @Override
    public void evaluate() throws Throwable {
        final Iterator<FrameworkMethod> iterator = this.fBefores.iterator();
        while (iterator.hasNext()) {
            iterator.next().invokeExplosively(this.fTarget, new Object[0]);
        }
        this.fNext.evaluate();
    }
}
