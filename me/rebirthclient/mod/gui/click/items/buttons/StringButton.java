//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.gui.click.items.buttons;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.render.*;
import java.awt.*;
import me.rebirthclient.mod.gui.screen.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;

public class StringButton extends Button
{
    public boolean isListening;
    private final Setting setting;
    private CurrentString currentString;
    
    private void enterString() {
        if (this.currentString.getString().isEmpty()) {
            this.setting.setValue(this.setting.getDefaultValue());
        }
        else {
            this.setting.setValue(this.currentString.getString());
        }
        this.setString(this.setting.getValue().toString() + "1");
        this.onMouseClick();
    }
    
    public int getHeight() {
        return ClickGui.INSTANCE.getButtonHeight() - 1;
    }
    
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        final boolean b = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.FUTURE;
        final boolean b2 = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.DOTGOD;
        if (b) {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.isHovering(n, n2) ? Managers.COLORS.getCurrentWithAlpha(90) : Managers.COLORS.getCurrentWithAlpha(26));
        }
        else if (b2) {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.isHovering(n, n2) ? Managers.COLORS.getCurrentWithAlpha(120) : Managers.COLORS.getCurrentWithAlpha(26));
        }
        else if (this.isHovering(n, n2)) {
            RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, Managers.COLORS.getCurrentWithAlpha(200));
        }
        RenderUtil.drawRect(this.x, this.y + this.height - 0.5f, this.x + this.width + 7.4f, this.y + this.height - 1.5f, new Color(255, 255, 255, 200).getRGB());
        if (this.isListening) {
            Managers.TEXT.drawStringWithShadow(this.currentString.getString() + Managers.TEXT.getIdleSign(), this.x + 2.3f, this.y - 1.7f - Gui.INSTANCE.getTextOffset(), (this != 0) ? -1 : -5592406);
        }
        else {
            Managers.TEXT.drawStringWithShadow(this.setting.getName() + " " + ChatFormatting.GRAY + this.setting.getValue(), this.x + 2.3f, this.y - 1.7f - Gui.INSTANCE.getTextOffset(), (this != 0) ? -1 : -5592406);
        }
    }
    
    public void onKeyTyped(final char c, final int n) {
        if (this.isListening) {
            switch (n) {
                case 1: {
                    return;
                }
                case 28: {
                    this.enterString();
                }
                case 14: {
                    this.setString(removeLastChar(this.currentString.getString()));
                    break;
                }
            }
            if (ChatAllowedCharacters.isAllowedCharacter(c)) {
                this.setString(this.currentString.getString() + c);
            }
        }
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            StringButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
    }
    
    public void toggle() {
        this.isListening = !this.isListening;
    }
    
    public void setString(final String s) {
        this.currentString = new CurrentString(s);
    }
    
    public StringButton(final Setting setting) {
        super(setting.getName());
        this.currentString = new CurrentString("");
        this.setting = setting;
        this.width = 15;
    }
    
    public static String removeLastChar(final String s) {
        String substring = "";
        if (s != null && s.length() > 0) {
            substring = s.substring(0, s.length() - 1);
        }
        return substring;
    }
    
    public static class CurrentString
    {
        private final String string;
        
        public String getString() {
            return this.string;
        }
        
        public CurrentString(final String string) {
            this.string = string;
        }
    }
}
