//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin;

import org.spongepowered.asm.mixin.transformer.*;
import org.spongepowered.asm.launch.*;
import java.util.*;
import org.apache.logging.log4j.*;

public final class Mixins
{
    private static final Logger logger;
    private static final String CONFIGS_KEY = "mixin.configs.queue";
    private static final Set errorHandlers;
    
    private Mixins() {
    }
    
    public static void addConfigurations(final String... array) {
        final MixinEnvironment defaultEnvironment = MixinEnvironment.getDefaultEnvironment();
        for (int length = array.length, i = 0; i < length; ++i) {
            createConfiguration(array[i], defaultEnvironment);
        }
    }
    
    public static void addConfiguration(final String s) {
        createConfiguration(s, MixinEnvironment.getDefaultEnvironment());
    }
    
    @Deprecated
    static void addConfiguration(final String s, final MixinEnvironment mixinEnvironment) {
        createConfiguration(s, mixinEnvironment);
    }
    
    private static void createConfiguration(final String s, final MixinEnvironment mixinEnvironment) {
        Config create = null;
        try {
            create = Config.create(s, mixinEnvironment);
        }
        catch (Exception ex) {
            Mixins.logger.error("Error encountered reading mixin config " + s + ": " + ex.getClass().getName() + " " + ex.getMessage(), (Throwable)ex);
        }
        registerConfiguration(create);
    }
    
    private static void registerConfiguration(final Config config) {
        if (config == null) {
            return;
        }
        final MixinEnvironment environment = config.getEnvironment();
        if (environment != null) {
            environment.registerConfig(config.getName());
        }
        getConfigs().add(config);
    }
    
    public static int getUnvisitedCount() {
        int n = 0;
        final Iterator<Config> iterator = (Iterator<Config>)getConfigs().iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().isVisited()) {
                ++n;
            }
        }
        return n;
    }
    
    public static Set getConfigs() {
        Set set = (Set)GlobalProperties.get("mixin.configs.queue");
        if (set == null) {
            set = new LinkedHashSet();
            GlobalProperties.put("mixin.configs.queue", (Object)set);
        }
        return set;
    }
    
    public static void registerErrorHandlerClass(final String s) {
        if (s != null) {
            Mixins.errorHandlers.add(s);
        }
    }
    
    public static Set getErrorHandlerClasses() {
        return Collections.unmodifiableSet((Set<?>)Mixins.errorHandlers);
    }
    
    static {
        logger = LogManager.getLogger("mixin");
        errorHandlers = new LinkedHashSet();
    }
}
