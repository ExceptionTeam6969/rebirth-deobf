//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.gui.click.items.buttons;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.render.*;
import com.mojang.realmsclient.gui.*;
import me.rebirthclient.mod.gui.screen.*;
import java.util.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;

public class EnumButton extends Button
{
    public final Setting setting;
    
    public boolean getState() {
        return true;
    }
    
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        final boolean b = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.NEW || ClickGui.INSTANCE.style.getValue() == ClickGui.Style.DOTGOD;
        final boolean b2 = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.FUTURE;
        final boolean b3 = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.DOTGOD;
        if (b2) {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? Managers.COLORS.getCurrentWithAlpha(120) : Managers.COLORS.getCurrentWithAlpha(99)) : (this.isHovering(n, n2) ? Managers.COLORS.getCurrentWithAlpha(55) : Managers.COLORS.getCurrentWithAlpha(26)));
        }
        else if (b3) {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? Managers.COLORS.getCurrentWithAlpha(90) : Managers.COLORS.getCurrentWithAlpha(65)) : (this.isHovering(n, n2) ? Managers.COLORS.getCurrentWithAlpha(35) : Managers.COLORS.getCurrentWithAlpha(26)));
        }
        else {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? Managers.COLORS.getCurrentWithAlpha(200) : Managers.COLORS.getCurrentWithAlpha(120)) : (this.isHovering(n, n2) ? -2007673515 : 290805077));
        }
        Managers.TEXT.drawStringWithShadow((b ? (this.setting.getName().toLowerCase() + ":") : this.setting.getName()) + " " + ChatFormatting.GRAY + (Integer.valueOf("ABC".toUpperCase().hashCode()).equals(this.setting.getCurrentEnumName().toUpperCase().hashCode()) ? "ABC" : this.setting.getCurrentEnumName()), this.x + 2.3f, this.y - 1.7f - Gui.INSTANCE.getTextOffset(), this.getState() ? -1 : -5592406);
        int n4 = (int)this.y;
        if (this.setting.open) {
            final?[] enumConstants = this.setting.getValue().getClass().getEnumConstants();
            final int length = enumConstants.length;
            int n5 = 0;
            if (n5 < length) {
                final Object o = enumConstants[n5];
                n4 += 12;
                final String s = Objects.equals(o.toString(), "ABC") ? o.toString() : (Character.toUpperCase(o.toString().charAt(0)) + o.toString().toLowerCase().substring(1));
                Managers.TEXT.drawStringWithShadow((Integer.valueOf(s.hashCode()).equals(this.setting.getCurrentEnumName().hashCode()) ? ChatFormatting.WHITE : ChatFormatting.GRAY) + s, this.width / 2.0f - Managers.TEXT.getStringWidth(s) / 2.0f + 2.0f + this.x, n4 + 6.0f - EnumButton.mc.fontRenderer.FONT_HEIGHT / 2.0f + 3.5f, -1);
                ++n5;
            }
        }
    }
    
    public int getHeight() {
        return ClickGui.INSTANCE.getButtonHeight() - 1;
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            EnumButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
        if (n3 == 1 && this.isHovering(n, n2)) {
            this.setting.open = !this.setting.open;
            EnumButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
        if (this.setting.open) {
            final?[] enumConstants = this.setting.getValue().getClass().getEnumConstants();
            final int length = enumConstants.length;
            int n4 = 0;
            if (n4 < length) {
                final Object o = enumConstants[n4];
                this.y += 12.0f;
                if (n > this.x && n < this.x + this.width && n2 > this.y && n2 < this.y + 12.0f + 3.5f && n3 == 0) {
                    this.setting.setEnumValue(String.valueOf(o));
                    EnumButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
                }
                ++n4;
            }
        }
    }
    
    public void toggle() {
        this.setting.increaseEnum();
    }
    
    public EnumButton(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }
}
