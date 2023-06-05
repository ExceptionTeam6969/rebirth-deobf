//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.*;
import me.rebirthclient.api.events.impl.*;

public class Debug extends Module
{
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onPacketSend(final PacketEvent.Send send) {
        if (fullNullCheck() || Debug.mc.player.isCreative() || !(send.getPacket() instanceof CPacketPlayerDigging)) {
            return;
        }
        this.sendMessage(((CPacketPlayerDigging)send.getPacket()).getAction().name());
    }
    
    public Debug() {
        super("Debug", "dev!", Category.MISC);
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onClickBlock(final BlockEvent blockEvent) {
        if (fullNullCheck()) {
            return;
        }
        blockEvent.setCanceled(true);
    }
}
