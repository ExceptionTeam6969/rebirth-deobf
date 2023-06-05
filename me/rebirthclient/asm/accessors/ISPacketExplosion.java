//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.accessors;

import org.spongepowered.asm.mixin.*;
import net.minecraft.network.play.server.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ SPacketExplosion.class })
public interface ISPacketExplosion
{
    @Accessor("motionX")
    float getX();
    
    @Accessor("motionX")
    void setX(final float p0);
    
    @Accessor("motionY")
    float getY();
    
    @Accessor("motionY")
    void setY(final float p0);
    
    @Accessor("motionZ")
    float getZ();
    
    @Accessor("motionZ")
    void setZ(final float p0);
}
