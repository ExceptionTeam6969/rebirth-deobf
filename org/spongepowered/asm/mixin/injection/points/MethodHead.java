//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.points;

import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.lib.tree.*;
import java.util.*;

@InjectionPoint.AtCode("HEAD")
public class MethodHead extends InjectionPoint
{
    public MethodHead(final InjectionPointData injectionPointData) {
        super(injectionPointData);
    }
    
    public boolean checkPriority(final int n, final int n2) {
        return true;
    }
    
    public boolean find(final String s, final InsnList list, final Collection collection) {
        collection.add(list.getFirst());
        return true;
    }
}
