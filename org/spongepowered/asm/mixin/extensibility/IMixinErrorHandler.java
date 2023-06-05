//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.extensibility;

import org.apache.logging.log4j.*;

public interface IMixinErrorHandler
{
    ErrorAction onPrepareError(final IMixinConfig p0, final Throwable p1, final IMixinInfo p2, final ErrorAction p3);
    
    ErrorAction onApplyError(final String p0, final Throwable p1, final IMixinInfo p2, final ErrorAction p3);
    
    public enum ErrorAction
    {
        NONE("NONE", 0, Level.INFO), 
        WARN("WARN", 1, Level.WARN), 
        ERROR("ERROR", 2, Level.FATAL);
        
        public final Level logLevel;
        private static final ErrorAction[] $VALUES;
        
        private ErrorAction(final String s, final int n, final Level logLevel) {
            this.logLevel = logLevel;
        }
        
        static {
            $VALUES = new ErrorAction[] { ErrorAction.NONE, ErrorAction.WARN, ErrorAction.ERROR };
        }
    }
}
