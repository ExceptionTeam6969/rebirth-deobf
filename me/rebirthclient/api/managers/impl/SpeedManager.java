//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import me.rebirthclient.mod.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.util.math.*;

public class SpeedManager extends Mod
{
    public boolean wasFirstJump;
    public double speedometerCurrentSpeed;
    public boolean didJumpLastTick;
    public static boolean isJumping;
    public final HashMap playerSpeeds;
    public double percentJumpSpeedChanged;
    public long jumpInfoStartTime;
    public double lastJumpSpeed;
    public static final Minecraft mc;
    private final int distancer = 20;
    public double firstJumpSpeed;
    public static final double LAST_JUMP_INFO_DURATION_DEFAULT = 3.0;
    public double jumpSpeedChanged;
    public static boolean didJumpThisTick;
    
    public double getSpeedMpS() {
        return Math.round(10.0 * (this.turnIntoKpH(this.speedometerCurrentSpeed) / 3.6)) / 10.0;
    }
    
    public void updatePlayers() {
        final Iterator<EntityPlayer> iterator = (Iterator<EntityPlayer>)SpeedManager.mc.world.playerEntities.iterator();
        if (!iterator.hasNext()) {
            return;
        }
        final EntityPlayer entityPlayer = iterator.next();
        if (SpeedManager.mc.player.getDistanceSq((Entity)entityPlayer) >= 400.0) {
            return;
        }
        final double n = entityPlayer.posX - entityPlayer.prevPosX;
        final double n2 = entityPlayer.posZ - entityPlayer.prevPosZ;
        this.playerSpeeds.put(entityPlayer, n * n + n2 * n2);
    }
    
    public float lastJumpInfoTimeRemaining() {
        return (Minecraft.getSystemTime() - this.jumpInfoStartTime) / 1000.0f;
    }
    
    public void updateValues() {
        final double n = SpeedManager.mc.player.posX - SpeedManager.mc.player.prevPosX;
        final double n2 = SpeedManager.mc.player.posZ - SpeedManager.mc.player.prevPosZ;
        this.speedometerCurrentSpeed = n * n + n2 * n2;
        if (SpeedManager.didJumpThisTick && (!SpeedManager.mc.player.onGround || SpeedManager.isJumping)) {
            if (SpeedManager.didJumpThisTick && !this.didJumpLastTick) {
                this.wasFirstJump = (this.lastJumpSpeed == 0.0);
                this.percentJumpSpeedChanged = ((this.speedometerCurrentSpeed != 0.0) ? (this.speedometerCurrentSpeed / this.lastJumpSpeed - 1.0) : -1.0);
                this.jumpSpeedChanged = this.speedometerCurrentSpeed - this.lastJumpSpeed;
                this.jumpInfoStartTime = Minecraft.getSystemTime();
                this.lastJumpSpeed = this.speedometerCurrentSpeed;
                this.firstJumpSpeed = (this.wasFirstJump ? this.lastJumpSpeed : 0.0);
            }
            this.didJumpLastTick = SpeedManager.didJumpThisTick;
        }
        else {
            this.didJumpLastTick = false;
            this.lastJumpSpeed = 0.0;
        }
        this.updatePlayers();
    }
    
    public double getPlayerSpeed(final EntityPlayer entityPlayer) {
        if (this.playerSpeeds.get(entityPlayer) == null) {
            return 0.0;
        }
        return this.turnIntoKpH(this.playerSpeeds.get(entityPlayer));
    }
    
    public SpeedManager() {
        this.playerSpeeds = new HashMap();
        this.wasFirstJump = true;
    }
    
    public static void setIsJumping(final boolean isJumping) {
        SpeedManager.isJumping = isJumping;
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
    
    public double turnIntoKpH(final double n) {
        return MathHelper.sqrt(n) * 71.2729367892;
    }
    
    public double getSpeedKpH() {
        return Math.round(10.0 * this.turnIntoKpH(this.speedometerCurrentSpeed)) / 10.0;
    }
    
    public static void setDidJumpThisTick(final boolean didJumpThisTick) {
        SpeedManager.didJumpThisTick = didJumpThisTick;
    }
}
