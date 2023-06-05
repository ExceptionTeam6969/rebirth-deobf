//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import java.awt.*;
import java.text.*;
import me.rebirthclient.mod.modules.*;
import java.util.concurrent.*;
import java.util.function.*;
import com.mojang.realmsclient.gui.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.client.renderer.*;
import me.rebirthclient.api.util.render.*;
import me.rebirthclient.mod.modules.impl.client.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.api.events.impl.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.util.render.entity.*;
import me.rebirthclient.api.util.*;
import net.minecraft.client.entity.*;

public class LogOutSpots extends Module
{
    private final Setting outline;
    private final Setting rect;
    private final Setting lineColor;
    private final Setting coords;
    static final boolean $assertionsDisabled;
    private final Setting chams;
    private final Setting color;
    private final Setting range;
    private final Setting box;
    private final Setting text;
    private final Setting fillColor;
    protected final Map spots;
    private final Setting time;
    final Date date;
    
    private boolean lambda$new$0(final Color color) {
        return this.chams.isOpen();
    }
    
    private String getLogOutTime() {
        return new SimpleDateFormat("HH:mm:ss").format(this.date);
    }
    
    @Override
    public void onDisable() {
        this.spots.clear();
    }
    
    public LogOutSpots() {
        super("LogOutSpots", "Displays logout spots for players", Category.RENDER);
        this.spots = new ConcurrentHashMap();
        this.date = new Date();
        this.text = this.add(new Setting("Text", true));
        this.range = this.add(new Setting("Range", 150.0f, 50.0f, 500.0f));
        this.rect = this.add(new Setting("Rectangle", true));
        this.outline = this.add(new Setting("Outline", true));
        this.time = this.add(new Setting("Time", true));
        this.coords = this.add(new Setting("Coords", true));
        this.box = this.add(new Setting("Box", true));
        this.color = this.add(new Setting("Color", new Color(-1766449377, true)));
        this.chams = this.add(new Setting("Chams", true).setParent());
        this.fillColor = this.add(new Setting("ChamsColor", new Color(190, 0, 0, 100), this::lambda$new$0));
        this.lineColor = this.add(new Setting("LineColor", new Color(255, 255, 255, 120), this::lambda$new$1).injectBoolean(false));
    }
    
