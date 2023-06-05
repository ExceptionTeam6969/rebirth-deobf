//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.rules;

import org.junit.runner.*;

public class TestName extends TestWatcher
{
    private String fName;
    
    @Override
    protected void starting(final Description description) {
        this.fName = description.getMethodName();
    }
    
    public String getMethodName() {
        return this.fName;
    }
}
