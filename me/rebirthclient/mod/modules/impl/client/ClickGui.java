//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.client;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.gui.screen.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.api.managers.*;
import com.mojang.realmsclient.gui.*;
import me.rebirthclient.mod.commands.*;
import java.awt.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;

public class ClickGui extends Module
{
    public final Setting rollingLine;
    public final Setting rainbowSaturation;
    public final Setting style;
    public final Setting snow;
    public final Setting color;
    public final Setting colorParticles;
    public final Setting height;
    public final Setting rainbow;
    public final Setting icon;
    public final Setting rainbowMode;
    public static ClickGui INSTANCE;
    public final Setting secondColor;
    public final Setting blur;
    public final Setting gear;
    public final Setting rainbowDelay;
    public final Setting colorRect;
    public final Setting disableSave;
    public final Setting cleanGui;
    public final Setting rainbowSpeed;
    public final Setting background;
    public final Setting rect;
    public final Setting prefix;
    public final Setting line;
    private final Setting animationTime;
    public final Setting hudRainbow;
    public final Setting particles;
    public static FadeUtils animation;
    
    private boolean lambda$new$0(final Boolean b) {
        return this.line.isOpen();
    }
    
    private boolean lambda$new$1(final Boolean b) {
        return this.rect.isOpen();
    }
    
    private boolean lambda$new$7(final Integer n) {
        return this.rainbow.isOpen();
    }
    
    private boolean lambda$new$4(final Float n) {
        return this.rainbow.isOpen() && this.rainbowMode.getValue() == Rainbow.NORMAL;
    }
    
    @Override
    public void onTick() {
        if (!(ClickGui.mc.currentScreen instanceof Gui) && !(ClickGui.mc.currentScreen instanceof GuiMainMenu)) {
            this.disable();
        }
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent clientEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (clientEvent.getStage() == 2 && clientEvent.getSetting().getMod().equals(this)) {
            if (clientEvent.getSetting().equals(this.prefix)) {
                Managers.COMMANDS.setPrefix((String)this.prefix.getPlannedValue());
                Command.sendMessage("Prefix set to " + ChatFormatting.DARK_GRAY + Managers.COMMANDS.getCommandPrefix());
            }
            Managers.COLORS.setCurrent((Color)this.color.getValue());
        }
    }
    
    private boolean lambda$new$5(final Color color) {
        return this.rainbow.isOpen() && this.rainbowMode.getValue() == Rainbow.DOUBLE;
    }
    
    private boolean lambda$new$2(final Boolean b) {
        return this.particles.isOpen();
    }
    
    private boolean lambda$new$6(final HudRainbow hudRainbow) {
        return this.rainbow.isOpen();
    }
    
    private boolean lambda$new$3(final Rainbow rainbow) {
        return this.rainbow.isOpen();
    }
    
    static {
        ClickGui.animation = new FadeUtils(500L);
    }
    
    @Override
    public void onLoad() {
        Managers.COLORS.setCurrent((Color)this.color.getValue());
        Managers.COMMANDS.setPrefix((String)this.prefix.getValue());
    }
    
    private boolean lambda$new$8(final Integer n) {
        return this.rainbow.isOpen();
    }
    
    @Override
    public void onDisable() {
        if (this.disableSave.getValue()) {
            Managers.CONFIGS.saveConfig(Managers.CONFIGS.config.replaceFirst("Rebirth/", ""));
        }
    }
    
    @Override
    public void onEnable() {
        ClickGui.animation.setLength((long)(int)this.animationTime.getValue());
        ClickGui.animation.reset();
        if (ClickGui.mc.world != null) {
            ClickGui.mc.displayGuiScreen((GuiScreen)Gui.INSTANCE);
        }
        if (this.blur.getValue()) {
            ClickGui.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
        }
    }
    
    public ClickGui() {
        super("ClickGui", "Opens the ClickGui", Category.CLIENT);
        this.prefix = this.add(new Setting("Prefix", ";"));
        this.disableSave = this.add(new Setting("DisableSave", true));
        this.style = this.add(new Setting("Style", Style.NEW));
        this.height = this.add(new Setting("ButtonHeight", 4, 1, 5));
        this.blur = this.add(new Setting("Blur", false));
        this.line = this.add(new Setting("Line", true).setParent());
        this.rollingLine = this.add(new Setting("RollingLine", true, this::lambda$new$0));
        this.rect = this.add(new Setting("Rect", true).setParent());
        this.colorRect = this.add(new Setting("ColorRect", false, this::lambda$new$1));
        this.gear = this.add(new Setting("Gear", true));
        this.icon = this.add(new Setting("Icon", true));
        this.animationTime = this.add(new Setting("AnimationTime", 500, 0, 2000));
        this.snow = this.add(new Setting("Snow", true));
        this.particles = this.add(new Setting("Particles", false).setParent());
        this.colorParticles = this.add(new Setting("ColorParticles", true, this::lambda$new$2));
        this.background = this.add(new Setting("Background", true));
        this.cleanGui = this.add(new Setting("CleanGui", false));
        this.color = this.add(new Setting("Color", new Color(125, 125, 213)).hideAlpha().noRainbow());
        this.rainbow = this.add(new Setting("Rainbow", false).setParent());
        this.rainbowMode = this.add(new Setting("Mode", Rainbow.NORMAL, this::lambda$new$3));
        this.rainbowSaturation = this.add(new Setting("Saturation", 130.0f, 1.0f, 255.0f, this::lambda$new$4));
        this.secondColor = this.add(new Setting("SecondColor", new Color(255, 255, 255), this::lambda$new$5).hideAlpha());
        this.hudRainbow = this.add(new Setting("HUD", HudRainbow.STATIC, this::lambda$new$6));
        this.rainbowDelay = this.add(new Setting("Delay", 350, 0, 600, this::lambda$new$7));
        this.rainbowSpeed = this.add(new Setting("Speed", 350, 0, 600, this::lambda$new$8));
        ClickGui.INSTANCE = this;
    }
    
    public int getButtonHeight() {
        return 11 + (int)this.height.getValue();
    }
    
    public enum Style
    {
        private static final Style[] $VALUES;
        
        DOTGOD("DOTGOD", 3), 
        NEW("NEW", 1), 
        FUTURE("FUTURE", 2), 
        OLD("OLD", 0);
        
        static {
            $VALUES = new Style[] { Style.OLD, Style.NEW, Style.FUTURE, Style.DOTGOD };
        }
        
        private Style(final String s, final int n) {
        }
    }
    
    public enum HudRainbow
    {
        STATIC("STATIC", 0), 
        ROLLING("ROLLING", 1);
        
        private static final HudRainbow[] $VALUES;
        
        private HudRainbow(final String s, final int n) {
        }
        
        static {
            $VALUES = new HudRainbow[] { HudRainbow.STATIC, HudRainbow.ROLLING };
        }
    }
    
    public enum Rainbow
    {
        private static final Rainbow[] $VALUES;
        
        NORMAL("NORMAL", 0), 
        DOUBLE("DOUBLE", 2), 
        PLAIN("PLAIN", 1);
        
        static {
            $VALUES = new Rainbow[] { Rainbow.NORMAL, Rainbow.PLAIN, Rainbow.DOUBLE };
        }
        
        private Rainbow(final String s, final int n) {
        }
    }
}
