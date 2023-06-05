//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import java.awt.*;

public class CrystalChams extends Module
{
    public final Setting color;
    public final Setting changeSpeed;
    public final Setting wireframe;
    public final Setting modelColor;
    private final Setting page;
    public final Setting lineWidth;
    public final Setting spinSpeed;
    public static CrystalChams INSTANCE;
    public final Setting floatFactor;
    public final Setting glint;
    public final Setting fill;
    public final Setting lineColor;
    public final Setting scale;
    public final Setting model;
    public final Setting xqz;
    
    public CrystalChams() {
        super("CrystalChams", "Draws a pretty ESP around end crystals", Category.RENDER);
        this.page = this.add(new Setting("Settings", Page.GLOBAL));
        this.fill = this.add(new Setting("Fill", true, this::lambda$new$0).setParent());
        this.xqz = this.add(new Setting("XQZ", true, this::lambda$new$1));
        this.wireframe = this.add(new Setting("Wireframe", true, this::lambda$new$2));
        this.model = this.add(new Setting("Model", Model.XQZ, this::lambda$new$3));
        this.glint = this.add(new Setting("Glint", false, this::lambda$new$4));
        this.scale = this.add(new Setting("Scale", 1.0f, 0.1f, 1.0f, this::lambda$new$5));
        this.changeSpeed = this.add(new Setting("ChangeSpeed", false, this::lambda$new$6).setParent());
        this.spinSpeed = this.add(new Setting("SpinSpeed", 1.0f, 0.0f, 10.0f, this::lambda$new$7));
        this.floatFactor = this.add(new Setting("FloatFactor", 1.0f, 0.0f, 1.0f, this::lambda$new$8));
        this.lineWidth = this.add(new Setting("LineWidth", 1.0f, 0.1f, 3.0f, this::lambda$new$9));
        this.color = this.add(new Setting("Color", new Color(132, 132, 241, 150), this::lambda$new$10));
        this.lineColor = this.add(new Setting("LineColor", new Color(255, 255, 255), this::lambda$new$11).injectBoolean(false));
        this.modelColor = this.add(new Setting("ModelColor", new Color(125, 125, 213, 150), this::lambda$new$12).injectBoolean(false));
        CrystalChams.INSTANCE = this;
    }
    
    @Override
    public String getInfo() {
        String s = null;
        if (this.fill.getValue()) {
            s = "Fill";
        }
        else if (this.wireframe.getValue()) {
            s = "Wireframe";
        }
        if ((boolean)this.wireframe.getValue() && (boolean)this.fill.getValue()) {
            s = "Both";
        }
        return s;
    }
    
    private boolean lambda$new$5(final Float n) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$9(final Float n) {
        return this.page.getValue() == Page.COLORS;
    }
    
    private boolean lambda$new$10(final Color color) {
        return this.page.getValue() == Page.COLORS;
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$11(final Color color) {
        return this.page.getValue() == Page.COLORS;
    }
    
    private boolean lambda$new$4(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$3(final Model model) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$6(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$2(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL && this.fill.isOpen();
    }
    
    private boolean lambda$new$12(final Color color) {
        return this.page.getValue() == Page.COLORS;
    }
    
    private boolean lambda$new$8(final Float n) {
        return this.page.getValue() == Page.GLOBAL && this.changeSpeed.isOpen();
    }
    
    private boolean lambda$new$7(final Float n) {
        return this.page.getValue() == Page.GLOBAL && this.changeSpeed.isOpen();
    }
    
    public enum Model
    {
        XQZ("XQZ", 0);
        
        private static final Model[] $VALUES;
        
        OFF("OFF", 2), 
        VANILLA("VANILLA", 1);
        
        static {
            $VALUES = new Model[] { Model.XQZ, Model.VANILLA, Model.OFF };
        }
        
        private Model(final String s, final int n) {
        }
    }
    
    public enum Page
    {
        private static final Page[] $VALUES;
        
        GLOBAL("GLOBAL", 1), 
        COLORS("COLORS", 0);
        
        static {
            $VALUES = new Page[] { Page.COLORS, Page.GLOBAL };
        }
        
        private Page(final String s, final int n) {
        }
    }
}
