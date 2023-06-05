//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import java.util.*;
import net.minecraft.client.*;

public final class FpsManager
{
    private final LinkedList frames;
    private int fps;
    
    public int getMcFPS() {
        return Minecraft.getDebugFPS();
    }
    
    public FpsManager() {
        this.frames = new LinkedList();
    }
    
    public void update() {
        final long nanoTime = System.nanoTime();
        this.frames.add(nanoTime);
        if (nanoTime - this.frames.getFirst() > 1000000000L) {
            this.frames.remove();
            return;
        }
        this.fps = this.frames.size();
    }
    
    public int getFPS() {
        return this.fps;
    }
    
    public float getFrametime() {
        return 0.004166667f;
    }
}
