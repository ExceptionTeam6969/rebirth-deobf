//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.util.math.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;
import me.rebirthclient.mod.modules.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.*;

public class BreadCrumbs extends Module
{
    private final Setting lineWidth;
    private final Setting secondColor;
    private final Setting timeExisted;
    protected final Map trails;
    private final Setting xp;
    private final Setting color;
    private final Setting self;
    private final Setting arrow;
    
    public void drawTrail(final ItemTrail itemTrail) {
        final int n = 255 - MathHelper.clamp((int)(MathUtil.normalize((double)(System.currentTimeMillis() - itemTrail.timer.getStartTime()), 0.0, (double)(int)this.timeExisted.getValue()) * 255.0), 0, 255);
        final Color color = new Color(((Color)this.secondColor.getValue()).getRed(), ((Color)this.secondColor.getValue()).getGreen(), ((Color)this.secondColor.getValue()).getBlue(), itemTrail.timer.isPaused() ? 255 : n);
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        GlStateManager.disableAlpha();
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(((Number)this.lineWidth.getValue()).floatValue());
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        this.buildBuffer(getBuffer, itemTrail, new Color(((Color)this.color.getValue()).getRGB()), this.secondColor.booleanValue ? color : new Color(((Color)this.color.getValue()).getRGB()));
        getInstance.draw();
        GlStateManager.depthMask(true);
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.popMatrix();
        GL11.glEnable(3553);
        GL11.glPolygonMode(1032, 6914);
    }
    
