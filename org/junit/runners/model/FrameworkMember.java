//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.runners.model;

import java.lang.annotation.*;
import java.util.*;

abstract class FrameworkMember
{
    abstract Annotation[] getAnnotations();
    
    abstract boolean isShadowedBy(final FrameworkMember p0);
    
    boolean isShadowedBy(final List list) {
        final Iterator<FrameworkMember> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (this.isShadowedBy(iterator.next())) {
                return true;
            }
        }
        return false;
    }
}
