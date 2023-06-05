//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;
import java.util.*;
import me.rebirthclient.mod.modules.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.entity.*;
import org.lwjgl.input.*;
import net.minecraft.client.gui.*;

public class Strafe extends Module
{
    private final Setting multiplier;
    private final Setting Dist;
    private final Setting jumpMotion;
    private final Setting SPEEDH;
    private final Setting plier;
    private final Setting SPEEDY;
    public final Setting mode;
    private double lastDist;
    int stage;
    private final Setting StrafeH;
    private final Setting StrafeY;
    public static Strafe INSTANCE;
    private final Setting jump;
    private double moveSpeed;
    private final Setting multiDist;
    
    public double getBaseMoveSpeed() {
        double n = 0.2873;
        if (Strafe.mc.player.isPotionActive(MobEffects.SPEED)) {
            n *= 1.0 + 0.2 * (Objects.requireNonNull(Strafe.mc.player.getActivePotionEffect(MobEffects.SPEED)).getAmplifier() + 1);
        }
        return n;
    }
    
    static {
        Strafe.INSTANCE = new Strafe();
    }
    
    public Strafe() {
        super("Strafe", "Modifies sprinting", Category.MOVEMENT);
        this.mode = this.add(new Setting("Mode", Mode.Normal));
        this.jump = this.add(new Setting("Jump", false));
        this.jumpMotion = this.add(new Setting("JumpMotion", 0.40123128, 0.1, 1.0));
        this.multiplier = this.add(new Setting("Factor", 1.67f, 0.0f, 3.0f));
        this.plier = this.add(new Setting("Factor+", 2.149f, 0.0f, 3.0f));
        this.Dist = this.add(new Setting("Dist", 0.6896f, 0.1f, 1.0f));
        this.multiDist = this.add(new Setting("Dist+", 0.795f, 0.1f, 1.0f));
        this.SPEEDY = this.add(new Setting("SpeedY", 730.0f, 500.0f, 800.0f));
        this.SPEEDH = this.add(new Setting("SpeedH", 159.0f, 100.0f, 300.0f));
        this.StrafeH = this.add(new Setting("SpeedH", 0.993f, 0.1f, 1.0f));
        this.StrafeY = this.add(new Setting("SpeedY", 0.99f, 0.1f, 1.2f));
        Strafe.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayerEvent(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (fullNullCheck()) {
            return;
        }
        this.lastDist = Math.sqrt((Strafe.mc.player.posX - Strafe.mc.player.prevPosX) * (Strafe.mc.player.posX - Strafe.mc.player.prevPosX) + (Strafe.mc.player.posZ - Strafe.mc.player.prevPosZ) * (Strafe.mc.player.posZ - Strafe.mc.player.prevPosZ));
    }
    
    @SubscribeEvent
    public void onStrafe(final MoveEvent moveEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (HoleSnap.INSTANCE.isOn()) {
            return;
        }
        if (!Strafe.mc.player.isInWater() && !Strafe.mc.player.isInLava()) {
            if (Strafe.mc.player.onGround) {
                this.stage = 2;
            }
            if (this.stage == 0) {
                ++this.stage;
                this.lastDist = 0.0;
            }
            else if (this.stage == 2) {
                double doubleValue = (double)this.jumpMotion.getValue();
                if ((Strafe.mc.player.onGround && (boolean)this.jump.getValue()) || Strafe.mc.gameSettings.keyBindJump.isKeyDown()) {
                    if (Strafe.mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
                        doubleValue += (Objects.requireNonNull(Strafe.mc.player.getActivePotionEffect(MobEffects.JUMP_BOOST)).getAmplifier() + 1) * 0.1f;
                    }
                    moveEvent.setY(Strafe.mc.player.motionY = doubleValue);
                    this.moveSpeed *= (float)((this.mode.getValue() == Mode.Normal) ? this.multiplier.getValue() : ((Float)this.plier.getValue()));
                }
            }
            else if (this.stage == 3) {
                this.moveSpeed = this.lastDist - (float)((this.mode.getValue() == Mode.Normal) ? this.Dist.getValue() : ((Float)this.multiDist.getValue())) * (this.lastDist - this.getBaseMoveSpeed());
            }
            else {
                if ((Strafe.mc.world.getCollisionBoxes((Entity)Strafe.mc.player, Strafe.mc.player.getEntityBoundingBox().offset(0.0, Strafe.mc.player.motionY, 0.0)).size() > 0 || Strafe.mc.player.collidedVertically) && this.stage > 0) {
                    this.stage = ((Strafe.mc.player.moveForward != 0.0f || Strafe.mc.player.moveStrafing != 0.0f) ? 1 : 0);
                }
                this.moveSpeed = this.lastDist - this.lastDist / (float)((this.mode.getValue() == Mode.Normal) ? this.SPEEDY.getValue() : ((Float)this.SPEEDH.getValue()));
            }
            this.moveSpeed = ((!Strafe.mc.gameSettings.keyBindJump.isKeyDown() && (!InventoryMove.INSTANCE.isOn() || !Keyboard.isKeyDown(Strafe.mc.gameSettings.keyBindJump.getKeyCode()) || Strafe.mc.currentScreen instanceof GuiChat) && Strafe.mc.player.onGround) ? this.getBaseMoveSpeed() : Math.max(this.moveSpeed, this.getBaseMoveSpeed()));
            double n = Strafe.mc.player.movementInput.moveForward;
            double n2 = Strafe.mc.player.movementInput.moveStrafe;
            final double n3 = Strafe.mc.player.rotationYaw;
            if (n == 0.0 && n2 == 0.0) {
                moveEvent.setX(0.0);
                moveEvent.setZ(0.0);
            }
            else if (n != 0.0 && n2 != 0.0) {
                n *= Math.sin(0.7853981633974483);
                n2 *= Math.cos(0.7853981633974483);
            }
            final double n4 = (this.mode.getValue() == Mode.Normal) ? ((double)(float)this.StrafeH.getValue()) : ((double)(float)this.StrafeY.getValue());
            moveEvent.setX((n * this.moveSpeed * -Math.sin(Math.toRadians(n3)) + n2 * this.moveSpeed * Math.cos(Math.toRadians(n3))) * n4);
            moveEvent.setZ((n * this.moveSpeed * Math.cos(Math.toRadians(n3)) - n2 * this.moveSpeed * -Math.sin(Math.toRadians(n3))) * n4);
            ++this.stage;
            moveEvent.setCanceled(false);
        }
    }
    
    @Override
    public String getInfo() {
        return ((Mode)this.mode.getValue()).name();
    }
    
    public enum Mode
    {
        Normal("Normal", 0), 
        Strict("Strict", 1);
        
        private static final Mode[] $VALUES;
        
        static {
            $VALUES = new Mode[] { Mode.Normal, Mode.Strict };
        }
        
        private Mode(final String s, final int n) {
        }
    }
}
