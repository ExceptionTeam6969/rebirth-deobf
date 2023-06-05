//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events.impl;

import me.rebirthclient.api.events.*;
import me.rebirthclient.api.util.*;

public class MotionEvent extends Event implements Wrapper
{
    protected float pitch;
    protected double z;
    protected double x;
    public static MotionEvent INSTANCE;
    protected double y;
    protected boolean onGround;
    protected float yaw;
    
    public boolean isOnGround() {
        return this.onGround;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setYaw(final double n) {
        this.yaw = (float)n;
    }
    
    public void setRotation(final float yaw, final float pitch) {
        this.setYaw(yaw);
        this.setPitch(pitch);
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public double getX() {
        return this.x;
    }
    
    public MotionEvent(final int n, final MotionEvent motionEvent) {
        this(n, motionEvent.x, motionEvent.y, motionEvent.z, motionEvent.yaw, motionEvent.pitch, motionEvent.onGround);
    }
    
    public void setPostion(final double x, final double y, final double z, final float yaw, final float pitch, final boolean onGround) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
        this.setYaw(yaw);
        this.setPitch(pitch);
        this.setOnGround(onGround);
    }
    
    public void setPitch(final double n) {
        this.pitch = (float)n;
    }
    
    public void setOnGround(final boolean onGround) {
        this.onGround = onGround;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setPostion(final double x, final double y, final double z, final boolean onGround) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
        this.setOnGround(onGround);
    }
    
    public double getZ() {
        return this.z;
    }
    
    public MotionEvent(final int n, final double x, final double y, final double z, final float yaw, final float pitch, final boolean onGround) {
        super(n);
        MotionEvent.INSTANCE = this;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.onGround = onGround;
    }
}
