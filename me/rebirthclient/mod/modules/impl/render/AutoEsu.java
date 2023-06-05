//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.util.*;

public class AutoEsu extends Module
{
    private final Setting noFriend;
    private final Setting mode;
    private final Setting height;
    private final Setting offsetY;
    private final Setting width;
    private final Setting offsetX;
    
    public AutoEsu() {
        super("AutoEsu", "IQ RuiNan", Category.RENDER);
        this.offsetX = this.add(new Setting("OffsetX", -42, -500, 500));
        this.offsetY = this.add(new Setting("OffsetY", -27, -500, 500));
        this.width = this.add(new Setting("Width", 84, 0, 500));
        this.height = this.add(new Setting("Height", 40, 0, 500));
        this.noFriend = this.add(new Setting("NoFriend", true));
        this.mode = this.add(new Setting("Mode", Mode.RuiNan));
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        final Iterator<EntityPlayer> iterator = (Iterator<EntityPlayer>)AutoEsu.mc.world.playerEntities.iterator();
        if (!iterator.hasNext()) {
            return;
        }
        final EntityPlayer entityPlayer = iterator.next();
        if (entityPlayer != 0) {
            return;
        }
        this.drawBurrowESP(entityPlayer.posX, entityPlayer.posY + 1.5, entityPlayer.posZ);
    }
    
    private void drawBurrowESP(final double n, final double n2, final double n3) {
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, -1500000.0f);
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        final double n4 = 0.0245;
        GlStateManager.translate(n - AutoEsu.mc.getRenderManager().renderPosX, n2 - AutoEsu.mc.getRenderManager().renderPosY, n3 - AutoEsu.mc.getRenderManager().renderPosZ);
        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-AutoEsu.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(AutoEsu.mc.getRenderManager().playerViewX, (AutoEsu.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-n4, -n4, -n4);
        RenderUtil.glColor(new Color(255, 255, 255, 120));
        RenderUtil.drawCircle(1.5f, -5.0f, 16.0f, ColorUtil.injectAlpha(new Color(255, 255, 255, 120).getRGB(), 0));
        GlStateManager.enableAlpha();
        if (this.mode.getValue() == Mode.ShengJie) {
            AutoEsu.mc.getTextureManager().bindTexture(new ResourceLocation("textures/rebirth/mugshot/shengjie.png"));
        }
        else if (this.mode.getValue() == Mode.RuiNan) {
            AutoEsu.mc.getTextureManager().bindTexture(new ResourceLocation("textures/rebirth/mugshot/ruinan.png"));
        }
        else if (this.mode.getValue() == Mode.ShanZhu) {
            AutoEsu.mc.getTextureManager().bindTexture(new ResourceLocation("textures/rebirth/mugshot/shanzhu.png"));
        }
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        RenderUtil.drawModalRect((int)this.offsetX.getValue(), (int)this.offsetY.getValue(), 0.0f, 0.0f, 12, 12, (int)this.width.getValue(), (int)this.height.getValue(), 12.0f, 12.0f);
        GlStateManager.disableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.disablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
        GlStateManager.popMatrix();
    }
    
    public enum Mode
    {
        ShanZhu("ShanZhu", 2), 
        ShengJie("ShengJie", 1), 
        RuiNan("RuiNan", 0);
        
        private static final Mode[] $VALUES;
        
        static {
            $VALUES = new Mode[] { Mode.RuiNan, Mode.ShengJie, Mode.ShanZhu };
        }
        
        private Mode(final String s, final int n) {
        }
    }
}
