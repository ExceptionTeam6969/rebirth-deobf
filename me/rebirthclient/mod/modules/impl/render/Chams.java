//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import java.awt.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;

public class Chams extends Module
{
    public final Setting model;
    public final Setting sneak;
    public final Setting modelColor;
    public final Setting self;
    public final Setting noInterp;
    public final Setting color;
    public final Setting glint;
    public final Setting wireframe;
    private final Setting page;
    public static Chams INSTANCE;
    public final Setting xqz;
    private final Setting range;
    public final Setting lineColor;
    public final Setting fill;
    public final Setting lineWidth;
    private final Setting hide;
    
    private boolean lambda$new$12(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$6(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$11(final Color color) {
        return this.page.getValue() == Page.COLORS;
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$2(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    public Chams() {
        super("Chams", "Draws a pretty ESP around other players", Category.RENDER);
        this.page = this.add(new Setting("Settings", Page.GLOBAL));
        this.fill = this.add(new Setting("Fill", true, this::lambda$new$0).setParent());
        this.xqz = this.add(new Setting("XQZ", true, this::lambda$new$1));
        this.wireframe = this.add(new Setting("Wireframe", true, this::lambda$new$2));
        this.model = this.add(new Setting("Model", Model.XQZ, this::lambda$new$3));
        this.self = this.add(new Setting("Self", true, this::lambda$new$4));
        this.noInterp = this.add(new Setting("NoInterp", false, this::lambda$new$5));
        this.sneak = this.add(new Setting("Sneak", false, this::lambda$new$6));
        this.glint = this.add(new Setting("Glint", false, this::lambda$new$7));
        this.lineWidth = this.add(new Setting("LineWidth", 1.0f, 0.1f, 3.0f, this::lambda$new$8));
        this.color = this.add(new Setting("Color", new Color(132, 132, 241, 150), this::lambda$new$9));
        this.lineColor = this.add(new Setting("LineColor", new Color(255, 255, 255), this::lambda$new$10).injectBoolean(false));
        this.modelColor = this.add(new Setting("ModelColor", new Color(125, 125, 213, 150), this::lambda$new$11).injectBoolean(false));
        this.hide = this.add(new Setting("Hide", false, this::lambda$new$12).setParent());
        this.range = this.add(new Setting("Range", 1.5f, 1.0f, 12.0f, this::lambda$new$13));
        Chams.INSTANCE = this;
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
    
    private boolean lambda$new$3(final Model model) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$10(final Color color) {
        return this.page.getValue() == Page.COLORS;
    }
    
    private boolean lambda$new$9(final Color color) {
        return this.page.getValue() == Page.COLORS;
    }
    
    @SubscribeEvent
    public void onRenderPlayerEvent(final RenderPlayerEvent.Pre pre) {
        pre.getEntityPlayer().hurtTime = 0;
    }
    
    private boolean lambda$new$8(final Float n) {
        return this.page.getValue() == Page.COLORS;
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL && this.fill.isOpen();
    }
    
    private boolean lambda$new$7(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$13(final Float n) {
        return this.hide.isOpen() && this.page.getValue() == Page.GLOBAL;
    }
    
    @SubscribeEvent
    public void onRenderLiving(final RenderLivingEvent.Pre pre) {
        if (pre.getEntity() instanceof EntityPlayer && pre.getEntity() != Chams.mc.player && (boolean)this.hide.getValue() && pre.getEntity().getDistance((Entity)Chams.mc.player) < (double)(float)this.range.getValue()) {
            pre.setCanceled(true);
        }
    }
    
    private boolean lambda$new$5(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    private boolean lambda$new$4(final Boolean b) {
        return this.page.getValue() == Page.GLOBAL;
    }
    
    public enum Page
    {
        GLOBAL("GLOBAL", 1), 
        COLORS("COLORS", 0);
        
        private static final Page[] $VALUES;
        
        static {
            $VALUES = new Page[] { Page.COLORS, Page.GLOBAL };
        }
        
        private Page(final String s, final int n) {
        }
    }
    
    public enum Model
    {
        VANILLA("VANILLA", 1), 
        OFF("OFF", 2), 
        XQZ("XQZ", 0);
        
        private static final Model[] $VALUES;
        
        private Model(final String s, final int n) {
        }
        
        static {
            $VALUES = new Model[] { Model.XQZ, Model.VANILLA, Model.OFF };
        }
    }
}
