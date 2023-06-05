//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.junit.experimental.theories;

public abstract class PotentialAssignment
{
    public static PotentialAssignment forValue(final String s, final Object o) {
        return new PotentialAssignment(o, s) {
            final Object val$value;
            final String val$name;
            
            @Override
            public Object getValue() throws CouldNotGenerateValueException {
                return this.val$value;
            }
            
            @Override
            public String toString() {
                return String.format("[%s]", this.val$value);
            }
            
            @Override
            public String getDescription() throws CouldNotGenerateValueException {
                return this.val$name;
            }
        };
    }
    
    public abstract Object getValue() throws CouldNotGenerateValueException;
    
    public abstract String getDescription() throws CouldNotGenerateValueException;
    
    public static class CouldNotGenerateValueException extends Exception
    {
        private static final long serialVersionUID = 1L;
    }
}
