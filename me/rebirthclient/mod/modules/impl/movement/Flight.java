//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.mod.modules.*;
import me.rebirthclient.api.util.*;
import org.lwjgl.input.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public final class Flight extends Module
{
    private final Setting verticalSpeed;
    public static Flight INSTANCE;
    private final Setting speed;
    private final Setting glide;
    
    static {
        Flight.INSTANCE = new Flight();
    }
    
    private void setMoveSpeed(final MoveEvent moveEvent, final double n) {
        double n2 = Flight.mc.player.movementInput.moveForward;
        double n3 = Flight.mc.player.movementInput.moveStrafe;
        float rotationYaw = Flight.mc.player.rotationYaw;
        if (n2 == 0.0 && n3 == 0.0) {
            moveEvent.setX(0.0);
            moveEvent.setZ(0.0);
            Flight.mc.player.motionX = 0.0;
            Flight.mc.player.motionZ = 0.0;
        }
        else {
            if (n2 != 0.0) {
                if (n3 > 0.0) {
                    rotationYaw += ((n2 > 0.0) ? -45 : 45);
                }
                else if (n3 < 0.0) {
                    rotationYaw += ((n2 > 0.0) ? 45 : -45);
                }
                n3 = 0.0;
                if (n2 > 0.0) {
                    n2 = 1.0;
                }
                else if (n2 < 0.0) {
                    n2 = -1.0;
                }
            }
            final double n4 = n2 * n * -Math.sin(Math.toRadians(rotationYaw)) + n3 * n * Math.cos(Math.toRadians(rotationYaw));
            final double n5 = n2 * n * Math.cos(Math.toRadians(rotationYaw)) - n3 * n * -Math.sin(Math.toRadians(rotationYaw));
            moveEvent.setX(n4);
            moveEvent.setZ(n5);
            Flight.mc.player.motionX = n4;
            Flight.mc.player.motionZ = n5;
        }
    }
    
    public Flight() {
        super("Flight", "Allows you to fly", Category.MOVEMENT);
        this.speed = this.add(new Setting("Speed", 1.0f, 0.1f, 10.0f));
        this.verticalSpeed = this.add(new Setting("VerticalSpeed", 1.0f, 0.1f, 10.0f));
        this.glide = this.add(new Setting("Glide", true));
        Flight.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onMove(final MoveEvent moveEvent) {
        if (fullNullCheck()) {
            return;
        }
        Flight.mc.player.capabilities.isFlying = false;
        Flight.mc.player.motionY = 0.0;
        this.setMoveSpeed(moveEvent, (float)this.speed.getValue());
        if ((boolean)this.glide.getValue() && !Flight.mc.player.onGround) {
            moveEvent.setY(-0.03150000050663948);
            Flight.mc.player.motionY = -0.03150000050663948;
        }
        if (MovementUtil.isJumping()) {
            moveEvent.setY(Flight.mc.player.motionY + (float)this.verticalSpeed.getValue());
            final EntityPlayerSP player = Flight.mc.player;
            player.motionY += (float)this.verticalSpeed.getValue();
        }
        if (Flight.mc.gameSettings.keyBindSneak.isKeyDown() || (InventoryMove.INSTANCE.isOn() && (boolean)InventoryMove.INSTANCE.sneak.getValue() && Keyboard.isKeyDown(Flight.mc.gameSettings.keyBindSneak.getKeyCode()))) {
            moveEvent.setY(Flight.mc.player.motionY - (float)this.verticalSpeed.getValue());
            final EntityPlayerSP player2 = Flight.mc.player;
            player2.motionY -= (float)this.verticalSpeed.getValue();
        }
    }
}
