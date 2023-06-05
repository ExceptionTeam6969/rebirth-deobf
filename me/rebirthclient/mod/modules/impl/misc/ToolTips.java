//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import java.text.*;
import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.enchantment.*;
import net.minecraft.init.*;
import java.util.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.item.*;
import me.rebirthclient.api.managers.*;
import net.minecraft.util.text.translation.*;
import net.minecraft.client.renderer.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.inventory.*;
import net.minecraft.util.*;
import net.minecraft.nbt.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;

public class ToolTips extends Module
{
    private float height;
    private final DecimalFormat format;
    public final Setting onlyShulker;
    public static ToolTips INSTANCE;
    private float width;
    public final Setting wheelPeek;
    public final Setting shulkerPreview;
    
    private String getEnchants(final ItemStack itemStack) {
        final StringBuilder sb = new StringBuilder();
        final Iterator<Enchantment> iterator = EnchantmentHelper.getEnchantments(itemStack).keySet().iterator();
        if (iterator.hasNext()) {
            final Enchantment enchantment = iterator.next();
            if (enchantment == null) {
                return null;
            }
            final String getTranslatedName = enchantment.getTranslatedName(EnchantmentHelper.getEnchantmentLevel(enchantment, itemStack));
            if (getTranslatedName.contains("Vanish")) {
                sb.append("Vanishing ");
            }
            else if (getTranslatedName.contains("Binding")) {
                sb.append("Binding ");
            }
            return null;
        }
        else {
            if (itemStack.getItem().equals(Items.GOLDEN_APPLE) && itemStack.hasEffect()) {
                return "God";
            }
            return (sb.length() == 0) ? null : String.valueOf(sb);
        }
    }
    
    private int getItemColor(final ItemStack itemStack) {
        if (itemStack.getItem() instanceof ItemArmor) {
            switch (((ItemArmor)itemStack.getItem()).getArmorMaterial()) {
                case CHAIN: {
                    return 28893;
                }
                case DIAMOND: {
                    return EnchantmentHelper.getEnchantments(itemStack).keySet().isEmpty() ? 2031360 : 10696174;
                }
                case GOLD:
                case IRON: {
                    return 2031360;
                }
                case LEATHER: {
                    return 10329501;
                }
            }
        }
        else if (itemStack.getItem().equals(Items.GOLDEN_APPLE)) {
            if (itemStack.hasEffect()) {
                return 10696174;
            }
            return 52735;
        }
        else if (itemStack.getItem() instanceof ItemSword) {
            final String getToolMaterialName = ((ItemSword)itemStack.getItem()).getToolMaterialName();
            if (Integer.valueOf("DIAMOND".hashCode()).equals(getToolMaterialName.hashCode())) {
                return 10696174;
            }
            if (Integer.valueOf("CHAIN".hashCode()).equals(getToolMaterialName.hashCode())) {
                return 28893;
            }
            if (Integer.valueOf("GOLD".hashCode()).equals(getToolMaterialName.hashCode())) {
                return 2031360;
            }
            if (Integer.valueOf("IRON".hashCode()).equals(getToolMaterialName.hashCode())) {
                return 2031360;
            }
            if (Integer.valueOf("LEATHER".hashCode()).equals(getToolMaterialName.hashCode())) {
                return 10329501;
            }
            return -1;
        }
        else {
            if (itemStack.getItem().equals(Items.TOTEM_OF_UNDYING)) {
                return 16744448;
            }
            if (itemStack.getItem().equals(Items.CHORUS_FRUIT)) {
                return 28893;
            }
            if (itemStack.getItem().equals(Items.ENDER_PEARL)) {
                return 28893;
            }
            if (itemStack.getItem().equals(Items.END_CRYSTAL)) {
                return 10696174;
            }
            if (itemStack.getItem().equals(Items.EXPERIENCE_BOTTLE)) {
                return 2031360;
            }
            if (itemStack.getItem().equals(Items.POTIONITEM)) {
                return 2031360;
            }
            if (Item.getIdFromItem(itemStack.getItem()) == 130) {
                return 10696174;
            }
            if (itemStack.getItem() instanceof ItemShulkerBox) {
                return 10696174;
            }
        }
        return -1;
    }
    
