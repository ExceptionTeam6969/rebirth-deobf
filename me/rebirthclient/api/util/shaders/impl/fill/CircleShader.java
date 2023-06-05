//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.shaders.impl.fill;

import me.rebirthclient.api.util.shaders.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import java.util.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;

public class CircleShader extends FramebufferShader
{
    public static final CircleShader INSTANCE;
    public float time;
    
    public void startShader(final float n, final Color color, final Float n2, final Float n3) {
        GL11.glPushMatrix();
        GL20.glUseProgram(this.program);
        if (this.uniformsMap == null) {
            this.uniformsMap = new HashMap();
            this.setupUniforms();
        }
        this.updateUniforms(n, color, n2, n3);
    }
    
    public void setupUniforms() {
        this.setupUniform("resolution");
        this.setupUniform("time");
        this.setupUniform("colors");
        this.setupUniform("PI");
        this.setupUniform("rad");
    }
    
    static {
        INSTANCE = new CircleShader();
    }
    
    public void updateUniforms(final float n, final Color color, final Float n2, final Float n3) {
        GL20.glUniform2f(this.getUniform("resolution"), new ScaledResolution(this.mc).getScaledWidth() / n, new ScaledResolution(this.mc).getScaledHeight() / n);
        GL20.glUniform1f(this.getUniform("time"), this.time);
        GL20.glUniform1f(this.getUniform("PI"), (float)n2);
        GL20.glUniform1f(this.getUniform("rad"), (float)n3);
        GL20.glUniform4f(this.getUniform("colors"), color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
    
    public void update(final double n) {
        this.time += (float)n;
    }
    
    public CircleShader() {
        super("circle.frag");
    }
    
    public void stopDraw(final float n, final Color color, final Float n2, final Float n3) {
        this.mc.gameSettings.entityShadows = this.entityShadows;
        this.framebuffer.unbindFramebuffer();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        this.mc.getFramebuffer().bindFramebuffer(true);
        this.mc.entityRenderer.disableLightmap();
        RenderHelper.disableStandardItemLighting();
        this.startShader(n, color, n2, n3);
        this.mc.entityRenderer.setupOverlayRendering();
        this.drawFramebuffer(this.framebuffer);
        this.stopShader();
        this.mc.entityRenderer.disableLightmap();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
}
