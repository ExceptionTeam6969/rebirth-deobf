//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import java.util.*;
import javax.tools.*;
import javax.annotation.processing.*;
import org.spongepowered.asm.obfuscation.mapping.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import org.spongepowered.tools.obfuscation.interfaces.*;
import javax.lang.model.element.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;

class AnnotatedMixinElementHandlerShadow extends AnnotatedMixinElementHandler
{
    AnnotatedMixinElementHandlerShadow(final IMixinAnnotationProcessor mixinAnnotationProcessor, final AnnotatedMixin annotatedMixin) {
        super(mixinAnnotationProcessor, annotatedMixin);
    }
    
    public void registerShadow(final AnnotatedElementShadow annotatedElementShadow) {
        this.validateTarget(annotatedElementShadow.getElement(), annotatedElementShadow.getAnnotation(), (AnnotatedMixinElementHandler.AliasedElementName)annotatedElementShadow.getName(), "@Shadow");
        if (!annotatedElementShadow.shouldRemap()) {
            return;
        }
        final Iterator<TypeHandle> iterator = this.mixin.getTargets().iterator();
        while (iterator.hasNext()) {
            this.registerShadowForTarget(annotatedElementShadow, iterator.next());
        }
    }
    
    private void registerShadowForTarget(final AnnotatedElementShadow annotatedElementShadow, final TypeHandle typeHandle) {
        final ObfuscationData obfuscationData = annotatedElementShadow.getObfuscationData(this.obf.getDataProvider(), typeHandle);
        if (obfuscationData.isEmpty()) {
            final String s = this.mixin.isMultiTarget() ? (" in target " + typeHandle) : "";
            if (typeHandle.isSimulated()) {
                annotatedElementShadow.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, (CharSequence)("Unable to locate obfuscation mapping" + s + " for @Shadow " + annotatedElementShadow));
            }
            else {
                annotatedElementShadow.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, (CharSequence)("Unable to locate obfuscation mapping" + s + " for @Shadow " + annotatedElementShadow));
            }
            return;
        }
        for (final ObfuscationType obfuscationType : obfuscationData) {
            try {
                annotatedElementShadow.addMapping(obfuscationType, (IMapping)obfuscationData.get(obfuscationType));
            }
            catch (Mappings.MappingConflictException ex) {
                annotatedElementShadow.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, (CharSequence)("Mapping conflict for @Shadow " + annotatedElementShadow + ": " + ex.getNew().getSimpleName() + " for target " + typeHandle + " conflicts with existing mapping " + ex.getOld().getSimpleName()));
            }
        }
    }
    
    class AnnotatedElementShadowMethod extends AnnotatedElementShadow
    {
        final AnnotatedMixinElementHandlerShadow this$0;
        
        public AnnotatedElementShadowMethod(final AnnotatedMixinElementHandlerShadow this$0, final ExecutableElement executableElement, final AnnotationHandle annotationHandle, final boolean b) {
            this.this$0 = this$0;
            super(executableElement, annotationHandle, b, IMapping.Type.METHOD);
        }
        
        public MappingMethod getMapping(final TypeHandle typeHandle, final String s, final String s2) {
            return typeHandle.getMappingMethod(s, s2);
        }
        
        @Override
        public void addMapping(final ObfuscationType obfuscationType, final IMapping obfuscatedName) {
            this.this$0.addMethodMapping(obfuscationType, this.setObfuscatedName(obfuscatedName), this.getDesc(), obfuscatedName.getDesc());
        }
        
        @Override
        public IMapping getMapping(final TypeHandle typeHandle, final String s, final String s2) {
            return (IMapping)this.getMapping(typeHandle, s, s2);
        }
    }
    
    abstract static class AnnotatedElementShadow extends AnnotatedMixinElementHandler.AnnotatedElement
    {
        private final boolean shouldRemap;
        private final AnnotatedMixinElementHandler.ShadowElementName name;
        private final IMapping.Type type;
        
        protected AnnotatedElementShadow(final Element element, final AnnotationHandle annotationHandle, final boolean shouldRemap, final IMapping.Type type) {
            super(element, annotationHandle);
            this.shouldRemap = shouldRemap;
            this.name = new AnnotatedMixinElementHandler.ShadowElementName(element, annotationHandle);
            this.type = type;
        }
        
        public boolean shouldRemap() {
            return this.shouldRemap;
        }
        
        public AnnotatedMixinElementHandler.ShadowElementName getName() {
            return this.name;
        }
        
        public IMapping.Type getElementType() {
            return this.type;
        }
        
        public String toString() {
            return this.getElementType().name().toLowerCase();
        }
        
        public AnnotatedMixinElementHandler.ShadowElementName setObfuscatedName(final IMapping mapping) {
            return this.setObfuscatedName(mapping.getSimpleName());
        }
        
        public AnnotatedMixinElementHandler.ShadowElementName setObfuscatedName(final String obfuscatedName) {
            return this.getName().setObfuscatedName(obfuscatedName);
        }
        
        public ObfuscationData getObfuscationData(final IObfuscationDataProvider obfuscationDataProvider, final TypeHandle typeHandle) {
            return obfuscationDataProvider.getObfEntry(this.getMapping(typeHandle, this.getName().toString(), this.getDesc()));
        }
        
        public abstract IMapping getMapping(final TypeHandle p0, final String p1, final String p2);
        
        public abstract void addMapping(final ObfuscationType p0, final IMapping p1);
    }
    
    class AnnotatedElementShadowField extends AnnotatedElementShadow
    {
        final AnnotatedMixinElementHandlerShadow this$0;
        
        public AnnotatedElementShadowField(final AnnotatedMixinElementHandlerShadow this$0, final VariableElement variableElement, final AnnotationHandle annotationHandle, final boolean b) {
            this.this$0 = this$0;
            super(variableElement, annotationHandle, b, IMapping.Type.FIELD);
        }
        
        public MappingField getMapping(final TypeHandle typeHandle, final String s, final String s2) {
            return new MappingField(typeHandle.getName(), s, s2);
        }
        
        @Override
        public void addMapping(final ObfuscationType obfuscationType, final IMapping obfuscatedName) {
            this.this$0.addFieldMapping(obfuscationType, this.setObfuscatedName(obfuscatedName), this.getDesc(), obfuscatedName.getDesc());
        }
        
        @Override
        public IMapping getMapping(final TypeHandle typeHandle, final String s, final String s2) {
            return (IMapping)this.getMapping(typeHandle, s, s2);
        }
    }
}