    public static void addBuilderVertex(final BufferBuilder bufferBuilder, final double n, final double n2, final double n3, final Color color) {
        bufferBuilder.pos(n, n2, n3).color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f).endVertex();
    }
    
    public BreadCrumbs() {
        super("BreadCrumbs", "Draws trails behind projectiles and you (bread crumbs)", Category.RENDER);
        this.trails = new HashMap();
        this.lineWidth = this.add(new Setting("Width", 0.8f, 0.1f, 3.0f));
        this.timeExisted = this.add(new Setting("Delay", 1000, 100, 3000));
        this.xp = this.add(new Setting("Exp", true));
        this.arrow = this.add(new Setting("Arrows", true));
        this.self = this.add(new Setting("Self", true));
        this.color = this.add(new Setting("Color", new Color(125, 125, 213)).hideAlpha());
        this.secondColor = this.add(new Setting("SecondColor", new Color(12550399)).injectBoolean(false).hideAlpha());
    }
    
    public void buildBuffer(final BufferBuilder bufferBuilder, final ItemTrail itemTrail, final Color color, final Color color2) {
        final Iterator<Position> iterator = itemTrail.positions.iterator();
        if (iterator.hasNext()) {
            final Position position = iterator.next();
            final Vec3d updateToCamera = updateToCamera(position.pos);
            addBuilderVertex(bufferBuilder, updateToCamera.x, updateToCamera.y, updateToCamera.z, ColorUtil.interpolate((float)MathUtil.normalize((double)itemTrail.positions.indexOf(position), 0.0, (double)itemTrail.positions.size()), color, color2));
        }
    }
    
    public static Vec3d updateToCamera(final Vec3d vec3d) {
        return new Vec3d(vec3d.x - BreadCrumbs.mc.getRenderManager().viewerPosX, vec3d.y - BreadCrumbs.mc.getRenderManager().viewerPosY, vec3d.z - BreadCrumbs.mc.getRenderManager().viewerPosZ);
    }
    
    static Setting access$000(final BreadCrumbs breadCrumbs) {
        return breadCrumbs.timeExisted;
    }
    
    @Override
    public void onTick() {
        final Iterator<Entity> iterator = BreadCrumbs.mc.world.loadedEntityList.iterator();
        if (iterator.hasNext()) {
            final Entity entity = iterator.next();
            if (entity == 0) {
                if (this.trails.containsKey(entity.getUniqueID())) {
                    if (entity.isDead) {
                        if (((ItemTrail)this.trails.get(entity.getUniqueID())).timer.isPaused()) {
                            ((ItemTrail)this.trails.get(entity.getUniqueID())).timer.resetDelay();
                        }
                        ((ItemTrail)this.trails.get(entity.getUniqueID())).timer.setPaused(false);
                    }
                    else {
                        ((ItemTrail)this.trails.get(entity.getUniqueID())).positions.add(new Position(entity.getPositionVector()));
                    }
                }
                else {
                    this.trails.put(entity.getUniqueID(), new ItemTrail(entity));
                }
            }
            return;
        }
        if (this.self.getValue()) {
            if (this.trails.containsKey(BreadCrumbs.mc.player.getUniqueID())) {
                final ItemTrail itemTrail = this.trails.get(BreadCrumbs.mc.player.getUniqueID());
                itemTrail.timer.resetDelay();
                final ArrayList<Position> list = new ArrayList<Position>();
                final Iterator iterator2 = itemTrail.positions.iterator();
                if (iterator2.hasNext()) {
                    final Position position = iterator2.next();
                    if (System.currentTimeMillis() - position.time > (int)this.timeExisted.getValue()) {
                        list.add(position);
                    }
                    return;
                }
                itemTrail.positions.removeAll(list);
                itemTrail.positions.add(new Position(BreadCrumbs.mc.player.getPositionVector()));
            }
            else {
                this.trails.put(BreadCrumbs.mc.player.getUniqueID(), new ItemTrail((Entity)BreadCrumbs.mc.player));
            }
        }
        else {
            this.trails.remove(BreadCrumbs.mc.player.getUniqueID());
        }
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent renderWorldLastEvent) {
        if (!nullCheck()) {
            final Iterator<Map.Entry<K, ItemTrail>> iterator = this.trails.entrySet().iterator();
            if (iterator.hasNext()) {
                final Map.Entry<K, ItemTrail> entry = iterator.next();
                if (entry.getValue().entity.isDead || BreadCrumbs.mc.world.getEntityByID(entry.getValue().entity.getEntityId()) == null) {
                    if (entry.getValue().timer.isPaused()) {
                        entry.getValue().timer.resetDelay();
                    }
                    entry.getValue().timer.setPaused(false);
                }
                if (!entry.getValue().timer.isPassed()) {
                    this.drawTrail(entry.getValue());
                }
            }
        }
    }
    
    public static class Timer
    {
        long delay;
        long startTime;
        boolean paused;
        
        public Timer() {
            this.startTime = System.currentTimeMillis();
        }
        
        public void setPaused(final boolean paused) {
            this.paused = paused;
        }
        
        public long getTime() {
            return System.currentTimeMillis() - this.startTime;
        }
        
        public void setDelay(final long delay) {
            this.delay = delay;
        }
        
        public void resetDelay() {
            this.startTime = System.currentTimeMillis();
        }
        
        public boolean isPaused() {
            return this.paused;
        }
        
        public boolean isPassed() {
            return !this.paused && System.currentTimeMillis() - this.startTime >= this.delay;
        }
        
        public long getStartTime() {
            return this.startTime;
        }
    }
    
    public static class Position
    {
        public final long time;
        public final Vec3d pos;
        
        @Override
        public int hashCode() {
            return Objects.hash(this.pos, this.time);
        }
        
        public Position(final Vec3d pos) {
            this.pos = pos;
            this.time = System.currentTimeMillis();
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o != null && this.getClass() == o.getClass()) {
                final Position position = (Position)o;
                return this.time == position.time && Objects.equals(this.pos, position.pos);
            }
            return false;
        }
    }
    
    public class ItemTrail
    {
        public final List positions;
        public final Entity entity;
        final BreadCrumbs this$0;
        public final Timer timer;
        
        public ItemTrail(final BreadCrumbs this$0, final Entity entity) {
            this.this$0 = this$0;
            this.entity = entity;
            this.positions = new ArrayList();
            (this.timer = new Timer()).setDelay((int)BreadCrumbs.access$000(this$0).getValue());
            this.timer.setPaused(true);
        }
    }
}
