//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import net.minecraftforge.common.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class SneakManager
{
    public static boolean isSneaking;
    
    public void init() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketEntityAction) {
            if (((CPacketEntityAction)send.getPacket()).getAction() == CPacketEntityAction.Action.START_SNEAKING) {
                SneakManager.isSneaking = true;
            }
            if (((CPacketEntityAction)send.getPacket()).getAction() == CPacketEntityAction.Action.STOP_SNEAKING) {
                SneakManager.isSneaking = false;
            }
        }
    }
    
    static {
        SneakManager.isSneaking = false;
    }
}
