//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.shaders;

import org.lwjgl.opengl.*;
import java.util.*;
import org.apache.commons.io.*;
import java.io.*;

public class Shader
{
    protected int program;
    protected Map uniformsMap;
    
    public void stopShader() {
        GL20.glUseProgram(0);
        GL11.glPopMatrix();
    }
    
    private String getLogInfo(final int n) {
        return ARBShaderObjects.glGetInfoLogARB(n, ARBShaderObjects.glGetObjectParameteriARB(n, 35716));
    }
    
    public void setupUniforms() {
    }
    
    public void setUniform(final String s, final int n) {
        this.uniformsMap.put(s, n);
    }
    
    private int createShader(final String s, final int n) {
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
    
    public void updateUniforms(final float n) {
    }
    
    public void setupUniform(final String s) {
        this.setUniform(s, GL20.glGetUniformLocation(this.program, (CharSequence)s));
    }
    
    public int getUniform(final String s) {
        return this.uniformsMap.get(s);
    }
    
    public void startShader(final float n) {
        GL11.glPushMatrix();
        GL20.glUseProgram(this.program);
        if (this.uniformsMap == null) {
            this.uniformsMap = new HashMap();
            this.setupUniforms();
        }
        this.updateUniforms(n);
    }
    
    public Shader(final String s) {
        int shader;
        int shader2;
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream("/assets/minecraft/shaders/vertex.vert");
            shader = this.createShader(IOUtils.toString(resourceAsStream), 35633);
            IOUtils.closeQuietly(resourceAsStream);
            final InputStream resourceAsStream2 = this.getClass().getResourceAsStream("/assets/minecraft/shaders/fragment/" + s);
            shader2 = this.createShader(IOUtils.toString(resourceAsStream2), 35632);
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
}
