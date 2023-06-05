//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import me.rebirthclient.mod.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class PositionManager extends Mod
{
    private double z;
    private double y;
    private double x;
    private boolean onGround;
    
    public void updatePosition() {
        this.x = PositionManager.mc.player.posX;
        this.y = PositionManager.mc.player.posY;
        this.z = PositionManager.mc.player.posZ;
        this.onGround = PositionManager.mc.player.onGround;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public double getX() {
        return this.x;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
    
    public void setPlayerPosition(final double posX, final double posY, final double posZ, final boolean onGround) {
        PositionManager.mc.player.posX = posX;
        PositionManager.mc.player.posY = posY;
        PositionManager.mc.player.posZ = posZ;
        PositionManager.mc.player.onGround = onGround;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setPlayerPosition(final double posX, final double posY, final double posZ) {
        PositionManager.mc.player.posX = posX;
        PositionManager.mc.player.posY = posY;
        PositionManager.mc.player.posZ = posZ;
    }
    
    public void restorePosition() {
        PositionManager.mc.player.posX = this.x;
        PositionManager.mc.player.posY = this.y;
        PositionManager.mc.player.posZ = this.z;
        PositionManager.mc.player.onGround = this.onGround;
    }
    
    public void setPositionPacket(final double n, final double n2, final double n3, final boolean b, final boolean b2, final boolean b3) {
        PositionManager.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(n, n2, n3, b));
        if (b2) {
            PositionManager.mc.player.setPosition(n, n2, n3);
            if (b3) {
                this.updatePosition();
            }
        }
    }
    
    public double getZ() {
        return this.z;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
}
