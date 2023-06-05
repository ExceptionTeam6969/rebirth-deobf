//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import me.rebirthclient.mod.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.client.*;
import me.rebirthclient.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.common.*;
import com.mojang.realmsclient.gui.*;
import me.rebirthclient.mod.commands.*;

public class ReloadManager extends Mod
{
    public String prefix;
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        final CPacketChatMessage cPacketChatMessage;
        if (send.getPacket() instanceof CPacketChatMessage && (cPacketChatMessage = (CPacketChatMessage)send.getPacket()).getMessage().startsWith(this.prefix) && cPacketChatMessage.getMessage().contains("reload")) {
            Rebirth.load();
            send.setCanceled(true);
        }
    }
    
    public void unload() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public void init(final String prefix) {
        this.prefix = prefix;
        MinecraftForge.EVENT_BUS.register((Object)this);
        if (!Mod.fullNullCheck()) {
            Command.sendMessage(ChatFormatting.RED + "Rebirth" + " has been unloaded. Type " + prefix + "reload to reload.");
        }
    }
}
