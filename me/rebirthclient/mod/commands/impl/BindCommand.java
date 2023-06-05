//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.commands.impl;

import me.rebirthclient.mod.commands.*;
import me.rebirthclient.api.managers.*;
import com.mojang.realmsclient.gui.*;
import org.lwjgl.input.*;
import me.rebirthclient.mod.modules.settings.*;
import me.rebirthclient.mod.modules.*;

public class BindCommand extends Command
{
    public BindCommand() {
        super("bind", new String[] { "<module>", "<bind>" });
    }
    
    public void execute(final String[] array) {
        if (array.length == 1) {
            sendMessage("Please specify a module.");
            return;
        }
        final String s = array[1];
        final Module moduleByName = Managers.MODULES.getModuleByName(array[0]);
        if (moduleByName == null) {
            sendMessage("Unknown module '" + moduleByName + "'!");
            return;
        }
        if (s == null) {
            sendMessage(moduleByName.getName() + " is bound to " + ChatFormatting.GRAY + moduleByName.getBind().toString());
            return;
        }
        int keyIndex = Keyboard.getKeyIndex(s.toUpperCase());
        if (Integer.valueOf("none".toUpperCase().hashCode()).equals(s.toUpperCase().hashCode())) {
            keyIndex = -1;
        }
        if (keyIndex == 0) {
            sendMessage("Unknown key '" + s + "'!");
            return;
        }
        moduleByName.bind.setValue(new Bind(keyIndex));
        sendMessage("Bind for " + ChatFormatting.GREEN + moduleByName.getName() + ChatFormatting.WHITE + " set to " + ChatFormatting.GRAY + s.toUpperCase());
    }
}
