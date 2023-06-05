//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import com.google.common.collect.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.*;
import org.lwjgl.opengl.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;
import java.awt.*;
import net.minecraft.util.math.*;
import com.ibm.icu.math.*;

public class DMGParticles extends Module
{
    private final Setting deleteAfter;
    private final Map hpData;
    private final List particles;
    
    @Override
    public void onDisable() {
        this.particles.clear();
    }
    
    public DMGParticles() {
        super("DMGParticles", "show damage", Category.RENDER);
        this.hpData = Maps.newHashMap();
        this.particles = Lists.newArrayList();
        this.deleteAfter = this.add(new Setting("Remove Ticks", 7, 1, 60));
    }
    
    @SubscribeEvent
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        final boolean b = true;
        if (!this.particles.isEmpty()) {
            final Iterator<Particle> iterator = this.particles.iterator();
            if (iterator.hasNext()) {
                final Particle particle = iterator.next();
                if (particle != null) {
                    if (particle.ticks > (int)this.deleteAfter.getValue()) {
                        return;
                    }
                    GlStateManager.pushMatrix();
                    GlStateManager.disableDepth();
                    final Tessellator getInstance = Tessellator.getInstance();
                    getInstance.getBuffer().begin(3, DefaultVertexFormats.POSITION_COLOR);
                    getInstance.draw();
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    GlStateManager.pushMatrix();
                    GlStateManager.enablePolygonOffset();
                    GlStateManager.doPolygonOffset(1.0f, -1500000.0f);
                    GlStateManager.translate(particle.posX - DMGParticles.mc.getRenderManager().renderPosX, particle.posY - DMGParticles.mc.getRenderManager().renderPosY, particle.posZ - DMGParticles.mc.getRenderManager().renderPosZ);
                    GlStateManager.rotate(-DMGParticles.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate(DMGParticles.mc.getRenderManager().playerViewX, (DMGParticles.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
                    GlStateManager.scale(-0.03, -0.03, 0.03);
                    GL11.glDepthMask(false);
                    DMGParticles.mc.fontRenderer.drawStringWithShadow(particle.str, (float)(-DMGParticles.mc.fontRenderer.getStringWidth(particle.str) * 0.5), (float)(-DMGParticles.mc.fontRenderer.FONT_HEIGHT + 1), particle.color.getRGB());
                    GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                    GL11.glDepthMask(true);
                    GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
                    GlStateManager.disablePolygonOffset();
                    GlStateManager.resetColor();
                    GlStateManager.popMatrix();
                }
                return;
            }
        }
        if (b) {
            this.particles.clear();
        }
    }
    
    @Override
    public void onUpdate() {
        if (!this.particles.isEmpty()) {
            final Iterator<Particle> iterator = this.particles.iterator();
            if (iterator.hasNext()) {
                final Particle particle = iterator.next();
                if (particle != null) {
                    ++particle.ticks;
                }
                return;
            }
        }
        final Iterator<Entity> iterator2 = DMGParticles.mc.world.loadedEntityList.iterator();
        if (iterator2.hasNext()) {
            final Entity entity = iterator2.next();
            if (entity instanceof EntityLivingBase) {
                final EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
                final double n = this.hpData.getOrDefault(entityLivingBase.getEntityId(), entityLivingBase.getMaxHealth());
                this.hpData.remove(entity.getEntityId());
                this.hpData.put(entity.getEntityId(), entityLivingBase.getHealth());
                if (n == entityLivingBase.getHealth()) {
                    return;
                }
                final Color yellow = Color.YELLOW;
                final Vec3d vec3d = new Vec3d(entity.posX + Math.random() * 0.5 * ((Math.random() > 0.5) ? -1 : 1), entity.getEntityBoundingBox().minY + (entity.getEntityBoundingBox().maxY - entity.getEntityBoundingBox().minY) * 0.5, entity.posZ + Math.random() * 0.5 * ((Math.random() > 0.5) ? -1 : 1));
                this.particles.add(new Particle(String.valueOf(new BigDecimal(Math.abs(n - entityLivingBase.getHealth())).setScale(1, 4).doubleValue()), vec3d.x, vec3d.y, vec3d.z, yellow));
            }
        }
    }
    
    static class Particle
    {
        public final double posZ;
        public final double posY;
        public final String str;
        public final double posX;
        public final Color color;
        public int ticks;
        
        public Particle(final String str, final double posX, final double posY, final double posZ, final Color color) {
            this.str = str;
            this.posX = posX;
            this.posY = posY;
            this.posZ = posZ;
            this.color = color;
            this.ticks = 0;
        }
    }
}
