//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.entity.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.math.*;
import java.util.function.*;
import me.rebirthclient.api.util.render.entity.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.mod.modules.*;
import java.util.*;
import com.mojang.authlib.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;

public class EarthPopChams extends Module
{
    private final Setting selfColor;
    private final Setting fadeTime;
    private final Setting outline;
    private final Setting friendPop;
    private final Setting color;
    private final Setting copyAnimations;
    private final Setting friendColor;
    public static EarthPopChams INSTANCE;
    private final Setting lineWidth;
    private final Setting friendOutline;
    private final List popDataList;
    private final Setting yAnimations;
    private final Setting selfOutline;
    private final Setting selfPop;
    
    @Override
    public void onTotemPop(final EntityPlayer entityPlayer) {
        if (entityPlayer != 0) {
            return;
        }
        this.popDataList.add(new PopData(this.copyPlayer(entityPlayer, (boolean)this.copyAnimations.getValue()), System.currentTimeMillis(), entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, entityPlayer instanceof AbstractClientPlayer && Integer.valueOf("slim".hashCode()).equals(((AbstractClientPlayer)entityPlayer).getSkinType().hashCode())));
    }
    
    private void color(final Color color) {
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
    
    private boolean lambda$new$1(final Color color) {
        return this.selfPop.isOpen();
    }
    
    private boolean lambda$new$0(final Color color) {
        return this.selfPop.isOpen();
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (this.popDataList.isEmpty()) {
            return;
        }
        final Iterator<PopData> iterator = (Iterator<PopData>)this.popDataList.iterator();
        if (iterator.hasNext()) {
            final PopData popData = iterator.next();
            final EntityPlayer player = popData.getPlayer();
            final StaticModelPlayer model = popData.getModel();
            final double n = popData.getX() - EarthPopChams.mc.getRenderManager().viewerPosX;
            final double n2 = popData.getY() - EarthPopChams.mc.getRenderManager().viewerPosY + (double)this.yAnimations.getValue() * (System.currentTimeMillis() - popData.getTime()) / (double)this.fadeTime.getValue();
            final double n3 = popData.getZ() - EarthPopChams.mc.getRenderManager().viewerPosZ;
            GlStateManager.pushMatrix();
            this.startRender();
            GlStateManager.translate(n, n2, n3);
            GlStateManager.rotate(180.0f - model.getYaw(), 0.0f, 1.0f, 0.0f);
            final Color color = this.getColor(popData.getPlayer());
            final Color outlineColor = this.getOutlineColor(popData.getPlayer());
            final float n4 = (float)color.getAlpha();
            final float n5 = (float)outlineColor.getAlpha();
            final float n6 = n4 / (int)this.fadeTime.getValue();
            final float n7 = n5 / (int)this.fadeTime.getValue();
            final int clamp = MathHelper.clamp((int)(n6 * (popData.getTime() + (int)this.fadeTime.getValue() - System.currentTimeMillis())), 0, (int)n4);
            final int clamp2 = MathHelper.clamp((int)(n7 * (popData.getTime() + (int)this.fadeTime.getValue() - System.currentTimeMillis())), 0, (int)n5);
            final Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue(), clamp);
            final Color color3 = new Color(outlineColor.getRed(), outlineColor.getGreen(), outlineColor.getBlue(), clamp2);
            GlStateManager.enableRescaleNormal();
            GlStateManager.scale(-1.0f, -1.0f, 1.0f);
            GlStateManager.scale(player.getEntityBoundingBox().maxX - player.getRenderBoundingBox().minX + 1.0, (double)player.height, player.getEntityBoundingBox().maxZ - player.getEntityBoundingBox().minZ + 1.0);
            GlStateManager.translate(0.0f, -1.501f, 0.0f);
            this.color(color2);
            GL11.glPolygonMode(1032, 6914);
            model.render(0.0625f);
            this.color(color3);
            GL11.glLineWidth((float)this.lineWidth.getValue());
            GL11.glPolygonMode(1032, 6913);
            model.render(0.0625f);
            this.endRender();
            GlStateManager.popMatrix();
            return;
        }
        this.popDataList.removeIf(this::lambda$onRender3D$4);
    }
    
    private boolean lambda$onRender3D$4(final PopData popData) {
        return popData.getTime() + (int)this.fadeTime.getValue() < System.currentTimeMillis();
    }
    
    private Color getColor(final EntityPlayer entityPlayer) {
        if (entityPlayer.equals((Object)EarthPopChams.mc.player)) {
            return (Color)this.selfColor.getValue();
        }
        if (Managers.FRIENDS.isFriend(entityPlayer)) {
            return (Color)this.friendColor.getValue();
        }
        return (Color)this.color.getValue();
    }
    
