//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.player;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.api.events.impl.*;
import me.rebirthclient.api.managers.impl.*;
import me.rebirthclient.api.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.inventory.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import me.rebirthclient.mod.modules.*;

public class AntiOpen extends Module
{
    public boolean needPacket;
    private CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock;
    private final Setting packet;
    
    @Override
    public void onDisable() {
        this.needPacket = false;
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent.Send send) {
        if (fullNullCheck() || !(boolean)this.packet.getValue() || !(send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) || SneakManager.isSneaking || !BlockUtil.canUseList.contains(AntiOpen.mc.world.getBlockState(((CPacketPlayerTryUseItemOnBlock)send.getPacket()).getPos()).getBlock())) {
            if (send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
                this.needPacket = false;
            }
            return;
        }
        if (!this.needPacket && !SneakManager.isSneaking) {
            this.cPacketPlayerTryUseItemOnBlock = (CPacketPlayerTryUseItemOnBlock)send.getPacket();
            send.setCanceled(true);
            this.needPacket = true;
        }
        else {
            this.needPacket = false;
        }
    }
    
    @Override
    public void onTick() {
        if (!(boolean)this.packet.getValue()) {
            if (AntiOpen.mc.player.openContainer instanceof ContainerChest) {
                AntiOpen.mc.player.closeScreen();
            }
        }
        else if (this.needPacket && this.cPacketPlayerTryUseItemOnBlock != null) {
            AntiOpen.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AntiOpen.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            AntiOpen.mc.player.connection.sendPacket((Packet)this.cPacketPlayerTryUseItemOnBlock);
            AntiOpen.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AntiOpen.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
    }
    
    public AntiOpen() {
        super("AntiOpen", "Anti Chest", Category.PLAYER);
        this.packet = this.add(new Setting("Packet", true));
        this.needPacket = false;
        this.cPacketPlayerTryUseItemOnBlock = null;
    }
}
