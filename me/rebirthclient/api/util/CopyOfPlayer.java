//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.util;

import me.rebirthclient.api.util.render.entity.*;
import net.minecraft.entity.player.*;

public class CopyOfPlayer
{
    private final StaticModelPlayer model;
    private final double z;
    private final EntityPlayer player;
    private final double y;
    private final long time;
    private final double x;
    
    public StaticModelPlayer getModel() {
        return this.model;
    }
    
    public double getY() {
        return this.y;
    }
    
    public CopyOfPlayer(final EntityPlayer player, final long time, final double x, final double n, final double z, final boolean b) {
        this.player = player;
        this.time = time;
        this.x = x;
        this.y = n - (player.isSneaking() ? 0.125 : 0.0);
        this.z = z;
        (this.model = new StaticModelPlayer(player, b, 0.0f)).disableArmorLayers();
    }
    
    public EntityPlayer getPlayer() {
        return this.player;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public double getX() {
        return this.x;
    }
}
