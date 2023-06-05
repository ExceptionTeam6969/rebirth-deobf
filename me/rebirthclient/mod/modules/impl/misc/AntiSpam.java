//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.modules.impl.misc;

import me.rebirthclient.mod.modules.settings.*;
import java.util.*;
import me.rebirthclient.api.events.impl.*;
import net.minecraft.network.play.server.*;
import me.rebirthclient.mod.commands.*;
import net.minecraft.util.text.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.rebirthclient.mod.modules.*;

public class AntiSpam extends Module
{
    int size;
    public static AntiSpam INSTANCE;
    private final Setting number;
    private static final HashMap StringMap;
    
    static {
        AntiSpam.INSTANCE = new AntiSpam();
        StringMap = new HashMap();
    }
    
    @SubscribeEvent
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (fullNullCheck()) {
            return;
        }
        if (receive.isCanceled()) {
            return;
        }
        if (!(receive.getPacket() instanceof SPacketChat)) {
            return;
        }
        receive.setCanceled(true);
        final ITextComponent getChatComponent = ((SPacketChat)receive.getPacket()).getChatComponent();
        final String getFormattedText = getChatComponent.getFormattedText();
        if (AntiSpam.StringMap.containsKey(getFormattedText)) {
            ((text)AntiSpam.StringMap.get(getFormattedText)).addNumber();
            AntiSpam.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new Command.ChatMessage(getFormattedText + (this.number.getValue() ? (" ¡ì7x" + ((text)AntiSpam.StringMap.get(getFormattedText)).number) : "")), ((text)AntiSpam.StringMap.get(getFormattedText)).size);
            return;
        }
        ++this.size;
        AntiSpam.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(getChatComponent, this.size);
        AntiSpam.StringMap.put(getFormattedText, new text(this.size));
    }
    
    public AntiSpam() {
        super("AntiSpam", "Anti Spam", Category.MISC);
        this.number = this.add(new Setting("Number", true));
        this.size = 2;
        AntiSpam.INSTANCE = this;
    }
    
    private static class text
    {
        int number;
        int size;
        
        public void addNumber() {
            ++this.number;
        }
        
        public text(final int size) {
            this.number = 1;
            this.size = size;
        }
    }
}
