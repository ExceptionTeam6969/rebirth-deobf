//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.agent;

import org.spongepowered.asm.mixin.transformer.ext.*;
import org.spongepowered.asm.mixin.transformer.*;
import org.apache.logging.log4j.*;
import java.util.*;
import java.security.*;
import java.lang.instrument.*;
import org.spongepowered.asm.mixin.transformer.throwables.*;

public class MixinAgent implements IHotSwap
{
    public static final byte[] ERROR_BYTECODE;
    static final MixinAgentClassLoader classLoader;
    static final Logger logger;
    static Instrumentation instrumentation;
    private static List agents;
    final MixinTransformer classTransformer;
    
    public MixinAgent(final MixinTransformer classTransformer) {
        this.classTransformer = classTransformer;
        MixinAgent.agents.add(this);
        if (MixinAgent.instrumentation != null) {
            this.initTransformer();
        }
    }
    
    private void initTransformer() {
        MixinAgent.instrumentation.addTransformer(new Transformer(), true);
    }
    
    public void registerMixinClass(final String s) {
        MixinAgent.classLoader.addMixinClass(s);
    }
    
    public void registerTargetClass(final String s, final byte[] array) {
        MixinAgent.classLoader.addTargetClass(s, array);
    }
    
    public static void init(final Instrumentation instrumentation) {
        MixinAgent.instrumentation = instrumentation;
        if (!MixinAgent.instrumentation.isRedefineClassesSupported()) {
            MixinAgent.logger.error("The instrumentation doesn't support re-definition of classes");
        }
        final Iterator<MixinAgent> iterator = MixinAgent.agents.iterator();
        while (iterator.hasNext()) {
            iterator.next().initTransformer();
        }
    }
    
    public static void premain(final String s, final Instrumentation instrumentation) {
        System.setProperty("mixin.hotSwap", "true");
        init(instrumentation);
    }
    
    public static void agentmain(final String s, final Instrumentation instrumentation) {
        init(instrumentation);
    }
    
    static {
        ERROR_BYTECODE = new byte[] { 1 };
        classLoader = new MixinAgentClassLoader();
        logger = LogManager.getLogger("mixin.agent");
        MixinAgent.instrumentation = null;
        MixinAgent.agents = new ArrayList();
    }
    
    class Transformer implements ClassFileTransformer
    {
        final MixinAgent this$0;
        
        Transformer(final MixinAgent this$0) {
            this.this$0 = this$0;
        }
        
        @Override
        public byte[] transform(final ClassLoader p0, final String p1, final Class p2, final ProtectionDomain p3, final byte[] p4) throws IllegalClassFormatException {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: ifnonnull       6
            //     4: aconst_null    
            //     5: areturn        
            //     6: getstatic       org/spongepowered/tools/agent/MixinAgent.classLoader:Lorg/spongepowered/tools/agent/MixinAgentClassLoader;
            //     9: aload_3        
            //    10: invokevirtual   org/spongepowered/tools/agent/MixinAgentClassLoader.getFakeMixinBytecode:(Ljava/lang/Class;)[B
            //    13: astore          6
            //    15: aload           6
            //    17: ifnull          47
            //    20: aload_0        
            //    21: aload_2        
            //    22: aload           5
            //    24: invokespecial   org/spongepowered/tools/agent/MixinAgent$Transformer.reloadMixin:(Ljava/lang/String;[B)Ljava/util/List;
            //    27: astore          7
            //    29: aload           7
            //    31: ifnull          40
            //    34: aload_0        
            //    35: aload           7
            //    37: ifeq            44
            //    40: getstatic       org/spongepowered/tools/agent/MixinAgent.ERROR_BYTECODE:[B
            //    43: areturn        
            //    44: aload           6
            //    46: areturn        
            //    47: getstatic       org/spongepowered/tools/agent/MixinAgent.logger:Lorg/apache/logging/log4j/Logger;
            //    50: new             Ljava/lang/StringBuilder;
            //    53: dup            
            //    54: invokespecial   java/lang/StringBuilder.<init>:()V
            //    57: ldc             "Redefining class "
            //    59: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    62: aload_2        
            //    63: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    66: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //    69: invokeinterface org/apache/logging/log4j/Logger.info:(Ljava/lang/String;)V
            //    74: aload_0        
            //    75: getfield        org/spongepowered/tools/agent/MixinAgent$Transformer.this$0:Lorg/spongepowered/tools/agent/MixinAgent;
            //    78: getfield        org/spongepowered/tools/agent/MixinAgent.classTransformer:Lorg/spongepowered/asm/mixin/transformer/MixinTransformer;
            //    81: aconst_null    
            //    82: aload_2        
            //    83: aload           5
            //    85: invokevirtual   org/spongepowered/asm/mixin/transformer/MixinTransformer.transformClassBytes:(Ljava/lang/String;Ljava/lang/String;[B)[B
            //    88: areturn        
            //    89: astore          7
            //    91: getstatic       org/spongepowered/tools/agent/MixinAgent.logger:Lorg/apache/logging/log4j/Logger;
            //    94: new             Ljava/lang/StringBuilder;
            //    97: dup            
            //    98: invokespecial   java/lang/StringBuilder.<init>:()V
            //   101: ldc             "Error while re-transforming class "
            //   103: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   106: aload_2        
            //   107: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   110: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   113: aload           7
            //   115: invokeinterface org/apache/logging/log4j/Logger.error:(Ljava/lang/String;Ljava/lang/Throwable;)V
            //   120: getstatic       org/spongepowered/tools/agent/MixinAgent.ERROR_BYTECODE:[B
            //   123: areturn        
            //    Exceptions:
            //  throws java.lang.instrument.IllegalClassFormatException
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  47     88     89     124    Ljava/lang/Throwable;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Inconsistent stack size at #0040 (coming from #0037).
            //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2183)
            //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
        
        private List reloadMixin(final String s, final byte[] array) {
            MixinAgent.logger.info("Redefining mixin {}", new Object[] { s });
            try {
                return this.this$0.classTransformer.reload(s.replace('/', '.'), array);
            }
            catch (MixinReloadException ex) {
                MixinAgent.logger.error("Mixin {} cannot be reloaded, needs a restart to be applied: {} ", new Object[] { ex.getMixinInfo(), ex.getMessage() });
            }
            catch (Throwable t) {
                MixinAgent.logger.error("Error while finding targets for mixin " + s, t);
            }
            return null;
        }
    }
}
