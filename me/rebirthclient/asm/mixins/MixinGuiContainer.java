//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import net.minecraft.client.gui.inventory.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import java.util.*;
import com.google.common.collect.*;
import me.rebirthclient.api.managers.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;
import me.rebirthclient.mod.modules.impl.client.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;

@Mixin({ GuiContainer.class })
public abstract class MixinGuiContainer extends GuiScreen
{
    @Shadow
    public Container inventorySlots;
    @Shadow
    protected int guiLeft;
    @Shadow
    protected int guiTop;
    @Shadow
    private Slot hoveredSlot;
    @Shadow
    private boolean isRightMouseClick;
    @Shadow
    private ItemStack draggedStack;
    @Shadow
    private int touchUpX;
    @Shadow
    private int touchUpY;
    @Shadow
    private Slot returningStackDestSlot;
    @Shadow
    private long returningStackTime;
    @Shadow
    private ItemStack returningStack;
    @Final
    @Shadow
    protected final Set dragSplittingSlots;
    @Shadow
    protected boolean dragSplitting;
    @Shadow
    private int dragSplittingRemnant;
    
    public MixinGuiContainer() {
        this.draggedStack = ItemStack.EMPTY;
        this.returningStack = ItemStack.EMPTY;
        this.dragSplittingSlots = Sets.newHashSet();
    }
    
    @Overwrite
    public void drawScreen(final int n, final int n2, final float n3) {
        if ((boolean)ClickGui.INSTANCE.background.getValue() && this.mc.world != null) {
            RenderUtil.drawVGradientRect(0.0f, 0.0f, (float)Managers.TEXT.scaledWidth, (float)Managers.TEXT.scaledHeight, new Color(0, 0, 0, 0).getRGB(), Managers.COLORS.getCurrentWithAlpha(60));
        }
        final float n4 = (float)GuiAnimation.inventoryFade.easeOutQuad();
        GlStateManager.pushMatrix();
        GL11.glScaled((double)n4, (double)n4, (double)n4);
        final int guiLeft = this.guiLeft;
        final int guiTop = this.guiTop;
        this.drawGuiContainerBackgroundLayer(n3, n, n2);
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        super.drawScreen(n, n2, n3);
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)guiLeft, (float)guiTop, 0.0f);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableRescaleNormal();
        this.hoveredSlot = null;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0f, 240.0f);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        for (int i = 0; i < this.inventorySlots.inventorySlots.size(); ++i) {
            final Slot hoveredSlot = this.inventorySlots.inventorySlots.get(i);
            if (hoveredSlot.isEnabled()) {
                this.drawSlot(hoveredSlot);
            }
            if (this.isMouseOverSlot(hoveredSlot, n, n2) && hoveredSlot.isEnabled()) {
                this.hoveredSlot = hoveredSlot;
                GlStateManager.disableLighting();
                GlStateManager.disableDepth();
                final int xPos = hoveredSlot.xPos;
                final int yPos = hoveredSlot.yPos;
                GlStateManager.colorMask(true, true, true, false);
                this.drawGradientRect(xPos, yPos, xPos + 16, yPos + 16, -2130706433, -2130706433);
                GlStateManager.colorMask(true, true, true, true);
                GlStateManager.enableLighting();
                GlStateManager.enableDepth();
            }
        }
        RenderHelper.disableStandardItemLighting();
        this.drawGuiContainerForegroundLayer(n, n2);
        RenderHelper.enableGUIStandardItemLighting();
        MinecraftForge.EVENT_BUS.post((Event)new GuiContainerEvent.DrawForeground((GuiContainer)this, n, n2));
        final InventoryPlayer inventory = this.mc.player.inventory;
        ItemStack itemStack = this.draggedStack.isEmpty() ? inventory.getItemStack() : this.draggedStack;
        if (!itemStack.isEmpty()) {
            final int n5 = this.draggedStack.isEmpty() ? 8 : 16;
            String string = null;
            if (!this.draggedStack.isEmpty() && this.isRightMouseClick) {
                itemStack = itemStack.copy();
                itemStack.setCount(MathHelper.ceil(itemStack.getCount() / 2.0f));
            }
            else if (this.dragSplitting && this.dragSplittingSlots.size() > 1) {
                itemStack = itemStack.copy();
                itemStack.setCount(this.dragSplittingRemnant);
                if (itemStack.isEmpty()) {
                    string = TextFormatting.YELLOW + "0";
                }
            }
            this.drawItemStack(itemStack, n - guiLeft - 8, n2 - guiTop - n5, string);
        }
        if (!this.returningStack.isEmpty()) {
            float n6 = (Minecraft.getSystemTime() - this.returningStackTime) / 100.0f;
            if (n6 >= 1.0f) {
                n6 = 1.0f;
                this.returningStack = ItemStack.EMPTY;
            }
            this.drawItemStack(this.returningStack, this.touchUpX + (int)((this.returningStackDestSlot.xPos - this.touchUpX) * n6), this.touchUpY + (int)((this.returningStackDestSlot.yPos - this.touchUpY) * n6), null);
        }
        GlStateManager.popMatrix();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }
    
    @Shadow
    protected abstract void drawGuiContainerBackgroundLayer(final float p0, final int p1, final int p2);
    
    @Shadow
    private void drawSlot(final Slot slot) {
    }
    
    @Shadow
    protected void drawGuiContainerForegroundLayer(final int n, final int n2) {
    }
    
    @Shadow
    private void drawItemStack(final ItemStack itemStack, final int n, final int n2, final String s) {
        GlStateManager.translate(0.0f, 0.0f, 32.0f);
        this.zLevel = 200.0f;
        this.itemRender.zLevel = 200.0f;
        FontRenderer fontRenderer = itemStack.getItem().getFontRenderer(itemStack);
        if (fontRenderer == null) {
            fontRenderer = this.fontRenderer;
        }
        this.itemRender.renderItemAndEffectIntoGUI(itemStack, n, n2);
        this.itemRender.renderItemOverlayIntoGUI(fontRenderer, itemStack, n, n2 - (this.draggedStack.isEmpty() ? 0 : 8), s);
        this.zLevel = 0.0f;
        this.itemRender.zLevel = 0.0f;
    }
    
    @Shadow
    private boolean isMouseOverSlot(final Slot slot, final int n, final int n2) {
        return false;
    }
}
