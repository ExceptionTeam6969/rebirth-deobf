//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn��ǿ��������\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.shaders.impl.fill;

import me.rebirthclient.api.util.shaders.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import java.util.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;

public class AquaShader extends FramebufferShader
{
    public float time;
    public static final AquaShader INSTANCE;
    
    public void startShader(final float n, final Color color, final int n2, final double n3) {
        GL11.glPushMatrix();
        GL20.glUseProgram(this.program);
        if (this.uniformsMap == null) {
            this.uniformsMap = new HashMap();
            this.setupUniforms();
        }
        this.updateUniforms(n, color, n2, n3);
    }
    
    public AquaShader() {
        super("aqua.frag");
    }
    
    public void stopDraw(final Color color, final float radius, final float quality, final float n, final int n2, final double n3) {
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
        this.startShader(n, color, n2, n3);
        this.mc.entityRenderer.setupOverlayRendering();
        this.drawFramebuffer(this.framebuffer);
        this.stopShader();
        this.mc.entityRenderer.disableLightmap();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
    
    public void updateUniforms(final float n, final Color color, final int n2, final double n3) {
        GL20.glUniform2f(this.getUniform("resolution"), new ScaledResolution(this.mc).getScaledWidth() / n, new ScaledResolution(this.mc).getScaledHeight() / n);
        GL20.glUniform1f(this.getUniform("time"), this.time);
        GL20.glUniform4f(this.getUniform("rgba"), color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GL20.glUniform1i(this.getUniform("lines"), n2);
        GL20.glUniform1f(this.getUniform("tau"), (float)n3);
    }
    
    static {
        INSTANCE = new AquaShader();
    }
    
    public void setupUniforms() {
        this.setupUniform("resolution");
        this.setupUniform("time");
        this.setupUniform("rgba");
        this.setupUniform("lines");
        this.setupUniform("tau");
    }
    
    public void update(final double n) {
        this.time += (float)n;
    }
}
