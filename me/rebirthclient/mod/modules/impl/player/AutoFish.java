//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.server.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.*;

public class AutoFish extends Module
{
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (fullNullCheck()) {
            return;
        }
        if (receive.isCanceled()) {
            return;
        }
        if (receive.getPacket() instanceof SPacketSoundEffect) {
            final SPacketSoundEffect sPacketSoundEffect = (SPacketSoundEffect)receive.getPacket();
            if (sPacketSoundEffect.getCategory() == SoundCategory.NEUTRAL && sPacketSoundEffect.getSound() == SoundEvents.ENTITY_BOBBER_SPLASH && AutoFish.mc.player.getHeldItemMainhand().getItem() == Items.FISHING_ROD) {
                AutoFish.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                AutoFish.mc.player.swingArm(EnumHand.MAIN_HAND);
                AutoFish.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                AutoFish.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
        }
    }
    
    public AutoFish() {
        super("AutoFish", "auto fishing", Category.PLAYER);
    }
}
