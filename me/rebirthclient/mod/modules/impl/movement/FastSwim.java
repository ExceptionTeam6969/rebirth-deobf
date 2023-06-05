//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import me.rebirthclient.api.util.*;
import org.lwjgl.input.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public final class FastSwim extends Module
{
    private final Setting speed;
    private final Setting verticalSpeed;
    public static FastSwim INSTANCE;
    private final Setting glideSpeed;
    private final Setting glide;
    
    private void setMoveSpeed(final MoveEvent moveEvent, final double n) {
        double n2 = FastSwim.mc.player.movementInput.moveForward;
        double n3 = FastSwim.mc.player.movementInput.moveStrafe;
        float rotationYaw = FastSwim.mc.player.rotationYaw;
        if (n2 == 0.0 && n3 == 0.0) {
            moveEvent.setX(0.0);
            moveEvent.setZ(0.0);
            FastSwim.mc.player.motionX = 0.0;
            FastSwim.mc.player.motionZ = 0.0;
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
            FastSwim.mc.player.motionX = n4;
            FastSwim.mc.player.motionZ = n5;
        }
    }
    
    public FastSwim() {
        super("FastSwim", "Allows you fast swim", Category.MOVEMENT);
        this.speed = this.add(new Setting("Speed", 5.0f, 1.0f, 10.0f));
        this.verticalSpeed = this.add(new Setting("VerticalSpeed", 2.0f, 1.0f, 10.0f));
        this.glide = this.add(new Setting("Glide", true).setParent());
        this.glideSpeed = this.add(new Setting("GlideSpeed", 2.0f, 1.0f, 10.0f, this::lambda$new$0));
        FastSwim.INSTANCE = this;
    }
    
    private boolean lambda$new$0(final Float n) {
        return this.glide.isOpen();
    }
    
    @SubscribeEvent
    public void onMove(final MoveEvent moveEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (!FastSwim.mc.player.isInLava() && !FastSwim.mc.player.isInWater()) {
            return;
        }
        FastSwim.mc.player.capabilities.isFlying = false;
        moveEvent.setY(FastSwim.mc.player.motionY = 0.0);
        this.setMoveSpeed(moveEvent, (float)this.speed.getValue() / 20.0f);
        if ((boolean)this.glide.getValue() && !FastSwim.mc.player.onGround) {
            moveEvent.setY((double)(-0.007875f * (float)this.glideSpeed.getValue()));
            FastSwim.mc.player.motionY = -0.007875f * (float)this.glideSpeed.getValue();
        }
        if (MovementUtil.isJumping()) {
            moveEvent.setY(FastSwim.mc.player.motionY + (float)this.verticalSpeed.getValue() / 20.0);
            final EntityPlayerSP player = FastSwim.mc.player;
            player.motionY += (float)this.verticalSpeed.getValue() / 20.0;
        }
        if (FastSwim.mc.gameSettings.keyBindSneak.isKeyDown() || (InventoryMove.INSTANCE.isOn() && (boolean)InventoryMove.INSTANCE.sneak.getValue() && Keyboard.isKeyDown(FastSwim.mc.gameSettings.keyBindSneak.getKeyCode()))) {
            moveEvent.setY(FastSwim.mc.player.motionY - (float)this.verticalSpeed.getValue() / 20.0);
            final EntityPlayerSP player2 = FastSwim.mc.player;
            player2.motionY -= (float)this.verticalSpeed.getValue() / 20.0;
        }
    }
    
    static {
        FastSwim.INSTANCE = new FastSwim();
    }
}
