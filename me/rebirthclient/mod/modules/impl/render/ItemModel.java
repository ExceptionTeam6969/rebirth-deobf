//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.render;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.util.*;
import net.minecraft.util.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.api.events.impl.*;

public class ItemModel extends Module
{
    public final Setting noSway;
    public final Setting mainY;
    public float angle;
    public final Setting noEatAnimation;
    public final Setting mainZ;
    public final Setting offY;
    public final Setting doBob;
    public final Setting offZ;
    public final Setting eatY;
    public final Setting slowSwing;
    public final Setting spinY;
    public final Setting mainX;
    public final Setting delay;
    public final Setting offX;
    final Timer timer;
    public final Setting angleSpeed;
    public final Setting swing;
    public final Setting customSwing;
    public final Setting spinX;
    public final Setting settings;
    public final Setting eatX;
    public static ItemModel INSTANCE;
    public final Setting swingSpeed;
    
    @Override
    public void onUpdate() {
        if (this.swing.getValue() == Swing.OFFHAND && (boolean)this.customSwing.getValue()) {
            ItemModel.mc.player.swingingHand = EnumHand.OFF_HAND;
        }
        else if (this.swing.getValue() == Swing.MAINHAND && (boolean)this.customSwing.getValue()) {
            ItemModel.mc.player.swingingHand = EnumHand.MAIN_HAND;
        }
        this.doAngle();
    }
    
    private boolean lambda$new$4(final Double n) {
        return this.settings.getValue() == Settings.TRANSLATE;
    }
    
    static {
        ItemModel.INSTANCE = new ItemModel();
    }
    
    private boolean lambda$new$11(final Boolean b) {
        return this.settings.getValue() == Settings.TRANSLATE;
    }
    
    private boolean lambda$new$2(final Double n) {
        return this.settings.getValue() == Settings.TWEAKS && !(boolean)this.noEatAnimation.getValue();
    }
    
    private boolean lambda$new$14(final Boolean b) {
        return this.settings.getValue() == Settings.OTHERS;
    }
    
    private boolean lambda$new$15(final Swing swing) {
        return this.settings.getValue() == Settings.OTHERS && this.customSwing.isOpen();
    }
    
    private boolean lambda$new$6(final Double n) {
        return this.settings.getValue() == Settings.TRANSLATE;
    }
    
    private boolean lambda$new$16(final Boolean b) {
        return this.settings.getValue() == Settings.OTHERS;
    }
    
    private boolean lambda$new$17(final Integer n) {
        return this.settings.getValue() == Settings.OTHERS && this.slowSwing.isOpen();
    }
    
    private void doAngle() {
        if (this.timer.passedMs((long)(int)this.delay.getValue())) {
            this.angle += (int)this.angleSpeed.getValue();
            this.timer.reset();
        }
    }
    
    private boolean lambda$new$18(final Boolean b) {
        return this.settings.getValue() == Settings.OTHERS;
    }
    
    private boolean lambda$new$8(final Double n) {
        return this.settings.getValue() == Settings.TRANSLATE;
    }
    
    private boolean lambda$new$7(final Double n) {
        return this.settings.getValue() == Settings.TRANSLATE;
    }
    
    private boolean lambda$new$9(final Double n) {
        return this.settings.getValue() == Settings.TRANSLATE;
    }
    
    private boolean lambda$new$10(final Boolean b) {
        return this.settings.getValue() == Settings.TRANSLATE;
    }
    
    private boolean lambda$new$3(final Boolean b) {
        return this.settings.getValue() == Settings.TWEAKS;
    }
    
    public ItemModel() {
        super("ItemModel", "Change the position of the arm", Category.RENDER);
        this.settings = this.add(new Setting("Settings", Settings.TRANSLATE));
        this.noEatAnimation = this.add(new Setting("NoEatAnimation", false, this::lambda$new$0));
        this.eatX = this.add(new Setting("EatX", 1.4, -5.0, 15.0, this::lambda$new$1));
        this.eatY = this.add(new Setting("EatY", 1.3, -5.0, 15.0, this::lambda$new$2));
        this.doBob = this.add(new Setting("ItemBob", true, this::lambda$new$3));
        this.mainX = this.add(new Setting("MainX", 0.2, -4.0, 4.0, this::lambda$new$4));
        this.mainY = this.add(new Setting("MainY", -0.2, -3.0, 3.0, this::lambda$new$5));
        this.mainZ = this.add(new Setting("MainZ", -0.3, -5.0, 5.0, this::lambda$new$6));
        this.offX = this.add(new Setting("OffX", -0.2, -4.0, 4.0, this::lambda$new$7));
        this.offY = this.add(new Setting("OffY", -0.2, -3.0, 3.0, this::lambda$new$8));
        this.offZ = this.add(new Setting("OffZ", -0.3, -5.0, 5.0, this::lambda$new$9));
        this.spinY = this.add(new Setting("SpinX", false, this::lambda$new$10));
        this.spinX = this.add(new Setting("SpinY", false, this::lambda$new$11));
        this.delay = this.add(new Setting("Delay", 50, 0, 500, this::lambda$new$12));
        this.angleSpeed = this.add(new Setting("AngleSpeed", 5, 0, 10, this::lambda$new$13));
        this.customSwing = this.add(new Setting("CustomSwing", false, this::lambda$new$14).setParent());
        this.swing = this.add(new Setting("Swing", Swing.MAINHAND, this::lambda$new$15));
        this.slowSwing = this.add(new Setting("SwingSpeed", false, this::lambda$new$16).setParent());
        this.swingSpeed = this.add(new Setting("swingSpeed", 15, 0, 30, this::lambda$new$17));
        this.noSway = this.add(new Setting("NoSway", false, this::lambda$new$18));
        this.timer = new Timer();
        this.angle = 0.0f;
        ItemModel.INSTANCE = this;
    }
    
    private boolean lambda$new$1(final Double n) {
        return this.settings.getValue() == Settings.TWEAKS && !(boolean)this.noEatAnimation.getValue();
    }
    
    private boolean lambda$new$12(final Integer n) {
        return this.settings.getValue() == Settings.TRANSLATE && ((boolean)this.spinX.getValue() || (boolean)this.spinY.getValue());
    }
    
    @SubscribeEvent
    public void Update(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        this.doAngle();
    }
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        this.doAngle();
    }
    
    private boolean lambda$new$5(final Double n) {
        return this.settings.getValue() == Settings.TRANSLATE;
    }
    
    @Override
    public void onTick() {
        this.doAngle();
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        this.doAngle();
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.settings.getValue() == Settings.TWEAKS;
    }
    
    private boolean lambda$new$13(final Integer n) {
        return this.settings.getValue() == Settings.TRANSLATE && ((boolean)this.spinX.getValue() || (boolean)this.spinY.getValue());
    }
    
    public enum Swing
    {
        SERVER("SERVER", 2), 
        OFFHAND("OFFHAND", 1), 
        MAINHAND("MAINHAND", 0);
        
        private static final Swing[] $VALUES;
        
        private Swing(final String s, final int n) {
        }
        
        static {
            $VALUES = new Swing[] { Swing.MAINHAND, Swing.OFFHAND, Swing.SERVER };
        }
    }
    
    private enum Settings
    {
        TRANSLATE("TRANSLATE", 0), 
        TWEAKS("TWEAKS", 1), 
        OTHERS("OTHERS", 2);
        
        private static final Settings[] $VALUES;
        
        private Settings(final String s, final int n) {
        }
        
        static {
            $VALUES = new Settings[] { Settings.TRANSLATE, Settings.TWEAKS, Settings.OTHERS };
        }
    }
}
