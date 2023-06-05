//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.render.shaders.shaders;

import me.rebirthclient.api.util.render.shaders.*;
import org.lwjgl.opengl.*;

public class GlowShader extends FramebufferShader
{
    private static GlowShader INSTANCE;
    
    public static GlowShader INSTANCE() {
        if (GlowShader.INSTANCE == null) {
            GlowShader.INSTANCE = new GlowShader();
        }
        return GlowShader.INSTANCE;
    }
    
    public void setupUniforms() {
        this.setupUniform("texture");
        this.setupUniform("texelSize");
        this.setupUniform("color");
        this.setupUniform("divider");
        this.setupUniform("radius");
        this.setupUniform("maxSample");
    }
    
    private GlowShader() {
        super("glow.frag");
    }
    
    public void updateUniforms() {
        GL20.glUniform1i(this.getUniform("texture"), 0);
        GL20.glUniform2f(this.getUniform("texelSize"), Float.intBitsToFloat(Float.floatToIntBits(1531.2186f) ^ 0x7B3F66FF) / this.mc.displayWidth * (this.radius * this.quality), Float.intBitsToFloat(Float.floatToIntBits(103.132805f) ^ 0x7D4E43FF) / this.mc.displayHeight * (this.radius * this.quality));
        GL20.glUniform3f(this.getUniform("color"), this.red, this.green, this.blue);
        GL20.glUniform1f(this.getUniform("divider"), Float.intBitsToFloat(Float.floatToIntBits(0.060076397f) ^ 0x7E7A12AB));
        GL20.glUniform1f(this.getUniform("radius"), this.radius);
        GL20.glUniform1f(this.getUniform("maxSample"), Float.intBitsToFloat(Float.floatToIntBits(0.08735179f) ^ 0x7C92E57F));
    }
}
