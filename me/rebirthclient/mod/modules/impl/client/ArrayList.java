//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.client;

import me.rebirthclient.mod.modules.settings.*;
import java.awt.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.stream.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.api.util.render.*;
import java.util.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraftforge.fml.common.gameevent.*;
import me.rebirthclient.api.util.*;

public class ArrayList extends Module
{
    private List Map;
    private final Setting animationY;
    private final Setting animationTime;
    private final Setting onlyDrawn;
    private final Setting listY;
    private boolean needUpdate;
    public final Setting rainbowDelay;
    private final Setting saturation;
    private final Setting bgSync;
    private final Setting listX;
    private final Setting reverse;
    private final Setting forgeHax;
    int pulseProgress;
    int progress;
    private final Setting rect;
    private final Timer updateTimer;
    private final Setting fps;
    private final Setting pulseSpeed;
    private final Setting color;
    private final Setting onlyBind;
    public final Setting colorMode;
    private final Setting bgColor;
    private final Setting rainbowSpeed;
    private final Setting backGround;
    
    private boolean lambda$new$2(final Integer n) {
        return this.colorMode.getValue() == ColorMode.Pulse || this.colorMode.getValue() == ColorMode.PulseRainbow;
    }
    
    private boolean lambda$new$1(final Float n) {
        return this.colorMode.getValue() == ColorMode.Rainbow || this.colorMode.getValue() == ColorMode.PulseRainbow;
    }
    
    private boolean lambda$new$6(final Color color) {
        return this.backGround.isOpen();
    }
    
    @SubscribeEvent
    public void clientEvent(final ClientEvent clientEvent) {
        if (!this.updateTimer.passedMs(5000L)) {
            return;
        }
        this.updateTimer.reset();
        this.needUpdate = true;
    }
    
    private Color rainbow(final int n) {
        final double ceil = Math.ceil((this.progress + n * (int)this.rainbowDelay.getValue()) / 20.0);
        if (this.colorMode.getValue() == ColorMode.Pulse) {
            return this.pulseColor((Color)this.color.getValue(), n);
        }
        if (this.colorMode.getValue() == ColorMode.Rainbow) {
            return Color.getHSBColor((float)(ceil % 360.0 / 360.0), (float)this.saturation.getValue() / 255.0f, 1.0f);
        }
        return this.pulseColor(Color.getHSBColor((float)(ceil % 360.0 / 360.0), (float)this.saturation.getValue() / 255.0f, 1.0f), n);
    }
    
    private boolean lambda$new$5(final Boolean b) {
        return this.backGround.isOpen();
    }
    
