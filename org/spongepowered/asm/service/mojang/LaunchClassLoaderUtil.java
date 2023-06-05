//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.service.mojang;

import net.minecraft.launchwrapper.*;
import java.util.*;
import java.lang.reflect.*;

final class LaunchClassLoaderUtil
{
    private static final String CACHED_CLASSES_FIELD = "cachedClasses";
    private static final String INVALID_CLASSES_FIELD = "invalidClasses";
    private static final String CLASS_LOADER_EXCEPTIONS_FIELD = "classLoaderExceptions";
    private static final String TRANSFORMER_EXCEPTIONS_FIELD = "transformerExceptions";
    private final LaunchClassLoader classLoader;
    private final Map cachedClasses;
    private final Set invalidClasses;
    private final Set classLoaderExceptions;
    private final Set transformerExceptions;
    
    LaunchClassLoaderUtil(final LaunchClassLoader classLoader) {
        this.classLoader = classLoader;
        this.cachedClasses = (Map)getField(classLoader, "cachedClasses");
        this.invalidClasses = (Set)getField(classLoader, "invalidClasses");
        this.classLoaderExceptions = (Set)getField(classLoader, "classLoaderExceptions");
        this.transformerExceptions = (Set)getField(classLoader, "transformerExceptions");
    }
    
    LaunchClassLoader getClassLoader() {
        return this.classLoader;
    }
    
    boolean isClassLoaded(final String s) {
        return this.cachedClasses.containsKey(s);
    }
    
    boolean isClassExcluded(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: aload_2        
        //     3: ifeq            12
        //     6: aload_0        
        //     7: aload_1        
        //     8: aload_2        
        //     9: ifeq            16
        //    12: iconst_1       
        //    13: goto            17
        //    16: iconst_0       
        //    17: ireturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0012 (coming from #0009).
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
    
    void registerInvalidClass(final String s) {
        if (this.invalidClasses != null) {
            this.invalidClasses.add(s);
        }
    }
    
    Set getClassLoaderExceptions() {
        if (this.classLoaderExceptions != null) {
            return this.classLoaderExceptions;
        }
        return Collections.emptySet();
    }
    
    Set getTransformerExceptions() {
        if (this.transformerExceptions != null) {
            return this.transformerExceptions;
        }
        return Collections.emptySet();
    }
    
    private static Object getField(final LaunchClassLoader launchClassLoader, final String s) {
        try {
            final Field declaredField = LaunchClassLoader.class.getDeclaredField(s);
            declaredField.setAccessible(true);
            return declaredField.get(launchClassLoader);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
