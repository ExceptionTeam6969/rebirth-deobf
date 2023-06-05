//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.accessors;

import org.spongepowered.asm.mixin.*;
import java.util.*;
import org.spongepowered.asm.mixin.gen.*;
import net.minecraft.client.shader.*;

@Mixin({ ShaderGroup.class })
public interface IShaderGroup
{
    @Accessor("listShaders")
    List getListShaders();
    
    @Accessor("mainFramebuffer")
    Framebuffer getMainFramebuffer();
}
