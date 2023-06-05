//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.render.shaders.shaders;

import me.rebirthclient.api.util.render.shaders.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;

public class FlowShader extends FramebufferShader
{
    public static FlowShader INSTANCE;
    protected float time;
    
    public void updateUniforms() {
        GL20.glUniform2f(this.getUniform("resolution"), (float)new ScaledResolution(this.mc).getScaledWidth(), (float)new ScaledResolution(this.mc).getScaledHeight());
        GL20.glUniform1f(this.getUniform("time"), this.time);
        if (!this.animation) {
            return;
        }
        this.time = ((this.time > 100.0f) ? 0.0f : ((float)(this.time + 0.001 * this.animationSpeed)));
    }
    
    public static FlowShader INSTANCE() {
        if (FlowShader.INSTANCE == null) {
            FlowShader.INSTANCE = new FlowShader();
        }
        return FlowShader.INSTANCE;
    }
    
    public void setupUniforms() {
        this.setupUniform("resolution");
        this.setupUniform("time");
    }
    
    private FlowShader() {
        super("flow.frag");
        this.time = 0.0f;
    }
}
