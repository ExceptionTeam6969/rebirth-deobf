//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events.impl;

import me.rebirthclient.api.events.*;
import net.minecraft.entity.player.*;
import java.util.*;

public class ConnectionEvent extends Event
{
    private final EntityPlayer player;
    private final String name;
    private final UUID uuid;
    
    public String getName() {
        return this.name;
    }
    
    public UUID getUuid() {
        return this.uuid;
    }
    
    public ConnectionEvent(final int n, final EntityPlayer player, final UUID uuid, final String name) {
        super(n);
        this.player = player;
        this.uuid = uuid;
        this.name = name;
    }
    
    public ConnectionEvent(final int n, final UUID uuid, final String name) {
        super(n);
        this.uuid = uuid;
        this.name = name;
        this.player = null;
    }
    
    public EntityPlayer getPlayer() {
        return this.player;
    }
}
