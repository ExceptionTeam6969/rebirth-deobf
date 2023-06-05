//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.render.shaders;

import net.minecraft.client.shader.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;

public abstract class FramebufferShader extends Shader
{
    protected float red;
    protected int animationSpeed;
    protected float radius;
    protected float alpha;
    protected static int lastScaleHeight;
    private boolean entityShadows;
    protected float divider;
    protected float maxSample;
    protected float blue;
    protected boolean animation;
    protected float quality;
    protected static int lastScaleWidth;
    protected float green;
    protected static int lastScale;
    private static Framebuffer framebuffer;
    public final Minecraft mc;
    
    public Framebuffer setupFrameBuffer(Framebuffer framebuffer) {
        if (Display.isActive() || Display.isVisible()) {
            if (framebuffer != null) {
                framebuffer.framebufferClear();
                final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
                final int getScaleFactor = scaledResolution.getScaleFactor();
                final int getScaledWidth = scaledResolution.getScaledWidth();
                final int getScaledHeight = scaledResolution.getScaledHeight();
                if (FramebufferShader.lastScale != getScaleFactor || FramebufferShader.lastScaleWidth != getScaledWidth || FramebufferShader.lastScaleHeight != getScaledHeight) {
                    framebuffer.deleteFramebuffer();
                    framebuffer = new Framebuffer(this.mc.displayWidth, this.mc.displayHeight, true);
                    framebuffer.framebufferClear();
                }
                FramebufferShader.lastScale = getScaleFactor;
                FramebufferShader.lastScaleWidth = getScaledWidth;
                FramebufferShader.lastScaleHeight = getScaledHeight;
            }
            else {
                framebuffer = new Framebuffer(this.mc.displayWidth, this.mc.displayHeight, true);
            }
        }
        else if (framebuffer == null) {
            framebuffer = new Framebuffer(this.mc.displayWidth, this.mc.displayHeight, true);
        }
        return framebuffer;
    }
    
    public void setShaderParams(final Boolean b, final int n, final Color color, final float n2, final float divider, final float maxSample) {
        this.setShaderParams(b, n, color, n2);
        this.divider = divider;
        this.maxSample = maxSample;
    }
    
    public void setShaderParams(final Boolean b, final int animationSpeed, final Color color) {
        this.animation = b;
        this.animationSpeed = animationSpeed;
        this.red = color.getRed() / 255.0f;
        this.green = color.getGreen() / 255.0f;
        this.blue = color.getBlue() / 255.0f;
        this.alpha = color.getAlpha() / 255.0f;
    }
    
    public void setShaderParams(final Boolean b, final int n, final Color color, final float radius) {
        this.setShaderParams(b, n, color);
        this.radius = radius;
    }
    
    public FramebufferShader(final String s) {
        super(s);
        this.mc = Minecraft.getMinecraft();
        this.alpha = 1.0f;
        this.radius = 2.0f;
        this.quality = 1.0f;
        this.animation = true;
        this.animationSpeed = 1;
        this.divider = 1.0f;
        this.maxSample = 1.0f;
    }
    
    public void drawFramebuffer(final Framebuffer framebuffer) {
        final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        GL11.glBindTexture(3553, framebuffer.framebufferTexture);
        GL11.glBegin(7);
        GL11.glTexCoord2d(0.0, 1.0);
        GL11.glVertex2d(0.0, 0.0);
        GL11.glTexCoord2d(0.0, 0.0);
        GL11.glVertex2d(0.0, (double)scaledResolution.getScaledHeight());
        GL11.glTexCoord2d(1.0, 0.0);
        GL11.glVertex2d((double)scaledResolution.getScaledWidth(), (double)scaledResolution.getScaledHeight());
        GL11.glTexCoord2d(1.0, 1.0);
        GL11.glVertex2d((double)scaledResolution.getScaledWidth(), 0.0);
        GL11.glEnd();
        GL20.glUseProgram(0);
    }
    
    public void stopDraw() {
        this.mc.gameSettings.entityShadows = this.entityShadows;
        GlStateManager.enableBlend();
        GL11.glBlendFunc(770, 771);
        this.mc.getFramebuffer().bindFramebuffer(true);
        this.mc.entityRenderer.disableLightmap();
        RenderHelper.disableStandardItemLighting();
        this.startShader();
        this.mc.entityRenderer.setupOverlayRendering();
        this.drawFramebuffer(FramebufferShader.framebuffer);
        this.stopShader();
        this.mc.entityRenderer.disableLightmap();
        GlStateManager.popMatrix();
    }
    
    public void stopDraw(final Color color, final float radius, final float quality, final Runnable... array) {
        this.mc.gameSettings.entityShadows = this.entityShadows;
        GlStateManager.enableBlend();
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
        this.startShader();
        this.mc.entityRenderer.setupOverlayRendering();
        this.drawFramebuffer(FramebufferShader.framebuffer);
        this.stopShader();
        this.mc.entityRenderer.disableLightmap();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
    
    public void startDraw(final float n) {
        GlStateManager.enableAlpha();
        GlStateManager.pushMatrix();
        (FramebufferShader.framebuffer = this.setupFrameBuffer(FramebufferShader.framebuffer)).bindFramebuffer(true);
        this.entityShadows = this.mc.gameSettings.entityShadows;
        this.mc.gameSettings.entityShadows = false;
    }
}
