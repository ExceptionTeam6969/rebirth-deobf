//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn��ǿ��������\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.gen;

import org.spongepowered.asm.mixin.struct.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.mixin.transformer.*;
import org.spongepowered.asm.mixin.gen.throwables.*;
import org.spongepowered.asm.mixin.refmap.*;
import com.google.common.base.*;
import org.spongepowered.asm.mixin.*;
import org.apache.logging.log4j.*;
import java.util.regex.*;
import org.spongepowered.asm.util.*;
import java.util.*;
import com.google.common.collect.*;

public class AccessorInfo extends SpecialMethodInfo
{
    protected static final Pattern PATTERN_ACCESSOR;
    protected final Type[] argTypes;
    protected final Type returnType;
    protected final AccessorType type;
    private final Type targetFieldType;
    protected final MemberInfo target;
    protected FieldNode targetField;
    protected MethodNode targetMethod;
    
    public AccessorInfo(final MixinTargetContext mixinTargetContext, final MethodNode methodNode) {
        this(mixinTargetContext, methodNode, Accessor.class);
    }
    
    protected AccessorInfo(final MixinTargetContext mixinTargetContext, final MethodNode methodNode, final Class clazz) {
        super(mixinTargetContext, methodNode, Annotations.getVisible(methodNode, clazz));
        this.argTypes = Type.getArgumentTypes(methodNode.desc);
        this.returnType = Type.getReturnType(methodNode.desc);
        this.type = this.initType();
        this.targetFieldType = this.initTargetFieldType();
        this.target = this.initTarget();
    }
    
    protected AccessorType initType() {
        if (this.returnType.equals((Object)Type.VOID_TYPE)) {
            return AccessorType.FIELD_SETTER;
        }
        return AccessorType.FIELD_GETTER;
    }
    
    protected Type initTargetFieldType() {
        switch (this.type) {
            case FIELD_GETTER: {
                if (this.argTypes.length > 0) {
                    throw new InvalidAccessorException(this.mixin, this + " must take exactly 0 arguments, found " + this.argTypes.length);
                }
                return this.returnType;
            }
            case FIELD_SETTER: {
                if (this.argTypes.length != 1) {
                    throw new InvalidAccessorException(this.mixin, this + " must take exactly 1 argument, found " + this.argTypes.length);
                }
                return this.argTypes[0];
            }
            default: {
                throw new InvalidAccessorException(this.mixin, "Computed unsupported accessor type " + this.type + " for " + this);
            }
        }
    }
    
    protected MemberInfo initTarget() {
        final MemberInfo memberInfo = new MemberInfo(this.getTargetName(), null, this.targetFieldType.getDescriptor());
        this.annotation.visit("target", (Object)memberInfo.toString());
        return memberInfo;
    }
    
    protected String getTargetName() {
        final String s = (String)Annotations.getValue(this.annotation);
        if (!Strings.isNullOrEmpty(s)) {
            return MemberInfo.parse(s, this.mixin).name;
        }
        final String inflectTarget = this.inflectTarget();
        if (inflectTarget == null) {
            throw new InvalidAccessorException(this.mixin, "Failed to inflect target name for " + this + ", supported prefixes: [get, set, is]");
        }
        return inflectTarget;
    }
    
    protected String inflectTarget() {
        return inflectTarget(this.method.name, this.type, this.toString(), this.mixin, this.mixin.getEnvironment().getOption(MixinEnvironment.Option.DEBUG_VERBOSE));
    }
    
    public static String inflectTarget(final String s, final AccessorType accessorType, final String s2, final IMixinContext mixinContext, final boolean b) {
        final Matcher matcher = AccessorInfo.PATTERN_ACCESSOR.matcher(s);
        if (matcher.matches()) {
            final String group = matcher.group(1);
            final String group2 = matcher.group(3);
            final String group3 = matcher.group(4);
            final String format = String.format("%s%s", toLowerCase(group2, !isUpperCase(group3)), group3);
            if (!accessorType.isExpectedPrefix(group) && b) {
                LogManager.getLogger("mixin").warn("Unexpected prefix for {}, found [{}] expecting {}", new Object[] { s2, group, accessorType.getExpectedPrefixes() });
            }
            return MemberInfo.parse(format, mixinContext).name;
        }
        return null;
    }
    
    public final MemberInfo getTarget() {
        return this.target;
    }
    
    public final Type getTargetFieldType() {
        return this.targetFieldType;
    }
    
    public final FieldNode getTargetField() {
        return this.targetField;
    }
    
    public final MethodNode getTargetMethod() {
        return this.targetMethod;
    }
    
    public final Type getReturnType() {
        return this.returnType;
    }
    
    public final Type[] getArgTypes() {
        return this.argTypes;
    }
    
