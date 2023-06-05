//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.interfaces.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import java.util.*;
import org.spongepowered.asm.obfuscation.mapping.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;

public class ObfuscationDataProvider implements IObfuscationDataProvider
{
    private final IMixinAnnotationProcessor ap;
    private final List environments;
    
    public ObfuscationDataProvider(final IMixinAnnotationProcessor ap, final List environments) {
        this.ap = ap;
        this.environments = environments;
    }
    
    public ObfuscationData getObfEntryRecursive(final MemberInfo memberInfo) {
        MemberInfo move = memberInfo;
        final ObfuscationData obfClass = this.getObfClass(move.owner);
        ObfuscationData obfuscationData = this.getObfEntry(move);
        try {
            while (obfuscationData.isEmpty()) {
                final TypeHandle typeHandle = this.ap.getTypeProvider().getTypeHandle(move.owner);
                if (typeHandle == null) {
                    return obfuscationData;
                }
                final TypeHandle superclass = typeHandle.getSuperclass();
                obfuscationData = this.getObfEntryUsing(move, superclass);
                if (!obfuscationData.isEmpty()) {
                    return applyParents(obfClass, obfuscationData);
                }
                final Iterator iterator = typeHandle.getInterfaces().iterator();
                while (iterator.hasNext()) {
                    obfuscationData = this.getObfEntryUsing(move, iterator.next());
                    if (!obfuscationData.isEmpty()) {
                        return applyParents(obfClass, obfuscationData);
                    }
                }
                if (superclass == null) {
                    break;
                }
                move = move.move(superclass.getName());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return this.getObfEntry(memberInfo);
        }
        return obfuscationData;
    }
    
    private ObfuscationData getObfEntryUsing(final MemberInfo memberInfo, final TypeHandle typeHandle) {
        return (typeHandle == null) ? new ObfuscationData() : this.getObfEntry(memberInfo.move(typeHandle.getName()));
    }
    
    public ObfuscationData getObfEntry(final MemberInfo memberInfo) {
        if (memberInfo.isField()) {
            return this.getObfField(memberInfo);
        }
        return this.getObfMethod(memberInfo.asMethodMapping());
    }
    
    public ObfuscationData getObfEntry(final IMapping mapping) {
        if (mapping != null) {
            if (mapping.getType() == IMapping.Type.FIELD) {
                return this.getObfField((MappingField)mapping);
            }
            if (mapping.getType() == IMapping.Type.METHOD) {
                return this.getObfMethod((MappingMethod)mapping);
            }
        }
        return new ObfuscationData();
    }
    
    public ObfuscationData getObfMethodRecursive(final MemberInfo memberInfo) {
        return this.getObfEntryRecursive(memberInfo);
    }
    
    public ObfuscationData getObfMethod(final MemberInfo memberInfo) {
        return this.getRemappedMethod(memberInfo, memberInfo.isConstructor());
    }
    
    public ObfuscationData getRemappedMethod(final MemberInfo memberInfo) {
        return this.getRemappedMethod(memberInfo, true);
    }
    
    private ObfuscationData getRemappedMethod(final MemberInfo memberInfo, final boolean b) {
        final ObfuscationData obfuscationData = new ObfuscationData();
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            final MappingMethod obfMethod = obfuscationEnvironment.getObfMethod(memberInfo);
            if (obfMethod != null) {
                obfuscationData.put(obfuscationEnvironment.getType(), (Object)obfMethod);
            }
        }
        if (!obfuscationData.isEmpty() || !b) {
            return obfuscationData;
        }
        return this.remapDescriptor(obfuscationData, memberInfo);
    }
    
    public ObfuscationData getObfMethod(final MappingMethod mappingMethod) {
        return this.getRemappedMethod(mappingMethod, mappingMethod.isConstructor());
    }
    
    public ObfuscationData getRemappedMethod(final MappingMethod mappingMethod) {
        return this.getRemappedMethod(mappingMethod, true);
    }
    
    private ObfuscationData getRemappedMethod(final MappingMethod mappingMethod, final boolean b) {
        final ObfuscationData obfuscationData = new ObfuscationData();
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            final MappingMethod obfMethod = obfuscationEnvironment.getObfMethod(mappingMethod);
            if (obfMethod != null) {
                obfuscationData.put(obfuscationEnvironment.getType(), (Object)obfMethod);
            }
        }
        if (!obfuscationData.isEmpty() || !b) {
            return obfuscationData;
        }
        return this.remapDescriptor(obfuscationData, new MemberInfo((IMapping)mappingMethod));
    }
    
    public ObfuscationData remapDescriptor(final ObfuscationData obfuscationData, final MemberInfo memberInfo) {
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            final MemberInfo remapDescriptor = obfuscationEnvironment.remapDescriptor(memberInfo);
            if (remapDescriptor != null) {
                obfuscationData.put(obfuscationEnvironment.getType(), (Object)remapDescriptor.asMethodMapping());
            }
        }
        return obfuscationData;
    }
    
    public ObfuscationData getObfFieldRecursive(final MemberInfo memberInfo) {
        return this.getObfEntryRecursive(memberInfo);
    }
    
    public ObfuscationData getObfField(final MemberInfo memberInfo) {
        return this.getObfField(memberInfo.asFieldMapping());
    }
    
    public ObfuscationData getObfField(final MappingField mappingField) {
        final ObfuscationData obfuscationData = new ObfuscationData();
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            MappingField mappingField2 = obfuscationEnvironment.getObfField(mappingField);
            if (mappingField2 != null) {
                if (mappingField2.getDesc() == null && mappingField.getDesc() != null) {
                    mappingField2 = mappingField2.transform(obfuscationEnvironment.remapDescriptor(mappingField.getDesc()));
                }
                obfuscationData.put(obfuscationEnvironment.getType(), (Object)mappingField2);
            }
        }
        return obfuscationData;
    }
    
    public ObfuscationData getObfClass(final TypeHandle typeHandle) {
        return this.getObfClass(typeHandle.getName());
    }
    
    public ObfuscationData getObfClass(final String s) {
        final ObfuscationData obfuscationData = new ObfuscationData((Object)s);
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            final String obfClass = obfuscationEnvironment.getObfClass(s);
            if (obfClass != null) {
                obfuscationData.put(obfuscationEnvironment.getType(), (Object)obfClass);
            }
        }
        return obfuscationData;
    }
    
    private static ObfuscationData applyParents(final ObfuscationData obfuscationData, final ObfuscationData obfuscationData2) {
        for (final ObfuscationType obfuscationType : obfuscationData2) {
            obfuscationData2.put(obfuscationType, (Object)MemberInfo.fromMapping((IMapping)obfuscationData2.get(obfuscationType)).move((String)obfuscationData.get(obfuscationType)).asMapping());
        }
        return obfuscationData2;
    }
}
