//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.hamcrest;

import java.io.*;

public class StringDescription extends BaseDescription
{
    private final Appendable out;
    
    public StringDescription() {
        this(new StringBuilder());
    }
    
    public StringDescription(final Appendable out) {
        this.out = out;
    }
    
    public static String toString(final SelfDescribing selfDescribing) {
        return new StringDescription().appendDescriptionOf(selfDescribing).toString();
    }
    
    public static String asString(final SelfDescribing selfDescribing) {
        return toString(selfDescribing);
    }
    
    @Override
    protected void append(final String s) {
        try {
            this.out.append(s);
        }
        catch (IOException ex) {
            throw new RuntimeException("Could not write description", ex);
        }
    }
    
    @Override
    protected void append(final char c) {
        try {
            this.out.append(c);
        }
        catch (IOException ex) {
            throw new RuntimeException("Could not write description", ex);
        }
    }
    
    @Override
    public String toString() {
        return this.out.toString();
    }
}
