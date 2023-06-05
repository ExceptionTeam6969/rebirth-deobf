//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.util.perf.*;
import com.google.common.collect.*;
import com.google.common.base.*;
import org.spongepowered.asm.mixin.extensibility.*;
import org.apache.logging.log4j.*;
import java.io.*;
import org.spongepowered.asm.service.*;
import java.util.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.util.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.mixin.transformer.throwables.*;

class MixinInfo implements Comparable, IMixinInfo
{
    private static final IMixinService classLoaderUtil;
    static int mixinOrder;
    private final transient Logger logger;
    private final transient Profiler profiler;
    private final transient MixinConfig parent;
    private final String name;
    private final String className;
    private final int priority;
    private final boolean virtual;
    private final List targetClasses;
    private final List targetClassNames;
    private final transient int order;
    private final transient IMixinService service;
    private final transient IMixinConfigPlugin plugin;
    private final transient MixinEnvironment.Phase phase;
    private final transient ClassInfo info;
    private final transient SubType type;
    private final transient boolean strict;
    private transient State pendingState;
    private transient State state;
    
    MixinInfo(final IMixinService service, final MixinConfig parent, final String name, final boolean b, final IMixinConfigPlugin plugin, final boolean b2) {
        this.logger = LogManager.getLogger("mixin");
        this.profiler = MixinEnvironment.getProfiler();
        this.order = MixinInfo.mixinOrder++;
        this.service = service;
        this.parent = parent;
        this.name = name;
        this.className = parent.getMixinPackage() + name;
        this.plugin = plugin;
        this.phase = parent.getEnvironment().getPhase();
        this.strict = parent.getEnvironment().getOption(MixinEnvironment.Option.DEBUG_TARGETS);
        try {
            this.pendingState = new State(this.loadMixinClass(this.className, b));
            this.info = this.pendingState.getClassInfo();
            this.type = SubType.getTypeFor(this);
        }
        catch (InvalidMixinException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            throw new InvalidMixinException((IMixinInfo)this, ex2);
        }
        if (!this.type.isLoadable()) {
            MixinInfo.classLoaderUtil.registerInvalidClass(this.className);
        }
        try {
            this.priority = this.readPriority(this.pendingState.getClassNode());
            this.virtual = this.readPseudo(this.pendingState.getClassNode());
            this.targetClasses = this.readTargetClasses(this.pendingState.getClassNode(), b2);
            this.targetClassNames = Collections.unmodifiableList((List<?>)Lists.transform(this.targetClasses, Functions.toStringFunction()));
        }
        catch (InvalidMixinException ex3) {
            throw ex3;
        }
        catch (Exception ex4) {
            throw new InvalidMixinException((IMixinInfo)this, ex4);
        }
    }
    
    void validate() {
        if (this.pendingState == null) {
            throw new IllegalStateException("No pending validation state for " + this);
        }
        this.pendingState.validate(this.type, this.targetClasses);
        this.state = this.pendingState;
        this.pendingState = null;
    }
    
    protected List readTargetClasses(final MixinClassNode mixinClassNode, final boolean b) {
        if (mixinClassNode == null) {
            return Collections.emptyList();
        }
        final AnnotationNode invisible = Annotations.getInvisible(mixinClassNode, Mixin.class);
        if (invisible == null) {
            throw new InvalidMixinException((IMixinInfo)this, String.format("The mixin '%s' is missing an @Mixin annotation", this.className));
        }
        final ArrayList list = new ArrayList();
        final List list2 = (List)Annotations.getValue(invisible, "value");
        final List list3 = (List)Annotations.getValue(invisible, "targets");
        if (list2 != null) {
            this.readTargets(list, Lists.transform(list2, (Function)new Function() {
                final MixinInfo this$0;
                
                public String apply(final Type type) {
                    return type.getClassName();
                }
                
                public Object apply(final Object o) {
                    return this.apply((Type)o);
                }
            }), b, false);
        }
        if (list3 != null) {
            this.readTargets(list, Lists.transform(list3, (Function)new Function() {
                final MixinInfo this$0;
                
                public String apply(final String s) {
                    return this.this$0.getParent().remapClassName(this.this$0.getClassRef(), s);
                }
                
                public Object apply(final Object o) {
                    return this.apply((String)o);
                }
            }), b, true);
        }
        return list;
    }
    
