//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.shaders.impl.fill;

import me.rebirthclient.api.util.shaders.*;
import org.lwjgl.opengl.*;
import java.util.*;
import java.awt.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;

public class GradientShader extends FramebufferShader
{
    public static final GradientShader INSTANCE;
    public float time;
    
    public void startShader(final float n, final float n2, final float n3, final float n4, final int n5) {
        GL11.glPushMatrix();
        GL20.glUseProgram(this.program);
        if (this.uniformsMap == null) {
            this.uniformsMap = new HashMap();
            this.setupUniforms();
        }
        this.updateUniforms(n, n2, n3, n4, n5);
    }
    
    public GradientShader() {
        super("gradient.frag");
    }
    
    public void update(final double n) {
        this.time += (float)n;
    }
    
    public void setupUniforms() {
        this.setupUniform("resolution");
        this.setupUniform("time");
        this.setupUniform("moreGradient");
        this.setupUniform("Creepy");
        this.setupUniform("alpha");
        this.setupUniform("NUM_OCTAVES");
    }
    
    public void stopDraw(final Color color, final float radius, final float quality, final float n, final float n2, final float n3, final float n4, final int n5) {
        this.mc.gameSettings.entityShadows = this.entityShadows;
        this.framebuffer.unbindFramebuffer();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        this.mc.getFramebuffer().bindFramebuffer(true);
        this.red = color.getRed() / 255.0f;
        this.green = color.getGreen() / 255.0f;
        this.blue = color.getBlue() / 255.0f;
        this.radius = radius;
        this.quality = quality;
        this.mc.entityRenderer.disableLightmap();
        RenderHelper.disableStandardItemLighting();
        this.startShader(n, n2, n3, n4, n5);
        this.mc.entityRenderer.setupOverlayRendering();
        this.drawFramebuffer(this.framebuffer);
        this.stopShader();
        this.mc.entityRenderer.disableLightmap();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
    
    public void updateUniforms(final float n, final float n2, final float n3, final float n4, final int n5) {
        GL20.glUniform2f(this.getUniform("resolution"), new ScaledResolution(this.mc).getScaledWidth() / n, new ScaledResolution(this.mc).getScaledHeight() / n);
        GL20.glUniform1f(this.getUniform("time"), this.time);
        GL20.glUniform1f(this.getUniform("moreGradient"), n2);
        GL20.glUniform1f(this.getUniform("Creepy"), n3);
        GL20.glUniform1f(this.getUniform("alpha"), n4);
        GL20.glUniform1i(this.getUniform("NUM_OCTAVES"), n5);
    }
    
    static {
        INSTANCE = new GradientShader();
    }
}
