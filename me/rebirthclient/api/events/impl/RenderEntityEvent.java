//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events.impl;

import me.rebirthclient.api.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;

@Cancelable
public class RenderEntityEvent extends Event
{
    private Entity entity;
    private float yaw;
    private double z;
    private double x;
    private float partialTicks;
    private double y;
    
    public void setPartialTicks(final float partialTicks) {
        this.partialTicks = partialTicks;
    }
    
    public double getX() {
        return this.x;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
    
    public void setEntity(final Entity entity) {
        this.entity = entity;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public RenderEntityEvent(final int n, final Entity entity, final double x, final double y, final double z, final float yaw, final float partialTicks) {
        super(n);
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.partialTicks = partialTicks;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public double getY() {
        return this.y;
    }
}