    private void doRender() {
        if (fullNullCheck()) {
            return;
        }
        if (this.needUpdate) {
            this.Map = (List)this.Map.stream().sorted(Comparator.comparing((Function<? super T, ? extends Comparable>)ArrayList::lambda$doRender$7)).collect(Collectors.toList());
        }
        final int intValue = (int)this.listY.getValue();
        final int n = 20;
        final Iterator<Modules> iterator = (Iterator<Modules>)this.Map.iterator();
        if (!iterator.hasNext()) {
            return;
        }
        final Modules modules = iterator.next();
        if (!modules.module.isDrawn() && (boolean)this.onlyDrawn.getValue()) {
            return;
        }
        if ((boolean)this.onlyBind.getValue() && modules.module.getBind().getKey() == -1) {
            return;
        }
        modules.fade.setLength((long)(int)this.animationTime.getValue());
        if (modules.module.isOn()) {
            modules.enable();
        }
        else {
            modules.disable();
        }
        modules.updateName();
        int n2;
        int n3;
        if (!(boolean)this.reverse.getValue()) {
            if (modules.isEnabled) {
                final double easeOutQuad = modules.fade.easeOutQuad();
                n2 = (int)(Managers.TEXT.getStringWidth(modules.module.getArrayListInfo() + this.getSuffix()) * easeOutQuad);
                n3 = (int)(Managers.TEXT.getFontHeight2() * easeOutQuad);
                modules.lastY = n3;
                modules.lastX = n2;
            }
            else {
                final double abs = Math.abs(modules.fade.easeOutQuad() - 1.0);
                n2 = (int)(modules.lastX * abs);
                n3 = (int)(modules.lastY * abs);
                if (abs <= 0.0) {
                    return;
                }
            }
        }
        else if (modules.isEnabled) {
            n2 = (int)(Managers.TEXT.getStringWidth(modules.module.getArrayListInfo() + this.getSuffix()) * Math.abs(modules.fade.easeOutQuad() - 1.0));
            n3 = (int)(Managers.TEXT.getFontHeight2() * modules.fade.easeOutQuad());
            modules.lastY = n3;
            modules.lastX = n2;
        }
        else {
            n2 = (int)(Managers.TEXT.getStringWidth(modules.module.getArrayListInfo() + this.getSuffix()) * modules.fade.easeOutQuad()) + modules.lastX;
            final double abs2 = Math.abs(modules.fade.easeOutQuad() - 1.0);
            n3 = (int)(modules.lastY * abs2);
            if (abs2 <= 0.0) {
                return;
            }
            if (n2 >= Managers.TEXT.getStringWidth(modules.module.getArrayListInfo() + this.getSuffix())) {
                return;
            }
        }
        final int n4 = (int)(n2 + 20.0 * Math.abs(modules.change.easeOutQuad() - 1.0));
        final int n5 = n + 1;
        int n6;
        if (!(boolean)this.reverse.getValue()) {
            n6 = Managers.TEXT.scaledWidth - n4 - (int)this.listX.getValue() - (this.rect.getValue() ? 2 : 0);
            if (this.backGround.getValue()) {
                RenderUtil.drawRect((float)n6, (float)(intValue - (this.animationY.getValue() ? Math.abs(n3 - Managers.TEXT.getFontHeight2()) : 0) - 1), (float)(Managers.TEXT.scaledWidth - (int)this.listX.getValue()), (float)(intValue - (this.animationY.getValue() ? Math.abs(n3 - Managers.TEXT.getFontHeight2()) : 0) + Managers.TEXT.getFontHeight2() - 1), ((boolean)this.bgSync.getValue()) ? ColorUtil.injectAlpha(this.getColor(n5), ((Color)this.bgColor.getValue()).getAlpha()) : ((Color)this.bgColor.getValue()).getRGB());
            }
            if (this.rect.getValue()) {
                RenderUtil.drawRect((float)(Managers.TEXT.scaledWidth - (int)this.listX.getValue() - 1), (float)(intValue - (this.animationY.getValue() ? Math.abs(n3 - Managers.TEXT.getFontHeight2()) : 0) - 1), (float)(Managers.TEXT.scaledWidth - (int)this.listX.getValue()), (float)(intValue - (this.animationY.getValue() ? Math.abs(n3 - Managers.TEXT.getFontHeight2()) : 0) + Managers.TEXT.getFontHeight2()), this.getColor(n5));
            }
        }
        else {
            n6 = -n4 + (int)this.listX.getValue() + (this.rect.getValue() ? 2 : 0);
            if (this.rect.getValue()) {
                RenderUtil.drawRect((float)(int)this.listX.getValue(), (float)(intValue - (this.animationY.getValue() ? Math.abs(n3 - Managers.TEXT.getFontHeight2()) : 0) - 1), (float)((int)this.listX.getValue() + 1), (float)(intValue - (this.animationY.getValue() ? Math.abs(n3 - Managers.TEXT.getFontHeight2()) : 0) + Managers.TEXT.getFontHeight2() - 1), this.getColor(n5));
            }
            if (this.backGround.getValue()) {
                RenderUtil.drawRect((float)(int)this.listX.getValue(), (float)(intValue - (this.animationY.getValue() ? Math.abs(n3 - Managers.TEXT.getFontHeight2()) : 0) - 1), (float)(Math.abs(n4 - Managers.TEXT.getStringWidth(modules.module.getArrayListInfo() + this.getSuffix())) + (this.rect.getValue() ? 2 : 0)), (float)(intValue - (this.animationY.getValue() ? Math.abs(n3 - Managers.TEXT.getFontHeight2()) : 0) + Managers.TEXT.getFontHeight2() - 1), ((boolean)this.bgSync.getValue()) ? ColorUtil.injectAlpha(this.getColor(n5), ((Color)this.bgColor.getValue()).getAlpha()) : ((Color)this.bgColor.getValue()).getRGB());
            }
        }
        Managers.TEXT.drawString(modules.module.getArrayListInfo() + this.getSuffix(), (float)n6, (float)(intValue - (this.animationY.getValue() ? Math.abs(n3 - Managers.TEXT.getFontHeight2()) : 0)), this.getColor(n5), true);
    }
    
