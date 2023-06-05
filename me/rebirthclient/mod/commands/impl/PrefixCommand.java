//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.commands.impl;

import me.rebirthclient.mod.commands.*;
import com.mojang.realmsclient.gui.*;
import me.rebirthclient.api.managers.*;

public class PrefixCommand extends Command
{
    public PrefixCommand() {
        super("prefix", new String[] { "<char>" });
    }
    
    public void execute(final String[] array) {
        if (array.length == 1) {
            Command.sendMessage(ChatFormatting.GREEN + "The current prefix is " + Managers.COMMANDS.getCommandPrefix());
            return;
        }
        Managers.COMMANDS.setPrefix(array[0]);
        Command.sendMessage("Prefix changed to " + ChatFormatting.GRAY + array[0]);
    }
}
