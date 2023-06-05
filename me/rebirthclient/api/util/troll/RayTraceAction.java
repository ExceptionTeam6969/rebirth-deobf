//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.troll;

import net.minecraft.util.math.*;

public abstract class RayTraceAction
{
    private RayTraceAction() {
    }
    
    RayTraceAction(final RayTraceAction$1 object) {
        this();
    }
    
    public static final class Skip extends RayTraceAction
    {
        public static final Skip INSTANCE;
        
        private Skip() {
            super(null);
        }
        
        static {
            INSTANCE = new Skip();
        }
    }
    
    public static final class Null extends RayTraceAction
    {
        public static final Null INSTANCE;
        
        static {
            INSTANCE = new Null();
        }
        
        private Null() {
            super(null);
        }
    }
    
    public static final class Result extends RayTraceAction
    {
        private final RayTraceResult rayTraceResult;
        
        public RayTraceResult getRayTraceResult() {
            return this.rayTraceResult;
        }
        
        public Result(final RayTraceResult rayTraceResult) {
            super(null);
            this.rayTraceResult = rayTraceResult;
        }
    }
    
    public static final class Calc extends RayTraceAction
    {
        public static final Calc INSTANCE;
        
        static {
            INSTANCE = new Calc();
        }
        
        private Calc() {
            super(null);
        }
    }
}