    public static void drawShulkerGui(final ItemStack itemStack, final String s) {
        try {
            final Item getItem = itemStack.getItem();
            final TileEntityShulkerBox tileEntityShulkerBox = new TileEntityShulkerBox();
            tileEntityShulkerBox.blockType = ((ItemShulkerBox)getItem).getBlock();
            tileEntityShulkerBox.setWorld((World)Peek.mc.world);
            ItemStackHelper.loadAllItems(itemStack.getTagCompound().getCompoundTag("BlockEntityTag"), tileEntityShulkerBox.items);
            tileEntityShulkerBox.readFromNBT(itemStack.getTagCompound().getCompoundTag("BlockEntityTag"));
            tileEntityShulkerBox.setCustomName((s == null) ? itemStack.getDisplayName() : s);
            new Thread(ToolTips::lambda$drawShulkerGui$1).start();
        }
        catch (Exception ex) {}
    }
    
    @SubscribeEvent
    public void onRenderToolTip(final RenderToolTipEvent renderToolTipEvent) {
        if (renderToolTipEvent.isCanceled() || nullCheck() || fullNullCheck()) {
            return;
        }
        if (!renderToolTipEvent.getItemStack().isEmpty()) {
            if (!(renderToolTipEvent.getItemStack().getItem() instanceof ItemShulkerBox) && (boolean)this.onlyShulker.getValue()) {
                return;
            }
            renderToolTipEvent.setCanceled(true);
            final int x = renderToolTipEvent.getX();
            final int y = renderToolTipEvent.getY();
            if (renderToolTipEvent.getItemStack().getItem() instanceof ItemShulkerBox && (boolean)this.shulkerPreview.getValue()) {
                this.drawShulkerPreview(renderToolTipEvent.getItemStack(), x + 3, y - 10);
            }
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            GlStateManager.translate((float)(x + 10), (float)(y - 5), 0.0f);
            final String getDisplayName = renderToolTipEvent.getItemStack().getDisplayName();
            RenderUtil.drawRect(0.0f, -2.0f, this.width, this.height, -519565792);
            final float width = this.width;
            this.width = 0.0f;
            int n = this.drawString(getDisplayName, 3, 1, this.getItemColor(renderToolTipEvent.getItemStack()));
            final String enchants = this.getEnchants(renderToolTipEvent.getItemStack());
            if (enchants != null) {
                n = this.drawString(enchants, 3, n, 16711680);
            }
            String s = null;
            String s2 = null;
            if (renderToolTipEvent.getItemStack().getItem() instanceof ItemArmor) {
                final ItemArmor itemArmor = (ItemArmor)renderToolTipEvent.getItemStack().getItem();
                switch (itemArmor.getEquipmentSlot()) {
                    case CHEST: {
                        s = "Chest";
                        break;
                    }
                    case FEET: {
                        s = "Feet";
                        break;
                    }
                    case HEAD: {
                        s = "Head";
                        break;
                    }
                    case LEGS: {
                        s = "Leggings";
                        break;
                    }
                }
                switch (itemArmor.getArmorMaterial()) {
                    case CHAIN: {
                        s2 = "Chain";
                        break;
                    }
                    case DIAMOND: {
                        s2 = "Diamond";
                        break;
                    }
                    case GOLD: {
                        s2 = "Gold";
                        break;
                    }
                    case IRON: {
                        s2 = "Iron";
                        break;
                    }
                    case LEATHER: {
                        s2 = "Leather";
                        break;
                    }
                }
            }
            if (renderToolTipEvent.getItemStack().getItem() instanceof ItemElytra) {
                s = "Chest";
            }
            if (renderToolTipEvent.getItemStack().getItem() instanceof ItemSword) {
                s = "Mainhand";
                s2 = "Sword";
            }
            if (s != null) {
                final int n2 = n;
                n = this.drawString(s, 3, n, -1);
                if (s2 != null) {
                    this.drawString(s2, (int)(width - Managers.TEXT.getStringWidth(s2) - 3.0f), n2, -1);
                    this.width = Math.max(48.0f, width);
                }
            }
            if (renderToolTipEvent.getItemStack().getItem() instanceof ItemSword) {
                final ItemSword itemSword = (ItemSword)renderToolTipEvent.getItemStack().getItem();
                n = this.drawString(itemSword.getAttackDamage() + " - " + itemSword.getAttackDamage() + " Damage", 3, n, -1);
            }
            for (final Enchantment enchantment : EnchantmentHelper.getEnchantments(renderToolTipEvent.getItemStack()).keySet()) {
                final String string = "+" + EnchantmentHelper.getEnchantmentLevel(enchantment, renderToolTipEvent.getItemStack()) + " " + I18n.translateToLocal(enchantment.getName());
                if (!string.contains("Vanish")) {
                    if (string.contains("Binding")) {
                        return;
                    }
                    int n3 = -1;
                    if (string.contains("Mending") || string.contains("Unbreaking")) {
                        n3 = 65280;
                    }
                    this.drawString(string, 3, n, n3);
                    return;
                }
            }
            if (renderToolTipEvent.getItemStack().getMaxDamage() > 1) {
                n = this.drawString(String.format("Durability %s %s / %s", this.format.format((renderToolTipEvent.getItemStack().getMaxDamage() - renderToolTipEvent.getItemStack().getItemDamage()) / (float)renderToolTipEvent.getItemStack().getMaxDamage() * 100.0f) + "%", renderToolTipEvent.getItemStack().getMaxDamage() - renderToolTipEvent.getItemStack().getItemDamage(), renderToolTipEvent.getItemStack().getMaxDamage()), 3, n, -1);
            }
            GlStateManager.enableDepth();
            ToolTips.mc.getRenderItem().zLevel = 150.0f;
            RenderHelper.enableGUIStandardItemLighting();
            RenderHelper.disableStandardItemLighting();
            ToolTips.mc.getRenderItem().zLevel = 0.0f;
            GlStateManager.enableLighting();
            GlStateManager.translate((float)(-(x + 10)), (float)(-(y - 5)), 0.0f);
            this.height = (float)(n + 1);
        }
    }
    
