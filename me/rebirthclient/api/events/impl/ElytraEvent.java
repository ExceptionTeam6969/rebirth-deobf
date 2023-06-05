//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events.impl;

import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;

@Cancelable
public class ElytraEvent extends Event
{
    private final Entity entity;
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public ElytraEvent(final Entity entity) {
        this.entity = entity;
    }
}
