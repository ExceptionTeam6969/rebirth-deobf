//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import java.awt.*;
import me.rebirthclient.mod.modules.*;

public class GlintModify extends Module
{
    public final Setting color;
    public static GlintModify INSTANCE;
    
    public static Color getColor() {
        return (Color)GlintModify.INSTANCE.color.getValue();
    }
    
    public GlintModify() {
        super("GlintModify", "Changes the enchant glint color", Category.RENDER);
        this.color = this.add(new Setting("Color", new Color(-557395713, true)).hideAlpha());
        GlintModify.INSTANCE = this;
    }
    
    static {
        GlintModify.INSTANCE = new GlintModify();
    }
}
