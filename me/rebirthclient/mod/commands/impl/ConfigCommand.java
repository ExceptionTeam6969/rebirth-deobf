//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.commands.impl;

import me.rebirthclient.mod.commands.*;
import java.io.*;
import java.util.function.*;
import java.util.stream.*;
import me.rebirthclient.api.managers.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;

public class ConfigCommand extends Command
{
    public ConfigCommand() {
        super("config", new String[] { "<save/load>" });
    }
    
    public void execute(final String[] array) {
        if (array.length == 1) {
            sendMessage("You`ll find the config files in your gameProfile directory under Rebirth/config");
            return;
        }
        if (array.length == 2) {
            if (Integer.valueOf(array[0].hashCode()).equals("list".hashCode())) {
                final String s = "Configs: ";
                final List<? super File> list = Arrays.stream(new File("Rebirth/").listFiles()).filter(File::isDirectory).filter(ConfigCommand::lambda$execute$0).collect((Collector<? super File, ?, List<? super File>>)Collectors.toList());
                final StringBuilder sb = new StringBuilder(s);
                final Iterator<File> iterator = list.iterator();
                if (iterator.hasNext()) {
                    sb.append(iterator.next().getName() + ", ");
                    return;
                }
                sendMessage(String.valueOf(sb));
            }
            else {
                sendMessage("Not a valid command... Possible usage: <list>");
            }
        }
        if (array.length >= 3) {
            final String s2 = array[0];
            int n = -1;
            switch (s2.hashCode()) {
                case 3522941: {
                    if (Integer.valueOf("save".hashCode()).equals(s2.hashCode())) {
                        n = 0;
                        break;
                    }
                    break;
                }
                case 3327206: {
                    if (Integer.valueOf("load".hashCode()).equals(s2.hashCode())) {
                        n = 1;
                        break;
                    }
                    break;
                }
            }
            switch (n) {
                case 0: {
                    Managers.CONFIGS.saveConfig(array[1]);
                    sendMessage(ChatFormatting.GREEN + "Config '" + array[1] + "' has been saved.");
                }
                case 1: {
                    if (Managers.CONFIGS.configExists(array[1])) {
                        Managers.CONFIGS.loadConfig(array[1]);
                        sendMessage(ChatFormatting.GREEN + "Config '" + array[1] + "' has been loaded.");
                    }
                    else {
                        sendMessage(ChatFormatting.RED + "Config '" + array[1] + "' does not exist.");
                    }
                }
                default: {
                    sendMessage("Not a valid command... Possible usage: <save/load>");
                    break;
                }
            }
        }
    }
    
    private static boolean lambda$execute$0(final File file) {
        return !Integer.valueOf("util".hashCode()).equals(file.getName().hashCode());
    }
}
