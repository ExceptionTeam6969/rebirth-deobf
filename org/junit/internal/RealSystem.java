//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.internal;

import java.io.*;

public class RealSystem implements JUnitSystem
{
    public void exit(final int n) {
        System.exit(n);
    }
    
    public PrintStream out() {
        return System.out;
    }
}
