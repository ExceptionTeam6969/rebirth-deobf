//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.client;

import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;
import java.util.function.*;
import java.awt.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import com.mojang.realmsclient.gui.*;
import java.text.*;
import java.util.*;
import net.minecraft.util.text.*;

public class Chat extends Module
{
    public final Setting bracket;
    public static Chat INSTANCE;
    public final Setting colorRect;
    public final Setting infinite;
    public final Setting time;
    private final Setting suffixString;
    public final Setting color;
    public final Setting animation;
    public final Setting suffix;
    public final Setting rect;
    
    private boolean lambda$new$2(final Bracket bracket) {
        return this.time.isOpen();
    }
    
    public Chat() {
        super("Chat", "Modifies your chat", Category.CLIENT);
        this.animation = this.add(new Setting("Animation", true));
        this.rect = this.add(new Setting("Rect", true).setParent());
        this.colorRect = this.add(new Setting("ColorRect", false, this::lambda$new$0));
        this.infinite = this.add(new Setting("InfiniteChat", true));
        this.suffix = this.add(new Setting("Suffix", false).setParent());
        this.suffixString = this.add(new Setting("suffixString", "| NewRebirth", this::lambda$new$1));
        this.time = this.add(new Setting("TimeStamps", false).setParent());
        this.bracket = this.add(new Setting("Bracket", Bracket.TRIANGLE, this::lambda$new$2));
        this.color = this.add(new Setting("Color", new Color(134, 173, 255, 255)).hideAlpha());
        Chat.INSTANCE = this;
    }
    
    private boolean lambda$new$0(final Boolean b) {
        return this.rect.isOpen();
    }
    
    private boolean lambda$new$1(final String s) {
        return this.suffix.isOpen();
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (fullNullCheck()) {
            return;
        }
        if ((boolean)this.suffix.getValue() && send.getPacket() instanceof CPacketChatMessage) {
            final CPacketChatMessage cPacketChatMessage = (CPacketChatMessage)send.getPacket();
            final String getMessage = cPacketChatMessage.getMessage();
            if (getMessage.startsWith("/") || getMessage.startsWith("!") || getMessage.endsWith((String)this.suffixString.getValue())) {
                return;
            }
            String message = getMessage + " " + (String)this.suffixString.getValue();
            if (message.length() >= 256) {
                message = message.substring(0, 256);
            }
            cPacketChatMessage.message = message;
        }
    }
    
    @SubscribeEvent
    public void onClientChatReceived(final ClientChatReceivedEvent clientChatReceivedEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (this.time.getValue()) {
            clientChatReceivedEvent.setMessage(new TextComponentString(ChatFormatting.GRAY + ((this.bracket.getValue() == Bracket.TRIANGLE) ? "<" : "[") + ChatFormatting.WHITE + new SimpleDateFormat("HH:mm").format(new Date()) + ChatFormatting.GRAY + ((this.bracket.getValue() == Bracket.TRIANGLE) ? ">" : "]") + ChatFormatting.RESET + " ").appendSibling(clientChatReceivedEvent.getMessage()));
        }
    }
    
    private enum Bracket
    {
        SQUARE("SQUARE", 0), 
        TRIANGLE("TRIANGLE", 1);
        
        private static final Bracket[] $VALUES;
        
        private Bracket(final String s, final int n) {
        }
        
        static {
            $VALUES = new Bracket[] { Bracket.SQUARE, Bracket.TRIANGLE };
        }
    }
}
