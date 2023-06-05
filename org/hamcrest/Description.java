//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.hamcrest;

public interface Description
{
    Description appendText(final String p0);
    
    Description appendDescriptionOf(final SelfDescribing p0);
    
    Description appendValue(final Object p0);
    
    Description appendValueList(final String p0, final String p1, final String p2, final Object... p3);
    
    Description appendValueList(final String p0, final String p1, final String p2, final Iterable p3);
    
    Description appendList(final String p0, final String p1, final String p2, final Iterable p3);
}
