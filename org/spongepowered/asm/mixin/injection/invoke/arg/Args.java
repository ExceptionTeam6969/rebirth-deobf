//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.invoke.arg;

public abstract class Args
{
    protected final Object[] values;
    
    protected Args(final Object[] values) {
        this.values = values;
    }
    
    public int size() {
        return this.values.length;
    }
    
    public Object get(final int n) {
        return this.values[n];
    }
    
    public abstract void set(final int p0, final Object p1);
    
    public abstract void setAll(final Object... p0);
}