    public EarthPopChams() {
        super("3arPopChams", "Pop rendering", Category.RENDER);
        this.lineWidth = this.add(new Setting("LineWidth", 1.0f, 0.1f, 3.0f));
        this.color = this.add(new Setting("Color", new Color(80, 80, 255, 80)));
        this.outline = this.add(new Setting("Outline", new Color(80, 80, 255, 255)));
        this.copyAnimations = this.add(new Setting("Copy-Animations", true));
        this.yAnimations = this.add(new Setting("Y-Animation", 0.0, -7.0, 7.0));
        this.fadeTime = this.add(new Setting("Fade-Time", 1500, 0, 5000));
        this.selfPop = this.add(new Setting("Self-Pop", false).setParent());
        this.selfColor = this.add(new Setting("Self-Color", new Color(80, 80, 255, 80), this::lambda$new$0));
        this.selfOutline = this.add(new Setting("Self-Outline", new Color(80, 80, 255, 255), this::lambda$new$1));
        this.friendPop = this.add(new Setting("Friend-Pop", false).setParent());
        this.friendColor = this.add(new Setting("Friend-Color", new Color(45, 255, 45, 80), this::lambda$new$2));
        this.friendOutline = this.add(new Setting("Friend-Outline", new Color(45, 255, 45, 255), this::lambda$new$3));
        this.popDataList = new ArrayList();
        EarthPopChams.INSTANCE = this;
    }
    
    private void startRender() {
        GL11.glPushAttrib(1048575);
        GL11.glPushMatrix();
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glEnable(2884);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4353);
        GL11.glDisable(2896);
    }
    
    private void endRender() {
        GL11.glEnable(2896);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glDepthMask(true);
        GL11.glCullFace(1029);
        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }
    
    private EntityPlayer copyPlayer(final EntityPlayer entityPlayer, final boolean b) {
        final EntityPlayer entityPlayer2 = new EntityPlayer((World)EarthPopChams.mc.world, new GameProfile(UUID.randomUUID(), entityPlayer.getName()), entityPlayer.getItemInUseCount()) {
            final EarthPopChams this$0;
            final int val$count;
            
            public int getItemInUseCount() {
                return this.val$count;
            }
            
            public boolean isSpectator() {
                return false;
            }
            
            public boolean isCreative() {
                return false;
            }
        };
        if (b) {
            entityPlayer2.setSneaking(entityPlayer.isSneaking());
            entityPlayer2.swingProgress = entityPlayer.swingProgress;
            entityPlayer2.limbSwing = entityPlayer.limbSwing;
            entityPlayer2.limbSwingAmount = entityPlayer.prevLimbSwingAmount;
            entityPlayer2.inventory.copyInventory(entityPlayer.inventory);
        }
        entityPlayer2.setPrimaryHand(entityPlayer.getPrimaryHand());
        entityPlayer2.ticksExisted = entityPlayer.ticksExisted;
        entityPlayer2.setEntityId(entityPlayer.getEntityId());
        entityPlayer2.copyLocationAndAnglesFrom((Entity)entityPlayer);
        return entityPlayer2;
    }
    
    private Color getOutlineColor(final EntityPlayer entityPlayer) {
        if (entityPlayer.equals((Object)EarthPopChams.mc.player)) {
            return (Color)this.selfOutline.getValue();
        }
        if (Managers.FRIENDS.isFriend(entityPlayer)) {
            return (Color)this.friendOutline.getValue();
        }
        return (Color)this.outline.getValue();
    }
    
    private boolean lambda$new$3(final Color color) {
        return this.friendPop.isOpen();
    }
    
    private boolean lambda$new$2(final Color color) {
        return this.friendPop.isOpen();
    }
    
    public static class PopData
    {
        private final long time;
        private final EntityPlayer player;
        private final StaticModelPlayer model;
        private final double z;
        private final double x;
        private final double y;
        
        public PopData(final EntityPlayer player, final long time, final double x, final double n, final double z, final boolean b) {
            this.player = player;
            this.time = time;
            this.x = x;
            this.y = n - (player.isSneaking() ? 0.125 : 0.0);
            this.z = z;
            (this.model = new StaticModelPlayer(player, b, 0.0f)).disableArmorLayers();
        }
        
        public double getX() {
            return this.x;
        }
        
        public StaticModelPlayer getModel() {
            return this.model;
        }
        
        public double getZ() {
            return this.z;
        }
        
        public double getY() {
            return this.y;
        }
        
        public EntityPlayer getPlayer() {
            return this.player;
        }
        
        public long getTime() {
            return this.time;
        }
    }
}
