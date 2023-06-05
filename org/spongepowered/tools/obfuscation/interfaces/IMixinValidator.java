//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.interfaces;

import javax.lang.model.element.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import java.util.*;

public interface IMixinValidator
{
    boolean validate(final ValidationPass p0, final TypeElement p1, final AnnotationHandle p2, final Collection p3);
    
    public enum ValidationPass
    {
        EARLY("EARLY", 0), 
        LATE("LATE", 1), 
        FINAL("FINAL", 2);
        
        private static final ValidationPass[] $VALUES;
        
        private ValidationPass(final String s, final int n) {
        }
        
        static {
            $VALUES = new ValidationPass[] { ValidationPass.EARLY, ValidationPass.LATE, ValidationPass.FINAL };
        }
    }
}
