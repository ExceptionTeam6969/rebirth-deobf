//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.callback;

public enum LocalCapture
{
    NO_CAPTURE("NO_CAPTURE", 0, false, false), 
    PRINT("PRINT", 1, false, true), 
    CAPTURE_FAILSOFT("CAPTURE_FAILSOFT", 2), 
    CAPTURE_FAILHARD("CAPTURE_FAILHARD", 3), 
    CAPTURE_FAILEXCEPTION("CAPTURE_FAILEXCEPTION", 4);
    
    private final boolean captureLocals;
    private final boolean printLocals;
    private static final LocalCapture[] $VALUES;
    
    private LocalCapture(final String s, final int n) {
        this(s, n, true, false);
    }
    
    private LocalCapture(final String s, final int n, final boolean captureLocals, final boolean printLocals) {
        this.captureLocals = captureLocals;
        this.printLocals = printLocals;
    }
    
    boolean isCaptureLocals() {
        return this.captureLocals;
    }
    
    boolean isPrintLocals() {
        return this.printLocals;
    }
    
    static {
        $VALUES = new LocalCapture[] { LocalCapture.NO_CAPTURE, LocalCapture.PRINT, LocalCapture.CAPTURE_FAILSOFT, LocalCapture.CAPTURE_FAILHARD, LocalCapture.CAPTURE_FAILEXCEPTION };
    }
}
