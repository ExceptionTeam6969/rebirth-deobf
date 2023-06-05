//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.api.managers.*;
import org.spongepowered.asm.mixin.injection.*;
import org.lwjgl.opengl.*;
import me.rebirthclient.api.util.render.*;

@Mixin({ GuiChat.class })
public abstract class MixinGuiChat
{
    @Shadow
    protected GuiTextField inputField;
    private boolean shouldDrawOutline;
    
    @Inject(method = { "keyTyped(CI)V" }, at = { @At("RETURN") })
    public void keyTypedHook(final char c, final int n, final CallbackInfo callbackInfo) {
        if (Wrapper.mc.currentScreen instanceof GuiChat) {
            this.shouldDrawOutline = this.inputField.getText().startsWith(Managers.COMMANDS.getCommandPrefix());
        }
        else {
            this.shouldDrawOutline = false;
        }
    }
    
    @Inject(method = { "drawScreen" }, at = { @At("TAIL") })
    public void drawScreenHook(final int n, final int n2, final float n3, final CallbackInfo callbackInfo) {
        if (this.shouldDrawOutline) {
            final boolean glIsEnabled = GL11.glIsEnabled(3042);
            final boolean glIsEnabled2 = GL11.glIsEnabled(3553);
            GL11.glDisable(3042);
            GL11.glDisable(3553);
            RenderUtil.glColor(Managers.COLORS.getCurrent());
            GL11.glLineWidth(1.5f);
            GL11.glBegin(1);
            final int n4 = this.inputField.x - 2;
            final int n5 = this.inputField.y - 2;
            final int width = this.inputField.width;
            final int height = this.inputField.height;
            GL11.glVertex2d((double)n4, (double)n5);
            GL11.glVertex2d((double)(n4 + width), (double)n5);
            GL11.glVertex2d((double)(n4 + width), (double)n5);
            GL11.glVertex2d((double)(n4 + width), (double)(n5 + height));
            GL11.glVertex2d((double)(n4 + width), (double)(n5 + height));
            GL11.glVertex2d((double)n4, (double)(n5 + height));
            GL11.glVertex2d((double)n4, (double)(n5 + height));
            GL11.glVertex2d((double)n4, (double)n5);
            GL11.glEnd();
            if (glIsEnabled) {
                GL11.glEnable(3042);
            }
            if (glIsEnabled2) {
                GL11.glEnable(3553);
            }
        }
    }
}
