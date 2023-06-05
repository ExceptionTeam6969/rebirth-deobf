//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.render.shaders.shaders;

import me.rebirthclient.api.util.render.shaders.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.gui.*;

public class BasicShader extends FramebufferShader
{
    private final float timeMult;
    private float time;
    private static BasicShader INSTANCE;
    
    public void setupUniforms() {
        this.setupUniform("time");
        this.setupUniform("resolution");
    }
    
    private BasicShader(final String s) {
        super(s);
        this.time = 0.0f;
        this.timeMult = 0.1f;
    }
    
    public static FramebufferShader INSTANCE(final String s, final float n) {
        if (BasicShader.INSTANCE == null || !Integer.valueOf(s.hashCode()).equals(BasicShader.INSTANCE.fragmentShader.hashCode())) {
            BasicShader.INSTANCE = new BasicShader(s, n);
        }
        return BasicShader.INSTANCE;
    }
    
    private BasicShader(final String s, final float timeMult) {
        super(s);
        this.time = 0.0f;
        this.timeMult = timeMult;
    }
    
    public void updateUniforms() {
        GL20.glUniform1f(this.getUniform("time"), this.time);
        GL20.glUniform2f(this.getUniform("resolution"), (float)new ScaledResolution(this.mc).getScaledWidth(), (float)new ScaledResolution(this.mc).getScaledHeight());
        if (!this.animation) {
            return;
        }
        this.time = ((this.time > 10000.0f) ? 0.0f : (this.time + this.timeMult * this.animationSpeed));
    }
    
    public static FramebufferShader INSTANCE(final String s) {
        if (BasicShader.INSTANCE == null || !Integer.valueOf(s.hashCode()).equals(BasicShader.INSTANCE.fragmentShader.hashCode())) {
            BasicShader.INSTANCE = new BasicShader(s);
        }
        return BasicShader.INSTANCE;
    }
}
