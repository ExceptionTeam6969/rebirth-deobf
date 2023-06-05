//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.render.shaders.shaders;

import me.rebirthclient.api.util.render.shaders.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.gui.*;

public class RedShader extends FramebufferShader
{
    protected float time;
    private static RedShader INSTANCE;
    
    private RedShader() {
        super("red.frag");
        this.time = 0.0f;
    }
    
    public static RedShader INSTANCE() {
        if (RedShader.INSTANCE == null) {
            RedShader.INSTANCE = new RedShader();
        }
        return RedShader.INSTANCE;
    }
    
    public void updateUniforms() {
        GL20.glUniform1f(this.getUniform("time"), this.time);
        GL20.glUniform2f(this.getUniform("resolution"), (float)new ScaledResolution(this.mc).getScaledWidth(), (float)new ScaledResolution(this.mc).getScaledHeight());
        GL20.glUniform2f(this.getUniform("texelSize"), 1.0f / this.mc.displayWidth * (this.radius * this.quality), 1.0f / this.mc.displayHeight * (this.radius * this.quality));
        if (!this.animation) {
            return;
        }
        this.time = ((this.time > 100.0f) ? 0.0f : ((float)(this.time + 0.05 * this.animationSpeed)));
    }
    
    public void setupUniforms() {
        this.setupUniform("time");
        this.setupUniform("resolution");
        this.setupUniform("texelSize");
    }
}
