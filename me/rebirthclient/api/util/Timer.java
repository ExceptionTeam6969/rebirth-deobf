//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util;

public class Timer
{
    boolean paused;
    private long time;
    
    public long getPassedTimeMs() {
        return this.getMs(System.nanoTime() - this.time);
    }
    
    public void setMs(final long n) {
        this.time = System.nanoTime() - this.convertToNS(n);
    }
    
    public void setPaused(final boolean paused) {
        this.paused = paused;
    }
    
    public Timer reset() {
        this.time = System.nanoTime();
        return this;
    }
    
    public boolean isPaused() {
        return this.paused;
    }
    
    public Timer() {
        this.time = -1L;
    }
    
    public boolean passedMs(final long p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        me/rebirthclient/api/util/Timer.paused:Z
        //     4: ifne            20
        //     7: aload_0        
        //     8: aload_0        
        //     9: lload_1        
        //    10: invokevirtual   me/rebirthclient/api/util/Timer.convertToNS:(J)J
        //    13: ifeq            20
        //    16: iconst_1       
        //    17: goto            21
        //    20: iconst_0       
        //    21: ireturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0020 (coming from #0013).
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2183)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.lang.Thread.run(Thread.java:750)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean passed(final long n, final boolean b) {
        if (b) {
            this.reset();
        }
        return System.currentTimeMillis() - this.time >= n;
    }
    
    public long convertToNS(final long n) {
        return n * 1000000L;
    }
    
    public boolean passedDms(final double n) {
        return this.passedMs((long)n * 10L);
    }
    
    public boolean passedDs(final double n) {
        return this.passedMs((long)n * 100L);
    }
    
    public long getMs(final long n) {
        return n / 1000000L;
    }
    
    public final boolean passed(final long n) {
        return this.passed(n, false);
    }
    
    public boolean passedS(final double n) {
        return this.passedMs((long)n * 1000L);
    }
}