    private static void lambda$drawShulkerGui$1(final TileEntityShulkerBox tileEntityShulkerBox) {
        try {
            Thread.sleep(200L);
        }
        catch (InterruptedException ex) {}
        Peek.mc.player.displayGUIChest((IInventory)tileEntityShulkerBox);
    }
    
    private int drawString(final String s, final int n, final int n2, final int n3) {
        Managers.TEXT.drawStringWithShadow(s, (float)n, (float)n2, n3);
        this.width = Math.max(this.width, (float)(Managers.TEXT.getStringWidth(s) + n + 3));
        return n2 + 9;
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.shulkerPreview.isOpen();
    }
    
    private void drawShulkerPreview(final ItemStack itemStack, final int n, final int n2) {
        final NBTTagCompound getTagCompound = itemStack.getTagCompound();
        final NBTTagCompound getCompoundTag;
        if (getTagCompound != null && getTagCompound.hasKey("BlockEntityTag", 10) && (getCompoundTag = getTagCompound.getCompoundTag("BlockEntityTag")).hasKey("Items", 9)) {
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.disableDepth();
            RenderUtil.drawRect((float)(n + 7), (float)(n2 + 17), (float)(n + 171), (float)(n2 + 57 + 16), -519565792);
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
                ToolTips.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack2, n4, n5);
                ToolTips.mc.getRenderItem().renderItemOverlayIntoGUI(Peek.mc.fontRenderer, itemStack2, n4, n5, (String)null);
                Peek.mc.getItemRenderer().itemRenderer.zLevel = 0.0f;
                ++n3;
                return;
            }
            GlStateManager.disableLighting();
            GlStateManager.disableBlend();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
    
    public ToolTips() {
        super("ToolTips", "Advanced tool tips", Category.MISC);
        this.shulkerPreview = this.add(new Setting("ShulkerPreview", true).setParent());
        this.onlyShulker = this.add(new Setting("OnlyShulker", false, this::lambda$new$0));
        this.wheelPeek = this.add(new Setting("WheelPeek", true));
        this.format = new DecimalFormat("#");
        ToolTips.INSTANCE = this;
    }
}
