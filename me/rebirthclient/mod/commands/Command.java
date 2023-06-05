//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.commands;

import me.rebirthclient.mod.*;
import me.rebirthclient.mod.modules.impl.hud.*;
import me.rebirthclient.api.managers.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.util.text.*;
import java.util.regex.*;

public abstract class Command extends Mod
{
    protected final String name;
    protected final String[] commands;
    
    public String complete(final String s) {
        if (this.name.toLowerCase().startsWith(s)) {
            return this.name;
        }
        final String[] commands = this.commands;
        final int length = commands.length;
        int n = 0;
        if (n >= length) {
            return null;
        }
        final String s2 = commands[n];
        if (s2.toLowerCase().startsWith(s)) {
            return s2;
        }
        ++n;
        return null;
    }
    
    public Command(final String name, final String[] commands) {
        super(name);
        this.name = name;
        this.commands = commands;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    public Command(final String name) {
        super(name);
        this.name = name;
        this.commands = new String[] { "" };
    }
    
    public static void sendMessage(final String s) {
        Notifications.notifyList.add(new Notifications.Notifys(s));
        sendSilentMessage(Managers.TEXT.getPrefix() + ChatFormatting.GRAY + s);
    }
    
    public static void sendMessageWithID(final String s, final int n) {
        if (!Mod.nullCheck()) {
            Command.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new ChatMessage(Managers.TEXT.getPrefix() + ChatFormatting.GRAY + s), n);
        }
    }
    
    public abstract void execute(final String[] p0);
    
    public static void sendSilentMessage(final String s) {
        if (nullCheck()) {
            return;
        }
        Command.mc.player.sendMessage((ITextComponent)new ChatMessage(s));
    }
    
    public static String getCommandPrefix() {
        return Managers.COMMANDS.getCommandPrefix();
    }
    
    public String[] getCommands() {
        return this.commands;
    }
    
    public static class ChatMessage extends TextComponentBase
    {
        private final String text;
        
        public ITextComponent shallowCopy() {
            return (ITextComponent)new ChatMessage(this.text);
        }
        
        public ITextComponent createCopy() {
            return null;
        }
        
        public String getUnformattedComponentText() {
            return this.text;
        }
        
        public ChatMessage(final String s) {
            final Matcher matcher = Pattern.compile("&[0123456789abcdefrlosmk]").matcher(s);
            final StringBuffer sb = new StringBuffer();
            if (matcher.find()) {
                matcher.appendReplacement(sb, matcher.group().substring(1));
                throw null;
            }
            matcher.appendTail(sb);
            this.text = sb.toString();
        }
    }
}
