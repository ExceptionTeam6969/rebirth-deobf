//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.item.*;
import me.rebirthclient.api.managers.*;
import java.awt.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.util.*;
import net.minecraft.inventory.*;
import net.minecraft.nbt.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.modules.*;
import java.util.concurrent.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;

public class Peek extends Module
{
    private final Map playerTimers;
    private final Map spiedPlayers;
    
    @Override
    public void onUpdate() {
        for (final EntityPlayer entityPlayer : Peek.mc.world.playerEntities) {
            if (entityPlayer != null && entityPlayer.getHeldItemMainhand().getItem() instanceof ItemShulkerBox) {
                if (Peek.mc.player == entityPlayer) {
                    return;
                }
                this.spiedPlayers.put(entityPlayer, entityPlayer.getHeldItemMainhand());
            }
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void makeTooltip(final ItemTooltipEvent itemTooltipEvent) {
    }
    
    private void renderShulkerToolTip(final ItemStack itemStack, final int n, final int n2, final String s) {
        final NBTTagCompound getTagCompound = itemStack.getTagCompound();
        final NBTTagCompound getCompoundTag;
        if (getTagCompound != null && getTagCompound.hasKey("BlockEntityTag", 10) && (getCompoundTag = getTagCompound.getCompoundTag("BlockEntityTag")).hasKey("Items", 9)) {
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            Peek.mc.getTextureManager().bindTexture(new ResourceLocation("textures/rebirth/constant/ingame/container.png"));
            this.drawTexturedRect(n, n2, 0, 16);
            this.drawTexturedRect(n, n2 + 16, 16, 57);
            this.drawTexturedRect(n, n2 + 16 + 54, 160, 8);
            GlStateManager.disableDepth();
            Managers.TEXT.drawStringWithShadow((s == null) ? itemStack.getDisplayName() : s, (float)(n + 8), (float)(n2 + 6), ColorUtil.toRGBA(new Color(((Color)ClickGui.INSTANCE.color.getValue()).getRed(), ((Color)ClickGui.INSTANCE.color.getValue()).getGreen(), ((Color)ClickGui.INSTANCE.color.getValue()).getBlue(), 200)));
            GlStateManager.enableDepth();
            RenderHelper.enableGUIStandardItemLighting();
            GlStateManager.enableRescaleNormal();
            GlStateManager.enableColorMaterial();
            GlStateManager.enableLighting();
            final NonNullList withSize = NonNullList.withSize(27, (Object)ItemStack.EMPTY);
            ItemStackHelper.loadAllItems(getCompoundTag, withSize);
            int n3 = 0;
            if (n3 < withSize.size()) {
                final int n4 = n + n3 % 9 * 18 + 8;
                final int n5 = n2 + n3 / 9 * 18 + 18;
                final ItemStack itemStack2 = (ItemStack)withSize.get(n3);
                Peek.mc.getItemRenderer().itemRenderer.zLevel = 501.0f;
                Peek.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack2, n4, n5);
                Peek.mc.getRenderItem().renderItemOverlayIntoGUI(Peek.mc.fontRenderer, itemStack2, n4, n5, (String)null);
                Peek.mc.getItemRenderer().itemRenderer.zLevel = 0.0f;
                ++n3;
                return;
            }
            GlStateManager.disableLighting();
            GlStateManager.disableBlend();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        if (fullNullCheck()) {
            return;
        }
        final int n = Managers.TEXT.scaledWidth / 2 - 78;
        final int n2 = 24;
        final Iterator iterator = Peek.mc.world.playerEntities.iterator();
        if (!iterator.hasNext()) {
            return;
        }
        final EntityPlayer entityPlayer = iterator.next();
        if (this.spiedPlayers.get(entityPlayer) == null) {
            return;
        }
        if (entityPlayer.getHeldItemMainhand() == null || !(entityPlayer.getHeldItemMainhand().getItem() instanceof ItemShulkerBox)) {
            final Timer timer = this.playerTimers.get(entityPlayer);
            if (timer == null) {
                final Timer timer2 = new Timer();
                timer2.reset();
                this.playerTimers.put(entityPlayer, timer2);
            }
            else if (timer.passedS(3.0)) {
                return;
            }
        }
        else {
            final Timer timer3;
            if (entityPlayer.getHeldItemMainhand().getItem() instanceof ItemShulkerBox && (timer3 = this.playerTimers.get(entityPlayer)) != null) {
                timer3.reset();
                this.playerTimers.put(entityPlayer, timer3);
            }
        }
        this.renderShulkerToolTip((ItemStack)this.spiedPlayers.get(entityPlayer), n, n2, entityPlayer.getName());
    }
    
    public Peek() {
        super("Peek", "Allows you to peek into your enemy's shulkerboxes", Category.MISC);
        this.spiedPlayers = new ConcurrentHashMap();
        this.playerTimers = new ConcurrentHashMap();
    }
    
    private void drawTexturedRect(final int n, final int n2, final int n3, final int n4) {
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        getBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        getBuffer.pos((double)n, (double)(n2 + n4), 500.0).tex(0.0, (double)((n3 + n4) * 0.00390625f)).endVertex();
        getBuffer.pos((double)(n + 176), (double)(n2 + n4), 500.0).tex(0.6875, (double)((n3 + n4) * 0.00390625f)).endVertex();
        getBuffer.pos((double)(n + 176), (double)n2, 500.0).tex(0.6875, (double)(n3 * 0.00390625f)).endVertex();
        getBuffer.pos((double)n, (double)n2, 500.0).tex(0.0, (double)(n3 * 0.00390625f)).endVertex();
        getInstance.draw();
    }
}
