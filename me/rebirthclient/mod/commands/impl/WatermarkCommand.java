//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.commands.impl;

import me.rebirthclient.mod.commands.*;
import me.rebirthclient.mod.modules.impl.client.*;
import com.mojang.realmsclient.gui.*;

public class WatermarkCommand extends Command
{
    public void execute(final String[] array) {
        if (array.length == 2) {
            final FontMod instance = FontMod.INSTANCE;
            final boolean on = instance.isOn();
            if (array[0] != null) {
                if (on) {
                    instance.disable();
                }
                HUD.INSTANCE.watermarkString.setValue(array[0]);
                if (on) {
                    instance.enable();
                }
                sendMessage("Watermark set to " + ChatFormatting.GREEN + array[0]);
            }
            else {
                sendMessage("Not a valid command... Possible usage: <New Watermark>");
            }
        }
    }
    
    public WatermarkCommand() {
        super("watermark", new String[] { "<watermark>" });
    }
}
