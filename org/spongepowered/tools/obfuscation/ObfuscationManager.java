//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.interfaces.*;
import org.spongepowered.tools.obfuscation.service.*;
import org.spongepowered.tools.obfuscation.mapping.*;
import java.util.*;

public class ObfuscationManager implements IObfuscationManager
{
    private final IMixinAnnotationProcessor ap;
    private final List environments;
    private final IObfuscationDataProvider obfs;
    private final IReferenceManager refs;
    private final List consumers;
    private boolean initDone;
    
    public ObfuscationManager(final IMixinAnnotationProcessor ap) {
        this.environments = new ArrayList();
        this.consumers = new ArrayList();
        this.ap = ap;
        this.obfs = (IObfuscationDataProvider)new ObfuscationDataProvider(ap, this.environments);
        this.refs = (IReferenceManager)new ReferenceManager(ap, this.environments);
    }
    
    public void init() {
        if (this.initDone) {
            return;
        }
        this.initDone = true;
        ObfuscationServices.getInstance().initProviders(this.ap);
        for (final ObfuscationType obfuscationType : ObfuscationType.types()) {
            if (obfuscationType.isSupported()) {
                this.environments.add(obfuscationType.createEnvironment());
            }
        }
    }
    
    public IObfuscationDataProvider getDataProvider() {
        return this.obfs;
    }
    
    public IReferenceManager getReferenceManager() {
        return this.refs;
    }
    
    public IMappingConsumer createMappingConsumer() {
        final Mappings mappings = new Mappings();
        this.consumers.add(mappings);
        return (IMappingConsumer)mappings;
    }
    
    public List getEnvironments() {
        return this.environments;
    }
    
    public void writeMappings() {
        final Iterator<ObfuscationEnvironment> iterator = this.environments.iterator();
        while (iterator.hasNext()) {
            iterator.next().writeMappings((Collection)this.consumers);
        }
    }
    
    public void writeReferences() {
        this.refs.write();
    }
}
