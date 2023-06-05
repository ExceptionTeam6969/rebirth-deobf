//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events.impl;

import me.rebirthclient.api.events.*;

public class JumpEvent extends Event
{
    public final double motionX;
    public final double motionY;
    
    public JumpEvent(final double motionX, final double motionY) {
        this.motionX = motionX;
        this.motionY = motionY;
    }
}
