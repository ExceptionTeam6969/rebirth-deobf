//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.accessors;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.shader.*;
import org.spongepowered.asm.mixin.gen.*;
import java.util.*;

@Mixin({ RenderGlobal.class })
public interface IRenderGlobal
{
    @Accessor("entityOutlineShader")
    ShaderGroup getEntityOutlineShader();
    
    @Accessor("damagedBlocks")
    Map getDamagedBlocks();
}
