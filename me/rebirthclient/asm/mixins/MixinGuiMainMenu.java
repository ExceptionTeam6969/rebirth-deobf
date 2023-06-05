//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import net.minecraft.util.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.mod.gui.screen.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import javax.imageio.*;
import net.minecraft.client.renderer.texture.*;
import java.io.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.resources.*;

@Mixin({ GuiMainMenu.class })
public abstract class MixinGuiMainMenu extends GuiScreen
{
    @Shadow
    private int widthCopyright;
    @Shadow
    private int widthCopyrightRest;
    @Shadow
    private float minceraftRoll;
    @Shadow
    private static ResourceLocation MINECRAFT_TITLE_TEXTURES;
    @Shadow
    private static ResourceLocation field_194400_H;
    @Shadow
    private float panoramaTimer;
    @Shadow
    private int openGLWarning2Width;
    @Shadow
    private int openGLWarningX1;
    @Shadow
    private int openGLWarningY1;
    @Shadow
    private int openGLWarningX2;
    @Shadow
    private int openGLWarningY2;
    @Shadow
    private String openGLWarning1;
    @Shadow
    private String openGLWarning2;
    @Shadow
    private ResourceLocation backgroundTexture;
    private boolean isGuiOpen;
    
    @Inject(method = { "keyTyped" }, at = { @At("HEAD") }, cancellable = true)
    protected void keyTyped(final char c, final int n, final CallbackInfo callbackInfo) {
        if (n == ((Bind)ClickGui.INSTANCE.bind.getValue()).getKey()) {
            ClickGui.INSTANCE.enable();
            this.isGuiOpen = true;
        }
        if (n == 1) {
            ClickGui.INSTANCE.disable();
            this.isGuiOpen = false;
        }
        if (this.isGuiOpen) {
            try {
                Gui.INSTANCE.keyTyped(c, n);
            }
            catch (Exception ex) {}
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "drawScreen(IIF)V" }, at = { @At("TAIL") })
    public void drawScreenTailHook(final int n, final int n2, final float n3, final CallbackInfo callbackInfo) {
        if (this.isGuiOpen) {
            Gui.INSTANCE.drawScreen(n, n2, n3);
        }
    }
    
    @Inject(method = { "mouseClicked" }, at = { @At("HEAD") }, cancellable = true)
    public void mouseClickedHook(final int n, final int n2, final int n3, final CallbackInfo callbackInfo) {
        if (this.isGuiOpen) {
            Gui.INSTANCE.mouseClicked(n, n2, n3);
            callbackInfo.cancel();
        }
    }
    
    protected void mouseReleased(final int n, final int n2, final int n3) {
        if (this.isGuiOpen) {
            Gui.INSTANCE.mouseReleased(n, n2, n3);
        }
    }
    
    @Inject(method = { "initGui" }, at = { @At("RETURN") })
    public void initGui2(final CallbackInfo callbackInfo) throws IOException {
        this.buttonList.add(new GuiButton(114514, this.width / 2 + 2, this.height / 4 + 48 + 48, 98, 20, "AltManager"));
        this.backgroundTexture = this.mc.getTextureManager().getDynamicTextureLocation("background", new DynamicTexture(ImageIO.read(Minecraft.class.getResourceAsStream("/assets/minecraft/textures/rebirth/background.png"))));
    }
    
    @Overwrite
    public void drawScreen(final int n, final int n2, final float n3) {
        this.panoramaTimer += n3;
        final int n4 = this.width / 2 - 137;
        this.drawGradientRect(0, 0, this.width, this.height, -2130706433, 16777215);
        this.drawGradientRect(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);
        this.mc.getTextureManager().bindTexture(MixinGuiMainMenu.MINECRAFT_TITLE_TEXTURES);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        if (this.minceraftRoll < 1.0E-4) {
            this.drawTexturedModalRect(n4, 30, 0, 0, 99, 44);
            this.drawTexturedModalRect(n4 + 99, 30, 129, 0, 27, 44);
            this.drawTexturedModalRect(n4 + 99 + 26, 30, 126, 0, 3, 44);
            this.drawTexturedModalRect(n4 + 99 + 26 + 3, 30, 99, 0, 26, 44);
            this.drawTexturedModalRect(n4 + 155, 30, 0, 45, 155, 44);
        }
        else {
            this.drawTexturedModalRect(n4, 30, 0, 0, 155, 44);
            this.drawTexturedModalRect(n4 + 155, 30, 0, 45, 155, 44);
        }
        this.mc.getTextureManager().bindTexture(MixinGuiMainMenu.field_194400_H);
        drawModalRectWithCustomSizedTexture(n4 + 88, 67, 0.0f, 0.0f, 98, 14, 128.0f, 16.0f);
        if (n > this.widthCopyrightRest && n < this.widthCopyrightRest + this.widthCopyright && n2 > this.height - 10 && n2 < this.height && Mouse.isInsideWindow()) {
            drawRect(this.widthCopyrightRest, this.height - 1, this.widthCopyrightRest + this.widthCopyright, this.height, -1);
        }
        if (this.openGLWarning1 != null && !this.openGLWarning1.isEmpty()) {
            drawRect(this.openGLWarningX1 - 2, this.openGLWarningY1 - 2, this.openGLWarningX2 + 2, this.openGLWarningY2 - 1, 1428160512);
            this.drawString(this.fontRenderer, this.openGLWarning1, this.openGLWarningX1, this.openGLWarningY1, -1);
            this.drawString(this.fontRenderer, this.openGLWarning2, (this.width - this.openGLWarning2Width) / 2, this.buttonList.get(0).y - 12, -1);
        }
        GL11.glPushMatrix();
        GL11.glTranslated(this.width / 2.0, this.height / 2.0, 0.0);
        GL11.glScaled(1.2000000476837158, 1.2000000476837158, 0.0);
        final float n5 = -1.0f * ((n - this.width / 2.0f) / (this.width / 16.0f));
        final float n6 = -1.0f * ((n2 - this.height / 2.0f) / (this.height / 9.0f));
        final float n7 = (float)(this.width + 78);
        final float n8 = (float)(this.height + 60);
        final float n9 = 20.0f + n5 - n7 / 2.0f;
        final float n10 = -18.0f + n6 - n8 / 2.0f;
        this.mc.getTextureManager().bindTexture(this.backgroundTexture);
        this.drawTexture(n9, n10, n7, n8);
        GL11.glPopMatrix();
        super.drawScreen(n, n2, n3);
    }
    
    public void drawTexture(final double n, final double n2, final double n3, final double n4) {
        GL11.glEnable(3553);
        GL11.glEnable(3042);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPushMatrix();
        GL11.glBegin(4);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2d(n + n3, n2);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex2d(n, n2);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2d(n, n2 + n4);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2d(n, n2 + n4);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex2d(n + n3, n2 + n4);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2d(n + n3, n2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glDisable(3042);
    }
    
    @Overwrite
    private void addSingleplayerMultiplayerButtons(final int n, final int n2) {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, n, I18n.format("menu.singleplayer", new Object[0])));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, n + n2, I18n.format("menu.multiplayer", new Object[0])));
        this.buttonList.add(new GuiButton(6, this.width / 2 - 100, n + n2 * 2, 98, 20, I18n.format("fml.menu.mods", new Object[0])));
    }
}
