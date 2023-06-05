//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.obfuscation.mapping;

public interface IMapping
{
    Type getType();
    
    Object move(final String p0);
    
    Object remap(final String p0);
    
    Object transform(final String p0);
    
    Object copy();
    
    String getName();
    
    String getSimpleName();
    
    String getOwner();
    
    String getDesc();
    
    Object getSuper();
    
    String serialise();
    
    public enum Type
    {
        FIELD("FIELD", 0), 
        METHOD("METHOD", 1), 
        CLASS("CLASS", 2), 
        PACKAGE("PACKAGE", 3);
        
        private static final Type[] $VALUES;
        
        private Type(final String s, final int n) {
        }
        
        static {
            $VALUES = new Type[] { Type.FIELD, Type.METHOD, Type.CLASS, Type.PACKAGE };
        }
    }
}
