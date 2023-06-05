//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events.impl;

import me.rebirthclient.api.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;

@Cancelable
public class MoveEvent extends Event
{
    private MoverType type;
    private double z;
    private double x;
    private double y;
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public void setType(final MoverType type) {
        this.type = type;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public double getX() {
        return this.x;
    }
    
    public MoverType getType() {
        return this.type;
    }
    
    public MoveEvent(final int n, final MoverType type, final double x, final double y, final double z) {
        super(n);
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
    
    public double getY() {
        return this.y;
    }
}
