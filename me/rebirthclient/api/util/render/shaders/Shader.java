//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.render.shaders;

import java.nio.charset.*;
import org.apache.commons.io.*;
import java.io.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import java.util.*;

public abstract class Shader
{
    public int program;
    public Map uniformsMap;
    protected final String fragmentShader;
    
    public Shader(final String fragmentShader) {
        this.fragmentShader = fragmentShader;
        int shader;
        int shader2;
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream("/shader/vertex.vert");
            shader = this.createShader(IOUtils.toString(resourceAsStream, Charset.defaultCharset()), 35633);
            IOUtils.closeQuietly(resourceAsStream);
            final InputStream resourceAsStream2 = this.getClass().getResourceAsStream("/shader/" + fragmentShader);
            shader2 = this.createShader(IOUtils.toString(resourceAsStream2, Charset.defaultCharset()), 35632);
            IOUtils.closeQuietly(resourceAsStream2);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        if (shader == 0 || shader2 == 0) {
            return;
        }
        this.program = ARBShaderObjects.glCreateProgramObjectARB();
        if (this.program == 0) {
            return;
        }
        ARBShaderObjects.glAttachObjectARB(this.program, shader);
        ARBShaderObjects.glAttachObjectARB(this.program, shader2);
        ARBShaderObjects.glLinkProgramARB(this.program);
        ARBShaderObjects.glValidateProgramARB(this.program);
    }
    
    public void setUniform(final String s, final int n) {
        this.uniformsMap.put(s, n);
    }
    
    public void stopShader() {
        GL20.glUseProgram(0);
        GlStateManager.popMatrix();
    }
    
    public int getProgramId() {
        return this.program;
    }
    
    public void setupUniform(final String s) {
        this.setUniform(s, GL20.glGetUniformLocation(this.program, (CharSequence)s));
    }
    
    public void startShader() {
        GlStateManager.pushMatrix();
        GL20.glUseProgram(this.program);
        if (this.uniformsMap == null) {
            this.uniformsMap = new HashMap();
            this.setupUniforms();
        }
        this.updateUniforms();
    }
    
    public abstract void updateUniforms();
    
    public int getUniform(final String s) {
        return this.uniformsMap.get(s);
    }
    
    public abstract void setupUniforms();
    
    public String getLogInfo(final int n) {
        return ARBShaderObjects.glGetInfoLogARB(n, ARBShaderObjects.glGetObjectParameteriARB(n, 35716));
    }
    
    public int createShader(final String s, final int n) {
        int glCreateShaderObjectARB = 0;
        try {
            glCreateShaderObjectARB = ARBShaderObjects.glCreateShaderObjectARB(n);
            if (glCreateShaderObjectARB == 0) {
                return 0;
            }
            ARBShaderObjects.glShaderSourceARB(glCreateShaderObjectARB, (CharSequence)s);
            ARBShaderObjects.glCompileShaderARB(glCreateShaderObjectARB);
            if (ARBShaderObjects.glGetObjectParameteriARB(glCreateShaderObjectARB, 35713) == 0) {
                throw new RuntimeException("Error creating shader: " + this.getLogInfo(glCreateShaderObjectARB));
            }
            return glCreateShaderObjectARB;
        }
        catch (Exception ex) {
            ARBShaderObjects.glDeleteObjectARB(glCreateShaderObjectARB);
            throw ex;
        }
    }
}
