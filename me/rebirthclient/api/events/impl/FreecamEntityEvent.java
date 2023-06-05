//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events.impl;

import me.rebirthclient.api.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;

@Cancelable
public class FreecamEntityEvent extends Event
{
    Entity entity;
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public void setEntity(final Entity entity) {
        this.entity = entity;
    }
    
    public FreecamEntityEvent(final Entity entity) {
        this.entity = entity;
    }
}
