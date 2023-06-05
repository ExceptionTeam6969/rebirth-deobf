//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events.impl;

import me.rebirthclient.api.events.*;
import net.minecraft.util.math.*;

public class BreakBlockEvent extends Event
{
    BlockPos pos;
    
    public BlockPos getPos() {
        return this.pos;
    }
    
    public BreakBlockEvent(final BlockPos pos) {
        this.pos = pos;
    }
    
    public void setPos(final BlockPos pos) {
        this.pos = pos;
    }
}
