//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.asm.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.network.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;
import io.netty.channel.*;
import me.rebirthclient.mod.modules.impl.misc.*;

@Mixin({ NetworkManager.class })
public class MixinNetworkManager
{
    @Inject(method = { "sendPacket(Lnet/minecraft/network/Packet;)V" }, at = { @At("HEAD") }, cancellable = true)
    private void onSendPacketPre(final Packet packet, final CallbackInfo callbackInfo) {
        final PacketEvent.Send send = new PacketEvent.Send(0, packet);
        MinecraftForge.EVENT_BUS.post((Event)send);
        if (send.isCanceled()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "exceptionCaught" }, at = { @At("HEAD") }, cancellable = true)
    private void exceptionCaught(final ChannelHandlerContext channelHandlerContext, final Throwable t, final CallbackInfo callbackInfo) {
        if (AntiNullPointer.INSTANCE.isOn()) {
            AntiNullPointer.INSTANCE.sendWarning(t);
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "channelRead0" }, at = { @At("HEAD") }, cancellable = true)
    private void onChannelReadPre(final ChannelHandlerContext channelHandlerContext, final Packet packet, final CallbackInfo callbackInfo) {
        final PacketEvent.Receive receive = new PacketEvent.Receive(0, packet);
        MinecraftForge.EVENT_BUS.post((Event)receive);
        if (receive.isCanceled()) {
            callbackInfo.cancel();
        }
    }
}
