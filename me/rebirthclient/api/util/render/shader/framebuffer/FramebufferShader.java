//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.render.shader.framebuffer;

import me.rebirthclient.api.util.render.shader.*;
import net.minecraft.client.shader.*;
import java.awt.*;
import me.rebirthclient.api.util.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;
import me.rebirthclient.asm.accessors.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.*;

public abstract class FramebufferShader extends Shader
{
    protected float quality;
    protected float green;
    protected static int lastScale;
    protected float blue;
    protected static int lastScaleWidth;
    protected static int lastScaleHeight;
    protected float alpha;
    protected float radius;
    private static Framebuffer framebuffer;
    private boolean entityShadows;
    protected float red;
    
    public void stopDraw(final Color color, final float radius, final float quality) {
        Wrapper.mc.gameSettings.entityShadows = this.entityShadows;
        GlStateManager.enableBlend();
        GL11.glBlendFunc(770, 771);
        Wrapper.mc.getFramebuffer().bindFramebuffer(true);
        this.red = color.getRed() / 255.0f;
        this.green = color.getGreen() / 255.0f;
        this.blue = color.getBlue() / 255.0f;
        this.alpha = color.getAlpha() / 255.0f;
        this.radius = radius;
        this.quality = quality;
        Wrapper.mc.entityRenderer.disableLightmap();
        RenderHelper.disableStandardItemLighting();
        this.startShader();
        Wrapper.mc.entityRenderer.setupOverlayRendering();
        this.drawFramebuffer(FramebufferShader.framebuffer);
        this.stopShader();
        Wrapper.mc.entityRenderer.disableLightmap();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
    
    public void drawFramebuffer(final Framebuffer framebuffer) {
        final ScaledResolution scaledResolution = new ScaledResolution(Wrapper.mc);
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
    
    public FramebufferShader(final String s) {
        super(s);
        this.alpha = 1.0f;
        this.radius = 2.0f;
        this.quality = 1.0f;
    }
    
    public void startDraw(final float n) {
        GlStateManager.enableAlpha();
        GlStateManager.pushMatrix();
        GlStateManager.pushAttrib();
        (FramebufferShader.framebuffer = this.setupFrameBuffer(FramebufferShader.framebuffer)).bindFramebuffer(true);
        this.entityShadows = Wrapper.mc.gameSettings.entityShadows;
        Wrapper.mc.gameSettings.entityShadows = false;
        ((IEntityRenderer)Wrapper.mc.entityRenderer).invokeSetupCameraTransform(n, 0);
    }
    
    public Framebuffer setupFrameBuffer(Framebuffer framebuffer) {
        if (Display.isActive() || Display.isVisible()) {
            if (framebuffer != null) {
                framebuffer.framebufferClear();
                final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
                final int getScaleFactor = scaledResolution.getScaleFactor();
                final int getScaledWidth = scaledResolution.getScaledWidth();
                final int getScaledHeight = scaledResolution.getScaledHeight();
                if (FramebufferShader.lastScale != getScaleFactor || FramebufferShader.lastScaleWidth != getScaledWidth || FramebufferShader.lastScaleHeight != getScaledHeight) {
                    framebuffer.deleteFramebuffer();
                    framebuffer = new Framebuffer(Wrapper.mc.displayWidth, Wrapper.mc.displayHeight, true);
                    framebuffer.framebufferClear();
                }
                FramebufferShader.lastScale = getScaleFactor;
                FramebufferShader.lastScaleWidth = getScaledWidth;
                FramebufferShader.lastScaleHeight = getScaledHeight;
            }
            else {
                framebuffer = new Framebuffer(Wrapper.mc.displayWidth, Wrapper.mc.displayHeight, true);
            }
        }
        else if (framebuffer == null) {
            framebuffer = new Framebuffer(Wrapper.mc.displayWidth, Wrapper.mc.displayHeight, true);
        }
        return framebuffer;
    }
}