    private void readTargets(final Collection p0, final Collection p1, final boolean p2, final boolean p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //     6: astore          5
        //     8: aload           5
        //    10: invokeinterface java/util/Iterator.hasNext:()Z
        //    15: ifeq            167
        //    18: aload           5
        //    20: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    25: checkcast       Ljava/lang/String;
        //    28: astore          6
        //    30: aload           6
        //    32: bipush          47
        //    34: bipush          46
        //    36: invokevirtual   java/lang/String.replace:(CC)Ljava/lang/String;
        //    39: astore          7
        //    41: getstatic       org/spongepowered/asm/mixin/transformer/MixinInfo.classLoaderUtil:Lorg/spongepowered/asm/service/IMixinService;
        //    44: aload           7
        //    46: invokeinterface org/spongepowered/asm/service/IMixinService.isClassLoaded:(Ljava/lang/String;)Z
        //    51: ifeq            116
        //    54: aload_0        
        //    55: invokespecial   org/spongepowered/asm/mixin/transformer/MixinInfo.isReloading:()Z
        //    58: ifne            116
        //    61: ldc_w           "Critical problem: %s target %s was already transformed."
        //    64: iconst_2       
        //    65: anewarray       Ljava/lang/Object;
        //    68: dup            
        //    69: iconst_0       
        //    70: aload_0        
        //    71: aastore        
        //    72: dup            
        //    73: iconst_1       
        //    74: aload           7
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: astore          8
        //    82: aload_0        
        //    83: getfield        org/spongepowered/asm/mixin/transformer/MixinInfo.parent:Lorg/spongepowered/asm/mixin/transformer/MixinConfig;
        //    86: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinConfig.isRequired:()Z
        //    89: ifeq            105
        //    92: new             Lorg/spongepowered/asm/mixin/transformer/throwables/MixinTargetAlreadyLoadedException;
        //    95: dup            
        //    96: aload_0        
        //    97: aload           8
        //    99: aload           7
        //   101: invokespecial   org/spongepowered/asm/mixin/transformer/throwables/MixinTargetAlreadyLoadedException.<init>:(Lorg/spongepowered/asm/mixin/extensibility/IMixinInfo;Ljava/lang/String;Ljava/lang/String;)V
        //   104: athrow         
        //   105: aload_0        
        //   106: getfield        org/spongepowered/asm/mixin/transformer/MixinInfo.logger:Lorg/apache/logging/log4j/Logger;
        //   109: aload           8
        //   111: invokeinterface org/apache/logging/log4j/Logger.error:(Ljava/lang/String;)V
        //   116: aload_0        
        //   117: iload_3        
        //   118: aload           7
        //   120: ifnull          164
        //   123: aload_0        
        //   124: aload           7
        //   126: iload           4
        //   128: invokespecial   org/spongepowered/asm/mixin/transformer/MixinInfo.getTarget:(Ljava/lang/String;Z)Lorg/spongepowered/asm/mixin/transformer/ClassInfo;
        //   131: astore          8
        //   133: aload           8
        //   135: ifnull          164
        //   138: aload_1        
        //   139: aload           8
        //   141: invokeinterface java/util/Collection.contains:(Ljava/lang/Object;)Z
        //   146: ifne            164
        //   149: aload_1        
        //   150: aload           8
        //   152: invokeinterface java/util/Collection.add:(Ljava/lang/Object;)Z
        //   157: pop            
        //   158: aload           8
        //   160: aload_0        
        //   161: invokevirtual   org/spongepowered/asm/mixin/transformer/ClassInfo.addMixin:(Lorg/spongepowered/asm/mixin/transformer/MixinInfo;)V
        //   164: goto            8
        //   167: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0008 (coming from #0164).
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2183)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.lang.Thread.run(Thread.java:750)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private ClassInfo getTarget(final String s, final boolean b) throws InvalidMixinException {
        final ClassInfo forName = ClassInfo.forName(s);
        if (forName == null) {
            if (this.isVirtual()) {
                this.logger.debug("Skipping virtual target {} for {}", new Object[] { s, this });
            }
            else {
                this.handleTargetError(String.format("@Mixin target %s was not found %s", s, this));
            }
            return null;
        }
        this.type.validateTarget(s, forName);
        if (b && forName.isPublic() && !this.isVirtual()) {
            this.handleTargetError(String.format("@Mixin target %s is public in %s and should be specified in value", s, this));
        }
        return forName;
    }
    
