//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import me.rebirthclient.mod.*;

public class TimerManager extends Mod
{
    public float timer;
    
    public void reset() {
        this.timer = 1.0f;
    }
    
    public TimerManager() {
        this.timer = 1.0f;
    }
    
    public float get() {
        return this.timer;
    }
    
    public void set(float timer) {
        if (timer < 0.1f) {
            timer = 0.1f;
        }
        this.timer = timer;
    }
}
