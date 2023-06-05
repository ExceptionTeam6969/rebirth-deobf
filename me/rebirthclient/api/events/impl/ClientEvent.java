//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events.impl;

import me.rebirthclient.api.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.*;
import me.rebirthclient.mod.modules.settings.*;

@Cancelable
public class ClientEvent extends Event
{
    private Mod mod;
    private Setting setting;
    
    public ClientEvent(final int n, final Mod mod) {
        super(n);
        this.mod = mod;
    }
    
    public ClientEvent(final Setting setting) {
        super(2);
        this.setting = setting;
    }
    
    public Mod getMod() {
        return this.mod;
    }
    
    public Setting getSetting() {
        return this.setting;
    }
}
