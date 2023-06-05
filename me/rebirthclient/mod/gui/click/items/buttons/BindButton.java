//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.gui.click.items.buttons;

import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import me.rebirthclient.mod.modules.impl.client.*;
import java.awt.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.render.*;
import me.rebirthclient.mod.gui.screen.*;
import com.mojang.realmsclient.gui.*;
import me.rebirthclient.mod.modules.settings.*;

public class BindButton extends Button
{
    private final Setting setting;
    public boolean isListening;
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            BindButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
    }
    
    @Override
    public void toggle() {
        this.isListening = !this.isListening;
    }
    
    @Override
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final float n3) {
        final boolean b = ClickGui.INSTANCE.style.getValue() == ClickGui.Style.NEW || ClickGui.INSTANCE.style.getValue() == ClickGui.Style.DOTGOD;
        ColorUtil.toARGB(((Color)ClickGui.INSTANCE.color.getValue()).getRed(), ((Color)ClickGui.INSTANCE.color.getValue()).getGreen(), ((Color)ClickGui.INSTANCE.color.getValue()).getBlue(), 255);
        RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, (this != 0) ? (this.isHovering(n, n2) ? -2007673515 : 290805077) : (this.isHovering(n, n2) ? Managers.COLORS.getCurrentGui(90) : Managers.COLORS.getCurrentGui(200)));
        if (this.isListening) {
            Managers.TEXT.drawStringWithShadow("Press a Key...", this.x + 2.3f, this.y - 1.7f - Gui.INSTANCE.getTextOffset(), -1);
        }
        else {
            Managers.TEXT.drawStringWithShadow((b ? this.setting.getName().toLowerCase() : this.setting.getName()) + " " + ChatFormatting.GRAY + this.setting.getValue().toString().toUpperCase(), this.x + 2.3f, this.y - 1.7f - Gui.INSTANCE.getTextOffset(), (this != 0) ? -1 : -5592406);
        }
    }
    
    @Override
    public int getHeight() {
        return ClickGui.INSTANCE.getButtonHeight() - 1;
    }
    
    public BindButton(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }
    
    @Override
    public void onKeyTyped(final char c, final int n) {
        if (this.isListening) {
            Bind value = new Bind(n);
            if (Integer.valueOf("Escape".toUpperCase().hashCode()).equals(value.toString().toUpperCase().hashCode())) {
                return;
            }
            if (Integer.valueOf("Delete".toUpperCase().hashCode()).equals(value.toString().toUpperCase().hashCode())) {
                value = new Bind(-1);
            }
            this.setting.setValue(value);
            this.onMouseClick();
        }
    }
}
