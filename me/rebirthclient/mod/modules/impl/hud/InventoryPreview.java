//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.hud;

import me.rebirthclient.mod.modules.settings.*;
import java.awt.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;

public class InventoryPreview extends Module
{
    public final Setting yOffset;
    public final Setting y;
    public final Setting xOffset;
    public final Setting x;
    public final Setting rect;
    public final Setting outline;
    public final Setting secondColor;
    public final Setting rectColor;
    public final Setting lineColor;
    
    private boolean lambda$new$1(final Integer n) {
        return this.yOffset.getValue() == YOffset.CUSTOM;
    }
    
    private boolean lambda$new$2(final Color color) {
        return this.outline.isOpen();
    }
    
    private boolean lambda$new$0(final Integer n) {
        return this.xOffset.getValue() == XOffset.CUSTOM;
    }
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        if (fullNullCheck()) {
            return;
        }
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.disableDepth();
        final int n = (int)((this.xOffset.getValue() == XOffset.CUSTOM) ? this.x.getValue() : ((this.xOffset.getValue() == XOffset.LEFT) ? 0 : (Managers.TEXT.scaledWidth - 172)));
        final int n2 = (int)((this.yOffset.getValue() == YOffset.CUSTOM) ? this.y.getValue() : ((this.yOffset.getValue() == YOffset.TOP) ? 0 : (Managers.TEXT.scaledHeight - 74)));
        if (this.outline.getValue()) {
            RenderUtil.drawNameTagOutline(n + 6.5f, n2 + 16.5f, n + 171.5f, n2 + 73.5f, 1.0f, ((Color)this.lineColor.getValue()).getRGB(), this.secondColor.booleanValue ? ((Color)this.secondColor.getValue()).getRGB() : ((Color)this.lineColor.getValue()).getRGB(), false);
        }
        if (this.rect.getValue()) {
            RenderUtil.drawRect((float)(n + 7), (float)(n2 + 17), (float)(n + 171), (float)(n2 + 73), ((Color)this.rectColor.getValue()).getRGB());
        }
        GlStateManager.enableDepth();
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableColorMaterial();
        GlStateManager.enableLighting();
        final NonNullList mainInventory = InventoryPreview.mc.player.inventory.mainInventory;
        int n3 = 0;
        if (n3 < mainInventory.size() - 9) {
            final int n4 = n + n3 % 9 * 18 + 8;
            final int n5 = n2 + n3 / 9 * 18 + 18;
            final ItemStack itemStack = (ItemStack)mainInventory.get(n3 + 9);
            InventoryPreview.mc.getItemRenderer().itemRenderer.zLevel = 501.0f;
            InventoryPreview.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, n4, n5);
            InventoryPreview.mc.getRenderItem().renderItemOverlayIntoGUI(InventoryPreview.mc.fontRenderer, itemStack, n4, n5, (String)null);
            InventoryPreview.mc.getItemRenderer().itemRenderer.zLevel = 0.0f;
            ++n3;
            return;
        }
        GlStateManager.disableLighting();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    private boolean lambda$new$3(final Color color) {
        return this.outline.isOpen();
    }
    
    private boolean lambda$new$4(final Color color) {
        return this.rect.isOpen();
    }
    
    public InventoryPreview() {
        super("Inventory", "Allows you to see your own inventory without opening it", Category.HUD);
        this.xOffset = this.add(new Setting("XOffset", XOffset.CUSTOM));
        this.x = this.add(new Setting("X", 500, 0, 1000, this::lambda$new$0));
        this.yOffset = this.add(new Setting("YOffset", YOffset.CUSTOM));
        this.y = this.add(new Setting("Y", 2, 0, 1000, this::lambda$new$1));
        this.outline = this.add(new Setting("Outline", true).setParent());
        this.lineColor = this.add(new Setting("LineColor", new Color(10, 10, 10, 100), this::lambda$new$2));
        this.secondColor = this.add(new Setting("SecondColor", new Color(30, 30, 30, 100), this::lambda$new$3).injectBoolean(true));
        this.rect = this.add(new Setting("Rect", true).setParent());
        this.rectColor = this.add(new Setting("RectColor", new Color(10, 10, 10, 50), this::lambda$new$4));
    }
    
    private enum XOffset
    {
        LEFT("LEFT", 1);
        
        private static final XOffset[] $VALUES;
        
        CUSTOM("CUSTOM", 0), 
        RIGHT("RIGHT", 2);
        
        static {
            $VALUES = new XOffset[] { XOffset.CUSTOM, XOffset.LEFT, XOffset.RIGHT };
        }
        
        private XOffset(final String s, final int n) {
        }
    }
    
    private enum YOffset
    {
        CUSTOM("CUSTOM", 0), 
        TOP("TOP", 1);
        
        private static final YOffset[] $VALUES;
        
        BOTTOM("BOTTOM", 2);
        
        private YOffset(final String s, final int n) {
        }
        
        static {
            $VALUES = new YOffset[] { YOffset.CUSTOM, YOffset.TOP, YOffset.BOTTOM };
        }
    }
}
