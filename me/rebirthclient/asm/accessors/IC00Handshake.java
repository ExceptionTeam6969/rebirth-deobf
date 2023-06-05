//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.accessors;

import org.spongepowered.asm.mixin.*;
import net.minecraft.network.handshake.client.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ C00Handshake.class })
public interface IC00Handshake
{
    @Accessor("ip")
    String getIp();
    
    @Accessor("ip")
    void setIp(final String p0);
}
