//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.*;

public class NoRotate extends Module
{
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (receive.isCanceled() || fullNullCheck()) {
            return;
        }
        if (receive.getStage() == 0 && receive.getPacket() instanceof SPacketPlayerPosLook) {
            final SPacketPlayerPosLook sPacketPlayerPosLook = (SPacketPlayerPosLook)receive.getPacket();
            sPacketPlayerPosLook.yaw = NoRotate.mc.player.rotationYaw;
            sPacketPlayerPosLook.pitch = NoRotate.mc.player.rotationPitch;
        }
    }
    
    public NoRotate() {
        super("NoRotate", "Dangerous to use might desync you", Category.PLAYER);
    }
}
