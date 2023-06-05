//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.hud;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.api.managers.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;
import me.rebirthclient.mod.modules.*;

public class BindList extends Module
{
    private final Setting x;
    private final Setting y;
    private final Setting color;
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        final int n = 0;
        final int intValue = (int)this.y.getValue();
        int n2 = 0;
        if (n2 < Managers.MODULES.sortedLength.size()) {
            final Module module = Managers.MODULES.sortedLength.get(n2);
            if (module.getBind().getKey() != -1) {
                final String name = module.getName();
                if (Managers.TEXT.getStringWidth(name) > n) {
                    Managers.TEXT.getStringWidth(name);
                }
            }
            ++n2;
            return;
        }
        int n3 = 0;
        if (n3 < Managers.MODULES.sortedLength.size()) {
            final Module module2 = Managers.MODULES.sortedLength.get(n3);
            if (module2.getBind().getKey() != -1) {
                Managers.TEXT.drawString(module2.getName(), (float)(int)this.x.getValue(), (float)intValue, -1, true);
                Managers.TEXT.drawString("[toggled]", (float)((int)this.x.getValue() + 10 + n), (float)intValue, -1, true);
            }
            ++n3;
            return;
        }
        RenderUtil.drawRectangleCorrectly((int)this.x.getValue() - 4, (int)this.y.getValue() - 16, 18 + Managers.TEXT.getStringWidth("[toggled]") + n, 1, ColorUtil.toRGBA(((Color)this.color.getValue()).getRed(), ((Color)this.color.getValue()).getGreen(), ((Color)this.color.getValue()).getBlue(), 255));
        RenderUtil.drawRectangleCorrectly((int)this.x.getValue() - 4, (int)this.y.getValue() - 15, 18 + Managers.TEXT.getStringWidth("[toggled]") + n, 12, ColorUtil.toRGBA(new Color(0, 0, 0, ((Color)this.color.getValue()).getAlpha())));
        Managers.TEXT.drawString("keybinds", (float)((int)this.x.getValue() + (11 + n + (double)Managers.TEXT.getStringWidth("[toggled]")) / 2.0 - Managers.TEXT.getStringWidth("keybinds") / 2.0), (float)((int)this.y.getValue() - 13), ColorUtil.toRGBA(255, 255, 255, 255), true);
    }
    
    public BindList() {
        super("BindList", "test", Category.HUD);
        this.x = this.add(new Setting("x", 50, 0, 500));
        this.y = this.add(new Setting("y", 50, 0, 500));
        this.color = this.add(new Setting("color", new Color(255, 255, 255, 50)));
    }
}
