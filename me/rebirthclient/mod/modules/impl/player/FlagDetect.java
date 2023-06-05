//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.mod.modules.settings.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import net.minecraft.util.math.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.entity.player.*;
import me.rebirthclient.api.util.render.entity.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.mod.modules.impl.exploit.*;
import net.minecraft.network.play.server.*;
import com.mojang.realmsclient.gui.*;
import me.rebirthclient.api.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class FlagDetect extends Module
{
    private final Setting chams;
    private final Setting color;
    private final Setting notify;
    private CopyOfPlayer player;
    private final Setting fadeTime;
    private final Setting lineColor;
    
    private boolean lambda$new$0(final Integer n) {
        return this.chams.isOpen();
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (fullNullCheck() || !(boolean)this.chams.getValue() || this.player == null) {
            return;
        }
        final EntityPlayer player = this.player.getPlayer();
        final StaticModelPlayer model = this.player.getModel();
        final double n = this.player.getX() - FlagDetect.mc.getRenderManager().viewerPosX;
        final double n2 = this.player.getY() - FlagDetect.mc.getRenderManager().viewerPosY;
        final double n3 = this.player.getZ() - FlagDetect.mc.getRenderManager().viewerPosZ;
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
        final Color color = (Color)this.color.getValue();
        final Color color2 = (Color)(this.lineColor.booleanValue ? this.lineColor.getValue() : ((Color)this.color.getValue()));
        final float n4 = (float)color.getAlpha();
        final float n5 = (float)color2.getAlpha();
        final float n6 = n4 / ((int)this.fadeTime.getValue() * 100);
        final float n7 = n5 / ((int)this.fadeTime.getValue() * 100);
        final int clamp = MathHelper.clamp((int)(n6 * (this.player.getTime() + (int)this.fadeTime.getValue() * 100 - System.currentTimeMillis())), 0, (int)n4);
        final int clamp2 = MathHelper.clamp((int)(n7 * (this.player.getTime() + (int)this.fadeTime.getValue() * 100 - System.currentTimeMillis())), 0, (int)n5);
        final Color injectAlpha = ColorUtil.injectAlpha(color, clamp);
        final Color injectAlpha2 = ColorUtil.injectAlpha(color2, clamp2);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(-1.0f, -1.0f, 1.0f);
        GlStateManager.scale(player.getEntityBoundingBox().maxX - player.getRenderBoundingBox().minX + 1.0, (double)player.height, player.getEntityBoundingBox().maxZ - player.getEntityBoundingBox().minZ + 1.0);
        GlStateManager.translate(0.0f, -1.501f, 0.0f);
        RenderUtil.glColor(injectAlpha);
        GL11.glPolygonMode(1032, 6914);
        model.render(0.0625f);
        RenderUtil.glColor(injectAlpha2);
        GL11.glLineWidth(0.8f);
        GL11.glPolygonMode(1032, 6913);
        model.render(0.0625f);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
        if (this.player.getTime() + (int)this.fadeTime.getValue() * 100 < System.currentTimeMillis()) {
            this.player = null;
        }
    }
    
    private boolean lambda$new$1(final Color color) {
        return this.chams.isOpen();
    }
    
    public FlagDetect() {
        super("FlagDetect", "Detects & notifies you when your player is being flagged", Category.PLAYER);
        this.notify = this.add(new Setting("ChatNotify", true));
        this.chams = this.add(new Setting("Chams", true).setParent());
        this.fadeTime = this.add(new Setting("FadeTime", 15, 1, 50, this::lambda$new$0));
        this.color = this.add(new Setting("Color", new Color(190, 0, 0, 100), this::lambda$new$1));
        this.lineColor = this.add(new Setting("LineColor", new Color(255, 255, 255, 120), this::lambda$new$2).injectBoolean(false));
    }
    
    private boolean lambda$new$2(final Color color) {
        return this.chams.isOpen();
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent packetEvent) {
        if (!fullNullCheck() && spawnCheck() && !Clip.INSTANCE.isOn() && packetEvent.getPacket() instanceof SPacketPlayerPosLook) {
            if (this.notify.getValue()) {
                this.sendMessageWithID(ChatFormatting.RED + "Server lagged you back!", -123);
            }
            if (this.chams.getValue()) {
                this.player = new CopyOfPlayer(EntityUtil.getCopiedPlayer((EntityPlayer)FlagDetect.mc.player), System.currentTimeMillis(), FlagDetect.mc.player.posX, FlagDetect.mc.player.posY, FlagDetect.mc.player.posZ, Integer.valueOf("slim".hashCode()).equals(FlagDetect.mc.player.getSkinType().hashCode()));
            }
        }
    }
}
