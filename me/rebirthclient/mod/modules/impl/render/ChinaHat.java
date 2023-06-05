//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.events.impl.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;
import me.rebirthclient.api.managers.impl.*;
import net.minecraft.entity.*;
import org.lwjgl.opengl.*;
import me.rebirthclient.mod.modules.*;

public class ChinaHat extends Module
{
    public final Setting points;
    public final Setting firstPerson;
    public final Setting color;
    public final Setting color2;
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (ChinaHat.mc.gameSettings.thirdPersonView != 0 || (boolean)this.firstPerson.getValue()) {
            int n = 0;
            if (n < 400) {
                final float n2 = (float)ColorUtil.getGradientOffset((Color)this.color2.getValue(), (Color)this.color.getValue(), Math.abs(System.currentTimeMillis() / 7L - n / 2) / 120.0).getRGB();
                if (ChinaHat.mc.player.isElytraFlying()) {
                    drawHat((Entity)ChinaHat.mc.player, 0.009 + n * 0.0014, render3DEvent.getPartialTicks(), (int)this.points.getValue(), 2.0f, 1.1f - n * 7.85E-4f - (SneakManager.isSneaking ? 0.07f : 0.03f), (int)n2);
                }
                else if (SneakManager.isSneaking) {
                    drawHat((Entity)ChinaHat.mc.player, 0.009 + n * 0.0014, render3DEvent.getPartialTicks(), (int)this.points.getValue(), 2.0f, 1.1f - n * 7.85E-4f - (SneakManager.isSneaking ? 0.07f : 0.03f), (int)n2);
                }
                else {
                    drawHat((Entity)ChinaHat.mc.player, 0.009 + n * 0.0014, render3DEvent.getPartialTicks(), (int)this.points.getValue(), 2.0f, 2.2f - n * 7.85E-4f - (SneakManager.isSneaking ? 0.07f : 0.03f), (int)n2);
                }
                ++n;
            }
        }
    }
    
    public static void drawHat(final Entity entity, final double n, final float n2, final int n3, final float n4, final float n5, final int n6) {
        GL11.glPushMatrix();
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glDepthMask(false);
        GL11.glLineWidth(n4);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2929);
        GL11.glBegin(3);
        final double n7 = interpolate(entity.prevPosX, entity.posX, n2) - ChinaHat.mc.getRenderManager().viewerPosX;
        final double n8 = interpolate(entity.prevPosY + n5, entity.posY + n5, n2) - ChinaHat.mc.getRenderManager().viewerPosY;
        final double n9 = interpolate(entity.prevPosZ, entity.posZ, n2) - ChinaHat.mc.getRenderManager().viewerPosZ;
        GL11.glColor4f(new Color(n6).getRed() / 255.0f, new Color(n6).getGreen() / 255.0f, new Color(n6).getBlue() / 255.0f, 0.15f);
        int n10 = 0;
        if (n10 <= n3) {
            GL11.glVertex3d(n7 + n * Math.cos(n10 * 3.141592653589793 * 2.0 / n3), n8, n9 + n * Math.sin(n10 * 3.141592653589793 * 2.0 / n3));
            ++n10;
            return;
        }
        GL11.glEnd();
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
        GL11.glDisable(2848);
        GL11.glEnable(2929);
        GL11.glEnable(3553);
        GL11.glPopMatrix();
    }
    
    public static double interpolate(final double n, final double n2, final double n3) {
        return n + (n2 - n) * n3;
    }
    
    public ChinaHat() {
        super("ChinaHat", "ChinaHat", Category.RENDER);
        this.color = this.add(new Setting("Color", new Color(-557395713, true)).hideAlpha());
        this.color2 = this.add(new Setting("SecondColor", new Color(-557395713, true)).hideAlpha());
        this.points = this.add(new Setting("Points", 12, 4, 64));
        this.firstPerson = this.add(new Setting("FirstPerson", false));
    }
}
