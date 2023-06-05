//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.launch.platform;

import java.io.*;
import java.util.jar.*;
import java.net.*;
import java.util.*;

final class MainAttributes
{
    private static final Map instances;
    protected final Attributes attributes;
    
    private MainAttributes() {
        this.attributes = new Attributes();
    }
    
    private MainAttributes(final File file) {
        this.attributes = getAttributes(file);
    }
    
    public final String get(final String s) {
        if (this.attributes != null) {
            return this.attributes.getValue(s);
        }
        return null;
    }
    
    private static Attributes getAttributes(final File file) {
        if (file == null) {
            return null;
        }
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(file);
            final Manifest manifest = jarFile.getManifest();
            if (manifest != null) {
                final Attributes mainAttributes = manifest.getMainAttributes();
                try {
                    if (jarFile != null) {
                        jarFile.close();
                    }
                }
                catch (IOException ex) {}
                return mainAttributes;
            }
            try {
                if (jarFile != null) {
                    jarFile.close();
                }
            }
            catch (IOException ex2) {}
            return new Attributes();
        }
        catch (IOException ex3) {
            try {
                if (jarFile != null) {
                    jarFile.close();
                }
            }
            catch (IOException ex4) {}
        }
        throw;
    }
    
    public static MainAttributes of(final File file) {
        return of(file.toURI());
    }
    
    public static MainAttributes of(final URI uri) {
        MainAttributes mainAttributes = MainAttributes.instances.get(uri);
        if (mainAttributes == null) {
            mainAttributes = new MainAttributes(new File(uri));
            MainAttributes.instances.put(uri, mainAttributes);
        }
        return mainAttributes;
    }
    
    static {
        instances = new HashMap();
    }
}
