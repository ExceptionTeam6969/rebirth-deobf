//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.client.network.*;
import java.util.*;
import net.minecraft.client.renderer.vertex.*;
import me.rebirthclient.mod.modules.*;
import java.awt.*;
import java.util.function.*;
import net.minecraft.init.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.render.*;
import net.minecraft.enchantment.*;
import net.minecraft.util.text.*;
import me.rebirthclient.api.util.*;
import net.minecraft.nbt.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;
import org.lwjgl.opengl.*;

public class NameTags extends Module
{
    private final Setting rect;
    private final Setting health;
    private final Setting reversed;
    private final Setting heldStackName;
    private final Setting outLineWidth;
    static final boolean $assertionsDisabled;
    private final Setting size;
    public final Setting outLine;
    private final Setting noMaxText;
    private final Setting gamemode;
    private final Setting ping;
    private final Setting armor;
    public final Setting sneak;
    public final Setting color;
    public final Setting friend;
    private final Setting factor;
    private final Setting scale;
    private final Setting entityID;
    public final Setting invisible;
    public static NameTags INSTANCE;
    private final Setting smartScale;
    public final Setting max;
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (fullNullCheck()) {
            return;
        }
        for (final EntityPlayer entityPlayer : NameTags.mc.world.playerEntities) {
            if (entityPlayer != null && !entityPlayer.equals((Object)NameTags.mc.player) && entityPlayer.isEntityAlive()) {
                if (entityPlayer.isInvisible() && !this.invisible.booleanValue) {
                    return;
                }
                this.renderNameTag(entityPlayer, this.interpolate(entityPlayer.lastTickPosX, entityPlayer.posX, render3DEvent.getPartialTicks()) - NameTags.mc.getRenderManager().renderPosX, this.interpolate(entityPlayer.lastTickPosY, entityPlayer.posY, render3DEvent.getPartialTicks()) - NameTags.mc.getRenderManager().renderPosY, this.interpolate(entityPlayer.lastTickPosZ, entityPlayer.posZ, render3DEvent.getPartialTicks()) - NameTags.mc.getRenderManager().renderPosZ, render3DEvent.getPartialTicks());
            }
        }
    }
    
    private String getDisplayTag(final EntityPlayer entityPlayer) {
        String getFormattedText = entityPlayer.getDisplayName().getFormattedText();
        if (getFormattedText.contains(NameTags.mc.getSession().getUsername())) {
            getFormattedText = "You";
        }
        final float health = EntityUtil.getHealth((Entity)entityPlayer);
        final String s = (health > 18.0f) ? TextUtil.GREEN : ((health > 16.0f) ? TextUtil.DARK_GREEN : ((health > 12.0f) ? TextUtil.YELLOW : ((health > 8.0f) ? TextUtil.RED : TextUtil.DARK_RED)));
        String string = "";
        if (this.ping.getValue()) {
            try {
                string = string + Objects.requireNonNull(NameTags.mc.getConnection()).getPlayerInfo(entityPlayer.getUniqueID()).getResponseTime() + "ms ";
            }
            catch (Exception ex) {}
        }
        String string2 = "";
        if (this.entityID.getValue()) {
            string2 = string2 + "ID: " + entityPlayer.getEntityId() + " ";
        }
        String s2 = "";
        if (this.gamemode.getValue()) {
            s2 = (entityPlayer.isCreative() ? (s2 + "[C] ") : ((entityPlayer.isSpectator() || entityPlayer.isInvisible()) ? (s2 + "[I] ") : (s2 + "[S] ")));
        }
        if (this.health.getValue()) {
            getFormattedText = ((Math.floor(health) == health) ? (getFormattedText + s + " " + ((health > 0.0f) ? Integer.valueOf((int)Math.floor(health)) : "dead")) : (getFormattedText + s + " " + ((health > 0.0f) ? Integer.valueOf((int)health) : "dead")));
        }
        return " " + string + string2 + s2 + getFormattedText + " ";
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.armor.isOpen();
    }
    
    public void drawOutlineRect(final float n, final float n2, final float n3, final float n4, final int n5) {
        final float n6 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth((float)this.outLineWidth.getValue());
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        getBuffer.begin(2, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    private boolean lambda$new$4(final Float n) {
        return this.outLine.isOpen();
    }
    
    public void drawRect(final float n, final float n2, final float n3, final float n4, final int n5) {
        final float n6 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth((float)this.outLineWidth.getValue());
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public NameTags() {
        super("NameTags", "Renders info about the player on a NameTag", Category.RENDER);
        this.max = this.add(new Setting("Max", new Color(255, 255, 255)).injectBoolean(true).setParent());
        this.color = this.add(new Setting("Color", new Color(255, 255, 255)));
        this.outLine = this.add(new Setting("outLine", new Color(255, 255, 255)).injectBoolean(false).setParent());
        this.friend = this.add(new Setting("Friend", new Color(155, 155, 255)).injectBoolean(true));
        this.invisible = this.add(new Setting("Invisible", new Color(200, 200, 200)).injectBoolean(true));
        this.sneak = this.add(new Setting("Sneaking", new Color(200, 200, 0)).injectBoolean(true));
        this.rect = this.add(new Setting("Rectangle", true));
        this.armor = this.add(new Setting("Armor", true).setParent());
        this.reversed = this.add(new Setting("ArmorReversed", false, this::lambda$new$0));
        this.health = this.add(new Setting("Health", true));
        this.ping = this.add(new Setting("Ping", true));
        this.gamemode = this.add(new Setting("Gamemode", true));
        this.entityID = this.add(new Setting("EntityID", false));
        this.heldStackName = this.add(new Setting("StackName", false));
        this.size = this.add(new Setting("Size", 2.5f, 0.1f, 15.0f));
        this.scale = this.add(new Setting("Scale", true).setParent());
        this.smartScale = this.add(new Setting("SmartScale", true, this::lambda$new$1));
        this.factor = this.add(new Setting("Factor", 0.3f, 0.1f, 1.0f, this::lambda$new$2));
        this.noMaxText = this.add(new Setting("NoMaxText", true, this::lambda$new$3));
        this.outLineWidth = this.add(new Setting("Width", 1.3f, 0.0f, 5.0f, this::lambda$new$4));
        NameTags.INSTANCE = this;
    }
    
    private void renderEnchantmentText(final ItemStack itemStack, final int n) {
        int n2 = -34;
        if (itemStack.getItem() == Items.GOLDEN_APPLE && itemStack.hasEffect()) {
            Managers.TEXT.drawMCString("god", (float)(n * 2), (float)n2, -3977919, true);
            n2 -= 8;
        }
        final NBTTagList getEnchantmentTagList;
        if ((getEnchantmentTagList = itemStack.getEnchantmentTagList()).tagCount() > 2 && this.max.booleanValue) {
            if (this.noMaxText.getValue()) {
                Managers.TEXT.drawMCString("", (float)(n * 2), (float)n2, ColorUtil.toRGBA((Color)this.max.getValue()), true);
            }
            else {
                Managers.TEXT.drawMCString("max", (float)(n * 2), (float)n2, ColorUtil.toRGBA((Color)this.max.getValue()), true);
            }
            n2 -= 8;
        }
        else {
            int n3 = 0;
            if (n3 < getEnchantmentTagList.tagCount()) {
                final short getShort = getEnchantmentTagList.getCompoundTagAt(n3).getShort("id");
                final short getShort2 = getEnchantmentTagList.getCompoundTagAt(n3).getShort("lvl");
                final Enchantment getEnchantmentByID = Enchantment.getEnchantmentByID((int)getShort);
                if (getEnchantmentByID != null) {
                    Managers.TEXT.drawMCString((getEnchantmentByID.isCurse() ? (TextFormatting.RED + getEnchantmentByID.getTranslatedName((int)getShort2).substring(0, 4).toLowerCase()) : getEnchantmentByID.getTranslatedName((int)getShort2).substring(0, 2).toLowerCase()) + getShort2, (float)(n * 2), (float)n2, -1, true);
                    n2 -= 8;
                }
                ++n3;
                return;
            }
        }
        if (DamageUtil.hasDurability(itemStack)) {
            final int n4 = 100 - (int)((1.0f - (itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / itemStack.getMaxDamage()) * 100.0f);
            Managers.TEXT.drawMCString(((n4 >= 60) ? TextUtil.GREEN : ((n4 >= 25) ? TextUtil.YELLOW : TextUtil.RED)) + n4 + "%", (float)(n * 2), (float)n2, -1, true);
        }
    }
    
    static {
        $assertionsDisabled = !NameTags.class.desiredAssertionStatus();
        NameTags.INSTANCE = new NameTags();
    }
    
    private float getBiggestArmorTag(final EntityPlayer entityPlayer) {
        float n = 0.0f;
        final boolean b = false;
        final Iterator iterator = entityPlayer.inventory.armorInventory.iterator();
        if (!iterator.hasNext()) {
            final ItemStack copy = entityPlayer.getHeldItemMainhand().copy();
            if (copy.hasEffect()) {
                final float n2 = 0.0f;
                final NBTTagList getEnchantmentTagList = copy.getEnchantmentTagList();
                int n3 = 0;
                if (n3 < getEnchantmentTagList.tagCount()) {
                    if (Enchantment.getEnchantmentByID((int)getEnchantmentTagList.getCompoundTagAt(n3).getShort("id")) == null) {}
                    ++n3;
                    return 0.0f;
                }
                if (n2 > n) {
                    n = n2;
                }
            }
            final ItemStack copy2;
            if ((copy2 = entityPlayer.getHeldItemOffhand().copy()).hasEffect()) {
                final float n4 = 0.0f;
                final NBTTagList getEnchantmentTagList2 = copy2.getEnchantmentTagList();
                int n5 = 0;
                if (n5 < getEnchantmentTagList2.tagCount()) {
                    if (Enchantment.getEnchantmentByID((int)getEnchantmentTagList2.getCompoundTagAt(n5).getShort("id")) == null) {}
                    ++n5;
                    return 0.0f;
                }
                if (n4 > n) {
                    n = n4;
                }
            }
            return (b ? 0 : 20) + n;
        }
        final ItemStack itemStack = iterator.next();
        final float n6 = 0.0f;
        if (itemStack != null) {
            final NBTTagList getEnchantmentTagList3 = itemStack.getEnchantmentTagList();
            int n7 = 0;
            if (n7 < getEnchantmentTagList3.tagCount()) {
                if (Enchantment.getEnchantmentByID((int)getEnchantmentTagList3.getCompoundTagAt(n7).getShort("id")) == null) {}
                ++n7;
                return 0.0f;
            }
        }
        if (n6 <= n) {
            return 0.0f;
        }
        return 0.0f;
    }
    
    private int getDisplayColor(final EntityPlayer entityPlayer) {
        int n = ColorUtil.toRGBA((Color)this.color.getValue());
        if (Managers.FRIENDS.isFriend(entityPlayer) && this.friend.booleanValue) {
            return ColorUtil.toRGBA((Color)this.friend.getValue());
        }
        if (entityPlayer.isInvisible() && this.invisible.booleanValue) {
            n = ColorUtil.toRGBA((Color)this.invisible.getValue());
        }
        else if (entityPlayer.isSneaking() && this.sneak.booleanValue) {
            n = ColorUtil.toRGBA((Color)this.sneak.getValue());
        }
        return n;
    }
    
    private boolean lambda$new$3(final Boolean b) {
        return this.max.isOpen();
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.scale.isOpen();
    }
    
    private void renderNameTag(final EntityPlayer entityPlayer, final double n, final double n2, final double n3, final float n4) {
        final double n5 = n2 + (entityPlayer.isSneaking() ? 0.5 : 0.7);
        final Entity getRenderViewEntity = NameTags.mc.getRenderViewEntity();
        assert getRenderViewEntity != null;
        final double posX = getRenderViewEntity.posX;
        final double posY = getRenderViewEntity.posY;
        final double posZ = getRenderViewEntity.posZ;
        getRenderViewEntity.posX = this.interpolate(getRenderViewEntity.prevPosX, getRenderViewEntity.posX, n4);
        getRenderViewEntity.posY = this.interpolate(getRenderViewEntity.prevPosY, getRenderViewEntity.posY, n4);
        getRenderViewEntity.posZ = this.interpolate(getRenderViewEntity.prevPosZ, getRenderViewEntity.posZ, n4);
        final String displayTag = this.getDisplayTag(entityPlayer);
        final double getDistance = getRenderViewEntity.getDistance(n + NameTags.mc.getRenderManager().viewerPosX, n2 + NameTags.mc.getRenderManager().viewerPosY, n3 + NameTags.mc.getRenderManager().viewerPosZ);
        final int n6 = Managers.TEXT.getMCStringWidth(displayTag) / 2;
        double n7 = (0.0018 + (float)this.size.getValue() * (getDistance * (float)this.factor.getValue())) / 1000.0;
        if (getDistance <= 6.0 && (boolean)this.smartScale.getValue()) {
            n7 = (0.0018 + ((float)this.size.getValue() + 2.0f) * (getDistance * (float)this.factor.getValue())) / 1000.0;
        }
        if (getDistance <= 4.0 && (boolean)this.smartScale.getValue()) {
            n7 = (0.0018 + ((float)this.size.getValue() + 4.0f) * (getDistance * (float)this.factor.getValue())) / 1000.0;
        }
        if (!(boolean)this.scale.getValue()) {
            n7 = (float)this.size.getValue() / 100.0;
        }
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, -1500000.0f);
        GlStateManager.disableLighting();
        GlStateManager.translate((float)n, (float)n5 + 1.4f, (float)n3);
        GlStateManager.rotate(-NameTags.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(NameTags.mc.getRenderManager().playerViewX, (NameTags.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-n7, -n7, n7);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.enableBlend();
        if (this.rect.getValue()) {
            this.drawRect((float)(-n6 - 2), (float)(-(NameTags.mc.fontRenderer.FONT_HEIGHT + 1)), n6 + 2.0f, 1.5f, 1426063360);
        }
        else if (!this.outLine.booleanValue) {
            this.drawRect(0.0f, 0.0f, 0.0f, 0.0f, 1426063360);
        }
        if (this.outLine.booleanValue) {
            this.drawOutlineRect((float)(-n6 - 2), (float)(-(NameTags.mc.fontRenderer.FONT_HEIGHT + 1)), n6 + 2.0f, 1.5f, this.getOutlineColor());
        }
        GlStateManager.disableBlend();
        final ItemStack copy = entityPlayer.getHeldItemMainhand().copy();
        if (copy.hasEffect() && (copy.getItem() instanceof ItemTool || copy.getItem() instanceof ItemArmor)) {
            copy.stackSize = 1;
        }
        if ((boolean)this.heldStackName.getValue() && !copy.isEmpty && copy.getItem() != Items.AIR) {
            final String getDisplayName = copy.getDisplayName();
            final int n8 = Managers.TEXT.getMCStringWidth(getDisplayName) / 2;
            GL11.glPushMatrix();
            GL11.glScalef(0.75f, 0.75f, 0.0f);
            Managers.TEXT.drawMCString(getDisplayName, (float)(-n8), -(this.getBiggestArmorTag(entityPlayer) + 20.0f), -1, true);
            GL11.glScalef(1.5f, 1.5f, 1.0f);
            GL11.glPopMatrix();
        }
        if (this.armor.getValue()) {
            GlStateManager.pushMatrix();
            int n9 = -6;
            final Iterator iterator = entityPlayer.inventory.armorInventory.iterator();
            if (iterator.hasNext()) {
                if (iterator.next() == null) {
                    return;
                }
                n9 -= 8;
                return;
            }
            else {
                n9 -= 8;
                final ItemStack copy2 = entityPlayer.getHeldItemOffhand().copy();
                if (copy2.hasEffect() && (copy2.getItem() instanceof ItemTool || copy2.getItem() instanceof ItemArmor)) {
                    copy2.stackSize = 1;
                }
                this.renderItemStack(copy2, n9);
                n9 += 16;
                if (this.reversed.getValue()) {
                    int n10 = 0;
                    if (n10 <= 3) {
                        final ItemStack itemStack = (ItemStack)entityPlayer.inventory.armorInventory.get(n10);
                        if (itemStack.getItem() != Items.AIR) {
                            itemStack.copy();
                            this.renderItemStack(itemStack, n9);
                            n9 += 16;
                        }
                        ++n10;
                        return;
                    }
                }
                else {
                    int n11 = 3;
                    if (n11 >= 0) {
                        final ItemStack itemStack2 = (ItemStack)entityPlayer.inventory.armorInventory.get(n11);
                        if (itemStack2.getItem() != Items.AIR) {
                            itemStack2.copy();
                            this.renderItemStack(itemStack2, n9);
                            n9 += 16;
                        }
                        --n11;
                        return;
                    }
                }
                this.renderItemStack(copy, n9);
                GlStateManager.popMatrix();
            }
        }
        Managers.TEXT.drawMCString(displayTag, (float)(-n6), (float)(-(Managers.TEXT.getFontHeight() - 1)), this.getDisplayColor(entityPlayer), true);
        getRenderViewEntity.posX = posX;
        getRenderViewEntity.posY = posY;
        getRenderViewEntity.posZ = posZ;
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.disablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
        GlStateManager.popMatrix();
    }
    
    private void renderItemStack(final ItemStack itemStack, final int n) {
        GlStateManager.pushMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.clear(256);
        RenderHelper.enableStandardItemLighting();
        NameTags.mc.getRenderItem().zLevel = -150.0f;
        GlStateManager.disableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.disableCull();
        NameTags.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, n, -26);
        NameTags.mc.getRenderItem().renderItemOverlays(NameTags.mc.fontRenderer, itemStack, n, -26);
        NameTags.mc.getRenderItem().zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        GlStateManager.disableDepth();
        this.renderEnchantmentText(itemStack, n);
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GlStateManager.popMatrix();
    }
    
    private double interpolate(final double n, final double n2, final float n3) {
        return n + (n2 - n) * n3;
    }
    
    private boolean lambda$new$2(final Float n) {
        return this.scale.isOpen();
    }
    
    private int getOutlineColor() {
        return ColorUtil.toRGBA((Color)this.outLine.getValue());
    }
}
