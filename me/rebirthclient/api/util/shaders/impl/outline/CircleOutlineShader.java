//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn��ǿ��������\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.shaders.impl.outline;

import me.rebirthclient.api.util.shaders.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;
import java.util.*;
import net.minecraft.client.renderer.*;

public final class CircleOutlineShader extends FramebufferShader
{
    public float time;
    public static final CircleOutlineShader INSTANCE;
    
    public void updateUniforms(final Color color, final float n, final float n2, final boolean b, final int n3, final float n4, final float n5, final float n6) {
        GL20.glUniform1i(this.getUniform("texture"), 0);
        GL20.glUniform2f(this.getUniform("texelSize"), 1.0f / this.mc.displayWidth * (n * n2), 1.0f / this.mc.displayHeight * (n * n2));
        GL20.glUniform3f(this.getUniform("colors"), color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f);
        GL20.glUniform1f(this.getUniform("divider"), 140.0f);
        GL20.glUniform1f(this.getUniform("radius"), n);
        GL20.glUniform1f(this.getUniform("maxSample"), 10.0f);
        GL20.glUniform1f(this.getUniform("alpha0"), b ? -1.0f : (n3 / 255.0f));
        GL20.glUniform2f(this.getUniform("resolution"), new ScaledResolution(this.mc).getScaledWidth() / n4, new ScaledResolution(this.mc).getScaledHeight() / n4);
        GL20.glUniform1f(this.getUniform("time"), this.time);
        GL20.glUniform1f(this.getUniform("PI"), n5);
        GL20.glUniform1f(this.getUniform("rad"), n6);
    }
    
    public void update(final double n) {
        this.time += (float)n;
    }
    
    public void setupUniforms() {
        this.setupUniform("texture");
        this.setupUniform("texelSize");
        this.setupUniform("colors");
        this.setupUniform("divider");
        this.setupUniform("radius");
        this.setupUniform("maxSample");
        this.setupUniform("alpha0");
        this.setupUniform("resolution");
        this.setupUniform("time");
        this.setupUniform("PI");
        this.setupUniform("rad");
    }
    
    static {
        INSTANCE = new CircleOutlineShader();
    }
    
    public void startShader(final Color color, final float n, final float n2, final boolean b, final int n3, final float n4, final float n5, final float n6) {
        GL11.glPushMatrix();
        GL20.glUseProgram(this.program);
        if (this.uniformsMap == null) {
            this.uniformsMap = new HashMap();
            this.setupUniforms();
        }
        this.updateUniforms(color, n, n2, b, n3, n4, n5, n6);
    }
    
    public CircleOutlineShader() {
        super("circleOutline.frag");
        this.time = 0.0f;
    }
    
    public void stopDraw(final Color color, final float n, final float n2, final boolean b, final int n3, final float n4, final float n5, final float n6) {
        this.mc.gameSettings.entityShadows = this.entityShadows;
        this.framebuffer.unbindFramebuffer();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        this.mc.getFramebuffer().bindFramebuffer(true);
        this.mc.entityRenderer.disableLightmap();
        RenderHelper.disableStandardItemLighting();
        this.startShader(color, n, n2, b, n3, n4, n5, n6);
        this.mc.entityRenderer.setupOverlayRendering();
        this.drawFramebuffer(this.framebuffer);
        this.stopShader();
        this.mc.entityRenderer.disableLightmap();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
}
