//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import me.rebirthclient.api.util.math.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AntiAim extends Module
{
    public Setting pitchDelta;
    private float yaw_sinus_step;
    private float pitch_sinus_step;
    private final Setting pitchMode;
    public Setting Speed;
    private float rotationYaw;
    public Setting yawDelta;
    public final Setting allowInteract;
    private final Setting yawMode;
    private float rotationPitch;
    
    public AntiAim() {
        super("AntiAim", "fun", Category.PLAYER);
        this.pitchMode = this.register(new Setting("PitchMode", Mode.None));
        this.yawMode = this.register(new Setting("YawMode", Mode.None));
        this.Speed = this.register(new Setting("Speed", 1, 1, 45));
        this.yawDelta = this.register(new Setting("YawDelta", 60, -360, 360));
        this.pitchDelta = this.register(new Setting("PitchDelta", 10, -90, 90));
        this.allowInteract = this.register(new Setting("AllowInteract", true));
    }
    
    @Override
    public void onUpdate() {
        if (this.pitchMode.getValue() == Mode.RandomAngle && AntiAim.mc.player.ticksExisted % (int)this.Speed.getValue() == 0) {
            this.rotationPitch = MathUtil.random(90.0f, -90.0f);
        }
        if (this.yawMode.getValue() == Mode.RandomAngle && AntiAim.mc.player.ticksExisted % (int)this.Speed.getValue() == 0) {
            this.rotationYaw = MathUtil.random(0.0f, 360.0f);
        }
        if (this.yawMode.getValue() == Mode.Spin && AntiAim.mc.player.ticksExisted % (int)this.Speed.getValue() == 0) {
            this.rotationYaw += (int)this.yawDelta.getValue();
            if (this.rotationYaw > 360.0f) {
                this.rotationYaw = 0.0f;
            }
            if (this.rotationYaw < 0.0f) {
                this.rotationYaw = 360.0f;
            }
        }
        if (this.pitchMode.getValue() == Mode.Spin && AntiAim.mc.player.ticksExisted % (int)this.Speed.getValue() == 0) {
            this.rotationPitch += (int)this.pitchDelta.getValue();
            if (this.rotationPitch > 90.0f) {
                this.rotationPitch = -90.0f;
            }
            if (this.rotationPitch < -90.0f) {
                this.rotationPitch = 90.0f;
            }
        }
        if (this.pitchMode.getValue() == Mode.Sinus) {
            this.pitch_sinus_step += (int)this.Speed.getValue() / 10.0f;
            this.rotationPitch = (float)(AntiAim.mc.player.rotationPitch + (int)this.pitchDelta.getValue() * Math.sin(this.pitch_sinus_step));
            this.rotationPitch = MathUtil.clamp(this.rotationPitch, -90.0f, 90.0f);
        }
        if (this.yawMode.getValue() == Mode.Sinus) {
            this.yaw_sinus_step += (int)this.Speed.getValue() / 10.0f;
            this.rotationYaw = (float)(AntiAim.mc.player.rotationYaw + (int)this.yawDelta.getValue() * Math.sin(this.yaw_sinus_step));
        }
        if (this.pitchMode.getValue() == Mode.Fixed) {
            this.rotationPitch = (float)(int)this.pitchDelta.getValue();
        }
        if (this.yawMode.getValue() == Mode.Fixed) {
            this.rotationYaw = (float)(int)this.yawDelta.getValue();
        }
        if (this.pitchMode.getValue() == Mode.Static) {
            this.rotationPitch = AntiAim.mc.player.rotationPitch + (int)this.pitchDelta.getValue();
            this.rotationPitch = MathUtil.clamp(this.rotationPitch, -90.0f, 90.0f);
        }
        if (this.yawMode.getValue() == Mode.Static) {
            this.rotationYaw = AntiAim.mc.player.rotationYaw % 360.0f + (int)this.yawDelta.getValue();
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onSync(final MotionEvent motionEvent) {
        if ((boolean)this.allowInteract.getValue() && (AntiAim.mc.gameSettings.keyBindAttack.isKeyDown() || AntiAim.mc.gameSettings.keyBindUseItem.isKeyDown())) {
            return;
        }
        if (this.yawMode.getValue() != Mode.None) {
            motionEvent.setYaw(this.rotationYaw);
        }
        if (this.pitchMode.getValue() != Mode.None) {
            motionEvent.setPitch(this.rotationPitch);
        }
    }
    
    public enum Mode
    {
        None("None", 0), 
        Spin("Spin", 2), 
        Sinus("Sinus", 3), 
        Static("Static", 5);
        
        private static final Mode[] $VALUES;
        
        Fixed("Fixed", 4), 
        RandomAngle("RandomAngle", 1);
        
        private Mode(final String s, final int n) {
        }
        
        static {
            $VALUES = new Mode[] { Mode.None, Mode.RandomAngle, Mode.Spin, Mode.Sinus, Mode.Fixed, Mode.Static };
        }
    }
}
