//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.injection.*;
import me.rebirthclient.mod.modules.impl.misc.*;
import me.rebirthclient.api.util.*;
import net.minecraft.client.gui.inventory.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.managers.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;

@Mixin({ GuiScreen.class })
public abstract class MixinGuiScreen extends Gui
{
    private boolean hoveringShulker;
    private ItemStack shulkerStack;
    private String shulkerName;
    
    @Inject(method = { "renderToolTip" }, at = { @At("HEAD") }, cancellable = true)
    public void renderToolTipHook(final ItemStack shulkerStack, final int n, final int n2, final CallbackInfo callbackInfo) {
        final RenderToolTipEvent renderToolTipEvent = new RenderToolTipEvent(shulkerStack, n, n2);
        MinecraftForge.EVENT_BUS.post((Event)renderToolTipEvent);
        if (renderToolTipEvent.isCanceled()) {
            callbackInfo.cancel();
        }
        if (shulkerStack.getItem() instanceof ItemShulkerBox) {
            this.hoveringShulker = true;
            this.shulkerStack = shulkerStack;
            this.shulkerName = shulkerStack.getDisplayName();
        }
        else {
            this.hoveringShulker = false;
        }
    }
    
    @Inject(method = { "mouseClicked" }, at = { @At("HEAD") })
    public void mouseClickedHook(final int n, final int n2, final int n3, final CallbackInfo callbackInfo) {
        if (n3 == 2 && this.hoveringShulker && (boolean)ToolTips.INSTANCE.wheelPeek.getValue() && ToolTips.INSTANCE.isOn()) {
            ToolTips.drawShulkerGui(this.shulkerStack, this.shulkerName);
        }
    }
    
    @Inject(method = { "drawScreen" }, at = { @At("HEAD") })
    public void drawScreenHook(final int n, final int n2, final float n3, final CallbackInfo callbackInfo) {
        if (Wrapper.mc.currentScreen != null && !(Wrapper.mc.currentScreen instanceof GuiContainer) && (boolean)ClickGui.INSTANCE.background.getValue() && Wrapper.mc.world != null) {
            RenderUtil.drawVGradientRect(0.0f, 0.0f, (float)Managers.TEXT.scaledWidth, (float)Managers.TEXT.scaledHeight, new Color(0, 0, 0, 0).getRGB(), Managers.COLORS.getCurrentWithAlpha(60));
        }
    }
    
    @Inject(method = { "drawWorldBackground(I)V" }, at = { @At("HEAD") }, cancellable = true)
    private void drawWorldBackgroundHook(final int n, final CallbackInfo callbackInfo) {
        if (Wrapper.mc.world != null && (boolean)ClickGui.INSTANCE.cleanGui.getValue()) {
            callbackInfo.cancel();
        }
    }
}
