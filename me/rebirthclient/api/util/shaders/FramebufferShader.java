//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util.shaders;

import net.minecraft.client.*;
import net.minecraft.client.shader.*;
import java.awt.*;
import net.minecraft.client.renderer.*;
import me.rebirthclient.asm.accessors.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;

public class FramebufferShader extends Shader
{
    protected float blue;
    protected boolean entityShadows;
    protected float radius;
    protected float red;
    protected float alpha;
    protected float green;
    protected Minecraft mc;
    protected Framebuffer framebuffer;
    protected float quality;
    
    public Framebuffer setupFrameBuffer(Framebuffer framebuffer) {
        if (framebuffer == null) {
            return new Framebuffer(this.mc.displayWidth, this.mc.displayHeight, true);
        }
        if (framebuffer.framebufferWidth != this.mc.displayWidth || framebuffer.framebufferHeight != this.mc.displayHeight) {
            framebuffer.deleteFramebuffer();
            framebuffer = new Framebuffer(this.mc.displayWidth, this.mc.displayHeight, true);
        }
        return framebuffer;
    }
    
    public void stopDraw(final Color color, final float radius, final float quality, final float n) {
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
        this.startShader(n);
        this.mc.entityRenderer.setupOverlayRendering();
        this.drawFramebuffer(this.framebuffer);
        this.stopShader();
        this.mc.entityRenderer.disableLightmap();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }
    
    public void startDraw(final float n) {
        GlStateManager.enableAlpha();
        GlStateManager.pushMatrix();
        GlStateManager.pushAttrib();
        (this.framebuffer = this.setupFrameBuffer(this.framebuffer)).framebufferClear();
        this.framebuffer.bindFramebuffer(true);
        this.entityShadows = this.mc.gameSettings.entityShadows;
        this.mc.gameSettings.entityShadows = false;
        ((IEntityRenderer)this.mc.entityRenderer).invokeSetupCameraTransform(n, 0);
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
    
    public FramebufferShader(final String s) {
        super(s);
        this.alpha = 1.0f;
        this.radius = 2.0f;
        this.quality = 1.0f;
        this.mc = Minecraft.getMinecraft();
    }
}
