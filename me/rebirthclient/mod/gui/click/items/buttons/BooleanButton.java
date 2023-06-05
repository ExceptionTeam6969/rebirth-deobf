//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.gui.click.items.buttons;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.render.*;
import me.rebirthclient.mod.gui.screen.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.*;
import me.rebirthclient.mod.gui.click.*;
import com.mojang.realmsclient.gui.*;

public class BooleanButton extends Button
{
    private final Setting setting;
    private int progress;
    
    @Override
    public boolean getState() {
        return (boolean)this.setting.getValue();
    }
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            BooleanButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
        if (n3 == 1 && this.isHovering(n, n2)) {
            this.setting.open = !this.setting.open;
            BooleanButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
    }
    
    @Override
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    @Override
    public int getHeight() {
        return ClickGui.INSTANCE.getButtonHeight() - 1;
    }
    
    public BooleanButton(final Setting setting) {
        super(setting.getName());
        this.progress = 0;
        this.setting = setting;
        this.width = 15;
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final float n3) {
        final boolean b = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.NEW;
        final boolean b2 = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.FUTURE;
        final boolean b3 = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.DOTGOD;
        if (b2) {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? Managers.COLORS.getCurrentWithAlpha(120) : Managers.COLORS.getCurrentWithAlpha(99)) : (this.isHovering(n, n2) ? Managers.COLORS.getCurrentWithAlpha(55) : Managers.COLORS.getCurrentWithAlpha(26)));
            Managers.TEXT.drawStringWithShadow(b ? this.getName().toLowerCase() : this.getName(), this.x + 2.3f, this.y - 1.7f - Gui.INSTANCE.getTextOffset(), this.getState() ? -1 : -5592406);
        }
        else if (b3) {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? Managers.COLORS.getCurrentWithAlpha(90) : Managers.COLORS.getCurrentWithAlpha(65)) : (this.isHovering(n, n2) ? Managers.COLORS.getCurrentWithAlpha(55) : Managers.COLORS.getCurrentWithAlpha(26)));
            Managers.TEXT.drawStringWithShadow(this.getName().toLowerCase(), this.x + 2.3f, this.y - 1.7f - Gui.INSTANCE.getTextOffset(), this.getState() ? Managers.COLORS.getCurrentGui(240) : 11579568);
        }
        else {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? Managers.COLORS.getCurrentWithAlpha(200) : Managers.COLORS.getCurrentWithAlpha(120)) : (this.isHovering(n, n2) ? -2007673515 : 290805077));
            Managers.TEXT.drawStringWithShadow(b ? this.getName().toLowerCase() : this.getName(), this.x + 2.3f, this.y - 1.7f - Gui.INSTANCE.getTextOffset(), this.getState() ? -1 : -5592406);
        }
        if (this.setting.parent) {
            if (this.setting.open) {
                ++this.progress;
            }
            if (b2) {
                if (!this.setting.open) {
                    this.progress = 0;
                }
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                BooleanButton.mc.getTextureManager().bindTexture(new ResourceLocation("textures/rebirth/gear.png"));
                GlStateManager.translate(this.getX() + this.getWidth() - 6.7f + 8.0f, this.getY() + 7.7f - 0.3f, 0.0f);
                GlStateManager.rotate(Component.calculateRotation((float)this.progress), 0.0f, 0.0f, 1.0f);
                RenderUtil.drawModalRect(-5, -5, 0.0f, 0.0f, 10, 10, 10, 10, 10.0f, 10.0f);
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }
            else {
                Managers.TEXT.drawStringWithShadow(((this.getState() || b) ? "" : ("" + ChatFormatting.GRAY)) + (this.setting.open ? "-" : "+"), this.x - 1.5f + this.width - 7.4f + 8.0f, this.y - 2.2f - Gui.INSTANCE.getTextOffset(), -1);
            }
        }
    }
    
    @Override
    public void toggle() {
        this.setting.setValue(!(boolean)this.setting.getValue());
    }
}
