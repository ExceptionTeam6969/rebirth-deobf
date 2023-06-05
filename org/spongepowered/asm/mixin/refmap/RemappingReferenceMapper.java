//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.refmap;

import org.spongepowered.asm.mixin.*;
import java.util.*;
import com.google.common.base.*;
import java.io.*;
import com.google.common.io.*;
import org.apache.logging.log4j.*;

public final class RemappingReferenceMapper implements IReferenceMapper
{
    private static final String DEFAULT_RESOURCE_PATH_PROPERTY = "net.minecraftforge.gradle.GradleStart.srg.srg-mcp";
    private static final String DEFAULT_MAPPING_ENV = "searge";
    private static final Logger logger;
    private static final Map srgs;
    private final IReferenceMapper refMap;
    private final Map mappings;
    private final Map cache;
    
    private RemappingReferenceMapper(final MixinEnvironment mixinEnvironment, final IReferenceMapper refMap) {
        this.cache = new HashMap();
        (this.refMap = refMap).setContext(getMappingEnv(mixinEnvironment));
        final String resource = getResource(mixinEnvironment);
        this.mappings = loadSrgs(resource);
        RemappingReferenceMapper.logger.info("Remapping refMap {} using {}", new Object[] { refMap.getResourceName(), resource });
    }
    
    public boolean isDefault() {
        return this.refMap.isDefault();
    }
    
    public String getResourceName() {
        return this.refMap.getResourceName();
    }
    
    public String getStatus() {
        return this.refMap.getStatus();
    }
    
    public String getContext() {
        return this.refMap.getContext();
    }
    
    public void setContext(final String s) {
    }
    
    public String remap(final String s, final String s2) {
        final Map cache = this.getCache(s);
        String s3 = cache.get(s2);
        if (s3 == null) {
            s3 = this.refMap.remap(s, s2);
            for (final Map.Entry<CharSequence, V> entry : this.mappings.entrySet()) {
                s3 = s3.replace(entry.getKey(), (CharSequence)entry.getValue());
            }
            cache.put(s2, s3);
        }
        return s3;
    }
    
    private Map getCache(final String s) {
        Map map = this.cache.get(s);
        if (map == null) {
            map = new HashMap();
            this.cache.put(s, map);
        }
        return map;
    }
    
    public String remapWithContext(final String s, final String s2, final String s3) {
        return this.refMap.remapWithContext(s, s2, s3);
    }
    
    private static Map loadSrgs(final String s) {
        if (RemappingReferenceMapper.srgs.containsKey(s)) {
            return RemappingReferenceMapper.srgs.get(s);
        }
        final HashMap hashMap = new HashMap();
        RemappingReferenceMapper.srgs.put(s, hashMap);
        final File file = new File(s);
        if (!file.isFile()) {
            return hashMap;
        }
        try {
            Files.readLines(file, Charsets.UTF_8, (LineProcessor)new LineProcessor(hashMap) {
                final Map val$map;
                
                public Object getResult() {
                    return null;
                }
                
                public boolean processLine(final String s) throws IOException {
                    if (Strings.isNullOrEmpty(s) || s.startsWith("#")) {
                        return true;
                    }
                    final int n = 0;
                    int n3;
                    final int n2 = s.startsWith("MD: ") ? (n3 = 2) : (s.startsWith("FD: ") ? (n3 = 1) : (n3 = 0));
                    final int n4 = n3;
                    if (n2 > 0) {
                        final String[] split = s.substring(4).split(" ", 4);
                        this.val$map.put(split[n].substring(split[n].lastIndexOf(47) + 1), split[n4].substring(split[n4].lastIndexOf(47) + 1));
                    }
                    return true;
                }
            });
        }
        catch (IOException ex) {
            RemappingReferenceMapper.logger.warn("Could not read input SRG file: {}", new Object[] { s });
            RemappingReferenceMapper.logger.catching((Throwable)ex);
        }
        return hashMap;
    }
    
    public static IReferenceMapper of(final MixinEnvironment mixinEnvironment, final IReferenceMapper referenceMapper) {
        if (!referenceMapper.isDefault() && mixinEnvironment != null) {
            return (IReferenceMapper)new RemappingReferenceMapper(mixinEnvironment, referenceMapper);
        }
        return referenceMapper;
    }
    
    private static String getResource(final MixinEnvironment mixinEnvironment) {
        final String optionValue = mixinEnvironment.getOptionValue(MixinEnvironment.Option.REFMAP_REMAP_RESOURCE);
        return Strings.isNullOrEmpty(optionValue) ? System.getProperty("net.minecraftforge.gradle.GradleStart.srg.srg-mcp") : optionValue;
    }
    
    private static String getMappingEnv(final MixinEnvironment mixinEnvironment) {
        final String optionValue = mixinEnvironment.getOptionValue(MixinEnvironment.Option.REFMAP_REMAP_SOURCE_ENV);
        return Strings.isNullOrEmpty(optionValue) ? "searge" : optionValue;
    }
    
    static {
        logger = LogManager.getLogger("mixin");
        srgs = new HashMap();
    }
}
