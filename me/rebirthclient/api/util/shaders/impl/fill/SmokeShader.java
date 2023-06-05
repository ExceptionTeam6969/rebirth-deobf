//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.shaders.impl.fill;

import me.rebirthclient.api.util.shaders.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import java.util.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;

public class SmokeShader extends FramebufferShader
{
    public static final SmokeShader INSTANCE;
    public float time;
    
    public void startShader(final float n, final Color color, final Color color2, final Color color3, final int n2) {
        GL11.glPushMatrix();
        GL20.glUseProgram(this.program);
        if (this.uniformsMap == null) {
            this.uniformsMap = new HashMap();
            this.setupUniforms();
        }
        this.updateUniforms(n, color, color2, color3, n2);
    }
    
    public void updateUniforms(final float n, final Color color, final Color color2, final Color color3, final int n2) {
        GL20.glUniform2f(this.getUniform("resolution"), new ScaledResolution(this.mc).getScaledWidth() / n, new ScaledResolution(this.mc).getScaledHeight() / n);
        GL20.glUniform1f(this.getUniform("time"), this.time);
        GL20.glUniform4f(this.getUniform("first"), color.getRed() / 255.0f * 5.0f, color.getGreen() / 255.0f * 5.0f, color.getBlue() / 255.0f * 5.0f, color.getAlpha() / 255.0f);
        GL20.glUniform3f(this.getUniform("second"), color2.getRed() / 255.0f * 5.0f, color2.getGreen() / 255.0f * 5.0f, color2.getBlue() / 255.0f * 5.0f);
        GL20.glUniform3f(this.getUniform("third"), color3.getRed() / 255.0f * 5.0f, color3.getGreen() / 255.0f * 5.0f, color3.getBlue() / 255.0f * 5.0f);
        GL20.glUniform1i(this.getUniform("oct"), n2);
    }
    
    public void stopDraw(final Color color, final float radius, final float quality, final float n, final Color color2, final Color color3, final Color color4, final int n2) {
        this.mc.gameSettings.entityShadows = this.entityShadows;
        this.framebuffer.unbindFramebuffer();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        this.mc.getFramebuffer().bindFramebuffer(true);
        this.red = color.getRed() / 255.0f;
        this.green = color.getGreen() / 255.0f;
        this.blue = color.getBlue() / 255.0f;
        this.alpha = color.getAlpha() / 255.0f;
        this.radius = radius;
        this.quality = quality;
        this.mc.entityRenderer.disableLightmap();
        RenderHelper.disableStandardItemLighting();
        this.startShader(n, color2, color3, color4, n2);
        this.mc.entityRenderer.setupOverlayRendering();
        this.drawFramebuffer(this.framebuffer);
        this.stopShader();
        this.mc.entityRenderer.disableLightmap();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
    
    static {
        INSTANCE = new SmokeShader();
    }
    
    public SmokeShader() {
        super("smoke.frag");
    }
    
    public void setupUniforms() {
        this.setupUniform("resolution");
        this.setupUniform("time");
        this.setupUniform("first");
        this.setupUniform("second");
        this.setupUniform("third");
        this.setupUniform("oct");
    }
    
    public void update(final double n) {
        this.time += (float)n;
    }
}
