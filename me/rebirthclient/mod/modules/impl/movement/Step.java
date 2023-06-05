//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.movement;

import me.rebirthclient.mod.modules.settings.*;
import net.minecraft.init.*;
import me.rebirthclient.api.managers.impl.*;
import me.rebirthclient.api.util.*;
import me.rebirthclient.mod.modules.impl.exploit.*;
import net.minecraft.entity.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import java.text.*;
import me.rebirthclient.mod.modules.*;

public class Step extends Module
{
    private final Setting mode;
    private final Setting pauseSneak;
    public static Step INSTANCE;
    private final Setting pauseBurrow;
    private final Setting height;
    private final Setting pauseWeb;
    private final Setting onlyMoving;
    
    @Override
    public void onUpdate() {
        if ((Step.mc.world.getBlockState(EntityUtil.getPlayerPos()).getBlock() == Blocks.PISTON_HEAD || Step.mc.world.getBlockState(EntityUtil.getPlayerPos()).getBlock() == Blocks.OBSIDIAN || Step.mc.world.getBlockState(EntityUtil.getPlayerPos()).getBlock() == Blocks.ENDER_CHEST || Step.mc.world.getBlockState(EntityUtil.getPlayerPos()).getBlock() == Blocks.BEDROCK) && (boolean)this.pauseBurrow.getValue()) {
            Step.mc.player.stepHeight = 0.1f;
            return;
        }
        if ((Step.mc.world.getBlockState(EntityUtil.getPlayerPos().up()).getBlock() == Blocks.PISTON_HEAD || Step.mc.world.getBlockState(EntityUtil.getPlayerPos().up()).getBlock() == Blocks.OBSIDIAN || Step.mc.world.getBlockState(EntityUtil.getPlayerPos().up()).getBlock() == Blocks.ENDER_CHEST || Step.mc.world.getBlockState(EntityUtil.getPlayerPos().up()).getBlock() == Blocks.BEDROCK) && (boolean)this.pauseBurrow.getValue()) {
            Step.mc.player.stepHeight = 0.1f;
            return;
        }
        if ((boolean)this.pauseWeb.getValue() && Step.mc.player.isInWeb) {
            Step.mc.player.stepHeight = 0.1f;
            return;
        }
        if (SneakManager.isSneaking && (boolean)this.pauseSneak.getValue()) {
            Step.mc.player.stepHeight = 0.1f;
            return;
        }
        if ((boolean)this.onlyMoving.getValue() && !MovementUtil.isMoving() && HoleSnap.INSTANCE.isOff()) {
            Step.mc.player.stepHeight = 0.1f;
            return;
        }
        if (Clip.INSTANCE.isOn()) {
            Step.mc.player.stepHeight = 0.1f;
            return;
        }
        if (Step.mc.player.isInWater() || Step.mc.player.isInLava() || Step.mc.player.isOnLadder() || Step.mc.gameSettings.keyBindJump.isKeyDown()) {
            Step.mc.player.stepHeight = 0.1f;
            return;
        }
        if (this.mode.getValue() == Mode.Normal) {
            Step.mc.player.stepHeight = 0.6f;
            final double[] forward = forward(0.1);
            boolean b = false;
            boolean b2 = false;
            boolean b3 = false;
            boolean b4 = false;
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 2.6, forward[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 2.4, forward[1])).isEmpty()) {
                b = true;
            }
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 2.1, forward[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 1.9, forward[1])).isEmpty()) {
                b2 = true;
            }
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 1.6, forward[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 1.4, forward[1])).isEmpty()) {
                b3 = true;
            }
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 1.0, forward[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 0.6, forward[1])).isEmpty()) {
                b4 = true;
            }
            if (Step.mc.player.collidedHorizontally && (Step.mc.player.moveForward != 0.0f || Step.mc.player.moveStrafing != 0.0f) && Step.mc.player.onGround) {
                if (b4 && (double)this.height.getValue() >= 1.0) {
                    final double[] array = { 0.42, 0.753 };
                    int n = 0;
                    if (n < array.length) {
                        Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + array[n], Step.mc.player.posZ, Step.mc.player.onGround));
                        ++n;
                        return;
                    }
                    Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 1.0, Step.mc.player.posZ);
                }
                if (b3 && (double)this.height.getValue() >= 1.5) {
                    final double[] array2 = { 0.42, 0.75, 1.0, 1.16, 1.23, 1.2 };
                    int n2 = 0;
                    if (n2 < array2.length) {
                        Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + array2[n2], Step.mc.player.posZ, Step.mc.player.onGround));
                        ++n2;
                        return;
                    }
                    Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 1.5, Step.mc.player.posZ);
                }
                if (b2 && (double)this.height.getValue() >= 2.0) {
                    final double[] array3 = { 0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43 };
                    int n3 = 0;
                    if (n3 < array3.length) {
                        Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + array3[n3], Step.mc.player.posZ, Step.mc.player.onGround));
                        ++n3;
                        return;
                    }
                    Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 2.0, Step.mc.player.posZ);
                }
                if (b && (double)this.height.getValue() >= 2.5) {
                    final double[] array4 = { 0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.907 };
                    int n4 = 0;
                    if (n4 < array4.length) {
                        Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + array4[n4], Step.mc.player.posZ, Step.mc.player.onGround));
                        ++n4;
                        return;
                    }
                    Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 2.5, Step.mc.player.posZ);
                }
            }
        }
        if (this.mode.getValue() == Mode.Vanilla) {
            Step.mc.player.stepHeight = Float.parseFloat(new DecimalFormat("#").format(this.height.getValue()));
        }
    }
    
    @Override
    public void onDisable() {
        Step.mc.player.stepHeight = 0.6f;
    }
    
    @Override
    public String getInfo() {
        return ((Mode)this.mode.getValue()).name();
    }
    
    public Step() {
        super("Step", "step", Category.MOVEMENT);
        this.height = this.add(new Setting("Height", 2.5, 0.5, 3.5));
        this.mode = this.add(new Setting("Mode", Mode.Vanilla));
        this.pauseBurrow = this.add(new Setting("PauseBurrow", true));
        this.pauseSneak = this.add(new Setting("PauseSneak", true));
        this.pauseWeb = this.add(new Setting("PauseWeb", true));
        this.onlyMoving = this.add(new Setting("OnlyMoving", true));
        Step.INSTANCE = this;
    }
    
    public static double[] forward(final double n) {
        float moveForward = Step.mc.player.movementInput.moveForward;
        float moveStrafe = Step.mc.player.movementInput.moveStrafe;
        float n2 = Step.mc.player.prevRotationYaw + (Step.mc.player.rotationYaw - Step.mc.player.prevRotationYaw) * Step.mc.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }
    
    public enum Mode
    {
        Vanilla("Vanilla", 0), 
        Normal("Normal", 1);
        
        private static final Mode[] $VALUES;
        
        static {
            $VALUES = new Mode[] { Mode.Vanilla, Mode.Normal };
        }
        
        private Mode(final String s, final int n) {
        }
    }
}
