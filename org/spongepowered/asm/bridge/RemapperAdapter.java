//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.bridge;

import org.spongepowered.asm.mixin.extensibility.*;
import org.spongepowered.asm.util.*;
import org.objectweb.asm.commons.*;
import org.apache.logging.log4j.*;

public abstract class RemapperAdapter implements IRemapper, ObfuscationUtil.IClassRemapper
{
    protected final Logger logger;
    protected final Remapper remapper;
    
    public RemapperAdapter(final Remapper remapper) {
        this.logger = LogManager.getLogger("mixin");
        this.remapper = remapper;
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
    
    @Override
    public String mapMethodName(final String s, final String s2, final String s3) {
        this.logger.debug("{} is remapping method {}{} for {}", new Object[] { this, s2, s3, s });
        final String mapMethodName = this.remapper.mapMethodName(s, s2, s3);
        if (!mapMethodName.equals(s2)) {
            return mapMethodName;
        }
        final String unmap = this.unmap(s);
        final String unmapDesc = this.unmapDesc(s3);
        this.logger.debug("{} is remapping obfuscated method {}{} for {}", new Object[] { this, s2, unmapDesc, unmap });
        return this.remapper.mapMethodName(unmap, s2, unmapDesc);
    }
    
    @Override
    public String mapFieldName(final String s, final String s2, final String s3) {
        this.logger.debug("{} is remapping field {}{} for {}", new Object[] { this, s2, s3, s });
        final String mapFieldName = this.remapper.mapFieldName(s, s2, s3);
        if (!mapFieldName.equals(s2)) {
            return mapFieldName;
        }
        final String unmap = this.unmap(s);
        final String unmapDesc = this.unmapDesc(s3);
        this.logger.debug("{} is remapping obfuscated field {}{} for {}", new Object[] { this, s2, unmapDesc, unmap });
        return this.remapper.mapFieldName(unmap, s2, unmapDesc);
    }
    
    @Override
    public String map(final String s) {
        this.logger.debug("{} is remapping class {}", new Object[] { this, s });
        return this.remapper.map(s);
    }
    
    @Override
    public String unmap(final String s) {
        return s;
    }
    
    @Override
    public String mapDesc(final String s) {
        return this.remapper.mapDesc(s);
    }
    
    @Override
    public String unmapDesc(final String s) {
        return ObfuscationUtil.unmapDescriptor(s, this);
    }
}
