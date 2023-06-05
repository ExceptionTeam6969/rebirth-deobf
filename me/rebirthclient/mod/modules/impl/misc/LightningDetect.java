//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.*;

public class LightningDetect extends Module
{
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (receive.isCanceled()) {
            return;
        }
        if (receive.getPacket() instanceof SPacketSpawnGlobalEntity) {
            this.sendMessage("Lightning Detected! X:" + (int)((SPacketSpawnGlobalEntity)receive.getPacket()).getX() + " Y:" + (int)((SPacketSpawnGlobalEntity)receive.getPacket()).getY() + " Z:" + (int)((SPacketSpawnGlobalEntity)receive.getPacket()).getZ());
        }
    }
    
    public LightningDetect() {
        super("LightningDetect", "EZ", Category.MISC);
    }
}
