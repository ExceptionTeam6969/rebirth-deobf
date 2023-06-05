//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface Constant {
    boolean nullValue() default false;
    
    int intValue() default 0;
    
    float floatValue() default 0.0f;
    
    long longValue() default 0L;
    
    double doubleValue() default 0.0;
    
    String stringValue() default "";
    
    Class classValue() default Object.class;
    
    int ordinal() default -1;
    
    String slice() default "";
    
    Condition[] expandZeroConditions() default {};
    
    boolean log() default false;
    
    public enum Condition
    {
        LESS_THAN_ZERO("LESS_THAN_ZERO", 0, new int[] { 155, 156 }), 
        LESS_THAN_OR_EQUAL_TO_ZERO("LESS_THAN_OR_EQUAL_TO_ZERO", 1, new int[] { 158, 157 }), 
        GREATER_THAN_OR_EQUAL_TO_ZERO("GREATER_THAN_OR_EQUAL_TO_ZERO", 2, Condition.LESS_THAN_ZERO), 
        GREATER_THAN_ZERO("GREATER_THAN_ZERO", 3, Condition.LESS_THAN_OR_EQUAL_TO_ZERO);
        
        private final int[] opcodes;
        private final Condition equivalence;
        private static final Condition[] $VALUES;
        
        private Condition(final String s, final int n, final int... array) {
            this(s, n, null, array);
        }
        
        private Condition(final String s, final int n, final Condition condition) {
            this(s, n, condition, condition.opcodes);
        }
        
        private Condition(final String s, final int n, final Condition condition, final int... opcodes) {
            this.equivalence = ((condition != null) ? condition : this);
            this.opcodes = opcodes;
        }
        
        public Condition getEquivalentCondition() {
            return this.equivalence;
        }
        
        public int[] getOpcodes() {
            return this.opcodes;
        }
        
        static {
            $VALUES = new Condition[] { Condition.LESS_THAN_ZERO, Condition.LESS_THAN_OR_EQUAL_TO_ZERO, Condition.GREATER_THAN_OR_EQUAL_TO_ZERO, Condition.GREATER_THAN_ZERO };
        }
    }
}
