//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.mirror;

public enum Visibility
{
    PRIVATE("PRIVATE", 0), 
    PROTECTED("PROTECTED", 1), 
    PACKAGE("PACKAGE", 2), 
    PUBLIC("PUBLIC", 3);
    
    private static final Visibility[] $VALUES;
    
    private Visibility(final String s, final int n) {
    }
    
    static {
        $VALUES = new Visibility[] { Visibility.PRIVATE, Visibility.PROTECTED, Visibility.PACKAGE, Visibility.PUBLIC };
    }
}
