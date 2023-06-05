//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.render.shaders.shaders;

import me.rebirthclient.api.util.render.shaders.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.gui.*;

public class AquaShader extends FramebufferShader
{
    private static AquaShader INSTANCE;
    public float time;
    
    public static FramebufferShader INSTANCE() {
        if (AquaShader.INSTANCE == null) {
            AquaShader.INSTANCE = new AquaShader();
        }
        return AquaShader.INSTANCE;
    }
    
    private AquaShader() {
        super("aqua.frag");
        this.time = 0.0f;
    }
    
    public void updateUniforms() {
        GL20.glUniform1f(this.getUniform("time"), this.time);
        GL20.glUniform2f(this.getUniform("resolution"), (float)new ScaledResolution(this.mc).getScaledWidth(), (float)new ScaledResolution(this.mc).getScaledHeight());
        if (!this.animation) {
            return;
        }
        this.time = ((this.time > 100.0f) ? 0.0f : ((float)(this.time + 0.01 * this.animationSpeed)));
    }
    
    public void setupUniforms() {
        this.setupUniform("resolution");
        this.setupUniform("time");
    }
}
