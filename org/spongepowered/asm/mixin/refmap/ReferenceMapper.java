//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.refmap;

import com.google.common.collect.*;
import java.util.*;
import org.spongepowered.asm.service.*;
import org.apache.commons.io.*;
import org.apache.logging.log4j.*;
import java.io.*;
import com.google.gson.*;

public final class ReferenceMapper implements IReferenceMapper, Serializable
{
    private static final long serialVersionUID = 2L;
    public static final String DEFAULT_RESOURCE = "mixin.refmap.json";
    public static final ReferenceMapper DEFAULT_MAPPER;
    private final Map mappings;
    private final Map data;
    private final transient boolean readOnly;
    private transient String context;
    private transient String resource;
    
    public ReferenceMapper() {
        this(false, "mixin.refmap.json");
    }
    
    private ReferenceMapper(final boolean readOnly, final String resource) {
        this.mappings = Maps.newHashMap();
        this.data = Maps.newHashMap();
        this.context = null;
        this.readOnly = readOnly;
        this.resource = resource;
    }
    
    public boolean isDefault() {
        return this.readOnly;
    }
    
    private void setResourceName(final String s) {
        if (!this.readOnly) {
            this.resource = ((s != null) ? s : "<unknown resource>");
        }
    }
    
    public String getResourceName() {
        return this.resource;
    }
    
    public String getStatus() {
        return this.isDefault() ? "No refMap loaded." : ("Using refmap " + this.getResourceName());
    }
    
    public String getContext() {
        return this.context;
    }
    
    public void setContext(final String context) {
        this.context = context;
    }
    
    public String remap(final String s, final String s2) {
        return this.remapWithContext(this.context, s, s2);
    }
    
    public String remapWithContext(final String s, final String s2, final String s3) {
        Map map = this.mappings;
        if (s != null) {
            map = this.data.get(s);
            if (map == null) {
                map = this.mappings;
            }
        }
        return this.remap(map, s2, s3);
    }
    
    private String remap(final Map map, final String s, final String s2) {
        if (s == null) {
            for (final Map<K, String> map2 : map.values()) {
                if (map2.containsKey(s2)) {
                    return map2.get(s2);
                }
            }
        }
        final Map<K, String> map3 = map.get(s);
        if (map3 == null) {
            return s2;
        }
        final String s3 = map3.get(s2);
        return (s3 != null) ? s3 : s2;
    }
    
    public String addMapping(final String s, final String s2, final String s3, final String s4) {
        if (this.readOnly || s3 == null || s4 == null || s3.equals(s4)) {
            return null;
        }
        Map<Object, Map<?, ?>> map = (Map<Object, Map<?, ?>>)this.mappings;
        if (s != null) {
            map = this.data.get(s);
            if (map == null) {
                map = (Map<Object, Map<?, ?>>)Maps.newHashMap();
                this.data.put(s, map);
            }
        }
        Map<?, ?> map2 = map.get(s2);
        if (map2 == null) {
            map2 = new HashMap<Object, Object>();
            map.put(s2, map2);
        }
        return (String)map2.put(s3, s4);
    }
    
    public void write(final Appendable appendable) {
        new GsonBuilder().setPrettyPrinting().create().toJson((Object)this, appendable);
    }
    
    public static ReferenceMapper read(final String resourceName) {
        final Logger logger = LogManager.getLogger("mixin");
        Reader reader = null;
        try {
            final InputStream resourceAsStream = MixinService.getService().getResourceAsStream(resourceName);
            if (resourceAsStream != null) {
                reader = new InputStreamReader(resourceAsStream);
                final ReferenceMapper json = readJson(reader);
                json.setResourceName(resourceName);
                final ReferenceMapper referenceMapper = json;
                IOUtils.closeQuietly(reader);
                return referenceMapper;
            }
            IOUtils.closeQuietly(reader);
        }
        catch (JsonParseException ex) {
            logger.error("Invalid REFMAP JSON in " + resourceName + ": " + ex.getClass().getName() + " " + ex.getMessage());
            IOUtils.closeQuietly(reader);
        }
        catch (Exception ex2) {
            logger.error("Failed reading REFMAP JSON from " + resourceName + ": " + ex2.getClass().getName() + " " + ex2.getMessage());
            IOUtils.closeQuietly(reader);
        }
        return ReferenceMapper.DEFAULT_MAPPER;
    }
    
    public static ReferenceMapper read(final Reader reader, final String resourceName) {
        try {
            final ReferenceMapper json = readJson(reader);
            json.setResourceName(resourceName);
            return json;
        }
        catch (Exception ex) {
            return ReferenceMapper.DEFAULT_MAPPER;
        }
    }
    
    private static ReferenceMapper readJson(final Reader reader) {
        return (ReferenceMapper)new Gson().fromJson(reader, (Class)ReferenceMapper.class);
    }
    
    static {
        DEFAULT_MAPPER = new ReferenceMapper(true, "invalid");
    }
}
