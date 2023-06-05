//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import java.awt.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.item.*;
import me.rebirthclient.api.util.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.util.*;
import net.minecraft.block.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;

public class ESP extends Module
{
    private final Setting burrow;
    private final Setting color;
    private final Setting lineColor;
    static final boolean $assertionsDisabled;
    private final Setting items;
    private final Setting players;
    private final Setting textColor;
    private final Setting xp;
    private final Setting page;
    private final Setting pearls;
    private final Setting xpOrbs;
    
    private boolean lambda$new$4(final Players players) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$3(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    static {
        $assertionsDisabled = !ESP.class.desiredAssertionStatus();
    }
    
    private boolean lambda$new$5(final Burrow burrow) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$7(final Color color) {
        return this.page.getValue() == Page.COLORS;
    }
    
    private boolean lambda$new$0(final Items items) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private void drawNameTag(final String s, final BlockPos blockPos) {
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, -1500000.0f);
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GL11.glEnable(3553);
        final double n = blockPos.getX() + 0.5;
        final double n2 = blockPos.getY() + 0.7;
        final double n3 = blockPos.getZ() + 0.5;
        final float n4 = 0.030833336f;
        GlStateManager.translate(n - ESP.mc.getRenderManager().renderPosX, n2 - ESP.mc.getRenderManager().renderPosY, n3 - ESP.mc.getRenderManager().renderPosZ);
        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-ESP.mc.player.rotationYaw, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(ESP.mc.player.rotationPitch, (ESP.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-n4, -n4, n4);
        float n5 = (int)ESP.mc.player.getDistance(n, n2, n3) / 2.0f / 3.0f;
        if (n5 < 1.0f) {
            n5 = 1.0f;
        }
        GlStateManager.scale(n5, n5, n5);
        GlStateManager.translate(-(Managers.TEXT.getStringWidth(s) / 2.0), 0.0, 0.0);
        Managers.TEXT.drawStringWithShadow(s, 0.0f, 6.0f, this.textColor.booleanValue ? ((Color)this.textColor.getValue()).getRGB() : -1);
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.disablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
        GlStateManager.popMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    private boolean lambda$new$8(final Color color) {
        return this.page.getValue() == Page.COLORS;
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (fullNullCheck()) {
            return;
        }
        final Iterator<Entity> iterator = ESP.mc.world.loadedEntityList.iterator();
        if (iterator.hasNext()) {
            final Entity entity = iterator.next();
            if ((entity != ESP.mc.player && entity instanceof EntityPlayer && this.players.getValue() == Players.BOX && !((EntityPlayer)entity).isSpectator()) || (entity instanceof EntityExpBottle && (boolean)this.xp.getValue()) || (entity instanceof EntityXPOrb && (boolean)this.xpOrbs.getValue()) || (entity instanceof EntityEnderPearl && (boolean)this.pearls.getValue()) || (entity instanceof EntityItem && this.items.getValue() == Items.BOX)) {
                RenderUtil.drawEntityBoxESP(entity, (Color)this.color.getValue(), this.lineColor.booleanValue, (Color)this.lineColor.getValue(), 1.0f, true, true, ((Color)this.color.getValue()).getAlpha());
            }
            return;
        }
        final Iterator<Entity> iterator2 = ESP.mc.world.loadedEntityList.iterator();
        if (iterator2.hasNext()) {
            final Entity entity2 = iterator2.next();
            if (entity2 instanceof EntityItem && this.items.getValue() == Items.TEXT) {
                final ItemStack getItem = ((EntityItem)entity2).getItem();
                this.drawNameTag(getItem.getDisplayName() + ((getItem.isStackable() && getItem.getCount() >= 2) ? (" x" + getItem.getCount()) : ""), InterpolationUtil.getInterpolatedPos(entity2, ESP.mc.getRenderPartialTicks(), true));
            }
            return;
        }
        final Iterator<EntityPlayer> iterator3 = ESP.mc.world.playerEntities.iterator();
        if (iterator3.hasNext()) {
            final EntityPlayer entityPlayer = iterator3.next();
            final BlockPos blockPos = new BlockPos(Math.floor(entityPlayer.posX), Math.floor(entityPlayer.posY + 0.2), Math.floor(entityPlayer.posZ));
            if (!entityPlayer.isSpectator() && !entityPlayer.isRiding() && entityPlayer != ESP.mc.player && BlockUtil.getBlock(blockPos) != Blocks.AIR && !BlockUtil.canReplace(blockPos) && !BlockUtil.isStair(BlockUtil.getBlock(blockPos)) && !BlockUtil.isSlab(BlockUtil.getBlock(blockPos)) && !BlockUtil.isFence(BlockUtil.getBlock(blockPos)) && ESP.mc.player.getDistanceSq(blockPos) <= 200.0) {
                if (this.burrow.getValue() == Burrow.PRETTY) {
                    this.drawBurrowESP(blockPos);
                }
                else if (this.burrow.getValue() == Burrow.TEXT) {
                    this.drawNameTag((BlockUtil.getBlock(blockPos) == Blocks.WEB) ? "Web" : "Burrow", blockPos);
                }
            }
        }
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private void drawNameTag(final String s, final Vec3d vec3d) {
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, -1500000.0f);
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GL11.glEnable(3553);
        final double x = vec3d.x;
        final double y = vec3d.y;
        final double z = vec3d.z;
        final Entity getRenderViewEntity = ESP.mc.getRenderViewEntity();
        assert getRenderViewEntity != null;
        final double getDistance = getRenderViewEntity.getDistance(x + ESP.mc.getRenderManager().viewerPosX, y + ESP.mc.getRenderManager().viewerPosY, z + ESP.mc.getRenderManager().viewerPosZ);
        double n = 0.0018 + 0.003000000026077032 * getDistance;
        final int n2 = Managers.TEXT.getStringWidth(s) / 2;
        if (getDistance <= 8.0) {
            n = 0.0245;
        }
        GlStateManager.translate(x, y + 0.4000000059604645, z);
        GlStateManager.rotate(-ESP.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(ESP.mc.getRenderManager().playerViewX, (ESP.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-n, -n, n);
        Managers.TEXT.drawStringWithShadow(s, -n2 - 0.1f, (float)(-(ESP.mc.fontRenderer.FONT_HEIGHT - 1)), this.textColor.booleanValue ? ((Color)this.textColor.getValue()).getRGB() : -1);
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.disablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
        GlStateManager.popMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    private void drawBurrowESP(final BlockPos blockPos) {
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, -1500000.0f);
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        final double n = blockPos.getX() + 0.5;
        final double n2 = blockPos.getY() + 0.4;
        final double n3 = blockPos.getZ() + 0.5;
        final int n4 = (int)ESP.mc.player.getDistance(n, n2, n3);
        double n5 = 0.0018f + 0.002f * n4;
        if (n4 <= 8.0) {
            n5 = 0.0245;
        }
        GlStateManager.translate(n - ESP.mc.getRenderManager().renderPosX, n2 - ESP.mc.getRenderManager().renderPosY, n3 - ESP.mc.getRenderManager().renderPosZ);
        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-ESP.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(ESP.mc.getRenderManager().playerViewX, (ESP.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-n5, -n5, -n5);
        RenderUtil.glColor((Color)this.color.getValue());
        RenderUtil.drawCircle(1.5f, -5.0f, 16.0f, ColorUtil.injectAlpha(((Color)this.color.getValue()).getRGB(), 100));
        GlStateManager.enableAlpha();
        final Block block = BlockUtil.getBlock(blockPos);
        if (block == Blocks.ENDER_CHEST) {
            ESP.mc.getTextureManager().bindTexture(new ResourceLocation("textures/rebirth/constant/ingame/echest.png"));
        }
        else if (block == Blocks.WEB) {
            ESP.mc.getTextureManager().bindTexture(new ResourceLocation("textures/rebirth/constant/ingame/web.png"));
        }
        else {
            ESP.mc.getTextureManager().bindTexture(new ResourceLocation("textures/rebirth/constant/ingame/obby.png"));
        }
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        RenderUtil.drawModalRect(-10, -17, 0.0f, 0.0f, 12, 12, 24, 24, 12.0f, 12.0f);
        GlStateManager.disableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.disablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
        GlStateManager.popMatrix();
    }
    
    private boolean lambda$new$2(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$6(final Color color) {
        return this.page.getValue() == Page.COLORS;
    }
    
    public ESP() {
        super("ESP", "Highlights entities through walls in several modes", Category.RENDER);
        this.page = this.add(new Setting("Settings", Page.GLOBAL));
        this.items = this.add(new Setting("Items", Items.BOX, this::lambda$new$0));
        this.xpOrbs = this.add(new Setting("ExpOrbs", false, this::lambda$new$1));
        this.xp = this.add(new Setting("ExpBottles", false, this::lambda$new$2));
        this.pearls = this.add(new Setting("Pearls", true, this::lambda$new$3));
        this.players = this.add(new Setting("Players", Players.BOX, this::lambda$new$4));
        this.burrow = this.add(new Setting("Burrow", Burrow.PRETTY, this::lambda$new$5));
        this.textColor = this.add(new Setting("TextColor", new Color(-1), this::lambda$new$6).injectBoolean(false));
        this.color = this.add(new Setting("Color", new Color(125, 125, 213, 150), this::lambda$new$7));
        this.lineColor = this.add(new Setting("LineColor", new Color(-1493172225, true), this::lambda$new$8).injectBoolean(false));
    }
    
    public enum Page
    {
        GLOBAL("GLOBAL", 1);
        
        private static final Page[] $VALUES;
        
        COLORS("COLORS", 0);
        
        static {
            $VALUES = new Page[] { Page.COLORS, Page.GLOBAL };
        }
        
        private Page(final String s, final int n) {
        }
    }
    
    public enum Players
    {
        private static final Players[] $VALUES;
        
        OFF("OFF", 1), 
        BOX("BOX", 0);
        
        private Players(final String s, final int n) {
        }
        
        static {
            $VALUES = new Players[] { Players.BOX, Players.OFF };
        }
    }
    
    public enum Items
    {
        BOX("BOX", 0);
        
        private static final Items[] $VALUES;
        
        OFF("OFF", 2), 
        TEXT("TEXT", 1);
        
        static {
            $VALUES = new Items[] { Items.BOX, Items.TEXT, Items.OFF };
        }
        
        private Items(final String s, final int n) {
        }
    }
    
    public enum Burrow
    {
        OFF("OFF", 2), 
        PRETTY("PRETTY", 0);
        
        private static final Burrow[] $VALUES;
        
        TEXT("TEXT", 1);
        
        static {
            $VALUES = new Burrow[] { Burrow.PRETTY, Burrow.TEXT, Burrow.OFF };
        }
        
        private Burrow(final String s, final int n) {
        }
    }
}