    private void handleTargetError(final String s) {
        if (this.strict) {
            this.logger.error(s);
            throw new InvalidMixinException((IMixinInfo)this, s);
        }
        this.logger.warn(s);
    }
    
    protected int readPriority(final ClassNode classNode) {
        if (classNode == null) {
            return this.parent.getDefaultMixinPriority();
        }
        final AnnotationNode invisible = Annotations.getInvisible(classNode, Mixin.class);
        if (invisible == null) {
            throw new InvalidMixinException((IMixinInfo)this, String.format("The mixin '%s' is missing an @Mixin annotation", this.className));
        }
        final Integer n = (Integer)Annotations.getValue(invisible, "priority");
        return (n == null) ? this.parent.getDefaultMixinPriority() : n;
    }
    
    protected boolean readPseudo(final ClassNode classNode) {
        return Annotations.getInvisible(classNode, Pseudo.class) != null;
    }
    
    private boolean isReloading() {
        return this.pendingState instanceof Reloaded;
    }
    
    private State getState() {
        return (this.state != null) ? this.state : this.pendingState;
    }
    
    ClassInfo getClassInfo() {
        return this.info;
    }
    
    public IMixinConfig getConfig() {
        return (IMixinConfig)this.parent;
    }
    
    MixinConfig getParent() {
        return this.parent;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public String getClassRef() {
        return this.getClassInfo().getName();
    }
    
    public byte[] getClassBytes() {
        return this.getState().getClassBytes();
    }
    
    public boolean isDetachedSuper() {
        return this.getState().isDetachedSuper();
    }
    
    public boolean isUnique() {
        return this.getState().isUnique();
    }
    
    public boolean isVirtual() {
        return this.virtual;
    }
    
    public boolean isAccessor() {
        return this.type instanceof SubType.Accessor;
    }
    
    public boolean isLoadable() {
        return this.type.isLoadable();
    }
    
    public Level getLoggingLevel() {
        return this.parent.getLoggingLevel();
    }
    
    public MixinEnvironment.Phase getPhase() {
        return this.phase;
    }
    
    public MixinClassNode getClassNode(final int n) {
        return this.getState().createClassNode(n);
    }
    
    public List getTargetClasses() {
        return this.targetClassNames;
    }
    
    List getSoftImplements() {
        return Collections.unmodifiableList((List<?>)this.getState().getSoftImplements());
    }
    
    Set getSyntheticInnerClasses() {
        return Collections.unmodifiableSet((Set<?>)this.getState().getSyntheticInnerClasses());
    }
    
    Set getInnerClasses() {
        return Collections.unmodifiableSet((Set<?>)this.getState().getInnerClasses());
    }
    
    List getTargets() {
        return Collections.unmodifiableList((List<?>)this.targetClasses);
    }
    
    Set getInterfaces() {
        return this.getState().getInterfaces();
    }
    
    MixinTargetContext createContextFor(final TargetClassContext targetClassContext) {
        final MixinClassNode classNode = this.getClassNode(8);
        final Profiler.Section begin = this.profiler.begin("pre");
        final MixinTargetContext context = this.type.createPreProcessor(classNode).prepare().createContextFor(targetClassContext);
        begin.end();
        return context;
    }
    
    private byte[] loadMixinClass(final String s, final boolean b) throws ClassNotFoundException {
        byte[] classBytes;
        try {
            if (b) {
                final String classRestrictions = this.service.getClassRestrictions(s);
                if (classRestrictions.length() > 0) {
                    this.logger.error("Classloader restrictions [{}] encountered loading {}, name: {}", new Object[] { classRestrictions, this, s });
                }
            }
            classBytes = this.service.getBytecodeProvider().getClassBytes(s, b);
        }
        catch (ClassNotFoundException ex2) {
            throw new ClassNotFoundException(String.format("The specified mixin '%s' was not found", s));
        }
        catch (IOException ex) {
            this.logger.warn("Failed to load mixin {}, the specified mixin will not be applied", new Object[] { s });
            throw new InvalidMixinException((IMixinInfo)this, "An error was encountered whilst loading the mixin class", ex);
        }
        return classBytes;
    }
    
    void reloadMixin(final byte[] array) {
        if (this.pendingState != null) {
            throw new IllegalStateException("Cannot reload mixin while it is initialising");
        }
        this.pendingState = new Reloaded(this.state, array);
        this.validate();
    }
    
    public int compareTo(final MixinInfo mixinInfo) {
        if (mixinInfo == null) {
            return 0;
        }
        if (mixinInfo.priority == this.priority) {
            return this.order - mixinInfo.order;
        }
        return this.priority - mixinInfo.priority;
    }
    
    public void preApply(final String s, final ClassNode classNode) {
        if (this.plugin != null) {
            final Profiler.Section begin = this.profiler.begin("plugin");
            this.plugin.preApply(s, classNode, this.className, (IMixinInfo)this);
            begin.end();
        }
    }
    
    public void postApply(final String s, final ClassNode classNode) {
        if (this.plugin != null) {
            final Profiler.Section begin = this.profiler.begin("plugin");
            this.plugin.postApply(s, classNode, this.className, (IMixinInfo)this);
            begin.end();
        }
        this.parent.postApply(s, classNode);
    }
    
    @Override
    public String toString() {
        return String.format("%s:%s", this.parent.getName(), this.name);
    }
    
    @Override
    public int compareTo(final Object o) {
        return this.compareTo((MixinInfo)o);
    }
    
    public ClassNode getClassNode(final int n) {
        return this.getClassNode(n);
    }
    
    static {
        classLoaderUtil = MixinService.getService();
        MixinInfo.mixinOrder = 0;
    }
    
    abstract static class SubType
    {
        protected final MixinInfo mixin;
        protected final String annotationType;
        protected final boolean targetMustBeInterface;
        protected boolean detached;
        
        SubType(final MixinInfo mixin, final String annotationType, final boolean targetMustBeInterface) {
            this.mixin = mixin;
            this.annotationType = annotationType;
            this.targetMustBeInterface = targetMustBeInterface;
        }
        
        Collection getInterfaces() {
            return Collections.emptyList();
        }
        
        boolean isDetachedSuper() {
            return this.detached;
        }
        
        boolean isLoadable() {
            return false;
        }
        
        void validateTarget(final String s, final ClassInfo classInfo) {
            final boolean interface1 = classInfo.isInterface();
            if (interface1 != this.targetMustBeInterface) {
                throw new InvalidMixinException((IMixinInfo)this.mixin, this.annotationType + " target type mismatch: " + s + " is " + (interface1 ? "" : "not ") + "an interface in " + this);
            }
        }
        
        abstract void validate(final State p0, final List p1);
        
        abstract MixinPreProcessorStandard createPreProcessor(final MixinClassNode p0);
        
        static SubType getTypeFor(final MixinInfo mixinInfo) {
            if (!mixinInfo.getClassInfo().isInterface()) {
                return new Standard(mixinInfo);
            }
            boolean b = false;
            final Iterator<ClassInfo.Method> iterator = mixinInfo.getClassInfo().getMethods().iterator();
            while (iterator.hasNext()) {
                b |= !iterator.next().isAccessor();
            }
            if (b) {
                return new Interface(mixinInfo);
            }
            return new Accessor(mixinInfo);
        }
        
        static class Accessor extends SubType
        {
            private final Collection interfaces;
            
            Accessor(final MixinInfo mixinInfo) {
                super(mixinInfo, "@Mixin", false);
                (this.interfaces = new ArrayList()).add(mixinInfo.getClassRef());
            }
            
            @Override
            boolean isLoadable() {
                return true;
            }
            
            @Override
            Collection getInterfaces() {
                return this.interfaces;
            }
            
            @Override
            void validateTarget(final String s, final ClassInfo classInfo) {
                if (classInfo.isInterface() && !MixinEnvironment.getCompatibilityLevel().supportsMethodsInInterfaces()) {
                    throw new InvalidMixinException((IMixinInfo)this.mixin, "Accessor mixin targetting an interface is not supported in current enviromnment");
                }
            }
            
            @Override
            void validate(final State state, final List list) {
                final MixinClassNode classNode = state.getClassNode();
                if (!"java/lang/Object".equals(classNode.superName)) {
                    throw new InvalidMixinException((IMixinInfo)this.mixin, "Super class of " + this + " is invalid, found " + classNode.superName.replace('/', '.'));
                }
            }
            
            @Override
            MixinPreProcessorStandard createPreProcessor(final MixinClassNode mixinClassNode) {
                return new MixinPreProcessorAccessor(this.mixin, mixinClassNode);
            }
        }
        
        static class Interface extends SubType
        {
            Interface(final MixinInfo mixinInfo) {
                super(mixinInfo, "@Mixin", true);
            }
            
            @Override
            void validate(final State state, final List list) {
                if (!MixinEnvironment.getCompatibilityLevel().supportsMethodsInInterfaces()) {
                    throw new InvalidMixinException((IMixinInfo)this.mixin, "Interface mixin not supported in current enviromnment");
                }
                final MixinClassNode classNode = state.getClassNode();
                if (!"java/lang/Object".equals(classNode.superName)) {
                    throw new InvalidMixinException((IMixinInfo)this.mixin, "Super class of " + this + " is invalid, found " + classNode.superName.replace('/', '.'));
                }
            }
            
            @Override
            MixinPreProcessorStandard createPreProcessor(final MixinClassNode mixinClassNode) {
                return new MixinPreProcessorInterface(this.mixin, mixinClassNode);
            }
        }
        
        static class Standard extends SubType
        {
            Standard(final MixinInfo mixinInfo) {
                super(mixinInfo, "@Mixin", false);
            }
            
            @Override
            void validate(final State state, final List list) {
                final MixinClassNode classNode = state.getClassNode();
                for (final ClassInfo classInfo : list) {
                    if (classNode.superName.equals(classInfo.getSuperName())) {
                        continue;
                    }
                    if (!classInfo.hasSuperClass(classNode.superName, ClassInfo.Traversal.SUPER)) {
                        final ClassInfo forName = ClassInfo.forName(classNode.superName);
                        if (forName.isMixin()) {
                            for (final ClassInfo classInfo2 : forName.getTargets()) {
                                if (list.contains(classInfo2)) {
                                    throw new InvalidMixinException((IMixinInfo)this.mixin, "Illegal hierarchy detected. Derived mixin " + this + " targets the same class " + classInfo2.getClassName() + " as its superclass " + forName.getClassName());
                                }
                            }
                        }
                        throw new InvalidMixinException((IMixinInfo)this.mixin, "Super class '" + classNode.superName.replace('/', '.') + "' of " + this.mixin.getName() + " was not found in the hierarchy of target class '" + classInfo + "'");
                    }
                    this.detached = true;
                }
            }
            
            @Override
            MixinPreProcessorStandard createPreProcessor(final MixinClassNode mixinClassNode) {
                return new MixinPreProcessorStandard(this.mixin, mixinClassNode);
            }
        }
    }
    
    class State
    {
        private byte[] mixinBytes;
        private final ClassInfo classInfo;
        private boolean detachedSuper;
        private boolean unique;
        protected final Set interfaces;
        protected final List softImplements;
        protected final Set syntheticInnerClasses;
        protected final Set innerClasses;
        protected MixinClassNode classNode;
        final MixinInfo this$0;
        
        State(final MixinInfo mixinInfo, final byte[] array) {
            this(mixinInfo, array, null);
        }
        
        State(final MixinInfo this$0, final byte[] mixinBytes, final ClassInfo classInfo) {
            this.this$0 = this$0;
            this.interfaces = new HashSet();
            this.softImplements = new ArrayList();
            this.syntheticInnerClasses = new HashSet();
            this.innerClasses = new HashSet();
            this.mixinBytes = mixinBytes;
            this.connect();
            this.classInfo = ((classInfo != null) ? classInfo : ClassInfo.fromClassNode((ClassNode)this.getClassNode()));
        }
        
        private void connect() {
            this.classNode = this.createClassNode(0);
        }
        
        private void complete() {
            this.classNode = null;
        }
        
        ClassInfo getClassInfo() {
            return this.classInfo;
        }
        
        byte[] getClassBytes() {
            return this.mixinBytes;
        }
        
        MixinClassNode getClassNode() {
            return this.classNode;
        }
        
        boolean isDetachedSuper() {
            return this.detachedSuper;
        }
        
        boolean isUnique() {
            return this.unique;
        }
        
        List getSoftImplements() {
            return this.softImplements;
        }
        
        Set getSyntheticInnerClasses() {
            return this.syntheticInnerClasses;
        }
        
        Set getInnerClasses() {
            return this.innerClasses;
        }
        
        Set getInterfaces() {
            return this.interfaces;
        }
        
        MixinClassNode createClassNode(final int n) {
            final MixinClassNode mixinClassNode = this.this$0.new MixinClassNode(this.this$0);
            new ClassReader(this.mixinBytes).accept((ClassVisitor)mixinClassNode, n);
            return mixinClassNode;
        }
        
        void validate(final SubType subType, final List list) {
            final MixinPreProcessorStandard prepare = subType.createPreProcessor(this.getClassNode()).prepare();
            final Iterator<ClassInfo> iterator = list.iterator();
            while (iterator.hasNext()) {
                prepare.conform(iterator.next());
            }
            subType.validate(this, list);
            this.detachedSuper = subType.isDetachedSuper();
            this.unique = (Annotations.getVisible(this.getClassNode(), Unique.class) != null);
            this.validateInner();
            this.validateClassVersion();
            this.validateRemappables(list);
            this.readImplementations(subType);
            this.readInnerClasses();
            this.validateChanges(subType, list);
            this.complete();
        }
        
        private void validateInner() {
            if (!this.classInfo.isProbablyStatic()) {
                throw new InvalidMixinException((IMixinInfo)this.this$0, "Inner class mixin must be declared static");
            }
        }
        
        private void validateClassVersion() {
            if (this.classNode.version > MixinEnvironment.getCompatibilityLevel().classVersion()) {
                String format = ".";
                for (final MixinEnvironment.CompatibilityLevel compatibilityLevel : MixinEnvironment.CompatibilityLevel.values()) {
                    if (compatibilityLevel.classVersion() >= this.classNode.version) {
                        format = String.format(". Mixin requires compatibility level %s or above.", compatibilityLevel.name());
                    }
                }
                throw new InvalidMixinException((IMixinInfo)this.this$0, "Unsupported mixin class version " + this.classNode.version + format);
            }
        }
        
        private void validateRemappables(final List list) {
            if (list.size() > 1) {
                for (final FieldNode fieldNode : this.classNode.fields) {
                    this.validateRemappable(Shadow.class, fieldNode.name, Annotations.getVisible(fieldNode, Shadow.class));
                }
                for (final MethodNode methodNode : this.classNode.methods) {
                    this.validateRemappable(Shadow.class, methodNode.name, Annotations.getVisible(methodNode, Shadow.class));
                    if (Annotations.getVisible(methodNode, Overwrite.class) != null && ((methodNode.access & 0x8) == 0x0 || (methodNode.access & 0x1) == 0x0)) {
                        throw new InvalidMixinException((IMixinInfo)this.this$0, "Found @Overwrite annotation on " + methodNode.name + " in " + this.this$0);
                    }
                }
            }
        }
        
        private void validateRemappable(final Class clazz, final String s, final AnnotationNode annotationNode) {
            if (annotationNode != null && (boolean)Annotations.getValue(annotationNode, "remap", Boolean.TRUE)) {
                throw new InvalidMixinException((IMixinInfo)this.this$0, "Found a remappable @" + clazz.getSimpleName() + " annotation on " + s + " in " + this);
            }
        }
        
        void readImplementations(final SubType subType) {
            this.interfaces.addAll(this.classNode.interfaces);
            this.interfaces.addAll(subType.getInterfaces());
            final AnnotationNode invisible = Annotations.getInvisible(this.classNode, Implements.class);
            if (invisible == null) {
                return;
            }
            final List list = (List)Annotations.getValue(invisible);
            if (list == null) {
                return;
            }
            final Iterator<AnnotationNode> iterator = list.iterator();
            while (iterator.hasNext()) {
                final InterfaceInfo fromAnnotation = InterfaceInfo.fromAnnotation(this.this$0, (AnnotationNode)iterator.next());
                this.softImplements.add(fromAnnotation);
                this.interfaces.add(fromAnnotation.getInternalName());
                if (!(this instanceof Reloaded)) {
                    this.classInfo.addInterface(fromAnnotation.getInternalName());
                }
            }
        }
        
        void readInnerClasses() {
            for (final InnerClassNode innerClassNode : this.classNode.innerClasses) {
                final ClassInfo forName = ClassInfo.forName(innerClassNode.name);
                if ((innerClassNode.outerName != null && innerClassNode.outerName.equals(this.classInfo.getName())) || innerClassNode.name.startsWith(this.classNode.name + "$")) {
                    if (forName.isProbablyStatic() && forName.isSynthetic()) {
                        this.syntheticInnerClasses.add(innerClassNode.name);
                    }
                    else {
                        this.innerClasses.add(innerClassNode.name);
                    }
                }
            }
        }
        
        protected void validateChanges(final SubType subType, final List list) {
            subType.createPreProcessor(this.classNode).prepare();
        }
    }
    
    class MixinClassNode extends ClassNode
    {
        public final List mixinMethods;
        final MixinInfo this$0;
        
        public MixinClassNode(final MixinInfo mixinInfo, final MixinInfo mixinInfo2) {
            this(mixinInfo, 327680);
        }
        
        public MixinClassNode(final MixinInfo this$0, final int n) {
            this.this$0 = this$0;
            super(n);
            this.mixinMethods = this.methods;
        }
        
        public MixinInfo getMixin() {
            return this.this$0;
        }
        
        public MethodVisitor visitMethod(final int n, final String s, final String s2, final String s3, final String[] array) {
            final MixinMethodNode mixinMethodNode = this.this$0.new MixinMethodNode(n, s, s2, s3, array);
            this.methods.add(mixinMethodNode);
            return (MethodVisitor)mixinMethodNode;
        }
    }
    
    class MixinMethodNode extends MethodNode
    {
        private final String originalName;
        final MixinInfo this$0;
        
        public MixinMethodNode(final MixinInfo this$0, final int n, final String originalName, final String s, final String s2, final String[] array) {
            this.this$0 = this$0;
            super(327680, n, originalName, s, s2, array);
            this.originalName = originalName;
        }
        
        public String toString() {
            return String.format("%s%s", this.originalName, this.desc);
        }
        
        public String getOriginalName() {
            return this.originalName;
        }
        
        public boolean isInjector() {
            return this.getInjectorAnnotation() != null || this != null;
        }
        
        public boolean isSynthetic() {
            return Bytecode.hasFlag((MethodNode)this, 4096);
        }
        
        public AnnotationNode getVisibleAnnotation(final Class clazz) {
            return Annotations.getVisible(this, clazz);
        }
        
        public AnnotationNode getInjectorAnnotation() {
            return InjectionInfo.getInjectorAnnotation((IMixinInfo)this.this$0, (MethodNode)this);
        }
        
        public IMixinInfo getOwner() {
            return (IMixinInfo)this.this$0;
        }
    }
    
    class Reloaded extends State
    {
        private final State previous;
        final MixinInfo this$0;
        
        Reloaded(final MixinInfo this$0, final State previous, final byte[] array) {
            this.this$0 = this$0.super(array, previous.getClassInfo());
            this.previous = previous;
        }
        
        @Override
        protected void validateChanges(final SubType subType, final List list) {
            if (!this.syntheticInnerClasses.equals(this.previous.syntheticInnerClasses)) {
                throw new MixinReloadException((IMixinInfo)this.this$0, "Cannot change inner classes");
            }
            if (!this.interfaces.equals(this.previous.interfaces)) {
                throw new MixinReloadException((IMixinInfo)this.this$0, "Cannot change interfaces");
            }
            if (!new HashSet(this.softImplements).equals(new HashSet(this.previous.softImplements))) {
                throw new MixinReloadException((IMixinInfo)this.this$0, "Cannot change soft interfaces");
            }
            if (!new HashSet(this.this$0.readTargetClasses(this.classNode, true)).equals(new HashSet(list))) {
                throw new MixinReloadException((IMixinInfo)this.this$0, "Cannot change target classes");
            }
            if (this.this$0.readPriority(this.classNode) != this.this$0.getPriority()) {
                throw new MixinReloadException((IMixinInfo)this.this$0, "Cannot change mixin priority");
            }
        }
    }
}
