//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import java.util.*;
import net.minecraft.entity.player.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.mod.modules.*;

public class PopCounter extends Module
{
    public static final HashMap TotemPopContainer;
    public static PopCounter INSTANCE;
    
    @Override
    public void onTotemPop(final EntityPlayer entityPlayer) {
        int intValue = 1;
        if (PopCounter.TotemPopContainer.containsKey(entityPlayer.getName())) {
            intValue = PopCounter.TotemPopContainer.get(entityPlayer.getName());
            PopCounter.TotemPopContainer.put(entityPlayer.getName(), ++intValue);
        }
        else {
            PopCounter.TotemPopContainer.put(entityPlayer.getName(), intValue);
        }
        if (intValue == 1) {
            if (PopCounter.mc.player.equals((Object)entityPlayer)) {
                if (this.isOn()) {
                    this.sendMessageWithID(ChatFormatting.BLUE + "You popped " + ChatFormatting.RED + intValue + ChatFormatting.RED + " Totem.", entityPlayer.getEntityId());
                }
            }
            else if (this.isOn()) {
                this.sendMessageWithID(ChatFormatting.RED + entityPlayer.getName() + " popped " + ChatFormatting.GREEN + intValue + ChatFormatting.RED + " Totem.", entityPlayer.getEntityId());
            }
        }
        else if (PopCounter.mc.player.equals((Object)entityPlayer)) {
            if (this.isOn()) {
                this.sendMessageWithID(ChatFormatting.BLUE + "You popped " + ChatFormatting.RED + intValue + ChatFormatting.RED + " Totems.", entityPlayer.getEntityId());
            }
        }
        else if (this.isOn()) {
            this.sendMessageWithID(ChatFormatting.RED + entityPlayer.getName() + " popped " + ChatFormatting.GREEN + intValue + ChatFormatting.RED + " Totems.", entityPlayer.getEntityId());
        }
    }
    
    @Override
    public void onDeath(final EntityPlayer entityPlayer) {
        if (PopCounter.TotemPopContainer.containsKey(entityPlayer.getName())) {
            final int intValue = PopCounter.TotemPopContainer.get(entityPlayer.getName());
            PopCounter.TotemPopContainer.remove(entityPlayer.getName());
            if (intValue == 1) {
                if (PopCounter.mc.player.equals((Object)entityPlayer)) {
                    if (this.isOn()) {
                        this.sendMessageWithID(ChatFormatting.BLUE + "You died after popping " + ChatFormatting.RED + intValue + ChatFormatting.RED + " Totem!", entityPlayer.getEntityId());
                    }
                    if (AutoEZ.INSTANCE.isOn() && (boolean)AutoEZ.INSTANCE.whenSelf.getValue()) {
                        PopCounter.mc.player.connection.sendPacket((Packet)new CPacketChatMessage((String)AutoEZ.INSTANCE.SelfString.getValue()));
                    }
                }
                else {
                    if (this.isOn()) {
                        this.sendMessageWithID(ChatFormatting.RED + entityPlayer.getName() + " died after popping " + ChatFormatting.GREEN + intValue + ChatFormatting.RED + " Totem!", entityPlayer.getEntityId());
                    }
                    if (AutoEZ.INSTANCE.isOn() && (!Managers.FRIENDS.isFriend(entityPlayer.getName()) || (boolean)AutoEZ.INSTANCE.whenFriend.getValue())) {
                        if (AutoEZ.INSTANCE.poped.getValue()) {
                            PopCounter.mc.player.connection.sendPacket((Packet)new CPacketChatMessage((String)AutoEZ.INSTANCE.EzString.getValue() + " " + entityPlayer.getName() + " popping" + intValue + " Totem!"));
                        }
                        else {
                            PopCounter.mc.player.connection.sendPacket((Packet)new CPacketChatMessage((String)AutoEZ.INSTANCE.EzString.getValue() + " " + entityPlayer.getName()));
                        }
                    }
                }
            }
            else if (PopCounter.mc.player.equals((Object)entityPlayer)) {
                if (this.isOn()) {
                    this.sendMessageWithID(ChatFormatting.BLUE + "You died after popping " + ChatFormatting.RED + intValue + ChatFormatting.RED + " Totems!", entityPlayer.getEntityId());
                }
                if (AutoEZ.INSTANCE.isOn() && (boolean)AutoEZ.INSTANCE.whenSelf.getValue()) {
                    PopCounter.mc.player.connection.sendPacket((Packet)new CPacketChatMessage((String)AutoEZ.INSTANCE.SelfString.getValue()));
                }
            }
            else {
                if (this.isOn()) {
                    this.sendMessageWithID(ChatFormatting.RED + entityPlayer.getName() + " died after popping " + ChatFormatting.GREEN + intValue + ChatFormatting.RED + " Totems!", entityPlayer.getEntityId());
                }
                if (AutoEZ.INSTANCE.isOn() && (!Managers.FRIENDS.isFriend(entityPlayer.getName()) || (boolean)AutoEZ.INSTANCE.whenFriend.getValue())) {
                    if (AutoEZ.INSTANCE.poped.getValue()) {
                        PopCounter.mc.player.connection.sendPacket((Packet)new CPacketChatMessage((String)AutoEZ.INSTANCE.EzString.getValue() + " " + entityPlayer.getName() + " popping " + intValue + " Totem!"));
                    }
                    else {
                        PopCounter.mc.player.connection.sendPacket((Packet)new CPacketChatMessage((String)AutoEZ.INSTANCE.EzString.getValue() + " " + entityPlayer.getName()));
                    }
                }
            }
        }
        else if (AutoEZ.INSTANCE.isOn() && (!Managers.FRIENDS.isFriend(entityPlayer.getName()) || (boolean)AutoEZ.INSTANCE.whenFriend.getValue())) {
            if (AutoEZ.INSTANCE.poped.getValue()) {
                PopCounter.mc.player.connection.sendPacket((Packet)new CPacketChatMessage((String)AutoEZ.INSTANCE.EzString.getValue() + " " + entityPlayer.getName() + " popping 0 Totem!"));
            }
            else {
                PopCounter.mc.player.connection.sendPacket((Packet)new CPacketChatMessage((String)AutoEZ.INSTANCE.EzString.getValue() + " " + entityPlayer.getName()));
            }
        }
    }
    
    public PopCounter() {
        super("PopCounter", "Counts other players totem pops", Category.MISC);
        PopCounter.INSTANCE = this;
    }
    
    static {
        TotemPopContainer = new HashMap();
        PopCounter.INSTANCE = new PopCounter();
    }
}
