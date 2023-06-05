//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events.impl;

import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;

@Cancelable
public class StepEvent extends Event
{
    private float height;
    private final AxisAlignedBB axisAlignedBB;
    
    public StepEvent(final AxisAlignedBB axisAlignedBB, final float height) {
        this.axisAlignedBB = axisAlignedBB;
        this.height = height;
    }
    
    public AxisAlignedBB getAxisAlignedBB() {
        return this.axisAlignedBB;
    }
    
    public float getHeight() {
        return this.height;
    }
    
    public void setHeight(final float height) {
        this.height = height;
    }
}
