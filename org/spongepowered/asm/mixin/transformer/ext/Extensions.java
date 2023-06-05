//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer.ext;

import org.spongepowered.asm.mixin.transformer.*;
import org.spongepowered.asm.mixin.*;
import com.google.common.collect.*;
import java.util.*;

public final class Extensions
{
    private final MixinTransformer transformer;
    private final List extensions;
    private final Map extensionMap;
    private final List generators;
    private final List generatorsView;
    private final Map generatorMap;
    private List activeExtensions;
    
    public Extensions(final MixinTransformer transformer) {
        this.extensions = new ArrayList();
        this.extensionMap = new HashMap();
        this.generators = new ArrayList();
        this.generatorsView = Collections.unmodifiableList((List<?>)this.generators);
        this.generatorMap = new HashMap();
        this.activeExtensions = Collections.emptyList();
        this.transformer = transformer;
    }
    
    public MixinTransformer getTransformer() {
        return this.transformer;
    }
    
    public void add(final IExtension extension) {
        this.extensions.add(extension);
        this.extensionMap.put(extension.getClass(), extension);
    }
    
    public List getExtensions() {
        return Collections.unmodifiableList((List<?>)this.extensions);
    }
    
    public List getActiveExtensions() {
        return this.activeExtensions;
    }
    
    public IExtension getExtension(final Class clazz) {
        return (IExtension)lookup(clazz, this.extensionMap, this.extensions);
    }
    
    public void select(final MixinEnvironment mixinEnvironment) {
        final ImmutableList.Builder builder = ImmutableList.builder();
        for (final IExtension extension : this.extensions) {
            if (extension.checkActive(mixinEnvironment)) {
                builder.add((Object)extension);
            }
        }
        this.activeExtensions = (List)builder.build();
    }
    
    public void preApply(final ITargetClassContext targetClassContext) {
        final Iterator<IExtension> iterator = this.activeExtensions.iterator();
        while (iterator.hasNext()) {
            iterator.next().preApply(targetClassContext);
        }
    }
    
    public void postApply(final ITargetClassContext targetClassContext) {
        final Iterator<IExtension> iterator = this.activeExtensions.iterator();
        while (iterator.hasNext()) {
            iterator.next().postApply(targetClassContext);
        }
    }
    
    public void export(final MixinEnvironment mixinEnvironment, final String s, final boolean b, final byte[] array) {
        final Iterator<IExtension> iterator = this.activeExtensions.iterator();
        while (iterator.hasNext()) {
            iterator.next().export(mixinEnvironment, s, b, array);
        }
    }
    
    public void add(final IClassGenerator classGenerator) {
        this.generators.add(classGenerator);
        this.generatorMap.put(classGenerator.getClass(), classGenerator);
    }
    
    public List getGenerators() {
        return this.generatorsView;
    }
    
    public IClassGenerator getGenerator(final Class clazz) {
        return (IClassGenerator)lookup(clazz, this.generatorMap, this.generators);
    }
    
    private static Object lookup(final Class clazz, final Map map, final List list) {
        Object value = map.get(clazz);
        if (value == null) {
            for (final Object next : list) {
                if (clazz.isAssignableFrom(next.getClass())) {
                    value = next;
                    break;
                }
            }
            if (value == null) {
                throw new IllegalArgumentException("Extension for <" + clazz.getName() + "> could not be found");
            }
            map.put(clazz, value);
        }
        return value;
    }
}
