//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.render.shaders.shaders;

import me.rebirthclient.api.util.render.shaders.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;

public class PurpleShader extends FramebufferShader
{
    public float time;
    public static PurpleShader INSTANCE;
    public final float timeMult;
    
    public void setupUniforms() {
        this.setupUniform("resolution");
        this.setupUniform("time");
    }
    
    public static PurpleShader INSTANCE() {
        if (PurpleShader.INSTANCE == null) {
            PurpleShader.INSTANCE = new PurpleShader();
        }
        return PurpleShader.INSTANCE;
    }
    
    public PurpleShader() {
        super("purple.frag");
        this.timeMult = 0.05f;
    }
    
    public void updateUniforms() {
        GL20.glUniform2f(this.getUniform("resolution"), (float)new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth(), (float)new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight());
        GL20.glUniform1f(this.getUniform("time"), this.time);
        this.time += this.timeMult * this.animationSpeed;
    }
}
