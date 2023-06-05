//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import java.awt.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;

public class Models extends Module
{
    public Setting legsColor;
    public Setting onlySelf;
    public Setting eyeColor;
    public Setting bodyColor;
    public static Models INSTANCE;
    public Setting Mode;
    public Setting friendHighlight;
    public Setting friends;
    
    private boolean lambda$new$3(final Color color) {
        return this.Mode.getValue() == mode.AmongUs;
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.friends.isOpen() && this.onlySelf.isOpen();
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.onlySelf.isOpen();
    }
    
    public Models() {
        super("Models", "something", Category.RENDER);
        this.Mode = this.add(new Setting("Mode", mode.AmongUs));
        this.onlySelf = this.add(new Setting("OnlySelf", false).setParent());
        this.friends = this.add(new Setting("Friends", false, this::lambda$new$0).setParent());
        this.friendHighlight = this.add(new Setting("friendHighLight", false, this::lambda$new$1));
        this.eyeColor = this.add(new Setting("eyeColor", new Color(255, 255, 255), this::lambda$new$2));
        this.bodyColor = this.add(new Setting("bodyColor", new Color(255, 0, 0), this::lambda$new$3));
        this.legsColor = this.add(new Setting("legsColor", new Color(255, 0, 0), this::lambda$new$4));
        Models.INSTANCE = this;
    }
    
    private boolean lambda$new$4(final Color color) {
        return this.Mode.getValue() == mode.AmongUs;
    }
    
    private boolean lambda$new$2(final Color color) {
        return this.Mode.getValue() == mode.AmongUs;
    }
    
    public enum mode
    {
        Rabbit("Rabbit", 1), 
        AmongUs("AmongUs", 0);
        
        private static final mode[] $VALUES;
        
        Freddy("Freddy", 2);
        
        static {
            $VALUES = new mode[] { mode.AmongUs, mode.Rabbit, mode.Freddy };
        }
        
        private mode(final String s, final int n) {
        }
    }
}
