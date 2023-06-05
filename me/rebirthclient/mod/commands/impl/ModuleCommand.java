//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.commands.impl;

import me.rebirthclient.mod.commands.*;
import me.rebirthclient.api.managers.*;
import me.rebirthclient.mod.modules.settings.*;
import com.google.gson.*;
import com.mojang.realmsclient.gui.*;
import me.rebirthclient.api.managers.impl.*;
import me.rebirthclient.mod.*;
import me.rebirthclient.mod.modules.*;
import java.util.*;

public class ModuleCommand extends Command
{
    public ModuleCommand() {
        super("module", new String[] { "<module>", "<set/reset>", "<setting>", "<value>" });
    }
    
    public void execute(final String[] array) {
        if (array.length == 1) {
            sendMessage("module <module> <set/reset> <setting> <value>");
            return;
        }
        final Module moduleByName = Managers.MODULES.getModuleByName(array[0]);
        if (moduleByName == null) {
            final Module moduleByName2 = Managers.MODULES.getModuleByName(array[0]);
            if (moduleByName2 == null) {
                sendMessage("This module doesnt exist.");
                return;
            }
            sendMessage(" This is the original name of the module. Its current name is: " + moduleByName2.getName());
        }
        else if (array.length == 2) {
            sendMessage(moduleByName.getName() + " : " + moduleByName.getDescription());
            final Iterator iterator = moduleByName.getSettings().iterator();
            if (iterator.hasNext()) {
                final Setting setting = iterator.next();
                sendMessage(setting.getName() + " : " + setting.getValue());
            }
        }
        else {
            if (array.length == 3) {
                if (Integer.valueOf("set".toUpperCase().hashCode()).equals(array[1].toUpperCase().hashCode())) {
                    sendMessage("Please specify a setting.");
                }
                else if (Integer.valueOf("reset".toUpperCase().hashCode()).equals(array[1].toUpperCase().hashCode())) {
                    final Iterator iterator2 = moduleByName.getSettings().iterator();
                    if (iterator2.hasNext()) {
                        final Setting setting2 = iterator2.next();
                        setting2.setValue(setting2.getDefaultValue());
                    }
                }
                else {
                    sendMessage("This command doesnt exist.");
                }
                return;
            }
            if (array.length == 4) {
                sendMessage("Please specify a value.");
                return;
            }
            final Setting settingByName;
            if (array.length == 5 && (settingByName = moduleByName.getSettingByName(array[2])) != null) {
                final JsonParser jsonParser = new JsonParser();
                if (Integer.valueOf("String".toUpperCase().hashCode()).equals(settingByName.getType().toUpperCase().hashCode())) {
                    settingByName.setValue(array[3]);
                    sendMessage(ChatFormatting.DARK_GRAY + moduleByName.getName() + " " + settingByName.getName() + " has been set to " + array[3] + ".");
                    return;
                }
                try {
                    if (Integer.valueOf("Enabled".toUpperCase().hashCode()).equals(settingByName.getName().toUpperCase().hashCode())) {
                        if (Integer.valueOf("true".toUpperCase().hashCode()).equals(array[3].toUpperCase().hashCode())) {
                            moduleByName.enable();
                        }
                        if (Integer.valueOf("false".toUpperCase().hashCode()).equals(array[3].toUpperCase().hashCode())) {
                            moduleByName.disable();
                        }
                    }
                    ConfigManager.setValueFromJson((Mod)moduleByName, settingByName, jsonParser.parse(array[3]));
                }
                catch (Exception ex) {
                    sendMessage("Bad Value! This setting requires a: " + settingByName.getType() + " value.");
                    return;
                }
                sendMessage(ChatFormatting.GRAY + moduleByName.getName() + " " + settingByName.getName() + " has been set to " + array[3] + ".");
            }
        }
    }
}
