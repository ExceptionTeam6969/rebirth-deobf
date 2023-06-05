//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.render.shaders.shaders;

import me.rebirthclient.api.util.render.shaders.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.gui.*;

public class SmokeShader extends FramebufferShader
{
    protected float time;
    private static SmokeShader INSTANCE;
    
    public void setupUniforms() {
        this.setupUniform("time");
        this.setupUniform("resolution");
        this.setupUniform("radius");
        this.setupUniform("divider");
        this.setupUniform("maxSample");
        this.setupUniform("texelSize");
    }
    
    public void updateUniforms() {
        GL20.glUniform1f(this.getUniform("time"), this.time);
        GL20.glUniform2f(this.getUniform("resolution"), new ScaledResolution(this.mc).getScaledWidth() / 2.0f, new ScaledResolution(this.mc).getScaledHeight() / 2.0f);
        GL20.glUniform1f(this.getUniform("radius"), this.radius);
        GL20.glUniform1f(this.getUniform("divider"), this.divider);
        GL20.glUniform1f(this.getUniform("maxSample"), this.maxSample);
        GL20.glUniform2f(this.getUniform("texelSize"), 1.0f / this.mc.displayWidth * (this.radius * this.quality), 1.0f / this.mc.displayHeight * (this.radius * this.quality));
        if (!this.animation) {
            return;
        }
        this.time = ((this.time > 100.0f) ? 0.0f : ((float)(this.time + 0.05 * this.animationSpeed)));
    }
    
    public static SmokeShader INSTANCE() {
        if (SmokeShader.INSTANCE == null) {
            SmokeShader.INSTANCE = new SmokeShader();
        }
        return SmokeShader.INSTANCE;
    }
    
    private SmokeShader() {
        super("smoke.frag");
        this.time = 0.0f;
    }
}
