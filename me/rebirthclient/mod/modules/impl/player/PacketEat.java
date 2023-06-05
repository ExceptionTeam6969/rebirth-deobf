//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.mod.modules.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.client.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PacketEat extends Module
{
    Item item;
    
    public PacketEat() {
        super("PacketEat", "cancel packet", Category.PLAYER);
    }
    
    @Override
    public void onTick() {
        if (PacketEat.mc.player.isHandActive()) {
            this.item = PacketEat.mc.player.getActiveItemStack().getItem();
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getPacket() instanceof CPacketPlayerDigging && ((CPacketPlayerDigging)send.getPacket()).getAction() == CPacketPlayerDigging.Action.RELEASE_USE_ITEM && this.item instanceof ItemFood) {
            send.setCanceled(true);
        }
    }
}
