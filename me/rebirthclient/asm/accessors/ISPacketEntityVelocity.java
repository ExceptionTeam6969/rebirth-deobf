//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn��ǿ��������\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.accessors;

import org.spongepowered.asm.mixin.*;
import net.minecraft.network.play.server.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ SPacketEntityVelocity.class })
public interface ISPacketEntityVelocity
{
    @Accessor("entityID")
    int getEntityID();
    
    @Accessor("motionX")
    int getX();
    
    @Accessor("motionX")
    void setX(final int p0);
    
    @Accessor("motionY")
    int getY();
    
    @Accessor("motionY")
    void setY(final int p0);
    
    @Accessor("motionZ")
    int getZ();
    
    @Accessor("motionZ")
    void setZ(final int p0);
}