    @Override
    public String toString() {
        return String.format("%s->@%s[%s]::%s%s", this.mixin.toString(), Bytecode.getSimpleName(this.annotation), this.type.toString(), this.method.name, this.method.desc);
    }
    
    public void locate() {
        this.targetField = this.findTargetField();
    }
    
    public MethodNode generate() {
        final MethodNode generate = this.type.getGenerator(this).generate();
        Bytecode.mergeAnnotations(this.method, generate);
        return generate;
    }
    
    private FieldNode findTargetField() {
        return (FieldNode)this.findTarget(this.classNode.fields);
    }
    
    protected Object findTarget(final List list) {
        Object o = null;
        final ArrayList<Object> list2 = new ArrayList<Object>();
        for (final Object next : list) {
            final String nodeDesc = getNodeDesc(next);
            if (nodeDesc != null) {
                if (!nodeDesc.equals(this.target.desc)) {
                    continue;
                }
                final String nodeName = getNodeName(next);
                if (nodeName == null) {
                    continue;
                }
                if (nodeName.equals(this.target.name)) {
                    o = next;
                }
                if (!nodeName.equalsIgnoreCase(this.target.name)) {
                    continue;
                }
                list2.add(next);
            }
        }
        if (o != null) {
            if (list2.size() > 1) {
                LogManager.getLogger("mixin").debug("{} found an exact match for {} but other candidates were found!", new Object[] { this, this.target });
            }
            return o;
        }
        if (list2.size() == 1) {
            return list2.get(0);
        }
        throw new InvalidAccessorException(this, ((list2.size() == 0) ? "No" : "Multiple") + " candidates were found matching " + this.target + " in " + this.classNode.name + " for " + this);
    }
    
    private static String getNodeDesc(final Object o) {
        return (o instanceof MethodNode) ? ((MethodNode)o).desc : ((o instanceof FieldNode) ? ((FieldNode)o).desc : null);
    }
    
    private static String getNodeName(final Object o) {
        return (o instanceof MethodNode) ? ((MethodNode)o).name : ((o instanceof FieldNode) ? ((FieldNode)o).name : null);
    }
    
    public static AccessorInfo of(final MixinTargetContext mixinTargetContext, final MethodNode methodNode, final Class clazz) {
        if (clazz == Accessor.class) {
            return new AccessorInfo(mixinTargetContext, methodNode);
        }
        if (clazz == Invoker.class) {
            return new InvokerInfo(mixinTargetContext, methodNode);
        }
        throw new InvalidAccessorException(mixinTargetContext, "Could not parse accessor for unknown type " + clazz.getName());
    }
    
    private static String toLowerCase(final String s, final boolean b) {
        return b ? s.toLowerCase() : s;
    }
    
    private static boolean isUpperCase(final String s) {
        return s.toUpperCase().equals(s);
    }
    
    static {
        PATTERN_ACCESSOR = Pattern.compile("^(get|set|is|invoke|call)(([A-Z])(.*?))(_\\$md.*)?$");
    }
    
    public enum AccessorType
    {
        FIELD_GETTER((Set)ImmutableSet.of((Object)"get", (Object)"is")) {
            @Override
            AccessorGenerator getGenerator(final AccessorInfo accessorInfo) {
                return (AccessorGenerator)new AccessorGeneratorFieldGetter(accessorInfo);
            }
        }, 
        FIELD_SETTER((Set)ImmutableSet.of((Object)"set")) {
            @Override
            AccessorGenerator getGenerator(final AccessorInfo accessorInfo) {
                return (AccessorGenerator)new AccessorGeneratorFieldSetter(accessorInfo);
            }
        }, 
        METHOD_PROXY((Set)ImmutableSet.of((Object)"call", (Object)"invoke")) {
            @Override
            AccessorGenerator getGenerator(final AccessorInfo accessorInfo) {
                return (AccessorGenerator)new AccessorGeneratorMethodProxy(accessorInfo);
            }
        };
        
        private final Set expectedPrefixes;
        private static final AccessorType[] $VALUES;
        
        private AccessorType(final String s, final int n, final Set expectedPrefixes) {
            this.expectedPrefixes = expectedPrefixes;
        }
        
        public boolean isExpectedPrefix(final String s) {
            return this.expectedPrefixes.contains(s);
        }
        
        public String getExpectedPrefixes() {
            return this.expectedPrefixes.toString();
        }
        
        abstract AccessorGenerator getGenerator(final AccessorInfo p0);
        
        AccessorType(final String s, final int n, final Set set, final AccessorInfo$1 object) {
            this(s, n, set);
        }
        
        static {
            $VALUES = new AccessorType[] { AccessorType.FIELD_GETTER, AccessorType.FIELD_SETTER, AccessorType.METHOD_PROXY };
        }
    }
}
