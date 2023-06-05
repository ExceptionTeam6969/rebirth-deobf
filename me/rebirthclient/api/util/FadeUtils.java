//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util;

public class FadeUtils
{
    protected long start;
    protected long length;
    
    public FadeUtils(final long length) {
        this.length = length;
        this.reset();
    }
    
    public void reset() {
        this.start = System.currentTimeMillis();
    }
    
    public double getFadeOutDefault() {
        return 1.0 - Math.tanh(this.getTime() / (double)this.length * 3.0);
    }
    
    public double def() {
        return (this != 0) ? 1.0 : this.getFadeOne();
    }
    
    private double getFadeOne() {
        return (this != 0) ? 1.0 : (this.getTime() / (double)this.length);
    }
    
    public double getEpsEzFadeOut() {
        return Math.sin(1.5707963267948966 * this.getFadeOne()) * Math.sin(2.5132741228718345 * this.getFadeOne());
    }
    
    public double easeOutQuad() {
        if (this.length == 0L) {
            return 1.0;
        }
        return 1.0 - (1.0 - this.getFadeOne()) * (1.0 - this.getFadeOne());
    }
    
    public void setLength(final long length) {
        this.length = length;
    }
    
    public double easeInQuad() {
        return this.getFadeOne() * this.getFadeOne();
    }
    
    public double getEpsEzFadeIn() {
        return 1.0 - Math.sin(1.5707963267948966 * this.getFadeOne()) * Math.sin(2.5132741228718345 * this.getFadeOne());
    }
    
    public double getFadeInDefault() {
        return Math.tanh(this.getTime() / (double)this.length * 3.0);
    }
    
    protected long getTime() {
        return System.currentTimeMillis() - this.start;
    }
}
