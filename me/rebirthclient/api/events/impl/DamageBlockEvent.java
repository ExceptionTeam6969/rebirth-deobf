//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events.impl;

import me.rebirthclient.api.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;

@Cancelable
public class DamageBlockEvent extends Event
{
    final BlockPos pos;
    final int progress;
    final int breakerId;
    
    public DamageBlockEvent(final BlockPos pos, final int progress, final int breakerId) {
        this.pos = pos;
        this.progress = progress;
        this.breakerId = breakerId;
    }
    
    public int getProgress() {
        return this.progress;
    }
    
    public int getBreakerId() {
        return this.breakerId;
    }
    
    public BlockPos getPosition() {
        return this.pos;
    }
}
