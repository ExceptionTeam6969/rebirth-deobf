//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.util.render.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.*;
import java.util.*;
import me.rebirthclient.api.util.math.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ GuiNewChat.class })
public abstract class MixinGuiNewChat extends Gui
{
    @Shadow
    public boolean isScrolled;
    private float percentComplete;
    private long prevMillis;
    private float animationPercent;
    
    public MixinGuiNewChat() {
        this.prevMillis = System.currentTimeMillis();
    }
    
    @Redirect(method = { "drawChat" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V"))
    private void drawRectHook(final int n, final int n2, final int n3, final int n4, final int n5) {
        final Chat instance = Chat.INSTANCE;
        final ClickGui instance2 = ClickGui.INSTANCE;
        final int n6 = instance.colorRect.getValue() ? (instance2.rainbow.getValue() ? ColorUtil.toARGB(ColorUtil.rainbow((int)instance2.rainbowDelay.getValue()).getRed(), ColorUtil.rainbow((int)instance2.rainbowDelay.getValue()).getGreen(), ColorUtil.rainbow((int)instance2.rainbowDelay.getValue()).getBlue(), 45) : ColorUtil.toARGB(((Color)instance2.color.getValue()).getRed(), ((Color)instance2.color.getValue()).getGreen(), ((Color)instance2.color.getValue()).getBlue(), 45)) : n5;
        if (instance.isOn()) {
            if (instance.rect.getValue()) {
                Gui.drawRect(n, n2, n3, n4, n6);
            }
            else {
                Gui.drawRect(n, n2, n3, n4, 0);
            }
        }
        else {
            Gui.drawRect(n, n2, n3, n4, n5);
        }
    }
    
    @Redirect(method = { "drawChat" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;FFI)I"))
    private int drawStringWithShadow(final FontRenderer fontRenderer, final String s, final float n, final float n2, final int n3) {
        Managers.TEXT.getClass();
        if (s.contains("¡ì(¡ì)")) {
            Wrapper.mc.fontRenderer.drawStringWithShadow(s, n, n2, ColorUtil.injectAlpha((Color)Chat.INSTANCE.color.getValue(), n3 >> 24 & 0xFF).getRGB());
        }
        else {
            Wrapper.mc.fontRenderer.drawStringWithShadow(s, n, n2, n3);
        }
        return 0;
    }
    
    @Redirect(method = { "setChatLine" }, at = @At(value = "INVOKE", target = "Ljava/util/List;size()I", ordinal = 0, remap = false))
    public int drawnChatLinesSize(final List list) {
        return (Chat.INSTANCE.isOn() && (boolean)Chat.INSTANCE.infinite.getValue()) ? -2147483647 : list.size();
    }
    
    @Redirect(method = { "setChatLine" }, at = @At(value = "INVOKE", target = "Ljava/util/List;size()I", ordinal = 2, remap = false))
    public int chatLinesSize(final List list) {
        return (Chat.INSTANCE.isOn() && (boolean)Chat.INSTANCE.infinite.getValue()) ? -2147483647 : list.size();
    }
    
    @Shadow
    public float getChatScale() {
        return Wrapper.mc.gameSettings.chatScale;
    }
    
    private void updatePercentage(final long n) {
        if (this.percentComplete < 1.0f) {
            this.percentComplete += 0.004f * n;
        }
        this.percentComplete = MathUtil.clamp(this.percentComplete, 0.0f, 1.0f);
    }
    
    @Inject(method = { "drawChat" }, at = { @At("HEAD") }, cancellable = true)
    private void modifyChatRendering(final CallbackInfo callbackInfo) {
        final long currentTimeMillis = System.currentTimeMillis();
        final long n = currentTimeMillis - this.prevMillis;
        this.prevMillis = currentTimeMillis;
        this.updatePercentage(n);
        final float n2;
        this.animationPercent = MathUtil.clamp(1.0f - (n2 = this.percentComplete - 1.0f) * n2 * n2 * n2, 0.0f, 1.0f);
    }
    
    @Inject(method = { "drawChat" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;pushMatrix()V", ordinal = 0, shift = At.Shift.AFTER) })
    private void translate(final CallbackInfo callbackInfo) {
        float n = 1.0f;
        if (!this.isScrolled && Chat.INSTANCE.isOn() && (boolean)Chat.INSTANCE.animation.getValue()) {
            n += (9.0f - 9.0f * this.animationPercent) * this.getChatScale();
        }
        GlStateManager.translate(0.0f, n, 0.0f);
    }
    
    @ModifyArg(method = { "drawChat" }, at = @At(value = "INVOKE", target = "Ljava/util/List;get(I)Ljava/lang/Object;", ordinal = 0, remap = false), index = 0)
    private int getLineBeingDrawn(final int n) {
        return n;
    }
    
    @Inject(method = { "printChatMessageWithOptionalDeletion" }, at = { @At("HEAD") })
    private void resetPercentage(final CallbackInfo callbackInfo) {
        this.percentComplete = 0.0f;
    }
    
    @ModifyVariable(method = { "setChatLine" }, at = @At("STORE"), ordinal = 0)
    private List setNewLines(final List list) {
        return list;
    }
}
