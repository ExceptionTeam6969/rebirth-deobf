//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.api.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.client.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import java.util.*;
import me.rebirthclient.api.events.impl.*;

public class LongJump extends Module
{
    public double distance;
    boolean inAir;
    public final Setting noKick;
    public final Setting autoDisable;
    public int groundTicks;
    public final Setting boost;
    public static LongJump INSTANCE;
    public double speed;
    public int airTicks;
    public int stage;
    
    public LongJump() {
        super("LongJump", "ear", Category.MOVEMENT);
        this.boost = this.add(new Setting("Boost", 4.5, 0.1, 20.0));
        this.noKick = this.add(new Setting("AntiKick", true));
        this.autoDisable = this.add(new Setting("AutoDisable", true));
        this.inAir = false;
        LongJump.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketPlayerPosLook) {
            if (this.noKick.getValue()) {
                this.disable();
            }
            this.speed = 0.0;
            this.stage = 0;
            this.airTicks = 0;
            this.groundTicks = 0;
        }
    }
    
    static {
        LongJump.INSTANCE = new LongJump();
    }
    
    @Override
    public void onEnable() {
        this.inAir = false;
        if (LongJump.mc.player != null) {
            this.distance = MovementUtil.getDistance2D();
            this.speed = MovementUtil.getSpeed();
        }
        this.stage = 0;
        this.airTicks = 0;
        this.groundTicks = 0;
    }
    
    @SubscribeEvent
    public void MoveEvent(final MoveEvent moveEvent) {
        if (LongJump.mc.player.moveStrafing <= 0.0f && LongJump.mc.player.moveForward <= 0.0f) {
            this.stage = 1;
        }
        if (MathUtil.round(LongJump.mc.player.posY - (int)LongJump.mc.player.posY, 3) == MathUtil.round(0.943, 3)) {
            final EntityPlayerSP player = LongJump.mc.player;
            player.motionY -= 0.03;
            moveEvent.setY(moveEvent.getY() - 0.03);
        }
        if (this.stage == 1 && MovementUtil.isMoving()) {
            this.stage = 2;
            this.speed = (double)this.boost.getValue() * MovementUtil.getSpeed() - 0.01;
        }
        else if (this.stage == 2) {
            this.stage = 3;
            moveEvent.setY(LongJump.mc.player.motionY = 0.424);
            this.speed *= 2.149802;
        }
        else if (this.stage == 3) {
            this.stage = 4;
            this.speed = this.distance - 0.66 * (this.distance - MovementUtil.getSpeed());
        }
        else {
            if (LongJump.mc.world.getCollisionBoxes((Entity)LongJump.mc.player, LongJump.mc.player.getEntityBoundingBox().offset(0.0, LongJump.mc.player.motionY, 0.0)).size() > 0 || LongJump.mc.player.collidedVertically) {
                this.stage = 1;
            }
            this.speed = this.distance - this.distance / 159.0;
        }
        MovementUtil.strafe(moveEvent, this.speed = Math.max(this.speed, MovementUtil.getSpeed()));
        float moveForward = LongJump.mc.player.movementInput.moveForward;
        float moveStrafe = LongJump.mc.player.movementInput.moveStrafe;
        float rotationYaw = LongJump.mc.player.rotationYaw;
        if (moveForward == 0.0f && moveStrafe == 0.0f) {
            moveEvent.setX(0.0);
            moveEvent.setZ(0.0);
        }
        else if (moveForward != 0.0f) {
            if (moveStrafe >= 1.0f) {
                rotationYaw += ((moveForward > 0.0f) ? -45 : 45);
                moveStrafe = 0.0f;
            }
            else if (moveStrafe <= -1.0f) {
                rotationYaw += ((moveForward > 0.0f) ? 45 : -45);
                moveStrafe = 0.0f;
            }
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double cos = Math.cos(Math.toRadians(rotationYaw + 90.0f));
        final double sin = Math.sin(Math.toRadians(rotationYaw + 90.0f));
        moveEvent.setX(moveForward * this.speed * cos + moveStrafe * this.speed * sin);
        moveEvent.setZ(moveForward * this.speed * sin - moveStrafe * this.speed * cos);
    }
    
    public double getDistance(final EntityPlayer entityPlayer, final double n) {
        final List getCollisionBoxes = entityPlayer.world.getCollisionBoxes((Entity)entityPlayer, entityPlayer.getEntityBoundingBox().offset(0.0, -n, 0.0));
        if (getCollisionBoxes.isEmpty()) {
            return 0.0;
        }
        final double n2 = 0.0;
        final Iterator<AxisAlignedBB> iterator = getCollisionBoxes.iterator();
        if (iterator.hasNext()) {
            final AxisAlignedBB axisAlignedBB = iterator.next();
            if (axisAlignedBB.maxY > n2) {
                final double maxY = axisAlignedBB.maxY;
            }
            return 0.0;
        }
        return entityPlayer.posY - n2;
    }
    
    @Override
    public void onUpdate() {
        if (!LongJump.mc.player.onGround) {
            this.inAir = true;
        }
        if (LongJump.mc.player.onGround && this.inAir && (boolean)this.autoDisable.getValue()) {
            this.disable();
        }
    }
    
    @SubscribeEvent
    public void onUpdate(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        this.distance = MovementUtil.getDistance2D();
    }
    
    @Override
    public void onDisable() {
        this.inAir = false;
    }
}
