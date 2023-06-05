//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.service.mojang;

import org.spongepowered.asm.service.*;
import net.minecraft.launchwrapper.*;

public class Blackboard implements IGlobalPropertyService
{
    public final Object getProperty(final String s) {
        return Launch.blackboard.get(s);
    }
    
    public final void setProperty(final String s, final Object o) {
        Launch.blackboard.put(s, o);
    }
    
    public final Object getProperty(final String s, final Object o) {
        final Object value = Launch.blackboard.get(s);
        return (value != null) ? value : o;
    }
    
    public final String getPropertyString(final String s, final String s2) {
        final Object value = Launch.blackboard.get(s);
        return (value != null) ? value.toString() : s2;
    }
}
