//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.hud;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.api.managers.*;
import java.awt.*;
import me.rebirthclient.api.util.render.*;
import java.util.*;
import me.rebirthclient.mod.modules.*;
import me.rebirthclient.api.util.*;

public class Notifications extends Module
{
    private final Setting notifyY;
    private final Setting color;
    public static final ArrayList notifyList;
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        final int n = Managers.TEXT.scaledHeight - (int)this.notifyY.getValue();
        final int scaledWidth = Managers.TEXT.scaledWidth;
        final boolean b = true;
        final int rgba = ColorUtil.toRGBA(((Color)this.color.getValue()).getRed(), ((Color)this.color.getValue()).getGreen(), ((Color)this.color.getValue()).getBlue(), 255);
        if (Notifications.notifyList.isEmpty()) {
            return;
        }
        for (final Notifys notifys : Notifications.notifyList) {
            if (notifys != null) {
                if (notifys.first == null) {
                    return;
                }
                if (notifys.delayed < 1) {
                    return;
                }
                if (notifys.delayed < 5 && !notifys.end) {
                    notifys.end = true;
                    notifys.endFade.reset();
                }
                final int n2 = (int)(n - 18.0 * notifys.yFade.easeOutQuad());
                final String first = notifys.first;
                double n3;
                if (notifys.delayed < 5) {
                    n3 = scaledWidth - (Managers.TEXT.getStringWidth(first) + 10) * (1.0 - notifys.endFade.easeOutQuad());
                }
                else {
                    n3 = scaledWidth - (Managers.TEXT.getStringWidth(first) + 10) * notifys.firstFade.easeOutQuad();
                }
                if (((Color)this.color.getValue()).getAlpha() > 5) {
                    RenderUtil.drawRectangleCorrectly((int)n3, n2, 10 + Managers.TEXT.getStringWidth(first), 15, ColorUtil.toRGBA(20, 20, 20, ((Color)this.color.getValue()).getAlpha()));
                }
                Managers.TEXT.drawString(first, (float)(5 + (int)n3), (float)(4 + n2), ((Color)this.color.getValue()).getRGB(), true);
                if (notifys.delayed < 5) {
                    final int n4 = (int)(n2 + 18.0 * notifys.yFade.easeOutQuad() - 18.0 * (1.0 - notifys.endFade.easeOutQuad()));
                }
                else {
                    RenderUtil.drawRectangleCorrectly((int)n3, n2 + 14, (10 + Managers.TEXT.getStringWidth(first)) * (notifys.delayed - 4) / 62, 1, rgba);
                }
                return;
            }
        }
        if (b) {
            Notifications.notifyList.clear();
        }
    }
    
    @Override
    public void onEnable() {
        Notifications.notifyList.clear();
    }
    
    @Override
    public void onDisable() {
        Notifications.notifyList.clear();
    }
    
    @Override
    public void onUpdate() {
        final Iterator<Notifys> iterator = (Iterator<Notifys>)Notifications.notifyList.iterator();
        if (!iterator.hasNext()) {
            return;
        }
        final Notifys notifys = iterator.next();
        if (notifys == null) {
            return;
        }
        if (notifys.first == null) {
            return;
        }
        --notifys.delayed;
    }
    
    public static void add(final String s) {
        Notifications.notifyList.add(new Notifys(s));
    }
    
    static {
        notifyList = new ArrayList();
    }
    
    public Notifications() {
        super("Notifications", "Notify toggle module", Category.HUD);
        this.color = this.add(new Setting("Color", new Color(255, 255, 255)));
        this.notifyY = this.add(new Setting("Y", 18, 25, 500));
    }
    
    public static class Notifys
    {
        public final String first;
        public final FadeUtils yFade;
        public boolean end;
        public final FadeUtils endFade;
        public int delayed;
        public final FadeUtils firstFade;
        
        public Notifys(final String first) {
            this.firstFade = new FadeUtils(500L);
            this.yFade = new FadeUtils(500L);
            this.endFade = new FadeUtils(350L);
            this.delayed = 55;
            this.first = first;
            this.firstFade.reset();
            this.yFade.reset();
            this.endFade.reset();
            this.end = false;
        }
    }
}
