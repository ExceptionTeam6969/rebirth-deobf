//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.gui.click.items.other;

import org.lwjgl.util.vector.*;
import org.lwjgl.opengl.*;
import me.rebirthclient.api.util.*;
import org.lwjgl.input.*;
import net.minecraft.util.math.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.managers.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;
import java.util.*;

public class Particle
{
    private float alpha;
    private final Vector2f pos;
    private final Vector2f velocity;
    private float size;
    private static final Random random;
    
    public float getSize() {
        return this.size;
    }
    
    public float getDistanceTo(final Particle particle) {
        return this.getDistanceTo(particle.getX(), particle.getY());
    }
    
    static {
        random = new Random();
    }
    
    public Particle(final Vector2f velocity, final float n, final float n2, final float size) {
        this.velocity = velocity;
        this.pos = new Vector2f(n, n2);
        this.size = size;
    }
    
    public float getY() {
        return this.pos.getY();
    }
    
    public float getAlpha() {
        return this.alpha;
    }
    
    public static Particle getParticle() {
        return new Particle(new Vector2f((float)(Math.random() * 3.0 - 1.0), (float)(Math.random() * 3.0 - 1.0)), (float)Particle.random.nextInt(Display.getWidth()), (float)Particle.random.nextInt(Display.getHeight()), (float)(Math.random() * 4.0) + 2.0f);
    }
    
    public void setup(final int n, final float n2) {
        final Vector2f pos = this.pos;
        pos.x += this.velocity.getX() * n * (n2 / 2.0f);
        final Vector2f pos2 = this.pos;
        pos2.y += this.velocity.getY() * n * (n2 / 2.0f);
        if (this.alpha < 180.0f) {
            this.alpha += 0.75f;
        }
        if (this.pos.getX() > Display.getWidth()) {
            this.pos.setX(0.0f);
        }
        if (this.pos.getX() < 0.0f) {
            this.pos.setX((float)Display.getWidth());
        }
        if (this.pos.getY() > Display.getHeight()) {
            this.pos.setY(0.0f);
        }
        if (this.pos.getY() < 0.0f) {
            this.pos.setY((float)Display.getHeight());
        }
    }
    
    public void setX(final float x) {
        this.pos.setX(x);
    }
    
    public void setSize(final float size) {
        this.size = size;
    }
    
    public float getDistanceTo(final float n, final float n2) {
        return (float)Util.getDistance(this.getX(), this.getY(), n, n2);
    }
    
    public void setY(final float y) {
        this.pos.setY(y);
    }
    
    public float getX() {
        return this.pos.getX();
    }
    
    public static class Util
    {
        private final List particles;
        
        public void drawParticles() {
            GL11.glPushMatrix();
            GL11.glEnable(3042);
            GL11.glDisable(3553);
            GL11.glBlendFunc(770, 771);
            GL11.glDisable(2884);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            if (Wrapper.mc.currentScreen == null) {
                return;
            }
            final Iterator<Particle> iterator = (Iterator<Particle>)this.particles.iterator();
            if (!iterator.hasNext()) {
                GL11.glPushMatrix();
                GL11.glTranslatef(0.5f, 0.5f, 0.5f);
                GL11.glNormal3f(0.0f, 1.0f, 0.0f);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glEnable(3553);
                GL11.glPopMatrix();
                GL11.glDepthMask(true);
                GL11.glEnable(2884);
                GL11.glEnable(2929);
                GL11.glDisable(3042);
                GL11.glPopMatrix();
                return;
            }
            final Particle particle = iterator.next();
            particle.setup(2, 0.1f);
            final int n = Mouse.getEventX() * Wrapper.mc.currentScreen.width / Wrapper.mc.displayWidth;
            final int n2 = Wrapper.mc.currentScreen.height - Mouse.getEventY() * Wrapper.mc.currentScreen.height / Wrapper.mc.displayHeight - 1;
            final float n3 = (float)MathHelper.clamp(particle.getAlpha() - particle.getAlpha() / 300.0f * getDistance((float)n, (float)n2, particle.getX(), particle.getY()), 0.0, (double)particle.getAlpha());
            final Color injectAlpha = ColorUtil.injectAlpha(((boolean)ClickGui.INSTANCE.colorParticles.getValue()) ? Managers.COLORS.getCurrent() : new Color(-1714829883), (int)n3);
            GL11.glColor4f(injectAlpha.getRed() / 255.0f, injectAlpha.getGreen() / 255.0f, injectAlpha.getBlue() / 255.0f, injectAlpha.getAlpha() / 255.0f);
            GL11.glPointSize(particle.getSize());
            GL11.glBegin(0);
            GL11.glVertex2f(particle.getX(), particle.getY());
            GL11.glEnd();
            final float n4 = 0.0f;
            final Particle particle2 = null;
            final Iterator<Particle> iterator2 = this.particles.iterator();
            if (iterator2.hasNext()) {
                final Particle particle3 = iterator2.next();
                final float distanceTo = particle.getDistanceTo(particle3);
                if (distanceTo <= 300.0f && (getDistance((float)n, (float)n2, particle.getX(), particle.getY()) <= 300.0 || getDistance((float)n, (float)n2, particle3.getX(), particle3.getY()) <= 300.0) && (n4 > 0.0f && distanceTo > n4)) {
                    return;
                }
            }
            else {
                if (particle2 == null) {
                    return;
                }
                this.drawTracer(particle.getX(), particle.getY(), particle2.getX(), particle2.getY(), injectAlpha, ColorUtil.injectAlpha(new Color(8618112), (int)n3), injectAlpha);
            }
        }
        
        public void addParticle(final int n) {
            int n2 = 0;
            if (n2 < n) {
                this.particles.add(Particle.getParticle());
                ++n2;
            }
        }
        
        private void drawTracer(final float n, final float n2, final float n3, final float n4, final Color color, final Color color2, final Color color3) {
            GL11.glPushMatrix();
            GL11.glDisable(3553);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glShadeModel(7425);
            GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            GL11.glLineWidth(0.6f);
            GL11.glBegin(1);
            GL11.glVertex2f(n, n2);
            GL11.glColor4f(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f);
            float n5;
            if (n2 >= n4) {
                n5 = n4 + (n2 - n4) / 2.0f;
            }
            else {
                n5 = n2 + (n4 - n2) / 2.0f;
            }
            float n6;
            if (n >= n3) {
                n6 = n3 + (n - n3) / 2.0f;
            }
            else {
                n6 = n + (n3 - n) / 2.0f;
            }
            GL11.glVertex2f(n6, n5);
            GL11.glEnd();
            GL11.glBegin(1);
            GL11.glColor4f(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f);
            GL11.glVertex2f(n6, n5);
            GL11.glColor4f(color3.getRed() / 255.0f, color3.getGreen() / 255.0f, color3.getBlue() / 255.0f, color3.getAlpha() / 255.0f);
            GL11.glVertex2f(n3, n4);
            GL11.glEnd();
            GL11.glPopMatrix();
        }
        
        public static double getDistance(final float n, final float n2, final float n3, final float n4) {
            return Math.sqrt((n - n3) * (n - n3) + (n2 - n4) * (n2 - n4));
        }
        
        public Util(final int n) {
            this.particles = new ArrayList();
            this.addParticle(n);
        }
    }
}
