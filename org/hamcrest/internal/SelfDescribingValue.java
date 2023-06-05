//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.hamcrest.internal;

import org.hamcrest.*;

public class SelfDescribingValue implements SelfDescribing
{
    private Object value;
    
    public SelfDescribingValue(final Object value) {
        this.value = value;
    }
    
    public void describeTo(final Description description) {
        description.appendValue(this.value);
    }
}
