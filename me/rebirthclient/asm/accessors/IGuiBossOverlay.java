//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.accessors;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import java.util.*;
import net.minecraft.world.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ GuiBossOverlay.class })
public interface IGuiBossOverlay
{
    @Accessor("mapBossInfos")
    Map getMapBossInfos();
    
    @Invoker("render")
    void invokeRender(final int p0, final int p1, final BossInfo p2);
}
