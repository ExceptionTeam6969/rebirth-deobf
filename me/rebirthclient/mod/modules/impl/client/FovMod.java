//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.client;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.settings.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraftforge.client.event.*;
import net.minecraft.init.*;

public class FovMod extends Module
{
    private final Setting aspectFactor;
    private final Setting sprint;
    private final Setting defaults;
    private final Setting aspectRatio;
    private final Setting customFov;
    private final Setting fov;
    private final Setting speed;
    private final Setting page;
    public static FovMod INSTANCE;
    
    @SubscribeEvent
    public void onPerspectiveUpdate(final PerspectiveEvent perspectiveEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (this.aspectRatio.getValue()) {
            perspectiveEvent.setAngle((float)this.aspectFactor.getValue());
        }
    }
    
    private boolean lambda$new$3(final Float n) {
        return this.page.getValue() == Page.FOV && this.aspectRatio.isOpen();
    }
    
    private boolean lambda$new$4(final Boolean b) {
        return this.page.getValue() == Page.ADVANCED;
    }
    
    private boolean lambda$new$1(final Float n) {
        return this.page.getValue() == Page.FOV && this.customFov.isOpen();
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.page.getValue() == Page.FOV;
    }
    
    private boolean lambda$new$5(final Float n) {
        return this.page.getValue() == Page.ADVANCED;
    }
    
    @Override
    public void onUpdate() {
        if (this.customFov.getValue()) {
            FovMod.mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, (float)this.fov.getValue());
        }
        if (this.defaults.getValue()) {
            this.sprint.setValue(1.15f);
            this.speed.setValue(1.15f);
            this.defaults.setValue(false);
        }
    }
    
    public FovMod() {
        super("FovMod", "FOV modifier", Category.CLIENT);
        this.page = this.add(new Setting("Settings", Page.FOV));
        this.customFov = this.add(new Setting("CustomFov", false, this::lambda$new$0).setParent());
        this.fov = this.add(new Setting("FOV", 120.0f, 10.0f, 180.0f, this::lambda$new$1));
        this.aspectRatio = this.add(new Setting("AspectRatio", false, this::lambda$new$2).setParent());
        this.aspectFactor = this.add(new Setting("AspectFactor", 1.8f, 0.1f, 3.0f, this::lambda$new$3));
        this.defaults = this.add(new Setting("Defaults", false, this::lambda$new$4));
        this.sprint = this.add(new Setting("SprintAdd", 1.15f, 1.0f, 2.0f, this::lambda$new$5));
        this.speed = this.add(new Setting("SwiftnessAdd", 1.15f, 1.0f, 2.0f, this::lambda$new$6));
        FovMod.INSTANCE = this;
    }
    
    static {
        FovMod.INSTANCE = new FovMod();
    }
    
    @SubscribeEvent
    public void onFOVUpdate(final FOVUpdateEvent fovUpdateEvent) {
        if (fullNullCheck()) {
            return;
        }
        float newfov = 1.0f;
        if (fovUpdateEvent.getEntity().isSprinting()) {
            newfov = (float)this.sprint.getValue();
            if (fovUpdateEvent.getEntity().isPotionActive(MobEffects.SPEED)) {
                newfov = (float)this.speed.getValue();
            }
        }
        fovUpdateEvent.setNewfov(newfov);
    }
    
    private boolean lambda$new$6(final Float n) {
        return this.page.getValue() == Page.ADVANCED;
    }
    
    private boolean lambda$new$2(final Boolean b) {
        return this.page.getValue() == Page.FOV;
    }
    
    public enum Page
    {
        ADVANCED("ADVANCED", 1);
        
        private static final Page[] $VALUES;
        
        FOV("FOV", 0);
        
        private Page(final String s, final int n) {
        }
        
        static {
            $VALUES = new Page[] { Page.FOV, Page.ADVANCED };
        }
    }
}
