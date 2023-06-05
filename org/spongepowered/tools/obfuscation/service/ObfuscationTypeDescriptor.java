//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.service;

public class ObfuscationTypeDescriptor
{
    private final String key;
    private final String inputFileArgName;
    private final String extraInputFilesArgName;
    private final String outFileArgName;
    private final Class environmentType;
    
    public ObfuscationTypeDescriptor(final String s, final String s2, final String s3, final Class clazz) {
        this(s, s2, null, s3, clazz);
    }
    
    public ObfuscationTypeDescriptor(final String key, final String inputFileArgName, final String extraInputFilesArgName, final String outFileArgName, final Class environmentType) {
        this.key = key;
        this.inputFileArgName = inputFileArgName;
        this.extraInputFilesArgName = extraInputFilesArgName;
        this.outFileArgName = outFileArgName;
        this.environmentType = environmentType;
    }
    
    public final String getKey() {
        return this.key;
    }
    
    public String getInputFileOption() {
        return this.inputFileArgName;
    }
    
    public String getExtraInputFilesOption() {
        return this.extraInputFilesArgName;
    }
    
    public String getOutputFileOption() {
        return this.outFileArgName;
    }
    
    public Class getEnvironmentType() {
        return this.environmentType;
    }
}
