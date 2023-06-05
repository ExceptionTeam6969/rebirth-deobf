//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.render.shader.framebuffer.impl;

import me.rebirthclient.api.util.render.shader.framebuffer.*;
import org.lwjgl.opengl.*;
import me.rebirthclient.api.util.*;

public class ItemShader extends FramebufferShader
{
    public float mix;
    public static final ItemShader INSTANCE;
    public float alpha;
    public boolean model;
    
    public void updateUniforms() {
        GL20.glUniform1i(this.getUniform("texture"), 0);
        GL20.glUniform1i(this.getUniform("inside"), (int)(this.model ? 1 : 0));
        GL20.glUniform2f(this.getUniform("texelSize"), 1.0f / Wrapper.mc.displayWidth * (this.radius * this.quality), 1.0f / Wrapper.mc.displayHeight * (this.radius * this.quality));
        GL20.glUniform3f(this.getUniform("color"), this.red, this.green, this.blue);
        GL20.glUniform1f(this.getUniform("divider"), 140.0f);
        GL20.glUniform1f(this.getUniform("radius"), this.radius);
        GL20.glUniform1f(this.getUniform("maxSample"), 10.0f);
        GL20.glUniform2f(this.getUniform("dimensions"), (float)Wrapper.mc.displayWidth, (float)Wrapper.mc.displayHeight);
        GL20.glUniform1f(this.getUniform("mixFactor"), this.mix);
        GL20.glUniform1f(this.getUniform("minAlpha"), this.alpha);
    }
    
    public ItemShader() {
        super("glow.frag");
        this.alpha = 1.0f;
    }
    
    static {
        INSTANCE = new ItemShader();
    }
    
    public void setupUniforms() {
        this.setupUniform("texture");
        this.setupUniform("texelSize");
        this.setupUniform("color");
        this.setupUniform("divider");
        this.setupUniform("radius");
        this.setupUniform("maxSample");
        this.setupUniform("dimensions");
        this.setupUniform("mixFactor");
        this.setupUniform("minAlpha");
        this.setupUniform("inside");
    }
}