    private void drawNameTag(final String s, final double n, double n2, final double n3, final double n4, final double n5, final double n6) {
        n2 += 0.7;
        final Entity getRenderViewEntity = LogOutSpots.mc.getRenderViewEntity();
        assert getRenderViewEntity != null;
        final double posX = getRenderViewEntity.posX;
        final double posY = getRenderViewEntity.posY;
        final double posZ = getRenderViewEntity.posZ;
        getRenderViewEntity.posX = InterpolationUtil.getInterpolatedDouble(getRenderViewEntity.prevPosX, getRenderViewEntity.posX, LogOutSpots.mc.getRenderPartialTicks());
        getRenderViewEntity.posY = InterpolationUtil.getInterpolatedDouble(getRenderViewEntity.prevPosY, getRenderViewEntity.posY, LogOutSpots.mc.getRenderPartialTicks());
        getRenderViewEntity.posZ = InterpolationUtil.getInterpolatedDouble(getRenderViewEntity.prevPosZ, getRenderViewEntity.posZ, LogOutSpots.mc.getRenderPartialTicks());
        final String string = s + (this.coords.getValue() ? (" XYZ: " + (int)n4 + ", " + (int)n5 + ", " + (int)n6) : "") + (this.time.getValue() ? (" " + ChatFormatting.GRAY + "(" + this.getLogOutTime() + ")") : "");
        final double getDistance = getRenderViewEntity.getDistance(n + LogOutSpots.mc.getRenderManager().viewerPosX, n2 + LogOutSpots.mc.getRenderManager().viewerPosY, n3 + LogOutSpots.mc.getRenderManager().viewerPosZ);
        final int n7 = Managers.TEXT.getStringWidth(string) / 2;
        double n8 = (0.0018 + 5.0 * (getDistance * 0.6000000238418579)) / 1000.0;
        if (getDistance <= 8.0) {
            n8 = 0.0245;
        }
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, -1500000.0f);
        GlStateManager.disableLighting();
        GlStateManager.translate((float)n, (float)n2 + 1.4f, (float)n3);
        GlStateManager.rotate(-LogOutSpots.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(LogOutSpots.mc.getRenderManager().playerViewX, (LogOutSpots.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-n8, -n8, n8);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.enableBlend();
        if (this.rect.getValue()) {
            RenderUtil.drawRect((float)(-n7 - 2), (float)(-(LogOutSpots.mc.fontRenderer.FONT_HEIGHT + 1)), n7 + 2.0f, 1.5f, 1426063360);
        }
        if (this.outline.getValue()) {
            RenderUtil.drawNameTagOutline((float)(-n7 - 2), (float)(-(LogOutSpots.mc.fontRenderer.FONT_HEIGHT + 1)), n7 + 2.0f, 1.5f, 0.8f, ((Color)this.color.getValue()).getRGB(), ((Color)this.color.getValue()).darker().getRGB(), false);
        }
        GlStateManager.disableBlend();
        Managers.TEXT.drawMCString(string, (float)(-n7), FontMod.INSTANCE.isOn() ? ((float)(-(LogOutSpots.mc.fontRenderer.FONT_HEIGHT + 1))) : ((float)(-(LogOutSpots.mc.fontRenderer.FONT_HEIGHT - 1))), ((Color)this.color.getValue()).getRGB(), true);
        getRenderViewEntity.posX = posX;
        getRenderViewEntity.posY = posY;
        getRenderViewEntity.posZ = posZ;
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.disablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
        GlStateManager.popMatrix();
    }
    
    static {
        $assertionsDisabled = !LogOutSpots.class.desiredAssertionStatus();
    }
    
    private boolean lambda$new$1(final Color color) {
        return this.chams.isOpen();
    }
    
    @Override
    public void onTick() {
        final Iterator<LogOutSpot> iterator = this.spots.values().iterator();
        if (iterator.hasNext()) {
            final LogOutSpot logOutSpot = iterator.next();
            if (LogOutSpots.mc.player.getDistanceSq((Entity)logOutSpot.getPlayer()) >= (float)this.range.getValue()) {
                this.spots.remove(logOutSpot.getPlayer().getUniqueID());
            }
        }
    }
    
    @Override
    public void onLogout() {
        this.spots.clear();
    }
    
    @SubscribeEvent
    public void onConnection(final ConnectionEvent connectionEvent) {
        if (LogOutSpots.mc.world.getPlayerEntityByUUID(connectionEvent.getUuid()) != null && Integer.valueOf("¡ìa¡ìl[\u8d5e\u52a9\u672c\u670d]".hashCode()).equals(LogOutSpots.mc.world.getPlayerEntityByUUID(connectionEvent.getUuid()).getName().hashCode())) {
            return;
        }
        if (connectionEvent.getStage() == 0) {
            final EntityPlayer getPlayerEntityByUUID = LogOutSpots.mc.world.getPlayerEntityByUUID(connectionEvent.getUuid());
            if (getPlayerEntityByUUID != null && (boolean)this.text.getValue()) {
                this.sendMessage("¡ìa" + getPlayerEntityByUUID.getName() + " just logged in" + (this.coords.getValue() ? (" at (" + (int)getPlayerEntityByUUID.posX + ", " + (int)getPlayerEntityByUUID.posY + ", " + (int)getPlayerEntityByUUID.posZ + ")!") : "!"));
            }
            this.spots.remove(connectionEvent.getUuid());
        }
        else if (connectionEvent.getStage() == 1) {
            final EntityPlayer player = connectionEvent.getPlayer();
            if (player == null || this.spots.containsKey(player.getUniqueID())) {
                return;
            }
            if (this.text.getValue()) {
                this.sendMessage("¡ìc" + connectionEvent.getName() + " just logged out" + (this.coords.getValue() ? (" at (" + (int)player.posX + ", " + (int)player.posY + ", " + (int)player.posZ + ")!") : "!"));
            }
            this.spots.put(player.getUniqueID(), new LogOutSpot(player));
        }
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        final Iterator<LogOutSpot> iterator = this.spots.values().iterator();
        if (iterator.hasNext()) {
            final LogOutSpot logOutSpot = iterator.next();
            final AxisAlignedBB interpolatedAxis = InterpolationUtil.getInterpolatedAxis(logOutSpot.getBoundingBox());
            if (this.chams.getValue()) {
                final StaticModelPlayer model = logOutSpot.getModel();
                final double n = logOutSpot.getX() - LogOutSpots.mc.getRenderManager().viewerPosX;
                final double n2 = logOutSpot.getY() - LogOutSpots.mc.getRenderManager().viewerPosY;
                final double n3 = logOutSpot.getZ() - LogOutSpots.mc.getRenderManager().viewerPosZ;
                GL11.glPushMatrix();
                GL11.glPushAttrib(1048575);
                GL11.glDisable(3553);
                GL11.glDisable(2896);
                GL11.glDisable(2929);
                GL11.glEnable(2848);
                GL11.glEnable(3042);
                GlStateManager.blendFunc(770, 771);
                GlStateManager.translate(n, n2, n3);
                GlStateManager.rotate(180.0f - model.getYaw(), 0.0f, 1.0f, 0.0f);
                GlStateManager.enableRescaleNormal();
                GlStateManager.scale(-1.0f, -1.0f, 1.0f);
                GlStateManager.scale(interpolatedAxis.maxX - interpolatedAxis.minX + 1.0, interpolatedAxis.maxY - interpolatedAxis.minY, interpolatedAxis.maxZ - interpolatedAxis.minZ + 1.0);
                GlStateManager.translate(0.0f, -1.501f, 0.0f);
                final Color color = (Color)this.fillColor.getValue();
                final Color color2 = (Color)(this.lineColor.booleanValue ? this.lineColor.getValue() : ((Color)this.fillColor.getValue()));
                RenderUtil.glColor(color);
                GL11.glPolygonMode(1032, 6914);
                model.render(0.0625f);
                RenderUtil.glColor(color2);
                GL11.glLineWidth(1.0f);
                GL11.glPolygonMode(1032, 6913);
                model.render(0.0625f);
                GL11.glPopAttrib();
                GL11.glPopMatrix();
            }
            if (this.box.getValue()) {
                RenderUtil.drawBlockOutline(interpolatedAxis, (Color)this.color.getValue(), 1.0f, false);
            }
            this.drawNameTag(logOutSpot.getName(), InterpolationUtil.getInterpolatedDouble(logOutSpot.getPlayer().lastTickPosX, logOutSpot.getPlayer().posX, render3DEvent.getPartialTicks()) - LogOutSpots.mc.getRenderManager().renderPosX, InterpolationUtil.getInterpolatedDouble(logOutSpot.getPlayer().lastTickPosY, logOutSpot.getPlayer().posY, render3DEvent.getPartialTicks()) - LogOutSpots.mc.getRenderManager().renderPosY, InterpolationUtil.getInterpolatedDouble(logOutSpot.getPlayer().lastTickPosZ, logOutSpot.getPlayer().posZ, render3DEvent.getPartialTicks()) - LogOutSpots.mc.getRenderManager().renderPosZ, logOutSpot.getX(), logOutSpot.getY(), logOutSpot.getZ());
        }
    }
    
    @Override
    public void onEnable() {
        this.spots.clear();
    }
    
    protected static class LogOutSpot implements Wrapper
    {
        private final AxisAlignedBB boundingBox;
        private final StaticModelPlayer model;
        private final String name;
        private final double y;
        private final double z;
        private final EntityPlayer player;
        private final double x;
        
        public double getDistance() {
            return LogOutSpot.mc.player.getDistance(this.x, this.y, this.z);
        }
        
        public String getName() {
            return this.name;
        }
        
        public AxisAlignedBB getBoundingBox() {
            return this.boundingBox;
        }
        
        public StaticModelPlayer getModel() {
            return this.model;
        }
        
        public LogOutSpot(final EntityPlayer player) {
            this.name = player.getName();
            (this.model = new StaticModelPlayer(EntityUtil.getCopiedPlayer(player), player instanceof AbstractClientPlayer && Integer.valueOf("slim".hashCode()).equals(((AbstractClientPlayer)player).getSkinType().hashCode()), 0.0f)).disableArmorLayers();
            this.boundingBox = player.getEntityBoundingBox();
            this.x = player.posX;
            this.y = player.posY;
            this.z = player.posZ;
            this.player = player;
        }
        
        public double getX() {
            return this.x;
        }
        
        public EntityPlayer getPlayer() {
            return this.player;
        }
        
        public double getZ() {
            return this.z;
        }
        
        public double getY() {
            return this.y;
        }
    }
}
