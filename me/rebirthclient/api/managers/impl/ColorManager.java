//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import java.awt.*;
import me.rebirthclient.mod.modules.impl.client.*;
import me.rebirthclient.api.util.render.*;
import me.rebirthclient.mod.gui.click.*;

public class ColorManager
{
    public int rainbowProgress;
    private Color current;
    
    public ColorManager() {
        this.rainbowProgress = 1;
        this.current = new Color(-1);
    }
    
    public Color getRainbow() {
        return ColorUtil.rainbow((int)ClickGui.INSTANCE.rainbowDelay.getValue());
    }
    
    public boolean isRainbow() {
        return (boolean)ClickGui.INSTANCE.rainbow.getValue();
    }
    
    public Color getCurrent() {
        if (this.isRainbow()) {
            return this.getRainbow();
        }
        return this.current;
    }
    
    public int getCurrentGui(final int n) {
        if (this.isRainbow()) {
            return ColorUtil.rainbow(Component.counter1[0] * (int)ClickGui.INSTANCE.rainbowDelay.getValue()).getRGB();
        }
        return ColorUtil.toRGBA(ColorUtil.injectAlpha(this.current, n));
    }
    
    public Color getFriendColor(final int n) {
        return new Color(0, 191, 255, n);
    }
    
    public int getCurrentWithAlpha(final int n) {
        if (this.isRainbow()) {
            return ColorUtil.toRGBA(ColorUtil.injectAlpha(this.getRainbow(), n));
        }
        return ColorUtil.toRGBA(ColorUtil.injectAlpha(this.current, n));
    }
    
    public void setCurrent(final Color current) {
        this.current = current;
    }
    
    public Color getNormalCurrent() {
        return this.current;
    }
}
