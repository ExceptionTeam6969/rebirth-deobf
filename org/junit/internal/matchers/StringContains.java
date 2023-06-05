//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal.matchers;

import org.hamcrest.*;

public class StringContains extends SubstringMatcher
{
    public StringContains(final String s) {
        super(s);
    }
    
    @Override
    protected boolean evalSubstringOf(final String s) {
        return s.indexOf(this.substring) >= 0;
    }
    
    @Override
    protected String relationship() {
        return "containing";
    }
    
    @Factory
    public static Matcher containsString(final String s) {
        return new StringContains(s);
    }
}