    private Color pulseColor(final Color color, final int n) {
        final float[] array = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), array);
        array[2] = (Float.intBitsToFloat(Float.floatToIntBits(18.996923f) ^ 0x7E97F9B3) + Float.intBitsToFloat(Float.floatToIntBits(2.7958195f) ^ 0x7F32EEB5) * Math.abs((this.pulseProgress % 2000L / Float.intBitsToFloat(Float.floatToIntBits(0.0013786979f) ^ 0x7ECEB56D) + n / 14.0f * Float.intBitsToFloat(Float.floatToIntBits(0.09192204f) ^ 0x7DBC419F)) % Float.intBitsToFloat(Float.floatToIntBits(0.7858098f) ^ 0x7F492AD5) - Float.intBitsToFloat(Float.floatToIntBits(6.46708f) ^ 0x7F4EF252))) % Float.intBitsToFloat(Float.floatToIntBits(0.8992331f) ^ 0x7F663424);
        return new Color(Color.HSBtoRGB(array[0], array[1], array[2]));
    }
    
    @Override
    public void onTick() {
        this.progress += (int)this.rainbowSpeed.getValue();
        this.pulseProgress += (int)this.pulseSpeed.getValue();
    }
    
    private String getSuffix() {
        if (this.forgeHax.getValue()) {
            return "¡ìr<";
        }
        return "";
    }
    
    @Override
    public void onLoad() {
        final Iterator<Module> iterator = Managers.MODULES.getModules().iterator();
        if (iterator.hasNext()) {
            this.Map.add(new Modules(iterator.next()));
        }
    }
    
    private int getColor(final int n) {
        if (this.colorMode.getValue() != ColorMode.Custom) {
            return this.rainbow(n).getRGB();
        }
        return ((Color)this.color.getValue()).getRGB();
    }
    
    @Override
    public void onLogin() {
        this.needUpdate = true;
    }
    
    public ArrayList() {
        super("ArrayList", "", Category.CLIENT);
        this.listX = this.add(new Setting("X", 0, 0, 2000));
        this.listY = this.add(new Setting("Y", 10, 1, 2000));
        this.animationTime = this.add(new Setting("AnimationTime", 300, 0, 1000));
        this.forgeHax = this.add(new Setting("ForgeHax", true));
        this.reverse = this.add(new Setting("Reverse", false));
        this.fps = this.add(new Setting("Fps", true));
        this.onlyDrawn = this.add(new Setting("OnlyDrawn", true));
        this.onlyBind = this.add(new Setting("OnlyBind", false));
        this.animationY = this.add(new Setting("AnimationY", true));
        this.colorMode = this.add(new Setting("ColorMode", ColorMode.Pulse));
        this.rainbowSpeed = this.register(new Setting("RainbowSpeed", 200, 1, 400, this::lambda$new$0));
        this.saturation = this.register(new Setting("Saturation", 130.0f, 1.0f, 255.0f, this::lambda$new$1));
        this.pulseSpeed = this.register(new Setting("PulseSpeed", 100, 1, 400, this::lambda$new$2));
        this.rainbowDelay = this.add(new Setting("Delay", 350, 0, 600, this::lambda$new$3));
        this.color = this.add(new Setting("Color", new Color(255, 255, 255, 255), this::lambda$new$4).hideAlpha());
        this.rect = this.add(new Setting("Rect", true));
        this.backGround = this.add(new Setting("BackGround", true).setParent());
        this.bgSync = this.add(new Setting("Sync", false, this::lambda$new$5));
        this.bgColor = this.add(new Setting("BGColor", new Color(0, 0, 0, 100), this::lambda$new$6));
        this.Map = new java.util.ArrayList();
        this.updateTimer = new Timer();
        this.needUpdate = false;
        this.progress = 0;
        this.pulseProgress = 0;
    }
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        if (!(boolean)this.fps.getValue()) {
            return;
        }
        this.doRender();
    }
    
    private static Integer lambda$doRender$7(final Modules modules) {
        return Managers.TEXT.getStringWidth(modules.module.getArrayListInfo()) * -1;
    }
    
    private boolean lambda$new$4(final Color color) {
        return this.colorMode.getValue() != ColorMode.Rainbow;
    }
    
    private boolean lambda$new$0(final Integer n) {
        return this.colorMode.getValue() == ColorMode.Rainbow || this.colorMode.getValue() == ColorMode.PulseRainbow;
    }
    
    @SubscribeEvent
    public void onRender(final TickEvent.RenderTickEvent renderTickEvent) {
        if (this.fps.getValue()) {
            return;
        }
        this.doRender();
    }
    
    private boolean lambda$new$3(final Integer n) {
        return this.colorMode.getValue() == ColorMode.Rainbow;
    }
    
    public static class Modules
    {
        public String lastName;
        public final FadeUtils fade;
        public int lastX;
        public boolean isEnabled;
        public final FadeUtils change;
        public int lastY;
        public Module module;
        
        public void disable() {
            if (!this.isEnabled) {
                return;
            }
            this.isEnabled = false;
            this.fade.reset();
        }
        
        public void updateName() {
            if (!Integer.valueOf(this.module.getArrayListInfo().hashCode()).equals(this.lastName.hashCode())) {
                this.lastName = this.module.getArrayListInfo();
                this.change.reset();
            }
        }
        
        public void enable() {
            if (this.isEnabled) {
                return;
            }
            this.isEnabled = true;
            this.fade.reset();
        }
        
        public Modules(final Module module) {
            this.isEnabled = false;
            this.lastX = 0;
            this.lastY = 0;
            this.module = module;
            this.fade = new FadeUtils(500L);
            this.change = new FadeUtils(200L);
            this.lastName = module.getArrayListInfo();
        }
    }
    
    private enum ColorMode
    {
        Custom("Custom", 0), 
        PulseRainbow("PulseRainbow", 3), 
        Pulse("Pulse", 1);
        
        private static final ColorMode[] $VALUES;
        
        Rainbow("Rainbow", 2);
        
        static {
            $VALUES = new ColorMode[] { ColorMode.Custom, ColorMode.Pulse, ColorMode.Rainbow, ColorMode.PulseRainbow };
        }
        
        private ColorMode(final String s, final int n) {
        }
    }
}
