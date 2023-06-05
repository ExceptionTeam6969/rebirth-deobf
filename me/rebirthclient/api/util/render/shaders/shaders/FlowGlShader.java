//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.render.shaders.shaders;

import me.rebirthclient.api.util.render.shaders.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.gui.*;

public class FlowGlShader extends FramebufferShader
{
    protected float time;
    private static FlowGlShader INSTANCE;
    
    public void updateUniforms() {
        GL20.glUniform1f(this.getUniform("time"), this.time);
        GL20.glUniform2f(this.getUniform("resolution"), (float)new ScaledResolution(this.mc).getScaledWidth(), (float)new ScaledResolution(this.mc).getScaledHeight());
        GL20.glUniform1i(this.getUniform("texture"), 0);
        GL20.glUniform2f(this.getUniform("texelSize"), Float.intBitsToFloat(Float.floatToIntBits(1531.2186f) ^ 0x7B3F66FF) / this.mc.displayWidth * (this.radius * this.quality), Float.intBitsToFloat(Float.floatToIntBits(103.132805f) ^ 0x7D4E43FF) / this.mc.displayHeight * (this.radius * this.quality));
        GL20.glUniform3f(this.getUniform("color"), this.red, this.green, this.blue);
        GL20.glUniform1f(this.getUniform("divider"), this.divider);
        GL20.glUniform1f(this.getUniform("radius"), this.radius);
        GL20.glUniform1f(this.getUniform("maxSample"), this.maxSample);
        if (!this.animation) {
            return;
        }
        this.time = ((this.time > 100.0f) ? 0.0f : ((float)(this.time + 0.001 * this.animationSpeed)));
    }
    
    public void setupUniforms() {
        this.setupUniform("time");
        this.setupUniform("resolution");
        this.setupUniform("texture");
        this.setupUniform("texelSize");
        this.setupUniform("color");
        this.setupUniform("divider");
        this.setupUniform("radius");
        this.setupUniform("maxSample");
    }
    
    private FlowGlShader() {
        super("flowglow.frag");
        this.time = 0.0f;
    }
    
    public static FlowGlShader INSTANCE() {
        if (FlowGlShader.INSTANCE == null) {
            FlowGlShader.INSTANCE = new FlowGlShader();
        }
        return FlowGlShader.INSTANCE;
    }
}
