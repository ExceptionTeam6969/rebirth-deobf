//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.CLASS)
public @interface Interface {
    Class iface();
    
    String prefix();
    
    boolean unique() default false;
    
    Remap remap() default Remap.ALL;
    
    public enum Remap
    {
        ALL("ALL", 0), 
        FORCE("FORCE", 1, true), 
        ONLY_PREFIXED("ONLY_PREFIXED", 2), 
        NONE("NONE", 3);
        
        private final boolean forceRemap;
        private static final Remap[] $VALUES;
        
        private Remap(final String s, final int n) {
            this(s, n, false);
        }
        
        private Remap(final String s, final int n, final boolean forceRemap) {
            this.forceRemap = forceRemap;
        }
        
        public boolean forceRemap() {
            return this.forceRemap;
        }
        
        static {
            $VALUES = new Remap[] { Remap.ALL, Remap.FORCE, Remap.ONLY_PREFIXED, Remap.NONE };
        }
    }
}
