//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface At {
    String id() default "";
    
    String value();
    
    String slice() default "";
    
    Shift shift() default Shift.NONE;
    
    int by() default 0;
    
    String[] args() default {};
    
    String target() default "";
    
    int ordinal() default -1;
    
    int opcode() default -1;
    
    boolean remap() default true;
    
    public enum Shift
    {
        NONE("NONE", 0), 
        BEFORE("BEFORE", 1), 
        AFTER("AFTER", 2), 
        BY("BY", 3);
        
        private static final Shift[] $VALUES;
        
        private Shift(final String s, final int n) {
        }
        
        static {
            $VALUES = new Shift[] { Shift.NONE, Shift.BEFORE, Shift.AFTER, Shift.BY };
        }
    }
}
