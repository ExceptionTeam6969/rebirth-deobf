//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.events.impl;

import me.rebirthclient.api.events.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PacketEvent extends Event
{
    private final Packet packet;
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public PacketEvent(final int n, final Packet packet) {
        super(n);
        this.packet = packet;
    }
    
    @Cancelable
    public static class Receive extends PacketEvent
    {
        public Receive(final int n, final Packet packet) {
            super(n, packet);
        }
    }
    
    @Cancelable
    public static class Send extends PacketEvent
    {
        public Send(final int n, final Packet packet) {
            super(n, packet);
        }
    }
}
