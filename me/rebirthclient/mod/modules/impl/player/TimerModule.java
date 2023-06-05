//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.impl.combat.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.client.*;
import me.rebirthclient.mod.modules.*;

public class TimerModule extends Module
{
    private int rotationMode;
    private int normalPos;
    public final Setting packetControl;
    private float lastPitch;
    private final Setting tickNormal;
    private int normalLookPos;
    public final Setting disableWhenCrystal;
    private float lastYaw;
    public static TimerModule INSTANCE;
    private final Timer packetListReset;
    
    @SubscribeEvent
    public final void RotateEvent(final MotionEvent motionEvent) {
        if ((boolean)this.disableWhenCrystal.getValue() && CatCrystal.lastPos != null) {
            return;
        }
        if (this.packetControl.getValue()) {
            switch (this.rotationMode) {
                case 1: {
                    motionEvent.setRotation(this.lastYaw, this.lastPitch);
                    break;
                }
                case 2: {
                    motionEvent.setRotation(this.lastYaw + nextFloat(1.0f, 3.0f), this.lastPitch + nextFloat(1.0f, 3.0f));
                    break;
                }
            }
        }
    }
    
    @Override
    public void onEnable() {
        TimerModule.mc.timer.tickLength = 50.0f;
        this.lastYaw = TimerModule.mc.player.rotationYaw;
        this.lastPitch = TimerModule.mc.player.rotationPitch;
        this.packetListReset.reset();
    }
    
    @Override
    public void onUpdate() {
        if ((boolean)this.disableWhenCrystal.getValue() && CatCrystal.lastPos != null) {
            TimerModule.mc.timer.tickLength = 50.0f;
            return;
        }
        if (this.packetListReset.passedMs(1000L)) {
            this.normalPos = 0;
            this.normalLookPos = 0;
            this.rotationMode = 1;
            this.lastYaw = TimerModule.mc.player.rotationYaw;
            this.lastPitch = TimerModule.mc.player.rotationPitch;
            this.packetListReset.reset();
        }
        if (this.lastPitch > 85.0f) {
            this.lastPitch = 85.0f;
        }
        if (PacketExp.INSTANCE.isThrow() && (boolean)PacketExp.INSTANCE.down.getValue()) {
            this.lastPitch = 85.0f;
        }
        TimerModule.mc.timer.tickLength = 50.0f / (float)this.tickNormal.getValue();
    }
    
    @Override
    public String getInfo() {
        return String.valueOf(this.tickNormal.getValue());
    }
    
    static {
        TimerModule.INSTANCE = new TimerModule();
    }
    
    public static float nextFloat(final float n, final float n2) {
        return (n == n2 || n2 - n <= 0.0f) ? n : ((float)(n + (n2 - n) * Math.random()));
    }
    
    @Override
    public void onDisable() {
        TimerModule.mc.timer.tickLength = 50.0f;
    }
    
    @SubscribeEvent
    public final void onPacketSend(final PacketEvent.Send send) {
        if (fullNullCheck()) {
            return;
        }
        if (send.getPacket() instanceof CPacketPlayer.Position && this.rotationMode == 1) {
            ++this.normalPos;
            if (this.normalPos > 20) {
                this.rotationMode = 2;
            }
        }
        else if (send.getPacket() instanceof CPacketPlayer.PositionRotation && this.rotationMode == 2) {
            ++this.normalLookPos;
            if (this.normalLookPos > 20) {
                this.rotationMode = 1;
            }
        }
    }
    
    public TimerModule() {
        super("Timer", "Timer", Category.PLAYER);
        this.tickNormal = this.add(new Setting("Speed", 1.2f, 0.1f, 10.0f));
        this.disableWhenCrystal = this.add(new Setting("NoCrystal", true));
        this.packetControl = this.add(new Setting("PacketControl", true));
        this.packetListReset = new Timer();
        TimerModule.INSTANCE = this;
    }
}
