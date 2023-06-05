//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\23204\Desktop\cn×îÇ¿·´±àÒëÆ÷\1.12 stable mappings"!

//Decompiled by Procyon!

package me.rebirthclient.mod.commands.impl;

import me.rebirthclient.mod.commands.*;
import java.awt.*;
import java.awt.datatransfer.*;
import com.mojang.realmsclient.gui.*;

public class ShrugCommand extends Command
{
    public ShrugCommand() {
        super("shrug");
    }
    
    public void execute(final String[] array) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection("?\\_(\u30c4)_/?"), null);
        Command.sendMessage(ChatFormatting.GRAY + "copied le shrug to ur clipboard");
    }
}
