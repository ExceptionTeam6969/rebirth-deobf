//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.api.managers.impl;

import me.rebirthclient.mod.*;
import me.rebirthclient.mod.commands.impl.*;
import com.mojang.realmsclient.gui.*;
import me.rebirthclient.mod.commands.*;
import java.util.*;

public class CommandManager extends Mod
{
    private String prefix;
    private String clientMessage;
    private final ArrayList commands;
    
    public String getClientMessage() {
        return this.clientMessage;
    }
    
    public CommandManager() {
        super("Command");
        this.commands = new ArrayList();
        this.clientMessage = "[Rebirth]";
        this.prefix = ";";
        this.commands.add(new BindCommand());
        this.commands.add(new ModuleCommand());
        this.commands.add(new PrefixCommand());
        this.commands.add(new ConfigCommand());
        this.commands.add(new FriendCommand());
        this.commands.add(new HelpCommand());
        this.commands.add(new UnloadCommand());
        this.commands.add(new ReloadSoundCommand());
        this.commands.add(new CoordsCommand());
        this.commands.add(new ShrugCommand());
        this.commands.add(new WatermarkCommand());
    }
    
    public void setClientMessage(final String clientMessage) {
        this.clientMessage = clientMessage;
    }
    
    public void executeCommand(final String s) {
        final String[] split = s.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        final String substring = split[0].substring(1);
        final String[] removeElement = removeElement(split, 0);
        int n = 0;
        if (n < removeElement.length) {
            if (removeElement[n] != null) {
                removeElement[n] = strip(removeElement[n]);
            }
            ++n;
            return;
        }
        final Iterator<Command> iterator = (Iterator<Command>)this.commands.iterator();
        if (!iterator.hasNext()) {
            Command.sendMessage(ChatFormatting.GRAY + "Command not found, type 'help' for the commands list.");
            return;
        }
        final Command command = iterator.next();
        if (!Integer.valueOf(substring.toUpperCase().hashCode()).equals(command.getName().toUpperCase().hashCode())) {
            return;
        }
        command.execute(split);
    }
    
    public String getCommandPrefix() {
        return this.prefix;
    }
    
    public ArrayList getCommands() {
        return this.commands;
    }
    
    public static String[] removeElement(final String[] array, final int n) {
        final LinkedList<String> list = new LinkedList<String>();
        int n2 = 0;
        if (n2 < array.length) {
            if (n2 != n) {
                list.add(array[n2]);
            }
            ++n2;
            return null;
        }
        return list.toArray(array);
    }
    
    private static String strip(final String s) {
        if (s.startsWith("\"") && s.endsWith("\"")) {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }
    
    public Command getCommandByName(final String s) {
        final Iterator<Command> iterator = (Iterator<Command>)this.commands.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        final Command command = iterator.next();
        if (!Integer.valueOf(s.hashCode()).equals(command.getName().hashCode())) {
            return null;
        }
        return command;
    }
    
    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }
}
