//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.commands.impl;

import me.rebirthclient.mod.commands.*;
import me.rebirthclient.api.managers.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;

public class HelpCommand extends Command
{
    public void execute(final String[] array) {
        sendMessage("Commands: ");
        final Iterator<Command> iterator = Managers.COMMANDS.getCommands().iterator();
        if (iterator.hasNext()) {
            sendMessage(ChatFormatting.GRAY + Managers.COMMANDS.getCommandPrefix() + iterator.next().getName());
        }
    }
    
    public HelpCommand() {
        super("help");
    }
}
