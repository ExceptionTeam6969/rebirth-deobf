//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events.impl;

import me.rebirthclient.api.events.*;
import net.minecraftforge.fml.common.eventhandler.*;

@Cancelable
public class TurnEvent extends Event
{
    private final float yaw;
    private final float pitch;
    
    public float getYaw() {
        return this.yaw;
    }
    
    public TurnEvent(final float yaw, final float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }
    
    public float getPitch() {
        return this.pitch;
    }
}
