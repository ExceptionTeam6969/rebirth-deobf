//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.launch;

import org.spongepowered.asm.launch.platform.*;
import org.spongepowered.asm.service.*;
import java.util.*;
import org.apache.logging.log4j.*;

public abstract class MixinBootstrap
{
    public static final String VERSION = "0.7.11";
    private static final Logger logger;
    private static boolean initialised;
    private static boolean initState;
    private static MixinPlatformManager platform;
    
    private MixinBootstrap() {
    }
    
    @Deprecated
    public static void addProxy() {
        MixinService.getService().beginPhase();
    }
    
    public static MixinPlatformManager getPlatform() {
        if (MixinBootstrap.platform == null) {
            final Object value = GlobalProperties.get("mixin.platform");
            if (value instanceof MixinPlatformManager) {
                MixinBootstrap.platform = (MixinPlatformManager)value;
            }
            else {
                GlobalProperties.put("mixin.platform", (Object)(MixinBootstrap.platform = new MixinPlatformManager()));
                MixinBootstrap.platform.init();
            }
        }
        return MixinBootstrap.platform;
    }
    
    public static void init() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: return         
        //     4: aconst_null    
        //     5: invokestatic    org/spongepowered/asm/launch/MixinBootstrap.doInit:(Ljava/util/List;)V
        //     8: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static void doInit(final List p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: ifne            30
        //     6: ifnull          20
        //     9: getstatic       org/spongepowered/asm/launch/MixinBootstrap.logger:Lorg/apache/logging/log4j/Logger;
        //    12: ldc             "Multiple Mixin containers present, init suppressed for 0.7.11"
        //    14: invokeinterface org/apache/logging/log4j/Logger.warn:(Ljava/lang/String;)V
        //    19: return         
        //    20: new             Ljava/lang/IllegalStateException;
        //    23: dup            
        //    24: ldc             "MixinBootstrap.doInit() called before MixinBootstrap.start()"
        //    26: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    29: athrow         
        //    30: invokestatic    org/spongepowered/asm/launch/MixinBootstrap.getPlatform:()Lorg/spongepowered/asm/launch/platform/MixinPlatformManager;
        //    33: invokevirtual   org/spongepowered/asm/launch/platform/MixinPlatformManager.getPhaseProviderClasses:()Ljava/util/Collection;
        //    36: pop            
        //    37: getstatic       org/spongepowered/asm/launch/MixinBootstrap.initState:Z
        //    40: ifeq            58
        //    43: invokestatic    org/spongepowered/asm/launch/MixinBootstrap.getPlatform:()Lorg/spongepowered/asm/launch/platform/MixinPlatformManager;
        //    46: aload_0        
        //    47: invokevirtual   org/spongepowered/asm/launch/platform/MixinPlatformManager.prepare:(Ljava/util/List;)V
        //    50: invokestatic    org/spongepowered/asm/service/MixinService.getService:()Lorg/spongepowered/asm/service/IMixinService;
        //    53: invokeinterface org/spongepowered/asm/service/IMixinService.init:()V
        //    58: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.ArrayIndexOutOfBoundsException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static void inject() {
        getPlatform().inject();
    }
    
    private static boolean checkSubsystemVersion() {
        return "0.7.11".equals(getActiveSubsystemVersion());
    }
    
    private static Object getActiveSubsystemVersion() {
        final Object value = GlobalProperties.get("mixin.initialised");
        return (value != null) ? value : "";
    }
    
    private static void registerSubsystem(final String s) {
        GlobalProperties.put("mixin.initialised", (Object)s);
    }
    
    static {
        logger = LogManager.getLogger("mixin");
        MixinBootstrap.initialised = false;
        MixinBootstrap.initState = true;
        MixinService.boot();
        MixinService.getService().prepare();
    }
}
