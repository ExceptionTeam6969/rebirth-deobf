//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.client;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import org.lwjgl.opengl.*;

public class Title extends Module
{
    public final Setting title;
    private static int titleLength;
    public final Setting updateTime;
    public final Setting animation;
    private static String lastTitle;
    public static Title INSTANCE;
    private static boolean original;
    private static boolean back;
    private static int breakTimer;
    private static final Timer updateTimer;
    
    public Title() {
        super("Title", "Change client title", Category.CLIENT);
        this.title = this.add(new Setting("Title", "Rebirth alpha"));
        this.animation = this.add(new Setting("Animation", true).setParent());
        this.updateTime = this.add(new Setting("updateTime", 300, 0, 1000, this::lambda$new$0));
        Title.INSTANCE = this;
    }
    
    public static void updateTitle() {
        if (!Title.original) {
            Display.setTitle("Minecraft 1.12.2");
            Title.original = true;
        }
        if (Title.INSTANCE.isOff()) {
            return;
        }
        if (Title.lastTitle != null && !Integer.valueOf(Title.INSTANCE.title.getValue().hashCode()).equals(Title.lastTitle.hashCode())) {
            Title.updateTimer.reset();
            Title.titleLength = 0;
            Title.breakTimer = 0;
            Title.back = false;
        }
        Title.lastTitle = (String)Title.INSTANCE.title.getValue();
        if (Title.INSTANCE.animation.getValue()) {
            if (Title.lastTitle != null && Title.updateTimer.passedMs((long)(int)Title.INSTANCE.updateTime.getValue())) {
                Title.updateTimer.reset();
                Display.setTitle(Title.lastTitle.substring(0, Title.lastTitle.length() - Title.titleLength));
                if ((Title.titleLength == Title.lastTitle.length() && Title.breakTimer != 2) || (Title.titleLength == 0 && Title.breakTimer != 4)) {
                    ++Title.breakTimer;
                    return;
                }
                Title.breakTimer = 0;
                if (Title.titleLength == Title.lastTitle.length()) {
                    Title.back = true;
                }
                if (Title.back) {
                    --Title.titleLength;
                }
                else {
                    ++Title.titleLength;
                }
                if (Title.titleLength == 0) {
                    Title.back = false;
                }
            }
        }
        else {
            Display.setTitle(Title.lastTitle);
        }
    }
    
    static {
        Title.INSTANCE = new Title();
        updateTimer = new Timer();
        Title.titleLength = 0;
        Title.breakTimer = 0;
        Title.back = false;
        Title.original = false;
    }
    
    private boolean lambda$new$0(final Integer n) {
        return this.animation.isOpen();
    }
    
    @Override
    public void onDisable() {
        Display.setTitle("Minecraft 1.12.2");
    }
}
